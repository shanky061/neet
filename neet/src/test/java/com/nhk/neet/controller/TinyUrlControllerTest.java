package com.nhk.neet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nhk.neet.model.URLPayload;
import com.nhk.neet.model.URLResource;
import com.nhk.neet.service.TinyUrlService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TinyUrlController.class)
public class TinyUrlControllerTest {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper mapper;

  @MockBean
  private TinyUrlService tinyUrlService;

  @Test
  public void createTinyUrl() throws Exception {
    given(tinyUrlService.createTinyUrl("s.co"))
        .willReturn(new URLResource("xyz"));

    mockMvc.perform(MockMvcRequestBuilders.post("/tu")
        .contentType(MediaType.APPLICATION_JSON)
        .content(mapper.writeValueAsBytes(new URLPayload("s.co"))))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.urlHash").value("xyz"));

    verify(tinyUrlService).createTinyUrl("s.co");
  }

  @Test
  public void expandTinyUrl() throws Exception {
    given(tinyUrlService.getUrlFromHash("xyz"))
        .willReturn("s.co");

    MvcResult response = mockMvc.perform(get("/tu/xyz"))
        .andExpect(status().isTemporaryRedirect())
        .andReturn();

    assertEquals("s.co", response.getResponse().getRedirectedUrl());
    verify(tinyUrlService).getUrlFromHash("xyz");
  }
}