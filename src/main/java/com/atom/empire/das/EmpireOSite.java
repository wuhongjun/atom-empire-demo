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

import com.atom.empire.das.auto.OSiteDB;
import com.atom.empire.das.auto.records.CfgTBO;
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

        db.executeDelete(db.CFG, db.createCommand(), conn);
        db.executeDelete(db.CTG, db.createCommand(), conn);
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
            if (db.CFG.recordExists(keys, conn)) {
                // 已经存在
                rec.read(db.CFG, keys, conn);
            } else {
                // 新建对象
                rec.create(db.CFG);
                rec.setValue(db.CFG.CATG, cfg.getCatg());
                rec.setValue(db.CFG.NAME, cfg.getName());
                rec.setValue(db.CFG.NEW_TIME, DateUtils.toStringDL(new Date()));
            }

            // 设置值
            rec.setValue(db.CFG.TITLE, cfg.getTitle());
            rec.setValue(db.CFG.VALUE, cfg.getValue());
            rec.setValue(db.CFG.VALUE_EXT, cfg.getValueExt());
            rec.setValue(db.CFG.UPD_TIME, DateUtils.toStringDL(new Date()));

            rec.update(conn);
            rec.close();
        }

        db.commit(conn);
        db.close(conn);
        /*
        // 属性复制
        CfgTBO tb = new CfgTBO(db);
        tb.create(db.CFG, conn);

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
        cmd.select(db.CFG.getColumns());
        cmd.where(db.CFG.CATG.cmp(DBCmpType.EQUAL, catg).and(db.CFG.NAME.cmp(DBCmpType.EQUAL, name)));

        DBReader reader = this.openReader(cmd, conn);
        if (!reader.moveNext()) {
            return null;
        }

        CfgTBO tb = new CfgTBO(db);
        tb.create(db.CFG);
        reader.initRecord(db.CFG, tb);
        reader.close();

        if (!tb.isValid()) {
            return null;
        }

        CfgDTO cfg = new CfgDTO();
        cfg.setCatg(catg);
        cfg.setName(name);
        cfg.setTitle(tb.getTitle());
        cfg.setValue(tb.getValueColumn());
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

        DBColumnExpr COUNT = db.CFG.count();
        DBColumnExpr MAX = db.CFG.NAME.max();
        DBColumnExpr MIN = db.CFG.NAME.min();
        DBCommand cmd = db.createCommand();
        cmd.select(db.CFG.CATG);
        cmd.select(COUNT, MAX, MIN);
        cmd.groupBy(db.CFG.CATG);

        List<Map<String, Object>> values = Lists.newArrayList();

        DBReader reader = this.openReader(cmd, conn);
        while (reader.moveNext()) {
            int count = reader.getFieldCount();

            Map<String, Object> value = Maps.newConcurrentMap();
            for (int i = 0; i < count; i++) {
                // DBColumnExpr expr = reader.getColumnExpr(i);
                // value.put(expr.getName(), reader.getValue(expr));
            }
            
            value.put(db.CFG.CATG.getName(), reader.getString(db.CFG.CATG));
            value.put("COUNT", reader.getInt(COUNT));
            value.put("MAX_NAME", reader.getString(MAX));
            value.put("MIN_NAME", reader.getString(MIN));
            values.add(value);
        }

        reader.close();

        return values;
    }

}
