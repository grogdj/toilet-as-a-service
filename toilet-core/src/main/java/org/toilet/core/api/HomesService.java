/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.toilet.core.api;

import java.util.List;
import org.toilet.core.exceptions.BusinessException;
import org.toilet.model.Home;


/**
 *
 * @author salaboy
 */
public interface HomesService {

    
    public List<Home> getAllHomes() throws BusinessException;
    
    
    public Home getHomeById(String id) throws BusinessException;
    
    
    public String addHome(Home home) throws BusinessException;

   
    public void deleteHome(String id) throws BusinessException;
    
    
    public void updateHome(Home home) throws BusinessException;

    
}
