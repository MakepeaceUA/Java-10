import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

void main() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Введите путь к файлу:");
    String filePath = scanner.nextLine();

    System.out.println("Введите запрещенные слова:");
    String[] forbiddenWordsArray = scanner.nextLine().split("\\s+");
    List<String> forbiddenWords = Arrays.asList(forbiddenWordsArray);

    Map<String, Integer> report = new HashMap<>();
    for (String word : forbiddenWords)
    {
        report.put(word, 0);
    }

    StringBuilder fileContent = new StringBuilder();

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
    {
        String currentLine;
        while ((currentLine = reader.readLine()) != null)
        {
            fileContent.append(currentLine).append(System.lineSeparator());
        }
    }
    catch (IOException e)
    {
        System.err.println("Ошибка при чтении файла: " + e.getMessage());
        scanner.close();
        return;
    }

    String contentAsString = fileContent.toString();
    String modifiedContent = contentAsString;

    for (String word : forbiddenWords)
    {
        StringBuilder stars = new StringBuilder();
        for (int i = 0; i < word.length(); i++)
        {
            stars.append('*');
        }
        String replacement = stars.toString();

        int originalLength = modifiedContent.length();
        String tempContent = modifiedContent.replace(word, "");
        int count = (originalLength - tempContent.length()) / word.length();

        if (count > 0) {
            report.put(word, report.get(word) + count);
            modifiedContent = modifiedContent.replace(word, replacement);
        }
    }

    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, false)))
    {
        writer.write(modifiedContent);
    }
    catch (IOException e)
    {
        System.err.println("Ошибка при записи в файл: " + e.getMessage());
        scanner.close();
        return;
    }

    boolean wordsCensored = false;
    for (Map.Entry<String, Integer> entry : report.entrySet())
    {
        if (entry.getValue() > 0)
        {
            System.out.println("Слово '" + entry.getKey() + "' было заменено " + entry.getValue() + " раз(а).");
            wordsCensored = true;
        }
    }

    if (!wordsCensored)
    {
        System.out.println("Запрещенные слова в файле не найдены.");
    }

    System.out.println("\nФайл успешно обработан.");

    scanner.close();
}