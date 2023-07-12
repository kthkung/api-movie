package com.api.movie.entities;

public interface User {
    
    public Long getId();
    public void setId(Long id);
    
    public String getUsername();
    public void setUsername(String username);
    
    public String getEmail();
    public void setEmail(String email);
}
