package okhttp3.internal.ws;

import java.io.Closeable;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal._UtilCommonKt;
import okhttp3.internal._UtilJvmKt;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001'B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0003\u0012\u0006\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bJ\u0006\u0010\u001f\u001a\u00020 J\b\u0010!\u001a\u00020 H\u0002J\b\u0010\"\u001a\u00020 H\u0002J\b\u0010#\u001a\u00020 H\u0002J\b\u0010$\u001a\u00020 H\u0002J\b\u0010%\u001a\u00020 H\u0002J\b\u0010&\u001a\u00020 H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lokhttp3/internal/ws/WebSocketReader;", "Ljava/io/Closeable;", "isClient", _UrlKt.FRAGMENT_ENCODE_SET, "source", "Lokio/BufferedSource;", "frameCallback", "Lokhttp3/internal/ws/WebSocketReader$FrameCallback;", "perMessageDeflate", "noContextTakeover", "<init>", "(ZLokio/BufferedSource;Lokhttp3/internal/ws/WebSocketReader$FrameCallback;ZZ)V", "getSource", "()Lokio/BufferedSource;", "closed", "opcode", _UrlKt.FRAGMENT_ENCODE_SET, "frameLength", _UrlKt.FRAGMENT_ENCODE_SET, "isFinalFrame", "isControlFrame", "readingCompressedMessage", "controlFrameBuffer", "Lokio/Buffer;", "messageFrameBuffer", "messageInflater", "Lokhttp3/internal/ws/MessageInflater;", "maskKey", _UrlKt.FRAGMENT_ENCODE_SET, "maskCursor", "Lokio/Buffer$UnsafeCursor;", "processNextFrame", _UrlKt.FRAGMENT_ENCODE_SET, "readHeader", "readControlFrame", "readMessageFrame", "readUntilNonControlFrame", "readMessage", "close", "FrameCallback", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nWebSocketReader.kt\nKotlin\n*S Kotlin\n*F\n+ 1 WebSocketReader.kt\nokhttp3/internal/ws/WebSocketReader\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,310:1\n1#2:311\n*E\n"})
/* loaded from: classes5.dex */
public final class WebSocketReader implements Closeable {
    private boolean closed;
    private final Buffer controlFrameBuffer;
    private final FrameCallback frameCallback;
    private long frameLength;
    private final boolean isClient;
    private boolean isControlFrame;
    private boolean isFinalFrame;
    private final Buffer.UnsafeCursor maskCursor;
    private final byte[] maskKey;
    private final Buffer messageFrameBuffer;
    private MessageInflater messageInflater;
    private final boolean noContextTakeover;
    private int opcode;
    private final boolean perMessageDeflate;
    private boolean readingCompressedMessage;
    private final BufferedSource source;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H&J\u0010\u0010\n\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0007H&J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0005H&¨\u0006\u000fÀ\u0006\u0003"}, d2 = {"Lokhttp3/internal/ws/WebSocketReader$FrameCallback;", _UrlKt.FRAGMENT_ENCODE_SET, "onReadMessage", _UrlKt.FRAGMENT_ENCODE_SET, "text", _UrlKt.FRAGMENT_ENCODE_SET, "bytes", "Lokio/ByteString;", "onReadPing", "payload", "onReadPong", "onReadClose", "code", _UrlKt.FRAGMENT_ENCODE_SET, "reason", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface FrameCallback {
        void onReadClose(int r1, String r2);

        void onReadMessage(String r1);

        void onReadMessage(ByteString r1);

        void onReadPing(ByteString r1);

        void onReadPong(ByteString r1);
    }

    public WebSocketReader(boolean r1, BufferedSource r2, FrameCallback r3, boolean r4, boolean r5) {
        this.isClient = r1;
        this.source = r2;
        this.frameCallback = r3;
        this.perMessageDeflate = r4;
        this.noContextTakeover = r5;
        this.controlFrameBuffer = new Buffer();
        this.messageFrameBuffer = new Buffer();
        Buffer.UnsafeCursor r22 = null;
        if (r1 == false) goto L5;
        byte[] r32 = null;
    L6:
        this.maskKey = r32;
        if (r1 == true) goto L10;
        r22 = new Buffer.UnsafeCursor();
    L10:
        this.maskCursor = r22;
        return;
    L5:
        r32 = new byte[4];
        goto L6
    }

    private final void readControlFrame() {
        long r0 = this.frameLength;
        if (r0 <= 0) goto L8;
        this.source.readFully(this.controlFrameBuffer, r0);
        if (this.isClient == true) goto L8;
        this.controlFrameBuffer.readAndWriteUnsafe(this.maskCursor);
        this.maskCursor.seek(0);
        WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
        this.maskCursor.close();
    L8:
        switch(this.opcode) {
            case 8: goto L15;
            case 9: goto L13;
            case 10: goto L11;
            default: goto L10;
        };
    L11:
        this.frameCallback.onReadPong(this.controlFrameBuffer.readByteString());
        return;
    L13:
        this.frameCallback.onReadPing(this.controlFrameBuffer.readByteString());
        return;
    L15:
        long r02 = this.controlFrameBuffer.size();
        if (r02 == 1) goto L28;
        if (r02 == 0) goto L24;
        short r03 = this.controlFrameBuffer.readShort();
        String r1 = this.controlFrameBuffer.readUtf8();
        String r2 = WebSocketProtocol.INSTANCE.closeCodeExceptionMessage(r03);
        if (r2 != null) goto L23;
    L25:
        this.frameCallback.onReadClose(r03, r1);
        this.closed = true;
        return;
    L23:
        throw new ProtocolException(r2);
    L24:
        r03 = 1005;
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L25
    L28:
        throw new ProtocolException("Malformed close payload length of 1.");
    L10:
        throw new ProtocolException("Unknown control opcode: " + _UtilJvmKt.toHexString(this.opcode));
    }

    private final void readHeader() {
        TimeUnit r0 = TimeUnit.NANOSECONDS;
        if (this.closed == true) goto L82;
        long r1 = this.source.timeout().timeoutNanos();
        this.source.timeout().clearTimeout();
        int r3 = _UtilCommonKt.and(this.source.readByte(), 255);     // Catch: Throwable -> L79
        this.source.timeout().timeout(r1, r0);
        int r02 = r3 & 15;
        this.opcode = r02;
        boolean r2 = false;
        if ((r3 & 128) == 0) goto L9;
        boolean r12 = true;
    L10:
        this.isFinalFrame = r12;
        if ((r3 & 8) == 0) goto L13;
        boolean r6 = true;
    L14:
        this.isControlFrame = r6;
        if (r6 == false) goto L21;
        if (r12 == true) goto L21;
        throw new ProtocolException("Control frames must be final.");
    L21:
        if ((r3 & 64) == 0) goto L23;
        boolean r13 = true;
    L25:
        if (r02 != 1) goto L27;
    L32:
        if (r13 == true) goto L34;
        boolean r03 = false;
    L39:
        this.readingCompressedMessage = r03;
    L41:
        if ((r3 & 32) != 0) goto L78;
        if ((r3 & 16) != 0) goto L76;
        int r04 = _UtilCommonKt.and(this.source.readByte(), 255);
        if ((r04 & 128) == 0) goto L48;
        r2 = true;
    L48:
        if (r2 == this.isClient) goto L50;
        long r05 = r04 & 127;
        this.frameLength = r05;
        if (r05 != 126) goto L59;
        this.frameLength = _UtilCommonKt.and(this.source.readShort(), 65535);
    L66:
        if (this.isControlFrame == true) goto L68;
    L72:
        if (r2 == false) goto L86;
        this.source.readFully(this.maskKey);
        return;
    L86:
        return;
    L68:
        if (this.frameLength <= 125) goto L72;
        throw new ProtocolException("Control frame must be less than 125B.");
    L59:
        if (r05 != 127) goto L66;
        long r06 = this.source.readLong();
        this.frameLength = r06;
        if (r06 >= 0) goto L66;
        throw new ProtocolException("Frame length 0x" + _UtilJvmKt.toHexString(this.frameLength) + " > 0x7FFFFFFFFFFFFFFF");
    L50:
        if (this.isClient == false) goto L52;
        String r8 = "Server-sent frames must not be masked.";
    L54:
        throw new ProtocolException(r8);
    L52:
        r8 = "Client-sent frames must be masked.";
        goto L54
    L76:
        throw new ProtocolException("Unexpected rsv3 flag");
    L78:
        throw new ProtocolException("Unexpected rsv2 flag");
    L34:
        if (this.perMessageDeflate == false) goto L37;
        r03 = true;
        goto L39
    L37:
        throw new ProtocolException("Unexpected rsv1 flag");
    L27:
        if (r02 == 2) goto L32;
        if (r13 == false) goto L41;
        throw new ProtocolException("Unexpected rsv1 flag");
    L23:
        r13 = false;
        goto L25
    L13:
        r6 = false;
        goto L14
    L9:
        r12 = false;
    L79:
        th = move-exception;
        this.source.timeout().timeout(r1, r0);
        throw th;
    L82:
        Model$$ExternalSyntheticBUOutline0.m("closed");
    }

    private final void readMessage() {
    L3:
        if (this.closed == true) goto L17;
        long r0 = this.frameLength;
        if (r0 <= 0) goto L10;
        this.source.readFully(this.messageFrameBuffer, r0);
        if (this.isClient == true) goto L10;
        this.messageFrameBuffer.readAndWriteUnsafe(this.maskCursor);
        this.maskCursor.seek(this.messageFrameBuffer.size() - this.frameLength);
        WebSocketProtocol.INSTANCE.toggleMask(this.maskCursor, this.maskKey);
        this.maskCursor.close();
    L10:
        if (this.isFinalFrame == true) goto L16;
        readUntilNonControlFrame();
        if (this.opcode == 0) goto L3;
        throw new ProtocolException("Expected continuation opcode. Got: " + _UtilJvmKt.toHexString(this.opcode));
    L16:
        return;
    L17:
        Model$$ExternalSyntheticBUOutline0.m("closed");
    }

    private final void readMessageFrame() {
        int r0 = this.opcode;
        if (r0 != 1) goto L5;
    L9:
        readMessage();
        if (this.readingCompressedMessage == false) goto L15;
        MessageInflater r2 = this.messageInflater;
        if (r2 != null) goto L14;
        r2 = new MessageInflater(this.noContextTakeover);
        this.messageInflater = r2;
    L14:
        r2.inflate(this.messageFrameBuffer);
    L15:
        FrameCallback r22 = this.frameCallback;
        if (r0 != 1) goto L19;
        r22.onReadMessage(this.messageFrameBuffer.readUtf8());
        return;
    L19:
        r22.onReadMessage(this.messageFrameBuffer.readByteString());
        return;
    L5:
        if (r0 == 2) goto L9;
        throw new ProtocolException("Unknown opcode: " + _UtilJvmKt.toHexString(r0));
    }

    private final void readUntilNonControlFrame() {
    L3:
        if (this.closed == true) goto L8;
        readHeader();
        if (this.isControlFrame == false) goto L11;
        readControlFrame();
        goto L3
    L11:
        return;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        MessageInflater r0 = this.messageInflater;
        if (r0 == null) goto L5;
        _UtilCommonKt.closeQuietly(r0);
    L5:
        _UtilCommonKt.closeQuietly(this.source);
    }

    public final BufferedSource getSource() {
        return this.source;
    }

    public final void processNextFrame() {
        readHeader();
        if (this.isControlFrame == false) goto L6;
        readControlFrame();
        return;
    L6:
        readMessageFrame();
    }
}
