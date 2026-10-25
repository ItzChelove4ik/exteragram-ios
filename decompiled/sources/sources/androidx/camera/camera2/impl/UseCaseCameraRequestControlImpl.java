package androidx.camera.camera2.impl;

import android.hardware.camera2.CaptureRequest;
import android.hardware.camera2.params.MeteringRectangle;
import android.util.Log;
import androidx.camera.camera2.config.UseCaseGraphContext;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.impl.UseCaseCameraRequestControl;
import androidx.camera.camera2.interop.Camera2CaptureRequestConfiguratorKt;
import androidx.camera.camera2.pipe.AeMode;
import androidx.camera.camera2.pipe.Lock3ABehavior;
import androidx.camera.camera2.pipe.Metadata;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestTemplate;
import androidx.camera.camera2.pipe.Result3A;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Logger;
import androidx.camera.core.UseCase;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.MutableTagBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.TagBundle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executor;
import javax.inject.Provider;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Deferred;
import kotlinx.coroutines.Job;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.NotificationBadge;

@Metadata(d1 = {"\u0000ö\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u001a\n\u0002\u0010%\n\u0002\b\u0005\b\u0007\u0018\u0000 \u0090\u00012\u00020\u0001:\u0004\u0091\u0001\u0090\u0001BO\b\u0007\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0002\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u0002\u0012\u0006\u0010\f\u001a\u00020\u000b\u0012\n\b\u0002\u0010\u000e\u001a\u0004\u0018\u00010\r¢\u0006\u0004\b\u000f\u0010\u0010J=\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0016\u0010\u0014\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0012\u0012\u0004\u0012\u00020\u00130\u00112\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ=\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0016\u0010\u0014\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0012\u0012\u0004\u0012\u00020\u00130\u00112\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0018\u001a\u00020\u0017H\u0016¢\u0006\u0004\b\u001d\u0010\u001cJ/\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0010\u0010\u001f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u001e2\u0006\u0010\u0016\u001a\u00020\u0015H\u0016¢\u0006\u0004\b \u0010!J+\u0010'\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010#\u001a\u00020\"2\f\u0010&\u001a\b\u0012\u0004\u0012\u00020%0$H\u0016¢\u0006\u0004\b'\u0010(J1\u0010-\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010*\u001a\u00020)2\u0012\u0010,\u001a\u000e\u0012\u0004\u0012\u00020+\u0012\u0004\u0012\u00020\u00130\u0011H\u0016¢\u0006\u0004\b-\u0010.J\u0015\u00100\u001a\b\u0012\u0004\u0012\u00020/0\u0019H\u0016¢\u0006\u0004\b0\u00101J\u001d\u00106\u001a\b\u0012\u0004\u0012\u00020/0\u00192\u0006\u00103\u001a\u000202H\u0016¢\u0006\u0004\b4\u00105Ju\u0010D\u001a\b\u0012\u0004\u0012\u00020/0\u00192\u000e\u00108\u001a\n\u0012\u0004\u0012\u000207\u0018\u00010\u001e2\u000e\u00109\u001a\n\u0012\u0004\u0012\u000207\u0018\u00010\u001e2\u000e\u0010:\u001a\n\u0012\u0004\u0012\u000207\u0018\u00010\u001e2\b\u0010<\u001a\u0004\u0018\u00010;2\b\u0010=\u001a\u0004\u0018\u00010;2\b\u0010>\u001a\u0004\u0018\u00010;2\b\u0010?\u001a\u0004\u0018\u0001022\u0006\u0010A\u001a\u00020@H\u0016¢\u0006\u0004\bB\u0010CJ\u0015\u0010E\u001a\b\u0012\u0004\u0012\u00020/0\u0019H\u0016¢\u0006\u0004\bE\u00101JC\u0010M\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010L0\u00190\u001e2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020F0\u001e2\u0006\u0010I\u001a\u00020H2\u0006\u0010J\u001a\u00020H2\u0006\u0010K\u001a\u00020HH\u0016¢\u0006\u0004\bM\u0010NJE\u0010O\u001a\b\u0012\u0004\u0012\u00020/0\u00192\u000e\u00108\u001a\n\u0012\u0004\u0012\u000207\u0018\u00010\u001e2\u000e\u00109\u001a\n\u0012\u0004\u0012\u000207\u0018\u00010\u001e2\u000e\u0010:\u001a\n\u0012\u0004\u0012\u000207\u0018\u00010\u001eH\u0016¢\u0006\u0004\bO\u0010PJ\u0010\u0010Q\u001a\u00020\"H\u0096@¢\u0006\u0004\bQ\u0010RJ\u000f\u0010S\u001a\u00020\u001aH\u0016¢\u0006\u0004\bS\u0010TJ\u0013\u0010X\u001a\u00020U*\u00020\u000bH\u0000¢\u0006\u0004\bV\u0010WJ3\u0010Z\u001a\u00020Y*\u00020Y2\u0016\u0010\u0014\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0012\u0012\u0004\u0012\u00020\u00130\u00112\u0006\u0010\u0018\u001a\u00020\u0017H\u0002¢\u0006\u0004\bZ\u0010[J%\u0010\\\u001a\u00020Y*\u00020Y2\u0010\u0010\u001f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u001eH\u0002¢\u0006\u0004\b\\\u0010]J>\u0010^\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u0016\u001a\u00020\u00152\u0016\u0010\u0014\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0012\u0012\u0004\u0012\u00020\u00130\u00112\u0006\u0010\u0018\u001a\u00020\u0017H\u0082@¢\u0006\u0004\b^\u0010_J-\u0010b\u001a\u0010\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010L0\u00190\u001e2\u0006\u0010`\u001a\u00020H2\u0006\u0010a\u001a\u00020+H\u0002¢\u0006\u0004\bb\u0010cJ\u0019\u0010d\u001a\u00020\"*\b\u0012\u0004\u0012\u00020F0\u001eH\u0002¢\u0006\u0004\bd\u0010eJ\u001f\u0010f\u001a\u00020Y*\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020Y0\u0011H\u0002¢\u0006\u0004\bf\u0010gJ\u0013\u0010i\u001a\u00020h*\u00020YH\u0002¢\u0006\u0004\bi\u0010jJ,\u0010n\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019*\u00020Y2\u0010\b\u0002\u0010m\u001a\n\u0012\u0004\u0012\u00020l\u0018\u00010kH\u0082@¢\u0006\u0004\bn\u0010oJ?\u0010t\u001a\b\u0012\u0004\u0012\u00028\u00000\u0019\"\u0004\b\u0000\u0010p2\"\u0010s\u001a\u001e\b\u0001\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00190r\u0012\u0006\u0012\u0004\u0018\u00010\u00130qH\u0002¢\u0006\u0004\bt\u0010uJS\u0010w\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00190\u001e\"\u0004\b\u0000\u0010p2\u0006\u0010v\u001a\u00020H2(\u0010s\u001a$\b\u0001\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00190\u001e0r\u0012\u0006\u0012\u0004\u0018\u00010\u00130qH\u0002¢\u0006\u0004\bw\u0010xR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010yR\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010yR\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010zR\u001a\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\n\u0010yR\u0014\u0010\f\u001a\u00020\u000b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010{R\u0016\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010|R\u0016\u0010}\u001a\u00020\"8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b}\u0010~R(\u0010\u0084\u0001\u001a\n \u007f*\u0004\u0018\u00010\u00030\u00038BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0080\u0001\u0010\u0081\u0001\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001R(\u0010\u0088\u0001\u001a\n \u007f*\u0004\u0018\u00010\t0\t8BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0085\u0001\u0010\u0081\u0001\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001R(\u0010\u008c\u0001\u001a\n \u007f*\u0004\u0018\u00010\u00050\u00058BX\u0082\u0084\u0002¢\u0006\u0010\n\u0006\b\u0089\u0001\u0010\u0081\u0001\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001R$\u0010\u008e\u0001\u001a\u000f\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020Y0\u008d\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u008e\u0001\u0010\u008f\u0001¨\u0006\u0092\u0001"}, d2 = {"Landroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl;", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl;", "Ljavax/inject/Provider;", "Landroidx/camera/camera2/impl/CapturePipeline;", "capturePipelineProvider", "Landroidx/camera/camera2/impl/UseCaseCameraState;", "useCaseCameraStateProvider", "Landroidx/camera/camera2/config/UseCaseGraphContext;", "useCaseGraphContext", "Landroidx/camera/camera2/impl/UseCaseSurfaceManager;", "useCaseSurfaceManagerProvider", "Landroidx/camera/camera2/impl/UseCaseThreads;", "threads", "Landroidx/camera/core/CameraXConfig;", "cameraXConfig", "<init>", "(Ljavax/inject/Provider;Ljavax/inject/Provider;Landroidx/camera/camera2/config/UseCaseGraphContext;Ljavax/inject/Provider;Landroidx/camera/camera2/impl/UseCaseThreads;Landroidx/camera/core/CameraXConfig;)V", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/hardware/camera2/CaptureRequest$Key;", _UrlKt.FRAGMENT_ENCODE_SET, "values", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControl$Type;", "type", "Landroidx/camera/core/impl/Config$OptionPriority;", "optionPriority", "Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET, "setParametersAsync", "(Ljava/util/Map;Landroidx/camera/camera2/impl/UseCaseCameraRequestControl$Type;Landroidx/camera/core/impl/Config$OptionPriority;)Lkotlinx/coroutines/Deferred;", "submitParameters", _UrlKt.FRAGMENT_ENCODE_SET, "keys", "removeParametersAsync", "(Ljava/util/List;Landroidx/camera/camera2/impl/UseCaseCameraRequestControl$Type;)Lkotlinx/coroutines/Deferred;", _UrlKt.FRAGMENT_ENCODE_SET, "isPrimary", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "runningUseCases", "updateRepeatingRequestAsync", "(ZLjava/util/Collection;)Lkotlinx/coroutines/Deferred;", "Landroidx/camera/core/impl/Config;", "config", _UrlKt.FRAGMENT_ENCODE_SET, "tags", "updateCamera2ConfigAsync", "(Landroidx/camera/core/impl/Config;Ljava/util/Map;)Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/pipe/Result3A;", "setTorchOnAsync", "()Lkotlinx/coroutines/Deferred;", "Landroidx/camera/camera2/pipe/AeMode;", "aeMode", "setTorchOffAsync-MtizInI", "(I)Lkotlinx/coroutines/Deferred;", "setTorchOffAsync", "Landroid/hardware/camera2/params/MeteringRectangle;", "aeRegions", "afRegions", "awbRegions", "Landroidx/camera/camera2/pipe/Lock3ABehavior;", "aeLockBehavior", "afLockBehavior", "awbLockBehavior", "afTriggerStartAeMode", _UrlKt.FRAGMENT_ENCODE_SET, "timeLimitNs", "startFocusAndMeteringAsync-NxRnBj4", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;Landroidx/camera/camera2/pipe/Lock3ABehavior;Landroidx/camera/camera2/pipe/Lock3ABehavior;Landroidx/camera/camera2/pipe/Lock3ABehavior;Landroidx/camera/camera2/pipe/AeMode;J)Lkotlinx/coroutines/Deferred;", "startFocusAndMeteringAsync", "cancelFocusAndMeteringAsync", "Landroidx/camera/core/impl/CaptureConfig;", "captureSequence", _UrlKt.FRAGMENT_ENCODE_SET, "captureMode", "flashType", "flashMode", "Ljava/lang/Void;", "issueSingleCaptureAsync", "(Ljava/util/List;III)Ljava/util/List;", "update3aRegions", "(Ljava/util/List;Ljava/util/List;Ljava/util/List;)Lkotlinx/coroutines/Deferred;", "awaitSurfaceSetup", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "close", "()V", "Lkotlinx/coroutines/CoroutineStart;", "determineStartStrategy$camera_camera2", "(Landroidx/camera/camera2/impl/UseCaseThreads;)Lkotlinx/coroutines/CoroutineStart;", "determineStartStrategy", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$InfoBundle;", "withParameters", "(Landroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$InfoBundle;Ljava/util/Map;Landroidx/camera/core/impl/Config$OptionPriority;)Landroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$InfoBundle;", "withoutParameters", "(Landroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$InfoBundle;Ljava/util/List;)Landroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$InfoBundle;", "setParametersInternal", "(Landroidx/camera/camera2/impl/UseCaseCameraRequestControl$Type;Ljava/util/Map;Landroidx/camera/core/impl/Config$OptionPriority;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", NotificationBadge.NewHtcHomeBadger.COUNT, "message", "failedResults", "(ILjava/lang/String;)Ljava/util/List;", "hasInvalidSurface", "(Ljava/util/List;)Z", "merge", "(Ljava/util/Map;)Landroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$InfoBundle;", "Landroidx/camera/core/impl/TagBundle;", "toTagBundle", "(Landroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$InfoBundle;)Landroidx/camera/core/impl/TagBundle;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/StreamId;", "streams", "updateCameraStateAsync", "(Landroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$InfoBundle;Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "T", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "block", "runOnSequential", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/Deferred;", "size", "runOnSequentialList", "(ILkotlin/jvm/functions/Function1;)Ljava/util/List;", "Ljavax/inject/Provider;", "Landroidx/camera/camera2/config/UseCaseGraphContext;", "Landroidx/camera/camera2/impl/UseCaseThreads;", "Landroidx/camera/core/CameraXConfig;", "closed", "Z", "kotlin.jvm.PlatformType", "capturePipeline$delegate", "Lkotlin/Lazy;", "getCapturePipeline", "()Landroidx/camera/camera2/impl/CapturePipeline;", "capturePipeline", "useCaseSurfaceManager$delegate", "getUseCaseSurfaceManager", "()Landroidx/camera/camera2/impl/UseCaseSurfaceManager;", "useCaseSurfaceManager", "useCaseCameraState$delegate", "getUseCaseCameraState", "()Landroidx/camera/camera2/impl/UseCaseCameraState;", "useCaseCameraState", _UrlKt.FRAGMENT_ENCODE_SET, "infoBundleMap", "Ljava/util/Map;", "Companion", "InfoBundle", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUseCaseCameraRequestControl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 3 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 6 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n+ 7 UseCaseCameraConfig.kt\nandroidx/camera/camera2/config/UseCaseGraphContext\n+ 8 UseCaseThreads.kt\nandroidx/camera/camera2/impl/UseCaseThreads\n*L\n1#1,742:1\n650#1:747\n650#1:759\n650#1:760\n650#1:761\n650#1:762\n650#1:763\n650#1:764\n650#1:765\n650#1:766\n650#1:767\n650#1:781\n85#2,4:743\n85#2,4:748\n85#2,4:768\n95#2,4:784\n384#3,7:752\n1869#4:772\n1869#4,2:773\n1870#4:775\n1869#4:776\n1870#4:778\n1#5:777\n1#5:783\n1#5:792\n216#6,2:779\n242#7:782\n151#8,3:788\n177#8:791\n178#8,4:793\n*S KotlinDebug\n*F\n+ 1 UseCaseCameraRequestControl.kt\nandroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl\n*L\n350#1:747\n387#1:759\n402#1:760\n433#1:761\n446#1:762\n454#1:763\n471#1:764\n491#1:765\n515#1:766\n551#1:767\n626#1:781\n287#1:743,4\n374#1:748,4\n568#1:768,4\n659#1:784,4\n378#1:752,7\n582#1:772\n586#1:773,2\n582#1:775\n605#1:776\n605#1:778\n657#1:783\n673#1:792\n620#1:779,2\n657#1:782\n665#1:788,3\n673#1:791\n673#1:793,4\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseCameraRequestControlImpl implements UseCaseCameraRequestControl {
    public static final Companion Companion = null;
    private static final CompletableDeferred<Unit> canceledResult = null;
    private static final CompletableDeferred<Result3A> submitFailedResult = null;
    private final CameraXConfig cameraXConfig;
    private final Lazy capturePipeline$delegate;
    private final Provider<CapturePipeline> capturePipelineProvider;
    private volatile boolean closed;
    private final Map<UseCaseCameraRequestControl.Type, InfoBundle> infoBundleMap;
    private final UseCaseThreads threads;
    private final Lazy useCaseCameraState$delegate;
    private final Provider<UseCaseCameraState> useCaseCameraStateProvider;
    private final UseCaseGraphContext useCaseGraphContext;
    private final Lazy useCaseSurfaceManager$delegate;
    private final Provider<UseCaseSurfaceManager> useCaseSurfaceManagerProvider;

    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\t\u001a\u00020\n*\u00020\u000bJ\u0016\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\r*\u00020\u000bJ\u0018\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010*\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013J\u0011\u0010\u0014\u001a\u00020\u0015*\u00020\u000b¢\u0006\u0004\b\u0016\u0010\u0017J\u0014\u0010\u0018\u001a\u00020\u0019*\u00020\u000b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\f\u0010\t\u001a\u00020\n*\u00020\u001aH\u0002R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Landroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "submitFailedResult", "Lkotlinx/coroutines/CompletableDeferred;", "Landroidx/camera/camera2/pipe/Result3A;", "canceledResult", _UrlKt.FRAGMENT_ENCODE_SET, "extractCamera2ImplConfigBuilder", "Landroidx/camera/camera2/impl/Camera2ImplConfig$Builder;", "Landroidx/camera/core/impl/SessionConfig;", "extractTags", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "extractListeners", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/Request$Listener;", "callbackExecutor", "Ljava/util/concurrent/Executor;", "extractTemplate", "Landroidx/camera/camera2/pipe/RequestTemplate;", "extractTemplate-ARED-Gk", "(Landroidx/camera/core/impl/SessionConfig;)I", "toInfoBundle", "Landroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$InfoBundle;", "Landroidx/camera/core/impl/Config;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public static final /* synthetic */ Camera2ImplConfig.Builder access$extractCamera2ImplConfigBuilder(Companion r0, Config r1) {
            return r0.extractCamera2ImplConfigBuilder(r1);
        }

        public static final /* synthetic */ InfoBundle access$toInfoBundle(Companion r0, SessionConfig r1, Executor r2) {
            return r0.toInfoBundle(r1, r2);
        }

        private final Camera2ImplConfig.Builder extractCamera2ImplConfigBuilder(Config r1) {
            Camera2ImplConfig.Builder r0 = new Camera2ImplConfig.Builder();
            r0.insertAllOptions(r1);
            return r0;
        }

        private final InfoBundle toInfoBundle(SessionConfig r7, Executor r8) {
            return new InfoBundle(extractCamera2ImplConfigBuilder(r7), extractTags(r7), extractListeners(r7, r8), RequestTemplate.m370boximpl(m99extractTemplateAREDGk(r7)), null);
        }

        public final Camera2ImplConfig.Builder extractCamera2ImplConfigBuilder(SessionConfig r3) {
            Camera2ImplConfig.Builder r2 = new Camera2ImplConfig.Builder();
            if (Intrinsics.areEqual(r3.getExpectedFrameRateRange(), StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED) == true) goto L5;
            r2.setCaptureRequestOption(CaptureRequest.CONTROL_AE_TARGET_FPS_RANGE, r3.getExpectedFrameRateRange());
        L5:
            r2.insertAllOptions(r3.getImplementationOptions());
            return r2;
        }

        public final Set<Request.Listener> extractListeners(SessionConfig r1, Executor r2) {
            return SetsKt.mutableSetOf(new Request.Listener[]{CameraCallbackMap.Companion.createFor(r1.getRepeatingCameraCaptureCallbacks(), r2)});
        }

        public final Map<String, Object> extractTags(SessionConfig r1) {
            return MapsKt.toMutableMap(UseCaseCameraRequestControlKt.toMap(r1.getRepeatingCaptureConfig().getTagBundle()));
        }

        /* renamed from: extractTemplate-ARED-Gk, reason: not valid java name */
        public final int m99extractTemplateAREDGk(SessionConfig r1) {
            return RequestTemplate.m371constructorimpl(r1.getTemplateType());
        }
    }

    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0011\b\u0082\b\u0018\u00002\u00020\u0001BC\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0002\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004\u0012\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\f\u0010\rJL\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00022\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00042\u000e\b\u0002\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00072\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\nHÆ\u0001¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0011\u001a\u00020\u0005HÖ\u0001¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0014\u001a\u00020\u0013HÖ\u0001¢\u0006\u0004\b\u0014\u0010\u0015J\u001a\u0010\u0018\u001a\u00020\u00172\b\u0010\u0016\u001a\u0004\u0018\u00010\u0001HÖ\u0003¢\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR#\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0006\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010 \u001a\u0004\b!\u0010\"R$\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u000b\u0010#\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'¨\u0006("}, d2 = {"Landroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$InfoBundle;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/impl/Camera2ImplConfig$Builder;", "options", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "tags", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/Request$Listener;", "listeners", "Landroidx/camera/camera2/pipe/RequestTemplate;", "template", "<init>", "(Landroidx/camera/camera2/impl/Camera2ImplConfig$Builder;Ljava/util/Map;Ljava/util/Set;Landroidx/camera/camera2/pipe/RequestTemplate;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "copy-0am55g4", "(Landroidx/camera/camera2/impl/Camera2ImplConfig$Builder;Ljava/util/Map;Ljava/util/Set;Landroidx/camera/camera2/pipe/RequestTemplate;)Landroidx/camera/camera2/impl/UseCaseCameraRequestControlImpl$InfoBundle;", "copy", "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "()I", "other", _UrlKt.FRAGMENT_ENCODE_SET, "equals", "(Ljava/lang/Object;)Z", "Landroidx/camera/camera2/impl/Camera2ImplConfig$Builder;", "getOptions", "()Landroidx/camera/camera2/impl/Camera2ImplConfig$Builder;", "Ljava/util/Map;", "getTags", "()Ljava/util/Map;", "Ljava/util/Set;", "getListeners", "()Ljava/util/Set;", "Landroidx/camera/camera2/pipe/RequestTemplate;", "getTemplate-ejQnlcg", "()Landroidx/camera/camera2/pipe/RequestTemplate;", "setTemplate-xlOpshk", "(Landroidx/camera/camera2/pipe/RequestTemplate;)V", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class InfoBundle {
        private final Set<Request.Listener> listeners;
        private final Camera2ImplConfig.Builder options;
        private final Map<String, Object> tags;
        private RequestTemplate template;

        private InfoBundle(Camera2ImplConfig.Builder r1, Map<String, Object> r2, Set<Request.Listener> r3, RequestTemplate r4) {
            this.options = r1;
            this.tags = r2;
            this.listeners = r3;
            this.template = r4;
        }

        public /* synthetic */ InfoBundle(Camera2ImplConfig.Builder r7, Map r8, Set r9, RequestTemplate r10, int r11, DefaultConstructorMarker r12) {
            if ((r11 & 1) == 0) goto L5;
            r7 = new Camera2ImplConfig.Builder();
        L5:
            Camera2ImplConfig.Builder r1 = r7;
            if ((r11 & 2) == 0) goto L8;
            r8 = new LinkedHashMap();
        L8:
            Map r2 = r8;
            if ((r11 & 4) == 0) goto L11;
            r9 = new LinkedHashSet();
        L11:
            Set r3 = r9;
            if ((r11 & 8) == 0) goto L14;
            r10 = null;
        L14:
            this(r1, r2, r3, r10, null);
        }

        public /* synthetic */ InfoBundle(Camera2ImplConfig.Builder r1, Map r2, Set r3, RequestTemplate r4, DefaultConstructorMarker r5) {
            this(r1, r2, r3, r4);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* renamed from: copy-0am55g4$default, reason: not valid java name */
        public static /* synthetic */ InfoBundle m100copy0am55g4$default(InfoBundle r0, Camera2ImplConfig.Builder r1, Map r2, Set r3, RequestTemplate r4, int r5, Object r6) {
            if ((r5 & 1) == 0) goto L6;
            r1 = r0.options;
        L6:
            if ((r5 & 2) == 0) goto L9;
            r2 = r0.tags;
        L9:
            if ((r5 & 4) == 0) goto L12;
            r3 = r0.listeners;
        L12:
            if ((r5 & 8) == 0) goto L15;
            r4 = r0.template;
        L15:
            return r0.m101copy0am55g4(r1, r2, r3, r4);
        }

        /* renamed from: copy-0am55g4, reason: not valid java name */
        public final InfoBundle m101copy0am55g4(Camera2ImplConfig.Builder r7, Map<String, Object> r8, Set<Request.Listener> r9, RequestTemplate r10) {
            return new InfoBundle(r7, r8, r9, r10, null);
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof InfoBundle) == true) goto L8;
            return false;
        L8:
            InfoBundle r52 = (InfoBundle) r5;
            if (Intrinsics.areEqual(this.options, r52.options) == true) goto L12;
            return false;
        L12:
            if (Intrinsics.areEqual(this.tags, r52.tags) == true) goto L15;
            return false;
        L15:
            if (Intrinsics.areEqual(this.listeners, r52.listeners) == true) goto L18;
            return false;
        L18:
            if (Intrinsics.areEqual(this.template, r52.template) == true) goto L20;
            return false;
        L20:
            return true;
        }

        public final Set<Request.Listener> getListeners() {
            return this.listeners;
        }

        public final Camera2ImplConfig.Builder getOptions() {
            return this.options;
        }

        public final Map<String, Object> getTags() {
            return this.tags;
        }

        /* renamed from: getTemplate-ejQnlcg, reason: not valid java name */
        public final RequestTemplate m102getTemplateejQnlcg() {
            return this.template;
        }

        public int hashCode() {
            int r0 = ((((this.options.hashCode() * 31) + this.tags.hashCode()) * 31) + this.listeners.hashCode()) * 31;
            RequestTemplate r2 = this.template;
            if (r2 != null) goto L5;
            int r22 = 0;
        L7:
            return r0 + r22;
        L5:
            r22 = RequestTemplate.m375hashCodeimpl(r2.m377unboximpl());
            goto L7
        }

        /* renamed from: setTemplate-xlOpshk, reason: not valid java name */
        public final void m103setTemplatexlOpshk(RequestTemplate r1) {
            this.template = r1;
        }

        public String toString() {
            return "InfoBundle(options=" + this.options + ", tags=" + this.tags + ", listeners=" + this.listeners + ", template=" + this.template + ')';
        }
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n"}, d2 = {"<anonymous>", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.UseCaseCameraRequestControlImpl$submitParameters$1", f = "UseCaseCameraRequestControl.kt", i = {}, l = {365, 365}, m = "invokeSuspend", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.UseCaseCameraRequestControlImpl$submitParameters$1, reason: invalid class name */
    public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Config.OptionPriority $optionPriority;
        final /* synthetic */ UseCaseCameraRequestControl.Type $type;
        final /* synthetic */ Map<CaptureRequest.Key<?>, Object> $values;
        int label;
        final /* synthetic */ UseCaseCameraRequestControlImpl this$0;

        public AnonymousClass1(UseCaseCameraRequestControlImpl r1, UseCaseCameraRequestControl.Type r2, Map<CaptureRequest.Key<?>, ? extends Object> r3, Config.OptionPriority r4, Continuation<? super AnonymousClass1> r5) {
            this.this$0 = r1;
            this.$type = r2;
            this.$values = r3;
            this.$optionPriority = r4;
            super(2, r5);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object r7, Continuation<?> r8) {
            return new AnonymousClass1(this.this$0, this.$type, this.$values, this.$optionPriority, r8);
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
        public final Object invokeSuspend(Object r7) {
            Object r0 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int r1 = this.label;
            if (r1 == 0) goto L10;
            if (r1 == 1) goto L9;
            if (r1 != 2) goto L7;
            ResultKt.throwOnFailure(r7);
        L17:
            return Unit.INSTANCE;
        L7:
            Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
            return null;
        L9:
            ResultKt.throwOnFailure(r7);
        L13:
            this.label = 2;
            if (((Deferred) r7).await(this) != r0) goto L17;
        L15:
            return r0;
        L10:
            ResultKt.throwOnFailure(r7);
            UseCaseCameraRequestControlImpl r72 = this.this$0;
            UseCaseCameraRequestControl.Type r12 = this.$type;
            Map<CaptureRequest.Key<?>, Object> r4 = this.$values;
            Config.OptionPriority r5 = this.$optionPriority;
            this.label = 1;
            r7 = UseCaseCameraRequestControlImpl.access$setParametersInternal(r72, r12, r4, r5, this);
            if (r7 != r0) goto L13;
            goto L13
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    @DebugMetadata(c = "androidx.camera.camera2.impl.UseCaseCameraRequestControlImpl", f = "UseCaseCameraRequestControl.kt", i = {}, l = {638}, m = "updateCameraStateAsync", n = {}, s = {}, v = 1)
    /* renamed from: androidx.camera.camera2.impl.UseCaseCameraRequestControlImpl$updateCameraStateAsync$1, reason: invalid class name and case insensitive filesystem */
    public static final class C00921 extends ContinuationImpl {
        int label;
        /* synthetic */ Object result;
        final /* synthetic */ UseCaseCameraRequestControlImpl this$0;

        public C00921(UseCaseCameraRequestControlImpl r1, Continuation<? super C00921> r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object r2) {
            this.result = r2;
            this.label |= Integer.MIN_VALUE;
            return UseCaseCameraRequestControlImpl.access$updateCameraStateAsync(this.this$0, null, null, this);
        }
    }

    /* renamed from: $r8$lambda$PW1fyHjBvJXB2LDe-VExEgiiT1Y, reason: not valid java name */
    public static UseCaseSurfaceManager m97$r8$lambda$PW1fyHjBvJXB2LDeVExEgiiT1Y(UseCaseCameraRequestControlImpl r0) {
        return r0.useCaseSurfaceManagerProvider.get();
    }

    public static UseCaseCameraState $r8$lambda$RsnhUcJE9gACxjjcxyIxEdGH9O8(UseCaseCameraRequestControlImpl r0) {
        return r0.useCaseCameraStateProvider.get();
    }

    /* renamed from: $r8$lambda$tOqWGcRII-tUePQCg5ICkQwXHS4, reason: not valid java name */
    public static CapturePipeline m98$r8$lambda$tOqWGcRIItUePQCg5ICkQwXHS4(UseCaseCameraRequestControlImpl r0) {
        return r0.capturePipelineProvider.get();
    }

    static {
        Companion = new Companion(null);
        submitFailedResult = CompletableDeferredKt.CompletableDeferred(new Result3A(Result3A.Status.Companion.m386getSUBMIT_FAILEDJvTi9ms(), null, 2, null));
        CompletableDeferred<Unit> r2 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        Job.DefaultImpls.cancel$default(r2, null, 1, null);
        canceledResult = r2;
    }

    public UseCaseCameraRequestControlImpl(Provider<CapturePipeline> r1, Provider<UseCaseCameraState> r2, UseCaseGraphContext r3, Provider<UseCaseSurfaceManager> r4, UseCaseThreads r5, CameraXConfig r6) {
        this.capturePipelineProvider = r1;
        this.useCaseCameraStateProvider = r2;
        this.useCaseGraphContext = r3;
        this.useCaseSurfaceManagerProvider = r4;
        this.threads = r5;
        this.cameraXConfig = r6;
        Camera2Logger r12 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L5;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Configured " + this);
    L5:
        this.capturePipeline$delegate = LazyKt.lazy(new UseCaseCameraRequestControlImpl$$ExternalSyntheticLambda0(this));
        this.useCaseSurfaceManager$delegate = LazyKt.lazy(new UseCaseCameraRequestControlImpl$$ExternalSyntheticLambda1(this));
        this.useCaseCameraState$delegate = LazyKt.lazy(new UseCaseCameraRequestControlImpl$$ExternalSyntheticLambda2(this));
        this.infoBundleMap = new LinkedHashMap();
    }

    public static final /* synthetic */ List access$failedResults(UseCaseCameraRequestControlImpl r0, int r1, String r2) {
        return r0.failedResults(r1, r2);
    }

    public static final /* synthetic */ CapturePipeline access$getCapturePipeline(UseCaseCameraRequestControlImpl r0) {
        return r0.getCapturePipeline();
    }

    public static final /* synthetic */ Map access$getInfoBundleMap$p(UseCaseCameraRequestControlImpl r0) {
        return r0.infoBundleMap;
    }

    public static final /* synthetic */ CompletableDeferred access$getSubmitFailedResult$cp() {
        return submitFailedResult;
    }

    public static final /* synthetic */ UseCaseThreads access$getThreads$p(UseCaseCameraRequestControlImpl r0) {
        return r0.threads;
    }

    public static final /* synthetic */ UseCaseGraphContext access$getUseCaseGraphContext$p(UseCaseCameraRequestControlImpl r0) {
        return r0.useCaseGraphContext;
    }

    public static final /* synthetic */ boolean access$hasInvalidSurface(UseCaseCameraRequestControlImpl r0, List r1) {
        return r0.hasInvalidSurface(r1);
    }

    public static final /* synthetic */ InfoBundle access$merge(UseCaseCameraRequestControlImpl r0, Map r1) {
        return r0.merge(r1);
    }

    public static final /* synthetic */ Object access$setParametersInternal(UseCaseCameraRequestControlImpl r0, UseCaseCameraRequestControl.Type r1, Map r2, Config.OptionPriority r3, Continuation r4) {
        return r0.setParametersInternal(r1, r2, r3, r4);
    }

    public static final /* synthetic */ Object access$updateCameraStateAsync(UseCaseCameraRequestControlImpl r0, InfoBundle r1, Set r2, Continuation r3) {
        return r0.updateCameraStateAsync(r1, r2, r3);
    }

    public static final /* synthetic */ InfoBundle access$withoutParameters(UseCaseCameraRequestControlImpl r0, InfoBundle r1, List r2) {
        return r0.withoutParameters(r1, r2);
    }

    private final List<Deferred<Void>> failedResults(int r6, String r7) {
        ArrayList r5 = new ArrayList(r6);
        int r0 = 0;
    L3:
        if (r0 >= r6) goto L5;
        CompletableDeferred r2 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        r2.completeExceptionally(new ImageCaptureException(2, r7, null));
        r5.add(r2);
        r0 = r0 + 1;
        goto L3
    L5:
        return r5;
    }

    private final CapturePipeline getCapturePipeline() {
        return (CapturePipeline) this.capturePipeline$delegate.getValue();
    }

    private final UseCaseCameraState getUseCaseCameraState() {
        return (UseCaseCameraState) this.useCaseCameraState$delegate.getValue();
    }

    private final UseCaseSurfaceManager getUseCaseSurfaceManager() {
        return (UseCaseSurfaceManager) this.useCaseSurfaceManager$delegate.getValue();
    }

    private final boolean hasInvalidSurface(List<CaptureConfig> r5) {
        Iterator<T> r52 = r5.iterator();
    L4:
        if (r52.hasNext() == false) goto L14;
        CaptureConfig r0 = (CaptureConfig) r52.next();
        if (r0.getSurfaces().isEmpty() == true) goto L7;
        Iterator<T> r02 = r0.getSurfaces().iterator();
    L10:
        if (r02.hasNext() == false) goto L4;
        DeferrableSurface r1 = (DeferrableSurface) r02.next();
        if (this.useCaseGraphContext.getSurfaceToStreamMap().get(r1) != null) goto L10;
        return true;
    L7:
        return true;
    L14:
        return false;
    }

    private final InfoBundle merge(Map<UseCaseCameraRequestControl.Type, InfoBundle> r8) {
        InfoBundle r0 = new InfoBundle(null, null, null, RequestTemplate.m370boximpl(RequestTemplate.m371constructorimpl(1)), 7, null);
        Iterator<UseCaseCameraRequestControl.Type> r7 = UseCaseCameraRequestControl.Type.getEntries().iterator();
    L4:
        if (r7.hasNext() == false) goto L10;
        InfoBundle r1 = r8.get(r7.next());
        if (r1 == null) goto L4;
        r0.getOptions().insertAllOptions(r1.getOptions().getMutableConfig());
        r0.getTags().putAll(r1.getTags());
        r0.getListeners().addAll(r1.getListeners());
        RequestTemplate r12 = r1.m102getTemplateejQnlcg();
        if (r12 == null) goto L4;
        r0.m103setTemplatexlOpshk(RequestTemplate.m370boximpl(r12.m377unboximpl()));
        goto L4
    L10:
        return r0;
    }

    private final <T> Deferred<T> runOnSequential(Function1<? super Continuation<? super Deferred<? extends T>>, ? extends Object> r8) {
        CoroutineStart r3 = determineStartStrategy$camera_camera2(this.threads);
        UseCaseThreads r7 = this.threads;
        CompletableDeferred r0 = CompletableDeferredKt.CompletableDeferred$default(null, 1, null);
        BuildersKt.launch$default(r7.getSequentialScope(), null, r3, new UseCaseCameraRequestControlImpl$runOnSequential$$inlined$confineDeferredSuspend$1(r8, r0, null), 1, null);
        return r0;
    }

    private final <T> List<Deferred<T>> runOnSequentialList(int r8, Function1<? super Continuation<? super List<? extends Deferred<? extends T>>>, ? extends Object> r9) {
        CoroutineStart r3 = determineStartStrategy$camera_camera2(this.threads);
        UseCaseThreads r7 = this.threads;
        ArrayList r0 = new ArrayList(r8);
        int r1 = 0;
    L4:
        if (r1 >= r8) goto L6;
        r0.add(CompletableDeferredKt.CompletableDeferred$default(null, 1, null));
        r1 = r1 + 1;
        goto L4
    L6:
        BuildersKt.launch$default(r7.getSequentialScope(), null, r3, new UseCaseCameraRequestControlImpl$runOnSequentialList$$inlined$confineDeferredListSuspend$1(r9, r0, null), 1, null);
        return r0;
    }

    private final Object setParametersInternal(UseCaseCameraRequestControl.Type r10, Map<CaptureRequest.Key<?>, ? extends Object> r11, Config.OptionPriority r12, Continuation<? super Deferred<Unit>> r13) {
        Camera2Logger r0 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L5;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "UseCaseCameraRequestControlImpl#setParametersAsync: [" + r10 + "] values = " + r11 + ", optionPriority = " + r12);
    L5:
        Map<UseCaseCameraRequestControl.Type, InfoBundle> r02 = this.infoBundleMap;
        InfoBundle r1 = r02.get(r10);
        if (r1 != null) goto L8;
        InfoBundle r2 = new InfoBundle(null, null, null, null, 15, null);
        r02.put(r10, r2);
        r1 = r2;
    L8:
        this.infoBundleMap.put(r10, withParameters(r1, r11, r12));
        return updateCameraStateAsync$default(this, merge(this.infoBundleMap), null, r13, 1, null);
    }

    private final TagBundle toTagBundle(InfoBundle r3) {
        MutableTagBundle r2 = MutableTagBundle.create();
        Iterator<Map.Entry<String, Object>> r32 = r3.getTags().entrySet().iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        Map.Entry<String, Object> r0 = r32.next();
        r2.putTag(r0.getKey(), r0.getValue());
        goto L4
    L6:
        return r2;
    }

    private final Object updateCameraStateAsync(InfoBundle r11, Set<StreamId> r12, Continuation<? super Deferred<Unit>> r13) {
        if ((r13 instanceof C00921) == false) goto L8;
        C00921 r0 = (C00921) r13;
        int r1 = r0.label;
        if ((r1 & Integer.MIN_VALUE) == 0) goto L8;
        r0.label = r1 - Integer.MIN_VALUE;
    L7:
        C00921 r9 = r0;
        Object r132 = r9.result;
        Object r02 = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int r14 = r9.label;
        Deferred r2 = null;
        if (r14 == 0) goto L15;
        if (r14 != 1) goto L13;
        ResultKt.throwOnFailure(r132);
    L27:
        r2 = (Deferred) r132;
    L28:
        if (r2 == null) goto L30;
        return r2;
    L30:
        return canceledResult;
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("call to 'resume' before 'invoke' with coroutine");
        return null;
    L15:
        ResultKt.throwOnFailure(r132);
        if (this.closed == true) goto L28;
        CameraXConfig r133 = this.cameraXConfig;
        if (r133 == null) goto L20;
        Camera2CaptureRequestConfiguratorKt.getCamera2CaptureRequestConfigurator(r133);
    L20:
        CapturePipeline r134 = getCapturePipeline();
        if (r11.m102getTemplateejQnlcg().m377unboximpl() == (-1)) goto L23;
        int r15 = r11.m102getTemplateejQnlcg().m377unboximpl();
    L24:
        r134.setTemplate(r15);
        UseCaseCameraState r16 = getUseCaseCameraState();
        Map<CaptureRequest.Key<?>, ? extends Object> r22 = Camera2ImplConfigKt.toParameters(r11.getOptions().build());
        Map<Metadata.Key<?>, ? extends Object> r4 = MapsKt.mapOf(TuplesKt.to(TagsKt.getCAMERAX_TAG_BUNDLE(), toTagBundle(r11)));
        RequestTemplate r7 = r11.m102getTemplateejQnlcg();
        Set<Request.Listener> r8 = r11.getListeners();
        r9.label = 1;
        r132 = r16.m104updateAsyncTp9XwKQ(r22, false, r4, false, r12, r7, r8, r9);
        if (r132 != r02) goto L27;
        return r02;
    L23:
        r15 = 1;
    L8:
        r0 = new C00921(this, r13);
        goto L7
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ Object updateCameraStateAsync$default(UseCaseCameraRequestControlImpl r0, InfoBundle r1, Set r2, Continuation r3, int r4, Object r5) {
        if ((r4 & 1) == 0) goto L6;
        r2 = null;
    L6:
        return r0.updateCameraStateAsync(r1, r2, r3);
    }

    private final InfoBundle withParameters(InfoBundle r8, Map<CaptureRequest.Key<?>, ? extends Object> r9, Config.OptionPriority r10) {
        Camera2ImplConfig.Builder r1 = new Camera2ImplConfig.Builder();
        r1.insertAllOptions(r8.getOptions().getMutableConfig());
        r1.addAllCaptureRequestOptionsWithPriority(r9, r10);
        return InfoBundle.m100copy0am55g4$default(r8, r1, MapsKt.toMutableMap(r8.getTags()), CollectionsKt.toMutableSet(r8.getListeners()), null, 8, null);
    }

    private final InfoBundle withoutParameters(InfoBundle r8, List<? extends CaptureRequest.Key<?>> r9) {
        Camera2ImplConfig.Builder r1 = new Camera2ImplConfig.Builder();
        r1.insertAllOptions(r8.getOptions().getMutableConfig());
        r1.removeCaptureRequestOptions(r9);
        return InfoBundle.m100copy0am55g4$default(r8, r1, MapsKt.toMutableMap(r8.getTags()), CollectionsKt.toMutableSet(r8.getListeners()), null, 8, null);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public Object awaitSurfaceSetup(Continuation<? super Boolean> r1) {
        return getUseCaseSurfaceManager().awaitSetupCompletion(r1);
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public Deferred<Result3A> cancelFocusAndMeteringAsync() {
        Deferred<Result3A> r1 = null;
        if (this.closed == true) goto L5;
        r1 = runOnSequential(new UseCaseCameraRequestControlImpl$cancelFocusAndMeteringAsync$1$1(this, null));
    L5:
        if (r1 == null) goto L7;
        return r1;
    L7:
        return submitFailedResult;
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public void close() {
        this.closed = true;
        Camera2Logger r0 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L5;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "UseCaseCameraRequestControl: closed");
    L5:
        getUseCaseCameraState().close();
    }

    public final CoroutineStart determineStartStrategy$camera_camera2(UseCaseThreads r1) {
        if (r1.isOnSequentialThread() == false) goto L7;
        return CoroutineStart.UNDISPATCHED;
    L7:
        return CoroutineStart.DEFAULT;
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public List<Deferred<Void>> issueSingleCaptureAsync(List<CaptureConfig> r9, int r10, int r11, int r12) {
        if (this.closed == true) goto L5;
        UseCaseCameraRequestControlImpl r2 = this;
        List<CaptureConfig> r3 = r9;
        List<Deferred<Void>> r8 = r2.runOnSequentialList(r9.size(), new UseCaseCameraRequestControlImpl$issueSingleCaptureAsync$1$1(r2, r3, r10, r11, r12, null));
    L6:
        if (r8 == null) goto L8;
        return r8;
    L8:
        return r2.failedResults(r3.size(), "Capture request is cancelled on closed CameraGraph");
    L5:
        r2 = this;
        r3 = r9;
        r8 = null;
        goto L6
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public Deferred<Unit> removeParametersAsync(List<? extends CaptureRequest.Key<?>> r3, UseCaseCameraRequestControl.Type r4) {
        Deferred<Unit> r1 = null;
        if (this.closed == true) goto L5;
        r1 = runOnSequential(new UseCaseCameraRequestControlImpl$removeParametersAsync$1$1(this, r4, r3, null));
    L5:
        if (r1 == null) goto L7;
        return r1;
    L7:
        return canceledResult;
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public Deferred<Unit> setParametersAsync(Map<CaptureRequest.Key<?>, ? extends Object> r8, UseCaseCameraRequestControl.Type r9, Config.OptionPriority r10) {
        if (this.closed == true) goto L5;
        Deferred<Unit> r7 = runOnSequential(new UseCaseCameraRequestControlImpl$setParametersAsync$1$1(this, r9, r8, r10, null));
    L6:
        if (r7 == null) goto L8;
        return r7;
    L8:
        return canceledResult;
    L5:
        r7 = null;
        goto L6
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    /* renamed from: setTorchOffAsync-MtizInI */
    public Deferred<Result3A> mo69setTorchOffAsyncMtizInI(int r3) {
        Deferred<Result3A> r1 = null;
        if (this.closed == true) goto L5;
        r1 = runOnSequential(new UseCaseCameraRequestControlImpl$setTorchOffAsync$1$1(this, r3, null));
    L5:
        if (r1 == null) goto L7;
        return r1;
    L7:
        return submitFailedResult;
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public Deferred<Result3A> setTorchOnAsync() {
        Deferred<Result3A> r1 = null;
        if (this.closed == true) goto L5;
        r1 = runOnSequential(new UseCaseCameraRequestControlImpl$setTorchOnAsync$1$1(this, null));
    L5:
        if (r1 == null) goto L7;
        return r1;
    L7:
        return submitFailedResult;
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    /* renamed from: startFocusAndMeteringAsync-NxRnBj4 */
    public Deferred<Result3A> mo70startFocusAndMeteringAsyncNxRnBj4(List<MeteringRectangle> r13, List<MeteringRectangle> r14, List<MeteringRectangle> r15, Lock3ABehavior r16, Lock3ABehavior r17, Lock3ABehavior r18, AeMode r19, long r20) {
        if (this.closed == true) goto L5;
        Deferred<Result3A> r0 = runOnSequential(new UseCaseCameraRequestControlImpl$startFocusAndMeteringAsync$1$1(this, r13, r14, r15, r16, r17, r18, r19, r20, null));
    L6:
        if (r0 == null) goto L8;
        return r0;
    L8:
        return submitFailedResult;
    L5:
        r0 = null;
        goto L6
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public Deferred<Unit> submitParameters(Map<CaptureRequest.Key<?>, ? extends Object> r11, UseCaseCameraRequestControl.Type r12, Config.OptionPriority r13) {
        if (this.closed == true) goto L5;
        this.threads.checkOnSequentialThread();
        return BuildersKt.async$default(this.threads.getSequentialScope(), null, CoroutineStart.UNDISPATCHED, new AnonymousClass1(this, r12, r11, r13, null), 1, null);
    L5:
        return canceledResult;
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public Deferred<Result3A> update3aRegions(List<MeteringRectangle> r8, List<MeteringRectangle> r9, List<MeteringRectangle> r10) {
        if (this.closed == true) goto L5;
        Deferred<Result3A> r7 = runOnSequential(new UseCaseCameraRequestControlImpl$update3aRegions$1$1(this, r8, r9, r10, null));
    L6:
        if (r7 == null) goto L8;
        return r7;
    L8:
        return submitFailedResult;
    L5:
        r7 = null;
        goto L6
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public Deferred<Unit> updateCamera2ConfigAsync(Config r3, Map<String, ? extends Object> r4) {
        Deferred<Unit> r1 = null;
        if (this.closed == true) goto L5;
        r1 = runOnSequential(new UseCaseCameraRequestControlImpl$updateCamera2ConfigAsync$1$1(this, r3, r4, null));
    L5:
        if (r1 == null) goto L7;
        return r1;
    L7:
        return canceledResult;
    }

    @Override // androidx.camera.camera2.impl.UseCaseCameraRequestControl
    public Deferred<Unit> updateRepeatingRequestAsync(boolean r3, Collection<? extends UseCase> r4) {
        Deferred<Unit> r1 = null;
        if (this.closed == true) goto L5;
        r1 = runOnSequential(new UseCaseCameraRequestControlImpl$updateRepeatingRequestAsync$1$1(r4, r3, this, null));
    L5:
        if (r1 == null) goto L7;
        return r1;
    L7:
        return canceledResult;
    }
}
