package com.github.KCulture.Notified;

import org.junit.Test;

import com.github.KCulture.Notified.Person;

import static org.junit.Assert.*;

public class PersonTest {
    @Test
    public void canConstructAPersonWithAName() {
        Person person = new Person("Larry");
        assertEquals("Larry", person.getName());
    }
}
