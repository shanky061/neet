package com.nhk.neet.service;

import com.nhk.neet.helper.HashingHelper;
import com.nhk.neet.model.TinyUrl;
import com.nhk.neet.model.URLResource;
import com.nhk.neet.repository.TinyUrlRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class TinyUrlServiceTest {

  @Mock
  private TinyUrlRepository tinyUrlRepository;
  @Mock
  private HashingHelper hashingHelper;

  @Captor
  ArgumentCaptor<TinyUrl> urlArgumentCaptor;

  @InjectMocks
  private TinyUrlService tinyUrlService;

  @Test
  public void createTinyUrl() {
    given(tinyUrlRepository.save(any(TinyUrl.class)))
        .willReturn(new TinyUrl("xyz", "s.co"));
    given(hashingHelper.getNextHash())
        .willReturn("xyz");

    URLResource resource = tinyUrlService.createTinyUrl("s.co");

    verify(hashingHelper).getNextHash();
    verify(tinyUrlRepository).save(urlArgumentCaptor.capture());

    TinyUrl savedEntity = urlArgumentCaptor.getValue();

    assertThat(resource.getUrlHash(), is("xyz"));
    assertThat(savedEntity, is(notNullValue()));
    assertThat(savedEntity, samePropertyValuesAs(new TinyUrl("xyz", "s.co")));
  }

  @Test
  public void getUrlFromHash() {
    given(tinyUrlRepository.findByHash("xyz"))
        .willReturn(new TinyUrl("xyz", "s.co"));

    String url = tinyUrlService.getUrlFromHash("xyz");

    verify(tinyUrlRepository).findByHash("xyz");
    assertThat(url, is("s.co"));
  }
}