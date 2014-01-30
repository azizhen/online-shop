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


package co.za.hendricks.domain;

import java.io.Serializable;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author aziz
 */
@Entity
@DiscriminatorValue("COMPANY")
public class Company extends User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String vatNo;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the vatNo
     */
    public String getVatNo() {
        return vatNo;
    }

    /**
     * @param vatNo the vatNo to set
     */
    public void setVatNo(String vatNo) {
        this.vatNo = vatNo;
    }
    
}
