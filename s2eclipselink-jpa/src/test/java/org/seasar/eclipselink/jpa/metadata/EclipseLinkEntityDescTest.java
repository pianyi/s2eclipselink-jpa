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
package org.seasar.eclipselink.jpa.metadata;

import java.util.Arrays;
import java.util.List;

import org.junit.runner.RunWith;
import org.seasar.eclipselink.jpa.entity.Customer;
import org.seasar.eclipselink.jpa.entity.JoindSample;
import org.seasar.eclipselink.jpa.entity.JoindSampleChild;
import org.seasar.eclipselink.jpa.entity.Product;
import org.seasar.eclipselink.jpa.metadata.EclipseLinkEntityDesc;
import org.seasar.framework.jpa.metadata.EntityDesc;
import org.seasar.framework.jpa.metadata.EntityDescFactory;
import org.seasar.framework.unit.Seasar2;

import static org.seasar.framework.unit.S2Assert.*;

/**
 * @author Hidenoshin Yoshida
 *
 */
@RunWith(Seasar2.class)
public class EclipseLinkEntityDescTest {
    
    public void testGetAttributeDesc() {
        EntityDesc desc = EntityDescFactory.getEntityDesc(Customer.class);
        assertEquals(desc.getAttributeDesc("id").getName(), "id");
        assertEquals(desc.getAttributeDesc("name").getName(), "name");
        assertEquals(desc.getAttributeDesc("address").getName(), "address");
        assertEquals(desc.getAttributeDesc("phone").getName(), "phone");
        assertEquals(desc.getAttributeDesc("age").getName(), "age");
        assertEquals(desc.getAttributeDesc("birthday").getName(), "birthday");
        assertEquals(desc.getAttributeDesc("sex").getName(), "sex");
        assertEquals(desc.getAttributeDesc("version").getName(), "version");
        assertEquals(desc.getAttributeDesc("products").getName(), "products");
        assertNull(desc.getAttributeDesc(null));
        assertNull(desc.getAttributeDesc(""));
        
        desc = EntityDescFactory.getEntityDesc(Product.class);
        assertEquals(desc.getAttributeDesc("id").getName(), "id");
        assertEquals(desc.getAttributeDesc("name").getName(), "name");
        assertEquals(desc.getAttributeDesc("version").getName(), "version");
        assertEquals(desc.getAttributeDesc("customer").getName(), "customer");
        assertNull(desc.getAttributeDesc(null));
        assertNull(desc.getAttributeDesc(""));
    }

    public void testGetAttributeDescs() {
        EntityDesc desc = EntityDescFactory.getEntityDesc(Customer.class);
        assertEquals(9, desc.getAttributeDescs().length);
        desc = EntityDescFactory.getEntityDesc(Product.class);
        assertEquals(4, desc.getAttributeDescs().length);
    }

    public void testGetAttributeNames() {
        EntityDesc desc = EntityDescFactory.getEntityDesc(Customer.class);
        String[] names = desc.getAttributeNames();
        assertEquals(9, names.length);
        List<String> list = Arrays.asList(names);
        list.contains("id");
        list.contains("name");
        list.contains("address");
        list.contains("phone");
        list.contains("age");
        list.contains("birthday");
        list.contains("sex");
        list.contains("version");
        list.contains("products");
        desc = EntityDescFactory.getEntityDesc(Product.class);
        names = desc.getAttributeNames();
        assertEquals(4, names.length);
        list = Arrays.asList(names);
        list.contains("id");
        list.contains("name");
        list.contains("version");
        list.contains("customer");
    }

    public void testGetEntityClass() {
        EntityDesc desc = EntityDescFactory.getEntityDesc(Customer.class);
        assertEquals(desc.getEntityClass(), Customer.class);
        desc = EntityDescFactory.getEntityDesc(Product.class);
        assertEquals(desc.getEntityClass(), Product.class);
    }

    public void testGetEntityName() {
        EntityDesc desc = EntityDescFactory.getEntityDesc(Customer.class);
        assertEquals(desc.getEntityName(), Customer.class.getSimpleName());
        desc = EntityDescFactory.getEntityDesc(Product.class);
        assertEquals(desc.getEntityName(), Product.class.getSimpleName());
    }

    public void testGetIdAttributeDesc() {
        EntityDesc desc = EntityDescFactory.getEntityDesc(Customer.class);
        assertEquals(desc.getIdAttributeDesc(), desc.getAttributeDesc("id"));
        desc = EntityDescFactory.getEntityDesc(Product.class);
        assertEquals(desc.getIdAttributeDesc(), desc.getAttributeDesc("id"));
    }
    
    public void testGetTableNames() {
        EclipseLinkEntityDesc desc = EclipseLinkEntityDesc.class.cast(EntityDescFactory.getEntityDesc(JoindSampleChild.class));
        assertNotNull(desc);
        assertEquals(2, desc.getTableNames().size());
        assertEquals(JoindSample.class.getSimpleName().toUpperCase(), desc.getTableNames().get(0));
        assertEquals("JOIND_SAMPLE_CHILD", desc.getTableNames().get(1));
    }

}
