package com.fulgay.bilenyum.domain;

/**
 * Test
 * 8/11/2020
 *
 * @author yazilim_ipuclari
 * @since 8/11/2020
 */
public class Test {


    public static void main(String[] args) {
        User user = new User();
        user.setName(null);

        System.out.println(user.getName());
    }
}
