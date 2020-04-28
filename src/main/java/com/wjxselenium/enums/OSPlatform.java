package com.wjxselenium.enums;


public enum OSPlatform {

    Any(1, "any"),
    Linux(2, "Linux"),
    Mac_OS(3, "Mac OS"),
    Mac_OS_X(4, "Mac OS X"),
    Windows(5, "Windows"),
    OS2(6, "OS/2"),
    Solaris(7, "Solaris"),
    SunOS(8, "SunOS"),
    MPEiX(9, "MPE/iX"),
    HP_UX(10, "HP-UX"),
    AIX(11, "AIX"),
    OS390(12, "OS/390"),
    FreeBSD(13, "FreeBSD"),
    Irix(14, "Irix"),
    Digital_Unix(15, "Digital Unix"),
    NetWare_411(16, "NetWare"),
    OSF1(17, "OSF1"),
    OpenVMS(18, "OpenVMS"),
    Others(19, "Others"),
    ;

    private int value;
    private String desc;

    OSPlatform(int value,String desc){
        this.value = value;
        this.desc = desc;
    }

    public static OSPlatform getByValue(int value) {
        for (OSPlatform osPlatform : OSPlatform.values()) {
            if (osPlatform.getValue() == value) {
                return osPlatform;
            }
        }
        return null;
    }

    public int getValue() {
        return value;
    }

    public String getDesc() {
        return desc;
    }





}
