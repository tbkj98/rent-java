package com.tbkj.rent.service;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.tbkj.rent.model.entity.User;

@Service
public class SecurityServiceImpl implements SecurityService {
    
    @Override
    public User getUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Object principal = securityContext.getAuthentication().getPrincipal();
        return ((User) principal);
    }
}
