package com.farm.smartfarm.controller.api;

import com.farm.smartfarm.config.auth.PrincipalDetail;
import com.farm.smartfarm.domain.*;
import com.farm.smartfarm.dto.ResponseDto;
import com.farm.smartfarm.service.CropListService;
import com.farm.smartfarm.service.InquiryService;
import com.farm.smartfarm.service.NoticeService;
import com.farm.smartfarm.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class BoardApiController {
    @Autowired
    private CropListService cropListService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private InquiryService inquiryService;
    @Autowired
    private UserService userService;

    @PostMapping("/addCrop")
    public ResponseDto<Integer> addCrop(@RequestBody CropList cropList) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof PrincipalDetail) {
            PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
            System.out.println("BoardApiController: addCrop 호출됨");
            log.info(cropList.toString());
            User user = userService.findByEmail(principal.getUsername());
            cropListService.saveCrop(cropList, user.getName());
        }

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
    @DeleteMapping("/cropList/{id}/delete")
    public ResponseEntity<?> deleteCropList(@PathVariable Long id) {
        try {
            cropListService.deleteCropList(id);
            return ResponseEntity.ok().body("게시글이 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("게시글 삭제 중 오류가 발생했습니다.");
        }
    }

    @PostMapping("/addNotice")
    public ResponseDto<Integer> addNotice(@RequestBody Notice notice) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof PrincipalDetail) {
            PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
            System.out.println("BoardApiController: addNotice 호출됨");
            log.info(notice.toString());
            User user = userService.findByEmail(principal.getUsername());
            noticeService.saveNotice(notice, user.getName());
        }

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }
    @DeleteMapping("/notice/{noticeId}/delete")
    public ResponseEntity<?> deleteNotice(@PathVariable Long noticeId) {
        try {
            noticeService.deleteNotice(noticeId);
            return ResponseEntity.ok().body("게시글이 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("게시글 삭제 중 오류가 발생했습니다.");
        }
    }

    @PostMapping("/addInquiry")
    public ResponseDto<Integer> addInquiry(@RequestBody Inquiry inquiry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof PrincipalDetail) {
            PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
            System.out.println("BoardApiController: addInquiry 호출됨");
            log.info(inquiry.toString());
            User user = userService.findByEmail(principal.getUsername());
            inquiryService.saveInquiry(inquiry, user.getName());
        }

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PutMapping("/updateInquiry")
    public ResponseDto<Integer> updateInquiry(@RequestBody Inquiry inquiry) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof PrincipalDetail) {
            PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
            System.out.println("BoardApiController: updateInquiry 호출됨");
            log.info(inquiry.toString());
            User user = userService.findByEmail(principal.getUsername());
            inquiryService.updateInquiry(inquiry, user.getName());
        }

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/addInquiryComment")
    public ResponseDto<Integer> addInquiryComment(@RequestBody InquiryComment inquiryComment) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof PrincipalDetail) {
            PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
            System.out.println("BoardApiController: addInquiryComment 호출됨");
            log.info(inquiryComment.toString());
            User user = userService.findByEmail(principal.getUsername());
            Long inquiryId = inquiryComment.getInquiry().getInquiryId();
            System.out.println("inquiryComment = " + inquiryComment);
            System.out.println("inquiryId = " + inquiryId);
            inquiryService.saveInquiryComment(inquiryComment, user.getName(), inquiryId);
        }

        return new ResponseDto<Integer>(HttpStatus.OK.value(), 1);
    }

    @DeleteMapping("/inquiry/{inquiryId}/delete")
    public ResponseEntity<?> deleteInquiry(@PathVariable Long inquiryId) {
        try {
            inquiryService.deleteInquiry(inquiryId);
            return ResponseEntity.ok().body("게시글이 삭제되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("게시글 삭제 중 오류가 발생했습니다.");
        }
    }
}
