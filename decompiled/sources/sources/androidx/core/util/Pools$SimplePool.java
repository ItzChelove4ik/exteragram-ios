package androidx.core.util;

import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.BuildConfig;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0005\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B\u0011\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0017\u0010\n\u001a\u00020\t2\u0006\u0010\b\u001a\u00028\u0000H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u0011\u0010\f\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0004\b\f\u0010\rJ\u0017\u0010\u000e\u001a\u00020\t2\u0006\u0010\b\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\u000e\u0010\u000bR\u001c\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\u000f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0012\u001a\u00020\u00048\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"androidx/core/util/Pools$SimplePool", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Landroidx/core/util/Pools$Pool;", _UrlKt.FRAGMENT_ENCODE_SET, "maxPoolSize", "<init>", "(I)V", "instance", _UrlKt.FRAGMENT_ENCODE_SET, "isInPool", "(Ljava/lang/Object;)Z", "acquire", "()Ljava/lang/Object;", BuildConfig.BUILD_TYPE, _UrlKt.FRAGMENT_ENCODE_SET, "pool", "[Ljava/lang/Object;", "poolSize", "I", "core"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPools.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Pools.kt\nandroidx/core/util/Pools$SimplePool\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,133:1\n1#2:134\n*E\n"})
/* loaded from: classes.dex */
public class Pools$SimplePool<T> implements Pools$Pool<T> {
    private final Object[] pool;
    private int poolSize;

    public Pools$SimplePool(int r1) {
        if (r1 <= 0) goto L6;
        this.pool = new Object[r1];
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("The max pool size must be > 0");
        throw null;
    }

    private final boolean isInPool(T r5) {
        int r0 = this.poolSize;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L9;
        if (this.pool[r2] == r5) goto L6;
        r2 = r2 + 1;
        goto L3
    L6:
        return true;
    L9:
        return false;
    }

    @Override // androidx.core.util.Pools$Pool
    public T acquire() {
        int r0 = this.poolSize;
        if (r0 <= 0) goto L6;
        int r2 = r0 - 1;
        Object[] r3 = this.pool;
        T r4 = (T) r3[r2];
        r3[r2] = null;
        this.poolSize = r0 - 1;
        return r4;
    L6:
        return null;
    }

    @Override // androidx.core.util.Pools$Pool
    public boolean release(T r4) {
        if (isInPool(r4) == true) goto L10;
        int r0 = this.poolSize;
        Object[] r1 = this.pool;
        if (r0 >= r1.length) goto L8;
        r1[r0] = r4;
        this.poolSize = r0 + 1;
        return true;
    L8:
        return false;
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("Already in the pool!");
        return false;
    }
}
