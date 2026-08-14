package com.chotuURL.url.entity;

import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name="url_mapping", indexes={@Index(name="idx_code_unique", columnList="shortUrl", unique=true)})
public class ShortUrl {
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public void setExpiresAt(Instant expiresAt) {
        this.expiresAt = expiresAt;
    }

    public long getHits() {
        return hits;
    }

    public void setHits(long hits) {
        this.hits = hits;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long userId;

    @Column(name="short_url", nullable=false, unique=true, length=32)
    private String code;

    @Column(name="long_url", nullable=false, unique=true)
    private String longUrl;

    private Instant expiresAt;

    private long hits=0;

    @Column(nullable = false, updatable = false)
    private final Instant createdAt=Instant.now();

    public ShortUrl(){}
    public ShortUrl(String code, String longUrl, Instant expiresAt){
        this.code= code;
        this.longUrl=longUrl;
        this.expiresAt=expiresAt;
    }

}
