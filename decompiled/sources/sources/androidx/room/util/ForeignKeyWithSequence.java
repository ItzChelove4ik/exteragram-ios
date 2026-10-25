package androidx.room.util;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\u0000H\u0096\u0002¢\u0006\u0004\b\u000b\u0010\fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\r\u001a\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\r\u001a\u0004\b\u0010\u0010\u000fR\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0007\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0011\u001a\u0004\b\u0014\u0010\u0013¨\u0006\u0015"}, d2 = {"Landroidx/room/util/ForeignKeyWithSequence;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "id", "sequence", _UrlKt.FRAGMENT_ENCODE_SET, "from", "to", "<init>", "(IILjava/lang/String;Ljava/lang/String;)V", "other", "compareTo", "(Landroidx/room/util/ForeignKeyWithSequence;)I", "I", "getId", "()I", "getSequence", "Ljava/lang/String;", "getFrom", "()Ljava/lang/String;", "getTo", "room-runtime"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
final class ForeignKeyWithSequence implements Comparable<ForeignKeyWithSequence> {
    private final String from;
    private final int id;
    private final int sequence;
    private final String to;

    public ForeignKeyWithSequence(int r1, int r2, String r3, String r4) {
        this.id = r1;
        this.sequence = r2;
        this.from = r3;
        this.to = r4;
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(ForeignKeyWithSequence r3) {
        int r0 = this.id - r3.id;
        if (r0 == 0) goto L5;
        return r0;
    L5:
        return this.sequence - r3.sequence;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(ForeignKeyWithSequence r1) {
        return compareTo2(r1);
    }

    public final String getFrom() {
        return this.from;
    }

    public final int getId() {
        return this.id;
    }

    public final String getTo() {
        return this.to;
    }
}
