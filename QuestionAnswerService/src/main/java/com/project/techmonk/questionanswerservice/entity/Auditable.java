package com.project.techmonk.questionanswerservice.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Data
public abstract class Auditable {

    @CreatedDate
    @Column(nullable = false, updatable = false)
    protected Timestamp createdDate;

    @LastModifiedDate
    protected Timestamp lastModifiedDate;
}