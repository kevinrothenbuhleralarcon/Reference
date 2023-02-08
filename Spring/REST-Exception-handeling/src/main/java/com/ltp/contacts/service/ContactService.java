package com.ltp.contacts.service;

import com.ltp.contacts.exception.NoContactException;
import com.ltp.contacts.pojo.Contact;

import java.util.List;

public interface ContactService {

    List<Contact> getContacts();
    Contact getContact(String id);
    void saveContact(Contact contact);
    void updateContact(String id, Contact contact);
    void deleteContact(String id)  throws NoContactException;
    
}
