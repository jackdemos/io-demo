package com.taocaicai.learn;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class BIODemo {
  public static void main(String[] args) throws Exception {
    ServerSocket serverSocket = new ServerSocket(Utils.DEFAULT_PORT,Utils.BACK_LOG,null);
    System.out.println("启动服务");
    while (true) {
      System.out.println("准备开门接客");
      Socket socket = serverSocket.accept();
      System.out.println("接收到客户端请求"+socket.getRemoteSocketAddress());
      BufferedWriter bufferedWriter = buildBufferWriter(socket.getOutputStream());

      /*模拟操作业务*/
      doSomeWork();
      bufferedWriter.write(Utils.buildHttpResp());
      bufferedWriter.flush();
    }
  }

  private static void doSomeWork() {
    System.out.println("模拟业务操作");
  }

  private static BufferedWriter buildBufferWriter(OutputStream outputStream) {
    return new BufferedWriter(new PrintWriter(outputStream));
  }
}
