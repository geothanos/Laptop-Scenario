package gr.knowledge.induction.web;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.knowledge.induction.domain.ERole;
import gr.knowledge.induction.domain.Role;
import gr.knowledge.induction.domain.User;
import gr.knowledge.induction.dto.payload.request.LoginRequestDto;
import gr.knowledge.induction.dto.payload.request.SignupRequestDto;
import gr.knowledge.induction.dto.payload.response.JwtResponseDto;
import gr.knowledge.induction.dto.payload.response.MessageResponseDto;
import gr.knowledge.induction.repository.RoleRepository;
import gr.knowledge.induction.repository.UserRepository;
import gr.knowledge.induction.security.jwt.JwtUtils;
import gr.knowledge.induction.security.service.UserDetailsImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")  // Set the base path for all endpoints of this controller
public class AuthController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticatedUser(@Valid @RequestBody LoginRequestDto LoginRequesDto){
        // Authentication token-like object will be created if the user is Authenticated depending on the username and the password he provided
        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(
                LoginRequesDto.getUsername(), LoginRequesDto.getPassword()));

        // Set the authentication in the security context allowing the user to be authenticated and give access to user in secured data
        SecurityContextHolder.getContext().setAuthentication(authentication);
        // Generate JWT for the authenticated user
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        // Retrieve Principal (user details data) from authentication to userDetails
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        // Retrieve the roles of the user from the Principal 
        List<String> roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        // Return a JwtResponse object including all user details
        return ResponseEntity.ok(new JwtResponseDto(jwt, 
                            userDetails.getId(), 
                            userDetails.getUsername(), 
                            userDetails.getFirstName(),
                            userDetails.getLastName(),
                            roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequestDto signUpRequestDto) {

        // Check if the username already exists and return a coresponding message if so
        if (userRepository.existsByUsername(signUpRequestDto.getUsername())) {
            return ResponseEntity.badRequest()
            .body(new MessageResponseDto("Error: Username is already taken!"));
        }

        // Create a new User entity object usign the basic information he provided
        User user = new User(signUpRequestDto.getUsername(),
                passwordEncoder.encode(signUpRequestDto.getPassword()),
                signUpRequestDto.getFirstname(),
                signUpRequestDto.getLastname()
                );

        // Set ([a,b,...]) of string roles the user provided.
        Set<String> strRoles = signUpRequestDto.getRole(); 
        
        // Construct an empty set of Role objects
        Set<Role> roles = new HashSet<>();

        // Assign role (roles) to the User
        // If no roles are provided during the signUp, the user will receive the role ROLE_USER
        if (strRoles == null) {
            Role userRole = (Role) roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        // Check every role provided by the user and assign the coresponding role (ROLE_ADMIN, ROLE_MODERATOR or the default ROLE_USER) 
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Admin Role is not found."));
                        roles.add(adminRole);
                        break;
                    
                    case "moderator":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Moderator Role is not found."));
                        roles.add(modRole);
                        break;
                    
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: User Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        // Set user roles
        user.setRoles(roles);
        // Save user to database
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponseDto("User registered successfully!"));
    }
}
