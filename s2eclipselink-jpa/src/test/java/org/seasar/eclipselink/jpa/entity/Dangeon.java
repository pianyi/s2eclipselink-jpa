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
import javax.persistence.Embeddable;

/**
 * @author Hidenoshin Yoshida
 *
 */
@Embeddable
public class Dangeon {
    
    @Column(name = "DANGEON")
    private String dangeonName;
    
    @Column(name = "DANGEON_LEVEL")
    private Integer dangeonLevel;

    /**
     * @return dangeonLevel
     */
    public Integer getDangeonLevel() {
        return dangeonLevel;
    }

    /**
     * @param dangeonLevel 設定する dangeonLevel
     */
    public void setDangeonLevel(Integer dangeonLevel) {
        this.dangeonLevel = dangeonLevel;
    }

    /**
     * @return dangeonName
     */
    public String getDangeonName() {
        return dangeonName;
    }

    /**
     * @param dangeonName 設定する dangeonName
     */
    public void setDangeonName(String dangeonName) {
        this.dangeonName = dangeonName;
    }

}
