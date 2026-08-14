package com.chotuURL.url.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateShortUrlRequest {
    @NotBlank(message="URL is required")
    @Size(max =2048, message ="URL is too long")
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Integer getExpiryDays() {
        return expiryDays;
    }

    public void setExpiryDays(Integer expiryDays) {
        this.expiryDays = expiryDays;
    }

    @Size(min=3, max=32, message="Alias must be between 3 to 32 characters.")
    private String alias;

    @Min(value = 1, message = "Expiry must be at least 1 day")
    @Max(value = 3650, message = "Expiry cannot exceed 10 years")
    private Integer expiryDays;

}
