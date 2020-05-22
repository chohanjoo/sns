package mwohae.gateway;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ImportResource;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
@ImportResource(value = {"classpath:META-INF/datasource.xml"})
@MapperScan(value = {"mwohae.gateway.dao"})
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
