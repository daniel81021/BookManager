package com.example.bookstore.author.controller;

import com.example.bookstore.author.model.Author;
import com.example.bookstore.author.mapper.AuthorVMMapper;
import com.example.bookstore.author.model.AuthorVM;
import com.example.bookstore.author.service.AuthorJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/author")
public class AuthorJpaController {

    @Autowired
    private AuthorJpaService authorJpaService;

    @PostMapping("/add")
    @ResponseBody
    private AuthorVM createAuthor(@RequestBody @Valid AuthorVM authorVM){
        Author author = authorJpaService.saveAuthor(AuthorVMMapper.MAPPER.toAuthor(authorVM));
        return AuthorVMMapper.MAPPER.toAuthorVM(author);
    }

    @PutMapping("/update")
    @ResponseBody
    private AuthorVM updateAuthor(@RequestBody @Valid AuthorVM authorVM){
        Author author = authorJpaService.updateAuthor(AuthorVMMapper.MAPPER.toAuthor(authorVM));
        return AuthorVMMapper.MAPPER.toAuthorVM(author);
    }
}
