package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import okhttp3.internal.url._UrlKt;
import retrofit2.Utils$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010(\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\b\u0012\u0004\u0012\u0002H\u00010\u00032\b\u0012\u0004\u0012\u00020\u00050\u0004B\t\bF¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\u0013\u001a\u00020\u0014H\u0096\u0082\u0004J\u000f\u0010\u0015\u001a\u00028\u0000H\u0096\u0082\u0004¢\u0006\u0002\u0010\u0016J\u000f\u0010\u0017\u001a\u00028\u0000H\u0082\u0080\u0004¢\u0006\u0002\u0010\u0016J\n\u0010\u0018\u001a\u00020\u0019H\u0082\u0080\u0004J\u0017\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00028\u0000H\u0096À\u0004¢\u0006\u0002\u0010\u001cJ\u001d\u0010\u001d\u001a\u00020\u00052\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003H\u0096À\u0004¢\u0006\u0002\u0010\u001fJ\u001d\u0010 \u001a\u00020\u00052\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050\"H\u0096\u0080\u0004¢\u0006\u0002\u0010#R\u0013\u0010\b\u001a\u00060\tj\u0002`\nX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u0013\u0010\u000b\u001a\u0004\u0018\u00018\u0000X\u0082\u008e\b¢\u0006\u0004\n\u0002\u0010\fR\u0017\u0010\r\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003X\u0082\u008e\b¢\u0006\u0002\n\u0000R#\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u0086\u008e\b¢\u0006\u000e\n\u0000\u001a\u0004\b\u000f\u0010\u0010\"\u0004\b\u0011\u0010\u0012R\u0015\u0010$\u001a\u00020%8VX\u0096\u0084\b¢\u0006\u0006\u001a\u0004\b&\u0010'¨\u0006("}, d2 = {"Lkotlin/sequences/SequenceBuilderIterator;", "T", "Lkotlin/sequences/SequenceScope;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "state", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/sequences/State;", "nextValue", "Ljava/lang/Object;", "nextIterator", "nextStep", "getNextStep", "()Lkotlin/coroutines/Continuation;", "setNextStep", "(Lkotlin/coroutines/Continuation;)V", "hasNext", _UrlKt.FRAGMENT_ENCODE_SET, "next", "()Ljava/lang/Object;", "nextNotReady", "exceptionalState", _UrlKt.FRAGMENT_ENCODE_SET, "yield", "value", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "yieldAll", "iterator", "(Ljava/util/Iterator;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "resumeWith", "result", "Lkotlin/Result;", "(Ljava/lang/Object;)V", "context", "Lkotlin/coroutines/CoroutineContext;", "getContext", "()Lkotlin/coroutines/CoroutineContext;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
final class SequenceBuilderIterator<T> extends SequenceScope<T> implements Iterator<T>, Continuation<Unit>, KMappedMarker {
    private Iterator<? extends T> nextIterator;
    private Continuation<? super Unit> nextStep;
    private T nextValue;
    private int state;

    public SequenceBuilderIterator() {
    }

    private final Throwable exceptionalState() {
        int r0 = this.state;
        if (r0 == 4) goto L11;
        if (r0 == 5) goto L9;
        return new IllegalStateException("Unexpected state of the iterator: " + this.state);
    L9:
        return new IllegalStateException("Iterator has failed.");
    L11:
        return new NoSuchElementException();
    }

    private final T nextNotReady() {
        if (hasNext() == true) goto L5;
        Utils$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        return next();
    }

    @Override // kotlin.coroutines.Continuation
    public CoroutineContext getContext() {
        return EmptyCoroutineContext.INSTANCE;
    }

    public final Continuation<Unit> getNextStep() {
        return this.nextStep;
    }

    @Override // java.util.Iterator
    public boolean hasNext() {
    L2:
        int r0 = this.state;
        if (r0 == 0) goto L21;
        if (r0 != 1) goto L6;
        if (this.nextIterator.hasNext() == true) goto L18;
        this.nextIterator = null;
        goto L21
    L18:
        this.state = 2;
        return true;
    L6:
        if (r0 != 2) goto L8;
    L15:
        return true;
    L8:
        if (r0 == 3) goto L15;
        if (r0 != 4) goto L14;
        return false;
    L14:
        throw exceptionalState();
    L21:
        this.state = 5;
        Continuation<? super Unit> r02 = this.nextStep;
        this.nextStep = null;
        r02.resumeWith(Result.m2018constructorimpl(Unit.INSTANCE));
        goto L2
    }

    @Override // java.util.Iterator
    public T next() {
        int r0 = this.state;
        if (r0 == 0) goto L17;
        if (r0 == 1) goto L17;
        if (r0 != 2) goto L9;
        this.state = 1;
        return this.nextIterator.next();
    L9:
        if (r0 != 3) goto L13;
        this.state = 0;
        T r02 = this.nextValue;
        this.nextValue = null;
        return r02;
    L13:
        throw exceptionalState();
    L17:
        return nextNotReady();
    }

    @Override // java.util.Iterator
    public void remove() {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }

    @Override // kotlin.coroutines.Continuation
    public void resumeWith(Object r1) {
        ResultKt.throwOnFailure(r1);
        this.state = 4;
    }

    public final void setNextStep(Continuation<? super Unit> r1) {
        this.nextStep = r1;
    }

    @Override // kotlin.sequences.SequenceScope
    public Object yield(T r1, Continuation<? super Unit> r2) {
        this.nextValue = r1;
        this.state = 3;
        this.nextStep = r2;
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (r0 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        DebugProbesKt.probeCoroutineSuspended(r2);
    L6:
        if (r0 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L9;
        return r0;
    L9:
        return Unit.INSTANCE;
    }

    @Override // kotlin.sequences.SequenceScope
    public Object yieldAll(Iterator<? extends T> r2, Continuation<? super Unit> r3) {
        if (r2.hasNext() == false) goto L5;
        this.nextIterator = r2;
        this.state = 2;
        this.nextStep = r3;
        Object r1 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (r1 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L10;
        DebugProbesKt.probeCoroutineSuspended(r3);
    L10:
        if (r1 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L13;
        return r1;
    L13:
        return Unit.INSTANCE;
    L5:
        return Unit.INSTANCE;
    }
}
