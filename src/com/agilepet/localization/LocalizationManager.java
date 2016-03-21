package com.agilepet.localization;
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

	}

	private void priorizarEvento(String message)
	{

	}

	private void atenderEvento()
	{
		//Atender evento si es prioritario - Enviar correo
		//Encolar evento si no es prioritario
	}

	private void encolarEvento()
	{
		
	}

}
