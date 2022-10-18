package com.baeldung.grpc.client;

import com.baeldung.grpc.HelloRequest;
import com.baeldung.grpc.HelloResponse;
import com.baeldung.grpc.streaming.Stock;

import java.lang.reflect.Constructor;

import static org.junit.jupiter.api.Assertions.*;

class GrpcClientTest {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Class<?> aClass = Class.forName("com.baeldung.grpc.HelloRequest");
        Constructor<?>[] constructors = aClass.getConstructors();
        for (Constructor<?> constructor : constructors) {
            System.out.println(constructor);
        }


//        System.out.println(HelloRequest.getDescriptor().getIndex());
//        System.out.println(HelloResponse.getDescriptor().getIndex());


    }

}


