package channel;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import connect.DefaultConnectFactory;
import exchange.ExchangeProperties;
import queue.QueueProperties;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public class DefaultChannelFactory {

    private final  HashMap<String, Channel> CONFIG =new HashMap<>();

    private DefaultConnectFactory defaultConnectFactory;

    public DefaultChannelFactory() {
        this.defaultConnectFactory=new DefaultConnectFactory();
    }

    public  Channel getChannel(){
        try {
            return  defaultConnectFactory.getConnection().createChannel();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public  void bind(Channel channel, ExchangeProperties exchange, QueueProperties queue){
        String exchangeName = exchange.getExchangeName();
        String routeKey = exchange.getRouteKey();
        String queueName = queue.getQueueName();
        String key=exchangeName+"_"+routeKey;
        try {
            if (!CONFIG.containsKey(key)){
                CONFIG.put(key,channel);
                channel.queueBind(queueName,exchangeName,routeKey);
            }
        } catch (IOException e) {
            close(channel);
            e.printStackTrace();
        }
    }

    public  void pulish(ExchangeProperties exchange,String message){
        String exchangeName = exchange.getExchangeName();
        String routeKey = exchange.getRouteKey();
        String key=exchangeName+"_"+routeKey;
        if (CONFIG.containsKey(key)){
            Channel channel=CONFIG.get(key);
            try {
                long start = System.currentTimeMillis();
                channel.basicPublish(exchangeName,routeKey, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
                long end = System.currentTimeMillis();
                System.out.println("消息已发送，共耗时"+(end-start)+"ms");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            System.out.println("消息未发送，未发现相关路由");
        }

    }

    public void close(Channel channel){
        try {
            channel.close();
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }
    }
}
