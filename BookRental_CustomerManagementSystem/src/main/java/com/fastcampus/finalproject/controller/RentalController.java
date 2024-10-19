package com.fastcampus.finalproject.controller;

import com.fastcampus.finalproject.dto.RentalAmountDTO;
import com.fastcampus.finalproject.dto.RentalDTO;
import com.fastcampus.finalproject.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/rental")
@RequiredArgsConstructor
public class RentalController {
    // @RequiredArgsConstructor를 사용하려면 final 키워드 꼭 붙일것
    private final RentalService rentalService;

    // 도서 대여 리스트 페이지 보여주기
    @GetMapping("/list")
    public String rentalList(Model model) {
        List<RentalDTO> rentalList = rentalService.getRentalList();
        model.addAttribute("rentalList", rentalList);
        return "rentalList";
    }

    @GetMapping("/amount")
    public String getRentalAmountList(Model model) {
        // 대여금액 조회 서비스 호출
        List<RentalAmountDTO> rentalAmountList = rentalService.getRentalAmountList();

        // Model에 조회 결과를 저장
        model.addAttribute("rentalAmountList", rentalAmountList);

        // rentalAmountList.jsp로 이동
        return "rentalAmount";
    }
}
