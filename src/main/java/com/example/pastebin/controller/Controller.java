package com.example.pastebin.controller;

import com.example.pastebin.entity.jsonEntity.PasteRequest;
import com.example.pastebin.entity.jsonEntity.PasteResponse;
import com.example.pastebin.entity.jsonEntity.UrlResponse;
import com.example.pastebin.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class Controller {

    private final FileService fileService;

    @PostMapping("/upload")
    public ResponseEntity<UrlResponse> uploadPaste(@RequestBody PasteRequest request) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(fileService.upload(request));
    }

    @GetMapping("/show/{hash}")
    public ResponseEntity<PasteResponse> showPaste(@PathVariable("hash") String hash) {
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(fileService.show(hash));
    }

    @GetMapping("/")
    public ResponseEntity<List<PasteResponse>> showPublicPastsLatest() {
        return ResponseEntity
            .status(HttpStatus.OK)
            .contentType(MediaType.APPLICATION_JSON)
            .body(fileService.showPasts());
    }
}
