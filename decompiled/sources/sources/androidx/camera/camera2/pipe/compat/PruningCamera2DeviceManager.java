package androidx.camera.camera2.pipe.compat;

import androidx.camera.camera2.pipe.CameraError;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.GraphState;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.Permissions;
import androidx.camera.camera2.pipe.core.PruningProcessingQueue;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.core.Token;
import androidx.camera.camera2.pipe.graph.GraphListener;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000Ê\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\b\b\u0001\u0018\u00002\u00020\u0001:\u0003efgB1\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0004\b\f\u0010\rJ\u0013\u0010\u0010\u001a\u00020\u000f*\u00020\u000eH\u0002¢\u0006\u0004\b\u0010\u0010\u0011J\u0018\u0010\u0013\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u000eH\u0082@¢\u0006\u0004\b\u0013\u0010\u0014J\u0018\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0015H\u0082@¢\u0006\u0004\b\u0016\u0010\u0017J\u0018\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0018H\u0082@¢\u0006\u0004\b\u0019\u0010\u001aJ\u0018\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u001bH\u0082@¢\u0006\u0004\b\u001c\u0010\u001dJ\u0018\u0010 \u001a\u00020\u000f2\u0006\u0010\u001f\u001a\u00020\u001eH\u0082@¢\u0006\u0004\b \u0010!J \u0010(\u001a\u00020%2\u0006\u0010#\u001a\u00020\"2\u0006\u0010$\u001a\u00020\u0015H\u0082@¢\u0006\u0004\b&\u0010'JB\u00103\u001a\u0002002\u0006\u0010#\u001a\u00020\"2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\"0)2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020,0+2\u0006\u0010/\u001a\u00020.H\u0082@¢\u0006\u0004\b1\u00102J\u001e\u00106\u001a\u00020\u000f2\f\u00105\u001a\b\u0012\u0004\u0012\u00020\"04H\u0082@¢\u0006\u0004\b6\u00107J\u001e\u0010:\u001a\u00020\u000f2\f\u00109\u001a\b\u0012\u0004\u0012\u0002080)H\u0082@¢\u0006\u0004\b:\u0010;J3\u0010@\u001a\b\u0012\u0004\u0012\u00028\u00000)\"\u0004\b\u0000\u0010<*\b\u0012\u0004\u0012\u00028\u00000=2\f\u0010?\u001a\b\u0012\u0004\u0012\u00020>04H\u0002¢\u0006\u0004\b@\u0010AJK\u0010H\u001a\u0004\u0018\u00010E2\u0006\u0010#\u001a\u00020\"2\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\"0)2\u0006\u0010C\u001a\u00020B2\u0006\u0010D\u001a\u00020,2\u0012\u0010-\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020,0+H\u0016¢\u0006\u0004\bF\u0010GJ\u001d\u0010L\u001a\b\u0012\u0004\u0012\u00020\u000f0I2\u0006\u0010#\u001a\u00020\"H\u0016¢\u0006\u0004\bJ\u0010KJ\u001d\u0010N\u001a\b\u0012\u0004\u0012\u00020\u000f0I2\u0006\u0010M\u001a\u00020,H\u0016¢\u0006\u0004\bN\u0010OJ\u001d\u0010S\u001a\u00020\u000f2\f\u0010P\u001a\b\u0012\u0004\u0012\u00020\u000e0=H\u0001¢\u0006\u0004\bQ\u0010RR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010TR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010UR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010VR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010WR\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010X\u001a\u0004\bY\u0010ZR\u0014\u0010/\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u0010[R\u001a\u0010]\u001a\b\u0012\u0004\u0012\u00020\u000e0\\8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b]\u0010^R\u001a\u0010a\u001a\b\u0012\u0004\u0012\u00020`0_8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\ba\u0010bR\u001a\u0010c\u001a\b\u0012\u0004\u0012\u0002080=8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bc\u0010d¨\u0006h"}, d2 = {"Landroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager;", "Landroidx/camera/camera2/pipe/compat/Camera2DeviceManager;", "Landroidx/camera/camera2/pipe/core/Permissions;", "permissions", "Landroidx/camera/camera2/pipe/compat/RetryingCameraStateOpener;", "retryingCameraStateOpener", "Landroidx/camera/camera2/pipe/compat/Camera2DeviceCloser;", "camera2DeviceCloser", "Landroidx/camera/camera2/pipe/compat/Camera2ErrorProcessor;", "camera2ErrorProcessor", "Landroidx/camera/camera2/pipe/core/Threads;", "threads", "<init>", "(Landroidx/camera/camera2/pipe/core/Permissions;Landroidx/camera/camera2/pipe/compat/RetryingCameraStateOpener;Landroidx/camera/camera2/pipe/compat/Camera2DeviceCloser;Landroidx/camera/camera2/pipe/compat/Camera2ErrorProcessor;Landroidx/camera/camera2/pipe/core/Threads;)V", "Landroidx/camera/camera2/pipe/compat/CameraRequest;", _UrlKt.FRAGMENT_ENCODE_SET, "onRemoved", "(Landroidx/camera/camera2/pipe/compat/CameraRequest;)V", PluginsConstants.REQUEST, "process", "(Landroidx/camera/camera2/pipe/compat/CameraRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/camera/camera2/pipe/compat/RequestOpen;", "processRequestOpen", "(Landroidx/camera/camera2/pipe/compat/RequestOpen;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/camera/camera2/pipe/compat/RequestClose;", "processRequestClose", "(Landroidx/camera/camera2/pipe/compat/RequestClose;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/camera/camera2/pipe/compat/RequestCloseById;", "processRequestCloseById", "(Landroidx/camera/camera2/pipe/compat/RequestCloseById;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/camera/camera2/pipe/compat/RequestCloseAll;", "requestCloseAll", "processRequestCloseAll", "(Landroidx/camera/camera2/pipe/compat/RequestCloseAll;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/camera/camera2/pipe/CameraId;", "cameraId", "requestOpen", "Landroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager$RetrieveActiveCameraResult;", "retrieveActiveCamera-RzXb1QE", "(Ljava/lang/String;Landroidx/camera/camera2/pipe/compat/RequestOpen;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "retrieveActiveCamera", _UrlKt.FRAGMENT_ENCODE_SET, "sharedCameraIds", "Lkotlin/Function1;", _UrlKt.FRAGMENT_ENCODE_SET, "isForegroundObserver", "Lkotlinx/coroutines/CoroutineScope;", "scope", "Landroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager$OpenVirtualCameraResult;", "openCameraWithRetry-zDSwpeU", "(Ljava/lang/String;Ljava/util/List;Lkotlin/jvm/functions/Function1;Lkotlinx/coroutines/CoroutineScope;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "openCameraWithRetry", _UrlKt.FRAGMENT_ENCODE_SET, "cameraIds", "connectPendingRequestOpens", "(Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager$PendingRequestOpen;", "pendingRequestOpensToDisconnect", "disconnectPendingRequestOpens", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "T", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "indices", "removeIndices", "(Ljava/util/List;Ljava/util/Set;)Ljava/util/List;", "Landroidx/camera/camera2/pipe/graph/GraphListener;", "graphListener", "isPrewarm", "Landroidx/camera/camera2/pipe/compat/VirtualCamera;", "open-zDSwpeU", "(Ljava/lang/String;Ljava/util/List;Landroidx/camera/camera2/pipe/graph/GraphListener;ZLkotlin/jvm/functions/Function1;)Landroidx/camera/camera2/pipe/compat/VirtualCamera;", "open", "Lkotlinx/coroutines/Deferred;", "close-EfqyGwQ", "(Ljava/lang/String;)Lkotlinx/coroutines/Deferred;", "close", "forceCancelOpen", "closeAll", "(Z)Lkotlinx/coroutines/Deferred;", "requests", "prune$camera_camera2_pipe", "(Ljava/util/List;)V", "prune", "Landroidx/camera/camera2/pipe/core/Permissions;", "Landroidx/camera/camera2/pipe/compat/RetryingCameraStateOpener;", "Landroidx/camera/camera2/pipe/compat/Camera2DeviceCloser;", "Landroidx/camera/camera2/pipe/compat/Camera2ErrorProcessor;", "Landroidx/camera/camera2/pipe/core/Threads;", "getThreads", "()Landroidx/camera/camera2/pipe/core/Threads;", "Lkotlinx/coroutines/CoroutineScope;", "Landroidx/camera/camera2/pipe/core/PruningProcessingQueue;", "queue", "Landroidx/camera/camera2/pipe/core/PruningProcessingQueue;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/compat/ActiveCamera;", "activeCameras", "Ljava/util/Set;", "pendingRequestOpens", "Ljava/util/List;", "PendingRequestOpen", "RetrieveActiveCameraResult", "OpenVirtualCameraResult", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCamera2DeviceManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Camera2DeviceManager.kt\nandroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager\n+ 2 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,656:1\n627#1,6:673\n627#1,6:679\n82#2,2:657\n82#2,2:659\n82#2,2:661\n50#2,2:685\n59#2,2:689\n82#2,2:700\n71#2,2:702\n59#2,2:710\n59#2,2:715\n59#2,2:722\n59#2,2:724\n59#2,2:726\n59#2,2:728\n50#2,2:730\n774#3:663\n865#3,2:664\n388#3,7:666\n1869#3,2:687\n774#3:691\n865#3,2:692\n774#3:694\n865#3,2:695\n774#3:697\n865#3,2:698\n1740#3,2:704\n1761#3,3:706\n1742#3:709\n774#3:712\n865#3,2:713\n774#3:717\n865#3,2:718\n295#3,2:720\n774#3:732\n865#3,2:733\n1740#3,2:735\n1761#3,3:737\n1742#3:740\n*S KotlinDebug\n*F\n+ 1 Camera2DeviceManager.kt\nandroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager\n*L\n317#1:673,6\n339#1:679,6\n221#1:657,2\n247#1:659,2\n259#1:661,2\n346#1:685,2\n376#1:689,2\n407#1:700,2\n412#1:702,2\n456#1:710,2\n474#1:715,2\n491#1:722,2\n546#1:724,2\n549#1:726,2\n559#1:728,2\n574#1:730,2\n268#1:663\n268#1:664,2\n276#1:666,7\n356#1:687,2\n380#1:691\n380#1:692,2\n384#1:694\n384#1:695,2\n392#1:697\n392#1:698,2\n429#1:704,2\n430#1:706,3\n429#1:709\n466#1:712\n466#1:713,2\n479#1:717\n479#1:718,2\n481#1:720,2\n597#1:732\n597#1:733,2\n604#1:735,2\n604#1:737,3\n604#1:740\n*E\n"})
/* loaded from: classes3.dex */
public final class PruningCamera2DeviceManager implements Camera2DeviceManager {
    private final Set<ActiveCamera> activeCameras;
    private final Camera2DeviceCloser camera2DeviceCloser;
    private final Camera2ErrorProcessor camera2ErrorProcessor;
    private final List<PendingRequestOpen> pendingRequestOpens;
    private final Permissions permissions;
    private final PruningProcessingQueue<CameraRequest> queue;
    private final RetryingCameraStateOpener retryingCameraStateOpener;
    private final CoroutineScope scope;
    private final Threads threads;

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\br\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager$OpenVirtualCameraResult;", _UrlKt.FRAGMENT_ENCODE_SET, "Success", "Error", "Landroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager$OpenVirtualCameraResult$Error;", "Landroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager$OpenVirtualCameraResult$Success;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface OpenVirtualCameraResult {

        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fHÖ\u0003¢\u0006\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager$OpenVirtualCameraResult$Error;", "Landroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager$OpenVirtualCameraResult;", "Landroidx/camera/camera2/pipe/CameraError;", "lastCameraError", "<init>", "(Landroidx/camera/camera2/pipe/CameraError;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/camera2/pipe/CameraError;", "getLastCameraError-mVEW8x0", "()Landroidx/camera/camera2/pipe/CameraError;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Error implements OpenVirtualCameraResult {
            private final CameraError lastCameraError;

            private Error(CameraError r1) {
                this.lastCameraError = r1;
            }

            public /* synthetic */ Error(CameraError r1, DefaultConstructorMarker r2) {
                this(r1);
            }

            public boolean equals(Object r4) {
                if (this != r4) goto L6;
                return true;
            L6:
                if ((r4 instanceof Error) == true) goto L9;
                return false;
            L9:
                if (Intrinsics.areEqual(this.lastCameraError, ((Error) r4).lastCameraError) == true) goto L11;
                return false;
            L11:
                return true;
            }

            /* renamed from: getLastCameraError-mVEW8x0, reason: not valid java name */
            public final CameraError m472getLastCameraErrormVEW8x0() {
                return this.lastCameraError;
            }

            public int hashCode() {
                CameraError r0 = this.lastCameraError;
                if (r0 != null) goto L7;
                return 0;
            L7:
                return CameraError.m179hashCodeimpl(r0.m182unboximpl());
            }

            public String toString() {
                return "Error(lastCameraError=" + this.lastCameraError + ')';
            }
        }

        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fHÖ\u0003¢\u0006\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager$OpenVirtualCameraResult$Success;", "Landroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager$OpenVirtualCameraResult;", "Landroidx/camera/camera2/pipe/compat/ActiveCamera;", "activeCamera", "<init>", "(Landroidx/camera/camera2/pipe/compat/ActiveCamera;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/camera2/pipe/compat/ActiveCamera;", "getActiveCamera", "()Landroidx/camera/camera2/pipe/compat/ActiveCamera;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Success implements OpenVirtualCameraResult {
            private final ActiveCamera activeCamera;

            public Success(ActiveCamera r1) {
                this.activeCamera = r1;
            }

            public boolean equals(Object r4) {
                if (this != r4) goto L6;
                return true;
            L6:
                if ((r4 instanceof Success) == true) goto L9;
                return false;
            L9:
                if (Intrinsics.areEqual(this.activeCamera, ((Success) r4).activeCamera) == true) goto L11;
                return false;
            L11:
                return true;
            }

            public final ActiveCamera getActiveCamera() {
                return this.activeCamera;
            }

            public int hashCode() {
                return this.activeCamera.hashCode();
            }

            public String toString() {
                return "Success(activeCamera=" + this.activeCamera + ')';
            }
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Landroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager$PendingRequestOpen;", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.REQUEST, "Landroidx/camera/camera2/pipe/compat/RequestOpen;", "activeCamera", "Landroidx/camera/camera2/pipe/compat/ActiveCamera;", "token", "Landroidx/camera/camera2/pipe/core/Token;", "<init>", "(Landroidx/camera/camera2/pipe/compat/RequestOpen;Landroidx/camera/camera2/pipe/compat/ActiveCamera;Landroidx/camera/camera2/pipe/core/Token;)V", "getRequest", "()Landroidx/camera/camera2/pipe/compat/RequestOpen;", "getActiveCamera", "()Landroidx/camera/camera2/pipe/compat/ActiveCamera;", "getToken", "()Landroidx/camera/camera2/pipe/core/Token;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class PendingRequestOpen {
        private final ActiveCamera activeCamera;
        private final RequestOpen request;
        private final Token token;

        public PendingRequestOpen(RequestOpen r1, ActiveCamera r2, Token r3) {
            this.request = r1;
            this.activeCamera = r2;
            this.token = r3;
        }

        public final ActiveCamera getActiveCamera() {
            return this.activeCamera;
        }

        public final RequestOpen getRequest() {
            return this.request;
        }

        public final Token getToken() {
            return this.token;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\br\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager$RetrieveActiveCameraResult;", _UrlKt.FRAGMENT_ENCODE_SET, "Success", "Error", "Landroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager$RetrieveActiveCameraResult$Error;", "Landroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager$RetrieveActiveCameraResult$Success;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface RetrieveActiveCameraResult {

        @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0086\b\u0018\u00002\u00020\u0001B\u0011\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0007\u001a\u00020\u0006HÖ\u0001¢\u0006\u0004\b\u0007\u0010\bJ\u0010\u0010\n\u001a\u00020\tHÖ\u0001¢\u0006\u0004\b\n\u0010\u000bJ\u001a\u0010\u000f\u001a\u00020\u000e2\b\u0010\r\u001a\u0004\u0018\u00010\fHÖ\u0003¢\u0006\u0004\b\u000f\u0010\u0010R\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Landroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager$RetrieveActiveCameraResult$Error;", "Landroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager$RetrieveActiveCameraResult;", "Landroidx/camera/camera2/pipe/CameraError;", "lastCameraError", "<init>", "(Landroidx/camera/camera2/pipe/CameraError;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/camera2/pipe/CameraError;", "getLastCameraError-mVEW8x0", "()Landroidx/camera/camera2/pipe/CameraError;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Error implements RetrieveActiveCameraResult {
            private final CameraError lastCameraError;

            private Error(CameraError r1) {
                this.lastCameraError = r1;
            }

            public /* synthetic */ Error(CameraError r1, DefaultConstructorMarker r2) {
                this(r1);
            }

            public boolean equals(Object r4) {
                if (this != r4) goto L6;
                return true;
            L6:
                if ((r4 instanceof Error) == true) goto L9;
                return false;
            L9:
                if (Intrinsics.areEqual(this.lastCameraError, ((Error) r4).lastCameraError) == true) goto L11;
                return false;
            L11:
                return true;
            }

            /* renamed from: getLastCameraError-mVEW8x0, reason: not valid java name */
            public final CameraError m473getLastCameraErrormVEW8x0() {
                return this.lastCameraError;
            }

            public int hashCode() {
                CameraError r0 = this.lastCameraError;
                if (r0 != null) goto L7;
                return 0;
            L7:
                return CameraError.m179hashCodeimpl(r0.m182unboximpl());
            }

            public String toString() {
                return "Error(lastCameraError=" + this.lastCameraError + ')';
            }
        }

        @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\t\u001a\u00020\bHÖ\u0001¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u001a\u0010\u0011\u001a\u00020\u00102\b\u0010\u000f\u001a\u0004\u0018\u00010\u000eHÖ\u0003¢\u0006\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0013\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0016\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Landroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager$RetrieveActiveCameraResult$Success;", "Landroidx/camera/camera2/pipe/compat/PruningCamera2DeviceManager$RetrieveActiveCameraResult;", "Landroidx/camera/camera2/pipe/compat/ActiveCamera;", "activeCamera", "Landroidx/camera/camera2/pipe/core/Token;", "token", "<init>", "(Landroidx/camera/camera2/pipe/compat/ActiveCamera;Landroidx/camera/camera2/pipe/core/Token;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/camera2/pipe/compat/ActiveCamera;", "getActiveCamera", "()Landroidx/camera/camera2/pipe/compat/ActiveCamera;", "Landroidx/camera/camera2/pipe/core/Token;", "getToken", "()Landroidx/camera/camera2/pipe/core/Token;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Success implements RetrieveActiveCameraResult {
            private final ActiveCamera activeCamera;
            private final Token token;

            public Success(ActiveCamera r1, Token r2) {
                this.activeCamera = r1;
                this.token = r2;
            }

            public boolean equals(Object r5) {
                if (this != r5) goto L6;
                return true;
            L6:
                if ((r5 instanceof Success) == true) goto L8;
                return false;
            L8:
                Success r52 = (Success) r5;
                if (Intrinsics.areEqual(this.activeCamera, r52.activeCamera) == true) goto L12;
                return false;
            L12:
                if (Intrinsics.areEqual(this.token, r52.token) == true) goto L14;
                return false;
            L14:
                return true;
            }

            public final ActiveCamera getActiveCamera() {
                return this.activeCamera;
            }

            public final Token getToken() {
                return this.token;
            }

            public int hashCode() {
                return (this.activeCamera.hashCode() * 31) + this.token.hashCode();
            }

            public String toString() {
                return "Success(activeCamera=" + this.activeCamera + ", token=" + this.token + ')';
            }
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.PruningCamera2DeviceManager", f = "Camera2DeviceManager.kt", i = {0}, l = {606}, m = "connectPendingRequestOpens", n = {"pendingRequestOpen"}, s = {"L$1"}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.compat.PruningCamera2DeviceManager$connectPendingRequestOpens$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ PruningCamera2DeviceManager this$0;

        public AnonymousClass1(PruningCamera2DeviceManager r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return PruningCamera2DeviceManager.access$connectPendingRequestOpens(this.this$0, null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.PruningCamera2DeviceManager", f = "Camera2DeviceManager.kt", i = {0}, l = {465, 469}, m = "processRequestClose", n = {PluginsConstants.REQUEST}, s = {"L$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.compat.PruningCamera2DeviceManager$processRequestClose$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00961 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ PruningCamera2DeviceManager this$0;

        public C00961(PruningCamera2DeviceManager r1, Continuation<? super C00961> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return PruningCamera2DeviceManager.access$processRequestClose(this.this$0, null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.PruningCamera2DeviceManager", f = "Camera2DeviceManager.kt", i = {0, 1}, l = {493, 498}, m = "processRequestCloseAll", n = {"requestCloseAll", "requestCloseAll"}, s = {"L$0", "L$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.compat.PruningCamera2DeviceManager$processRequestCloseAll$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00971 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ PruningCamera2DeviceManager this$0;

        public C00971(PruningCamera2DeviceManager r1, Continuation<? super C00971> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return PruningCamera2DeviceManager.access$processRequestCloseAll(this.this$0, null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.PruningCamera2DeviceManager", f = "Camera2DeviceManager.kt", i = {0, 0, 1}, l = {478, 485}, m = "processRequestCloseById", n = {PluginsConstants.REQUEST, "cameraId", PluginsConstants.REQUEST}, s = {"L$0", "L$1", "L$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.compat.PruningCamera2DeviceManager$processRequestCloseById$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00981 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ PruningCamera2DeviceManager this$0;

        public C00981(PruningCamera2DeviceManager r1, Continuation<? super C00981> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return PruningCamera2DeviceManager.access$processRequestCloseById(this.this$0, null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.PruningCamera2DeviceManager", f = "Camera2DeviceManager.kt", i = {0, 0, 0, 1, 1, 2, 2, 3}, l = {391, 398, 404, 436, 437, 445}, m = "processRequestOpen", n = {PluginsConstants.REQUEST, "cameraIdToOpen", "camerasToClose", PluginsConstants.REQUEST, "cameraIdToOpen", PluginsConstants.REQUEST, "cameraIdToOpen", PluginsConstants.REQUEST}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$0", "L$1", "L$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.compat.PruningCamera2DeviceManager$processRequestOpen$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00991 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ PruningCamera2DeviceManager this$0;

        public C00991(PruningCamera2DeviceManager r1, Continuation<? super C00991> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return PruningCamera2DeviceManager.access$processRequestOpen(this.this$0, null, this);
        }
    }

    /* renamed from: $r8$lambda$-mG8_AgNMPKBsIwSXjNb-j0kE9U, reason: not valid java name */
    public static Unit m466$r8$lambda$mG8_AgNMPKBsIwSXjNbj0kE9U(CameraRequest r0, Throwable r1) {
        CompletableDeferred<Unit> r02 = ((RequestCloseById) r0).getDeferred();
        Unit r12 = Unit.INSTANCE;
        r02.complete(r12);
        return r12;
    }

    /* renamed from: $r8$lambda$ZIVIARpnftt9kWIyXK-VcZYQevM, reason: not valid java name */
    public static /* synthetic */ Unit m467$r8$lambda$ZIVIARpnftt9kWIyXKVcZYQevM(CompletableDeferred r0, Throwable r1) {
        return prune$lambda$2$0(r0, r1);
    }

    public static Unit $r8$lambda$vP9kzpFdONuWMUT974T0jXAWwcs(PruningCamera2DeviceManager r1, ActiveCamera r2) {
        r1.queue.tryEmit(new RequestClose(r2));
        return Unit.INSTANCE;
    }

    public PruningCamera2DeviceManager(Permissions r8, RetryingCameraStateOpener r9, Camera2DeviceCloser r10, Camera2ErrorProcessor r11, Threads r12) {
        this.permissions = r8;
        this.retryingCameraStateOpener = r9;
        this.camera2DeviceCloser = r10;
        this.camera2ErrorProcessor = r11;
        this.threads = r12;
        CoroutineScope r82 = r12.getCameraPipeScope();
        this.scope = r82;
        this.queue = PruningProcessingQueue.Companion.processIn(new PruningProcessingQueue(0, new PruningCamera2DeviceManager$queue$1(this), null, new PruningCamera2DeviceManager$queue$2(this, null), 5, null), r82);
        this.activeCameras = new LinkedHashSet();
        this.pendingRequestOpens = new ArrayList();
    }

    public static final /* synthetic */ Object access$connectPendingRequestOpens(PruningCamera2DeviceManager r0, Set r1, Continuation r2) {
        return r0.connectPendingRequestOpens(r1, r2);
    }

    /* renamed from: access$openCameraWithRetry-zDSwpeU, reason: not valid java name */
    public static final /* synthetic */ Object m468access$openCameraWithRetryzDSwpeU(PruningCamera2DeviceManager r0, String r1, List r2, Function1 r3, CoroutineScope r4, Continuation r5) {
        return r0.m470openCameraWithRetryzDSwpeU(r1, r2, r3, r4, r5);
    }

    public static final /* synthetic */ Object access$process(PruningCamera2DeviceManager r0, CameraRequest r1, Continuation r2) {
        return r0.process(r1, r2);
    }

    public static final /* synthetic */ Object access$processRequestClose(PruningCamera2DeviceManager r0, RequestClose r1, Continuation r2) {
        return r0.processRequestClose(r1, r2);
    }

    public static final /* synthetic */ Object access$processRequestCloseAll(PruningCamera2DeviceManager r0, RequestCloseAll r1, Continuation r2) {
        return r0.processRequestCloseAll(r1, r2);
    }

    public static final /* synthetic */ Object access$processRequestCloseById(PruningCamera2DeviceManager r0, RequestCloseById r1, Continuation r2) {
        return r0.processRequestCloseById(r1, r2);
    }

    public static final /* synthetic */ Object access$processRequestOpen(PruningCamera2DeviceManager r0, RequestOpen r1, Continuation r2) {
        return r0.processRequestOpen(r1, r2);
    }

    /* renamed from: access$retrieveActiveCamera-RzXb1QE, reason: not valid java name */
    public static final /* synthetic */ Object m469access$retrieveActiveCameraRzXb1QE(PruningCamera2DeviceManager r0, String r1, RequestOpen r2, Continuation r3) {
        return r0.m471retrieveActiveCameraRzXb1QE(r1, r2, r3);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:44:0x0106 -> B:46:0x0109). Please report as a decompilation issue!!! */
    private final Object connectPendingRequestOpens(Set<CameraId> r10, Continuation<? super Unit> r11) {
        if ((r11 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r0 = (AnonymousClass1) r11;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r112 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        PendingRequestOpen r102 = (PendingRequestOpen) r0.L$1;
        Iterator r22 = (Iterator) r0.L$0;
        ResultKt.throwOnFailure(r112);
    L46:
        this.pendingRequestOpens.remove(r102);
    L22:
        if (r22.hasNext() == false) goto L48;
        r102 = (PendingRequestOpen) r22.next();
        RequestOpen r113 = r102.getRequest();
        List r5 = CollectionsKt.plus(CollectionsKt.listOf(CameraId.m227boximpl(r113.getVirtualCamera().m487getCameraIdDz_R5H8())), r113.getSharedCameraIds());
        if ((r5 instanceof Collection) == true) goto L26;
    L28:
        Iterator r52 = r5.iterator();
    L30:
        if (r52.hasNext() == false) goto L43;
        String r6 = ((CameraId) r52.next()).m233unboximpl();
        Set<ActiveCamera> r7 = this.activeCameras;
        if (r7 != null) goto L34;
    L35:
        Iterator<T> r72 = r7.iterator();
    L37:
        if (r72.hasNext() == false) goto L41;
        if (CameraId.m230equalsimpl0(((ActiveCamera) r72.next()).m409getCameraIdDz_R5H8(), r6) == false) goto L37;
    L41:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return null;
    L34:
        if (r7.isEmpty() == true) goto L41;
    L43:
        ActiveCamera r53 = r102.getActiveCamera();
        VirtualCameraState r114 = r113.getVirtualCamera();
        Token r62 = r102.getToken();
        r0.L$0 = r22;
        r0.L$1 = r102;
        r0.label = 1;
        if (r53.connectTo(r114, r62, r0) != r12) goto L46;
        return r12;
    L26:
        if (r5.isEmpty() == false) goto L28;
    L48:
        return Unit.INSTANCE;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r112);
        List<PendingRequestOpen> r115 = this.pendingRequestOpens;
        ArrayList r23 = new ArrayList();
        Iterator<T> r116 = r115.iterator();
    L16:
        if (r116.hasNext() == false) goto L20;
        Object r54 = r116.next();
        if (r10.contains(CameraId.m227boximpl(((PendingRequestOpen) r54).getRequest().getVirtualCamera().m487getCameraIdDz_R5H8())) == false) goto L16;
        r23.add(r54);
        goto L16
    L20:
        r22 = r23.iterator();
    L7:
        r0 = new AnonymousClass1(this, r11);
        goto L8
    }

    private final Object disconnectPendingRequestOpens(List<PendingRequestOpen> r2, Continuation<? super Unit> r3) {
        Iterator<PendingRequestOpen> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L7;
        PendingRequestOpen r32 = r22.next();
        r32.getToken().release();
        this.pendingRequestOpens.remove(r32);
        goto L4
    L7:
        return Unit.INSTANCE;
    }

    private final void onRemoved(CameraRequest r2) {
        if ((r2 instanceof RequestOpen) == false) goto L6;
        VirtualCamera.m485disconnectTPqeGZw$default(((RequestOpen) r2).getVirtualCamera(), null, 1, null);
        return;
    }

    /* renamed from: openCameraWithRetry-zDSwpeU, reason: not valid java name */
    private final Object m470openCameraWithRetryzDSwpeU(String r6, List<CameraId> r7, Function1<? super Unit, Boolean> r8, CoroutineScope r9, Continuation<? super OpenVirtualCameraResult> r10) {
        if ((r10 instanceof PruningCamera2DeviceManager$openCameraWithRetry$1) == false) goto L7;
        PruningCamera2DeviceManager$openCameraWithRetry$1 r0 = (PruningCamera2DeviceManager$openCameraWithRetry$1) r10;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r102 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        r9 = (CoroutineScope) r0.L$2;
        r7 = (List) r0.L$1;
        r6 = (String) r0.L$0;
        ResultKt.throwOnFailure(r102);
    L20:
        OpenCameraResult r103 = (OpenCameraResult) r102;
        if (r103.getCameraState() != null) goto L25;
        return new OpenVirtualCameraResult.Error(r103.m465getErrorCodemVEW8x0(), null);
    L25:
        return new OpenVirtualCameraResult.Success(new ActiveCamera(r103.getCameraState(), CollectionsKt.toSet(CollectionsKt.plus(r7, CameraId.m227boximpl(r6))), r9, new PruningCamera2DeviceManager$$ExternalSyntheticLambda2(this)));
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r102);
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L17;
        android.util.Log.d("CXCP", "Opening " + CameraId.m232toStringimpl(r6) + " with retries...");
    L17:
        RetryingCameraStateOpener r104 = this.retryingCameraStateOpener;
        Camera2DeviceCloser r22 = this.camera2DeviceCloser;
        r0.L$0 = r6;
        r0.L$1 = r7;
        r0.L$2 = r9;
        r0.label = 1;
        r102 = r104.mo478openCameraWithRetryaeCOTgg(r6, r22, r8, r0);
        if (r102 != r12) goto L20;
        return r12;
    L7:
        r0 = new PruningCamera2DeviceManager$openCameraWithRetry$1(this, r10);
        goto L8
    }

    private final Object process(CameraRequest r2, Continuation<? super Unit> r3) {
        if ((r2 instanceof RequestOpen) == false) goto L10;
        Object r1 = processRequestOpen((RequestOpen) r2, r3);
        if (r1 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L8;
        return r1;
    L8:
        return Unit.INSTANCE;
    L10:
        if ((r2 instanceof RequestClose) == false) goto L17;
        Object r12 = processRequestClose((RequestClose) r2, r3);
        if (r12 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L15;
        return r12;
    L15:
        return Unit.INSTANCE;
    L17:
        if ((r2 instanceof RequestCloseById) == false) goto L24;
        Object r13 = processRequestCloseById((RequestCloseById) r2, r3);
        if (r13 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L22;
        return r13;
    L22:
        return Unit.INSTANCE;
    L24:
        if ((r2 instanceof RequestCloseAll) == false) goto L30;
        Object r14 = processRequestCloseAll((RequestCloseAll) r2, r3);
        if (r14 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L29;
        return r14;
    L29:
        return Unit.INSTANCE;
    L30:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    }

    private final Object processRequestClose(RequestClose r10, Continuation<? super Unit> r11) {
        if ((r11 instanceof C00961) == false) goto L7;
        C00961 r0 = (C00961) r11;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r112 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L16;
        if (r2 == 1) goto L15;
        if (r2 != 2) goto L13;
        ResultKt.throwOnFailure(r112);
    L35:
        return Unit.INSTANCE;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        r10 = (RequestClose) r0.L$0;
        ResultKt.throwOnFailure(r112);
    L31:
        r10.getActiveCamera().close();
        ActiveCamera r9 = r10.getActiveCamera();
        r0.L$0 = null;
        r0.label = 2;
        if (r9.awaitClosed(r0) != r12) goto L35;
    L33:
        return r12;
    L16:
        ResultKt.throwOnFailure(r112);
        String r113 = r10.getActiveCamera().m409getCameraIdDz_R5H8();
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L20;
        android.util.Log.i("CXCP", "PruningCamera2DeviceManager#processRequestClose(" + CameraId.m232toStringimpl(r113) + ')');
    L20:
        if (this.activeCameras.contains(r10.getActiveCamera()) == false) goto L22;
        this.activeCameras.remove(r10.getActiveCamera());
    L22:
        List<PendingRequestOpen> r114 = this.pendingRequestOpens;
        ArrayList r22 = new ArrayList();
        Iterator<T> r115 = r114.iterator();
    L24:
        if (r115.hasNext() == false) goto L28;
        Object r6 = r115.next();
        if (Intrinsics.areEqual(((PendingRequestOpen) r6).getActiveCamera(), r10.getActiveCamera()) == false) goto L24;
        r22.add(r6);
        goto L24
    L28:
        r0.L$0 = r10;
        r0.label = 1;
        if (disconnectPendingRequestOpens(r22, r0) != r12) goto L31;
    L7:
        r0 = new C00961(this, r11);
        goto L8
    }

    private final Object processRequestCloseAll(RequestCloseAll r6, Continuation<? super Unit> r7) {
        if ((r7 instanceof C00971) == false) goto L7;
        C00971 r0 = (C00971) r7;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r72 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L16;
        if (r2 == 1) goto L15;
        if (r2 != 2) goto L13;
        Iterator<ActiveCamera> r62 = (Iterator) r0.L$1;
        RequestCloseAll r22 = (RequestCloseAll) r0.L$0;
        ResultKt.throwOnFailure(r72);
    L28:
        if (r62.hasNext() == false) goto L32;
        ActiveCamera r73 = r62.next();
        r0.L$0 = r22;
        r0.L$1 = r62;
        r0.label = 2;
        if (r73.awaitClosed(r0) != r12) goto L28;
    L31:
        return r12;
    L32:
        this.activeCameras.clear();
        CompletableDeferred<Unit> r5 = r22.getDeferred();
        Unit r63 = Unit.INSTANCE;
        r5.complete(r63);
        return r63;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        r6 = (RequestCloseAll) r0.L$0;
        ResultKt.throwOnFailure(r72);
    L22:
        Iterator<ActiveCamera> r74 = this.activeCameras.iterator();
    L24:
        if (r74.hasNext() == false) goto L26;
        r74.next().close();
        goto L24
    L26:
        r22 = r6;
        r62 = this.activeCameras.iterator();
        goto L28
    L16:
        ResultKt.throwOnFailure(r72);
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L19;
        android.util.Log.i("CXCP", "PruningCamera2DeviceManager#processRequestCloseAll()");
    L19:
        List<PendingRequestOpen> r75 = this.pendingRequestOpens;
        r0.L$0 = r6;
        r0.label = 1;
        if (disconnectPendingRequestOpens(r75, r0) != r12) goto L22;
    L7:
        r0 = new C00971(this, r7);
        goto L8
    }

    private final Object processRequestCloseById(RequestCloseById r10, Continuation<? super Unit> r11) {
        if ((r11 instanceof C00981) == false) goto L7;
        C00981 r0 = (C00981) r11;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r112 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L16;
        if (r2 == 1) goto L15;
        if (r2 != 2) goto L13;
        RequestCloseById r9 = (RequestCloseById) r0.L$0;
        ResultKt.throwOnFailure(r112);
    L42:
        RequestCloseById r22 = r9;
    L43:
        CompletableDeferred<Unit> r92 = r22.getDeferred();
        Unit r102 = Unit.INSTANCE;
        r92.complete(r102);
        return r102;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        String r103 = (String) r0.L$1;
        r22 = (RequestCloseById) r0.L$0;
        ResultKt.throwOnFailure(r112);
    L29:
        Iterator<T> r113 = this.activeCameras.iterator();
    L31:
        if (r113.hasNext() == false) goto L35;
        Object r5 = r113.next();
        if (CameraId.m230equalsimpl0(((ActiveCamera) r5).m409getCameraIdDz_R5H8(), r103) == false) goto L31;
    L36:
        ActiveCamera r52 = (ActiveCamera) r5;
        if (r52 == null) goto L43;
        this.activeCameras.remove(r52);
        r52.close();
        r0.L$0 = r22;
        r0.L$1 = null;
        r0.label = 2;
        if (r52.awaitClosed(r0) != r12) goto L41;
    L40:
        return r12;
    L41:
        r9 = r22;
        goto L42
    L35:
        r5 = null;
        goto L36
    L16:
        ResultKt.throwOnFailure(r112);
        String r114 = r10.m474getActiveCameraIdDz_R5H8();
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L19;
        android.util.Log.i("CXCP", "PruningCamera2DeviceManager#processRequestCloseById(" + CameraId.m232toStringimpl(r10.m474getActiveCameraIdDz_R5H8()) + ')');
    L19:
        List<PendingRequestOpen> r23 = this.pendingRequestOpens;
        ArrayList r6 = new ArrayList();
        Iterator<T> r24 = r23.iterator();
    L21:
        if (r24.hasNext() == false) goto L25;
        Object r7 = r24.next();
        if (CameraId.m230equalsimpl0(((PendingRequestOpen) r7).getRequest().getVirtualCamera().m487getCameraIdDz_R5H8(), r114) == false) goto L21;
        r6.add(r7);
        goto L21
    L25:
        r0.L$0 = r10;
        r0.L$1 = r114;
        r0.label = 1;
        if (disconnectPendingRequestOpens(r6, r0) == r12) goto L40;
        r22 = r10;
        r103 = r114;
    L7:
        r0 = new C00981(this, r11);
        goto L8
    }

    private final Object processRequestOpen(RequestOpen r11, Continuation<? super Unit> r12) {
        if ((r12 instanceof C00991) == false) goto L7;
        C00991 r0 = (C00991) r12;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r122 = r0.result;
        Object r13 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(r0.label) {
            case 0: goto L18;
            case 1: goto L17;
            case 2: goto L16;
            case 3: goto L15;
            case 4: goto L14;
            case 5: goto L13;
            case 6: goto L12;
            default: goto L10;
        };
    L10:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L12:
        ResultKt.throwOnFailure(r122);
    L119:
        return Unit.INSTANCE;
    L13:
        ResultKt.throwOnFailure(r122);
    L110:
        return Unit.INSTANCE;
    L14:
        RequestOpen r112 = (RequestOpen) r0.L$0;
        ResultKt.throwOnFailure(r122);
    L106:
        Set<CameraId> r113 = CollectionsKt.toSet(r112.getSharedCameraIds());
        r0.L$0 = null;
        r0.label = 5;
        if (connectPendingRequestOpens(r113, r0) != r13) goto L110;
    L117:
        return r13;
    L15:
        String r114 = (String) r0.L$1;
        RequestOpen r2 = (RequestOpen) r0.L$0;
        ResultKt.throwOnFailure(r122);
        String r123 = r114;
        r112 = r2;
        C00991 r22 = r0;
        Object r02 = r122;
    L64:
        RetrieveActiveCameraResult r03 = (RetrieveActiveCameraResult) r02;
        if ((r03 instanceof RetrieveActiveCameraResult.Error) == false) goto L77;
        RetrieveActiveCameraResult.Error r04 = (RetrieveActiveCameraResult.Error) r03;
        if (r04.m473getLastCameraErrormVEW8x0() == null) goto L72;
        if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L75;
        android.util.Log.e("CXCP", "Failed to retrieve active camera for " + CameraId.m232toStringimpl(r123) + ". Last camera error was " + CameraError.m181toStringimpl(r04.m473getLastCameraErrormVEW8x0().m182unboximpl()));
    L75:
        return Unit.INSTANCE;
    L72:
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L75;
        android.util.Log.w("CXCP", "Failed to retrieve active camera for " + CameraId.m232toStringimpl(r123) + ". Camera might have been closed during opening.");
        goto L75
    L77:
        if ((r03 instanceof RetrieveActiveCameraResult.Success) == false) goto L123;
        RetrieveActiveCameraResult.Success r05 = (RetrieveActiveCameraResult.Success) r03;
        ActiveCamera r124 = r05.getActiveCamera();
        Token r06 = r05.getToken();
        if (r112.getSharedCameraIds().isEmpty() == true) goto L114;
        List<CameraId> r5 = r112.getSharedCameraIds();
        if ((r5 instanceof Collection) == true) goto L83;
    L85:
        Iterator<T> r52 = r5.iterator();
    L87:
        if (r52.hasNext() == false) goto L101;
        String r6 = ((CameraId) r52.next()).m233unboximpl();
        List<PendingRequestOpen> r7 = this.pendingRequestOpens;
        if ((r7 instanceof Collection) == true) goto L91;
    L93:
        Iterator<T> r72 = r7.iterator();
    L95:
        if (r72.hasNext() == false) goto L99;
        if (CameraId.m230equalsimpl0(((PendingRequestOpen) r72.next()).getActiveCamera().m409getCameraIdDz_R5H8(), r6) == false) goto L95;
    L99:
        this.pendingRequestOpens.add(new PendingRequestOpen(r112, r124, r06));
    L122:
        return Unit.INSTANCE;
    L91:
        if (r7.isEmpty() == false) goto L93;
    L101:
        if (r112.isPrewarm() == true) goto L111;
        VirtualCameraState r3 = r112.getVirtualCamera();
        r22.L$0 = r112;
        r22.L$1 = null;
        r22.label = 4;
        if (r124.connectTo(r3, r06, r22) == r13) goto L117;
        r0 = r22;
        goto L106
    L111:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return null;
    L83:
        if (r5.isEmpty() == false) goto L85;
    L114:
        if (r112.isPrewarm() == true) goto L120;
        VirtualCameraState r10 = r112.getVirtualCamera();
        r22.L$0 = null;
        r22.L$1 = null;
        r22.label = 6;
        if (r124.connectTo(r10, r06, r22) != r13) goto L119;
    L120:
        r06.release();
        goto L122
    L123:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return null;
    L16:
        Iterator r115 = (Iterator) r0.L$2;
        String r23 = (String) r0.L$1;
        RequestOpen r53 = (RequestOpen) r0.L$0;
        ResultKt.throwOnFailure(r122);
    L54:
        if (r115.hasNext() == false) goto L58;
        ActiveCamera r125 = (ActiveCamera) r115.next();
        r0.L$0 = r53;
        r0.L$1 = r23;
        r0.L$2 = r115;
        r0.label = 2;
        if (r125.awaitClosed(r0) != r13) goto L54;
    L58:
        String r116 = r23;
        this.camera2ErrorProcessor.m448setActiveVirtualCamera0r8Bogc$camera_camera2_pipe(r116, r53.getVirtualCamera());
        r0.L$0 = r53;
        r0.L$1 = r116;
        r0.L$2 = null;
        r0.label = 3;
        Object r126 = m471retrieveActiveCameraRzXb1QE(r116, r53, r0);
        if (r126 == r13) goto L117;
        r22 = r0;
        r02 = r126;
        r123 = r116;
        r112 = r53;
        goto L64
    L17:
        List r117 = (List) r0.L$2;
        r23 = (String) r0.L$1;
        r53 = (RequestOpen) r0.L$0;
        ResultKt.throwOnFailure(r122);
    L48:
        Iterator r127 = r117.iterator();
    L50:
        if (r127.hasNext() == false) goto L52;
        ((ActiveCamera) r127.next()).close();
        goto L50
    L52:
        r115 = r117.iterator();
        goto L54
    L18:
        ResultKt.throwOnFailure(r122);
        r23 = r11.getVirtualCamera().m487getCameraIdDz_R5H8();
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L22;
        android.util.Log.i("CXCP", "PruningCamera2DeviceManager#processRequestOpen(" + CameraId.m232toStringimpl(r23) + ')');
    L22:
        if (r11.getSharedCameraIds().isEmpty() == false) goto L29;
        Set<ActiveCamera> r128 = this.activeCameras;
        ArrayList r54 = new ArrayList();
        Iterator<T> r129 = r128.iterator();
    L25:
        if (r129.hasNext() == false) goto L37;
        Object r62 = r129.next();
        if (CameraId.m230equalsimpl0(((ActiveCamera) r62).m409getCameraIdDz_R5H8(), r23) == true) goto L25;
        r54.add(r62);
    L37:
        if (r54.isEmpty() == true) goto L59;
        this.activeCameras.removeAll(r54);
        List<PendingRequestOpen> r1210 = this.pendingRequestOpens;
        ArrayList r63 = new ArrayList();
        Iterator<T> r1211 = r1210.iterator();
    L40:
        if (r1211.hasNext() == false) goto L44;
        Object r73 = r1211.next();
        if (r54.contains(((PendingRequestOpen) r73).getActiveCamera()) == false) goto L40;
        r63.add(r73);
        goto L40
    L44:
        r0.L$0 = r11;
        r0.L$1 = r23;
        r0.L$2 = r54;
        r0.label = 1;
        if (disconnectPendingRequestOpens(r63, r0) == r13) goto L117;
        ArrayList r9 = r54;
        r53 = r11;
        r117 = r9;
        goto L48
    L59:
        r53 = r11;
        goto L58
    L29:
        Set r1212 = CollectionsKt.toSet(CollectionsKt.plus(r11.getSharedCameraIds(), CameraId.m227boximpl(r11.getVirtualCamera().m487getCameraIdDz_R5H8())));
        Set<ActiveCamera> r55 = this.activeCameras;
        ArrayList r64 = new ArrayList();
        Iterator<T> r56 = r55.iterator();
    L31:
        if (r56.hasNext() == false) goto L35;
        Object r74 = r56.next();
        if (Intrinsics.areEqual(((ActiveCamera) r74).getAllCameraIds$camera_camera2_pipe(), r1212) == true) goto L31;
        r64.add(r74);
        goto L31
    L35:
        r54 = r64;
    L7:
        r0 = new C00991(this, r12);
        goto L8
    }

    private static final Unit prune$lambda$2$0(CompletableDeferred r0, Throwable r1) {
        Unit r12 = Unit.INSTANCE;
        r0.complete(r12);
        return r12;
    }

    private final <T> List<T> removeIndices(List<T> r3, Set<Integer> r4) {
        ArrayList r2 = new ArrayList();
        Iterator r42 = CollectionsKt.sorted(r4).iterator();
    L4:
        if (r42.hasNext() == false) goto L6;
        r2.add(r3.remove(((Number) r42.next()).intValue() - r2.size()));
        goto L4
    L6:
        return r2;
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:27:0x0091 -> B:28:0x0093). Please report as a decompilation issue!!! */
    /* renamed from: retrieveActiveCamera-RzXb1QE, reason: not valid java name */
    private final Object m471retrieveActiveCameraRzXb1QE(String r13, RequestOpen r14, Continuation<? super RetrieveActiveCameraResult> r15) {
        if ((r15 instanceof PruningCamera2DeviceManager$retrieveActiveCamera$1) == false) goto L7;
        PruningCamera2DeviceManager$retrieveActiveCamera$1 r0 = (PruningCamera2DeviceManager$retrieveActiveCamera$1) r15;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r152 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L16;
        if (r2 == 1) goto L15;
        if (r2 != 2) goto L13;
        RequestOpen r132 = (RequestOpen) r0.L$1;
        String r142 = (String) r0.L$0;
        ResultKt.throwOnFailure(r152);
        PruningCamera2DeviceManager r6 = this;
    L35:
        OpenVirtualCameraResult r153 = (OpenVirtualCameraResult) r152;
        if ((r153 instanceof OpenVirtualCameraResult.Success) == false) goto L49;
        ActiveCamera r154 = ((OpenVirtualCameraResult.Success) r153).getActiveCamera();
        Token r122 = r154.acquire();
        if (r122 == null) goto L44;
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L42;
        android.util.Log.i("CXCP", "PruningCameraDeviceManager: " + CameraId.m232toStringimpl(r142) + " opened successfully");
    L42:
        r6.activeCameras.add(r154);
        Token r02 = r122;
    L58:
        if (r02 != null) goto L60;
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L60:
        return new RetrieveActiveCameraResult.Success(r154, r02);
    L44:
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L46;
        android.util.Log.i("CXCP", "PruningCameraDeviceManager: Failed to open " + CameraId.m232toStringimpl(r142) + ": Camera may have been closed (possibly due to an error) immediately after opening");
    L46:
        r132.getVirtualCamera().mo486disconnectTPqeGZw(null);
        return new RetrieveActiveCameraResult.Error(null, null);
    L49:
        if ((r153 instanceof OpenVirtualCameraResult.Error) == true) goto L51;
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L51:
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L53;
        android.util.Log.i("CXCP", "PruningCameraDeviceManager: Failed to open " + CameraId.m232toStringimpl(r142));
    L53:
        OpenVirtualCameraResult.Error r155 = (OpenVirtualCameraResult.Error) r153;
        r132.getVirtualCamera().mo486disconnectTPqeGZw(r155.m472getLastCameraErrormVEW8x0());
        return new RetrieveActiveCameraResult.Error(r155.m472getLastCameraErrormVEW8x0(), null);
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ActiveCamera r133 = (ActiveCamera) r0.L$3;
        Iterator<ActiveCamera> r143 = (Iterator) r0.L$2;
        RequestOpen r22 = (RequestOpen) r0.L$1;
        String r62 = (String) r0.L$0;
        ResultKt.throwOnFailure(r152);
        PruningCamera2DeviceManager$retrieveActiveCamera$1 r11 = r0;
        String r7 = r62;
    L28:
        this.activeCameras.remove(r133);
        r132 = r22;
    L18:
        if (r143.hasNext() == false) goto L29;
        r154 = r143.next();
        if (CameraId.m230equalsimpl0(r154.m409getCameraIdDz_R5H8(), r7) == false) goto L18;
        r02 = r154.acquire();
        if (r02 != null) goto L30;
        r154.close();
        r11.L$0 = r7;
        r11.L$1 = r132;
        r11.L$2 = r143;
        r11.L$3 = r154;
        r11.label = 1;
        if (r154.awaitClosed(r11) == r12) goto L33;
        r22 = r132;
        r133 = r154;
    L33:
        return r12;
    L30:
        if (r154 != null) goto L58;
        List<CameraId> r8 = r132.getSharedCameraIds();
        Function1<Unit, Boolean> r9 = r132.isForegroundObserver();
        CoroutineScope r10 = this.scope;
        r11.L$0 = r7;
        r11.L$1 = r132;
        r11.L$2 = null;
        r11.L$3 = null;
        r11.label = 2;
        r6 = this;
        r152 = r6.m470openCameraWithRetryzDSwpeU(r7, r8, r9, r10, r11);
        if (r152 == r12) goto L33;
        r142 = r7;
        goto L35
    L29:
        r154 = null;
        r02 = null;
        goto L30
    L16:
        ResultKt.throwOnFailure(r152);
        r7 = r13;
        r132 = r14;
        r143 = this.activeCameras.iterator();
        r11 = r0;
    L7:
        r0 = new PruningCamera2DeviceManager$retrieveActiveCamera$1(this, r15);
        goto L8
    }

    @Override // androidx.camera.camera2.pipe.compat.Camera2DeviceManager
    /* renamed from: close-EfqyGwQ */
    public Deferred<Unit> mo445closeEfqyGwQ(String r3) {
        RequestCloseById r0 = new RequestCloseById(r3, null);
        if (this.queue.tryEmit(r0) == true) goto L9;
        if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L7;
        android.util.Log.e("CXCP", "Camera close by ID request failed for " + CameraId.m232toStringimpl(r3) + '!');
    L7:
        r0.getDeferred().complete(Unit.INSTANCE);
    L9:
        return r0.getDeferred();
    }

    @Override // androidx.camera.camera2.pipe.compat.Camera2DeviceManager
    public Deferred<Unit> closeAll(boolean r2) {
        if (r2 == false) goto L4;
        this.retryingCameraStateOpener.cancelOpen();
    L4:
        RequestCloseAll r22 = new RequestCloseAll();
        if (this.queue.tryEmit(r22) == true) goto L11;
        if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L9;
        android.util.Log.e("CXCP", "Camera close all request failed!");
    L9:
        r22.getDeferred().complete(Unit.INSTANCE);
    L11:
        return r22.getDeferred();
    }

    @Override // androidx.camera.camera2.pipe.compat.Camera2DeviceManager
    /* renamed from: open-zDSwpeU */
    public VirtualCamera mo446openzDSwpeU(String r8, List<CameraId> r9, GraphListener r10, boolean r11, Function1<? super Unit, Boolean> r12) {
        VirtualCameraState r1 = new VirtualCameraState(r8, r10, this.scope, null);
        if (this.queue.tryEmit(new RequestOpen(r1, r9, r10, r11, r12)) == false) goto L5;
        return r1;
    L5:
        if (Log.INSTANCE.getERROR_LOGGABLE() == false) goto L7;
        android.util.Log.e("CXCP", "Camera open request failed for " + CameraId.m232toStringimpl(r8) + '!');
    L7:
        r10.onGraphError(new GraphState.GraphStateError(CameraError.Companion.m191getERROR_CAMERA_OPENERv7Vf74A(), false, null));
        return null;
    }

    public final void prune$camera_camera2_pipe(List<CameraRequest> r18) {
        ArrayList r3 = new ArrayList();
        Iterator<T> r2 = r18.iterator();
    L4:
        if (r2.hasNext() == false) goto L8;
        Object r4 = r2.next();
        if ((((CameraRequest) r4) instanceof RequestClose) == false) goto L4;
        r3.add(r4);
        goto L4
    L8:
        r18.removeAll(r3);
        Iterator r22 = CollectionsKt.reversed(r3).iterator();
    L9:
        boolean r42 = false;
        if (r22.hasNext() == false) goto L12;
        r18.add(0, (CameraRequest) r22.next());
        goto L9
    L12:
        ListIterator<CameraRequest> r23 = r18.listIterator(r18.size());
    L14:
        if (r23.hasPrevious() == false) goto L18;
        if ((r23.previous() instanceof RequestCloseAll) == false) goto L14;
        int r24 = r23.nextIndex();
    L19:
        if (r24 <= 0) goto L32;
        RequestCloseAll r5 = (RequestCloseAll) r18.get(r24);
        int r6 = 0;
    L21:
        if (r6 >= r24) goto L32;
        CameraRequest r7 = r18.remove(0);
        if ((r7 instanceof RequestCloseById) == false) goto L26;
        CompletableDeferred<Unit> r8 = ((RequestCloseById) r7).getDeferred();
    L29:
        if (r8 == null) goto L31;
        r5.getDeferred().invokeOnCompletion(new PruningCamera2DeviceManager$$ExternalSyntheticLambda0(r8));
    L31:
        onRemoved(r7);
        r6 = r6 + 1;
        goto L21
    L26:
        if ((r7 instanceof RequestCloseAll) == false) goto L28;
        r8 = ((RequestCloseAll) r7).getDeferred();
        goto L29
    L28:
        r8 = null;
    L32:
        LinkedHashSet r25 = new LinkedHashSet();
        Iterator<T> r52 = r18.iterator();
        int r62 = 0;
    L34:
        if (r52.hasNext() == false) goto L86;
        int r72 = r62 + 1;
        CameraRequest r82 = (CameraRequest) r52.next();
        if ((r82 instanceof RequestOpen) == false) goto L64;
        RequestOpen r9 = (RequestOpen) r82;
        String r11 = r9.getVirtualCamera().m487getCameraIdDz_R5H8();
        Set r12 = CollectionsKt.toSet(CollectionsKt.plus(r9.getSharedCameraIds(), CameraId.m227boximpl(r11)));
        int r13 = r18.size();
        int r14 = r72;
    L38:
        if (r14 >= r13) goto L62;
        CameraRequest r15 = r18.get(r14);
        if ((r15 instanceof RequestCloseById) == false) goto L43;
        boolean r32 = r12.contains(CameraId.m227boximpl(((RequestCloseById) r15).m474getActiveCameraIdDz_R5H8()));
    L59:
        if (r32 == true) goto L60;
        r14 = r14 + 1;
        r42 = false;
        goto L38
    L60:
        Integer r33 = Integer.valueOf(r14);
    L76:
        if (r33 == null) goto L85;
        CameraRequest r34 = r18.get(r33.intValue());
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L80;
        android.util.Log.d("CXCP", r82 + " is pruned by " + r34);
    L80:
        r25.add(Integer.valueOf(r62));
        if ((r82 instanceof RequestCloseById) == false) goto L85;
        if ((r34 instanceof RequestCloseById) == false) goto L85;
        ((RequestCloseById) r34).getDeferred().invokeOnCompletion(new PruningCamera2DeviceManager$$ExternalSyntheticLambda1(r82));
    L85:
        r62 = r72;
        r42 = false;
        goto L34
    L43:
        if ((r15 instanceof RequestOpen) == true) goto L45;
    L58:
        r32 = false;
        goto L59
    L45:
        if (r9.isPrewarm() == false) goto L47;
    L50:
        boolean r35 = true;
    L51:
        RequestOpen r152 = (RequestOpen) r15;
        String r43 = r152.getVirtualCamera().m487getCameraIdDz_R5H8();
        Set r10 = CollectionsKt.toSet(CollectionsKt.plus(r152.getSharedCameraIds(), CameraId.m227boximpl(r43)));
        if (r35 == false) goto L58;
        if (CameraId.m230equalsimpl0(r11, r43) == false) goto L56;
    L57:
        r32 = true;
        goto L59
    L56:
        if (Intrinsics.areEqual(r12, r10) == true) goto L58;
    L47:
        if (((RequestOpen) r15).isPrewarm() == false) goto L50;
        r35 = r42;
    L62:
        r33 = null;
        goto L76
    L64:
        if ((r82 instanceof RequestCloseById) == false) goto L62;
        int r36 = r18.size();
        int r44 = r72;
    L66:
        if (r44 >= r36) goto L62;
        CameraRequest r92 = r18.get(r44);
        if ((r92 instanceof RequestCloseById) == true) goto L70;
    L72:
        boolean r93 = false;
    L73:
        if (r93 == true) goto L74;
        r44 = r44 + 1;
        goto L66
    L74:
        r33 = Integer.valueOf(r44);
        goto L76
    L70:
        if (CameraId.m230equalsimpl0(((RequestCloseById) r92).m474getActiveCameraIdDz_R5H8(), ((RequestCloseById) r82).m474getActiveCameraIdDz_R5H8()) == false) goto L72;
        r93 = true;
        goto L73
    L86:
        Iterator r1 = removeIndices(r18, r25).iterator();
    L88:
        if (r1.hasNext() == false) goto L90;
        onRemoved((CameraRequest) r1.next());
        goto L88
    L90:
        return;
    L18:
        r24 = -1;
        goto L19
    }
}
