package co.draxler.videostreaming.controller;

import co.draxler.videostreaming.service.VideoService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class VideoController {
    private final VideoService videoService;

    @GetMapping(value = "video/{title}", produces = "video/mp4")
    public Mono<Resource> getVideo(@PathVariable String title) {

        return videoService.getVideo(title);
    }
}
