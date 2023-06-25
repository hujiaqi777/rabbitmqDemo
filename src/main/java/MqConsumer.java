import com.rabbitmq.client.*;

import java.io.IOException;
import java.sql.Time;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;



public class MqConsumer {
    private static final String QUEUE_NAME="queue_demo";
    private static final Integer PORT=5672;
    private static final String HOST="127.0.0.1";

        public static void main (String[] args) throws TimeoutException, InterruptedException, IOException {
            Address[] addresses = new Address[]{ new Address (HOST, PORT) };
                ConnectionFactory factory = new ConnectionFactory ();
                factory.setUsername ("root");
                factory.setPassword ("root");
                Connection connection = factory.newConnection(addresses);//创建连接
                final Channel channel = connection.createChannel();//创建信道
                channel .basicQos (64);
                Consumer consumer = new DefaultConsumer (channel){
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
                channel.basicConsume(QUEUE_NAME,consumer);
            TimeUnit.SECONDS.sleep(5);
            channel.close();
            connection.close();
        }
}


