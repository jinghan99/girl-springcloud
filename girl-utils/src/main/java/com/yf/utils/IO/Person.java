package com.yf.utils.IO;

import java.io.Serializable;

/**
 * @Package com.yf.utils.IO
 * @Description: TODO
 * @author: jingh
 * @date 2019/2/28 10:17
 */
public class Person implements Serializable{

    private static final long serialVersionUID = 8656128222714547171L;
    private String name;
    private int age;


    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
