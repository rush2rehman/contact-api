package com.rahman.contactapi.controller;

import com.rahman.contactapi.domain.Contact;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

  Map<String,Contact> tempStorage = new HashMap();
  Random r = new Random();


  public String createContact(Contact newContact) {
    final String contactId = UUID.randomUUID().toString().replace("-", "");
    Contact contactToBeSaved = Contact.builder().firstName(newContact.getFirstName())
        .lastName(newContact.getLastName())
        .city(newContact.getCity())
        .country(newContact.getCountry())
        .emailId(newContact.getEmailId())
        .phoneNumber(newContact.getPhoneNumber())
        .build();
    tempStorage.put(contactId,contactToBeSaved);
    return contactId;
  }


  public Contact getContact(String contactId) {
    return tempStorage.get(contactId);
  }

  public void deleterAllContact() {
    tempStorage.clear();
  }

  public void updateContact(String contactId, Contact newContact) {
    Contact contactToBeSaved = Contact.builder().firstName(newContact.getFirstName())
        .lastName(newContact.getLastName())
        .city(newContact.getCity())
        .country(newContact.getCountry())
        .emailId(newContact.getEmailId())
        .phoneNumber(newContact.getPhoneNumber())
        .build();
    tempStorage.put(contactId,contactToBeSaved);
  }

  public void deleteContact(String contactId) {

    tempStorage.remove(contactId);
  }
}
