package com.example.fishhook.autoconfigure;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.example.fishhook.common.CommonConstant;
import com.example.fishhook.util.SpringContextUtil;
import com.example.fishhook.util.YamlTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

import java.io.File;
import java.util.Arrays;

/**
 * 初始化配置
 * @author jojojo
 */
@Slf4j
public class InitialConfiguration {

    private final String yamlWorkDir;

    private final String envWorkDir;

    public File file;

    public String confDir;

    public String buildFile;

    public String scriptBuildDir;


    public InitialConfiguration() {
        this.yamlWorkDir = System.getProperty(CommonConstant.USER_HOME) + File.separator;
                SpringContextUtil.getBean(YamlConfigProperties.class).getDefaultConf();
        this.envWorkDir = System.getenv(CommonConstant.GIT_WEBHOOK_HOME);
        Assert.isTrue(automaticallySwitchWorkingDirectory(), "初始化工作环境变量和内部属性配置时出错");
    }


    /**
     * 检查 env 和默认设置的工作目录，列出工作目录下的脚本空间。<br>
     * 每次监听到 webhook 将触发相应仓库名称的脚本空间，每一个脚本空间代表 1 个仓库的未经编排的 webhook 脚本
     * @see #envWorkDir
     * @see #automaticallySwitchWorkingDirectory()
     * @return 所有的脚本空间
     */
    public File[] listScriptSpacesInTheWorkingDirectory() {
        return FileUtil.ls(
                envWorkDir + File.separator + CommonConstant.SCRIPT_BUILD_DIR);
    }

    /**
     * 在工作目录下的的脚本空间中进行搜索匹配
     * <p>
     *     脚本空间在工作目录之下，具体请查看
     *     {@link com.example.fishhook.common.CommonConstant#SCRIPT_BUILD_DIR} 常量定义。<br>
     *     如果通过一个具有意义参数匹配到相应的脚本空间，将返回 1个该路径的文件对象。<br>
     *
     * @see CommonConstant#SCRIPT_BUILD_DIR 定义脚本空间工作目录的常量字段
     * @see #listScriptSpacesInTheWorkingDirectory() 方法引用
     * @param name 脚本空间名称
     * @return 如果存在返回该文件对象，不存在返回 null
     */
    public File isScriptSpace(String name) {
        //TODO：应该只激活 build.yaml 中定义的脚本空间
        final File[] files = listScriptSpacesInTheWorkingDirectory();
        Assert.hasText(name, "匹配的文件名必须合法");
        return Arrays.stream(files)
                .distinct()
                .filter(file -> name.equals(file.getName()))
                .findFirst()
                .orElse(null);
    }

    public boolean automaticallySwitchWorkingDirectory() {
        if (StrUtil.isNotBlank(envWorkDir)) {
            return initializeResources(envWorkDir);
        }
        if (StrUtil.isBlank(envWorkDir) && StrUtil.isNotBlank(yamlWorkDir)) {
            return initializeResources(yamlWorkDir);
        }
        return false;
    }



    private boolean initializeResources(String workDir) {
        this.confDir = workDir + File.separator + CommonConstant.CONF_DIR;
        this.buildFile = confDir + File.separator + CommonConstant.BUILD_FILE;
        this.scriptBuildDir = workDir + File.separator + CommonConstant.SCRIPT_BUILD_DIR;
        FileUtil.mkdir(confDir);
        FileUtil.mkdir(scriptBuildDir);
        FileUtil.mkdir(workDir + File.separator + CommonConstant.DEFAULT_BUILD_DIR);

        log.debug("用户目录是: {}",CommonConstant.USER_HOME);
        if (!FileUtil.exist(buildFile)) {
            YamlTool.creat(buildFile);
        }
        this.file = new File(buildFile);
        YamlTool.resolveBuildNode(file).keySet()
                .forEach(scriptSpace -> FileUtil
                        .mkdir(scriptBuildDir + File.separator + scriptSpace
                                + File.separator + CommonConstant.CLONE_CODE));

        return true;
    }

    public File getDefineYamlBuildFile() {
        return new File(buildFile);
    }
}
