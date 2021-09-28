package com.taocaicai.learn.threads;

/**
 * @project demo
 * @author Oakley
 * @created 2021-09-29 01:40:1:40
 * @package com.taocaicai.learn.threads
 * @description TODO
 * @since: 0.0.0.1
 */
public class ThreadState01 {
  static class ThreadTest extends Thread {
    @Override
    public void run() {
      System.out.println("当前状态:" + this.getState());
      for (int i = 0; i < 10; i++) {
        try {
          Thread.sleep(500);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(i);
      }
    }
  }

  public static void main(String[] args) {
    ThreadTest t = new ThreadTest();
    System.out.println(t.getState());
    t.start();
    System.out.println(t.getState());
    try {
      t.join(1000);
      System.out.println(t.getState());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
