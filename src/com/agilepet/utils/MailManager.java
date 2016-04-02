package com.agilepet.utils;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author hl.murcia222
 */
public class MailManager {

    
    static Properties mailServerProperties;
    static Session getMailSession;
    static MimeMessage generateMailMessage;
    

    public static void generateAndSendEmail(String message, String emailRecipient, String subject) {

        try {
            
            //loadProperties();

            //Step1		
            //System.out.println("\n 1st ===> setup Mail Server Properties..");
            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");
            System.out.println("Mail Server Properties have been setup successfully..");

            //Step2		
            //System.out.println("\n\n 2nd ===> get Mail Session..");
            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            generateMailMessage = new MimeMessage(getMailSession);
            generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailRecipient));
            generateMailMessage.setSubject("Alerta : Perro fuera de cuadrante");
            
            Date todaysDate = new Date();
    		Long horaMail = todaysDate.getTime();
    		
    		Long horaCollar = Long.valueOf(message.substring(47, message.length()));
    		
    		Long diferencia = horaMail - horaCollar;
    		
    		System.out.println("Collar time: "+ horaCollar);
    		System.out.println("Mail time: "+ horaMail);
    		System.out.println("Diferencia: "+ diferencia);
            
            String emailBody = message+"="+diferencia;
            generateMailMessage.setContent(emailBody, "text/html");
            //System.out.println("Mail Session has been created successfully..");

            //Step3		
            //System.out.println("\n\n 3rd ===> Get Session and Send mail");
            Transport transport = getMailSession.getTransport("smtp");

            // Enter your correct gmail UserID and Password (XXXApp Shah@gmail.com)
            transport.connect("smtp.gmail.com", "noreply.dummy10@gmail.com", "atalanta85");
            //transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
            transport.close();
            
        } catch (Exception e) {
            Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    private static void loadProperties() {

        Properties datos = new Properties( );
        String data="";
        try
        {
                FileInputStream input = new FileInputStream( "src/main/resources/admin_email.properties" );
                datos.load( input );
                //adminEmail=datos.getProperty("admin.email");
        }
        catch( Exception e )
        {
                Logger.getGlobal().log(Level.SEVERE, e.getMessage(), e);
        }

    }

}
