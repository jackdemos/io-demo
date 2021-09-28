package com.taocaicai.learn.threads;

import org.openjdk.jol.info.ClassLayout;

/**
 * @project demo
 * @author Oakley
 * @created 2021-09-29 01:55:1:55
 * @package com.taocaicai.learn.threads
 * @description TODO
 * @since: 0.0.0.1
 */
public class T1 {
  private int count = 10;

  public void m() {
    synchronized (this) {
      count--;
      System.out.println(Thread.currentThread().getName() + "count=" + count);
    }
  }

  public static void main(String[] args) {
    T1 a = new T1();
    System.out.println(ClassLayout.parseClass(a.getClass()).toPrintable());
  }
}
