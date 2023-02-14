package encryptdecrypt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import encryptdecrypt.algorithms.Algorithm;
import encryptdecrypt.algorithms.strategies.ShiftAlgorithm;
import encryptdecrypt.algorithms.strategies.UnicodeAlgorithm;

public class Main {

    private static final Map<String, String> argsMap = new HashMap<>();

    private static Algorithm algorithmInstance;

    public static void main(String[] args) {
        for (int i = 0; i < args.length; i += 2) {
            argsMap.put(args[i], args[i + 1]);
        }

        final String algorithm = argsMap.get("-alg");
        final String inputFilePath = argsMap.get("-in");
        final String outputFilePath = argsMap.get("-out");
        final String operation = argsMap.get("-mode");
        final int key = Integer.parseInt(argsMap.get("-key"));
        final File inputFile = new File(inputFilePath);
        final File outputFile = new File(outputFilePath);

        String message;

        try (final Scanner scanner = new Scanner(inputFile); final Writer writer = new FileWriter(outputFile)) {
            message = scanner.nextLine();

            switch (algorithm) {
                case "shift" -> algorithmInstance = new ShiftAlgorithm();
                case "unicode" -> algorithmInstance = new UnicodeAlgorithm();
                default -> System.out.println("Unsupported algorithm!");
            }

            switch (operation) {
                case "enc" -> writer.write(algorithmInstance.encrypt(message, key));
                case "dec" -> writer.write(algorithmInstance.decrypt(message, key));
                default -> System.out.println("Unsupported operation!");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
