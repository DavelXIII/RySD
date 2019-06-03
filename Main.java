package es.uma.rysd.app;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Random;
import java.util.Scanner;

import es.uma.rysd.entities.Category;
import es.uma.rysd.entities.Question;

public class Main {
	private final static String proxy = "proxy.lcc.uma.es";
	private final static String proxy_port = "3128"; 

	public static void main(String[] args) throws IOException {
    	// Descomente las siguiente líneas si lo está probando en el laboratorio y accede a Internet usando el proxy
    	// System.setProperty("https.proxyHost",proxy); 
    	// System.setProperty("https.proxyPort",proxy_port);
    	
		Scanner sc = new Scanner(System.in);
		System.out.println("Trivial (version RySD)");
		TriviaClient t = new TriviaClient();
		Category [] cs = t.getCategories();
		if(cs == null){
			System.out.println("No se obtuvieron las categorias correctamente");
			System.exit(-1);
		}

		Integer rep = null;

		do{
			System.out.print("Numero de preguntas: ");
			Integer noq = sc.nextInt();
			System.out.print("Categoria (0 - cualquiera, otro - aleatoria): ");
			Integer cat = sc.nextInt();
			if(cat == 0)
				cat = null;
			else {
				Random r = new Random();
				Integer rn = r.nextInt(cs.length);
				System.out.println("Categoria elegida: " + cs[rn].name);
				cat = cs[rn].id;
			}
			System.out.print("Dificultad (easy, medium, hard): ");
			String d = sc.next();
			if(!d.equals("easy") || !d.equals("medium") || !d.equals("hard"))
				d = "easy";
			Question [] qs = t.getQuestions(noq, cat, d);
			if(qs == null){
				System.out.println("No se obtuvieron las preguntas correctamente");
				System.exit(-1);
			}

			Integer points = 0;
			for(Question q: qs){
				System.out.println("\n"+q);
				System.out.print("Repuesta: ");
				int n = sc.nextInt();
				if(q.isCorrect(n)){
					System.out.println("Enhorabuena!");
					points++;
				} else {
					try {
						System.out.println("Lo siento, la respuesta correcta era: "+URLDecoder.decode(q.correct_answer,"UTF-8"));
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			System.out.println("Has conseguido "+ points + " puntos\n");
			System.out.print("Desea jugar de nuevo (1 para jugar) ");
			rep = sc.nextInt();
		} while(rep == 1);
		System.out.println("Gracias por jugar con nosotros");
	}

}
