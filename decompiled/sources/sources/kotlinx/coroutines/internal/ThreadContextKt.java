package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.ThreadContextElement;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u001a\u0010\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\fH\u0000\u001a\u001c\u0010\r\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0004H\u0000\u001a\u001a\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u000b\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0004H\u0000\"\u0010\u0010\u0000\u001a\u00020\u00018\u0000X\u0081\u0004¢\u0006\u0002\n\u0000\"$\u0010\u0002\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\",\u0010\u0006\u001a \u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u0007\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u00070\u0003X\u0082\u0004¢\u0006\u0002\n\u0000\" \u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"NO_THREAD_ELEMENTS", "Lkotlinx/coroutines/internal/Symbol;", "countAll", "Lkotlin/Function2;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/coroutines/CoroutineContext$Element;", "findOne", "Lkotlinx/coroutines/ThreadContextElement;", "updateState", "Lkotlinx/coroutines/internal/ThreadState;", "threadContextElements", "context", "Lkotlin/coroutines/CoroutineContext;", "updateThreadContext", "countOrElement", "restoreThreadContext", _UrlKt.FRAGMENT_ENCODE_SET, "oldState", "kotlinx-coroutines-core"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class ThreadContextKt {

    @JvmField
    public static final Symbol NO_THREAD_ELEMENTS = null;
    private static final Function2<Object, CoroutineContext.Element, Object> countAll = null;
    private static final Function2<ThreadContextElement<?>, CoroutineContext.Element, ThreadContextElement<?>> findOne = null;
    private static final Function2<ThreadState, CoroutineContext.Element, ThreadState> updateState = null;

    /* renamed from: $r8$lambda$ALKhLFIuERQZ-zTGtZp6kk6XMi8, reason: not valid java name */
    public static ThreadState m3560$r8$lambda$ALKhLFIuERQZzTGtZp6kk6XMi8(ThreadState r1, CoroutineContext.Element r2) {
        if ((r2 instanceof ThreadContextElement) == false) goto L5;
        ThreadContextElement<?> r22 = (ThreadContextElement) r2;
        r1.append(r22, r22.updateThreadContext(r1.context));
    L5:
        return r1;
    }

    /* renamed from: $r8$lambda$JV-_KVVI_n23GsB5XC6MwwiLObk, reason: not valid java name */
    public static Object m3561$r8$lambda$JV_KVVI_n23GsB5XC6MwwiLObk(Object r1, CoroutineContext.Element r2) {
        if ((r2 instanceof ThreadContextElement) == true) goto L5;
        return r1;
    L5:
        if ((r1 instanceof Integer) == false) goto L7;
        Integer r12 = (Integer) r1;
    L9:
        if (r12 == null) goto L11;
        int r13 = r12.intValue();
    L12:
        if (r13 != 0) goto L15;
        return r2;
    L15:
        return Integer.valueOf(r13 + 1);
    L11:
        r13 = 1;
        goto L12
    L7:
        r12 = null;
        goto L9
    }

    public static ThreadContextElement $r8$lambda$j6y1jG6XeS1egVB7zeghadfcFv0(ThreadContextElement r0, CoroutineContext.Element r1) {
        if (r0 == null) goto L5;
        return r0;
    L5:
        if ((r1 instanceof ThreadContextElement) == true) goto L7;
        return null;
    L7:
        return (ThreadContextElement) r1;
    }

    static {
        NO_THREAD_ELEMENTS = new Symbol("NO_THREAD_ELEMENTS");
        countAll = new ThreadContextKt$$ExternalSyntheticLambda0();
        findOne = new ThreadContextKt$$ExternalSyntheticLambda1();
        updateState = new ThreadContextKt$$ExternalSyntheticLambda2();
    }

    public static final void restoreThreadContext(CoroutineContext r2, Object r3) {
        if (r3 != NO_THREAD_ELEMENTS) goto L6;
        return;
    L6:
        if ((r3 instanceof ThreadState) == false) goto L9;
        ((ThreadState) r3).restore(r2);
        return;
    L9:
        ((ThreadContextElement) r2.fold(null, findOne)).restoreThreadContext(r2, r3);
    }

    public static final Object threadContextElements(CoroutineContext r2) {
        return r2.fold(0, countAll);
    }

    public static final Object updateThreadContext(CoroutineContext r1, Object r2) {
        if (r2 != null) goto L5;
        r2 = threadContextElements(r1);
    L5:
        if (r2 != 0) goto L9;
        return NO_THREAD_ELEMENTS;
    L9:
        if ((r2 instanceof Integer) == false) goto L13;
        return r1.fold(new ThreadState(r1, ((Number) r2).intValue()), updateState);
    L13:
        return ((ThreadContextElement) r2).updateThreadContext(r1);
    }
}
