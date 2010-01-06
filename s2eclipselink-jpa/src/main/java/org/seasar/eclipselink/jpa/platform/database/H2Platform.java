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
package org.seasar.eclipselink.jpa.platform.database;

import java.util.Hashtable;

import org.eclipse.persistence.expressions.ExpressionOperator;
import org.eclipse.persistence.internal.databaseaccess.FieldTypeDefinition;
import org.eclipse.persistence.platform.database.DatabasePlatform;
import org.eclipse.persistence.queries.ValueReadQuery;

/**
 * H2 Database用DatabasePlatformクラスです。
 * @author Hidenoshin Yoshida
 */
public class H2Platform extends DatabasePlatform {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9204328344795118413L;

	/**
	 * コンストラクタ
	 */
	public H2Platform() {
    }

	/**
	 * @see org.eclipse.persistence.internal.databaseaccess.DatabasePlatform#buildFieldTypes()
	 */
	@SuppressWarnings("unchecked")
	protected Hashtable<Object,Object> buildFieldTypes() {
        Hashtable<Object,Object> fieldTypeMapping;

        fieldTypeMapping = super.buildFieldTypes();
        fieldTypeMapping.put(Boolean.class, new FieldTypeDefinition("TINYINT", false));
        fieldTypeMapping.put(Integer.class, new FieldTypeDefinition("INTEGER", false));
        fieldTypeMapping.put(Long.class, new FieldTypeDefinition("NUMERIC", 19));
        fieldTypeMapping.put(Float.class, new FieldTypeDefinition("REAL", false));
        fieldTypeMapping.put(Double.class, new FieldTypeDefinition("REAL", false));
        fieldTypeMapping.put(Short.class, new FieldTypeDefinition("SMALLINT", false));
        fieldTypeMapping.put(Byte.class, new FieldTypeDefinition("SMALLINT", false));
        fieldTypeMapping.put(java.math.BigInteger.class, new FieldTypeDefinition("NUMERIC", 38));
        fieldTypeMapping.put(java.math.BigDecimal.class, new FieldTypeDefinition("NUMERIC", 38).setLimits(38, -19, 19));
        fieldTypeMapping.put(Number.class, new FieldTypeDefinition("NUMERIC", 38).setLimits(38, -19, 19));
        fieldTypeMapping.put(Byte[].class, new FieldTypeDefinition("BINARY", false));
        fieldTypeMapping.put(Character[].class, new FieldTypeDefinition("LONGVARCHAR", false));
        fieldTypeMapping.put(byte[].class, new FieldTypeDefinition("BINARY", false));
        fieldTypeMapping.put(char[].class, new FieldTypeDefinition("LONGVARCHAR", false));
        fieldTypeMapping.put(java.sql.Blob.class, new FieldTypeDefinition("BINARY", false));
        fieldTypeMapping.put(java.sql.Clob.class, new FieldTypeDefinition("LONGVARCHAR", false));
        fieldTypeMapping.put(java.sql.Date.class, new FieldTypeDefinition("DATE", false));
        fieldTypeMapping.put(java.sql.Time.class, new FieldTypeDefinition("TIME", false));
        fieldTypeMapping.put(java.sql.Timestamp.class, new FieldTypeDefinition("TIMESTAMP", false));

        return fieldTypeMapping;
    }

    /**
     * H2用DatabasePlatformであることを返します。
     * @return
     */
    public boolean isH2() {
        return true;
    }

    /**
     * @see org.eclipse.persistence.internal.databaseaccess.DatabasePlatform#supportsForeignKeyConstraints()
     */
    public boolean supportsForeignKeyConstraints() {
        return true;
    }

    /**
     * @see org.eclipse.persistence.internal.databaseaccess.DatabasePlatform#buildSelectQueryForSequenceObject(java.lang.String, java.lang.Integer)
     */
    public ValueReadQuery buildSelectQueryForSequenceObject(String seqName, Integer size) {
        return new ValueReadQuery("CALL NEXT VALUE FOR " + getQualifiedSequenceName(seqName));
    }

    /**
     * @see org.eclipse.persistence.internal.databaseaccess.DatabasePlatform#supportsNativeSequenceNumbers()
     */
    public boolean supportsNativeSequenceNumbers() {
        return true;
    }

    /**
     * SEQUENCE名を生成して返します。
     * @param seqName 加工前のSEQUENCE名
     * @return SEQUENCE名
     */
    protected String getQualifiedSequenceName(String seqName) {
        if (getTableQualifier().equals("")) {
            return seqName;
        } else {
            return getTableQualifier() + "." + seqName;
        }
    }

    /**
     * @return
     */
    public boolean supportsSelectForUpdateNoWait() {
        return true;
    }

    /**
     * @return
     */
    protected ExpressionOperator todayOperator() {
        return ExpressionOperator.simpleFunctionNoParentheses(ExpressionOperator.Today, "SYSDATE");
    }

    /**
     * @see org.eclipse.persistence.internal.databaseaccess.DatasourcePlatform#initializePlatformOperators()
     */
    protected void initializePlatformOperators() {
        super.initializePlatformOperators();
        addOperator(ExpressionOperator.simpleMath(ExpressionOperator.Concat, "||"));
    }

    /**
     * @see org.eclipse.persistence.internal.databaseaccess.DatabasePlatform#shouldUseJDBCOuterJoinSyntax()
     */
    public boolean shouldUseJDBCOuterJoinSyntax() {
        return false;
    }

}
