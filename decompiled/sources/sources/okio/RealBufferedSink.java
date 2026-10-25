package okio;

import com.android.dx.rop.code.RegisterSpec;
import com.google.android.gms.cast.MediaStatus;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001f\u0010\u000b\u001a\u00020\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\bH\u0016¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\rH\u0016¢\u0006\u0004\b\u000b\u0010\u000fJ\u0017\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0012\u0010\u0013J'\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0016\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0012\u0010\u0017J\u0017\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ\u0017\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u001bH\u0016¢\u0006\u0004\b\u000b\u0010\u001cJ'\u0010\u000b\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u00142\u0006\u0010\t\u001a\u00020\u0014H\u0016¢\u0006\u0004\b\u000b\u0010\u001eJ\u0017\u0010\u000b\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\u001fH\u0016¢\u0006\u0004\b\u000b\u0010 J\u0017\u0010\"\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020!H\u0016¢\u0006\u0004\b\"\u0010#J\u0017\u0010%\u001a\u00020\u00012\u0006\u0010$\u001a\u00020\u0014H\u0016¢\u0006\u0004\b%\u0010\u001aJ\u0017\u0010'\u001a\u00020\u00012\u0006\u0010&\u001a\u00020\u0014H\u0016¢\u0006\u0004\b'\u0010\u001aJ\u0017\u0010)\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u0014H\u0016¢\u0006\u0004\b)\u0010\u001aJ\u0017\u0010*\u001a\u00020\u00012\u0006\u0010(\u001a\u00020\u0014H\u0016¢\u0006\u0004\b*\u0010\u001aJ\u0017\u0010,\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\bH\u0016¢\u0006\u0004\b,\u0010-J\u0017\u0010.\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\bH\u0016¢\u0006\u0004\b.\u0010-J\u000f\u0010/\u001a\u00020\u0001H\u0016¢\u0006\u0004\b/\u00100J\u000f\u00102\u001a\u000201H\u0016¢\u0006\u0004\b2\u00103J\u000f\u00104\u001a\u00020\nH\u0016¢\u0006\u0004\b4\u00105J\u000f\u00107\u001a\u000206H\u0016¢\u0006\u0004\b7\u00108J\u000f\u00109\u001a\u00020\nH\u0016¢\u0006\u0004\b9\u00105J\u000f\u0010;\u001a\u00020:H\u0016¢\u0006\u0004\b;\u0010<J\u000f\u0010=\u001a\u00020\u0010H\u0016¢\u0006\u0004\b=\u0010>R\u0014\u0010\u0003\u001a\u00020\u00028\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010?R\u0014\u0010@\u001a\u00020\u00068\u0006X\u0087\u0004¢\u0006\u0006\n\u0004\b@\u0010AR\u0016\u0010B\u001a\u0002068\u0006@\u0006X\u0087\u000e¢\u0006\u0006\n\u0004\bB\u0010CR\u001b\u0010G\u001a\u00020\u00068Ö\u0002X\u0096\u0004¢\u0006\f\u0012\u0004\bF\u00105\u001a\u0004\bD\u0010E¨\u0006H"}, d2 = {"Lokio/RealBufferedSink;", "Lokio/BufferedSink;", "Lokio/Sink;", "sink", "<init>", "(Lokio/Sink;)V", "Lokio/Buffer;", "source", _UrlKt.FRAGMENT_ENCODE_SET, "byteCount", _UrlKt.FRAGMENT_ENCODE_SET, "write", "(Lokio/Buffer;J)V", "Lokio/ByteString;", "byteString", "(Lokio/ByteString;)Lokio/BufferedSink;", _UrlKt.FRAGMENT_ENCODE_SET, "string", "writeUtf8", "(Ljava/lang/String;)Lokio/BufferedSink;", _UrlKt.FRAGMENT_ENCODE_SET, "beginIndex", "endIndex", "(Ljava/lang/String;II)Lokio/BufferedSink;", "codePoint", "writeUtf8CodePoint", "(I)Lokio/BufferedSink;", _UrlKt.FRAGMENT_ENCODE_SET, "([B)Lokio/BufferedSink;", "offset", "([BII)Lokio/BufferedSink;", "Ljava/nio/ByteBuffer;", "(Ljava/nio/ByteBuffer;)I", "Lokio/Source;", "writeAll", "(Lokio/Source;)J", "b", "writeByte", "s", "writeShort", "i", "writeInt", "writeIntLe", RegisterSpec.PREFIX, "writeDecimalLong", "(J)Lokio/BufferedSink;", "writeHexadecimalUnsignedLong", "emitCompleteSegments", "()Lokio/BufferedSink;", "Ljava/io/OutputStream;", "outputStream", "()Ljava/io/OutputStream;", "flush", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "isOpen", "()Z", "close", "Lokio/Timeout;", "timeout", "()Lokio/Timeout;", "toString", "()Ljava/lang/String;", "Lokio/Sink;", "bufferField", "Lokio/Buffer;", "closed", "Z", "getBuffer", "()Lokio/Buffer;", "getBuffer$annotations", "buffer", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nRealBufferedSink.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealBufferedSink.kt\nokio/RealBufferedSink\n+ 2 RealBufferedSink.kt\nokio/internal/-RealBufferedSink\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,142:1\n51#1:146\n51#1:152\n51#1:157\n51#1:162\n51#1:167\n51#1:172\n51#1:175\n51#1:176\n51#1:180\n51#1:185\n51#1:188\n51#1:192\n51#1:201\n51#1:210\n51#1:215\n51#1:220\n51#1:225\n51#1:230\n51#1:235\n51#1:240\n51#1:245\n51#1:250\n51#1:255\n51#1:261\n51#1:267\n51#1:279\n34#2:143\n35#2:145\n36#2,2:147\n40#2:149\n41#2:151\n42#2:153\n50#2:154\n51#2:156\n52#2:158\n56#2:159\n57#2:161\n58#2:163\n66#2:164\n67#2:166\n68#2:168\n72#2:169\n73#2:171\n74#2:173\n78#2:177\n79#2:179\n80#2:181\n88#2:182\n89#2:184\n90#2:186\n94#2,3:189\n97#2,5:193\n105#2,3:198\n108#2,5:202\n116#2:207\n117#2:209\n118#2:211\n122#2:212\n123#2:214\n124#2:216\n128#2:217\n129#2:219\n130#2:221\n134#2:222\n135#2:224\n136#2:226\n140#2:227\n141#2:229\n142#2:231\n146#2:232\n147#2:234\n148#2:236\n152#2:237\n153#2:239\n154#2:241\n158#2:242\n159#2:244\n160#2:246\n164#2:247\n165#2:249\n166#2:251\n170#2:252\n171#2:254\n172#2,2:256\n177#2:258\n178#2:260\n179#2,2:262\n184#2:264\n185#2:266\n186#2,4:268\n192#2,7:272\n199#2,16:280\n216#2:296\n218#2:297\n1#3:144\n1#3:150\n1#3:155\n1#3:160\n1#3:165\n1#3:170\n1#3:174\n1#3:178\n1#3:183\n1#3:187\n1#3:208\n1#3:213\n1#3:218\n1#3:223\n1#3:228\n1#3:233\n1#3:238\n1#3:243\n1#3:248\n1#3:253\n1#3:259\n1#3:265\n*S KotlinDebug\n*F\n+ 1 RealBufferedSink.kt\nokio/RealBufferedSink\n*L\n55#1:146\n56#1:152\n58#1:157\n59#1:162\n61#1:167\n63#1:172\n67#1:175\n78#1:176\n82#1:180\n84#1:185\n88#1:188\n93#1:192\n94#1:201\n95#1:210\n96#1:215\n97#1:220\n98#1:225\n99#1:230\n100#1:235\n101#1:240\n102#1:245\n103#1:250\n104#1:255\n105#1:261\n134#1:267\n138#1:279\n55#1:143\n55#1:145\n55#1:147,2\n56#1:149\n56#1:151\n56#1:153\n58#1:154\n58#1:156\n58#1:158\n59#1:159\n59#1:161\n59#1:163\n61#1:164\n61#1:166\n61#1:168\n63#1:169\n63#1:171\n63#1:173\n82#1:177\n82#1:179\n82#1:181\n84#1:182\n84#1:184\n84#1:186\n93#1:189,3\n93#1:193,5\n94#1:198,3\n94#1:202,5\n95#1:207\n95#1:209\n95#1:211\n96#1:212\n96#1:214\n96#1:216\n97#1:217\n97#1:219\n97#1:221\n98#1:222\n98#1:224\n98#1:226\n99#1:227\n99#1:229\n99#1:231\n100#1:232\n100#1:234\n100#1:236\n101#1:237\n101#1:239\n101#1:241\n102#1:242\n102#1:244\n102#1:246\n103#1:247\n103#1:249\n103#1:251\n104#1:252\n104#1:254\n104#1:256,2\n105#1:258\n105#1:260\n105#1:262,2\n134#1:264\n134#1:266\n134#1:268,4\n138#1:272,7\n138#1:280,16\n139#1:296\n140#1:297\n55#1:144\n56#1:150\n58#1:155\n59#1:160\n61#1:165\n63#1:170\n82#1:178\n84#1:183\n95#1:208\n96#1:213\n97#1:218\n98#1:223\n99#1:228\n100#1:233\n101#1:238\n102#1:243\n103#1:248\n104#1:253\n105#1:259\n134#1:265\n*E\n"})
/* loaded from: classes.dex */
public final class RealBufferedSink implements BufferedSink {

    @JvmField
    public final Buffer bufferField;

    @JvmField
    public boolean closed;

    @JvmField
    public final Sink sink;

    @Metadata(d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u0005H\u0016J\b\u0010\n\u001a\u00020\u0003H\u0016J\b\u0010\u000b\u001a\u00020\u0003H\u0016J\b\u0010\f\u001a\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"okio/RealBufferedSink$outputStream$1", "Ljava/io/OutputStream;", "write", _UrlKt.FRAGMENT_ENCODE_SET, "b", _UrlKt.FRAGMENT_ENCODE_SET, "data", _UrlKt.FRAGMENT_ENCODE_SET, "offset", "byteCount", "flush", "close", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nRealBufferedSink.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RealBufferedSink.kt\nokio/RealBufferedSink$outputStream$1\n+ 2 RealBufferedSink.kt\nokio/RealBufferedSink\n*L\n1#1,142:1\n51#2:143\n51#2:144\n*S KotlinDebug\n*F\n+ 1 RealBufferedSink.kt\nokio/RealBufferedSink$outputStream$1\n*L\n111#1:143\n117#1:144\n*E\n"})
    /* renamed from: okio.RealBufferedSink$outputStream$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass1 extends OutputStream {
        final /* synthetic */ RealBufferedSink this$0;

        public AnonymousClass1(RealBufferedSink r1) {
            this.this$0 = r1;
        }

        @Override // java.io.OutputStream, java.io.Closeable, java.lang.AutoCloseable
        public void close() {
            this.this$0.close();
        }

        @Override // java.io.OutputStream, java.io.Flushable
        public void flush() {
            RealBufferedSink r1 = this.this$0;
            if (r1.closed == true) goto L6;
            r1.flush();
            return;
        }

        public String toString() {
            return this.this$0 + ".outputStream()";
        }

        @Override // java.io.OutputStream
        public void write(int r3) {
            RealBufferedSink r0 = this.this$0;
            if (r0.closed == true) goto L6;
            r0.bufferField.writeByte((byte) r3);
            this.this$0.emitCompleteSegments();
            return;
        L6:
            Model$$ExternalSyntheticBUOutline0.m("closed");
        }

        @Override // java.io.OutputStream
        public void write(byte[] r3, int r4, int r5) {
            RealBufferedSink r0 = this.this$0;
            if (r0.closed == true) goto L6;
            r0.bufferField.write(r3, r4, r5);
            this.this$0.emitCompleteSegments();
            return;
        L6:
            Model$$ExternalSyntheticBUOutline0.m("closed");
        }
    }

    public RealBufferedSink(Sink r1) {
        this.sink = r1;
        this.bufferField = new Buffer();
    }

    @Override // okio.Sink, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        if (this.closed == false) goto L23;
        return;
    L23:
    L8:
        th = th;
    L21:
        this.sink.close();     // Catch: Throwable -> L13
    L16:
        this.closed = true;
        if (th == null) goto L25;
        throw th;
    L25:
        return;
    L13:
        th = move-exception;
        if (th != null) goto L16;
        th = th;
        goto L16
    L5:
        if (this.bufferField.size() <= 0) goto L10;
        Sink r0 = this.sink;     // Catch: Throwable -> L8
        Buffer r1 = this.bufferField;     // Catch: Throwable -> L8
        r0.write(r1, r1.size());     // Catch: Throwable -> L8
    L10:
        th = null;
        goto L21
    }

    @Override // okio.BufferedSink
    public BufferedSink emitCompleteSegments() {
        if (this.closed == true) goto L8;
        long r0 = this.bufferField.completeSegmentByteCount();
        if (r0 <= 0) goto L7;
        this.sink.write(this.bufferField, r0);
    L7:
        return this;
    L8:
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return null;
    }

    @Override // okio.BufferedSink, okio.Sink, java.io.Flushable
    public void flush() {
        if (this.closed == false) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return;
    L5:
        if (this.bufferField.size() <= 0) goto L7;
        Sink r0 = this.sink;
        Buffer r1 = this.bufferField;
        r0.write(r1, r1.size());
    L7:
        this.sink.flush();
    }

    @Override // okio.BufferedSink
    public Buffer getBuffer() {
        return this.bufferField;
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return !this.closed;
    }

    @Override // okio.BufferedSink
    public OutputStream outputStream() {
        return new AnonymousClass1(this);
    }

    @Override // okio.Sink
    public Timeout timeout() {
        return this.sink.timeout();
    }

    public String toString() {
        return "buffer(" + this.sink + ')';
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer r2) {
        if (this.closed == true) goto L6;
        int r22 = this.bufferField.write(r2);
        emitCompleteSegments();
        return r22;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return 0;
    }

    @Override // okio.BufferedSink
    public BufferedSink write(ByteString r2) {
        if (this.closed == true) goto L6;
        this.bufferField.write(r2);
        return emitCompleteSegments();
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return null;
    }

    @Override // okio.BufferedSink
    public BufferedSink write(byte[] r2) {
        if (this.closed == true) goto L6;
        this.bufferField.write(r2);
        return emitCompleteSegments();
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return null;
    }

    @Override // okio.BufferedSink
    public BufferedSink write(byte[] r2, int r3, int r4) {
        if (this.closed == true) goto L6;
        this.bufferField.write(r2, r3, r4);
        return emitCompleteSegments();
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return null;
    }

    @Override // okio.Sink
    public void write(Buffer r2, long r3) {
        if (this.closed == true) goto L6;
        this.bufferField.write(r2, r3);
        emitCompleteSegments();
        return;
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("closed");
    }

    @Override // okio.BufferedSink
    public long writeAll(Source r7) {
        long r0 = 0;
    L3:
        long r2 = r7.read(this.bufferField, MediaStatus.COMMAND_PLAYBACK_RATE);
        if (r2 == (-1)) goto L6;
        r0 = r0 + r2;
        emitCompleteSegments();
        goto L3
    L6:
        return r0;
    }

    @Override // okio.BufferedSink
    public BufferedSink writeByte(int r2) {
        if (this.closed == true) goto L6;
        this.bufferField.writeByte(r2);
        return emitCompleteSegments();
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return null;
    }

    @Override // okio.BufferedSink
    public BufferedSink writeDecimalLong(long r2) {
        if (this.closed == true) goto L6;
        this.bufferField.writeDecimalLong(r2);
        return emitCompleteSegments();
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return null;
    }

    @Override // okio.BufferedSink
    public BufferedSink writeHexadecimalUnsignedLong(long r2) {
        if (this.closed == true) goto L6;
        this.bufferField.writeHexadecimalUnsignedLong(r2);
        return emitCompleteSegments();
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return null;
    }

    @Override // okio.BufferedSink
    public BufferedSink writeInt(int r2) {
        if (this.closed == true) goto L6;
        this.bufferField.writeInt(r2);
        return emitCompleteSegments();
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return null;
    }

    public BufferedSink writeIntLe(int r2) {
        if (this.closed == true) goto L6;
        this.bufferField.writeIntLe(r2);
        return emitCompleteSegments();
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return null;
    }

    @Override // okio.BufferedSink
    public BufferedSink writeShort(int r2) {
        if (this.closed == true) goto L6;
        this.bufferField.writeShort(r2);
        return emitCompleteSegments();
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return null;
    }

    @Override // okio.BufferedSink
    public BufferedSink writeUtf8(String r2) {
        if (this.closed == true) goto L6;
        this.bufferField.writeUtf8(r2);
        return emitCompleteSegments();
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return null;
    }

    @Override // okio.BufferedSink
    public BufferedSink writeUtf8(String r2, int r3, int r4) {
        if (this.closed == true) goto L6;
        this.bufferField.writeUtf8(r2, r3, r4);
        return emitCompleteSegments();
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return null;
    }

    @Override // okio.BufferedSink
    public BufferedSink writeUtf8CodePoint(int r2) {
        if (this.closed == true) goto L6;
        this.bufferField.writeUtf8CodePoint(r2);
        return emitCompleteSegments();
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("closed");
        return null;
    }
}
