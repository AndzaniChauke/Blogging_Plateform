package com.example.Blogging.Plateform2.constant;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class RequestRouting {

    public static final String HOME="/";


    @NoArgsConstructor(access= AccessLevel.PRIVATE)
    public static final class Cache{
        public static final String ROUTE="/cache";

        public static final String SAVE=ROUTE+"/save";

        public static final String GET=ROUTE+"/get";

    }

    @NoArgsConstructor(access= AccessLevel.PRIVATE)
    public static final class Login{
        public static final String ROUTE="/login";


    }

    @NoArgsConstructor(access= AccessLevel.PRIVATE)
    public static final class Registration{
        public static final String ROUTE="/registration";


    }
}
