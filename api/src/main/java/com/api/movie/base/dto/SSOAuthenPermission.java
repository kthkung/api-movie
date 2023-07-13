package com.api.movie.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SSOAuthenPermission {

    @JsonProperty("permissionId")
    private Integer permissionId;

    @JsonProperty("code")
    private String code;

    @JsonProperty("functionId")
    private Integer functionId;

    @JsonProperty("parentId")
    private Integer parentId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private String type;

    @JsonProperty("action")
    private String action;

    @JsonProperty("children")
    private List<SSOAuthenPermission> children;
}
