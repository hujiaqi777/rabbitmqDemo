package consumer;

import channel.DefaultChannelFactory;
import com.rabbitmq.client.*;
import exchange.ExchangeConst;
import exchange.ExchangeFactory;
import exchange.ExchangeProperties;
import queue.QueueConst;
import queue.QueueFactory;
import queue.QueueProperties;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class GetClient {
    public static void main(String[] args) {
        DefaultChannelFactory defaultChannelFactory = new DefaultChannelFactory();
        final Channel channel = defaultChannelFactory.getChannel();
        try {
            channel .basicQos (64);
            Consumer consumer = new DefaultConsumer(channel){
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    System.out.println("recv message: " + new String(body));
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    channel.basicAck(envelope.getDeliveryTag(),false);

                }
            };
            QueueProperties queue = QueueFactory.getQueue(QueueConst.DEFAULT_QUEUE);
            channel.basicConsume(queue.getQueueName(),consumer);
            TimeUnit.SECONDS.sleep(5);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
