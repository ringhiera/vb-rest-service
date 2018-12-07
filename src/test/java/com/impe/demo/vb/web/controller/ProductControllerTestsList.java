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
public class ProductControllerTestsList {

	@Autowired
	private MockMvc mockMvc;


	@Test
	public void listProducts_Empty() throws Exception {

		this.mockMvc.perform(get("/api/v0.0/products")).andDo(print()).andExpect(status().isOk());
//                .andExpect(jsonPath("$.content").value("Hello, World!"));
	}

	@Test
	public void listProducts_Populated() throws Exception {

		String requestJson = "{  \"currency\": \"GBP\",  \"name\": \"John\",  \"price\": 123}";
		this.mockMvc.perform(post("/api/v0.0/products").contentType(MediaType.APPLICATION_JSON).content(requestJson))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.name").value("John"))
				.andExpect(jsonPath("$.price").value("123"))
				.andExpect(jsonPath("$.currency").value("GBP"));
		this.mockMvc.perform(get("/api/v0.0/products")).andDo(print()).andExpect(status().isOk());
	}


	
	
//  // POSITIVE
//  // insert product
//  // list product
//  // supersed product

}

//
//
///*
// * Copyright 2016 the original author or authors.
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *      http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.impe.demo.vb.web.controller;
//
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import java.nio.charset.Charset;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//@RunWith(SpringRunner.class)
//@DataJpaTest
//@SpringBootTest
//@AutoConfigureMockMvc
//public class ProductControllerTests {
//
//	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    public void noParamGreetingShouldReturnDefaultMessage() throws Exception {
//        this.mockMvc
////				.perform(get("/api/v0.0/products").contentType(MediaType.APPLICATION_JSON))
//		.perform(get("/products").contentType(MediaType.APPLICATION_JSON))
//        		.andDo(print())
//        		.andExpect(status().isOk());
////                .andExpect(jsonPath("$.content").value("Hello, World!"));
//    }
//
////    @Test
////    public void insertProduct() throws Exception {
////    	
////    	String requestJson = "{  \"currency\": \"GBP\",  \"name\": \"string\",  \"price\": 123}";
////        this.mockMvc
////        		.perform(post("/api/v0.0/products")
////        				.contentType(APPLICATION_JSON_UTF8)
////        		        .content(requestJson)
////        				)
////        		.andDo(print())
////        		.andExpect(status().isOk());
//////                .andExpect(jsonPath("$.content").value("Hello, World!"));
////    }
//
//    
//    // POSITIVE
//    // insert product
//    // list product
//    // supersed product
//    
//    //NEGATIVE
//    // insert product fail currency 
//    // insert product fail price
//    // supersed product fail non existent
//    
//    
//    
////    @Test
////    public void paramGreetingShouldReturnTailoredMessage() throws Exception {
////
////        this.mockMvc.perform(get("/greeting").param("name", "Spring Community"))
////                .andDo(print()).andExpect(status().isOk())
////                .andExpect(jsonPath("$.content").value("Hello, Spring Community!"));
////    }
//
//}
