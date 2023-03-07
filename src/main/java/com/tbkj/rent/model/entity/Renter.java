package com.tbkj.rent.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Table(name = "Renters")
@Entity
public class Renter extends Base {
    @Column
    private String name;

    @Column(unique = true, length = 10)
    private String mobile;
}
