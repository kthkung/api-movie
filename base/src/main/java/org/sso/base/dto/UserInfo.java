package org.sso.base.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {
    @JsonProperty("sub")
    public UUID sub;

    @JsonProperty("email_verified")
    public boolean emailVerified;

    @JsonProperty("name")
    public String name;

    @JsonProperty("preferred_username")
    public String preferredUsername;

    @JsonProperty("given_name")
    public String givenName;

    @JsonProperty("family_name")
    public String familyName;

    @JsonProperty("email")
    public String email;
}
