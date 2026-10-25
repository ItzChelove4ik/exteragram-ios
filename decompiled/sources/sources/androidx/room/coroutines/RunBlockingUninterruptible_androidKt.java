package androidx.room.coroutines;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.ContinuationInterceptor;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.GlobalScope;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u001c\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a<\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012'\u0010\u0002\u001a#\b\u0001\u0012\u0004\u0012\u00020\u0004\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u0003¢\u0006\u0002\b\u0007H\u0000¢\u0006\u0002\u0010\b¨\u0006\t"}, d2 = {"runBlockingUninterruptible", "T", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/CoroutineScope;", "Lkotlin/coroutines/Continuation;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "room-runtime"}, k = 2, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class RunBlockingUninterruptible_androidKt {

    /* JADX INFO: Add missing generic type declarations: [T] */
    @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.room.coroutines.RunBlockingUninterruptible_androidKt$runBlockingUninterruptible$1", f = "RunBlockingUninterruptible.android.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.room.coroutines.RunBlockingUninterruptible_androidKt$runBlockingUninterruptible$1, reason: invalid class name */
    public static final class AnonymousClass1<T> extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
        final /* synthetic */ Function2<CoroutineScope, Continuation<? super T>, Object> $block;
        private /* synthetic */ Object L$0;
        int label;

        @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.room.coroutines.RunBlockingUninterruptible_androidKt$runBlockingUninterruptible$1$1", f = "RunBlockingUninterruptible.android.kt", i = {}, l = {52}, m = "invokeSuspend", n = {}, s = {})
        @SourceDebugExtension({"SMAP\nRunBlockingUninterruptible.android.kt\nKotlin\n*S Kotlin\n*F\n+ 1 RunBlockingUninterruptible.android.kt\nandroidx/room/coroutines/RunBlockingUninterruptible_androidKt$runBlockingUninterruptible$1$1\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,67:1\n1#2:68\n*E\n"})
        /* renamed from: androidx.room.coroutines.RunBlockingUninterruptible_androidKt$runBlockingUninterruptible$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C00091 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
            final /* synthetic */ Function2<CoroutineScope, Continuation<? super T>, Object> $block;
            final /* synthetic */ CompletableDeferred<T> $deferred;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: Multi-variable type inference failed */
            public C00091(CompletableDeferred<T> r1, Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r2, Continuation<? super C00091> r3) {
                this.$deferred = r1;
                this.$block = r2;
                super(2, r3);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r3, Continuation<?> r4) {
                C00091 r0 = new C00091(this.$deferred, this.$block, r4);
                r0.L$0 = r3;
                return r0;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
                return invoke2(r1, r2);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
                return ((C00091) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r6) {
                Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int r1 = this.label;
                if (r1 == 0) goto L12;
                if (r1 != 1) goto L10;
                CompletableDeferred<T> r5 = (CompletableDeferred) this.L$0;
                ResultKt.throwOnFailure(r6);     // Catch: Throwable -> L8
            L17:
                Object r62 = Result.m2018constructorimpl(r6);     // Catch: Throwable -> L8
            L22:
                CompletableDeferredKt.completeWith(r5, r62);
                return Unit.INSTANCE;
            L8:
                th = th;
            L21:
                Result.Companion r02 = Result.Companion;
                r62 = Result.m2018constructorimpl(ResultKt.createFailure(th));
                goto L22
            L10:
                Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
                return null;
            L12:
                ResultKt.throwOnFailure(r6);
                CoroutineScope r63 = (CoroutineScope) this.L$0;
                CompletableDeferred<T> r12 = this.$deferred;
                Function2<CoroutineScope, Continuation<? super T>, Object> r3 = this.$block;
                Result.Companion r4 = Result.Companion;     // Catch: Throwable -> L19
                this.L$0 = r12;     // Catch: Throwable -> L19
                this.label = 1;     // Catch: Throwable -> L19
                r6 = r3.invoke(r63, this);     // Catch: Throwable -> L19
                if (r6 != r0) goto L16;
                return r0;
            L16:
                r5 = r12;
            L19:
                th = th;
                r5 = r12;
                goto L21
            }
        }

        @Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.room.coroutines.RunBlockingUninterruptible_androidKt$runBlockingUninterruptible$1$2", f = "RunBlockingUninterruptible.android.kt", i = {}, l = {58}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: androidx.room.coroutines.RunBlockingUninterruptible_androidKt$runBlockingUninterruptible$1$2, reason: invalid class name */
        /* loaded from: classes4.dex */
        public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
            final /* synthetic */ CompletableDeferred<T> $deferred;
            int label;

            public AnonymousClass2(CompletableDeferred<T> r1, Continuation<? super AnonymousClass2> r2) {
                this.$deferred = r1;
                super(2, r2);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
                return new AnonymousClass2(this.$deferred, r2);
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
                CompletableDeferred<T> r42 = this.$deferred;
                this.label = 1;
                Object r3 = r42.await(this);
                if (r3 != r0) goto L12;
                return r0;
            L12:
                return r3;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass1(Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r1, Continuation<? super AnonymousClass1> r2) {
            this.$block = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            AnonymousClass1 r0 = new AnonymousClass1(this.$block, r3);
            r0.L$0 = r2;
            return r0;
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Object r2) {
            return invoke(r1, (Continuation) r2);
        }

        public final Object invoke(CoroutineScope r1, Continuation<? super T> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r6) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L11;
            ResultKt.throwOnFailure(r6);
            ContinuationInterceptor r62 = (ContinuationInterceptor) ((CoroutineScope) this.L$0).getCoroutineContext().get(ContinuationInterceptor.Key);
            CompletableDeferred r0 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
            BuildersKt.launch(GlobalScope.INSTANCE, r62, CoroutineStart.UNDISPATCHED, new C00091(r0, this.$block, null));
        L6:
            if (r0.isCompleted() == true) goto L10;
            return BuildersKt.runBlocking(r62, new AnonymousClass2(r0, null));
        L10:
            return r0.getCompleted();
        L11:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    public static final <T> T runBlockingUninterruptible(Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> r2) {
        Thread.interrupted();
        return (T) BuildersKt.runBlocking$default(null, new AnonymousClass1(r2, null), 1, null);
    }
}
