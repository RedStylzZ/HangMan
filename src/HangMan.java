import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class HangMan {

    // Game Buildup ---------------------------------------------------
    public static void printArr(char[] arr) {
        for (char x : arr) {
            System.out.print(x + " ");
        }
        System.out.println();
    }

    public static void fillCharArray(char[] arr) {
        Arrays.fill(arr, '_');
    }

    // ----------------------------------------------------------------
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static String input(Scanner s) {
        String guessedChar = null;

        while (guessedChar == null || guessedChar.length() > 1 || guessedChar.equals(" ") || guessedChar.equals("")) {
            clearScreen();
            System.out.println("Type in a SINGLE character");
            System.out.print("Guess char: ");
            guessedChar = s.nextLine().toUpperCase();
        }
        return guessedChar;
    }

    public static boolean checkInput(String guessWord, String word) {
        return word.contains(guessWord);
    }

    public static void addCharToArray(String word, String guess, char[] arr) {
        char[] wordArr = word.toCharArray();
        char[] guessArr = guess.toCharArray();
        for (int x = 0; x < wordArr.length; x++) {
            if (guessArr[0] == wordArr[x]) {
                arr[x] = wordArr[x];
            }
        }
    }

    public static void addMissedChar(char[] missedArr, String guess) {
        char[] guessArr = guess.toCharArray();

        for (int x = 0; x < missedArr.length; x++) {
            if (missedArr[x] == '_') {
                missedArr[x] = guessArr[0];
                break;
            }
        }
    }

//    public static void checkFinished(char[] arr) {
//        for (char x : arr) {
//            if
//        }
//    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String word = "PFANNKUCHEN";
        int remainingFails = 5;
        char[] guessWord = new char[word.length()];
        char[] missedChar = new char[remainingFails];

        fillCharArray(guessWord);
        fillCharArray(missedChar);

        while (remainingFails > 0 && Arrays.toString(guessWord).contains("_")) {
            clearScreen();
            printArr(guessWord);
            printArr(missedChar);

            String guessedChar = input(scanner);
            if (checkInput(guessedChar, word)) {
                System.out.println("That was right!");
                System.out.println("Press ENTER");
//                scanner.nextLine();
                addCharToArray(word, guessedChar, guessWord);
            } else {
                remainingFails--;
                System.out.println("No sorry, try again");
                System.out.println(remainingFails + " trys remaining");
                System.out.println("Press ENTER");
                scanner.nextLine();
                addMissedChar(missedChar, guessedChar);
            }
        }

        if (remainingFails == 0) {
            System.out.println("You lost, too many tries");
        } else {
            System.out.println("You won!");
        }

        printArr(guessWord);
        printArr(missedChar);

    }

}
