package ch.zhaw.fswd.powerDate.boundary;

import java.security.Principal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.RolesAllowed;

@RestController
public class AuthEndpointREST {
    
    @GetMapping("/auth/current")
    @RolesAllowed("USER")
    public String get(Principal principal) {
        return principal.getName();
    }
}
