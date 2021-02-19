package com.visitor.entities;

import com.visitor.entities.audit.UserDateAudit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.UnsupportedEncodingException;

@Entity
@Table(name = "visitors")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Visitor extends UserDateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank
    @Size(max =100)
    private String full_name;

    @NotBlank
    @Size(max = 20)
    @Column(unique = true)
    private String contact;

    @ManyToOne
    @JoinColumn(name="type_piece_id")
    private TypePiece typePiece;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @Column(name="photo", unique =true, nullable = true,length = 80000000)
    private byte[] photo;

    private String photoName;

    @Transient
    private String photoTransient;

    @NotBlank
    private Short status;


    public String getPhotoTransient() {
        if(getPhoto() != null) {
            byte[] encodeBase64 = Base64.encodeBase64(getPhoto());
            String base64Encoded = null;
            try {
                base64Encoded = new String(encodeBase64, "UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            String img = "data:image/jpeg;base64,"+ base64Encoded;
            return img;
        }else{
            return null;
        }
    }


}
