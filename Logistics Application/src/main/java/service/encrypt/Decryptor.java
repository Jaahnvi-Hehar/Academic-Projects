package service.encrypt;

public class Decryptor extends AbstractConvertor {

    private String removePadding(String plaintext) {
        int i = plaintext.length() - 1;
        while (plaintext.charAt(i) == ENCRYPTION_SYMBOL) {
            i -= 1;
        }
        return plaintext.substring(0, (i + 1));
    }


    private char[][] createMatrix(int row, int column, String text) {
        char[][] transformedTextCharacters = new char[row][column];
        char[] textCharacters = text.toCharArray();
        int k = 0;
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                transformedTextCharacters[j][SECRET_KEY[i] - 1] = textCharacters[k++];
            }
        }
        return transformedTextCharacters;
    }


    private String rearrangeText(int row, int column, char[][] matrix) {
        StringBuilder transformedText = new StringBuilder();
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                transformedText.append(matrix[i][j]);
            }
        }
        return transformedText.toString();
    }


    @Override
    public String alterText(String text) {
        int row = text.length() / SECRET_KEY.length;
        int column = SECRET_KEY.length;
        char[][] matrix = createMatrix(row, column, text);
        String transformedText = rearrangeText(row, column, matrix);
        return removePadding(transformedText);
    }
}