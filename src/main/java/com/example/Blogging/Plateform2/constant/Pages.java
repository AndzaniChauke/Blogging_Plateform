package com.example.Blogging.Plateform2.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access= AccessLevel.PRIVATE)
public class Pages {

    public static final String HOME="index";

    public static final String LOGIN="login";

    @NoArgsConstructor(access= AccessLevel.PRIVATE)
    public static final class Registration{
        public static final String ROUTE="registration";


    }

}
