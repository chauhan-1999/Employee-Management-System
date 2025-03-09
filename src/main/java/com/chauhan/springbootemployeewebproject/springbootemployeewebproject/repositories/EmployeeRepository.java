package com.chauhan.springbootemployeewebproject.springbootemployeewebproject.repositories;

import com.chauhan.springbootemployeewebproject.springbootemployeewebproject.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
}
