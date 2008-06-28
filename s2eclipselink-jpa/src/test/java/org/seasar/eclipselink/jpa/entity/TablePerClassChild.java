/*
 * Copyright 2004-2008 the Seasar Foundation and the Others.
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

import javax.persistence.Embedded;
//import javax.persistence.Entity;


/**
 * @author Hidenoshin Yoshida
 *
 */
//@Entity
public class TablePerClassChild extends TablePerClassSample {

    private String childName;
    
    @Embedded
    private EmbeddedJoindSample sample;

    
    /**
     * @return childName
     */
    public String getChildName() {
        return childName;
    }

    
    /**
     * @param childName 設定する childName
     */
    public void setChildName(String childName) {
        this.childName = childName;
    }

    
    /**
     * @return sample
     */
    public EmbeddedJoindSample getSample() {
        return sample;
    }

    
    /**
     * @param sample 設定する sample
     */
    public void setSample(EmbeddedJoindSample sample) {
        this.sample = sample;
    }
}
