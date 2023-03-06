package ru.academits.kozhevnikov;

import java.io.*;

public class CSVToHTMLConverter {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.printf("Передано следующее количество аргументов: %d. Необходимо передать два аргумента. Первый аргумент - путь к CSV файлу," +
                    "второй аргумент - путь к выходному HTML файлу.", args.length);

            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(args[0]));
             PrintWriter writer = new PrintWriter(args[1])) {
            writer.println("<html>\n<head>\n    <meta charset=\"UTF-8\">\n    <title>title</title>\n</head>");
            writer.println("<body>");
            writer.println("<table border=\"1\">");

            boolean isCellCreated = false;

            while (reader.ready()) {
                String line = reader.readLine();

                for (int i = 0; i < line.length(); i++) {
                    if (i == 0) {
                        if (isCellCreated) {
                            writer.print("<br/>");
                        } else {
                            writer.print("    <tr><td>");
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
            }

            writer.print("</table>\n</body>\n</html>");
        } catch (FileNotFoundException e) {
            System.out.println("Входной файл не найден");
        } catch (IOException e) {
            System.out.println("Ошибка ввода/вывода");
        }
    }
}
