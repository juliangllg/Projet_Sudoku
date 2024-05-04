import java.util.Arrays;

/**
 * Classe pour résoudre une grille de Sudoku.
 * 
 * @author Julian GALLEGO
 * @author Wilfried BRIGITTE
 */
public class resolveurGrille {

    private static int[][] solution = new int[9][9];
    private static boolean stop = false;

    /**
     * Résout une grille de Sudoku.
     *
     * @param grille La grille à résoudre.
     * @return La grille résolue.
     */
    public static int[][] resoudreGrille(int[][] grille) {
        // Remise à zéro de la solution
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                solution[i][j] = 0;
            }
        }

        // Réinitialiser la valeur de stop à chaque début de résolution
        resoudreSudoku(grille);
        return solution;
    }

    /**
     * Résout une grille de Sudoku.
     *
     * @param grille La grille à résoudre.
     * @return true si une solution a été trouvée, sinon false.
     */
    public static boolean resoudreSudoku(int[][] grille) {
        //Parcours la grille
        stop = false;
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grille[row][col] == 0) {
                    //essayer les chiffres de 1 a 9 pour la case vide
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(grille, row, col, num)) {
                            grille[row][col] = num;
                            resoudreSudoku(grille);
                            if (stop) {
                                return true;
                            }
                            grille[row][col] = 0; //réinitialiser la case si la solution n'est pas trouvée
                        }
                    }
                    return false; //Si aucune solution trouvée à cette étape on s'arrête
                }
            }
        }

        //copier l a grille résolue dans la solution
        for (int i = 0; i < 9; i++) {
            System.arraycopy(grille[i], 0, solution[i], 0, 9);
        }
        return stop = true;
    }

    /**
     * Vérifie si un chiffre est valide dans une case donnée.
     *
     * @param grille La grille de Sudoku.
     * @param row    L'indice de ligne de la case.
     * @param col    L'indice de colonne de la case.
     * @param num    Le chiffre à vérifier.
     * @return true si le chiffre est valide, sinon false.
     */
    public static boolean isValid(int[][] grille, int row, int col, int num) {
        //Vérifier la ligne
        for (int x = 0; x < 9; x++) {
            if (grille[row][x] == num) {                       
                    return false;
            }
        }
        //Vérifier la colonne
        for (int x = 0; x < 9; x++) {
            if (grille[x][col] == num) {
                return false;
            }
        }

        //Vérifier la sous-grille 3x3
        int startRow = row - row % 3;
        int startCol = col - col % 3;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (grille[i + startRow][j + startCol] == num) {
                    return false;
                }
            }
        }
        return true;
    }
}
