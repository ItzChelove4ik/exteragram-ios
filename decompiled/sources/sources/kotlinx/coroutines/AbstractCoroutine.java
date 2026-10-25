package kotlinx.coroutines;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\r\b'\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00002\u00020\u00022\u00020\u00032\b\u0012\u0004\u0012\u00028\u00000\u00042\u00020\u0005B\u001f\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\n\u001a\u00020\b¢\u0006\u0004\b\u000b\u0010\fJ\u0017\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00028\u0000H\u0014¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\bH\u0014¢\u0006\u0004\b\u0014\u0010\u0015J\u000f\u0010\u0017\u001a\u00020\u0016H\u0014¢\u0006\u0004\b\u0017\u0010\u0018J\u0019\u0010\u001b\u001a\u00020\u000e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0004¢\u0006\u0004\b\u001b\u0010\u0010J\u001b\u0010\u001e\u001a\u00020\u000e2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00028\u00000\u001c¢\u0006\u0004\b\u001e\u0010\u0010J\u0019\u0010\u001f\u001a\u00020\u000e2\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0014¢\u0006\u0004\b\u001f\u0010\u0010J\u0017\u0010#\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u0011H\u0000¢\u0006\u0004\b!\u0010\"J\u000f\u0010%\u001a\u00020\u0016H\u0010¢\u0006\u0004\b$\u0010\u0018JL\u0010(\u001a\u00020\u000e\"\u0004\b\u0001\u0010&2\u0006\u0010(\u001a\u00020'2\u0006\u0010)\u001a\u00028\u00012'\u0010,\u001a#\b\u0001\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00190*¢\u0006\u0002\b+¢\u0006\u0004\b(\u0010-R\u001d\u0010.\u001a\u00020\u00068\u0006¢\u0006\u0012\n\u0004\b.\u0010/\u0012\u0004\b2\u00103\u001a\u0004\b0\u00101R\u0014\u00105\u001a\u00020\u00068VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b4\u00101R\u0014\u00106\u001a\u00020\b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b6\u00107¨\u00068"}, d2 = {"Lkotlinx/coroutines/AbstractCoroutine;", "T", "Lkotlinx/coroutines/JobSupport;", "Lkotlinx/coroutines/Job;", "Lkotlin/coroutines/Continuation;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/CoroutineContext;", "parentContext", _UrlKt.FRAGMENT_ENCODE_SET, "initParentJob", "active", "<init>", "(Lkotlin/coroutines/CoroutineContext;ZZ)V", "value", _UrlKt.FRAGMENT_ENCODE_SET, "onCompleted", "(Ljava/lang/Object;)V", _UrlKt.FRAGMENT_ENCODE_SET, "cause", "handled", "onCancelled", "(Ljava/lang/Throwable;Z)V", _UrlKt.FRAGMENT_ENCODE_SET, "cancellationExceptionMessage", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "state", "onCompletionInternal", "Lkotlin/Result;", "result", "resumeWith", "afterResume", "exception", "handleOnCompletionException$kotlinx_coroutines_core", "(Ljava/lang/Throwable;)V", "handleOnCompletionException", "nameString$kotlinx_coroutines_core", "nameString", "R", "Lkotlinx/coroutines/CoroutineStart;", "start", "receiver", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "block", "(Lkotlinx/coroutines/CoroutineStart;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)V", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "getContext$annotations", "()V", "getCoroutineContext", "coroutineContext", "isActive", "()Z", "kotlinx-coroutines-core"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class AbstractCoroutine<T> extends JobSupport implements Job, Continuation<T>, CoroutineScope {
    private final CoroutineContext context;

    public AbstractCoroutine(CoroutineContext r1, boolean r2, boolean r3) {
        super(r3);
        if (r2 == false) goto L5;
        initParentJob((Job) r1.get(Job.Key));
    L5:
        this.context = r1.plus(this);
    }

    public void afterResume(Object r1) {
        afterCompletion(r1);
    }

    @Override // kotlinx.coroutines.JobSupport
    public String cancellationExceptionMessage() {
        return DebugStringsKt.getClassSimpleName(this) + " was cancelled";
    }

    @Override // kotlin.coroutines.Continuation
    public final CoroutineContext getContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.CoroutineScope
    public CoroutineContext getCoroutineContext() {
        return this.context;
    }

    @Override // kotlinx.coroutines.JobSupport
    public final void handleOnCompletionException$kotlinx_coroutines_core(Throwable r1) {
        CoroutineExceptionHandlerKt.handleCoroutineException(this.context, r1);
    }

    @Override // kotlinx.coroutines.JobSupport, kotlinx.coroutines.Job
    public boolean isActive() {
        return super.isActive();
    }

    @Override // kotlinx.coroutines.JobSupport
    public String nameString$kotlinx_coroutines_core() {
        String r0 = CoroutineContextKt.getCoroutineName(this.context);
        if (r0 != null) goto L7;
        return super.nameString$kotlinx_coroutines_core();
    L7:
        return "\"" + r0 + "\":" + super.nameString$kotlinx_coroutines_core();
    }

    public void onCancelled(Throwable r1, boolean r2) {
    }

    public void onCompleted(T r1) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // kotlinx.coroutines.JobSupport
    public final void onCompletionInternal(Object r2) {
        if ((r2 instanceof CompletedExceptionally) == false) goto L6;
        CompletedExceptionally r22 = (CompletedExceptionally) r2;
        onCancelled(r22.cause, r22.getHandled());
        return;
    L6:
        onCompleted(r2);
    }

    @Override // kotlin.coroutines.Continuation
    public final void resumeWith(Object r2) {
        Object r22 = makeCompletingOnce$kotlinx_coroutines_core(CompletionStateKt.toState(r2));
        if (r22 != JobSupportKt.COMPLETING_WAITING_CHILDREN) goto L5;
        return;
    L5:
        afterResume(r22);
    }

    public final <R> void start(CoroutineStart r1, R r2, Function2<? super R, ? super Continuation<? super T>, ? extends Object> r3) {
        r1.invoke(r3, r2, this);
    }
}
