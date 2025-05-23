package net.j22.cloud.jwtauthapi.web;

import net.j22.cloud.jwtauthapi.dtos.CustomUser;
import net.j22.cloud.jwtauthapi.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/me")
    public ResponseEntity<CustomUser> authenticatedUser() {
        Authentication authentication =

                SecurityContextHolder.getContext().getAuthentication();

        CustomUser currentUser = (CustomUser)

                authentication.getPrincipal();

        return ResponseEntity.ok(currentUser);
    }
    @GetMapping
    public ResponseEntity<List<CustomUser>> allUsers() {
        List<CustomUser> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }
}
