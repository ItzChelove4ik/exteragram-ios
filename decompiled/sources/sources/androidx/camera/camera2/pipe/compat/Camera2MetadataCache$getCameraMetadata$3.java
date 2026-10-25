package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraMetadata;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/camera/camera2/pipe/CameraMetadata;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.pipe.compat.Camera2MetadataCache$getCameraMetadata$3", f = "Camera2MetadataCache.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
/* loaded from: classes3.dex */
public final class Camera2MetadataCache$getCameraMetadata$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super CameraMetadata>, Object> {
    final /* synthetic */ String $cameraId;
    int label;
    final /* synthetic */ Camera2MetadataCache this$0;

    public Camera2MetadataCache$getCameraMetadata$3(Camera2MetadataCache r1, String r2, Continuation<? super Camera2MetadataCache$getCameraMetadata$3> r3) {
        this.this$0 = r1;
        this.$cameraId = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        return new Camera2MetadataCache$getCameraMetadata$3(this.this$0, this.$cameraId, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super CameraMetadata> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super CameraMetadata> r2) {
        return ((Camera2MetadataCache$getCameraMetadata$3) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r2) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) goto L6;
        ResultKt.throwOnFailure(r2);
        return this.this$0.mo456awaitCameraMetadataEfqyGwQ(this.$cameraId);
    L6:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
