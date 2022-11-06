package com.zara.pricesbackapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "PRICES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    @Id
    @SequenceGenerator(name = "PRICES_SEQ", sequenceName = "PRICES_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PRICES_SEQ")
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "BRANDS_ID")
    private Brand brand;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @ManyToOne(optional = false)
    @JoinColumn(name = "TARIFFS_ID")
    private Tariff tariff;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PRODUCTS_ID")
    private Product product;

    private Integer priority;

    private Double pvp;

    @Enumerated(EnumType.STRING)
    private Currency currency;

}
