package com.google.common.base;

import java.util.Locale;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
abstract class Platform {
    private static final PatternCompiler patternCompiler = null;

    /* renamed from: com.google.common.base.Platform$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class JdkPatternCompiler implements PatternCompiler {
        private JdkPatternCompiler() {
        }

        public /* synthetic */ JdkPatternCompiler(AnonymousClass1 r1) {
            this();
        }
    }

    static {
        patternCompiler = loadPatternCompiler();
    }

    public static String emptyToNull(String r1) {
        if (stringIsNullOrEmpty(r1) == false) goto L6;
        return null;
    L6:
        return r1;
    }

    public static String formatCompact4Digits(double r1) {
        return String.format(Locale.ROOT, "%.4g", new Object[]{Double.valueOf(r1)});
    }

    public static String lenientFormat(String r0, Object... r1) {
        return Strings.lenientFormat(r0, r1);
    }

    private static PatternCompiler loadPatternCompiler() {
        return new JdkPatternCompiler(null);
    }

    public static String nullToEmpty(String r0) {
        if (r0 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return r0;
    }

    public static boolean stringIsNullOrEmpty(String r0) {
        if (r0 != null) goto L4;
        return true;
    L4:
        if (r0.isEmpty() == true) goto L10;
        return false;
    L10:
        return true;
    }

    public static String stringValueOf(Object r0) {
        return String.valueOf(r0);
    }
}
