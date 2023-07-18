package com.example.pastebin.entity.jsonEntity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UrlResponse {
    private String url;
    private String exception;
}
