package store.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVFileLoader {
    public static CSVData readCSV(String filePath) {
        List<String> headers;
        List<List<String>> rows;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            headers = readHeader(reader);
            rows = readRows(reader);
            reader.close();
        } catch (IOException e) {
            throw new RuntimeException("[ERROR] CSV 파일 읽는 중 오류 발생");
        }
        return new CSVData(headers, rows);
    }

    private static List<String> readHeader(BufferedReader reader) throws IOException {
        String line = reader.readLine();
        return List.of(line.split(","));
    }

    private static List<List<String>> readRows(BufferedReader reader) throws IOException {
        List<List<String>> rows = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            rows.add(List.of(line.split(",")));
        }
        return rows;
    }

}
