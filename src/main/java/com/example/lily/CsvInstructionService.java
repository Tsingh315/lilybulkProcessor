package com.example.lily;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvInstructionService {
    private final InstructionRepository repository;

    public CsvInstructionService(InstructionRepository repository) {
        this.repository = repository;
    }

    public void persistOrders(MultipartFile file) throws IOException {

        List<CsvInstruction> lilyInstructions;
        try (Reader reader = new InputStreamReader(file.getInputStream())) {
            CsvToBean<CsvInstruction> lilyInstructionParser = new CsvToBeanBuilder<CsvInstruction>(reader).withType(CsvInstruction.class).build();
            lilyInstructions = lilyInstructionParser.parse();
        }

        List<Instruction> instructions = new ArrayList<>();
        lilyInstructions.forEach(lilyInstruction -> {
            instructions.add(new Instruction(lilyInstruction));
        });
        repository.saveAll(instructions);
    }
}
