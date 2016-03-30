package com.agilepet.localization;

import com.agilepet.utils.MailManager;

/**
 * 
 * @author harold
 *
 */
public class LocalizationManager {

	//Identificador unico del collar
	String horaCollar;
	String horaServerMQTT;
	String horaEnvioMail;
	String cliente;
	String latitud;
	String longitud;

	public LocalizationManager()
	{




	}

	public void procesarMensaje(String message)
	{
		//Recibir info
		cliente=message.substring(0, 9);
		latitud=message.substring(10, 27);
		longitud=message.substring(28, 45);
		horaCollar = message.substring(47,message.length());
		//horaServerMQTT = message.substring(71, 89);
		
		
		float latitudF= Float.parseFloat(latitud);
		float longitudF=Float.parseFloat(longitud);
		System.out.println(cliente+"*"+latitudF+"*"+longitudF+"*"+horaCollar);//+"*"+horaServerMQTT);	
		
		if (( latitudF<3.58 || latitudF>3.596 )|| (longitudF<-74.082 || longitudF>74.081))
		{
			priorizarEvento(message);
		}
				
		//Persistir info
		persistirInfo(message);
		//Priorizar evento
	
		
	}

	private void persistirInfo(String message)
	{
		//TODO save message on BD
		//DBConnection.connect();

	}

	private void priorizarEvento(String message)
	{
		//Revisar si el perro esta perdido
		//Atender evento si es prioritario 
		atenderEvento(message);
	    //Encolar evento si no es prioritario
	}

	private void atenderEvento(String message)
	{
		//Enviar correo MailManager class
		
		MailManager.generateAndSendEmail(message, "hl.murcia222@uniandes.edu.co", "test");
	}

	private void encolarEvento()
	{
		//Encolar nuevamente
	}

}
