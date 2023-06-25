package queue;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import exchange.ExchangeProperties;

import java.io.IOException;
import java.util.HashMap;

public class QueueFactory {

    private final static HashMap<String, QueueProperties> CONFIG =new HashMap<>();

    public static QueueProperties getQueue(String properties){
        String queueName = (String)JSONObject.parseObject(properties).get("exchangeName");
        if (!CONFIG.containsKey(queueName)){
            QueueProperties queueProperties = JSONObject.parseObject(properties, QueueProperties.class);
            CONFIG.put(queueProperties.getQueueName(),queueProperties);
            return queueProperties;
        }else {
            return CONFIG.get(queueName);
        }
    }


    public static void declare(Channel channel, QueueProperties queueProrerties){
        try {
            channel.queueDeclare(queueProrerties.getQueueName(),
                    queueProrerties.isIsdurable(),
                    queueProrerties.isExclusive(),
                    queueProrerties.isAutoDelete(),
                    queueProrerties.getArgs());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
