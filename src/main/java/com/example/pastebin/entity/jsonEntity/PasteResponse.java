package com.example.pastebin.entity.jsonEntity;

import com.example.pastebin.entity.Paste;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PasteResponse {
    private String data;
    private Paste.AccessStatus status;
    private String exception;

}
