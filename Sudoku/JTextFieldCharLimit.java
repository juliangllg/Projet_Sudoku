import javax.swing.text.PlainDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class JTextFieldCharLimit extends PlainDocument 
{
   private int max;
   JTextFieldCharLimit(int max) {
      super();
      this.max = max;
   }
   public void insertString(int offset, String text, AttributeSet attr) throws BadLocationException {
      if (text == null){
         return;
      }
      StringBuilder sb = new StringBuilder();
      sb.append(getText(0, getLength())); 
      sb.insert(offset, text);

      /* Vérifier si le texte ne contient que des chiffres de 1 à 9 et si il ne depasse pas 4 caractères */
      if (sb.length() <= max && sb.toString().matches("[1-9]*")) {
         super.insertString(offset, text, attr);
      }
   }
}