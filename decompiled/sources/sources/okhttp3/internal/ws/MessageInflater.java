package okhttp3.internal.ws;

import c.f$$ExternalSyntheticBUOutline1;
import java.io.Closeable;
import java.util.zip.Inflater;
import kotlin.Metadata;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Buffer;
import okio.InflaterSource;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007J\b\u0010\u000f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lokhttp3/internal/ws/MessageInflater;", "Ljava/io/Closeable;", "noContextTakeover", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Z)V", "deflatedBytes", "Lokio/Buffer;", "inflater", "Ljava/util/zip/Inflater;", "inflaterSource", "Lokio/InflaterSource;", "inflate", _UrlKt.FRAGMENT_ENCODE_SET, "buffer", "close", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nMessageInflater.kt\nKotlin\n*S Kotlin\n*F\n+ 1 MessageInflater.kt\nokhttp3/internal/ws/MessageInflater\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,79:1\n1#2:80\n*E\n"})
/* loaded from: classes5.dex */
public final class MessageInflater implements Closeable {
    private final Buffer deflatedBytes;
    private Inflater inflater;
    private InflaterSource inflaterSource;
    private final boolean noContextTakeover;

    public MessageInflater(boolean r1) {
        this.noContextTakeover = r1;
        this.deflatedBytes = new Buffer();
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        InflaterSource r0 = this.inflaterSource;
        if (r0 == null) goto L5;
        r0.close();
    L5:
        this.inflaterSource = null;
        this.inflater = null;
    }

    public final void inflate(Buffer r7) {
        if (this.deflatedBytes.size() != 0) goto L22;
        Inflater r0 = this.inflater;
        if (r0 != null) goto L7;
        r0 = new Inflater(true);
        this.inflater = r0;
    L7:
        InflaterSource r1 = this.inflaterSource;
        if (r1 != null) goto L11;
        r1 = new InflaterSource(this.deflatedBytes, r0);
        this.inflaterSource = r1;
    L11:
        if (this.noContextTakeover == false) goto L13;
        r0.reset();
    L13:
        this.deflatedBytes.writeAll(r7);
        this.deflatedBytes.writeInt(65535);
        long r2 = r0.getBytesRead() + this.deflatedBytes.size();
    L14:
        r1.readOrInflate(r7, LongCompanionObject.MAX_VALUE);
        if (r0.getBytesRead() >= r2) goto L19;
        if (r0.finished() == false) goto L14;
    L19:
        if (r0.getBytesRead() >= r2) goto L27;
        this.deflatedBytes.clear();
        r1.close();
        this.inflaterSource = null;
        this.inflater = null;
        return;
    L27:
        return;
    L22:
        f$$ExternalSyntheticBUOutline1.m("Failed requirement.");
    }
}
