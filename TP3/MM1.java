package MPI.TP3.EX1;

import java.io.PrintWriter;

import MPI.TP2.EX2.Exponentielle;

public class MM1 {
	
	/**
	 * Calcul de la loi exponentielle
	 * 
	 * @param lambda Paramètre lambda de la loi exponentielle
	 * @return Le résultat de la loi exponentielle de paramètre lambda et un nombre aléatoire
	 */
	public static double compute(double lambda){
		return Exponentielle.compute(lambda, Math.random());
	}
	
	/**
	 * Génère un tableau de valeur représentant l'état de la file d'attente
	 * 
	 * @param lambda Durée des instants d'arrivés
	 * @param mu Durée de service des clients
	 * @param d Periode sur laquella simulation a lieu
	 * @param fiabilite Nombre de répétitions du calcul (Une haute fiabilité permet un lissage des resultats, mais augmente de temps de calcul)
	 * @return Le tableau des valeurs générées
	 */
	public static double[] generate(double lambda, double mu, int d, int fiabilite){
		double[] table = new double[d+1];
		for (int i = 0; i < table.length; i++) {
			table[i] = 0;
		}
		
		double nextA; // Temps avant la prochaine arrivée
		double nextL; // Temps avant le prochain service
		
		for (int j = 0; j < fiabilite; j++) {
			int c = 0;
			nextA = compute(lambda);
			nextL = nextA + compute(mu);
			
			for (int i = 0; i < table.length; i++) {
				while(nextA < i || (nextL < i && c > 0)){
					if(nextL < i && c > 0){
						c--;
						nextL += compute(mu);
					}
					if(nextA < i){
						nextA += compute(lambda);
						if(c == 0)
							nextL = nextA + compute(mu);
						c++;
					}
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
	 * Ecrit les valeurs générés dans un fichier
	 * 
	 * @param filename Nom du fichier à générer
	 * @param lambda Durée des instants d'arrivés
	 * @param mu Durée de service des clients
	 * @param d Periode sur laquella simulation a lieu
	 * @param fiabilite Nombre de répétitions du calcul (Une haute fiabilité permet un lissage des resultats, mais augmente de temps de calcul)
	 * @throws Exception En cas de problème de lecture ou écriture dans le fichier
	 */
	public static void write(String filename, double lambda, double mu, int d, int fiabilite) throws Exception{
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		
		double[] table = generate(lambda, mu, d, fiabilite);
		
		for(int i = 0; i < table.length; i++){
			writer.println(i + " " + table[i]);
		}
		
		writer.close();	
		
		System.out.println("\"" + System.getProperty("user.dir") + "/" + filename + "\" t \"Lambda=" + lambda + "mu=" + mu + "\" with lines, ");
	}
	
	public static void main(String[] args) {
		try {
			write("MM10505", 0.5, 0.5, 1000, 100000);
			write("MM10510", 0.5, 1.0, 1000, 100000);
			write("MM10515", 0.5, 1.5, 1000, 100000);
			write("MM11005", 1.0, 0.5, 1000, 100000);
			write("MM11010", 1.0, 1.0, 1000, 100000);
			write("MM11015", 1.0, 1.5, 1000, 100000);
			write("MM11505", 1.5, 0.5, 1000, 100000);
			write("MM11510", 1.5, 1.0, 1000, 100000);
			write("MM11515", 1.5, 1.5, 1000, 100000);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
