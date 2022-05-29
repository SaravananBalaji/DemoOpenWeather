package com.sg.weather.demo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static com.sg.weather.demo.constant.AppConstants.API_GET_API_KEY;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@TestPropertySource(
//		locations = "classpath:application-integrationtest.properties")
class DemoApplicationTests {

	@Autowired
	public MockMvc mvc;
//	public WebApplicationContext wac;


//	@BeforeEach
//	public void setUpMockMvc() {
//		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
//	}

	@Test
	void testGetWeather() {
//		mvc.perform(
//				MockMvcRequestBuilders.get(API_GET_API_KEY)
//						.accept(MediaType.APPLICATION_JSON)
//						.c
//		)
	}

}
