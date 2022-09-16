package com.disney.alkemy.Impl;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.disney.alkemy.dtos.DynamicTemplatePersonalization;
import com.disney.alkemy.models.Usuario;
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class SendEmailImpl {
	private static final Logger logger = LoggerFactory.getLogger(SendEmailImpl.class);

	public String sendTextEmail(Usuario usuario) throws IOException {
		
		Email from = new Email("jpbernal34@misena.edu.co");
		String subject = "Bienvenido a la API de Disney";
		Email to = new Email(usuario.getUsuario());
		Mail mail = new Mail();

		DynamicTemplatePersonalization personalization = new DynamicTemplatePersonalization();
		personalization.addTo(to);
		mail.setFrom(from);
		mail.setSubject(subject);

		personalization.addDynamicTemplateData("clave", usuario.getClave());
		mail.addPersonalization(personalization);
		mail.setTemplateId("d-574ff84b63c04cc6abf65e8ae85373c8");

		SendGrid sg = new SendGrid("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");
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			logger.info(response.getBody());
			return response.getBody();
		} catch (IOException ex) {
			throw ex;
		}
	}
}