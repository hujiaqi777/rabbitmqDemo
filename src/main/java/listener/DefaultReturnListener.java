package listener;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.ReturnListener;

import java.io.IOException;
/*
    ��Ϣ���ؼ�����������Ϣûƥ�䵽��Ӧ�Ľ�������·�ɼ�������mandatoryΪtrue������Ϣ��������
 */
public class DefaultReturnListener implements ReturnListener {
    @Override
    public void handleReturn(int i, String s, String s1, String s2, AMQP.BasicProperties basicProperties, byte[] bytes) throws IOException {
        String message=new String(bytes);
        System.out.println(message);
    }
}
