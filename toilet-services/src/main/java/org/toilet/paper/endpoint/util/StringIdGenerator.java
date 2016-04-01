/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.toilet.paper.endpoint.util;

import java.util.UUID;

/**
 *
 * @author salaboy
 */
public class StringIdGenerator {
    public static String generateId(){
        return UUID.randomUUID().toString().substring(0, 12);
    }
}
