package org.commonmark.internal.util;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes5.dex */
public abstract class Escaping {
    private static final Pattern BACKSLASH_OR_AMP = null;
    private static final Pattern ENTITY_OR_ESCAPED_CHAR = null;
    private static final Pattern ESCAPE_IN_URI = null;
    private static final char[] HEX_DIGITS = null;
    private static final Replacer UNESCAPE_REPLACER = null;
    private static final Replacer URI_REPLACER = null;
    private static final Pattern WHITESPACE = null;

    /* renamed from: org.commonmark.internal.util.Escaping$1, reason: invalid class name */
    public static class AnonymousClass1 implements Replacer {
        public AnonymousClass1() {
        }

        @Override // org.commonmark.internal.util.Escaping.Replacer
        public void replace(String r2, StringBuilder r3) {
            if (r2.charAt(0) != '\\') goto L6;
            r3.append(r2, 1, r2.length());
            return;
        L6:
            r3.append(Html5Entities.entityToString(r2));
        }
    }

    /* renamed from: org.commonmark.internal.util.Escaping$2, reason: invalid class name */
    public static class AnonymousClass2 implements Replacer {
        public AnonymousClass2() {
        }

        @Override // org.commonmark.internal.util.Escaping.Replacer
        public void replace(String r5, StringBuilder r6) {
            if (r5.startsWith("%") == true) goto L5;
            byte[] r4 = r5.getBytes(Charset.forName("UTF-8"));
            int r52 = r4.length;
            int r0 = 0;
        L11:
            if (r0 >= r52) goto L13;
            byte r1 = r4[r0];
            r6.append('%');
            r6.append(Escaping.access$000()[(r1 >> 4) & 15]);
            r6.append(Escaping.access$000()[r1 & 15]);
            r0 = r0 + 1;
            goto L11
        L13:
            return;
        L5:
            if (r5.length() != 3) goto L8;
            r6.append(r5);
            return;
        L8:
            r6.append("%25");
            r6.append(r5, 1, r5.length());
        }
    }

    public interface Replacer {
        void replace(String r1, StringBuilder r2);
    }

    static {
        BACKSLASH_OR_AMP = Pattern.compile("[\\\\&]");
        ENTITY_OR_ESCAPED_CHAR = Pattern.compile("\\\\[!\"#$%&'()*+,./:;<=>?@\\[\\\\\\]^_`{|}~-]|&(?:#x[a-f0-9]{1,6}|#[0-9]{1,7}|[a-z][a-z0-9]{1,31});", 2);
        ESCAPE_IN_URI = Pattern.compile("(%[a-fA-F0-9]{0,2}|[^:/?#@!$&'()*+,;=a-zA-Z0-9\\-._~])");
        HEX_DIGITS = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        WHITESPACE = Pattern.compile("[ \t\r\n]+");
        UNESCAPE_REPLACER = new AnonymousClass1();
        URI_REPLACER = new AnonymousClass2();
    }

    public static /* synthetic */ char[] access$000() {
        return HEX_DIGITS;
    }

    public static String normalizeLabelContent(String r1) {
        String r12 = r1.trim().toLowerCase(Locale.ROOT);
        return WHITESPACE.matcher(r12).replaceAll(" ");
    }

    public static String normalizeReference(String r2) {
        return normalizeLabelContent(r2.substring(1, r2.length() - 1));
    }

    private static String replaceAll(Pattern r3, String r4, Replacer r5) {
        Matcher r32 = r3.matcher(r4);
        if (r32.find() == true) goto L5;
        return r4;
    L5:
        StringBuilder r0 = new StringBuilder(r4.length() + 16);
        int r1 = 0;
    L6:
        r0.append(r4, r1, r32.start());
        r5.replace(r32.group(), r0);
        r1 = r32.end();
        if (r32.find() == true) goto L6;
        if (r1 == r4.length()) goto L12;
        r0.append(r4, r1, r4.length());
    L12:
        return r0.toString();
    }

    public static String unescapeString(String r2) {
        if (BACKSLASH_OR_AMP.matcher(r2).find() == true) goto L5;
        return r2;
    L5:
        return replaceAll(ENTITY_OR_ESCAPED_CHAR, r2, UNESCAPE_REPLACER);
    }
}
