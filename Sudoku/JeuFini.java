import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * La classe JeuFini affiche une fenêtre de fin de jeu pour un Sudoku résolu.
 * 
 * @author Julian GALLEGO
 * @author Wilfried BRIGITTE
 */
public class JeuFini {
	// Créer une nouvelle fenêtre
	public static void JeuFini(){
		JFrame fenetre = new JFrame();
        fenetre.setSize(500, 200);
        fenetre.setTitle("End Game ! (By Wilfried BRIGITTE & Julian GALLEGO)");
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Utiliser un gestionnaire de disposition pour organiser les composants
        GridLayout gestionnaire = new GridLayout(2,1);
        fenetre.setLayout(gestionnaire);

        // Ajouter un texte à la fenêtre
        JLabel titre = new JLabel("Bravo vous avez résolu le Sudoku");
        titre.setHorizontalAlignment(JLabel.CENTER);
        titre.setFont(new Font("Arial", Font.PLAIN, 30));
        fenetre.add(titre, BorderLayout.CENTER);

        // Ajouter un bouton "Quitter"
        JPanel ligne2 = new JPanel();
        JButton quitter = new JButton("Quitter");
        ligne2.add(quitter);
        fenetre.add(ligne2, BorderLayout.CENTER);

        // Rendre la fenêtre visible
        fenetre.setVisible(true);

        // Définir un évènement pour le bouton "Quitter"
        quitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Quitter l'application lorsque le bouton "Quitter" est cliqué
            	System.exit(0);
            }
        });
	}
}