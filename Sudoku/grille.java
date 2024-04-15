import javax.swing.*;
import java.awt.*;
import java.io.*;
import javax.swing.border.LineBorder;

public class grille extends JComponent{

	/*tableau de valeurs de la grille de sudoku*/
	public static int[][] grid_values = null; 

	/*fonction pour afficher graphiquement la grille*/
	public static void AfficherGrille (int[][] grille, boolean editable) {
		/*paramètre de base de la fenetre*/
		JFrame fenetre = new JFrame();
		fenetre.setSize(900, 1100);
		fenetre.setResizable(false);
	    fenetre.setLocationRelativeTo(null);
	    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*Panneau pour la grille */
		JPanel place_grille = new JPanel();
		place_grille.setSize(900,900);
		
	    /*creation grille*/
	    GridLayout gestionnaire = new GridLayout(9,9,-2,-2);
		place_grille.setLayout(gestionnaire);

		if(editable){
			JTextField[][] case_editable = null;
			case_editable = new JTextField[9][9];
			for (int i = 0; i < 9; i++) {
		        for (int j = 0; j < 9; j++) {
		        	if (grille[i][j] == 0){
		        		case_editable[i][j] = new JTextField("", 1);
		        	}else{
			            case_editable[i][j] = new JTextField(String.valueOf(grille[i][j]), 1);
			        }
		            case_editable[i][j].setBorder(new LineBorder(Color.BLACK, 5));
		            case_editable[i][j].setFont(new Font("Arial", Font.PLAIN, 30));
		            case_editable[i][j].setHorizontalAlignment(JTextField.CENTER);
		            place_grille.add(case_editable[i][j]);
			    }
			}
		}else{

		    /*affichage de la grille*/
			JTextField[][] case_modifiable = null;
			JLabel[][] case_depart = null;
			case_depart = new JLabel[9][9];
			case_modifiable = new JTextField[9][9];

		    for (int i = 0; i < 9; i++) {
		        for (int j = 0; j < 9; j++) {
		            if ((grid_values[i][j]) == 0) {
			            case_modifiable[i][j] = new JTextField("", 1);
			            case_modifiable[i][j].setBorder(new LineBorder(Color.BLACK, 5));
			            case_modifiable[i][j].setFont(new Font("Arial", Font.PLAIN, 30));
			            case_modifiable[i][j].setHorizontalAlignment(JTextField.CENTER);
			            place_grille.add(case_modifiable[i][j]);
		            } else {
		            	case_depart[i][j] = new JLabel(String.valueOf(grid_values[i][j]));
		            	case_depart[i][j].setBorder(new LineBorder(Color.BLACK, 5));
		            	case_depart[i][j].setFont(new Font("Arial", Font.PLAIN, 30));
		            	case_depart[i][j].setHorizontalAlignment(JTextField.CENTER);
		            	place_grille.add(case_depart[i][j]);
		            }
		        }
		    }
		}
		JPanel bouton_grille = new JPanel();
		bouton_grille.setSize(900,200);
		JButton verifier = new JButton("verifier");
		bouton_grille.add(verifier);
		fenetre.add(bouton_grille,BorderLayout.SOUTH);
		/*affichage fenetre*/
		fenetre.setVisible(true);

		/*System.out.println(grid[0][0].getText());*/
	} 


	/*fonction pour passer d'un fichier.gri à un tableau de valeur*/
	public static int[][] ChargerGrille(String cheminFichier){
		try {
			FileInputStream fs = new FileInputStream(cheminFichier);
			DataInputStream fichier = new DataInputStream(fs);

			grid_values = new int[9][9];
			String string_values = "";
			int index = 0;

			for (int a = 0; a < 9 ; a++ ) {
				String ligne = String.valueOf(fichier.readInt());
				while (ligne.length() < 9){
					ligne = "0" + ligne;
				}
				string_values = string_values + ligne;
			}

	        for (int i = 0; i < 9; i++) {
	            for (int j = 0; j < 9; j++) {
	                grid_values[i][j] = Character.getNumericValue(string_values.charAt(index));
	                index++; 
	            }
	        }
	        try {
	        	fs.close();
	        	return grid_values;
	        }catch(IOException e){
	        	System.err.println("erreur fermeture du fichier");
	        }		
	    }catch(IOException e) {
			System.err.println("erreur ouverture du fichier");
		}

		return null;
	}
}