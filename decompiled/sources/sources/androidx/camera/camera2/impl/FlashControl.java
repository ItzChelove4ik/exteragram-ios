package androidx.camera.camera2.impl;

import android.util.Log;
import androidx.camera.camera2.adapter.CoroutineAdaptersKt;
import androidx.camera.camera2.compat.workaround.UseFlashModeTorchFor3aUpdate;
import androidx.camera.camera2.impl.TorchControl;
import androidx.camera.core.CameraControl;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Logger;
import androidx.mediarouter.media.GlobalMediaRouter;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.MainCoroutineDispatcher;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0007\u0018\u00002\u00020\u0001B1\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000f\u001a\u00020\u000eH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00132\u0006\u0010\u0012\u001a\u00020\u0011H\u0082@¢\u0006\u0004\b\u0014\u0010\u0015J\u0017\u0010\u0016\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0013H\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u0017\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0013H\u0002¢\u0006\u0004\b\u0018\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u0019\u0010\u0010J%\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00132\u0006\u0010\u001b\u001a\u00020\u001a2\b\b\u0002\u0010\u001d\u001a\u00020\u001c¢\u0006\u0004\b\u001e\u0010\u001fJ\u0017\u0010\"\u001a\u00020\u000e2\b\u0010!\u001a\u0004\u0018\u00010 ¢\u0006\u0004\b\"\u0010#J\u0010\u0010$\u001a\u00020\u000eH\u0086@¢\u0006\u0004\b$\u0010%J\u0010\u0010&\u001a\u00020\u000eH\u0086@¢\u0006\u0004\b&\u0010%J\u0010\u0010'\u001a\u00020\u001aH\u0086@¢\u0006\u0004\b'\u0010%R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010(R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010)R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010*R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010+R\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010,R\u0018\u0010.\u001a\u0004\u0018\u00010-8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010/R\u001c\u00100\u001a\u00020\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\f\n\u0004\b0\u00101\u0012\u0004\b2\u0010\u0010R*\u0010\u001b\u001a\u00020\u001a2\u0006\u00103\u001a\u00020\u001a8F@BX\u0086\u000e¢\u0006\u0012\n\u0004\b\u001b\u00101\u0012\u0004\b6\u0010\u0010\u001a\u0004\b4\u00105R\u0018\u00107\u001a\u0004\u0018\u00010 8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b7\u00108R(\u0010!\u001a\u0004\u0018\u00010 2\b\u00103\u001a\u0004\u0018\u00010 8F@BX\u0086\u000e¢\u0006\f\n\u0004\b!\u00108\u001a\u0004\b9\u0010:R\u001e\u0010<\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010;8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b<\u0010=R0\u0010>\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00132\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00138F@BX\u0086\u000e¢\u0006\f\n\u0004\b>\u0010?\u001a\u0004\b@\u0010\u0017R(\u0010E\u001a\u0004\u0018\u00010-2\b\u00103\u001a\u0004\u0018\u00010-8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\bA\u0010B\"\u0004\bC\u0010D¨\u0006F"}, d2 = {"Landroidx/camera/camera2/impl/FlashControl;", "Landroidx/camera/camera2/impl/UseCaseCameraControl;", "Landroidx/camera/camera2/impl/CameraProperties;", "cameraProperties", "Landroidx/camera/camera2/impl/State3AControl;", "state3AControl", "Landroidx/camera/camera2/impl/UseCaseThreads;", "threads", "Landroidx/camera/camera2/impl/TorchControl;", "torchControl", "Landroidx/camera/camera2/compat/workaround/UseFlashModeTorchFor3aUpdate;", "useFlashModeTorchFor3aUpdate", "<init>", "(Landroidx/camera/camera2/impl/CameraProperties;Landroidx/camera/camera2/impl/State3AControl;Landroidx/camera/camera2/impl/UseCaseThreads;Landroidx/camera/camera2/impl/TorchControl;Landroidx/camera/camera2/compat/workaround/UseFlashModeTorchFor3aUpdate;)V", _UrlKt.FRAGMENT_ENCODE_SET, "stopRunningTask", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "timeoutMillis", "Lkotlinx/coroutines/Deferred;", "applyScreenFlash", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setExternalFlashAeModeAsync", "()Lkotlinx/coroutines/Deferred;", "setTorchForScreenFlash", "reset", _UrlKt.FRAGMENT_ENCODE_SET, "flashMode", _UrlKt.FRAGMENT_ENCODE_SET, "cancelPreviousTask", "setFlashAsync", "(IZ)Lkotlinx/coroutines/Deferred;", "Landroidx/camera/core/ImageCapture$ScreenFlash;", "screenFlash", "setScreenFlash", "(Landroidx/camera/core/ImageCapture$ScreenFlash;)V", "startScreenFlashCaptureTasks", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "stopScreenFlashCaptureTasks", "awaitFlashModeUpdate", "Landroidx/camera/camera2/impl/CameraProperties;", "Landroidx/camera/camera2/impl/State3AControl;", "Landroidx/camera/camera2/impl/UseCaseThreads;", "Landroidx/camera/camera2/impl/TorchControl;", "Landroidx/camera/camera2/compat/workaround/UseFlashModeTorchFor3aUpdate;", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "_requestControl", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "_flashMode", "I", "get_flashMode$annotations", "value", "getFlashMode", "()I", "getFlashMode$annotations", "_screenFlash", "Landroidx/camera/core/ImageCapture$ScreenFlash;", "getScreenFlash", "()Landroidx/camera/core/ImageCapture$ScreenFlash;", "Lkotlinx/coroutines/CompletableDeferred;", "_updateSignal", "Lkotlinx/coroutines/CompletableDeferred;", "updateSignal", "Lkotlinx/coroutines/Deferred;", "getUpdateSignal", "getRequestControl", "()Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "setRequestControl", "(Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;)V", "requestControl", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nFlashControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FlashControl.kt\nandroidx/camera/camera2/impl/FlashControl\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,296:1\n85#2,4:297\n85#2,4:302\n85#2,4:306\n85#2,4:310\n85#2,4:314\n85#2,4:318\n85#2,4:322\n85#2,4:326\n85#2,4:330\n1#3:301\n*S KotlinDebug\n*F\n+ 1 FlashControl.kt\nandroidx/camera/camera2/impl/FlashControl\n*L\n97#1:297,4\n207#1:302,4\n217#1:306,4\n239#1:310,4\n250#1:314,4\n280#1:318,4\n285#1:322,4\n221#1:326,4\n254#1:330,4\n*E\n"})
/* loaded from: classes3.dex */
public final class FlashControl implements UseCaseCameraControl {
    private volatile int _flashMode;
    private UseCaseCameraRequestControl _requestControl;
    private volatile ImageCapture.ScreenFlash _screenFlash;
    private CompletableDeferred<Unit> _updateSignal;
    private final CameraProperties cameraProperties;
    private int flashMode;
    private ImageCapture.ScreenFlash screenFlash;
    private final State3AControl state3AControl;
    private final UseCaseThreads threads;
    private final TorchControl torchControl;
    private Deferred<Unit> updateSignal;
    private final UseFlashModeTorchFor3aUpdate useFlashModeTorchFor3aUpdate;

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.FlashControl", f = "FlashControl.kt", i = {0, 0}, l = {171}, m = "applyScreenFlash", n = {"onApplyCompletedSignal", "timeoutMillis"}, s = {"L$0", "J$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.FlashControl$applyScreenFlash$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        long J$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ FlashControl this$0;

        public AnonymousClass1(FlashControl r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r3) {
            this.result = r3;
            this.label |= Integer.MIN_VALUE;
            return FlashControl.access$applyScreenFlash(this.this$0, 0, this);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.FlashControl$applyScreenFlash$2", f = "FlashControl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    @SourceDebugExtension({"SMAP\nFlashControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FlashControl.kt\nandroidx/camera/camera2/impl/FlashControl$applyScreenFlash$2\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,296:1\n85#2,4:297\n*S KotlinDebug\n*F\n+ 1 FlashControl.kt\nandroidx/camera/camera2/impl/FlashControl$applyScreenFlash$2\n*L\n174#1:297,4\n*E\n"})
    /* renamed from: androidx.camera.camera2.impl.FlashControl$applyScreenFlash$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ImageCapture.ScreenFlashListener $screenFlashListener;
        final /* synthetic */ long $timeoutMillis;
        int label;
        final /* synthetic */ FlashControl this$0;

        public AnonymousClass2(long r1, FlashControl r3, ImageCapture.ScreenFlashListener r4, Continuation<? super AnonymousClass2> r5) {
            this.$timeoutMillis = r1;
            this.this$0 = r3;
            this.$screenFlashListener = r4;
            super(2, r5);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r7, Continuation<?> r8) {
            return new AnonymousClass2(this.$timeoutMillis, this.this$0, this.$screenFlashListener, r8);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass2) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r5) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L12;
            ResultKt.throwOnFailure(r5);
            long r0 = System.currentTimeMillis() + this.$timeoutMillis;
            ImageCapture.ScreenFlash r52 = this.this$0.getScreenFlash();
            if (r52 == null) goto L7;
            r52.apply(r0, this.$screenFlashListener);
        L7:
            Camera2Logger r4 = Camera2Logger.INSTANCE;
            if (Logger.isDebugEnabled("CXCP") == false) goto L11;
            Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "applyScreenFlash: ScreenFlash.apply() invoked, expirationTimeMillis = " + r0);
        L11:
            return Unit.INSTANCE;
        L12:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.FlashControl$applyScreenFlash$3", f = "FlashControl.kt", i = {}, l = {188}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    @SourceDebugExtension({"SMAP\nFlashControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FlashControl.kt\nandroidx/camera/camera2/impl/FlashControl$applyScreenFlash$3\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,296:1\n85#2,4:297\n85#2,4:301\n119#2,4:305\n*S KotlinDebug\n*F\n+ 1 FlashControl.kt\nandroidx/camera/camera2/impl/FlashControl$applyScreenFlash$3\n*L\n181#1:297,4\n189#1:301,4\n191#1:305,4\n*E\n"})
    /* renamed from: androidx.camera.camera2.impl.FlashControl$applyScreenFlash$3, reason: invalid class name */
    public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ CompletableDeferred<Unit> $onApplyCompletedSignal;
        final /* synthetic */ long $timeoutMillis;
        int label;

        public AnonymousClass3(CompletableDeferred<Unit> r1, long r2, Continuation<? super AnonymousClass3> r4) {
            this.$onApplyCompletedSignal = r1;
            this.$timeoutMillis = r2;
            super(2, r4);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r4, Continuation<?> r5) {
            return new AnonymousClass3(this.$onApplyCompletedSignal, this.$timeoutMillis, r5);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass3) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r7) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r7);
        L15:
            if (((Boolean) r7).booleanValue() == false) goto L19;
            Camera2Logger r6 = Camera2Logger.INSTANCE;
            if (Logger.isDebugEnabled("CXCP") == false) goto L23;
            Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "applyScreenFlash: ScreenFlashListener completed");
        L23:
            return Unit.INSTANCE;
        L19:
            Camera2Logger r72 = Camera2Logger.INSTANCE;
            long r62 = this.$timeoutMillis;
            if (Logger.isWarnEnabled("CXCP") == false) goto L23;
            Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "applyScreenFlash: ScreenFlashListener completion timed out after " + r62 + " ms");
            goto L23
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r7);
            Camera2Logger r73 = Camera2Logger.INSTANCE;
            if (Logger.isDebugEnabled("CXCP") == false) goto L11;
            Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "applyScreenFlash: Waiting for ScreenFlashListener to be completed");
        L11:
            CompletableDeferred<Unit> r74 = this.$onApplyCompletedSignal;
            long r4 = this.$timeoutMillis;
            this.label = 1;
            r7 = CoroutineAdaptersKt.awaitUntil(r74, r4, this);
            if (r7 != r0) goto L15;
            return r0;
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.FlashControl", f = "FlashControl.kt", i = {0}, l = {284}, m = "awaitFlashModeUpdate", n = {"initialFlashMode"}, s = {"I$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.FlashControl$awaitFlashModeUpdate$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00841 extends ContinuationImpl {
        int I$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ FlashControl this$0;

        public C00841(FlashControl r1, Continuation<? super C00841> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.awaitFlashModeUpdate(this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.FlashControl", f = "FlashControl.kt", i = {0}, l = {149, 160}, m = "startScreenFlashCaptureTasks", n = {"pendingTasks"}, s = {"L$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.FlashControl$startScreenFlashCaptureTasks$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00851 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ FlashControl this$0;

        public C00851(FlashControl r1, Continuation<? super C00851> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.startScreenFlashCaptureTasks(this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.FlashControl", f = "FlashControl.kt", i = {}, l = {GlobalMediaRouter.CallbackHandler.MSG_ROUTE_VOLUME_CHANGED}, m = "stopScreenFlashCaptureTasks", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.FlashControl$stopScreenFlashCaptureTasks$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00861 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ FlashControl this$0;

        public C00861(FlashControl r1, Continuation<? super C00861> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.stopScreenFlashCaptureTasks(this);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.FlashControl$stopScreenFlashCaptureTasks$2", f = "FlashControl.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    @SourceDebugExtension({"SMAP\nFlashControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 FlashControl.kt\nandroidx/camera/camera2/impl/FlashControl$stopScreenFlashCaptureTasks$2\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,296:1\n85#2,4:297\n*S KotlinDebug\n*F\n+ 1 FlashControl.kt\nandroidx/camera/camera2/impl/FlashControl$stopScreenFlashCaptureTasks$2\n*L\n262#1:297,4\n*E\n"})
    /* renamed from: androidx.camera.camera2.impl.FlashControl$stopScreenFlashCaptureTasks$2, reason: invalid class name and case insensitive filesystem */
    public static final class C00872 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ FlashControl this$0;

        public C00872(FlashControl r1, Continuation<? super C00872> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new C00872(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C00872) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L12;
            ResultKt.throwOnFailure(r2);
            ImageCapture.ScreenFlash r1 = this.this$0.getScreenFlash();
            if (r1 == null) goto L7;
            r1.clear();
        L7:
            Camera2Logger r12 = Camera2Logger.INSTANCE;
            if (Logger.isDebugEnabled("CXCP") == false) goto L11;
            Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "screenFlashPostCapture: ScreenFlash.clear() invoked");
        L11:
            return Unit.INSTANCE;
        L12:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    /* renamed from: $r8$lambda$SwVrm2z41YDL9LSU-Gcsr2C2tZY, reason: not valid java name */
    public static /* synthetic */ Unit m71$r8$lambda$SwVrm2z41YDL9LSUGcsr2C2tZY(Throwable r0) {
        return setTorchForScreenFlash$lambda$1$1(r0);
    }

    public static void $r8$lambda$m0mZpEIYJi1SscD8hMkjjTqPaT8(CompletableDeferred r1) {
        r1.complete(Unit.INSTANCE);
    }

    /* renamed from: $r8$lambda$vM4TrryT-p58oBOc-QCWtFkO6dA, reason: not valid java name */
    public static /* synthetic */ Unit m72$r8$lambda$vM4TrryTp58oBOcQCWtFkO6dA(Throwable r0) {
        return setExternalFlashAeModeAsync$lambda$1$1(r0);
    }

    public FlashControl(CameraProperties r1, State3AControl r2, UseCaseThreads r3, TorchControl r4, UseFlashModeTorchFor3aUpdate r5) {
        this.cameraProperties = r1;
        this.state3AControl = r2;
        this.threads = r3;
        this.torchControl = r4;
        this.useFlashModeTorchFor3aUpdate = r5;
        this._flashMode = 2;
        this.flashMode = this._flashMode;
        this.screenFlash = this._screenFlash;
        this.updateSignal = CompletableDeferredKt.CompletableDeferred(Unit.INSTANCE);
    }

    public static final /* synthetic */ Object access$applyScreenFlash(FlashControl r0, long r1, Continuation r3) {
        return r0.applyScreenFlash(r1, r3);
    }

    private final Object applyScreenFlash(long r12, Continuation<? super Deferred<Unit>> r14) {
        if ((r14 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r14;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r142 = r0.result;
        Object r13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        long r122 = r0.J$0;
        CompletableDeferred r02 = (CompletableDeferred) r0.L$0;
        ResultKt.throwOnFailure(r142);
        FlashControl r8 = this;
    L19:
        return BuildersKt.async$default(r8.threads.getScope(), null, null, new AnonymousClass3(r02, r122, null), 3, null);
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r142);
        CompletableDeferred r143 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        FlashControl$$ExternalSyntheticLambda1 r9 = new FlashControl$$ExternalSyntheticLambda1(r143);
        MainCoroutineDispatcher r22 = Dispatchers.getMain();
        r8 = this;
        AnonymousClass2 r5 = new AnonymousClass2(r12, r8, r9, null);
        r0.L$0 = r143;
        r0.J$0 = r12;
        r0.label = 1;
        if (BuildersKt.withContext(r22, r5, r0) != r13) goto L17;
        return r13;
    L17:
        r02 = r143;
        r122 = r12;
    L7:
        r0 = new AnonymousClass1(this, r14);
        goto L8
    }

    private final Deferred<Unit> setExternalFlashAeModeAsync() {
        boolean r0 = CameraMetadataIntegrationKt.isExternalFlashAeModeSupported(this.cameraProperties.getMetadata());
        Camera2Logger r1 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L5;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "setExternalFlashAeModeAsync: isExternalFlashAeModeSupported = " + r0);
    L5:
        if (r0 == true) goto L8;
        return null;
    L8:
        Deferred<Unit> r5 = this.state3AControl.setTryExternalFlashAeModeAsync(true);
        if (Logger.isDebugEnabled("CXCP") == false) goto L11;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "setExternalFlashAeModeAsync: need to wait for state3AControl.updateSignal");
    L11:
        r5.invokeOnCompletion(new FlashControl$$ExternalSyntheticLambda2());
        return r5;
    }

    private static final Unit setExternalFlashAeModeAsync$lambda$1$1(Throwable r1) {
        Camera2Logger r12 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L6;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "setExternalFlashAeModeAsync: state3AControl.updateSignal completed");
    L6:
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Deferred setFlashAsync$default(FlashControl r0, int r1, boolean r2, int r3, Object r4) {
        if ((r3 & 2) == 0) goto L6;
        r2 = true;
    L6:
        return r0.setFlashAsync(r1, r2);
    }

    private final Deferred<Unit> setTorchForScreenFlash() {
        boolean r0 = this.useFlashModeTorchFor3aUpdate.shouldUseFlashModeTorch();
        Camera2Logger r1 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L5;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "setTorchIfRequired: shouldUseFlashModeTorch = " + r0);
    L5:
        if (r0 == true) goto L8;
        return null;
    L8:
        Deferred<Unit> r8 = TorchControl.m77setTorchAsyncOup_wC0$camera_camera2$default(this.torchControl, TorchControl.TorchMode.Companion.m89getUSED_AS_FLASHIRs_R8(), false, true, 2, null);
        if (Logger.isDebugEnabled("CXCP") == false) goto L11;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "setTorchIfRequired: need to wait for torch control to be completed");
    L11:
        r8.invokeOnCompletion(new FlashControl$$ExternalSyntheticLambda0());
        return r8;
    }

    private static final Unit setTorchForScreenFlash$lambda$1$1(Throwable r1) {
        Camera2Logger r12 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L6;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "setTorchIfRequired: torch control completed");
    L6:
        return Unit.INSTANCE;
    }

    private final void stopRunningTask() {
        CompletableDeferred<Unit> r0 = this._updateSignal;
        if (r0 == null) goto L5;
        r0.completeExceptionally(new CameraControl.OperationCanceledException("There is a new flash mode being set or camera was closed"));
    L5:
        this._updateSignal = null;
    }

    public final Object awaitFlashModeUpdate(Continuation<? super Integer> r6) {
        if ((r6 instanceof C00841) == false) goto L7;
        C00841 r0 = (C00841) r6;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r62 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        int r5 = r0.I$0;
        ResultKt.throwOnFailure(r62);
    L21:
        Camera2Logger r63 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L25;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "awaitFlashModeUpdate: initialFlashMode = " + r5);
    L25:
        return Boxing.boxInt(r5);
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r62);
        Camera2Logger r64 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L17;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "FlashControl: Waiting for any ongoing update to be completed");
    L17:
        int r65 = getFlashMode();
        Deferred<Unit> r52 = getUpdateSignal();
        r0.I$0 = r65;
        r0.label = 1;
        if (r52.join(r0) != r12) goto L20;
        return r12;
    L20:
        r5 = r65;
    L7:
        r0 = new C00841(this, r6);
        goto L8
    }

    public final int getFlashMode() {
        return this._flashMode;
    }

    public UseCaseCameraRequestControl getRequestControl() {
        return this._requestControl;
    }

    public final ImageCapture.ScreenFlash getScreenFlash() {
        return this._screenFlash;
    }

    public final Deferred<Unit> getUpdateSignal() {
        CompletableDeferred<Unit> r0 = this._updateSignal;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return CompletableDeferredKt.CompletableDeferred(Unit.INSTANCE);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraControl
    public void reset() {
        this._flashMode = 2;
        this._screenFlash = null;
        stopRunningTask();
        setFlashAsync$default(this, 2, false, 2, null);
    }

    public final Deferred<Unit> setFlashAsync(int r4, boolean r5) {
        Camera2Logger r0 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L5;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "setFlashAsync: flashMode = " + r4 + ", requestControl = " + getRequestControl());
    L5:
        CompletableDeferred<Unit> r02 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        if (getRequestControl() == null) goto L15;
        this._flashMode = r4;
        if (r5 == false) goto L10;
        stopRunningTask();
    L13:
        this._updateSignal = r02;
        CoroutineAdaptersKt.propagateTo(this.state3AControl.setFlashModeAsync(r4), r02);
        return r02;
    L10:
        CompletableDeferred<Unit> r52 = this._updateSignal;
        if (r52 == null) goto L13;
        CoroutineAdaptersKt.propagateTo(r02, r52);
        goto L13
    L15:
        r02.completeExceptionally(new CameraControl.OperationCanceledException("Camera is not active."));
        return r02;
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraControl
    public void setRequestControl(UseCaseCameraRequestControl r2) {
        this._requestControl = r2;
        setFlashAsync(this._flashMode, false);
    }

    public final void setScreenFlash(ImageCapture.ScreenFlash r1) {
        this._screenFlash = r1;
    }

    public final Object startScreenFlashCaptureTasks(Continuation<? super Unit> r8) {
        if ((r8 instanceof C00851) == false) goto L7;
        C00851 r0 = (C00851) r8;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r82 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L16;
        if (r2 == 1) goto L15;
        if (r2 != 2) goto L13;
        ResultKt.throwOnFailure(r82);
    L30:
        return Unit.INSTANCE;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        List r22 = (List) r0.L$1;
        List r5 = (List) r0.L$0;
        ResultKt.throwOnFailure(r82);
    L20:
        r22.add(r82);
        Deferred<Unit> r83 = setExternalFlashAeModeAsync();
        if (r83 == null) goto L23;
        Boxing.boxBoolean(r5.add(r83));
    L23:
        Deferred<Unit> r7 = setTorchForScreenFlash();
        if (r7 == null) goto L26;
        Boxing.boxBoolean(r5.add(r7));
    L26:
        r0.L$0 = null;
        r0.L$1 = null;
        r0.label = 2;
        if (AwaitKt.awaitAll(r5, r0) != r12) goto L30;
    L28:
        return r12;
    L16:
        ResultKt.throwOnFailure(r82);
        r22 = new ArrayList();
        r0.L$0 = r22;
        r0.L$1 = r22;
        r0.label = 1;
        r82 = applyScreenFlash(3000, r0);
        if (r82 == r12) goto L28;
        r5 = r22;
    L7:
        r0 = new C00851(this, r8);
        goto L8
    }

    public final Object stopScreenFlashCaptureTasks(Continuation<? super Unit> r7) {
        if ((r7 instanceof C00861) == false) goto L7;
        C00861 r0 = (C00861) r7;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r72 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        ResultKt.throwOnFailure(r72);
    L18:
        if (CameraMetadataIntegrationKt.isExternalFlashAeModeSupported(this.cameraProperties.getMetadata()) == false) goto L21;
        this.state3AControl.setTryExternalFlashAeModeAsync(false);
    L21:
        if (this.useFlashModeTorchFor3aUpdate.shouldUseFlashModeTorch() == false) goto L24;
        TorchControl.m77setTorchAsyncOup_wC0$camera_camera2$default(this.torchControl, TorchControl.TorchMode.Companion.m87getOFFIRs_R8(), false, true, 2, null);
    L24:
        return Unit.INSTANCE;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r72);
        MainCoroutineDispatcher r73 = Dispatchers.getMain();
        C00872 r22 = new C00872(this, null);
        r0.label = 1;
        if (BuildersKt.withContext(r73, r22, r0) != r12) goto L18;
        return r12;
    L7:
        r0 = new C00861(this, r7);
        goto L8
    }
}
