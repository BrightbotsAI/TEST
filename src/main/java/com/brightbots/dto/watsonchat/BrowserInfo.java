package com.brightbots.dto.watsonchat;

import java.util.ArrayList;

public class BrowserInfo{

    public String page_url;
    public String browser_name;
    public ArrayList<String> ip_address_list;
    public String client_ip_address;
    public String language;
    public String browser_OS;
    public String browser_version;
    public String screen_resolution;
    public String user_agent;

    public String getPage_url() {
        return page_url;
    }

    public String getBrowser_name() {
        return browser_name;
    }

    public ArrayList<String> getIp_address_list() {
        return ip_address_list;
    }

    public String getClient_ip_address() {
        return client_ip_address;
    }

    public String getLanguage() {
        return language;
    }

    public String getBrowser_OS() {
        return browser_OS;
    }

    public String getBrowser_version() {
        return browser_version;
    }

    public String getScreen_resolution() {
        return screen_resolution;
    }

    public String getUser_agent() {
        return user_agent;
    }
}
