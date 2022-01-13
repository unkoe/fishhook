package com.example.fishhook.common;

import java.io.File;

/**
 * 通用常量
 *
 * @author jojojo
 */
public class CommonConstant {

    /**
     * 工作目录
     */
    public static final String USER_HOME = "user.home";

    /**
     * 工作目录
     */
    public static final String GIT_WEBHOOK_HOME = "GIT_WEBHOOK_HOME";

    /**
     * 配置文件目录
     */
    public static final String CONF_DIR = "conf";

    /**
     * 脚本构建工作目录
     */
    public static final String SCRIPT_BUILD_DIR = "build";

    /**
     * 脚本构建工作目录
     */
    public static final String CLONE_CODE = "code";


    /**
     * 默认构建的脚本目录
     */
    public static final String DEFAULT_BUILD_DIR = SCRIPT_BUILD_DIR + File.separator + "default";

    /**
     * build 配置
     */
    public static final String BUILD_FILE = "build.yaml";

    /**
     * 默认构建的脚本目录
     */
    public static final String FULL_BUILD_FILE = CONF_DIR + "build.yaml";

    /**
     * build.yaml中定义的 build 节点
     */
    public static final String BUILD_NODE = "build";

    /**
     * build.yaml中定义的 seting 节点
     */
    public static final String SETTING_NODE = "setting";

    /**
     * build.yaml中定义的 strategy 节点
     */
    public static final String SETTING_STRATEGY_NODE = "strategy";



}
