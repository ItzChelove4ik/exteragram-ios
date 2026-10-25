package kotlin.text;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmName;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\bÆ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u00058\u0006X\u0087\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u00058\u0006X\u0087\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\u000b\u001a\u00020\u00058GX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0011\u0010\u000e\u001a\u0004\u0018\u00010\u0005X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0015\u0010\u000f\u001a\u00020\u00058GX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0011\u001a\u0004\u0018\u00010\u0005X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0012\u001a\u00020\u00058GX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0013\u0010\rR\u0011\u0010\u0014\u001a\u0004\u0018\u00010\u0005X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lkotlin/text/Charsets;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "UTF_8", "Ljava/nio/charset/Charset;", "UTF_16", "UTF_16BE", "UTF_16LE", "US_ASCII", "ISO_8859_1", "UTF_32", "UTF32", "()Ljava/nio/charset/Charset;", "utf_32", "UTF_32LE", "UTF32_LE", "utf_32le", "UTF_32BE", "UTF32_BE", "utf_32be", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public final class Charsets {
    public static final Charsets INSTANCE = null;

    @JvmField
    public static final Charset ISO_8859_1 = null;

    @JvmField
    public static final Charset US_ASCII = null;

    @JvmField
    public static final Charset UTF_16 = null;

    @JvmField
    public static final Charset UTF_16BE = null;

    @JvmField
    public static final Charset UTF_16LE = null;

    @JvmField
    public static final Charset UTF_8 = null;
    private static volatile Charset utf_32;
    private static volatile Charset utf_32be;
    private static volatile Charset utf_32le;

    static {
        INSTANCE = new Charsets();
        UTF_8 = Charset.forName("UTF-8");
        UTF_16 = Charset.forName("UTF-16");
        UTF_16BE = Charset.forName("UTF-16BE");
        UTF_16LE = Charset.forName("UTF-16LE");
        US_ASCII = Charset.forName("US-ASCII");
        ISO_8859_1 = Charset.forName("ISO-8859-1");
    }

    private Charsets() {
    }

    @JvmName(name = "UTF32")
    public final Charset UTF32() {
        Charset r0 = utf_32;
        if (r0 != null) goto L6;
        Charset r02 = Charset.forName("UTF-32");
        utf_32 = r02;
        return r02;
    L6:
        return r0;
    }

    @JvmName(name = "UTF32_BE")
    public final Charset UTF32_BE() {
        Charset r0 = utf_32be;
        if (r0 != null) goto L6;
        Charset r02 = Charset.forName("UTF-32BE");
        utf_32be = r02;
        return r02;
    L6:
        return r0;
    }

    @JvmName(name = "UTF32_LE")
    public final Charset UTF32_LE() {
        Charset r0 = utf_32le;
        if (r0 != null) goto L6;
        Charset r02 = Charset.forName("UTF-32LE");
        utf_32le = r02;
        return r02;
    L6:
        return r0;
    }
}
