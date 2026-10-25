package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlinx.coroutines.ThreadContextElement;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001c\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\f2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001J\u000e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0002\u001a\u00020\u0003R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00010\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR \u0010\u000b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u00010\f0\tX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lkotlinx/coroutines/internal/ThreadState;", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Lkotlin/coroutines/CoroutineContext;", "n", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlin/coroutines/CoroutineContext;I)V", "values", _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/Object;", "elements", "Lkotlinx/coroutines/ThreadContextElement;", "[Lkotlinx/coroutines/ThreadContextElement;", "i", "append", _UrlKt.FRAGMENT_ENCODE_SET, "element", "value", "restore", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
final class ThreadState {

    @JvmField
    public final CoroutineContext context;
    private final ThreadContextElement<Object>[] elements;
    private int i;
    private final Object[] values;

    public ThreadState(CoroutineContext r1, int r2) {
        this.context = r1;
        this.values = new Object[r2];
        this.elements = new ThreadContextElement[r2];
    }

    public final void append(ThreadContextElement<?> r3, Object r4) {
        Object[] r0 = this.values;
        int r1 = this.i;
        r0[r1] = r4;
        ThreadContextElement<Object>[] r42 = this.elements;
        this.i = r1 + 1;
        r42[r1] = r3;
    }

    public final void restore(CoroutineContext r5) {
        int r0 = this.elements.length - 1;
        if (r0 < 0) goto L8;
    L4:
        int r1 = r0 - 1;
        this.elements[r0].restoreThreadContext(r5, this.values[r0]);
        if (r1 < 0) goto L10;
        r0 = r1;
        goto L4
    L10:
        return;
    }
}
