package hackathon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import javax.swing.*;;


public class Main {

	
	static String d;
	static String pro;
	
	
	static LinkedList<Boolean> distance = new LinkedList<Boolean>();
	static LinkedList<Boolean> posture = new LinkedList<Boolean>();
	static LinkedList<Boolean> brightness = new LinkedList<Boolean>();
	
	
	public static void main(String[] args) {
		
		/*
		Process p;
		try {
			p = Runtime.getRuntime().exec("python3 trainScript.py");
			BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String ret = in.readLine();
					
			d = ret.split(";")[0];
			pro = ret.split(";")[1];
			
			System.out.println(ret);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		*/
		
		Interface interface1 = new Interface();
		
	}

}
