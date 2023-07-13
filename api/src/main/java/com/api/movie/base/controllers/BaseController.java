package com.api.movie.base.controllers;

import java.security.Principal;

public abstract class BaseController {
    public String currentUserName(Principal principal) {
        if (principal == null)
            return "TEST001"; //FIXME
        return principal.getName();
    }
}
