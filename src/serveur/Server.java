/*
 * Code source inspire de :
 * http://www.careerbless.com/samplecodes/java/beginners/socket/SocketBasic1.php
 * https://openclassrooms.com/courses/introduction-aux-sockets-1
 * 
 */
package serveur;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;
import java.net.ServerSocket;

public class Server {

	static int DEFAULT_SERVER_PORT = 2222;
		
	
	public static void main(String[] args){
		
		int serveurPort;
		ServerSocket socket;

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Serveur Port ?: " + DEFAULT_SERVER_PORT);
		
		String scannPort = scanner.nextLine();
		
		try{
			
			serveurPort = Integer.parseInt(scannPort);
	    }catch(NumberFormatException e){
	    	serveurPort=DEFAULT_SERVER_PORT;
	    }		
		
		
		try {
		socket = new ServerSocket(serveurPort);
		Thread t = new Thread(new Accepter_clients(socket));
		t.start();
		System.out.println("Merci. Le serveur est pret.");
		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class Accepter_clients implements Runnable {
		
	private int MAX = 256;
	private ServerSocket socketserver;
	private Socket socket;
	@SuppressWarnings("unused")
	private byte[] buff = new byte[MAX];
	
	@SuppressWarnings("unused")
	private String message;
	 
	public Accepter_clients(ServerSocket s){
		socketserver = s;
	}
		
		public void run() {
	        
			try {
	        	while(true){
	        		//accepter la demande de connexion du client 
	        		socket = socketserver.accept(); // Un client se connecte on l'accepte
	                                
	                //recevoir le message du client
	                InputStream is = socket.getInputStream();
	                InputStreamReader isr = new InputStreamReader(is);
	                BufferedReader br = new BufferedReader(isr);
	                String message = br.readLine();
	                
	                String destinataire = socket.getRemoteSocketAddress().toString();
	                System.out.println("Message reçu du client: "+message+ " " + destinataire);
	        		
	           
	                message = message.toUpperCase();
	                
	                
	                //envoyer le message de retour au client.
	                OutputStream os = socket.getOutputStream();
	                OutputStreamWriter osw = new OutputStreamWriter(os);
	                BufferedWriter bw = new BufferedWriter(osw);
	                bw.write(message);
	                System.out.println("Message retourne au client: " + message);
	                bw.flush();
	                
	                socket.close();
	        	}
	        
	        } catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
