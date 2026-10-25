package com.google.zxing.common;

import com.google.zxing.FormatException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public enum CharacterSetECI extends Enum<CharacterSetECI> {
    private static final /* synthetic */ CharacterSetECI[] $VALUES = null;
    public static final CharacterSetECI ASCII = null;
    public static final CharacterSetECI Big5 = null;
    public static final CharacterSetECI Cp1250 = null;
    public static final CharacterSetECI Cp1251 = null;
    public static final CharacterSetECI Cp1252 = null;
    public static final CharacterSetECI Cp1256 = null;
    public static final CharacterSetECI Cp437 = null;
    public static final CharacterSetECI EUC_KR = null;
    public static final CharacterSetECI GB18030 = null;
    public static final CharacterSetECI ISO8859_1 = null;
    public static final CharacterSetECI ISO8859_10 = null;
    public static final CharacterSetECI ISO8859_11 = null;
    public static final CharacterSetECI ISO8859_13 = null;
    public static final CharacterSetECI ISO8859_14 = null;
    public static final CharacterSetECI ISO8859_15 = null;
    public static final CharacterSetECI ISO8859_16 = null;
    public static final CharacterSetECI ISO8859_2 = null;
    public static final CharacterSetECI ISO8859_3 = null;
    public static final CharacterSetECI ISO8859_4 = null;
    public static final CharacterSetECI ISO8859_5 = null;
    public static final CharacterSetECI ISO8859_6 = null;
    public static final CharacterSetECI ISO8859_7 = null;
    public static final CharacterSetECI ISO8859_8 = null;
    public static final CharacterSetECI ISO8859_9 = null;
    private static final Map<String, CharacterSetECI> NAME_TO_ECI = null;
    public static final CharacterSetECI SJIS = null;
    public static final CharacterSetECI UTF8 = null;
    public static final CharacterSetECI UnicodeBigUnmarked = null;
    private static final Map<Integer, CharacterSetECI> VALUE_TO_ECI = null;
    private final String[] otherEncodingNames;
    private final int[] values;

    private static /* synthetic */ CharacterSetECI[] $values() {
        return new CharacterSetECI[]{Cp437, ISO8859_1, ISO8859_2, ISO8859_3, ISO8859_4, ISO8859_5, ISO8859_6, ISO8859_7, ISO8859_8, ISO8859_9, ISO8859_10, ISO8859_11, ISO8859_13, ISO8859_14, ISO8859_15, ISO8859_16, SJIS, Cp1250, Cp1251, Cp1252, Cp1256, UnicodeBigUnmarked, UTF8, ASCII, Big5, GB18030, EUC_KR};
    }

    static {
        Cp437 = new CharacterSetECI("Cp437", 0, new int[]{0, 2}, new String[0]);
        ISO8859_1 = new CharacterSetECI("ISO8859_1", 1, new int[]{1, 3}, new String[]{"ISO-8859-1"});
        ISO8859_2 = new CharacterSetECI("ISO8859_2", 2, 4, new String[]{"ISO-8859-2"});
        ISO8859_3 = new CharacterSetECI("ISO8859_3", 3, 5, new String[]{"ISO-8859-3"});
        ISO8859_4 = new CharacterSetECI("ISO8859_4", 4, 6, new String[]{"ISO-8859-4"});
        ISO8859_5 = new CharacterSetECI("ISO8859_5", 5, 7, new String[]{"ISO-8859-5"});
        ISO8859_6 = new CharacterSetECI("ISO8859_6", 6, 8, new String[]{"ISO-8859-6"});
        ISO8859_7 = new CharacterSetECI("ISO8859_7", 7, 9, new String[]{"ISO-8859-7"});
        ISO8859_8 = new CharacterSetECI("ISO8859_8", 8, 10, new String[]{"ISO-8859-8"});
        ISO8859_9 = new CharacterSetECI("ISO8859_9", 9, 11, new String[]{"ISO-8859-9"});
        ISO8859_10 = new CharacterSetECI("ISO8859_10", 10, 12, new String[]{"ISO-8859-10"});
        ISO8859_11 = new CharacterSetECI("ISO8859_11", 11, 13, new String[]{"ISO-8859-11"});
        ISO8859_13 = new CharacterSetECI("ISO8859_13", 12, 15, new String[]{"ISO-8859-13"});
        ISO8859_14 = new CharacterSetECI("ISO8859_14", 13, 16, new String[]{"ISO-8859-14"});
        ISO8859_15 = new CharacterSetECI("ISO8859_15", 14, 17, new String[]{"ISO-8859-15"});
        ISO8859_16 = new CharacterSetECI("ISO8859_16", 15, 18, new String[]{"ISO-8859-16"});
        SJIS = new CharacterSetECI("SJIS", 16, 20, new String[]{"Shift_JIS"});
        Cp1250 = new CharacterSetECI("Cp1250", 17, 21, new String[]{"windows-1250"});
        Cp1251 = new CharacterSetECI("Cp1251", 18, 22, new String[]{"windows-1251"});
        Cp1252 = new CharacterSetECI("Cp1252", 19, 23, new String[]{"windows-1252"});
        Cp1256 = new CharacterSetECI("Cp1256", 20, 24, new String[]{"windows-1256"});
        UnicodeBigUnmarked = new CharacterSetECI("UnicodeBigUnmarked", 21, 25, new String[]{"UTF-16BE", "UnicodeBig"});
        UTF8 = new CharacterSetECI("UTF8", 22, 26, new String[]{"UTF-8"});
        ASCII = new CharacterSetECI("ASCII", 23, new int[]{27, 170}, new String[]{"US-ASCII"});
        Big5 = new CharacterSetECI("Big5", 24, 28);
        GB18030 = new CharacterSetECI("GB18030", 25, 29, new String[]{"GB2312", "EUC_CN", "GBK"});
        EUC_KR = new CharacterSetECI("EUC_KR", 26, 30, new String[]{"EUC-KR"});
        $VALUES = $values();
        VALUE_TO_ECI = new HashMap();
        NAME_TO_ECI = new HashMap();
        CharacterSetECI[] r0 = values();
        int r2 = r0.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L13;
        CharacterSetECI r4 = r0[r3];
        if (Charset.isSupported(r4.name()) == false) goto L12;
        int[] r5 = r4.values;
        int r6 = r5.length;
        int r7 = 0;
    L7:
        if (r7 >= r6) goto L9;
        VALUE_TO_ECI.put(Integer.valueOf(r5[r7]), r4);
        r7 = r7 + 1;
        goto L7
    L9:
        NAME_TO_ECI.put(r4.name(), r4);
        String[] r52 = r4.otherEncodingNames;
        int r62 = r52.length;
        int r72 = 0;
    L10:
        if (r72 >= r62) goto L12;
        NAME_TO_ECI.put(r52[r72], r4);
        r72 = r72 + 1;
    L12:
        r3 = r3 + 1;
        goto L3
    }

    CharacterSetECI(String r2, int r3, int r4) {
        this(r2, r3, new int[]{r4}, new String[0]);
    }

    CharacterSetECI(String r1, int r2, int r3, String... r4) {
        this.values = new int[]{r3};
        this.otherEncodingNames = r4;
    }

    CharacterSetECI(String r1, int r2, int[] r3, String... r4) {
        this.values = r3;
        this.otherEncodingNames = r4;
    }

    public static CharacterSetECI getCharacterSetECI(Charset r1) {
        return NAME_TO_ECI.get(r1.name());
    }

    public static CharacterSetECI getCharacterSetECIByName(String r1) {
        return NAME_TO_ECI.get(r1);
    }

    public static CharacterSetECI getCharacterSetECIByValue(int r1) {
        if (r1 < 0) goto L8;
        if (r1 >= 900) goto L8;
        return VALUE_TO_ECI.get(Integer.valueOf(r1));
    L8:
        throw FormatException.getFormatInstance();
    }

    public static CharacterSetECI valueOf(String r1) {
        return (CharacterSetECI) Enum.valueOf(CharacterSetECI.class, r1);
    }

    public static CharacterSetECI[] values() {
        return (CharacterSetECI[]) $VALUES.clone();
    }

    public Charset getCharset() {
        return Charset.forName(name());
    }

    public int getValue() {
        return this.values[0];
    }
}
