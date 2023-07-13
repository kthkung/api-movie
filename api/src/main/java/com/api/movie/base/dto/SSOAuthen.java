package com.api.movie.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SSOAuthen {

    @JsonProperty("username")
    private String username;

    @JsonProperty("fullName")
    private String fullName;

    @JsonProperty("ssoPosition")
    private SSOAuthenPosition ssoPosition;

    @JsonProperty("ssoOffice")
    private SSOAuthenOffice ssoOffice;

    @JsonProperty("appPermission")
    private List<SSOAuthenAppPermission> appPermission;

    @JsonProperty("hierarchyPermission")
    private List<SSOAuthenPermission> hierarchyPermission;
}
