package com.visitor.controller;

import com.visitor.entities.ERole;
import com.visitor.entities.Role;
import com.visitor.entities.User;

import com.visitor.payload.ApiResponse;
import com.visitor.payload.AppConstants;
import com.visitor.payload.request.LoginRequest;
import com.visitor.payload.request.SignupRequest;
import com.visitor.payload.response.JwtResponse;
import com.visitor.payload.response.MessageResponse;

import com.visitor.repositories.RoleRepository;
import com.visitor.repositories.UserRepository;

import com.visitor.security.jwt.JwtUtils;
import com.visitor.security.services.UserDetailsImpl;

import com.visitor.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private static final Logger logger = Logger.getLogger(AuthController.class.getName());
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	UserService userService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) {
		try {
			
			System.out.println("================================= user name : "+loginRequest.getUsername());
			Optional<User> user = userRepository.findByUsername(loginRequest.getUsername());
			
			System.out.println("============================= encoder : "+encoder.encode("admin123"));
			if(user.isPresent()){
				if(user.get().getStatus().equals((short)1)){
					
					Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
	
					SecurityContextHolder.getContext().setAuthentication(authentication);
					String jwt = jwtUtils.generateJwtToken(authentication);
					
					UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
					
					List<String> roles = userDetails.getAuthorities().stream()
							.map(item -> item.getAuthority())
							.collect(Collectors.toList());
					
					System.out.println("username ============>> " + loginRequest.getUsername());
					
	
					return ResponseEntity.ok(new ApiResponse(
						"v1", 
						true, 
						"Succés", 
						new JwtResponse(jwt,
							userDetails.getId(),
							userDetails.getUsername(),
							userDetails.getEmail(),
							roles, AppConstants.STATUS_CODE_SUCCESS[0])
						)
					);
	
				}else{
					throw new IllegalStateException("Vous ne pouvez pas vous connectez car votre compte est desactivé");
				}
			}
			else{
				throw new IllegalStateException("Vous ne pouvez pas vous connectez car votre compte est desactivé");
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.badRequest().body(new JwtResponse(null,
					0,
					"",
					"",
					null, AppConstants.STATUS_CODE_BAD_CREDENTIALS[0], ex.getMessage()));
		}

	}

	@PostMapping("/signup")
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public ResponseEntity<?> registerUser(@RequestBody @Valid SignupRequest signUpRequest) {

		System.out.println("SignupRequest "+signUpRequest);

		//Verify Username
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Erreur: le nom utilisateur est deja utilisé!"));
		}

		//Verify email
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Erreur: Email est deja utilisé!"));
		}

		//Verify phone
		if (userRepository.existsByPhone(signUpRequest.getPhone())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Erreur: Le telephone est deja utilisé!"));
		}

		// Create new user's account
		signUpRequest.setStatus((short) 1);
		
		User user = new User(signUpRequest.getFullName(),
				signUpRequest.getUsername(),signUpRequest.getPhone(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getStatus());

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
					.orElseThrow(() -> new RuntimeException("Erreur: Le role est introuvable."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "ROLE_ADMIN":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Erreur: Le role est introuvable."));
						roles.add(adminRole);
						break;
					default:
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Erreur: Le role est introuvable."));
						roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("L'utilisateur est enregistré avec succès!"));
	}


	@GetMapping("/users")
	public List<User> getAllUsers() {
		return userService.getAll();
	}


	@PutMapping("/users/{id}")
	public ResponseEntity<?> updateUser(@Valid @RequestBody SignupRequest signUpRequest, @PathVariable Integer id) {
		try{
		// Create update user's account
			signUpRequest.setStatus((short) 1);
			User user = new User(signUpRequest.getFullName(),
			signUpRequest.getUsername(), signUpRequest.getPhone(), signUpRequest.getEmail(),
			encoder.encode(signUpRequest.getPassword()),signUpRequest.getStatus());
			user.setId(id);

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Erreur: Le role est introuvable."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "ROLE_ADMIN":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Erreur: Le role est introuvable."));
						roles.add(adminRole);

						break;
					default:
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Erreur: Le role est introuvable."));
						roles.add(userRole);
				}
			});
		}

			user.setRoles(roles);
			User data = userService.update(user);
			return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_UPDATED[1], data));

		}catch (Exception ex){
			return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

		}
	}


	@GetMapping("/users/{id}")
	public  ResponseEntity<?> getUser (@PathVariable Integer id){
		try{
			User data = userService.getOneById(id);
			return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_UPDATED[1], data));
		}catch (Exception ex){
			return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1], ex.getMessage()));

		}
	}

	@GetMapping("/users/{userId}/{status}")
	public ResponseEntity<?> activateUsers(@PathVariable Integer userId, @PathVariable short status) {
		Boolean bool =  userService.activateUser(userId, status);
		if(bool) {
			if(status == 1){
				return ResponseEntity.ok().body(new ApiResponse(true, AppConstants.STATUS_CODE_UPDATED[1],"Utilisateur activé "));

			}else {
				return ResponseEntity.ok().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1],"Utilisateur desactivé "));
			}
		}else{

			return ResponseEntity.badRequest().body(new ApiResponse(false, AppConstants.STATUS_CODE_ERROR[1],"Erreur d'activation "));
		}
	}

}