package ru.reeson2003.aisd2;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FourSet {

    private static final String[] HEADERS = {"a", "b", "c", "d"};

    public final char[] a;
    public final char[] b;
    public final char[] c;
    public final char[] d;

    public FourSet(char[] a, char[] b, char[] c, char[] d) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
    }

    public static List<FourSet> fromFile(String filename) throws IOException {
        try (Reader reader = new FileReader(filename);
             CSVParser parse = CSVFormat.DEFAULT
                     .withHeader(HEADERS)
                     .withSkipHeaderRecord()
                     .withQuote(null)
                     .parse(reader)) {
            return StreamSupport.stream(parse.spliterator(), false)
                    .map(r -> new FourSet(getChars(r, "a"), getChars(r, "b"), getChars(r, "c"), getChars(r, "d")))
                    .collect(Collectors.toList());
        }
    }

    public static void writeToFile(List<FourSet> sets, String filename) throws IOException {
        try (Writer writer = new FileWriter(filename);
             CSVPrinter printer = new CSVPrinter(writer, CSVFormat.DEFAULT
                     .withQuote(null)
                     .withHeader(HEADERS))) {
            sets.forEach(f -> {
                try {
                    printer.printRecord(getString(f.a), getString(f.b), getString(f.c), getString(f.d));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        }
    }

    private static String getString(char[] chars) {
        return String.valueOf(chars);
    }

    private static char[] getChars(CSVRecord r, String a) {
        return r.get(a).toCharArray();
    }

    @Override
    public String toString() {
        return "{" +
                String.valueOf(a) +
                ", " + String.valueOf(b) +
                ", " + String.valueOf(c) +
                ", " + String.valueOf(d) +
                '}';
    }
}
