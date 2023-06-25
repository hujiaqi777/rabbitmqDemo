package queue;

import java.io.Serializable;
import java.util.HashMap;

public class QueueProperties implements Serializable {

    private String queueName;//队列名称
    private boolean isdurable;//持久化
    private boolean isExclusive;//排它
    private boolean isAutoDelete;//自动删除
    private HashMap<String, Object> args;//额外参数

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
