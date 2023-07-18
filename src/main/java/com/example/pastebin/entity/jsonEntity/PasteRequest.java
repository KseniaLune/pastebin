package com.example.pastebin.entity.jsonEntity;

import com.example.pastebin.entity.Paste;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

@Data
public class PasteRequest {

    private String data;
    private Paste.AccessStatus accessStatus = Paste.AccessStatus.PUBLIC;
    private Long expirationTimeMinutes = 1440L;
}
