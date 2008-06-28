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

import java.sql.Types;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.TemporalType;

import org.eclipse.persistence.descriptors.ClassDescriptor;
import org.eclipse.persistence.descriptors.VersionLockingPolicy;
import org.eclipse.persistence.indirection.WeavedAttributeValueHolderInterface;
import org.eclipse.persistence.internal.helper.DatabaseField;
import org.eclipse.persistence.mappings.AggregateMapping;
import org.eclipse.persistence.mappings.DatabaseMapping;
import org.eclipse.persistence.mappings.ForeignReferenceMapping;
import org.eclipse.persistence.platform.database.DatabasePlatform;
import org.eclipse.persistence.sessions.server.ServerSession;
import org.seasar.framework.jpa.metadata.AttributeDesc;
import org.seasar.framework.jpa.util.TemporalTypeUtil;

/**
 * EclipseLink用のAttributeDesc実装です。
 * @author Hidenoshin Yoshida
 */
public class EclipseLinkAttributeDesc implements AttributeDesc {

    /**
     * ServerSessionオブジェクト
     */
    protected ServerSession serverSession;

    /**
     * DatabaseMappingオブジェクト
     */
    protected DatabaseMapping mapping;

    protected Class<?> elementType;

    protected String name;

    protected int sqlType;

    protected TemporalType temporalType;

    protected Class<?> type;

    protected boolean association;

    protected boolean collection;

    protected boolean component;

    protected boolean id;

    protected boolean version;
    
    protected EclipseLinkAttributeDesc[] childAttributeDescs;
    
    protected Map<String, EclipseLinkAttributeDesc> childAttributeMap;

    /**
     * コンストラクタ
     * @param mapping DatabaseMappingオブジェクト
     * @param serverSession ServerSessionオブジェクト
     */
    public EclipseLinkAttributeDesc(DatabaseMapping mapping,
            ServerSession serverSession) {
        this.mapping = mapping;
        this.serverSession = serverSession;
        this.name = mapping.getAttributeName();
        Class<?> lType = mapping.getAttributeAccessor().getAttributeClass();
        if (lType != null
            && WeavedAttributeValueHolderInterface.class.isAssignableFrom(lType)
            && mapping instanceof ForeignReferenceMapping) {
            this.type = ForeignReferenceMapping.class.cast(mapping).getReferenceClass();
        } else {
            this.type = lType;
        }
        this.id = mapping.isPrimaryKeyMapping();
        this.collection = mapping.isCollectionMapping();
        this.association = mapping.isForeignReferenceMapping();
        if (collection && mapping instanceof ForeignReferenceMapping) {
            ForeignReferenceMapping fMapping = ForeignReferenceMapping.class
                    .cast(mapping);
            elementType = fMapping.getReferenceClass();
        }
        ClassDescriptor descriptor = mapping.getDescriptor();
        if (descriptor.usesVersionLocking()) {
            VersionLockingPolicy vPolicy = VersionLockingPolicy.class
                    .cast(descriptor.getOptimisticLockingPolicy());
            if (mapping.getField() != null
                    && vPolicy.getWriteLockFieldName().equals(
                            mapping.getField().getQualifiedName())) {
                version = true;
            }
        }
        if (mapping.getField() != null) {
            DatabaseField field = mapping.getField();
            DatabasePlatform platform = serverSession.getPlatform();
            sqlType = platform.getJDBCType(field);
        } else {
            sqlType = Types.OTHER;
        }
        this.component = mapping.isAggregateMapping();
        if (component) {
            AggregateMapping aMapping = AggregateMapping.class.cast(mapping);
            ClassDescriptor referenceDescriptor = aMapping.getReferenceDescriptor();
            List<DatabaseMapping> childMapList = referenceDescriptor.getMappings();
            childAttributeMap = new HashMap<String, EclipseLinkAttributeDesc>();
            List<EclipseLinkAttributeDesc> childAttributeList = new ArrayList<EclipseLinkAttributeDesc>();
            for (DatabaseMapping childMap : childMapList) {
                EclipseLinkAttributeDesc childDesc = new EclipseLinkAttributeDesc(childMap, serverSession);
                childAttributeMap.put(childMap.getAttributeName(), childDesc);
                childAttributeList.add(childDesc);
            }
            childAttributeDescs = childAttributeList.toArray(new EclipseLinkAttributeDesc[childMapList.size()]);
        }
        if (type == Date.class || type == Calendar.class) {
            temporalType = TemporalTypeUtil.fromSqlTypeToTemporalType(sqlType);
        }
    }

    /**
     * @see org.seasar.framework.jpa.metadata.AttributeDesc#getElementType()
     */
    public Class<?> getElementType() {
        return elementType;
    }

    /**
     * @see org.seasar.framework.jpa.metadata.AttributeDesc#getName()
     */
    public String getName() {
        return name;
    }

    /**
     * @see org.seasar.framework.jpa.metadata.AttributeDesc#getSqlType()
     */
    public int getSqlType() {
        return sqlType;
    }

    /**
     * @see org.seasar.framework.jpa.metadata.AttributeDesc#getTemporalType()
     */
    public TemporalType getTemporalType() {
        return temporalType;
    }

    /**
     * @see org.seasar.framework.jpa.metadata.AttributeDesc#getType()
     */
    public Class<?> getType() {
        return type;
    }

    /**
     * @see org.seasar.framework.jpa.metadata.AttributeDesc#getValue(java.lang.Object)
     */
    public Object getValue(Object entity) {
        return mapping.getRealAttributeValueFromObject(entity, null);
    }

    /**
     * @see org.seasar.framework.jpa.metadata.AttributeDesc#isAssociation()
     */
    public boolean isAssociation() {
        return association;
    }

    /**
     * @see org.seasar.framework.jpa.metadata.AttributeDesc#isCollection()
     */
    public boolean isCollection() {
        return collection;
    }

    /**
     * @see org.seasar.framework.jpa.metadata.AttributeDesc#isComponent()
     */
    public boolean isComponent() {
        return component;
    }

    /**
     * @see org.seasar.framework.jpa.metadata.AttributeDesc#isId()
     */
    public boolean isId() {
        return id;
    }

    /**
     * @see org.seasar.framework.jpa.metadata.AttributeDesc#isVersion()
     */
    public boolean isVersion() {
        return version;
    }

    /**
     * @see org.seasar.framework.jpa.metadata.AttributeDesc#setValue(java.lang.Object, java.lang.Object)
     */
    public void setValue(Object entity, Object value) {
        mapping.setAttributeValueInObject(entity, value);
    }

    /**
     * @see org.seasar.framework.jpa.metadata.AttributeDesc#getChildAttributeDesc(java.lang.String)
     */
    public EclipseLinkAttributeDesc getChildAttributeDesc(String name) {
        return childAttributeMap.get(name);
    }

    /**
     * @see org.seasar.framework.jpa.metadata.AttributeDesc#getChildAttributeDescs()
     */
    public EclipseLinkAttributeDesc[] getChildAttributeDescs() {
        return childAttributeDescs;
    }

    /**
     * DatabaseMappingを返します。
     * @return DatabaseMapping
     */
    public DatabaseMapping getMapping() {
        return mapping;
    }

}
