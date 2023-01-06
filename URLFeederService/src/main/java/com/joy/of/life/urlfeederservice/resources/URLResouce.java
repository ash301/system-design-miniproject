package com.joy.of.life.urlfeederservice.resources;

import com.joy.of.life.urlfeederservice.common.Constants;
import com.joy.of.life.urlfeederservice.model.URL;
import com.joy.of.life.urlfeederservice.service.URLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.UUID;

@RestController
public class URLResouce {

    @Autowired
    private URLService urlService;

    @GetMapping("/ping")
    public String ping() {
        return "pong";
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> submitURL(@RequestBody URL url) {
        url.setId(Constants.URL_UUID_PREFIX + UUID.randomUUID().toString());
        url.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        System.out.println(url);
        urlService.save(url);
        return ResponseEntity.ok().build();
    }
}

