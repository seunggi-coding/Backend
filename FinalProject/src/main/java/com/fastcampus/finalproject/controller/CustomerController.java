package com.fastcampus.finalproject.controller;

import com.fastcampus.finalproject.dto.CustomerDTO;
import com.fastcampus.finalproject.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    // @RequiredArgsConstructor를 사용하려면 final 키워드 꼭 붙일것
    private final CustomerService customerService;

    // 회원가입 페이지 보여주기
    @GetMapping("/register")
    public String registerPage(Model model) throws SQLException {
        int total_num = customerService.getMaxCustNo();
        model.addAttribute("value", total_num+1);
        return "registerCustomers";
    }

    // 고객 목록 및 조회 페이지 보여주기
    @GetMapping("/list")
    public String listPage(@ModelAttribute CustomerDTO customerdto, Model model){
        // getCustomerList() 메서드를 호출하여 고객 목록을 가져옴
        List<CustomerDTO> customerList = customerService.getCustomerList();

        // Model에 고객 목록을 저장
        model.addAttribute("customerList", customerList);

        // customerList.jsp로 이동
        return "customerList";
    }

    // 고객 정보 수정 페이지 보여주기
    @GetMapping("/edit/{custNo}")
    public String editPage(@PathVariable int custNo, Model model) {
        // custNo를 이용하여 해당 고객의 정보를 가져옴
        CustomerDTO customer = customerService.getCustomerByNo(custNo);

        // Model에 고객 정보를 저장
        model.addAttribute("customer", customer);

        // editCustomers.jsp로 이동
        return "editCustomer";
    }

    // 회원가입 처리
    @PostMapping("/signup")
    public String signup(@ModelAttribute CustomerDTO customerdto) {
        System.out.println("sign 메서드 동작 시작");
        int saveResult = customerService.save(customerdto);
        System.out.println("saveResult = " + saveResult);
        if(saveResult > 0) {
            return "redirect:/";
        } else {
            return "registerCustomers";
        }
    }

    // 고객정보 수정
    @PostMapping("/update")
    public String update(@ModelAttribute CustomerDTO customerdto) {
        int updateResult = customerService.update(customerdto);
        if (updateResult > 0) {
            return "redirect:/customer/list";
        } else {
            // 실패 시 어떻게 처리할지 추가
            return "editCustomers";
        }
    }

}
