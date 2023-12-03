package com.example.lily;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTests {
    @LocalServerPort
    private int port;

    @Test
    void aCsvInstructionIsPersisted() throws Exception {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, String> fileMap = new LinkedMultiValueMap<>();
        ContentDisposition contentDisposition = ContentDisposition.builder("form-data").name("file").filename("instructions.csv").build();
        fileMap.add(HttpHeaders.CONTENT_DISPOSITION, contentDisposition.toString());

        HttpEntity<byte[]> fileEntity = new HttpEntity<>(getTestFile(), fileMap);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", fileEntity);

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(fullyQualifiedUri("/lily/bulk-processing/instructions"), HttpMethod.POST, requestEntity, String.class);
        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).contains("processed");
    }

    private byte[] getTestFile() throws URISyntaxException, IOException {
        Path path = Paths.get(ClassLoader.getSystemResource("order.csv").toURI());
        return Files.readAllBytes(path);
    }

    private String fullyQualifiedUri(String uri) {
        return "http://localhost:" + port + uri;
    }
}
