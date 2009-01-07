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
package org.seasar.eclipselink.jpa.aop.interceptors;

import org.aopalliance.intercept.MethodInvocation;
import org.eclipse.persistence.jpa.JpaQuery;
import org.seasar.framework.aop.interceptors.AbstractInterceptor;

/**
 * EclipseLinkのQuery実装が、createNamedQuery実行時にNamedQueryの存在チェックを行わない問題の対処を行うInterceptor
 * @author Hidenoshin Yoshida
 *
 */
public class S2EclipseLinkEntityManagerInterceptor extends AbstractInterceptor {

    /**
     * 
     */
    private static final long serialVersionUID = -4065883918496694771L;


    /**
     * 実行結果がEJBQueryだった場合、getDatabaseQueryメソッドを実行してNamedQueryの存在チェックを行います。
     * @see org.aopalliance.intercept.MethodInterceptor#invoke(org.aopalliance.intercept.MethodInvocation)
     */
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object ret = invocation.proceed();
        if (ret instanceof JpaQuery) {
        	JpaQuery query = JpaQuery.class.cast(ret);
            query.getDatabaseQuery();
        }
        return ret;
    }

}
