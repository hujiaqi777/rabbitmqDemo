import com.rabbitmq.client.*;
import listener.DefaultReturnListener;
import listener.DefaultShutdownListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeoutException;

public class MqProducer {
    private static final String EXCHANGE_NAME="exchange_demo";
    private static final String ROUTING_KEY="route_demo";
    private static final String QUEUE_NAME="queue_demo";
    private static final Integer PORT=5672;
    private static final String HOST="127.0.0.1";
    private final ConnectionFactory factory;


    public MqProducer() {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(HOST);
        connectionFactory.setPort(PORT);
        connectionFactory.setUsername("root");
        connectionFactory.setPassword("root");
        this.factory=connectionFactory;
    }

    public ConnectionFactory getFactory() {
        return factory;
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        MqProducer mqProducer = new MqProducer();
        Connection connection = mqProducer.getFactory().newConnection();
        //���Ӷ������ӹرռ�����
        connection.addShutdownListener(new DefaultShutdownListener());
        Channel channel = connection.createChannel();
        //ͨ������ͨ���رռ�����
        channel.addShutdownListener(new DefaultShutdownListener());
        //ͨ��������Ϣ���ؼ�����
        channel.addReturnListener(new DefaultReturnListener());
        //���ݽ�����
        HashMap<String, Object> arg = new HashMap<>();
        arg.put("alternate-exchange","Ae_demo");
        channel.exchangeDeclare("Ae_demo","fanout",true,false,arg);
        channel.queueDeclare("Ae_demo_queue",true,false,false,null);
        channel.queueBind("Ae_demo_queue","Ae_demo","");
        //ֱ��·�ɽ�����
        channel.exchangeDeclare(EXCHANGE_NAME,"direct",true,false,null);
        channel.queueDeclare(QUEUE_NAME,true,false,false,null);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
        //������Ϣ����
        HashMap<String, Object> argss = new HashMap<>();
        argss.put("x-message-ttl",6000);
        channel.queueDeclare(QUEUE_NAME,true,false,false,argss);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
        //���ö��й���
        HashMap<String, Object> argsss = new HashMap<>();
        argss.put("x-expires",6000);
        channel.queueDeclare(QUEUE_NAME,true,false,false,argsss);
        channel.queueBind(QUEUE_NAME,EXCHANGE_NAME,ROUTING_KEY);
        String text="hello world";
        for (int i = 0; i < 10; i++) {
            String message=text+"-"+(i+1);
            if (i>5){
                channel.basicPublish(EXCHANGE_NAME,"demo",true, null,message.getBytes());
            }
            channel.basicPublish(EXCHANGE_NAME,ROUTING_KEY, MessageProperties.PERSISTENT_TEXT_PLAIN,message.getBytes());
        }
        channel.close();
        connection.close();
    }
}
