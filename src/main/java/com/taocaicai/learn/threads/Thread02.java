package com.taocaicai.learn.threads;

/**
 * @project demo
 * @author Oakley
 * @created 2021-09-29 00:23:0:23
 * @package com.taocaicai.learn.threads
 * @description TODO
 * @since: 0.0.0.1
 */
public class Thread02 {
   static int a;
   static int b;
   static int x;
   static int y;

  public static void main(String[] args) {
    for (int i = 0; i < Integer.MAX_VALUE; i++) {
        System.out.println("执行了第" + i + "次(" + x + "，" + y + ")");
      new Thread(
              () -> {
                a = x;
                x = a;
              })
          .start();
      new Thread(
              () -> {
                b = y;
                y = b;
              })
          .start();
      if (x == 0 && y == 0) {
        System.out.println("执行了第" + i + "次(" + x + "，" + y + ")");
        break;
      }
    }
  }
}
