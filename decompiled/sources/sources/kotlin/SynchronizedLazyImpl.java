package kotlin;

import java.io.Serializable;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u00060\u0003j\u0002`\u0004B#\bF\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\n\u0010\u0013\u001a\u00020\bH\u0082\u0080\u0004R\u0017\u0010\u0005\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0006X\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0011\u0010\u000b\u001a\u0004\u0018\u00010\bX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0007\u001a\u00020\bX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\f\u001a\u00028\u00008VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0014"}, d2 = {"Lkotlin/SynchronizedLazyImpl;", "T", "Lkotlin/Lazy;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "initializer", "Lkotlin/Function0;", "lock", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Lkotlin/jvm/functions/Function0;Ljava/lang/Object;)V", "_value", "value", "getValue", "()Ljava/lang/Object;", "isInitialized", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "writeReplace", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
final class SynchronizedLazyImpl<T> implements Lazy<T>, Serializable {
    private volatile Object _value;
    private Function0<? extends T> initializer;
    private final Object lock;

    public SynchronizedLazyImpl(Function0<? extends T> r1, Object r2) {
        this.initializer = r1;
        this._value = UNINITIALIZED_VALUE.INSTANCE;
        if (r2 != null) goto L5;
        r2 = this;
    L5:
        this.lock = r2;
    }

    public /* synthetic */ SynchronizedLazyImpl(Function0 r1, Object r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = null;
    L5:
        this(r1, r2);
    }

    private final Object writeReplace() {
        return new InitializedLazyImpl(getValue());
    }

    @Override // kotlin.Lazy
    public T getValue() {
        T r0 = (T) this._value;
        UNINITIALIZED_VALUE r1 = UNINITIALIZED_VALUE.INSTANCE;
        if (r0 == r1) goto L5;
        return r0;
    L5:
        Object r02 = this.lock;
        monitor-enter(r02);
        T r2 = (T) this._value;
        if (r2 != r1) goto L11;
        r2 = this.initializer.invoke();     // Catch: Throwable -> L13
        this._value = r2;     // Catch: Throwable -> L13
        this.initializer = null;     // Catch: Throwable -> L13
    L11:
        monitor-exit(r02);
        return r2;
    L13:
        th = move-exception;
        throw th;
    }

    @Override // kotlin.Lazy
    public boolean isInitialized() {
        if (this._value == UNINITIALIZED_VALUE.INSTANCE) goto L6;
        return true;
    L6:
        return false;
    }

    public String toString() {
        if (isInitialized() == true) goto L5;
        return "Lazy value not initialized yet.";
    L5:
        return String.valueOf(getValue());
    }
}
