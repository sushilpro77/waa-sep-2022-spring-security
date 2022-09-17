package edu.miu.springdataday3.security;

import edu.miu.springdataday3.entity.User;
import edu.miu.springdataday3.repo.UserRepo;
import edu.miu.springdataday3.security.AwesomeUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
@Transactional

public class AwesomeUserDetailsService implements UserDetailsService {

    private final UserRepo userRepo;

    public AwesomeUserDetailsService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByEmail(username);
        var userDetails = new AwesomeUserDetails(user);
        return userDetails;
    }

}
