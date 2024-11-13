package store.util;

import java.util.List;

public class CSVData {
    private final List<String> headers;
    private final List<List<String>> rows;

    public CSVData(List<String> headers, List<List<String>> rows) {
        this.headers = headers;
        this.rows = rows;
    }

    public List<String> getHeaders() {
        return headers;
    }

    public List<List<String>> getRows() {
        return rows;
    }
}
