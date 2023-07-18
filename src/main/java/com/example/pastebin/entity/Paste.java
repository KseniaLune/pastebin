package com.example.pastebin.entity;


import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Paste {
    private Long id;
    private String data;
    private AccessStatus accessStatus;
    private LocalDateTime lifetime;
    private String hash;

    public enum AccessStatus {
        PUBLIC, UNLISTED
    }
}
