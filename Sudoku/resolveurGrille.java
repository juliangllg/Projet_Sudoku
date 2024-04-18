public class resolveurGrille {

    private static int[][] solution = new int[9][9];
    private static boolean stop = false;

    public static int[][] resoudreGrille(int[][] grille) {
        /*remise à zéro de la solution*/
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                solution[i][j] = 0;
            }
        }

        /* Réinitialiser la valeur de stop à chaque début de résolution*/
        stop = false;
        resoudreSudoku(grille);
        return solution;
    }

    private static boolean resoudreSudoku(int[][] grille) {
        /*parcourage la grille*/
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (grille[row][col] == 0) {
                    /*essayer les chiffres de 1 a 9 pour la case vide*/
                    for (int num = 1; num <= 9; num++) {
                        if (isValid(grille, row, col, num)) {
                            grille[row][col] = num;
                            resoudreSudoku(grille);
                            if (stop) {
                                return true;
                            }
                            grille[row][col] = 0; /*réinitialiser la case si la solution n'est pas trouvée*/
                        }
                    }
                    return false; /* Si aucune solution trouvée à cette étape on s'arrêtee*/
                }
            }
        }

        /*copier la grille résolue dans la solution*/
        for (int i = 0; i < 9; i++) {
            System.arraycopy(grille[i], 0, solution[i], 0, 9);
        }
        return stop = true;
    }

    /*Méthode pour vérifier la validité d'un chiffre dans une case donnée*/
    private static boolean isValid(int[][] grille, int row, int col, int num) {
        /*Vérifier la ligne*/
        for (int x = 0; x < 9; x++) {
            if (grille[row][x] == num) {
                return false;
            }
        }

        /*Vérifier la colonne*/
        for (int x = 0; x < 9; x++) {
            if (grille[x][col] == num) {
                return false;
            }
        }

        /*Vérifier la sous-grille 3x3*/
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
