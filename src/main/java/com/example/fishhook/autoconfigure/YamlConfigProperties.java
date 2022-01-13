package com.example.fishhook.autoconfigure;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 读取脚本配置上下文
 * @author jojojo
 */
@Component
public class YamlConfigProperties {

    @Value("${deploy.default-conf}")
    public String defaultConf = "git-webhook/";


    public String getDefaultConf() {
        return defaultConf;
    }

    public void setDefaultConf(String defaultConf) {
        this.defaultConf = defaultConf;
    }

    @Override
    public String toString() {
        return "YamlConfigContext{" +
                "defaultConf='" + defaultConf + '\'' +
                '}';
    }

}
