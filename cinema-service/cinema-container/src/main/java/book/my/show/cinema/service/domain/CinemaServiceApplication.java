package book.my.show.cinema.service.domain;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages = { "book.my.show.cinema.service.dataaccess", "book.my.show.dataaccess" })
@EntityScan(basePackages = { "book.my.show.cinema.service.dataaccess", "book.my.show.dataaccess" })
@SpringBootApplication(scanBasePackages = "book.my.show")
@EnableDiscoveryClient
public class CinemaServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(CinemaServiceApplication.class, args);
    }
}
