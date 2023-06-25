package producer;

import channel.DefaultChannelFactory;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import connect.DefaultConnectFactory;
import exchange.ExchangeConst;
import exchange.ExchangeFactory;
import exchange.ExchangeProperties;
import queue.QueueConst;
import queue.QueueFactory;
import queue.QueueProperties;

import java.util.Scanner;

public class PutClient {

    public static void main(String[] args) {
        DefaultChannelFactory defaultChannelFactory = new DefaultChannelFactory();
        Channel channel = defaultChannelFactory.getChannel();
        ExchangeProperties exchange = ExchangeFactory.getExchange(ExchangeConst.DEFAULT_EXCHANGE);
        ExchangeFactory.declare(channel,exchange);
        QueueProperties queue = QueueFactory.getQueue(QueueConst.DEFAULT_QUEUE);
        QueueFactory.declare(channel,queue);
        defaultChannelFactory.bind(channel,exchange,queue);
        while (true){
            Scanner scanner = new Scanner(System.in);
            System.out.println("请输入要发送的消息:");
            String message = scanner.nextLine();
            if ("quit".equals(message)){
                scanner.close();
                break;
            }
            defaultChannelFactory.pulish(exchange,message);
        }

    }
}
