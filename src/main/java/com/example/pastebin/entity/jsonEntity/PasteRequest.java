package com.example.pastebin.entity.jsonEntity;

import com.example.pastebin.entity.Paste;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PasteRequest {

    private String data;
    @Builder.Default
    @JsonProperty("status")
    private Paste.AccessStatus accessStatus = Paste.AccessStatus.PUBLIC;
    @Builder.Default
    @JsonProperty("lifetime")
    private Long expirationTimeMinutes = 1440L;


}
