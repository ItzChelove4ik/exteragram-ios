package com.chaquo.python.internal;

import com.sun.jna.Native$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import org.mvel2.MVEL$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public class Common {
    public static final String ABI_COMMON = "common";
    public static final String ASSET_APP = "app";
    public static final String ASSET_BOOTSTRAP = "bootstrap";
    public static final String ASSET_BOOTSTRAP_NATIVE = "bootstrap-native";
    public static final String ASSET_BUILD_JSON = "build.json";
    public static final String ASSET_CACERT = "cacert.pem";
    public static final String ASSET_DIR = "chaquopy";
    public static final String ASSET_REQUIREMENTS = "requirements";
    public static final String ASSET_STDLIB = "stdlib";
    public static final int COMPILE_SDK_VERSION = 36;
    public static final String DEFAULT_PYTHON_VERSION = "3.11";
    public static final String MIN_AGP_VERSION = "7.3.0";
    public static final int MIN_SDK_VERSION = 24;
    public static final Map<String, String> PYTHON_VERSIONS = null;
    public static List<String> PYTHON_VERSIONS_SHORT;

    static {
        LinkedHashMap r0 = new LinkedHashMap();
        PYTHON_VERSIONS = r0;
        r0.put("3.11.10", "1");
        PYTHON_VERSIONS_SHORT = new ArrayList();
        Iterator r02 = r0.keySet().iterator();
    L4:
        if (r02.hasNext() == false) goto L6;
        String r1 = (String) r02.next();
        PYTHON_VERSIONS_SHORT.add(r1.substring(0, r1.lastIndexOf(46)));
        goto L4
    }

    public Common() {
    }

    public static String assetZip(String r1) {
        return assetZip(r1, null);
    }

    public static String assetZip(String r2, String r3) {
        if (r3 != null) goto L7;
        return r2 + ".imy";
    L7:
        return r2 + "-" + r3 + ".imy";
    }

    public static String osName() {
        String r0 = System.getProperty("os.name");
        String[] r1 = {"linux", "mac", "windows"};
        int r2 = 0;
    L4:
        if (r2 >= 3) goto L9;
        String r3 = r1[r2];
        if (r0.toLowerCase(Locale.ENGLISH).startsWith(r3) == true) goto L7;
        r2 = r2 + 1;
        goto L4
    L7:
        return r3;
    L9:
        MVEL$$ExternalSyntheticBUOutline0.m("unknown os.name: ", r0);
        return null;
    }

    public static List<String> supportedAbis(String r5) {
        if (PYTHON_VERSIONS_SHORT.contains(r5) == false) goto L9;
        ArrayList r0 = new ArrayList();
        r0.add("arm64-v8a");
        r0.add("x86_64");
        if (Arrays.asList(new String[]{"3.8", "3.9", "3.10", DEFAULT_PYTHON_VERSION}).contains(r5) == false) goto L7;
        r0.add("armeabi-v7a");
        r0.add("x86");
    L7:
        r0.sort(null);
        return r0;
    L9:
        Native$$ExternalSyntheticBUOutline0.m("Unknown Python version: '", r5, "'");
        return null;
    }
}
