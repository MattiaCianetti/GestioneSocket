/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestionesocketcianetti;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.BindException;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Studente
 */
public class Server 
{
    /**
     * @param args the command line arguments
     */
        public static void main(String[] args)  
        {
            //porta del server maggiore di 1024
            int port = 2000;
            //tempo in millisecondi di attesa
            int time = 5000;
            //oggetto server socket necessario per accettare la connessione  
            ServerSocket serverSocket = null;
            //oggetto socket da usare per la connessione 
            Socket socket = null;

            try 
            {
                //1 server in ascolto su una porta
                serverSocket = new ServerSocket(port);
                System.out.println("Server creato correttamente in attesa di connessione");
                //2 server in attesa di connessione
                socket = serverSocket.accept();
                System.out.println("Server connesso con il client " + socket.getRemoteSocketAddress());    
                //3 scambio dati - scrittura messaggio di benvenuto
                OutputStream os = socket.getOutputStream();
                BufferedWriter bw = new BufferedWriter( new OutputStreamWriter (os));  
                bw.write("Benvenuto client");  
                //chiudere l'outputStream 
                bw.flush();
                //3 scambio dati - lettura messaggio client
                InputStream is = socket.getInputStream();
                BufferedReader br = new BufferedReader( new InputStreamReader (is));
                br.readLine();
                
                
            } 

            catch(ConnectException ex)
            {
                System.err.println("Errore server non disponibile");
            }catch (IOException ex) 
            {
                System.err.println("Errore nella creazione del server");
                Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
            }
      

            //4 chiusura socket non server socket 
            finally
            {
                try
                { 
                    if(socket != null)
                        socket.close();
                }
                catch(IOException ex)
                {
                    System.err.println("Errore nella chiusura del socket");
                }
            }
        }
    }

