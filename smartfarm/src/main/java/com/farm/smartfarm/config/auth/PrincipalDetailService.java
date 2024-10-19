package com.farm.smartfarm.config.auth;

import com.farm.smartfarm.domain.User;
import com.farm.smartfarm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public PrincipalDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("id = " + email);
        try {
            User principal = userRepository.findByEmail(email).orElseThrow(() -> {
                return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : " + email);
            });
            return new PrincipalDetail(principal);
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("로그인 중 오류가 발생했습니다.");
        }
    }
}
