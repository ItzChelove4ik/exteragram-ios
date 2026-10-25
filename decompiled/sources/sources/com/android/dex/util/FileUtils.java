package com.android.dex.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.mvel2.util.ReflectionUtil$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class FileUtils {
    public static boolean hasArchiveSuffix(String r1) {
        if (r1.endsWith(".zip") == false) goto L5;
        return true;
    L5:
        if (r1.endsWith(".jar") == false) goto L7;
        return true;
    L7:
        if (r1.endsWith(".apk") == true) goto L14;
        return false;
    L14:
        return true;
    }

    public static byte[] readFile(File r6) {
        if (r6.exists() == true) goto L5;
        ReflectionUtil$$ExternalSyntheticBUOutline0.m(r6, ": file not found");
        return null;
    L5:
        if (r6.isFile() == true) goto L7;
        ReflectionUtil$$ExternalSyntheticBUOutline0.m(r6, ": not a file");
        return null;
    L7:
        if (r6.canRead() == false) goto L26;
        long r2 = r6.length();
        int r0 = (int) r2;
        if (r0 != r2) goto L24;
        byte[] r1 = new byte[r0];
        FileInputStream r22 = new FileInputStream(r6);     // Catch: IOException -> L18
        int r3 = 0;
    L12:
        if (r0 <= 0) goto L20;
        int r4 = r22.read(r1, r3, r0);     // Catch: IOException -> L18
        if (r4 == (-1)) goto L17;
        r3 = r3 + r4;     // Catch: IOException -> L18
        r0 = r0 - r4;     // Catch: IOException -> L18
        goto L12
    L17:
        throw new RuntimeException(r6 + ": unexpected EOF");     // Catch: IOException -> L18
    L20:
        r22.close();     // Catch: IOException -> L18
        return r1;
    L18:
        e = move-exception;
        throw new RuntimeException(r6 + ": trouble reading", e);
    L24:
        ReflectionUtil$$ExternalSyntheticBUOutline0.m(r6, ": file too long");
        return null;
    L26:
        ReflectionUtil$$ExternalSyntheticBUOutline0.m(r6, ": file not readable");
        return null;
    }

    public static byte[] readFile(String r1) {
        return readFile(new File(r1));
    }
}
