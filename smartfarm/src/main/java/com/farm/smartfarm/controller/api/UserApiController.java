package com.farm.smartfarm.controller.api;

import com.farm.smartfarm.domain.User;
import com.farm.smartfarm.dto.ResponseDto;
import com.farm.smartfarm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
public class UserApiController {
    @Autowired
    private UserService userService;

    @PostMapping("/joinProc")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("MemberApiController: join 호출됨");
        log.info(user.toString());
        userService.join(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }


    @GetMapping("/checkUserId")
    public boolean checkUserId(@RequestParam String userEmail) {
        log.info("Checking user Email: {}", userEmail);
        return userService.isUserIdAvailable(userEmail);
    }

    @PostMapping("/editProc")
    public ResponseDto<Integer> edit(@RequestBody User user) {
        System.out.println("MemberApiController: edit 호출됨");
        log.info(user.toString());
        userService.edit(user);
        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
}
