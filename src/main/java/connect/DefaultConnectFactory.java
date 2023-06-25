package connect;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public class DefaultConnectFactory {

    private static final Integer PORT=5672;
    private static final String HOST="127.0.0.1";
    private static final String USERNAME="admin";
    private static final String PASSWORD="admin";
    private static final String VHOST="base";
    private final ConnectionFactory factory;



    public DefaultConnectFactory() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername(USERNAME);
        connectionFactory.setPassword(PASSWORD);
        connectionFactory.setVirtualHost(VHOST);
        this.factory=connectionFactory;
    }

    public Connection getConnection(){
        try {
            return this.factory.newConnection();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void close(Connection connection){
        try {
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
