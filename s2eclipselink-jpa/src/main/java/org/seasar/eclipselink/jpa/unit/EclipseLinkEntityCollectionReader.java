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

import java.util.Collection;
import java.util.Map;

import org.seasar.eclipselink.jpa.metadata.EclipseLinkEntityDesc;

/**
 * EclipseLink用のEntityReader実装です（Collection用）。
 * @author Hidenoshin Yoshida
 */
public class EclipseLinkEntityCollectionReader extends EclipseLinkEntityReader {

    /**
     * Entityクラスと対応するEntityDescのMap
     */
    protected Map<Class<?>, EclipseLinkEntityDesc> entityDescs;

    /**
     * EntityReader生成時に一時的にEntityClassを保持するフィールド
     */
    protected Class<?> processingClass;

    /**
     * コンストラクタ
     * @param entities 対象となるEntityのList
     * @param entityDescs Entityクラスと対応するEntityDescのMap
     */
    public EclipseLinkEntityCollectionReader(final Collection<?> entities,
            final Map<Class<?>, EclipseLinkEntityDesc> entityDescs) {

        this.entityDescs = entityDescs;

        for (final Object entity : entities) {
            processingClass = entity.getClass();
            setupColumns();
            setupRow(entity);
            processingClass = null;
        }
    }

    /**
     * @see org.seasar.eclipselink.jpa.unit.EclipseLinkEntityReader#getEntityDesc()
     */
    @Override
    protected EclipseLinkEntityDesc getEntityDesc() {
        return entityDescs.get(processingClass);
    }
}
