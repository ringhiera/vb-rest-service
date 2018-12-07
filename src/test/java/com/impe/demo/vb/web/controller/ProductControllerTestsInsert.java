/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.impe.demo.vb.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ProductControllerTestsInsert {

	@Autowired
	private MockMvc mockMvc;


	@Test
	public void insertProduct() throws Exception {

		String requestJson = "{  \"currency\": \"GBP\",  \"name\": \"John\",  \"price\": 123.45}";
		this.mockMvc.perform(post("/api/v0.0/products").contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("John"))
				.andExpect(jsonPath("$.price").value("123.45"))
				.andExpect(jsonPath("$.currency").value("GBP"));
	}

	@Test
	public void insertProduct_BadCurrency() throws Exception {

		String requestJson = "{  \"currency\": \"FOO\",  \"name\": \"John\",  \"price\": 123}";
		this.mockMvc.perform(post("/api/v0.0/products").contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	public void insertProduct_BadPrice() throws Exception {

		String requestJson = "{  \"currency\": \"GBP\",  \"name\": \"John\",  \"price\": 123.9999}";
		this.mockMvc.perform(post("/api/v0.0/products").contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isBadRequest());
	}


}
