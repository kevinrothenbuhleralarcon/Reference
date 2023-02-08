package com.ltp.contacts.web;

import com.ltp.contacts.exception.NoContactException;
import com.ltp.contacts.pojo.Contact;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import com.ltp.contacts.service.ContactService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@RestController
@AllArgsConstructor
public class ContactController {

    private final ContactService contactService;

    @GetMapping("/contact")
    public ResponseEntity<List<Contact>> getContacts() {
        return new ResponseEntity<>(contactService.getContacts(), HttpStatus.OK);
    }

    @GetMapping(value = "/contact/{id}")
    public ResponseEntity<Contact> getContact(@PathVariable String id) {
        return new ResponseEntity<>(contactService.getContact(id), HttpStatus.OK);
    }

    @PostMapping(value = "/contact")
    public ResponseEntity<HttpStatus> createContact(@RequestBody @Valid Contact contact) {
        contactService.saveContact(contact);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/contact/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable String id, @RequestBody @Valid Contact contact) {
        contactService.updateContact(id, contact);
        return new ResponseEntity<>(contactService.getContact(id), HttpStatus.OK);
    }

    @DeleteMapping("/contact/{id}")
    public ResponseEntity<HttpStatus> deleteContact(@PathVariable String id) {
        try {
            contactService.deleteContact(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NoContactException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
