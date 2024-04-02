import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class menu {

    private static String cheminFichier = null;
    private static boolean ResolutionManuel = true;

    public static void menuFenetre() {
        /*fenetre*/
        JFrame fenetre = new JFrame();
        fenetre.setSize(500, 250);
        fenetre.setTitle("Sudoku Resolver (By Wilfried BRIGITTE)");
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout gestionnaire = new GridLayout(5,1);
        fenetre.setLayout(gestionnaire);

        /*composants G*/

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

        /*affichage*/
        fenetre.setVisible(true);

        /*evenements*/
        ouvrir.addActionListener(new ActionListener() {
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
            public void actionPerformed(ActionEvent e2) {
            	if(cheminFichier != null){
                    if (ResolutionManuel == true) {
	            	   fenetre.dispose();
                       grille.AfficherGrille(grille.ChargerGrille(cheminFichier));
                    } else {
                        grille.AfficherGrille(resolveurGrille.resoudreGrille(grille.ChargerGrille(cheminFichier)));
                    }
                } else {
                	InfoLogiciel.setText(" Info : Erreur : Veuillez selectionner un fichier .gri");
                	InfoLogiciel.setForeground(Color.RED);
                }
            }
        });

        quitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e3) {
            	System.exit(0);
            }
        });

        manuel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResolutionManuel = true;
            }
        });
        
        auto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResolutionManuel = false;
            }
        });
    }
}
