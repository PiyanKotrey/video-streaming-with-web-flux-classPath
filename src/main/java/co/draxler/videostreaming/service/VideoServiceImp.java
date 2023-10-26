package co.draxler.videostreaming.service;



import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;


@Service

public class VideoServiceImp implements VideoService{
    private final ResourceLoader resourceLoader;
    private static final String VIDEO_STORAGE_PATH = "classpath:video/%s.mp4";

    public VideoServiceImp(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public Mono<Resource> getVideo(String title) {
        return Mono.fromSupplier(() ->
                resourceLoader.getResource(String.format(VIDEO_STORAGE_PATH, title)));
    }
}
