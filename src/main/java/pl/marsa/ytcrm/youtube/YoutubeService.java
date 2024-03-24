package pl.marsa.ytcrm.youtube;

import com.google.api.client.auth.oauth2.ClientParametersAuthentication;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.InputStreamContent;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.Video;
import org.springframework.beans.factory.annotation.Value;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collection;

public class YoutubeService {
    private static final Collection<String> SCOPES =
            Arrays.asList("https://www.googleapis.com/auth/youtube.upload");

    @Value("${youtube.project.application_name}")
    private String APPLICATION_NAME = "API";
    @Value("${youtube.project.client_id}")
    private String CLIENT_ID;
    @Value("${youtube.project.client_secret}")
    private String CLIENT_SECRET;

    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    private YouTube getService() throws GeneralSecurityException, IOException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        ClientParametersAuthentication credentials = new ClientParametersAuthentication(CLIENT_ID, CLIENT_SECRET);
        return new YouTube.Builder(httpTransport, JSON_FACTORY, credentials)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public Video publishVideo(Video meta, File content) throws GeneralSecurityException, IOException {
        var service = getService();
        InputStreamContent mediaContent =
                new InputStreamContent("application/octet-stream",
                        new BufferedInputStream(new FileInputStream(content)));

        YouTube.Videos.Insert request = service.videos()
                .insert("part", meta, mediaContent);
        return request.execute();
    }

    public String removeVideo(String id) throws GeneralSecurityException, IOException {
        var service = getService();


        YouTube.Videos.Delete request = service.videos().delete(id);
        request.execute();
        return id;
    }

}