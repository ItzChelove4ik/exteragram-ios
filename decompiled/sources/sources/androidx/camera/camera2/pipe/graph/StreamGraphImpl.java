package androidx.camera.camera2.pipe.graph;

import android.hardware.camera2.params.OutputConfiguration;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.util.Size;
import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0;
import androidx.camera.camera2.pipe.CameraController;
import androidx.camera.camera2.pipe.CameraGraph;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.CameraStream;
import androidx.camera.camera2.pipe.InputStream;
import androidx.camera.camera2.pipe.InputStreamId;
import androidx.camera.camera2.pipe.OutputId;
import androidx.camera.camera2.pipe.OutputStream;
import androidx.camera.camera2.pipe.StreamFormat;
import androidx.camera.camera2.pipe.StreamGraph;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.camera2.pipe.media.ImageSource;
import androidx.camera.camera2.pipe.media.ImageSources;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.inject.Provider;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.atomicfu.AtomicFU;
import kotlinx.atomicfu.AtomicInt;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000¨\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\f\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\b\u000b\b\u0001\u0018\u0000 W2\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0004XYZWB/\b\u0007\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n¢\u0006\u0004\b\r\u0010\u000eJ\u0019\u0010\u0012\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J\u0017\u0010\u0015\u001a\u00020\u00142\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u0015\u0010\u0016J#\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00140\u00172\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017H\u0002¢\u0006\u0004\b\u001a\u0010\u001bJ\u001f\u0010\u001d\u001a\u00020\u001c2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u001d\u0010\u001eJ#\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00172\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001f0\u0017H\u0002¢\u0006\u0004\b!\u0010\u001bJ#\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00172\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u001f0\u0017H\u0002¢\u0006\u0004\b#\u0010\u001bJ\u001a\u0010%\u001a\u0004\u0018\u00010\u001f2\u0006\u0010$\u001a\u00020\u0018H\u0096\u0002¢\u0006\u0004\b%\u0010&J\u0017\u0010+\u001a\u0004\u0018\u00010\u00182\u0006\u0010(\u001a\u00020'¢\u0006\u0004\b)\u0010*J\u000f\u0010-\u001a\u00020,H\u0016¢\u0006\u0004\b-\u0010.J\u000f\u00100\u001a\u00020/H\u0016¢\u0006\u0004\b0\u00101R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u00102\u001a\u0004\b3\u00104R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u00105\u001a\u0004\b6\u00107R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u00108\u001a\u0004\b9\u0010:R\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\n8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\f\u0010;R \u0010=\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u001f0<8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b=\u0010>R \u0010@\u001a\b\u0012\u0004\u0012\u00020?0\u00178\u0000X\u0080\u0004¢\u0006\f\n\u0004\b@\u0010A\u001a\u0004\bB\u0010CR&\u0010E\u001a\u000e\u0012\u0004\u0012\u00020D\u0012\u0004\u0012\u00020?0<8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bE\u0010>\u001a\u0004\bF\u0010GR&\u0010I\u001a\u000e\u0012\u0004\u0012\u00020'\u0012\u0004\u0012\u00020H0<8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bI\u0010>\u001a\u0004\bJ\u0010GR \u0010L\u001a\b\u0012\u0004\u0012\u00020K0\u00178\u0016X\u0096\u0004¢\u0006\f\n\u0004\bL\u0010A\u001a\u0004\bM\u0010CR \u0010N\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00178\u0016X\u0096\u0004¢\u0006\f\n\u0004\bN\u0010A\u001a\u0004\bO\u0010CR \u0010U\u001a\b\u0012\u0004\u0012\u00020'0P8\u0016X\u0096\u0004¢\u0006\f\n\u0004\bQ\u0010R\u001a\u0004\bS\u0010TR \u0010\u0019\u001a\b\u0012\u0004\u0012\u00020D0\u00178\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0019\u0010A\u001a\u0004\bV\u0010C¨\u0006["}, d2 = {"Landroidx/camera/camera2/pipe/graph/StreamGraphImpl;", "Landroidx/camera/camera2/pipe/StreamGraph;", "Ljava/lang/AutoCloseable;", "Lkotlin/AutoCloseable;", "Landroidx/camera/camera2/pipe/CameraMetadata;", "cameraMetadata", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "graphConfig", "Landroidx/camera/camera2/pipe/media/ImageSources;", "imageSources", "Ljavax/inject/Provider;", "Landroidx/camera/camera2/pipe/CameraController;", "cameraControllerProvider", "<init>", "(Landroidx/camera/camera2/pipe/CameraMetadata;Landroidx/camera/camera2/pipe/CameraGraph$Config;Landroidx/camera/camera2/pipe/media/ImageSources;Ljavax/inject/Provider;)V", "Landroidx/camera/camera2/pipe/OutputStream$Config;", "outputConfig", "Landroid/hardware/camera2/params/OutputConfiguration;", "getOutputConfigurationOrNull", "(Landroidx/camera/camera2/pipe/OutputStream$Config;)Landroid/hardware/camera2/params/OutputConfiguration;", _UrlKt.FRAGMENT_ENCODE_SET, "computeNextSurfaceGroupId", "(Landroidx/camera/camera2/pipe/CameraGraph$Config;)I", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraStream$Config;", "outputs", "readExistingGroupNumbers", "(Ljava/util/List;)Ljava/util/List;", _UrlKt.FRAGMENT_ENCODE_SET, "computeIfDeferredStreamsAreSupported", "(Landroidx/camera/camera2/pipe/CameraMetadata;Landroidx/camera/camera2/pipe/CameraGraph$Config;)Z", "Landroidx/camera/camera2/pipe/CameraStream;", "unsortedStreams", "sortOutputsByPreviewStream", "unsortedOutputs", "sortOutputsByVideoStream", "config", "get", "(Landroidx/camera/camera2/pipe/CameraStream$Config;)Landroidx/camera/camera2/pipe/CameraStream;", "Landroidx/camera/camera2/pipe/StreamId;", "streamId", "getCameraStreamConfig-aKI5c8E", "(I)Landroidx/camera/camera2/pipe/CameraStream$Config;", "getCameraStreamConfig", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", _UrlKt.FRAGMENT_ENCODE_SET, "close", "()V", "Landroidx/camera/camera2/pipe/CameraMetadata;", "getCameraMetadata", "()Landroidx/camera/camera2/pipe/CameraMetadata;", "Landroidx/camera/camera2/pipe/CameraGraph$Config;", "getGraphConfig", "()Landroidx/camera/camera2/pipe/CameraGraph$Config;", "Landroidx/camera/camera2/pipe/media/ImageSources;", "getImageSources", "()Landroidx/camera/camera2/pipe/media/ImageSources;", "Ljavax/inject/Provider;", _UrlKt.FRAGMENT_ENCODE_SET, "_streamMap", "Ljava/util/Map;", "Landroidx/camera/camera2/pipe/graph/StreamGraphImpl$OutputConfig;", "outputConfigs", "Ljava/util/List;", "getOutputConfigs$camera_camera2_pipe", "()Ljava/util/List;", "Landroidx/camera/camera2/pipe/OutputStream;", "outputConfigMap", "getOutputConfigMap$camera_camera2_pipe", "()Ljava/util/Map;", "Landroidx/camera/camera2/pipe/media/ImageSource;", "imageSourceMap", "getImageSourceMap$camera_camera2_pipe", "Landroidx/camera/camera2/pipe/InputStream;", "inputs", "getInputs", "streams", "getStreams", _UrlKt.FRAGMENT_ENCODE_SET, "streamIds$1", "Ljava/util/Set;", "getStreamIds", "()Ljava/util/Set;", "streamIds", "getOutputs", "Companion", "OutputConfig", "OutputStreamImpl", "InputStreamImpl", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nStreamGraphImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StreamGraphImpl.kt\nandroidx/camera/camera2/pipe/graph/StreamGraphImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,475:1\n1563#2:476\n1634#2,3:477\n1563#2:480\n1634#2,3:481\n1563#2:484\n1634#2,3:485\n1056#2:488\n1374#2:489\n1460#2,5:490\n295#2,2:496\n1374#2:498\n1460#2,5:499\n808#2,11:504\n1803#2,3:515\n3307#2,4:518\n1761#2,3:522\n3311#2,6:525\n3307#2,4:531\n1761#2,3:535\n3311#2,6:538\n3307#2,4:544\n1761#2,3:548\n3311#2,6:551\n3307#2,4:557\n1761#2,3:561\n3311#2,6:564\n3307#2,4:570\n1761#2,3:574\n3311#2,6:577\n1#3:495\n*S KotlinDebug\n*F\n+ 1 StreamGraphImpl.kt\nandroidx/camera/camera2/pipe/graph/StreamGraphImpl\n*L\n168#1:476\n168#1:477,3\n199#1:480\n199#1:481,3\n206#1:484\n206#1:485,3\n209#1:488\n213#1:489\n213#1:490,5\n105#1:496,2\n306#1:498\n306#1:499,5\n307#1:504,11\n308#1:515,3\n352#1:518,4\n353#1:522,3\n352#1:525,6\n364#1:531,4\n365#1:535,3\n364#1:538,6\n374#1:544,4\n375#1:548,3\n374#1:551,6\n399#1:557,4\n400#1:561,3\n399#1:564,6\n411#1:570,4\n412#1:574,3\n411#1:577,6\n*E\n"})
/* loaded from: classes3.dex */
public final class StreamGraphImpl implements StreamGraph, AutoCloseable {
    public static final Companion Companion = null;
    private static final AtomicInt configIds = null;
    private static final AtomicInt groupIds = null;
    private static final AtomicInt inputIds = null;
    private static final AtomicInt outputIds = null;
    private static final Comparator<CameraStream> previewFormatComparator = null;
    private static final List<StreamFormat> previewFormats = null;
    private static final List<OutputStream.OutputType> previewOutputTypes = null;
    private static final Comparator<CameraStream> previewOutputTypesComparator = null;
    private static final AtomicInt streamIds = null;
    private final Map<CameraStream.Config, CameraStream> _streamMap;
    private final Provider<CameraController> cameraControllerProvider;
    private final CameraMetadata cameraMetadata;
    private final CameraGraph.Config graphConfig;
    private final Map<StreamId, ImageSource> imageSourceMap;
    private final ImageSources imageSources;
    private final List<InputStream> inputs;
    private final Map<OutputStream, OutputConfig> outputConfigMap;
    private final List<OutputConfig> outputConfigs;
    private final List<OutputStream> outputs;
    private final Set<StreamId> streamIds$1;
    private final List<CameraStream> streams;

    @Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000f\u0010\u0006\u001a\u00020\u0007H\u0000¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000b\u001a\u00020\fH\u0000¢\u0006\u0004\b\r\u0010\tJ\u000f\u0010\u000f\u001a\u00020\u0010H\u0000¢\u0006\u0004\b\u0011\u0010\tJ\u000f\u0010\u0013\u001a\u00020\u0014H\u0000¢\u0006\u0004\b\u0015\u0010\tJ\r\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u0019R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u001d\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010!\u001a\b\u0012\u0004\u0012\u00020\"0\u001bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010#\u001a\u0012\u0012\u0004\u0012\u00020\u001f0\u001ej\b\u0012\u0004\u0012\u00020\u001f` X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006$"}, d2 = {"Landroidx/camera/camera2/pipe/graph/StreamGraphImpl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "streamIds", "Lkotlinx/atomicfu/AtomicInt;", "nextStreamId", "Landroidx/camera/camera2/pipe/StreamId;", "nextStreamId-ptHMqGs$camera_camera2_pipe", "()I", "outputIds", "nextOutputId", "Landroidx/camera/camera2/pipe/OutputId;", "nextOutputId-4LaLFng$camera_camera2_pipe", "inputIds", "nextInputId", "Landroidx/camera/camera2/pipe/InputStreamId;", "nextInputId-m1bwn9M$camera_camera2_pipe", "configIds", "nextConfigId", "Landroidx/camera/camera2/pipe/graph/OutputConfigId;", "nextConfigId-hoCEiqs$camera_camera2_pipe", "groupIds", "nextGroupId", _UrlKt.FRAGMENT_ENCODE_SET, "nextGroupId$camera_camera2_pipe", "previewOutputTypes", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/OutputStream$OutputType;", "previewOutputTypesComparator", "Ljava/util/Comparator;", "Landroidx/camera/camera2/pipe/CameraStream;", "Lkotlin/Comparator;", "previewFormats", "Landroidx/camera/camera2/pipe/StreamFormat;", "previewFormatComparator", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* renamed from: nextConfigId-hoCEiqs$camera_camera2_pipe, reason: not valid java name */
        public final int m540nextConfigIdhoCEiqs$camera_camera2_pipe() {
            return OutputConfigId.m530constructorimpl(StreamGraphImpl.access$getConfigIds$cp().incrementAndGet());
        }

        public final int nextGroupId$camera_camera2_pipe() {
            return StreamGraphImpl.access$getGroupIds$cp().incrementAndGet();
        }

        /* renamed from: nextInputId-m1bwn9M$camera_camera2_pipe, reason: not valid java name */
        public final int m541nextInputIdm1bwn9M$camera_camera2_pipe() {
            return InputStreamId.m278constructorimpl(StreamGraphImpl.access$getInputIds$cp().incrementAndGet());
        }

        /* renamed from: nextOutputId-4LaLFng$camera_camera2_pipe, reason: not valid java name */
        public final int m542nextOutputId4LaLFng$camera_camera2_pipe() {
            return OutputId.m291constructorimpl(StreamGraphImpl.access$getOutputIds$cp().incrementAndGet());
        }

        /* renamed from: nextStreamId-ptHMqGs$camera_camera2_pipe, reason: not valid java name */
        public final int m543nextStreamIdptHMqGs$camera_camera2_pipe() {
            return StreamId.m402constructorimpl(StreamGraphImpl.access$getStreamIds$cp().incrementAndGet());
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0002\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tR\u0016\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0016\u0010\u0006\u001a\u00020\u0007X\u0096\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\u000e\u0010\u000b¨\u0006\u000f"}, d2 = {"Landroidx/camera/camera2/pipe/graph/StreamGraphImpl$InputStreamImpl;", "Landroidx/camera/camera2/pipe/InputStream;", "id", "Landroidx/camera/camera2/pipe/InputStreamId;", "maxImages", _UrlKt.FRAGMENT_ENCODE_SET, "format", "Landroidx/camera/camera2/pipe/StreamFormat;", "<init>", "(IIILkotlin/jvm/internal/DefaultConstructorMarker;)V", "getId-m1bwn9M", "()I", "I", "getMaxImages", "getFormat-8FPWQzE", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class InputStreamImpl implements InputStream {
        private final int format;
        private final int id;
        private final int maxImages;

        private InputStreamImpl(int r1, int r2, int r3) {
            this.id = r1;
            this.maxImages = r2;
            this.format = r3;
        }

        public /* synthetic */ InputStreamImpl(int r1, int r2, int r3, DefaultConstructorMarker r4) {
            this(r1, r2, r3);
        }

        @Override // androidx.camera.camera2.pipe.InputStream
        /* renamed from: getFormat-8FPWQzE */
        public int mo275getFormat8FPWQzE() {
            return this.format;
        }

        @Override // androidx.camera.camera2.pipe.InputStream
        /* renamed from: getId-m1bwn9M */
        public int mo276getIdm1bwn9M() {
            return this.id;
        }

        @Override // androidx.camera.camera2.pipe.InputStream
        public int getMaxImages() {
            return this.maxImages;
        }
    }

    @Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b#\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0085\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\r\u001a\u0004\u0018\u00010\f\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\b\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014\u0012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0016\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018\u0012\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u001a¢\u0006\u0004\b\u001c\u0010\u001dJ\u000f\u0010\u001f\u001a\u00020\u001eH\u0016¢\u0006\u0004\b\u001f\u0010 R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010!\u001a\u0004\b\"\u0010#R\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010$\u001a\u0004\b%\u0010&R\u0017\u0010\u0007\u001a\u00020\u00068\u0006¢\u0006\f\n\u0004\b\u0007\u0010!\u001a\u0004\b'\u0010#R\u0017\u0010\t\u001a\u00020\b8\u0006¢\u0006\f\n\u0004\b\t\u0010(\u001a\u0004\b)\u0010 R\u0019\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010*\u001a\u0004\b+\u0010,R\u0019\u0010\r\u001a\u0004\u0018\u00010\f8\u0006¢\u0006\f\n\u0004\b\r\u0010-\u001a\u0004\b.\u0010/R\u0019\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u00100\u001a\u0004\b1\u00102R\u0019\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0006¢\u0006\f\n\u0004\b\u0011\u00103\u001a\u0004\b4\u00105R\u0019\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u00106\u001a\u0004\b7\u00108R\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u00168\u0006¢\u0006\f\n\u0004\b\u0017\u00109\u001a\u0004\b:\u0010;R\u0019\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0006¢\u0006\f\n\u0004\b\u0019\u0010<\u001a\u0004\b=\u0010>R\u001d\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00010\u001a8\u0006¢\u0006\f\n\u0004\b\u001b\u0010?\u001a\u0004\b@\u0010AR \u0010D\u001a\b\u0012\u0004\u0012\u00020C0B8\u0000X\u0080\u0004¢\u0006\f\n\u0004\bD\u0010?\u001a\u0004\bE\u0010AR\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0006¢\u0006\f\n\u0004\b\u0013\u0010F\u001a\u0004\bG\u0010HR\u0017\u0010J\u001a\b\u0012\u0004\u0012\u00020C0\u001a8F¢\u0006\u0006\u001a\u0004\bI\u0010AR\u0011\u0010N\u001a\u00020K8F¢\u0006\u0006\u001a\u0004\bL\u0010MR\u0011\u0010P\u001a\u00020K8F¢\u0006\u0006\u001a\u0004\bO\u0010M¨\u0006Q"}, d2 = {"Landroidx/camera/camera2/pipe/graph/StreamGraphImpl$OutputConfig;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/graph/OutputConfigId;", "id", "Landroid/util/Size;", "size", "Landroidx/camera/camera2/pipe/StreamFormat;", "format", "Landroidx/camera/camera2/pipe/CameraId;", "camera", _UrlKt.FRAGMENT_ENCODE_SET, "groupNumber", "Landroid/hardware/camera2/params/OutputConfiguration;", "externalOutputConfig", "Landroidx/camera/camera2/pipe/OutputStream$OutputType;", "deferredOutputType", "Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;", "mirrorMode", "Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;", "timestampBase", "Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;", "dynamicRangeProfile", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;", "streamUseCase", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;", "streamUseHint", _UrlKt.FRAGMENT_ENCODE_SET, "sensorPixelModes", "<init>", "(ILandroid/util/Size;ILjava/lang/String;Ljava/lang/Integer;Landroid/hardware/camera2/params/OutputConfiguration;Landroidx/camera/camera2/pipe/OutputStream$OutputType;Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;Ljava/util/List;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "I", "getId-hoCEiqs", "()I", "Landroid/util/Size;", "getSize", "()Landroid/util/Size;", "getFormat-8FPWQzE", "Ljava/lang/String;", "getCamera-Dz_R5H8", "Ljava/lang/Integer;", "getGroupNumber", "()Ljava/lang/Integer;", "Landroid/hardware/camera2/params/OutputConfiguration;", "getExternalOutputConfig", "()Landroid/hardware/camera2/params/OutputConfiguration;", "Landroidx/camera/camera2/pipe/OutputStream$OutputType;", "getDeferredOutputType", "()Landroidx/camera/camera2/pipe/OutputStream$OutputType;", "Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;", "getMirrorMode-dO1_9xk", "()Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;", "Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;", "getDynamicRangeProfile-OoVcG5w", "()Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;", "getStreamUseCase-8x2ez34", "()Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;", "getStreamUseHint-HIPxoCc", "()Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;", "Ljava/util/List;", "getSensorPixelModes", "()Ljava/util/List;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/CameraStream;", "streamBuilder", "getStreamBuilder$camera_camera2_pipe", "Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;", "getTimestampBase-pcPfPbY", "()Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;", "getStreams", "streams", _UrlKt.FRAGMENT_ENCODE_SET, "getDeferrable", "()Z", "deferrable", "getSurfaceSharing", "surfaceSharing", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class OutputConfig {
        private final String camera;
        private final OutputStream.OutputType deferredOutputType;
        private final OutputStream.DynamicRangeProfile dynamicRangeProfile;
        private final OutputConfiguration externalOutputConfig;
        private final int format;
        private final Integer groupNumber;
        private final int id;
        private final OutputStream.MirrorMode mirrorMode;
        private final List<Object> sensorPixelModes;
        private final Size size;
        private final List<CameraStream> streamBuilder;
        private final OutputStream.StreamUseCase streamUseCase;
        private final OutputStream.StreamUseHint streamUseHint;

        private OutputConfig(int r1, Size r2, int r3, String r4, Integer r5, OutputConfiguration r6, OutputStream.OutputType r7, OutputStream.MirrorMode r8, OutputStream.TimestampBase r9, OutputStream.DynamicRangeProfile r10, OutputStream.StreamUseCase r11, OutputStream.StreamUseHint r12, List<Object> r13) {
            this.id = r1;
            this.size = r2;
            this.format = r3;
            this.camera = r4;
            this.groupNumber = r5;
            this.externalOutputConfig = r6;
            this.deferredOutputType = r7;
            this.mirrorMode = r8;
            this.dynamicRangeProfile = r10;
            this.streamUseCase = r11;
            this.streamUseHint = r12;
            this.sensorPixelModes = r13;
            this.streamBuilder = new ArrayList();
        }

        public /* synthetic */ OutputConfig(int r1, Size r2, int r3, String r4, Integer r5, OutputConfiguration r6, OutputStream.OutputType r7, OutputStream.MirrorMode r8, OutputStream.TimestampBase r9, OutputStream.DynamicRangeProfile r10, OutputStream.StreamUseCase r11, OutputStream.StreamUseHint r12, List r13, DefaultConstructorMarker r14) {
            this(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13);
        }

        /* renamed from: getCamera-Dz_R5H8, reason: not valid java name */
        public final String m544getCameraDz_R5H8() {
            return this.camera;
        }

        public final boolean getDeferrable() {
            if (this.deferredOutputType == null) goto L6;
            return true;
        L6:
            return false;
        }

        public final OutputStream.OutputType getDeferredOutputType() {
            return this.deferredOutputType;
        }

        /* renamed from: getDynamicRangeProfile-OoVcG5w, reason: not valid java name */
        public final OutputStream.DynamicRangeProfile m545getDynamicRangeProfileOoVcG5w() {
            return this.dynamicRangeProfile;
        }

        public final OutputConfiguration getExternalOutputConfig() {
            return this.externalOutputConfig;
        }

        /* renamed from: getFormat-8FPWQzE, reason: not valid java name */
        public final int m546getFormat8FPWQzE() {
            return this.format;
        }

        public final Integer getGroupNumber() {
            return this.groupNumber;
        }

        /* renamed from: getMirrorMode-dO1_9xk, reason: not valid java name */
        public final OutputStream.MirrorMode m547getMirrorModedO1_9xk() {
            return this.mirrorMode;
        }

        public final List<Object> getSensorPixelModes() {
            return this.sensorPixelModes;
        }

        public final Size getSize() {
            return this.size;
        }

        public final List<CameraStream> getStreamBuilder$camera_camera2_pipe() {
            return this.streamBuilder;
        }

        /* renamed from: getStreamUseCase-8x2ez34, reason: not valid java name */
        public final OutputStream.StreamUseCase m548getStreamUseCase8x2ez34() {
            return this.streamUseCase;
        }

        /* renamed from: getStreamUseHint-HIPxoCc, reason: not valid java name */
        public final OutputStream.StreamUseHint m549getStreamUseHintHIPxoCc() {
            return this.streamUseHint;
        }

        public final List<CameraStream> getStreams() {
            return this.streamBuilder;
        }

        public final boolean getSurfaceSharing() {
            if (this.streamBuilder.size() <= 1) goto L5;
            return true;
        L5:
            return false;
        }

        /* renamed from: getTimestampBase-pcPfPbY, reason: not valid java name */
        public final OutputStream.TimestampBase m550getTimestampBasepcPfPbY() {
            return null;
        }

        public String toString() {
            return OutputConfigId.m531toStringimpl(this.id);
        }
    }

    @Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u001a\n\u0002\u0018\u0002\n\u0002\b\n\b\u0000\u0018\u00002\u00020\u0001Bo\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\t\u001a\u00020\b\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0013\u001a\u0004\u0018\u00010\u0012\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u001a\u0010\u0003\u001a\u00020\u00028\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0003\u0010\u001b\u001a\u0004\b\u001c\u0010\u001dR\u001a\u0010\u0005\u001a\u00020\u00048\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0005\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u001a\u0010\u0007\u001a\u00020\u00068\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0007\u0010\u001b\u001a\u0004\b!\u0010\u001dR\u001a\u0010\t\u001a\u00020\b8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\t\u0010\"\u001a\u0004\b#\u0010\u001aR\u001c\u0010\u000b\u001a\u0004\u0018\u00010\n8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000b\u0010$\u001a\u0004\b%\u0010&R\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u000e8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u000f\u0010'\u001a\u0004\b(\u0010)R\u001c\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0011\u0010*\u001a\u0004\b+\u0010,R\u001c\u0010\u0013\u001a\u0004\u0018\u00010\u00128\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0013\u0010-\u001a\u0004\b.\u0010/R\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\u0015\u00100\u001a\u0004\b1\u00102R\"\u00104\u001a\u0002038\u0016@\u0016X\u0096.¢\u0006\u0012\n\u0004\b4\u00105\u001a\u0004\b6\u00107\"\u0004\b8\u00109R\u001c\u0010\r\u001a\u0004\u0018\u00010\f8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b\r\u0010:\u001a\u0004\b;\u0010<¨\u0006="}, d2 = {"Landroidx/camera/camera2/pipe/graph/StreamGraphImpl$OutputStreamImpl;", "Landroidx/camera/camera2/pipe/OutputStream;", "Landroidx/camera/camera2/pipe/OutputId;", "id", "Landroid/util/Size;", "size", "Landroidx/camera/camera2/pipe/StreamFormat;", "format", "Landroidx/camera/camera2/pipe/CameraId;", "camera", "Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;", "mirrorMode", "Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;", "timestampBase", "Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;", "dynamicRangeProfile", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;", "streamUseCase", "Landroidx/camera/camera2/pipe/OutputStream$OutputType;", "outputType", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;", "streamUseHint", "<init>", "(ILandroid/util/Size;ILjava/lang/String;Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;Landroidx/camera/camera2/pipe/OutputStream$OutputType;Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "()Ljava/lang/String;", "I", "getId-4LaLFng", "()I", "Landroid/util/Size;", "getSize", "()Landroid/util/Size;", "getFormat-8FPWQzE", "Ljava/lang/String;", "getCamera-Dz_R5H8", "Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;", "getMirrorMode-dO1_9xk", "()Landroidx/camera/camera2/pipe/OutputStream$MirrorMode;", "Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;", "getDynamicRangeProfile-OoVcG5w", "()Landroidx/camera/camera2/pipe/OutputStream$DynamicRangeProfile;", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;", "getStreamUseCase-8x2ez34", "()Landroidx/camera/camera2/pipe/OutputStream$StreamUseCase;", "Landroidx/camera/camera2/pipe/OutputStream$OutputType;", "getOutputType", "()Landroidx/camera/camera2/pipe/OutputStream$OutputType;", "Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;", "getStreamUseHint-HIPxoCc", "()Landroidx/camera/camera2/pipe/OutputStream$StreamUseHint;", "Landroidx/camera/camera2/pipe/CameraStream;", "stream", "Landroidx/camera/camera2/pipe/CameraStream;", "getStream", "()Landroidx/camera/camera2/pipe/CameraStream;", "setStream", "(Landroidx/camera/camera2/pipe/CameraStream;)V", "Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;", "getTimestampBase-pcPfPbY", "()Landroidx/camera/camera2/pipe/OutputStream$TimestampBase;", "camera-camera2-pipe"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class OutputStreamImpl implements OutputStream {
        private final String camera;
        private final OutputStream.DynamicRangeProfile dynamicRangeProfile;
        private final int format;
        private final int id;
        private final OutputStream.MirrorMode mirrorMode;
        private final OutputStream.OutputType outputType;
        private final Size size;
        public CameraStream stream;
        private final OutputStream.StreamUseCase streamUseCase;
        private final OutputStream.StreamUseHint streamUseHint;

        private OutputStreamImpl(int r1, Size r2, int r3, String r4, OutputStream.MirrorMode r5, OutputStream.TimestampBase r6, OutputStream.DynamicRangeProfile r7, OutputStream.StreamUseCase r8, OutputStream.OutputType r9, OutputStream.StreamUseHint r10) {
            this.id = r1;
            this.size = r2;
            this.format = r3;
            this.camera = r4;
            this.mirrorMode = r5;
            this.dynamicRangeProfile = r7;
            this.streamUseCase = r8;
            this.outputType = r9;
            this.streamUseHint = r10;
        }

        public /* synthetic */ OutputStreamImpl(int r1, Size r2, int r3, String r4, OutputStream.MirrorMode r5, OutputStream.TimestampBase r6, OutputStream.DynamicRangeProfile r7, OutputStream.StreamUseCase r8, OutputStream.OutputType r9, OutputStream.StreamUseHint r10, DefaultConstructorMarker r11) {
            this(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10);
        }

        @Override // androidx.camera.camera2.pipe.OutputStream
        /* renamed from: getCamera-Dz_R5H8 */
        public String mo308getCameraDz_R5H8() {
            return this.camera;
        }

        @Override // androidx.camera.camera2.pipe.OutputStream
        /* renamed from: getDynamicRangeProfile-OoVcG5w */
        public OutputStream.DynamicRangeProfile mo309getDynamicRangeProfileOoVcG5w() {
            return this.dynamicRangeProfile;
        }

        @Override // androidx.camera.camera2.pipe.OutputStream
        /* renamed from: getFormat-8FPWQzE */
        public int mo310getFormat8FPWQzE() {
            return this.format;
        }

        @Override // androidx.camera.camera2.pipe.OutputStream
        /* renamed from: getId-4LaLFng */
        public int mo311getId4LaLFng() {
            return this.id;
        }

        @Override // androidx.camera.camera2.pipe.OutputStream
        /* renamed from: getMirrorMode-dO1_9xk */
        public OutputStream.MirrorMode mo312getMirrorModedO1_9xk() {
            return this.mirrorMode;
        }

        @Override // androidx.camera.camera2.pipe.OutputStream
        public OutputStream.OutputType getOutputType() {
            return this.outputType;
        }

        @Override // androidx.camera.camera2.pipe.OutputStream
        public Size getSize() {
            return this.size;
        }

        @Override // androidx.camera.camera2.pipe.OutputStream
        public CameraStream getStream() {
            CameraStream r0 = this.stream;
            if (r0 == null) goto L5;
            return r0;
        L5:
            return null;
        }

        @Override // androidx.camera.camera2.pipe.OutputStream
        /* renamed from: getStreamUseCase-8x2ez34 */
        public OutputStream.StreamUseCase mo313getStreamUseCase8x2ez34() {
            return this.streamUseCase;
        }

        @Override // androidx.camera.camera2.pipe.OutputStream
        /* renamed from: getStreamUseHint-HIPxoCc */
        public OutputStream.StreamUseHint mo314getStreamUseHintHIPxoCc() {
            return this.streamUseHint;
        }

        @Override // androidx.camera.camera2.pipe.OutputStream
        /* renamed from: getTimestampBase-pcPfPbY */
        public OutputStream.TimestampBase mo315getTimestampBasepcPfPbY() {
            return null;
        }

        public void setStream(CameraStream r1) {
            this.stream = r1;
        }

        public String toString() {
            return OutputId.m295toStringimpl(mo311getId4LaLFng());
        }
    }

    static {
        Companion = new Companion(null);
        streamIds = AtomicFU.atomic(0);
        outputIds = AtomicFU.atomic(0);
        inputIds = AtomicFU.atomic(0);
        configIds = AtomicFU.atomic(0);
        groupIds = AtomicFU.atomic(0);
        OutputStream.OutputType.Companion r0 = OutputStream.OutputType.Companion;
        previewOutputTypes = CollectionsKt.listOf(new OutputStream.OutputType[]{r0.getSURFACE_VIEW(), r0.getSURFACE_TEXTURE()});
        previewOutputTypesComparator = new StreamGraphImpl$special$$inlined$compareBy$1();
        StreamFormat.Companion r02 = StreamFormat.Companion;
        previewFormats = CollectionsKt.listOf(new StreamFormat[]{StreamFormat.m389boximpl(r02.m398getUNKNOWN8FPWQzE()), StreamFormat.m389boximpl(r02.m397getPRIVATE8FPWQzE())});
        previewFormatComparator = new StreamGraphImpl$special$$inlined$compareBy$2();
    }

    public StreamGraphImpl(CameraMetadata r29, CameraGraph.Config r30, ImageSources r31, Provider<CameraController> r32) {
        this.cameraMetadata = r29;
        this.graphConfig = r30;
        this.imageSources = r31;
        this.cameraControllerProvider = r32;
        ArrayList r3 = new ArrayList();
        LinkedHashMap r4 = new LinkedHashMap();
        ArrayList r5 = new ArrayList();
        LinkedHashMap r6 = new LinkedHashMap();
        boolean r1 = computeIfDeferredStreamsAreSupported(r29, r30);
        LinkedHashMap r7 = new LinkedHashMap();
        Iterator<List<CameraStream.Config>> r2 = r30.getExclusiveStreamGroups().iterator();
    L4:
        if (r2.hasNext() == false) goto L17;
        List<CameraStream.Config> r8 = r2.next();
        if (r8.isEmpty() == true) goto L15;
        int r10 = computeNextSurfaceGroupId(this.graphConfig);
        Iterator<CameraStream.Config> r82 = r8.iterator();
    L9:
        if (r82.hasNext() == false) goto L4;
        CameraStream.Config r12 = r82.next();
        if (r7.containsKey(r12) == true) goto L13;
        r7.put(r12, Integer.valueOf(r10));
        goto L9
    L13:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        throw null;
    L15:
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        throw null;
    L17:
        Iterator<CameraStream.Config> r22 = this.graphConfig.getStreams().iterator();
    L19:
        if (r22.hasNext() == false) goto L39;
        CameraStream.Config r83 = r22.next();
        Iterator<OutputStream.Config> r102 = r83.getOutputs().iterator();
    L22:
        if (r102.hasNext() == false) goto L19;
        OutputStream.Config r11 = r102.next();
        if (r4.containsKey(r11) == true) goto L22;
        int r14 = Companion.m540nextConfigIdhoCEiqs$camera_camera2_pipe();
        Size r15 = r11.getSize();
        int r16 = r11.m318getFormat8FPWQzE();
        String r122 = r11.m316getCamera1LO98Z0();
        if (r122 != null) goto L29;
        r122 = this.graphConfig.m200getCameraDz_R5H8();
    L29:
        String r17 = r122;
        Integer r18 = (Integer) r7.get(r83);
        if (r1 == true) goto L32;
    L37:
        OutputStream.OutputType r20 = null;
    L38:
        OutputStream.MirrorMode r21 = r11.m319getMirrorModedO1_9xk();
        r11.m322getTimestampBasepcPfPbY();
        OutputConfig r13 = new OutputConfig(r14, r15, r16, r17, r18, getOutputConfigurationOrNull(r11), r20, r21, null, r11.m317getDynamicRangeProfileOoVcG5w(), r11.m320getStreamUseCase8x2ez34(), r11.m321getStreamUseHintHIPxoCc(), r11.getSensorPixelModes(), null);
        r4.put(r11, r13);
        r3.add(r13);
        goto L22
    L32:
        if ((r11 instanceof OutputStream.Config.LazyOutputConfig) == false) goto L34;
        OutputStream.Config.LazyOutputConfig r123 = (OutputStream.Config.LazyOutputConfig) r11;
    L35:
        if (r123 == null) goto L37;
        r20 = r123.getOutputType$camera_camera2_pipe();
        goto L38
    L34:
        r123 = null;
        goto L35
    L39:
        LinkedHashMap r19 = new LinkedHashMap();
        int r23 = this.graphConfig.getStreams().size();
        int r84 = 0;
    L40:
        CameraGraph.Config r103 = this.graphConfig;
        if (r84 >= r23) goto L54;
        CameraStream.Config r104 = r103.getStreams().get(r84);
        List<OutputStream.Config> r124 = r104.getOutputs();
        ArrayList r132 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r124, 10));
        Iterator<T> r112 = r124.iterator();
    L44:
        if (r112.hasNext() == false) goto L46;
        OutputConfig r125 = (OutputConfig) r4.get((OutputStream.Config) r112.next());
        int r152 = Companion.m542nextOutputId4LaLFng$camera_camera2_pipe();
        Size r162 = r125.getSize();
        int r172 = r125.m546getFormat8FPWQzE();
        String r182 = r125.m544getCameraDz_R5H8();
        OutputStream.MirrorMode r192 = r125.m547getMirrorModedO1_9xk();
        r125.m550getTimestampBasepcPfPbY();
        OutputStreamImpl r142 = new OutputStreamImpl(r152, r162, r172, r182, r192, null, r125.m545getDynamicRangeProfileOoVcG5w(), r125.m548getStreamUseCase8x2ez34(), r125.getDeferredOutputType(), r125.m549getStreamUseHintHIPxoCc(), null);
        r19.put(r142, r125);
        r132.add(r142);
        goto L44
    L46:
        CameraStream r113 = new CameraStream(Companion.m543nextStreamIdptHMqGs$camera_camera2_pipe(), r132, null);
        r6.put(r104, r113);
        r5.add(r113);
        int r126 = r132.size();
        int r143 = 0;
    L47:
        if (r143 >= r126) goto L49;
        Object r153 = r132.get(r143);
        r143 = r143 + 1;
        ((OutputStreamImpl) r153).setStream(r113);
        goto L47
    L49:
        Iterator<OutputStream.Config> r105 = r104.getOutputs().iterator();
    L51:
        if (r105.hasNext() == false) goto L53;
        ((OutputConfig) r4.get(r105.next())).getStreamBuilder$camera_camera2_pipe().add(r113);
        goto L51
    L53:
        r84 = r84 + 1;
        goto L40
    L54:
        List<InputStream.Config> r24 = r103.getInput();
        if (r24 == null) goto L60;
        List<InputStream.Config> r25 = r24;
        List<InputStream> r42 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r25, 10));
        Iterator<T> r26 = r25.iterator();
    L58:
        if (r26.hasNext() == false) goto L61;
        InputStream.Config r72 = (InputStream.Config) r26.next();
        r42.add(new InputStreamImpl(Companion.m541nextInputIdm1bwn9M$camera_camera2_pipe(), r72.getMaxImages(), r72.m277getStreamFormat8FPWQzE(), null));
    L61:
        this.inputs = r42;
        this.streams = sortOutputsByVideoStream(sortOutputsByPreviewStream(r5));
        List<CameraStream> r27 = getStreams();
        ArrayList r43 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r27, 10));
        Iterator<T> r28 = r27.iterator();
    L63:
        if (r28.hasNext() == false) goto L65;
        r43.add(StreamId.m401boximpl(((CameraStream) r28.next()).m241getIdptHMqGs()));
        goto L63
    L65:
        this.streamIds$1 = CollectionsKt.toSet(r43);
        this._streamMap = r6;
        this.outputConfigs = CollectionsKt.sortedWith(r3, new StreamGraphImpl$special$$inlined$sortedBy$1(this));
        this.outputConfigMap = r19;
        List<CameraStream> r110 = getStreams();
        ArrayList r210 = new ArrayList();
        Iterator<T> r111 = r110.iterator();
    L67:
        if (r111.hasNext() == false) goto L69;
        CollectionsKt.addAll(r210, ((CameraStream) r111.next()).getOutputs());
        goto L67
    L69:
        this.outputs = r210;
        Map r114 = MapsKt.createMapBuilder();
        Iterator<CameraStream.Config> r211 = this.graphConfig.getStreams().iterator();
    L71:
        if (r211.hasNext() == false) goto L73;
        r211.next().getImageSourceConfig();
        goto L71
    L73:
        this.imageSourceMap = MapsKt.build(r114);
        return;
    L60:
        r42 = CollectionsKt.emptyList();
        goto L61
    }

    public static final /* synthetic */ AtomicInt access$getConfigIds$cp() {
        return configIds;
    }

    public static final /* synthetic */ AtomicInt access$getGroupIds$cp() {
        return groupIds;
    }

    public static final /* synthetic */ AtomicInt access$getInputIds$cp() {
        return inputIds;
    }

    public static final /* synthetic */ AtomicInt access$getOutputIds$cp() {
        return outputIds;
    }

    public static final /* synthetic */ List access$getPreviewFormats$cp() {
        return previewFormats;
    }

    public static final /* synthetic */ List access$getPreviewOutputTypes$cp() {
        return previewOutputTypes;
    }

    public static final /* synthetic */ AtomicInt access$getStreamIds$cp() {
        return streamIds;
    }

    private final boolean computeIfDeferredStreamsAreSupported(CameraMetadata r2, CameraGraph.Config r3) {
        int r1 = Build.VERSION.SDK_INT;
        if (r1 >= 26) goto L5;
        return false;
    L5:
        if (CameraGraph.OperatingMode.m216equalsimpl0(r3.m204getSessionMode2uNL3no(), CameraGraph.OperatingMode.Companion.m222getNORMAL2uNL3no()) == false) goto L19;
        CameraMetadata.Companion r32 = CameraMetadata.Companion;
        if (r32.isHardwareLevelLegacy(r2) == false) goto L9;
        return false;
    L9:
        if (r32.isHardwareLevelLimited(r2) == false) goto L11;
        return false;
    L11:
        if (r1 >= 28) goto L13;
        return true;
    L13:
        if (r32.isHardwareLevelExternal(r2) == true) goto L22;
        return true;
    L22:
        return false;
    L19:
        return false;
    }

    private final int computeNextSurfaceGroupId(CameraGraph.Config r2) {
        List<Integer> r1 = readExistingGroupNumbers(r2.getStreams());
        int r22 = Companion.nextGroupId$camera_camera2_pipe();
    L4:
        if (r1.contains(Integer.valueOf(r22)) == false) goto L6;
        r22 = Companion.nextGroupId$camera_camera2_pipe();
        goto L4
    L6:
        return r22;
    }

    private final OutputConfiguration getOutputConfigurationOrNull(OutputStream.Config r1) {
        return null;
    }

    private final List<Integer> readExistingGroupNumbers(List<CameraStream.Config> r3) {
        ArrayList r2 = new ArrayList();
        Iterator<T> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        CollectionsKt.addAll(r2, ((CameraStream.Config) r32.next()).getOutputs());
        goto L4
    L6:
        ArrayList r33 = new ArrayList();
        int r0 = r2.size();
        int r1 = 0;
    L7:
        if (r1 >= r0) goto L9;
        r2.get(r1);
        r1 = r1 + 1;
        goto L7
    L9:
        ArrayList r22 = new ArrayList();
        Iterator r34 = r33.iterator();
        if (r34.hasNext() == true) goto L12;
        return r22;
    L12:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r34.next());
        throw null;
    }

    private final List<CameraStream> sortOutputsByPreviewStream(List<CameraStream> r13) {
        List<CameraStream> r12 = r13;
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        Iterator<T> r2 = r12.iterator();
    L3:
        boolean r4 = true;
        if (r2.hasNext() == false) goto L21;
        Object r3 = r2.next();
        List<OutputStream> r6 = ((CameraStream) r3).getOutputs();
        if ((r6 instanceof Collection) == true) goto L8;
    L10:
        Iterator<T> r62 = r6.iterator();
    L12:
        if (r62.hasNext() == false) goto L9;
        OutputStream.StreamUseCase r7 = ((OutputStream) r62.next()).mo313getStreamUseCase8x2ez34();
        long r8 = OutputStream.StreamUseCase.Companion.m349getPREVIEWvrKr8v8();
        if (r7 != null) goto L16;
        boolean r72 = false;
    L17:
        if (r72 == false) goto L12;
    L18:
        if (r4 == true) goto L19;
        r1.add(r3);
        goto L3
    L19:
        r0.add(r3);
        goto L3
    L16:
        r72 = OutputStream.StreamUseCase.m344equalsimpl0(r7.m347unboximpl(), r8);
    L9:
        r4 = false;
        goto L18
    L8:
        if (r6.isEmpty() == false) goto L10;
    L21:
        Pair r22 = new Pair(r0, r1);
        List r02 = (List) r22.component1();
        List r14 = (List) r22.component2();
        List r03 = r02;
        if (r03.isEmpty() == false) goto L24;
        ArrayList r04 = new ArrayList();
        ArrayList r15 = new ArrayList();
        Iterator<T> r23 = r12.iterator();
    L27:
        if (r23.hasNext() == false) goto L42;
        Object r32 = r23.next();
        List<OutputStream> r63 = ((CameraStream) r32).getOutputs();
        if ((r63 instanceof Collection) == true) goto L31;
    L33:
        Iterator<T> r64 = r63.iterator();
    L35:
        if (r64.hasNext() == false) goto L32;
        if (CollectionsKt.contains(previewOutputTypes, ((OutputStream) r64.next()).getOutputType()) == false) goto L35;
        boolean r65 = true;
    L39:
        if (r65 == true) goto L40;
        r15.add(r32);
        goto L27
    L40:
        r04.add(r32);
    L32:
        r65 = false;
        goto L39
    L31:
        if (r63.isEmpty() == false) goto L33;
    L42:
        Pair r24 = new Pair(r04, r15);
        List r05 = (List) r24.component1();
        List r16 = (List) r24.component2();
        if (r05.isEmpty() == false) goto L45;
        ArrayList r06 = new ArrayList();
        ArrayList r17 = new ArrayList();
        Iterator<T> r122 = r12.iterator();
    L48:
        if (r122.hasNext() == false) goto L63;
        Object r25 = r122.next();
        List<OutputStream> r33 = ((CameraStream) r25).getOutputs();
        if ((r33 instanceof Collection) == true) goto L52;
    L54:
        Iterator<T> r34 = r33.iterator();
    L56:
        if (r34.hasNext() == false) goto L53;
        if (previewFormats.contains(StreamFormat.m389boximpl(((OutputStream) r34.next()).mo310getFormat8FPWQzE())) == false) goto L56;
        boolean r35 = true;
    L60:
        if (r35 == true) goto L61;
        r17.add(r25);
        goto L48
    L61:
        r06.add(r25);
    L53:
        r35 = false;
        goto L60
    L52:
        if (r33.isEmpty() == false) goto L54;
    L63:
        Pair r123 = new Pair(r06, r17);
        List r07 = (List) r123.component1();
        List r124 = (List) r123.component2();
        if (r07.isEmpty() == false) goto L66;
        return r13;
    L66:
        return CollectionsKt.plus(CollectionsKt.sortedWith(r07, previewFormatComparator), r124);
    L45:
        return CollectionsKt.plus(CollectionsKt.sortedWith(r05, previewOutputTypesComparator), r16);
    L24:
        return CollectionsKt.plus(r03, r14);
    }

    private final List<CameraStream> sortOutputsByVideoStream(List<CameraStream> r13) {
        List<CameraStream> r12 = r13;
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        Iterator<T> r2 = r12.iterator();
    L3:
        boolean r4 = true;
        if (r2.hasNext() == false) goto L21;
        Object r3 = r2.next();
        List<OutputStream> r6 = ((CameraStream) r3).getOutputs();
        if ((r6 instanceof Collection) == true) goto L8;
    L10:
        Iterator<T> r62 = r6.iterator();
    L12:
        if (r62.hasNext() == false) goto L9;
        OutputStream.StreamUseCase r7 = ((OutputStream) r62.next()).mo313getStreamUseCase8x2ez34();
        long r8 = OutputStream.StreamUseCase.Companion.m350getVIDEO_RECORDvrKr8v8();
        if (r7 != null) goto L16;
        boolean r72 = false;
    L17:
        if (r72 == false) goto L12;
    L18:
        if (r4 == true) goto L19;
        r1.add(r3);
        goto L3
    L19:
        r0.add(r3);
        goto L3
    L16:
        r72 = OutputStream.StreamUseCase.m344equalsimpl0(r7.m347unboximpl(), r8);
    L9:
        r4 = false;
        goto L18
    L8:
        if (r6.isEmpty() == false) goto L10;
    L21:
        Pair r22 = new Pair(r0, r1);
        List r02 = (List) r22.component1();
        List r14 = (List) r22.component2();
        if (r02.isEmpty() == false) goto L24;
        ArrayList r03 = new ArrayList();
        ArrayList r15 = new ArrayList();
        Iterator<T> r122 = r12.iterator();
    L27:
        if (r122.hasNext() == false) goto L45;
        Object r23 = r122.next();
        List<OutputStream> r32 = ((CameraStream) r23).getOutputs();
        if ((r32 instanceof Collection) == true) goto L31;
    L33:
        Iterator<T> r33 = r32.iterator();
    L35:
        if (r33.hasNext() == false) goto L32;
        OutputStream.StreamUseHint r63 = ((OutputStream) r33.next()).mo314getStreamUseHintHIPxoCc();
        long r73 = OutputStream.StreamUseHint.Companion.m359getVIDEO_RECORD4VYZOf8();
        if (r63 != null) goto L39;
        boolean r64 = false;
    L40:
        if (r64 == false) goto L35;
        boolean r34 = true;
    L42:
        if (r34 == true) goto L43;
        r15.add(r23);
        goto L27
    L43:
        r03.add(r23);
        goto L27
    L39:
        r64 = OutputStream.StreamUseHint.m354equalsimpl0(r63.m357unboximpl(), r73);
    L32:
        r34 = false;
        goto L42
    L31:
        if (r32.isEmpty() == false) goto L33;
    L45:
        Pair r123 = new Pair(r03, r15);
        List r04 = (List) r123.component1();
        List r124 = (List) r123.component2();
        if (r04.isEmpty() == false) goto L48;
        return r13;
    L48:
        return CollectionsKt.plus(r124, r04);
    L24:
        return CollectionsKt.plus(r14, r02);
    }

    @Override // java.lang.AutoCloseable
    public void close() {
        Iterator<ImageSource> r1 = this.imageSourceMap.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(r1.next());
        goto L4
    }

    @Override // androidx.camera.camera2.pipe.StreamGraph
    public CameraStream get(CameraStream.Config r1) {
        return this._streamMap.get(r1);
    }

    /* renamed from: getCameraStreamConfig-aKI5c8E, reason: not valid java name */
    public final CameraStream.Config m539getCameraStreamConfigaKI5c8E(int r4) {
        Iterator<T> r3 = this._streamMap.entrySet().iterator();
    L4:
        if (r3.hasNext() == false) goto L8;
        Object r0 = r3.next();
        if (StreamId.m404equalsimpl0(((CameraStream) ((Map.Entry) r0).getValue()).m241getIdptHMqGs(), r4) == false) goto L4;
    L9:
        Map.Entry r02 = (Map.Entry) r0;
        if (r02 != null) goto L12;
        return null;
    L12:
        return (CameraStream.Config) r02.getKey();
    L8:
        r0 = null;
        goto L9
    }

    public final Map<StreamId, ImageSource> getImageSourceMap$camera_camera2_pipe() {
        return this.imageSourceMap;
    }

    @Override // androidx.camera.camera2.pipe.StreamGraph
    public List<InputStream> getInputs() {
        return this.inputs;
    }

    public final Map<OutputStream, OutputConfig> getOutputConfigMap$camera_camera2_pipe() {
        return this.outputConfigMap;
    }

    public final List<OutputConfig> getOutputConfigs$camera_camera2_pipe() {
        return this.outputConfigs;
    }

    @Override // androidx.camera.camera2.pipe.StreamGraph
    public List<OutputStream> getOutputs() {
        return this.outputs;
    }

    @Override // androidx.camera.camera2.pipe.StreamGraph
    public List<CameraStream> getStreams() {
        return this.streams;
    }

    public String toString() {
        return "StreamGraph(" + this._streamMap + ')';
    }
}
