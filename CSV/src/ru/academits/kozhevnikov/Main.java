package ru.academits.kozhevnikov;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("CSV/src/ru/academits/kozhevnikov/in.csv"));
             PrintWriter writer = new PrintWriter("CSV/src/ru/academits/kozhevnikov/out.html")) {
            writer.println("<html><head><meta charset=\"UTF-8\"><title>title</title></head>");
            writer.println("<table border=\"1\">");

            int cellsCount = 0;
            boolean isCellCreated = false;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int cellsInRow = 0;

                for (int i = 0; i < line.length(); i++) {
                    if (i == 0 && !isCellCreated) {
                        cellsInRow++;
                        writer.print("<tr><td>");
                    }

                    if (i == 0 && isCellCreated) {
                        cellsCount++;
                        writer.print("<br/>");
                    }

                    if (i == line.length() - 1 && line.charAt(i) == '"') {
                        writer.print("</td></tr>");
                        isCellCreated = false;
                        continue;
                    }

                    if (i == line.length() - 1 && line.charAt(i) == '"' && cellsInRow < cellsCount) {
                        writer.print("</td><td></td></tr>");
                        isCellCreated = false;
                        continue;
                    }

                    if (i == line.length() - 1 && line.charAt(i) == ',') {
                        writer.print("</td><td></td></tr>");
                        isCellCreated = false;
                        continue;
                    }

                    if (i == line.length() - 1 && !isCellCreated) {
                        writer.print(line.charAt(i) + "</td></tr>");
                        continue;
                    }

                    if (line.charAt(i) == '"' && !isCellCreated) {
                        isCellCreated = true;
                        continue;
                    }

                    if (i != line.length() - 1 && isCellCreated && line.charAt(i) == '"' && line.charAt(i + 1) != '"') {
                        isCellCreated = false;
                        continue;
                    }

                    if (line.charAt(i) == ',' && isCellCreated) {
                        writer.print(',');
                        continue;
                    }

                    if (i != line.length() - 1 && isCellCreated && line.charAt(i) == '"' && line.charAt(i + 1) == '"') {
                        writer.print('"');
                        isCellCreated = false;
                        continue;
                    }

                    if (line.charAt(i) == ',' && !isCellCreated) {
                        writer.print("</td><td>");
                        cellsInRow++;
                        continue;
                    }

                    if (line.charAt(i) == '<') {
                        writer.print("&lt;");
                        continue;
                    }

                    if (line.charAt(i) == '>') {
                        writer.print("&gt;");
                        continue;
                    }

                    if (line.charAt(i) == '&') {
                        writer.print("&amp;");
                        continue;
                    }

                    writer.print(line.charAt(i));
                }
            }

            writer.print("</table></html>");
        }
    }
}
