package com.example.bookstore.publisher.controller;

import com.example.bookstore.publisher.model.Publisher;
import com.example.bookstore.publisher.mapper.PublisherVMMapper;
import com.example.bookstore.publisher.model.PublisherVM;
import com.example.bookstore.publisher.service.PublisherJpaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/publisher")
public class PublisherJpaController {

    @Autowired
    private PublisherJpaService publisherJpaService;

    @PostMapping("/add")
    @ResponseBody
    private PublisherVM createPublisher(@RequestBody @Valid PublisherVM publisherVM){
        Publisher publisher = publisherJpaService.save(PublisherVMMapper.MAPPER.toPublisher(publisherVM));
        return PublisherVMMapper.MAPPER.toPublisherVM(publisher);
    }

    @PutMapping("/update")
    @ResponseBody
    private PublisherVM updatePublisher(@RequestBody @Valid PublisherVM publisherVM){
        Publisher publisher = publisherJpaService.update(PublisherVMMapper.MAPPER.toPublisher(publisherVM));
        return PublisherVMMapper.MAPPER.toPublisherVM(publisher);
    }
}
