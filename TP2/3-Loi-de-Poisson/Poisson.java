package MPI.TP2.EX3;

import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.Hashtable;

import MPI.TP2.EX2.Exponentielle;

public class Poisson {

	/**
	 * Calcul la loi Exponentielle avec le second paramètre aléatoire
	 * 
	 * @param lambda Le premier paramètre de la loi Exponentielle
	 */
	public static double compute(double lambda){
		return Exponentielle.compute(lambda, Math.random());
	}
	
	/**
	 * Génère un tableau de valeur de la loi de Poisson
	 * 
	 * @param d Le nombre de valeur que l'on souhaite
	 * @param lambda Le premier paramètre de la loi Exponentielle
	 * @param fiabilité Nombre de fois à répéter le tirage aléatoire
	 * @return Un tableau de valeurs
	 */
	private static double[] generate(int d, double lambda, int fiabilite) {
		double[] table = new double[d+1];
		for (int i = 0; i < table.length; i++) {
			table[i] = 0;
		}
		
		for (int j = 0; j < fiabilite; j++) {
			int c = 0;
			double nextA = compute(lambda);
			
			for (int i = 0; i < table.length; i++) {
				while(nextA < i){
					c++;
					nextA += compute(lambda);
				}
				table[i] += c;
			}
		}
		
		for (int i = 0; i < table.length; i++) {
			table[i] /= fiabilite;
		}
		
		return table;
	}
	
	/**
	 * Ecrit dans un fichier les valeurs généré
	 * 
	 * @param filename Le nom du fichier à créer
	 * @param d Le nombre de valeur que l'on souhaite
	 * @param lambda Le premier paramètre de la loi Exponentielle
	 * @param fiabilité Nombre de fois à répéter le tirage aléatoire
	 */
	public static void write(String filename, double lambda, int d, int fiabilite) throws Exception{
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		
		double[] table = generate(d, lambda, fiabilite);
		
		for(int i = 0; i < table.length; i++){
			writer.println(i + " " + table[i]);
		}
		
		writer.close();	
		
		System.out.println("\"" + System.getProperty("user.dir") + "/" + filename + "\" t \"Lambda=" + lambda + "\" with linesp lt 1 pt 1, ");
	}
	
	public static void main(String[] args) {
		try {
			write("Poisson01", 0.1, 1000, 1);
			write("Poisson05", 0.5, 1000, 1);
			write("Poisson10", 1.0, 1000, 1);
			write("Poisson15", 1.5, 1000, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
