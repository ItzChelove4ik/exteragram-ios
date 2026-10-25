package androidx.camera.camera2.adapter;

import android.util.Log;
import androidx.camera.core.CameraIdentifier;
import androidx.concurrent.futures.CallbackToFutureAdapter;
import java.util.ArrayList;
import java.util.List;
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

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
@DebugMetadata(c = "androidx.camera.camera2.adapter.PipeCameraPresenceSource$fetchData$1$1", f = "PipeCameraPresenceSource.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
@SourceDebugExtension({"SMAP\nPipeCameraPresenceSource.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PipeCameraPresenceSource.kt\nandroidx/camera/camera2/adapter/PipeCameraPresenceSource$fetchData$1$1\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,151:1\n11546#2,9:152\n13472#2:161\n13473#2:163\n11555#2:164\n1#3:162\n*S KotlinDebug\n*F\n+ 1 PipeCameraPresenceSource.kt\nandroidx/camera/camera2/adapter/PipeCameraPresenceSource$fetchData$1$1\n*L\n121#1:152,9\n121#1:161\n121#1:163\n121#1:164\n121#1:162\n*E\n"})
/* loaded from: classes3.dex */
public final class PipeCameraPresenceSource$fetchData$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ CallbackToFutureAdapter.Completer<List<CameraIdentifier>> $completer;
    int label;
    final /* synthetic */ PipeCameraPresenceSource this$0;

    public PipeCameraPresenceSource$fetchData$1$1(PipeCameraPresenceSource r1, CallbackToFutureAdapter.Completer<List<CameraIdentifier>> r2, Continuation<? super PipeCameraPresenceSource$fetchData$1$1> r3) {
        this.this$0 = r1;
        this.$completer = r2;
        super(2, r3);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
        return new PipeCameraPresenceSource$fetchData$1$1(this.this$0, this.$completer, r3);
    }

    @Override // kotlin.jvm.functions.Function2
    public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
        return invoke2(r1, r2);
    }

    /* renamed from: invoke, reason: avoid collision after fix types in other method */
    public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
        return ((PipeCameraPresenceSource$fetchData$1$1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object r10) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label != 0) goto L23;
        ResultKt.throwOnFailure(r10);
        String[] r102 = PipeCameraPresenceSource.access$getCameraManager$p(this.this$0).getCameraIdList();     // Catch: Exception -> L11
        ArrayList r1 = new ArrayList();     // Catch: Exception -> L11
        int r3 = r102.length;     // Catch: Exception -> L11
        int r4 = 0;
    L6:
        if (r4 >= r3) goto L18;
        String r5 = r102[r4];     // Catch: Exception -> L11
        CameraIdentifier r52 = CameraIdentifier.Factory.create$default(r5, null, null, 6, null);     // Catch: Exception -> L11 IllegalArgumentException -> L13
    L15:
        if (r52 == null) goto L17;
        r1.add(r52);     // Catch: Exception -> L11
    L17:
        r4 = r4 + 1;     // Catch: Exception -> L11
        goto L6
    L13:
        e = move-exception;
        Log.w("PipePresenceSrc", "Could not create CameraIdentifier for system ID: " + r5, e);     // Catch: Exception -> L11
        r52 = null;
        goto L15
    L18:
        Log.d("PipePresenceSrc", "[FetchData] Refreshed camera list from hardware: " + r1);     // Catch: Exception -> L11
        PipeCameraPresenceSource.access$updateData(this.this$0, r1);     // Catch: Exception -> L11
        this.$completer.set(r1);     // Catch: Exception -> L11
    L22:
        return Unit.INSTANCE;
    L11:
        e = move-exception;
        Log.e("PipePresenceSrc", "[FetchData] Failed to refresh camera list from hardware.", e);
        PipeCameraPresenceSource.access$updateError(this.this$0, e);
        this.$completer.setException(e);
        goto L22
    L23:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    }
}
