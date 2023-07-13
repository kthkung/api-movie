package com.api.movie.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SSOAuthenAppPermissionFunction {

    @JsonProperty("userFunctionId")
    private Integer userFunctionId;

    @JsonProperty("parentFunctionId")
    private Integer parentFunctionId;

    @JsonProperty("functionType")
    private String functionType;

    @JsonProperty("name")
    private String name;

    @JsonProperty("code")
    private String code;

    @JsonProperty("url")
    private String url;

    @JsonProperty("description")
    private String description;
}
