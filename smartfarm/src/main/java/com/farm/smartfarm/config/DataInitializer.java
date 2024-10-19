package com.farm.smartfarm.config;

import com.farm.smartfarm.domain.CropList;
import com.farm.smartfarm.domain.Inquiry;
import com.farm.smartfarm.domain.Notice;
import com.farm.smartfarm.domain.User;
import com.farm.smartfarm.repository.CropListRepository;
import com.farm.smartfarm.repository.InquiryRepository;
import com.farm.smartfarm.repository.NoticeRepository;
import com.farm.smartfarm.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserRepository userRepository;
    private final CropListRepository cropListRepository;
    private final NoticeRepository noticeRepository;
    private final InquiryRepository inquiryRepository;

    public DataInitializer(UserRepository userRepository, CropListRepository cropListRepository, NoticeRepository noticeRepository, InquiryRepository inquiryRepository) {
        this.userRepository = userRepository;
        this.cropListRepository = cropListRepository;
        this.noticeRepository = noticeRepository;
        this.inquiryRepository = inquiryRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        boolean exists = userRepository.existsByEmail("admin@admin");
        if (!exists) {
            User admin = User.builder()
                    .id(1L)
                    .name("관리자")
                    .email("admin@admin")
                    .pwd(encoder.encode("admin"))
                    .joinDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .role("ADMIN")
                    .build();
            System.out.println("admin = " + admin);
            userRepository.save(admin);
        }

        if (cropListRepository.count() == 0) {
            cropListRepository.save(CropList.builder()
                    .cropName("토마토")
                    .env_sun("하루 6~8시간")
                    .env_water("주 1회")
                    .write_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .writer("관리자")
                    .build());
            cropListRepository.save(CropList.builder()
                    .cropName("상추")
                    .env_sun("하루 4-5시간")
                    .env_water("주 2-3회")
                    .write_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .writer("관리자")
                    .build());
            cropListRepository.save(CropList.builder()
                    .cropName("딸기")
                    .env_sun("하루 8-10시간")
                    .env_water("주 3-4회")
                    .write_date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .writer("관리자")
                    .build());
        }
        User admin = userRepository.findByEmail("admin@admin").orElse(null);
        if (admin != null && noticeRepository.count() == 0) {
            noticeRepository.save(Notice.builder()
                    .noticeId(1L)
                    .user(admin)
                    .title("공지사항 제목")
                    .content("공지사항 내용입니다.")
                    .creationDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .viewCount(0L)
                    .build());
        }

        if (admin != null && inquiryRepository.count() == 0) {
            inquiryRepository.save(Inquiry.builder()
                    .inquiryId(1L)
                    .user(admin)
                    .title("문의사항 제목")
                    .content("문의사항 내용입니다.")
                    .creationDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                    .viewCount(0L)
                    .build());
        }
    }
}
