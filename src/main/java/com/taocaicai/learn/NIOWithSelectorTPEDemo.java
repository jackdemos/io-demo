package com.taocaicai.learn;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @project demo
 * @author Oakley
 * @created 2021-09-19 03:51:3:51
 * @package com.taocaicai.learn
 * @description TODO
 * @since: 0.0.0.1
 */
public class NIOWithSelectorTPEDemo {

  static final int POLLER_NUM = 1;
  static Poller[] pollers;
  static ThreadPoolExecutor poolExecutor = buildThreadPoolExecutor();

  public static ServerSocketChannel createServerSocketChannel() throws Exception {
    ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.configureBlocking(true);
    serverSocketChannel.bind(new InetSocketAddress(Utils.DEFAULT_PORT), Utils.BACK_LOG);
    return serverSocketChannel;
  }

  public static void initPoller() throws Exception {
    pollers = new Poller[POLLER_NUM];
    for (int i = 0; i < pollers.length; i++) {
      Poller poller = new Poller();
      poller.init();
      pollers[i] = poller;
    }
  }

  public static void startPoller() {
    for (int i = 0; i < pollers.length; i++) {
      pollers[i].start();
    }
  }

  private static ThreadPoolExecutor buildThreadPoolExecutor() {
    return new ThreadPoolExecutor(5,
    10,
    1000,
    TimeUnit.SECONDS,
    new LinkedBlockingQueue<>(),
            new ThreadPoolExecutor.AbortPolicy());
  }

  public static void main(String[] args) throws Exception {
    ServerSocketChannel serverSocketChannel = createServerSocketChannel();
    System.out.println("启动服务器");
    initPoller();
    startPoller();
    poolExecutor.prestartAllCoreThreads();
    int m = pollers.length - 1;
    long count = 0;
    for (; ; ) {
      SocketChannel socketChannel = serverSocketChannel.accept();
      System.out.println(socketChannel.getRemoteAddress());
      pollers[(int) (count++ & m)].addSocketChannel(socketChannel);
    }
  }
}

class Poller extends Thread {
  private Selector selector;
  private BlockingQueue<SocketChannel> socketChannelBlockingQueue = new LinkedBlockingQueue<>();
  private AtomicBoolean atomicBoolean = new AtomicBoolean();

  public void init() throws Exception {
    selector = Selector.open();
  }

  public void addSocketChannel(SocketChannel socketChannel) {
    try {
      socketChannelBlockingQueue.put(socketChannel);
      if (atomicBoolean.get()) {
        selector.wakeup();
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void run() {
    while (true) {
      try {
        while (true) {
          SocketChannel socketChannel = socketChannelBlockingQueue.poll();
          if (socketChannel != null) {
            NIOWithSelectorTPEDemo.poolExecutor.execute(
                () -> {
                  try {
                    socketChannel.configureBlocking(false);
                    doSomeWork();
                    String resp = Utils.buildHttpResp();
                    socketChannel.register(
                        selector, SelectionKey.OP_WRITE, ByteBuffer.wrap(resp.getBytes()));

                  } catch (Exception e) {

                  }
                });
            continue;
          }
          break;
        }
        atomicBoolean.set(true);
        selector.select(1000);

        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
          SelectionKey next = iterator.next();
          if (next.isWritable()) {
            SocketChannel sc = (SocketChannel) next.channel();
            try {
              sc.write((ByteBuffer) next.attachment());
            } catch (Exception e) {
              e.printStackTrace();
            }
          } else {
            System.out.println("未知SelectionKey");
          }
          iterator.remove();
        }

      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void doSomeWork() {
    System.out.println("AAAAAAAAAAAAAA");
  }
}
