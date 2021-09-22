package com.taocaicai.learn;

/**
 * @author Oakley
 * @project demo
 * @created 2021-09-19 01:53:1:53
 * @package com.taocaicai.learn
 * @description TODO
 * @since: 0.0.0.1
 */
class Utils {
    public static final String HTTP_SEPARATOR = "\r\n";
    public static final int DEFAULT_PORT = 8888;
    public static final int BACK_LOG = 1024;

    public static String buildHttpResp() {
        StringBuilder stringBuilder = new StringBuilder();
        String str = "<h1>Hello world</h1>";
        stringBuilder.append("HTTP/1.1 200 OK").append(HTTP_SEPARATOR);
        stringBuilder.append("connection:Close").append(HTTP_SEPARATOR);
        stringBuilder.append("content-type:text/html").append(HTTP_SEPARATOR);
        stringBuilder.append("content-length:" + str.length()).append(HTTP_SEPARATOR);
        stringBuilder.append(HTTP_SEPARATOR);
        stringBuilder.append(str);
        return stringBuilder.toString();
    }
}
