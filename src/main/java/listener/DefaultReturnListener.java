package listener;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ReturnListener;

import java.io.IOException;
/*
    消息返回监听器，当消息没匹配到对应的交换机与路由键，设置mandatory为true返回消息到生产者
 */
public class DefaultReturnListener implements ReturnListener {
    @Override
    public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
        String message=new String(bytes);
        System.out.println(message);
    }
}
