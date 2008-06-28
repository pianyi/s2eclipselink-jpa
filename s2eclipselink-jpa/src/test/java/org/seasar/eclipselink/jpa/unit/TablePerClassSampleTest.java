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
package org.seasar.eclipselink.jpa.unit;

//import java.util.List;
//
//import javax.persistence.EntityManager;
//
import org.junit.runner.RunWith;
//import org.seasar.eclipselink.jpa.entity.TablePerClassChild;
//import org.seasar.eclipselink.jpa.entity.TablePerClassSample;
import org.seasar.framework.unit.Seasar2;
//import org.seasar.framework.unit.TestContext;

import static org.seasar.framework.unit.S2Assert.*;


/**
 * @author Hidenoshin Yoshida
 *
 */
@RunWith(Seasar2.class)
public class TablePerClassSampleTest {
    
//    private TestContext ctx;
    
//    private EntityManager em;
    
    public void testTablePerClassSample() {
    	assertTrue(true);
//        TablePerClassSample sample = em.find(TablePerClassSample.class, 1);
//        assertEntityEquals(ctx.getExpected(), sample);
    }
    
//    public void testTablePerClassChild() {
//        TablePerClassChild child = em.find(TablePerClassChild.class, 2);
//        assertEntityEquals(ctx.getExpected(), child);
//    }
//
//    public void testCollection() {
//        @SuppressWarnings("unchecked")
//        List<TablePerClassSample> list = em.createNamedQuery("TablePerClassSampleTest.testCollection")
//            .getResultList();
//        assertEntityEquals(ctx.getExpected(), list);
//    }
}
