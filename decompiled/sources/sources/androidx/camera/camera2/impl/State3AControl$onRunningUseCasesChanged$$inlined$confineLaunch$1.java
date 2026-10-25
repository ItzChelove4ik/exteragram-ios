package androidx.camera.camera2.impl;

import java.util.Set;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0002\u001a\u00020\u0001*\u00020\u0000H\n¢\u0006\u0004\b\u0002\u0010\u0003"}, d2 = {"Lkotlinx/coroutines/CoroutineScope;", _UrlKt.FRAGMENT_ENCODE_SET, "<anonymous>", "(Lkotlinx/coroutines/CoroutineScope;)V"}, k = 3, mv = {2, 1, 0})
@DebugMetadata(c = "androidx.camera.camera2.impl.State3AControl$onRunningUseCasesChanged$$inlined$confineLaunch$1", f = "State3AControl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nUseCaseThreads.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseThreads.kt\nandroidx/camera/camera2/impl/UseCaseThreads$confineLaunch$1\n+ 2 State3AControl.kt\nandroidx/camera/camera2/impl/State3AControl\n*L\n1#1,200:1\n127#2,18:201\n*E\n"})
/* loaded from: classes3.dex */
public final class State3AControl$onRunningUseCasesChanged$$inlined$confineLaunch$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Set $useCasesSnapshot$inlined;
    int label;
    final /* synthetic */ State3AControl this$0;

    public State3AControl$onRunningUseCasesChanged$$inlined$confineLaunch$1(Continuation r1, Set r2, State3AControl r3) {
        this.$useCasesSnapshot$inlined = r2;
        this.this$0 = r3;
        super(2, r1);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        return new State3AControl$onRunningUseCasesChanged$$inlined$confineLaunch$1(r3, this.$useCasesSnapshot$inlined, this.this$0);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
        return ((State3AControl$onRunningUseCasesChanged$$inlined$confineLaunch$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r3) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) goto L22;
        ResultKt.throwOnFailure(r3);
        if (this.$useCasesSnapshot$inlined.isEmpty() == true) goto L21;
        int r32 = State3AControl.access$calculateTemplateFromUseCases(this.this$0, this.$useCasesSnapshot$inlined);
        Object r0 = State3AControl.access$getLock$p(this.this$0);
        monitor-enter(r0);
    L12:
        th = move-exception;
        throw th;
    L9:
        if (State3AControl.access$get_template$p(this.this$0) == r32) goto L14;
        State3AControl.access$set_template$p(this.this$0, r32);     // Catch: Throwable -> L12
        boolean r33 = true;
    L15:
        monitor-exit(r0);
        if (r33 == false) goto L21;
        State3AControl.access$update(this.this$0);
        goto L21
    L14:
        r33 = false;
    L21:
        return Unit.INSTANCE;
    L22:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
