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
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author aziz
 */
@Entity
public class Price implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double costPrice;
    private Double sellingPrice;
    
    @OneToOne(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    private Supplier supplier;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the costPrice
     */
    public Double getCostPrice() {
        return costPrice;
    }

    /**
     * @param costPrice the costPrice to set
     */
    public void setCostPrice(Double costPrice) {
        this.costPrice = costPrice;
    }

    /**
     * @return the sellingPrice
     */
    public Double getSellingPrice() {
        return sellingPrice;
    }

    /**
     * @param sellingPrice the sellingPrice to set
     */
    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    /**
     * @return the supplier
     */
    public Supplier getSupplier() {
        return supplier;
    }

    /**
     * @param supplier the supplier to set
     */
    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }
    
}
