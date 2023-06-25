package queue;

import java.io.Serializable;
import java.util.HashMap;

public class QueueProperties implements Serializable {

    private String queueName;//��������
    private boolean isdurable;//�־û�
    private boolean isExclusive;//����
    private boolean isAutoDelete;//�Զ�ɾ��
    private HashMap<String, Object> args;//�������

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public void setIsdurable(boolean isdurable) {
        this.isdurable = isdurable;
    }

    public void setExclusive(boolean exclusive) {
        isExclusive = exclusive;
    }

    public void setAutoDelete(boolean autoDelete) {
        isAutoDelete = autoDelete;
    }

    public void setArgs(HashMap<String, Object> args) {
        this.args = args;
    }

    public String getQueueName() {
        return queueName;
    }

    public boolean isIsdurable() {
        return isdurable;
    }

    public boolean isExclusive() {
        return isExclusive;
    }

    public boolean isAutoDelete() {
        return isAutoDelete;
    }

    public HashMap<String, Object> getArgs() {
        return args;
    }
}
