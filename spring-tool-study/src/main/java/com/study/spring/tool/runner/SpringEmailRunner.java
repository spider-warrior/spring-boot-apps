package com.study.spring.tool.runner;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.Properties;

/**
 * @author yj
 * @since 2019-12-10 21:23
 **/
@Component
public class SpringEmailRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.126.com");
        props.put("mail.smtp.port", "25");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("qq362961910@126.com", "yangjian1234");
            }
        });

        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("qq362961910@126.com", false));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("qq362961910@126.com"));
        msg.setSubject("Tutorials point email");
        msg.setContent("Tutorials point email", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Tutorials point email", "text/html");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();

        attachPart.attachFile("/Users/yangjian/workace/test.png");
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);

        Transport.send(msg);
    }
}
