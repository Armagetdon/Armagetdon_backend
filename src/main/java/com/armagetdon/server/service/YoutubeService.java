package com.armagetdon.server.service;

import com.armagetdon.server.dto.response.YoutubeDetail;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import com.google.api.services.youtube.model.VideoListResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class YoutubeService {

    @Value("${youtube.api.key}")
    private String apiKey;


    private static final String APPLICATION_NAME = "armageddon";

    private final YouTube youtube;

    public YoutubeService() throws GeneralSecurityException, IOException {
        this.youtube = new YouTube.Builder(
                GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(),
                request -> {})
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public YoutubeDetail getYoutubeDetails(String youtubeUrl) throws GeneralSecurityException, IOException {
        String videoId = extractVideoId(youtubeUrl);

        YouTube.Videos.List request = youtube.videos().list("snippet");
        VideoListResponse response = request.setId(videoId).setKey(apiKey).execute();
        List<Video> videoList = response.getItems();

        // 잘못된 비디오 ID가 입력되었을 때
        if (videoList.isEmpty()) {
            //TODO: 예외처리
        }

        Video video = videoList.get(0);
        String title = video.getSnippet().getTitle();
        String thumbnailUrl = video.getSnippet().getThumbnails().getDefault().getUrl();
        return new YoutubeDetail(title, thumbnailUrl);
    }

    private String extractVideoId(String videoUrl) {
        String regex = "v=([^&]*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(videoUrl);

        if (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }
}
