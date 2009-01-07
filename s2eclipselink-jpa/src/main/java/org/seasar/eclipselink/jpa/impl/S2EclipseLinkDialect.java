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
package org.seasar.eclipselink.jpa.impl;

import java.sql.Connection;
import java.util.Properties;

import javax.persistence.EntityManager;

import org.eclipse.persistence.jpa.JpaEntityManager;
import org.eclipse.persistence.sessions.UnitOfWork;
import org.eclipse.persistence.sessions.server.ServerSession;
import org.seasar.framework.container.annotation.tiger.Binding;
import org.seasar.framework.container.annotation.tiger.BindingType;
import org.seasar.framework.container.annotation.tiger.DestroyMethod;
import org.seasar.framework.container.annotation.tiger.InitMethod;
import org.seasar.framework.jpa.Dialect;
import org.seasar.framework.jpa.DialectManager;

/**
 * EclipseLink用のDialect実装です。
 * 
 * @author Hidenoshin Yoshida
 */
public class S2EclipseLinkDialect implements Dialect {

    /**
     * DialectManagerオブジェクト
     */
    @Binding(bindingType = BindingType.MUST)
    protected DialectManager dialectManager;

    /**
     * dialectManagerにこのオブジェクトを登録します。
     */
    @InitMethod
    public void initialize() {
        dialectManager.addDialect(
        		JpaEntityManager.class, this);
    }

    /**
     * dialectManagerからこのオブジェクトを削除します。
     */
    @DestroyMethod
    public void destroy() {
        dialectManager
                .removeDialect(JpaEntityManager.class);
    }

    /**
     * @see org.seasar.framework.jpa.Dialect#getConnection(javax.persistence.EntityManager)
     */
    public Connection getConnection(EntityManager em) {
        Object delegate = em.getDelegate();
        JpaEntityManager eclipselinkEm = JpaEntityManager.class.cast(delegate);
        ServerSession session = eclipselinkEm.getServerSession();
        return session.getLogin().getConnector().connect(
                new Properties(), session);
    }

    /**
     * @see org.seasar.framework.jpa.Dialect#detach(javax.persistence.EntityManager, java.lang.Object)
     */
    public void detach(EntityManager em, Object managedEntity) {
        Object delegate = em.getDelegate();
        JpaEntityManager eclipselinkEm = JpaEntityManager.class.cast(delegate);
        UnitOfWork work = eclipselinkEm.getUnitOfWork();
        work.unregisterObject(managedEntity);
    }

}
