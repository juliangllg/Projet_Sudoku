import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

/**
 * Menu pour le Programme 2 (Résolveur de Sudoku).
 * 
 * @author Julian GALLEGO
 * @author Wilfried BRIGITTE
 */
public class menuProgramme2 {

    /**
     * designe le chemin du fichier
     */
    private static String cheminFichier = null;
    /**
     * permet de savoir si l'utilisateur veut resoudre le sudoku lui même 
     * ou si il veut voir la solution
     */
    private static boolean ResolutionManuel = true;

    /**
     * Affiche le menu du Programme 2.
     */
    public static void menuProgramme2() {
        //Création de la fenêtre
        JFrame fenetre = new JFrame();
        fenetre.setSize(500, 250);
        fenetre.setTitle("Sudoku Resolver (By Wilfried BRIGITTE & Julian GALLEGO)");
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout gestionnaire = new GridLayout(5,1);
        fenetre.setLayout(gestionnaire);

        //Composants graphique 
        JLabel titre = new JLabel("Sudoku Resolver");
        titre.setHorizontalAlignment(JLabel.CENTER);
        titre.setFont(new Font("Arial", Font.PLAIN, 25));
        fenetre.add(titre, BorderLayout.CENTER);

        JPanel ligne2 = new JPanel();
        ButtonGroup groupModeResolution = new ButtonGroup();
        JRadioButton manuel = new JRadioButton("manuel");
        JRadioButton auto = new JRadioButton("auto");
        groupModeResolution.add(manuel);
        groupModeResolution.add(auto);
        ligne2.add(manuel);
        ligne2.add(auto);
        manuel.setSelected(true);
        fenetre.add(ligne2, BorderLayout.CENTER);


        JPanel ligne3 = new JPanel();
        JButton ouvrir = new JButton("Ouvrir");
        JLabel nomFichier = new JLabel("Fichier ouvert : ");
        ligne3.add(ouvrir);
        ligne3.add(nomFichier);
        fenetre.add(ligne3, BorderLayout.CENTER);

        JPanel ligne4 = new JPanel();
        JButton lancer = new JButton("Lancer");
        JButton quitter = new JButton("Quitter");
        ligne4.add(lancer);
        ligne4.add(quitter);
        fenetre.add(ligne4, BorderLayout.CENTER);

        JLabel InfoLogiciel = new JLabel(" Info : Tout est normal");
        InfoLogiciel.setHorizontalAlignment(JLabel.LEFT);
        InfoLogiciel.setFont(new Font("Arial", Font.PLAIN, 11));
        fenetre.add(InfoLogiciel);

        //Affichage de la fenêtre
        fenetre.setVisible(true);

        /*evenements*/
        ouvrir.addActionListener(new ActionListener() {
            /**
             * Ouvre un sélecteur de fichiers pour choisir un fichier de grille.
             *
             * @param e L'événement d'action.
             */
            public void actionPerformed(ActionEvent e) {
                try {
                    JFileChooser filechooser = new JFileChooser();
                    filechooser.setCurrentDirectory(new File("./grille"));
                    int result = filechooser.showOpenDialog(null);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        File selectedFile = filechooser.getSelectedFile();
                        nomFichier.setText("Fichier ouvert : " + selectedFile.getName());
                        cheminFichier = selectedFile.getAbsolutePath();
                        InfoLogiciel.setText(" Info : Tout est normal");
                		InfoLogiciel.setForeground(Color.BLACK);
                    }
                } catch (Exception ex) {
                    System.err.println("Erreur selection du fichier");
                }
            }
        });

        lancer.addActionListener(new ActionListener() {
             /**
             * Lance la résolution du Sudoku en fonction du mode choisi.
             *
             * @param e2 L'événement d'action.
             */
            public void actionPerformed(ActionEvent e2) {
            	if(cheminFichier != null){
                    if (ResolutionManuel == true) {
	            	   fenetre.dispose();
                       Grille.AfficherGrille(Grille.ChargerGrille(cheminFichier),false, true, 0);
                    } else {
                        fenetre.dispose();
                        long debut = System.nanoTime();
                        int[][] grille_resolue = new int[9][9];
                        grille_resolue = resolveurGrille.resoudreGrille(Grille.ChargerGrille(cheminFichier));
                        long fin = System.nanoTime();
                        long duree = fin - debut;
                        Grille.AfficherGrille(grille_resolue,false, false, duree);
                    }
                } else {
                	InfoLogiciel.setText(" Info : Erreur : Veuillez selectionner un fichier .gri");
                	InfoLogiciel.setForeground(Color.RED);
                }
            }
        });

        quitter.addActionListener(new ActionListener() {
             /**
             * Ferme l'application.
             *
             * @param e3 L'événement d'action.
             */
            public void actionPerformed(ActionEvent e3) {
            	System.exit(0);
            }
        });

        manuel.addActionListener(new ActionListener() {
             /**
             * Définit le mode de résolution manuel.
             *
             * @param e1 L'événement d'action.
             */
            @Override
            public void actionPerformed(ActionEvent e1) {
                ResolutionManuel = true;
            }
        });
        
        auto.addActionListener(new ActionListener() {
             /**
             * Définit le mode de résolution automatique.
             *
             * @param e L'événement d'action.
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                ResolutionManuel = false;
            }
        });
    }
}
