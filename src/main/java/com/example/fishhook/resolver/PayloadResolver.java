package com.example.fishhook.resolver;

import cn.hutool.core.util.StrUtil;
import com.example.fishhook.common.RepositoryConstant;
import com.example.fishhook.exception.GitParameterResolverException;
import lombok.Getter;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 载荷解析器
 *
 * @author jojojo
 */

@Getter
public abstract class PayloadResolver {

    protected String xFlag;

    protected String ref;

    protected String baseRef;

    protected String repositoryName;

    protected String url;

    protected String gitUrl;

    protected String gitSshUrl;

    protected String homePage;


    /**
     * 实现
     *
     * @param params 解析参数
     * @param request 请求参数
     */
    public static PayloadResolver resolve(Map<String, Object> params, HttpServletRequest request) {
        if (StrUtil.isNotBlank(request.getHeader(RepositoryConstant.X_HEAD_GITHUB))) {
            final PayloadResolver resolver = new GithubPayloadResolver();
            resolver.resolveImpl(params);
            return resolver;
        }
        if (StrUtil.isNotBlank(request.getHeader(RepositoryConstant.X_HEAD_GITLAB))) {
            final PayloadResolver resolver = new GitlabPayloadResolver();
            resolver.resolveImpl(params);
            return resolver;
        }
        throw new GitParameterResolverException("解析异常");
    }

    /**
     * 实现
     *
     * @param params 解析参数
     */
    public abstract void resolveImpl(Map<String, Object> params);
}
