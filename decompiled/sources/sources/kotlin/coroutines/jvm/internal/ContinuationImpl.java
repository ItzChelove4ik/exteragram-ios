package kotlin.coroutines.jvm.internal;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.3")
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\b!\u0018\u00002\u00020\u0001B%\bF\u0012\u0010\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bB\u001b\bV\u0012\u0010\u0010\u0002\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0007\u0010\tJ\u0012\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0003H\u0086\u0080\u0004J\n\u0010\u000e\u001a\u00020\u000fH\u0094\u0080\u0004R\u0011\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0015\u0010\n\u001a\u00020\u00068VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\r\u001a\f\u0012\u0006\u0012\u0004\u0018\u00010\u0004\u0018\u00010\u0003X\u0082\u008e\b¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lkotlin/coroutines/jvm/internal/ContinuationImpl;", "Lkotlin/coroutines/jvm/internal/BaseContinuationImpl;", "completion", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "_context", "Lkotlin/coroutines/CoroutineContext;", "<init>", "(Lkotlin/coroutines/Continuation;Lkotlin/coroutines/CoroutineContext;)V", "(Lkotlin/coroutines/Continuation;)V", "context", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "intercepted", "releaseIntercepted", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nContinuationImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 ContinuationImpl.kt\nkotlin/coroutines/jvm/internal/ContinuationImpl\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,169:1\n1#2:170\n*E\n"})
/* loaded from: classes.dex */
public abstract class ContinuationImpl extends BaseContinuationImpl {
    private final CoroutineContext _context;
    private transient Continuation<Object> intercepted;

    public ContinuationImpl(Continuation<Object> r2) {
        if (r2 == null) goto L4;
        CoroutineContext r0 = r2.getContext();
    L5:
        this(r2, r0);
        return;
    L4:
        r0 = null;
        goto L5
    }

    public ContinuationImpl(Continuation<Object> r1, CoroutineContext r2) {
        super(r1);
        this._context = r2;
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return this._context;
    }

    public final Continuation<Object> intercepted() {
        Continuation<Object> r0 = this.intercepted;
        if (r0 != null) goto L10;
        ContinuationInterceptor r02 = (ContinuationInterceptor) getContext().get(ContinuationInterceptor.Key);
        if (r02 == null) goto L8;
        r0 = r02.interceptContinuation(this);
        if (r0 == null) goto L8;
    L9:
        this.intercepted = r0;
    L8:
        r0 = this;
    L10:
        return r0;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public void releaseIntercepted() {
        Continuation<?> r0 = this.intercepted;
        if (r0 == null) goto L6;
        if (r0 == this) goto L6;
        ((ContinuationInterceptor) getContext().get(ContinuationInterceptor.Key)).releaseInterceptedContinuation(r0);
    L6:
        this.intercepted = CompletedContinuation.INSTANCE;
    }
}
