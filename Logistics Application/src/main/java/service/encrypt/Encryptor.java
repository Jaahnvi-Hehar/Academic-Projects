package service.encrypt;

public class Encryptor extends AbstractConvertor {

    private String repeatCharacter(int count) {
        StringBuilder padding = new StringBuilder();
        for (int i = 0; i < count; i++) {
            padding.append(ENCRYPTION_SYMBOL);
        }
        return padding.toString();
    }


    private String addPadding(String plaintext) {
        int count;
        if (plaintext.length() % SECRET_KEY.length > 0) {
            count = SECRET_KEY.length - plaintext.length() % SECRET_KEY.length;
        } else {
            count = SECRET_KEY.length;
        }
        return plaintext + repeatCharacter(count);
    }


    private char[][] createMatrix(int row, int column, String text) {
        char[] paddedTextCharacters = text.toCharArray();
        char[][] transformedTextCharacters = new char[row][column];
        int k = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                transformedTextCharacters[i][j] = paddedTextCharacters[k++];
            }
        }
        return transformedTextCharacters;
    }

    private String rearrangeText(int row, int column, char[][] matrix) {
        StringBuilder transformedText = new StringBuilder();
        for (int i = 0; i < column; i++) {
            for (int j = 0; j < row; j++) {
                transformedText.append(matrix[j][SECRET_KEY[i] - 1]);
            }
        }
        return transformedText.toString();
    }

    @Override
    public String alterText(String text) {
        String paddedText = addPadding(text);
        int row = paddedText.length() / SECRET_KEY.length;
        int column = SECRET_KEY.length;
        char[][] matrix = createMatrix(row, column, paddedText);
        return rearrangeText(row, column, matrix);
    }
}
