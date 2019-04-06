package com.nhk.neet.controller;

import com.google.common.base.Preconditions;
import com.nhk.neet.model.URLPayload;
import com.nhk.neet.model.URLResource;
import com.nhk.neet.service.TinyUrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("tu")
public class TinyUrlController {
    private final TinyUrlService tinyUrlService;

    public TinyUrlController(@NotNull TinyUrlService tinyUrlService) {
        this.tinyUrlService =
                Preconditions.checkNotNull(tinyUrlService, "Required dependency \"%s\" not found");
    }

    @PostMapping
    public URLResource createTinyUrl(@RequestBody URLPayload payload) {
        return tinyUrlService.createTinyUrl(payload.getPayload());
    }

    @GetMapping("{hash}")
    public ResponseEntity expandTinyUrl(@PathVariable("hash") String hash) {
        String urlResource = tinyUrlService.getUrlFromHash(hash);
        return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT)
                .location(URI.create(urlResource))
                .build();
    }
}
