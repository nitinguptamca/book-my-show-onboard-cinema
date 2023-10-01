package book.my.show.cinema.mgmt.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class CinemaMgmtService {
    public static void main(String[] args) {
        SpringApplication.run(CinemaMgmtService.class,args);
    }
}
