package com.nttdata.afp.microservice.entity;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Data
@Entity
public class Withdrawal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name="afp", nullable = false)
    private Afp afp;
    @Column(precision=12, scale=2, nullable = false)
    private BigDecimal withdrawalAmount;
    @Column(nullable = false)
    private String status = "active";
    @CreationTimestamp
    private OffsetDateTime createdAt;
}
