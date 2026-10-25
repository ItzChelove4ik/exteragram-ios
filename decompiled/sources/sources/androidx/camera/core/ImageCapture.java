package androidx.camera.core;

import android.hardware.camera2.CameraCharacteristics;
import android.net.Uri;
import android.util.Log;
import android.util.Pair;
import android.util.Rational;
import android.util.Size;
import androidx.camera.core.featuregroup.GroupableFeature;
import androidx.camera.core.featuregroup.impl.feature.ImageFormatFeature;
import androidx.camera.core.imagecapture.ImageCaptureControl;
import androidx.camera.core.imagecapture.ImagePipeline;
import androidx.camera.core.imagecapture.PostviewSettings;
import androidx.camera.core.imagecapture.TakePictureManager;
import androidx.camera.core.impl.AdapterCameraInfo;
import androidx.camera.core.impl.CameraInfoInternal;
import androidx.camera.core.impl.CameraInternal;
import androidx.camera.core.impl.Config;
import androidx.camera.core.impl.ImageCaptureConfig;
import androidx.camera.core.impl.ImageInputConfig;
import androidx.camera.core.impl.ImageOutputConfig;
import androidx.camera.core.impl.ImageReaderProxy;
import androidx.camera.core.impl.MutableConfig;
import androidx.camera.core.impl.MutableOptionsBundle;
import androidx.camera.core.impl.OptionsBundle;
import androidx.camera.core.impl.SessionConfig;
import androidx.camera.core.impl.SessionProcessor;
import androidx.camera.core.impl.StreamSpec;
import androidx.camera.core.impl.StreamUseCase;
import androidx.camera.core.impl.UseCaseConfig;
import androidx.camera.core.impl.UseCaseConfigFactory;
import androidx.camera.core.impl.utils.CameraOrientationUtil;
import androidx.camera.core.impl.utils.Threads;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import androidx.camera.core.internal.IoConfig;
import androidx.camera.core.internal.ScreenFlashWrapper;
import androidx.camera.core.internal.TargetConfig;
import androidx.camera.core.internal.compat.quirk.SoftwareJpegEncodingPreferredQuirk;
import androidx.camera.core.internal.compat.workaround.ExifRotationAvailability;
import androidx.camera.core.internal.utils.ImageUtil;
import androidx.camera.core.resolutionselector.AspectRatioStrategy;
import androidx.camera.core.resolutionselector.ResolutionSelector;
import androidx.camera.core.resolutionselector.ResolutionStrategy;
import androidx.core.util.Preconditions;
import c.f$$ExternalSyntheticBUOutline1;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicReference;
import retrofit2.Utils$$ExternalSyntheticBUOutline2;

/* loaded from: classes3.dex */
public final class ImageCapture extends UseCase {
    public static final Defaults DEFAULT_CONFIG = null;
    static final ExifRotationAvailability EXIF_ROTATION_AVAILABILITY = null;
    private final int mCaptureMode;
    private SessionConfig.CloseableErrorListener mCloseableErrorListener;
    private final ImageReaderProxy.OnImageAvailableListener mClosingListener;
    private Rational mCropAspectRatio;
    private int mFlashMode;
    private final int mFlashType;
    private final ImageCaptureControl mImageCaptureControl;
    private ImagePipeline mImagePipeline;
    private final AtomicReference<Integer> mLockedFlashMode;
    private ScreenFlashWrapper mScreenFlashWrapper;
    SessionConfig.Builder mSessionConfigBuilder;
    private TakePictureManager mTakePictureManager;

    /* renamed from: androidx.camera.core.ImageCapture$1, reason: invalid class name */
    public class AnonymousClass1 implements ImageCaptureControl {
        final /* synthetic */ ImageCapture this$0;

        public AnonymousClass1(ImageCapture r1) {
            this.this$0 = r1;
        }
    }

    public static final class Builder implements UseCaseConfig.Builder<ImageCapture, ImageCaptureConfig, Builder>, ImageOutputConfig.Builder<Builder> {
        private final MutableOptionsBundle mMutableConfig;

        public Builder() {
            this(MutableOptionsBundle.create());
        }

        private Builder(MutableOptionsBundle r4) {
            this.mMutableConfig = r4;
            Class r42 = (Class) r4.retrieveOption(TargetConfig.OPTION_TARGET_CLASS, null);
            if (r42 != null) goto L5;
        L9:
            setCaptureType(UseCaseConfigFactory.CaptureType.IMAGE_CAPTURE);
            setTargetClass(ImageCapture.class);
            return;
        L5:
            if (r42.equals(ImageCapture.class) == true) goto L9;
            Utils$$ExternalSyntheticBUOutline2.m("Invalid target class configuration for ", this, ": ", r42);
            throw null;
        }

        public static Builder fromConfig(Config r1) {
            return new Builder(MutableOptionsBundle.from(r1));
        }

        public ImageCapture build() {
            Integer r2 = (Integer) getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_BUFFER_FORMAT, null);
            if (r2 == null) goto L6;
            getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, r2);
        L15:
            ImageCaptureConfig r0 = getUseCaseConfig();
            ImageOutputConfig.validateConfig(r0);
            ImageCapture r1 = new ImageCapture(r0);
            Size r02 = (Size) getMutableConfig().retrieveOption(ImageOutputConfig.OPTION_TARGET_RESOLUTION, null);
            if (r02 == null) goto L18;
            r1.setCropAspectRatio(new Rational(r02.getWidth(), r02.getHeight()));
        L18:
            Preconditions.checkNotNull((Executor) getMutableConfig().retrieveOption(IoConfig.OPTION_IO_EXECUTOR, CameraXExecutors.ioExecutor()), "The IO executor can't be null");
            MutableConfig r03 = getMutableConfig();
            Config.Option<Integer> r22 = ImageCaptureConfig.OPTION_FLASH_MODE;
            if (r03.containsOption(r22) == false) goto L39;
            Integer r04 = (Integer) getMutableConfig().retrieveOption(r22);
            if (r04 != null) goto L23;
        L37:
            Native$$ExternalSyntheticBUOutline5.m("The flash mode is not allowed to set: ", r04);
            return null;
        L23:
            if (r04.intValue() == 0) goto L31;
            if (r04.intValue() == 1) goto L31;
            if (r04.intValue() == 3) goto L31;
            if (r04.intValue() != 2) goto L37;
        L31:
            if (r04.intValue() != 3) goto L39;
            if (getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_SCREEN_FLASH, null) != null) goto L39;
            f$$ExternalSyntheticBUOutline1.m("A ScreenFlash instance is required for FLASH_MODE_SCREEN but was not found. If value from PreviewView.getScreenFlash() is set to ImageCapture.setScreenFlash(), ensure PreviewView.setScreenFlashWindow() is invoked first.");
            return null;
        L39:
            return r1;
        L6:
            if (ImageCapture.access$000(getMutableConfig()) == false) goto L9;
            getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 32);
            goto L15
        L9:
            if (ImageCapture.access$100(getMutableConfig()) == false) goto L12;
            getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 32);
            getMutableConfig().insertOption(ImageInputConfig.OPTION_SECONDARY_INPUT_FORMAT, 256);
            goto L15
        L12:
            if (ImageCapture.access$200(getMutableConfig()) == false) goto L14;
            getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 4101);
            getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_DYNAMIC_RANGE, DynamicRange.UNSPECIFIED);
            goto L15
        L14:
            getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 256);
            goto L15
        }

        @Override // androidx.camera.core.ExtendableBuilder
        public MutableConfig getMutableConfig() {
            return this.mMutableConfig;
        }

        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        public ImageCaptureConfig getUseCaseConfig() {
            return new ImageCaptureConfig(OptionsBundle.from(this.mMutableConfig));
        }

        @Override // androidx.camera.core.impl.UseCaseConfig.Builder
        public /* bridge */ /* synthetic */ UseCaseConfig getUseCaseConfig() {
            return getUseCaseConfig();
        }

        public Builder setCaptureType(UseCaseConfigFactory.CaptureType r3) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_CAPTURE_TYPE, r3);
            return this;
        }

        public Builder setDynamicRange(DynamicRange r3) {
            getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_DYNAMIC_RANGE, r3);
            return this;
        }

        public Builder setOutputFormat(int r3) {
            getMutableConfig().insertOption(ImageCaptureConfig.OPTION_OUTPUT_FORMAT, Integer.valueOf(r3));
            return this;
        }

        public Builder setResolutionSelector(ResolutionSelector r3) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_RESOLUTION_SELECTOR, r3);
            return this;
        }

        public Builder setStreamUseCase(StreamUseCase r3) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_STREAM_USE_CASE, r3);
            return this;
        }

        public Builder setSurfaceOccupancyPriority(int r3) {
            getMutableConfig().insertOption(UseCaseConfig.OPTION_SURFACE_OCCUPANCY_PRIORITY, Integer.valueOf(r3));
            return this;
        }

        @Deprecated
        public Builder setTargetAspectRatio(int r3) {
            if (r3 != (-1)) goto L5;
            r3 = 0;
        L5:
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_TARGET_ASPECT_RATIO, Integer.valueOf(r3));
            return this;
        }

        public Builder setTargetClass(Class<ImageCapture> r4) {
            getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_CLASS, r4);
            if (getMutableConfig().retrieveOption(TargetConfig.OPTION_TARGET_NAME, null) != null) goto L5;
            setTargetName(r4.getCanonicalName() + "-" + UUID.randomUUID());
        L5:
            return this;
        }

        public Builder setTargetName(String r3) {
            getMutableConfig().insertOption(TargetConfig.OPTION_TARGET_NAME, r3);
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        @Deprecated
        public Builder setTargetResolution(Size r3) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_TARGET_RESOLUTION, r3);
            return this;
        }

        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        @Deprecated
        public /* bridge */ /* synthetic */ Builder setTargetResolution(Size r1) {
            return setTargetResolution(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        public Builder setTargetRotation(int r3) {
            getMutableConfig().insertOption(ImageOutputConfig.OPTION_TARGET_ROTATION, Integer.valueOf(r3));
            return this;
        }

        @Override // androidx.camera.core.impl.ImageOutputConfig.Builder
        public /* bridge */ /* synthetic */ Builder setTargetRotation(int r1) {
            return setTargetRotation(r1);
        }
    }

    public static final class Defaults {
        private static final ImageCaptureConfig DEFAULT_CONFIG = null;
        private static final DynamicRange DEFAULT_DYNAMIC_RANGE = null;
        private static final ResolutionSelector DEFAULT_RESOLUTION_SELECTOR = null;
        private static final StreamUseCase DEFAULT_STREAM_USE_CASE = null;

        static {
            StreamUseCase r0 = StreamUseCase.STILL_CAPTURE;
            DEFAULT_STREAM_USE_CASE = r0;
            ResolutionSelector r1 = new ResolutionSelector.Builder().setAspectRatioStrategy(AspectRatioStrategy.RATIO_4_3_FALLBACK_AUTO_STRATEGY).setResolutionStrategy(ResolutionStrategy.HIGHEST_AVAILABLE_STRATEGY).build();
            DEFAULT_RESOLUTION_SELECTOR = r1;
            DynamicRange r2 = DynamicRange.SDR;
            DEFAULT_DYNAMIC_RANGE = r2;
            DEFAULT_CONFIG = new Builder().setSurfaceOccupancyPriority(4).setStreamUseCase(r0).setTargetAspectRatio(0).setResolutionSelector(r1).setOutputFormat(0).setDynamicRange(r2).getUseCaseConfig();
        }

        public Defaults() {
        }

        public ImageCaptureConfig getConfig() {
            return DEFAULT_CONFIG;
        }
    }

    public static class ImageCaptureCapabilitiesImpl implements ImageCaptureCapabilities {
        private final CameraInfo mCameraInfo;

        public ImageCaptureCapabilitiesImpl(CameraInfo r1) {
            this.mCameraInfo = r1;
        }

        private Set<Integer> getSupportedOutputFormatsFromAdapterCameraInfo() {
            CameraInfo r4 = this.mCameraInfo;
            HashSet r1 = null;
            if ((r4 instanceof AdapterCameraInfo) == true) goto L5;
            return null;
        L5:
            Config r42 = ((AdapterCameraInfo) r4).getCameraConfig().getUseCaseConfigFactory().getConfig(UseCaseConfigFactory.CaptureType.IMAGE_CAPTURE, 1);
            if (r42 == null) goto L16;
            Config.Option<List<Pair<Integer, Size[]>>> r0 = ImageOutputConfig.OPTION_SUPPORTED_RESOLUTIONS;
            if (r42.containsOption(r0) == false) goto L16;
            r1 = new HashSet();
            r1.add(0);
            Iterator r43 = ((List) r42.retrieveOption(r0)).iterator();
        L12:
            if (r43.hasNext() == false) goto L16;
            if (((Integer) ((Pair) r43.next()).first).intValue() != 4101) goto L12;
            r1.add(1);
        L16:
            return r1;
        }

        private boolean isRawSupported() {
            CameraInfo r3 = this.mCameraInfo;
            if ((r3 instanceof CameraInfoInternal) == false) goto L9;
            CameraInfoInternal r32 = (CameraInfoInternal) r3;
            if (r32.getAvailableCapabilities().contains(3) == true) goto L8;
            return false;
        L8:
            return r32.getSupportedOutputFormats().contains(32);
        L9:
            return false;
        }

        private boolean isUltraHdrSupported() {
            CameraInfo r1 = this.mCameraInfo;
            if ((r1 instanceof CameraInfoInternal) == true) goto L5;
            return false;
        L5:
            return ((CameraInfoInternal) r1).getSupportedOutputFormats().contains(4101);
        }

        @Override // androidx.camera.core.ImageCaptureCapabilities
        public Set<Integer> getSupportedOutputFormats() {
            Set<Integer> r0 = getSupportedOutputFormatsFromAdapterCameraInfo();
            if (r0 == null) goto L5;
            return r0;
        L5:
            HashSet r02 = new HashSet();
            r02.add(0);
            if (isUltraHdrSupported() == false) goto L9;
            r02.add(1);
        L9:
            if (isRawSupported() == false) goto L11;
            r02.add(2);
            r02.add(3);
        L11:
            return r02;
        }
    }

    public static final class OutputFileOptions {
    }

    public static class OutputFileResults {
        private final int mImageFormat;
        private final Uri mSavedUri;

        public OutputFileResults(Uri r1, int r2) {
            this.mSavedUri = r1;
            this.mImageFormat = r2;
        }
    }

    public interface ScreenFlash {
        void apply(long r1, ScreenFlashListener r3);

        void clear();
    }

    public interface ScreenFlashListener {
        void onCompleted();
    }

    public static /* synthetic */ void $r8$lambda$3PPYv7aM85CQohSH7HAYGLoL3uo(ImageCapture r1, androidx.camera.core.impl.SessionConfig r2, SessionConfig.SessionError r3) {
        if (r1.getCamera() != null) goto L5;
        return;
    L5:
        r1.mTakePictureManager.pause();
        r1.clearPipeline(true);
        SessionConfig.Builder r22 = r1.createPipeline(r1.getCameraId(), (ImageCaptureConfig) r1.getCurrentConfig(), (StreamSpec) Preconditions.checkNotNull(r1.getAttachedStreamSpec()));
        r1.mSessionConfigBuilder = r22;
        r1.updateSessionConfig(ImageCapture$$ExternalSyntheticBackport0.m(r22.build()));
        r1.notifyReset();
        r1.mTakePictureManager.resume();
    }

    public static /* synthetic */ void $r8$lambda$EdX94iwOLUWR62CxNRlObVsL3Gk(ImageReaderProxy r3) {
        ImageProxy r32 = r3.acquireLatestImage();     // Catch: IllegalStateException -> L8
        Log.d("ImageCapture", "Discarding ImageProxy which was inadvertently acquired: " + r32);     // Catch: Throwable -> L11
        if (r32 == null) goto L10;
        r32.close();     // Catch: IllegalStateException -> L8
        return;
    L10:
        return;
    L11:
        th = move-exception;
        if (r32 != null) goto L22;
    L17:
        throw th;     // Catch: IllegalStateException -> L8
    L22:
        r32.close();     // Catch: Throwable -> L15
    L15:
        th = move-exception;
        th.addSuppressed(th);     // Catch: IllegalStateException -> L8
    L8:
        e = move-exception;
        Log.e("ImageCapture", "Failed to acquire latest image.", e);
    }

    static {
        DEFAULT_CONFIG = new Defaults();
        EXIF_ROTATION_AVAILABILITY = new ExifRotationAvailability();
    }

    public ImageCapture(ImageCaptureConfig r2) {
        super(r2);
        this.mClosingListener = new ImageCapture$$ExternalSyntheticLambda2();
        this.mLockedFlashMode = new AtomicReference(null);
        this.mFlashMode = -1;
        this.mCropAspectRatio = null;
        this.mImageCaptureControl = new AnonymousClass1(this);
        ImageCaptureConfig r22 = (ImageCaptureConfig) getCurrentConfig();
        if (r22.containsOption(ImageCaptureConfig.OPTION_IMAGE_CAPTURE_MODE) == false) goto L5;
        this.mCaptureMode = r22.getCaptureMode();
    L6:
        this.mFlashType = r22.getFlashType(0);
        this.mScreenFlashWrapper = ScreenFlashWrapper.from(r22.getScreenFlash());
        return;
    L5:
        this.mCaptureMode = 1;
        goto L6
    }

    private void abortImageCaptureRequests() {
        this.mScreenFlashWrapper.completePendingTasks();
        TakePictureManager r1 = this.mTakePictureManager;
        if (r1 == null) goto L6;
        r1.abortRequests();
        return;
    }

    public static /* synthetic */ boolean access$000(MutableConfig r0) {
        return isOutputFormatRaw(r0);
    }

    public static /* synthetic */ boolean access$100(MutableConfig r0) {
        return isOutputFormatRawJpeg(r0);
    }

    public static /* synthetic */ boolean access$200(MutableConfig r0) {
        return isOutputFormatUltraHdr(r0);
    }

    private void applyFeatureGroupToConfig(UseCaseConfig.Builder<?, ?, ?> r4) {
        Set<GroupableFeature> r3 = getFeatureGroup();
        if (r3 == null) goto L17;
        Iterator<GroupableFeature> r32 = r3.iterator();
        int r0 = 0;
    L6:
        if (r32.hasNext() == false) goto L10;
        GroupableFeature r1 = r32.next();
        if ((r1 instanceof ImageFormatFeature) == false) goto L6;
        r0 = ((ImageFormatFeature) r1).getImageCaptureOutputFormat();
        goto L6
    L10:
        r4.getMutableConfig().insertOption(ImageCaptureConfig.OPTION_OUTPUT_FORMAT, Integer.valueOf(r0));
        return;
    }

    private PostviewSettings calculatePostviewSettings(int r1, Size r2) {
        getSessionProcessor();
        return null;
    }

    private void clearPipeline() {
        clearPipeline(false);
    }

    private void clearPipeline(boolean r3) {
        Log.d("ImageCapture", "clearPipeline");
        Threads.checkMainThread();
        SessionConfig.CloseableErrorListener r0 = this.mCloseableErrorListener;
        if (r0 == null) goto L5;
        r0.close();
        this.mCloseableErrorListener = null;
    L5:
        ImagePipeline r02 = this.mImagePipeline;
        if (r02 == null) goto L8;
        r02.close();
        this.mImagePipeline = null;
    L8:
        if (r3 == true) goto L12;
        TakePictureManager r32 = this.mTakePictureManager;
        if (r32 == null) goto L12;
        r32.abortRequests();
        this.mTakePictureManager = null;
    L12:
        getCameraControl().clearZslConfig();
    }

    private SessionConfig.Builder createPipeline(String r10, ImageCaptureConfig r11, StreamSpec r12) {
        Threads.checkMainThread();
        Log.d("ImageCapture", String.format("createPipeline(cameraId: %s, streamSpec: %s)", new Object[]{r10, r12}));
        Size r4 = r12.getResolution();
        CameraInternal r102 = getCamera();
        Objects.requireNonNull(r102);
        boolean r7 = !r102.getHasTransform();
        if (this.mImagePipeline == null) goto L5;
        Preconditions.checkState(r7);
        this.mImagePipeline.close();
    L5:
        Set<Integer> r103 = getImageCaptureCapabilities(getCamera().getCameraInfo()).getSupportedOutputFormats();
        Preconditions.checkArgument(r103.contains(Integer.valueOf(getOutputFormat())), "The specified output format (" + getOutputFormat() + ") is not supported by current configuration. Supported output formats: " + r103);
        CameraCharacteristics r2 = null;
        if (isPostviewEnabled() == false) goto L8;
        PostviewSettings r8 = calculatePostviewSettings(r11.getInputFormat(), r4);
    L10:
        if (getCamera() != null) goto L35;
    L17:
        CameraCharacteristics r5 = r2;
        getEffect();
        this.mImagePipeline = new ImagePipeline(r11, r4, r5, null, r7, r8);
        if (this.mTakePictureManager != null) goto L22;
        this.mTakePictureManager = getCurrentConfig().getTakePictureManagerProvider().newInstance(this.mImageCaptureControl);
    L22:
        this.mTakePictureManager.setImagePipeline(this.mImagePipeline);
        SessionConfig.Builder r104 = this.mImagePipeline.createSessionConfigBuilder(r12.getResolution());
        r104.setSessionType(r12.getSessionType());
        if (getCaptureMode() != 2) goto L28;
        if (r12.getZslDisabled() == true) goto L28;
        getCameraControl().addZslConfig(r104);
    L28:
        if (r12.getImplementationOptions() == null) goto L30;
        r104.addImplementationOptions(r12.getImplementationOptions());
    L30:
        SessionConfig.CloseableErrorListener r112 = this.mCloseableErrorListener;
        if (r112 == null) goto L33;
        r112.close();
    L33:
        SessionConfig.CloseableErrorListener r113 = new SessionConfig.CloseableErrorListener(new ImageCapture$$ExternalSyntheticLambda3(this));
        this.mCloseableErrorListener = r113;
        r104.setErrorListener(r113);
        return r104;
    L35:
        Object r105 = getCamera().getCameraInfoInternal().getCameraCharacteristics();     // Catch: Exception -> L15
        if ((r105 instanceof CameraCharacteristics) == false) goto L17;
        r2 = (CameraCharacteristics) r105;     // Catch: Exception -> L15
    L15:
        e = move-exception;
        Log.e("ImageCapture", "getCameraCharacteristics failed", e);
        goto L17
    L8:
        r8 = null;
        goto L10
    }

    private int getCameraLens() {
        CameraInternal r0 = getCamera();
        if (r0 != null) goto L5;
        return -1;
    L5:
        return r0.getCameraInfo().getLensFacing();
    }

    public static ImageCaptureCapabilities getImageCaptureCapabilities(CameraInfo r1) {
        return new ImageCaptureCapabilitiesImpl(r1);
    }

    private SessionProcessor getSessionProcessor() {
        getCamera().getExtendedConfig().getSessionProcessor(null);
        return null;
    }

    private static boolean isImageFormatSupported(List<Pair<Integer, Size[]>> r3, int r4) {
        if (r3 != null) goto L5;
        return false;
    L5:
        Iterator<Pair<Integer, Size[]>> r32 = r3.iterator();
    L7:
        if (r32.hasNext() == false) goto L12;
        if (((Integer) r32.next().first).equals(Integer.valueOf(r4)) == false) goto L7;
        return true;
    L12:
        return false;
    }

    private static boolean isOutputFormatRaw(MutableConfig r2) {
        return Objects.equals(r2.retrieveOption(ImageCaptureConfig.OPTION_OUTPUT_FORMAT, null), 2);
    }

    private static boolean isOutputFormatRawJpeg(MutableConfig r2) {
        return Objects.equals(r2.retrieveOption(ImageCaptureConfig.OPTION_OUTPUT_FORMAT, null), 3);
    }

    private static boolean isOutputFormatUltraHdr(MutableConfig r2) {
        return Objects.equals(r2.retrieveOption(ImageCaptureConfig.OPTION_OUTPUT_FORMAT, null), 1);
    }

    private boolean isSessionProcessorEnabledInCurrentCamera() {
        if (getCamera() != null) goto L5;
        return false;
    L5:
        getCamera().getExtendedConfig().getSessionProcessor(null);
        return false;
    }

    private void setScreenFlashToCameraControl() {
        setScreenFlashToCameraControl(this.mScreenFlashWrapper);
    }

    private void setScreenFlashToCameraControl(ScreenFlash r1) {
        getCameraControl().setScreenFlash(r1);
    }

    private void trySetFlashModeToCameraControl() {
        AtomicReference<Integer> r0 = this.mLockedFlashMode;
        monitor-enter(r0);
    L8:
        th = move-exception;
        throw th;
    L5:
        if (this.mLockedFlashMode.get() == null) goto L10;
        monitor-exit(r0);     // Catch: Throwable -> L8
        return;
    L10:
        getCameraControl().setFlashMode(getFlashMode());     // Catch: Throwable -> L8
        monitor-exit(r0);     // Catch: Throwable -> L8
    }

    public boolean enforceSoftwareJpegConstraints(MutableConfig r7) {
        Boolean r0 = Boolean.TRUE;
        Config.Option<Boolean> r1 = ImageCaptureConfig.OPTION_USE_SOFTWARE_JPEG_ENCODER;
        Boolean r2 = Boolean.FALSE;
        boolean r3 = false;
        if (r0.equals(r7.retrieveOption(r1, r2)) == true) goto L5;
    L16:
        return r3;
    L5:
        if (isSessionProcessorEnabledInCurrentCamera() == false) goto L7;
        Logger.w("ImageCapture", "Software JPEG cannot be used with Extensions.");
        boolean r6 = false;
    L8:
        Integer r4 = (Integer) r7.retrieveOption(ImageCaptureConfig.OPTION_BUFFER_FORMAT, null);
        if (r4 != null) goto L11;
    L13:
        r3 = r6;
    L14:
        if (r3 == true) goto L16;
        Logger.w("ImageCapture", "Unable to support software JPEG. Disabling.");
        r7.insertOption(r1, r2);
        goto L16
    L11:
        if (r4.intValue() == 256) goto L13;
        Logger.w("ImageCapture", "Software JPEG cannot be used with non-JPEG output buffer format.");
        goto L14
    L7:
        r6 = true;
        goto L8
    }

    public int getCaptureMode() {
        return this.mCaptureMode;
    }

    @Override // androidx.camera.core.UseCase
    public UseCaseConfig<?> getDefaultConfig(boolean r4, UseCaseConfigFactory r5) {
        Defaults r0 = DEFAULT_CONFIG;
        Config r52 = r5.getConfig(r0.getConfig().getCaptureType(), getCaptureMode());
        if (r4 == false) goto L5;
        r52 = Config.mergeConfigs(r52, r0.getConfig());
    L5:
        if (r52 != null) goto L9;
        return null;
    L9:
        return getUseCaseConfigBuilder(r52).getUseCaseConfig();
    }

    public int getFlashMode() {
        AtomicReference<Integer> r0 = this.mLockedFlashMode;
        monitor-enter(r0);
        int r1 = this.mFlashMode;     // Catch: Throwable -> L10
        if (r1 != (-1)) goto L8;
        r1 = ((ImageCaptureConfig) getCurrentConfig()).getFlashMode(2);     // Catch: Throwable -> L10
    L8:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return r1;
    L10:
        th = move-exception;
        throw th;
    }

    public int getOutputFormat() {
        return ((Integer) Preconditions.checkNotNull((Integer) getCurrentConfig().retrieveOption(ImageCaptureConfig.OPTION_OUTPUT_FORMAT, 0))).intValue();
    }

    @Override // androidx.camera.core.UseCase
    public Set<Integer> getSupportedEffectTargets() {
        HashSet r1 = new HashSet();
        r1.add(4);
        return r1;
    }

    public int getTargetRotation() {
        return getTargetRotationInternal();
    }

    @Override // androidx.camera.core.UseCase
    public UseCaseConfig.Builder<?, ?, ?> getUseCaseConfigBuilder(Config r1) {
        return Builder.fromConfig(r1);
    }

    @Override // androidx.camera.core.UseCase
    public boolean isAutoRotationSupported() {
        return true;
    }

    public boolean isPostviewEnabled() {
        return ((Boolean) getCurrentConfig().retrieveOption(ImageCaptureConfig.OPTION_POSTVIEW_ENABLED, Boolean.FALSE)).booleanValue();
    }

    @Override // androidx.camera.core.UseCase
    public void onBind() {
        Preconditions.checkNotNull(getCamera(), "Attached camera cannot be null");
        if (getFlashMode() == 3) goto L5;
        return;
    L5:
        if (getCameraLens() == 0) goto L10;
        f$$ExternalSyntheticBUOutline1.m("Not a front camera despite setting FLASH_MODE_SCREEN in ImageCapture");
        return;
    }

    @Override // androidx.camera.core.UseCase
    public void onCameraControlReady() {
        Logger.d("ImageCapture", "onCameraControlReady");
        trySetFlashModeToCameraControl();
        setScreenFlashToCameraControl();
    }

    @Override // androidx.camera.core.UseCase
    public UseCaseConfig<?> onMergeConfig(CameraInfoInternal r9, UseCaseConfig.Builder<?, ?, ?> r10) {
        int r1 = 35;
        applyFeatureGroupToConfig(r10);
        if (r9.getCameraQuirks().contains(SoftwareJpegEncodingPreferredQuirk.class) == false) goto L8;
        Boolean r92 = Boolean.FALSE;
        MutableConfig r5 = r10.getMutableConfig();
        Config.Option<Boolean> r6 = ImageCaptureConfig.OPTION_USE_SOFTWARE_JPEG_ENCODER;
        Boolean r7 = Boolean.TRUE;
        if (r92.equals(r5.retrieveOption(r6, r7)) == false) goto L7;
        Logger.w("ImageCapture", "Device quirk suggests software JPEG encoder, but it has been explicitly disabled.");
        goto L8
    L7:
        Logger.i("ImageCapture", "Requesting software JPEG due to device quirk.");
        r10.getMutableConfig().insertOption(r6, r7);
    L8:
        boolean r93 = enforceSoftwareJpegConstraints(r10.getMutableConfig());
        Integer r52 = (Integer) r10.getMutableConfig().retrieveOption(ImageCaptureConfig.OPTION_BUFFER_FORMAT, null);
        if (r52 == null) goto L23;
        if (isSessionProcessorEnabledInCurrentCamera() == true) goto L13;
    L16:
        boolean r8 = true;
    L17:
        Preconditions.checkArgument(r8, "Cannot set non-JPEG buffer format with Extensions enabled.");
        MutableConfig r82 = r10.getMutableConfig();
        Config.Option<Integer> r0 = ImageInputConfig.OPTION_INPUT_FORMAT;
        if (r93 == true) goto L21;
        r1 = r52.intValue();
    L21:
        r82.insertOption(r0, Integer.valueOf(r1));
    L43:
        return r10.getUseCaseConfig();
    L13:
        if (r52.intValue() == 256) goto L16;
        r8 = false;
        goto L17
    L23:
        if (isOutputFormatRaw(r10.getMutableConfig()) == false) goto L26;
        r10.getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 32);
        goto L43
    L26:
        if (isOutputFormatRawJpeg(r10.getMutableConfig()) == false) goto L29;
        r10.getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 32);
        r10.getMutableConfig().insertOption(ImageInputConfig.OPTION_SECONDARY_INPUT_FORMAT, 256);
        goto L43
    L29:
        if (isOutputFormatUltraHdr(r10.getMutableConfig()) == false) goto L31;
        r10.getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 4101);
        r10.getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_DYNAMIC_RANGE, DynamicRange.UNSPECIFIED);
        goto L43
    L31:
        if (r93 == false) goto L33;
        r10.getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 35);
        goto L43
    L33:
        List r83 = (List) r10.getMutableConfig().retrieveOption(ImageOutputConfig.OPTION_SUPPORTED_RESOLUTIONS, null);
        if (r83 != null) goto L37;
        r10.getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 256);
        goto L43
    L37:
        if (isImageFormatSupported(r83, 256) == false) goto L40;
        r10.getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 256);
        goto L43
    L40:
        if (isImageFormatSupported(r83, 35) == false) goto L43;
        r10.getMutableConfig().insertOption(ImageInputConfig.OPTION_INPUT_FORMAT, 35);
        goto L43
    }

    @Override // androidx.camera.core.UseCase
    public void onProviderRotationChanged(int r1) {
        setTargetRotation(r1);
    }

    @Override // androidx.camera.core.UseCase
    public void onSessionStop() {
        abortImageCaptureRequests();
    }

    @Override // androidx.camera.core.UseCase
    public StreamSpec onSuggestedStreamSpecImplementationOptionsUpdated(Config r2) {
        this.mSessionConfigBuilder.addImplementationOptions(r2);
        updateSessionConfig(ImageCapture$$ExternalSyntheticBackport0.m(this.mSessionConfigBuilder.build()));
        return getAttachedStreamSpec().toBuilder().setImplementationOptions(r2).build();
    }

    @Override // androidx.camera.core.UseCase
    public StreamSpec onSuggestedStreamSpecUpdated(StreamSpec r3, StreamSpec r4) {
        Logger.d("ImageCapture", "onSuggestedStreamSpecUpdated: primaryStreamSpec = " + r3 + ", secondaryStreamSpec " + r4);
        SessionConfig.Builder r42 = createPipeline(getCameraId(), (ImageCaptureConfig) getCurrentConfig(), r3);
        this.mSessionConfigBuilder = r42;
        updateSessionConfig(ImageCapture$$ExternalSyntheticBackport0.m(r42.build()));
        notifyActive();
        return r3;
    }

    @Override // androidx.camera.core.UseCase
    public void onUnbind() {
        abortImageCaptureRequests();
        clearPipeline();
        setScreenFlashToCameraControl(null);
    }

    public void setCropAspectRatio(Rational r1) {
        this.mCropAspectRatio = r1;
    }

    public void setTargetRotation(int r3) {
        int r0 = getTargetRotation();
        if (setTargetRotationInternal(r3) == true) goto L5;
        return;
    L5:
        if (this.mCropAspectRatio == null) goto L9;
        int r02 = CameraOrientationUtil.surfaceRotationToDegrees(r0);
        this.mCropAspectRatio = ImageUtil.getRotatedAspectRatio(Math.abs(CameraOrientationUtil.surfaceRotationToDegrees(r3) - r02), this.mCropAspectRatio);
        return;
    }

    public String toString() {
        return "ImageCapture:" + getName();
    }
}
