package listener;

import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;
/*
设置关闭生命周期
 connection或者channel处于关闭状态后调用此类方法，可以方便监听异常关闭的信息
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
