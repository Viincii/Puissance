
public class MéthodePuissance4 {
	
	public final static char [][] grille = new char[6][7];
	
	/*
	 * Méthode pour le jeu à 2 joueurs
	 */
	public static void jeuA2() {
		
		boolean victoire1=false;
		boolean victoire2=false;
		boolean matchNul=false;
		boolean jouable;
		String reponse;
		char reponseC;
		int colonne;
		String nom1, nom2="";
		System.out.print("Quel est le nom du premier joueur? ");
		nom1 = Puissance4.sc.nextLine();
		System.out.print("Quel est le nom du second joueur? ");
		nom2 = Puissance4.sc.nextLine();
		
		MéthodePuissance4.remplirGrille(grille);
		do {
			jouable=false;
			do {
				System.out.println("Tour de "+nom1+ " : \nDans quelle colonne voulez-vous mettre un jeton? (De 1 à 7)");
				reponse=Puissance4.sc.nextLine();
				reponseC= reponse.charAt(0);
			}while(reponse.length()!=1 || reponseC!='1'&&reponseC!='2'&&reponseC!='3'&&reponseC!='4'&&reponseC!='5'&&reponseC!='6'&&reponseC!='7');
			
			colonne = Character.getNumericValue(reponseC)-1;
			//On vérifie que la colonne n'est pas pleine
			jouable= MéthodePuissance4.estJouable(grille, colonne);
			while (!jouable) {
				do {
					System.out.println("Tour de "+nom1+ "  : \nLa colonne est pleine choissisez en une autre : ");
					colonne = Integer.parseInt(Puissance4.sc.nextLine())-1;
				}while(colonne < 0 && colonne > 6);
				jouable= MéthodePuissance4.estJouable(grille, colonne);
			}
			//On joue et on test la victoire ou le nul
			MéthodePuissance4.mettreJeton(grille, colonne, 'R');
			MéthodePuissance4.afficherGrille(grille);
			victoire1=MéthodePuissance4.testVictoire(grille);
			// On vérifie le match nul
			matchNul=MéthodePuissance4.testNul(grille);
				
			if(!victoire1) {
				jouable=false;
				do {
					System.out.println("Tour de "+nom2+ " : \nDans quelle colonne voulez-vous mettre un jeton? (De 1 à 7)");
					colonne= Integer.parseInt(Puissance4.sc.nextLine())-1;
				}while(colonne < 0 && colonne > 6);
				//On vérifie que la colonne n'est pas pleine
				jouable= MéthodePuissance4.estJouable(grille, colonne);
				while (!jouable) {
					do {
						System.out.println("Tour de "+nom2+ "  : \nLa colonne est pleine choissisez en une autre : ");
						colonne = Integer.parseInt(Puissance4.sc.nextLine())-1;
					}while(colonne < 0 && colonne > 6);
					jouable= MéthodePuissance4.estJouable(grille, colonne);
				}
				//On joue et on test la victoire ou le nul
				MéthodePuissance4.mettreJeton(grille, colonne, 'J');
				MéthodePuissance4.afficherGrille(grille);
				victoire2=MéthodePuissance4.testVictoire(grille);
				// On vérifie le match nul
				matchNul=MéthodePuissance4.testNul(grille);
			}
		}while (!victoire1 && !victoire2 && !matchNul);
		System.out.println("La partie est finie!");
		if(victoire1)
			System.out.println(nom1+" a gagné, bravo!");
		else if(victoire2)
			System.out.println(nom2+" a gagné, bravo!");
		else if(matchNul)
			System.out.println("C'est un match nul!");	
		
	}
	/*
	 * Méthode pour le jeu vs IA
	 */
	public static void jeuVsIA() {
		boolean victoire1=false;
		boolean victoireOrdi=false;
		boolean matchNul=false;
		boolean jouable;
		String reponse;
		char reponseC;
		int colonne;
		int colonneIA;
		String nom;
		int difficulté;
		System.out.print("Quel est le nom du joueur? ");
		nom = Puissance4.sc.nextLine();
		do {
		System.out.println("De quel niveau doit être l'ordinateur (1 pour novice et 2 pour avancé) ?");
		difficulté= Integer.parseInt(Puissance4.sc.nextLine());
		}while(difficulté != 1 && difficulté != 2);
		
		MéthodePuissance4.remplirGrille(grille);
		do {
			jouable=false;
			do {
				do {
					System.out.println("Tour de "+nom+ " : \nDans quelle colonne voulez-vous mettre un jeton? (De 1 à 7)");
					reponse=Puissance4.sc.nextLine();
					reponseC= reponse.charAt(0);
				}while(reponse.length()!=1 || reponseC!='1'&&reponseC!='2'&&reponseC!='3'&&reponseC!='4'&&reponseC!='5'&&reponseC!='6'&&reponseC!='7');
			
			colonne = Character.getNumericValue(reponseC)-1;
			}while(colonne < 0 && colonne > 6);
			//On vérifie que la colonne n'est pas pleine
			jouable= MéthodePuissance4.estJouable(grille, colonne);
			while (!jouable) {
				do {
					System.out.println("Tour de "+nom+ "  : \nLa colonne est pleine choissisez en une autre : ");
					colonne = Integer.parseInt(Puissance4.sc.nextLine())-1;
				}while(colonne < 0 && colonne > 6);
				jouable= MéthodePuissance4.estJouable(grille, colonne);
			}
			//On joue et on test la victoire ou le nul
			MéthodePuissance4.mettreJeton(grille, colonne, 'R');
			MéthodePuissance4.afficherGrille(grille);
			victoire1=MéthodePuissance4.testVictoire(grille);
			// On vérifie le match nul
			matchNul=MéthodePuissance4.testNul(grille);
			
			if(!victoire1) {
				jouable=false;
				System.out.println("Tour de l'ordinateur : ");
				if(difficulté== 1)
					colonneIA = (int)(Math.random()*6);
				else {
					colonneIA = MéthodePuissance4.presqueVictoire(grille);
					if (colonneIA == -1) {
						do {
						colonneIA = MéthodePuissance4.choixColonneAvancé(colonne);
						}while (colonneIA < 0 || colonneIA > 6);
					}
				}
				//On vérifie que la colonne n'est pas pleine
				jouable= MéthodePuissance4.estJouable(grille, colonneIA);
				while (!jouable) {
					if(difficulté== 1)
						colonneIA = (int)(Math.random()*6);
					else {
						colonneIA = MéthodePuissance4.presqueVictoire(grille);
						if (colonneIA == -1) { //La méthode presqueVictoire renvoie -1 si elle ne détecte pas de cas de victoire
							do {
								colonneIA = MéthodePuissance4.choixColonneAvancé(colonne);
							}while (colonneIA < 0 || colonneIA > 6);
						}
					}
					jouable= MéthodePuissance4.estJouable(grille, colonneIA);
				}
				//On joue et on test la victoire ou le nul
				MéthodePuissance4.mettreJeton(grille, colonneIA, 'J');
				MéthodePuissance4.afficherGrille(grille);
				victoireOrdi=MéthodePuissance4.testVictoire(grille);
				// On vérifie le match nul
				matchNul=MéthodePuissance4.testNul(grille);
			}
		}while (!victoire1 && !matchNul && !victoireOrdi);
		System.out.println("La partie est finie!");
		if(victoire1)
			System.out.println(nom+" a gagné, bravo!");
		else if(victoireOrdi)
			System.out.println("L'ordinateur a gagné!");
		else if(matchNul)
			System.out.println("C'est un match nul!");
	}
	/*
	 * Methode pour remplir le tableau de .
	 */
	public static void remplirGrille(char [][] grille){
		for(int ligne=0; ligne< grille.length; ligne++)
			for(int colonne=0; colonne< grille[ligne].length; colonne++)
				grille[ligne][colonne]= '.';
	}	
	/*
	 * Méthode d'affichage du tableau
	 */
	public static void afficherGrille(char [][] grille) {
		System.out.println();
		for(int ligne=0; ligne< grille.length; ligne++) {
			System.out.print("\t");
			for(int colonne=0; colonne< grille[ligne].length; colonne++)
				System.out.print(grille[ligne][colonne]+" ");
			System.out.println();	
		}
		System.out.println();
	}
	/*
	 * Méthode qui insert le jeton d'un joueur
	 */
	public static void mettreJeton(char [][] grille, int colonne, char c) {
		boolean fait = false;
		int ligne = grille.length-1;
		do {
			if(grille[ligne][colonne] == '.') {
				grille[ligne][colonne] = c;
				fait = true;
			}
			ligne--;
		}while (ligne >= 0 && !fait);
	}
	/*
	 * Vérifie la jouabilité de la colonne
	 */
	public static boolean estJouable(char [][] grille, int a) {
		if (grille[0][a]== '.')
			return true;
		return false;
	}
	/*
	 * Méthode qui détermine s'il y a une victoire
	 */
	public static boolean testVictoire(char [][] grille) {
		int cpt =0;
		int i ;
		//Pour le test sur les lignes
		while(cpt<grille.length){
			i= grille[cpt].length-1;
			while (i>=3) {
				if(grille[cpt][i]!= '.' && grille[cpt][i] == grille[cpt][i-1] 
						&& grille[cpt][i] == grille[cpt][i-2] && 
						grille[cpt][i] == grille[cpt][i-3])
								return true;
				 i--;
			}
			cpt ++;
		}
		//Pour le test sur les colonnes
		cpt=0;
		
		while(cpt<grille[0].length){
			i= grille.length-1;
			while(i>=3) {
				if(grille[i][cpt]!= '.' && grille[i][cpt] == grille[i-1][cpt]
						&& grille[i][cpt] == grille[i-2][cpt] && 
						grille[i][cpt]==grille[i-3][cpt])
								return true;
				i--;
			}
			cpt++;
		}
		//Pour le test sur les diagonales droites
		int ligne = grille.length-1;
		int colonne;
		while(ligne>=3) {
			colonne=0;
			while (colonne <=grille[ligne].length-4) {
				if (grille[ligne][colonne]!= '.' && grille[ligne][colonne]==grille[ligne-1][colonne+1]
						&& grille[ligne][colonne]==grille[ligne-2][colonne+2] && grille[ligne][colonne] == grille[ligne-3][colonne+3])
								return true;
				colonne ++;
			}
			ligne--;	
		}
		
		//Pour le test sur les diagonales gauches
		ligne = grille.length-1;
		while(ligne>=3) {
			colonne=grille[ligne].length-1;
			while (colonne>=3) {
				if (grille[ligne][colonne]!= '.' && grille[ligne][colonne]==grille[ligne-1][colonne-1]
						&& grille[ligne][colonne]==grille[ligne-2][colonne-2] && grille[ligne][colonne] == grille[ligne-3][colonne-3])
								return true;
				 colonne --;
			}
			ligne--;	
		}
		
		return false;
	}
	/*
	 * Test si il y a match Nul (toutes les colonnes sont remplis
	 */
	public static boolean testNul(char [][] grille) {
		boolean nul=true;
		for (int colonne=0; colonne < grille[0].length;colonne++)
			if(grille[0][colonne]=='.')
				nul=false;
		return nul;
	}
	/*
	 * Méthode pour bloquer la victoire dans le cas de l'ordinateur avancé
	 */
	public static int presqueVictoire(char [][] grille) {
		int choixColonne = -1;
		int ligne =0;
		int colonne;
		//Pour le test sur les lignes
		while(ligne<grille.length){
			colonne = grille[ligne].length-1;
			while (colonne>=3) {
				if(grille[ligne][colonne]!= '.' && grille[ligne][colonne] == grille[ligne][colonne-1] 
						&& grille[ligne][colonne] == grille[ligne][colonne-2]) {
							if (grille[ligne][colonne-3]== '.') {
								choixColonne = colonne-3;
							}
							else{
								choixColonne = colonne+1;
							}
						}
				 colonne--;
			}
			ligne ++;
		}

		//Pour le test sur les colonnes
		ligne=3;
		
		while(ligne<grille.length){
			colonne= grille[ligne].length-1;
			while(colonne>=0) {
				if(grille[ligne][colonne]!= '.' && grille[ligne][colonne]== grille[ligne-1][colonne] 
						&& grille[ligne][colonne]== grille[ligne-1][colonne] && grille[ligne][colonne]== grille[ligne-2][colonne])
							if(grille[ligne-3][colonne]== '.')
								choixColonne =  colonne;
				colonne--;
			}
			ligne++;
		}
		
		//Pour le test sur les diagonales droites
		ligne = grille.length-1;
		colonne=0;
		while(ligne>=3) {
			colonne=0;
			while (colonne <=grille[ligne].length-4) {
				if (grille[ligne][colonne]!= '.' && grille[ligne][colonne] == grille[ligne-1][colonne+1]
						&& grille[ligne][colonne] == grille[ligne-2][colonne+2])
							if(grille[ligne-2][colonne+3]!= '.')
								choixColonne =  colonne + 3;
				
				colonne ++;
			}
			ligne--;	
		}
		
		//Pour le test sur les diagonales gauches
		ligne = grille.length-1;
		
		while(ligne>=3) {
			colonne=grille[ligne].length-1;
			while (colonne>=3) {
				if (grille[ligne][colonne] != '.' && grille[ligne][colonne] == grille[ligne-1][colonne-1]
						&& grille[ligne][colonne] == grille[ligne-2][colonne-2])
							if(grille[ligne-2][colonne-3]!='.')
								choixColonne = colonne-3;
				 colonne --;
			}
			ligne--;	
		}
		return choixColonne;
	}
	/*
	 * Méthode pour choissir en fonction du jeton du joueur
	 */
	public static int choixColonneAvancé(int a) {
		int aleatoire= (int)(Math.random()*4);
		switch (aleatoire) {
		case 1:
			return a + 1;
		case 2:
			return a + 3;
		case 3:
			return a - 2;
		case 4:
			return a - 4;
		}
		return a;
	}
	
	
	
	
	
}
