package com.agilepet.localization;

import com.agilepet.utils.MailManager;

/**
 * 
 * @author harold
 *
 */
public class LocalizationManager {

	//Identificador unico del collar
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
		System.out.println(cliente+"*"+latitud+"*"+longitud);

		//Persistir info
		persistirInfo(message);
		//Priorizar evento
	
		priorizarEvento(message);
	}

	private void persistirInfo(String message)
	{
		//TODO save message on BD

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
		
		MailManager.generateAndSendEmail(message, "xxxx@uniandes.edu.co", "test");
	}

	private void encolarEvento()
	{
		//Encolar nuevamente
	}

}
