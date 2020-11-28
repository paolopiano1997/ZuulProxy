package zuul.proxy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;



@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@RibbonClient(name = "detection", configuration = UserConfiguration.class)
public class ZuulProxy {

	public static void main(String[] args) {
		SpringApplication.run(ZuulProxy.class, args);
	}

}
