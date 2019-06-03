package es.uma.rysd.app;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

import es.uma.rysd.entities.TokenResponse;
import es.uma.rysd.entities.Category;
import es.uma.rysd.entities.CategoryResponse;
import es.uma.rysd.entities.Question;
import es.uma.rysd.entities.QuestionResponse;

public class TriviaClient {
    private String token = null;
    
    // TODO: Completar el nombre de la aplicación indicando sus nombres
    private final String app_name = "DFR Application";
    
    private final String url_api = "https://opentdb.com/";
    private final String get_token = url_api + "api_token.php?command=request";
    private final String categories = url_api + "api_category.php";
    private final String questions = url_api + "api.php";
    
    public TriviaClient() throws IOException {
		super();
        // TODO: Crear una conexión a la url indicada en get_token
		URL servicio = new URL(url_api); 
		HttpURLConnection connection = (HttpURLConnection) servicio.openConnection(); 
        // TODO: Indicar el método GET y que el mensaje NO lleva datos
		connection.setRequestMethod("GET");
		connection.setDoOutput(false);
        // TODO: Añadir las cabeceras User-Agent con el nombre de su aplicación y 
		// 		 Content-Type y Accept con el valor "application/json"
        connection.setRequestProperty("User-Agent", "app_name");
        connection.setRequestProperty("Content-Type", "application/json"); 
        connection.setRequestProperty("Accept", "application/json"); 
        // TODO: Obtener el código de respuesta y comprobar que es correcto
        TokenResponse respuesta = new TokenResponse();
        respuesta.response_code = connection.getResponseCode();
        // TODO: Obtener el flujo de entrada (InputStream) y deserializar su contenido en un objeto de tipo TokenResponse usando Gson
        Gson parser = new Gson();
        InputStream in = null; // TODO: indicar el de la conexión
        TokenResponse r = parser.fromJson(new InputStreamReader(in), TokenResponse.class);
        // TODO: Almacenar el token en el atributo token
        token = r.token;
        respuesta.token = token;
    }

    Category [] getCategories() throws IOException{
        // TODO: Crear una conexión a la url indicada en categories
    	URL servicio = new URL(categories); 
		HttpURLConnection connection = (HttpURLConnection) servicio.openConnection(); 
        // TODO: Indicar el método GET y que el mensaje NO lleva datos
		connection.setRequestMethod("GET");
		connection.setDoOutput(false);
        // TODO: Añadir las cabeceras User-Agent con el nombre de su aplicación y 
		// 		 Content-Type y Accept con el valor "application/json"
        connection.setRequestProperty("User-Agent", "app_name");
        connection.setRequestProperty("Content-Type", "application/json"); 
        connection.setRequestProperty("Accept", "application/json"); 
        // TODO: Obtener el código de respuesta y comprobar que es correcto
        CategoryResponse respuesta = new CategoryResponse();
        /*
        connection.getResponseCode();
        */
        // TODO: Obtener el flujo de entrada (InputStream) y deserializar su contenido en un objeto de tipo CategoryResponse usando Gson
        Gson g = new Gson(); 
        InputStream is = connection.getInputStream(); 
        respuesta.trivia_categories =  g.fromJson(new InputStreamReader(is), Category[].class);
        // TODO: Devuelva el atributo trivia_categories del objeto deserializado
        return respuesta.trivia_categories;
    }
    
    Question [] getQuestions(Integer amount, Integer category, String difficulty) throws IOException{
        // TODO: Crear una conexión a la url indicada en questions
    	//		 Añada los parámetros recibidos en la llamada al método y los atributos encode=url3986 y token=<token dela clase>
    	// Un ejemplo: https://opentdb.com/api.php?amount=5&difficulty=easy&category=1&encode=url3986&token=XXXX
    	// Si el parámetro category es null, en la URL no debe aparecer el parámetro category
    	URL servicio = new URL(questions); 
		HttpURLConnection connection = (HttpURLConnection) servicio.openConnection(); 
		/*
		*/
        // TODO: Indicar el método GET y que el mensaje NO lleva datos
		connection.setRequestMethod("GET");
		connection.setDoOutput(false);
        // TODO: Añadir las cabeceras User-Agent con el nombre de su aplicación y 
		// 		 Content-Type y Accept con el valor "application/json"
        connection.setRequestProperty("User-Agent", "app_name");
        connection.setRequestProperty("Content-Type", "application/json"); 
    	connection.setRequestProperty("Accept", "application/json");
        // TODO: Obtener el código de respuesta y comprobar que es correcto
    	QuestionResponse respuesta = new QuestionResponse();
    	respuesta.response_code = connection.getResponseCode();
        // TODO: Obtener el flujo de entrada (InputStream) y deserializar su contenido en un objeto de tipo QuestionResponse usando Gson
        Gson g = new Gson(); 
        InputStream is = connection.getInputStream();
        respuesta.results = g.fromJson(new InputStreamReader(is), Question[].class);
        // TODO: Devuelva el atributo results del objeto deserializado
        return respuesta.results;
    }
}
