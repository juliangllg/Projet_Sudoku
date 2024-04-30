import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class menuProgramme1 {

    private static String cheminFichier = null;

    public static void menuProgramme1() {
        /*fenetre*/
        JFrame fenetre = new JFrame();
        fenetre.setSize(500, 200);
        fenetre.setTitle("Sudoku Creator (By Wilfried BRIGITTE & Julian GALLEGO)");
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout gestionnaire = new GridLayout(4,1);
        fenetre.setLayout(gestionnaire);

        /*composants G*/

        JLabel titre = new JLabel("Sudoku Creator");
        titre.setHorizontalAlignment(JLabel.CENTER);
        titre.setFont(new Font("Arial", Font.PLAIN, 25));
        fenetre.add(titre, BorderLayout.CENTER);

        JPanel ligne3 = new JPanel();
        JButton ouvrir = new JButton("Ouvrir");
        JLabel nomFichier = new JLabel("Fichier ouvert : ");
        ligne3.add(ouvrir);
        ligne3.add(nomFichier);
        fenetre.add(ligne3, BorderLayout.CENTER);

        JPanel ligne4 = new JPanel();
        JButton editerGrilleVierge = new JButton("Editer Grille Vierge");
        JButton editerGrilleSelectionner = new JButton("Editer Grille Selectionner");
        JButton quitter = new JButton("Quitter");
        ligne4.add(editerGrilleVierge);
        ligne4.add(editerGrilleSelectionner);
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

        editerGrilleVierge.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e4){
                int[][] grille_vide = null;
                grille_vide = new int[9][9];

                for (int i = 0; i < 9; i++) {
                    for (int j = 0; j < 9 ; j++ ) {
                        grille_vide[i][j] = 0;   
                    }
                }

                fenetre.dispose();
                grille.AfficherGrille(grille_vide, true, false, 0);
            }
        });

        editerGrilleSelectionner.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e4){
                if (cheminFichier == null){
                    InfoLogiciel.setText(" Info : Erreur : Veuillez selectionner un fichier .gri");
                    InfoLogiciel.setForeground(Color.RED);
                }else{
                    fenetre.dispose();
                    grille.AfficherGrille(grille.ChargerGrille(cheminFichier), true, false, 0);
                }
            }
        });    

        quitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e3) {
            	System.exit(0);
            }
        });
    }
}
