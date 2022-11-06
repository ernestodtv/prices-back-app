package com.zara.pricesbackapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "BRANDS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    @Id
    @SequenceGenerator(name = "BRANDS_SEQ", sequenceName = "BRANDS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BRANDS_SEQ")
    private Long id;

    private String name;

}
