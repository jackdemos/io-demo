package com.taocaicai.learn.stream;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @project demo
 * @author Oakley
 * @created 2021-09-23 02:18:2:18
 * @package com.taocaicai.learn.stream
 * @description TODO
 * @since: 0.0.0.1
 */
public class MySteam {
  public static void main(String[] args) {
    List<User> userList =
        Arrays.asList(
                new User("张三", 22, 4000),
                new User("JACK", 20, 2000),
                new User("JACKC", 21, 2000),
                new User("张LAN", 22, 4000));

    Predicate<User> predicate1 = user -> user.getAge() > 20;
    Predicate<User> predicate2 = user -> user.getSalary() < 4000;
    List<User> collect =
        userList.stream()
            //.filter(predicate1.or(predicate2))
            .filter(predicate1.and(predicate2))
            .collect(Collectors.toList());
    System.out.println(collect);
  }
}
