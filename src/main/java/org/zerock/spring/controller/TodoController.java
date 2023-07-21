package org.zerock.spring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.spring.dto.TodoDTO;
import org.zerock.spring.service.TodoService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/todo")
@RequiredArgsConstructor
@Log4j2
public class TodoController {

    private final TodoService todoService;

    @GetMapping("/list")
    public String list(Model model) {
        log.info("todo list........");
         //모델에 담아서 뷰로

        model.addAttribute("dtoList",todoService.getAll());
        return "/todo/list";
    }

    // @RequestMapping(value = "/register", method = RequestMethod.GET)
    @GetMapping("/register")
    public void registerGET() {
        log.info("GET todo register........");
    }

    @PostMapping("/register")
    public String registerPost(@Valid TodoDTO todoDTO,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {
        log.info("POST todo register.......");

        if(bindingResult.hasErrors()) {
            log.info("has error.......");

            //redirectAttributes : 스프링 mvc에서 제공하는 Model. 리다이렉트+모델기능함
            //모델에 "error"란 이름으로 저 데이터들을 담아서 리다이렉트 <--- 예외출력 추가하기
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/todo/register";
        }

        todoService.register(todoDTO);
        log.info(todoDTO);

        return "redirect:/todo/list";
    }

    @GetMapping({"/read", "/modify"}) // read, modify 둘 다 조회되는 화면은 동일
    public void read(@RequestParam("tno") Long tno, Model model) { // 파라미터가 하나라 생략가능
        log.info("call read get method....");

        TodoDTO todoDTO = todoService.getOne(tno);

        model.addAttribute("dto", todoDTO);
        //GetMapping에서 return으로 페이지를 명시하지않으면 url과 동일한 부분으로 보낸다!
    }

    @PostMapping("/remove")
    public String remove(Long tno, RedirectAttributes redirectAttributes) {
        log.info("call remove method post .....");
        log.info("tno = {}", tno);

        todoService.remove(tno);

        return "redirect:/todo/list";
    }
}
