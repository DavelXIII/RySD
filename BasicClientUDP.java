import java.io.*;
import java.net.*;
import java.util.Arrays;

public class BasicClientUDP {

	public static void main(String[] args) throws Exception {
		// Creamos el socket del cliente
		// Al no indicar el puerto, cogerá automática uno libre dentro de los temporales/efímeros
		// (Vea una alternativa para su creación con puerto/IP en el servidor)
		DatagramSocket socket = new DatagramSocket(); 

		// Preparamos los datos necesarios para el envío:
		String request = "Esto es el mensaje del cliente"; // Datos
		InetAddress serverAddress = InetAddress.getByName("127.0.0.1"); // IP del servidor
		int portNumber = 10000; // Puerto del servidor

		// Creamos el datagrama a ser enviado: (datos y su longitud, IP y puerto del servidora)
		// (Vea el servidor para una alternativa ligeramente diferente)
		DatagramPacket dp = new DatagramPacket(request.getBytes(), request.getBytes().length, serverAddress, portNumber);
		// Y lo enviamos			
		socket.send(dp);

		System.out.println("Enviado: " + request);
        	
		// Preparamos los datos para la recepción:	
		byte [] data = new byte[1000]; // Memoria donde vamos a guardar la respuesta
		// Creamos el datagrama para almacenar la respuesta
		DatagramPacket dpR = new DatagramPacket(data, data.length);
		// Esperamos a un datagrama entrante
		socket.receive(dpR);
        		
		// Analizamos la respuesta: (más análisis en el servidor)
		// Convertimos la respuesta a string
		String reply = new String(Arrays.copyOfRange(dpR.getData(), dpR.getOffset(), dpR.getLength()+dpR.getOffset()));
        
		System.out.println("Recibimos: " + reply);
        
		// Cerramos el socket
		socket.close();
    }
}
