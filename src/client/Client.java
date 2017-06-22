package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

//http://www.careerbless.com/samplecodes/java/beginners/socket/SocketBasic1.php

public class Client {
	
	public static void main(String[] zero) {
		
		Socket socket;
		String message = "We come in piece.";

		try {
		
			//Connecter au serveur
		    socket = new Socket(InetAddress.getLocalHost(),2222);	
		    
		    //envoyer un message au serveur
		    OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
 
            String envoieMessage = message + "\n";
            bw.write(envoieMessage);
            bw.flush();
            
            //recevoir le message retourner du serveur
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String messageRecu = br.readLine();
            System.out.println("Message retourner par le serveur : " +messageRecu);
            
			socket.close();
			
		}catch (UnknownHostException e) {
			
			e.printStackTrace();
		}catch (IOException e) {
			
			e.printStackTrace();
		}	
	}
}
