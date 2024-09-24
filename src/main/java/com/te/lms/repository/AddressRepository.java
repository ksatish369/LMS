package com.te.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
