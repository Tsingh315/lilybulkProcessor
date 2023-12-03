package com.example.lily;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Slf4j
public class CsvInstructionController {

    private final CsvInstructionService csvInstructionService;

    public CsvInstructionController(CsvInstructionService csvInstructionService) {
        this.csvInstructionService = csvInstructionService;
    }

    @PostMapping(value = "/lily/bulk-processing/instructions", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> uploadSingleFileExample1(MultipartFile file) throws IOException {
        log.info("file : " + file);
        this.csvInstructionService.persistOrders(file);
        return ResponseEntity.ok(" processed.");
    }
}


