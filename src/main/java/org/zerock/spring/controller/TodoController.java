package org.zerock.spring.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.spring.dto.PageRequestDTO;
import org.zerock.spring.dto.PageResponseDTO;
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

//    @GetMapping("/list")
//    public String list(Model model) {
//        log.info("todo list........");
//         //모델에 담아서 뷰로
//
//        model.addAttribute("dtoList",todoService.getAll());
//        return "/todo/list";
//    }

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
    public void read(@RequestParam("tno") Long tno,
                     PageRequestDTO pageRequestDTO,
                     Model model) { // 파라미터가 하나라 생략가능


        TodoDTO todoDTO = todoService.getOne(tno);

        model.addAttribute("dto", todoDTO);
        //GetMapping에서 return으로 페이지를 명시하지않으면 url과 동일한 부분으로 보낸다!
    }

    @PostMapping("/remove")
    public String remove(Long tno,
                         PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes) {
        log.info("call remove method post .....");
        log.info("tno = {}", tno);

        todoService.remove(tno);

        //삭제시에는 무조건 1페이지로 이동시키기
        redirectAttributes.addAttribute("page",1);
        redirectAttributes.addAttribute("size", pageRequestDTO.getSize());

        return "redirect:/todo/list?"+pageRequestDTO.getLink();
    }




    /**
     * addFlashAttribute 와 addAttribute 차이
     * addFlashAttribute : POST방식, 일회성이라 새로고침시 데이터 사라짐 즉, 리다이렉트 후 값 소멸
     * addAttribute : GET방식 , 새로고침해도 값 유지
     */
    @PostMapping("/modify")
    public String modify(@Valid TodoDTO todoDTO,
                         BindingResult bindingResult,
                         PageRequestDTO pageRequestDTO,
                         RedirectAttributes redirectAttributes) {

        if(bindingResult.hasErrors()) {
            log.info("has errors............");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors()); //<-- 에러정보는 한번 출력하고 필요없으니 FLASH
            redirectAttributes.addAttribute("tno",todoDTO.getTno()); //<-- 몇번째 질문인지 tno값은 계속 사용되니까 유지되도록 addAttribute

            return "redirect:/todo/modify"; //<---여기서 의문이였음! modify?tno=XXX가 없는데 어떻게 해당 글로 리다이렉트시키지?
            //근데 생각해보면, GET요청일 때, 요청데이터가 쿼리파라미터로 실려가는거야
            //그래서 리다이렉트로 다시 요청이 들어가는데 addAttribute를 통해 데이터가 GET으로 요청되니까 tno정보가 쿼리파라미터에 실리는거지!
        }

        log.info(todoDTO);
        todoService.modify(todoDTO);
        redirectAttributes.addAttribute("tno", todoDTO.getTno());

        return "redirect:/todo/read"; //<- 난 read?tno=XXX로 가야하는거같은데;;
    }

    @GetMapping("/list")
    public void list(@Valid PageRequestDTO pageRequestDTO,
                     BindingResult bindingResult,
                     Model model) {

        log.info(pageRequestDTO);

        if(bindingResult.hasErrors()) {
            //에러가 있는경우 list 1페이지로 보내버리기
            pageRequestDTO = PageRequestDTO.builder().build();//기본값이 1페이지 10개출력
        }

        PageResponseDTO<TodoDTO> responseDTO = todoService.getList(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
        // <--여기도 마찬가지로 return으로 페이지명시안하면 요청과 같은경로의 페이지로 보내줌
    }
}
