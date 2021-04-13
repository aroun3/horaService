package com.visitor.repositories;

import com.visitor.entities.Hparams;
import com.visitor.payload.response.HparamResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Created by olivier on 16/09/2020.
 */
@Repository
public interface HparamsRepository extends JpaRepository<Hparams, Integer> {
    Optional<Hparams> findById(Integer id);

    @Query(value = "SELECT hp.id AS id, hp.min_checkin AS minCheckin, hp.h_checkin AS hCheckin, hp.late_checkin AS lateCheckin, hp.max_checkin AS maxCheckin, hp.min_checkout AS minCheckout," +
            " hp.early_checkout AS hearlyCheckout, hp.h_checkout AS hCheckout, hp.max_checkout AS maxChectout, hp.callback_date AS callbackDate                             ," +
            " (SELECT pa.area_name FROM personnel_area pa WHERE pa.id = hp.area_id) AS area FROM h_params hp", nativeQuery = true)
    List<HparamResponse> getAllHparam();

}
