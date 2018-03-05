package MPI.TP2.EX1;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class Binomial {
	
	/**
	 * Calcul la factorielle d'un nombre
	 * 
	 * @param i Le nombre dont on veut calculer la factorielle
	 * @return La factorielle du nombre pass� en param�tre
	 * @throws Exception si i est inferieur � z�ro
	 */
	private static int factorielle(int i) throws Exception{
		if(i < 0)
			throw new Exception("Erreur: On ne peut pas calculer la factorielle d'un nombre négatif.");
		if(i == 0)
			return 1;
		return i * factorielle(i-1);
	}
	
	/**
	 * Calcul la combinaison de deux nombres
	 * 
	 * @param k Le premier param�tre de la combinaison
	 * @param n Le second param�tre de la combinaison
	 * @return La combinaison des nombres pass�s en param�tre
	 * @throws Exception Si n, k, ou (n-k) est inferieur � z�ro
	 */
	private static double combinaison(int k, int n) throws Exception{
		return factorielle(n) / (factorielle(k) * factorielle(n - k));
	}	
	
	/**
	 * Calcul la combinaison de deux nombres de mani�re r�cursive
	 * 
	 * @param k Le premier param�tre de la combinaison
	 * @param n Le second param�tre de la combinaison
	 * @return La combinaison des nombres pass�s en param�tre
	 * @throws Exception Si n, k, ou (n-k) est inferieur � z�ro
	 */
	private static double combinaison2(int k, int n) throws Exception{
		if(k < 0 || n < 0)
			throw new Exception("Erreur: k et n doivent �tre positifs.");
		if(k == 0 || k == n)
			return 1;
		else
			return (combinaison2(k, n - 1) + combinaison2(k - 1, n - 1));
	}
	
	/**
	 * Calcul la valeur de la loi Binomial
	 * 
	 * @param n Premier param�tre de la loi Binomial
	 * @param p Second param�tre de la loi Binomial
	 * @param k Troisi�me param�tre de la loi Binomial
	 * @return La valeur de la loi Binomial
	 * @throws Exception Si n, k, ou (n-k) est inferieur � z�ro
	 */
	public static double compute(int n, double p, int k) throws Exception{
		double q = 1 - p;
		return combinaison2(k, n) * Math.pow(p, k) * Math.pow(q, n - k);
	}
	
	/**
	 * G�n�re un tableau contenant les valeur de la loi Binomial
	 * 
	 * @param n Premier param�tre de la loi Binomial
	 * @param p Second param�tre de la loi Binomial
	 * @param t Valeur jusqu'� laquelle l'on va faire varier le trois�me de la loi Binomial
	 * @return Un tableau contenant le r�sultat de la loi Binomial avec comme trois�me param�tre 0 � t
	 * @throws Exception Si n, t, ou (n-t) est inferieur � z�ro
	 */
	private static double[] generate(int n, double p, int t) throws Exception{
		double[] g = new double[t];
		
		for (int k = 0; k < t; k++) {
			g[k] = compute(n, p, k);
		}
		
		return g;
	}
	
	/**
	 * Ecrit dans un fichier les valeurs g�n�r�
	 * 
	 * @param filename Nom du fichier dans lequel les valeurs seront inscrites
	 * @param n Premier param�tre de la loi Binomial
	 * @param p Second param�tre de la loi Binomial
	 * @param t Valeur jusqu'� laquelle l'on va faire varier le trois�me de la loi Binomial
	 * @throws Exception En cas de probl�me lors de l'�criture du fichier
	 * @throws Exception Si n, t, ou (n-t) est inferieur � z�ro
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
