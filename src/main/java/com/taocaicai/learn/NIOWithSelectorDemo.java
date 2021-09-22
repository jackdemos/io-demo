package com.taocaicai.learn;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Base64;
import java.util.Iterator;
import java.util.Set;

/**
 * @project demo
 * @author Oakley
 * @created 2021-09-19 03:17:3:17
 * @package com.taocaicai.learn
 * @description TODO
 * @since: 0.0.0.1
 */
public class NIOWithSelectorDemo {
  private static Selector selector;
  private static ServerSocketChannel serverSocketChannel;

  public static void main(String[] args) throws Exception {
    System.out.println("启动服务");
    serverSocketChannel = createServerSocketChannel();
    for (; ; ) {
      Set<SelectionKey> selectionKeys = selector.selectedKeys();
      Iterator<SelectionKey> iterator = selectionKeys.iterator();
      while (iterator.hasNext()) {
        /*阻塞方法*/
        selector.select(100);
        /*获取已经准备好的通道*/
        SelectionKey selectionKey = iterator.next();
        if (selectionKey.isAcceptable()) {
          handleAccept((ServerSocketChannel) selectionKey.channel());
        }
        if (selectionKey.isWritable()) {
          handleWrite((SocketChannel) selectionKey.channel());
        }
        iterator.remove();
      }
    }
  }

  public static ServerSocketChannel createServerSocketChannel() throws Exception {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.configureBlocking(false);
    serverSocketChannel.bind(new InetSocketAddress(Utils.DEFAULT_PORT), Utils.BACK_LOG);

    selector = Selector.open();
    /*通道挂到选择器上，通过SelectionKey告诉选择器，我对什么事件感兴趣*/
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    return serverSocketChannel;
  }

  public static void handleWrite(SocketChannel socketChannel) {
    String resp = Utils.buildHttpResp();
    try {
      doSomeWork();
      socketChannel.write(ByteBuffer.wrap(resp.getBytes()));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static void doSomeWork() {
    System.out.println("do some work");
  }

  public static void handleAccept(ServerSocketChannel serverSocketChannel) throws Exception {
    SocketChannel socketChannel = serverSocketChannel.accept();
    System.out.println(socketChannel.getRemoteAddress());
    socketChannel.configureBlocking(false);

    socketChannel.register(selector, SelectionKey.OP_WRITE);
  }
}
