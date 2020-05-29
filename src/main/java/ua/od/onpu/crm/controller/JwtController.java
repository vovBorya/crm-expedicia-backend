package ua.od.onpu.crm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ua.od.onpu.crm.dao.model.security.AuthRequest;
import ua.od.onpu.crm.dao.model.security.AuthResponse;
import ua.od.onpu.crm.dao.model.security.JwtUtil;
import ua.od.onpu.crm.service.MyUserDetailsService;

@RestController
public class JwtController {
    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    MyUserDetailsService userDetailsService;

    @CrossOrigin(allowedHeaders = "Content-Type")
    @PostMapping(value = "/api/auth")
    public ResponseEntity<?> createAuthToken(@RequestBody AuthRequest request) throws BadCredentialsException {
        try {
            authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        } catch (BadCredentialsException ex) {
            ex.printStackTrace();
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthResponse(jwt));
    }
}
