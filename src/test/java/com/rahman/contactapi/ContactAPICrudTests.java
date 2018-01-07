package com.rahman.contactapi;


import static org.junit.Assert.*;

import com.rahman.contactapi.controller.ContactController;
import com.rahman.contactapi.domain.Contact;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ContactAPICrudTests {

  @Autowired
  ContactController contactController;

  @Test
  public void create_contact_and_retrieve()
  {

    Contact newContact = createNewContact("Muhammathu Aadil",
        "Kalilur Rahmna", "Blr","IN",
        "rush2rehman@gmail.com", "123");
    String contactId = contactController.createContact(newContact);
    assertNotEquals(null, contactId);
    assertNotEquals("",contactId);
    Contact retrivedContact = contactController.getContact(contactId);
    assertEquals(newContact, retrivedContact);

    clean_up();

  }

  private void clean_up() {
    contactController.deleterAllContact();
  }

  @Test
  public void update_contact()
  {
    Contact newContact = createNewContact("Muhammathu Aadil",
        "Kalilur Rahmna", "Blr","IN",
        "rush2rehman@gmail.com", "123");
    String contactId = contactController.createContact(newContact);
    newContact.setCity("CHN");
    contactController.updateContact(contactId, newContact);
    Contact updatedContact = contactController.getContact(contactId);
    assertEquals("CHN", updatedContact.getCity());

    clean_up();

  }

  private Contact createNewContact(String fname, String lName, String city, String country,
              String email, String phoneNo)
  {
    return Contact.builder().firstName(fname)
        .lastName(lName).phoneNumber(phoneNo)
        .city(city).emailId(email).country(country).build();

  }

  @Test
  public void deleteContact()
  {
    Contact newContact = createNewContact("Muhammathu Aadil",
        "Kalilur Rahmna", "Blr","IN",
        "rush2rehman@gmail.com", "123");
    String contactId = contactController.createContact(newContact);
    newContact.setCity("CHN");
    contactController.deleteContact(contactId);
    Contact updatedContact = contactController.getContact(contactId);
    assertEquals(null, updatedContact);



  }



}
