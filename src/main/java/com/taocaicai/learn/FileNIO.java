package com.taocaicai.learn;

import java.io.File;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @project demo
 * @author Oakley
 * @created 2021-09-19 01:35:1:35
 * @package com.taocaicai.learn
 * @description TODO
 * @since: 0.0.0.1
 */
public class FileNIO {
  public static void main(String[] args) throws Exception {
    File file = new File("E:\\IdeaProjects\\a.txt");
    /*构建通道*/
    FileChannel channel = new FileInputStream(file).getChannel();
    /*构建缓冲区*/
    ByteBuffer byteBuffer = ByteBuffer.allocate(1);
    /*开始读操作*/
    channel.read(byteBuffer);
    byteBuffer.flip();
    System.out.println((char) byteBuffer.get());
  }
}
