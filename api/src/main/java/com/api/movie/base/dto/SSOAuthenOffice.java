package com.api.movie.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SSOAuthenOffice {

    @JsonProperty("ssoOfficeId")
    private Integer ssoOfficeId;

    @JsonProperty("provinceNo")
    private String provinceNo;

    @JsonProperty("ssoOfficeCode")
    private String ssoOfficeCode;

    @JsonProperty("name")
    private String name;

    @JsonProperty("address1")
    private String address1;

    @JsonProperty("address2")
    private String address2;

    @JsonProperty("address3")
    private String address3;

    @JsonProperty("telephone")
    private String telephone;

    @JsonProperty("status")
    private String status;

    @JsonProperty("taxId")
    private String taxId;

    @JsonProperty("runningTempCode")
    private String runningTempCode;

    @JsonProperty("runningM39No")
    private String runningM39No;

    @JsonProperty("unpRegion")
    private String unpRegion;

    @JsonProperty("unpOnline")
    private String unpOnline;

    @JsonProperty("unpSize")
    private String unpSize;

    @JsonProperty("label")
    private String label;
}
