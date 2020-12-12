package zuul.proxy;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

public class RandomWeightedRoundRobin implements IRule{

	private ILoadBalancer lb;
	private static Integer position = 0;
	private Map<Server, Integer> serverMap = new ConcurrentHashMap<>();
	private List<Server> servers;
	
	@Override
	public Server choose(Object key) {
		servers = lb.getAllServers();
		Random r = new Random();
		for(Server s : servers) {
			if(!serverMap.containsKey(s)) { //Se si è aggiunto qualche server runtime lo considero
				Integer weight = r.nextInt(5) + 1;
				synchronized(serverMap) {
					serverMap.put(s, weight);
				}
				System.out.println("Server" + s.getHost() + ":" + s.getPort() +
						" added with weight " + weight);
			}
		}
		synchronized(servers) {
			servers= new ArrayList<Server>();
			for(Server s : serverMap.keySet()) {
				for(int i = 0; i<serverMap.get(s); i++) { //Aggiungo tante entry di un server quanto è il suo peso
					servers.add(s);
				}
			}
		}
		synchronized (position) {
			if(position>=servers.size()) {
				position=0;
			}
			return servers.get(position++);
		}
	}

	@Override
	public void setLoadBalancer(ILoadBalancer lb) {
		this.lb=lb;
	}

	@Override
	public ILoadBalancer getLoadBalancer() {
		return this.lb;
	}

}