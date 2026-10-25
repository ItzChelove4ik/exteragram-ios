package kotlin.jvm.internal;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0011\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0013\u0010\u0007\u001a\u00020\u0004*\u00028\u0000H¤\u0080\u0004¢\u0006\u0002\u0010\bJ\u0017\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00028\u0000H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0015J\n\u0010\u0003\u001a\u00020\u0004H\u0084\u0080\u0004J\u001f\u0010\u0016\u001a\u00028\u00002\u0006\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00028\u0000H\u0084\u0080\u0004¢\u0006\u0002\u0010\u0019R\u000f\u0010\u0003\u001a\u00020\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\u0004X\u0084\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\u0006R\u001f\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00018\u00000\u000eX\u0082\u0084\b¢\u0006\n\n\u0002\u0010\u0011\u0012\u0004\b\u000f\u0010\u0010¨\u0006\u001a"}, d2 = {"Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "size", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(I)V", "getSize", "(Ljava/lang/Object;)I", "position", "getPosition", "()I", "setPosition", "spreads", _UrlKt.FRAGMENT_ENCODE_SET, "getSpreads$annotations", "()V", "[Ljava/lang/Object;", "addSpread", _UrlKt.FRAGMENT_ENCODE_SET, "spreadArgument", "(Ljava/lang/Object;)V", "toArray", "values", "result", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class PrimitiveSpreadBuilder<T> {
    private int position;
    private final int size;
    private final T[] spreads;

    public PrimitiveSpreadBuilder(int r1) {
        this.size = r1;
        this.spreads = (T[]) new Object[r1];
    }

    private static /* synthetic */ void getSpreads$annotations() {
    }

    public final void addSpread(T r4) {
        T[] r0 = this.spreads;
        int r1 = this.position;
        this.position = r1 + 1;
        r0[r1] = r4;
    }

    public final int getPosition() {
        return this.position;
    }

    public abstract int getSize(T r1);

    public final void setPosition(int r1) {
        this.position = r1;
    }

    public final int size() {
        int r0 = this.size - 1;
        int r2 = 0;
        if (r0 < 0) goto L12;
        int r3 = 0;
    L5:
        T r4 = this.spreads[r3];
        if (r4 == null) goto L8;
        int r42 = getSize(r4);
    L9:
        r2 = r2 + r42;
        if (r3 == r0) goto L12;
        r3 = r3 + 1;
        goto L5
    L8:
        r42 = 1;
    L12:
        return r2;
    }

    public final T toArray(T r8, T r9) {
        int r0 = this.size - 1;
        int r1 = 0;
        if (r0 < 0) goto L13;
        int r2 = 0;
        int r3 = 0;
        int r4 = 0;
    L5:
        T r5 = this.spreads[r2];
        if (r5 == null) goto L10;
        if (r3 >= r2) goto L9;
        int r6 = r2 - r3;
        System.arraycopy(r8, r3, r9, r4, r6);
        r4 = r4 + r6;
    L9:
        int r32 = getSize(r5);
        System.arraycopy(r5, 0, r9, r4, r32);
        r4 = r4 + r32;
        r3 = r2 + 1;
    L10:
        if (r2 == r0) goto L12;
        r2 = r2 + 1;
        goto L5
    L12:
        r1 = r3;
    L14:
        int r7 = this.size;
        if (r1 >= r7) goto L17;
        System.arraycopy(r8, r1, r9, r4, r7 - r1);
    L17:
        return r9;
    L13:
        r4 = 0;
        goto L14
    }
}
