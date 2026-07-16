package net.likelion.bebc25.board03.post.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import net.likelion.bebc25.board03.post.dto.PostDto;
import net.likelion.bebc25.board03.post.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/03/board")
public class Board03Controller {

    private final PostService postService;
    public Board03Controller(PostService postService){
        this.postService = postService;
    }


    // 게시글 목록 조회하는 컨트롤러
    @GetMapping("/list.html")
    public String getBoardList(Model model){
        // 게시글 목록 조회(데이터)
        List<PostDto> posts = postService.getPosts();

        model.addAttribute("posts", posts);
        return "board/list";
    }

    // 게시글 상세 조회하는 컨트롤러
    @GetMapping("/detail.html")
    public String getDetail(@RequestParam("id") int id, Model model){
        PostDto post = postService.getPost(id);

        model.addAttribute("post", post);
        return "board/detail"; // 템플릿 파일 경로
    }


    // 게시글 등록 화면을 요청하는 컨트롤러
    @GetMapping("/write.html")
    public String getWriteForm(@ModelAttribute("postForm") PostDto post){ // 이름을 따로 지정해주지 않으면 이름이 자동으로 카멜클래스로 설정
//        model.addAttribute("postForm", new PostDto());
        return "board/write";
    }

    // 게시글 수정 화면을 요청하는 컨트롤러
    @GetMapping("/edit.html")
    public String getEditForm(@RequestParam("id") int id, Model model){
        PostDto post = postService.getPost(id);

        model.addAttribute("postForm", post);
        return "board/write";
    }

    // 게시글 등록 요청을 처리하는 컨트롤러
    @PostMapping("/write")
    public String writePost(@Valid @ModelAttribute("postForm") PostDto post, // Validation 검증 대상 객체
                            BindingResult bindingResult){ // Validation 검증 결과가 저장된 객체(대상 객체 뒤에 기술)

        log.debug(post.toString());

        if(bindingResult.hasErrors()){ // 검증에 실패하면
            return "board/write"; // 작성 중이던 페이지로 보냄
        }

        postService.writePost(post);

        return "redirect:list.html"; // 브라우저에 list.html로 재요청하라고 응답
    }


    // 게시글 수정 요청을 처리하는 컨트롤러
    @PostMapping("/edit")
    public String editPost(@Valid @ModelAttribute("postForm") PostDto post,
                           BindingResult bindingResult){
        log.debug(post.toString());

        if(bindingResult.hasErrors()){
            return "board/write";
        }

        postService.editPost(post);
        return "redirect:detail.html?id=" + post.getId();
    }


    // 게시글 삭제 요청을 처리하는 컨트롤러
    @PostMapping("/delete")
    public String deletePost(@RequestParam int id){
        postService.removePost(id);
        return "redirect:list.html";
    }

}