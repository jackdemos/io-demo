package com.taocaicai.learn.stream;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

/**
 * @project demo
 * @author Oakley
 * @created 2021-09-23 02:19:2:19
 * @package com.taocaicai.learn.stream
 * @description TODO
 * @since: 0.0.0.1
 */
@Data
@AllArgsConstructor
@ToString
public class User {
  private String name;
  private int age;
  private int salary;

  public synchronized  int getMyAge(){
    return  this.age;
  }

}
