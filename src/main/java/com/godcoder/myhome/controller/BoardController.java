package com.godcoder.myhome.controller;

import com.godcoder.myhome.model.Board;
import com.godcoder.myhome.repository.BoardRepository;
import com.godcoder.myhome.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    //게시판 목록
    @GetMapping("/list")
    public String list(Model model) {
        List<Board> boards = boardRepository.findAll(); //전체 조회
        model.addAttribute("boards", boards); //모델에 저장
        return "board/list";
    }

    //글쓰기 폼
    @GetMapping("/form")
    public String form(Model model, @RequestParam(required = false) Long id) {
        if (id == null) {
            model.addAttribute("board", new Board());
        } else {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        }
        return "board/form";
    }

    //글쓰기 저장
    @PostMapping("/form")
    //public String formSubmit(@ModelAttribute Board board) {
    public String formSubmit(@Valid Board board, BindingResult bindingResult) {
        boardValidator.validate(board, bindingResult);

        if (bindingResult.hasErrors()) {
            return "board/form";
        }
        boardRepository.save(board);
        return "redirect:/board/list";
    }

}
