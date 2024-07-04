package com.armagetdon.server.service;
import com.armagetdon.server.repository.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class NicknameService {

    private final MemberRepository memberRepository;
    private final Random random = new Random();
    private List<String> adjectives;
    private List<String> nouns;

    @Value("${nickname.adjective-loc}")
    private String adjectivesFile;

    @Value("${nickname.noun-loc}")
    private String nounsFile;

    @PostConstruct
    public void init() {
        try {
            adjectives = readWordsFromFile(adjectivesFile);
            nouns = readWordsFromFile(nounsFile);
        } catch (Exception e) {
            // Handle any other unexpected exceptions
            e.printStackTrace();
            throw new RuntimeException("Unexpected error during nickname service initialization", e);
        }
    }


    private List<String> readWordsFromFile(String filePath) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(filePath)));
        return Arrays.asList(content.split("\\s*,\\s*"));
    }

    public String generateNickname() {
        String adjective = adjectives.get(random.nextInt(adjectives.size()));
        String noun = nouns.get(random.nextInt(nouns.size()));

        return adjective + " "+ noun;
    }
}
