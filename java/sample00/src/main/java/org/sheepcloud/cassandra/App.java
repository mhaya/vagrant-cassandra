package org.sheepcloud.cassandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.QueryOptions;
import com.datastax.driver.core.ConsistencyLevel;
import com.datastax.driver.core.policies.RoundRobinPolicy;
import com.datastax.driver.core.policies.ConstantReconnectionPolicy;
import com.datastax.driver.core.policies.LoggingRetryPolicy;
import com.datastax.driver.core.policies.DefaultRetryPolicy;
import java.util.concurrent.TimeUnit;

public class App
{
    public static void main( String[] args )
    {
	QueryOptions qOptions = new QueryOptions()
	    .setConsistencyLevel(ConsistencyLevel.ONE)
            .setFetchSize(500);
	Cluster cluster = null;
	String query = "SELECT keyspace_name AS a,table_name AS b FROM system_schema.tables ;";
	try {
	    cluster = Cluster.builder()   
		.addContactPoints("192.168.12.10","192.168.12.11","192.168.12.12")
		.withLoadBalancingPolicy(new RoundRobinPolicy())
		.withQueryOptions(qOptions)
		.withReconnectionPolicy(new ConstantReconnectionPolicy(TimeUnit.SECONDS.toMillis(5)))
		.withRetryPolicy(new LoggingRetryPolicy(DefaultRetryPolicy.INSTANCE))
		.withCredentials("cassandra", "cassandra")
		.build();

	    Session session = cluster.connect();
	    Session.State session_state = session.getState();
	    System.out.println(session_state.getConnectedHosts());

            ResultSet rs = session.execute(query);
            Row row = rs.one();
            System.out.println(row.getString("a"));
            System.out.println(row.getString("b"));
	    session.close();

	    session = cluster.connect();
	    session_state = session.getState();
	    System.out.println(session_state.getConnectedHosts());
            rs = session.execute(query);
            row = rs.one();
            System.out.println(row.getString("a"));
            System.out.println(row.getString("b"));
	    session.close();

	} finally {
	    if (cluster != null) cluster.close();                                      
	}
    }
}

