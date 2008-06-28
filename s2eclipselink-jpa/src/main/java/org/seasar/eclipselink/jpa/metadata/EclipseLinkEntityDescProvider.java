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
package org.seasar.eclipselink.jpa.metadata;

import javax.persistence.EntityManagerFactory;

import org.eclipse.persistence.jpa.JpaHelper;
import org.eclipse.persistence.sessions.server.ServerSession;
import org.seasar.framework.jpa.metadata.EntityDescProvider;

/**
 * EclipseLink用のEntityDescProvider実装です。
 * 
 * @author Hidenoshin Yoshida
 */
public class EclipseLinkEntityDescProvider implements EntityDescProvider {

    /**
     * コンストラクタ
     * 
     */
    public EclipseLinkEntityDescProvider() {
    }

    /**
     * @see org.seasar.framework.jpa.metadata.EntityDescProvider#createEntityDesc(javax.persistence.EntityManagerFactory, java.lang.Class)
     */
    public EclipseLinkEntityDesc createEntityDesc(EntityManagerFactory emf,
            Class<?> entityClass) {
        ServerSession serverSession = JpaHelper.getEntityManagerFactory(emf)
                .getServerSession();
        if (serverSession.getDescriptor(entityClass) != null) {
            return new EclipseLinkEntityDesc(entityClass, serverSession);
        }
        return null;
    }

}
