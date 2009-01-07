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

import java.sql.Types;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

import javax.persistence.TemporalType;

import org.junit.runner.RunWith;
import org.seasar.eclipselink.jpa.entity.Customer;
import org.seasar.eclipselink.jpa.entity.Dangeon;
import org.seasar.eclipselink.jpa.entity.EmbeddedSample;
import org.seasar.eclipselink.jpa.entity.Enemy;
import org.seasar.eclipselink.jpa.entity.Product;
import org.seasar.eclipselink.jpa.entity.Sex;
import org.seasar.framework.jpa.metadata.AttributeDesc;
import org.seasar.framework.jpa.metadata.EntityDesc;
import org.seasar.framework.jpa.metadata.EntityDescFactory;
import org.seasar.framework.unit.Seasar2;
import org.seasar.framework.util.tiger.CollectionsUtil;

import static org.seasar.framework.unit.S2Assert.*;

/**
 * @author Hidenoshin Yoshida
 *
 */
@RunWith(Seasar2.class)
public class EclipseLinkAttributeDescTest {

    public void testGetElementType() {
        EntityDesc eDesc = EntityDescFactory.getEntityDesc(Customer.class);
        AttributeDesc desc = eDesc.getAttributeDesc("id");
        assertNotNull(desc);
        assertNull(eDesc.getAttributeDesc("AA"));
    }

    public void testGetName() {
        EntityDesc eDesc = EntityDescFactory.getEntityDesc(Customer.class);
        AttributeDesc desc = eDesc.getAttributeDesc("id");
        assertEquals("id", desc.getName());
        desc = eDesc.getAttributeDesc("name");
        assertEquals("name", desc.getName());
    }

    public void testGetSqlType() {
        EntityDesc eDesc = EntityDescFactory.getEntityDesc(Customer.class);
        AttributeDesc desc = eDesc.getAttributeDesc("id");
        assertEquals(Types.INTEGER, desc.getSqlType());
        desc = eDesc.getAttributeDesc("name");
        assertEquals(Types.VARCHAR, desc.getSqlType());
        desc = eDesc.getAttributeDesc("birthday");
        assertEquals(Types.DATE, desc.getSqlType());
    }

    public void testGetTemporalType() {
        EntityDesc eDesc = EntityDescFactory.getEntityDesc(Customer.class);
        AttributeDesc desc = eDesc.getAttributeDesc("id");
        assertNull(desc.getTemporalType());
        desc = eDesc.getAttributeDesc("birthday");
        assertEquals(TemporalType.DATE, desc.getTemporalType());
    }

   public void testGetType() {
        EntityDesc eDesc = EntityDescFactory.getEntityDesc(Customer.class);
        AttributeDesc desc = eDesc.getAttributeDesc("id");
        assertEquals(Integer.class, desc.getType());
        desc = eDesc.getAttributeDesc("name");
        assertEquals(String.class, desc.getType());
        desc = eDesc.getAttributeDesc("address");
        assertEquals(String.class, desc.getType());
        desc = eDesc.getAttributeDesc("phone");
        assertEquals(String.class, desc.getType());
        desc = eDesc.getAttributeDesc("age");
        assertEquals(Integer.class, desc.getType());
        desc = eDesc.getAttributeDesc("birthday");
        assertEquals(Date.class, desc.getType());
        desc = eDesc.getAttributeDesc("sex");
        assertEquals(Sex.class, desc.getType());
        desc = eDesc.getAttributeDesc("version");
        assertEquals(Integer.class, desc.getType());
        desc = eDesc.getAttributeDesc("products");
        assertEquals(Set.class, desc.getType());
    }

    public void testGetValue() {
        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("name");
        customer.setAddress("address");
        customer.setPhone("phone");
        customer.setAge(20);
        customer.setBirthday(new GregorianCalendar(1980, 0, 1).getTime());
        customer.setSex(Sex.MAN);
        customer.setVersion(1);
        Set<Product> set = CollectionsUtil.newHashSet();
        set.add(new Product());
        customer.setProducts(set);
        
        EntityDesc eDesc = EntityDescFactory.getEntityDesc(Customer.class);
        AttributeDesc desc = eDesc.getAttributeDesc("id");
        assertEquals(1, desc.getValue(customer));
        desc = eDesc.getAttributeDesc("name");
        assertEquals("name", desc.getValue(customer));
        desc = eDesc.getAttributeDesc("address");
        assertEquals("address", desc.getValue(customer));
        desc = eDesc.getAttributeDesc("phone");
        assertEquals("phone", desc.getValue(customer));
        desc = eDesc.getAttributeDesc("age");
        assertEquals(20, desc.getValue(customer));
        desc = eDesc.getAttributeDesc("birthday");
        assertEquals(new GregorianCalendar(1980, 0, 1).getTime(), desc.getValue(customer));
        desc = eDesc.getAttributeDesc("sex");
        assertEquals(Sex.MAN, desc.getValue(customer));
        desc = eDesc.getAttributeDesc("version");
        assertEquals(1, desc.getValue(customer));
        desc = eDesc.getAttributeDesc("products");
        assertEquals(set, desc.getValue(customer));
        
    }
    
    public void testGetValue2() {
    	
    }

    public void testIsAssociation() {
        EntityDesc eDesc = EntityDescFactory.getEntityDesc(Customer.class);
        AttributeDesc desc = eDesc.getAttributeDesc("id");
        assertFalse(desc.isAssociation());
        desc = eDesc.getAttributeDesc("name");
        assertFalse(desc.isAssociation());
        desc = eDesc.getAttributeDesc("address");
        assertFalse(desc.isAssociation());
        desc = eDesc.getAttributeDesc("phone");
        assertFalse(desc.isAssociation());
        desc = eDesc.getAttributeDesc("age");
        assertFalse(desc.isAssociation());
        desc = eDesc.getAttributeDesc("birthday");
        assertFalse(desc.isAssociation());
        desc = eDesc.getAttributeDesc("sex");
        assertFalse(desc.isAssociation());
        desc = eDesc.getAttributeDesc("version");
        assertFalse(desc.isAssociation());
        desc = eDesc.getAttributeDesc("products");
        assertTrue(desc.isAssociation());
    }

    public void testIsCollection() {
        EntityDesc eDesc = EntityDescFactory.getEntityDesc(Customer.class);
        AttributeDesc desc = eDesc.getAttributeDesc("id");
        assertFalse(desc.isCollection());
        desc = eDesc.getAttributeDesc("name");
        assertFalse(desc.isCollection());
        desc = eDesc.getAttributeDesc("address");
        assertFalse(desc.isCollection());
        desc = eDesc.getAttributeDesc("phone");
        assertFalse(desc.isCollection());
        desc = eDesc.getAttributeDesc("age");
        assertFalse(desc.isCollection());
        desc = eDesc.getAttributeDesc("birthday");
        assertFalse(desc.isCollection());
        desc = eDesc.getAttributeDesc("sex");
        assertFalse(desc.isCollection());
        desc = eDesc.getAttributeDesc("version");
        assertFalse(desc.isCollection());
        desc = eDesc.getAttributeDesc("products");
        assertTrue(desc.isCollection());
    }

    public void testIsComponent() {
        EntityDesc eDesc = EntityDescFactory.getEntityDesc(Enemy.class);
        AttributeDesc desc = eDesc.getAttributeDesc("id");
        assertFalse(desc.isComponent());
        desc = eDesc.getAttributeDesc("name");
        assertFalse(desc.isComponent());
        desc = eDesc.getAttributeDesc("address");
        assertFalse(desc.isComponent());
        desc = eDesc.getAttributeDesc("phone");
        assertFalse(desc.isComponent());
        desc = eDesc.getAttributeDesc("age");
        assertFalse(desc.isComponent());
        desc = eDesc.getAttributeDesc("birthday");
        assertFalse(desc.isComponent());
        desc = eDesc.getAttributeDesc("sex");
        assertFalse(desc.isComponent());
        desc = eDesc.getAttributeDesc("version");
        assertFalse(desc.isComponent());
        desc = eDesc.getAttributeDesc("products");
        assertFalse(desc.isComponent());
        desc = eDesc.getAttributeDesc("dangeon");
        assertTrue(desc.isComponent());
    }
    
    public void testIsComponentId() {
        EntityDesc eDesc = EntityDescFactory.getEntityDesc(EmbeddedSample.class);
        AttributeDesc desc = eDesc.getAttributeDesc("id");
        assertTrue(desc.isComponent());
        desc = eDesc.getAttributeDesc("name");
        assertFalse(desc.isComponent());
    }

    public void testIsId() {
        EntityDesc eDesc = EntityDescFactory.getEntityDesc(Enemy.class);
        AttributeDesc desc = eDesc.getAttributeDesc("id");
        assertTrue(desc.isId());
        desc = eDesc.getAttributeDesc("name");
        assertFalse(desc.isId());
        desc = eDesc.getAttributeDesc("address");
        assertFalse(desc.isId());
        desc = eDesc.getAttributeDesc("phone");
        assertFalse(desc.isId());
        desc = eDesc.getAttributeDesc("age");
        assertFalse(desc.isId());
        desc = eDesc.getAttributeDesc("birthday");
        assertFalse(desc.isId());
        desc = eDesc.getAttributeDesc("sex");
        assertFalse(desc.isId());
        desc = eDesc.getAttributeDesc("version");
        assertFalse(desc.isId());
        desc = eDesc.getAttributeDesc("products");
        assertFalse(desc.isId());
        desc = eDesc.getAttributeDesc("dangeon");
        assertFalse(desc.isId());
    }
    
    public void testIsVersion() {
        EntityDesc eDesc = EntityDescFactory.getEntityDesc(Enemy.class);
        AttributeDesc desc = eDesc.getAttributeDesc("id");
        assertFalse(desc.isVersion());
        desc = eDesc.getAttributeDesc("name");
        assertFalse(desc.isVersion());
        desc = eDesc.getAttributeDesc("address");
        assertFalse(desc.isVersion());
        desc = eDesc.getAttributeDesc("phone");
        assertFalse(desc.isVersion());
        desc = eDesc.getAttributeDesc("age");
        assertFalse(desc.isVersion());
        desc = eDesc.getAttributeDesc("birthday");
        assertFalse(desc.isVersion());
        desc = eDesc.getAttributeDesc("sex");
        assertFalse(desc.isVersion());
        desc = eDesc.getAttributeDesc("version");
        assertTrue(desc.isVersion());
        desc = eDesc.getAttributeDesc("products");
        assertFalse(desc.isVersion());
        desc = eDesc.getAttributeDesc("dangeon");
        assertFalse(desc.isVersion());
    }

    public void testSetValue() {
        Customer customer = new Customer();
        
        EntityDesc eDesc = EntityDescFactory.getEntityDesc(Customer.class);
        AttributeDesc desc = eDesc.getAttributeDesc("id");
        desc.setValue(customer, 1);
        assertEquals(1, customer.getId().intValue());
        desc = eDesc.getAttributeDesc("name");
        desc.setValue(customer, "name");
        assertEquals("name", customer.getName());
        desc = eDesc.getAttributeDesc("address");
        desc.setValue(customer, "address");
        assertEquals("address", customer.getAddress());
        desc = eDesc.getAttributeDesc("phone");
        desc.setValue(customer, "phone");
        assertEquals("phone", customer.getPhone());
        desc = eDesc.getAttributeDesc("age");
        desc.setValue(customer, 20);
        assertEquals(20, customer.getAge().intValue());
        desc = eDesc.getAttributeDesc("birthday");
        desc.setValue(customer, new GregorianCalendar(2080, 0, 1).getTime());
        assertEquals(new GregorianCalendar(2080, 0, 1).getTime(), customer.getBirthday());
        desc = eDesc.getAttributeDesc("sex");
        desc.setValue(customer, Sex.MAN);
        assertEquals(Sex.MAN, customer.getSex());
        desc = eDesc.getAttributeDesc("version");
        desc.setValue(customer, 1);
        assertEquals(1, customer.getVersion().intValue());
        desc = eDesc.getAttributeDesc("products");
        Set<Product> set = CollectionsUtil.newHashSet();
        set.add(new Product());
        desc.setValue(customer, set);
        assertEquals(set, customer.getProducts());
    }

    public void testGetChildAttributeDescs() {
        EntityDesc eDesc = EntityDescFactory.getEntityDesc(Enemy.class);
        AttributeDesc desc = eDesc.getAttributeDesc("dangeon");
        AttributeDesc[] childDescs = desc.getChildAttributeDescs();
        assertEquals(2, childDescs.length);
    }
    
    public void testGetChildAttributeDesc() {
        Dangeon d = new Dangeon();
        d.setDangeonLevel(1);
        d.setDangeonName("dangeon");

        EntityDesc eDesc = EntityDescFactory.getEntityDesc(Enemy.class);
        AttributeDesc desc = eDesc.getAttributeDesc("dangeon");

        AttributeDesc childDesc = desc.getChildAttributeDesc("dangeonName");
        assertNotNull(childDesc);
        assertEquals("dangeonName", childDesc.getName());
        assertEquals("dangeon", childDesc.getValue(d));
        
        childDesc = desc.getChildAttributeDesc("dangeonLevel");
        assertNotNull(childDesc);
        assertEquals("dangeonLevel", childDesc.getName());
        assertEquals(1, childDesc.getValue(d));
    }

}
