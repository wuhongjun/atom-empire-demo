/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.atom.empire.das;

import java.sql.Connection;

import org.apache.empire.db.DBCommand;
import org.apache.empire.db.DBDatabase;
import org.apache.empire.db.DBDatabaseDriver;
import org.apache.empire.db.DBReader;
import org.apache.empire.db.DBSQLScript;
import org.apache.empire.db.DBTable;
import org.apache.empire.exceptions.EmpireException;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.util.Assert;

/**
 * DAO
 * 
 * @author obullxl@gmail.com
 * @version $Id: EmpireDAO.java, V1.0.1 2014年8月23日 上午11:16:30 $
 */
public class EmpireDAO extends JdbcDaoSupport {

    /** EmpireDB */
    private DBDatabase       empireDB;

    /** Empire驱动 */
    private DBDatabaseDriver empireDriver;

    /** 
     * @see org.springframework.jdbc.core.support.JdbcDaoSupport#checkDaoConfig()
     */
    public void checkDaoConfig() {
        super.checkDaoConfig();

        Assert.notNull(this.empireDB, "EmpireDB注入失败！");
        Assert.notNull(this.empireDriver, "empire驱动注入失败！");
    }

    /**
     * 获取EmpireDB
     */
    @SuppressWarnings("unchecked")
    public <T extends DBDatabase> T empireDB() {
        try {
            if (!this.empireDB.isOpen()) {
                this.empireDB.open(this.empireDriver, getConnection());
            }

            return (T) this.empireDB;
        } catch (EmpireException e) {
            throw translateEmpireException(e);
        }
    }

    /**
     * 获取Empire驱动
     */
    @SuppressWarnings("unchecked")
    public <T extends DBDatabaseDriver> T empireDriver() {
        return (T) this.empireDriver;
    }

    /**
     * 获取数据库连接
     */
    public Connection connection() {
        return this.getConnection();
    }

    /**
     * 检查数据库是否存在
     */
    public boolean isDatabaseExist() {
        Connection conn = this.connection();
        try {
            DBDatabase db = this.empireDB();
            if (db.getTables() == null || db.getTables().isEmpty()) {
                throw new AssertionError("There are no tables in this database!");
            }

            DBCommand cmd = db.createCommand();
            if (cmd == null) {
                throw new AssertionError("The DBCommand object is null.");
            }

            DBTable t = db.getTables().get(0);
            cmd.select(t.count());
            return (db.querySingleInt(cmd, -1, conn) >= 0);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 创建数据库
     */
    public void createDatabase() {
        Connection conn = getConnection();

        // create DLL for Database Definition
        DBSQLScript script = new DBSQLScript();
        this.empireDB.getCreateDDLScript(this.empireDriver, script);
        // Show DLL Statement
        System.out.println(script.toString());
        // Execute Script
        script.run(this.empireDriver, conn, false);
    }

    /**
     * DB异常转换
     */
    public RuntimeException translateEmpireException(EmpireException e) {
        return new EmpireDBException(e);
    }

    /**
     * 打开数据库读对象
     */
    public DBReader openReader(DBCommand cmd, Connection conn) {
        DBReader r = new DBReader();
        r.open(cmd, conn);
        return r;
    }

    // ~~~~~~~~~~~~~~~ 依赖注入 ~~~~~~~~~~~~~~~~~ //

    public void setEmpireDB(DBDatabase empireDB) {
        this.empireDB = empireDB;
    }

    public void setEmpireDriver(DBDatabaseDriver empireDriver) {
        this.empireDriver = empireDriver;
    }

}
