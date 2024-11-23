package com.example.helloworld.learningkotlin.lambda;

public class LambdaJavaTest {

    public static void main(String[] args) {


        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("run run run");
            }
        });

        new Thread(() -> System.out.println("run run run"));
    }
}
