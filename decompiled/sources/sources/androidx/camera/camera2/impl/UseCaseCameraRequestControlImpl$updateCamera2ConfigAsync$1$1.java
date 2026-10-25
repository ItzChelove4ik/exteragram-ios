package androidx.camera.camera2.impl;

import android.util.Log;
import androidx.camera.camera2.impl.UseCaseCameraRequestControl;
import androidx.camera.camera2.impl.UseCaseCameraRequestControlImpl;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.Config;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.MapsKt;
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
@DebugMetadata(c = "androidx.camera.camera2.impl.UseCaseCameraRequestControlImpl$updateCamera2ConfigAsync$1$1", f = "UseCaseCameraRequestControl.kt", i = {}, l = {441}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nUseCaseCameraRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$updateCamera2ConfigAsync$1$1\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,742:1\n85#2,4:743\n*S KotlinDebug\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$updateCamera2ConfigAsync$1$1\n*L\n435#1:743,4\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseCameraRequestControlImpl$updateCamera2ConfigAsync$1$1 extends SuspendLambda implements Function1<Continuation<? super Deferred<? extends Unit>>, Object> {
    final /* synthetic */ Config $config;
    final /* synthetic */ Map<String, Object> $tags;
    int label;
    final /* synthetic */ UseCaseCameraRequestControlImpl this$0;

    public UseCaseCameraRequestControlImpl$updateCamera2ConfigAsync$1$1(UseCaseCameraRequestControlImpl r1, Config r2, Map<String, ? extends Object> r3, Continuation<? super UseCaseCameraRequestControlImpl$updateCamera2ConfigAsync$1$1> r4) {
        this.this$0 = r1;
        this.$config = r2;
        this.$tags = r3;
        super(1, r4);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Continuation<?> r4) {
        return new UseCaseCameraRequestControlImpl$updateCamera2ConfigAsync$1$1(this.this$0, this.$config, this.$tags, r4);
    }

    @Override // kotlin.jvm.functions.Function1
    public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Deferred<? extends Unit>> r1) {
        return invoke2(r1);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(Continuation<? super Deferred<Unit>> r1) {
        return ((UseCaseCameraRequestControlImpl$updateCamera2ConfigAsync$1$1) create(r1)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r11) {
        Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r1 = this.label;
        if (r1 == 0) goto L9;
        if (r1 != 1) goto L7;
        ResultKt.throwOnFailure(r11);
        return r11;
    L7:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L9:
        ResultKt.throwOnFailure(r11);
        Camera2Logger r112 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L12;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "UseCaseCameraRequestControlImpl#updateCamera2ConfigAsync");
    L12:
        UseCaseCameraRequestControlImpl.access$getInfoBundleMap$p(this.this$0).put(UseCaseCameraRequestControl.Type.CAMERA2_CAMERA_CONTROL, new UseCaseCameraRequestControlImpl.InfoBundle(UseCaseCameraRequestControlImpl.Companion.access$extractCamera2ImplConfigBuilder(UseCaseCameraRequestControlImpl.Companion, this.$config), MapsKt.toMutableMap(this.$tags), null, null, 12, null));
        UseCaseCameraRequestControlImpl r4 = this.this$0;
        UseCaseCameraRequestControlImpl.InfoBundle r5 = UseCaseCameraRequestControlImpl.access$merge(r4, UseCaseCameraRequestControlImpl.access$getInfoBundleMap$p(r4));
        this.label = 1;
        Object r10 = UseCaseCameraRequestControlImpl.updateCameraStateAsync$default(r4, r5, null, this, 1, null);
        if (r10 != r0) goto L15;
        return r0;
    L15:
        return r10;
    }
}
