package com.chotuURL.url.controller;

import com.chotuURL.url.dto.CreateShortUrlRequest;
import com.chotuURL.url.entity.ShortUrl;
import com.chotuURL.url.service.UrlService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/urls")
public class UrlController {
    private final UrlService urlService;

    public UrlController(UrlService urlService){
        this.urlService=urlService;
    }

    @PostMapping
    public ResponseEntity<ShortUrl> create (@Valid @RequestBody CreateShortUrlRequest request){
        ShortUrl result= urlService.create(request);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{code}")
    public ResponseEntity<ShortUrl> get(@PathVariable String code){
        return urlService.lookupActive(code)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
