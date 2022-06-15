package com.bgroup.mis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bgroup.mis.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
	
}
