package okhttp3.internal.ws;

import c.f$$ExternalSyntheticBUOutline1;
import java.io.Closeable;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\u0018\u00002\u00020\u0001B7\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ\u000e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u000e\u0010 \u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fJ\u0018\u0010!\u001a\u00020\u001d2\u0006\u0010\"\u001a\u00020#2\b\u0010$\u001a\u0004\u0018\u00010\u001fJ\u0018\u0010%\u001a\u00020\u001d2\u0006\u0010&\u001a\u00020#2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0016\u0010'\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020#2\u0006\u0010)\u001a\u00020\u001fJ\b\u0010*\u001a\u00020\u001dH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lokhttp3/internal/ws/WebSocketWriter;", "Ljava/io/Closeable;", "isClient", _UrlKt.FRAGMENT_ENCODE_SET, "sink", "Lokio/BufferedSink;", "random", "Ljava/util/Random;", "perMessageDeflate", "noContextTakeover", "minimumDeflateSize", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(ZLokio/BufferedSink;Ljava/util/Random;ZZJ)V", "getSink", "()Lokio/BufferedSink;", "getRandom", "()Ljava/util/Random;", "messageBuffer", "Lokio/Buffer;", "sinkBuffer", "writerClosed", "messageDeflater", "Lokhttp3/internal/ws/MessageDeflater;", "maskKey", _UrlKt.FRAGMENT_ENCODE_SET, "maskCursor", "Lokio/Buffer$UnsafeCursor;", "writePing", _UrlKt.FRAGMENT_ENCODE_SET, "payload", "Lokio/ByteString;", "writePong", "writeClose", "code", _UrlKt.FRAGMENT_ENCODE_SET, "reason", "writeControlFrame", "opcode", "writeMessageFrame", "formatOpcode", "data", "close", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWebSocketWriter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebSocketWriter.kt\nokhttp3/internal/ws/WebSocketWriter\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,215:1\n1#2:216\n*E\n"})
/* loaded from: classes5.dex */
public final class WebSocketWriter implements Closeable {
    private final boolean isClient;
    private final Buffer.UnsafeCursor maskCursor;
    private final byte[] maskKey;
    private final Buffer messageBuffer;
    private MessageDeflater messageDeflater;
    private final long minimumDeflateSize;
    private final boolean noContextTakeover;
    private final boolean perMessageDeflate;
    private final Random random;
    private final BufferedSink sink;
    private final Buffer sinkBuffer;
    private boolean writerClosed;

    public WebSocketWriter(boolean r1, BufferedSink r2, Random r3, boolean r4, boolean r5, long r6) {
        this.isClient = r1;
        this.sink = r2;
        this.random = r3;
        this.perMessageDeflate = r4;
        this.noContextTakeover = r5;
        this.minimumDeflateSize = r6;
        this.messageBuffer = new Buffer();
        this.sinkBuffer = r2.getBuffer();
        Buffer.UnsafeCursor r22 = null;
        if (r1 == false) goto L5;
        byte[] r32 = new byte[4];
    L6:
        this.maskKey = r32;
        if (r1 == false) goto L9;
        r22 = new Buffer.UnsafeCursor();
    L9:
        this.maskCursor = r22;
        return;
    L5:
        r32 = null;
        goto L6
    }

    private final void writeControlFrame(int r6, ByteString r7) {
        if (this.writerClosed == true) goto L16;
        int r0 = r7.size();
        if (r0 > 125) goto L14;
        this.sinkBuffer.writeByte(r6 | 128);
        boolean r62 = this.isClient;
        Buffer r1 = this.sinkBuffer;
        if (r62 == false) goto L11;
        r1.writeByte(r0 | 128);
        this.random.nextBytes(this.maskKey);
        this.sinkBuffer.write(this.maskKey);
        if (r0 <= 0) goto L12;
        long r02 = this.sinkBuffer.size();
        this.sinkBuffer.write(r7);
        this.sinkBuffer.readAndWriteUnsafe(this.maskCursor);
        this.maskCursor.seek(r02);
        WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
        this.maskCursor.close();
    L12:
        this.sink.flush();
        return;
    L11:
        r1.writeByte(r0);
        this.sinkBuffer.write(r7);
        goto L12
    L14:
        f$$ExternalSyntheticBUOutline1.m("Payload size must be less than or equal to 125");
        return;
    L16:
        Model$$ExternalSyntheticBUOutline0.m("closed");
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        MessageDeflater r0 = this.messageDeflater;
        if (r0 == null) goto L5;
        _UtilCommonKt.closeQuietly(r0);
    L5:
        _UtilCommonKt.closeQuietly(this.sink);
    }

    public final Random getRandom() {
        return this.random;
    }

    public final BufferedSink getSink() {
        return this.sink;
    }

    public final void writeClose(int r2, ByteString r3) {
        ByteString r0 = ByteString.EMPTY;
        if (r2 != 0) goto L5;
        if (r3 != null) goto L5;
    L18:
        writeControlFrame(8, r0);     // Catch: Throwable -> L15
        this.writerClosed = true;
        return;
    L15:
        th = move-exception;
        this.writerClosed = true;
        throw th;
    L5:
        if (r2 == 0) goto L7;
        WebSocketProtocol.INSTANCE.validateCloseCode(r2);
    L7:
        Buffer r02 = new Buffer();
        r02.writeShort(r2);
        if (r3 == null) goto L10;
        r02.write(r3);
    L10:
        r0 = r02.readByteString();
        goto L18
    }

    public final void writeMessageFrame(int r6, ByteString r7) {
        if (this.writerClosed == true) goto L30;
        this.messageBuffer.write(r7);
        int r0 = r6 | 128;
        if (this.perMessageDeflate == true) goto L7;
    L12:
        long r62 = this.messageBuffer.size();
        this.sinkBuffer.writeByte(r0);
        if (this.isClient == false) goto L15;
        int r02 = 128;
    L17:
        if (r62 > 125) goto L19;
        this.sinkBuffer.writeByte(r02 | ((int) r62));
    L24:
        if (this.isClient == false) goto L28;
        this.random.nextBytes(this.maskKey);
        this.sinkBuffer.write(this.maskKey);
        if (r62 <= 0) goto L28;
        this.messageBuffer.readAndWriteUnsafe(this.maskCursor);
        this.maskCursor.seek(0);
        WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
        this.maskCursor.close();
    L28:
        this.sinkBuffer.write(this.messageBuffer, r62);
        this.sink.flush();
        return;
    L19:
        Buffer r2 = this.sinkBuffer;
        if (r62 > WebSocketProtocol.PAYLOAD_SHORT_MAX) goto L22;
        r2.writeByte(r02 | 126);
        this.sinkBuffer.writeShort((int) r62);
        goto L24
    L22:
        r2.writeByte(r02 | 127);
        this.sinkBuffer.writeLong(r62);
        goto L24
    L15:
        r02 = 0;
        goto L17
    L7:
        if (r7.size() < this.minimumDeflateSize) goto L12;
        MessageDeflater r72 = this.messageDeflater;
        if (r72 != null) goto L11;
        r72 = new MessageDeflater(this.noContextTakeover);
        this.messageDeflater = r72;
    L11:
        r72.deflate(this.messageBuffer);
        r0 = r6 | 192;
        goto L12
    L30:
        Model$$ExternalSyntheticBUOutline0.m("closed");
    }

    public final void writePing(ByteString r2) {
        writeControlFrame(9, r2);
    }

    public final void writePong(ByteString r2) {
        writeControlFrame(10, r2);
    }
}
