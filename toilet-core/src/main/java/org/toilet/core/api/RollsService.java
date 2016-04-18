/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.toilet.core.api;

import java.util.List;
import org.toilet.core.exceptions.BusinessException;
import org.toilet.model.Roll;


/**
 *
 * @author salaboy
 */
public interface RollsService {

   
    public List<Roll> getAllRolls() throws BusinessException;
    
    
    public void addRoll(Roll roll) throws BusinessException;


    
}
