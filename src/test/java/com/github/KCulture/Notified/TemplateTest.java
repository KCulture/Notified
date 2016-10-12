package com.github.KCulture.Notified;

import org.junit.Test;
import org.junit.Assert;

import com.github.KCulture.Notified.Services.EmailMessageService;


public class TemplateTest {
    @Test
    public void emptyTest() {
    	EmailMessageService se = new EmailMessageService();
    	Assert.assertTrue(se.loadProps().containsKey("mail.smtp.host"));
    }
}
