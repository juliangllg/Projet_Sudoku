import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class JeuFini {
	/*afficher la fenetre de fin*/
	public static void JeuFini(){
		JFrame fenetre = new JFrame();
        fenetre.setSize(500, 200);
        fenetre.setTitle("End Game ! (By Wilfried BRIGITTE & Julian GALLEGO)");
        fenetre.setResizable(false);
        fenetre.setLocationRelativeTo(null);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GridLayout gestionnaire = new GridLayout(2,1);
        fenetre.setLayout(gestionnaire);

        JLabel titre = new JLabel("Bravo vous avez résolu le Sudoku");
        titre.setHorizontalAlignment(JLabel.CENTER);
        titre.setFont(new Font("Arial", Font.PLAIN, 30));
        fenetre.add(titre, BorderLayout.CENTER);

        JPanel ligne2 = new JPanel();
        JButton quitter = new JButton("Quitter");
        ligne2.add(quitter);
        fenetre.add(ligne2, BorderLayout.CENTER);

        fenetre.setVisible(true);

        quitter.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.exit(0);
            }
        });
	}
}