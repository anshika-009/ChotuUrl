package com.chotuURL.url.service;

import com.chotuURL.url.repository.ShortUrlRepository;
import com.chotuURL.url.util.Base62;
import org.springframework.stereotype.Service;

@Service
public class ShortCodeService {
    private static final int defaultLength=7;
    private final ShortUrlRepository shortUrlRepository;

    public ShortCodeService(ShortUrlRepository shortUrlRepository){
        this.shortUrlRepository=shortUrlRepository;
    }

    //if the user opts to generate a random code
    public String generateCode() {
        for (int i = 0; i < 10; i++) {
            String candidate = Base62.randomCode(defaultLength);
            if (!shortUrlRepository.existsByCode(candidate)) {
                return candidate;
            }
        }
        throw new IllegalStateException("Failed to generate a unique short code. Please try again later.");
    }

    //if a user goes for custom name aliasing
    public String validateCustomAlias(String alias){
        if(alias==null || alias.isBlank()){return null;}
        if(!alias.matches("^[A-Za-z0-9_]{3,32}$")){
            throw new IllegalStateException("Alias must be 3–32 characters long and contain only letters, digits, or underscores");
        }
        if(shortUrlRepository.existsByCode(alias)){
            throw new IllegalArgumentException("Alias already exists in the database.");
        }
        return alias;
    }
}
