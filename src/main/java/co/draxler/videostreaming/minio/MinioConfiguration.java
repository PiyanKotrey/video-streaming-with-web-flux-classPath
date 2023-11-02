package co.draxler.videostreaming.minio;

import io.minio.MinioClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.codec.ResourceHttpMessageWriter;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.codec.json.Jackson2JsonDecoder;
import org.springframework.http.codec.json.Jackson2JsonEncoder;

@Configuration
public class MinioConfiguration {
    @Bean
    public MinioClient minioClient(){
        return MinioClient.builder()
                .endpoint("http://localhost:9000")
                .credentials("cBVDXtXFEtdSnckMMLF6",
                        "YgWzv3sEbrVhjDCwJQJzz27fh5OW0QXX7r9slURN")
                .build();
    }
}
