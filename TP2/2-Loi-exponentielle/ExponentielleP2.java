package MPI.TP2.EX2;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;
import MPI.TP2.EX2.Exponentielle;

public class ExponentielleP2 {
	
	/**
	 * G�n�re un tableau de valeur de la loi Exponentielle
	 * 
	 * @param lambda Le premier param�tre de la loi Exponentielle
	 * @param nbVal Fiabilit�
	 * @return Un tableau contenant les valeurs de la loi exponentielle
	 */
	public static Hashtable<Integer, Integer> generate(double lambda, int nbval){
		Hashtable<Integer, Integer> g = new Hashtable<Integer, Integer>();
		
		for (int k = 0; k < nbval; k++) {
			double y = Math.random();
			int result = (int) (Exponentielle.compute(lambda, y) * 10);
			try{
				g.put(result, g.get(result) + 1);
			}catch(NullPointerException e){
				g.put(result, 1);
			}
		}
		
		return g;
	}
	
	/**
	 * Ecrit dans un fichier les valeurs g�n�r�
	 * 
	 * @param filename Nom du fichier dans lequel les valeurs seront inscrites
	 * @param lambda Le premier param�tre de la loi Exponentielle
	 * @param nblances Fiabilit�
	 * @throws Exception En cas de probl�me lors de l'�criture du fichier
	 */
	public static void write(String filename, double lambda, int nblances) throws Exception{
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		Hashtable<Integer, Integer> g = generate(lambda, nblances);
		
		Enumeration<Integer> keyList = g.keys();
		while (keyList.hasMoreElements()) {
			int key = keyList.nextElement();
			writer.println(key + " " + g.get(key));
			
		}
		
		writer.close();	
		
		System.out.print("\"" + System.getProperty("user.dir") + "/" + filename + "\" using 1:2 t \"Lambda=" + lambda + "\" with boxes, ");
	}
	
	public static void main(String[] args) {
		try {
			for (double i = 0.5; i < 2; i+= 0.5) {
				write("LAMBDA" + i + ".txt", i, 100000);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

