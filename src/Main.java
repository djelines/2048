import java.util.Scanner;

public class Main {

    /**
     * Method to create an N*N matrix
     *
     * @param N the size of the matrix
     * @return the created matrix
     */
    public static int[][] create(int N) {
        // Create matrix
        int[][] matrix = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                matrix[i][j] = 0;
            }
        }
        return matrix;
    }

    /**
     * Method to display the matrix
     * @param matrix the matrix to display
     */
    public static void displayMatrix(int[][] matrix) {
        // Display the matrix
        for (int i = 0; i < matrix.length; i++) {
            System.out.println();
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(" " + matrix[i][j]);
            }
        }
        System.out.println();
    }

    /**
     * Return matrix with 2 or 4
     * @param matrix
     */
    public static void start(int[][] matrix) {
        int x1 = -1, y1 = -1, x2 = -1, y2 =-1;
        while (x1 == x2 && y1 == y2){  // Ensure the positions are different{
            // Generate random positions for two distinct cells
            x1 = (int) (Math.random() * matrix.length);
            y1 = (int) (Math.random() * matrix.length);
            x2 = (int) (Math.random() * matrix.length);
            y2 = (int) (Math.random() * matrix.length);
        }

        // Randomly choose 2 or 4 with 80% for 2 and 20% for 4
        matrix[x1][y1] = Math.random() < 0.8 ? 2 : 4;
        matrix[x2][y2] = Math.random() < 0.2 ? 2 : 4;
    }

    public static void regen(int[][] matrix) {
        int x = -1, y = -1;
        boolean adam = false;
        while (!adam) {
            x = (int) (Math.random() * matrix.length);
            y = (int) (Math.random() * matrix.length);
            if (matrix[x][y] == 0) {
                matrix[x][y] = Math.random() < 0.8 ? 2 : 4;
                adam = true;
            }
        }


    }

    /**
     * Move the blocks on the matrix by Z Q S D
     * @param matrix
     */
    public static void move(int[][] matrix) {
        String inputPress;
        Scanner input = new Scanner(System.in);
        System.out.print("Z Q S D: ");  // Z=Haut, Q=Gauche, S=Bas, D=Droite
        inputPress = input.nextLine();

        switch (inputPress) {
            case "Z":  // Déplacer vers le haut
                // Parcours de chaque ligne de la matrice pour déplacer les valeurs
                for (int k = 0; k < 3; k++) {
                    for (int i = 3; i > 0; i--) {  // Commence à la ligne 1 pour éviter le bord
                        for (int j = 0; j < matrix[i].length; j++) {
                            if (matrix[i - 1][j] == 0) {  // Si la case au-dessus est vide
                                matrix[i - 1][j] = matrix[i][j];  // Déplacer la valeur
                                matrix[i][j] = 0;  // Vider la case actuelle
                            }else {
                                if (matrix[i - 1][j] == matrix[i][j]) {
                                    matrix[i - 1][j] = matrix[i][j] * 2;
                                    matrix[i][j] = 0;
                                }
                            }
                        }
                    }
                }
                break;

            case "Q":  // Déplacer vers la gauche
                for (int k = 0; k < 3; k++) {
                    for (int i = 3; i > -1 ; i--) {
                        for (int j = 3; j > 0 ; j--) {// Commence à la colonne 1 pour éviter le bord
                            if (matrix[i][j - 1] == 0) {  // Si la case à gauche est vide
                                matrix[i][j - 1] = matrix[i][j];  // Déplacer la valeur
                                matrix[i][j] = 0;  // Vider la case actuelle
                            }else {
                                if (matrix[i][j - 1] == matrix[i][j]) {
                                    matrix[i][j - 1] = matrix[i][j] * 2;
                                    matrix[i][j] = 0;
                                }
                            }
                        }
                    }
                }
                break;

            case "S":  // Déplacer vers le bas
                for (int k = 0; k < 3; k++) {
                    for (int i = 0; i < 3; i++) {  // Parcours inverse pour éviter les conflits
                        for (int j = 0; j < matrix[i].length; j++) {
                            if (matrix[i + 1][j]==0) {  // Si la case en dessous est vide
                                matrix[i + 1][j] = matrix[i][j];  // Déplacer la valeur
                                matrix[i][j] = 0;  // Vider la case actuelle
                            }else {
                                if (matrix[i + 1][j] == matrix[i][j]) {
                                    matrix[i + 1][j] = matrix[i][j] * 2;
                                    matrix[i][j] = 0;
                                }
                            }
                        }
                    }
                }

                break;

            case "D":  // Déplacer vers la droite
                for (int k = 0; k < 3; k++) {
                    for (int i = 0; i < 4 ; i++) {
                        for (int j = 0; j < 3 ; j++) {  // Parcours inverse pour éviter les conflits
                            if (matrix[i][j + 1] == 0) {  // Si la case à droite est vide
                                matrix[i][j + 1] = matrix[i][j];  // Déplacer la valeur
                                matrix[i][j] = 0;  // Vider la case actuelle
                            } else {
                                if (matrix[i][j + 1] == matrix[i][j]) {
                                    matrix[i][j + 1] = matrix[i][j] * 2;
                                    matrix[i][j] = 0;
                                }
                            }
                        }
                    }
                }
                break;

            default:
                System.out.println("Entrée non valide. Utilisez Z, Q, S, ou D.");
        }
    }

    public static void main(String[] args) {
        boolean continuer = true;
        boolean gameOver = false;
        int[][] matrix = create(4);
        start(matrix);
        displayMatrix(matrix);
        while (continuer){
            move(matrix);
            regen(matrix);
            displayMatrix(matrix);
        }

    }
}
