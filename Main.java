import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите путь к первому файлу:");
        String file1Path = scanner.nextLine();

        System.out.println("Введите путь ко второму файлу:");
        String file2Path = scanner.nextLine();

        try (BufferedReader reader1 = new BufferedReader(new FileReader(file1Path));
             BufferedReader reader2 = new BufferedReader(new FileReader(file2Path))) {

            String line1;
            String line2;
            int lineNumber = 1;
            boolean filesMatch = true;

            while ((line1 = reader1.readLine()) != null && (line2 = reader2.readLine()) != null)
            {
                if (!line1.equals(line2))
                {
                    filesMatch = false;
                    System.out.println("Расхождение в строке " + lineNumber + ":");
                    System.out.println("Файл 1: " + line1);
                    System.out.println("Файл 2: " + line2);
                    System.out.println();
                }
                lineNumber++;
            }

            if ((line1 = reader1.readLine()) != null)
            {
                filesMatch = false;
                System.out.println("Файл 1 содержит больше строк, начиная со строки " + lineNumber + ":");
                System.out.println("Файл 1: " + line1);
                while ((line1 = reader1.readLine()) != null)
                {
                    lineNumber++;
                    System.out.println("Файл 1: " + line1);
                }
            }

            if ((line2 = reader2.readLine()) != null)
            {
                filesMatch = false;
                System.out.println("Файл 2 содержит больше строк, начиная со строки " + lineNumber + ":");
                System.out.println("Файл 2: " + line2);
                while ((line2 = reader2.readLine()) != null)
                {
                    lineNumber++;
                    System.out.println("Файл 2: " + line2);
                }
            }


            if (filesMatch)
            {
                System.out.println("Файлы полностью совпадают.");
            }

        }
        catch (IOException e)
        {
            System.err.println("Ошибка при чтении файлов: " + e.getMessage());
        }
        finally
        {
            scanner.close();
        }
    }
}