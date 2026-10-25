package androidx.camera.camera2.adapter;

import android.hardware.camera2.CaptureRequest;
import android.media.Image;
import androidx.camera.camera2.compat.workaround.TemplateParamsOverride;
import androidx.camera.camera2.config.UseCaseGraphContext;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.impl.Camera2ImplConfigKt;
import androidx.camera.camera2.impl.CameraCallbackMap;
import androidx.camera.camera2.impl.CameraProperties;
import androidx.camera.camera2.impl.TagsKt;
import androidx.camera.camera2.impl.UseCaseThreads;
import androidx.camera.camera2.pipe.CameraMetadata;
import androidx.camera.camera2.pipe.FrameInfo;
import androidx.camera.camera2.pipe.InputRequest;
import androidx.camera.camera2.pipe.Request;
import androidx.camera.camera2.pipe.RequestFailure;
import androidx.camera.camera2.pipe.RequestMetadata;
import androidx.camera.camera2.pipe.RequestTemplate;
import androidx.camera.camera2.pipe.StreamId;
import androidx.camera.camera2.pipe.media.AndroidImage;
import androidx.camera.core.ImageProxy;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CameraCaptureResult;
import androidx.camera.core.impl.CameraCaptureResults;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.DeferrableSurface;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.OkHttpClient$$ExternalSyntheticBUOutline0;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B1\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ7\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u000e\b\u0002\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019H\u0007¢\u0006\u0004\b\u001b\u0010\u001cJ\u0010\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Landroidx/camera/camera2/adapter/CaptureConfigAdapter;", _UrlKt.FRAGMENT_ENCODE_SET, "cameraProperties", "Landroidx/camera/camera2/impl/CameraProperties;", "useCaseGraphContext", "Landroidx/camera/camera2/config/UseCaseGraphContext;", "zslControl", "Landroidx/camera/camera2/adapter/ZslControl;", "threads", "Landroidx/camera/camera2/impl/UseCaseThreads;", "templateParamsOverride", "Landroidx/camera/camera2/compat/workaround/TemplateParamsOverride;", "<init>", "(Landroidx/camera/camera2/impl/CameraProperties;Landroidx/camera/camera2/config/UseCaseGraphContext;Landroidx/camera/camera2/adapter/ZslControl;Landroidx/camera/camera2/impl/UseCaseThreads;Landroidx/camera/camera2/compat/workaround/TemplateParamsOverride;)V", "isLegacyDevice", _UrlKt.FRAGMENT_ENCODE_SET, "mapToRequest", "Landroidx/camera/camera2/pipe/Request;", "captureConfig", "Landroidx/camera/core/impl/CaptureConfig;", "requestTemplate", "Landroidx/camera/camera2/pipe/RequestTemplate;", "sessionConfigOptions", "Landroidx/camera/core/impl/Config;", "additionalListeners", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/camera/camera2/pipe/Request$Listener;", "mapToRequest-nAberiA", "(Landroidx/camera/core/impl/CaptureConfig;ILandroidx/camera/core/impl/Config;Ljava/util/List;)Landroidx/camera/camera2/pipe/Request;", "buildImageClosingRequestListener", "imageProxy", "Landroidx/camera/core/ImageProxy;", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCaptureConfigAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CaptureConfigAdapter.kt\nandroidx/camera/camera2/adapter/CaptureConfigAdapter\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,238:1\n1563#2:239\n1634#2,3:240\n1869#2,2:243\n1#3:245\n*S KotlinDebug\n*F\n+ 1 CaptureConfigAdapter.kt\nandroidx/camera/camera2/adapter/CaptureConfigAdapter\n*L\n83#1:239\n83#1:240,3\n91#1:243,2\n*E\n"})
/* loaded from: classes3.dex */
public final class CaptureConfigAdapter {
    public static final Companion Companion = null;
    private final boolean isLegacyDevice;
    private final TemplateParamsOverride templateParamsOverride;
    private final UseCaseThreads threads;
    private final UseCaseGraphContext useCaseGraphContext;
    private final ZslControl zslControl;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\u0004\u001a\u00020\u0005*\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/camera/camera2/adapter/CaptureConfigAdapter$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getStillCaptureTemplate", "Landroidx/camera/camera2/pipe/RequestTemplate;", "Landroidx/camera/core/impl/CaptureConfig;", "sessionTemplate", "isLegacyDevice", _UrlKt.FRAGMENT_ENCODE_SET, "getStillCaptureTemplate-CMLptTo$camera_camera2", "(Landroidx/camera/core/impl/CaptureConfig;IZ)I", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        /* renamed from: getStillCaptureTemplate-CMLptTo$camera_camera2, reason: not valid java name */
        public final int m12getStillCaptureTemplateCMLptTo$camera_camera2(CaptureConfig r1, int r2, boolean r3) {
            if (RequestTemplate.m373equalsimpl0(r2, RequestTemplate.m371constructorimpl(3)) == false) goto L7;
            if (r3 == true) goto L7;
            int r0 = 4;
        L13:
            if (r0 == (-1)) goto L17;
            return RequestTemplate.m371constructorimpl(r0);
        L17:
            return RequestTemplate.m371constructorimpl(r1.getTemplateType());
        L7:
            if (r1.getTemplateType() != (-1)) goto L9;
        L12:
            r0 = 2;
            goto L13
        L9:
            if (r1.getTemplateType() == 5) goto L12;
            r0 = -1;
            goto L13
        }
    }

    @Metadata(d1 = {"\u00005\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J'\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¢\u0006\u0004\b\n\u0010\u000bJ'\u0010\f\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0016¢\u0006\u0004\b\u000f\u0010\u0010J\u0010\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J'\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\tH\u0016¢\u0006\u0004\b\u0016\u0010\u000b¨\u0006\u0017"}, d2 = {"androidx/camera/camera2/adapter/CaptureConfigAdapter$buildImageClosingRequestListener$1", "Landroidx/camera/camera2/pipe/Request$Listener;", "onComplete", _UrlKt.FRAGMENT_ENCODE_SET, "requestMetadata", "Landroidx/camera/camera2/pipe/RequestMetadata;", "frameNumber", "Landroidx/camera/camera2/pipe/FrameNumber;", "result", "Landroidx/camera/camera2/pipe/FrameInfo;", "onComplete-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/FrameInfo;)V", "onFailed", "requestFailure", "Landroidx/camera/camera2/pipe/RequestFailure;", "onFailed-CcXjc1I", "(Landroidx/camera/camera2/pipe/RequestMetadata;JLandroidx/camera/camera2/pipe/RequestFailure;)V", "onAborted", PluginsConstants.REQUEST, "Landroidx/camera/camera2/pipe/Request;", "onTotalCaptureResult", "totalCaptureResult", "onTotalCaptureResult-CcXjc1I", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    /* renamed from: androidx.camera.camera2.adapter.CaptureConfigAdapter$buildImageClosingRequestListener$1, reason: invalid class name */
    public static final class AnonymousClass1 implements Request.Listener {
        final /* synthetic */ AtomicReference<ImageProxy> $imageProxyToClose;

        public AnonymousClass1(AtomicReference<ImageProxy> r1) {
            this.$imageProxyToClose = r1;
        }

        @Override // androidx.camera.camera2.pipe.Request.Listener
        public void onAborted(Request r1) {
            CaptureConfigAdapter.access$buildImageClosingRequestListener$closeImageProxy(this.$imageProxyToClose);
        }

        @Override // androidx.camera.camera2.pipe.Request.Listener
        /* renamed from: onComplete-CcXjc1I, reason: not valid java name */
        public void mo13onCompleteCcXjc1I(RequestMetadata r1, long r2, FrameInfo r4) {
            CaptureConfigAdapter.access$buildImageClosingRequestListener$closeImageProxy(this.$imageProxyToClose);
        }

        @Override // androidx.camera.camera2.pipe.Request.Listener
        /* renamed from: onFailed-CcXjc1I, reason: not valid java name */
        public void mo14onFailedCcXjc1I(RequestMetadata r1, long r2, RequestFailure r4) {
            CaptureConfigAdapter.access$buildImageClosingRequestListener$closeImageProxy(this.$imageProxyToClose);
        }

        @Override // androidx.camera.camera2.pipe.Request.Listener
        /* renamed from: onTotalCaptureResult-CcXjc1I, reason: not valid java name */
        public void mo15onTotalCaptureResultCcXjc1I(RequestMetadata r1, long r2, FrameInfo r4) {
            CaptureConfigAdapter.access$buildImageClosingRequestListener$closeImageProxy(this.$imageProxyToClose);
        }
    }

    static {
        Companion = new Companion(null);
    }

    public CaptureConfigAdapter(CameraProperties r1, UseCaseGraphContext r2, ZslControl r3, UseCaseThreads r4, TemplateParamsOverride r5) {
        this.useCaseGraphContext = r2;
        this.zslControl = r3;
        this.threads = r4;
        this.templateParamsOverride = r5;
        this.isLegacyDevice = CameraMetadata.Companion.isHardwareLevelLegacy(r1.getMetadata());
    }

    public static final /* synthetic */ void access$buildImageClosingRequestListener$closeImageProxy(AtomicReference r0) {
        buildImageClosingRequestListener$closeImageProxy(r0);
    }

    private final Request.Listener buildImageClosingRequestListener(ImageProxy r1) {
        return new AnonymousClass1(new AtomicReference(r1));
    }

    private static final void buildImageClosingRequestListener$closeImageProxy(AtomicReference<ImageProxy> r1) {
        ImageProxy r12 = r1.getAndSet(null);
        if (r12 == null) goto L6;
        r12.close();
        return;
    }

    /* renamed from: mapToRequest-nAberiA, reason: not valid java name */
    public final Request m11mapToRequestnAberiA(CaptureConfig r12, int r13, Config r14, List<? extends Request.Listener> r15) {
        List<DeferrableSurface> r0 = r12.getSurfaces();
        Request.Listener r2 = null;
        InputRequest r22 = null;
        if (r0.isEmpty() == true) goto L55;
        List<DeferrableSurface> r02 = r0;
        ArrayList r4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r02, 10));
        Iterator<T> r03 = r02.iterator();
    L6:
        if (r03.hasNext() == false) goto L12;
        DeferrableSurface r1 = (DeferrableSurface) r03.next();
        StreamId r3 = this.useCaseGraphContext.getSurfaceToStreamMap().get(r1);
        if (r3 == null) goto L10;
        r4.add(StreamId.m401boximpl(r3.m407unboximpl()));
        goto L6
    L10:
        OkHttpClient$$ExternalSyntheticBUOutline0.m("Attempted to issue a capture with an unrecognized surface: ", r1);
        return null;
    L12:
        CameraCallbackMap r04 = new CameraCallbackMap();
        Iterator<T> r16 = r12.getCameraCaptureCallbacks().iterator();
    L14:
        if (r16.hasNext() == false) goto L16;
        r04.addCaptureCallback((CameraCaptureCallback) r16.next(), this.threads.getSequentialExecutor());
        goto L14
    L16:
        Config r17 = r12.getImplementationOptions();
        Camera2ImplConfig.Builder r32 = new Camera2ImplConfig.Builder();
        r32.insertAllOptions(r14);
        r32.insertAllOptions(r17);
        Config.Option<Integer> r142 = CaptureConfig.OPTION_ROTATION;
        if (r17.containsOption(r142) == false) goto L19;
        r32.setCaptureRequestOption(CaptureRequest.JPEG_ORIENTATION, r17.retrieveOption(r142));
    L19:
        Config.Option<Integer> r143 = CaptureConfig.OPTION_JPEG_QUALITY;
        if (r17.containsOption(r143) == false) goto L22;
        r32.setCaptureRequestOption(CaptureRequest.JPEG_QUALITY, Byte.valueOf((byte) ((Number) r17.retrieveOption(r143)).intValue()));
    L22:
        int r144 = RequestTemplate.m371constructorimpl(r12.getTemplateType());
        if (r12.getTemplateType() == 5) goto L25;
    L47:
        InputRequest r9 = null;
    L48:
        if (r9 != null) goto L50;
        r144 = Companion.m12getStillCaptureTemplateCMLptTo$camera_camera2(r12, r13, this.isLegacyDevice);
    L50:
        Map r5 = MapsKt.plus(this.templateParamsOverride.mo35getOverrideParamsxlOpshk(RequestTemplate.m370boximpl(r144)), Camera2ImplConfigKt.toParameters(r32.build()));
        List r11 = CollectionsKt.createListBuilder();
        r11.add(r04);
        if (r2 == null) goto L53;
        r11.add(r2);
    L53:
        r11.addAll(r15);
        return new Request(r4, r5, MapsKt.mapOf(TuplesKt.to(TagsKt.getCAMERAX_TAG_BUNDLE(), r12.getTagBundle())), CollectionsKt.build(r11), RequestTemplate.m370boximpl(r144), r9, null);
    L25:
        if (this.zslControl.isZslDisabledByUserCaseConfig() == true) goto L47;
        if (this.zslControl.isZslDisabledByFlashMode() == true) goto L47;
        ImageProxy r18 = this.zslControl.dequeueImageFromBuffer();
        if (r18 == null) goto L47;
        CameraCaptureResult r52 = CameraCaptureResults.retrieveCameraCaptureResult(r18.getImageInfo());
        if (r52 != null) goto L33;
        Request.Listener r19 = null;
    L46:
        r9 = r22;
        r2 = r19;
        goto L48
    L33:
        if ((r52 instanceof CaptureResultAdapter) == false) goto L43;
        Image r7 = r18.getImage();
        if (r7 == null) goto L41;
        AndroidImage r6 = new AndroidImage(r7);
        Object r53 = ((CaptureResultAdapter) r52).unwrapAs(Reflection.getOrCreateKotlinClass(FrameInfo.class));
        if (r53 == null) goto L39;
        r22 = new InputRequest(r6, (FrameInfo) r53);
        r19 = buildImageClosingRequestListener(r18);
        goto L46
    L39:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L41:
        Segment$$ExternalSyntheticBUOutline1.m("Required value was null.");
        return null;
    L43:
        OkHttpClient$$ExternalSyntheticBUOutline0.m("Unexpected capture result type: ", r52.getClass());
        return null;
    L55:
        OkHttpClient$$ExternalSyntheticBUOutline0.m("Attempted to issue a capture without surfaces using ", r12);
        return null;
    }
}
