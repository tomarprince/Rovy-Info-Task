/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tera.codingtest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Prince
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Response {

    private String response;
    private String message = "SUCCESS";
}
