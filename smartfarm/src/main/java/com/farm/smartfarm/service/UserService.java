package com.farm.smartfarm.service;

import com.farm.smartfarm.domain.User;
import com.farm.smartfarm.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public void join(User user) {
        System.out.println("join 메서드 동작");
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        ZonedDateTime nowInKorea = ZonedDateTime.now(ZoneId.of("Asia/Seoul"));
        LocalDateTime localDateTimeInKorea = nowInKorea.toLocalDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String formattedDate = localDateTimeInKorea.format(formatter);
        user.setJoinDate(formattedDate);

        String rawPwd = user.getPwd();
        String encPwd = encoder.encode(rawPwd);
        user.setPwd(encPwd);

        user.setRole("USER");

        userRepository.save(user);
    }

    @Transactional(readOnly = true)
    public boolean isUserIdAvailable(String email) {
        Optional<User> existingMemberOptional = userRepository.findByEmail(email);
        return existingMemberOptional.isEmpty();
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다. 이메일: " + email));
    }

    public void edit(User user) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        User editUser = userRepository.findByEmail(user.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다. Email: " + user.getEmail()));

        editUser.setName(user.getName());
        editUser.setPwd(encoder.encode(user.getPwd()));

        userRepository.save(editUser);
    }

    public User findByName(String writer) {
        User user = userRepository.findByName(writer)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다. 이름: " + writer));
        return user;
    }

}
