package exchange;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONReader;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.HashMap;

public class ExchangeFactory {

    private final static HashMap<String,ExchangeProperties> CONFIG =new HashMap<>();

    public static ExchangeProperties getExchange(String properties){
        String exchangeName = (String)JSONObject.parseObject(properties).get("exchangeName");
        if (!CONFIG.containsKey(exchangeName)){
            ExchangeProperties exchangeProperties = JSONObject.parseObject(properties, ExchangeProperties.class);
            CONFIG.put(exchangeProperties.getExchangeName(),exchangeProperties);
            return exchangeProperties;
        }else {
            return CONFIG.get(exchangeName);
        }
    }

    public static void declare(Channel channel,ExchangeProperties exchangeProperties){
        try {
            channel.exchangeDeclare(exchangeProperties.getExchangeName(),
                                    exchangeProperties.getExchangeType(),
                                    exchangeProperties.isIsdurable(),
                                    exchangeProperties.isAutoDelete(),
                                    exchangeProperties.isInternal(),
                                    exchangeProperties.getArgs());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
