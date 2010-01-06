/*
 * Copyright 2004-2010 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.eclipselink.jpa.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;


/**
 * @author Hidenoshin Yoshida
 *
 */
@Entity
public class Normal implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 897724740502061595L;

    @Id
    @GeneratedValue(generator = "NormalGenerator", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "NormalGenerator", sequenceName = "SEQ_NOMAL", allocationSize = 1)
    private Integer id;
    
    private String name;
    
    private BigDecimal amount;
    
    @Temporal(TemporalType.DATE)
    private Date start_date;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date created_at;

    @Version
    private Integer lock_version;

    
    public Integer getId() {
        return id;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }

    
    public String getName() {
        return name;
    }

    
    public void setName(String name) {
        this.name = name;
    }

    
    public BigDecimal getAmount() {
        return amount;
    }

    
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    
    public Date getStart_date() {
        return start_date;
    }

    
    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    
    public Date getCreated_at() {
        return created_at;
    }

    
    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    
    public Integer getLock_version() {
        return lock_version;
    }

    
    public void setLock_version(Integer lock_version) {
        this.lock_version = lock_version;
    }
}
