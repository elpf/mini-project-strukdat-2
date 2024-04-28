import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String plainText;
        do {
            System.out.println("Masukkan Password (8-15 huruf abjad): ");
            plainText = scanner.nextLine().toUpperCase(); 
        } while (!isValidPassword(plainText));

        String encryptedPassword = Encryption.encrypt(plainText);
        System.out.println("Hasil Enkripsi: " + encryptedPassword);

        String decryptedPassword = Decryption.decrypt(encryptedPassword);
        System.out.println("Hasil Dekripsi: " + decryptedPassword);
    }

    public static boolean isValidPassword(String password) {
        if (password.length() < 8 || password.length() > 15) {
            System.out.println("Password harus memiliki panjang antara 8 hingga 15 karakter.");
            return false;
        }
        for (char c : password.toCharArray()) {
            if (!Character.isLetter(c)) {
                System.out.println("Password hanya boleh mengandung huruf abjad.");
                return false;
            }
        }
        return true;
    }

    public static class Encryption {

        public static String encrypt(String plainText) {
            String step1 = step1(plainText);
            System.out.println("Hasil Step 1 (Encrypt): " + step1);

            String step2 = step2(step1);
            System.out.println("Hasil Step 2 (Encrypt): " + step2);

            String step3 = step3(step2);
            System.out.println("Hasil Step 3 (Encrypt): " + step3);

            return step3;
        }

        private static String step1(String password) {
            Queue<Character> queue = new LinkedList<>();
            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);
                char shiftedChar = (char) (c - 5);
                if (shiftedChar < ('A')) {
                    shiftedChar = (char) (shiftedChar + 26);
                }
                queue.add(shiftedChar);
            }
            StringBuilder result = new StringBuilder();
            while (!queue.isEmpty()) {
                result.append(queue.poll());
            }
            return result.toString();
        }

        private static String step2(String password) {
            String front = password.substring(0, 3);
            String mid = password.substring(3, password.length() - 3);
            String end = password.substring(password.length() - 3);
            return end + mid + front;
        }

        private static String step3(String password) {
            Stack<Character> stack = new Stack<>();
            for (char c : password.toCharArray()) {
                stack.push(c);
            }
            StringBuilder reversedPassword = new StringBuilder();
            while (!stack.isEmpty()) {
                reversedPassword.append(stack.pop());
            }
            return reversedPassword.toString();
        }
    }

    public static class Decryption {

        public static String decrypt(String encryptedPassword) {
            String decryptedStep3 = reverseStep3(encryptedPassword);
            System.out.println("Hasil Step 3 (Decrypt): " + decryptedStep3);

            String decryptedStep2 = reverseStep2(decryptedStep3);
            System.out.println("Hasil Step 2 (Decrypt): " + decryptedStep2);

            String decryptedPassword = reverseStep1(decryptedStep2);
            System.out.println("Hasil Step 1 (Decrypt): " + decryptedPassword);

            return decryptedPassword;
        }

        private static String reverseStep1(String password) {
            Queue<Character> queue = new LinkedList<>();
            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);
                char shiftedChar = (char) (c + 5);
                if (shiftedChar > ('Z')) {
                    shiftedChar = (char) (shiftedChar - 26);
                }
                queue.add(shiftedChar);
            }
            StringBuilder result = new StringBuilder();
            while (!queue.isEmpty()) {
                result.append(queue.poll());
            }
            return result.toString();
        }

        private static String reverseStep2(String password) {
            String end = password.substring(0, 3);
            String mid = password.substring(3, password.length() - 3);
            String front = password.substring(password.length() - 3);
            return front + mid + end;
        }

        private static String reverseStep3(String password) {
            Stack<Character> stack = new Stack<>();
            for (char c : password.toCharArray()) {
                stack.push(c);
            }
            StringBuilder reversedPassword = new StringBuilder();
            while (!stack.isEmpty()) {
                reversedPassword.append(stack.pop());
            }
            return reversedPassword.toString();
        }
    }
}
