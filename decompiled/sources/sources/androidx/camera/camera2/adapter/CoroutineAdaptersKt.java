package androidx.camera.camera2.adapter;

import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.impl.utils.futures.FutureChain;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import com.google.common.util.concurrent.ListenableFuture;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.TimeoutKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a#\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003*\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0005\u0010\u0006\u001a/\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u00028\u00000\b2\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0001¢\u0006\u0004\b\u0005\u0010\t\u001a#\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u00028\u00000\b¢\u0006\u0004\b\n\u0010\u000b\u001a+\u0010\u000f\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u00028\u00000\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\f¢\u0006\u0004\b\u000f\u0010\u0010\u001aE\u0010\u000f\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\u0011*\b\u0012\u0004\u0012\u00028\u00000\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\f2\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0012¢\u0006\u0004\b\u000f\u0010\u0014\u001a5\u0010\u0017\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u00028\u00000\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00000\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015¢\u0006\u0004\b\u0017\u0010\u0018\u001aO\u0010\u0017\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0007\"\u0004\b\u0001\u0010\u0011*\b\u0012\u0004\u0012\u00028\u00000\b2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00028\u00010\f2\b\u0010\u0016\u001a\u0004\u0018\u00010\u00152\u0012\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0012¢\u0006\u0004\b\u0017\u0010\u0019\u001a%\u0010\u001b\u001a\u00020\u000e\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u00028\u00000\f2\u0006\u0010\u001a\u001a\u00020\u0015¢\u0006\u0004\b\u001b\u0010\u001c\u001a(\u0010 \u001a\u00020\u001f\"\u0004\b\u0000\u0010\u0007*\b\u0012\u0004\u0012\u00028\u00000\b2\u0006\u0010\u001e\u001a\u00020\u001dH\u0086@¢\u0006\u0004\b \u0010!¨\u0006\""}, d2 = {"Lkotlinx/coroutines/Job;", _UrlKt.FRAGMENT_ENCODE_SET, "tag", "Lcom/google/common/util/concurrent/ListenableFuture;", "Ljava/lang/Void;", "asListenableFuture", "(Lkotlinx/coroutines/Job;Ljava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;", "T", "Lkotlinx/coroutines/Deferred;", "(Lkotlinx/coroutines/Deferred;Ljava/lang/Object;)Lcom/google/common/util/concurrent/ListenableFuture;", "asVoidListenableFuture", "(Lkotlinx/coroutines/Deferred;)Lcom/google/common/util/concurrent/ListenableFuture;", "Lkotlinx/coroutines/CompletableDeferred;", "destination", _UrlKt.FRAGMENT_ENCODE_SET, "propagateTo", "(Lkotlinx/coroutines/Deferred;Lkotlinx/coroutines/CompletableDeferred;)V", "R", "Lkotlin/Function1;", "transform", "(Lkotlinx/coroutines/Deferred;Lkotlinx/coroutines/CompletableDeferred;Lkotlin/jvm/functions/Function1;)V", _UrlKt.FRAGMENT_ENCODE_SET, "completionCause", "propagateCompletion", "(Lkotlinx/coroutines/Deferred;Lkotlinx/coroutines/CompletableDeferred;Ljava/lang/Throwable;)V", "(Lkotlinx/coroutines/Deferred;Lkotlinx/coroutines/CompletableDeferred;Ljava/lang/Throwable;Lkotlin/jvm/functions/Function1;)V", "cause", "completeFailing", "(Lkotlinx/coroutines/CompletableDeferred;Ljava/lang/Throwable;)V", _UrlKt.FRAGMENT_ENCODE_SET, "timeoutMillis", _UrlKt.FRAGMENT_ENCODE_SET, "awaitUntil", "(Lkotlinx/coroutines/Deferred;JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "camera-camera2"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCoroutineAdapters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoroutineAdapters.kt\nandroidx/camera/camera2/adapter/CoroutineAdaptersKt\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,200:1\n1#2:201\n*E\n"})
/* loaded from: classes3.dex */
public abstract class CoroutineAdaptersKt {

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.adapter.CoroutineAdaptersKt", f = "CoroutineAdapters.kt", i = {}, l = {199}, m = "awaitUntil", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.adapter.CoroutineAdaptersKt$awaitUntil$1, reason: invalid class name */
    public static final class AnonymousClass1<T> extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;

        public AnonymousClass1(Continuation<? super AnonymousClass1> r1) {
            super(r1);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r3) {
            this.result = r3;
            this.label |= Integer.MIN_VALUE;
            return CoroutineAdaptersKt.awaitUntil(null, 0, this);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.adapter.CoroutineAdaptersKt$awaitUntil$2", f = "CoroutineAdapters.kt", i = {}, l = {199}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.adapter.CoroutineAdaptersKt$awaitUntil$2, reason: invalid class name */
    public static final class AnonymousClass2<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ Deferred<T> $this_awaitUntil;
        int label;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass2(Deferred<? extends T> r1, Continuation<? super AnonymousClass2> r2) {
            this.$this_awaitUntil = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass2(this.$this_awaitUntil, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Object r2) {
            return invoke(r1, (Continuation) r2);
        }

        public final Object invoke(CoroutineScope r1, Continuation<? super T> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L9;
            if (r1 != 1) goto L7;
            ResultKt.throwOnFailure(r4);
            return r4;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r4);
            Deferred<T> r42 = this.$this_awaitUntil;
            this.label = 1;
            Object r3 = r42.await(this);
            if (r3 != r0) goto L12;
            return r0;
        L12:
            return r3;
        }
    }

    public static Object $r8$lambda$291ob6vuDNDeI74nkG615Jp79gg(Job r1, Object r2, CallbackToFutureAdapter.Completer r3) {
        r1.invokeOnCompletion(new CoroutineAdaptersKt$$ExternalSyntheticLambda4(r3));
        return r2;
    }

    public static Object $r8$lambda$CWMmOpF3SrnUZxqkZ9tNskVvWfc(Deferred r1, Object r2, CallbackToFutureAdapter.Completer r3) {
        r1.invokeOnCompletion(new CoroutineAdaptersKt$$ExternalSyntheticLambda5(r3, r1));
        return r2;
    }

    /* renamed from: $r8$lambda$MYmxNEn3jLkJk17hLnjOKOP-vkU, reason: not valid java name */
    public static Void m16$r8$lambda$MYmxNEn3jLkJk17hLnjOKOPvkU(Object r0) {
        return null;
    }

    public static Unit $r8$lambda$NqijppL2DSYQX2kgqByrGzDCers(Deferred r0, CompletableDeferred r1, Throwable r2) {
        propagateCompletion(r0, r1, r2);
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$V0luSTHRidOVmS9hfgdjliJRNUE(Deferred r0, CompletableDeferred r1, Function1 r2, Throwable r3) {
        propagateCompletion(r0, r1, r3, r2);
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$jVJCEkJI0ZZBOGn-an7ETARyhJY, reason: not valid java name */
    public static /* synthetic */ Unit m17$r8$lambda$jVJCEkJI0ZZBOGnan7ETARyhJY(CallbackToFutureAdapter.Completer r0, Deferred r1, Throwable r2) {
        return asListenableFuture$lambda$1$0(r0, r1, r2);
    }

    /* renamed from: $r8$lambda$yh7VCNLWmlYjZayiRXB7r-NOQlQ, reason: not valid java name */
    public static /* synthetic */ Unit m18$r8$lambda$yh7VCNLWmlYjZayiRXB7rNOQlQ(CallbackToFutureAdapter.Completer r0, Throwable r1) {
        return asListenableFuture$lambda$0$0(r0, r1);
    }

    public static final <T> ListenableFuture<T> asListenableFuture(Deferred<? extends T> r1, Object r2) {
        return CallbackToFutureAdapter.getFuture(new CoroutineAdaptersKt$$ExternalSyntheticLambda3(r1, r2));
    }

    public static final ListenableFuture<Void> asListenableFuture(Job r1, Object r2) {
        return CallbackToFutureAdapter.getFuture(new CoroutineAdaptersKt$$ExternalSyntheticLambda0(r1, r2));
    }

    public static /* synthetic */ ListenableFuture asListenableFuture$default(Deferred r0, Object r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = "Deferred.asListenableFuture";
    L6:
        return asListenableFuture(r0, r1);
    }

    public static /* synthetic */ ListenableFuture asListenableFuture$default(Job r0, Object r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L6;
        r1 = "Job.asListenableFuture";
    L6:
        return asListenableFuture(r0, r1);
    }

    private static final Unit asListenableFuture$lambda$0$0(CallbackToFutureAdapter.Completer r1, Throwable r2) {
        if (r2 != null) goto L4;
        r1.set(null);
    L9:
        return Unit.INSTANCE;
    L4:
        if ((r2 instanceof CancellationException) == false) goto L6;
        r1.setCancelled();
        goto L9
    L6:
        r1.setException(r2);
        goto L9
    }

    private static final Unit asListenableFuture$lambda$1$0(CallbackToFutureAdapter.Completer r0, Deferred r1, Throwable r2) {
        if (r2 != null) goto L4;
        r0.set(r1.getCompleted());
    L9:
        return Unit.INSTANCE;
    L4:
        if ((r2 instanceof CancellationException) == false) goto L6;
        r0.setCancelled();
        goto L9
    L6:
        r0.setException(r2);
        goto L9
    }

    public static final <T> ListenableFuture<Void> asVoidListenableFuture(Deferred<? extends T> r2) {
        return FutureChain.from(asListenableFuture$default(r2, null, 1, null)).transform(new CoroutineAdaptersKt$$ExternalSyntheticLambda1(), CameraXExecutors.directExecutor());
    }

    public static final <T> Object awaitUntil(Deferred<? extends T> r5, long r6, Continuation<? super Boolean> r8) {
        if ((r8 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r8;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r82 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        boolean r4 = true;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        ResultKt.throwOnFailure(r82);
    L17:
        if (r82 != null) goto L21;
        r4 = false;
    L21:
        return Boxing.boxBoolean(r4);
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r82);
        AnonymousClass2 r83 = new AnonymousClass2(r5, null);
        r0.label = 1;
        r82 = TimeoutKt.withTimeoutOrNull(r6, r83, r0);
        if (r82 != r12) goto L17;
        return r12;
    L7:
        r0 = new AnonymousClass1(r8);
        goto L8
    }

    public static final <T> void completeFailing(CompletableDeferred<T> r1, Throwable r2) {
        if ((r2 instanceof CancellationException) == false) goto L6;
        r1.cancel((CancellationException) r2);
        return;
    L6:
        r1.completeExceptionally(r2);
    }

    public static final <T> void propagateCompletion(Deferred<? extends T> r0, CompletableDeferred<T> r1, Throwable r2) {
        if (r2 == null) goto L5;
        completeFailing(r1, r2);
        return;
    L5:
        r1.complete(r0.getCompleted());
    }

    public static final <T, R> void propagateCompletion(Deferred<? extends T> r0, CompletableDeferred<R> r1, Throwable r2, Function1<? super T, ? extends R> r3) {
        if (r2 == null) goto L5;
        completeFailing(r1, r2);
        return;
    L5:
        r1.complete(r3.invoke(r0.getCompleted()));
    }

    public static final <T> void propagateTo(Deferred<? extends T> r1, CompletableDeferred<T> r2) {
        r1.invokeOnCompletion(new CoroutineAdaptersKt$$ExternalSyntheticLambda2(r1, r2));
    }

    public static final <T, R> void propagateTo(Deferred<? extends T> r1, CompletableDeferred<R> r2, Function1<? super T, ? extends R> r3) {
        r1.invokeOnCompletion(new CoroutineAdaptersKt$$ExternalSyntheticLambda6(r1, r2, r3));
    }
}
