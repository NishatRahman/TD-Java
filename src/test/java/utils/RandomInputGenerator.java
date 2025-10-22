package utils;

import constants.Constants;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class RandomInputGenerator {
    public static Map<String, String> generateCredentials(int passwordLength) {
        final int minLength = Constants.MIN_PASSWORD_LENGTH;

        if (passwordLength < minLength) {
            throw new IllegalArgumentException("Password must be at least " + minLength + " characters.");
        }

        String lowercaseChars = "abcdefghijklmnopqrstuvwxyz";
        String uppercaseChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numeralChars = "0123456789";
        String cyrillicChars = "абвгдежзи";

        Random random = new Random();

        StringBuilder email = new StringBuilder();
        int emailLength = 6 + random.nextInt(5);
        for (int i = 0; i < emailLength; i++) {
            email.append(lowercaseChars.charAt(random.nextInt(lowercaseChars.length())));
        }
        String emailStr = email.toString();

        StringBuilder domain = new StringBuilder();
        int domainLength = 4 + random.nextInt(4);
        for (int i = 0; i < domainLength; i++) {
            domain.append(lowercaseChars.charAt(random.nextInt(lowercaseChars.length())));
        }
        String domainStr = domain.toString();

        StringBuilder password = new StringBuilder();
        password.append(uppercaseChars.charAt(random.nextInt(uppercaseChars.length())));
        password.append(numeralChars.charAt(random.nextInt(numeralChars.length())));
        password.append(emailStr.charAt(random.nextInt(emailStr.length())));
        password.append(cyrillicChars.charAt(random.nextInt(cyrillicChars.length())));

        String charPool = lowercaseChars + uppercaseChars + numeralChars + cyrillicChars;
        while (password.length() < passwordLength) {
            password.append(charPool.charAt(random.nextInt(charPool.length())));
        }

        char[] passwordArray = password.toString().toCharArray();
        for (int i = passwordArray.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[j];
            passwordArray[j] = temp;
        }
        String shuffledPassword = new String(passwordArray);

        Map<String, String> credentials = new HashMap<>();
        credentials.put("Password", shuffledPassword);
        credentials.put("Email", emailStr);
        credentials.put("Domain", domainStr);

        return credentials;
    }
}