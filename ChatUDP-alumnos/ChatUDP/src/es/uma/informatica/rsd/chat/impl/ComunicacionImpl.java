package es.uma.informatica.rsd.chat.impl;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;

import es.uma.informatica.rsd.chat.ifaces.Comunicacion;
import es.uma.informatica.rsd.chat.ifaces.Controlador;
import es.uma.informatica.rsd.chat.impl.DialogoPuerto.PuertoAlias;

// Clase a implementar 
public class ComunicacionImpl implements Comunicacion {
	
	private InetAddress ia;
	private MulticastSocket ms;
	private String alias;
	
	private Controlador c;
	
	@Override
	public void crearSocket(PuertoAlias puerto) {
		try {
			ia = InetAddress.getByName("102.168.164.9");
			ms = new MulticastSocket(new InetSocketAddress(ia, puerto.puerto));
			alias = puerto.alias;
		} catch (Exception e) {
			throw new RuntimeException();
		}
	}

	@Override
	public void setControlador(Controlador c) {
		this.c = c;
	}

	@Override
	public void runReceptor() {
		byte[] data;
		String cadena;
		
	}

	@Override
	public void envia(InetSocketAddress sa, String mensaje) {
		
	}

	@Override
	public void joinGroup(InetAddress multi) {
		multi.
	}

	@Override
	public void leaveGroup(InetAddress multi) {
		
		
	}

	
}
