package zuul.proxy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import com.netflix.loadbalancer.WeightedResponseTimeRule;


@Configuration
public class UserConfiguration {
	
	@Bean
	public IRule ribbonRule() {
		return new MyRule();
	}
		
}
