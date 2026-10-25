package androidx.camera.camera2.impl;

import android.hardware.camera2.CaptureRequest;
import android.util.Log;
import androidx.camera.camera2.compat.workaround.TemplateParamsOverride;
import androidx.camera.camera2.config.UseCaseGraphContext;
import androidx.camera.camera2.pipe.AeMode;
import androidx.camera.camera2.pipe.AfMode;
import androidx.camera.camera2.pipe.AwbMode;
import androidx.camera.camera2.pipe.CameraControls3A;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.FrameInfo;
import androidx.camera.camera2.pipe.Metadata;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestFailure;
import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.camera2.pipe.RequestTemplate;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.core.Logger;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.MediaError;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000®\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0007\u0018\u00002\u00020\u0001:\u0002OPB\u0019\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bH\u0082@¢\u0006\u0004\b\t\u0010\nJ-\u0010\u000f\u001a\u00020\b*\u00020\u000b2\u0018\u0010\u000e\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0004\u0012\u00020\u0001\u0018\u00010\fH\u0002¢\u0006\u0004\b\u000f\u0010\u0010J3\u0010\u0013\u001a\u0004\u0018\u00010\u0012*\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f2\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\rH\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\u0092\u0001\u0010$\u001a\b\u0012\u0004\u0012\u00020\b0!2\u001a\b\u0002\u0010\u000e\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f2\b\b\u0002\u0010\u0016\u001a\u00020\u00152\u001a\b\u0002\u0010\u0018\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0017\u0012\u0004\u0012\u00020\u0001\u0018\u00010\f2\b\b\u0002\u0010\u0019\u001a\u00020\u00152\u0010\b\u0002\u0010\u001c\u001a\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001d2\u0010\b\u0002\u0010 \u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010\u001aH\u0086@¢\u0006\u0004\b\"\u0010#J\u0010\u0010%\u001a\u00020\bH\u0086@¢\u0006\u0004\b%\u0010\nJ\r\u0010&\u001a\u00020\b¢\u0006\u0004\b&\u0010'R\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010(R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010)R\u0014\u0010*\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b*\u0010+R\u001e\u0010-\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010,8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b-\u0010.R\u0014\u00100\u001a\u00020/8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b0\u00101R\u001c\u00104\u001a\b\u0012\u0004\u0012\u000203028\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b4\u00105R\u0016\u00106\u001a\u00020\u00158\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b6\u00107R$\u00109\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0004\u0012\u00020\u0001088\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b9\u0010:R$\u0010;\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0017\u0012\u0004\u0012\u00020\u0001088\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b;\u0010:R\u001a\u0010=\u001a\b\u0012\u0004\u0012\u00020\u001b0<8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b=\u0010>R\u001a\u0010?\u001a\b\u0012\u0004\u0012\u00020\u001f0<8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\b?\u0010>R\u0018\u0010@\u001a\u0004\u0018\u00010\u001d8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\u0018\u0010C\u001a\u0004\u0018\u00010B8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bC\u0010DR\u0018\u0010F\u001a\u0004\u0018\u00010E8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bF\u0010GR\u0018\u0010I\u001a\u0004\u0018\u00010H8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bI\u0010JR\u0018\u0010L\u001a\u00060KR\u00020\u00008\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bL\u0010MR\u0014\u0010N\u001a\u00020/8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bN\u00101¨\u0006Q"}, d2 = {"Landroidx/camera/camera2/impl/UseCaseCameraState;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/config/UseCaseGraphContext;", "useCaseGraphContext", "Landroidx/camera/camera2/compat/workaround/TemplateParamsOverride;", "templateParamsOverride", "<init>", "(Landroidx/camera/camera2/config/UseCaseGraphContext;Landroidx/camera/camera2/compat/workaround/TemplateParamsOverride;)V", _UrlKt.FRAGMENT_ENCODE_SET, "submitLatest", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/camera/camera2/pipe/CameraGraph$Session;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest$Key;", "parameters", "update3A", "(Landroidx/camera/camera2/pipe/CameraGraph$Session;Ljava/util/Map;)V", PluginsConstants.Settings.KEY, _UrlKt.FRAGMENT_ENCODE_SET, "getIntOrNull", "(Ljava/util/Map;Landroid/hardware/camera2/CaptureRequest$Key;)Ljava/lang/Integer;", _UrlKt.FRAGMENT_ENCODE_SET, "appendParameters", "Landroidx/camera/camera2/pipe/Metadata$Key;", "internalParameters", "appendInternalParameters", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "streams", "Landroidx/camera/camera2/pipe/RequestTemplate;", "template", "Landroidx/camera/camera2/pipe/Request$Listener;", "listeners", "Lkotlinx/coroutines/Deferred;", "updateAsync-Tp9XwKQ", "(Ljava/util/Map;ZLjava/util/Map;ZLjava/util/Set;Landroidx/camera/camera2/pipe/RequestTemplate;Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateAsync", "tryStartRepeating", "close", "()V", "Landroidx/camera/camera2/config/UseCaseGraphContext;", "Landroidx/camera/camera2/compat/workaround/TemplateParamsOverride;", "lock", "Ljava/lang/Object;", "Lkotlinx/coroutines/CompletableDeferred;", "updateSignal", "Lkotlinx/coroutines/CompletableDeferred;", "Lkotlinx/atomicfu/AtomicInt;", "submittedRequestCounter", "Lkotlinx/atomicfu/AtomicInt;", "Lkotlin/collections/ArrayDeque;", "Landroidx/camera/camera2/impl/UseCaseCameraState$RequestSignal;", "updateSignals", "Lkotlin/collections/ArrayDeque;", "updating", "Z", _UrlKt.FRAGMENT_ENCODE_SET, "currentParameters", "Ljava/util/Map;", "currentInternalParameters", _UrlKt.FRAGMENT_ENCODE_SET, "currentStreams", "Ljava/util/Set;", "currentListeners", "currentTemplate", "Landroidx/camera/camera2/pipe/RequestTemplate;", "Landroidx/camera/camera2/pipe/AeMode;", "lastAeMode", "Landroidx/camera/camera2/pipe/AeMode;", "Landroidx/camera/camera2/pipe/AfMode;", "lastAfMode", "Landroidx/camera/camera2/pipe/AfMode;", "Landroidx/camera/camera2/pipe/AwbMode;", "lastAwbMode", "Landroidx/camera/camera2/pipe/AwbMode;", "Landroidx/camera/camera2/impl/UseCaseCameraState$RequestListener;", "requestListener", "Landroidx/camera/camera2/impl/UseCaseCameraState$RequestListener;", "pendingSignalCount", "RequestSignal", "RequestListener", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUseCaseCameraState.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseCameraState.kt\nandroidx/camera/camera2/impl/UseCaseCameraState\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 UseCaseCameraConfig.kt\nandroidx/camera/camera2/config/UseCaseGraphContext\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,399:1\n166#1:400\n167#1,2:403\n171#1,24:406\n85#2,2:401\n88#2:405\n85#2,4:430\n85#2,4:436\n95#2,4:440\n85#2,4:444\n242#3:434\n1#4:435\n*S KotlinDebug\n*F\n+ 1 UseCaseCameraState.kt\nandroidx/camera/camera2/impl/UseCaseCameraState\n*L\n128#1:400\n128#1:403,2\n128#1:406,24\n128#1:401,2\n128#1:405\n166#1:430,4\n255#1:436,4\n261#1:440,4\n316#1:444,4\n213#1:434\n213#1:435\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseCameraState {
    private final Map<Metadata.Key<?>, Object> currentInternalParameters;
    private final Set<Request.Listener> currentListeners;
    private final Map<CaptureRequest.Key<?>, Object> currentParameters;
    private final Set<StreamId> currentStreams;
    private RequestTemplate currentTemplate;
    private AeMode lastAeMode;
    private AfMode lastAfMode;
    private AwbMode lastAwbMode;
    private final Object lock;
    private final AtomicInt pendingSignalCount;
    private final RequestListener requestListener;
    private final AtomicInt submittedRequestCounter;
    private final TemplateParamsOverride templateParamsOverride;
    private CompletableDeferred<Unit> updateSignal;
    private ArrayDeque<RequestSignal> updateSignals;
    private boolean updating;
    private final UseCaseGraphContext useCaseGraphContext;

    @kotlin.Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J'\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016¢\u0006\u0004\b\f\u0010\rJ'\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016¢\u0006\u0004\b\u0011\u0010\u0012J\u001c\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002J\u001a\u0010\u0014\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\"\u0010\u0013\u001a\u00020\u0005*\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002¨\u0006\u001b"}, d2 = {"Landroidx/camera/camera2/impl/UseCaseCameraState$RequestListener;", "Landroidx/camera/camera2/pipe/Request$Listener;", "<init>", "(Landroidx/camera/camera2/impl/UseCaseCameraState;)V", "onTotalCaptureResult", _UrlKt.FRAGMENT_ENCODE_SET, "requestMetadata", "Landroidx/camera/camera2/pipe/RequestMetadata;", "frameNumber", "Landroidx/camera/camera2/pipe/FrameNumber;", "totalCaptureResult", "Landroidx/camera/camera2/pipe/FrameInfo;", "onTotalCaptureResult-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/FrameInfo;)V", "onFailed", "requestFailure", "Landroidx/camera/camera2/pipe/RequestFailure;", "onFailed-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/RequestFailure;)V", "completeExceptionally", "complete", "Lkotlin/collections/ArrayDeque;", "Landroidx/camera/camera2/impl/UseCaseCameraState$RequestSignal;", "requestNo", _UrlKt.FRAGMENT_ENCODE_SET, "throwable", _UrlKt.FRAGMENT_ENCODE_SET, "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nUseCaseCameraState.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseCameraState.kt\nandroidx/camera/camera2/impl/UseCaseCameraState$RequestListener\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,399:1\n1#2:400\n*E\n"})
    public final class RequestListener implements Request.Listener {
        final /* synthetic */ UseCaseCameraState this$0;

        public RequestListener(UseCaseCameraState r1) {
            this.this$0 = r1;
        }

        private final void complete(ArrayDeque<RequestSignal> r3, int r4) {
        L3:
            if (r3.isEmpty() == true) goto L7;
            if (r3.first().getRequestNo() > r4) goto L10;
            r3.first().getSignal().complete(Unit.INSTANCE);
            CollectionsKt.removeFirst(r3);
            UseCaseCameraState.access$getPendingSignalCount$p(this.this$0).decrementAndGet();
            goto L3
        L10:
            return;
        }

        private final void completeExceptionally(RequestMetadata r6, RequestFailure r7) {
            Integer r62 = (Integer) r6.get(TagsKt.getUSE_CASE_CAMERA_STATE_CUSTOM_TAG());
            if (r62 == null) goto L17;
            UseCaseCameraState r2 = this.this$0;
            int r63 = r62.intValue();
            Object r3 = UseCaseCameraState.access$getLock$p(r2);
            monitor-enter(r3);
            ArrayDeque<RequestSignal> r22 = UseCaseCameraState.access$getUpdateSignals$p(r2);     // Catch: Throwable -> L9
            StringBuilder r4 = new StringBuilder("Failed in framework level");     // Catch: Throwable -> L9
            if (r7 == null) goto L11;
            String r72 = " with CaptureFailure.reason = " + r7.getReason();     // Catch: Throwable -> L9
        L12:
            r4.append(r72);     // Catch: Throwable -> L9
            completeExceptionally(r22, r63, new Throwable(r4.toString()));     // Catch: Throwable -> L9
            Unit r5 = Unit.INSTANCE;     // Catch: Throwable -> L9
            monitor-exit(r3);
            return;
        L11:
            r72 = _UrlKt.FRAGMENT_ENCODE_SET;
        L9:
            th = move-exception;
            throw th;
        }

        private final void completeExceptionally(ArrayDeque<RequestSignal> r2, int r3, Throwable r4) {
        L3:
            if (r2.isEmpty() == true) goto L7;
            if (r2.first().getRequestNo() > r3) goto L10;
            r2.first().getSignal().completeExceptionally(r4);
            CollectionsKt.removeFirst(r2);
            UseCaseCameraState.access$getPendingSignalCount$p(this.this$0).decrementAndGet();
            goto L3
        L10:
            return;
        }

        @Override // androidx.camera.camera2.pipe.Request.Listener
        /* renamed from: onFailed-CcXjc1I */
        public void mo14onFailedCcXjc1I(RequestMetadata r1, long r2, RequestFailure r4) {
            if (UseCaseCameraState.access$getPendingSignalCount$p(this.this$0).getValue() != 0) goto L5;
            return;
        L5:
            completeExceptionally(r1, r4);
        }

        @Override // androidx.camera.camera2.pipe.Request.Listener
        /* renamed from: onTotalCaptureResult-CcXjc1I */
        public void mo15onTotalCaptureResultCcXjc1I(RequestMetadata r1, long r2, FrameInfo r4) {
            if (UseCaseCameraState.access$getPendingSignalCount$p(this.this$0).getValue() == 0) goto L18;
            Integer r12 = (Integer) r1.get(TagsKt.getUSE_CASE_CAMERA_STATE_CUSTOM_TAG());
            if (r12 == null) goto L15;
            UseCaseCameraState r22 = this.this$0;
            int r13 = r12.intValue();
            Object r3 = UseCaseCameraState.access$getLock$p(r22);
            monitor-enter(r3);
            complete(UseCaseCameraState.access$getUpdateSignals$p(r22), r13);     // Catch: Throwable -> L12
            Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L12
            monitor-exit(r3);
            return;
        L12:
            th = move-exception;
            throw th;
        L15:
            return;
        }
    }

    @kotlin.Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\b\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u0010\u0010\f\u001a\u00020\u0002HÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0010\u001a\u00020\u000f2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\rR\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0017"}, d2 = {"Landroidx/camera/camera2/impl/UseCaseCameraState$RequestSignal;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "requestNo", "Lkotlinx/coroutines/CompletableDeferred;", _UrlKt.FRAGMENT_ENCODE_SET, "signal", "<init>", "(ILkotlinx/coroutines/CompletableDeferred;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "I", "getRequestNo", "Lkotlinx/coroutines/CompletableDeferred;", "getSignal", "()Lkotlinx/coroutines/CompletableDeferred;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class RequestSignal {
        private final int requestNo;
        private final CompletableDeferred<Unit> signal;

        public RequestSignal(int r1, CompletableDeferred<Unit> r2) {
            this.requestNo = r1;
            this.signal = r2;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof RequestSignal) == true) goto L8;
            return false;
        L8:
            RequestSignal r52 = (RequestSignal) r5;
            if (this.requestNo == r52.requestNo) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.signal, r52.signal) == true) goto L14;
            return false;
        L14:
            return true;
        }

        public final int getRequestNo() {
            return this.requestNo;
        }

        public final CompletableDeferred<Unit> getSignal() {
            return this.signal;
        }

        public int hashCode() {
            return (Integer.hashCode(this.requestNo) * 31) + this.signal.hashCode();
        }

        public String toString() {
            return "RequestSignal(requestNo=" + this.requestNo + ", signal=" + this.signal + ')';
        }
    }

    @kotlin.Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.UseCaseCameraState", f = "UseCaseCameraState.kt", i = {0}, l = {MediaError.DetailedErrorCode.MANIFEST_UNKNOWN}, m = "submitLatest", n = {"signalToComplete"}, s = {"L$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.UseCaseCameraState$submitLatest$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ UseCaseCameraState this$0;

        public AnonymousClass1(UseCaseCameraState r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return UseCaseCameraState.access$submitLatest(this.this$0, this);
        }
    }

    public UseCaseCameraState(UseCaseGraphContext r1, TemplateParamsOverride r2) {
        this.useCaseGraphContext = r1;
        this.templateParamsOverride = r2;
        this.lock = new Object();
        this.submittedRequestCounter = AtomicFU.atomic(0);
        this.updateSignals = new ArrayDeque();
        this.currentParameters = new LinkedHashMap();
        this.currentInternalParameters = new LinkedHashMap();
        this.currentStreams = new LinkedHashSet();
        this.currentListeners = new LinkedHashSet();
        this.requestListener = new RequestListener(this);
        this.pendingSignalCount = AtomicFU.atomic(0);
    }

    public static final /* synthetic */ Object access$getLock$p(UseCaseCameraState r0) {
        return r0.lock;
    }

    public static final /* synthetic */ AtomicInt access$getPendingSignalCount$p(UseCaseCameraState r0) {
        return r0.pendingSignalCount;
    }

    public static final /* synthetic */ ArrayDeque access$getUpdateSignals$p(UseCaseCameraState r0) {
        return r0.updateSignals;
    }

    public static final /* synthetic */ Object access$submitLatest(UseCaseCameraState r0, Continuation r1) {
        return r0.submitLatest(r1);
    }

    private final Integer getIntOrNull(Map<CaptureRequest.Key<?>, ? extends Object> r1, CaptureRequest.Key<?> r2) {
        if (r1 == null) goto L5;
        Object r12 = r1.get(r2);
    L7:
        if ((r12 instanceof Integer) == true) goto L9;
        return null;
    L9:
        return (Integer) r12;
    L5:
        r12 = null;
        goto L7
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v10, types: [T, kotlinx.coroutines.CompletableDeferred<kotlin.Unit>] */
    /* JADX WARN: Type inference failed for: r10v10, types: [T, kotlinx.coroutines.CompletableDeferred<kotlin.Unit>] */
    /* JADX WARN: Type inference failed for: r10v9, types: [T, androidx.camera.camera2.pipe.Request] */
    private final Object submitLatest(Continuation<? super Unit> r20) {
        if ((r20 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r3 = (AnonymousClass1) r20;
        int r4 = r3.label;
        if ((r4 & Integer.MIN_VALUE) == 0) goto L7;
        r3.label = r4 - Integer.MIN_VALUE;
    L8:
        Object r0 = r3.result;
        Object r42 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r5 = r3.label;
        if (r5 == 0) goto L18;
        if (r5 != 1) goto L16;
        Ref.ObjectRef r32 = (Ref.ObjectRef) r3.L$0;
        ResultKt.throwOnFailure(r0);     // Catch: CancellationException -> L14
    L23:
        AutoCloseable r43 = (AutoCloseable) r0;     // Catch: CancellationException -> L14
        CameraGraph.Session r02 = (CameraGraph.Session) r43;     // Catch: Throwable -> L37
        Ref.ObjectRef r52 = new Ref.ObjectRef();     // Catch: Throwable -> L37
        Ref.ObjectRef r7 = new Ref.ObjectRef();     // Catch: Throwable -> L37
        Object r9 = this.lock;     // Catch: Throwable -> L37
        monitor-enter(r9);     // Catch: Throwable -> L37
    L29:
        th = move-exception;
        throw th;     // Catch: Throwable -> L37
    L27:
        if (this.currentStreams.isEmpty() == false) goto L31;
        r52.element = null;     // Catch: Throwable -> L29
    L32:
        ?? r10 = this.updateSignal;     // Catch: Throwable -> L29
        r7.element = r10;     // Catch: Throwable -> L29
        this.updating = false;     // Catch: Throwable -> L29
        this.updateSignal = null;     // Catch: Throwable -> L29
        Unit r11 = Unit.INSTANCE;     // Catch: Throwable -> L29
        monitor-exit(r9);     // Catch: Throwable -> L37
        if (r52.element != 0) goto L39;
        r02.stopRepeating();     // Catch: Throwable -> L37
        r32.element = r7.element;     // Catch: Throwable -> L37
    L53:
        AutoCloseableKt.closeFinally(r43, null);     // Catch: CancellationException -> L14
    L75:
        CompletableDeferred r03 = (CompletableDeferred) r32.element;
        if (r03 == null) goto L79;
        Boxing.boxBoolean(r03.complete(Unit.INSTANCE));
    L79:
        return Unit.INSTANCE;
    L39:
        CompletableDeferred r102 = (CompletableDeferred) r10;     // Catch: Throwable -> L37
        if (r102 == null) goto L49;
        Object r72 = this.lock;     // Catch: Throwable -> L37
        monitor-enter(r72);     // Catch: Throwable -> L37
        this.updateSignals.add(new RequestSignal(this.submittedRequestCounter.getValue(), r102));     // Catch: Throwable -> L46
        int r92 = this.pendingSignalCount.incrementAndGet();     // Catch: Throwable -> L46
        monitor-exit(r72);     // Catch: Throwable -> L37
        Boxing.boxInt(r92);     // Catch: Throwable -> L37
        goto L49
    L46:
        th = move-exception;
        throw th;     // Catch: Throwable -> L37
    L49:
        Camera2Logger r73 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L37
        if (Logger.isDebugEnabled("CXCP") == false) goto L52;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Update RepeatingRequest: " + r52.element);     // Catch: Throwable -> L37
    L52:
        r02.startRepeating((Request) r52.element);     // Catch: Throwable -> L37
        update3A(r02, ((Request) r52.element).getParameters());     // Catch: Throwable -> L37
        goto L53
    L31:
        RequestTemplate r15 = this.currentTemplate;     // Catch: Throwable -> L29
        List r112 = CollectionsKt.toList(this.currentStreams);     // Catch: Throwable -> L29
        Map r12 = MapsKt.plus(this.templateParamsOverride.mo35getOverrideParamsxlOpshk(this.currentTemplate), MapsKt.toMap(this.currentParameters));     // Catch: Throwable -> L29
        Map r13 = MapsKt.toMutableMap(this.currentInternalParameters);     // Catch: Throwable -> L29
        r13.put(TagsKt.getUSE_CASE_CAMERA_STATE_CUSTOM_TAG(), Boxing.boxInt(this.submittedRequestCounter.incrementAndGet()));     // Catch: Throwable -> L29
        List r14 = CollectionsKt.toMutableList(this.currentListeners);     // Catch: Throwable -> L29
        r14.add(this.requestListener);     // Catch: Throwable -> L29
        r52.element = new Request(r112, r12, r13, r14, r15, null, 32, null);     // Catch: Throwable -> L29
    L37:
        th = move-exception;
        throw th;     // Catch: Throwable -> L58
    L58:
        th = move-exception;
        AutoCloseableKt.closeFinally(r43, th);     // Catch: CancellationException -> L14
        throw th;     // Catch: CancellationException -> L14
    L14:
        e = e;
    L63:
        Camera2Logger r2 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L66;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Cannot acquire session at " + this, e);
    L66:
        Object r22 = this.lock;
        monitor-enter(r22);
    L71:
        th = move-exception;
        throw th;
    L69:
        if (this.updating == false) goto L73;
        this.updating = false;     // Catch: Throwable -> L71
        r32.element = this.updateSignal;     // Catch: Throwable -> L71
        this.updateSignal = null;     // Catch: Throwable -> L71
    L73:
        Unit r04 = Unit.INSTANCE;     // Catch: Throwable -> L71
        monitor-exit(r22);
        goto L75
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r0);
        Ref.ObjectRef r53 = new Ref.ObjectRef();
        CameraGraph r05 = this.useCaseGraphContext.getGraph();     // Catch: CancellationException -> L61
        r3.L$0 = r53;     // Catch: CancellationException -> L61
        r3.label = 1;     // Catch: CancellationException -> L61
        r0 = r05.acquireSession(r3);     // Catch: CancellationException -> L61
        if (r0 != r42) goto L22;
        return r42;
    L22:
        r32 = r53;
    L61:
        e = e;
        r32 = r53;
    L7:
        r3 = new AnonymousClass1(this, r20);
        goto L8
    }

    private final void update3A(CameraGraph.Session r12, Map<CaptureRequest.Key<?>, ? extends Object> r13) {
        Integer r0 = getIntOrNull(r13, CaptureRequest.CONTROL_AE_MODE);
        AwbMode r1 = null;
        if (r0 == null) goto L5;
        AeMode r3 = AeMode.Companion.m117fromIntOrNullkQd0u18(r0.intValue());
    L6:
        Integer r02 = getIntOrNull(r13, CaptureRequest.CONTROL_AF_MODE);
        if (r02 == null) goto L9;
        AfMode r4 = AfMode.Companion.m128fromIntOrNullMKXwA8g(r02.intValue());
    L10:
        Integer r132 = getIntOrNull(r13, CaptureRequest.CONTROL_AWB_MODE);
        if (r132 == null) goto L13;
        r1 = AwbMode.Companion.m147fromIntOrNullSaEiwI(r132.intValue());
    L13:
        AwbMode r5 = r1;
        boolean r133 = false;
        if (r3 != null) goto L16;
    L18:
        boolean r14 = false;
    L19:
        if (r4 != null) goto L21;
    L23:
        boolean r2 = false;
    L24:
        if (r5 != null) goto L26;
    L28:
        if (r14 == true) goto L31;
        if (r2 == true) goto L31;
        if (r133 == true) goto L31;
        return;
    L31:
        Camera2Logger r03 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L34;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "UseCaseCameraState: Updating 3A modes: AE(" + r3 + ", changed=" + r14 + "), AF(" + r4 + ", changed=" + r2 + "), AWB(" + r5 + ", changed=" + r133 + ')');
    L34:
        CameraControls3A.m164update3AydBZfZg$default(r12, r3, r4, r5, null, null, null, 56, null);
        if (r3 == null) goto L37;
        this.lastAeMode = r3;
    L37:
        if (r4 == null) goto L39;
        this.lastAfMode = r4;
    L39:
        if (r5 == null) goto L43;
        this.lastAwbMode = r5;
        return;
    L43:
        return;
    L26:
        if (Intrinsics.areEqual(r5, this.lastAwbMode) == true) goto L28;
        r133 = true;
        goto L28
    L21:
        if (Intrinsics.areEqual(r4, this.lastAfMode) == true) goto L23;
        r2 = true;
        goto L24
    L16:
        if (Intrinsics.areEqual(r3, this.lastAeMode) == true) goto L18;
        r14 = true;
        goto L19
    L9:
        r4 = null;
        goto L10
    L5:
        r3 = null;
        goto L6
    }

    public final void close() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.updating == false) goto L13;
        this.updating = false;     // Catch: Throwable -> L9
        CompletableDeferred<Unit> r1 = this.updateSignal;     // Catch: Throwable -> L9
        if (r1 == null) goto L11;
        r1.completeExceptionally(new CancellationException("UseCaseCameraState closed"));     // Catch: Throwable -> L9
    L11:
        this.updateSignal = null;     // Catch: Throwable -> L9
    L13:
        if (this.updateSignals.isEmpty() == true) goto L15;
        this.updateSignals.removeFirst().getSignal().completeExceptionally(new CancellationException("UseCaseCameraState closed"));     // Catch: Throwable -> L9
        this.pendingSignalCount.decrementAndGet();     // Catch: Throwable -> L9
        goto L13
    L15:
        Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r0);
    }

    public final Object tryStartRepeating(Continuation<? super Unit> r1) {
        Object r0 = submitLatest(r1);
        if (r0 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L6;
        return r0;
    L6:
        return Unit.INSTANCE;
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [T, kotlinx.coroutines.CompletableDeferred<kotlin.Unit>] */
    /* renamed from: updateAsync-Tp9XwKQ, reason: not valid java name */
    public final Object m104updateAsyncTp9XwKQ(Map<CaptureRequest.Key<?>, ? extends Object> r15, boolean r16, Map<Metadata.Key<?>, ? extends Object> r17, boolean r18, Set<StreamId> r19, RequestTemplate r20, Set<? extends Request.Listener> r21, Continuation<? super Deferred<Unit>> r22) {
        if ((r22 instanceof UseCaseCameraState$updateAsync$1) == false) goto L7;
        UseCaseCameraState$updateAsync$1 r7 = (UseCaseCameraState$updateAsync$1) r22;
        int r8 = r7.label;
        if ((r8 & Integer.MIN_VALUE) == 0) goto L7;
        r7.label = r8 - Integer.MIN_VALUE;
    L8:
        Object r5 = r7.result;
        Object r82 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r9 = r7.label;
        if (r9 == 0) goto L14;
        if (r9 != 1) goto L12;
        Ref.ObjectRef r14 = (Ref.ObjectRef) r7.L$0;
        ResultKt.throwOnFailure(r5);
    L50:
        return r14.element;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r5);
        Ref.ObjectRef r52 = new Ref.ObjectRef();
        Object r92 = this.lock;
        monitor-enter(r92);
        Camera2Logger r12 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L19
        if (Logger.isDebugEnabled("CXCP") == false) goto L21;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "UseCaseCameraState#updateState: parameters = " + r15 + ", internalParameters = " + r17 + ", streams = " + r19 + ", template = " + r20);     // Catch: Throwable -> L19
    L21:
        if (r15 == null) goto L25;
        if (r16 == true) goto L24;
        this.currentParameters.clear();     // Catch: Throwable -> L19
    L24:
        this.currentParameters.putAll(r15);     // Catch: Throwable -> L19
    L25:
        if (r17 == null) goto L29;
        if (r18 == true) goto L28;
        this.currentInternalParameters.clear();     // Catch: Throwable -> L19
    L28:
        this.currentInternalParameters.putAll(r17);     // Catch: Throwable -> L19
    L29:
        if (r19 == null) goto L31;
        this.currentStreams.clear();     // Catch: Throwable -> L19
        this.currentStreams.addAll(r19);     // Catch: Throwable -> L19
    L31:
        if (r20 == null) goto L33;
        this.currentTemplate = r20;     // Catch: Throwable -> L19
    L33:
        if (r21 == null) goto L36;
        this.currentListeners.clear();     // Catch: Throwable -> L19
        this.currentListeners.addAll(r21);     // Catch: Throwable -> L19
    L36:
        if (this.updateSignal != null) goto L39;
        this.updateSignal = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);     // Catch: Throwable -> L19
    L39:
        if (this.updating == false) goto L43;
        CompletableDeferred<Unit> r142 = this.updateSignal;     // Catch: Throwable -> L19
        monitor-exit(r92);
        return r142;
    L43:
        this.updating = true;     // Catch: Throwable -> L19
        r52.element = this.updateSignal;     // Catch: Throwable -> L19
        Unit r0 = Unit.INSTANCE;     // Catch: Throwable -> L19
        monitor-exit(r92);
        r7.L$0 = r52;
        r7.label = 1;
        if (submitLatest(r7) != r82) goto L48;
        return r82;
    L48:
        r14 = r52;
    L19:
        th = move-exception;
        throw th;
    L7:
        r7 = new UseCaseCameraState$updateAsync$1(this, r22);
        goto L8
    }
}
