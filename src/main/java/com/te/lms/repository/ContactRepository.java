package com.te.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.te.lms.entity.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer> {

}
