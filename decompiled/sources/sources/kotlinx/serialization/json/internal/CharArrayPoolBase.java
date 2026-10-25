package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0010\u0018\u00002\u00020\u0001B\t\bF¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\bH\u0084\u0080\u0004J\u0012\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0006H\u0084\u0080\u0004R\u0015\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\bX\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lkotlinx/serialization/json/internal/CharArrayPoolBase;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "arrays", "Lkotlin/collections/ArrayDeque;", _UrlKt.FRAGMENT_ENCODE_SET, "charsTotal", _UrlKt.FRAGMENT_ENCODE_SET, "take", "size", "releaseImpl", _UrlKt.FRAGMENT_ENCODE_SET, "array", "kotlinx-serialization-json"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nArrayPools.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ArrayPools.kt\nkotlinx/serialization/json/internal/CharArrayPoolBase\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,90:1\n1#2:91\n*E\n"})
/* loaded from: classes.dex */
public abstract class CharArrayPoolBase {
    private final ArrayDeque<char[]> arrays;
    private int charsTotal;

    public CharArrayPoolBase() {
        this.arrays = new ArrayDeque();
    }

    public final void releaseImpl(char[] r3) {
        monitor-enter(this);
    L6:
        th = move-exception;
        throw th;
    L4:
        if ((this.charsTotal + r3.length) >= ArrayPoolsKt.access$getMAX_CHARS_IN_POOL$p()) goto L8;
        this.charsTotal += r3.length;
        this.arrays.addLast(r3);     // Catch: Throwable -> L6
    L8:
        Unit r32 = Unit.INSTANCE;     // Catch: Throwable -> L6
        monitor-exit(this);
    }

    public final char[] take(int r4) {
        monitor-enter(this);
        char[] r0 = this.arrays.removeLastOrNull();     // Catch: Throwable -> L7
        if (r0 == null) goto L9;
        this.charsTotal -= r0.length;
    L10:
        monitor-exit(this);
        if (r0 == null) goto L13;
        return r0;
    L13:
        return new char[r4];
    L9:
        r0 = null;
    L7:
        th = move-exception;
        throw th;
    }
}
