package org.telegram.messenger;

import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes5.dex */
public final class EmuInputDevicesDetector {
    private static final String INPUT_DEVICES_FILE = "/proc/bus/input/devices";
    private static final String NAME_PREFIX = "N: Name=\"";
    private static final String[] RESTRICTED_DEVICES = null;

    static {
        RESTRICTED_DEVICES = new String[]{"bluestacks", "memuhyperv", "virtualbox"};
    }

    private EmuInputDevicesDetector() {
    }

    public static boolean detect() {
        List<String> r0 = getInputDevicesNames();
        if (r0 == null) goto L14;
        Iterator<String> r02 = r0.iterator();
    L6:
        if (r02.hasNext() == false) goto L14;
        String r2 = r02.next();
        String[] r3 = RESTRICTED_DEVICES;
        int r4 = r3.length;
        int r5 = 0;
    L8:
        if (r5 >= r4) goto L6;
        if (r2.toLowerCase().contains(r3[r5]) == true) goto L11;
        r5 = r5 + 1;
        goto L8
    L11:
        return true;
    L14:
        return false;
    }

    private static List<String> getInputDevicesNames() {
        File r0 = new File(INPUT_DEVICES_FILE);
        if (r0.canRead() == true) goto L19;
        return null;
    L19:
        ArrayList r1 = new ArrayList();     // Catch: IOException -> L13
        BufferedReader r3 = new BufferedReader(new InputStreamReader(new FileInputStream(r0)));     // Catch: IOException -> L13
    L6:
        String r02 = r3.readLine();     // Catch: IOException -> L13
        if (r02 == null) goto L15;
        if (r02.startsWith(NAME_PREFIX) == false) goto L6;
        String r03 = r02.substring(9, r02.length() - 1);     // Catch: IOException -> L13
        if (TextUtils.isEmpty(r03) == true) goto L6;
        r1.add(r03);     // Catch: IOException -> L13
        goto L6
    L15:
        r3.close();     // Catch: IOException -> L13
        return r1;
    L13:
        e = move-exception;
        FileLog.e(e);
        return null;
    }
}
