package CitoolAutomationTesting;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendEMailcitrussClass {

	final String username = "ctvbuild.test@ctv-it.net"; // change to your Gmail username
	final String password = "NewComp123@"; // change to your Gmail password
	final String from = "ctvbuild.test@ctv-it.net"; // change to from email address
	final String to = "pooja.patange@citruss.com"; // change to to email address
	final String msg = "OOPS CITool Automation Testing Failed";
	final String subject = "OOPS CITool Automation Testing Failed";
	final String body = "CITool Automation Testing Failed";

	public void emailsend(String Filename) {

		String host = "mail.gandi.net";
		Properties props = new Properties();
		props.put("mail.smtp.starttls.enable", "true");

		props.put("mail.smtp.host", host);

		props.put("mail.smtp.user", from);

		props.put("mail.smtp.password", password);

		props.put("mail.smtp.port", "25");

		props.put("mail.smtp.auth", "true");

		Session session = Session.getDefaultInstance(props);

		MimeMessage message = new MimeMessage(session);

		try {

			// Set from address

			message.setFrom(new InternetAddress(from));

			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// Set subject

			message.setSubject(subject);

			message.setText(body);

			BodyPart objMessageBodyPart = new MimeBodyPart();

			objMessageBodyPart.setText("Please Find The Attached Report File!");

			Multipart multipart = new MimeMultipart();

			multipart.addBodyPart(objMessageBodyPart);

			objMessageBodyPart = new MimeBodyPart();

			String fileName = Filename;

			DataSource source = new FileDataSource(fileName);

			objMessageBodyPart.setDataHandler(new DataHandler(source));

			objMessageBodyPart.setFileName(fileName);

			multipart.addBodyPart(objMessageBodyPart);

			message.setContent(multipart);

			Transport transport = session.getTransport("smtp");

			transport.connect(host, from, password);

			transport.sendMessage(message, message.getAllRecipients());
			
			System.out.println("Mail sent successfully");
			
			transport.close();

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
