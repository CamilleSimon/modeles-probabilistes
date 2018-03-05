package MPI.TP2.EX1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Binomial {
	
	/**
	 * Calcul la factorielle d'un nombre
	 * 
	 * @param i Le nombre dont on veut calculer la factorielle
	 * @return La factorielle du nombre passé en paramètre
	 * @throws Exception si i est inferieur à zéro
	 */
	private static int factorielle(int i) throws Exception{
		if(i < 0)
			throw new Exception("Erreur: On ne peut pas calculer la factorielle d'un nombre nÃ©gatif.");
		if(i == 0)
			return 1;
		return i * factorielle(i-1);
	}
	
	/**
	 * Calcul la combinaison de deux nombres
	 * 
	 * @param k Le premier paramètre de la combinaison
	 * @param n Le second paramètre de la combinaison
	 * @return La combinaison des nombres passés en paramètre
	 * @throws Exception Si n, k, ou (n-k) est inferieur à zéro
	 */
	private static double combinaison(int k, int n) throws Exception{
		return factorielle(n) / (factorielle(k) * factorielle(n - k));
	}	
	
	/**
	 * Calcul la combinaison de deux nombres de manière récursive
	 * 
	 * @param k Le premier paramètre de la combinaison
	 * @param n Le second paramètre de la combinaison
	 * @return La combinaison des nombres passés en paramètre
	 * @throws Exception Si n, k, ou (n-k) est inferieur à zéro
	 */
	private static double combinaison2(int k, int n) throws Exception{
		if(k < 0 || n < 0)
			throw new Exception("Erreur: k et n doivent être positifs.");
		if(k == 0 || k == n)
			return 1;
		else
			return (combinaison2(k, n - 1) + combinaison2(k - 1, n - 1));
	}
	
	/**
	 * Calcul la valeur de la loi Binomial
	 * 
	 * @param n Premier paramètre de la loi Binomial
	 * @param p Second paramètre de la loi Binomial
	 * @param k Troisième paramètre de la loi Binomial
	 * @return La valeur de la loi Binomial
	 * @throws Exception Si n, k, ou (n-k) est inferieur à zéro
	 */
	public static double compute(int n, double p, int k) throws Exception{
		double q = 1 - p;
		return combinaison2(k, n) * Math.pow(p, k) * Math.pow(q, n - k);
	}
	
	/**
	 * Génère un tableau contenant les valeur de la loi Binomial
	 * 
	 * @param n Premier paramètre de la loi Binomial
	 * @param p Second paramètre de la loi Binomial
	 * @param t Valeur jusqu'à laquelle l'on va faire varier le troisème de la loi Binomial
	 * @return Un tableau contenant le résultat de la loi Binomial avec comme troisème paramètre 0 à t
	 * @throws Exception Si n, t, ou (n-t) est inferieur à zéro
	 */
	private static double[] generate(int n, double p, int t) throws Exception{
		double[] g = new double[t];
		
		for (int k = 0; k < t; k++) {
			g[k] = compute(n, p, k);
		}
		
		return g;
	}
	
	/**
	 * Ecrit dans un fichier les valeurs généré
	 * 
	 * @param filename Nom du fichier dans lequel les valeurs seront inscrites
	 * @param n Premier paramètre de la loi Binomial
	 * @param p Second paramètre de la loi Binomial
	 * @param t Valeur jusqu'à laquelle l'on va faire varier le troisème de la loi Binomial
	 * @throws Exception En cas de problème lors de l'écriture du fichier
	 * @throws Exception Si n, t, ou (n-t) est inferieur à zéro
	 */
	public static void write(String filename, int n, double p, int t) throws Exception{
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		double[] g = generate(n, p, t);
		
		for(int i = 0; i < t; i++){
			writer.println(i + " " + g[i]);
		}
		
		writer.close();	
		
		System.out.println(System.getProperty("user.dir") + "/" + filename);
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(factorielle(3));
			System.out.println(combinaison(6,8));
			System.out.println(combinaison2(6,8));
			System.out.println(compute(40, 0.012, 2));
			
			for (int i = 1; i < 10; i++) {
				write("N10P" + 0.1 * i + "K11", 10, 0.1 * i , 11);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
