package androidx.camera.camera2.impl;

import android.hardware.camera2.CaptureRequest;
import androidx.camera.camera2.impl.UseCaseCameraRequestControl;
import androidx.camera.core.impl.Config;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.impl.UseCaseCameraRequestControlImpl$setParametersAsync$1$1", f = "UseCaseCameraRequestControl.kt", i = {}, l = {351}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
public final class UseCaseCameraRequestControlImpl$setParametersAsync$1$1 extends SuspendLambda implements Function1<Continuation<? super Deferred<? extends Unit>>, Object> {
    final /* synthetic */ Config.OptionPriority $optionPriority;
    final /* synthetic */ UseCaseCameraRequestControl.Type $type;
    final /* synthetic */ Map<CaptureRequest.Key<?>, Object> $values;
    int label;
    final /* synthetic */ UseCaseCameraRequestControlImpl this$0;

    public UseCaseCameraRequestControlImpl$setParametersAsync$1$1(UseCaseCameraRequestControlImpl r1, UseCaseCameraRequestControl.Type r2, Map<CaptureRequest.Key<?>, ? extends Object> r3, Config.OptionPriority r4, Continuation<? super UseCaseCameraRequestControlImpl$setParametersAsync$1$1> r5) {
        this.this$0 = r1;
        this.$type = r2;
        this.$values = r3;
        this.$optionPriority = r4;
        super(1, r5);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> r7) {
        return new UseCaseCameraRequestControlImpl$setParametersAsync$1$1(this.this$0, this.$type, this.$values, this.$optionPriority, r7);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Deferred<? extends Unit>> r1) {
        return invoke2(r1);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Continuation<? super Deferred<Unit>> r1) {
        return ((UseCaseCameraRequestControlImpl$setParametersAsync$1$1) create(r1)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r6) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L9;
        if (r1 != 1) goto L7;
        ResultKt.throwOnFailure(r6);
        return r6;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L9:
        ResultKt.throwOnFailure(r6);
        UseCaseCameraRequestControlImpl r62 = this.this$0;
        UseCaseCameraRequestControl.Type r12 = this.$type;
        Map<CaptureRequest.Key<?>, Object> r3 = this.$values;
        Config.OptionPriority r4 = this.$optionPriority;
        this.label = 1;
        Object r5 = UseCaseCameraRequestControlImpl.access$setParametersInternal(r62, r12, r3, r4, this);
        if (r5 != r0) goto L12;
        return r0;
    L12:
        return r5;
    }
}
