package com.zara.pricesbackapp.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TARIFFS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tariff {

    @Id
    @SequenceGenerator(name = "TARIFFS_SEQ", sequenceName = "TARIFFS_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TARIFFS_SEQ")
    private Long id;

}
