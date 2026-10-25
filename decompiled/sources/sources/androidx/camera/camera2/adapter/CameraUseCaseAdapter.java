package androidx.camera.camera2.adapter;

import android.app.Application;
import android.content.Context;
import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.util.Log;
import android.util.Size;
import androidx.camera.camera2.compat.quirk.DeviceQuirks;
import androidx.camera.camera2.compat.quirk.PreviewUnderExposureQuirk;
import androidx.camera.camera2.compat.workaround.ImageCapturePixelHDRPlusKt;
import androidx.camera.camera2.compat.workaround.PreviewPixelHDRnetKt;
import androidx.camera.camera2.impl.Camera2ImplConfig;
import androidx.camera.camera2.impl.Camera2Logger;
import androidx.camera.camera2.impl.DisplayInfoManager;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.CameraCaptureCallback;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.DeferrableSurface;
import androidx.camera.core.impl.ImageCaptureConfig;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.PreviewConfig;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.Iterator;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0004\u000e\u000f\u0010\u0011B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH\u0017R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/camera/camera2/adapter/CameraUseCaseAdapter;", "Landroidx/camera/core/impl/UseCaseConfigFactory;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "displayInfoManager", "Landroidx/camera/camera2/impl/DisplayInfoManager;", "getConfig", "Landroidx/camera/core/impl/Config;", "captureType", "Landroidx/camera/core/impl/UseCaseConfigFactory$CaptureType;", "captureMode", _UrlKt.FRAGMENT_ENCODE_SET, "DefaultCaptureOptionsUnpacker", "ImageCaptureOptionUnpacker", "DefaultSessionOptionsUnpacker", "CaptureCallbackContainer", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCameraUseCaseAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraUseCaseAdapter.kt\nandroidx/camera/camera2/adapter/CameraUseCaseAdapter\n+ 2 Camera2Logger.kt\nandroidx/camera/camera2/impl/Camera2Logger\n*L\n1#1,289:1\n102#2,4:290\n85#2,4:294\n85#2,4:298\n*S KotlinDebug\n*F\n+ 1 CameraUseCaseAdapter.kt\nandroidx/camera/camera2/adapter/CameraUseCaseAdapter\n*L\n60#1:290,4\n66#1:294,4\n76#1:298,4\n*E\n"})
/* loaded from: classes3.dex */
public final class CameraUseCaseAdapter implements UseCaseConfigFactory {
    private final DisplayInfoManager displayInfoManager;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0000\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Landroidx/camera/camera2/adapter/CameraUseCaseAdapter$CaptureCallbackContainer;", "Landroidx/camera/core/impl/CameraCaptureCallback;", "captureCallback", "Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;", "<init>", "(Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;)V", "getCaptureCallback", "()Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class CaptureCallbackContainer extends CameraCaptureCallback {
        public static final Companion Companion = null;
        private final CameraCaptureSession.CaptureCallback captureCallback;

        @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/adapter/CameraUseCaseAdapter$CaptureCallbackContainer$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "create", "Landroidx/camera/camera2/adapter/CameraUseCaseAdapter$CaptureCallbackContainer;", "captureCallback", "Landroid/hardware/camera2/CameraCaptureSession$CaptureCallback;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            public final CaptureCallbackContainer create(CameraCaptureSession.CaptureCallback r2) {
                return new CaptureCallbackContainer(r2, null);
            }
        }

        static {
            Companion = new Companion(null);
        }

        private CaptureCallbackContainer(CameraCaptureSession.CaptureCallback r1) {
            this.captureCallback = r1;
        }

        public /* synthetic */ CaptureCallbackContainer(CameraCaptureSession.CaptureCallback r1, DefaultConstructorMarker r2) {
            this(r1);
        }

        public final CameraCaptureSession.CaptureCallback getCaptureCallback() {
            return this.captureCallback;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\tH\u0017¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/adapter/CameraUseCaseAdapter$DefaultCaptureOptionsUnpacker;", "Landroidx/camera/core/impl/CaptureConfig$OptionUnpacker;", "<init>", "()V", "unpack", _UrlKt.FRAGMENT_ENCODE_SET, "config", "Landroidx/camera/core/impl/UseCaseConfig;", "builder", "Landroidx/camera/core/impl/CaptureConfig$Builder;", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCameraUseCaseAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraUseCaseAdapter.kt\nandroidx/camera/camera2/adapter/CameraUseCaseAdapter$DefaultCaptureOptionsUnpacker\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,289:1\n1869#2,2:290\n*S KotlinDebug\n*F\n+ 1 CameraUseCaseAdapter.kt\nandroidx/camera/camera2/adapter/CameraUseCaseAdapter$DefaultCaptureOptionsUnpacker\n*L\n165#1:290,2\n*E\n"})
    public static class DefaultCaptureOptionsUnpacker implements CaptureConfig.OptionUnpacker {
        public static final Companion Companion = null;
        private static final DefaultCaptureOptionsUnpacker INSTANCE = null;

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/adapter/CameraUseCaseAdapter$DefaultCaptureOptionsUnpacker$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "INSTANCE", "Landroidx/camera/camera2/adapter/CameraUseCaseAdapter$DefaultCaptureOptionsUnpacker;", "getINSTANCE", "()Landroidx/camera/camera2/adapter/CameraUseCaseAdapter$DefaultCaptureOptionsUnpacker;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            public final DefaultCaptureOptionsUnpacker getINSTANCE() {
                return DefaultCaptureOptionsUnpacker.access$getINSTANCE$cp();
            }
        }

        static {
            Companion = new Companion(null);
            INSTANCE = new DefaultCaptureOptionsUnpacker();
        }

        public DefaultCaptureOptionsUnpacker() {
        }

        public static final /* synthetic */ DefaultCaptureOptionsUnpacker access$getINSTANCE$cp() {
            return INSTANCE;
        }

        @Override // androidx.camera.core.impl.CaptureConfig.OptionUnpacker
        public void unpack(UseCaseConfig<?> r5, CaptureConfig.Builder r6) {
            CaptureConfig r0 = r5.getDefaultCaptureConfig(null);
            Config r1 = OptionsBundle.emptyBundle();
            int r2 = CaptureConfig.defaultEmptyCaptureConfig().getTemplateType();
            if (r0 == null) goto L8;
            r2 = r0.getTemplateType();
            r6.addAllCameraCaptureCallbacks(r0.getCameraCaptureCallbacks());
            r1 = r0.getImplementationOptions();
            r6.setUseRepeatingSurface(r0.isUseRepeatingSurface());
            r6.addAllTags(r0.getTagBundle());
            Iterator<T> r02 = r0.getSurfaces().iterator();
        L6:
            if (r02.hasNext() == false) goto L8;
            r6.addSurface((DeferrableSurface) r02.next());
        L8:
            r6.setImplementationOptions(r1);
            Camera2ImplConfig r03 = new Camera2ImplConfig(r5);
            r6.setTemplateType(r03.getCaptureRequestTemplate(r2));
            CameraCaptureSession.CaptureCallback r4 = Camera2ImplConfig.getSessionCaptureCallback$default(r03, null, 1, null);
            if (r4 == null) goto L11;
            r6.addCameraCaptureCallback(CaptureCallbackContainer.Companion.create(r4));
        L11:
            r6.addImplementationOptions(r03.getCaptureRequestOptions());
        }
    }

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J$\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\n\u001a\u00020\u000bH\u0017¨\u0006\f"}, d2 = {"Landroidx/camera/camera2/adapter/CameraUseCaseAdapter$DefaultSessionOptionsUnpacker;", "Landroidx/camera/core/impl/SessionConfig$OptionUnpacker;", "<init>", "()V", "unpack", _UrlKt.FRAGMENT_ENCODE_SET, "resolution", "Landroid/util/Size;", "config", "Landroidx/camera/core/impl/UseCaseConfig;", "builder", "Landroidx/camera/core/impl/SessionConfig$Builder;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCameraUseCaseAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraUseCaseAdapter.kt\nandroidx/camera/camera2/adapter/CameraUseCaseAdapter$DefaultSessionOptionsUnpacker\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,289:1\n1#2:290\n*E\n"})
    public static final class DefaultSessionOptionsUnpacker implements SessionConfig.OptionUnpacker {
        public static final DefaultSessionOptionsUnpacker INSTANCE = null;

        static {
            INSTANCE = new DefaultSessionOptionsUnpacker();
        }

        private DefaultSessionOptionsUnpacker() {
        }

        @Override // androidx.camera.core.impl.SessionConfig.OptionUnpacker
        public void unpack(Size r4, UseCaseConfig<?> r5, SessionConfig.Builder r6) {
            SessionConfig r0 = r5.getDefaultSessionConfig(null);
            Config r1 = OptionsBundle.emptyBundle();
            int r2 = SessionConfig.defaultEmptySessionConfig().getTemplateType();
            if (r0 == null) goto L5;
            r2 = r0.getTemplateType();
            r6.addAllDeviceStateCallbacks(r0.getDeviceStateCallbacks());
            r6.addAllSessionStateCallbacks(r0.getSessionStateCallbacks());
            r6.addAllRepeatingCameraCaptureCallbacks(r0.getRepeatingCameraCaptureCallbacks());
            r1 = r0.getImplementationOptions();
        L5:
            r6.setImplementationOptions(r1);
            if ((r5 instanceof PreviewConfig) == false) goto L8;
            PreviewPixelHDRnetKt.setupHDRnet(r6, r4);
        L8:
            Camera2ImplConfig r42 = new Camera2ImplConfig(r5);
            r6.setTemplateType(r42.getCaptureRequestTemplate(r2));
            CameraDevice.StateCallback r12 = Camera2ImplConfig.getDeviceStateCallback$default(r42, null, 1, null);
            if (r12 == null) goto L11;
            r6.addDeviceStateCallback(r12);
        L11:
            CameraCaptureSession.StateCallback r13 = Camera2ImplConfig.getSessionStateCallback$default(r42, null, 1, null);
            if (r13 == null) goto L14;
            r6.addSessionStateCallback(r13);
        L14:
            CameraCaptureSession.CaptureCallback r14 = Camera2ImplConfig.getSessionCaptureCallback$default(r42, null, 1, null);
            if (r14 == null) goto L17;
            r6.addCameraCaptureCallback(CaptureCallbackContainer.Companion.create(r14));
        L17:
            r6.setPreviewStabilization(r5.getPreviewStabilizationMode());
            r6.setVideoStabilization(r5.getVideoStabilizationMode());
            MutableOptionsBundle r52 = MutableOptionsBundle.create();
            String r15 = Camera2ImplConfig.getPhysicalCameraId$default(r42, null, 1, null);
            if (r15 == null) goto L20;
            r52.insertOption(Camera2ImplConfig.SESSION_PHYSICAL_CAMERA_ID_OPTION, r15);
        L20:
            Long r3 = Camera2ImplConfig.getStreamUseCase$default(r42, null, 1, null);
            if (r3 == null) goto L23;
            long r02 = r3.longValue();
            r52.insertOption(Camera2ImplConfig.STREAM_USE_CASE_OPTION, Long.valueOf(r02));
        L23:
            r6.addImplementationOptions(r52);
            r6.addImplementationOptions(r42.getCaptureRequestOptions());
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \n2\u00020\u0001:\u0001\nB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\tH\u0016¨\u0006\u000b"}, d2 = {"Landroidx/camera/camera2/adapter/CameraUseCaseAdapter$ImageCaptureOptionUnpacker;", "Landroidx/camera/camera2/adapter/CameraUseCaseAdapter$DefaultCaptureOptionsUnpacker;", "<init>", "()V", "unpack", _UrlKt.FRAGMENT_ENCODE_SET, "config", "Landroidx/camera/core/impl/UseCaseConfig;", "builder", "Landroidx/camera/core/impl/CaptureConfig$Builder;", "Companion", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCameraUseCaseAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CameraUseCaseAdapter.kt\nandroidx/camera/camera2/adapter/CameraUseCaseAdapter$ImageCaptureOptionUnpacker\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,289:1\n1#2:290\n*E\n"})
    public static final class ImageCaptureOptionUnpacker extends DefaultCaptureOptionsUnpacker {
        public static final Companion Companion = null;
        private static final ImageCaptureOptionUnpacker INSTANCE = null;

        @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Landroidx/camera/camera2/adapter/CameraUseCaseAdapter$ImageCaptureOptionUnpacker$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "INSTANCE", "Landroidx/camera/camera2/adapter/CameraUseCaseAdapter$ImageCaptureOptionUnpacker;", "getINSTANCE", "()Landroidx/camera/camera2/adapter/CameraUseCaseAdapter$ImageCaptureOptionUnpacker;", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }

            public final ImageCaptureOptionUnpacker getINSTANCE() {
                return ImageCaptureOptionUnpacker.access$getINSTANCE$cp();
            }
        }

        static {
            Companion = new Companion(null);
            INSTANCE = new ImageCaptureOptionUnpacker();
        }

        public ImageCaptureOptionUnpacker() {
        }

        public static final /* synthetic */ ImageCaptureOptionUnpacker access$getINSTANCE$cp() {
            return INSTANCE;
        }

        @Override // androidx.camera.camera2.adapter.CameraUseCaseAdapter.DefaultCaptureOptionsUnpacker, androidx.camera.core.impl.CaptureConfig.OptionUnpacker
        public void unpack(UseCaseConfig<?> r1, CaptureConfig.Builder r2) {
            super.unpack(r1, r2);
            if ((r1 instanceof ImageCaptureConfig) == false) goto L6;
            Camera2ImplConfig.Builder r0 = new Camera2ImplConfig.Builder();
            ImageCapturePixelHDRPlusKt.toggleHDRPlus(r0, (ImageCaptureConfig) r1);
            r2.addImplementationOptions(r0.build());
            return;
        L6:
            f$$ExternalSyntheticBUOutline1.m("config is not ImageCaptureConfig");
        }
    }

    @Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

        static {
            int[] r0 = new int[UseCaseConfigFactory.CaptureType.values().length];
            r0[UseCaseConfigFactory.CaptureType.IMAGE_CAPTURE.ordinal()] = 1;     // Catch: NoSuchFieldError -> L11
        L19:
            r0[UseCaseConfigFactory.CaptureType.PREVIEW.ordinal()] = 2;     // Catch: NoSuchFieldError -> L12
        L27:
            r0[UseCaseConfigFactory.CaptureType.STREAM_SHARING.ordinal()] = 3;     // Catch: NoSuchFieldError -> L13
        L21:
            r0[UseCaseConfigFactory.CaptureType.METERING_REPEATING.ordinal()] = 4;     // Catch: NoSuchFieldError -> L14
        L23:
            r0[UseCaseConfigFactory.CaptureType.IMAGE_ANALYSIS.ordinal()] = 5;     // Catch: NoSuchFieldError -> L15
        L17:
            r0[UseCaseConfigFactory.CaptureType.VIDEO_CAPTURE.ordinal()] = 6;     // Catch: NoSuchFieldError -> L16
        L9:
            $EnumSwitchMapping$0 = r0;
        }
    }

    public CameraUseCaseAdapter(Context r4) {
        this.displayInfoManager = DisplayInfoManager.Companion.getInstance(r4);
        if ((r4 instanceof Application) == false) goto L7;
        Camera2Logger r3 = Camera2Logger.INSTANCE;
        if (Logger.isInfoEnabled("CXCP") == false) goto L7;
        Log.i(Camera2Logger.access$getTRUNCATED_TAG$p(), "The provided context (" + r4 + ") is application scoped and will be used to infer the default display for computing the default preview size, orientation, and default aspect ratio for UseCase outputs.");
    L7:
        Camera2Logger r32 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L11;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Created UseCaseConfigurationMap");
        return;
    }

    @Override // androidx.camera.core.impl.UseCaseConfigFactory
    public Config getConfig(UseCaseConfigFactory.CaptureType r9, int r10) {
        Camera2Logger r0 = Camera2Logger.INSTANCE;
        if (Logger.isDebugEnabled("CXCP") == false) goto L5;
        Log.d(Camera2Logger.access$getTRUNCATED_TAG$p(), "Creating config for " + r9);
    L5:
        MutableOptionsBundle r02 = MutableOptionsBundle.create();
        SessionConfig.Builder r1 = new SessionConfig.Builder();
        int[] r2 = WhenMappings.$EnumSwitchMapping$0;
        int r5 = 3;
        switch(r2[r9.ordinal()]) {
            case 1: goto L14;
            case 2: goto L14;
            case 3: goto L14;
            case 4: goto L14;
            case 5: goto L14;
            case 6: goto L10;
            default: goto L7;
        };
    L7:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L14:
        r1.setTemplateType(1);
    L15:
        r02.insertOption(UseCaseConfig.OPTION_DEFAULT_SESSION_CONFIG, r1.build());
        CaptureConfig.Builder r12 = new CaptureConfig.Builder();
        switch(r2[r9.ordinal()]) {
            case 1: goto L24;
            case 2: goto L23;
            case 3: goto L23;
            case 4: goto L23;
            case 5: goto L23;
            case 6: goto L20;
            default: goto L17;
        };
    L17:
        LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
        return null;
    L23:
        r12.setTemplateType(1);
    L28:
        r02.insertOption(UseCaseConfig.OPTION_DEFAULT_CAPTURE_CONFIG, r12.build());
        Config.Option<CaptureConfig.OptionUnpacker> r102 = UseCaseConfig.OPTION_CAPTURE_CONFIG_UNPACKER;
        if (r9 != UseCaseConfigFactory.CaptureType.IMAGE_CAPTURE) goto L31;
        DefaultCaptureOptionsUnpacker r13 = ImageCaptureOptionUnpacker.Companion.getINSTANCE();
    L32:
        r02.insertOption(r102, r13);
        r02.insertOption(UseCaseConfig.OPTION_SESSION_CONFIG_UNPACKER, DefaultSessionOptionsUnpacker.INSTANCE);
        if (r9 != UseCaseConfigFactory.CaptureType.PREVIEW) goto L35;
        r02.insertOption(ImageOutputConfig.OPTION_MAX_RESOLUTION, this.displayInfoManager.getPreviewSize());
    L35:
        r02.insertOption(ImageOutputConfig.OPTION_TARGET_ROTATION, Integer.valueOf(DisplayInfoManager.getMaxSizeDisplay$default(this.displayInfoManager, false, 1, null).getRotation()));
        return OptionsBundle.from(r02);
    L31:
        r13 = DefaultCaptureOptionsUnpacker.Companion.getINSTANCE();
        goto L32
    L24:
        int r22 = 2;
        if (r10 != 2) goto L27;
        r22 = 5;
    L27:
        r12.setTemplateType(r22);
        goto L28
    L20:
        if (DeviceQuirks.INSTANCE.get(PreviewUnderExposureQuirk.class) == null) goto L22;
        r5 = 1;
    L22:
        r12.setTemplateType(r5);
        goto L28
    L10:
        if (DeviceQuirks.INSTANCE.get(PreviewUnderExposureQuirk.class) == null) goto L12;
        int r3 = 1;
    L13:
        r1.setTemplateType(r3);
        goto L15
    L12:
        r3 = 3;
        goto L13
    }
}
