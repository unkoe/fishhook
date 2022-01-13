package com.example.fishhook.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileNameUtil;
import com.example.fishhook.common.BashConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 命令行工具, 支持平台 windows/linux
 * @author jojojo
 */
@Slf4j
public class CommandLineTool {

    public static final boolean IS_WINDOWS = System
            .getProperty("os.name")
            .toLowerCase().contains("win");

    public static final boolean IS_LINUX = System
            .getProperty("os.name")
            .toLowerCase().contains("linux");


    /**
     * 传入脚本的路径执行
     * @param bashPath 脚本路径
     * @throws IOException
     */
    public static void autoExec(String bashPath) throws IOException {
        Assert.isTrue(FileUtil.isFile(bashPath), String.format("%s不是一个合法的文件", bashPath));
        String name = FileNameUtil.extName(FileUtil.file(bashPath));
        if (IS_LINUX) {
            command("chmod 755 " + bashPath);
        }
        if (IS_WINDOWS
                && name.equals(BashConstant.SHELL_SUFFIX)) {
            throw new IOException("windows 平台仅支持 python 脚本使用");
        }

        switch (name) {
            case BashConstant
                    .SHELL_SUFFIX:
                shell(bashPath);
                break;
            case BashConstant
                    .PYTHON_SUFFIX:
                python(bashPath);
                break;
            default:
                log.warn("目前仅支持python和shell脚本");
        }
    }


    public static void shell(String file) throws IOException {
        command(String.format("%s %s", BashConstant.SHELL, file));
    }

    public static void python(String file) throws IOException {
        command(String.format("%s %s", BashConstant.PYTHON, file));
    }

    public static void command(String userCommand) throws IOException {
        log.info("exec command :{}", userCommand);

        Process process = Runtime.getRuntime().exec(userCommand);
        process.getOutputStream().close();

        String line;
        log.info("Standard Output:");
        BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while ((line = stdout.readLine()) != null) {
            log.info(line);
        }
        stdout.close();
        log.info("Standard Error:");
        BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while ((line = stderr.readLine()) != null) {
            log.info(line);
        }
        stderr.close();
        log.info("Done");
    }
}
