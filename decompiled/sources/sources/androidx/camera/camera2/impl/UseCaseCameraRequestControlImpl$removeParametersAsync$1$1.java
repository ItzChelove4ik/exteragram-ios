package androidx.camera.camera2.impl;

import android.hardware.camera2.CaptureRequest;
import android.util.Log;
import androidx.camera.camera2.impl.UseCaseCameraRequestControl;
import androidx.camera.camera2.impl.UseCaseCameraRequestControlImpl;
import androidx.camera.core.Logger;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.impl.UseCaseCameraRequestControlImpl$removeParametersAsync$1$1", f = "UseCaseCameraRequestControl.kt", i = {}, l = {394}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nUseCaseCameraRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$removeParametersAsync$1$1\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n*L\n1#1,742:1\n85#2,4:743\n384#3,7:747\n*S KotlinDebug\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$removeParametersAsync$1$1\n*L\n389#1:743,4\n392#1:747,7\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseCameraRequestControlImpl$removeParametersAsync$1$1 extends SuspendLambda implements Function1<Continuation<? super Deferred<? extends Unit>>, Object> {
    final /* synthetic */ List<CaptureRequest.Key<?>> $keys;
    final /* synthetic */ UseCaseCameraRequestControl.Type $type;
    int label;
    final /* synthetic */ UseCaseCameraRequestControlImpl this$0;

    /* JADX WARN: Multi-variable type inference failed */
    public UseCaseCameraRequestControlImpl$removeParametersAsync$1$1(UseCaseCameraRequestControlImpl r1, UseCaseCameraRequestControl.Type r2, List<? extends CaptureRequest.Key<?>> r3, Continuation<? super UseCaseCameraRequestControlImpl$removeParametersAsync$1$1> r4) {
        this.this$0 = r1;
        this.$type = r2;
        this.$keys = r3;
        super(1, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> r4) {
        return new UseCaseCameraRequestControlImpl$removeParametersAsync$1$1(this.this$0, this.$type, this.$keys, r4);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Deferred<? extends Unit>> r1) {
        return invoke2(r1);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Continuation<? super Deferred<Unit>> r1) {
        return ((UseCaseCameraRequestControlImpl$removeParametersAsync$1$1) create(r1)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r12) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L9;
        if (r1 != 1) goto L7;
        ResultKt.throwOnFailure(r12);
        return r12;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L9:
        ResultKt.throwOnFailure(r12);
        Camera2Logger r122 = Camera2Logger.INSTANCE;
        UseCaseCameraRequestControl.Type r123 = this.$type;
        List<CaptureRequest.Key<?>> r13 = this.$keys;
        if (Logger.isDebugEnabled("CXCP") == false) goto L12;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "UseCaseCameraRequestControlImpl#removeParametersAsync: [" + r123 + "] keys = " + r13);
    L12:
        Map r124 = UseCaseCameraRequestControlImpl.access$getInfoBundleMap$p(this.this$0);
        UseCaseCameraRequestControl.Type r14 = this.$type;
        Object r3 = r124.get(r14);
        if (r3 != null) goto L15;
        UseCaseCameraRequestControlImpl.InfoBundle r4 = new UseCaseCameraRequestControlImpl.InfoBundle(null, null, null, null, 15, null);
        r124.put(r14, r4);
        r3 = r4;
    L15:
        UseCaseCameraRequestControlImpl.access$getInfoBundleMap$p(this.this$0).put(this.$type, UseCaseCameraRequestControlImpl.access$withoutParameters(this.this$0, (UseCaseCameraRequestControlImpl.InfoBundle) r3, this.$keys));
        UseCaseCameraRequestControlImpl r42 = this.this$0;
        UseCaseCameraRequestControlImpl.InfoBundle r5 = UseCaseCameraRequestControlImpl.access$merge(r42, UseCaseCameraRequestControlImpl.access$getInfoBundleMap$p(r42));
        this.label = 1;
        Object r11 = UseCaseCameraRequestControlImpl.updateCameraStateAsync$default(r42, r5, null, this, 1, null);
        if (r11 != r0) goto L18;
        return r0;
    L18:
        return r11;
    }
}
