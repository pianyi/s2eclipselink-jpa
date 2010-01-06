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
package org.seasar.eclipselink.jpa.unit;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.runner.RunWith;
import org.seasar.eclipselink.jpa.entity.JoindSample;
import org.seasar.eclipselink.jpa.entity.JoindSampleChild;
import org.seasar.eclipselink.jpa.entity.JoindSampleChild2;
import org.seasar.framework.unit.Seasar2;
import org.seasar.framework.unit.TestContext;

import static org.seasar.framework.unit.S2Assert.*;


/**
 * @author Hidenoshin Yoshida
 *
 */
@RunWith(Seasar2.class)
public class JoindSampleTest {
    
    private EntityManager em;
    
    private TestContext ctx;
    
    public void testJoindSample() {
        JoindSample js = em.find(JoindSample.class, 1);
        assertEntityEquals(ctx.getExpected(), js);
        
    }
    
    public void testJoindSampleChild() {
        JoindSampleChild jsc = em.find(JoindSampleChild.class, 2);
        assertEntityEquals(ctx.getExpected(), jsc);
    }
    
    public void testJoindSampleChild2() {
        JoindSampleChild2 jsc2 = em.find(JoindSampleChild2.class, 3);
        assertEntityEquals(ctx.getExpected(), jsc2);
        
    }
    
    public void testCollection() {
        @SuppressWarnings("unchecked")
        List<JoindSample> list = em.createNamedQuery("JoindSampleTest.testCollection")
            .getResultList();
        Collections.sort(list, new Comparator<JoindSample>() {

			public int compare(JoindSample o1, JoindSample o2) {
				return o1.getId().compareTo(o2.getId());
			}
        	
        });
        assertEntityEquals(ctx.getExpected(), list);
    }

}
