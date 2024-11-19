package gr.knowledge.induction.security.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import gr.knowledge.induction.domain.User;
import gr.knowledge.induction.repository.UserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional // Ensure Data integrity for every database operation
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userRepository.findByUsername(username).orElseThrow(
            () -> new UsernameNotFoundException("User not found with username: {}" + username)
        );

        // Return a UserDetailsImpl for a specific user that has all the details (principal) of user's "account" with the roles
        return UserDetailsImpl.build(user);
    }
}
