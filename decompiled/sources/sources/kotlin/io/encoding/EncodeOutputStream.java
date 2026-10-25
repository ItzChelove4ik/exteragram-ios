package kotlin.io.encoding;

import com.android.dx.util.Hex$$ExternalSyntheticBUOutline0;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@ExperimentalEncodingApi
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\r\b\u0003\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\nH\u0016J \u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\n2\u0006\u0010\u0014\u001a\u00020\nH\u0016J\b\u0010\u0015\u001a\u00020\u0010H\u0016J\b\u0010\u0016\u001a\u00020\u0010H\u0016J\"\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\nH\u0082\u0080\u0004J\n\u0010\u001a\u001a\u00020\u0010H\u0082\u0080\u0004J\"\u0010\u001b\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\n2\u0006\u0010\u0019\u001a\u00020\nH\u0082\u0080\u0004J\n\u0010\u001c\u001a\u00020\u0010H\u0082\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\bX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\t\u001a\u00020\nX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000b\u001a\u00020\fX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\r\u001a\u00020\fX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000e\u001a\u00020\nX\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lkotlin/io/encoding/EncodeOutputStream;", "Ljava/io/OutputStream;", "output", "base64", "Lkotlin/io/encoding/Base64;", "<init>", "(Ljava/io/OutputStream;Lkotlin/io/encoding/Base64;)V", "isClosed", _UrlKt.FRAGMENT_ENCODE_SET, "lineLength", _UrlKt.FRAGMENT_ENCODE_SET, "symbolBuffer", _UrlKt.FRAGMENT_ENCODE_SET, "byteBuffer", "byteBufferLength", "write", _UrlKt.FRAGMENT_ENCODE_SET, "b", "source", "offset", "length", "flush", "close", "copyIntoByteBuffer", "startIndex", "endIndex", "encodeByteBufferIntoOutput", "encodeIntoOutput", "checkOpen", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class EncodeOutputStream extends OutputStream {
    private final Base64 base64;
    private final byte[] byteBuffer;
    private int byteBufferLength;
    private boolean isClosed;
    private int lineLength;
    private final OutputStream output;
    private final byte[] symbolBuffer;

    public EncodeOutputStream(OutputStream r1, Base64 r2) {
        this.output = r1;
        this.base64 = r2;
        if (r2.isMimeScheme$kotlin_stdlib() == false) goto L5;
        int r12 = r2.getMimeLineLength$kotlin_stdlib();
    L6:
        this.lineLength = r12;
        this.symbolBuffer = new byte[1024];
        this.byteBuffer = new byte[3];
        return;
    L5:
        r12 = -1;
        goto L6
    }

    private final void checkOpen() {
        if (this.isClosed == true) goto L5;
        return;
    L5:
        Model$$ExternalSyntheticBUOutline0.m("The output stream is closed.");
    }

    private final int copyIntoByteBuffer(byte[] r5, int r6, int r7) {
        int r72 = Math.min(3 - this.byteBufferLength, r7 - r6);
        ArraysKt.copyInto(r5, this.byteBuffer, this.byteBufferLength, r6, r6 + r72);
        int r52 = this.byteBufferLength + r72;
        this.byteBufferLength = r52;
        if (r52 != 3) goto L5;
        encodeByteBufferIntoOutput();
    L5:
        return r72;
    }

    private final void encodeByteBufferIntoOutput() {
        if (encodeIntoOutput(this.byteBuffer, 0, this.byteBufferLength) != 4) goto L6;
        this.byteBufferLength = 0;
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
    }

    private final int encodeIntoOutput(byte[] r7, int r8, int r9) {
        int r72 = this.base64.encodeIntoByteArray(r7, this.symbolBuffer, 0, r8, r9);
        if (this.lineLength != 0) goto L9;
        this.output.write(Base64.Default.getMimeLineSeparatorSymbols$kotlin_stdlib());
        this.lineLength = this.base64.getMimeLineLength$kotlin_stdlib();
        if (r72 <= this.base64.getMimeLineLength$kotlin_stdlib()) goto L9;
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return 0;
    L9:
        this.output.write(this.symbolBuffer, 0, r72);
        this.lineLength -= r72;
        return r72;
    }

    @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.isClosed == true) goto L9;
        this.isClosed = true;
        if (this.byteBufferLength == 0) goto L7;
        encodeByteBufferIntoOutput();
    L7:
        this.output.close();
        return;
    }

    @Override // java.io.OutputStream, java.io.Flushable
    public void flush() {
        checkOpen();
        this.output.flush();
    }

    @Override // java.io.OutputStream
    public void write(int r4) {
        checkOpen();
        byte[] r0 = this.byteBuffer;
        int r1 = this.byteBufferLength;
        int r2 = r1 + 1;
        this.byteBufferLength = r2;
        r0[r1] = (byte) r4;
        if (r2 != 3) goto L6;
        encodeByteBufferIntoOutput();
        return;
    }

    @Override // java.io.OutputStream
    public void write(byte[] r8, int r9, int r10) {
        checkOpen();
        if (r9 < 0) goto L30;
        if (r10 < 0) goto L30;
        int r0 = r9 + r10;
        if (r0 > r8.length) goto L30;
        if (r10 == 0) goto L35;
        int r102 = this.byteBufferLength;
        if (r102 >= 3) goto L28;
        if (r102 == 0) goto L16;
        r9 = r9 + copyIntoByteBuffer(r8, r9, r0);
        if (this.byteBufferLength == 0) goto L16;
        return;
    L16:
        if ((r9 + 3) > r0) goto L26;
        if (this.base64.isMimeScheme$kotlin_stdlib() == false) goto L20;
        int r103 = this.lineLength;
    L21:
        int r104 = Math.min(r103 / 4, (r0 - r9) / 3);
        int r3 = (r104 * 3) + r9;
        if (encodeIntoOutput(r8, r9, r3) != (r104 * 4)) goto L24;
        r9 = r3;
        goto L16
    L24:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return;
    L20:
        r103 = this.symbolBuffer.length;
        goto L21
    L26:
        ArraysKt.copyInto(r8, this.byteBuffer, 0, r9, r0);
        this.byteBufferLength = r0 - r9;
        return;
    L28:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return;
    L35:
        return;
    L30:
        Hex$$ExternalSyntheticBUOutline0.m("offset: ", r9, ", length: ", r10, ", source size: ", r8.length);
    }
}
