/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.atom.empire;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atom.empire.das.EmpireOSite;
import com.github.obullxl.lang.config.ConfigFactory;
import com.github.obullxl.plugin.das.dto.CfgDTO;

/**
 * Demo程序入口
 * 
 * @author obullxl@gmail.com
 * @version $Id: OSiteMain.java, V1.0.1 2014年8月23日 上午9:55:32 $
 */
public class OSiteMain {
    private static final Logger logger = LoggerFactory.getLogger("DEFAULT");

    /**
     * @param args
     */
    public static void main(String[] args) {
        logger.warn("* 系统参数:");
        Set<Map.Entry<String, String>> cfgSet = ConfigFactory.get().getConfig().entrySet();
        for (Map.Entry<String, String> entry : cfgSet) {
            logger.warn(" | {}  [{}]", entry.getKey(), entry.getValue());

            // 设置系统参数
            System.setProperty(entry.getKey(), entry.getValue());
        }

        // 启动系统
        final AbstractApplicationContext context = new ClassPathXmlApplicationContext(//
            "classpath*:/META-INF/spring/spring-context.xml");

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                // 释放资源
                try {
                    context.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        // Demo测试
        EmpireOSite osite = context.getBean("empireOSite", EmpireOSite.class);

        // 1.初始化数据库
        System.out.println("1.初始化数据库....");
        osite.setupDatabase();
        System.out.println("1.初始化数据库完成.");

        // 2.清空数据表内容
        System.out.println("2.清空数据表内容....");
        // osite.clearDatabase();
        System.out.println("2.清空数据表内容.");

        // 3.插入数据
        System.out.println("3.插入数据....");
        // osite.createConfig();
        /*
        for (int i = 1; i <= 1000; i++) {
            String idx = StringUtils.leftPad(Integer.toString(i), 4, "0");
            
            CfgDTO cfg = new CfgDTO();
            cfg.setCatg("Catg" + idx);
            cfg.setName("Name" + idx);
            cfg.setTitle("参数配置标题" + idx);
            cfg.setValue("参数值" + idx);
            cfg.setValueExt("参数值扩展" + idx);

            osite.createConfig(cfg);
        }
        */
        System.out.println("3.插入数据.");

        // 4.查询整条数据
        System.out.println("4.查询整条数据....");
        CfgDTO cfg = osite.findConfig("catg0011", "name0011");
        System.out.println(cfg);
        cfg = osite.findConfig("NotExist0011", "NotExist0011");
        System.out.println(cfg);
        System.out.println("4.查询整条数据.");

        // 5.查询部分字段
        System.out.println("5.查询部分字段....");
        List<Map<String, Object>> values = osite.statConfigCatg();
        System.out.println(values);
        System.out.println("5.查询部分字段.");

        // 6.数据统计
        System.out.println("6.数据统计....");
        List<Map<String, Object>> stat = osite.statConfigCatg();
        System.out.println(stat);
        System.out.println("6.数据统计.");

        // z.退出系统
        System.exit(0);
    }

}
