package com.elasticsearch.cvssearch.controller;

import com.elasticsearch.cvssearch.model.Cvs;
import com.elasticsearch.cvssearch.repository.CVsRepository;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;


@RestController
@RequestMapping("/api/cvs")
public class CVsController {

    public final static String typePDF = "application/pdf";
    public final static String typeWord = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";

    @Autowired
    CVsRepository repository;


    @PostMapping
    public String saveCV(@RequestParam("file") MultipartFile file ) throws IOException, InvalidFormatException {
        String content = null;
        System.out.println(file.getContentType());
        String type = file.getContentType();
        if(type == typePDF){

        }else {
            try {
                /*InputStream inputStream =  new BufferedInputStream(file.getInputStream());
                byte[] bytes = file.getBytes();
                inputStream.read(bytes);
                encodedfile = new String(Base64.getEncoder().encodeToString(bytes));*/
                ByteArrayInputStream stream = new ByteArrayInputStream(file.getBytes());
                content = IOUtils.toString(stream, StandardCharsets.UTF_8);
                //System.out.println(content);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Cvs cv = new Cvs(file.getOriginalFilename(), content);
            repository.save(cv);
        }
        return "ADDED";
    }

    @GetMapping
    public Iterable<Cvs> getCVByFullName(){
        return repository.findAll();
    }

    @GetMapping("/findByCv/{langage}")
    public List<Cvs> findByCv(@PathVariable String langage){
        return repository.findByCv(langage);
    }





}
