package okio;

import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.UShort;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u0018\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0013H\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\b\u0010\u0019\u001a\u00020\u0018H\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0016J\b\u0010\u001c\u001a\u00020\u0018H\u0016J \u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0013H\u0002J \u0010 \u001a\u00020\u00182\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020$H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00060\tj\u0002`\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00060\u000fj\u0002`\u0010X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011¨\u0006&"}, d2 = {"Lokio/GzipSource;", "Lokio/Source;", "source", "<init>", "(Lokio/Source;)V", "section", _UrlKt.FRAGMENT_ENCODE_SET, "Lokio/RealBufferedSource;", "inflater", "Ljava/util/zip/Inflater;", "Lokio/Inflater;", "Ljava/util/zip/Inflater;", "inflaterSource", "Lokio/InflaterSource;", "crc", "Ljava/util/zip/CRC32;", "Lokio/internal/CRC32;", "Ljava/util/zip/CRC32;", "read", _UrlKt.FRAGMENT_ENCODE_SET, "sink", "Lokio/Buffer;", "byteCount", "consumeHeader", _UrlKt.FRAGMENT_ENCODE_SET, "consumeTrailer", "timeout", "Lokio/Timeout;", "close", "updateCrc", "buffer", "offset", "checkEqual", "name", _UrlKt.FRAGMENT_ENCODE_SET, "expected", _UrlKt.FRAGMENT_ENCODE_SET, "actual", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGzipSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GzipSource.kt\nokio/GzipSource\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 RealBufferedSource.kt\nokio/RealBufferedSource\n+ 4 GzipSource.kt\nokio/-GzipSourceExtensions\n+ 5 Util.kt\nokio/-SegmentedByteString\n*L\n1#1,222:1\n1#2:223\n63#3:224\n63#3:226\n63#3:228\n63#3:229\n63#3:230\n63#3:232\n63#3:234\n204#4:225\n204#4:227\n204#4:231\n204#4:233\n88#5:235\n*S KotlinDebug\n*F\n+ 1 GzipSource.kt\nokio/GzipSource\n*L\n103#1:224\n105#1:226\n117#1:228\n118#1:229\n120#1:230\n131#1:232\n142#1:234\n104#1:225\n115#1:227\n128#1:231\n139#1:233\n185#1:235\n*E\n"})
/* loaded from: classes.dex */
public final class GzipSource implements Source {
    private final CRC32 crc;
    private final Inflater inflater;
    private final InflaterSource inflaterSource;
    private byte section;
    private final RealBufferedSource source;

    public GzipSource(Source r3) {
        RealBufferedSource r0 = new RealBufferedSource(r3);
        this.source = r0;
        Inflater r32 = new Inflater(true);
        this.inflater = r32;
        this.inflaterSource = new InflaterSource(r0, r32);
        this.crc = new CRC32();
    }

    private final void checkEqual(String r3, int r4, int r5) {
        if (r5 != r4) goto L5;
        return;
    L5:
        throw new IOException(r3 + ": actual 0x" + StringsKt.padStart(SegmentedByteString.toHexString(r5), 8, '0') + " != expected 0x" + StringsKt.padStart(SegmentedByteString.toHexString(r4), 8, '0'));
    }

    private final void consumeHeader() {
        this.source.require(10);
        byte r6 = this.source.bufferField.getByte(3);
        if (((r6 >> 1) & 1) != 1) goto L5;
        boolean r9 = true;
    L6:
        if (r9 == false) goto L8;
        updateCrc(this.source.bufferField, 0, 10);
    L8:
        checkEqual("ID1ID2", 8075, this.source.readShort());
        this.source.skip(8);
        if (((r6 >> 2) & 1) != 1) goto L18;
        this.source.require(2);
        if (r9 == false) goto L13;
        updateCrc(this.source.bufferField, 0, 2);
    L13:
        long r4 = this.source.bufferField.readShortLe() & UShort.MAX_VALUE;
        this.source.require(r4);
        if (r9 == false) goto L16;
        updateCrc(this.source.bufferField, 0, r4);
    L16:
        this.source.skip(r4);
    L18:
        if (((r6 >> 3) & 1) != 1) goto L27;
        long r14 = this.source.indexOf((byte) 0);
        if (r14 == (-1)) goto L24;
        if (r9 == false) goto L23;
        updateCrc(this.source.bufferField, 0, r14 + 1);
    L23:
        this.source.skip(r14 + 1);
        goto L27
    L24:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return;
    L27:
        if (((r6 >> 4) & 1) != 1) goto L35;
        long r62 = this.source.indexOf((byte) 0);
        if (r62 == (-1)) goto L33;
        if (r9 == false) goto L32;
        updateCrc(this.source.bufferField, 0, r62 + 1);
    L32:
        this.source.skip(r62 + 1);
        goto L35
    L33:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return;
    L35:
        if (r9 == false) goto L38;
        checkEqual("FHCRC", this.source.readShortLe(), (short) this.crc.getValue());
        this.crc.reset();
        return;
    L38:
        return;
    L5:
        r9 = false;
        goto L6
    }

    private final void consumeTrailer() {
        checkEqual("CRC", this.source.readIntLe(), (int) this.crc.getValue());
        checkEqual("ISIZE", this.source.readIntLe(), (int) this.inflater.getBytesWritten());
    }

    private final void updateCrc(Buffer r5, long r6, long r8) {
        Segment r52 = r5.head;
    L3:
        int r0 = r52.limit;
        int r1 = r52.pos;
        if (r6 < (r0 - r1)) goto L7;
        r6 = r6 - (r0 - r1);
        r52 = r52.next;
    L7:
        if (r8 <= 0) goto L9;
        int r7 = (int) Math.min(r52.limit - r6, r8);
        this.crc.update(r52.data, (int) (r52.pos + r6), r7);
        r8 = r8 - r7;
        r52 = r52.next;
        r6 = 0;
        goto L7
    }

    @Override // okio.Source, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.inflaterSource.close();
    }

    @Override // okio.Source
    public long read(Buffer r14, long r15) {
        if (r15 < 0) goto L24;
        if (r15 != 0) goto L7;
        return 0;
    L7:
        if (this.section != 0) goto L10;
        consumeHeader();
        this.section = 1;
    L10:
        if (this.section != 1) goto L17;
        long r3 = r14.size();
        long r1 = this.inflaterSource.read(r14, r15);
        if (r1 == (-1)) goto L15;
        updateCrc(r14, r3, r1);
        return r1;
    L15:
        this.section = 2;
    L17:
        if (this.section != 2) goto L23;
        consumeTrailer();
        this.section = 3;
        if (this.source.exhausted() == true) goto L23;
        Model$$ExternalSyntheticBUOutline0.m("gzip finished without exhausting source");
        return 0;
    L23:
        return -1;
    L24:
        Buffer$$ExternalSyntheticBUOutline3.m("byteCount < 0: ", r15);
        return 0;
    }

    @Override // okio.Source
    public Timeout timeout() {
        return this.source.timeout();
    }
}
