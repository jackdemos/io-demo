package com.taocaicai.learn.threads;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 * @project demo
 * @author Oakley
 * @created 2021-09-29 01:55:1:55
 * @package com.taocaicai.learn.threads
 * @description TODO
 * @since: 0.0.0.1
 */
public class T3 implements Runnable {
//  private volatile int count = 10;
  private int count = 10;

  @Override
  public synchronized void run() {
    count--;
    System.out.println(Thread.currentThread().getName() + " count=" + count);
  }

  public static void main(String[] args) {
    T3 t2 = new T3();
    for (int i = 0; i < 10; i++) {
      new Thread(t2, "thread-" + i).start();
    }
    System.out.println(VM.current().details());
    System.out.println("------------------------------------------------");
    System.out.println(ClassLayout.parseInstance(t2).toPrintable());
  }
}
