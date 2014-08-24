/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.atom.empire.das;

import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.empire.db.DBCmpType;
import org.apache.empire.db.DBColumnExpr;
import org.apache.empire.db.DBCommand;
import org.apache.empire.db.DBReader;
import org.apache.empire.db.DBRecord;
import org.springframework.transaction.annotation.Transactional;

import com.atom.empire.das.osite.auto.OSiteDB;
import com.atom.empire.das.osite.auto.records.CfgTBO;
import com.github.obullxl.lang.utils.DateUtils;
import com.github.obullxl.plugin.das.dto.CfgDTO;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

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
        if (!this.isDatabaseExist()) {
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

        db.executeDelete(db.T_CFG, db.createCommand(), conn);
        db.executeDelete(db.T_CTG, db.createCommand(), conn);
    }

    /**
     * 参数配置-新增对象
     */
    @Transactional
    public void createConfig() {
        OSiteDB db = this.empireDB();
        Connection conn = this.connection();

        for (int i = 1; i <= 1000; i++) {
            String idx = StringUtils.leftPad(Integer.toString(i), 4, "0");

            CfgDTO cfg = new CfgDTO();
            cfg.setCatg("Catg" + RandomUtils.nextInt(10));
            cfg.setName("Name" + idx);
            cfg.setTitle("参数配置标题" + idx);
            cfg.setValue("参数值" + idx);
            cfg.setValueExt("参数值扩展" + idx);

            DBRecord rec = new DBRecord();

            String[] keys = new String[] { cfg.getCatg(), cfg.getName() };
            if (db.T_CFG.recordExists(keys, conn)) {
                // 已经存在
                rec.read(db.T_CFG, keys, conn);
            } else {
                // 新建对象
                rec.create(db.T_CFG);
                rec.setValue(db.T_CFG.C_CATG, cfg.getCatg());
                rec.setValue(db.T_CFG.C_NAME, cfg.getName());
                rec.setValue(db.T_CFG.C_NEW_TIME, DateUtils.toStringDL(new Date()));
            }

            // 设置值
            rec.setValue(db.T_CFG.C_TITLE, cfg.getTitle());
            rec.setValue(db.T_CFG.C_VALUE, cfg.getValue());
            rec.setValue(db.T_CFG.C_VALUE_EXT, cfg.getValueExt());
            rec.setValue(db.T_CFG.C_UPD_TIME, DateUtils.toStringDL(new Date()));

            rec.update(conn);
            rec.close();
        }

        db.commit(conn);
        db.close(conn);
        /*
        // 属性复制
        CfgTBO tb = new CfgTBO(db);
        tb.create(db.T_CFG, conn);

        tb.setCatg(cfg.getCatg());
        tb.setName(cfg.getName());
        tb.setTitle(cfg.getTitle());
        tb.setValueColumn(cfg.getValue());
        tb.setValueExt(cfg.getValueExt());

        tb.update(conn);
        */
    }

    /**
     * 查询
     */
    public CfgDTO findConfig(String catg, String name) {
        OSiteDB db = this.empireDB();
        Connection conn = this.connection();

        DBCommand cmd = db.createCommand();
        cmd.select(db.T_CFG.getColumns());
        cmd.where(db.T_CFG.C_CATG.cmp(DBCmpType.EQUAL, catg).and(db.T_CFG.C_NAME.cmp(DBCmpType.EQUAL, name)));

        DBReader reader = this.openReader(cmd, conn);
        if (!reader.moveNext()) {
            return null;
        }

        CfgTBO tb = new CfgTBO(db);
        tb.create(db.T_CFG);
        reader.initRecord(db.T_CFG, tb);
        reader.close();

        if (!tb.isValid()) {
            return null;
        }

        CfgDTO cfg = new CfgDTO();
        cfg.setCatg(catg);
        cfg.setName(name);
        cfg.setTitle(tb.getTitle());
        cfg.setValue(tb.getValue_());
        cfg.setValueExt(tb.getValueExt());
        cfg.setNewTime(tb.getNewTime());
        cfg.setUpdTime(tb.getUpdTime());

        return cfg;
    }

    /**
     * 统计分类条数
     */
    public List<Map<String, Object>> statConfigCatg() {
        OSiteDB db = this.empireDB();
        Connection conn = this.connection();

        DBColumnExpr COUNT = db.T_CFG.count();
        DBColumnExpr MAX = db.T_CFG.C_NAME.max();
        DBColumnExpr MIN = db.T_CFG.C_NAME.min();
        DBCommand cmd = db.createCommand();
        cmd.select(db.T_CFG.C_CATG);
        cmd.select(COUNT, MAX, MIN);
        cmd.groupBy(db.T_CFG.C_CATG);

        List<Map<String, Object>> values = Lists.newArrayList();

        DBReader reader = this.openReader(cmd, conn);
        while (reader.moveNext()) {
            int count = reader.getFieldCount();

            Map<String, Object> value = Maps.newConcurrentMap();
            for (int i = 0; i < count; i++) {
                // DBColumnExpr expr = reader.getColumnExpr(i);
                // value.put(expr.getName(), reader.getValue(expr));
            }
            
            value.put(db.T_CFG.C_CATG.getName(), reader.getString(db.T_CFG.C_CATG));
            value.put("COUNT", reader.getInt(COUNT));
            value.put("MAX_NAME", reader.getString(MAX));
            value.put("MIN_NAME", reader.getString(MIN));
            values.add(value);
        }

        reader.close();

        return values;
    }

}
