package org.sso.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SSOAuthenPosition {

    @JsonProperty("ssoPositionId")
    private Integer ssoPositionId;

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;
}
