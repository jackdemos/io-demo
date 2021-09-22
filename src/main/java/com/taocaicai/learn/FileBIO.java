package com.taocaicai.learn;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @project demo
 * @author Oakley
 * @created 2021-09-19 01:31:1:31
 * @package com.taocaicai.learn
 * @description TODO
 * @since: 0.0.0.1
 */
public class FileBIO {
  public static void main(String[] args) throws Exception {
    File file = new File("E:\\IdeaProjects\\a.txt");
    InputStream inputStream = new FileInputStream(file);
    int read = inputStream.read();
    System.out.println("aaaa");
    System.out.println((char) read );
  }
}
