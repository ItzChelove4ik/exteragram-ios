package kotlin.io;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\u0010\t\n\u0002\u0010\u0005\n\u0002\u0010\n\n\u0002\u0010\f\n\u0002\u0010\u000b\n\u0002\u0010\u0007\n\u0002\u0010\u0006\n\u0002\u0010\u0019\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a\u0014\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0087\u0088\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0004H\u0087\u0088\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0005H\u0087\u0088\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0006H\u0087\u0088\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0007H\u0087\u0088\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\bH\u0087\u0088\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\tH\u0087\u0088\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\nH\u0087\u0088\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000bH\u0087\u0088\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\fH\u0087\u0088\u0004\u001a\u0014\u0010\r\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u0087\u0088\u0004\u001a\u0012\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0004H\u0087\u0088\u0004\u001a\u0012\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0005H\u0087\u0088\u0004\u001a\u0012\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0006H\u0087\u0088\u0004\u001a\u0012\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0007H\u0087\u0088\u0004\u001a\u0012\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\bH\u0087\u0088\u0004\u001a\u0012\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\tH\u0087\u0088\u0004\u001a\u0012\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\nH\u0087\u0088\u0004\u001a\u0012\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u000bH\u0087\u0088\u0004\u001a\u0012\u0010\r\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\fH\u0087\u0088\u0004\u001a\n\u0010\r\u001a\u00020\u0001H\u0087\u0088\u0004\u001a\n\u0010\u000e\u001a\u00020\u000fH\u0087\u0080\u0004\u001a\f\u0010\u0010\u001a\u0004\u0018\u00010\u000fH\u0087\u0080\u0004\u001a\f\u0010\u0011\u001a\u0004\u0018\u00010\u000fH\u0086\u0080\u0004¨\u0006\u0012"}, d2 = {"print", _UrlKt.FRAGMENT_ENCODE_SET, "message", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "println", "readln", _UrlKt.FRAGMENT_ENCODE_SET, "readlnOrNull", "readLine", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@JvmName(name = "ConsoleKt")
/* loaded from: classes5.dex */
public final class ConsoleKt {
    @InlineOnly
    private static final void print(byte r1) {
        System.out.print(Byte.valueOf(r1));
    }

    @InlineOnly
    private static final void print(char r1) {
        System.out.print(r1);
    }

    @InlineOnly
    private static final void print(double r1) {
        System.out.print(r1);
    }

    @InlineOnly
    private static final void print(float r1) {
        System.out.print(r1);
    }

    @InlineOnly
    private static final void print(int r1) {
        System.out.print(r1);
    }

    @InlineOnly
    private static final void print(long r1) {
        System.out.print(r1);
    }

    @InlineOnly
    private static final void print(Object r1) {
        System.out.print(r1);
    }

    @InlineOnly
    private static final void print(short r1) {
        System.out.print(Short.valueOf(r1));
    }

    @InlineOnly
    private static final void print(boolean r1) {
        System.out.print(r1);
    }

    @InlineOnly
    private static final void print(char[] r1) {
        System.out.print(r1);
    }

    @InlineOnly
    private static final void println() {
        System.out.println();
    }

    @InlineOnly
    private static final void println(byte r1) {
        System.out.println(Byte.valueOf(r1));
    }

    @InlineOnly
    private static final void println(char r1) {
        System.out.println(r1);
    }

    @InlineOnly
    private static final void println(double r1) {
        System.out.println(r1);
    }

    @InlineOnly
    private static final void println(float r1) {
        System.out.println(r1);
    }

    @InlineOnly
    private static final void println(int r1) {
        System.out.println(r1);
    }

    @InlineOnly
    private static final void println(long r1) {
        System.out.println(r1);
    }

    @InlineOnly
    private static final void println(Object r1) {
        System.out.println(r1);
    }

    @InlineOnly
    private static final void println(short r1) {
        System.out.println(Short.valueOf(r1));
    }

    @InlineOnly
    private static final void println(boolean r1) {
        System.out.println(r1);
    }

    @InlineOnly
    private static final void println(char[] r1) {
        System.out.println(r1);
    }

    public static final String readLine() {
        return LineReader.INSTANCE.readLine(System.in, Charset.defaultCharset());
    }

    @SinceKotlin(version = "1.6")
    public static final String readln() {
        String r0 = readlnOrNull();
        if (r0 == null) goto L6;
        return r0;
    L6:
        throw new ReadAfterEOFException("EOF has already been reached");
    }

    @SinceKotlin(version = "1.6")
    public static final String readlnOrNull() {
        return readLine();
    }
}
