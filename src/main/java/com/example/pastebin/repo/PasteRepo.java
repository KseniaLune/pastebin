package com.example.pastebin.repo;

import com.example.pastebin.entity.Paste;

import java.util.List;


public interface PasteRepo {
    Paste getByHash(String hash);

    List<Paste> showLatestPublicPaste(int amount);

    void save(Paste newPaste);
}
