/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tera.codingtest.repository;

import com.tera.codingtest.model.Todo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Prince
 */
@Repository
public interface TodoRepository extends JpaRepository < Todo, Long >{
    
    List<Todo> findByUserId(Long userId);
}
