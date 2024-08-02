package com.example.SpringSecurityJWT.secured;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/crackit/v1/admin")
@PreAuthorize("hasRole(Role.ADMIN)")
public class AdminController {

    @GetMapping
    public String getAdmin()
    {
       return "Secured End point :: GET ADMIN Controller";
    }
}
