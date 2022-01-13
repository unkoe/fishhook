package com.example.fishhook.strategy;

import java.util.List;

/**
 * 脚本编排策略
 *
 * @author jojojo
 */
public interface ScriptLayoutStrategy {


    /**
     * 设置脚本的编排目录
     *
     * @param location 定位
     * @return 设置成功
     */
    boolean location(String location);


    /**
     * 开始工作
     *
     * @return 是否成功
     */
    List<String> work();


}
