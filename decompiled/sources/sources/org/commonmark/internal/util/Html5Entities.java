package org.commonmark.internal.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mvel2.asm.Constants$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class Html5Entities {
    private static final Map<String, String> NAMED_CHARACTER_REFERENCES = null;
    private static final Pattern NUMERIC_PATTERN = null;

    static {
        NAMED_CHARACTER_REFERENCES = readEntities();
        NUMERIC_PATTERN = Pattern.compile("^&#[Xx]?");
    }

    public static String entityToString(String r5) {
        Matcher r1 = NUMERIC_PATTERN.matcher(r5);
        if (r1.find() == true) goto L5;
        String r0 = r5.substring(1, r5.length() - 1);
        String r02 = NAMED_CHARACTER_REFERENCES.get(r0);
        if (r02 == null) goto L17;
        return r02;
    L17:
        return r5;
    L5:
        if (r1.end() != 2) goto L7;
        int r2 = 10;
    L18:
        int r52 = Integer.parseInt(r5.substring(r1.end(), r5.length() - 1), r2);     // Catch: IllegalArgumentException -> L13
        if (r52 != 0) goto L11;
        return "�";
    L11:
        return new String(Character.toChars(r52));
    L13:
        return "�";
    L7:
        r2 = 16;
        goto L18
    }

    private static Map<String, String> readEntities() {
        HashMap r0 = new HashMap();
        BufferedReader r3 = new BufferedReader(new InputStreamReader(Html5Entities.class.getResourceAsStream("/org/commonmark/internal/util/entities.properties"), Charset.forName("UTF-8")));     // Catch: IOException -> L23
    L31:
        String r1 = r3.readLine();     // Catch: Throwable -> L11
        if (r1 == null) goto L13;
        if (r1.length() == 0) goto L31;
        int r2 = r1.indexOf("=");     // Catch: Throwable -> L11
        r0.put(r1.substring(0, r2), r1.substring(r2 + 1));     // Catch: Throwable -> L11
        goto L31
    L13:
        r3.close();     // Catch: IOException -> L23
        r0.put("NewLine", "\n");
        return r0;
    L11:
        th = move-exception;
        throw th;     // Catch: Throwable -> L17
    L17:
        th = move-exception;
        r3.close();     // Catch: Throwable -> L20
    L22:
        throw th;     // Catch: IOException -> L23
    L20:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IOException -> L23
    L23:
        e = move-exception;
        Constants$$ExternalSyntheticBUOutline0.m("Failed reading data for HTML named character references", e);
        return null;
    }
}
