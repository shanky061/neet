package com.nhk.neet.service;

import com.nhk.neet.helper.HashingHelper;
import com.nhk.neet.model.TinyUrl;
import com.nhk.neet.model.URLResource;
import com.nhk.neet.repository.TinyUrlRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Service @Validated
public class TinyUrlService {

    private HashingHelper hashingHelper;
    private TinyUrlRepository tinyUrlRepository;

    public TinyUrlService(HashingHelper hashingHelper, TinyUrlRepository tinyUrlRepository) {
        Assert.notNull(hashingHelper, "Required dependency \"HashingHelper\" not found");
        Assert.notNull(tinyUrlRepository, "Required dependency \"TinyUrlRepository\" not found");

        this.hashingHelper = hashingHelper;
        this.tinyUrlRepository = tinyUrlRepository;
    }

    /**
     * Persist and get hash of given Url resource
     * @param urlResource url resource to persist
     * @return tiny url resource
     */
    public URLResource createTinyUrl(@NotBlank final String urlResource) {
        String hash = hashingHelper.getNextHash();
        TinyUrl tinyUrl = new TinyUrl(hash, urlResource);
        tinyUrlRepository.save(tinyUrl);

        return new URLResource(tinyUrl.getHash());
    }

    /**
     * Convert resource hash to original Url
     * @param hash resource hash
     * @return Url string
     */
    public String getUrlFromHash(@NotNull final String hash) {
        TinyUrl tinyUrl = tinyUrlRepository.findByHash(hash);
        return tinyUrl.getResource();
    }
}
