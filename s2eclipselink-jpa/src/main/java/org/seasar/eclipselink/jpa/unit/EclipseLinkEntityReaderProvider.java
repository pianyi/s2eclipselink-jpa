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
package org.seasar.eclipselink.jpa.unit;

import java.util.Collection;
import java.util.Map;

import org.seasar.eclipselink.jpa.metadata.EclipseLinkEntityDesc;
import org.seasar.framework.jpa.metadata.EntityDesc;
import org.seasar.framework.jpa.metadata.EntityDescFactory;
import org.seasar.framework.jpa.unit.EntityReaderProvider;
import org.seasar.framework.util.tiger.CollectionsUtil;

/**
 * EclipseLink用のEntityReaderProvider実装です。
 * 
 * @author Hidenoshin Yoshida
 */
public class EclipseLinkEntityReaderProvider implements EntityReaderProvider {

    /**
     * @see org.seasar.framework.jpa.unit.EntityReaderProvider#createEntityReader(java.lang.Object)
     */
    public EclipseLinkEntityReader createEntityReader(final Object entity) {
        if (entity == null) {
            return null;
        }
        final EclipseLinkEntityDesc entityDesc = getEntityDesc(entity.getClass());
        if (entityDesc == null) {
            return null;
        }
        return new EclipseLinkEntityReader(entity, entityDesc);
    }

    /**
     * @see org.seasar.framework.jpa.unit.EntityReaderProvider#createEntityReader(java.util.Collection)
     */
    public EclipseLinkEntityCollectionReader createEntityReader(
            final Collection<?> entities) {
        if (entities == null) {
            return null;
        }

        final Collection<Object> newEntities = flatten(entities);
        if (newEntities.isEmpty()) {
            return null;
        }

        final Map<Class<?>, EclipseLinkEntityDesc> entityDescs = CollectionsUtil
                .newHashMap();
        for (final Object entity : newEntities) {
            final Class<?> entityClass = entity.getClass();
            if (entityDescs.containsKey(entityClass)) {
                continue;
            }
            final EclipseLinkEntityDesc entityDesc = getEntityDesc(entityClass);
            if (entityDescs == null) {
                return null;
            }
            entityDescs.put(entityClass, entityDesc);
        }
        return new EclipseLinkEntityCollectionReader(newEntities, entityDescs);
    }

    /**
     * entitiesの中にObject配列が含まれていた場合、配列の要素をCollectionに追加して、新たなCollectionを生成します。
     * 
     * @param entities
     *            対象Collection
     * @return 新規作成したCollection
     */
    protected Collection<Object> flatten(final Collection<?> entities) {
        Collection<Object> newEntities = CollectionsUtil.newArrayList(entities
                .size());
        for (final Object element : entities) {
            if (element instanceof Object[]) {
                for (final Object nested : Object[].class.cast(element)) {
                    newEntities.add(nested);
                }
            } else {
                newEntities.add(element);
            }
        }
        return newEntities;
    }

    /**
     * 指定したentityClassに対応するEclipseLinkEntityDescオブジェクトを返します。
     * 
     * @param entityClass
     *            Entityクラス
     * @return entityClassに対応するEclipseLinkEntityDesc
     */
    protected EclipseLinkEntityDesc getEntityDesc(final Class<?> entityClass) {
        final EntityDesc entityDesc = EntityDescFactory
                .getEntityDesc(entityClass);
        if (entityDesc == null || !(entityDesc instanceof EclipseLinkEntityDesc)) {
            return null;
        }
        return EclipseLinkEntityDesc.class.cast(entityDesc);
    }
}
