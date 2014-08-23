/**
 * Author: obullxl@gmail.com
 * Copyright (c) 2004-2014 All Rights Reserved.
 */
package com.atom.empire;

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
        osite.clearDatabase();
        System.out.println("2.清空数据表内容.");

        // 3.插入数据
        System.out.println("3.插入数据....");
        CfgDTO cfg1 = new CfgDTO();
        cfg1.setCatg("Catg1");
        cfg1.setName("Name1");
        cfg1.setTitle("参数配置标题1");
        cfg1.setValue("参数值1");
        cfg1.setValueExt("参数值扩展1");

        osite.createConfig(cfg1);

        System.out.println("3.插入数据.");

        // z.退出系统
        System.exit(0);
    }

}
