package androidx.camera.camera2.pipe.compat;

import android.os.Trace;
import android.view.Surface;
import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0;
import androidx.camera.camera2.pipe.CameraError;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraId;
import androidx.camera.camera2.pipe.CameraStream;
import androidx.camera.camera2.pipe.CameraSurfaceManager;
import androidx.camera.camera2.pipe.CaptureSequenceProcessor;
import androidx.camera.camera2.pipe.GraphState;
import androidx.camera.camera2.pipe.OutputId;
import androidx.camera.camera2.pipe.OutputStream;
import androidx.camera.camera2.pipe.StreamGraph;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.camera2.pipe.StrictMode;
import androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper;
import androidx.camera.camera2.pipe.compat.CaptureSessionFactory;
import androidx.camera.camera2.pipe.core.Debug;
import androidx.camera.camera2.pipe.core.DurationNs;
import androidx.camera.camera2.pipe.core.Log;
import androidx.camera.camera2.pipe.core.Threads;
import androidx.camera.camera2.pipe.core.TimeSource;
import androidx.camera.camera2.pipe.core.TimestampNs;
import androidx.camera.camera2.pipe.core.Timestamps;
import androidx.camera.camera2.pipe.graph.GraphListener;
import androidx.camera.camera2.pipe.graph.GraphRequestProcessor;
import androidx.lifecycle.LifecycleRegistry$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CountDownLatch;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicRef;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineScope;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000ì\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u0000 ~2\u00020\u0001:\u0004\u007f\u0080\u0001~Ba\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0014\u0012\u0006\u0010\u0017\u001a\u00020\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0019\u0010\u001d\u001a\u00020\u001c2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001aH\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ\u0019\u0010!\u001a\u00020\u001c2\b\b\u0002\u0010 \u001a\u00020\u001fH\u0002¢\u0006\u0004\b!\u0010\"J\u0010\u0010#\u001a\u00020\u001cH\u0082@¢\u0006\u0004\b#\u0010$J7\u0010*\u001a\u00020\u001c2\u0012\u0010(\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0%2\u0012\u0010)\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0%H\u0003¢\u0006\u0004\b*\u0010+J!\u0010-\u001a\u00020\u001c2\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0%¢\u0006\u0004\b-\u0010.J\u0017\u0010/\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b/\u0010\u001eJ\u0017\u00100\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b0\u0010\u001eJ\u0017\u00101\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b1\u0010\u001eJ\u0017\u00102\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b2\u0010\u001eJ\u0017\u00103\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b3\u0010\u001eJ\u0017\u00104\u001a\u00020\u001c2\u0006\u0010\u001b\u001a\u00020\u001aH\u0016¢\u0006\u0004\b4\u0010\u001eJ\u000f\u00105\u001a\u00020\u001cH\u0016¢\u0006\u0004\b5\u00106J\u000f\u00107\u001a\u00020\u001cH\u0016¢\u0006\u0004\b7\u00106J\r\u00108\u001a\u00020\u001c¢\u0006\u0004\b8\u00106J\r\u00109\u001a\u00020\u001c¢\u0006\u0004\b9\u00106J\u0019\u0010>\u001a\u00020\u001c2\b\b\u0002\u0010;\u001a\u00020:H\u0000¢\u0006\u0004\b<\u0010=J\u000f\u0010@\u001a\u00020?H\u0016¢\u0006\u0004\b@\u0010AR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010BR\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010CR\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010DR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010ER\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010FR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010GR\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010HR\u0014\u0010\u0011\u001a\u00020\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0011\u0010IR\u0014\u0010\u0013\u001a\u00020\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010JR\u0014\u0010\u0015\u001a\u00020\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0015\u0010KR\u0014\u0010\u0017\u001a\u00020\u00168\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0017\u0010LR\u0014\u0010N\u001a\u00020M8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bN\u0010OR\u0014\u0010Q\u001a\u00020P8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bQ\u0010RR\u001a\u0010T\u001a\b\u0012\u0004\u0012\u00020\u001f0S8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bT\u0010URT\u0010X\u001aB\u0012\f\u0012\n W*\u0004\u0018\u00010&0&\u0012\f\u0012\n W*\u0004\u0018\u00010'0' W* \u0012\f\u0012\n W*\u0004\u0018\u00010&0&\u0012\f\u0012\n W*\u0004\u0018\u00010'0'\u0018\u00010%0V8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bX\u0010YRT\u0010[\u001aB\u0012\f\u0012\n W*\u0004\u0018\u00010Z0Z\u0012\f\u0012\n W*\u0004\u0018\u00010'0' W* \u0012\f\u0012\n W*\u0004\u0018\u00010Z0Z\u0012\f\u0012\n W*\u0004\u0018\u00010'0'\u0018\u00010%0V8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b[\u0010YR\u0018\u0010]\u001a\u0004\u0018\u00010\\8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b]\u0010^R\u0016\u0010`\u001a\u0004\u0018\u00010_8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b`\u0010aR\u0018\u0010c\u001a\u0004\u0018\u00010b8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bc\u0010dR\u0018\u0010f\u001a\u0004\u0018\u00010e8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bf\u0010gR$\u0010i\u001a\u0010\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020h\u0018\u00010%8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bi\u0010YR$\u0010j\u001a\u0010\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'\u0018\u00010%8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bj\u0010YR\u0016\u0010l\u001a\u00020k8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bl\u0010mR\u0014\u0010o\u001a\u00020n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bo\u0010pR\u0016\u0010q\u001a\u00020\u001f8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bq\u0010rR\u0014\u0010s\u001a\u00020n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bs\u0010pR$\u0010t\u001a\u0010\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'\u0018\u00010%8\u0002@\u0002X\u0083\u000e¢\u0006\u0006\n\u0004\bt\u0010YR$\u0010w\u001a\u0012\u0012\u0004\u0012\u00020'\u0012\b\u0012\u00060uj\u0002`v0V8\u0002X\u0083\u0004¢\u0006\u0006\n\u0004\bw\u0010YR(\u0010}\u001a\u0004\u0018\u00010b2\b\u0010x\u001a\u0004\u0018\u00010b8F@FX\u0086\u000e¢\u0006\f\u001a\u0004\by\u0010z\"\u0004\b{\u0010|¨\u0006\u0081\u0001"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CaptureSessionState;", "Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper$StateCallback;", "Landroidx/camera/camera2/pipe/graph/GraphListener;", "graphListener", "Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory;", "captureSessionFactory", "Landroidx/camera/camera2/pipe/compat/Camera2CaptureSequenceProcessorFactory;", "captureSequenceProcessorFactory", "Landroidx/camera/camera2/pipe/CameraSurfaceManager;", "cameraSurfaceManager", "Landroidx/camera/camera2/pipe/core/TimeSource;", "timeSource", "Landroidx/camera/camera2/pipe/CameraGraph$Flags;", "cameraGraphFlags", "Landroidx/camera/camera2/pipe/compat/ConcurrentSessionSequencer;", "concurrentSessionSequencer", "Landroidx/camera/camera2/pipe/StreamGraph;", "streamGraph", "Landroidx/camera/camera2/pipe/StrictMode;", "strictMode", "Landroidx/camera/camera2/pipe/core/Threads;", "threads", "Lkotlinx/coroutines/CoroutineScope;", "scope", "<init>", "(Landroidx/camera/camera2/pipe/graph/GraphListener;Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory;Landroidx/camera/camera2/pipe/compat/Camera2CaptureSequenceProcessorFactory;Landroidx/camera/camera2/pipe/CameraSurfaceManager;Landroidx/camera/camera2/pipe/core/TimeSource;Landroidx/camera/camera2/pipe/CameraGraph$Flags;Landroidx/camera/camera2/pipe/compat/ConcurrentSessionSequencer;Landroidx/camera/camera2/pipe/StreamGraph;Landroidx/camera/camera2/pipe/StrictMode;Landroidx/camera/camera2/pipe/core/Threads;Lkotlinx/coroutines/CoroutineScope;)V", "Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper;", "session", _UrlKt.FRAGMENT_ENCODE_SET, "configure", "(Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper;)V", _UrlKt.FRAGMENT_ENCODE_SET, "retryAllowed", "finalizeOutputsIfAvailable", "(Z)V", "tryCreateCaptureSession", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "Landroid/view/Surface;", "oldSurfaceMap", "newSurfaceMap", "updateTrackedSurfaces", "(Ljava/util/Map;Ljava/util/Map;)V", "surfaces", "configureSurfaceMap", "(Ljava/util/Map;)V", "onActive", "onClosed", "onConfigureFailed", "onConfigured", "onReady", "onCaptureQueueEmpty", "onSessionDisconnected", "()V", "onSessionFinalized", "disconnect", "shutdown", _UrlKt.FRAGMENT_ENCODE_SET, "delayMs", "finalizeSession$camera_camera2_pipe", "(J)V", "finalizeSession", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroidx/camera/camera2/pipe/graph/GraphListener;", "Landroidx/camera/camera2/pipe/compat/CaptureSessionFactory;", "Landroidx/camera/camera2/pipe/compat/Camera2CaptureSequenceProcessorFactory;", "Landroidx/camera/camera2/pipe/CameraSurfaceManager;", "Landroidx/camera/camera2/pipe/core/TimeSource;", "Landroidx/camera/camera2/pipe/CameraGraph$Flags;", "Landroidx/camera/camera2/pipe/compat/ConcurrentSessionSequencer;", "Landroidx/camera/camera2/pipe/StreamGraph;", "Landroidx/camera/camera2/pipe/StrictMode;", "Landroidx/camera/camera2/pipe/core/Threads;", "Lkotlinx/coroutines/CoroutineScope;", _UrlKt.FRAGMENT_ENCODE_SET, "debugId", "I", _UrlKt.FRAGMENT_ENCODE_SET, "lock", "Ljava/lang/Object;", "Lkotlinx/atomicfu/AtomicRef;", "finalized", "Lkotlinx/atomicfu/AtomicRef;", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin.jvm.PlatformType", "activeStreamSurfaceMap", "Ljava/util/Map;", "Landroidx/camera/camera2/pipe/OutputId;", "activeOutputSurfaceMap", "Landroidx/camera/camera2/pipe/core/TimestampNs;", "sessionCreatingTimestamp", "Landroidx/camera/camera2/pipe/core/TimestampNs;", "Landroidx/camera/camera2/pipe/compat/SessionSequencer;", "sessionSequencer", "Landroidx/camera/camera2/pipe/compat/SessionSequencer;", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "_cameraDevice", "Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "Landroidx/camera/camera2/pipe/compat/CaptureSessionState$ConfiguredCameraCaptureSession;", "cameraCaptureSession", "Landroidx/camera/camera2/pipe/compat/CaptureSessionState$ConfiguredCameraCaptureSession;", "Landroidx/camera/camera2/pipe/compat/OutputConfigurationWrapper;", "pendingOutputMap", "pendingSurfaceMap", "Landroidx/camera/camera2/pipe/compat/CaptureSessionState$State;", "state", "Landroidx/camera/camera2/pipe/compat/CaptureSessionState$State;", "Ljava/util/concurrent/CountDownLatch;", "sessionDisconnected", "Ljava/util/concurrent/CountDownLatch;", "hasAttemptedCaptureSession", "Z", "captureSessionAttemptCompleted", "_surfaceMap", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "_surfaceTokenMap", "value", "getCameraDevice", "()Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;", "setCameraDevice", "(Landroidx/camera/camera2/pipe/compat/CameraDeviceWrapper;)V", "cameraDevice", "Companion", "State", "ConfiguredCameraCaptureSession", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCaptureSessionState.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CaptureSessionState.kt\nandroidx/camera/camera2/pipe/compat/CaptureSessionState\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n+ 5 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n+ 6 Timestamps.kt\nandroidx/camera/camera2/pipe/core/Timestamps\n+ 7 Timestamps.kt\nandroidx/camera/camera2/pipe/core/TimestampNs\n+ 8 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 9 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,657:1\n1#2:658\n538#3:659\n523#3,6:660\n538#3:841\n523#3,6:842\n50#4,2:666\n50#4,2:668\n71#4,2:678\n50#4,2:688\n50#4,2:698\n50#4,2:700\n50#4,2:702\n50#4,2:725\n59#4:735\n60#4:740\n50#4,2:741\n82#4,2:743\n50#4,2:753\n82#4,2:759\n82#4,2:769\n50#4,2:791\n59#4:803\n60#4:812\n50#4,2:818\n59#4,2:820\n82#4,2:835\n59#4,2:837\n59#4,2:839\n71#5,4:670\n78#5,4:674\n71#5,4:680\n78#5,4:684\n71#5,4:690\n78#5,4:694\n71#5,4:704\n48#5,2:708\n71#5,4:710\n50#5,3:714\n78#5,4:717\n78#5,4:721\n71#5,4:727\n78#5,4:731\n71#5,4:745\n78#5,4:749\n71#5,4:755\n71#5,4:761\n78#5,4:765\n71#5,4:771\n78#5,4:775\n78#5,4:779\n71#5,4:783\n78#5,4:787\n71#5,4:795\n78#5,4:813\n48#5,2:822\n71#5,4:824\n50#5,3:828\n78#5,4:831\n70#6:736\n74#6,2:738\n70#6:799\n70#6:804\n74#6,2:810\n70#6:817\n29#7:737\n29#7:805\n1869#8,2:793\n153#9,3:800\n126#9:806\n153#9,3:807\n*S KotlinDebug\n*F\n+ 1 CaptureSessionState.kt\nandroidx/camera/camera2/pipe/compat/CaptureSessionState\n*L\n147#1:659\n147#1:660,6\n603#1:841\n603#1:842,6\n169#1:666,2\n173#1:668,2\n182#1:678,2\n194#1:688,2\n203#1:698,2\n207#1:700,2\n211#1:702,2\n227#1:725,2\n287#1:735\n287#1:740\n334#1:741,2\n341#1:743,2\n366#1:753,2\n380#1:759,2\n411#1:769,2\n479#1:791,2\n527#1:803\n527#1:812\n566#1:818,2\n572#1:820,2\n580#1:835,2\n586#1:837,2\n597#1:839,2\n174#1:670,4\n178#1:674,4\n183#1:680,4\n190#1:684,4\n195#1:690,4\n199#1:694,4\n212#1:704,4\n220#1:708,2\n220#1:710,4\n220#1:714,3\n220#1:717,4\n221#1:721,4\n228#1:727,4\n231#1:731,4\n349#1:745,4\n351#1:749,4\n367#1:755,4\n386#1:761,4\n388#1:765,4\n416#1:771,4\n418#1:775,4\n420#1:779,4\n424#1:783,4\n426#1:787,4\n501#1:795,4\n539#1:813,4\n576#1:822,2\n576#1:824,4\n576#1:828,3\n576#1:831,4\n288#1:736\n289#1:738,2\n502#1:799\n528#1:804\n530#1:810,2\n559#1:817\n288#1:737\n528#1:805\n486#1:793,2\n513#1:800,3\n529#1:806\n529#1:807,3\n*E\n"})
/* loaded from: classes3.dex */
public final class CaptureSessionState implements CameraCaptureSessionWrapper.StateCallback {
    private static final Companion Companion = null;
    private CameraDeviceWrapper _cameraDevice;
    private Map<StreamId, ? extends Surface> _surfaceMap;
    private final Map<Surface, AutoCloseable> _surfaceTokenMap;
    private final Map<OutputId, Surface> activeOutputSurfaceMap;
    private final Map<StreamId, Surface> activeStreamSurfaceMap;
    private ConfiguredCameraCaptureSession cameraCaptureSession;
    private final CameraGraph.Flags cameraGraphFlags;
    private final CameraSurfaceManager cameraSurfaceManager;
    private final Camera2CaptureSequenceProcessorFactory captureSequenceProcessorFactory;
    private final CountDownLatch captureSessionAttemptCompleted;
    private final CaptureSessionFactory captureSessionFactory;
    private final ConcurrentSessionSequencer concurrentSessionSequencer;
    private final int debugId;
    private final AtomicRef<Boolean> finalized;
    private final GraphListener graphListener;
    private boolean hasAttemptedCaptureSession;
    private final Object lock;
    private Map<StreamId, ? extends OutputConfigurationWrapper> pendingOutputMap;
    private Map<StreamId, ? extends Surface> pendingSurfaceMap;
    private final CoroutineScope scope;
    private TimestampNs sessionCreatingTimestamp;
    private final CountDownLatch sessionDisconnected;
    private final SessionSequencer sessionSequencer;
    private State state;
    private final StreamGraph streamGraph;
    private final StrictMode strictMode;
    private final Threads threads;
    private final TimeSource timeSource;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CaptureSessionState$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "CAPTURE_SESSION_TIMEOUT_MS", _UrlKt.FRAGMENT_ENCODE_SET, "ABORT_CAPTURES_TIMEOUT_MS", "CLOSE_SESSION_TIMEOUT_MS", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\f\b\u0082\b\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\b\u0010\tJ\u0010\u0010\u000b\u001a\u00020\nHÖ\u0001¢\u0006\u0004\b\u000b\u0010\fJ\u0010\u0010\u000e\u001a\u00020\rHÖ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u001a\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019R\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CaptureSessionState$ConfiguredCameraCaptureSession;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper;", "session", "Landroidx/camera/camera2/pipe/graph/GraphRequestProcessor;", "processor", "Landroidx/camera/camera2/pipe/compat/Camera2CaptureSequenceProcessor;", "captureSequenceProcessor", "<init>", "(Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper;Landroidx/camera/camera2/pipe/graph/GraphRequestProcessor;Landroidx/camera/camera2/pipe/compat/Camera2CaptureSequenceProcessor;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper;", "getSession", "()Landroidx/camera/camera2/pipe/compat/CameraCaptureSessionWrapper;", "Landroidx/camera/camera2/pipe/graph/GraphRequestProcessor;", "getProcessor", "()Landroidx/camera/camera2/pipe/graph/GraphRequestProcessor;", "Landroidx/camera/camera2/pipe/compat/Camera2CaptureSequenceProcessor;", "getCaptureSequenceProcessor", "()Landroidx/camera/camera2/pipe/compat/Camera2CaptureSequenceProcessor;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class ConfiguredCameraCaptureSession {
        private final Camera2CaptureSequenceProcessor captureSequenceProcessor;
        private final GraphRequestProcessor processor;
        private final CameraCaptureSessionWrapper session;

        public ConfiguredCameraCaptureSession(CameraCaptureSessionWrapper r1, GraphRequestProcessor r2, Camera2CaptureSequenceProcessor r3) {
            this.session = r1;
            this.processor = r2;
            this.captureSequenceProcessor = r3;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof ConfiguredCameraCaptureSession) == true) goto L8;
            return false;
        L8:
            ConfiguredCameraCaptureSession r52 = (ConfiguredCameraCaptureSession) r5;
            if (Intrinsics.areEqual(this.session, r52.session) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.processor, r52.processor) == true) goto L15;
            return false;
        L15:
            if (Intrinsics.areEqual(this.captureSequenceProcessor, r52.captureSequenceProcessor) == true) goto L17;
            return false;
        L17:
            return true;
        }

        public final Camera2CaptureSequenceProcessor getCaptureSequenceProcessor() {
            return this.captureSequenceProcessor;
        }

        public final GraphRequestProcessor getProcessor() {
            return this.processor;
        }

        public final CameraCaptureSessionWrapper getSession() {
            return this.session;
        }

        public int hashCode() {
            int r0 = ((this.session.hashCode() * 31) + this.processor.hashCode()) * 31;
            Camera2CaptureSequenceProcessor r2 = this.captureSequenceProcessor;
            if (r2 != null) goto L5;
            int r22 = 0;
        L7:
            return r0 + r22;
        L5:
            r22 = r2.hashCode();
            goto L7
        }

        public String toString() {
            return "ConfiguredCameraCaptureSession(session=" + this.session + ", processor=" + this.processor + ", captureSequenceProcessor=" + this.captureSequenceProcessor + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\b\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\b¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/pipe/compat/CaptureSessionState$State;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "PENDING", "CREATING", "CREATED", "CLOSING", "CLOSED", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum State extends Enum<State> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ State[] $VALUES = null;
        public static final State CLOSED = null;
        public static final State CLOSING = null;
        public static final State CREATED = null;
        public static final State CREATING = null;
        public static final State PENDING = null;

        private static final /* synthetic */ State[] $values() {
            return new State[]{PENDING, CREATING, CREATED, CLOSING, CLOSED};
        }

        static {
            PENDING = new State("PENDING", 0);
            CREATING = new State("CREATING", 1);
            CREATED = new State("CREATED", 2);
            CLOSING = new State("CLOSING", 3);
            CLOSED = new State("CLOSED", 4);
            State[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        State(String r1, int r2) {
        }

        public static State valueOf(String r1) {
            return (State) Enum.valueOf(State.class, r1);
        }

        public static State[] values() {
            return (State[]) $VALUES.clone();
        }
    }

    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.CaptureSessionState$disconnect$12", f = "CaptureSessionState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    @SourceDebugExtension({"SMAP\nCaptureSessionState.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CaptureSessionState.kt\nandroidx/camera/camera2/pipe/compat/CaptureSessionState$disconnect$12\n+ 2 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n+ 3 Log.kt\nandroidx/camera/camera2/pipe/core/Log\n*L\n1#1,657:1\n48#2,2:658\n71#2,4:660\n50#2:664\n52#2:667\n78#2,4:668\n50#3,2:665\n*S KotlinDebug\n*F\n+ 1 CaptureSessionState.kt\nandroidx/camera/camera2/pipe/compat/CaptureSessionState$disconnect$12\n*L\n406#1:658,2\n406#1:660,4\n406#1:664\n406#1:667\n406#1:668,4\n407#1:665,2\n*E\n"})
    /* renamed from: androidx.camera.camera2.pipe.compat.CaptureSessionState$disconnect$12, reason: invalid class name */
    public static final class AnonymousClass12 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ ConfiguredCameraCaptureSession $captureSession;
        int label;
        final /* synthetic */ CaptureSessionState this$0;

        public AnonymousClass12(CaptureSessionState r1, ConfiguredCameraCaptureSession r2, Continuation<? super AnonymousClass12> r3) {
            this.this$0 = r1;
            this.$captureSession = r2;
            super(1, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> r3) {
            return new AnonymousClass12(this.this$0, this.$captureSession, r3);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Unit> r1) {
            return invoke2(r1);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Continuation<? super Unit> r1) {
            return ((AnonymousClass12) create(r1)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L14;
            ResultKt.throwOnFailure(r4);
            Debug r42 = Debug.INSTANCE;
            String r43 = this.this$0 + " CameraCaptureSessionWrapper#close";
            ConfiguredCameraCaptureSession r1 = this.$captureSession;
            CaptureSessionState r3 = this.this$0;
            Trace.beginSection(r43);     // Catch: Throwable -> L11
            if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L8;
            android.util.Log.d("CXCP", "Closing capture session for " + r3);     // Catch: Throwable -> L11
        L8:
            UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(r1.getSession());     // Catch: Throwable -> L11
            Unit r32 = Unit.INSTANCE;     // Catch: Throwable -> L11
            Trace.endSection();
            return Unit.INSTANCE;
        L11:
            th = move-exception;
            Trace.endSection();
            throw th;
        L14:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.CaptureSessionState$disconnect$3", f = "CaptureSessionState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.compat.CaptureSessionState$disconnect$3, reason: invalid class name */
    public static final class AnonymousClass3 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        int label;
        final /* synthetic */ CaptureSessionState this$0;

        public AnonymousClass3(CaptureSessionState r1, Continuation<? super AnonymousClass3> r2) {
            this.this$0 = r1;
            super(1, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> r2) {
            return new AnonymousClass3(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Unit> r1) {
            return invoke2(r1);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Continuation<? super Unit> r1) {
            return ((AnonymousClass3) create(r1)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L6;
            ResultKt.throwOnFailure(r2);
            CaptureSessionState.access$getCaptureSessionAttemptCompleted$p(this.this$0).await();
            return Unit.INSTANCE;
        L6:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0010\u0002\u0010\u0000\u001a\u00020\u0001H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.CaptureSessionState$disconnect$9", f = "CaptureSessionState.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    @SourceDebugExtension({"SMAP\nCaptureSessionState.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CaptureSessionState.kt\nandroidx/camera/camera2/pipe/compat/CaptureSessionState$disconnect$9\n+ 2 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n*L\n1#1,657:1\n48#2,2:658\n71#2,4:660\n50#2,3:664\n78#2,4:667\n48#2,2:671\n71#2,4:673\n50#2,3:677\n78#2,4:680\n*S KotlinDebug\n*F\n+ 1 CaptureSessionState.kt\nandroidx/camera/camera2/pipe/compat/CaptureSessionState$disconnect$9\n*L\n378#1:658,2\n378#1:660,4\n378#1:664,3\n378#1:667,4\n379#1:671,2\n379#1:673,4\n379#1:677,3\n379#1:680,4\n*E\n"})
    /* renamed from: androidx.camera.camera2.pipe.compat.CaptureSessionState$disconnect$9, reason: invalid class name */
    public static final class AnonymousClass9 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        final /* synthetic */ GraphRequestProcessor $graphProcessor;
        int label;
        final /* synthetic */ CaptureSessionState this$0;

        public AnonymousClass9(CaptureSessionState r1, GraphRequestProcessor r2, Continuation<? super AnonymousClass9> r3) {
            this.this$0 = r1;
            this.$graphProcessor = r2;
            super(1, r3);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> r3) {
            return new AnonymousClass9(this.this$0, this.$graphProcessor, r3);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Continuation<? super Unit> r1) {
            return invoke2(r1);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Continuation<? super Unit> r1) {
            return ((AnonymousClass9) create(r1)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) goto L16;
            ResultKt.throwOnFailure(r2);
            Debug r22 = Debug.INSTANCE;
            String r23 = this.this$0 + " stopRepeating";
            GraphRequestProcessor r0 = this.$graphProcessor;
            Trace.beginSection(r23);     // Catch: Throwable -> L13
            r0.stopRepeating$camera_camera2_pipe();     // Catch: Throwable -> L13
            Unit r24 = Unit.INSTANCE;     // Catch: Throwable -> L13
            Trace.endSection();
            String r25 = this.this$0 + " abortCaptures";
            GraphRequestProcessor r1 = this.$graphProcessor;
            Trace.beginSection(r25);     // Catch: Throwable -> L10
            r1.abortCaptures$camera_camera2_pipe();     // Catch: Throwable -> L10
            Trace.endSection();
            return Unit.INSTANCE;
        L10:
            th = move-exception;
            Trace.endSection();
            throw th;
        L13:
            th = move-exception;
            Trace.endSection();
            throw th;
        L16:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.pipe.compat.CaptureSessionState", f = "CaptureSessionState.kt", i = {0, 0}, l = {567}, m = "tryCreateCaptureSession", n = {"surfaces", "device"}, s = {"L$0", "L$1"}, v = 1)
    /* renamed from: androidx.camera.camera2.pipe.compat.CaptureSessionState$tryCreateCaptureSession$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ CaptureSessionState this$0;

        public AnonymousClass1(CaptureSessionState r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return CaptureSessionState.access$tryCreateCaptureSession(this.this$0, this);
        }
    }

    static {
        Companion = new Companion(null);
    }

    public CaptureSessionState(GraphListener r1, CaptureSessionFactory r2, Camera2CaptureSequenceProcessorFactory r3, CameraSurfaceManager r4, TimeSource r5, CameraGraph.Flags r6, ConcurrentSessionSequencer r7, StreamGraph r8, StrictMode r9, Threads r10, CoroutineScope r11) {
        this.graphListener = r1;
        this.captureSessionFactory = r2;
        this.captureSequenceProcessorFactory = r3;
        this.cameraSurfaceManager = r4;
        this.timeSource = r5;
        this.cameraGraphFlags = r6;
        this.concurrentSessionSequencer = r7;
        this.streamGraph = r8;
        this.strictMode = r9;
        this.threads = r10;
        this.scope = r11;
        this.debugId = CaptureSessionStateKt.getCaptureSessionDebugIds().incrementAndGet();
        this.lock = new Object();
        this.finalized = AtomicFU.atomic(Boolean.FALSE);
        this.activeStreamSurfaceMap = Collections.synchronizedMap(new HashMap());
        this.activeOutputSurfaceMap = Collections.synchronizedMap(new HashMap());
        if (r7 == null) goto L5;
        SessionSequencer r12 = new SessionSequencer(r7);
    L6:
        this.sessionSequencer = r12;
        this.state = State.PENDING;
        this.sessionDisconnected = new CountDownLatch(1);
        this.captureSessionAttemptCompleted = new CountDownLatch(1);
        this._surfaceTokenMap = new LinkedHashMap();
        return;
    L5:
        r12 = null;
        goto L6
    }

    public static final /* synthetic */ CountDownLatch access$getCaptureSessionAttemptCompleted$p(CaptureSessionState r0) {
        return r0.captureSessionAttemptCompleted;
    }

    public static final /* synthetic */ CaptureSessionFactory access$getCaptureSessionFactory$p(CaptureSessionState r0) {
        return r0.captureSessionFactory;
    }

    public static final /* synthetic */ GraphListener access$getGraphListener$p(CaptureSessionState r0) {
        return r0.graphListener;
    }

    public static final /* synthetic */ TimestampNs access$getSessionCreatingTimestamp$p(CaptureSessionState r0) {
        return r0.sessionCreatingTimestamp;
    }

    public static final /* synthetic */ CountDownLatch access$getSessionDisconnected$p(CaptureSessionState r0) {
        return r0.sessionDisconnected;
    }

    public static final /* synthetic */ State access$getState$p(CaptureSessionState r0) {
        return r0.state;
    }

    public static final /* synthetic */ TimeSource access$getTimeSource$p(CaptureSessionState r0) {
        return r0.timeSource;
    }

    public static final /* synthetic */ Object access$tryCreateCaptureSession(CaptureSessionState r0, Continuation r1) {
        return r0.tryCreateCaptureSession(r1);
    }

    private final void configure(CameraCaptureSessionWrapper r11) {
        Object r1 = this.lock;
        monitor-enter(r1);
        ConfiguredCameraCaptureSession r2 = this.cameraCaptureSession;     // Catch: Throwable -> L11
        if (r2 != null) goto L16;
        if (r11 == null) goto L16;
        CaptureSequenceProcessor<?, ?> r22 = this.captureSequenceProcessorFactory.create(r11, this.activeStreamSurfaceMap, this.activeOutputSurfaceMap);     // Catch: Throwable -> L11
        if ((r22 instanceof Camera2CaptureSequenceProcessor) == false) goto L13;
        ConfiguredCameraCaptureSession r4 = new ConfiguredCameraCaptureSession(r11, GraphRequestProcessor.Companion.from(r22), (Camera2CaptureSequenceProcessor) r22);     // Catch: Throwable -> L11
    L10:
        r2 = r4;
        this.cameraCaptureSession = r2;     // Catch: Throwable -> L11
        goto L16
    L13:
        r4 = new ConfiguredCameraCaptureSession(r11, GraphRequestProcessor.Companion.from(r22), null);     // Catch: Throwable -> L11
    L16:
        if (this.state != State.CREATED) goto L41;
        if (r2 == null) goto L41;
        if (this.pendingOutputMap != null) goto L22;
    L24:
        boolean r112 = false;
    L25:
        Unit r6 = Unit.INSTANCE;     // Catch: Throwable -> L11
        monitor-exit(r1);
        if (r112 == false) goto L29;
        finalizeOutputsIfAvailable(false);
    L29:
        Object r113 = this.lock;
        monitor-enter(r113);
    L34:
        th = move-exception;
        throw th;
    L32:
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L36;
        Timestamps r5 = Timestamps.INSTANCE;     // Catch: Throwable -> L34
        android.util.Log.i("CXCP", "Configured " + this + " in " + String.format(null, "%.3f ms", Arrays.copyOf(new Object[]{Double.valueOf(DurationNs.m498constructorimpl(access$getTimeSource$p(this).mo506nowvQl9yQU() - access$getSessionCreatingTimestamp$p(this).m514unboximpl()) / 1000000.0d)}, 1)));     // Catch: Throwable -> L34
    L36:
        this.graphListener.onGraphStarted(r2.getProcessor());     // Catch: Throwable -> L34
        monitor-exit(r113);
        return;
    L22:
        if (this.pendingSurfaceMap == null) goto L24;
        r112 = true;
    L41:
        monitor-exit(r1);
        return;
    L11:
        th = move-exception;
        throw th;
    }

    private final void finalizeOutputsIfAvailable(boolean r11) {
        Object r0 = this.lock;
        monitor-enter(r0);
        ConfiguredCameraCaptureSession r1 = this.cameraCaptureSession;     // Catch: Throwable -> L57
        Map<StreamId, ? extends OutputConfigurationWrapper> r2 = this.pendingOutputMap;     // Catch: Throwable -> L57
        Map<StreamId, ? extends Surface> r3 = this.pendingSurfaceMap;     // Catch: Throwable -> L57
        Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L57
        monitor-exit(r0);
        if (r1 == null) goto L56;
        if (r2 == null) goto L70;
        if (r3 == null) goto L71;
        Debug r02 = Debug.INSTANCE;
        Trace.beginSection(this + "#finalizeOutputConfigurations");
        Timestamps r03 = Timestamps.INSTANCE;
        long r42 = this.timeSource.mo506nowvQl9yQU();
        Iterator<Map.Entry<StreamId, ? extends OutputConfigurationWrapper>> r04 = r2.entrySet().iterator();
    L11:
        if (r04.hasNext() == false) goto L17;
        Map.Entry<StreamId, ? extends OutputConfigurationWrapper> r6 = r04.next();
        int r7 = r6.getKey().m407unboximpl();
        OutputConfigurationWrapper r62 = r6.getValue();
        Surface r72 = r3.get(StreamId.m401boximpl(r7));
        if (r72 == null) goto L15;
        r62.addSurface(r72);
        goto L11
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return;
    L17:
        LinkedHashSet r05 = new LinkedHashSet();
        Iterator<Map.Entry<StreamId, ? extends OutputConfigurationWrapper>> r63 = r2.entrySet().iterator();
    L19:
        if (r63.hasNext() == false) goto L21;
        r05.add(r63.next().getValue());
        goto L19
    L21:
        r1.getSession().finalizeOutputConfigurations(CollectionsKt.toList(r05));
        Object r06 = this.lock;
        monitor-enter(r06);
    L33:
        th = move-exception;
        throw th;
    L24:
        if (this.state != State.CREATED) goto L46;
        this.activeStreamSurfaceMap.putAll(r3);     // Catch: Throwable -> L33
        Iterator<Map.Entry<StreamId, ? extends Surface>> r32 = r3.entrySet().iterator();     // Catch: Throwable -> L33
    L26:
        boolean r73 = true;
        if (r32.hasNext() == false) goto L40;
        Map.Entry<StreamId, ? extends Surface> r64 = r32.next();     // Catch: Throwable -> L33
        int r8 = r64.getKey().m407unboximpl();     // Catch: Throwable -> L33
        Surface r65 = r64.getValue();     // Catch: Throwable -> L33
        CameraStream r82 = this.streamGraph.m399getaKI5c8E(r8);     // Catch: Throwable -> L33
        if (r82 == null) goto L38;
        if (r82.getOutputs().size() != 1) goto L36;
        this.activeOutputSurfaceMap.put(OutputId.m290boximpl(((OutputStream) CollectionsKt.single(r82.getOutputs())).mo311getId4LaLFng()), r65);     // Catch: Throwable -> L33
        goto L26
    L36:
        throw new IllegalStateException("Cannot finalize a multi-output stream!");     // Catch: Throwable -> L33
    L38:
        throw new IllegalStateException("Required value was null.");     // Catch: Throwable -> L33
    L40:
        if (Log.INSTANCE.getINFO_LOGGABLE() == false) goto L47;
        Timestamps r66 = Timestamps.INSTANCE;     // Catch: Throwable -> L33
        long r43 = DurationNs.m498constructorimpl(access$getTimeSource$p(this).mo506nowvQl9yQU() - r42);     // Catch: Throwable -> L33
        StringBuilder r67 = new StringBuilder();     // Catch: Throwable -> L33
        r67.append("Finalized ");     // Catch: Throwable -> L33
        ArrayList r83 = new ArrayList(r2.size());     // Catch: Throwable -> L33
        Iterator<Map.Entry<StreamId, ? extends OutputConfigurationWrapper>> r22 = r2.entrySet().iterator();     // Catch: Throwable -> L33
    L43:
        if (r22.hasNext() == false) goto L45;
        r83.add(StreamId.m401boximpl(r22.next().getKey().m407unboximpl()));     // Catch: Throwable -> L33
        goto L43
    L45:
        r67.append(r83);     // Catch: Throwable -> L33
        r67.append(" for ");     // Catch: Throwable -> L33
        r67.append(this);     // Catch: Throwable -> L33
        r67.append(" in ");     // Catch: Throwable -> L33
        Timestamps r23 = Timestamps.INSTANCE;     // Catch: Throwable -> L33
        r67.append(String.format(null, "%.3f ms", Arrays.copyOf(new Object[]{Double.valueOf(r43 / 1000000.0d)}, 1)));     // Catch: Throwable -> L33
        android.util.Log.i("CXCP", r67.toString());     // Catch: Throwable -> L33
    L47:
        Unit r24 = Unit.INSTANCE;     // Catch: Throwable -> L33
        monitor-exit(r06);
        if (r73 == false) goto L52;
        if (r11 == false) goto L52;
        this.graphListener.onGraphModified(r1.getProcessor());
    L52:
        Debug r10 = Debug.INSTANCE;
        Trace.endSection();
        return;
    L46:
        r73 = false;
        goto L47
    L71:
        return;
    L70:
        return;
    L56:
        return;
    L57:
        th = move-exception;
        throw th;
    }

    public static /* synthetic */ void finalizeOutputsIfAvailable$default(CaptureSessionState r0, boolean r1, int r2, Object r3) {
        if ((r2 & 1) == 0) goto L5;
        r1 = true;
    L5:
        r0.finalizeOutputsIfAvailable(r1);
    }

    /* JADX WARN: Type inference failed for: r9v1, types: [T, java.util.Map<androidx.camera.camera2.pipe.StreamId, ? extends android.view.Surface>] */
    /* JADX WARN: Type inference failed for: r9v2, types: [T, androidx.camera.camera2.pipe.compat.CameraDeviceWrapper] */
    private final Object tryCreateCaptureSession(Continuation<? super Unit> r12) {
        if ((r12 instanceof AnonymousClass1) == false) goto L7;
        AnonymousClass1 r3 = (AnonymousClass1) r12;
        int r4 = r3.label;
        if ((r4 & Integer.MIN_VALUE) == 0) goto L7;
        r3.label = r4 - Integer.MIN_VALUE;
    L8:
        Object r122 = r3.result;
        Object r42 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r5 = r3.label;
        if (r5 == 0) goto L14;
        if (r5 != 1) goto L12;
        Ref.ObjectRef r43 = (Ref.ObjectRef) r3.L$1;
        Ref.ObjectRef r32 = (Ref.ObjectRef) r3.L$0;
        ResultKt.throwOnFailure(r122);
    L38:
        Ref.ObjectRef r123 = r32;
        Ref.ObjectRef r52 = r43;
    L39:
        Log r33 = Log.INSTANCE;
        if (r33.getINFO_LOGGABLE() == false) goto L49;
        StringBuilder r6 = new StringBuilder("Creating CameraCaptureSession from ");
        CameraDeviceWrapper r8 = (CameraDeviceWrapper) r52.element;
        if (r8 == null) goto L44;
        String r82 = r8.mo412getCameraIdDz_R5H8();
    L45:
        if (r82 != null) goto L47;
        String r83 = "null";
    L48:
        r6.append(r83);
        r6.append(" using ");
        r6.append(this);
        r6.append(" with ");
        r6.append(r123.element);
        android.util.Log.i("CXCP", r6.toString());
        goto L49
    L47:
        r83 = CameraId.m232toStringimpl(r82);
        goto L48
    L44:
        r82 = null;
    L49:
        Debug r44 = Debug.INSTANCE;
        StringBuilder r45 = new StringBuilder("CameraDevice-");
        CameraDeviceWrapper r62 = (CameraDeviceWrapper) r52.element;
        if (r62 == null) goto L52;
        String r63 = r62.mo412getCameraIdDz_R5H8();
    L53:
        r45.append(r63);
        r45.append("#createCaptureSession");
        Trace.beginSection(r45.toString());     // Catch: Throwable -> L105
        CaptureSessionFactory.Result r46 = access$getCaptureSessionFactory$p(this).create((CameraDeviceWrapper) r52.element, (Map) r123.element, this);     // Catch: Throwable -> L105
        Trace.endSection();
        if ((r46 instanceof CaptureSessionFactory.Result.Success) == false) goto L58;
        Object r53 = this.lock;
        monitor-enter(r53);
        State r64 = this.state;     // Catch: Throwable -> L76
        if (r64 == State.CLOSING) goto L98;
        if (r64 == State.CLOSED) goto L98;
        if (r64 != State.CREATING) goto L96;
        this.state = State.CREATED;     // Catch: Throwable -> L76
        this.activeStreamSurfaceMap.putAll((Map) r123.element);     // Catch: Throwable -> L76
        this.activeOutputSurfaceMap.putAll(((CaptureSessionFactory.Result.Success) r46).getOutputSurfaceMap());     // Catch: Throwable -> L76
        Map<StreamId, OutputConfigurationWrapper> r0 = ((CaptureSessionFactory.Result.Success) r46).getDeferred();     // Catch: Throwable -> L76
        if (r0.isEmpty() == false) goto L74;
    L91:
        Unit r124 = Unit.INSTANCE;     // Catch: Throwable -> L76
        monitor-exit(r53);
        configure(null);
        return Unit.INSTANCE;
    L74:
        if (r33.getINFO_LOGGABLE() == false) goto L78;
        android.util.Log.i("CXCP", "Created " + this + " with " + CollectionsKt.toList(((Map) r123.element).keySet()) + ". Waiting to finalize " + CollectionsKt.toList(r0.keySet()));     // Catch: Throwable -> L76
    L78:
        this.pendingOutputMap = r0;     // Catch: Throwable -> L76
        Map<StreamId, ? extends Surface> r125 = this._surfaceMap;     // Catch: Throwable -> L76
        if (r125 == null) goto L86;
        LinkedHashMap r1 = new LinkedHashMap();     // Catch: Throwable -> L76
        Iterator<Map.Entry<StreamId, ? extends Surface>> r126 = r125.entrySet().iterator();     // Catch: Throwable -> L76
    L82:
        if (r126.hasNext() == false) goto L87;
        Map.Entry<StreamId, ? extends Surface> r2 = r126.next();     // Catch: Throwable -> L76
        if (r0.containsKey(r2.getKey()) == false) goto L82;
        r1.put(r2.getKey(), r2.getValue());     // Catch: Throwable -> L76
    L87:
        if (r1 == null) goto L91;
        if (r1.size() != r0.size()) goto L91;
        this.pendingSurfaceMap = r1;     // Catch: Throwable -> L76
        goto L91
    L86:
        r1 = null;
        goto L87
    L96:
        throw new IllegalStateException(("Unexpected state: " + this.state).toString());     // Catch: Throwable -> L76
    L98:
        if (r33.getINFO_LOGGABLE() == false) goto L100;
        android.util.Log.i("CXCP", "Warning: " + this + " was " + access$getState$p(this) + " while configuration was in progress.");     // Catch: Throwable -> L76
    L100:
        Unit r11 = Unit.INSTANCE;     // Catch: Throwable -> L76
        monitor-exit(r53);
        return r11;
    L76:
        th = move-exception;
        throw th;
    L58:
        if (r33.getERROR_LOGGABLE() == false) goto L61;
        android.util.Log.e("CXCP", "Failed to create capture session for " + this + '!');
    L61:
        return Unit.INSTANCE;
    L105:
        th = move-exception;
        Trace.endSection();
        throw th;
    L52:
        r63 = null;
        goto L53
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r122);
        r123 = new Ref.ObjectRef();
        r52 = new Ref.ObjectRef();
        Object r84 = this.lock;
        monitor-enter(r84);
    L21:
        th = move-exception;
        throw th;
    L17:
        if (this.state == State.PENDING) goto L23;
        Unit r112 = Unit.INSTANCE;     // Catch: Throwable -> L21
        monitor-exit(r84);
        return r112;
    L23:
        r123.element = this._surfaceMap;     // Catch: Throwable -> L21
        ?? r9 = this._cameraDevice;     // Catch: Throwable -> L21
        r52.element = r9;     // Catch: Throwable -> L21
        if (r123.element == 0) goto L108;
        if (r9 == 0) goto L108;
        this.state = State.CREATING;     // Catch: Throwable -> L21
        this.hasAttemptedCaptureSession = true;     // Catch: Throwable -> L21
        Timestamps r92 = Timestamps.INSTANCE;     // Catch: Throwable -> L21
        this.sessionCreatingTimestamp = TimestampNs.m508boximpl(this.timeSource.mo506nowvQl9yQU());     // Catch: Throwable -> L21
        Unit r93 = Unit.INSTANCE;     // Catch: Throwable -> L21
        monitor-exit(r84);
        SessionSequencer r85 = this.sessionSequencer;
        if (r85 == null) goto L39;
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L34;
        android.util.Log.d("CXCP", "Awaiting session lock");
    L34:
        r3.L$0 = r123;
        r3.L$1 = r52;
        r3.label = 1;
        if (r85.awaitSessionLock(r3) != r42) goto L37;
        return r42;
    L37:
        r32 = r123;
        r43 = r52;
    L108:
        Unit r113 = Unit.INSTANCE;     // Catch: Throwable -> L21
        monitor-exit(r84);
        return r113;
    L7:
        r3 = new AnonymousClass1(this, r12);
        goto L8
    }

    private final void updateTrackedSurfaces(Map<StreamId, ? extends Surface> r4, Map<StreamId, ? extends Surface> r5) {
        Set r42 = CollectionsKt.toSet(r4.values());
        Set r52 = CollectionsKt.toSet(r5.values());
        Iterator r0 = SetsKt.minus(r42, r52).iterator();
    L4:
        if (r0.hasNext() == false) goto L13;
        Surface r1 = (Surface) r0.next();
        AutoCloseable r2 = this._surfaceTokenMap.remove(r1);
        if (r2 == null) goto L8;
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(r2);
    L9:
        if (r2 != null) goto L4;
        LifecycleRegistry$$ExternalSyntheticBUOutline0.m("Surface ", r1, " doesn't have a matching surface token!");
        return;
    L8:
        r2 = null;
        goto L9
    L13:
        Iterator r43 = SetsKt.minus(r52, r42).iterator();
    L15:
        if (r43.hasNext() == false) goto L17;
        Surface r53 = (Surface) r43.next();
        AutoCloseable r02 = this.cameraSurfaceManager.registerSurface$camera_camera2_pipe(r53);
        this._surfaceTokenMap.put(r53, r02);
        goto L15
    }

    public final void configureSurfaceMap(Map<StreamId, ? extends Surface> r11) {
        Object r1 = this.lock;
        monitor-enter(r1);
        State r0 = this.state;     // Catch: Throwable -> L12
        if (r0 != State.CLOSING) goto L7;
    L28:
        Unit r10 = Unit.INSTANCE;     // Catch: Throwable -> L12
        monitor-exit(r1);
        return;
    L7:
        if (r0 == State.CLOSED) goto L28;
        Map<StreamId, ? extends Surface> r02 = this._surfaceMap;     // Catch: Throwable -> L12
        if (r02 != null) goto L14;
        r02 = MapsKt.emptyMap();     // Catch: Throwable -> L12
    L14:
        updateTrackedSurfaces(r02, r11);     // Catch: Throwable -> L12
        this._surfaceMap = r11;     // Catch: Throwable -> L12
        Map<StreamId, ? extends OutputConfigurationWrapper> r03 = this.pendingOutputMap;     // Catch: Throwable -> L12
        if (r03 != null) goto L17;
    L27:
        BuildersKt.launch$default(this.scope, null, null, new CaptureSessionState$configureSurfaceMap$1$2(this, null), 3, null);     // Catch: Throwable -> L12
        goto L28
    L17:
        if (this.pendingSurfaceMap != null) goto L27;
        LinkedHashMap r3 = new LinkedHashMap();     // Catch: Throwable -> L12
        Iterator<Map.Entry<StreamId, ? extends Surface>> r112 = r11.entrySet().iterator();     // Catch: Throwable -> L12
    L20:
        if (r112.hasNext() == false) goto L25;
        Map.Entry<StreamId, ? extends Surface> r4 = r112.next();     // Catch: Throwable -> L12
        if (r03.containsKey(r4.getKey()) == false) goto L20;
        r3.put(r4.getKey(), r4.getValue());     // Catch: Throwable -> L12
        goto L20
    L25:
        if (r3.size() != r03.size()) goto L27;
        this.pendingSurfaceMap = r3;     // Catch: Throwable -> L12
        BuildersKt.launch$default(this.scope, null, null, new CaptureSessionState$configureSurfaceMap$1$1(this, null), 3, null);     // Catch: Throwable -> L12
    L12:
        th = move-exception;
        throw th;
    }

    public final void disconnect() {
        Object r0 = this.lock;
        monitor-enter(r0);
        State r1 = this.state;     // Catch: Throwable -> L12
        State r2 = State.CLOSING;     // Catch: Throwable -> L12
        if (r1 != r2) goto L7;
    L69:
        monitor-exit(r0);
        return;
    L7:
        if (r1 == State.CLOSED) goto L69;
        this.state = r2;     // Catch: Throwable -> L12
        ConfiguredCameraCaptureSession r12 = this.cameraCaptureSession;     // Catch: Throwable -> L12
        boolean r3 = false;
        if (r12 == null) goto L15;
        this.cameraCaptureSession = null;     // Catch: Throwable -> L12
    L20:
        Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L12
        monitor-exit(r0);
        SessionSequencer r02 = this.sessionSequencer;
        if (r02 == null) goto L26;
        r02.release();
    L26:
        if (r3 == false) goto L43;
        Log r03 = Log.INSTANCE;
        if (r03.getDEBUG_LOGGABLE() == false) goto L31;
        android.util.Log.d("CXCP", "Waiting for CameraCaptureSession configuration");
    L31:
        if (((Unit) this.threads.runBlockingCheckedOrNull(3000, new AnonymousClass3(this, null))) == null) goto L33;
    L35:
        Object r04 = this.lock;
        monitor-enter(r04);
        r12 = this.cameraCaptureSession;     // Catch: Throwable -> L40
        this.cameraCaptureSession = null;     // Catch: Throwable -> L40
        monitor-exit(r04);
    L40:
        th = move-exception;
        throw th;
    L33:
        if (r03.getERROR_LOGGABLE() == false) goto L35;
        android.util.Log.e("CXCP", "Waiting for CameraCaptureSession configuration timed out");
    L43:
        Debug r05 = Debug.INSTANCE;
        Trace.beginSection(access$getGraphListener$p(this) + "#onGraphStopping");
        this.graphListener.onGraphStopping();
        Trace.endSection();
        if (r12 == null) goto L66;
        GraphRequestProcessor r06 = r12.getProcessor();
        Log r32 = Log.INSTANCE;
        if (r32.getDEBUG_LOGGABLE() == false) goto L48;
        android.util.Log.d("CXCP", this + " Shutdown");
    L48:
        Trace.beginSection(this + "#shutdown");
        if (this.cameraGraphFlags.getAbortCapturesOnStop() == true) goto L51;
    L55:
        Trace.beginSection(this + "#disconnect");
        Camera2CaptureSequenceProcessor r6 = r12.getCaptureSequenceProcessor();
        if (r6 == null) goto L58;
        r6.disconnect$camera_camera2_pipe();
    L58:
        Trace.endSection();
        if (this.cameraGraphFlags.getCloseCaptureSessionOnDisconnect() == true) goto L61;
    L65:
        Trace.beginSection(access$getGraphListener$p(this) + "#onGraphStopped");
        this.graphListener.onGraphStopped(r06);
        Trace.endSection();
        Trace.endSection();
    L67:
        this.sessionDisconnected.countDown();
        return;
    L61:
        if (((Unit) this.threads.runBlockingCheckedOrNull(3000, new AnonymousClass12(this, r12, null))) != null) goto L65;
        if (r32.getERROR_LOGGABLE() == false) goto L65;
        android.util.Log.e("CXCP", "Failed to close the capture session in 3000ms");
        goto L65
    L51:
        if (((Unit) this.threads.runBlockingCheckedOrNull(2000, new AnonymousClass9(this, r06, null))) != null) goto L55;
        if (r32.getERROR_LOGGABLE() == false) goto L55;
        android.util.Log.e("CXCP", "Failed to abort captures in 2000ms");
        goto L55
    L66:
        Trace.beginSection(access$getGraphListener$p(this) + "#onGraphStopped");
        this.graphListener.onGraphStopped(null);
        Trace.endSection();
        goto L67
    L15:
        if (this.cameraGraphFlags.getCloseCaptureSessionOnDisconnect() == true) goto L17;
    L19:
        r12 = null;
        goto L20
    L17:
        if (this.hasAttemptedCaptureSession == false) goto L19;
        r3 = true;
    L12:
        th = move-exception;
        throw th;
    }

    public final void finalizeSession$camera_camera2_pipe(long r8) {
        if (r8 == 0) goto L7;
        BuildersKt.launch$default(this.scope, null, null, new CaptureSessionState$finalizeSession$1(r8, this, null), 3, null);
        return;
    L7:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L9;
        android.util.Log.d("CXCP", "Finalizing " + this);
    L9:
        Object r82 = this.lock;
        monitor-enter(r82);
        List r9 = CollectionsKt.toList(this._surfaceTokenMap.values());     // Catch: Throwable -> L18
        this._surfaceTokenMap.clear();     // Catch: Throwable -> L18
        monitor-exit(r82);
        Iterator r7 = r9.iterator();
    L15:
        if (r7.hasNext() == false) goto L17;
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m((AutoCloseable) r7.next());
        goto L15
    L17:
        return;
    L18:
        th = move-exception;
        throw th;
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper.StateCallback
    public void onActive(CameraCaptureSessionWrapper r1) {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L6;
        android.util.Log.d("CXCP", this + " Active");
        return;
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper.StateCallback
    public void onCaptureQueueEmpty(CameraCaptureSessionWrapper r1) {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L6;
        android.util.Log.d("CXCP", this + " CaptureQueueEmpty");
        return;
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper.StateCallback
    public void onClosed(CameraCaptureSessionWrapper r2) {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", this + " Closed");
    L5:
        Debug r22 = Debug.INSTANCE;
        Trace.beginSection(this + "#onClosed");
        shutdown();
        this.captureSessionAttemptCompleted.countDown();
        SessionSequencer r1 = this.sessionSequencer;
        if (r1 == null) goto L8;
        r1.release();
    L8:
        Trace.endSection();
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper.StateCallback
    public void onConfigureFailed(CameraCaptureSessionWrapper r5) {
        if (Log.INSTANCE.getWARN_LOGGABLE() == false) goto L5;
        android.util.Log.w("CXCP", this + " Configuration Failed");
    L5:
        Debug r52 = Debug.INSTANCE;
        Trace.beginSection(this + "#onConfigureFailed");
        this.graphListener.onGraphError(new GraphState.GraphStateError(CameraError.Companion.m195getERROR_GRAPH_CONFIGv7Vf74A(), false, null));
        shutdown();
        this.captureSessionAttemptCompleted.countDown();
        SessionSequencer r4 = this.sessionSequencer;
        if (r4 == null) goto L8;
        r4.release();
    L8:
        Trace.endSection();
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper.StateCallback
    public void onConfigured(CameraCaptureSessionWrapper r3) {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", this + " Configured");
    L5:
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection(this + "#configure");
        configure(r3);
        this.captureSessionAttemptCompleted.countDown();
        SessionSequencer r2 = this.sessionSequencer;
        if (r2 == null) goto L8;
        r2.release();
    L8:
        Trace.endSection();
    }

    @Override // androidx.camera.camera2.pipe.compat.CameraCaptureSessionWrapper.StateCallback
    public void onReady(CameraCaptureSessionWrapper r1) {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L6;
        android.util.Log.d("CXCP", this + " Ready");
        return;
    }

    @Override // androidx.camera.camera2.pipe.compat.SessionStateCallback
    public void onSessionDisconnected() {
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L5;
        android.util.Log.d("CXCP", this + " session disconnecting");
    L5:
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection(this + "#onSessionDisconnected");
        disconnect();
        Trace.beginSection(this + "#onSessionDisconnected Await");     // Catch: Throwable -> L9
        access$getSessionDisconnected$p(this).await();     // Catch: Throwable -> L9
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L9
        Trace.endSection();
        Trace.endSection();
        return;
    L9:
        th = move-exception;
        Trace.endSection();
        throw th;
    }

    @Override // androidx.camera.camera2.pipe.compat.SessionStateCallback
    public void onSessionFinalized() {
        if (this.finalized.compareAndSet(Boolean.FALSE, Boolean.TRUE) == true) goto L5;
        return;
    L5:
        if (Log.INSTANCE.getDEBUG_LOGGABLE() == false) goto L7;
        android.util.Log.d("CXCP", this + " session finalizing");
    L7:
        Debug r0 = Debug.INSTANCE;
        Trace.beginSection(this + "#onSessionFinalized");
        shutdown();
        finalizeSession$camera_camera2_pipe(0);
        Trace.endSection();
    }

    public final void setCameraDevice(CameraDeviceWrapper r10) {
        Object r1 = this.lock;
        monitor-enter(r1);
        State r0 = this.state;     // Catch: Throwable -> L12
        if (r0 != State.CLOSING) goto L7;
    L17:
        monitor-exit(r1);
        return;
    L7:
        if (r0 == State.CLOSED) goto L17;
        this._cameraDevice = r10;     // Catch: Throwable -> L12
        if (r10 == null) goto L14;
        BuildersKt.launch$default(this.scope, null, null, new CaptureSessionState$cameraDevice$2$1(this, null), 3, null);     // Catch: Throwable -> L12
    L14:
        Unit r9 = Unit.INSTANCE;     // Catch: Throwable -> L12
        monitor-exit(r1);
        return;
    L12:
        th = move-exception;
        throw th;
    }

    public final void shutdown() {
        disconnect();
        Object r0 = this.lock;
        monitor-enter(r0);
        State r1 = this.state;     // Catch: Throwable -> L17
        State r2 = State.CLOSED;     // Catch: Throwable -> L17
        long r3 = 0;
        if (r1 == r2) goto L19;
        boolean r5 = true;
        if (this._cameraDevice != null) goto L9;
    L20:
        this._cameraDevice = null;     // Catch: Throwable -> L17
        this.state = r2;     // Catch: Throwable -> L17
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L17
        monitor-exit(r0);
        if (r5 == false) goto L29;
        finalizeSession$camera_camera2_pipe(r3);
        return;
    L29:
        return;
    L9:
        if (this.hasAttemptedCaptureSession == false) goto L20;
        int r13 = this.cameraGraphFlags.m206getFinalizeSessionOnCloseBehaviorBm6Tfm4();     // Catch: Throwable -> L17
        CameraGraph.Flags.FinalizeSessionOnCloseBehavior.Companion r6 = CameraGraph.Flags.FinalizeSessionOnCloseBehavior.Companion;     // Catch: Throwable -> L17
        if (CameraGraph.Flags.FinalizeSessionOnCloseBehavior.m208equalsimpl0(r13, r6.m211getIMMEDIATEBm6Tfm4()) == true) goto L20;
        if (CameraGraph.Flags.FinalizeSessionOnCloseBehavior.m208equalsimpl0(r13, r6.m213getTIMEOUTBm6Tfm4()) == false) goto L19;
        r3 = 2000;
    L19:
        r5 = false;
    L17:
        th = move-exception;
        throw th;
    }

    public String toString() {
        return "CaptureSessionState-" + this.debugId;
    }
}
