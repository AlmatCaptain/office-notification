package kz.iitu.office.notification.service;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import kz.iitu.office.notification.model.ReservedRoomDTO;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailService {
    public static void sendByEmail(ReservedRoomDTO roomDTO) throws AddressException {
        Config config = ConfigFactory.load();
        String host = config.getString("mail-config.host");
        String username = config.getString("mail-config.login");
        String password = config.getString("mail-config.password");
        Properties props = System.getProperties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(
                        username, password);
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, roomDTO.getLogin());
            message.setSubject("From office-notification");
            message.setText("Hi! You reserved room №" + roomDTO.getRoomNumber() + ", from " + roomDTO.getDate() + " " +
                    "to " + roomDTO.getToDate());

            Transport transport = session.getTransport("smtp");

            transport.connect(username, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
