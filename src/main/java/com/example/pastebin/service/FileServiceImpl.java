package com.example.pastebin.service;

import com.example.pastebin.entity.Paste;
import com.example.pastebin.entity.jsonEntity.PasteRequest;
import com.example.pastebin.entity.jsonEntity.PasteResponse;
import com.example.pastebin.entity.jsonEntity.UrlResponse;
import com.example.pastebin.exception.LifeTimeException;
import com.example.pastebin.exception.NoDataAvailableEx;
import com.example.pastebin.exception.NotFoundEntityException;
import com.example.pastebin.repo.PasteRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {
    @Value("${app.host}")
    private String host;
    @Value("${app.public_list_size}")
    private int publicListSize;

    private AtomicLong idGenerator = new AtomicLong(0);

    private final PasteRepo pasteRepo;


    @Override
    public UrlResponse upload(PasteRequest paste) {
        if (paste.getData() == null){
            return new UrlResponse("", new NoDataAvailableEx("Please add data").getMessage());
        }

        long id = generateId();
        String hash = generateHash();

        LocalDateTime lifetime = LocalDateTime.now().plusMinutes(paste.getExpirationTimeMinutes());

        Paste newPaste = Paste.builder()
            .id(id)
            .data(paste.getData())
            .accessStatus(paste.getAccessStatus())
            .lifetime(lifetime)
            .hash(hash)
            .build();
        pasteRepo.save(newPaste);

        return new UrlResponse(host + "/" + newPaste.getHash(), null);
    }

    @Override
    public PasteResponse show(String hash) {
        Paste paste = pasteRepo.getByHash(hash);
        if(paste==null){
            return new PasteResponse(null, null, new NotFoundEntityException("Paste not found").getMessage());
        }

        LocalDateTime now = LocalDateTime.now();
        if (paste.getLifetime().isBefore(now)) {
            return new PasteResponse(null, null, new LifeTimeException("Time has expired").getMessage());
        }
        return PasteResponse.builder()
            .data(paste.getData())
            .status(paste.getAccessStatus())
            .exception(null)
            .build();
    }

    @Override
    public List<PasteResponse> showPasts() {
        List<Paste> pastes = pasteRepo.showLatestPublicPaste(publicListSize);

        return pastes.stream().map(paste -> PasteResponse.builder()
                .data(paste.getData())
                .status(paste.getAccessStatus())
                .build())
            .collect(Collectors.toList());
    }

    private long generateId() {
        return idGenerator.incrementAndGet();
    }

    private String generateHash() {
        UUID hash = UUID.randomUUID();
        return "" + hash;
    }
}
