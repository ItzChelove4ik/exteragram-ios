package com.google.android.gms.cast;

import com.google.android.gms.common.api.CommonStatusCodes;
import java.util.Locale;

/* loaded from: classes4.dex */
public final class CastStatusCodes extends CommonStatusCodes {
    public static final int APPLICATION_NOT_FOUND = 2004;
    public static final int APPLICATION_NOT_RUNNING = 2005;
    public static final int AUTHENTICATION_FAILED = 2000;
    public static final int CANCELED = 2002;
    public static final int DEVICE_CONNECTION_SUSPENDED = 2016;
    public static final int ERROR_CAST_PLATFORM_INCOMPATIBLE = 2110;
    public static final int ERROR_CAST_PLATFORM_NOT_CONNECTED = 2113;
    public static final int ERROR_DEVICE_ID_FLAGS_NOT_SET = 2115;
    public static final int ERROR_HOST_NOT_ALLOWED = 2112;
    public static final int ERROR_NO_CAST_CONFIGURATION = 2114;
    public static final int ERROR_SERVICE_CREATION_FAILED = 2200;
    public static final int ERROR_SERVICE_DISCONNECTED = 2201;
    public static final int ERROR_STOPPING_SERVICE_FAILED = 2202;
    public static final int ERROR_URL_INSEURE = 2111;
    public static final int FAILED = 2100;
    public static final int INTERNAL_ERROR = 8;
    public static final int INTERRUPTED = 14;
    public static final int INVALID_REQUEST = 2001;
    public static final int MEDIA_ERROR = 2104;
    public static final int MESSAGE_SEND_BUFFER_TOO_FULL = 2007;
    public static final int MESSAGE_TOO_LARGE = 2006;
    public static final int NETWORK_ERROR = 7;
    public static final int NOT_ALLOWED = 2003;
    public static final int REPLACED = 2103;
    public static final int SUCCESS = 0;
    public static final int TIMEOUT = 15;
    public static final int UNKNOWN_ERROR = 13;

    private CastStatusCodes() {
    }

    public static String getStatusCodeString(int r3) {
        if (r3 < (-999)) goto L24;
        if (r3 > 999) goto L24;
        if (r3 != 0) goto L8;
        return "SUCCESS";
    L8:
        if (r3 != 7) goto L10;
        return "NETWORK_ERROR";
    L10:
        if (r3 != 14) goto L12;
        return "INTERRUPTED";
    L12:
        if (r3 != 15) goto L14;
        return "TIMEOUT";
    L14:
        return CommonStatusCodes.getStatusCodeString(r3);
    L24:
        if (r3 < 2000) goto L51;
        if (r3 > 2049) goto L51;
        if (r3 == 2015) goto L48;
        switch(r3) {
            case 2000: goto L46;
            case 2001: goto L44;
            case 2002: goto L42;
            case 2003: goto L40;
            case 2004: goto L38;
            case 2005: goto L36;
            case 2006: goto L34;
            case 2007: goto L32;
            default: goto L31;
        };
    L32:
        return "MESSAGE_SEND_BUFFER_TOO_FULL";
    L34:
        return "MESSAGE_TOO_LARGE";
    L36:
        return "APPLICATION_NOT_RUNNING";
    L38:
        return "APPLICATION_NOT_FOUND";
    L40:
        return "NOT_ALLOWED";
    L42:
        return "CANCELED";
    L44:
        return "INVALID_REQUEST";
    L46:
        return "AUTHENTICATION_FAILED";
    L31:
        return String.format(Locale.ROOT, "%s %d", new Object[]{"Common cast status code", Integer.valueOf(r3)});
    L48:
        return "TCP_PROBER_FAIL_TO_VERIFY_DEVICE";
    L51:
        if (r3 < 2050) goto L58;
        if (r3 > 2059) goto L58;
        return String.format(Locale.ROOT, "%s %d", new Object[]{"Cast controller status code", Integer.valueOf(r3)});
    L58:
        if (r3 < 2100) goto L65;
        if (r3 > 2109) goto L65;
        return String.format(Locale.ROOT, "%s %d", new Object[]{"Media control channel status code", Integer.valueOf(r3)});
    L65:
        if (r3 < 2150) goto L72;
        if (r3 > 2169) goto L72;
        return String.format(Locale.ROOT, "%s %d", new Object[]{"Cast session status code", Integer.valueOf(r3)});
    L72:
        if (r3 < 2200) goto L79;
        if (r3 > 2219) goto L79;
        return String.format(Locale.ROOT, "%s %d", new Object[]{"Cast remote display status code", Integer.valueOf(r3)});
    L79:
        if (r3 < 2250) goto L86;
        if (r3 > 2299) goto L86;
        return String.format(Locale.ROOT, "%s %d", new Object[]{"Cast socket status code", Integer.valueOf(r3)});
    L86:
        if (r3 < 2300) goto L93;
        if (r3 > 2309) goto L93;
        return String.format(Locale.ROOT, "%s %d", new Object[]{"Cast service status code", Integer.valueOf(r3)});
    L93:
        if (r3 < 2310) goto L100;
        if (r3 > 2319) goto L100;
        return String.format(Locale.ROOT, "%s %d", new Object[]{"Endpoint switch status code", Integer.valueOf(r3)});
    L100:
        if (r3 < 2350) goto L107;
        if (r3 > 2359) goto L107;
        return String.format(Locale.ROOT, "%s %d", new Object[]{"Cast multizone device status code", Integer.valueOf(r3)});
    L107:
        if (r3 < 2400) goto L114;
        if (r3 > 2419) goto L114;
        return String.format(Locale.ROOT, "%s %d", new Object[]{"Cast relay casting status code", Integer.valueOf(r3)});
    L114:
        if (r3 < 2450) goto L121;
        if (r3 > 2469) goto L121;
        return String.format(Locale.ROOT, "%s %d", new Object[]{"Cast nearby casting status code", Integer.valueOf(r3)});
    L121:
        if (r3 < 2420) goto L128;
        if (r3 > 2439) goto L128;
        return String.format(Locale.ROOT, "%s %d", new Object[]{"Remote connection status code", Integer.valueOf(r3)});
    L128:
        if (r3 < 2470) goto L135;
        if (r3 > 2479) goto L135;
        return String.format(Locale.ROOT, "%s %d", new Object[]{"Cast application status code", Integer.valueOf(r3)});
    L135:
        if (r3 < 2490) goto L141;
        if (r3 > 2499) goto L141;
        return String.format(Locale.ROOT, "%s %d", new Object[]{"Cast media loading status code", Integer.valueOf(r3)});
    L141:
        return String.format(Locale.ROOT, "Unknown cast status code %d", new Object[]{Integer.valueOf(r3)});
    }
}
