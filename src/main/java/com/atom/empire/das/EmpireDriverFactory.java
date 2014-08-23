/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.atom.empire.das;

import org.apache.empire.db.DBDatabaseDriver;
import org.apache.empire.db.derby.DBDatabaseDriverDerby;
import org.apache.empire.db.h2.DBDatabaseDriverH2;
import org.apache.empire.db.hsql.DBDatabaseDriverHSql;
import org.apache.empire.db.mysql.DBDatabaseDriverMySQL;
import org.apache.empire.db.oracle.DBDatabaseDriverOracle;
import org.apache.empire.db.sqlserver.DBDatabaseDriverMSSQL;
import org.springframework.beans.factory.FactoryBean;

/**
 * EmpireDB驱动工厂
 * 
 * @author obullxl@gmail.com
 * @version $Id: EmpireDriverFactory.java, V1.0.1 2014年8月23日 上午11:10:00 $
 */
public class EmpireDriverFactory implements FactoryBean<DBDatabaseDriver> {

    /** 驱动器 */
    private final String driver;

    /** 数据模式 */
    private final String schema;

    /**
     * CTOR
     */
    public EmpireDriverFactory(String driver, String schema) {
        this.driver = driver;
        this.schema = schema;
    }

    /** 
     * @see org.springframework.beans.factory.FactoryBean#getObject()
     */
    public DBDatabaseDriver getObject() throws Exception {
        if (isClass(DBDatabaseDriverMySQL.class)) {
            DBDatabaseDriverMySQL driver = new DBDatabaseDriverMySQL();
            // Set Driver specific properties (if any)
            driver.setDatabaseName(this.schema);
            return driver;
        } else if (isClass(DBDatabaseDriverOracle.class)) {
            DBDatabaseDriverOracle driver = new DBDatabaseDriverOracle();
            // Set Driver specific properties (if any)
            return driver;
        } else if (isClass(DBDatabaseDriverMSSQL.class)) {
            DBDatabaseDriverMSSQL driver = new DBDatabaseDriverMSSQL();
            // Set Driver specific properties (if any)
            driver.setDatabaseName(this.schema);
            return driver;
        } else if (isClass(DBDatabaseDriverHSql.class)) {
            DBDatabaseDriverHSql driver = new DBDatabaseDriverHSql();
            // Set Driver specific properties (if any)
            return driver;
        } else if (isClass(DBDatabaseDriverH2.class)) {
            DBDatabaseDriverH2 driver = new DBDatabaseDriverH2();
            // Set Driver specific properties (if any)
            driver.setDatabaseName(this.schema);
            return driver;
        } else if (isClass(DBDatabaseDriverDerby.class)) {
            DBDatabaseDriverDerby driver = new DBDatabaseDriverDerby();
            // Set Driver specific properties (if any)
            driver.setDatabaseName(this.schema);
            return driver;
        }

        // Unknown Provider
        throw new RuntimeException("Unknown Database Driver " + this.driver);
    }

    /**
     * 判断是否为驱动类
     */
    private boolean isClass(Class<? extends DBDatabaseDriver> clazz) {
        return this.driver.equals(clazz.getName());
    }

    /** 
     * @see org.springframework.beans.factory.FactoryBean#getObjectType()
     */
    public Class<?> getObjectType() {
        return DBDatabaseDriver.class;
    }

    /** 
     * @see org.springframework.beans.factory.FactoryBean#isSingleton()
     */
    public boolean isSingleton() {
        return true;
    }

}
