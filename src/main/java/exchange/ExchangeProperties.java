package exchange;

import java.io.Serializable;
import java.util.HashMap;

public class ExchangeProperties implements Serializable {
    private String exchangeName;
    private String routeKey;
    private String exchangeType;
    private boolean isdurable;
    private boolean isAutoDelete;
    private boolean isInternal;
    private HashMap<String, Object> args;

    public void setExchangeName(String exchangeName) {
        this.exchangeName = exchangeName;
    }

    public void setRouteKey(String routeKey) {
        this.routeKey = routeKey;
    }

    public void setExchangeType(String exchangeType) {
        this.exchangeType = exchangeType;
    }

    public void setIsdurable(boolean isdurable) {
        this.isdurable = isdurable;
    }

    public void setAutoDelete(boolean autoDelete) {
        isAutoDelete = autoDelete;
    }

    public void setInternal(boolean internal) {
        isInternal = internal;
    }

    public void setArgs(HashMap<String, Object> args) {
        this.args = args;
    }

    public String getExchangeName() {
        return exchangeName;
    }

    public String getExchangeType() {
        return exchangeType;
    }

    public boolean isIsdurable() {
        return isdurable;
    }

    public boolean isAutoDelete() {
        return isAutoDelete;
    }

    public boolean isInternal() {
        return isInternal;
    }

    public HashMap<String, Object> getArgs() {
        return args;
    }

    public String getRouteKey() {
        return routeKey;
    }
}
