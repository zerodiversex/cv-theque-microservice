package com.elasticsearch.cvssearch.controller;

import com.elasticsearch.cvssearch.model.Cv;
import com.elasticsearch.cvssearch.repository.CVsRepository;
import com.elasticsearch.cvssearch.services.CVService;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping("/api/v1/cvs")
public class CVController {

    private final CVsRepository repository;
    private final CVService cvService;


    @Autowired
    public CVController(CVsRepository repository, CVService cvService) {
        this.repository = repository;
        this.cvService = cvService;
    }

    @PostMapping(path = {"", "/"})
    public String saveCV(@RequestParam("file") MultipartFile file) throws IOException, InvalidFormatException {
        Cv cv = new Cv(file.getOriginalFilename(), cvService.getContentFromFileByType(file));
        repository.save(cv);
        return cv.getId();
    }


    @GetMapping(path = {"", "/"})
    public Iterable<Cv> findByCv(@RequestParam(value = "keyword", required = false) String keyword) {
        if (keyword == null) {
            return repository.findAll();
        }
        return repository.findByCv(keyword);
    }


}
