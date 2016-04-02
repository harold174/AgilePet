package com.agilepet.localization;

import java.util.ArrayList;

import com.agilepet.utils.DBConnection;
import com.agilepet.utils.MailManager;

/**
 * 
 * @author harold
 *
 */
public class LocalizationManager {

	//Identificador unico del collar
	private String horaCollar;
	private String horaServerMQTT;
	private String horaEnvioMail;
	private String cliente;
	private String latitud;
	private String longitud;
	private DBConnection connection;

	public LocalizationManager()
	{

	}

	public void procesarMensaje(String message)
	{
		//Recibir info
		cliente=message.substring(0, 9);
		//Eje y; 90 a -90°
		latitud=message.substring(10, 27);
		//Eje x; 180 a -180°
		longitud=message.substring(28, 45);
		horaCollar = message.substring(47,message.length());

		System.out.println(cliente+"*"+latitud+"*"+longitud+"*"+horaCollar);	

		//Priorizar evento
		priorizarEvento(message);

	}

	private void persistirInfo(String message)
	{
		//TODO save message on BD
		//DBConnection.connect();

	}

	private void priorizarEvento(String message)
	{
		//Revisar si el perro esta perdido
		String query="select * from mascota m " +
				"join zona_segura_mascota z on m.id = z.id_mascota " +
				"where serial_collar="+cliente + " "+
				"and (to_number('"+longitud+"', 'S99D9999999999999') between z.coordenada_x2 and z.coordenada_x1 or to_number('"+longitud+"', 'S99D9999999999999') between z.coordenada_x1 and z.coordenada_x2) "+
				"and ("+latitud+" between coordenada_y1 and coordenada_y2 or "+latitud+" between coordenada_y2 and coordenada_y1)";

		ArrayList<String[]> resultado = DBConnection.executeQuery(query);
		
		//Atender evento si es prioritario 
		if(resultado.size()>0)
		{
			atenderEvento(message);
		}else{
			//Encolar evento si no es prioritario
		}
		
		
	}

	private void atenderEvento(String message)
	{
		//Enviar correo MailManager class

		MailManager.generateAndSendEmail(message, "hl.murcia222@uniandes.edu.co", "test");
		//Persistir info
		persistirInfo(message);

	}

	private void encolarEvento()
	{
		//Encolar nuevamente
	}

}
