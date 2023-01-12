/**
 * Clase Servidor del examen de Programaci�n de Servicios y Procesos
 * 
 * Fecha: 30/11/2022
 * 
 * Autor: Enrique Moyano Carballo
 * 
 * Versi�n: 1.0
 * 
 * Esta clase implementa el servidor de un socket siguiendo el modelo cliente-servidor para la comunicaci�n de datos
 * 
 */
package Trimestre1.ExamenesAntiguos.ExamenPRSP2223;

import java.io.IOException;
import java.net.*;

public class Servidor
{

  public static void main(String[] args)
  {
    try
    {
      ServerSocket servidor;
      servidor=new ServerSocket(6000);
      System.out.println("Server Casino is running...");
      while (true) {
        Socket cliente = new Socket();
        cliente=servidor.accept();//esperando cliente
        HiloServidor hilo = new HiloServidor(cliente);
        hilo.start();
      }
      
    } catch (IOException e) { e.printStackTrace(); }

  }

}
