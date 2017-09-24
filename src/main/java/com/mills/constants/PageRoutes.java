package com.mills.constants;

public class PageRoutes {

    public static final String HOME = "/";
    public static final String METHOD_MANAGEMENT = "/methods";

    public static String redirect(String route)
    {
        return "redirect:" + route;
    }

}

