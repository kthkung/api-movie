package org.sso.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SSOAuthenAppPermission {

    @JsonProperty("userPermissionId")
    private Integer userPermissionId;

    @JsonProperty("action")
    private String action;

    @JsonProperty("description")
    private String description;

    @JsonProperty("function")
    private SSOAuthenAppPermissionFunction function;
}
