package co.draxler.videostreaming.service;

//import co.draxler.videostreaming.model.Video;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.awt.image.DataBuffer;

public interface VideoService {
//    Mono<Video> upload(FilePart filePart);
    Mono<Resource> getVideo(String title);
}
