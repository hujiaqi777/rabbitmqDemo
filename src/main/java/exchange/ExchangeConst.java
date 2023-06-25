package exchange;

import java.util.HashMap;

public class ExchangeConst {
    //默认交换机参数
    public final static String DEFAULT_EXCHANGE=
            "{\n" +
            "\t\"exchangeName\": \"amq.direct\",\n" +
            "\t\"routeKey\": \"test.demo\",\n" +
            "\t\"exchangeType\": \"direct\",\n" +
            "\t\"isdurable\": true,\n" +
            "\t\"isAutoDelete\": false,\n" +
            "\t\"isInternal\": false,\n" +
            "\t\"args\": null\n" +
            "}";



}
