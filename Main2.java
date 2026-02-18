import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

void main() {
    Scanner consoleScanner = new Scanner(System.in);

    System.out.println("Введите путь для сохранения нового файла:");
    String filePath = consoleScanner.nextLine();

    System.out.println("Введите массив целых чисел:");

    List<Integer> numbers = new ArrayList<>();
    String numbersLine = consoleScanner.nextLine();

    try (Scanner lineScanner = new Scanner(numbersLine))
    {
        while (lineScanner.hasNextInt())
        {
            numbers.add(lineScanner.nextInt());
        }

        if (lineScanner.hasNext())
        {
            System.out.println("Предупреждение: Некоторые данные введены некорректно.");
        }
    }

    if (numbers.isEmpty())
    {
        System.err.println("Ошибка");
        consoleScanner.close();
        return;
    }

    try
    {
        List<Integer> evenNumbers = new ArrayList<>();
        List<Integer> oddNumbers = new ArrayList<>();

        for (int number : numbers)
        {
            if (number % 2 == 0)
            {
                evenNumbers.add(number);
            }
            else
            {
                oddNumbers.add(number);
            }
        }

        List<Integer> reversedNumbers = new ArrayList<>(numbers);
        Collections.reverse(reversedNumbers);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath)))
        {
            writer.write(listToString(numbers));
            writer.newLine();

            writer.write(listToString(evenNumbers));
            writer.newLine();

            writer.write(listToString(oddNumbers));
            writer.newLine();

            writer.write(listToString(reversedNumbers));

            System.out.println("Файл создан и данные записаны в " + filePath);
        }

    }
    catch (IOException e)
    {
        System.err.println("Ошибка при записи в файл: " + e.getMessage());
    }
    finally
    {
        consoleScanner.close();
    }
}

private static String listToString(List<Integer> list)
{
    if (list == null || list.isEmpty())
    {
        return "";
    }
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < list.size(); i++)
    {
        sb.append(list.get(i));
        if (i < list.size() - 1)
        {
            sb.append(" ");
        }
    }
    return sb.toString();
}