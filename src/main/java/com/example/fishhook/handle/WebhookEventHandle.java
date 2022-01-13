package com.example.fishhook.handle;

import cn.hutool.json.JSONUtil;
import com.example.fishhook.autoconfigure.InitialConfiguration;
import com.example.fishhook.resolver.PayloadResolver;
import com.example.fishhook.util.CommandLineTool;
import com.example.fishhook.util.FUtil;
import com.example.fishhook.util.YamlTool;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

/**
 * 钩子事件处理
 * @author jojojo
 */
@RestController
@Slf4j
public class WebhookEventHandle {

    @PostMapping("/release")
    public void releaseEvent() {
        log.info("现在Push了...");
    }

    @PostMapping("/push")
    public void pushEvent(HttpServletRequest request, @RequestBody Map<String, Object> params) {

        final InitialConfiguration initialConfiguration = new InitialConfiguration();
        final File scriptSpace = initialConfiguration.isScriptSpace(
                PayloadResolver.resolve(params, request).getRepositoryName());

        if (Objects.nonNull(scriptSpace)) {
            final Map<String, String> fileMap = FUtil.listTheFilesInTheDirectoryAndMapThem(scriptSpace);
            //TODO 须对脚本编排顺序进行更多模式的处理
            //做build.yaml定义的顺序编排执行
            for (String scriptName : YamlTool.resolveBuildNode(
                    initialConfiguration.getDefineYamlBuildFile(), scriptSpace.getName())) {
                if (fileMap.containsKey(scriptName)) {
                    try {
                        CommandLineTool.autoExec(fileMap.get(scriptName));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        log.info("现在Push了..., repository: {}",
                JSONUtil.parse(params.get("repository"))
                        .getByPath("name"));
    }




    @PostMapping("/all")
    public void allEvent() {
        log.info("现在Push了...");
    }

    @PostMapping("/creat/tag")
    public void tagEvent() {
        log.info("现在Push了...");
    }

    @PostMapping("/creat/branch")
    public void branchEvent() {
        log.info("现在Push了...");
    }

    @PostMapping("/creat/tagOrBranch")
    public void branchOrTagEvent() {
        log.info("现在Push了...");
    }

    @PostMapping("/pull")
    public void pullOrTagEvent() {
        log.info("现在Push了...");
    }
}
