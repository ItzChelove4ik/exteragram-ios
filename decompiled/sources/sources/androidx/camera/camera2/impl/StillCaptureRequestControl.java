package androidx.camera.camera2.impl;

import android.util.Log;
import androidx.camera.camera2.adapter.CoroutineAdaptersKt;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CaptureConfig;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.LinkedList;
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
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\b\u0007\u0018\u00002\u00020\u0001:\u0001(B\u0019\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u000f\u0010\t\u001a\u00020\bH\u0002¢\u0006\u0004\b\t\u0010\nJ.\u0010\u0012\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00100\u000f2\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\u000e\u001a\u00020\rH\u0082@¢\u0006\u0004\b\u0012\u0010\u0013J1\u0010\u0016\u001a\u00020\b*\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u00100\u000f2\u0006\u0010\u0014\u001a\u00020\u000b2\u0006\u0010\u0015\u001a\u00020\rH\u0002¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0018\u001a\u00020\bH\u0016¢\u0006\u0004\b\u0018\u0010\nR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u0019R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u0018\u0010\u001e\u001a\u0004\u0018\u00010\r8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001e\u0010\u001fR\u001a\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000b0 8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b!\u0010\"R(\u0010\u000e\u001a\u0004\u0018\u00010\r2\b\u0010#\u001a\u0004\u0018\u00010\r8V@VX\u0096\u000e¢\u0006\f\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006)"}, d2 = {"Landroidx/camera/camera2/impl/StillCaptureRequestControl;", "Landroidx/camera/camera2/impl/UseCaseCameraControl;", "Landroidx/camera/camera2/impl/FlashControl;", "flashControl", "Landroidx/camera/camera2/impl/UseCaseThreads;", "threads", "<init>", "(Landroidx/camera/camera2/impl/FlashControl;Landroidx/camera/camera2/impl/UseCaseThreads;)V", _UrlKt.FRAGMENT_ENCODE_SET, "trySubmitPendingRequests", "()V", "Landroidx/camera/camera2/impl/StillCaptureRequestControl$CaptureRequest;", PluginsConstants.REQUEST, "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "requestControl", "Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/Void;", "submitRequest", "(Landroidx/camera/camera2/impl/StillCaptureRequestControl$CaptureRequest;Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "submittedRequest", "currentRequestControl", "propagateResultOrEnqueueRequest", "(Lkotlinx/coroutines/Deferred;Landroidx/camera/camera2/impl/StillCaptureRequestControl$CaptureRequest;Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;)V", "reset", "Landroidx/camera/camera2/impl/FlashControl;", "Landroidx/camera/camera2/impl/UseCaseThreads;", "Lkotlinx/coroutines/sync/Mutex;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "_requestControl", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "Ljava/util/LinkedList;", "pendingRequests", "Ljava/util/LinkedList;", "value", "getRequestControl", "()Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "setRequestControl", "(Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;)V", "CaptureRequest", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nStillCaptureRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StillCaptureRequestControl.kt\nandroidx/camera/camera2/impl/StillCaptureRequestControl\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,216:1\n85#2,4:217\n85#2,4:221\n*S KotlinDebug\n*F\n+ 1 StillCaptureRequestControl.kt\nandroidx/camera/camera2/impl/StillCaptureRequestControl\n*L\n140#1:217,4\n145#1:221,4\n*E\n"})
/* loaded from: classes3.dex */
public final class StillCaptureRequestControl implements UseCaseCameraControl {
    private UseCaseCameraRequestControl _requestControl;
    private final FlashControl flashControl;
    private final Mutex mutex;
    private final LinkedList<CaptureRequest> pendingRequests;
    private final UseCaseThreads threads;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\b\u0018\u00002\u00020\u0001R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\r\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\r\u0010\n\u001a\u0004\b\u000e\u0010\fR%\u0010\u0011\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00100\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/camera/camera2/impl/StillCaptureRequestControl$CaptureRequest;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/CaptureConfig;", "captureConfigs", "Ljava/util/List;", "getCaptureConfigs", "()Ljava/util/List;", _UrlKt.FRAGMENT_ENCODE_SET, "captureMode", "I", "getCaptureMode", "()I", "flashType", "getFlashType", "Lkotlinx/coroutines/CompletableDeferred;", "Ljava/lang/Void;", "result", "Lkotlinx/coroutines/CompletableDeferred;", "getResult", "()Lkotlinx/coroutines/CompletableDeferred;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class CaptureRequest {
        public abstract List<CaptureConfig> getCaptureConfigs();

        public abstract int getCaptureMode();

        public abstract int getFlashType();

        public abstract CompletableDeferred<List<Void>> getResult();
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.StillCaptureRequestControl$reset$1", f = "StillCaptureRequestControl.kt", i = {0}, l = {Opcodes.OR_INT_LIT8}, m = "invokeSuspend", n = {"$this$withLock_u24default$iv"}, s = {"L$0"}, v = 1)
    @SourceDebugExtension({"SMAP\nStillCaptureRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StillCaptureRequestControl.kt\nandroidx/camera/camera2/impl/StillCaptureRequestControl$reset$1\n+ 2 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n*L\n1#1,216:1\n116#2,11:217\n*S KotlinDebug\n*F\n+ 1 StillCaptureRequestControl.kt\nandroidx/camera/camera2/impl/StillCaptureRequestControl$reset$1\n*L\n72#1:217,11\n*E\n"})
    /* renamed from: androidx.camera.camera2.impl.StillCaptureRequestControl$reset$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;
        final /* synthetic */ StillCaptureRequestControl this$0;

        public AnonymousClass1(StillCaptureRequestControl r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass1(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((AnonymousClass1) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r5) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            StillCaptureRequestControl r02 = (StillCaptureRequestControl) this.L$1;
            Mutex r4 = (Mutex) this.L$0;
            ResultKt.throwOnFailure(r5);
        L22:
        L15:
            th = move-exception;
            r4.unlock(null);
            throw th;
        L13:
            if (StillCaptureRequestControl.access$getPendingRequests$p(r02).isEmpty() == true) goto L17;
            CaptureRequest r52 = (CaptureRequest) StillCaptureRequestControl.access$getPendingRequests$p(r02).poll();     // Catch: Throwable -> L15
            goto L22
        L17:
            Unit r53 = Unit.INSTANCE;     // Catch: Throwable -> L15
            r4.unlock(null);
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r5);
            Mutex r54 = StillCaptureRequestControl.access$getMutex$p(this.this$0);
            StillCaptureRequestControl r12 = this.this$0;
            this.L$0 = r54;
            this.L$1 = r12;
            this.label = 1;
            if (r54.lock(null, this) != r0) goto L11;
            return r0;
        L11:
            r4 = r54;
            r02 = r12;
            goto L22
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.StillCaptureRequestControl", f = "StillCaptureRequestControl.kt", i = {0, 0}, l = {144}, m = "submitRequest", n = {PluginsConstants.REQUEST, "requestControl"}, s = {"L$0", "L$1"}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.StillCaptureRequestControl$submitRequest$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00901 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ StillCaptureRequestControl this$0;

        public C00901(StillCaptureRequestControl r1, Continuation<? super C00901> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return StillCaptureRequestControl.access$submitRequest(this.this$0, null, null, this);
        }
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001*\u00020\u0003H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/Void;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.StillCaptureRequestControl$submitRequest$4", f = "StillCaptureRequestControl.kt", i = {}, l = {160}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    @SourceDebugExtension({"SMAP\nStillCaptureRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StillCaptureRequestControl.kt\nandroidx/camera/camera2/impl/StillCaptureRequestControl$submitRequest$4\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,216:1\n85#2,4:217\n85#2,4:221\n*S KotlinDebug\n*F\n+ 1 StillCaptureRequestControl.kt\nandroidx/camera/camera2/impl/StillCaptureRequestControl$submitRequest$4\n*L\n157#1:217,4\n161#1:221,4\n*E\n"})
    /* renamed from: androidx.camera.camera2.impl.StillCaptureRequestControl$submitRequest$4, reason: invalid class name */
    public static final class AnonymousClass4 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends Void>>, Object> {
        final /* synthetic */ List<Deferred<Void>> $deferredList;
        final /* synthetic */ CaptureRequest $request;
        int label;

        /* JADX WARN: Multi-variable type inference failed */
        public AnonymousClass4(List<? extends Deferred<Void>> r1, CaptureRequest r2, Continuation<? super AnonymousClass4> r3) {
            this.$deferredList = r1;
            this.$request = r2;
            super(2, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r2, Continuation<?> r3) {
            return new AnonymousClass4(this.$deferredList, this.$request, r3);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super List<? extends Void>> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super List<Void>> r2) {
            return ((AnonymousClass4) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r7) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L8;
            if (r1 != 1) goto L6;
            ResultKt.throwOnFailure(r7);
        L14:
            CaptureRequest r6 = this.$request;
            List r02 = (List) r7;
            Camera2Logger r03 = Camera2Logger.INSTANCE;
            if (Logger.isDebugEnabled("CXCP") == false) goto L17;
            Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "StillCaptureRequestControl: Waiting for deferred list from " + r6 + " done");
        L17:
            return r7;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L8:
            ResultKt.throwOnFailure(r7);
            Camera2Logger r72 = Camera2Logger.INSTANCE;
            CaptureRequest r73 = this.$request;
            if (Logger.isDebugEnabled("CXCP") == false) goto L11;
            Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "StillCaptureRequestControl: Waiting for deferred list from " + r73);
        L11:
            List<Deferred<Void>> r74 = this.$deferredList;
            this.label = 1;
            r7 = AwaitKt.awaitAll(r74, this);
            if (r7 != r0) goto L14;
            return r0;
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.StillCaptureRequestControl$trySubmitPendingRequests$1", f = "StillCaptureRequestControl.kt", i = {0, 1, 1, 2, 2, 2, 2}, l = {118, Opcodes.OR_INT_LIT8, 123}, m = "invokeSuspend", n = {"requestControl", "requestControl", "$this$withLock_u24default$iv", "requestControl", "$this$withLock_u24default$iv", PluginsConstants.REQUEST, "requestControl"}, s = {"L$0", "L$0", "L$1", "L$0", "L$1", "L$3", "L$4"}, v = 1)
    @SourceDebugExtension({"SMAP\nStillCaptureRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StillCaptureRequestControl.kt\nandroidx/camera/camera2/impl/StillCaptureRequestControl$trySubmitPendingRequests$1\n+ 2 Mutex.kt\nkotlinx/coroutines/sync/MutexKt\n*L\n1#1,216:1\n116#2,11:217\n*S KotlinDebug\n*F\n+ 1 StillCaptureRequestControl.kt\nandroidx/camera/camera2/impl/StillCaptureRequestControl$trySubmitPendingRequests$1\n*L\n119#1:217,11\n*E\n"})
    /* renamed from: androidx.camera.camera2.impl.StillCaptureRequestControl$trySubmitPendingRequests$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00911 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        Object L$4;
        Object L$5;
        int label;
        final /* synthetic */ StillCaptureRequestControl this$0;

        public C00911(StillCaptureRequestControl r1, Continuation<? super C00911> r2) {
            this.this$0 = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new C00911(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super Unit> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super Unit> r2) {
            return ((C00911) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r7) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L17;
            if (r1 == 1) goto L16;
            if (r1 != 2) goto L7;
            StillCaptureRequestControl r02 = (StillCaptureRequestControl) this.L$2;
            Mutex r12 = (Mutex) this.L$1;
            UseCaseCameraRequestControl r6 = (UseCaseCameraRequestControl) this.L$0;
            ResultKt.throwOnFailure(r7);
            StillCaptureRequestControl r3 = r02;
            Mutex r5 = r12;
        L31:
            if (StillCaptureRequestControl.access$getPendingRequests$p(r3).isEmpty() == true) goto L33;
            CaptureRequest r62 = (CaptureRequest) StillCaptureRequestControl.access$getPendingRequests$p(r3).poll();     // Catch: Throwable -> L11
            goto L31
        L33:
            Unit r63 = Unit.INSTANCE;     // Catch: Throwable -> L11
            r5.unlock(null);
        L38:
            return Unit.INSTANCE;
        L11:
            th = move-exception;
            r5.unlock(null);
            throw th;
        L7:
            if (r1 != 3) goto L13;
            StillCaptureRequestControl r03 = (StillCaptureRequestControl) this.L$5;
            UseCaseCameraRequestControl r13 = (UseCaseCameraRequestControl) this.L$4;
            CaptureRequest r2 = (CaptureRequest) this.L$3;
            r3 = (StillCaptureRequestControl) this.L$2;
            r5 = (Mutex) this.L$1;
            UseCaseCameraRequestControl r64 = (UseCaseCameraRequestControl) this.L$0;
            ResultKt.throwOnFailure(r7);     // Catch: Throwable -> L11
            StillCaptureRequestControl.access$propagateResultOrEnqueueRequest(r03, (Deferred) r7, r2, r13);     // Catch: Throwable -> L11
            goto L31
        L13:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L16:
            UseCaseCameraRequestControl r14 = (UseCaseCameraRequestControl) this.L$0;
            ResultKt.throwOnFailure(r7);
        L25:
            if (((Boolean) r7).booleanValue() == false) goto L38;
            Mutex r72 = StillCaptureRequestControl.access$getMutex$p(this.this$0);
            r3 = this.this$0;
            this.L$0 = r14;
            this.L$1 = r72;
            this.L$2 = r3;
            this.label = 2;
            if (r72.lock(null, this) != r0) goto L29;
        L28:
            return r0;
        L29:
            r5 = r72;
            goto L31
        L17:
            ResultKt.throwOnFailure(r7);
            r14 = this.this$0.getRequestControl();
            if (r14 == null) goto L20;
            this.L$0 = r14;
            this.label = 1;
            r7 = r14.awaitSurfaceSetup(this);
            if (r7 != r0) goto L25;
        L20:
            return Unit.INSTANCE;
        }
    }

    public static Unit $r8$lambda$d94nr3kKLaWRlrxlGv5M4rvfiJA(StillCaptureRequestControl r6, Deferred r7, CaptureRequest r8, UseCaseCameraRequestControl r9, Throwable r10) {
        if ((r10 instanceof ImageCaptureException) == true) goto L5;
    L7:
        CoroutineAdaptersKt.propagateCompletion(r7, r8.getResult(), r10);
    L9:
        return Unit.INSTANCE;
    L5:
        if (((ImageCaptureException) r10).getImageCaptureError() != 3) goto L7;
        BuildersKt.launch$default(r6.threads.getSequentialScope(), null, null, new StillCaptureRequestControl$propagateResultOrEnqueueRequest$1$1(r6, r9, r8, null), 3, null);
        goto L9
    }

    public StillCaptureRequestControl(FlashControl r2, UseCaseThreads r3) {
        this.flashControl = r2;
        this.threads = r3;
        this.mutex = MutexKt.Mutex$default(false, 1, null);
        this.pendingRequests = new LinkedList();
    }

    public static final /* synthetic */ Mutex access$getMutex$p(StillCaptureRequestControl r0) {
        return r0.mutex;
    }

    public static final /* synthetic */ LinkedList access$getPendingRequests$p(StillCaptureRequestControl r0) {
        return r0.pendingRequests;
    }

    public static final /* synthetic */ void access$propagateResultOrEnqueueRequest(StillCaptureRequestControl r0, Deferred r1, CaptureRequest r2, UseCaseCameraRequestControl r3) {
        r0.propagateResultOrEnqueueRequest(r1, r2, r3);
    }

    public static final /* synthetic */ Object access$submitRequest(StillCaptureRequestControl r0, CaptureRequest r1, UseCaseCameraRequestControl r2, Continuation r3) {
        return r0.submitRequest(r1, r2, r3);
    }

    private final void propagateResultOrEnqueueRequest(Deferred<? extends List<Void>> r2, CaptureRequest r3, UseCaseCameraRequestControl r4) {
        r2.invokeOnCompletion(new StillCaptureRequestControl$$ExternalSyntheticLambda0(this, r2, r3, r4));
    }

    private final Object submitRequest(CaptureRequest r11, UseCaseCameraRequestControl r12, Continuation<? super Deferred<? extends List<Void>>> r13) {
        if ((r13 instanceof C00901) == false) goto L7;
        C00901 r0 = (C00901) r13;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r132 = r0.result;
        Object r14 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        r12 = (UseCaseCameraRequestControl) r0.L$1;
        r11 = (CaptureRequest) r0.L$0;
        ResultKt.throwOnFailure(r132);
    L20:
        int r133 = ((Number) r132).intValue();
        Camera2Logger r02 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L24;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "StillCaptureRequestControl: Issuing single capture");
    L24:
        return BuildersKt.async$default(this.threads.getSequentialScope(), null, null, new AnonymousClass4(r12.issueSingleCaptureAsync(r11.getCaptureConfigs(), r11.getCaptureMode(), r11.getFlashType(), r133), r11, null), 3, null);
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r132);
        Camera2Logger r134 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L17;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "StillCaptureRequestControl: submitting " + r11 + " at " + r12);
    L17:
        FlashControl r135 = this.flashControl;
        r0.L$0 = r11;
        r0.L$1 = r12;
        r0.label = 1;
        r132 = r135.awaitFlashModeUpdate(r0);
        if (r132 != r14) goto L20;
        return r14;
    L7:
        r0 = new C00901(this, r13);
        goto L8
    }

    private final void trySubmitPendingRequests() {
        BuildersKt.launch$default(this.threads.getSequentialScope(), null, null, new C00911(this, null), 3, null);
    }

    public UseCaseCameraRequestControl getRequestControl() {
        return this._requestControl;
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraControl
    public void reset() {
        BuildersKt.launch$default(this.threads.getSequentialScope(), null, null, new AnonymousClass1(this, null), 3, null);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraControl
    public void setRequestControl(UseCaseCameraRequestControl r1) {
        this._requestControl = r1;
        trySubmitPendingRequests();
    }
}
