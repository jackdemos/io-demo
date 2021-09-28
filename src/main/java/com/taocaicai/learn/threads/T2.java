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
public class T2 {
  private int count = 10;

  public synchronized void m() {
    count--;
    System.out.println(Thread.currentThread().getName() + "count=" + count);
  }

  public static void main(String[] args) {
    T2 t2 = new T2();
    System.out.println(VM.current().details());
    System.out.println("------------------------------------------------");
    System.out.println(ClassLayout.parseInstance(t2).toPrintable());
  }
}
