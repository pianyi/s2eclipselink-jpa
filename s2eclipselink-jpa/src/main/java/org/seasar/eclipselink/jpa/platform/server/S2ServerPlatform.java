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
package org.seasar.eclipselink.jpa.platform.server;

import org.eclipse.persistence.platform.server.ServerPlatformBase;
import org.eclipse.persistence.sessions.DatabaseSession;
import org.seasar.eclipselink.jpa.transaction.SingletonS2TransactionController;

/**
 * Seasar2用のServerPlatform実装です。
 * @author Hidenoshin Yoshida
 */
public class S2ServerPlatform extends ServerPlatformBase {

	/**
     * コンストラクタ
	 * @param newDatabaseSession DatabaseSessionImplオブジェクト
	 */
	public S2ServerPlatform(DatabaseSession newDatabaseSession) {
		super(newDatabaseSession);
	}

    /**
     * @see org.eclipse.persistence.platform.server.ServerPlatformBase#getExternalTransactionControllerClass()
     */
    @Override
    @SuppressWarnings("unchecked")
	public Class getExternalTransactionControllerClass() {
    	if (externalTransactionControllerClass == null){
    		externalTransactionControllerClass = SingletonS2TransactionController.class;
    	}
        return externalTransactionControllerClass;
	}

}
