package zuul.proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;


@Configuration
public class UserConfiguration {
	
	@Bean
	public IRule ribbonRule() {
		return new MyRule();
	}
		
}
