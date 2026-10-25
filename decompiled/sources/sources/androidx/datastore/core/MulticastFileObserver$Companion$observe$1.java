package androidx.datastore.core;

import androidx.datastore.core.MulticastFileObserver;
import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.DisposableHandle;
import kotlinx.coroutines.channels.ProduceKt;
import kotlinx.coroutines.channels.ProducerScope;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H\u008a@"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/channels/ProducerScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.datastore.core.MulticastFileObserver$Companion$observe$1", f = "MulticastFileObserver.android.kt", i = {0, 0}, l = {84, 85}, m = "invokeSuspend", n = {"$this$channelFlow", "disposeListener"}, s = {"L$0", "L$1"})
/* loaded from: classes.dex */
public final class MulticastFileObserver$Companion$observe$1 extends SuspendLambda implements Function2<ProducerScope<? super Unit>, Continuation<? super Unit>, Object> {
    final /* synthetic */ File $file;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    @Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
    /* renamed from: androidx.datastore.core.MulticastFileObserver$Companion$observe$1$1, reason: invalid class name */
    public static final class AnonymousClass1 extends Lambda implements Function0<Unit> {
        final /* synthetic */ DisposableHandle $disposeListener;

        public AnonymousClass1(DisposableHandle r1) {
            this.$disposeListener = r1;
            super(0);
        }

        @Override // kotlin.jvm.functions.Function0
        public /* bridge */ /* synthetic */ Unit invoke() {
            invoke2();
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2() {
            this.$disposeListener.dispose();
        }
    }

    public MulticastFileObserver$Companion$observe$1(File r1, Continuation<? super MulticastFileObserver$Companion$observe$1> r2) {
        this.$file = r1;
        super(2, r2);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        MulticastFileObserver$Companion$observe$1 r0 = new MulticastFileObserver$Companion$observe$1(this.$file, r3);
        r0.L$0 = r2;
        return r0;
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(ProducerScope<? super Unit> r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(ProducerScope<? super Unit> r1, Continuation<? super Unit> r2) {
        return ((MulticastFileObserver$Companion$observe$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r8) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L10;
        if (r1 == 1) goto L9;
        if (r1 != 2) goto L7;
        ResultKt.throwOnFailure(r8);
    L18:
        return Unit.INSTANCE;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L9:
        DisposableHandle r12 = (DisposableHandle) this.L$1;
        ProducerScope r4 = (ProducerScope) this.L$0;
        ResultKt.throwOnFailure(r8);
    L14:
        AnonymousClass1 r82 = new AnonymousClass1(r12);
        this.L$0 = null;
        this.L$1 = null;
        this.label = 2;
        if (ProduceKt.awaitClose(r4, r82, this) != r0) goto L18;
    L16:
        return r0;
    L10:
        ResultKt.throwOnFailure(r8);
        ProducerScope r83 = (ProducerScope) this.L$0;
        MulticastFileObserver$Companion$observe$1$flowObserver$1 r13 = new MulticastFileObserver$Companion$observe$1$flowObserver$1(this.$file, r83);
        r12 = MulticastFileObserver.Companion.access$observe(MulticastFileObserver.Companion, this.$file.getParentFile(), r13);
        Unit r5 = Unit.INSTANCE;
        this.L$0 = r83;
        this.L$1 = r12;
        this.label = 1;
        if (r83.send(r5, this) == r0) goto L16;
        r4 = r83;
        goto L14
    }
}
