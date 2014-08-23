/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.atom.empire.das;

import java.sql.Connection;

import org.springframework.transaction.annotation.Transactional;

import com.atom.empire.das.auto.OSiteDB;
import com.atom.empire.das.auto.records.CfgTBO;
import com.github.obullxl.plugin.das.dto.CfgDTO;

/**
 * Empire服务实现
 * 
 * @author obullxl@gmail.com
 * @version $Id: EmpireOSite.java, V1.0.1 2014年8月23日 上午11:26:11 $
 */
public class EmpireOSite extends EmpireDAO {

    /**
     * 检查数据库
     */
    @Transactional
    public void setupDatabase() {
        if(!this.isDatabaseExist()) {
            this.createDatabase();
        }
    }

    /**
     * 清空数据表记录
     */
    @Transactional
    public void clearDatabase() {
        OSiteDB db = this.empireDB();
        Connection conn = this.connection();
        
        db.executeDelete(db.CFG, db.createCommand(), conn);
        db.executeDelete(db.CTG, db.createCommand(), conn);
    }
    
    /**
     * 参数配置-新增对象
     */
    public void createConfig(CfgDTO cfg) {
        OSiteDB db = this.empireDB();
        Connection conn = this.connection();
        
        // 属性复制
        CfgTBO tb = new CfgTBO(db);
        tb.create(db.CFG, conn);
        
        tb.setCatg(cfg.getCatg());
        tb.setName(cfg.getName());
        tb.setTitle(cfg.getTitle());
        tb.setValueColumn(cfg.getValue());
        tb.setValueExt(cfg.getValueExt());
        
        tb.update(conn);
    }
    
}
