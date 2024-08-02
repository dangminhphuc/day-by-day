package com.dangminhphuc.daybyday;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Predicate;

public class Main {
    private static final Logger log = LoggerFactory.getLogger("read-extremely-csv");

    public static void main(String[] args) throws IOException {
        final Path path = Paths.get("read-extremely-csv/src/main/resources/5m Sales Records.csv");

        try (BufferedReader br = Files.newBufferedReader(path)) {
            long start = System.currentTimeMillis();
            List<SaleRecord> saleRecords = br.lines()
//                    .parallel()
                    .skip(1)
                    .map(s -> s.split(","))
                    .map(s -> new SaleRecord(s[0], s[1], s[2], s[3], s[4], s[5], s[6], s[7], s[8], s[9], s[10], s[11], s[12], s[13]))
                    .toList();
            log.info(" List<SaleRecord>: {} ; Time = {}", saleRecords.size(), (System.currentTimeMillis() - start));

            // Process Sale records here

            Predicate<SaleRecord> asiaRegion = s -> s.getRegion().equals("Asia");
            Predicate<SaleRecord> europeRegion = s -> s.getRegion().equals("Europe");
            Predicate<SaleRecord> europeNAsiaRegion = s -> s.getRegion().equals("Europe") || s.getRegion().equals("Asia");

            // use atomic int to filter by index
            AtomicInteger atomicInteger = new AtomicInteger();
            Predicate<SaleRecord> getEvenIdxAndOver10 = s -> {
                int i = atomicInteger.incrementAndGet();
                return i % 2 == 0 && i < 10;
            };

            log.info("  - Count Asia Region         : " + saleRecords.stream().filter(asiaRegion).count());
            log.info("  - Count Europe Region       : " + saleRecords.stream().filter(europeRegion).count());
            log.info("  - Count Europe & Asia Region: " + saleRecords.stream().filter(europeNAsiaRegion).count());
            log.info("  - Get 10th rows and row_index is even: " + saleRecords.stream().filter(getEvenIdxAndOver10).toList());
        }
    }
}