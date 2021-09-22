package com.taocaicai.learn.stream;

import org.openjdk.jol.info.ClassLayout;

/**
 * @project demo
 * @author Oakley
 * @created 2021-09-23 02:51:2:51
 * @package com.taocaicai.learn.stream
 * @description TODO
 * @since: 0.0.0.1
 */
public class MyJOL {
  public static void main(String[] args) {
    System.out.println(ClassLayout.parseClass(User.class).toPrintable());
    System.out.println("------------------------------------------");
    System.out.println(ClassLayout.parseClass(Object.class).toPrintable());
    System.out.println("------------------------------------------");
    System.out.println(ClassLayout.parseClass(String.class).toPrintable());
  }
}
