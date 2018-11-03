package io.vasiliyplatonov.helpers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

public interface CsvReadable<setType> {
    setType setFromString(String s);

    default List<Map<Character, setType>> readFile(String filename) throws IOException {
        final List<Map<Character, setType>> result = new ArrayList<>();

        try (Reader reader = new FileReader(filename);
             CSVParser parse = CSVFormat.DEFAULT
                     .withHeader()
                     .withSkipHeaderRecord()
                     .withQuote(null)
                     .parse(reader)) {

            StreamSupport.stream(parse.spliterator(), false)
                    .forEach(r -> {
                        Map<Character, setType> setList = new HashMap<>();
                        r.toMap().forEach((key, value) -> {
                            setList.put(key.charAt(0), setFromString(value));
                        });
                        result.add(setList);
                    });
            return result;
        }
    }
}
