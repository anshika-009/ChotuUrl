package com.chotuURL.url.controller;

import com.chotuURL.url.service.UrlService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedirectController {
    private final UrlService urlService;
    public RedirectController(UrlService urlService){
        this.urlService=urlService;
    }

    //this will return an HTTP 302 redirect-moves temporarily
    //good for registering the number of hits
    @GetMapping("/{code}")
    public ResponseEntity<Object> redirect(@PathVariable String code){
        return urlService.lookupActive(code)
                .map(url->{
                    urlService.registerHit(url);
                    return ResponseEntity.status(302)
                            .header(HttpHeaders.LOCATION, url.getLongUrl())
                            .build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
