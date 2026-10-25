package kotlin.io.encoding;

import com.android.dx.util.Hex$$ExternalSyntheticBUOutline0;
import com.exteragram.messenger.plugins.PluginsConstants;
import j$.io.DesugarInputStream;
import j$.io.InputStreamRetargetInterface;
import java.io.InputStream;
import java.io.OutputStream;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@ExperimentalEncodingApi
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u000b\b\u0003\u0018\u00002\u00020\u0001B\u0019\bF\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\u0014\u001a\u00020\u000fH\u0016J \u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000fH\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J*\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u000fH\u0082\u0080\u0004J\"\u0010\u001f\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0017\u001a\u00020\u000fH\u0082\u0080\u0004J\n\u0010 \u001a\u00020\u0019H\u0082\u0080\u0004J\n\u0010!\u001a\u00020\u0019H\u0082\u0080\u0004J\u0012\u0010\"\u001a\u00020\u000f2\u0006\u0010\u001e\u001a\u00020\u000fH\u0082\u0080\u0004J\n\u0010#\u001a\u00020\u000fH\u0082\u0080\u0004R\u000f\u0010\u0002\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\bX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\t\u001a\u00020\bX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\u000bX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\f\u001a\u00020\u000bX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\r\u001a\u00020\u000bX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000e\u001a\u00020\u000fX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0010\u001a\u00020\u000fX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0015\u0010\u0011\u001a\u00020\u000f8BX\u0082\u0084\b¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013¨\u0006$"}, d2 = {"Lkotlin/io/encoding/DecodeInputStream;", "Ljava/io/InputStream;", PluginsConstants.Settings.TYPE_INPUT, "base64", "Lkotlin/io/encoding/Base64;", "<init>", "(Ljava/io/InputStream;Lkotlin/io/encoding/Base64;)V", "isClosed", _UrlKt.FRAGMENT_ENCODE_SET, "isEOF", "singleByteBuffer", _UrlKt.FRAGMENT_ENCODE_SET, "symbolBuffer", "byteBuffer", "byteBufferStartIndex", _UrlKt.FRAGMENT_ENCODE_SET, "byteBufferEndIndex", "byteBufferLength", "getByteBufferLength", "()I", "read", "destination", "offset", "length", "close", _UrlKt.FRAGMENT_ENCODE_SET, "decodeSymbolBufferInto", "dst", "dstOffset", "dstEndIndex", "symbolBufferLength", "copyByteBufferInto", "resetByteBufferIfEmpty", "shiftByteBufferToStartIfNeeded", "handlePaddingSymbol", "readNextSymbol", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class DecodeInputStream extends InputStream implements InputStreamRetargetInterface {
    private final Base64 base64;
    private final byte[] byteBuffer;
    private int byteBufferEndIndex;
    private int byteBufferStartIndex;
    private final InputStream input;
    private boolean isClosed;
    private boolean isEOF;
    private final byte[] singleByteBuffer;
    private final byte[] symbolBuffer;

    public DecodeInputStream(InputStream r1, Base64 r2) {
        this.input = r1;
        this.base64 = r2;
        this.singleByteBuffer = new byte[1];
        this.symbolBuffer = new byte[1024];
        this.byteBuffer = new byte[1024];
    }

    private final void copyByteBufferInto(byte[] r4, int r5, int r6) {
        byte[] r0 = this.byteBuffer;
        int r1 = this.byteBufferStartIndex;
        ArraysKt.copyInto(r0, r4, r5, r1, r1 + r6);
        this.byteBufferStartIndex += r6;
        resetByteBufferIfEmpty();
    }

    private final int decodeSymbolBufferInto(byte[] r7, int r8, int r9, int r10) {
        int r3 = this.byteBufferEndIndex;
        this.byteBufferEndIndex = r3 + this.base64.decodeIntoByteArray(this.symbolBuffer, this.byteBuffer, r3, 0, r10);
        int r92 = Math.min(getByteBufferLength(), r9 - r8);
        copyByteBufferInto(r7, r8, r92);
        shiftByteBufferToStartIfNeeded();
        return r92;
    }

    private final int getByteBufferLength() {
        return this.byteBufferEndIndex - this.byteBufferStartIndex;
    }

    private final int handlePaddingSymbol(int r4) {
        this.symbolBuffer[r4] = Base64.padSymbol;
        if ((r4 & 3) != 2) goto L10;
        int r0 = readNextSymbol();
        if (r0 < 0) goto L8;
        this.symbolBuffer[r4 + 1] = (byte) r0;
    L8:
        return r4 + 2;
    L10:
        return r4 + 1;
    }

    private final int readNextSymbol() {
        if (this.base64.isMimeScheme$kotlin_stdlib() == false) goto L5;
    L6:
        int r0 = this.input.read();
        if (r0 == (-1)) goto L10;
        if (Base64Kt.isInMimeAlphabet(r0) == false) goto L6;
    L10:
        return r0;
    L5:
        return this.input.read();
    }

    private final void resetByteBufferIfEmpty() {
        if (this.byteBufferStartIndex != this.byteBufferEndIndex) goto L6;
        this.byteBufferStartIndex = 0;
        this.byteBufferEndIndex = 0;
        return;
    }

    private final void shiftByteBufferToStartIfNeeded() {
        byte[] r0 = this.byteBuffer;
        int r1 = r0.length;
        int r2 = this.byteBufferEndIndex;
        if (((this.symbolBuffer.length / 4) * 3) <= (r1 - r2)) goto L6;
        ArraysKt.copyInto(r0, r0, 0, this.byteBufferStartIndex, r2);
        this.byteBufferEndIndex -= this.byteBufferStartIndex;
        this.byteBufferStartIndex = 0;
        return;
    }

    @Override // java.io.InputStream, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.isClosed == true) goto L6;
        this.isClosed = true;
        this.input.close();
        return;
    }

    @Override // java.io.InputStream
    public int read() {
        int r0 = this.byteBufferStartIndex;
        if (r0 >= this.byteBufferEndIndex) goto L6;
        int r1 = this.byteBuffer[r0] & UByte.MAX_VALUE;
        this.byteBufferStartIndex = r0 + 1;
        resetByteBufferIfEmpty();
        return r1;
    L6:
        int r02 = read(this.singleByteBuffer, 0, 1);
        if (r02 == (-1)) goto L13;
        if (r02 == 1) goto L10;
        Segment$$ExternalSyntheticBUOutline1.m("Unreachable");
        return 0;
    L10:
        return this.singleByteBuffer[0] & UByte.MAX_VALUE;
    L13:
        return -1;
    }

    @Override // java.io.InputStream
    public int read(byte[] r10, int r11, int r12) {
        if (r11 < 0) goto L46;
        if (r12 < 0) goto L46;
        int r1 = r11 + r12;
        if (r1 > r10.length) goto L46;
        if (this.isClosed == false) goto L10;
        Model$$ExternalSyntheticBUOutline0.m("The input stream is closed.");
        return 0;
    L10:
        if (this.isEOF == false) goto L12;
        return -1;
    L12:
        if (r12 != 0) goto L15;
        return 0;
    L15:
        if (getByteBufferLength() < r12) goto L18;
        copyByteBufferInto(r10, r11, r12);
        return r12;
    L18:
        int r122 = (((r12 - getByteBufferLength()) + 2) / 3) * 4;
        int r2 = r11;
    L19:
        boolean r4 = this.isEOF;
        if (r4 == true) goto L39;
        if (r122 <= 0) goto L39;
        int r42 = Math.min(this.symbolBuffer.length, r122);
        int r5 = 0;
    L23:
        boolean r6 = this.isEOF;
        if (r6 == true) goto L33;
        if (r5 >= r42) goto L33;
        int r62 = readNextSymbol();
        if (r62 != (-1)) goto L29;
        this.isEOF = true;
        goto L23
    L29:
        if (r62 != 61) goto L30;
        r5 = handlePaddingSymbol(r5);
        this.isEOF = true;
        goto L23
    L30:
        this.symbolBuffer[r5] = (byte) r62;
        r5 = r5 + 1;
    L33:
        if (r6 == true) goto L38;
        if (r5 == r42) goto L38;
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return 0;
    L38:
        r122 = r122 - r5;
        r2 = r2 + decodeSymbolBufferInto(r10, r2, r1, r5);
    L39:
        if (r2 != r11) goto L43;
        if (r4 == false) goto L43;
        return -1;
    L43:
        return r2 - r11;
    L46:
        Hex$$ExternalSyntheticBUOutline0.m("offset: ", r11, ", length: ", r12, ", buffer size: ", r10.length);
        return 0;
    }

    @Override // java.io.InputStream, j$.io.InputStreamRetargetInterface
    public /* synthetic */ long transferTo(OutputStream r1) {
        return DesugarInputStream.transferTo(this, r1);
    }
}
