package com.hongru;

import com.hongru.config.LoadAppConfigFromFiles;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * 启动初始化类
 *
 */
public class AppInit
{
    public static void init() {
        while (!loadConfig()) {
            pathFault = true;
        }
    }

    private static boolean loadConfig() {
        if (pathFault) {
            System.out.print("路径无效,请重新输入info.properties的完整有效路径,结束用回车确认:");
        } else {
            System.out.print("请输入info.properties的完整有效路径,结束用回车确认:");
        }

        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();

        return LoadAppConfigFromFiles.load(filePath);
    }

    private static final Logger logger = LogManager.getLogger(AppInit.class);
    private static boolean pathFault;
}
