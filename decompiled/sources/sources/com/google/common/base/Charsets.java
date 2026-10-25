package com.google.common.base;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/* loaded from: classes.dex */
public abstract class Charsets {

    @Deprecated
    public static final Charset ISO_8859_1 = null;

    @Deprecated
    public static final Charset US_ASCII = null;

    @Deprecated
    public static final Charset UTF_16 = null;

    @Deprecated
    public static final Charset UTF_16BE = null;

    @Deprecated
    public static final Charset UTF_16LE = null;

    @Deprecated
    public static final Charset UTF_8 = null;

    static {
        US_ASCII = StandardCharsets.US_ASCII;
        ISO_8859_1 = StandardCharsets.ISO_8859_1;
        UTF_8 = StandardCharsets.UTF_8;
        UTF_16BE = StandardCharsets.UTF_16BE;
        UTF_16LE = StandardCharsets.UTF_16LE;
        UTF_16 = StandardCharsets.UTF_16;
    }
}
