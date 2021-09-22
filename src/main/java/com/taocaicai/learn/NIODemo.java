package com.taocaicai.learn;

import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NIODemo {
  public static void main(String[] args) throws Exception {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.bind(new InetSocketAddress(Utils.DEFAULT_PORT), Utils.BACK_LOG);
    serverSocketChannel.configureBlocking(true);
    System.out.println("启动服务");
    for (;;) {
      System.out.println("准备开门接客");
      SocketChannel socketChannel = serverSocketChannel.accept();
      if (socketChannel != null) {
        System.out.println("接收到客户端请求" + socketChannel.getRemoteAddress());
        String resp = Utils.buildHttpResp();

        /*模拟操作业务*/
        doSomeWork();
        socketChannel.write(ByteBuffer.wrap(resp.getBytes()));
      }
      System.out.println("print 1");
    }
  }

  private static void doSomeWork() {
    System.out.println("模拟业务操作");
  }
}
