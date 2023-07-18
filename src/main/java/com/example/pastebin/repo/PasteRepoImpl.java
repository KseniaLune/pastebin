package com.example.pastebin.repo;

import com.example.pastebin.entity.Paste;
import com.example.pastebin.exception.NotFoundEntityException;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class PasteRepoImpl implements PasteRepo {

    //Для хранения можно подключить minIO, или схранять локально
    //Для простоты хранилищем будет мапа, где ключ = хэш, значение = Paste
    private final Map<String, Paste> storage = new HashMap<>();

    @Override
    public Paste getByHash(String hash) {
        return storage.get(hash);
    }

    @Override
    public List<Paste> showLatestPublicPaste(int amount) {
        LocalDateTime now = LocalDateTime.now();

        return storage.values().stream()
            .filter(paste -> paste.getAccessStatus() == Paste.AccessStatus.PUBLIC)
            .filter(paste -> paste.getLifetime().isAfter(now))
            .sorted(Comparator.comparing(Paste::getId).reversed())
            .limit(amount)
            .collect(Collectors.toList());
    }

    @Override
    public void save(Paste newPaste) {
        storage.put(newPaste.getHash(), newPaste);
    }
}
