package ru.academits.kozhevnikov.csv_to_html_converter;

import java.io.*;

public class CsvToHtmlConverter {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.printf("Передано следующее количество аргументов: %d. Необходимо передать два аргумента. Первый аргумент - путь к CSV файлу," +
                    "второй аргумент - путь к выходному HTML файлу.", args.length);

            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
             PrintWriter writer = new PrintWriter(args[1])) {
            writer.println("<html>");
            writer.println("<head>");
            writer.println("\t<meta charset=\"UTF-8\">");
            writer.println("\t<title>title</title>");
            writer.println("</head>");
            writer.println("<body>");
            writer.println("<table border=\"1\">");

            boolean isCellCreated = false;
            String line = reader.readLine();

            while (line != null) {
                for (int i = 0; i < line.length(); i++) {
                    if (i == 0) {
                        if (isCellCreated) {
                            writer.print("<br/>");
                        } else {
                            writer.print("\t<tr><td>");
                        }
                    }

                    char character = line.charAt(i);
                    int lastCharacterIndex = line.length() - 1;

                    if (i == lastCharacterIndex) {
                        if (character == '"') {
                            writer.println("</td></tr>");
                            isCellCreated = false;
                            continue;
                        }

                        if (character == ',') {
                            writer.println("</td><td></td></tr>");
                            isCellCreated = false;
                            continue;
                        }

                        if (!isCellCreated) {
                            writer.print(character);
                            writer.println("</td></tr>");
                            continue;
                        }
                    }

                    if (character == '"' && !isCellCreated) {
                        isCellCreated = true;
                        continue;
                    }

                    if (i != lastCharacterIndex && isCellCreated && character == '"' && line.charAt(i + 1) != '"') {
                        isCellCreated = false;
                        continue;
                    }

                    if (character == ',' && isCellCreated) {
                        writer.print(',');
                        continue;
                    }

                    if (i != lastCharacterIndex && isCellCreated && character == '"' && line.charAt(i + 1) == '"') {
                        writer.print('"');
                        isCellCreated = false;
                        continue;
                    }

                    if (character == ',') {
                        writer.print("</td><td>");
                        continue;
                    }

                    if (character == '<') {
                        writer.print("&lt;");
                        continue;
                    }

                    if (character == '>') {
                        writer.print("&gt;");
                        continue;
                    }

                    if (character == '&') {
                        writer.print("&amp;");
                        continue;
                    }

                    writer.print(character);
                }

                line = reader.readLine();
            }

            writer.println("</table>");
            writer.println("</body>");
            writer.print("</html>");
        } catch (FileNotFoundException e) {
            System.out.println("Входной файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода");
        }
    }
}
