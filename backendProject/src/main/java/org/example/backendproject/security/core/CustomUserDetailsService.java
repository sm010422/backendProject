package org.example.backendproject.security.core;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {


    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userid)throws UsernameNotFoundException {
        User user = userRepository.findByUserid(userid).orElseThrow(
                ()-> new UsernameNotFoundException("해당 유저가 존재하지 않습니다 " + userid));

            return new CustomUserDetails(user);
        }
    @Override
    public UserDetails loadUserByUsername(String userid)throws UsernameNotFoundException {
        User user = userRepository.findByUserid(userid).orElseThrow(
                ()-> new UsernameNotFoundException("해당 유저가 존재하지 않습니다 " + userid));

        return new CustomUserDetails(user);
    }
}
