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
import java.util.Date;

import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author Hidenoshin Yoshida
 *
 */
@Embeddable
public class EmbeddedSampleId implements Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -4093884686902774238L;

    private Integer pk1;
    
    @Temporal(TemporalType.DATE)
    private Date pk2;

    
    /**
     * 
     */
    public EmbeddedSampleId() {
    }


    /**
     * @param pk1
     * @param pk2
     */
    public EmbeddedSampleId(Integer pk1, Date pk2) {
        this.pk1 = pk1;
        this.pk2 = pk2;
    }


    public Integer getPk1() {
        return pk1;
    }

    
    public void setPk1(Integer pk1) {
        this.pk1 = pk1;
    }

    
    public Date getPk2() {
        return pk2;
    }

    
    public void setPk2(Date pk2) {
        this.pk2 = pk2;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((pk1 == null) ? 0 : pk1.hashCode());
        result = prime * result + ((pk2 == null) ? 0 : pk2.hashCode());
        return result;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final EmbeddedSampleId other = (EmbeddedSampleId) obj;
        if (pk1 == null) {
            if (other.pk1 != null)
                return false;
        } else if (!pk1.equals(other.pk1))
            return false;
        if (pk2 == null) {
            if (other.pk2 != null)
                return false;
        } else if (!pk2.equals(other.pk2))
            return false;
        return true;
    }

}
