package com.example.project.trainer;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class TrainerController {
    private final TrainerService service;

    @GetMapping("/trainerlist")
    public String trainerpage(Model model , @RequestParam(value = "trainerName" ,required = false) String trainerName,
                              @RequestParam(defaultValue = "0") int page,
                              @RequestParam(defaultValue = "7") int size) {
        Page<TrainerEntity> trainerEntityList;
        if(trainerName==null){
            trainerEntityList =service.getTrainers(page,size);
        }else{
            trainerEntityList =service.getSearchTrainer(trainerName,page,size);
        }
        //페이징처리 - 다음 ,이전
        Page<TrainerEntity> trainerPage = service.getTrainers(page, size);
        model.addAttribute("trainerName",trainerName);
        model.addAttribute("trainerPage", trainerPage);
        model.addAttribute("totalPage",trainerPage.getTotalPages());
        model.addAttribute("trainerlist",trainerEntityList);
        return "trainer/trainerhome";
    }
    @GetMapping("/trainerread")
    public String trainerread(@RequestParam("boardNo") Long boardNo, @RequestParam("action") String action, Model model) {
        TrainerEntity read = service.gettrainerInfo(boardNo);
        model.addAttribute("trainer", read);
        String view = "";
        if (action.equals("READ")) {
            view = "trainer/trainer_read";
        } else {
            view = "trainer/trainer_update";
        }
        return "trainer/trainer_read";
    }

    @GetMapping("/trainerregister")
    public String registerPage() {
        return "trainer/trainer_register";
    }

    @PostMapping("/trainerregister")
    public String register(TrainerRequestDTO trainer, HttpSession session)
            throws IllegalStateException, IOException {
        System.out.println(trainer);
        service.insert(trainer);
        return "redirect:/trainerlist?category=all";
    }
    @GetMapping("/trainerdelete")
    public String delete(String boardNo){
        service.delete(Long.parseLong(boardNo));
        return "redirect:/trainerlist?category=all";
    }
    @PostMapping("/update")
    public String update( TrainerRequestDTO trainer) {
        return "redirect:/board/list?category=all";
    }

}
