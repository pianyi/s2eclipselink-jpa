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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.runner.RunWith;
import org.seasar.eclipselink.jpa.entity.Product;
import org.seasar.framework.unit.Seasar2;
import org.seasar.framework.unit.TestContext;

import static org.seasar.framework.unit.S2Assert.*;


/**
 * @author Hidenoshin Yoshida
 *
 */
@RunWith(Seasar2.class)
public class ProductTest {
    
    private TestContext ctx;
    
    private EntityManager em;
    
    public void testSingle() {
        Product p = em.find(Product.class, 1);
        assertEntityEquals(ctx.getExpected(), p);
    }
    
    public void testCollection() {
        @SuppressWarnings("unchecked")
        List<Product> list = em.createNamedQuery("ProductTest.testCollection")
            .getResultList();
        assertEntityEquals(ctx.getExpected(), list);
    }

    public void testArrayCollection() {
        @SuppressWarnings("unchecked")
        List<Product> list = em.createNamedQuery("ProductTest.testArrayCollection")
        .getResultList();
        List<Object[]> list2 = new ArrayList<Object[]>();
        for (Product p : list) {
            list2.add(new Object[]{p, p.getCustomer()});
        }
        assertEntityEquals(ctx.getExpected(), list2);
    }
}
