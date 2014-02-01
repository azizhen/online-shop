/*
 * Copyright (C) 2014 aziz
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package za.co.hendricks.services;

import co.za.hendricks.utility.ApplicationUtility;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author aziz
 */
public class ServicesTest {
    /**
     * If you encrypt the same password twice it should generate the same hashed password
     */
    @Test
    public void testEncryptionOneWay(){
        String password = "password";
        String hashedPassword = ApplicationUtility.getInstance().encrypt(password);
        
        String samePassword = "password";
        String hashedPassword2 = ApplicationUtility.getInstance().encrypt(samePassword);
        
        Assert.assertEquals(hashedPassword, hashedPassword2);
    }
    
    /**
     * Ensure that the hash won't generate the same hash for 2 different passwords
     */
    @Test
    public void testEncryptDifferentOutputs(){
        String password = "password1";
        String hashedPassword = ApplicationUtility.getInstance().encrypt(password);
        
        String differentPassword = "password2";
        String hashedPassword2 = ApplicationUtility.getInstance().encrypt(differentPassword);
        
        Assert.assertNotSame(hashedPassword, hashedPassword2);
    }
    
 
            
}
