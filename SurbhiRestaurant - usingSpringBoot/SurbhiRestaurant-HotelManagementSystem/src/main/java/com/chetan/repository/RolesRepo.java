package com.chetan.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chetan.model.Roles;

@Repository
public interface RolesRepo extends JpaRepository<Roles,Integer> {

	public Roles getRolesByName(String string);

}
