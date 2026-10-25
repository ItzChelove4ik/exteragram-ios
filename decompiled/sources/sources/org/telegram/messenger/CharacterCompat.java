package org.telegram.messenger;

/* loaded from: classes5.dex */
public class CharacterCompat {
    public static final char MIN_HIGH_SURROGATE = 55296;
    public static final char MIN_LOW_SURROGATE = 56320;
    public static final int MIN_SUPPLEMENTARY_CODE_POINT = 65536;

    public CharacterCompat() {
    }

    public static char highSurrogate(int r1) {
        return (char) ((r1 >>> 10) + 55232);
    }

    public static char lowSurrogate(int r1) {
        return (char) ((r1 & 1023) + 56320);
    }
}
