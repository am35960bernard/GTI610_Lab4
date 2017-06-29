/*
 * Code source inspire de :
 * http://www.careerbless.com/samplecodes/java/beginners/socket/SocketBasic1.php
 * https://openclassrooms.com/courses/introduction-aux-sockets-1
 * 
 */
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
import java.util.Scanner;



public class Client {
	
	static int DEFAULT_SERVER_PORT = 2222;
	
	public static void main(String[] args) {
			
		Socket socket;
		String message;

		
		@SuppressWarnings("unused")
		InetAddress adresseIP;

		int port;
	
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Vers adresse IP : localhost");
		
		String scannIpAddress = scanner.nextLine();
		
		try {
			adresseIP = InetAddress.getByName(scannIpAddress);
		} catch (UnknownHostException e) {
			try {
				adresseIP = InetAddress.getLocalHost();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
		}
		
	
		System.out.println("Vers Port : "+ DEFAULT_SERVER_PORT);
		
		String scannPort = scanner.nextLine();
							
		try{
			
			port = Integer.parseInt(scannPort);
	    }catch(NumberFormatException e){
	    	port = DEFAULT_SERVER_PORT;
	    }
		
		
		System.out.println("Veuillez saisir un message : ");
		
		String scannMessage = scanner.nextLine();
							
		message = scannMessage;
				
			
		try {
		
			//Connecter au serveur
		    socket = new Socket(InetAddress.getLocalHost(),port);	
		    
		    //envoyer un message au serveur
		    OutputStream os = socket.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os);
            BufferedWriter bw = new BufferedWriter(osw);
 
            String envoieMessage = message + "\n";
            bw.write(envoieMessage);
            bw.flush();
            
            //recevoir le message retourne du serveur
            InputStream is = socket.getInputStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String messageRecu = br.readLine();
            System.out.println("\nMessage retourner par le serveur : " +messageRecu);
            
			socket.close();
			
		}catch (UnknownHostException e) {
			
			e.printStackTrace();
		}catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
}
