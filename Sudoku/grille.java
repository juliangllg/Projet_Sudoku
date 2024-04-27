import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import javax.swing.border.Border;
import javax.swing.text.*;

public class grille extends JComponent{

	/*tableau de valeurs de la grille de sudoku*/
	public static int[][] grid_values = null; 

	/*fonction pour afficher graphiquement la grille*/
	public static void AfficherGrille (int[][] grille, boolean editable) {
		/*paramètre de base de la fenetre*/
		JFrame fenetre = new JFrame();
		fenetre.setSize(900, 950);
		/*fenetre.setResizable(false);*/
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
			for (int ligne = 0; ligne < 9; ligne++) {
		        for (int col = 0; col < 9; col++) {
		        	if (grille[ligne][col] == 0){
		        		case_editable[ligne][col] = new JTextField("", 1);
		        	}else{
			            case_editable[ligne][col] = new JTextField(String.valueOf(grille[ligne][col]), 1);
			        }
		            case_editable[ligne][col].setFont(new Font("Arial", Font.PLAIN, 30));
		            case_editable[ligne][col].setHorizontalAlignment(JTextField.CENTER);
		            if ((ligne % 3 == 0) && (ligne != 0) && (col % 3 == 0) && (col != 0)){
	            		case_editable[ligne][col].setBorder(BorderFactory.createMatteBorder(5,5,2,2,Color.BLACK));
		            } else if((ligne % 3 == 0) && (ligne != 0)){
		            	case_editable[ligne][col].setBorder(BorderFactory.createMatteBorder(5,2,2,2,Color.BLACK));
		            } else if ((col % 3 == 0) && (col != 0)){
		            	case_editable[ligne][col].setBorder(BorderFactory.createMatteBorder(2,5,2,2,Color.BLACK));
		            } else {
		            	case_editable[ligne][col].setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLACK));
		            }
		            place_grille.add(case_editable[ligne][col]);
				}
			}
 		        
		} else {

		    /*affichage de la grille*/
			JTextField[][] case_modifiable = null;
			JLabel[][] case_depart = null;
			case_depart = new JLabel[9][9];
			case_modifiable = new JTextField[9][9];

		    for (int ligne = 0; ligne < 9; ligne++) {
		        for (int col = 0; col < 9; col++) {
		            if ((grid_values[ligne][col]) == 0) {
			            case_modifiable[ligne][col] = new JTextField("", 1);
			            case_modifiable[ligne][col].setFont(new Font("Arial", Font.PLAIN, 30));
			            case_modifiable[ligne][col].setHorizontalAlignment(JTextField.CENTER);
			            if ((ligne % 3 == 0) && (ligne != 0)){
			            	case_modifiable[ligne][col].setBorder(BorderFactory.createMatteBorder(5,2,2,2,Color.BLACK));
			            } else if ((col % 3 == 0) && (col != 0)){
			            	case_modifiable[ligne][col].setBorder(BorderFactory.createMatteBorder(2,5,2,2,Color.BLACK));
			            } else if ((ligne % 3 == 0) && (ligne != 0) && (col % 3 == 0) && (col != 0)){
			            	case_modifiable[ligne][col].setBorder(BorderFactory.createMatteBorder(5,5,2,2,Color.BLACK));
			            }else {
			            	case_modifiable[ligne][col].setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLACK));
			            }
			            place_grille.add(case_modifiable[ligne][col]);
		            } else {
		            	case_depart[ligne][col] = new JLabel(String.valueOf(grid_values[ligne][col]));
		            	case_depart[ligne][col].setFont(new Font("Arial", Font.PLAIN, 30));
		            	case_depart[ligne][col].setHorizontalAlignment(JTextField.CENTER);
		            	if ((ligne % 3 == 0) && (ligne != 0)){
			            	case_depart[ligne][col].setBorder(BorderFactory.createMatteBorder(5,2,2,2,Color.BLACK));
			            } else if ((col % 3 == 0) && (col != 0)){
			            	case_depart[ligne][col].setBorder(BorderFactory.createMatteBorder(2,5,2,2,Color.BLACK));
			            } else if ((ligne % 3 == 0) && (ligne != 0) && (col % 3 == 0) && (col != 0)){
			            	case_depart[ligne][col].setBorder(BorderFactory.createMatteBorder(5,5,2,2,Color.BLACK));
			            } else {
			            	case_depart[ligne][col].setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLACK));
			            }
		            	place_grille.add(case_depart[ligne][col]);
		            }
		        }
		    }
		}

		/*bouton(s) grille(s)*/
		JButton verifier = null;
		JButton exporter = null;
		JPanel bouton_grille = new JPanel();

		if(editable){
			exporter = new JButton("exporter");
			bouton_grille.add(exporter);
			place_grille.add(bouton_grille);
		}else{
			verifier = new JButton("verifier");
			bouton_grille.add(verifier);
			place_grille.add(bouton_grille);
		}

		fenetre.add(bouton_grille,BorderLayout.SOUTH);
		fenetre.add(place_grille, BorderLayout.CENTER);

		/*affichage fenetre*/
		fenetre.setVisible(true);

		/*System.out.println(grid[0][0].getText());*/

		if (verifier != null) { // Vérification pour s'assurer que verifier a été initialisé
		    verifier.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent verifier) {
		            
		        }
		    });
		}
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

	public static void ExporterGrille(int[][] grille){

	}
}