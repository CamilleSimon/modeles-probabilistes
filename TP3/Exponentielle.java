package MPI.TP2.EX2;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

public class Exponentielle {
	
	/**
	 * Calcul le résultat de la loi Exponentielle
	 * 
	 * @param lambda Le premier paramètre de la loi Exponentielle
	 * @param y Le second paramètre de la loi Exponentielle
	 * @return La valeur de la loi exponentielle pour les paramètres donnés
	 */
	public static double compute(double lambda, double y){
		return -(Math.log(1 - y) / lambda);
	}
	
	/**
	 * Génère un tableau de valeur de la loi Exponentielle
	 * 
	 * @param lambda Le premier paramètre de la loi Exponentielle
	 * @param step Le nombre de point de l'on veux
	 * @return Un tableau contenant les valeurs de la loi exponentielle
	 */
	private static double[] generate(double lambda, double step){
		int nbval = (int) (1 / step);
		double[] g = new double[nbval +1];
		
		for (int k = 0; k < nbval; k++) {
			g[k] = compute(lambda, step * k);
		}
		
		g[nbval] = compute(lambda, 0.99);
		return g;
	}
	
	/**
	 * Ecrit dans un fichier les valeurs généré
	 * 
	 * @param filename Nom du fichier dans lequel les valeurs seront inscrites
	 * @param lambda Le premier paramètre de la loi Exponentielle
	 * @param step Le nombre de point de l'on veux
	 * @throws Exception En cas de problème lors de l'écriture du fichier
	 */
	public static void write(String filename, double lambda, double step) throws Exception{
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		double[] g = generate(lambda, step);
		
		for(int i = 0; i < g.length; i++){
			writer.println((double)Math.round(step * i * 10d) / 10d + " " + g[i] * 10);
		}
		
		writer.close();	
		
		System.out.println("\"" + System.getProperty("user.dir") + "/" + filename + "\" t \"Lambda=" + lambda + "\" with linesp lt 1 pt 1, ");
	}
	
	public static void main(String[] args) {
		try{
			for (double i = 0.5; i < 2; i+= 0.5) {
				write("LAMBDA" + i, i, 0.1);
			}
			}catch(Exception e){
				e.printStackTrace();
		}
	}
}
