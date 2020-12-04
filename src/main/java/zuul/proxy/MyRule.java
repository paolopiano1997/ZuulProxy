package zuul.proxy;

import java.util.List;
import java.util.Random;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;



public class MyRule implements IRule{

	private ILoadBalancer lb;
	
	
	@Override
	public Server choose(Object key) {
		List<Server> servers = lb.getAllServers();
		Random r = new Random();
		int rand = r.nextInt(1000)%servers.size();
		System.out.println("Random number: " + rand);
		for(Server s : servers) {
			System.out.println("Host: " +s.getHost() + "\nPort: "+ s.getPort() + "\nHostPort: " + s.getHostPort());
		}
		/*if (rand<50)
			return getServerByPort(servers,8081);
		else if(rand>=33 && rand <66)
			return getServerByPort(servers, 9999);
		return getServerByPort(servers, 9092);
		*/
		return servers.get(rand);
	}
	
	
	private Server getServerByPort(List<Server> servers, int port){
	        for(Server s : servers){
	            if(s.getPort() == port){
	                return s;
	            }
	        }
	        return null;
    }

	@Override
	public void setLoadBalancer(ILoadBalancer lb) {
		this.lb = lb;
		
	}

	@Override
	public ILoadBalancer getLoadBalancer() {
		return this.lb;
	}
	
}