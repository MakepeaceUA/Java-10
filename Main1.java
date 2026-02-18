import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

void main() {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Введите путь к файлу:");
    String filePath = scanner.nextLine();

    String longestLine = "";
    int maxLength = 0;

    try (BufferedReader reader = new BufferedReader(new FileReader(filePath)))
    {
        String currentLine;

        while ((currentLine = reader.readLine()) != null)
        {
            if (currentLine.length() > maxLength)
            {
                maxLength = currentLine.length();
                longestLine = currentLine;
            }
        }

        if (!longestLine.isEmpty())
        {
            System.out.println("Самая длинная строка в файле:");
            System.out.println(longestLine);
            System.out.println("Ее длина: " + maxLength + " символов.");
        }
        else
        {
            System.out.println("Ошибка");
        }

    }
    catch (IOException e)
    {
        System.err.println("Ошибка при чтении файла: " + e.getMessage());
    }
    finally
    {
        scanner.close();
    }
}