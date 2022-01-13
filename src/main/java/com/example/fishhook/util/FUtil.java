package com.example.fishhook.util;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 文件工具
 *
 * @author jojojo
 */
public class FUtil {
    
    public static Map<String, String> listTheFilesInTheDirectoryAndMapThem(File scriptSpace) {
        return Arrays.stream(FileUtil.ls(scriptSpace.getPath()))
                .collect(Collectors.toMap(File::getName, File::getPath, (a1, a2) -> a1));
    }

    public static Map<String, String> listDirectoriesToMap(String path) {
        return listDirectories(path).stream()
                .collect(Collectors.toMap(File::getName, File::getPath, (a1, a2) -> a1));
    }

    private static List<File> listDirectories(String path) {
        return Arrays.stream(FileUtil.ls(path))
                .filter(File::isDirectory)
                .collect(Collectors.toList());
    }
}
