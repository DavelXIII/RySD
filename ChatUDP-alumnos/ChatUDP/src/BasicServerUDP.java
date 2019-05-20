import java.io.IOException;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.SocketAddress;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.HashMap;

public class BasicServerUDP {
	public static void main(String[] args) throws Exception
	{
		// Creamos el socket UDP, en este caso indicamos la IP (una de las que tengamos) y puerto a utilizar
		DatagramSocket socket = new DatagramSocket(10000, InetAddress.getByName("127.0.0.1"));

		// Una de las diferencias con el cliente es que el servidor no suele acabar
		while(true){
			System.out.println("Esperando una petición");
			// Preparamos la memoria y el datagrama para recibir
			byte [] data = new byte[1000];
			DatagramPacket dpR = new DatagramPacket(data, data.length);
			// Esperamos una recepción
			socket.receive(dpR);
    		
			// Analizamos la petición 
			String request = new String(Arrays.copyOfRange(dpR.getData(), dpR.getOffset(), dpR.getLength()+dpR.getOffset()));
			// InetAddress almacena una IP
			// SocketAddress almacena una IP y puerto (se pueden consultar por separado, véase sus métodos)
			// SocketAddress es abstracta (su principal heredera es InetSocketAddress)
			SocketAddress client = dpR.getSocketAddress();
			System.out.println("Cliente: " + client + " ha enviado: " + request);

			// Preparamos la respuesta (mensaje y datagrama):
			String reply = "... y esto es la respuesta del servidor";
			// La dirección destino en vez de tener la IP y el puerto por separado (como en el cliente), está junta
			DatagramPacket dp = new DatagramPacket(reply.getBytes(), reply.getBytes().length, client);
			// Enviamos
			socket.send(dp);

			System.out.println("Enviando al cliente " + client + " el mensaje  "+ reply);
		}
	}
}

    

