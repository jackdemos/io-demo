package com.taocaicai.learn.threads;

/**
 * @project demo
 * @author Oakley
 * @created 2021-09-29 01:55:1:55
 * @package com.taocaicai.learn.threads
 * @description TODO
 * @since: 0.0.0.1
 */
public class T4 {

  public synchronized void m1() {
    System.out.println(Thread.currentThread().getName() + "  m1 start");
    try {
      Thread.sleep(10000);
      System.out.println(Thread.currentThread().getName() + "  m1 end");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void m2() {
    try {
      Thread.sleep(10000);
      System.out.println(Thread.currentThread().getName() + "    m2");
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    T4 t4 = new T4();
    new Thread(t4::m1, "t1").start();
    new Thread(t4::m2, "t2").start();
  }
}
