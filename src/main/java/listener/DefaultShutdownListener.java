package listener;

import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;
/*
���ùر���������
 connection����channel���ڹر�״̬����ô��෽�������Է�������쳣�رյ���Ϣ
 */
public class DefaultShutdownListener implements ShutdownListener {
    @Override
    public void shutdownCompleted(ShutdownSignalException e) {
        e.isHardError();
        e.getReason();
        e.getReference();
        e.getCause();
    }
}
