package io.vasiliyplatonov.helpers;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public interface CsvWritable<setType> {

    String setToString(setType s);

    default void writeFile(List<Map<Character, setType>> listSet, String filename) throws IOException {

        final Set<String> headers = listSet.get(0).keySet().stream().map(String::valueOf).collect(Collectors.toSet());

        try (Writer writer = new FileWriter(filename);
             CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withQuote(null)
                     .withHeader(headers.toArray(new String[headers.size()])))) {
            listSet.forEach(i -> {
                try {
                    final List<String> data = i.values().stream().map(this::setToString).collect(Collectors.toList());
                    printer.printRecord(data);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }
}
