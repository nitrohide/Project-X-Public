//package com.cognixia.jump;
//
//import org.junit.*;
//import org.junit.runner.*;
//import org.springframework.beans.factory.annotation.*;
//import org.springframework.boot.test.autoconfigure.web.servlet.*;
//import org.springframework.boot.test.mock.mockito.*;
//import org.springframework.http.MediaType;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import com.cognixia.jump.controller.CustomerController;
//import com.cognixia.jump.model.Customer;
//import com.cognixia.jump.repository.CustomerRepository;
//
//import static org.assertj.core.api.Assertions.*;
//import static org.mockito.BDDMockito.*;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringRunner.class)
//@WebMvcTest(CustomerController.class)
//public class CustomerControllerTest {
//
//    @Autowired
//    private MockMvc mvc;
//
//    @MockBean
//    private CustomerRepository customerRepository;
//
//    @Test
//    public void testExample() throws Exception {
//        given(this.customerRepository.findById((long)1))
//                .willReturn();
//        this.mvc.perform(get("/customer/1").accept(MediaType.TEXT_PLAIN))
//                .andExpect(status().isOk());
//    }
//
//}