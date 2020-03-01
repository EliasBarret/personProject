package br.com.elias.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.elias.entity.Person;



@RunWith(SpringRunner.class)
@WebMvcTest(value = PersonController.class)
public class PersonControllerTest {

   @Autowired
   private MockMvc mockMvc;
   
   @MockBean
   private PersonController personController;
   
   
   @Test
   public void insertPerson() throws Exception{
	  
	   Person person = new Person();
       Date date = new Date();
       
       person.setBirth(date);
       person.setEmail("email@teste.com");
       person.setGender("Masculino");
       person.setName("teste");
       person.setNationality("Brasileiro");
       person.setNaturalness("Olinda");
       
       Mockito.when(personController.post(Mockito.any(Person.class))).thenReturn(person);
       
       mockMvc.perform(MockMvcRequestBuilders
               .post("/person")
               .content(asJsonString(person))
               .contentType(MediaType. APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isCreated());
   }
   
   @Test
   public void alterPerson() throws Exception{
	   Person person = new Person();
       Date date = new Date();
       
       person.setBirth(date);
       person.setEmail("email@teste.com");
       person.setGender("Masculino");
       person.setName("teste");
       person.setNationality("Brasileiro");
       person.setNaturalness("Olinda");
       
       Mockito.when(personController.Put(Mockito.anyLong(), Mockito.any(Person.class))).thenReturn(null);
       
       mockMvc.perform(MockMvcRequestBuilders
               .put("/person/{id}", 1)
               .content(asJsonString(person))
               .contentType(MediaType.APPLICATION_JSON)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
   }
   
   @Test
   public void deleteById() throws Exception{
       mockMvc.perform(MockMvcRequestBuilders
              .delete("/person/{id}",1))
              .andExpect(status().isOk());
   }

   @Test
   public void findById() throws  Exception{
       mockMvc.perform(MockMvcRequestBuilders
               .get("/person/{id}",1)
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
   }

   @Test
   public void listPersons() throws Exception{
       mockMvc.perform(MockMvcRequestBuilders
               .get("/")
               .accept(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk());
   }
   
   private String asJsonString(final Object obj) {
       try {
           return new ObjectMapper().writeValueAsString(obj);
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
   }
}
