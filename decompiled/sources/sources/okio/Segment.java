package okio;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\t\b\u0016¢\u0006\u0004\b\u0002\u0010\u0003B1\b\u0016\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\u0002\u0010\fJ\u0006\u0010\u000f\u001a\u00020\u0000J\u0006\u0010\u0010\u001a\u00020\u0000J\b\u0010\u0011\u001a\u0004\u0018\u00010\u0000J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u0000J\u000e\u0010\u0014\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0007J\u0006\u0010\u0016\u001a\u00020\u0017J\u0016\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u00002\u0006\u0010\u0015\u001a\u00020\u0007R\u0010\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\b\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\t\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00020\n8\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\u0004\u0018\u00010\u00008\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u0004\u0018\u00010\u00008\u0006@\u0006X\u0087\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lokio/Segment;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "data", _UrlKt.FRAGMENT_ENCODE_SET, "pos", _UrlKt.FRAGMENT_ENCODE_SET, "limit", "shared", _UrlKt.FRAGMENT_ENCODE_SET, "owner", "([BIIZZ)V", "next", "prev", "sharedCopy", "unsharedCopy", "pop", "push", "segment", "split", "byteCount", "compact", _UrlKt.FRAGMENT_ENCODE_SET, "writeTo", "sink", "Companion", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSegment.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Segment.kt\nokio/Segment\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,187:1\n1#2:188\n*E\n"})
/* loaded from: classes.dex */
public final class Segment {
    public static final Companion Companion = null;

    @JvmField
    public final byte[] data;

    @JvmField
    public int limit;

    @JvmField
    public Segment next;

    @JvmField
    public boolean owner;

    @JvmField
    public int pos;

    @JvmField
    public Segment prev;

    @JvmField
    public boolean shared;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lokio/Segment$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "SIZE", _UrlKt.FRAGMENT_ENCODE_SET, "SHARE_MINIMUM", "okio"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public Segment() {
        this.data = new byte[8192];
        this.owner = true;
        this.shared = false;
    }

    public Segment(byte[] r1, int r2, int r3, boolean r4, boolean r5) {
        this.data = r1;
        this.pos = r2;
        this.limit = r3;
        this.shared = r4;
        this.owner = r5;
    }

    public final void compact() {
        Segment r0 = this.prev;
        if (r0 != this) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("cannot compact");
        return;
    L5:
        if (r0.owner == false) goto L18;
        int r1 = this.limit - this.pos;
        int r2 = 8192 - r0.limit;
        if (r0.shared == false) goto L10;
        int r3 = 0;
    L12:
        if (r1 <= (r2 + r3)) goto L14;
        return;
    L14:
        writeTo(r0, r1);
        pop();
        SegmentPool.recycle(this);
        return;
    L10:
        r3 = r0.pos;
        goto L12
    }

    public final Segment pop() {
        Segment r0 = this.next;
        if (r0 == this) goto L5;
        Segment r2 = r0;
    L6:
        Segment r3 = this.prev;
        r3.next = r0;
        this.next.prev = r3;
        this.next = null;
        this.prev = null;
        return r2;
    L5:
        r2 = null;
        goto L6
    }

    public final Segment push(Segment r2) {
        r2.prev = this;
        r2.next = this.next;
        this.next.prev = r2;
        this.next = r2;
        return r2;
    }

    public final Segment sharedCopy() {
        this.shared = true;
        return new Segment(this.data, this.pos, this.limit, true, false);
    }

    public final Segment split(int r9) {
        if (r9 > 0) goto L4;
    L11:
        f$$ExternalSyntheticBUOutline1.m("byteCount out of range");
        return null;
    L4:
        if (r9 > (this.limit - this.pos)) goto L11;
        if (r9 < 1024) goto L8;
        Segment r0 = sharedCopy();
    L9:
        r0.limit = r0.pos + r9;
        this.pos += r9;
        this.prev.push(r0);
        return r0;
    L8:
        r0 = SegmentPool.take();
        byte[] r1 = this.data;
        byte[] r2 = r0.data;
        int r4 = this.pos;
        ArraysKt.copyInto$default(r1, r2, 0, r4, r4 + r9, 2, null);
        goto L9
    }

    public final Segment unsharedCopy() {
        byte[] r1 = this.data;
        return new Segment(Arrays.copyOf(r1, r1.length), this.pos, this.limit, false, true);
    }

    public final void writeTo(Segment r9, int r10) {
        if (r9.owner == false) goto L17;
        int r5 = r9.limit;
        if ((r5 + r10) > 8192) goto L7;
    L15:
        byte[] r0 = this.data;
        byte[] r1 = r9.data;
        int r2 = r9.limit;
        int r3 = this.pos;
        ArraysKt.copyInto(r0, r1, r2, r3, r3 + r10);
        r9.limit += r10;
        this.pos += r10;
        return;
    L7:
        if (r9.shared == true) goto L13;
        int r4 = r9.pos;
        if (((r5 + r10) - r4) > 8192) goto L11;
        byte[] r12 = r9.data;
        ArraysKt.copyInto$default(r12, r12, 0, r4, r5, 2, null);
        r9.limit -= r9.pos;
        r9.pos = 0;
        goto L15
    L11:
        Segment$$ExternalSyntheticBUOutline0.m();
        return;
    L13:
        Segment$$ExternalSyntheticBUOutline0.m();
        return;
    L17:
        Segment$$ExternalSyntheticBUOutline1.m("only owner can write");
    }
}
