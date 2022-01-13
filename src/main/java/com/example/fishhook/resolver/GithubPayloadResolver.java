package com.example.fishhook.resolver;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import com.example.fishhook.common.RepositoryConstant;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Map;

/**
 * 通用载荷
 *
 * @author jojojo
 */
@ToString
@Data
@EqualsAndHashCode
public class GithubPayloadResolver extends PayloadResolver {

    @Override
    public void resolveImpl(Map<String, Object> params) {
        this.ref = (String) params.get(RepositoryConstant.REF);
        this.baseRef = (String) params.get(RepositoryConstant.BASE_REF);
        final JSON repository = JSONUtil.parse(params.get(RepositoryConstant.REPOSITORY));
        this.repositoryName = repository
                .getByPath(RepositoryConstant.NAME, String.class);
        this.url = repository
                .getByPath(RepositoryConstant.URL, String.class);
        this.gitUrl = repository
                .getByPath(RepositoryConstant.GIT_URL, String.class);
        this.gitSshUrl = repository
                .getByPath(RepositoryConstant.GIT_SSH_URL, String.class);
    }


}
