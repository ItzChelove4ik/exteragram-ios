package androidx.camera.camera2.impl;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LowLightBoostControl$setLowLightBoostAsync$2$1$2 implements Function1<Throwable, Unit> {
    final /* synthetic */ CompletableDeferred<Unit> $signal;
    final /* synthetic */ LowLightBoostControl this$0;

    public LowLightBoostControl$setLowLightBoostAsync$2$1$2(CompletableDeferred<Unit> r1, LowLightBoostControl r2) {
        this.$signal = r1;
        this.this$0 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Unit invoke(Throwable r1) {
        invoke2(r1);
        return Unit.INSTANCE;
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final void invoke2(Throwable r2) {
        if (Intrinsics.areEqual(this.$signal, LowLightBoostControl.access$get_updateSignal$p(this.this$0)) == false) goto L6;
        LowLightBoostControl.access$set_updateSignal$p(this.this$0, null);
        return;
    }
}
