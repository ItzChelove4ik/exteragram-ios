package androidx.core.util;

import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.BuildConfig;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\b\u0012\u0004\u0012\u00028\u00000\u0003B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0011\u0010\b\u001a\u0004\u0018\u00018\u0000H\u0016¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u000b2\u0006\u0010\n\u001a\u00028\u0000H\u0016¢\u0006\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"androidx/core/util/Pools$SynchronizedPool", _UrlKt.FRAGMENT_ENCODE_SET, "T", "Landroidx/core/util/Pools$SimplePool;", _UrlKt.FRAGMENT_ENCODE_SET, "maxPoolSize", "<init>", "(I)V", "acquire", "()Ljava/lang/Object;", "instance", _UrlKt.FRAGMENT_ENCODE_SET, BuildConfig.BUILD_TYPE, "(Ljava/lang/Object;)Z", "lock", "Ljava/lang/Object;", "core"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class Pools$SynchronizedPool<T> extends Pools$SimplePool<T> {
    private final Object lock;

    public Pools$SynchronizedPool(int r1) {
        super(r1);
        this.lock = new Object();
    }

    @Override // androidx.core.util.Pools$SimplePool, androidx.core.util.Pools$Pool
    public T acquire() {
        Object r0 = this.lock;
        monitor-enter(r0);
        T r1 = (T) super.acquire();
        monitor-exit(r0);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }

    @Override // androidx.core.util.Pools$SimplePool, androidx.core.util.Pools$Pool
    public boolean release(T r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        boolean r1 = super.release(r2);     // Catch: Throwable -> L7
        monitor-exit(r0);
        return r1;
    L7:
        th = move-exception;
        throw th;
    }
}
