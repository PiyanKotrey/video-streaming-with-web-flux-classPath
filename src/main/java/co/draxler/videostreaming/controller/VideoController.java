package co.draxler.videostreaming.controller;

import co.draxler.videostreaming.service.VideoService;
import io.minio.*;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;



@RestController
@RequiredArgsConstructor
@RequestMapping
public class VideoController {
    private final VideoService videoService;
    @Autowired
    private MinioClient minioClient;

    @GetMapping("/video/{title}")
    public Mono<Resource> getVideo(@PathVariable String title){
        return videoService.getVideo(title);
    }

    @GetMapping(value = "/video/{bucket}/{objectName}", produces = "video/mp4")
    public Mono<ResponseEntity<byte[]>> streamVideo(@PathVariable String bucket,
                                                    @PathVariable String objectName) {
        try {
            byte[] videoData = minioClient.getObject(
                    GetObjectArgs.builder()
                            .bucket(bucket)
                            .object(objectName)
                            .build()
            ).readAllBytes();

            System.out.println(videoData.length);

            return Mono.just(ResponseEntity.ok()
                    .body(videoData));
        } catch (MinioException e) {
            e.printStackTrace();
            return Mono.just(ResponseEntity.status(500).build());
        } catch (Exception e) {
            e.printStackTrace();
            return Mono.just(ResponseEntity.status(500).build());
        }
    }

    @Autowired
    private ResourceLoader resourceLoader;

    @GetMapping(value = "/stream/{bucket}/{objectName}", produces = "video/mp4")
    public Mono<Resource> stream(@PathVariable String bucket, @PathVariable String objectName) {
        return Mono.fromSupplier(() -> {
            try {
                return resourceLoader.getResource(String.valueOf(minioClient.getObject(
                        GetObjectArgs.builder()
                                .bucket(bucket)
                                .object(objectName)
                                .build())));
            } catch (ErrorResponseException e) {
                if ("NoSuchKey".equals(e.errorResponse().code())) {
                    return null;
                } else {
                    throw new RuntimeException(e);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }



}


