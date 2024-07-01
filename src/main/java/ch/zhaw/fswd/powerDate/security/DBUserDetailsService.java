package ch.zhaw.fswd.powerDate.security;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import ch.zhaw.fswd.powerDate.entity.User;
import ch.zhaw.fswd.powerDate.entity.UserRepository;

@Service
public class DBUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findById(loginName);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found with login name: " + loginName);
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority(user.get().getRoleName()));

        return new org.springframework.security.core.userdetails.User(
                loginName,
                user.get().getPasswordHash(),
                authorities);
    }
}