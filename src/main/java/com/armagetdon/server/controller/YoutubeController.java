package com.armagetdon.server.controller;

import com.armagetdon.server.service.YoutubeDetail;
import com.armagetdon.server.service.YoutubeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequiredArgsConstructor
public class YoutubeController {
    private final YoutubeService youtubeService;

    @GetMapping("/youtube")
    private YoutubeDetail getYoutubeDetails(@RequestParam String youtubeUrl) {
        try {
            return youtubeService.getYoutubeDetails(youtubeUrl);
        } catch (IOException | GeneralSecurityException e) {
            e.printStackTrace();
            return null;
        }    }
}
