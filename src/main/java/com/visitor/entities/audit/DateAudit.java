package com.visitor.entities.audit;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;

/**
 * Created by olivier on 15/09/2020.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
public abstract class DateAudit implements Serializable {

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;

    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy à HH:mm:ss")
    @JsonFormat(pattern = "dd-MM-yyyy à HH:mm:ss", timezone = "UTC")
    public Instant getCreatedAt() {
        Duration duration = Duration.ofHours(1);
        return createdAt.plus(duration);
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

   // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy à HH:mm:ss")
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Instant updatedAt) {
        this.updatedAt = updatedAt;
    }

}
