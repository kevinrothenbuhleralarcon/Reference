package com.ltp.contacts;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ltp.contacts.pojo.Contact;
import com.ltp.contacts.repository.ContactRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContactsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Contact[] contacts = new Contact[]{
            new Contact("1", "Jon Snow", "6135342524"),
            new Contact("2", "Tyrion Lannister", "4145433332"),
            new Contact("3", "The Hound", "3452125631"),
    };

    @BeforeEach
    void setup() {
        for (Contact contact : contacts) {
            contactRepository.saveContact(contact);
        }
    }

    @AfterEach
    void clear() {
        contactRepository.getContacts().clear();
    }


    @Test
    public void getContactByIdTest() throws Exception {
        /*
         * GIVEN
         */
        RequestBuilder request = MockMvcRequestBuilders.get("/contact/1");

        /*
         * WHEN-THEN
         */
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.name").value(contacts[0].getName()))
                .andExpect(jsonPath("$.phoneNumber").value(contacts[0].getPhoneNumber()));
    }

    @Test
    public void getAllContactsTest() throws Exception {
        /*
         * GIVEN
         */
        RequestBuilder request = MockMvcRequestBuilders.get("/contact");

        /*
         * WHEN-THEN
         */
        mockMvc.perform(request)
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().contentType(APPLICATION_JSON))
                .andExpect(jsonPath("$.size()").value(contacts.length))
                .andExpect(jsonPath("$.[?(@.id == \"" + contacts[1].getId() + "\" && @.name == \"" + contacts[1].getName() + "\" && @.phoneNumber == \"" + contacts[1].getPhoneNumber() + "\")]").exists());
    }

    @Test
    public void contactNotFoundTest() throws Exception {
        /*
         * GIVEN
         */
        RequestBuilder request = MockMvcRequestBuilders
                .get("/contact/4");

        /*
         * WHEN-THEN
         */
        mockMvc.perform(request)
                .andExpect(status().is(404));
    }

    @Test
    public void validContactCreation() throws Exception {
        /*
         * GIVEN
         */
        RequestBuilder request = MockMvcRequestBuilders
                .post("/contact")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Contact(
                        null,
                        "Rayan",
                        "5334221234"
                )));
        /*
         * WHEN-THEN
         */
        mockMvc.perform(request)
                .andExpect(status().is(201));
    }

    @Test
    public void invalidContactCreation() throws Exception {
        /*
         * GIVEN
         */
        RequestBuilder request = MockMvcRequestBuilders
                .post("/contact")
                .contentType(APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(new Contact(
                        null,
                        "      ",
                        "      "
                )));
        /*
         * WHEN-THEN
         */
        mockMvc.perform(request)
                .andExpect(status().is(400));
    }
}