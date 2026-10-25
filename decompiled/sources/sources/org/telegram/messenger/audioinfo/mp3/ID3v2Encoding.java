package org.telegram.messenger.audioinfo.mp3;

import java.nio.charset.Charset;

/* loaded from: classes5.dex */
public enum ID3v2Encoding extends Enum<ID3v2Encoding> {
    private static final /* synthetic */ ID3v2Encoding[] $VALUES = null;
    public static final ID3v2Encoding ISO_8859_1 = null;
    public static final ID3v2Encoding UTF_16 = null;
    public static final ID3v2Encoding UTF_16BE = null;
    public static final ID3v2Encoding UTF_8 = null;
    private final Charset charset;
    private final int zeroBytes;

    private static /* synthetic */ ID3v2Encoding[] $values() {
        return new ID3v2Encoding[]{ISO_8859_1, UTF_16, UTF_16BE, UTF_8};
    }

    static {
        ISO_8859_1 = new ID3v2Encoding("ISO_8859_1", 0, Charset.forName("ISO-8859-1"), 1);
        UTF_16 = new ID3v2Encoding("UTF_16", 1, Charset.forName("UTF-16"), 2);
        UTF_16BE = new ID3v2Encoding("UTF_16BE", 2, Charset.forName("UTF-16BE"), 2);
        UTF_8 = new ID3v2Encoding("UTF_8", 3, Charset.forName("UTF-8"), 1);
        $VALUES = $values();
    }

    ID3v2Encoding(String r1, int r2, Charset r3, int r4) {
        this.charset = r3;
        this.zeroBytes = r4;
    }

    public static ID3v2Encoding valueOf(String r1) {
        return (ID3v2Encoding) Enum.valueOf(ID3v2Encoding.class, r1);
    }

    public static ID3v2Encoding[] values() {
        return (ID3v2Encoding[]) $VALUES.clone();
    }

    public Charset getCharset() {
        return this.charset;
    }

    public int getZeroBytes() {
        return this.zeroBytes;
    }
}
