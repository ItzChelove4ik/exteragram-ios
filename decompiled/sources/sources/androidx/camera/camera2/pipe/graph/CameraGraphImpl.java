package androidx.camera.camera2.pipe.graph;

import android.hardware.camera2.params.MeteringRectangle;
import android.os.Build;
import android.os.Trace;
import android.view.Surface;
import androidx.camera.camera2.pipe.AeMode;
import androidx.camera.camera2.pipe.AfMode;
import androidx.camera.camera2.pipe.AwbMode;
import androidx.camera.camera2.pipe.CameraController;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraGraphId;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.OutputStream;
import androidx.camera.camera2.pipe.Result3A;
import androidx.camera.camera2.pipe.StreamGraph;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.camera2.pipe.compat.AudioRestrictionController;
import androidx.camera.camera2.pipe.core.Debug;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.Token;
import androidx.camera.camera2.pipe.internal.CameraGraphParametersImpl;
import androidx.camera.camera2.pipe.internal.CameraGraphRequestListenersImpl;
import androidx.camera.camera2.pipe.internal.FrameCaptureQueue;
import androidx.camera.camera2.pipe.internal.FrameDistributor;
import androidx.camera.camera2.pipe.internal.GraphSessionLock;
import androidx.lifecycle.LifecycleRegistry$$ExternalSyntheticBUOutline0;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicBoolean;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;
import okio.Options$Companion$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000ô\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0016\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001B\u008b\u0001\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0017\u001a\u00020\u0016\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u001b\u001a\u00020\u001a\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\b\b\u0001\u0010\u001f\u001a\u00020\u001e\u0012\u0006\u0010!\u001a\u00020 ¢\u0006\u0004\b\"\u0010#J\u0017\u0010'\u001a\u00020&2\u0006\u0010%\u001a\u00020$H\u0002¢\u0006\u0004\b'\u0010(JJ\u00100\u001a\b\u0012\u0004\u0012\u00028\u00000,\"\u0004\b\u0000\u0010)2-\u0010/\u001a)\b\u0001\u0012\u0004\u0012\u00020\u001e\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000,0+\u0012\u0006\u0012\u0004\u0018\u00010-0*¢\u0006\u0002\b.H\u0002¢\u0006\u0004\b0\u00101J\u000f\u00103\u001a\u000202H\u0016¢\u0006\u0004\b3\u00104J\u0010\u00106\u001a\u000205H\u0096@¢\u0006\u0004\b6\u00107J!\u0010>\u001a\u0002022\u0006\u00109\u001a\u0002082\b\u0010;\u001a\u0004\u0018\u00010:H\u0016¢\u0006\u0004\b<\u0010=Jc\u0010M\u001a\b\u0012\u0004\u0012\u00020J0,2\b\u0010@\u001a\u0004\u0018\u00010?2\b\u0010B\u001a\u0004\u0018\u00010A2\b\u0010D\u001a\u0004\u0018\u00010C2\u000e\u0010G\u001a\n\u0012\u0004\u0012\u00020F\u0018\u00010E2\u000e\u0010H\u001a\n\u0012\u0004\u0012\u00020F\u0018\u00010E2\u000e\u0010I\u001a\n\u0012\u0004\u0012\u00020F\u0018\u00010EH\u0016¢\u0006\u0004\bK\u0010LJ\u000f\u0010N\u001a\u000202H\u0016¢\u0006\u0004\bN\u00104J\u000f\u0010P\u001a\u00020OH\u0016¢\u0006\u0004\bP\u0010QR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010RR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010SR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010TR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010UR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010VR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010WR\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010XR\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010YR\u001a\u0010\u0017\u001a\u00020\u00168\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0017\u0010Z\u001a\u0004\b[\u0010\\R\u001a\u0010\u0019\u001a\u00020\u00188\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0019\u0010]\u001a\u0004\b^\u0010_R\u001a\u0010\u001b\u001a\u00020\u001a8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u001b\u0010`\u001a\u0004\ba\u0010bR\u0014\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001d\u0010cR\u0014\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010dR\u0014\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b!\u0010eR\u0014\u0010g\u001a\u00020f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bg\u0010hR*\u0010k\u001a\u00020i2\u0006\u0010j\u001a\u00020i8\u0016@VX\u0096\u000e¢\u0006\u0012\n\u0004\bk\u0010l\u001a\u0004\bk\u0010m\"\u0004\bn\u0010oR\u0014\u0010s\u001a\u00020p8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bq\u0010r¨\u0006t"}, d2 = {"Landroidx/camera/camera2/pipe/graph/CameraGraphImpl;", "Landroidx/camera/camera2/pipe/CameraGraph;", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "graphConfig", "Landroidx/camera/camera2/pipe/CameraMetadata;", "metadata", "Landroidx/camera/camera2/pipe/graph/GraphProcessor;", "graphProcessor", "Landroidx/camera/camera2/pipe/graph/GraphListener;", "graphListener", "Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;", "streamGraph", "Landroidx/camera/camera2/pipe/graph/SurfaceGraph;", "surfaceGraph", "Landroidx/camera/camera2/pipe/CameraController;", "cameraController", "Landroidx/camera/camera2/pipe/internal/FrameDistributor;", "frameDistributor", "Landroidx/camera/camera2/pipe/internal/FrameCaptureQueue;", "frameCaptureQueue", "Landroidx/camera/camera2/pipe/compat/AudioRestrictionController;", "audioRestrictionController", "Landroidx/camera/camera2/pipe/CameraGraphId;", "id", "Landroidx/camera/camera2/pipe/internal/CameraGraphParametersImpl;", "parameters", "Landroidx/camera/camera2/pipe/internal/CameraGraphRequestListenersImpl;", "listeners", "Landroidx/camera/camera2/pipe/internal/GraphSessionLock;", "sessionLock", "Lkotlinx/coroutines/CoroutineScope;", "graphScope", "Landroidx/camera/camera2/pipe/graph/Controller3A;", "controller3A", "<init>", "(Landroidx/camera/camera2/pipe/CameraGraph$Config;Landroidx/camera/camera2/pipe/CameraMetadata;Landroidx/camera/camera2/pipe/graph/GraphProcessor;Landroidx/camera/camera2/pipe/graph/GraphListener;Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;Landroidx/camera/camera2/pipe/graph/SurfaceGraph;Landroidx/camera/camera2/pipe/CameraController;Landroidx/camera/camera2/pipe/internal/FrameDistributor;Landroidx/camera/camera2/pipe/internal/FrameCaptureQueue;Landroidx/camera/camera2/pipe/compat/AudioRestrictionController;Landroidx/camera/camera2/pipe/CameraGraphId;Landroidx/camera/camera2/pipe/internal/CameraGraphParametersImpl;Landroidx/camera/camera2/pipe/internal/CameraGraphRequestListenersImpl;Landroidx/camera/camera2/pipe/internal/GraphSessionLock;Lkotlinx/coroutines/CoroutineScope;Landroidx/camera/camera2/pipe/graph/Controller3A;)V", "Landroidx/camera/camera2/pipe/core/Token;", "token", "Landroidx/camera/camera2/pipe/graph/CameraGraphSessionImpl;", "createSessionFromToken", "(Landroidx/camera/camera2/pipe/core/Token;)Landroidx/camera/camera2/pipe/graph/CameraGraphSessionImpl;", "T", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/ExtensionFunctionType;", "block", "withSessionLockAsync", "(Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET, "start", "()V", "Landroidx/camera/camera2/pipe/CameraGraph$Session;", "acquireSession", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/camera/camera2/pipe/StreamId;", "stream", "Landroid/view/Surface;", "surface", "setSurface-NYG5g8E", "(ILandroid/view/Surface;)V", "setSurface", "Landroidx/camera/camera2/pipe/AeMode;", "aeMode", "Landroidx/camera/camera2/pipe/AfMode;", "afMode", "Landroidx/camera/camera2/pipe/AwbMode;", "awbMode", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/params/MeteringRectangle;", "aeRegions", "afRegions", "awbRegions", "Landroidx/camera/camera2/pipe/Result3A;", "update3A-ydBZfZg", "(Landroidx/camera/camera2/pipe/AeMode;Landroidx/camera/camera2/pipe/AfMode;Landroidx/camera/camera2/pipe/AwbMode;Ljava/util/List;Ljava/util/List;Ljava/util/List;)Lkotlinx/coroutines/Deferred;", "update3A", "close", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroidx/camera/camera2/pipe/graph/GraphProcessor;", "Landroidx/camera/camera2/pipe/graph/GraphListener;", "Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;", "Landroidx/camera/camera2/pipe/graph/SurfaceGraph;", "Landroidx/camera/camera2/pipe/CameraController;", "Landroidx/camera/camera2/pipe/internal/FrameDistributor;", "Landroidx/camera/camera2/pipe/internal/FrameCaptureQueue;", "Landroidx/camera/camera2/pipe/compat/AudioRestrictionController;", "Landroidx/camera/camera2/pipe/CameraGraphId;", "getId", "()Landroidx/camera/camera2/pipe/CameraGraphId;", "Landroidx/camera/camera2/pipe/internal/CameraGraphParametersImpl;", "getParameters", "()Landroidx/camera/camera2/pipe/internal/CameraGraphParametersImpl;", "Landroidx/camera/camera2/pipe/internal/CameraGraphRequestListenersImpl;", "getListeners", "()Landroidx/camera/camera2/pipe/internal/CameraGraphRequestListenersImpl;", "Landroidx/camera/camera2/pipe/internal/GraphSessionLock;", "Lkotlinx/coroutines/CoroutineScope;", "Landroidx/camera/camera2/pipe/graph/Controller3A;", "Lkotlinx/atomicfu/AtomicBoolean;", "closed", "Lkotlinx/atomicfu/AtomicBoolean;", _UrlKt.FRAGMENT_ENCODE_SET, "value", "isForeground", "Z", "()Z", "setForeground", "(Z)V", "Landroidx/camera/camera2/pipe/StreamGraph;", "getStreams", "()Landroidx/camera/camera2/pipe/StreamGraph;", "streams", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraGraphImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraGraphImpl.kt\nandroidx/camera/camera2/pipe/graph/CameraGraphImpl\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n*L\n1#1,346:1\n59#2,2:347\n59#2,2:357\n59#2,2:367\n71#2,2:377\n59#2,2:387\n1740#3,3:349\n1#4:352\n71#5,4:353\n78#5,4:359\n71#5,4:363\n78#5,4:369\n71#5,4:373\n78#5,4:379\n71#5,4:383\n78#5,4:389\n*S KotlinDebug\n*F\n+ 1 CameraGraphImpl.kt\nandroidx/camera/camera2/pipe/graph/CameraGraphImpl\n*L\n86#1:347,2\n156#1:357,2\n166#1:367,2\n212#1:377,2\n308#1:387,2\n100#1:349,3\n155#1:353,4\n159#1:359,4\n165#1:363,4\n169#1:369,4\n210#1:373,4\n215#1:379,4\n307#1:383,4\n317#1:389,4\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraGraphImpl implements CameraGraph {
    private final AudioRestrictionController audioRestrictionController;
    private final CameraController cameraController;
    private final AtomicBoolean closed;
    private final Controller3A controller3A;
    private final FrameCaptureQueue frameCaptureQueue;
    private final FrameDistributor frameDistributor;
    private final GraphListener graphListener;
    private final GraphProcessor graphProcessor;
    private final CoroutineScope graphScope;
    private final CameraGraphId id;
    private boolean isForeground;
    private final CameraGraphRequestListenersImpl listeners;
    private final CameraGraphParametersImpl parameters;
    private final GraphSessionLock sessionLock;
    private final StreamGraphImpl streamGraph;
    private final SurfaceGraph surfaceGraph;

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.graph.CameraGraphImpl", f = "CameraGraphImpl.kt", i = {}, l = {175}, m = "acquireSession", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.graph.CameraGraphImpl$acquireSession$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ CameraGraphImpl this$0;

        public AnonymousClass1(CameraGraphImpl r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.acquireSession(this);
        }
    }

    /* JADX INFO: Add missing generic type declarations: [T] */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Deferred;", "T", "it", "Landroidx/camera/camera2/pipe/core/Token;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.graph.CameraGraphImpl$withSessionLockAsync$1", f = "CameraGraphImpl.kt", i = {}, l = {344}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.graph.CameraGraphImpl$withSessionLockAsync$1, reason: invalid class name and case insensitive filesystem */
    public static final class C01011<T> extends SuspendLambda implements Function2<Token, Continuation<? super Deferred<? extends T>>, Object> {
        final /* synthetic */ Function2<CoroutineScope, Continuation<? super Deferred<? extends T>>, Object> $block;
        int label;

        @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\n"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/Deferred;", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
        @DebugMetadata(c = "androidx.camera.camera2.pipe.graph.CameraGraphImpl$withSessionLockAsync$1$1", f = "CameraGraphImpl.kt", i = {}, l = {344}, m = "invokeSuspend", n = {}, s = {}, v = 1)
        /* renamed from: androidx.camera.camera2.pipe.graph.CameraGraphImpl$withSessionLockAsync$1$1, reason: invalid class name and collision with other inner class name */
        public static final class C00051 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Deferred<? extends T>>, Object> {
            final /* synthetic */ Function2<CoroutineScope, Continuation<? super Deferred<? extends T>>, Object> $block;
            private /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: Multi-variable type inference failed */
            public C00051(Function2<? super CoroutineScope, ? super Continuation<? super Deferred<? extends T>>, ? extends Object> r1, Continuation<? super C00051> r2) {
                this.$block = r1;
                super(2, r2);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
                C00051 r0 = new C00051(this.$block, r3);
                r0.L$0 = r2;
                return r0;
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Object r2) {
                return invoke(r1, (Continuation) r2);
            }

            public final Object invoke(CoroutineScope r1, Continuation<? super Deferred<? extends T>> r2) {
                return ((C00051) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
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
                CoroutineScope r42 = (CoroutineScope) this.L$0;
                Function2<CoroutineScope, Continuation<? super Deferred<? extends T>>, Object> r12 = this.$block;
                this.label = 1;
                Object r3 = r12.invoke(r42, this);
                if (r3 != r0) goto L12;
                return r0;
            L12:
                return r3;
            }
        }

        /* JADX WARN: Multi-variable type inference failed */
        public C01011(Function2<? super CoroutineScope, ? super Continuation<? super Deferred<? extends T>>, ? extends Object> r1, Continuation<? super C01011> r2) {
            this.$block = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new C01011(this.$block, r2);
        }

        public final Object invoke(Token r1, Continuation<? super Deferred<? extends T>> r2) {
            return ((C01011) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(Token r1, Object r2) {
            return invoke(r1, (Continuation) r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r5) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L9;
            if (r1 != 1) goto L7;
            ResultKt.throwOnFailure(r5);
            return r5;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r5);
            C00051 r52 = new C00051(this.$block, null);
            this.label = 1;
            Object r4 = CoroutineScopeKt.coroutineScope(r52, this);
            if (r4 != r0) goto L12;
            return r0;
        L12:
            return r4;
        }
    }

    public CameraGraphImpl(CameraGraph.Config r1, CameraMetadata r2, GraphProcessor r3, GraphListener r4, StreamGraphImpl r5, SurfaceGraph r6, CameraController r7, FrameDistributor r8, FrameCaptureQueue r9, AudioRestrictionController r10, CameraGraphId r11, CameraGraphParametersImpl r12, CameraGraphRequestListenersImpl r13, GraphSessionLock r14, CoroutineScope r15, Controller3A r16) {
        this.graphProcessor = r3;
        this.graphListener = r4;
        this.streamGraph = r5;
        this.surfaceGraph = r6;
        this.cameraController = r7;
        this.frameDistributor = r8;
        this.frameCaptureQueue = r9;
        this.audioRestrictionController = r10;
        this.id = r11;
        this.parameters = r12;
        this.listeners = r13;
        this.sessionLock = r14;
        this.graphScope = r15;
        this.controller3A = r16;
        this.closed = AtomicFU.atomic(false);
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L6;
        android.util.Log.i("CXCP", Debug.INSTANCE.formatCameraGraphProperties(r2, r1, this));
    L6:
        if (CameraGraph.OperatingMode.m216equalsimpl0(r1.m204getSessionMode2uNL3no(), CameraGraph.OperatingMode.Companion.m221getHIGH_SPEED2uNL3no()) == false) goto L29;
        if (r5.getOutputs().isEmpty() == false) goto L10;
        f$$ExternalSyntheticBUOutline1.m("Cannot create a HIGH_SPEED CameraGraph without outputs.");
        throw null;
    L10:
        if (r5.getOutputs().size() > 2) goto L24;
        List<OutputStream> r22 = r5.getOutputs();
        if ((r22 instanceof Collection) == true) goto L14;
    L16:
        Iterator<T> r23 = r22.iterator();
    L18:
        if (r23.hasNext() == false) goto L29;
        if (((OutputStream) r23.next()).isValidForHighSpeedOperatingMode() == true) goto L18;
        Options$Companion$$ExternalSyntheticBUOutline0.m("HIGH_SPEED CameraGraph must only contain Preview and/or Video streams. Configured outputs are ", this.streamGraph.getOutputs());
        throw null;
    L14:
        if (r22.isEmpty() == false) goto L16;
    L24:
        Options$Companion$$ExternalSyntheticBUOutline0.m("Cannot create a HIGH_SPEED CameraGraph with more than two outputs. Configured outputs are ", r5.getOutputs());
        throw null;
    L29:
        if (r1.getInput() == null) goto L42;
        if (r1.getInput().isEmpty() == false) goto L33;
        f$$ExternalSyntheticBUOutline1.m("At least one InputConfiguration is required for reprocessing");
        throw null;
    L33:
        if (Build.VERSION.SDK_INT >= 31) goto L42;
        if (r1.getInput().size() <= 1) goto L42;
        f$$ExternalSyntheticBUOutline1.m("Multi resolution reprocessing not supported under Android S");
        throw null;
    L42:
        if (this.streamGraph.getImageSourceMap$camera_camera2_pipe().isEmpty() == true) goto L44;
        this.surfaceGraph.maybeUpdateSurfaces$camera_camera2_pipe();
    L44:
        this.isForeground = true;
    }

    public static final /* synthetic */ Controller3A access$getController3A$p(CameraGraphImpl r0) {
        return r0.controller3A;
    }

    private final CameraGraphSessionImpl createSessionFromToken(Token r8) {
        return new CameraGraphSessionImpl(r8, this.graphProcessor, this.controller3A, this.frameCaptureQueue, getParameters(), getListeners());
    }

    private final <T> Deferred<T> withSessionLockAsync(Function2<? super CoroutineScope, ? super Continuation<? super Deferred<? extends T>>, ? extends Object> r4) {
        return this.sessionLock.withTokenInAsync$camera_camera2_pipe(this.graphScope, new C01011(r4, null));
    }

    @Override // androidx.camera.camera2.pipe.CameraGraphBase
    public Object acquireSession(Continuation<? super CameraGraph.Session> r5) {
        if ((r5 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r5;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r52 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        ResultKt.throwOnFailure(r52);
    L18:
        return createSessionFromToken((Token) r52);
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r52);
        GraphSessionLock r53 = this.sessionLock;
        r0.label = 1;
        r52 = r53.acquireToken$camera_camera2_pipe(r0);
        if (r52 != r12) goto L18;
        return r12;
    L7:
        r0 = new AnonymousClass1(this, r5);
        goto L8
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        if (this.closed.compareAndSet(false, true) == false) goto L9;
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection(this + "#close");
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L7;
        android.util.Log.i("CXCP", "Closing " + this);
    L7:
        this.graphProcessor.close();
        this.cameraController.close();
        this.frameDistributor.close();
        this.frameCaptureQueue.close();
        this.surfaceGraph.close();
        this.streamGraph.close();
        this.audioRestrictionController.removeCameraGraph(this);
        CoroutineScopeKt.cancel$default(this.graphScope, null, 1, null);
        Trace.endSection();
        return;
    }

    public CameraGraphId getId() {
        return this.id;
    }

    public CameraGraphRequestListenersImpl getListeners() {
        return this.listeners;
    }

    public CameraGraphParametersImpl getParameters() {
        return this.parameters;
    }

    @Override // androidx.camera.camera2.pipe.CameraGraphBase
    public StreamGraph getStreams() {
        return this.streamGraph;
    }

    @Override // androidx.camera.camera2.pipe.CameraGraphBase
    public void setForeground(boolean r1) {
        this.isForeground = r1;
        this.cameraController.setForeground(r1);
    }

    @Override // androidx.camera.camera2.pipe.CameraGraphBase
    /* renamed from: setSurface-NYG5g8E */
    public void mo226setSurfaceNYG5g8E(int r3, Surface r4) {
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection(StreamId.m406toStringimpl(r3) + "#setSurface");
        if (r4 != null) goto L5;
    L9:
        this.surfaceGraph.m551setNYG5g8E(r3, r4);
        Trace.endSection();
        return;
    L5:
        if (r4.isValid() == true) goto L9;
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L9;
        android.util.Log.w("CXCP", this + "#setSurface: " + r4 + " is invalid");
        goto L9
    }

    @Override // androidx.camera.camera2.pipe.CameraGraphBase
    public void start() {
        if (this.closed.getValue() == true) goto L9;
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection(this + "#start");
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L7;
        android.util.Log.i("CXCP", "Starting " + this);
    L7:
        this.graphListener.onGraphStarting();
        this.cameraController.start();
        Trace.endSection();
        return;
    L9:
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("Cannot start ", this, " after calling close()");
    }

    public String toString() {
        return getId().toString();
    }

    @Override // androidx.camera.camera2.pipe.CameraControls3A
    /* renamed from: update3A-ydBZfZg */
    public Deferred<Result3A> mo166update3AydBZfZg(AeMode r10, AfMode r11, AwbMode r12, List<MeteringRectangle> r13, List<MeteringRectangle> r14, List<MeteringRectangle> r15) {
        return withSessionLockAsync(new CameraGraphImpl$update3A$1(this, r10, r11, r12, r13, r14, r15, null));
    }
}
