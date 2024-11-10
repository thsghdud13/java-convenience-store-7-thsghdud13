package store.util;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.Test;

class CSVFileLoaderTest {

    @Test
    void readCSV() {
        //given
        String filePath = "src/main/resources/products.md";

        //when
        CSVData csvData = CSVFileLoader.readCSV(filePath);

        //then
        assertAll(
                () -> assertThat(List.of("name", "price", "quantity", "promotion"))
                        .isEqualTo(csvData.getHeaders()),
                () -> assertThat(List.of("콜라", "1000", "10", "탄산2+1"))
                        .isEqualTo(csvData.getRows().get(0))
        );
    }
}
