package androidx.camera.camera2.impl;

import android.hardware.camera2.CaptureResult;
import android.util.Log;
import androidx.camera.camera2.adapter.CaptureConfigAdapter;
import androidx.camera.camera2.adapter.CaptureResultAdapter;
import androidx.camera.camera2.compat.workaround.FlashAvailabilityCheckerKt;
import androidx.camera.camera2.compat.workaround.UseTorchAsFlash;
import androidx.camera.camera2.config.UseCaseGraphContext;
import androidx.camera.camera2.impl.TorchControl;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.FrameInfo;
import androidx.camera.camera2.pipe.FrameMetadata;
import androidx.camera.camera2.pipe.Lock3ABehavior;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestTemplate;
import androidx.camera.camera2.pipe.Result3A;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ConvergenceUtils;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.inject.Provider;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jdk7.AutoCloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.TimeoutKt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000Ë\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u001c\n\u0002\b\u0006*\u0001x\b\u0007\u0018\u00002\u00020\u0001:\u0002{|B_\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\r\u001a\u00020\f\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0011\u001a\u00020\u0010\u0012\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012\u0012\u0006\u0010\u0016\u001a\u00020\u0015¢\u0006\u0004\b\u0017\u0010\u0018J\u0012\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0082@¢\u0006\u0004\b\u001a\u0010\u001bJN\u0010'\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010&0%0\u001c2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020\u001f2\b\u0010$\u001a\u0004\u0018\u00010#H\u0082@¢\u0006\u0004\b'\u0010(JF\u0010)\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010&0%0\u001c2\b\u0010$\u001a\u0004\u0018\u00010#2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0082@¢\u0006\u0004\b)\u0010*JF\u0010+\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010&0%0\u001c2\b\u0010$\u001a\u0004\u0018\u00010#2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0082@¢\u0006\u0004\b+\u0010*J>\u0010,\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010&0%0\u001c2\b\u0010$\u001a\u0004\u0018\u00010#2\u0006\u0010 \u001a\u00020\u001f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0082@¢\u0006\u0004\b,\u0010-JN\u00102\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010&0%0\u001c2\b\u0010$\u001a\u0004\u0018\u00010#2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010/\u001a\u00020.2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u00101\u001a\u000200H\u0082@¢\u0006\u0004\b2\u00103JF\u00104\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010&0%0\u001c2\b\u0010$\u001a\u0004\u0018\u00010#2\u0006\u0010/\u001a\u00020.2\u0006\u0010 \u001a\u00020\u001f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0082@¢\u0006\u0004\b4\u00105J>\u00106\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010&0%0\u001c2\b\u0010$\u001a\u0004\u0018\u00010#2\u0006\u0010 \u001a\u00020\u001f2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0082@¢\u0006\u0004\b6\u0010-J \u0010:\u001a\u0002092\u0006\u00107\u001a\u00020.2\u0006\u00108\u001a\u000200H\u0082@¢\u0006\u0004\b:\u0010;J2\u0010@\u001a\u001d\u0012\u0013\u0012\u00110\u0019¢\u0006\f\b=\u0012\b\b>\u0012\u0004\b\b(?\u0012\u0004\u0012\u0002000<2\u0006\u00108\u001a\u000200H\u0002¢\u0006\u0004\b@\u0010AJ\u0013\u0010C\u001a\u00020B*\u00020\u0019H\u0002¢\u0006\u0004\bC\u0010DJ\u0018\u0010E\u001a\u0002092\u0006\u0010/\u001a\u00020.H\u0082@¢\u0006\u0004\bE\u0010FJ%\u0010H\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010&0%0\u001c2\u0006\u0010G\u001a\u00020#H\u0002¢\u0006\u0004\bH\u0010IJ\u0018\u0010J\u001a\u0002002\u0006\u0010!\u001a\u00020\u001fH\u0082@¢\u0006\u0004\bJ\u0010KJ?\u0010P\u001a\u0004\u0018\u00010M2\u0006\u0010L\u001a\u00020.2#\b\u0002\u0010O\u001a\u001d\u0012\u0013\u0012\u00110M¢\u0006\f\b=\u0012\b\b>\u0012\u0004\b\b(N\u0012\u0004\u0012\u0002000<H\u0082@¢\u0006\u0004\bP\u0010QJ\u0018\u00108\u001a\u0002002\u0006\u0010\"\u001a\u00020\u001fH\u0082@¢\u0006\u0004\b8\u0010KJT\u0010Z\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010&0%0\u001c2\f\u0010S\u001a\b\u0012\u0004\u0012\u00020R0\u001c2\u0006\u0010U\u001a\u00020T2\u0006\u0010W\u001a\u00020V2\u0006\u0010 \u001a\u00020\u001f2\u0006\u0010\"\u001a\u00020\u001f2\u0006\u0010!\u001a\u00020\u001fH\u0096@¢\u0006\u0004\bX\u0010YJ\u0018\u0010\\\u001a\u00020[2\u0006\u0010 \u001a\u00020\u001fH\u0087@¢\u0006\u0004\b\\\u0010KJ\u0018\u0010]\u001a\u00020[2\u0006\u0010 \u001a\u00020\u001fH\u0087@¢\u0006\u0004\b]\u0010KR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010^R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0005\u0010_R\u0014\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0007\u0010`R\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010aR\u0014\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000b\u0010bR\u0014\u0010\r\u001a\u00020\f8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\r\u0010cR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000f\u0010dR\u001a\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00130\u00128\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0014\u0010eR\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010fR\u001b\u0010k\u001a\u0002008BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bg\u0010h\u001a\u0004\bi\u0010jR#\u0010p\u001a\n l*\u0004\u0018\u00010\u00130\u00138BX\u0082\u0084\u0002¢\u0006\f\n\u0004\bm\u0010h\u001a\u0004\bn\u0010oR\"\u0010q\u001a\u00020\u001f8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bq\u0010r\u001a\u0004\bs\u0010t\"\u0004\bu\u0010vR\u0018\u0010?\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b?\u0010wR\u0014\u0010y\u001a\u00020x8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\by\u0010z¨\u0006}"}, d2 = {"Landroidx/camera/camera2/impl/CapturePipelineImpl;", "Landroidx/camera/camera2/impl/CapturePipeline;", "Landroidx/camera/camera2/adapter/CaptureConfigAdapter;", "configAdapter", "Landroidx/camera/camera2/impl/FlashControl;", "flashControl", "Landroidx/camera/camera2/impl/TorchControl;", "torchControl", "Landroidx/camera/camera2/impl/VideoUsageControl;", "videoUsageControl", "Landroidx/camera/camera2/impl/UseCaseThreads;", "threads", "Landroidx/camera/camera2/impl/ComboRequestListener;", "requestListener", "Landroidx/camera/camera2/compat/workaround/UseTorchAsFlash;", "useTorchAsFlash", "Landroidx/camera/camera2/impl/CameraProperties;", "cameraProperties", "Ljavax/inject/Provider;", "Landroidx/camera/camera2/impl/UseCaseCameraState;", "useCaseCameraStateProvider", "Landroidx/camera/camera2/config/UseCaseGraphContext;", "useCaseGraphContext", "<init>", "(Landroidx/camera/camera2/adapter/CaptureConfigAdapter;Landroidx/camera/camera2/impl/FlashControl;Landroidx/camera/camera2/impl/TorchControl;Landroidx/camera/camera2/impl/VideoUsageControl;Landroidx/camera/camera2/impl/UseCaseThreads;Landroidx/camera/camera2/impl/ComboRequestListener;Landroidx/camera/camera2/compat/workaround/UseTorchAsFlash;Landroidx/camera/camera2/impl/CameraProperties;Ljavax/inject/Provider;Landroidx/camera/camera2/config/UseCaseGraphContext;)V", "Landroidx/camera/camera2/pipe/FrameMetadata;", "getFrameMetadata", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/impl/CapturePipelineImpl$PipelineTask;", "pipelineTasks", _UrlKt.FRAGMENT_ENCODE_SET, "captureMode", "flashMode", "flashType", "Landroidx/camera/camera2/impl/CapturePipelineImpl$MainCaptureParams;", "mainCaptureParams", "Lkotlinx/coroutines/Deferred;", "Ljava/lang/Void;", "invokeCaptureTasks", "(Ljava/util/List;IIILandroidx/camera/camera2/impl/CapturePipelineImpl$MainCaptureParams;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "torchAsFlashCapture", "(Landroidx/camera/camera2/impl/CapturePipelineImpl$MainCaptureParams;IILjava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "defaultCapture", "defaultNoFlashCapture", "(Landroidx/camera/camera2/impl/CapturePipelineImpl$MainCaptureParams;ILjava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "timeLimitNs", _UrlKt.FRAGMENT_ENCODE_SET, "triggerAePreCapture", "torchApplyCapture", "(Landroidx/camera/camera2/impl/CapturePipelineImpl$MainCaptureParams;IJLjava/util/List;ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "aePreCaptureApplyCapture", "(Landroidx/camera/camera2/impl/CapturePipelineImpl$MainCaptureParams;JILjava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "screenFlashCapture", "convergedTimeLimitNs", "isTorchAsFlash", "Landroidx/camera/camera2/pipe/Result3A;", "lockAf", "(JZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "frameMetadata", "getConvergeCondition", "(Z)Lkotlin/jvm/functions/Function1;", "Landroidx/camera/core/impl/CameraCaptureResult;", "toCameraCaptureResult", "(Landroidx/camera/camera2/pipe/FrameMetadata;)Landroidx/camera/core/impl/CameraCaptureResult;", "unlockAf", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", PluginsConstants.PARAMS, "submitRequestInternal", "(Landroidx/camera/camera2/impl/CapturePipelineImpl$MainCaptureParams;)Ljava/util/List;", "isPhysicalFlashRequired", "(ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "waitTimeoutNanos", "Landroidx/camera/camera2/pipe/FrameInfo;", "totalCaptureResult", "checker", "waitForResult", "(JLkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/camera/core/impl/CaptureConfig;", "configs", "Landroidx/camera/camera2/pipe/RequestTemplate;", "requestTemplate", "Landroidx/camera/core/impl/Config;", "sessionConfigOptions", "submitStillCaptures-BvXKQx0", "(Ljava/util/List;ILandroidx/camera/core/impl/Config;IIILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "submitStillCaptures", _UrlKt.FRAGMENT_ENCODE_SET, "invokeScreenFlashPreCaptureTasks", "invokeScreenFlashPostCaptureTasks", "Landroidx/camera/camera2/adapter/CaptureConfigAdapter;", "Landroidx/camera/camera2/impl/FlashControl;", "Landroidx/camera/camera2/impl/TorchControl;", "Landroidx/camera/camera2/impl/VideoUsageControl;", "Landroidx/camera/camera2/impl/UseCaseThreads;", "Landroidx/camera/camera2/impl/ComboRequestListener;", "Landroidx/camera/camera2/compat/workaround/UseTorchAsFlash;", "Ljavax/inject/Provider;", "Landroidx/camera/camera2/config/UseCaseGraphContext;", "hasFlashUnit$delegate", "Lkotlin/Lazy;", "getHasFlashUnit", "()Z", "hasFlashUnit", "kotlin.jvm.PlatformType", "useCaseCameraState$delegate", "getUseCaseCameraState", "()Landroidx/camera/camera2/impl/UseCaseCameraState;", "useCaseCameraState", "template", "I", "getTemplate", "()I", "setTemplate", "(I)V", "Landroidx/camera/camera2/pipe/FrameMetadata;", "androidx/camera/camera2/impl/CapturePipelineImpl$emptyRequestMetadata$1", "emptyRequestMetadata", "Landroidx/camera/camera2/impl/CapturePipelineImpl$emptyRequestMetadata$1;", "PipelineTask", "MainCaptureParams", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCapturePipeline.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CapturePipeline.kt\nandroidx/camera/camera2/impl/CapturePipelineImpl\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 4 UseCaseCameraConfig.kt\nandroidx/camera/camera2/config/UseCaseGraphContext\n+ 5 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 6 UseCaseThreads.kt\nandroidx/camera/camera2/impl/UseCaseThreads\n*L\n1#1,870:1\n291#1:912\n292#1,3:917\n295#1:928\n297#1,11:933\n320#1:944\n291#1:949\n292#1,3:954\n295#1:991\n297#1,11:996\n320#1:1007\n291#1:1012\n292#1,3:1017\n295#1:1034\n297#1,11:1039\n320#1:1050\n291#1:1055\n292#1,16:1060\n320#1:1076\n85#2,4:871\n85#2,4:875\n85#2,4:879\n85#2,4:884\n85#2,4:888\n85#2,4:892\n85#2,4:896\n85#2,4:900\n85#2,4:904\n85#2,4:908\n85#2,4:913\n85#2,4:920\n85#2,4:924\n85#2,4:929\n85#2,4:945\n85#2,4:950\n85#2,4:957\n85#2,4:961\n85#2,4:965\n85#2,4:971\n85#2,4:975\n85#2,4:979\n85#2,4:983\n85#2,4:987\n85#2,4:992\n85#2,4:1008\n85#2,4:1013\n85#2,4:1020\n85#2,4:1026\n85#2,4:1030\n85#2,4:1035\n85#2,4:1051\n85#2,4:1056\n85#2,4:1079\n85#2,4:1083\n85#2,4:1087\n85#2,4:1093\n85#2,4:1097\n85#2,4:1105\n112#2,4:1119\n1#3:883\n1#3:970\n1#3:1025\n1#3:1078\n1#3:1092\n1#3:1102\n1#3:1104\n1#3:1123\n242#4:969\n242#4:1024\n242#4:1077\n242#4:1091\n242#4:1101\n242#4:1103\n1617#5,9:1109\n1869#5:1118\n1870#5:1124\n1626#5:1125\n194#6:1126\n*S KotlinDebug\n*F\n+ 1 CapturePipeline.kt\nandroidx/camera/camera2/impl/CapturePipelineImpl\n*L\n373#1:912\n373#1:917,3\n373#1:928\n373#1:933,11\n373#1:944\n403#1:949\n403#1:954,3\n403#1:991\n403#1:996,11\n403#1:1007\n486#1:1012\n486#1:1017,3\n486#1:1034\n486#1:1039,11\n486#1:1050\n526#1:1055\n526#1:1060,16\n526#1:1076\n175#1:871,4\n178#1:875,4\n199#1:879,4\n291#1:884,4\n293#1:888,4\n295#1:892,4\n298#1:896,4\n300#1:900,4\n329#1:904,4\n371#1:908,4\n373#1:913,4\n377#1:920,4\n379#1:924,4\n373#1:929,4\n399#1:945,4\n403#1:950,4\n407#1:957,4\n409#1:961,4\n413#1:965,4\n423#1:971,4\n435#1:975,4\n437#1:979,4\n439#1:983,4\n446#1:987,4\n403#1:992,4\n484#1:1008,4\n486#1:1013,4\n489#1:1020,4\n493#1:1026,4\n500#1:1030,4\n486#1:1035,4\n524#1:1051,4\n526#1:1056,4\n548#1:1079,4\n557#1:1083,4\n566#1:1087,4\n568#1:1093,4\n571#1:1097,4\n652#1:1105,4\n703#1:1119,4\n415#1:970\n492#1:1025\n546#1:1078\n567#1:1092\n584#1:1102\n648#1:1104\n657#1:1123\n415#1:969\n492#1:1024\n546#1:1077\n567#1:1091\n584#1:1101\n648#1:1103\n657#1:1109,9\n657#1:1118\n657#1:1124\n657#1:1125\n723#1:1126\n*E\n"})
/* loaded from: classes3.dex */
public final class CapturePipelineImpl implements CapturePipeline {
    private final CaptureConfigAdapter configAdapter;
    private final CapturePipelineImpl$emptyRequestMetadata$1 emptyRequestMetadata;
    private final FlashControl flashControl;
    private FrameMetadata frameMetadata;
    private final Lazy hasFlashUnit$delegate;
    private final ComboRequestListener requestListener;
    private int template;
    private final UseCaseThreads threads;
    private final TorchControl torchControl;
    private final Lazy useCaseCameraState$delegate;
    private final Provider<UseCaseCameraState> useCaseCameraStateProvider;
    private final UseCaseGraphContext useCaseGraphContext;
    private final UseTorchAsFlash useTorchAsFlash;
    private final VideoUsageControl videoUsageControl;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\b\u0082\b\u0018\u00002\u00020\u0001B%\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\f\u001a\u00020\u000bHÖ\u0001¢\u0006\u0004\b\f\u0010\rJ\u0010\u0010\u000f\u001a\u00020\u000eHÖ\u0001¢\u0006\u0004\b\u000f\u0010\u0010J\u001a\u0010\u0013\u001a\u00020\u00122\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0013\u0010\u0014R\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0006\u001a\u00020\u00058\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u0018\u001a\u0004\b\u0019\u0010\u0010R\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001a\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Landroidx/camera/camera2/impl/CapturePipelineImpl$MainCaptureParams;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/CaptureConfig;", "configs", "Landroidx/camera/camera2/pipe/RequestTemplate;", "requestTemplate", "Landroidx/camera/core/impl/Config;", "sessionConfigOptions", "<init>", "(Ljava/util/List;ILandroidx/camera/core/impl/Config;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Ljava/util/List;", "getConfigs", "()Ljava/util/List;", "I", "getRequestTemplate-fGx8uWA", "Landroidx/camera/core/impl/Config;", "getSessionConfigOptions", "()Landroidx/camera/core/impl/Config;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class MainCaptureParams {
        private final List<CaptureConfig> configs;
        private final int requestTemplate;
        private final Config sessionConfigOptions;

        private MainCaptureParams(List<CaptureConfig> r1, int r2, Config r3) {
            this.configs = r1;
            this.requestTemplate = r2;
            this.sessionConfigOptions = r3;
        }

        public /* synthetic */ MainCaptureParams(List r1, int r2, Config r3, DefaultConstructorMarker r4) {
            this(r1, r2, r3);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof MainCaptureParams) == true) goto L8;
            return false;
        L8:
            MainCaptureParams r52 = (MainCaptureParams) r5;
            if (Intrinsics.areEqual(this.configs, r52.configs) == true) goto L12;
            return false;
        L12:
            if (RequestTemplate.m373equalsimpl0(this.requestTemplate, r52.requestTemplate) == true) goto L15;
            return false;
        L15:
            if (Intrinsics.areEqual(this.sessionConfigOptions, r52.sessionConfigOptions) == true) goto L17;
            return false;
        L17:
            return true;
        }

        public final List<CaptureConfig> getConfigs() {
            return this.configs;
        }

        /* renamed from: getRequestTemplate-fGx8uWA, reason: not valid java name */
        public final int m66getRequestTemplatefGx8uWA() {
            return this.requestTemplate;
        }

        public final Config getSessionConfigOptions() {
            return this.sessionConfigOptions;
        }

        public int hashCode() {
            return (((this.configs.hashCode() * 31) + RequestTemplate.m375hashCodeimpl(this.requestTemplate)) * 31) + this.sessionConfigOptions.hashCode();
        }

        public String toString() {
            return "MainCaptureParams(configs=" + this.configs + ", requestTemplate=" + RequestTemplate.m376toStringimpl(this.requestTemplate) + ", sessionConfigOptions=" + this.sessionConfigOptions + ')';
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0006\b\u0082\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006¨\u0006\u0007"}, d2 = {"Landroidx/camera/camera2/impl/CapturePipelineImpl$PipelineTask;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "PRE_CAPTURE", "MAIN_CAPTURE", "POST_CAPTURE", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public enum PipelineTask extends Enum<PipelineTask> {
        private static final /* synthetic */ EnumEntries $ENTRIES = null;
        private static final /* synthetic */ PipelineTask[] $VALUES = null;
        public static final PipelineTask MAIN_CAPTURE = null;
        public static final PipelineTask POST_CAPTURE = null;
        public static final PipelineTask PRE_CAPTURE = null;

        private static final /* synthetic */ PipelineTask[] $values() {
            return new PipelineTask[]{PRE_CAPTURE, MAIN_CAPTURE, POST_CAPTURE};
        }

        static {
            PRE_CAPTURE = new PipelineTask("PRE_CAPTURE", 0);
            MAIN_CAPTURE = new PipelineTask("MAIN_CAPTURE", 1);
            POST_CAPTURE = new PipelineTask("POST_CAPTURE", 2);
            PipelineTask[] r0 = $values();
            $VALUES = r0;
            $ENTRIES = EnumEntriesKt.enumEntries(r0);
        }

        PipelineTask(String r1, int r2) {
        }

        public static PipelineTask valueOf(String r1) {
            return (PipelineTask) Enum.valueOf(PipelineTask.class, r1);
        }

        public static PipelineTask[] values() {
            return (PipelineTask[]) $VALUES.clone();
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl", f = "CapturePipeline.kt", i = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2}, l = {887, 494, 499}, m = "aePreCaptureApplyCapture", n = {"this_$iv", "$this$invoke$iv", "mainCaptureParams$iv", "timeLimitNs", "captureMode", "this_$iv", "$this$invoke$iv", "mainCaptureParams$iv", "captureMode", "this_$iv", "$this$invoke$iv", "mainCaptureParams$iv", "captureMode"}, s = {"L$0", "L$1", "L$2", "J$0", "I$0", "L$0", "L$1", "L$2", "I$0", "L$0", "L$1", "L$2", "I$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.CapturePipelineImpl$aePreCaptureApplyCapture$1, reason: invalid class name */
    public static final class AnonymousClass1 extends ContinuationImpl {
        int I$0;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ CapturePipelineImpl this$0;

        public AnonymousClass1(CapturePipelineImpl r1, Continuation<? super AnonymousClass1> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r8) {
            this.result = r8;
            this.label |= Integer.MIN_VALUE;
            return CapturePipelineImpl.access$aePreCaptureApplyCapture(this.this$0, null, 0, 0, null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl", f = "CapturePipeline.kt", i = {0, 0, 0}, l = {352, 357, 359, 362}, m = "defaultCapture", n = {"mainCaptureParams", "pipelineTasks", "captureMode"}, s = {"L$0", "L$1", "I$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.CapturePipelineImpl$defaultCapture$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00711 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ CapturePipelineImpl this$0;

        public C00711(CapturePipelineImpl r1, Continuation<? super C00711> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r7) {
            this.result = r7;
            this.label |= Integer.MIN_VALUE;
            return CapturePipelineImpl.access$defaultCapture(this.this$0, null, 0, 0, null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl", f = "CapturePipeline.kt", i = {0, 0, 0, 0}, l = {378}, m = "defaultNoFlashCapture", n = {"this_$iv", "$this$invoke$iv", "mainCaptureParams$iv", "lock3ARequired"}, s = {"L$0", "L$1", "L$2", "I$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.CapturePipelineImpl$defaultNoFlashCapture$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00721 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ CapturePipelineImpl this$0;

        public C00721(CapturePipelineImpl r1, Continuation<? super C00721> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r3) {
            this.result = r3;
            this.label |= Integer.MIN_VALUE;
            return CapturePipelineImpl.access$defaultNoFlashCapture(this.this$0, null, 0, null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl", f = "CapturePipeline.kt", i = {}, l = {176}, m = "getFrameMetadata", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.CapturePipelineImpl$getFrameMetadata$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00731 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ CapturePipelineImpl this$0;

        public C00731(CapturePipelineImpl r1, Continuation<? super C00731> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return CapturePipelineImpl.access$getFrameMetadata(this.this$0, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl", f = "CapturePipeline.kt", i = {1, 1, 1, 1}, l = {Opcodes.ADD_INT_LIT8, Opcodes.RSUB_INT_LIT8, Opcodes.MUL_INT_LIT8, Opcodes.REM_INT_LIT8}, m = "invokeCaptureTasks", n = {"pipelineTasks", "mainCaptureParams", "captureMode", "flashMode"}, s = {"L$0", "L$1", "I$0", "I$1"}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.CapturePipelineImpl$invokeCaptureTasks$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00741 extends ContinuationImpl {
        int I$0;
        int I$1;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ CapturePipelineImpl this$0;

        public C00741(CapturePipelineImpl r1, Continuation<? super C00741> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r8) {
            this.result = r8;
            this.label |= Integer.MIN_VALUE;
            return CapturePipelineImpl.access$invokeCaptureTasks(this.this$0, null, 0, 0, 0, null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl", f = "CapturePipeline.kt", i = {0, 1}, l = {563, 875, 570}, m = "invokeScreenFlashPostCaptureTasks", n = {"captureMode", "captureMode"}, s = {"I$0", "I$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.CapturePipelineImpl$invokeScreenFlashPostCaptureTasks$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00751 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ CapturePipelineImpl this$0;

        public C00751(CapturePipelineImpl r1, Continuation<? super C00751> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.invokeScreenFlashPostCaptureTasks(0, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl", f = "CapturePipeline.kt", i = {0, 1}, l = {544, 871, 551, 556}, m = "invokeScreenFlashPreCaptureTasks", n = {"captureMode", "captureMode"}, s = {"I$0", "I$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.CapturePipelineImpl$invokeScreenFlashPreCaptureTasks$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00761 extends ContinuationImpl {
        int I$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ CapturePipelineImpl this$0;

        public C00761(CapturePipelineImpl r1, Continuation<? super C00761> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return this.this$0.invokeScreenFlashPreCaptureTasks(0, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl", f = "CapturePipeline.kt", i = {}, l = {772}, m = "isPhysicalFlashRequired", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.CapturePipelineImpl$isPhysicalFlashRequired$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00771 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ CapturePipelineImpl this$0;

        public C00771(CapturePipelineImpl r1, Continuation<? super C00771> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return CapturePipelineImpl.access$isPhysicalFlashRequired(this.this$0, 0, this);
        }
    }

    @Metadata(d1 = {"\u0000\u0006\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001H\n"}, d2 = {"<anonymous>", "Landroidx/camera/camera2/pipe/FrameMetadata;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl$isTorchAsFlash$2", f = "CapturePipeline.kt", i = {}, l = {808}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.CapturePipelineImpl$isTorchAsFlash$2, reason: invalid class name */
    public static final class AnonymousClass2 extends SuspendLambda implements Function1<Continuation<? super FrameMetadata>, Object> {
        int label;
        final /* synthetic */ CapturePipelineImpl this$0;

        public AnonymousClass2(CapturePipelineImpl r1, Continuation<? super AnonymousClass2> r2) {
            this.this$0 = r1;
            super(1, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Continuation<?> r2) {
            return new AnonymousClass2(this.this$0, r2);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Object invoke(Continuation<? super FrameMetadata> r1) {
            return invoke2(r1);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(Continuation<? super FrameMetadata> r1) {
            return ((AnonymousClass2) create(r1)).invokeSuspend(Unit.INSTANCE);
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
            CapturePipelineImpl r42 = this.this$0;
            this.label = 1;
            Object r3 = CapturePipelineImpl.access$getFrameMetadata(r42, this);
            if (r3 != r0) goto L12;
            return r0;
        L12:
            return r3;
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl", f = "CapturePipeline.kt", i = {0, 0}, l = {871, 585, 594}, m = "lockAf", n = {"convergedTimeLimitNs", "isTorchAsFlash"}, s = {"J$0", "Z$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.CapturePipelineImpl$lockAf$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00781 extends ContinuationImpl {
        long J$0;
        Object L$0;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ CapturePipelineImpl this$0;

        public C00781(CapturePipelineImpl r1, Continuation<? super C00781> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            this.result = r4;
            this.label |= Integer.MIN_VALUE;
            return CapturePipelineImpl.access$lockAf(this.this$0, 0, false, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl", f = "CapturePipeline.kt", i = {0, 0, 0, 0}, l = {528}, m = "screenFlashCapture", n = {"this_$iv", "$this$invoke$iv", "mainCaptureParams$iv", "captureMode"}, s = {"L$0", "L$1", "L$2", "I$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.CapturePipelineImpl$screenFlashCapture$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00791 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        Object L$2;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ CapturePipelineImpl this$0;

        public C00791(CapturePipelineImpl r1, Continuation<? super C00791> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r3) {
            this.result = r3;
            this.label |= Integer.MIN_VALUE;
            return CapturePipelineImpl.access$screenFlashCapture(this.this$0, null, 0, null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl", f = "CapturePipeline.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4, 4, 5, 5, 5, 5, 5, 5, 5}, l = {408, 895, 416, 421, 436, 440}, m = "torchApplyCapture", n = {"this_$iv", "$this$invoke$iv", "mainCaptureParams$iv", "captureMode", "timeLimitNs", "triggerAePreCapture", "torchOnRequired", "lock3ARequired", "this_$iv", "$this$invoke$iv", "mainCaptureParams$iv", "captureMode", "timeLimitNs", "triggerAePreCapture", "torchOnRequired", "lock3ARequired", "this_$iv", "$this$invoke$iv", "mainCaptureParams$iv", "captureMode", "triggerAePreCapture", "torchOnRequired", "lock3ARequired", "this_$iv", "$this$invoke$iv", "mainCaptureParams$iv", "captureMode", "triggerAePreCapture", "torchOnRequired", "lock3ARequired", "this_$iv", "$this$invoke$iv", "mainCaptureParams$iv", "captureMode", "triggerAePreCapture", "torchOnRequired", "lock3ARequired", "this_$iv", "$this$invoke$iv", "mainCaptureParams$iv", "captureMode", "triggerAePreCapture", "torchOnRequired", "lock3ARequired"}, s = {"L$0", "L$1", "L$2", "I$0", "J$0", "Z$0", "I$1", "I$2", "L$0", "L$1", "L$2", "I$0", "J$0", "Z$0", "I$1", "I$2", "L$0", "L$1", "L$2", "I$0", "Z$0", "I$1", "I$2", "L$0", "L$1", "L$2", "I$0", "Z$0", "I$1", "I$2", "L$0", "L$1", "L$2", "I$0", "Z$0", "I$1", "I$2", "L$0", "L$1", "L$2", "I$0", "Z$0", "I$1", "I$2"}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.CapturePipelineImpl$torchApplyCapture$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00801 extends ContinuationImpl {
        int I$0;
        int I$1;
        int I$2;
        long J$0;
        Object L$0;
        Object L$1;
        Object L$2;
        Object L$3;
        boolean Z$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ CapturePipelineImpl this$0;

        public C00801(CapturePipelineImpl r1, Continuation<? super C00801> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r9) {
            this.result = r9;
            this.label |= Integer.MIN_VALUE;
            return CapturePipelineImpl.access$torchApplyCapture(this.this$0, null, 0, 0, null, false, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl", f = "CapturePipeline.kt", i = {0, 0, 0}, l = {330, MediaError.DetailedErrorCode.SMOOTH_NETWORK, 341}, m = "torchAsFlashCapture", n = {"mainCaptureParams", "pipelineTasks", "captureMode"}, s = {"L$0", "L$1", "I$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.CapturePipelineImpl$torchAsFlashCapture$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00811 extends ContinuationImpl {
        int I$0;
        Object L$0;
        Object L$1;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ CapturePipelineImpl this$0;

        public C00811(CapturePipelineImpl r1, Continuation<? super C00811> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r7) {
            this.result = r7;
            this.label |= Integer.MIN_VALUE;
            return CapturePipelineImpl.access$torchAsFlashCapture(this.this$0, null, 0, 0, null, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl", f = "CapturePipeline.kt", i = {0}, l = {871, 648, 649}, m = "unlockAf", n = {"timeLimitNs"}, s = {"J$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.CapturePipelineImpl$unlockAf$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00821 extends ContinuationImpl {
        long J$0;
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ CapturePipelineImpl this$0;

        public C00821(CapturePipelineImpl r1, Continuation<? super C00821> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r3) {
            this.result = r3;
            this.label |= Integer.MIN_VALUE;
            return CapturePipelineImpl.access$unlockAf(this.this$0, 0, this);
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl", f = "CapturePipeline.kt", i = {0}, l = {793}, m = "waitForResult", n = {"resultListener"}, s = {"L$0"}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.CapturePipelineImpl$waitForResult$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00831 extends ContinuationImpl {
        Object L$0;
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ CapturePipelineImpl this$0;

        public C00831(CapturePipelineImpl r1, Continuation<? super C00831> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r4) {
            this.result = r4;
            this.label |= Integer.MIN_VALUE;
            return CapturePipelineImpl.access$waitForResult(this.this$0, 0, null, this);
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", "Landroidx/camera/camera2/pipe/FrameInfo;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.CapturePipelineImpl$waitForResult$3", f = "CapturePipeline.kt", i = {}, l = {796}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.CapturePipelineImpl$waitForResult$3, reason: invalid class name */
    public static final class AnonymousClass3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super FrameInfo>, Object> {
        final /* synthetic */ ResultListener $resultListener;
        int label;

        public AnonymousClass3(ResultListener r1, Continuation<? super AnonymousClass3> r2) {
            this.$resultListener = r1;
            super(2, r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r1, Continuation<?> r2) {
            return new AnonymousClass3(this.$resultListener, r2);
        }

        @Override // kotlin.jvm.functions.Function2
        public /* bridge */ /* synthetic */ Object invoke(CoroutineScope r1, Continuation<? super FrameInfo> r2) {
            return invoke2(r1, r2);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(CoroutineScope r1, Continuation<? super FrameInfo> r2) {
            return ((AnonymousClass3) create(r1, r2)).invokeSuspend(Unit.INSTANCE);
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
            Deferred<FrameInfo> r42 = this.$resultListener.getResult();
            this.label = 1;
            Object r3 = r42.await(this);
            if (r3 != r0) goto L12;
            return r0;
        L12:
            return r3;
        }
    }

    public static UseCaseCameraState $r8$lambda$2IvuaKRBXv4NPn3pCARNzCjl050(CapturePipelineImpl r0) {
        return r0.useCaseCameraStateProvider.get();
    }

    /* renamed from: $r8$lambda$4y4FwpMApia-NBeQyJGSZ11GwMA, reason: not valid java name */
    public static boolean m65$r8$lambda$4y4FwpMApiaNBeQyJGSZ11GwMA(CameraProperties r3) {
        return FlashAvailabilityCheckerKt.isFlashAvailable$default(r3, false, 1, null);
    }

    public static boolean $r8$lambda$QUMNp0tfcXRSiLsQB0i9runZEEs(FrameInfo r0) {
        return true;
    }

    public static boolean $r8$lambda$u8rpfFcSEU3zva8xfZhpabtQWUM(CapturePipelineImpl r0, boolean r1, FrameMetadata r2) {
        return ConvergenceUtils.is3AConverged(r0.toCameraCaptureResult(r2), r1);
    }

    public CapturePipelineImpl(CaptureConfigAdapter r1, FlashControl r2, TorchControl r3, VideoUsageControl r4, UseCaseThreads r5, ComboRequestListener r6, UseTorchAsFlash r7, CameraProperties r8, Provider<UseCaseCameraState> r9, UseCaseGraphContext r10) {
        this.configAdapter = r1;
        this.flashControl = r2;
        this.torchControl = r3;
        this.videoUsageControl = r4;
        this.threads = r5;
        this.requestListener = r6;
        this.useTorchAsFlash = r7;
        this.useCaseCameraStateProvider = r9;
        this.useCaseGraphContext = r10;
        this.hasFlashUnit$delegate = LazyKt.lazy(new CapturePipelineImpl$$ExternalSyntheticLambda1(r8));
        this.useCaseCameraState$delegate = LazyKt.lazy(new CapturePipelineImpl$$ExternalSyntheticLambda2(this));
        this.template = 1;
        this.emptyRequestMetadata = new CapturePipelineImpl$emptyRequestMetadata$1();
    }

    public static final /* synthetic */ Object access$aePreCaptureApplyCapture(CapturePipelineImpl r0, MainCaptureParams r1, long r2, int r4, List r5, Continuation r6) {
        return r0.aePreCaptureApplyCapture(r1, r2, r4, r5, r6);
    }

    public static final /* synthetic */ Object access$defaultCapture(CapturePipelineImpl r0, MainCaptureParams r1, int r2, int r3, List r4, Continuation r5) {
        return r0.defaultCapture(r1, r2, r3, r4, r5);
    }

    public static final /* synthetic */ Object access$defaultNoFlashCapture(CapturePipelineImpl r0, MainCaptureParams r1, int r2, List r3, Continuation r4) {
        return r0.defaultNoFlashCapture(r1, r2, r3, r4);
    }

    public static final /* synthetic */ CapturePipelineImpl$emptyRequestMetadata$1 access$getEmptyRequestMetadata$p(CapturePipelineImpl r0) {
        return r0.emptyRequestMetadata;
    }

    public static final /* synthetic */ Object access$getFrameMetadata(CapturePipelineImpl r0, Continuation r1) {
        return r0.getFrameMetadata(r1);
    }

    public static final /* synthetic */ FrameMetadata access$getFrameMetadata$p(CapturePipelineImpl r0) {
        return r0.frameMetadata;
    }

    public static final /* synthetic */ ComboRequestListener access$getRequestListener$p(CapturePipelineImpl r0) {
        return r0.requestListener;
    }

    public static final /* synthetic */ TorchControl access$getTorchControl$p(CapturePipelineImpl r0) {
        return r0.torchControl;
    }

    public static final /* synthetic */ UseCaseCameraState access$getUseCaseCameraState(CapturePipelineImpl r0) {
        return r0.getUseCaseCameraState();
    }

    public static final /* synthetic */ UseCaseGraphContext access$getUseCaseGraphContext$p(CapturePipelineImpl r0) {
        return r0.useCaseGraphContext;
    }

    public static final /* synthetic */ Object access$invokeCaptureTasks(CapturePipelineImpl r0, List r1, int r2, int r3, int r4, MainCaptureParams r5, Continuation r6) {
        return r0.invokeCaptureTasks(r1, r2, r3, r4, r5, r6);
    }

    public static final /* synthetic */ Object access$isPhysicalFlashRequired(CapturePipelineImpl r0, int r1, Continuation r2) {
        return r0.isPhysicalFlashRequired(r1, r2);
    }

    public static final /* synthetic */ Object access$lockAf(CapturePipelineImpl r0, long r1, boolean r3, Continuation r4) {
        return r0.lockAf(r1, r3, r4);
    }

    public static final /* synthetic */ Object access$screenFlashCapture(CapturePipelineImpl r0, MainCaptureParams r1, int r2, List r3, Continuation r4) {
        return r0.screenFlashCapture(r1, r2, r3, r4);
    }

    public static final /* synthetic */ CameraCaptureResult access$toCameraCaptureResult(CapturePipelineImpl r0, FrameMetadata r1) {
        return r0.toCameraCaptureResult(r1);
    }

    public static final /* synthetic */ Object access$torchApplyCapture(CapturePipelineImpl r0, MainCaptureParams r1, int r2, long r3, List r5, boolean r6, Continuation r7) {
        return r0.torchApplyCapture(r1, r2, r3, r5, r6, r7);
    }

    public static final /* synthetic */ Object access$torchAsFlashCapture(CapturePipelineImpl r0, MainCaptureParams r1, int r2, int r3, List r4, Continuation r5) {
        return r0.torchAsFlashCapture(r1, r2, r3, r4, r5);
    }

    public static final /* synthetic */ Object access$unlockAf(CapturePipelineImpl r0, long r1, Continuation r3) {
        return r0.unlockAf(r1, r3);
    }

    public static final /* synthetic */ Object access$waitForResult(CapturePipelineImpl r0, long r1, Function1 r3, Continuation r4) {
        return r0.waitForResult(r1, r3, r4);
    }

    private final Object aePreCaptureApplyCapture(MainCaptureParams r22, long r23, int r25, List<? extends PipelineTask> r26, Continuation<? super List<? extends Deferred<Void>>> r27) {
        List<? extends PipelineTask> r1 = r26;
        if ((r27 instanceof AnonymousClass1) == false) goto L8;
        AnonymousClass1 r3 = (AnonymousClass1) r27;
        int r4 = r3.label;
        if ((r4 & Integer.MIN_VALUE) == 0) goto L8;
        r3.label = r4 - Integer.MIN_VALUE;
    L7:
        AnonymousClass1 r10 = r3;
        Object r2 = r10.result;
        Object r32 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r42 = r10.label;
        if (r42 == 0) goto L27;
        if (r42 == 1) goto L26;
        if (r42 == 2) goto L21;
        if (r42 != 3) goto L19;
        int r12 = r10.I$0;
        AutoCloseable r33 = (AutoCloseable) r10.L$3;
        MainCaptureParams r43 = (MainCaptureParams) r10.L$2;
        List<? extends PipelineTask> r5 = (List) r10.L$1;
        CapturePipelineImpl r6 = (CapturePipelineImpl) r10.L$0;
        ResultKt.throwOnFailure(r2);     // Catch: Throwable -> L17
    L68:
        Camera2Logger r24 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L17
        if (Logger.isDebugEnabled("CXCP") == false) goto L71;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#aePreCaptureApplyCapture: Locking 3A for capture done");     // Catch: Throwable -> L17
    L71:
        Unit r28 = Unit.INSTANCE;     // Catch: Throwable -> L17
        AutoCloseableKt.closeFinally(r33, null);
        if (Logger.isDebugEnabled("CXCP") == false) goto L75;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: PRE_CAPTURE completed");
    L75:
        int r9 = r12;
        r1 = r5;
    L86:
        if (r1.contains(PipelineTask.MAIN_CAPTURE) == true) goto L88;
        Continuation r29 = null;
        List<Deferred<Void>> r34 = CollectionsKt.listOf(CompletableDeferredKt.CompletableDeferred(null));
    L99:
        if (r1.contains(PipelineTask.POST_CAPTURE) == false) goto L101;
        BuildersKt.launch$default(r6.threads.getSequentialScope(), null, null, new CapturePipelineImpl$aePreCaptureApplyCapture$$inlined$invoke$1(r34, r29, this, r9), 3, null);
    L101:
        return r34;
    L88:
        if (Logger.isDebugEnabled("CXCP") == false) goto L90;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: starting MAIN_CAPTURE");
    L90:
        if (r43 == null) goto L95;
        List<Deferred<Void>> r210 = r6.submitRequestInternal(r43);
        if (Logger.isDebugEnabled("CXCP") == false) goto L94;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: MAIN_CAPTURE completed");
    L94:
        r34 = r210;
        r29 = null;
        goto L99
    L95:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L17:
        th = move-exception;
        Throwable r13 = th;
    L110:
        throw r13;     // Catch: Throwable -> L81
    L81:
        th = move-exception;
        AutoCloseableKt.closeFinally(r33, r13);
        throw th;
    L19:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L21:
        r12 = r10.I$0;
        AutoCloseable r44 = (AutoCloseable) r10.L$3;
        MainCaptureParams r52 = (MainCaptureParams) r10.L$2;
        List<? extends PipelineTask> r62 = (List) r10.L$1;
        CapturePipelineImpl r7 = (CapturePipelineImpl) r10.L$0;
        ResultKt.throwOnFailure(r2);     // Catch: Throwable -> L24
        AutoCloseable r15 = r44;
        List<? extends PipelineTask> r45 = r62;
        r6 = r7;
    L64:
        r10.L$0 = r6;     // Catch: Throwable -> L76
        r10.L$1 = r45;     // Catch: Throwable -> L76
        r10.L$2 = r52;     // Catch: Throwable -> L76
        r10.L$3 = r15;     // Catch: Throwable -> L76
        r10.I$0 = r12;     // Catch: Throwable -> L76
        r10.label = 3;     // Catch: Throwable -> L76
        if (((Deferred) r2).join(r10) != r32) goto L67;
    L66:
        return r32;
    L67:
        MainCaptureParams r35 = r52;
        r5 = r45;
        r43 = r35;
        r33 = r15;
    L76:
        th = th;
    L77:
        r13 = th;
        r33 = r15;
    L24:
        th = move-exception;
        r13 = th;
        r33 = r44;
        goto L110
    L26:
        int r14 = r10.I$0;
        long r72 = r10.J$0;
        MainCaptureParams r46 = (MainCaptureParams) r10.L$2;
        List<? extends PipelineTask> r92 = (List) r10.L$1;
        CapturePipelineImpl r11 = (CapturePipelineImpl) r10.L$0;
        ResultKt.throwOnFailure(r2);
        int r211 = r14;
        MainCaptureParams r16 = r46;
        List<? extends PipelineTask> r47 = r92;
        long r8 = r72;
        Object r73 = r2;
    L45:
        AutoCloseable r74 = (AutoCloseable) r73;
        CameraGraph.Session r122 = (CameraGraph.Session) r74;     // Catch: Throwable -> L78
        Camera2Logger r162 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L78
        if (Logger.isDebugEnabled("CXCP") == true) goto L108;
    L52:
        boolean r63 = false;
        if (r211 != 0) goto L55;
        boolean r152 = true;
    L56:
        if (r211 != 0) goto L58;
        r63 = true;
    L58:
        r10.L$0 = r11;     // Catch: Throwable -> L78
        r10.L$1 = r47;     // Catch: Throwable -> L78
        r10.L$2 = r16;     // Catch: Throwable -> L78
        r10.L$3 = r74;     // Catch: Throwable -> L78
        r10.I$0 = r211;     // Catch: Throwable -> L78
        r10.label = 2;     // Catch: Throwable -> L78
        CapturePipelineImpl r17 = r11;
        List<? extends PipelineTask> r18 = r47;
        boolean r19 = r152;
        r15 = r74;
        Object r48 = CameraGraph.Session.lock3AForCapture$default(r122, r19, r63, 0, r8, r10, 4, null);     // Catch: Throwable -> L76
        if (r48 == r32) goto L66;
        r52 = r16;
        r12 = r211;
        r2 = r48;
        r6 = r17;
        r45 = r18;
        goto L64
    L55:
        r152 = false;
        goto L56
    L108:
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#aePreCaptureApplyCapture: Locking 3A for capture");     // Catch: Throwable -> L50
    L50:
        th = move-exception;
        r13 = th;
        r33 = r74;
    L78:
        th = th;
        r15 = r74;
        goto L77
    L27:
        ResultKt.throwOnFailure(r2);
        Camera2Logger r212 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L31;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#aePreCaptureApplyCapture");
    L31:
        if (Logger.isDebugEnabled("CXCP") == false) goto L34;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: tasks = " + r1);
    L34:
        if (r1.contains(PipelineTask.PRE_CAPTURE) == true) goto L36;
        r43 = r22;
        r9 = r25;
        r6 = this;
        goto L86
    L36:
        if (Logger.isDebugEnabled("CXCP") == false) goto L39;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: starting PRE_CAPTURE");
    L39:
        if (Logger.isDebugEnabled("CXCP") == false) goto L41;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#aePreCaptureApplyCapture: Acquiring session for locking 3A");
    L41:
        CameraGraph r213 = access$getUseCaseGraphContext$p(this).getGraph();
        r10.L$0 = this;
        r10.L$1 = r1;
        r10.L$2 = r22;
        r10.J$0 = r23;
        r10.I$0 = r25;
        r10.label = 1;
        Object r214 = r213.acquireSession(r10);
        if (r214 == r32) goto L66;
        r47 = r1;
        r16 = r22;
        r73 = r214;
        r211 = r25;
        r8 = r23;
        r11 = this;
    L8:
        r3 = new AnonymousClass1(this, r27);
        goto L7
    }

    private final Object defaultCapture(MainCaptureParams r9, int r10, int r11, List<? extends PipelineTask> r12, Continuation<? super List<? extends Deferred<Void>>> r13) {
        if ((r13 instanceof C00711) == false) goto L8;
        C00711 r0 = (C00711) r13;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L8;
        r0.label = r1 - Integer.MIN_VALUE;
    L7:
        C00711 r7 = r0;
        Object r132 = r7.result;
        Object r02 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r14 = r7.label;
        if (r14 == 0) goto L25;
        if (r14 == 1) goto L23;
        if (r14 == 2) goto L21;
        if (r14 == 3) goto L19;
        if (r14 != 4) goto L17;
        ResultKt.throwOnFailure(r132);
        return r132;
    L17:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L19:
        ResultKt.throwOnFailure(r132);
        return r132;
    L21:
        ResultKt.throwOnFailure(r132);
        return r132;
    L23:
        r10 = r7.I$0;
        r12 = (List) r7.L$1;
        r9 = (MainCaptureParams) r7.L$0;
        ResultKt.throwOnFailure(r132);
    L24:
        MainCaptureParams r2 = r9;
        int r5 = r10;
        boolean r92 = ((Boolean) r132).booleanValue();
        if (r92 == false) goto L33;
        long r102 = CapturePipelineKt.access$getCHECK_3A_WITH_FLASH_TIMEOUT_IN_NS$p();
    L34:
        if (r92 == true) goto L41;
        if (r5 == 0) goto L41;
        r7.L$0 = null;
        r7.L$1 = null;
        r7.label = 3;
        Object r8 = defaultNoFlashCapture(r2, r5, r12, r7);
        if (r8 == r02) goto L47;
        return r8;
    L47:
        return r02;
    L41:
        r7.L$0 = null;
        r7.L$1 = null;
        r7.label = 2;
        Object r82 = aePreCaptureApplyCapture(r2, r102, r5, r12, r7);
        if (r82 == r02) goto L47;
        return r82;
    L33:
        r102 = CapturePipelineKt.access$getCHECK_3A_TIMEOUT_IN_NS$p();
        goto L34
    L25:
        ResultKt.throwOnFailure(r132);
        if (getHasFlashUnit() == false) goto L45;
        r7.L$0 = r9;
        r7.L$1 = r12;
        r7.I$0 = r10;
        r7.label = 1;
        r132 = isPhysicalFlashRequired(r11, r7);
        if (r132 != r02) goto L24;
    L45:
        r7.label = 4;
        Object r83 = defaultNoFlashCapture(r9, r10, r12, r7);
        if (r83 == r02) goto L47;
        return r83;
    L8:
        r0 = new C00711(this, r13);
        goto L7
    }

    private final Object defaultNoFlashCapture(MainCaptureParams r15, int r16, List<? extends PipelineTask> r17, Continuation<? super List<? extends Deferred<Void>>> r18) {
        List<? extends PipelineTask> r0 = r17;
        if ((r18 instanceof C00721) == false) goto L7;
        C00721 r2 = (C00721) r18;
        int r3 = r2.label;
        if ((r3 & Integer.MIN_VALUE) == 0) goto L7;
        r2.label = r3 - Integer.MIN_VALUE;
    L8:
        Object r1 = r2.result;
        Object r32 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r4 = r2.label;
        boolean r6 = false;
        if (r4 == 0) goto L14;
        if (r4 != 1) goto L12;
        int r02 = r2.I$0;
        MainCaptureParams r33 = (MainCaptureParams) r2.L$2;
        List<? extends PipelineTask> r42 = (List) r2.L$1;
        CapturePipelineImpl r22 = (CapturePipelineImpl) r2.L$0;
        ResultKt.throwOnFailure(r1);
        int r12 = r02;
        r0 = r42;
    L36:
        Camera2Logger r43 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L41;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#defaultNoFlashCapture: Locking 3A done");
    L41:
        if (Logger.isDebugEnabled("CXCP") == false) goto L45;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: PRE_CAPTURE completed");
    L45:
        if (r0.contains(PipelineTask.MAIN_CAPTURE) == true) goto L47;
        List<Deferred<Void>> r34 = CollectionsKt.listOf(CompletableDeferredKt.CompletableDeferred(null));
    L57:
        if (r0.contains(PipelineTask.POST_CAPTURE) == false) goto L62;
        CoroutineScope r8 = r22.threads.getSequentialScope();
        if (r12 == 0) goto L61;
        r6 = true;
    L61:
        BuildersKt.launch$default(r8, null, null, new CapturePipelineImpl$defaultNoFlashCapture$$inlined$invoke$1(r34, null, r6, this), 3, null);
    L62:
        return r34;
    L47:
        if (Logger.isDebugEnabled("CXCP") == false) goto L49;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: starting MAIN_CAPTURE");
    L49:
        if (r33 == null) goto L53;
        r34 = r22.submitRequestInternal(r33);
        if (Logger.isDebugEnabled("CXCP") == false) goto L57;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: MAIN_CAPTURE completed");
        goto L57
    L53:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r1);
        Camera2Logger r13 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L17;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#defaultNoFlashCapture");
    L17:
        if (r16 != 0) goto L19;
        r12 = 1;
    L21:
        if (Logger.isDebugEnabled("CXCP") == false) goto L24;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: tasks = " + r0);
    L24:
        if (r0.contains(PipelineTask.PRE_CAPTURE) == true) goto L26;
        r22 = this;
        r33 = r15;
        goto L45
    L26:
        if (Logger.isDebugEnabled("CXCP") == false) goto L28;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: starting PRE_CAPTURE");
    L28:
        if (r12 != 0) goto L30;
        r22 = this;
        r33 = r15;
        goto L41
    L30:
        if (Logger.isDebugEnabled("CXCP") == false) goto L32;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#defaultNoFlashCapture: Locking 3A");
    L32:
        long r9 = CapturePipelineKt.access$getCHECK_3A_TIMEOUT_IN_NS$p();
        r2.L$0 = this;
        r2.L$1 = r0;
        r2.L$2 = r15;
        r2.I$0 = r12;
        r2.label = 1;
        if (access$lockAf(this, r9, false, r2) != r32) goto L35;
        return r32;
    L35:
        r22 = this;
        r33 = r15;
        goto L36
    L19:
        r12 = 0;
    L7:
        r2 = new C00721(this, r18);
        goto L8
    }

    private final Function1<FrameMetadata, Boolean> getConvergeCondition(boolean r2) {
        return new CapturePipelineImpl$$ExternalSyntheticLambda3(this, r2);
    }

    private final Object getFrameMetadata(Continuation<? super FrameMetadata> r11) {
        if ((r11 instanceof C00731) == false) goto L8;
        C00731 r0 = (C00731) r11;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L8;
        r0.label = r1 - Integer.MIN_VALUE;
    L7:
        C00731 r5 = r0;
        Object r112 = r5.result;
        Object r02 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r12 = r5.label;
        FrameMetadata r8 = null;
        if (r12 == 0) goto L15;
        if (r12 != 1) goto L13;
        CapturePipelineImpl r03 = (CapturePipelineImpl) r5.L$0;
        ResultKt.throwOnFailure(r112);
        CapturePipelineImpl r13 = this;
    L24:
        FrameInfo r113 = (FrameInfo) r112;
        if (r113 == null) goto L27;
        r8 = r113.getMetadata();
    L27:
        r03.frameMetadata = r8;
    L29:
        Camera2Logger r10 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L33;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "getFrameMetadata: frameMetadata = " + access$getFrameMetadata$p(r13));
    L33:
        return r13.frameMetadata;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ResultKt.throwOnFailure(r112);
        if (this.frameMetadata != null) goto L28;
        Camera2Logger r114 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L20;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "getFrameMetadata: waiting for result");
    L20:
        long r2 = CapturePipelineKt.access$getCHECK_FLASH_REQUIRED_TIMEOUT_IN_NS$p();
        r5.L$0 = this;
        r5.label = 1;
        r13 = this;
        r112 = waitForResult$default(r13, r2, null, r5, 2, null);
        if (r112 != r02) goto L23;
        return r02;
    L23:
        r03 = r13;
        goto L24
    L28:
        r13 = this;
    L8:
        r0 = new C00731(this, r11);
        goto L7
    }

    private final boolean getHasFlashUnit() {
        return ((Boolean) this.hasFlashUnit$delegate.getValue()).booleanValue();
    }

    private final UseCaseCameraState getUseCaseCameraState() {
        return (UseCaseCameraState) this.useCaseCameraState$delegate.getValue();
    }

    private final Object invokeCaptureTasks(List<? extends PipelineTask> r10, int r11, int r12, int r13, MainCaptureParams r14, Continuation<? super List<? extends Deferred<Void>>> r15) {
        if ((r15 instanceof C00741) == false) goto L7;
        C00741 r0 = (C00741) r15;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r152 = r0.result;
        Object r16 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L24;
        if (r2 == 1) goto L22;
        if (r2 == 2) goto L20;
        if (r2 == 3) goto L18;
        if (r2 != 4) goto L16;
        ResultKt.throwOnFailure(r152);
        return r152;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r152);
        return r152;
    L20:
        r12 = r0.I$1;
        r11 = r0.I$0;
        r14 = (MainCaptureParams) r0.L$1;
        r10 = (List) r0.L$0;
        ResultKt.throwOnFailure(r152);
    L21:
        List<? extends PipelineTask> r132 = r10;
        MainCaptureParams r102 = r14;
        if (((Boolean) r152).booleanValue() == false) goto L47;
        r0.L$0 = null;
        r0.L$1 = null;
        r0.label = 3;
        Object r9 = torchAsFlashCapture(r102, r11, r12, r132, r0);
        if (r9 == r16) goto L49;
        return r9;
    L49:
        return r16;
    L47:
        C00741 r142 = r0;
        r142.L$0 = null;
        r142.L$1 = null;
        r142.label = 4;
        Object r92 = defaultCapture(r102, r11, r12, r132, r142);
        if (r92 == r16) goto L49;
        return r92;
    L22:
        ResultKt.throwOnFailure(r152);
        return r152;
    L24:
        ResultKt.throwOnFailure(r152);
        Camera2Logger r153 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L27;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#invokeCaptureTasks: tasks = " + r10 + ", captureMode = " + r11 + ", flashMode = " + r12 + ", flashType = " + r13);
    L27:
        this.frameMetadata = null;
        if (r10.contains(PipelineTask.MAIN_CAPTURE) == false) goto L33;
        if (r14 != null) goto L33;
        Segment$$ExternalSyntheticBUOutline1.m("Must not be null for PipelineType.MAIN_CAPTURE");
        return null;
    L33:
        if (r12 != 3) goto L38;
        r0.label = 1;
        Object r93 = screenFlashCapture(r14, r11, r10, r0);
        if (r93 == r16) goto L49;
        return r93;
    L38:
        r0.L$0 = r10;
        r0.L$1 = r14;
        r0.I$0 = r11;
        r0.I$1 = r12;
        r0.label = 2;
        r152 = isTorchAsFlash(r13, r0);
        if (r152 != r16) goto L21;
    L7:
        r0 = new C00741(this, r15);
        goto L8
    }

    private final Object isPhysicalFlashRequired(int r6, Continuation<? super Boolean> r7) {
        if ((r7 instanceof C00771) == false) goto L7;
        C00771 r0 = (C00771) r7;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r72 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        boolean r3 = false;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        ResultKt.throwOnFailure(r72);
    L28:
        FrameMetadata r73 = (FrameMetadata) r72;
        if (r73 == null) goto L37;
        Integer r5 = (Integer) r73.get(CaptureResult.CONTROL_AE_STATE);
        if (r5 == null) goto L37;
        if (r5.intValue() != 4) goto L37;
    L24:
        r3 = true;
    L37:
        return Boxing.boxBoolean(r3);
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r72);
        if (r6 == 0) goto L25;
        if (r6 == 1) goto L24;
        if (r6 == 2) goto L37;
        if (r6 == 3) goto L37;
        throw new AssertionError(r6);
    L25:
        r0.label = 1;
        r72 = getFrameMetadata(r0);
        if (r72 != r12) goto L28;
        return r12;
    L7:
        r0 = new C00771(this, r7);
        goto L8
    }

    private final Object isTorchAsFlash(int r4, Continuation<? super Boolean> r5) {
        if (getTemplate() == 3) goto L8;
        if (r4 == 1) goto L8;
        return this.useTorchAsFlash.shouldUseTorchAsFlash(new AnonymousClass2(this, null), r5);
    L8:
        return Boxing.boxBoolean(true);
    }

    private final Object lockAf(long r27, boolean r29, Continuation<? super Result3A> r30) {
        if ((r30 instanceof C00781) == false) goto L7;
        C00781 r2 = (C00781) r30;
        int r3 = r2.label;
        if ((r3 & Integer.MIN_VALUE) == 0) goto L7;
        r2.label = r3 - Integer.MIN_VALUE;
    L8:
        Object r1 = r2.result;
        Object r32 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r4 = r2.label;
        if (r4 == 0) goto L24;
        if (r4 == 1) goto L22;
        if (r4 == 2) goto L17;
        if (r4 != 3) goto L15;
        ResultKt.throwOnFailure(r1);
        return r1;
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L17:
        AutoCloseable r42 = (AutoCloseable) r2.L$0;
        ResultKt.throwOnFailure(r1);     // Catch: Throwable -> L20
        Object r0 = r32;
        AutoCloseable r33 = r42;
        C00781 r43 = r2;
        Throwable r22 = null;
    L46:
        Deferred r12 = (Deferred) r1;     // Catch: Throwable -> L38
        AutoCloseableKt.closeFinally(r33, r22);
        r43.L$0 = r22;
        r43.label = 3;
        Object r13 = r12.await(r43);
        if (r13 == r0) goto L54;
        return r13;
    L54:
        return r0;
    L38:
        th = move-exception;
        Throwable r14 = th;
        r42 = r33;
    L52:
        throw r14;     // Catch: Throwable -> L43
    L43:
        th = move-exception;
        AutoCloseableKt.closeFinally(r42, r14);
        throw th;
    L20:
        th = th;
    L21:
        r14 = th;
        goto L52
    L22:
        boolean r44 = r2.Z$0;
        long r9 = r2.J$0;
        ResultKt.throwOnFailure(r1);
    L23:
        long r17 = r9;
        AutoCloseable r15 = (AutoCloseable) r1;
        Lock3ABehavior r11 = Lock3ABehavior.m280boximpl(Lock3ABehavior.Companion.m287getAFTER_CURRENT_SCANhRqSH3k());     // Catch: Throwable -> L40
        Function1<FrameMetadata, Boolean> r142 = getConvergeCondition(r44);     // Catch: Throwable -> L40
        long r19 = CapturePipelineKt.access$getCHECK_3A_TIMEOUT_IN_NS$p();     // Catch: Throwable -> L40
        r2.L$0 = r15;     // Catch: Throwable -> L40
        r2.label = 2;     // Catch: Throwable -> L40
        r0 = r32;
        C00781 r21 = r2;
        r22 = null;
        Object r34 = CameraGraph.Session.m224lock3AtS25XM$default((CameraGraph.Session) r15, null, null, null, null, null, null, null, r11, null, null, r142, null, 0, r17, r19, r21, 6719, null);     // Catch: Throwable -> L40
        r43 = r21;
        if (r34 == r0) goto L55;
        r33 = r15;
        r1 = r34;
        goto L46
    L55:
        return r0;
    L40:
        th = th;
        r42 = r15;
        goto L21
    L24:
        ResultKt.throwOnFailure(r1);
        CameraGraph r16 = this.useCaseGraphContext.getGraph();
        r9 = r27;
        r2.J$0 = r9;
        r44 = r29;
        r2.Z$0 = r44;
        r2.label = 1;
        r1 = r16.acquireSession(r2);
        if (r1 != r32) goto L23;
        return r32;
    L7:
        r2 = new C00781(this, r30);
        goto L8
    }

    private final Object screenFlashCapture(MainCaptureParams r11, int r12, List<? extends PipelineTask> r13, Continuation<? super List<? extends Deferred<Void>>> r14) {
        if ((r14 instanceof C00791) == false) goto L7;
        C00791 r0 = (C00791) r14;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r142 = r0.result;
        Object r15 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        if (r2 == 0) goto L14;
        if (r2 != 1) goto L12;
        r12 = r0.I$0;
        r11 = (MainCaptureParams) r0.L$2;
        r13 = (List) r0.L$1;
        CapturePipelineImpl r02 = (CapturePipelineImpl) r0.L$0;
        ResultKt.throwOnFailure(r142);
    L29:
        Camera2Logger r143 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L34;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: PRE_CAPTURE completed");
    L34:
        if (r13.contains(PipelineTask.MAIN_CAPTURE) == true) goto L36;
        List<Deferred<Void>> r112 = CollectionsKt.listOf(CompletableDeferredKt.CompletableDeferred(null));
    L46:
        if (r13.contains(PipelineTask.POST_CAPTURE) == false) goto L48;
        BuildersKt.launch$default(r02.threads.getSequentialScope(), null, null, new CapturePipelineImpl$screenFlashCapture$$inlined$invoke$1(r112, null, this, r12), 3, null);
    L48:
        return r112;
    L36:
        if (Logger.isDebugEnabled("CXCP") == false) goto L38;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: starting MAIN_CAPTURE");
    L38:
        if (r11 == null) goto L42;
        r112 = r02.submitRequestInternal(r11);
        if (Logger.isDebugEnabled("CXCP") == false) goto L46;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: MAIN_CAPTURE completed");
        goto L46
    L42:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r142);
        Camera2Logger r144 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L18;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#screenFlashCapture");
    L18:
        if (Logger.isDebugEnabled("CXCP") == false) goto L21;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: tasks = " + r13);
    L21:
        if (r13.contains(PipelineTask.PRE_CAPTURE) == true) goto L23;
        r02 = this;
        goto L34
    L23:
        if (Logger.isDebugEnabled("CXCP") == false) goto L25;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: starting PRE_CAPTURE");
    L25:
        r0.L$0 = this;
        r0.L$1 = r13;
        r0.L$2 = r11;
        r0.I$0 = r12;
        r0.label = 1;
        if (invokeScreenFlashPreCaptureTasks(r12, r0) != r15) goto L28;
        return r15;
    L28:
        r02 = this;
    L7:
        r0 = new C00791(this, r14);
        goto L8
    }

    private final List<Deferred<Void>> submitRequestInternal(MainCaptureParams r13) {
        Camera2Logger r0 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L5;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#submitRequestInternal; Submitting " + r13.getConfigs() + " with CameraPipe");
    L5:
        ArrayList r2 = new ArrayList();
        List<CaptureConfig> r02 = r13.getConfigs();
        ArrayList r3 = new ArrayList();
        Iterator<T> r4 = r02.iterator();
    L6:
        Request r5 = null;
        if (r4.hasNext() == false) goto L19;
        CaptureConfig r03 = (CaptureConfig) r4.next();
        CompletableDeferred r6 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        r2.add(r6);
        r5 = this.configAdapter.m11mapToRequestnAberiA(r03, r13.m66getRequestTemplatefGx8uWA(), r13.getSessionConfigOptions(), CollectionsKt.listOf(new CapturePipelineImpl$submitRequestInternal$requests$1$1(r6)));     // Catch: IllegalStateException -> L11
    L16:
        if (r5 == null) goto L6;
        r3.add(r5);
    L11:
        e = move-exception;
        Camera2Logger r7 = Camera2Logger.INSTANCE;
        if (Logger.isInfoEnabled("CXCP") == false) goto L15;
        Log.i(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#submitRequestInternal: configAdapter.mapToRequest failed!", e);
    L15:
        r6.completeExceptionally(new ImageCaptureException(2, "Capture request failed with reason " + e.getMessage(), e));
        goto L16
    L19:
        if (r3.isEmpty() == false) goto L21;
        return r2;
    L21:
        BuildersKt.launch$default(this.threads.getSequentialScope(), null, null, new CapturePipelineImpl$submitRequestInternal$$inlined$confineLaunch$1(null, this, r2, r3), 3, null);
        return r2;
    }

    private final CameraCaptureResult toCameraCaptureResult(FrameMetadata r7) {
        CapturePipelineImpl$toCameraCaptureResult$frameInfo$1 r4 = new CapturePipelineImpl$toCameraCaptureResult$frameInfo$1(r7, this);
        return new CaptureResultAdapter(this.emptyRequestMetadata, r7.mo266getFrameNumberUgla2oM(), r4, null);
    }

    /* JADX WARN: Type inference failed for: r7v2 */
    /* JADX WARN: Type inference failed for: r7v35, types: [java.lang.AutoCloseable] */
    /* JADX WARN: Type inference failed for: r7v49 */
    /* JADX WARN: Type inference failed for: r7v50 */
    /* JADX WARN: Type inference failed for: r7v51 */
    private final Object torchApplyCapture(MainCaptureParams r26, int r27, long r28, List<? extends PipelineTask> r30, boolean r31, Continuation<? super List<? extends Deferred<Void>>> r32) {
        if ((r32 instanceof C00801) == false) goto L8;
        C00801 r3 = (C00801) r32;
        int r5 = r3.label;
        ?? r7 = r5 & Integer.MIN_VALUE;
        if (r7 == 0) goto L8;
        r3.label = r5 - Integer.MIN_VALUE;
    L7:
        C00801 r11 = r3;
        Object r2 = r11.result;
        Object r33 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch(r11.label) {
            case 0: goto L29;
            case 1: goto L27;
            case 2: goto L25;
            case 3: goto L20;
            case 4: goto L15;
            case 5: goto L14;
            case 6: goto L13;
            default: goto L11;
        };
    L11:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L13:
        int r0 = r11.I$2;
        int r1 = r11.I$1;
        boolean r34 = r11.Z$0;
        int r52 = r11.I$0;
        MainCaptureParams r72 = (MainCaptureParams) r11.L$2;
        List<? extends PipelineTask> r8 = (List) r11.L$1;
        CapturePipelineImpl r9 = (CapturePipelineImpl) r11.L$0;
        ResultKt.throwOnFailure(r2);
        Throwable r15 = null;
        boolean r6 = true;
    L120:
        Camera2Logger r22 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == true) goto L122;
    L112:
        int r23 = r1;
        CapturePipelineImpl r13 = r9;
        List<? extends PipelineTask> r92 = r8;
        MainCaptureParams r82 = r72;
    L125:
        if (Logger.isDebugEnabled("CXCP") == false) goto L127;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: PRE_CAPTURE completed");
    L127:
        int r73 = r52;
        boolean r53 = r34;
    L130:
        if (r92.contains(PipelineTask.MAIN_CAPTURE) == true) goto L132;
        List<Deferred<Void>> r12 = CollectionsKt.listOf(CompletableDeferredKt.CompletableDeferred(r15));
    L142:
        if (r92.contains(PipelineTask.POST_CAPTURE) == false) goto L151;
        CoroutineScope r83 = r13.threads.getSequentialScope();
        int r35 = r0;
        if (r23 == 0) goto L146;
        int r24 = r35;
        boolean r36 = r6;
    L147:
        if (r24 != 0) goto L150;
        r6 = false;
    L150:
        BuildersKt.launch$default(r83, null, null, new CapturePipelineImpl$torchApplyCapture$$inlined$invoke$1(r12, null, r36, this, r53, r6, r73), 3, null);
        goto L151
    L146:
        r24 = r35;
        r36 = false;
    L151:
        return r12;
    L132:
        if (Logger.isDebugEnabled("CXCP") == false) goto L134;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: starting MAIN_CAPTURE");
    L134:
        if (r82 == null) goto L138;
        r12 = r13.submitRequestInternal(r82);
        if (Logger.isDebugEnabled("CXCP") == false) goto L142;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: MAIN_CAPTURE completed");
        goto L142
    L138:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return r15;
    L122:
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#torchApplyCapture: 3A convergence waiting done");
        goto L112
    L14:
        r0 = r11.I$2;
        r1 = r11.I$1;
        r34 = r11.Z$0;
        r52 = r11.I$0;
        r72 = (MainCaptureParams) r11.L$2;
        r8 = (List) r11.L$1;
        r9 = (CapturePipelineImpl) r11.L$0;
        ResultKt.throwOnFailure(r2);
        r15 = null;
        r6 = true;
    L109:
        Camera2Logger r25 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L112;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#torchApplyCapture: Locking 3A done");
        goto L112
    L15:
        r0 = r11.I$2;
        int r14 = r11.I$1;
        r34 = r11.Z$0;
        r52 = r11.I$0;
        AutoCloseable r74 = (AutoCloseable) r11.L$3;
        r82 = (MainCaptureParams) r11.L$2;
        r92 = (List) r11.L$1;
        CapturePipelineImpl r10 = (CapturePipelineImpl) r11.L$0;
        ResultKt.throwOnFailure(r2);     // Catch: Throwable -> L18
        r15 = null;
        r7 = r74;
    L88:
        Result3A r29 = (Result3A) r2;     // Catch: Throwable -> L18
        AutoCloseableKt.closeFinally(r7, r15);
        Camera2Logger r62 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L92;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#torchApplyCapture: Locking 3A for capture done, result3A = " + r29);
    L92:
        r23 = r14;
        r13 = r10;
        r6 = true;
        goto L125
    L20:
        r0 = r11.I$2;
        r14 = r11.I$1;
        boolean r54 = r11.Z$0;
        int r75 = r11.I$0;
        AutoCloseable r84 = (AutoCloseable) r11.L$3;
        MainCaptureParams r93 = (MainCaptureParams) r11.L$2;
        List<? extends PipelineTask> r102 = (List) r11.L$1;
        CapturePipelineImpl r122 = (CapturePipelineImpl) r11.L$0;
        ResultKt.throwOnFailure(r2);     // Catch: Throwable -> L23
        r15 = null;
        int r142 = r75;
        AutoCloseable r76 = r84;
        r82 = r93;
        r92 = r102;
        r10 = r122;
    L84:
        r11.L$0 = r10;     // Catch: Throwable -> L18
        r11.L$1 = r92;     // Catch: Throwable -> L18
        r11.L$2 = r82;     // Catch: Throwable -> L18
        r11.L$3 = r76;     // Catch: Throwable -> L18
        r11.I$0 = r142;     // Catch: Throwable -> L18
        r11.Z$0 = r54;     // Catch: Throwable -> L18
        r11.I$1 = r14;     // Catch: Throwable -> L18
        r11.I$2 = r0;     // Catch: Throwable -> L18
        r11.label = 4;     // Catch: Throwable -> L18
        r2 = ((Deferred) r2).await(r11);     // Catch: Throwable -> L18
        if (r2 == r33) goto L118;
        r34 = r54;
        r52 = r142;
        r7 = r76;
    L118:
        return r33;
    L23:
        th = move-exception;
        Throwable r16 = th;
        AutoCloseable r77 = r84;
    L158:
        throw r16;     // Catch: Throwable -> L96
    L96:
        th = move-exception;
        AutoCloseableKt.closeFinally(r77, r16);
        throw th;
    L25:
        r0 = r11.I$2;
        r14 = r11.I$1;
        boolean r55 = r11.Z$0;
        long r78 = r11.J$0;
        int r94 = r11.I$0;
        MainCaptureParams r103 = (MainCaptureParams) r11.L$2;
        List<? extends PipelineTask> r123 = (List) r11.L$1;
        r13 = (CapturePipelineImpl) r11.L$0;
        ResultKt.throwOnFailure(r2);
        long r232 = r78;
        MainCaptureParams r79 = r103;
    L26:
        int r63 = r94;
        long r95 = r232;
        List<? extends PipelineTask> r85 = r123;
        CapturePipelineImpl r124 = r13;
        AutoCloseable r210 = (AutoCloseable) r2;
        CameraGraph.Session r132 = (CameraGraph.Session) r210;     // Catch: Throwable -> L93
        if (r63 != 0) goto L76;
        boolean r17 = true;
    L77:
        if (r63 != 0) goto L79;
        boolean r18 = true;
    L80:
        r11.L$0 = r124;     // Catch: Throwable -> L93
        r11.L$1 = r85;     // Catch: Throwable -> L93
        r11.L$2 = r79;     // Catch: Throwable -> L93
        r11.L$3 = r210;     // Catch: Throwable -> L93
        r11.I$0 = r63;     // Catch: Throwable -> L93
        r11.Z$0 = r55;     // Catch: Throwable -> L93
        r11.I$1 = r14;     // Catch: Throwable -> L93
        r11.I$2 = r0;     // Catch: Throwable -> L93
        r11.label = 3;     // Catch: Throwable -> L93
        boolean r21 = r55;
        r142 = r63;
        boolean r152 = r18;
        MainCaptureParams r182 = r79;
        r15 = null;
        Object r56 = CameraGraph.Session.lock3AForCapture$default(r132, r17, r152, 0, r95, r11, 4, null);     // Catch: Throwable -> L93
        if (r56 == r33) goto L118;
        r76 = r210;
        r2 = r56;
        r92 = r85;
        r82 = r182;
        r10 = r124;
        r54 = r21;
        goto L84
    L79:
        r18 = false;
        goto L80
    L76:
        r17 = false;
    L93:
        th = move-exception;
        r16 = th;
        r77 = r210;
        goto L158
    L27:
        int r02 = r11.I$2;
        int r19 = r11.I$1;
        r55 = r11.Z$0;
        long r710 = r11.J$0;
        r94 = r11.I$0;
        MainCaptureParams r104 = (MainCaptureParams) r11.L$2;
        r123 = (List) r11.L$1;
        r13 = (CapturePipelineImpl) r11.L$0;
        ResultKt.throwOnFailure(r2);
        int r211 = r02;
        long r233 = r710;
        int r711 = r19;
        r82 = r104;
    L28:
        long r03 = r233;
        Camera2Logger r105 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L64;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#torchApplyCapture: Setting torch done");
    L64:
        if (r55 == true) goto L66;
        r15 = null;
        if (r211 == 0) goto L123;
        if (r94 == 0) goto L103;
        r6 = true;
        if (Logger.isDebugEnabled("CXCP") == false) goto L116;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#torchApplyCapture: Awaiting 3A convergence");
    L116:
        CapturePipelineImpl$torchApplyCapture$3$8 r106 = new CapturePipelineImpl$torchApplyCapture$3$8(this);
        r11.L$0 = r13;
        r11.L$1 = r123;
        r11.L$2 = r82;
        r11.I$0 = r94;
        r11.Z$0 = r55;
        r11.I$1 = r711;
        r11.I$2 = r211;
        r11.label = 6;
        if (access$waitForResult(this, r03, r106, r11) == r33) goto L118;
        r0 = r211;
        r34 = r55;
        r1 = r711;
        r72 = r82;
        r52 = r94;
        r8 = r123;
        r9 = r13;
        goto L120
    L103:
        if (Logger.isDebugEnabled("CXCP") == false) goto L105;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#torchApplyCapture: Locking 3A");
    L105:
        r11.L$0 = r13;
        r11.L$1 = r123;
        r11.L$2 = r82;
        r11.I$0 = r94;
        r11.Z$0 = r55;
        r11.I$1 = r711;
        r11.I$2 = r211;
        r11.label = 5;
        r6 = true;
        if (access$lockAf(this, r03, true, r11) == r33) goto L118;
        r0 = r211;
        r34 = r55;
        r1 = r711;
        r72 = r82;
        r52 = r94;
        r8 = r123;
        r9 = r13;
        goto L109
    L123:
        r6 = true;
        r0 = r211;
        r34 = r55;
        r23 = r711;
        r52 = r94;
        r92 = r123;
        goto L125
    L66:
        if (Logger.isDebugEnabled("CXCP") == false) goto L68;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#torchApplyCapture: Locking 3A for capture");
    L68:
        CameraGraph r64 = access$getUseCaseGraphContext$p(this).getGraph();
        r11.L$0 = r13;
        r11.L$1 = r123;
        r11.L$2 = r82;
        r11.I$0 = r94;
        r11.J$0 = r03;
        r11.Z$0 = r55;
        r11.I$1 = r711;
        r11.I$2 = r211;
        r11.label = 2;
        Object r65 = r64.acquireSession(r11);
        if (r65 == r33) goto L118;
        r232 = r03;
        r0 = r211;
        r2 = r65;
        r14 = r711;
        r79 = r82;
        goto L26
    L29:
        ResultKt.throwOnFailure(r2);
        Camera2Logger r212 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L32;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#torchApplyCapture");
    L32:
        Integer r213 = this.torchControl.getTorchStateLiveData().getValue();
        if (r213 != null) goto L36;
    L38:
        r23 = 0;
    L39:
        if (r23 != 0) goto L43;
        if (r27 == 0) goto L43;
        int r57 = 0;
    L45:
        if (Logger.isDebugEnabled("CXCP") == false) goto L48;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: tasks = " + r30);
    L48:
        if (r30.contains(PipelineTask.PRE_CAPTURE) == true) goto L50;
        r15 = null;
        r6 = true;
        r82 = r26;
        r73 = r27;
        r92 = r30;
        r13 = this;
        r0 = r57;
        r53 = r31;
        goto L130
    L50:
        if (Logger.isDebugEnabled("CXCP") == false) goto L52;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#List<PipelineTask>.invoke: starting PRE_CAPTURE");
    L52:
        if (r23 != 0) goto L54;
        r82 = r26;
        r711 = r23;
        r13 = this;
        r211 = r57;
        r55 = r31;
        r123 = r30;
        r94 = r27;
        r03 = r28;
        goto L64
    L54:
        if (Logger.isDebugEnabled("CXCP") == false) goto L56;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#torchApplyCapture: Setting torch");
    L56:
        Deferred r712 = TorchControl.m77setTorchAsyncOup_wC0$camera_camera2$default(access$getTorchControl$p(this), TorchControl.TorchMode.Companion.m89getUSED_AS_FLASHIRs_R8(), false, false, 6, null);
        r11.L$0 = this;
        r11.L$1 = r30;
        r82 = r26;
        r11.L$2 = r82;
        r11.I$0 = r27;
        r11.J$0 = r28;
        r11.Z$0 = r31;
        r11.I$1 = r23;
        r11.I$2 = r57;
        r11.label = 1;
        if (r712.join(r11) == r33) goto L118;
        r711 = r23;
        r13 = this;
        r211 = r57;
        r55 = r31;
        r123 = r30;
        r233 = r28;
        r94 = r27;
    L43:
        r57 = 1;
        goto L45
    L36:
        if (r213.intValue() != 0) goto L38;
        r23 = 1;
    L18:
        th = move-exception;
        r16 = th;
        r77 = r7;
    L8:
        r3 = new C00801(this, r32);
        goto L7
    }

    private final Object torchAsFlashCapture(MainCaptureParams r10, int r11, int r12, List<? extends PipelineTask> r13, Continuation<? super List<? extends Deferred<Void>>> r14) {
        if ((r14 instanceof C00811) == false) goto L8;
        C00811 r0 = (C00811) r14;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L8;
        r0.label = r1 - Integer.MIN_VALUE;
    L7:
        C00811 r8 = r0;
        Object r142 = r8.result;
        Object r02 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r15 = r8.label;
        if (r15 == 0) goto L22;
        if (r15 == 1) goto L20;
        if (r15 == 2) goto L18;
        if (r15 != 3) goto L16;
        ResultKt.throwOnFailure(r142);
        return r142;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        ResultKt.throwOnFailure(r142);
        return r142;
    L20:
        r11 = r8.I$0;
        r13 = (List) r8.L$1;
        r10 = (MainCaptureParams) r8.L$0;
        ResultKt.throwOnFailure(r142);
    L21:
        List<? extends PipelineTask> r6 = r13;
        if (((Boolean) r142).booleanValue() == false) goto L42;
        boolean r122 = true;
        long r4 = CapturePipelineKt.access$getCHECK_3A_WITH_FLASH_TIMEOUT_IN_NS$p();
        if (this.useTorchAsFlash.shouldDisableAePrecapture() == false) goto L35;
    L37:
        r122 = false;
    L36:
        boolean r7 = r122;
        r8.L$0 = null;
        r8.L$1 = null;
        r8.label = 2;
        Object r9 = torchApplyCapture(r10, r11, r4, r6, r7, r8);
        if (r9 == r02) goto L45;
        return r9;
    L45:
        return r02;
    L35:
        if (this.videoUsageControl.isInVideoUsage() == true) goto L37;
    L42:
        r13 = r6;
    L43:
        r8.L$0 = null;
        r8.L$1 = null;
        r8.label = 3;
        Object r92 = defaultNoFlashCapture(r10, r11, r13, r8);
        if (r92 == r02) goto L45;
        return r92;
    L22:
        ResultKt.throwOnFailure(r142);
        Camera2Logger r143 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L26;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "CapturePipeline#torchAsFlashCapture");
    L26:
        if (getHasFlashUnit() == false) goto L43;
        r8.L$0 = r10;
        r8.L$1 = r13;
        r8.I$0 = r11;
        r8.label = 1;
        r142 = isPhysicalFlashRequired(r12, r8);
        if (r142 != r02) goto L21;
    L8:
        r0 = new C00811(this, r14);
        goto L7
    }

    private final Object unlockAf(long r17, Continuation<? super Result3A> r19) {
        if ((r19 instanceof C00821) == false) goto L8;
        C00821 r2 = (C00821) r19;
        int r3 = r2.label;
        if ((r3 & Integer.MIN_VALUE) == 0) goto L8;
        r2.label = r3 - Integer.MIN_VALUE;
    L7:
        C00821 r11 = r2;
        Object r1 = r11.result;
        Object r22 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r32 = r11.label;
        if (r32 == 0) goto L25;
        if (r32 == 1) goto L23;
        if (r32 == 2) goto L18;
        if (r32 != 3) goto L16;
        ResultKt.throwOnFailure(r1);
        return r1;
    L16:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L18:
        AutoCloseable r33 = (AutoCloseable) r11.L$0;
        ResultKt.throwOnFailure(r1);     // Catch: Throwable -> L21
    L33:
        Deferred r12 = (Deferred) r1;     // Catch: Throwable -> L21
        AutoCloseableKt.closeFinally(r33, null);
        r11.L$0 = null;
        r11.label = 3;
        Object r0 = r12.await(r11);
        if (r0 != r22) goto L37;
    L36:
        return r22;
    L37:
        return r0;
    L21:
        th = th;
    L22:
        Throwable r13 = th;
        throw r13;     // Catch: Throwable -> L41
    L41:
        th = move-exception;
        AutoCloseableKt.closeFinally(r33, r13);
        throw th;
    L23:
        long r6 = r11.J$0;
        ResultKt.throwOnFailure(r1);
    L24:
        long r9 = r6;
        AutoCloseable r14 = (AutoCloseable) r1;
        Boolean r5 = Boxing.boxBoolean(true);     // Catch: Throwable -> L38
        r11.L$0 = r14;     // Catch: Throwable -> L38
        r11.label = 2;     // Catch: Throwable -> L38
        Object r02 = CameraGraph.Session.unlock3A$default((CameraGraph.Session) r14, null, r5, null, null, 0, r9, r11, 29, null);     // Catch: Throwable -> L38
        if (r02 == r22) goto L36;
        r33 = r14;
        r1 = r02;
    L38:
        th = th;
        r33 = r14;
        goto L22
    L25:
        ResultKt.throwOnFailure(r1);
        CameraGraph r03 = this.useCaseGraphContext.getGraph();
        r6 = r17;
        r11.J$0 = r6;
        r11.label = 1;
        r1 = r03.acquireSession(r11);
        if (r1 != r22) goto L24;
    L8:
        r2 = new C00821(this, r19);
        goto L7
    }

    private final Object waitForResult(long r17, Function1<? super FrameInfo, Boolean> r19, Continuation<? super FrameInfo> r20) {
        if ((r20 instanceof C00831) == false) goto L7;
        C00831 r4 = (C00831) r20;
        int r5 = r4.label;
        if ((r5 & Integer.MIN_VALUE) == 0) goto L7;
        r4.label = r5 - Integer.MIN_VALUE;
    L8:
        Object r3 = r4.result;
        Object r52 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r6 = r4.label;
        if (r6 == 0) goto L14;
        if (r6 != 1) goto L12;
        ResultListener r1 = (ResultListener) r4.L$0;
        ResultKt.throwOnFailure(r3);
    L19:
        if (((FrameInfo) r3) != null) goto L21;
        this.requestListener.removeListener(r1);
    L21:
        return r3;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L14:
        ResultKt.throwOnFailure(r3);
        ResultListener r32 = new ResultListener(r17, r19);
        this.requestListener.addListener(r32, this.threads.getSequentialExecutor());
        BuildersKt.launch$default(this.threads.getSequentialScope(), null, null, new CapturePipelineImpl$waitForResult$resultListener$1$1(r32, this, null), 3, null);
        AnonymousClass3 r62 = new AnonymousClass3(r32, null);
        r4.L$0 = r32;
        r4.label = 1;
        Object r12 = TimeoutKt.withTimeoutOrNull(r17 / 1000000, r62, r4);
        if (r12 != r52) goto L17;
        return r52;
    L17:
        r3 = r12;
        r1 = r32;
    L7:
        r4 = new C00831(this, r20);
        goto L8
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object waitForResult$default(CapturePipelineImpl r0, long r1, Function1 r3, Continuation r4, int r5, Object r6) {
        if ((r5 & 2) == 0) goto L6;
        r3 = new CapturePipelineImpl$$ExternalSyntheticLambda0();
    L6:
        return r0.waitForResult(r1, r3, r4);
    }

    public int getTemplate() {
        return this.template;
    }

    public final Object invokeScreenFlashPostCaptureTasks(int r10, Continuation<? super Unit> r11) {
        if ((r11 instanceof C00751) == false) goto L7;
        C00751 r0 = (C00751) r11;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L7;
        r0.label = r1 - Integer.MIN_VALUE;
    L8:
        Object r112 = r0.result;
        Object r12 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r2 = r0.label;
        boolean r6 = true;
        if (r2 == 0) goto L22;
        if (r2 == 1) goto L21;
        if (r2 == 2) goto L20;
        if (r2 != 3) goto L18;
        AutoCloseable r9 = (AutoCloseable) r0.L$0;
        ResultKt.throwOnFailure(r112);     // Catch: Throwable -> L16
    L45:
        Camera2Logger r102 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L16
        if (Logger.isDebugEnabled("CXCP") == false) goto L48;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "screenFlashPostCapture: Unlocking 3A done");     // Catch: Throwable -> L16
    L48:
        Unit r103 = Unit.INSTANCE;     // Catch: Throwable -> L16
        AutoCloseableKt.closeFinally(r9, null);
        return Unit.INSTANCE;
    L16:
        Throwable th = th;
    L59:
        throw th;     // Catch: Throwable -> L52
    L52:
        th = move-exception;
        AutoCloseableKt.closeFinally(r9, th);
        throw th;
    L18:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L20:
        int r92 = r0.I$0;
        ResultKt.throwOnFailure(r112);
    L32:
        AutoCloseable r104 = (AutoCloseable) r112;
        CameraGraph.Session r113 = (CameraGraph.Session) r104;     // Catch: Throwable -> L36
        Camera2Logger r22 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L36
        if (Logger.isDebugEnabled("CXCP") == false) goto L38;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "screenFlashPostCapture: Unlocking 3A");     // Catch: Throwable -> L36
    L38:
        if (r92 == 0) goto L41;
        r6 = false;
    L41:
        r0.L$0 = r104;     // Catch: Throwable -> L36
        r0.label = 3;     // Catch: Throwable -> L36
        if (r113.unlock3APostCapture(r6, r0) != r12) goto L44;
    L43:
        return r12;
    L44:
        r9 = r104;
    L36:
        th = move-exception;
        th = th;
        r9 = r104;
        goto L59
    L21:
        r10 = r0.I$0;
        ResultKt.throwOnFailure(r112);
    L25:
        Camera2Logger r114 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L28;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "screenFlashPostCapture: Acquiring session for unlocking 3A");
    L28:
        CameraGraph r93 = this.useCaseGraphContext.getGraph();
        r0.I$0 = r10;
        r0.label = 2;
        r112 = r93.acquireSession(r0);
        if (r112 == r12) goto L43;
        r92 = r10;
        goto L32
    L22:
        ResultKt.throwOnFailure(r112);
        FlashControl r115 = this.flashControl;
        r0.I$0 = r10;
        r0.label = 1;
        if (r115.stopScreenFlashCaptureTasks(r0) != r12) goto L25;
    L7:
        r0 = new C00751(this, r11);
        goto L8
    }

    public final Object invokeScreenFlashPreCaptureTasks(int r17, Continuation<? super Unit> r18) {
        if ((r18 instanceof C00761) == false) goto L8;
        C00761 r3 = (C00761) r18;
        int r4 = r3.label;
        if ((r4 & Integer.MIN_VALUE) == 0) goto L8;
        r3.label = r4 - Integer.MIN_VALUE;
    L7:
        C00761 r10 = r3;
        Object r1 = r10.result;
        Object r32 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r42 = r10.label;
        boolean r7 = true;
        if (r42 == 0) goto L29;
        if (r42 == 1) goto L28;
        if (r42 == 2) goto L27;
        if (r42 == 3) goto L22;
        if (r42 != 4) goto L20;
        AutoCloseable r33 = (AutoCloseable) r10.L$0;
        ResultKt.throwOnFailure(r1);     // Catch: Throwable -> L18
    L54:
        Result3A r12 = (Result3A) r1;     // Catch: Throwable -> L18
        Camera2Logger r0 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L18
        if (Logger.isDebugEnabled("CXCP") == false) goto L57;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "screenFlashPreCapture: Locking 3A for capture done, result3A = " + r12);     // Catch: Throwable -> L18
    L57:
        Unit r02 = Unit.INSTANCE;     // Catch: Throwable -> L18
        AutoCloseableKt.closeFinally(r33, null);
        return Unit.INSTANCE;
    L18:
        th = th;
    L19:
        Throwable r13 = th;
    L68:
        throw r13;     // Catch: Throwable -> L61
    L61:
        th = move-exception;
        AutoCloseableKt.closeFinally(r33, r13);
        throw th;
    L20:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L22:
        AutoCloseable r43 = (AutoCloseable) r10.L$0;
        ResultKt.throwOnFailure(r1);     // Catch: Throwable -> L25
    L50:
        r10.L$0 = r43;     // Catch: Throwable -> L25
        r10.label = 4;     // Catch: Throwable -> L25
        r1 = ((Deferred) r1).await(r10);     // Catch: Throwable -> L25
        if (r1 != r32) goto L53;
    L52:
        return r32;
    L53:
        r33 = r43;
    L25:
        th = move-exception;
        r13 = th;
        r33 = r43;
        goto L68
    L27:
        int r03 = r10.I$0;
        ResultKt.throwOnFailure(r1);
    L36:
        AutoCloseable r14 = (AutoCloseable) r1;
        CameraGraph.Session r44 = (CameraGraph.Session) r14;     // Catch: Throwable -> L40
        Camera2Logger r6 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L40
        if (Logger.isDebugEnabled("CXCP") == false) goto L42;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "screenFlashPreCapture: Locking 3A for capture");     // Catch: Throwable -> L40
    L42:
        long r8 = CapturePipelineKt.access$getCHECK_3A_WITH_SCREEN_FLASH_TIMEOUT_IN_NS$p();     // Catch: Throwable -> L40
        if (r03 == 0) goto L46;
        r7 = false;
    L46:
        r10.L$0 = r14;     // Catch: Throwable -> L40
        r10.label = 3;     // Catch: Throwable -> L40
        Object r04 = CameraGraph.Session.lock3AForCapture$default(r44, r7, true, 0, r8, r10, 4, null);     // Catch: Throwable -> L40
        if (r04 == r32) goto L52;
        r43 = r14;
        r1 = r04;
    L40:
        th = th;
        r33 = r14;
        goto L19
    L28:
        int r45 = r10.I$0;
        ResultKt.throwOnFailure(r1);
    L32:
        CameraGraph r05 = this.useCaseGraphContext.getGraph();
        r10.I$0 = r45;
        r10.label = 2;
        r1 = r05.acquireSession(r10);
        if (r1 == r32) goto L52;
        r03 = r45;
        goto L36
    L29:
        ResultKt.throwOnFailure(r1);
        FlashControl r15 = this.flashControl;
        r45 = r17;
        r10.I$0 = r45;
        r10.label = 1;
        if (r15.startScreenFlashCaptureTasks(r10) != r32) goto L32;
    L8:
        r3 = new C00761(this, r18);
        goto L7
    }

    @Override // androidx.camera.camera2.impl.CapturePipeline
    public void setTemplate(int r1) {
        this.template = r1;
    }

    @Override // androidx.camera.camera2.impl.CapturePipeline
    /* renamed from: submitStillCaptures-BvXKQx0 */
    public Object mo33submitStillCapturesBvXKQx0(List<CaptureConfig> r4, int r5, Config r6, int r7, int r8, int r9, Continuation<? super List<? extends Deferred<Void>>> r10) {
        return invokeCaptureTasks(CollectionsKt.listOf(new PipelineTask[]{PipelineTask.PRE_CAPTURE, PipelineTask.MAIN_CAPTURE, PipelineTask.POST_CAPTURE}), r7, r9, r8, new MainCaptureParams(r4, r5, r6, null), r10);
    }
}
