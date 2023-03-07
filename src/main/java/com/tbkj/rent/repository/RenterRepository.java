package com.tbkj.rent.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tbkj.rent.model.entity.Renter;

public interface RenterRepository extends JpaRepository<Renter, Long> {

}
