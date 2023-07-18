package com.example.pastebin.service;

import com.example.pastebin.entity.jsonEntity.PasteRequest;
import com.example.pastebin.entity.jsonEntity.PasteResponse;
import com.example.pastebin.entity.jsonEntity.UrlResponse;

import java.util.List;

public interface FileService {
    UrlResponse upload(PasteRequest paste);

    PasteResponse show(String hash);

    List<PasteResponse> showPasts();
}
