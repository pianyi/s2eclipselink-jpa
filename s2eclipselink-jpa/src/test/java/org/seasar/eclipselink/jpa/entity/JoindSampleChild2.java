/*
 * Copyright 2004-2009 the Seasar Foundation and the Others.
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

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Embedded;
import javax.persistence.Entity;


/**
 * @author Hidenoshin Yoshida
 *
 */
@Entity
@DiscriminatorValue("C2")
public class JoindSampleChild2 extends JoindSample {

    /**
     * 
     */
    private static final long serialVersionUID = -4089665876988936937L;

    @Column(name = "CHILD_NAME")
    private String childName;
    
    @Embedded
    private EmbeddedJoindSample sample;

    
    public String getChildName() {
        return childName;
    }

    
    public void setChildName(String childName) {
        this.childName = childName;
    }

    
    public EmbeddedJoindSample getSample() {
        return sample;
    }

    
    public void setSample(EmbeddedJoindSample sample) {
        this.sample = sample;
    }

}
