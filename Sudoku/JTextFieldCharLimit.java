import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

/**
 * Cette classe limite le nombre de caractères à 4 et limite les caractères a des chiffres 
 * pour les JTextField
 * 
 * @author Julian GALLEGO
 * @author Wilfried BRIGITTE
 */
public class JTextFieldCharLimit extends PlainDocument 
{
   private int max;
   JTextFieldCharLimit(int max) {
      super();
      this.max = max;
   }

   /**
    * Vérifie si le texte répond aux exigences 
    * 
    * @param offset L'offset auquel insérer le texte.
    * @param text   La chaîne de texte à insérer.
    * @param attr   Les attributs à appliquer au texte inséré.
    * @throws BadLocationException Si l'offset est invalide.
    */
   public void insertString(int offset, String text, AttributeSet attr) throws BadLocationException {
      if (text == null){
         return;
      }
      StringBuilder sb = new StringBuilder();
      sb.append(getText(0, getLength())); 
      sb.insert(offset, text);

      // Vérifier si le texte ne contient que des chiffres de 1 à 9 et si il ne depasse pas 4 caractères 
      if (sb.length() <= max && sb.toString().matches("[1-9]*")) {
         super.insertString(offset, text, attr);
      }
   }
}