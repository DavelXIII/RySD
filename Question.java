package es.uma.rysd.entities;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Random;

public class Question {
	public String category = null;
	public String type = null;
	public String difficulty = null;
	public String question = null;
	public String correct_answer = null;
	public String [] incorrect_answers = null;
	
	private Integer n = null;
	
	public boolean isCorrect(Integer option){
		if(n == null || option != n)
			return false;
		else
			return true;
	}
	
	public String toString(){
		String s = "";
		if(question != null){
			try {
				s = URLDecoder.decode(question, "UTF-8") + "\n";
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Random r = new Random();
			n = r.nextInt(this.incorrect_answers.length+1)+1;
			int count = 0;
			s += "\nOpciones:\n";
			for(int i = 1; i <= this.incorrect_answers.length+1; i++){
				if(n == i){
					try {
						s += "\t"+i+" - "+ URLDecoder.decode(this.correct_answer, "UTF-8") +"\n";
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				} else {
					try {
						s += "\t"+i+" - "+ URLDecoder.decode(this.incorrect_answers[count], "UTF-8") +"\n";
					} catch (UnsupportedEncodingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					count++;
				}
			}
		}
		return s;
	}
}
