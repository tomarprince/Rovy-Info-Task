/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tera.codingtest.repository;

import com.tera.codingtest.model.User;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;  
/**
 *
 * @author Prince
 */
@Repository
public interface UserRepository extends JpaRepository < User, Long > {
    User findByEmail(String email);
    User findByfirstName(String firstName);
}
