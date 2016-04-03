package com.mqtt.consumer;

import java.io.IOException;
import java.util.Date;

import com.agilepet.localization.LocalizationManager;
import com.ibm.mq.*;
/**
 * 
 * @author harold
 *
 */
public class QueueManager {

	private static String hostname = "ec2-52-87-240-191.compute-1.amazonaws.com";
	private static String channel  = "SYSTEM.DEF.SVRCONN";
	private static String qManager = "GRUPO8";
	private static String queueName = "GUARDAR.QUEUE";
	private static int port = 1414;
	private static String user = "Administrator";
	private static String password = "Grupo*8";
	private MQQueueManager qMgr;


	public static void main(String[] args) {
		
		System.out.println("Begin connection...");
		System.out.println("Set settings...");
		MQEnvironment.hostname = hostname;
		MQEnvironment.port = port;
		MQEnvironment.channel = channel;
		MQEnvironment.userID = user;
		MQEnvironment.password = password;
		MQEnvironment.properties.put(MQC.TRANSPORT_PROPERTY, MQC.TRANSPORT_MQSERIES);

		try {
			//int openOptions = MQC.MQOO_INQUIRE + MQC.MQOO_FAIL_IF_QUIESCING;
			System.out.println("Connect");
			MQQueueManager qMgr = new MQQueueManager(qManager);
			System.out.println("Success Connection");
			int openOptions = MQC.MQOO_FAIL_IF_QUIESCING | MQC.MQOO_INPUT_SHARED | MQC.MQOO_BROWSE | MQC.MQOO_INQUIRE;  
			MQQueue queue = qMgr.accessQueue(queueName, openOptions);

			MQMessage theMessage    = new MQMessage();
			MQGetMessageOptions gmo = new MQGetMessageOptions();
			    gmo.options=MQC.MQGMO_WAIT | MQC.MQGMO_BROWSE_FIRST;
			    gmo.matchOptions=MQC.MQMO_NONE;
			    gmo.waitInterval=MQC.MQEI_UNLIMITED;

			LocalizationManager localizacion = new LocalizationManager();
			    
			boolean thereAreMessages=true;
			while(thereAreMessages){
		
				System.out.println(queue.getCurrentDepth());
				if(queue.getCurrentDepth()!=0)
				{
			        //read the message          
			        queue.get(theMessage,gmo);  
			        //print the text            
			        String msgText = theMessage.readString(theMessage.getMessageLength());
			        System.out.println("msg text: "+msgText);
			        System.out.println("Fecha inicio: "+new Date().getTime());
			        localizacion.procesarMensaje(msgText);
			        gmo.options = MQC.MQGMO_MSG_UNDER_CURSOR;
			        queue.get(theMessage,gmo);
			        gmo.options = MQC.MQGMO_BROWSE_NEXT + MQC.MQGMO_NO_WAIT + MQC.MQGMO_FAIL_IF_QUIESCING + MQC.MQGMO_ACCEPT_TRUNCATED_MSG; 
				}
			}
			
		} catch (MQException e) {
			if(e.reasonCode == e.MQRC_NO_MSG_AVAILABLE) {
	            System.out.println("no more message available or retrived");
	        }else if(e.reasonCode == e.MQRC_NO_MSG_UNDER_CURSOR){
	        	System.out.println("no more message under cursor");
	        }
		}catch (IOException e) {
	        System.out.println("ERROR: "+e.getMessage());
	    }
	}
}
