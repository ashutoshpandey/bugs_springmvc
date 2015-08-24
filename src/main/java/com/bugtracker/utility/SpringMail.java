package com.bugtracker.utility;

import java.util.ArrayList;
import java.util.List;

import javax.mail.internet.AddressException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
 
public class SpringMail
{
	private JavaMailSender mailSender;
 
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
 
	public void sendMail(String from, String to, String subject, String text, boolean hasAttachment, String fileName, String filePath) {
		
		if(to==null || to.trim().length()==0 || subject==null || text==null)
			return;
		
		if(text.trim().length()==0)
			text = " ";
		
		if(subject.trim().length()==0)
			subject = " ";

		try{
			MimeMessage message = mailSender.createMimeMessage();
			
			MimeMessageHelper helper;
			
			try {
				if(hasAttachment){
					helper = new MimeMessageHelper(message, true, "UTF-8");
					
					if(hasAttachment){
						FileSystemResource file = new FileSystemResource(filePath);
						
						helper.addAttachment(fileName, file);					
					}
				}
				else
					helper = new MimeMessageHelper(message, false, "UTF-8");
				
				helper.setFrom(from);
				helper.setTo(to);
				helper.setSubject(subject);
				helper.setText(text, true);

				mailSender.send(message);
			}
			catch(AddressException aex){
				System.out.println("Cannot send : " + aex.getMessage());
			}
			catch (Exception e) {
				System.out.println("Not sending " + e.getMessage());
			}
		}
		catch(Exception ex){
			System.out.println("Could not send : " + ex.getMessage());
		}
	}

	public void sendMail(String from, ArrayList<String> emails, String subject,
			String text, boolean hasAttachment, String fileName, String filePath) {

		if(emails==null || emails.size()==0 || subject==null || text==null)
			return;
		
		if(text.trim().length()==0)
			text = " ";
		
		if(subject.trim().length()==0)
			subject = " ";

		try{
			MimeMessage message = mailSender.createMimeMessage();
			
			MimeMessageHelper helper;
			
			try {
				if(hasAttachment){
					helper = new MimeMessageHelper(message, true, "UTF-8");
					
					if(hasAttachment){
						FileSystemResource file = new FileSystemResource(filePath);
						
						helper.addAttachment(fileName, file);					
					}
				}
				else
					helper = new MimeMessageHelper(message, false, "UTF-8");
				
				helper.setFrom(from);
				
				helper.setTo(emails.get(0));
				for(int i=1;i<emails.size();i++)
					helper.addCc(emails.get(i));
				
				helper.setSubject(subject);
				helper.setText(text, true);

				mailSender.send(message);
			}
			catch(AddressException aex){
				System.out.println("Cannot send : " + aex.getMessage());
			}
			catch (Exception e) {
				System.out.println("Not sending " + e.getMessage());
			}
		}
		catch(Exception ex){
			System.out.println("Could not send : " + ex.getMessage());
		}
		
	}
}