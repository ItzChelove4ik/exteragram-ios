package com.google.gson.internal.bind.util;

import j$.util.DesugarTimeZone;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;

/* loaded from: classes5.dex */
public abstract class ISO8601Utils {
    private static final TimeZone TIMEZONE_UTC = null;

    static {
        TIMEZONE_UTC = DesugarTimeZone.getTimeZone("UTC");
    }

    private static boolean checkOffset(String r1, int r2, char r3) {
        if (r2 < r1.length()) goto L5;
        return false;
    L5:
        if (r1.charAt(r2) != r3) goto L10;
        return true;
    L10:
        return false;
    }

    private static int indexOfNonDigit(String r2, int r3) {
    L3:
        if (r3 >= r2.length()) goto L12;
        char r0 = r2.charAt(r3);
        if (r0 < '0') goto L10;
        if (r0 > '9') goto L10;
        r3 = r3 + 1;
    L10:
        return r3;
    L12:
        return r2.length();
    }

    public static Date parse(String r19, ParsePosition r20) {
        int r6 = r20.getIndex();     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        int r7 = r6 + 4;     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        int r8 = parseInt(r19, r6, r7);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        if (checkOffset(r19, r7, SignatureVisitor.SUPER) == false) goto L6;
        r7 = r6 + 5;     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
    L6:
        int r62 = r7 + 2;     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        int r10 = parseInt(r19, r7, r62);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        if (checkOffset(r19, r62, SignatureVisitor.SUPER) == false) goto L9;
        r62 = r7 + 3;     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
    L9:
        int r72 = r62 + 2;     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        int r12 = parseInt(r19, r62, r72);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        boolean r13 = checkOffset(r19, r72, 'T');     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        if (r13 == true) goto L20;
        if (r19.length() > r72) goto L20;
        GregorianCalendar r0 = new GregorianCalendar(r8, r10 - 1, r12);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        r0.setLenient(false);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        r20.setIndex(r72);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        return r0.getTime();
    L20:
        if (r13 == false) goto L51;
        int r132 = r62 + 5;     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        int r73 = parseInt(r19, r62 + 3, r132);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        if (checkOffset(r19, r132, ':') == false) goto L24;
        r132 = r62 + 6;     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
    L24:
        int r63 = r132 + 2;     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        int r17 = parseInt(r19, r132, r63);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        if (checkOffset(r19, r63, ':') == false) goto L28;
        r63 = r132 + 3;     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
    L28:
        if (r19.length() <= r63) goto L49;
        char r133 = r19.charAt(r63);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        if (r133 == 'Z') goto L49;
        if (r133 == '+') goto L49;
        if (r133 == '-') goto L49;
        int r134 = r63 + 2;     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        int r14 = parseInt(r19, r63, r134);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        if (r14 <= 59) goto L39;
        if (r14 >= 63) goto L39;
        r14 = 59;
    L39:
        if (checkOffset(r19, r134, '.') == false) goto L48;
        int r9 = r63 + 3;     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        int r135 = indexOfNonDigit(r19, r63 + 4);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        int r64 = Math.min(r135, r63 + 6);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        int r18 = parseInt(r19, r9, r64);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        int r65 = r64 - r9;     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        if (r65 != 1) goto L43;
        r18 = r18 * 100;     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
    L47:
        int r66 = r73;
        r72 = r135;
        int r92 = r17;
        int r136 = r18;
    L53:
        if (r19.length() <= r72) goto L85;
        char r11 = r19.charAt(r72);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        if (r11 != 'Z') goto L58;
        TimeZone r02 = TIMEZONE_UTC;     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        int r74 = r72 + 1;     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
    L82:
        GregorianCalendar r3 = new GregorianCalendar(r02);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        r3.setLenient(false);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        r3.set(1, r8);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        r3.set(2, r10 - 1);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        r3.set(5, r12);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        r3.set(11, r66);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        r3.set(12, r92);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        r3.set(13, r14);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        r3.set(14, r136);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        r20.setIndex(r74);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        return r3.getTime();
    L58:
        if (r11 != '+') goto L60;
    L64:
        String r5 = r19.substring(r72);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        if (r5.length() >= 5) goto L68;
        r5 = r5.concat("00");     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
    L68:
        r74 = r72 + r5.length();     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        if (r5.equals("+0000") == false) goto L71;
    L81:
        r02 = TIMEZONE_UTC;     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        goto L82
    L71:
        if (r5.equals("+00:00") == true) goto L81;
        String r32 = "GMT".concat(r5);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        TimeZone r4 = DesugarTimeZone.getTimeZone(r32);     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        String r52 = r4.getID();     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        if (r52.equals(r32) == false) goto L76;
    L80:
        r02 = r4;
        goto L82
    L76:
        if (r52.replace(":", _UrlKt.FRAGMENT_ENCODE_SET).equals(r32) == true) goto L80;
        throw new IndexOutOfBoundsException("Mismatching time zone indicator: " + r32 + " given, resolves to " + r4.getID());     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
    L60:
        if (r11 == '-') goto L64;
        throw new IndexOutOfBoundsException("Invalid time zone indicator '" + r11 + "'");     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
    L85:
        throw new IllegalArgumentException("No time zone indicator");     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
    L43:
        if (r65 != 2) goto L47;
        r18 = r18 * 10;     // Catch: IllegalArgumentException -> L15 IndexOutOfBoundsException -> L17
        goto L47
    L48:
        r66 = r73;
        r72 = r134;
        r92 = r17;
        r136 = 0;
    L49:
        r72 = r63;
        r66 = r73;
        r92 = r17;
    L50:
        r136 = 0;
        r14 = 0;
        goto L53
    L51:
        r66 = 0;
        r92 = 0;
    L15:
        e = e;
    L86:
        if (r19 != null) goto L88;
        String r1 = null;
    L89:
        String r33 = e.getMessage();
        if (r33 != null) goto L92;
    L93:
        r33 = "(" + e.getClass().getName() + ")";
    L94:
        ParseException r42 = new ParseException("Failed to parse date [" + r1 + "]: " + r33, r20.getIndex());
        r42.initCause(e);
        throw r42;
    L92:
        if (r33.isEmpty() == false) goto L94;
    L88:
        r1 = "\"" + r19 + Typography.quote;
    L17:
        e = e;
        goto L86
    }

    private static int parseInt(String r5, int r6, int r7) {
        if (r6 < 0) goto L23;
        if (r7 > r5.length()) goto L23;
        if (r6 > r7) goto L23;
        if (r6 >= r7) goto L13;
        int r2 = r6 + 1;
        int r3 = Character.digit(r5.charAt(r6), 10);
        if (r3 < 0) goto L12;
        int r32 = -r3;
    L14:
        if (r2 >= r7) goto L21;
        int r4 = r2 + 1;
        int r22 = Character.digit(r5.charAt(r2), 10);
        if (r22 < 0) goto L19;
        r32 = (r32 * 10) - r22;
        r2 = r4;
        goto L14
    L19:
        throw new NumberFormatException("Invalid number: ".concat(r5.substring(r6, r7)));
    L21:
        return -r32;
    L12:
        throw new NumberFormatException("Invalid number: ".concat(r5.substring(r6, r7)));
    L13:
        r32 = 0;
        r2 = r6;
    L23:
        throw new NumberFormatException(r5);
    }
}
