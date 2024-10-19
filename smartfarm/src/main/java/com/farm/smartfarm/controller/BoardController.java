package com.farm.smartfarm.controller;

import com.farm.smartfarm.config.auth.PrincipalDetail;
import com.farm.smartfarm.domain.CropList;
import com.farm.smartfarm.domain.Inquiry;
import com.farm.smartfarm.domain.InquiryComment;
import com.farm.smartfarm.domain.Notice;
import com.farm.smartfarm.service.CropListService;
import com.farm.smartfarm.service.InquiryService;
import com.farm.smartfarm.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class BoardController {
    @Autowired
    private CropListService cropListService;
    @Autowired
    private NoticeService noticeService;
    @Autowired
    private InquiryService inquiryService;

    @GetMapping("/")
    public String index(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof PrincipalDetail) {
            PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
            model.addAttribute("userPrincipal", principal);
        }

        List<Notice> recentNotices = noticeService.getRecentNotices();
        model.addAttribute("recentNotices", recentNotices);

        List<Inquiry> recentInquiries = inquiryService.getRecentInquiries();
        model.addAttribute("recentInquiries", recentInquiries);

        return "index";
    }

    @GetMapping("/croplist")
    public String showCropList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof PrincipalDetail) {
            PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
            model.addAttribute("userPrincipal", principal);
        }
        List<CropList> cropList = cropListService.findAll();
        model.addAttribute("cropList", cropList);
        return "cropList";
    }

    @GetMapping("/write_croplist")
    public String writeCropList(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof PrincipalDetail) {
            PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
            model.addAttribute("userPrincipal", principal);
        }
        return "write_cropList";
    }

    @GetMapping("/inquiry")
    public String showInquiry(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof PrincipalDetail) {
            PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
            model.addAttribute("userPrincipal", principal);
        }
        List<Inquiry> inquiryList = inquiryService.findAll();
        model.addAttribute("inquiryList", inquiryList);
        return "inquiry";
    }
    @GetMapping("/write_inquiry")
    public String writeInquiry(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof PrincipalDetail) {
            PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
            model.addAttribute("userPrincipal", principal);
        }
        return "write_inquiry";
    }

    @GetMapping("/inquiry-detail/{inquiryId}")
    public String inquiryDetail(@PathVariable("inquiryId") Long inquiryId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof PrincipalDetail) {
            PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
            model.addAttribute("userPrincipal", principal);
        }

        Inquiry inquiry = inquiryService.findInquiryById(inquiryId);
        if (inquiry == null) {
            return "redirect:/inquiry";
        }

        List<InquiryComment> comments = inquiryService.findCommentsByInquiryId(inquiryId);
        model.addAttribute("comments", comments);

        model.addAttribute("inquiry", inquiry);
        return "detail_inquiry";
    }

    @GetMapping("/inquiry/edit/{inquiryId}")
    public String showEditInquiryPage(@PathVariable("inquiryId") Long inquiryId, Model model) {
        // 문의사항 ID를 통해 문의사항 정보 조회
        Inquiry inquiry = inquiryService.findInquiryById(inquiryId);

        // 조회한 문의사항 정보를 모델에 추가
        model.addAttribute("inquiry", inquiry);

        // 수정 페이지의 경로를 반환 (Thymeleaf 템플릿 경로)
        return "edit_inquiry";
    }

    @GetMapping("/notice")
    public String showNotice(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof PrincipalDetail) {
            PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
            model.addAttribute("userPrincipal", principal);
        }
        List<Notice> noticeList = noticeService.findAll();
        model.addAttribute("noticeList", noticeList);
        return "notice";
    }

    @GetMapping("/write_notice")
    public String writeNotice(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof PrincipalDetail) {
            PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
            model.addAttribute("userPrincipal", principal);
        }
        return "write_notice";
    }

    @GetMapping("/notice-detail/{noticeId}")
    public String noticeDetail(@PathVariable("noticeId") Long noticeId, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated() && authentication.getPrincipal() instanceof PrincipalDetail) {
            PrincipalDetail principal = (PrincipalDetail) authentication.getPrincipal();
            model.addAttribute("userPrincipal", principal);
        }

        Notice notice = noticeService.findNoticeById(noticeId);
        if (notice == null) {
            return "redirect:/notice";
        }

        model.addAttribute("notice", notice);
        return "detail_notice";
    }
}
