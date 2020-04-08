import java.util.Scanner;

public class Puissance4 {
	public static Scanner sc= new Scanner (System.in);
	public static void jeu(){
		int rejouer;
		int choix;
		
		//choix du nombre de joueurs
		do {
		System.out.println("Y'a t-il 1 ou 2 joueurs? (Taper 1 ou 2) ");
		choix= Integer.parseInt(sc.nextLine());
		}while(choix !=1 && choix != 2);
		do {
			if(choix == 2) {
				MéthodePuissance4.jeuA2();
			}
			else {
				MéthodePuissance4.jeuVsIA();
			}			
		// On demande si les joueurs veulent rejouer.
			do {
			System.out.println("Voulez-vous rejouer? (0 pour Non ou 1 pour Oui)");
			rejouer=Integer.parseInt(sc.nextLine());
			}while(rejouer != 0 && rejouer != 1);
			
		}while(rejouer == 1);
		
		System.out.println("A la prochaine fois!");
		sc.close();
	
	}
}
