package androidx.camera.camera2.impl;

import android.content.Context;
import android.media.MediaCodec;
import android.util.Log;
import android.util.Size;
import androidx.camera.camera2.adapter.CameraStateAdapter;
import androidx.camera.camera2.adapter.GraphStateToCameraStateAdapter;
import androidx.camera.camera2.adapter.SessionConfigAdapter;
import androidx.camera.camera2.adapter.SupportedSurfaceCombination;
import androidx.camera.camera2.adapter.ZslControl;
import androidx.camera.camera2.config.UseCaseCameraComponent;
import androidx.camera.camera2.config.UseCaseCameraConfig;
import androidx.camera.camera2.impl.MeteringRepeating;
import androidx.camera.camera2.internal.DynamicRangeResolver;
import androidx.camera.camera2.interop.Camera2CameraControl;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraPipe;
import androidx.camera.core.CameraXConfig;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.Logger;
import androidx.camera.core.UseCase;
import androidx.camera.core.concurrent.CameraCoordinator;
import androidx.camera.core.featuregroup.impl.FeatureCombinationQuery;
import androidx.camera.core.impl.AttachedSurfaceInfo;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.EncoderProfilesProvider;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.SessionProcessor;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.SurfaceConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.UseCaseUtil;
import androidx.camera.core.streamsharing.StreamSharing;
import androidx.camera.core.streamsharing.StreamSharingConfig;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.Typography;
import kotlinx.coroutines.AwaitKt;
import kotlinx.coroutines.Job;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000º\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0013\n\u0002\u0010#\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001:\u0002»\u0001B©\u0001\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u0011\u0012\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013\u0012\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u0013\u0012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u0013\u0012\u0006\u0010\u001b\u001a\u00020\u001a\u0012\u0006\u0010\u001d\u001a\u00020\u001c\u0012\u0006\u0010\u001f\u001a\u00020\u001e\u0012\u0006\u0010!\u001a\u00020 \u0012\u0006\u0010#\u001a\u00020\"\u0012\u0006\u0010%\u001a\u00020$¢\u0006\u0004\b&\u0010'J\u000f\u0010)\u001a\u00020(H\u0003¢\u0006\u0004\b)\u0010*J\u001d\u0010.\u001a\u00020(2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020,0+H\u0002¢\u0006\u0004\b.\u0010/J\u001d\u00101\u001a\u00020(2\f\u00100\u001a\b\u0012\u0004\u0012\u00020,0+H\u0003¢\u0006\u0004\b1\u0010/J\u000f\u00102\u001a\u00020(H\u0003¢\u0006\u0004\b2\u0010*J\u0017\u00105\u001a\u00020(2\u0006\u00104\u001a\u000203H\u0003¢\u0006\u0004\b5\u00106J\u0017\u00107\u001a\u00020(2\u0006\u00104\u001a\u000203H\u0003¢\u0006\u0004\b7\u00106J\u0015\u00108\u001a\b\u0012\u0004\u0012\u00020,0+H\u0003¢\u0006\u0004\b8\u00109J\u001d\u0010;\u001a\u00020:2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020,0+H\u0003¢\u0006\u0004\b;\u0010<J\u001d\u0010=\u001a\u00020:2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020,0+H\u0003¢\u0006\u0004\b=\u0010<J\u0019\u0010?\u001a\u00020:*\b\u0012\u0004\u0012\u00020,0>H\u0002¢\u0006\u0004\b?\u0010@J\u001d\u0010A\u001a\u00020:2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020,0+H\u0003¢\u0006\u0004\bA\u0010<J\u001d\u0010B\u001a\u00020:2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020,0+H\u0003¢\u0006\u0004\bB\u0010<J\u000f\u0010C\u001a\u00020(H\u0003¢\u0006\u0004\bC\u0010*J\u000f\u0010D\u001a\u00020(H\u0003¢\u0006\u0004\bD\u0010*J\u0019\u0010E\u001a\u00020:*\b\u0012\u0004\u0012\u00020,0>H\u0002¢\u0006\u0004\bE\u0010@J\u000f\u0010G\u001a\u00020FH\u0002¢\u0006\u0004\bG\u0010HJ\u001d\u0010L\u001a\u00020F2\f\u0010K\u001a\b\u0012\u0004\u0012\u00020J0IH\u0002¢\u0006\u0004\bL\u0010MJ\u001f\u0010N\u001a\b\u0012\u0004\u0012\u00020J0I*\b\u0012\u0004\u0012\u00020,0>H\u0002¢\u0006\u0004\bN\u0010OJ!\u0010R\u001a\u0010\u0012\f\u0012\n Q*\u0004\u0018\u00010P0P0I*\u00020,H\u0002¢\u0006\u0004\bR\u0010SJ\u0019\u0010T\u001a\u00020:*\b\u0012\u0004\u0012\u00020,0>H\u0002¢\u0006\u0004\bT\u0010@J\u001f\u0010V\u001a\b\u0012\u0004\u0012\u00020U0I*\b\u0012\u0004\u0012\u00020,0>H\u0002¢\u0006\u0004\bV\u0010OJ\u000f\u0010W\u001a\u00020UH\u0002¢\u0006\u0004\bW\u0010XJ\u000f\u0010Y\u001a\u00020(H\u0002¢\u0006\u0004\bY\u0010*J\u0017\u0010]\u001a\u00020(2\u0006\u0010Z\u001a\u00020:H\u0000¢\u0006\u0004\b[\u0010\\J\u0011\u0010a\u001a\u0004\u0018\u00010^H\u0000¢\u0006\u0004\b_\u0010`J\u001b\u0010c\u001a\u00020(2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020,0I¢\u0006\u0004\bc\u0010dJ\u001b\u0010e\u001a\u00020(2\f\u0010b\u001a\b\u0012\u0004\u0012\u00020,0I¢\u0006\u0004\be\u0010dJ\u0015\u0010g\u001a\u00020(2\u0006\u0010f\u001a\u00020,¢\u0006\u0004\bg\u0010hJ\u0015\u0010i\u001a\u00020(2\u0006\u0010f\u001a\u00020,¢\u0006\u0004\bi\u0010hJ\u0015\u0010j\u001a\u00020(2\u0006\u0010f\u001a\u00020,¢\u0006\u0004\bj\u0010hJ\u0015\u0010k\u001a\u00020(2\u0006\u0010f\u001a\u00020,¢\u0006\u0004\bk\u0010hJ\u0015\u0010m\u001a\u00020(2\u0006\u0010l\u001a\u00020:¢\u0006\u0004\bm\u0010\\J\u0017\u0010o\u001a\u0004\u0018\u00010(2\u0006\u0010n\u001a\u00020:¢\u0006\u0004\bo\u0010pJ\u0010\u0010q\u001a\u00020(H\u0086@¢\u0006\u0004\bq\u0010rJ\u000f\u0010t\u001a\u00020sH\u0016¢\u0006\u0004\bt\u0010uJ)\u0010}\u001a\u0002032\u0006\u0010w\u001a\u00020v2\u0006\u0010y\u001a\u00020x2\b\b\u0002\u0010z\u001a\u00020:H\u0001¢\u0006\u0004\b{\u0010|J\u001a\u0010\u0082\u0001\u001a\u00020(2\u0006\u0010\u007f\u001a\u00020~H\u0000¢\u0006\u0006\b\u0080\u0001\u0010\u0081\u0001R\u0015\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0003\u0010\u0083\u0001R\u0015\u0010\u0005\u001a\u00020\u00048\u0002X\u0083\u0004¢\u0006\u0007\n\u0005\b\u0005\u0010\u0084\u0001R\u0015\u0010\u0007\u001a\u00020\u00068\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0007\u0010\u0085\u0001R\u0015\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\t\u0010\u0086\u0001R\u0015\u0010\u000b\u001a\u00020\n8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u000b\u0010\u0087\u0001R\u001b\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\r0\f8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u000e\u0010\u0088\u0001R\u0015\u0010\u0010\u001a\u00020\u000f8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0010\u0010\u0089\u0001R\u0015\u0010\u0012\u001a\u00020\u00118\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0012\u0010\u008a\u0001R\u001b\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0015\u0010\u008b\u0001R\u001b\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00138\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0017\u0010\u008b\u0001R\u001b\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00138\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u0019\u0010\u008b\u0001R\u0015\u0010\u001b\u001a\u00020\u001a8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u001b\u0010\u008c\u0001R\u0015\u0010\u001d\u001a\u00020\u001c8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u001d\u0010\u008d\u0001R\u0015\u0010\u001f\u001a\u00020\u001e8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b\u001f\u0010\u008e\u0001R\u0015\u0010!\u001a\u00020 8\u0002X\u0082\u0004¢\u0006\u0007\n\u0005\b!\u0010\u008f\u0001R\u0017\u0010\u0090\u0001\u001a\u00020\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u0090\u0001\u0010\u0091\u0001R\u001e\u0010\u0093\u0001\u001a\t\u0012\u0004\u0012\u00020,0\u0092\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0006\b\u0093\u0001\u0010\u0088\u0001R\u001e\u0010\u0094\u0001\u001a\t\u0012\u0004\u0012\u00020,0\u0092\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0006\b\u0094\u0001\u0010\u0088\u0001R\u0019\u0010\u0095\u0001\u001a\u00020:8\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0006\b\u0095\u0001\u0010\u0096\u0001R\u0019\u0010\u0097\u0001\u001a\u00020:8\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0006\b\u0097\u0001\u0010\u0096\u0001R\u001b\u0010\u0098\u0001\u001a\u0004\u0018\u0001038\u0002@\u0002X\u0083\u000e¢\u0006\b\n\u0006\b\u0098\u0001\u0010\u0099\u0001R\u0017\u0010l\u001a\u00020:8\u0002@\u0002X\u0083\u000e¢\u0006\u0007\n\u0005\bl\u0010\u0096\u0001R\u001e\u0010\u009a\u0001\u001a\t\u0012\u0004\u0012\u00020,0\u0092\u00018\u0002X\u0083\u0004¢\u0006\b\n\u0006\b\u009a\u0001\u0010\u0088\u0001R\u0018\u0010\u009c\u0001\u001a\u00030\u009b\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009c\u0001\u0010\u009d\u0001R\u0018\u0010\u009f\u0001\u001a\u00030\u009e\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b\u009f\u0001\u0010 \u0001R\u0018\u0010¢\u0001\u001a\u00030¡\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¢\u0001\u0010£\u0001R$\u0010¥\u0001\u001a\u000f\u0012\u0004\u0012\u00020^\u0012\u0004\u0012\u00020~0¤\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¥\u0001\u0010¦\u0001R\u001c\u0010¨\u0001\u001a\u0005\u0018\u00010§\u00018\u0002@\u0002X\u0082\u000e¢\u0006\b\n\u0006\b¨\u0001\u0010©\u0001R\u001f\u0010¬\u0001\u001a\n\u0012\u0005\u0012\u00030«\u00010ª\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b¬\u0001\u0010\u00ad\u0001R&\u0010®\u0001\u001a\u0011\u0012\f\u0012\n Q*\u0004\u0018\u00010\r0\r0\u0092\u00018\u0002X\u0082\u0004¢\u0006\b\n\u0006\b®\u0001\u0010\u0088\u0001R8\u0010±\u0001\u001a\u0005\u0018\u00010¯\u00012\n\u0010°\u0001\u001a\u0005\u0018\u00010¯\u00018@@@X\u0080\u000e¢\u0006\u0018\n\u0006\b±\u0001\u0010²\u0001\u001a\u0006\b³\u0001\u0010´\u0001\"\u0006\bµ\u0001\u0010¶\u0001R\u0017\u0010º\u0001\u001a\u0005\u0018\u00010·\u00018F¢\u0006\b\u001a\u0006\b¸\u0001\u0010¹\u0001¨\u0006¼\u0001"}, d2 = {"Landroidx/camera/camera2/impl/UseCaseManager;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraPipe;", "cameraPipe", "Landroidx/camera/core/concurrent/CameraCoordinator;", "cameraCoordinator", "Landroidx/camera/camera2/config/UseCaseCameraComponent$Builder;", "builder", "Landroidx/camera/camera2/adapter/ZslControl;", "zslControl", "Landroidx/camera/camera2/impl/LowLightBoostControl;", "lowLightBoostControl", "Ljava/util/Set;", "Landroidx/camera/camera2/impl/UseCaseCameraControl;", "controls", "Landroidx/camera/camera2/interop/Camera2CameraControl;", "camera2CameraControl", "Landroidx/camera/camera2/adapter/CameraStateAdapter;", "cameraStateAdapter", "Ljavax/inject/Provider;", "Landroidx/camera/core/impl/CameraInternal;", "cameraInternal", "Landroidx/camera/camera2/impl/UseCaseThreads;", "useCaseThreads", "Landroidx/camera/core/impl/CameraInfoInternal;", "cameraInfoInternal", "Landroidx/camera/core/impl/EncoderProfilesProvider;", "encoderProfilesProvider", "Landroidx/camera/camera2/impl/CameraProperties;", "cameraProperties", "Landroidx/camera/core/CameraXConfig;", "cameraXConfig", "Landroidx/camera/camera2/impl/CameraGraphConfigProvider;", "cameraGraphConfigProvider", "Landroid/content/Context;", "context", "Landroidx/camera/camera2/impl/DisplayInfoManager;", "displayInfoManager", "<init>", "(Landroidx/camera/camera2/pipe/CameraPipe;Landroidx/camera/core/concurrent/CameraCoordinator;Landroidx/camera/camera2/config/UseCaseCameraComponent$Builder;Landroidx/camera/camera2/adapter/ZslControl;Landroidx/camera/camera2/impl/LowLightBoostControl;Ljava/util/Set;Landroidx/camera/camera2/interop/Camera2CameraControl;Landroidx/camera/camera2/adapter/CameraStateAdapter;Ljavax/inject/Provider;Ljavax/inject/Provider;Ljavax/inject/Provider;Landroidx/camera/core/impl/EncoderProfilesProvider;Landroidx/camera/camera2/impl/CameraProperties;Landroidx/camera/core/CameraXConfig;Landroidx/camera/camera2/impl/CameraGraphConfigProvider;Landroid/content/Context;Landroidx/camera/camera2/impl/DisplayInfoManager;)V", _UrlKt.FRAGMENT_ENCODE_SET, "refreshRunningUseCases", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "runningUseCases", "updateRunningUseCases", "(Ljava/util/Set;)V", "newUseCases", "refreshAttachedUseCases", "closeCurrentUseCases", "Landroidx/camera/camera2/config/UseCaseCameraConfig;", "useCaseCameraConfig", "tryResumeUseCaseManager", "(Landroidx/camera/camera2/config/UseCaseCameraConfig;)V", "beginComponentCreation", "getRunningUseCases", "()Ljava/util/Set;", _UrlKt.FRAGMENT_ENCODE_SET, "addOrRemoveRepeatingUseCase", "(Ljava/util/Set;)Z", "isMeteringRepeatingRequired", _UrlKt.FRAGMENT_ENCODE_SET, "shouldForceRepeatingStream", "(Ljava/util/Collection;)Z", "shouldAddRepeatingUseCase", "shouldRemoveRepeatingUseCase", "addRepeatingUseCase", "removeRepeatingUseCase", "isMeteringCombinationSupported", _UrlKt.FRAGMENT_ENCODE_SET, "getCameraMode", "()I", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/impl/AttachedSurfaceInfo;", "attachedSurfaceInfoList", "getRequiredMaxBitDepth", "(Ljava/util/List;)I", "getAttachedSurfaceInfoList", "(Ljava/util/Collection;)Ljava/util/List;", "Landroidx/camera/core/impl/UseCaseConfigFactory$CaptureType;", "kotlin.jvm.PlatformType", "getCaptureTypes", "(Landroidx/camera/core/UseCase;)Ljava/util/List;", "isUltraHdrOn", "Landroidx/camera/core/impl/SurfaceConfig;", "getSessionSurfacesConfigs", "createMeteringRepeatingSurfaceConfig", "()Landroidx/camera/core/impl/SurfaceConfig;", "updateZslDisabledByUseCaseConfigStatus", "createImmediately", "setCameraGraphCreationMode$camera_camera2", "(Z)V", "setCameraGraphCreationMode", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "getDeferredCameraGraphConfig$camera_camera2", "()Landroidx/camera/camera2/pipe/CameraGraph$Config;", "getDeferredCameraGraphConfig", "useCases", "attach", "(Ljava/util/List;)V", "detach", "useCase", "activate", "(Landroidx/camera/core/UseCase;)V", "deactivate", PluginsConstants.UPDATE, "reset", "isPrimary", "setPrimary", "enabled", "setActiveResumeMode", "(Z)Lkotlin/Unit;", "close", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "Landroidx/camera/camera2/adapter/SessionConfigAdapter;", "sessionConfigAdapter", "Landroidx/camera/camera2/adapter/GraphStateToCameraStateAdapter;", "graphStateToCameraStateAdapter", "isExtensions", "createUseCaseCameraConfig$camera_camera2", "(Landroidx/camera/camera2/adapter/SessionConfigAdapter;Landroidx/camera/camera2/adapter/GraphStateToCameraStateAdapter;Z)Landroidx/camera/camera2/config/UseCaseCameraConfig;", "createUseCaseCameraConfig", "Landroidx/camera/camera2/pipe/CameraGraph;", "cameraGraph", "resumeDeferredComponentCreation$camera_camera2", "(Landroidx/camera/camera2/pipe/CameraGraph;)V", "resumeDeferredComponentCreation", "Landroidx/camera/camera2/pipe/CameraPipe;", "Landroidx/camera/core/concurrent/CameraCoordinator;", "Landroidx/camera/camera2/config/UseCaseCameraComponent$Builder;", "Landroidx/camera/camera2/adapter/ZslControl;", "Landroidx/camera/camera2/impl/LowLightBoostControl;", "Ljava/util/Set;", "Landroidx/camera/camera2/interop/Camera2CameraControl;", "Landroidx/camera/camera2/adapter/CameraStateAdapter;", "Ljavax/inject/Provider;", "Landroidx/camera/core/impl/EncoderProfilesProvider;", "Landroidx/camera/camera2/impl/CameraProperties;", "Landroidx/camera/core/CameraXConfig;", "Landroidx/camera/camera2/impl/CameraGraphConfigProvider;", "lock", "Ljava/lang/Object;", _UrlKt.FRAGMENT_ENCODE_SET, "attachedUseCases", "activeUseCases", "activeResumeEnabled", "Z", "shouldCreateCameraGraphImmediately", "deferredUseCaseCameraConfig", "Landroidx/camera/camera2/config/UseCaseCameraConfig;", "pendingUseCasesToNotifyCameraControlReady", "Landroidx/camera/camera2/impl/MeteringRepeating;", "meteringRepeating", "Landroidx/camera/camera2/impl/MeteringRepeating;", "Landroidx/camera/camera2/adapter/SupportedSurfaceCombination;", "supportedSurfaceCombination", "Landroidx/camera/camera2/adapter/SupportedSurfaceCombination;", "Landroidx/camera/camera2/internal/DynamicRangeResolver;", "dynamicRangeResolver", "Landroidx/camera/camera2/internal/DynamicRangeResolver;", "Lkotlin/Function1;", "defaultCameraGraphFactory", "Lkotlin/jvm/functions/Function1;", "Landroidx/camera/camera2/config/UseCaseCameraComponent;", "_activeComponent", "Landroidx/camera/camera2/config/UseCaseCameraComponent;", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlinx/coroutines/Job;", "closingCameraJobs", "Ljava/util/List;", "allControls", "Landroidx/camera/core/impl/SessionProcessor;", "value", "sessionProcessor", "Landroidx/camera/core/impl/SessionProcessor;", "getSessionProcessor$camera_camera2", "()Landroidx/camera/core/impl/SessionProcessor;", "setSessionProcessor$camera_camera2", "(Landroidx/camera/core/impl/SessionProcessor;)V", "Landroidx/camera/camera2/impl/UseCaseCamera;", "getCamera", "()Landroidx/camera/camera2/impl/UseCaseCamera;", "camera", "RunningUseCasesChangeListener", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nUseCaseManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 UseCaseManager.kt\nandroidx/camera/camera2/impl/UseCaseManager\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n+ 4 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 5 _Maps.kt\nkotlin/collections/MapsKt___MapsKt\n*L\n1#1,747:1\n1#2:748\n119#3,4:749\n85#3,4:753\n119#3,4:762\n85#3,4:766\n85#3,4:770\n85#3,4:774\n85#3,4:789\n119#3,4:796\n774#4:757\n865#4,2:758\n1869#4,2:760\n1761#4,3:778\n774#4:781\n865#4,2:782\n1869#4,2:784\n1740#4,3:786\n1869#4:795\n1870#4:800\n808#4,11:801\n1869#4:812\n1869#4,2:813\n1870#4:815\n1761#4,3:816\n216#5,2:793\n*S KotlinDebug\n*F\n+ 1 UseCaseManager.kt\nandroidx/camera/camera2/impl/UseCaseManager\n*L\n188#1:749,4\n191#1:753,4\n227#1:762,4\n230#1:766,4\n387#1:770,4\n467#1:774,4\n613#1:789,4\n659#1:796,4\n194#1:757\n194#1:758,2\n212#1:760,2\n514#1:778,3\n521#1:781\n521#1:782,2\n538#1:784,2\n547#1:786,3\n652#1:795\n652#1:800\n696#1:801,11\n701#1:812\n702#1:813,2\n701#1:815\n724#1:816,3\n640#1:793,2\n*E\n"})
/* loaded from: classes3.dex */
public final class UseCaseManager {
    private volatile UseCaseCameraComponent _activeComponent;
    private boolean activeResumeEnabled;
    private final Set<UseCase> activeUseCases;
    private final Set<UseCaseCameraControl> allControls;
    private final Set<UseCase> attachedUseCases;
    private final UseCaseCameraComponent.Builder builder;
    private final Camera2CameraControl camera2CameraControl;
    private final CameraCoordinator cameraCoordinator;
    private final CameraGraphConfigProvider cameraGraphConfigProvider;
    private final Provider<CameraInfoInternal> cameraInfoInternal;
    private final Provider<CameraInternal> cameraInternal;
    private final CameraPipe cameraPipe;
    private final CameraProperties cameraProperties;
    private final CameraStateAdapter cameraStateAdapter;
    private final CameraXConfig cameraXConfig;
    private final List<Job> closingCameraJobs;
    private final Set<UseCaseCameraControl> controls;
    private final Function1<CameraGraph.Config, CameraGraph> defaultCameraGraphFactory;
    private UseCaseCameraConfig deferredUseCaseCameraConfig;
    private final DynamicRangeResolver dynamicRangeResolver;
    private final EncoderProfilesProvider encoderProfilesProvider;
    private boolean isPrimary;
    private final Object lock;
    private final LowLightBoostControl lowLightBoostControl;
    private final MeteringRepeating meteringRepeating;
    private final Set<UseCase> pendingUseCasesToNotifyCameraControlReady;
    private boolean shouldCreateCameraGraphImmediately;
    private final SupportedSurfaceCombination supportedSurfaceCombination;
    private final Provider<UseCaseThreads> useCaseThreads;
    private final ZslControl zslControl;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0007À\u0006\u0001"}, d2 = {"Landroidx/camera/camera2/impl/UseCaseManager$RunningUseCasesChangeListener;", _UrlKt.FRAGMENT_ENCODE_SET, "onRunningUseCasesChanged", _UrlKt.FRAGMENT_ENCODE_SET, "runningUseCases", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/core/UseCase;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public interface RunningUseCasesChangeListener {
        void onRunningUseCasesChanged(Set<? extends UseCase> r1);
    }

    public static /* synthetic */ CameraGraph $r8$lambda$VpH9M1AGkIirqgAsBEVcikv2Vtc(CameraGraph r0, CameraGraph.Config r1) {
        return resumeDeferredComponentCreation$lambda$0$0(r0, r1);
    }

    public static /* synthetic */ Unit $r8$lambda$YeDXdnPC1j3yCifXlJ67BHina9I(UseCaseManager r0, Job r1, Throwable r2) {
        return closeCurrentUseCases$lambda$0$0$0(r0, r1, r2);
    }

    /* renamed from: $r8$lambda$igoGai8aYKmFGamYaqvk-UAnVWM, reason: not valid java name */
    public static CameraGraph m105$r8$lambda$igoGai8aYKmFGamYaqvkUAnVWM(UseCaseManager r0, CameraGraph.Config r1) {
        return r0.cameraPipe.createCameraGraph(r1);
    }

    public UseCaseManager(CameraPipe r1, CameraCoordinator r2, UseCaseCameraComponent.Builder r3, ZslControl r4, LowLightBoostControl r5, Set<UseCaseCameraControl> r6, Camera2CameraControl r7, CameraStateAdapter r8, Provider<CameraInternal> r9, Provider<UseCaseThreads> r10, Provider<CameraInfoInternal> r11, EncoderProfilesProvider r12, CameraProperties r13, CameraXConfig r14, CameraGraphConfigProvider r15, Context r16, DisplayInfoManager r17) {
        this.cameraPipe = r1;
        this.cameraCoordinator = r2;
        this.builder = r3;
        this.zslControl = r4;
        this.lowLightBoostControl = r5;
        this.controls = r6;
        this.camera2CameraControl = r7;
        this.cameraStateAdapter = r8;
        this.cameraInternal = r9;
        this.useCaseThreads = r10;
        this.cameraInfoInternal = r11;
        this.encoderProfilesProvider = r12;
        this.cameraProperties = r13;
        this.cameraXConfig = r14;
        this.cameraGraphConfigProvider = r15;
        this.lock = new Object();
        this.attachedUseCases = new LinkedHashSet();
        this.activeUseCases = new LinkedHashSet();
        this.shouldCreateCameraGraphImmediately = true;
        this.isPrimary = true;
        this.pendingUseCasesToNotifyCameraControlReady = new LinkedHashSet();
        this.meteringRepeating = new MeteringRepeating.Builder(r13, r17).build();
        this.supportedSurfaceCombination = new SupportedSurfaceCombination(r16, r13.getMetadata(), r12, FeatureCombinationQuery.NO_OP_FEATURE_COMBINATION_QUERY);
        this.dynamicRangeResolver = new DynamicRangeResolver(r13.getMetadata());
        this.defaultCameraGraphFactory = new UseCaseManager$$ExternalSyntheticLambda2(this);
        this.closingCameraJobs = new ArrayList();
        Set<UseCaseCameraControl> r18 = CollectionsKt.toMutableSet(r6);
        r18.add(r7);
        this.allControls = r18;
    }

    public static final /* synthetic */ MeteringRepeating access$getMeteringRepeating$p(UseCaseManager r0) {
        return r0.meteringRepeating;
    }

    public static final /* synthetic */ Set access$getPendingUseCasesToNotifyCameraControlReady$p(UseCaseManager r0) {
        return r0.pendingUseCasesToNotifyCameraControlReady;
    }

    private final boolean addOrRemoveRepeatingUseCase(Set<? extends UseCase> r3) {
        if (shouldAddRepeatingUseCase(r3) == false) goto L7;
        addRepeatingUseCase();
        return true;
    L7:
        if (shouldRemoveRepeatingUseCase(r3) == false) goto L10;
        removeRepeatingUseCase();
        return true;
    L10:
        return false;
    }

    private final void addRepeatingUseCase() {
        this.meteringRepeating.bindToCamera(this.cameraInternal.get(), null, null, null);
        this.meteringRepeating.setupSession();
        attach(CollectionsKt.listOf(this.meteringRepeating));
        activate(this.meteringRepeating);
    }

    private final void beginComponentCreation(UseCaseCameraConfig r4) {
        this._activeComponent = this.builder.config(r4).build();
        UseCaseCamera r42 = getCamera();
        if (r42 == null) goto L17;
        r42.start();
        Iterator<UseCaseCameraControl> r0 = this.allControls.iterator();
    L6:
        if (r0.hasNext() == false) goto L8;
        r0.next().setRequestControl(r42.getRequestControl());
        goto L6
    L8:
        r42.setActiveResumeMode(this.activeResumeEnabled);
        updateRunningUseCases(getRunningUseCases());
        Camera2Logger r43 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L11;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Notifying " + access$getPendingUseCasesToNotifyCameraControlReady$p(this) + " camera control ready");
    L11:
        Iterator<UseCase> r44 = this.pendingUseCasesToNotifyCameraControlReady.iterator();
    L13:
        if (r44.hasNext() == false) goto L15;
        r44.next().onCameraControlReady();
        goto L13
    L15:
        this.pendingUseCasesToNotifyCameraControlReady.clear();
        return;
    L17:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
    }

    private final void closeCurrentUseCases() {
        UseCaseCamera r0 = getCamera();
        this._activeComponent = null;
        this.cameraCoordinator.removePendingCameraInfo(this.cameraInfoInternal.get());
        if (r0 == null) goto L7;
        Job r02 = r0.close();
        if (r02 == null) goto L7;
        this.closingCameraJobs.add(r02);
        r02.invokeOnCompletion(new UseCaseManager$$ExternalSyntheticLambda0(this, r02));
    L7:
        getSessionProcessor$camera_camera2();
    }

    private static final Unit closeCurrentUseCases$lambda$0$0$0(UseCaseManager r0, Job r1, Throwable r2) {
        Object r22 = r0.lock;
        monitor-enter(r22);
        r0.closingCameraJobs.remove(r1);     // Catch: Throwable -> L8
        monitor-exit(r22);
        return Unit.INSTANCE;
    L8:
        th = move-exception;
        throw th;
    }

    private final SurfaceConfig createMeteringRepeatingSurfaceConfig() {
        return this.supportedSurfaceCombination.transformSurfaceConfig(getCameraMode(), this.meteringRepeating.getImageFormat(), this.meteringRepeating.getAttachedSurfaceResolution(), this.meteringRepeating.getCurrentConfig().getStreamUseCase());
    }

    private final List<AttachedSurfaceInfo> getAttachedSurfaceInfoList(Collection<? extends UseCase> r14) {
        ArrayList r0 = new ArrayList();
        Iterator<T> r142 = r14.iterator();
    L4:
        if (r142.hasNext() == false) goto L17;
        UseCase r1 = (UseCase) r142.next();
        Size r4 = r1.getAttachedSurfaceResolution();
        StreamSpec r2 = r1.getAttachedStreamSpec();
        if (r4 == null) goto L13;
        if (r2 == null) goto L13;
        SurfaceConfig r3 = this.supportedSurfaceCombination.transformSurfaceConfig(getCameraMode(), r1.getCurrentConfig().getInputFormat(), r4, r1.getCurrentConfig().getStreamUseCase());
        int r5 = r1.getCurrentConfig().getInputFormat();
        DynamicRange r52 = r2.getDynamicRange();
        List<UseCaseConfigFactory.CaptureType> r6 = getCaptureTypes(r1);
        Config r8 = r2.getImplementationOptions();
        if (r8 != null) goto L12;
        r8 = MutableOptionsBundle.create();
    L12:
        Config r7 = r8;
        r0.add(AttachedSurfaceInfo.create(r3, r5, r4, r52, r6, r7, r2.getSessionType(), r2.getExpectedFrameRateRange(), r1.getCurrentConfig().isStrictFrameRateRequired(), r1.getCurrentConfig().getCustomMaxFrameRate(r4)));
    L13:
        Camera2Logger r13 = Camera2Logger.INSTANCE;
        if (Logger.isWarnEnabled("CXCP") == false) goto L16;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Invalid surface resolution or stream spec is found.");
    L16:
        r0.clear();
    L17:
        return r0;
    }

    private final int getCameraMode() {
        Object r0 = this.lock;
        monitor-enter(r0);
    L14:
        th = move-exception;
        throw th;
    L6:
        if (this.cameraCoordinator.getCameraOperatingMode() != 2) goto L10;
        monitor-exit(r0);
        return 1;
    L10:
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L14
        monitor-exit(r0);
        return 0;
    }

    private final List<UseCaseConfigFactory.CaptureType> getCaptureTypes(UseCase r1) {
        if ((r1 instanceof StreamSharing) == false) goto L7;
        return ((StreamSharingConfig) ((StreamSharing) r1).getCurrentConfig()).getCaptureTypes();
    L7:
        return CollectionsKt.listOf(r1.getCurrentConfig().getCaptureType());
    }

    private final int getRequiredMaxBitDepth(List<? extends AttachedSurfaceInfo> r3) {
        Iterator<Map.Entry<UseCaseConfig<?>, DynamicRange>> r2 = this.dynamicRangeResolver.resolveAndValidateDynamicRanges(r3, CollectionsKt.listOf(this.meteringRepeating.getCurrentConfig()), CollectionsKt.listOf(0)).entrySet().iterator();
    L4:
        if (r2.hasNext() == false) goto L8;
        if (r2.next().getValue().getBitDepth() != 10) goto L4;
        return 10;
    L8:
        return 8;
    }

    private final Set<UseCase> getRunningUseCases() {
        return CollectionsKt.intersect(this.attachedUseCases, this.activeUseCases);
    }

    private final List<SurfaceConfig> getSessionSurfacesConfigs(Collection<? extends UseCase> r9) {
        ArrayList r0 = new ArrayList();
        Iterator<T> r92 = r9.iterator();
    L4:
        if (r92.hasNext() == false) goto L9;
        UseCase r1 = (UseCase) r92.next();
        Iterator<T> r2 = r1.getSessionConfig().getSurfaces().iterator();
    L7:
        if (r2.hasNext() == false) goto L4;
        DeferrableSurface r3 = (DeferrableSurface) r2.next();
        r0.add(this.supportedSurfaceCombination.transformSurfaceConfig(getCameraMode(), r1.getCurrentConfig().getInputFormat(), r3.getPrescribedSize(), r1.getCurrentConfig().getStreamUseCase()));
        goto L7
    L9:
        return r0;
    }

    private final boolean isMeteringCombinationSupported(Collection<? extends UseCase> r18) {
        if (this.meteringRepeating.getAttachedSurfaceResolution() != null) goto L5;
        this.meteringRepeating.setupSession();
    L5:
        List<AttachedSurfaceInfo> r1 = getAttachedSurfaceInfoList(r18);
        if (r1.isEmpty() == false) goto L9;
        return false;
    L9:
        List<SurfaceConfig> r2 = getSessionSurfacesConfigs(r18);
        SupportedSurfaceCombination r3 = this.supportedSurfaceCombination;
        SupportedSurfaceCombination.FeatureSettings r4 = new SupportedSurfaceCombination.FeatureSettings(getCameraMode(), getRequiredMaxBitDepth(r1), UseCaseUtil.containsVideoCapture(r18), UseCaseUtil.getVideoStabilization$default(r18, null, 1, null), isUltraHdrOn(r18), false, false, false, null, false, 992, null);
        ArrayList r5 = new ArrayList();
        r5.addAll(r2);
        r5.add(createMeteringRepeatingSurfaceConfig());
        Unit r12 = Unit.INSTANCE;
        boolean r13 = SupportedSurfaceCombination.checkSupported$default(r3, r4, r5, null, null, null, 28, null);
        Camera2Logger r32 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L12;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Combination of " + r2 + " + " + access$getMeteringRepeating$p(this) + " is supported: " + r13);
    L12:
        return r13;
    }

    private final boolean isMeteringRepeatingRequired(Set<? extends UseCase> r6) {
        if (this.cameraXConfig.isRepeatingStreamForced() == true) goto L5;
        return false;
    L5:
        if (r6 != null) goto L7;
    L9:
        Iterator<T> r62 = r6.iterator();
    L11:
        if (r62.hasNext() == false) goto L31;
        UseCase r0 = (UseCase) r62.next();
        if (Intrinsics.areEqual(r0, this.meteringRepeating) == true) goto L11;
        if (r0.getSessionConfig().getSurfaces().isEmpty() == true) goto L11;
        Set<UseCase> r63 = this.attachedUseCases;
        ArrayList r02 = new ArrayList();
        Iterator<T> r64 = r63.iterator();
    L18:
        if (r64.hasNext() == false) goto L23;
        Object r2 = r64.next();
        if (Intrinsics.areEqual((UseCase) r2, this.meteringRepeating) == true) goto L18;
        r02.add(r2);
        goto L18
    L23:
        if (r02.isEmpty() == false) goto L26;
        return false;
    L26:
        if (shouldForceRepeatingStream(r02) == false) goto L31;
        if (isMeteringCombinationSupported(r02) == false) goto L31;
        return true;
    L31:
        return false;
    L7:
        if (r6.isEmpty() == false) goto L9;
        goto L9
    }

    private final boolean isUltraHdrOn(Collection<? extends UseCase> r3) {
        ArrayList r2 = new ArrayList();
        Iterator<T> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L8;
        Object r0 = r32.next();
        if ((r0 instanceof ImageCapture) == false) goto L4;
        r2.add(r0);
        goto L4
    L8:
        ImageCapture r22 = (ImageCapture) CollectionsKt.firstOrNull(r2);
        if (r22 == null) goto L16;
        UseCaseConfig<?> r23 = r22.getCurrentConfig();
        if (r23 == null) goto L16;
        if (r23.getInputFormat() != 4101) goto L16;
        return true;
    L16:
        return false;
    }

    private final void refreshAttachedUseCases(Set<? extends UseCase> r4) {
        closeCurrentUseCases();
        List r42 = CollectionsKt.toList(r4);
        if (r42.isEmpty() == false) goto L10;
        Iterator<UseCaseCameraControl> r3 = this.allControls.iterator();
    L6:
        if (r3.hasNext() == false) goto L8;
        UseCaseCameraControl r43 = r3.next();
        r43.setRequestControl(null);
        r43.reset();
        goto L6
    L8:
        return;
    L10:
        if (this.shouldCreateCameraGraphImmediately == true) goto L15;
        Iterator<UseCaseCameraControl> r0 = this.allControls.iterator();
    L13:
        if (r0.hasNext() == false) goto L15;
        r0.next().setRequestControl(null);
    L15:
        GraphStateToCameraStateAdapter r02 = new GraphStateToCameraStateAdapter(this.cameraStateAdapter);
        getSessionProcessor$camera_camera2();
        tryResumeUseCaseManager(createUseCaseCameraConfig$camera_camera2(new SessionConfigAdapter(r42, this.isPrimary), r02, false));
    }

    private final void refreshRunningUseCases() {
        if (this.attachedUseCases.isEmpty() == false) goto L5;
        return;
    L5:
        Set<UseCase> r0 = getRunningUseCases();
        if (shouldAddRepeatingUseCase(r0) == false) goto L10;
        addRepeatingUseCase();
        return;
    L10:
        if (shouldRemoveRepeatingUseCase(r0) == false) goto L13;
        removeRepeatingUseCase();
        return;
    L13:
        updateRunningUseCases(r0);
    }

    private final void removeRepeatingUseCase() {
        deactivate(this.meteringRepeating);
        detach(CollectionsKt.listOf(this.meteringRepeating));
        this.meteringRepeating.unbindFromCamera(this.cameraInternal.get());
    }

    private static final CameraGraph resumeDeferredComponentCreation$lambda$0$0(CameraGraph r0, CameraGraph.Config r1) {
        return r0;
    }

    private final boolean shouldAddRepeatingUseCase(Set<? extends UseCase> r4) {
        if (this.cameraXConfig.isRepeatingStreamForced() == true) goto L6;
        return false;
    L6:
        if (this.attachedUseCases.contains(this.meteringRepeating) == false) goto L8;
    L11:
        return false;
    L8:
        if (isMeteringRepeatingRequired(r4) == false) goto L11;
        return true;
    }

    private final boolean shouldForceRepeatingStream(Collection<? extends UseCase> r5) {
        if (r5.isEmpty() == false) goto L5;
        return false;
    L5:
        SessionConfig.ValidatingBuilder r4 = new SessionConfig.ValidatingBuilder();
        Iterator<T> r52 = r5.iterator();
    L7:
        if (r52.hasNext() == false) goto L9;
        r4.add(((UseCase) r52.next()).getSessionConfig());
        goto L7
    L9:
        SessionConfig r42 = r4.build();
        List<DeferrableSurface> r53 = r42.getRepeatingCaptureConfig().getSurfaces();
        List<DeferrableSurface> r43 = r42.getSurfaces();
        if (r43.isEmpty() == false) goto L12;
        return false;
    L12:
        List<DeferrableSurface> r44 = r43;
        if ((r44 instanceof Collection) == true) goto L15;
    L17:
        Iterator<T> r45 = r44.iterator();
    L19:
        if (r45.hasNext() == false) goto L16;
        if (Intrinsics.areEqual(((DeferrableSurface) r45.next()).getContainerClass(), MediaCodec.class) == true) goto L19;
        boolean r46 = false;
    L23:
        boolean r54 = r53.isEmpty();
        if (r46 == true) goto L28;
        if (r54 == true) goto L28;
        return false;
    L28:
        return true;
    L16:
        r46 = true;
        goto L23
    L15:
        if (r44.isEmpty() == false) goto L17;
        goto L16
    }

    private final boolean shouldRemoveRepeatingUseCase(Set<? extends UseCase> r2) {
        if (r2.contains(this.meteringRepeating) == true) goto L5;
        return false;
    L5:
        if (isMeteringRepeatingRequired(r2) == true) goto L10;
        return true;
    L10:
        return false;
    }

    private final void tryResumeUseCaseManager(UseCaseCameraConfig r2) {
        if (this.shouldCreateCameraGraphImmediately == true) goto L6;
        this.deferredUseCaseCameraConfig = r2;
        this.cameraCoordinator.addPendingCameraInfo(this.cameraInfoInternal.get());
        return;
    L6:
        beginComponentCreation(r2);
    }

    private final void updateRunningUseCases(Set<? extends UseCase> r3) {
        UseCaseCamera r0 = getCamera();
        if (r0 == null) goto L10;
        r0.updateRepeatingRequestAsync(this.isPrimary, r3);
        Iterator<UseCaseCameraControl> r2 = this.allControls.iterator();
    L6:
        if (r2.hasNext() == false) goto L16;
        UseCaseCameraControl r02 = r2.next();
        if ((r02 instanceof RunningUseCasesChangeListener) == false) goto L6;
        ((RunningUseCasesChangeListener) r02).onRunningUseCasesChanged(r3);
        goto L6
    L16:
        return;
    }

    private final void updateZslDisabledByUseCaseConfigStatus() {
        Set<UseCase> r0 = this.attachedUseCases;
        boolean r1 = false;
        if (r0 != null) goto L5;
    L7:
        Iterator<T> r02 = r0.iterator();
    L9:
        if (r02.hasNext() == false) goto L13;
        if (((UseCase) r02.next()).getCurrentConfig().isZslDisabled(false) == false) goto L9;
        r1 = true;
    L13:
        this.zslControl.setZslDisabledByUserCaseConfig(r1);
        return;
    L5:
        if (r0.isEmpty() == false) goto L7;
        goto L7
    }

    public final void activate(UseCase r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.activeUseCases.add(r3) == false) goto L9;
        refreshRunningUseCases();     // Catch: Throwable -> L7
    L9:
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
    }

    public final void attach(List<? extends UseCase> r7) {
        Object r2 = this.lock;
        monitor-enter(r2);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (r7.isEmpty() == false) goto L14;
        Camera2Logger r72 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L10
        if (Logger.isWarnEnabled("CXCP") == false) goto L12;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Attach [] from " + this + " (Ignored)");     // Catch: Throwable -> L10
    L12:
        monitor-exit(r2);
        return;
    L14:
        Camera2Logger r1 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L10
        if (Logger.isDebugEnabled("CXCP") == false) goto L17;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Attaching " + r7 + " from " + this);     // Catch: Throwable -> L10
    L17:
        ArrayList r12 = new ArrayList();     // Catch: Throwable -> L10
        Iterator<T> r0 = r7.iterator();     // Catch: Throwable -> L10
    L19:
        if (r0.hasNext() == false) goto L23;
        Object r3 = r0.next();     // Catch: Throwable -> L10
        if (this.attachedUseCases.contains((UseCase) r3) == true) goto L19;
        r12.add(r3);     // Catch: Throwable -> L10
        goto L19
    L23:
        int r02 = r12.size();     // Catch: Throwable -> L10
        int r32 = 0;
        int r4 = 0;
    L24:
        if (r4 >= r02) goto L27;
        Object r5 = r12.get(r4);     // Catch: Throwable -> L10
        r4 = r4 + 1;     // Catch: Throwable -> L10
        ((UseCase) r5).onSessionStart();     // Catch: Throwable -> L10
        goto L24
    L27:
        if (this.attachedUseCases.addAll(r7) == false) goto L32;
        if (addOrRemoveRepeatingUseCase(getRunningUseCases()) == true) goto L32;
        updateZslDisabledByUseCaseConfigStatus();     // Catch: Throwable -> L10
        this.lowLightBoostControl.onSessionConfigChanged(CollectionsKt.toList(this.attachedUseCases));     // Catch: Throwable -> L10
        refreshAttachedUseCases(this.attachedUseCases);     // Catch: Throwable -> L10
    L32:
        if (this.shouldCreateCameraGraphImmediately == true) goto L34;
        this.pendingUseCasesToNotifyCameraControlReady.addAll(r12);     // Catch: Throwable -> L10
    L37:
        Unit r6 = Unit.INSTANCE;     // Catch: Throwable -> L10
        monitor-exit(r2);
        return;
    L34:
        int r62 = r12.size();     // Catch: Throwable -> L10
    L35:
        if (r32 >= r62) goto L37;
        Object r73 = r12.get(r32);     // Catch: Throwable -> L10
        r32 = r32 + 1;     // Catch: Throwable -> L10
        ((UseCase) r73).onCameraControlReady();     // Catch: Throwable -> L10
        goto L35
    }

    public final Object close(Continuation<? super Unit> r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
        closeCurrentUseCases();     // Catch: Throwable -> L11
        this.meteringRepeating.onUnbind();     // Catch: Throwable -> L11
        List r2 = CollectionsKt.toList(this.closingCameraJobs);     // Catch: Throwable -> L11
        monitor-exit(r0);
        Object r22 = AwaitKt.joinAll(r2, r3);
        if (r22 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L10;
        return r22;
    L10:
        return Unit.INSTANCE;
    L11:
        th = move-exception;
        throw th;
    }

    public final UseCaseCameraConfig createUseCaseCameraConfig$camera_camera2(SessionConfigAdapter r8, GraphStateToCameraStateAdapter r9, boolean r10) {
        UseCaseCameraConfig.Companion r0 = UseCaseCameraConfig.Companion;
        CameraGraphConfigProvider r2 = this.cameraGraphConfigProvider;
        Function1<CameraGraph.Config, CameraGraph> r3 = this.defaultCameraGraphFactory;
        getSessionProcessor$camera_camera2();
        return r0.create(r8, r2, r3, r9, null, r10);
    }

    public final void deactivate(UseCase r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.activeUseCases.remove(r3) == false) goto L9;
        refreshRunningUseCases();     // Catch: Throwable -> L7
    L9:
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
    }

    public final void detach(List<? extends UseCase> r5) {
        Object r2 = this.lock;
        monitor-enter(r2);
    L10:
        th = move-exception;
        throw th;
    L5:
        if (r5.isEmpty() == false) goto L14;
        Camera2Logger r52 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L10
        if (Logger.isWarnEnabled("CXCP") == false) goto L12;
        Log.w(Camera2Logger.access$getTRUNCATED_TAG$p(), "Detaching [] from " + this + " (Ignored)");     // Catch: Throwable -> L10
    L12:
        monitor-exit(r2);
        return;
    L14:
        Camera2Logger r1 = Camera2Logger.INSTANCE;     // Catch: Throwable -> L10
        if (Logger.isDebugEnabled("CXCP") == false) goto L17;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Detaching " + r5 + " from " + this);     // Catch: Throwable -> L10
    L17:
        this.activeUseCases.removeAll(r5);     // Catch: Throwable -> L10
        Iterator<? extends UseCase> r0 = r5.iterator();     // Catch: Throwable -> L10
    L19:
        if (r0.hasNext() == false) goto L24;
        UseCase r12 = r0.next();     // Catch: Throwable -> L10
        if (this.attachedUseCases.contains(r12) == false) goto L19;
        r12.onSessionStop();     // Catch: Throwable -> L10
        goto L19
    L24:
        if (this.attachedUseCases.removeAll(r5) == true) goto L26;
    L34:
        this.pendingUseCasesToNotifyCameraControlReady.removeAll(r5);     // Catch: Throwable -> L10
        Unit r4 = Unit.INSTANCE;     // Catch: Throwable -> L10
        monitor-exit(r2);
        return;
    L26:
        if (addOrRemoveRepeatingUseCase(getRunningUseCases()) == false) goto L30;
        monitor-exit(r2);
        return;
    L30:
        if (this.attachedUseCases.isEmpty() == false) goto L32;
        this.zslControl.setZslDisabledByUserCaseConfig(false);     // Catch: Throwable -> L10
        this.lowLightBoostControl.onSessionConfigChanged(CollectionsKt.emptyList());     // Catch: Throwable -> L10
    L33:
        refreshAttachedUseCases(this.attachedUseCases);     // Catch: Throwable -> L10
        goto L34
    L32:
        updateZslDisabledByUseCaseConfigStatus();     // Catch: Throwable -> L10
        this.lowLightBoostControl.onSessionConfigChanged(CollectionsKt.toList(this.attachedUseCases));     // Catch: Throwable -> L10
        goto L33
    }

    public final UseCaseCamera getCamera() {
        UseCaseCameraComponent r0 = this._activeComponent;
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.getUseCaseCamera();
    }

    public final CameraGraph.Config getDeferredCameraGraphConfig$camera_camera2() {
        Object r0 = this.lock;
        monitor-enter(r0);
        UseCaseCameraConfig r1 = this.deferredUseCaseCameraConfig;     // Catch: Throwable -> L8
        if (r1 == null) goto L10;
        CameraGraph.Config r12 = r1.getCameraGraphConfig();     // Catch: Throwable -> L8
    L11:
        monitor-exit(r0);
        return r12;
    L10:
        r12 = null;
    L8:
        th = move-exception;
        throw th;
    }

    public final SessionProcessor getSessionProcessor$camera_camera2() {
        Object r0 = this.lock;
        monitor-enter(r0);
        monitor-exit(r0);
        return null;
    }

    public final void reset(UseCase r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.attachedUseCases.contains(r3) == false) goto L9;
        refreshAttachedUseCases(this.attachedUseCases);     // Catch: Throwable -> L7
    L9:
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
    }

    public final void resumeDeferredComponentCreation$camera_camera2(CameraGraph r11) {
        Object r1 = this.lock;
        monitor-enter(r1);
        UseCaseCameraConfig r2 = this.deferredUseCaseCameraConfig;     // Catch: Throwable -> L9
        if (r2 == null) goto L12;
        beginComponentCreation(UseCaseCameraConfig.copy$default(r2, new UseCaseManager$$ExternalSyntheticLambda1(r11), null, null, null, null, 30, null));     // Catch: Throwable -> L9
        Unit r10 = Unit.INSTANCE;     // Catch: Throwable -> L9
        monitor-exit(r1);
        return;
    L12:
        throw new IllegalStateException("Required value was null.");     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    }

    public final Unit setActiveResumeMode(boolean r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.activeResumeEnabled = r2;     // Catch: Throwable -> L8
        UseCaseCamera r1 = getCamera();     // Catch: Throwable -> L8
        if (r1 == null) goto L10;
        r1.setActiveResumeMode(r2);     // Catch: Throwable -> L8
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L8
    L11:
        monitor-exit(r0);
        return r12;
    L10:
        r12 = null;
    L8:
        th = move-exception;
        throw th;
    }

    public final void setCameraGraphCreationMode$camera_camera2(boolean r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.shouldCreateCameraGraphImmediately = r2;     // Catch: Throwable -> L7
        if (r2 == false) goto L9;
        this.deferredUseCaseCameraConfig = null;     // Catch: Throwable -> L7
    L9:
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final void setPrimary(boolean r2) {
        Object r0 = this.lock;
        monitor-enter(r0);
        this.isPrimary = r2;     // Catch: Throwable -> L7
        Unit r1 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public final void setSessionProcessor$camera_camera2(SessionProcessor r1) {
        Object r0 = this.lock;
        monitor-enter(r0);
        Unit r12 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
        return;
    L7:
        th = move-exception;
        throw th;
    }

    public String toString() {
        return "UseCaseManager<" + this.cameraGraphConfigProvider + Typography.greater;
    }

    public final void update(UseCase r3) {
        Object r0 = this.lock;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (this.attachedUseCases.contains(r3) == false) goto L9;
        refreshRunningUseCases();     // Catch: Throwable -> L7
    L9:
        Unit r2 = Unit.INSTANCE;     // Catch: Throwable -> L7
        monitor-exit(r0);
    }
}
