package androidx.camera.camera2.adapter;

import android.util.Log;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.core.CameraIdentifier;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00028\u00000\u0001J\u001e\u0010\u0005\u001a\u00020\u00042\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0002H\u0096@¢\u0006\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"kotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1", "Lkotlinx/coroutines/flow/Flow;", "Lkotlinx/coroutines/flow/FlowCollector;", "collector", _UrlKt.FRAGMENT_ENCODE_SET, "collect", "(Lkotlinx/coroutines/flow/FlowCollector;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nSafeCollector.common.kt\nKotlin\n*S Kotlin\n*F\n+ 1 SafeCollector.common.kt\nkotlinx/coroutines/flow/internal/SafeCollector_commonKt$unsafeFlow$1\n+ 2 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt\n*L\n1#1,108:1\n47#2,5:109\n*E\n"})
/* loaded from: classes3.dex */
public final class PipeCameraPresenceSource$startMonitoring$$inlined$map$1 implements Flow<List<? extends CameraIdentifier>> {
    final /* synthetic */ Flow $this_unsafeTransform$inlined;

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nEmitters.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Emitters.kt\nkotlinx/coroutines/flow/FlowKt__EmittersKt$unsafeTransform$1$1\n+ 2 Transform.kt\nkotlinx/coroutines/flow/FlowKt__TransformKt\n+ 3 PipeCameraPresenceSource.kt\nandroidx/camera/camera2/adapter/PipeCameraPresenceSource\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,49:1\n50#2:50\n66#3:51\n67#3,10:62\n77#3:75\n1617#4,9:52\n1869#4:61\n1870#4:73\n1626#4:74\n1#5:72\n*S KotlinDebug\n*F\n+ 1 PipeCameraPresenceSource.kt\nandroidx/camera/camera2/adapter/PipeCameraPresenceSource\n*L\n66#1:52,9\n66#1:61\n66#1:73\n66#1:74\n66#1:72\n*E\n"})
    /* renamed from: androidx.camera.camera2.adapter.PipeCameraPresenceSource$startMonitoring$$inlined$map$1$2, reason: invalid class name */
    public static final class AnonymousClass2<T> implements FlowCollector {
        final /* synthetic */ FlowCollector $this_unsafeFlow;

        @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.camera.camera2.adapter.PipeCameraPresenceSource$startMonitoring$$inlined$map$1$2", f = "PipeCameraPresenceSource.kt", i = {}, l = {50}, m = "emit", n = {}, s = {}, v = 1)
        /* renamed from: androidx.camera.camera2.adapter.PipeCameraPresenceSource$startMonitoring$$inlined$map$1$2$1, reason: invalid class name */
        public static final class AnonymousClass1 extends ContinuationImpl {
            int label;
            /* synthetic */ Object result;
            final /* synthetic */ AnonymousClass2 this$0;

            public AnonymousClass1(AnonymousClass2 r1, Continuation r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object r2) {
                this.result = r2;
                this.label |= Integer.MIN_VALUE;
                return this.this$0.emit(null, this);
            }
        }

        public AnonymousClass2(FlowCollector r1) {
            this.$this_unsafeFlow = r1;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public final Object emit(Object r9, Continuation r10) {
            if ((r10 instanceof AnonymousClass1) == false) goto L7;
            AnonymousClass1 r0 = (AnonymousClass1) r10;
            int r1 = r0.label;
            if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
            r0.label = r1 - Integer.MIN_VALUE;
        L8:
            Object r102 = r0.result;
            Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r2 = r0.label;
            if (r2 == 0) goto L14;
            if (r2 != 1) goto L12;
            ResultKt.throwOnFailure(r102);
        L28:
            return Unit.INSTANCE;
        L12:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L14:
            ResultKt.throwOnFailure(r102);
            FlowCollector r8 = this.$this_unsafeFlow;
            ArrayList r103 = new ArrayList();
            Iterator<T> r92 = ((List) r9).iterator();
        L16:
            if (r92.hasNext() == false) goto L24;
            String r22 = ((CameraId) r92.next()).m233unboximpl();
            CameraIdentifier r23 = CameraIdentifier.Factory.create$default(r22, null, null, 6, null);     // Catch: Exception -> L20
        L22:
            if (r23 == null) goto L16;
            r103.add(r23);
        L20:
            e = move-exception;
            Log.w("PipePresenceSrc", "Failed to create CameraIdentifier for pipeId: " + r22, e);
            r23 = null;
            goto L22
        L24:
            r0.label = 1;
            if (r8.emit(r103, r0) != r12) goto L28;
            return r12;
        L7:
            r0 = new AnonymousClass1(this, r10);
            goto L8
        }
    }

    public PipeCameraPresenceSource$startMonitoring$$inlined$map$1(Flow r1) {
        this.$this_unsafeTransform$inlined = r1;
    }

    @Override // kotlinx.coroutines.flow.Flow
    public Object collect(FlowCollector<? super List<? extends CameraIdentifier>> r2, Continuation r3) {
        Object r1 = this.$this_unsafeTransform$inlined.collect(new AnonymousClass2(r2), r3);
        if (r1 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r1;
    L6:
        return Unit.INSTANCE;
    }
}
