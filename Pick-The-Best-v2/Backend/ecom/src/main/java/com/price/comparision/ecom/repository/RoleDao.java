package com.price.comparision.ecom.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.price.comparision.ecom.bean.Role;



@Repository
public interface RoleDao extends CrudRepository<Role,String>{

}
