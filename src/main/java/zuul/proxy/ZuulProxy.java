package zuul.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@RestController
@EnableDiscoveryClient
@RibbonClient(name = "detection", configuration = UserConfiguration.class)
public class ZuulProxy {

	public static void main(String[] args) {
		SpringApplication.run(ZuulProxy.class, args);
	}
	
	@RequestMapping
	public String get() {
		return "ZUUL PROXY";
	}

}
