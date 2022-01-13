package com.example.fishhook.util;

import cn.hutool.core.io.FileUtil;
import com.example.fishhook.common.CommonConstant;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.Assert;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * yaml读取工具
 * @author jojojo
 */
public class YamlTool {

    public static List<String> resolveBuildNode(File file, String path) {
        return resolveBuildNode(file).get(path);
    }

    public static Map<String, List<String>> resolveBuildNode(File file) {
        Map<String, Map<String, List<String>>> map = null;
        try {
            map = new Yaml().load(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Assert.notNull(map, "解析错误");
        return map.get(CommonConstant.BUILD_NODE);
    }

    public static Map<String, String> resolveSettingNode(File file) {
        Map<String, Map<String, String>> map = null;
        try {
            map = new Yaml().load(new FileInputStream(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Assert.notNull(map, "解析错误");
        return map.get(CommonConstant.SETTING_NODE);
    }

    public static File creat(String path) {
        File file = null;
        try {
             file = FileUtil.writeFromStream(new ClassPathResource(CommonConstant.BUILD_FILE).getInputStream(),
                    new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
