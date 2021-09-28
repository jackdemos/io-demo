package com.taocaicai.learn.threads;

/**
 * @project demo
 * @author Oakley
 * @created 2021-09-28 23:30:23:30
 * @package com.taocaicai.learn.threads
 * @description TODO
 * @since: 0.0.0.1
 */
public class MyThread {
  private int num = 0;

  public MyThread() {

    new Thread(
            () -> {
              System.out.println(this.num);
            })
        .start();
  }

  public static void main(String[] args) {
    new MyThread();
  }
}
