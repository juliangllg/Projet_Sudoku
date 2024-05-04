import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusAdapter;
import java.io.*;


/**
 * La classe Grille est une composante Swing pour représenter une grille de Sudoku.
 *
 * @author Julian GALLEGO
 * @author Wilfried BRIGITTE 
 */
public class Grille extends JComponent {
    private static JLabel etat_exportation = new JLabel();

    /**
     * Tableau des valeurs de la grille de Sudoku.
     */
    public static int[][] grid_values = null;

    /**
     * Panneau pour la grille.
     */
    public static JPanel place_grille = new JPanel();

    /**
     * Fenêtre de l'application.
     */
    public static JFrame fenetre = new JFrame();

    /**
     * Affiche graphiquement la grille de Sudoku.
     *
     * @param grille           La grille de Sudoku à afficher.
     * @param editable         Indique si la grille est éditable.
     * @param resolutionManuel Indique si la résolution est manuelle.
     * @param duree            La durée de résolution (en nanosecondes).
     */
	public static void AfficherGrille (int[][] grille, boolean editable, boolean resolutionManuel, long duree) {
		//paramètre de base de la fenetre
		fenetre.setSize(900, 950);
		fenetre.setResizable(false);
	    fenetre.setLocationRelativeTo(null);
	    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		place_grille.setSize(900,900);
		
	    //creation grille
	    GridLayout gestionnaire = new GridLayout(9,9,-2,-2);
		place_grille.setLayout(gestionnaire);

		JTextField[][] case_editable = null;
		case_editable = new JTextField[9][9];
		//si la grille peut etre entierement éditée (dans le cas du programme1
		if(editable){

			for (int ligne = 0; ligne < 9; ligne++) {
		        for (int col = 0; col < 9; col++) {
		        	if (grille[ligne][col] == 0){
		        		case_editable[ligne][col] = new JTextField("", 1);
		        		case_editable[ligne][col].setDocument(new JTextFieldCharLimit(4));
		        	}else{
			            case_editable[ligne][col] = new JTextField(1);
			            case_editable[ligne][col].setDocument(new JTextFieldCharLimit(4));
			            case_editable[ligne][col].setText(String.valueOf(grille[ligne][col]));
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

			JLabel[][] case_depart = null;
			case_depart = new JLabel[9][9];
			
		    for (int ligne = 0; ligne < 9; ligne++) {
		        for (int col = 0; col < 9; col++) {
		            if ((grid_values[ligne][col]) == 0) {
			            case_editable[ligne][col] = new JTextField("", 1);
			            case_editable[ligne][col].setDocument(new JTextFieldCharLimit(4));
			            case_editable[ligne][col].setFont(new Font("Arial", Font.PLAIN, 30));
			            case_editable[ligne][col].setHorizontalAlignment(JTextField.CENTER);
			            if ((ligne % 3 == 0) && (ligne != 0) && (col % 3 == 0) && (col != 0)){
			            	case_editable[ligne][col].setBorder(BorderFactory.createMatteBorder(5,5,2,2,Color.BLACK));
			            } else if ((col % 3 == 0) && (col != 0)){
			            	case_editable[ligne][col].setBorder(BorderFactory.createMatteBorder(2,5,2,2,Color.BLACK));
			            } else if ((ligne % 3 == 0) && (ligne != 0)){
			            	case_editable[ligne][col].setBorder(BorderFactory.createMatteBorder(5,2,2,2,Color.BLACK));
			            }else {
			            	case_editable[ligne][col].setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLACK));
			            }
			            place_grille.add(case_editable[ligne][col]);
		            } else {
		            	case_depart[ligne][col] = new JLabel(String.valueOf(grid_values[ligne][col]));
		            	case_depart[ligne][col].setFont(new Font("Arial", Font.PLAIN, 30));
		            	case_depart[ligne][col].setHorizontalAlignment(JTextField.CENTER);
		            	if ((ligne % 3 == 0) && (ligne != 0) && (col % 3 == 0) && (col != 0)){
			            	case_depart[ligne][col].setBorder(BorderFactory.createMatteBorder(5,5,2,2,Color.BLACK));
			            } else if ((col % 3 == 0) && (col != 0)){
			            	case_depart[ligne][col].setBorder(BorderFactory.createMatteBorder(2,5,2,2,Color.BLACK));
			            } else if ((ligne % 3 == 0) && (ligne != 0)){
			            	case_depart[ligne][col].setBorder(BorderFactory.createMatteBorder(5,2,2,2,Color.BLACK));
			            } else {
			            	case_depart[ligne][col].setBorder(BorderFactory.createMatteBorder(2,2,2,2,Color.BLACK));
			            }
		            	place_grille.add(case_depart[ligne][col]);
		            }
		        }
		    }
		}

		//bouton(s) grille(s)
		JButton verifier = null;
		JButton exporter = null;
		JPanel bouton_grille = new JPanel();

		//affichage des boutons en fonction du programme lancé
		if(editable){
			bouton_grille.add(etat_exportation);
			exporter = new JButton("exporter");
			bouton_grille.add(exporter);
			place_grille.add(bouton_grille);
		}else{
			if(resolutionManuel){
				verifier = new JButton("verifier");
				bouton_grille.add(verifier);
				place_grille.add(bouton_grille);
			}else {
				JLabel texteTemps = new JLabel("Le programme a mit "+duree+" nanoSecondes pour resoudre la grille");
				bouton_grille.add(texteTemps);
			}
		}

		fenetre.add(bouton_grille,BorderLayout.SOUTH);
		fenetre.add(place_grille, BorderLayout.CENTER);

		//affichage fenetre
		fenetre.setVisible(true);

		//verification si un chiffre peut être placé à un endroit
		for (int ligne = 0; ligne < 9; ligne++) {
		    for (int col = 0; col < 9; col++) {
		        final int finalLigne = ligne;
		        final int finalCol = col; 

		        if (case_editable[ligne][col] != null) {
		            JTextField textField = case_editable[ligne][col];
		            textField.addFocusListener(new FocusAdapter() {
		                @Override
		                public void focusLost(FocusEvent e) {
		                	VerificationGrilleFini();
		                    int[][] currentGrid = GrilleActuelle();
		                    String input = textField.getText().trim();
		                    if (!input.isEmpty()) {
		                        int newValue = Integer.parseInt(input);
		                        textField.setText("");
		                        currentGrid = GrilleActuelle();
		                        if (resolveurGrille.isValid(currentGrid, finalLigne, finalCol, newValue)) {
		                            // Mettre à jour la grille actuelle avec le nouveau chiffre
		                            currentGrid[finalLigne][finalCol] = newValue;
		                            textField.setText(Integer.toString(newValue));
		                        } else {
		                            // Le chiffre n'est pas valide, réinitialiser le champ
		                            textField.setText("");
		                        }
		                    }
		                }
		            });
		        }
		    }
		}


		//événement des boutons
		if (verifier != null) { // Vérification pour s'assurer que verifier a été initialisé 
		    verifier.addActionListener(new ActionListener() {
				/**
				 * verifie votre solution en appuyant sur le bouton verifier
				 * 
				 * @param verifier L'évènement d'action.
				 */
		        public void actionPerformed(ActionEvent verifier) {
		           	VerificationGrilleFini(); 
		        }
		    });
		}

		if (exporter != null) { // Vérification pour s'assurer que exporter a été initialisé 
		    exporter.addActionListener(new ActionListener() {
				/**
				 * permet d'exporter votre grille édité.
				 * 
				 * @param exporeter
				 */
		        public void actionPerformed(ActionEvent exporter) {        	
		        	if (!(resolveurGrille.resoudreSudoku(GrilleActuelle()))){
		        		etat_exportation.setHorizontalAlignment(SwingConstants.LEFT);
		        		etat_exportation.setText("Sudoku Impossible.");
		        		etat_exportation.setForeground(Color.RED);
		        	} else {
		            	ExporterGrille(GrilleActuelle());
		            	etat_exportation.setText("");
		        	}
		        }
		    });
		}
	} 


	/**
	 * Charge une grille à partir d'un fichier.
	 *
	 * @param cheminFichier Le chemin du fichier contenant la grille.
	 * @return Un tableau représentant la grille.
	 */
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

	/**
	 * Exporte une grille vers un fichier.
	 *
	 * @param grille La grille à exporter.
	 */
	public static void ExporterGrille(int[][] grille){

		try {
            JFileChooser filechooser2 = new JFileChooser();
            filechooser2.setCurrentDirectory(new File("./grille"));
            int result2 = filechooser2.showOpenDialog(null);
            if (result2 == JFileChooser.APPROVE_OPTION) {
            	File selectedFile2 = filechooser2.getSelectedFile();
            	FileOutputStream fs2 = new FileOutputStream(selectedFile2.getAbsolutePath());
				DataOutputStream fichier2 = new DataOutputStream(fs2);

				String ligne_a_ecrire = "";
				int entier_a_ecrire = 0;
				for (int i = 0; i < 9; i++) {
					for (int j = 0; j < 9; j++){
						ligne_a_ecrire = ligne_a_ecrire+String.valueOf(grille[i][j]);
					}
                    entier_a_ecrire = Integer.parseInt(ligne_a_ecrire);
	            	fichier2.writeInt(entier_a_ecrire);
	            	ligne_a_ecrire = "";
            	}

            	try {
            		fs2.close();
            	}catch(IOException e){
	        		System.err.println("erreur fermeture du fichier");
	        	}	
            }
		}catch(IOException e) {
            System.err.println("Erreur ouverture du fichier");
		}
	}

	/**
	 * Récupère les valeurs actuelles de la grille et les place dans un tableau.
	 *
	 * @return Un tableau représentant la grille actuelle.
	 */
	public static int[][] GrilleActuelle(){
		int[][] grilleActuelle = new int[9][9];

	    for (Component comp : place_grille.getComponents()) {
	        if (comp instanceof JTextField) {
	            JTextField textField = (JTextField) comp;
	            String text = textField.getText().trim();
	            int value = text.isEmpty() ? 0 : Integer.parseInt(text); 
	            grilleActuelle[place_grille.getComponentZOrder(comp) / 9][place_grille.getComponentZOrder(comp) % 9] = value;
	        } else if (comp instanceof JLabel) {
	            JLabel label = (JLabel) comp;
	            String text = label.getText().trim();
	            int value = Integer.parseInt(text);
	            grilleActuelle[place_grille.getComponentZOrder(comp) / 9][place_grille.getComponentZOrder(comp) % 9] = value;
	        }
	    }

	    return grilleActuelle;
	}

	/**
	 * Vérifie si la grille actuelle correspond à la grille résolue.
	 *
	 * @return true si la grille est résolue correctement, sinon false.
	 */
	public static boolean VerificationGrilleFini(){
		int[][] soluce_de_la_grille = new int[9][9];
		soluce_de_la_grille = resolveurGrille.resoudreGrille(grid_values);
		int[][] gActuelle = GrilleActuelle();
		for ( int ligne = 0; ligne<9; ligne ++){
			for (int col = 0; col <9; col++){
				if(soluce_de_la_grille[ligne][col] != gActuelle[ligne][col]){
					return false;
				}
			}
		} 
		JeuFini.JeuFini();
		fenetre.dispose();
		return true;
	}
}