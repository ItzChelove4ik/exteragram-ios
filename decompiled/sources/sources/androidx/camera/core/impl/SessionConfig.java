package androidx.camera.core.impl;

import android.hardware.camera2.CameraCaptureSession;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.params.InputConfiguration;
import android.util.Range;
import android.util.Size;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.Logger;
import androidx.camera.core.impl.AutoValue_SessionConfig_OutputConfig;
import androidx.camera.core.impl.CaptureConfig;
import androidx.camera.core.internal.HighSpeedFpsModifier;
import androidx.camera.core.internal.compat.workaround.SurfaceSorter;
import c.f$$ExternalSyntheticBUOutline1;
import com.android.dx.dex.code.CstInsn$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

/* loaded from: classes3.dex */
public final class SessionConfig {
    private static final List<Integer> SUPPORTED_TEMPLATE_PRIORITY = null;
    private final List<CameraDevice.StateCallback> mDeviceStateCallbacks;
    private final ErrorListener mErrorListener;
    private InputConfiguration mInputConfiguration;
    private final List<OutputConfig> mOutputConfigs;
    private final OutputConfig mPostviewOutputConfig;
    private final CaptureConfig mRepeatingCaptureConfig;
    private final List<CameraCaptureSession.StateCallback> mSessionStateCallbacks;
    private final int mSessionType;
    private final List<CameraCaptureCallback> mSingleCameraCaptureCallbacks;

    public static class BaseBuilder {
        final CaptureConfig.Builder mCaptureConfigBuilder;
        final List<CameraDevice.StateCallback> mDeviceStateCallbacks;
        ErrorListener mErrorListener;
        InputConfiguration mInputConfiguration;
        final Set<OutputConfig> mOutputConfigs;
        OutputConfig mPostviewOutputConfig;
        final List<CameraCaptureSession.StateCallback> mSessionStateCallbacks;
        int mSessionType;
        final List<CameraCaptureCallback> mSingleCameraCaptureCallbacks;

        public BaseBuilder() {
            this.mOutputConfigs = new LinkedHashSet();
            this.mCaptureConfigBuilder = new CaptureConfig.Builder();
            this.mDeviceStateCallbacks = new ArrayList();
            this.mSessionStateCallbacks = new ArrayList();
            this.mSingleCameraCaptureCallbacks = new ArrayList();
            this.mSessionType = 0;
        }
    }

    public static class Builder extends BaseBuilder {
        public Builder() {
        }

        public static Builder createFrom(UseCaseConfig<?> r2, Size r3) {
            OptionUnpacker r1 = r2.getSessionOptionUnpacker(null);
            if (r1 == null) goto L6;
            Builder r0 = new Builder();
            r1.unpack(r3, r2, r0);
            return r0;
        L6:
            CstInsn$$ExternalSyntheticBUOutline0.m("Implementation is missing option unpacker for ", r2.getTargetName(r2.toString()));
            return null;
        }

        public Builder addAllCameraCaptureCallbacks(Collection<CameraCaptureCallback> r3) {
            Iterator<CameraCaptureCallback> r32 = r3.iterator();
        L4:
            if (r32.hasNext() == false) goto L8;
            CameraCaptureCallback r0 = r32.next();
            this.mCaptureConfigBuilder.addCameraCaptureCallback(r0);
            if (this.mSingleCameraCaptureCallbacks.contains(r0) == true) goto L4;
            this.mSingleCameraCaptureCallbacks.add(r0);
            goto L4
        L8:
            return this;
        }

        public Builder addAllDeviceStateCallbacks(Collection<CameraDevice.StateCallback> r2) {
            Iterator<CameraDevice.StateCallback> r22 = r2.iterator();
        L4:
            if (r22.hasNext() == false) goto L6;
            addDeviceStateCallback(r22.next());
            goto L4
        L6:
            return this;
        }

        public Builder addAllRepeatingCameraCaptureCallbacks(Collection<CameraCaptureCallback> r2) {
            this.mCaptureConfigBuilder.addAllCameraCaptureCallbacks(r2);
            return this;
        }

        public Builder addAllSessionStateCallbacks(List<CameraCaptureSession.StateCallback> r2) {
            Iterator<CameraCaptureSession.StateCallback> r22 = r2.iterator();
        L4:
            if (r22.hasNext() == false) goto L6;
            addSessionStateCallback(r22.next());
            goto L4
        L6:
            return this;
        }

        public Builder addCameraCaptureCallback(CameraCaptureCallback r2) {
            this.mCaptureConfigBuilder.addCameraCaptureCallback(r2);
            if (this.mSingleCameraCaptureCallbacks.contains(r2) == true) goto L5;
            this.mSingleCameraCaptureCallbacks.add(r2);
        L5:
            return this;
        }

        public Builder addDeviceStateCallback(CameraDevice.StateCallback r2) {
            if (this.mDeviceStateCallbacks.contains(r2) == false) goto L5;
            return this;
        L5:
            this.mDeviceStateCallbacks.add(r2);
            return this;
        }

        public Builder addImplementationOptions(Config r2) {
            this.mCaptureConfigBuilder.addImplementationOptions(r2);
            return this;
        }

        public Builder addNonRepeatingSurface(DeferrableSurface r2) {
            return addNonRepeatingSurface(r2, DynamicRange.SDR);
        }

        public Builder addNonRepeatingSurface(DeferrableSurface r1, DynamicRange r2) {
            OutputConfig r12 = OutputConfig.builder(r1).setDynamicRange(r2).build();
            this.mOutputConfigs.add(r12);
            return this;
        }

        public Builder addRepeatingCameraCaptureCallback(CameraCaptureCallback r2) {
            this.mCaptureConfigBuilder.addCameraCaptureCallback(r2);
            return this;
        }

        public Builder addSessionStateCallback(CameraCaptureSession.StateCallback r2) {
            if (this.mSessionStateCallbacks.contains(r2) == false) goto L5;
            return this;
        L5:
            this.mSessionStateCallbacks.add(r2);
            return this;
        }

        public Builder addSurface(DeferrableSurface r2) {
            return addSurface(r2, DynamicRange.SDR);
        }

        public Builder addSurface(DeferrableSurface r3, DynamicRange r4) {
            return addSurface(r3, r4, null, -1);
        }

        public Builder addSurface(DeferrableSurface r2, DynamicRange r3, String r4, int r5) {
            OutputConfig r32 = OutputConfig.builder(r2).setPhysicalCameraId(r4).setDynamicRange(r3).setMirrorMode(r5).build();
            this.mOutputConfigs.add(r32);
            this.mCaptureConfigBuilder.addSurface(r2);
            return this;
        }

        public SessionConfig build() {
            return new SessionConfig(new ArrayList(this.mOutputConfigs), new ArrayList(this.mDeviceStateCallbacks), new ArrayList(this.mSessionStateCallbacks), new ArrayList(this.mSingleCameraCaptureCallbacks), this.mCaptureConfigBuilder.build(), this.mErrorListener, this.mInputConfiguration, this.mSessionType, this.mPostviewOutputConfig);
        }

        public Builder setErrorListener(ErrorListener r1) {
            this.mErrorListener = r1;
            return this;
        }

        public Builder setExpectedFrameRateRange(Range<Integer> r2) {
            this.mCaptureConfigBuilder.setExpectedFrameRateRange(r2);
            return this;
        }

        public Builder setImplementationOptions(Config r2) {
            this.mCaptureConfigBuilder.setImplementationOptions(r2);
            return this;
        }

        public Builder setInputConfiguration(InputConfiguration r1) {
            this.mInputConfiguration = r1;
            return this;
        }

        public Builder setPostviewSurface(DeferrableSurface r1) {
            this.mPostviewOutputConfig = OutputConfig.builder(r1).build();
            return this;
        }

        public Builder setPreviewStabilization(int r2) {
            if (r2 == 0) goto L4;
            this.mCaptureConfigBuilder.setPreviewStabilization(r2);
        L4:
            return this;
        }

        public Builder setSessionType(int r1) {
            this.mSessionType = r1;
            return this;
        }

        public Builder setTemplateType(int r2) {
            this.mCaptureConfigBuilder.setTemplateType(r2);
            return this;
        }

        public Builder setVideoStabilization(int r2) {
            if (r2 == 0) goto L4;
            this.mCaptureConfigBuilder.setVideoStabilization(r2);
        L4:
            return this;
        }
    }

    public static final class CloseableErrorListener implements ErrorListener {
        private final ErrorListener mErrorListener;
        private final AtomicBoolean mIsClosed;

        public CloseableErrorListener(ErrorListener r3) {
            this.mIsClosed = new AtomicBoolean(false);
            this.mErrorListener = r3;
        }

        public void close() {
            this.mIsClosed.set(true);
        }

        @Override // androidx.camera.core.impl.SessionConfig.ErrorListener
        public void onError(SessionConfig r2, SessionError r3) {
            if (this.mIsClosed.get() == true) goto L6;
            this.mErrorListener.onError(r2, r3);
            return;
        }
    }

    public interface ErrorListener {
        void onError(SessionConfig r1, SessionError r2);
    }

    public interface OptionUnpacker {
        void unpack(Size r1, UseCaseConfig<?> r2, Builder r3);
    }

    public static abstract class OutputConfig {

        public static abstract class Builder {
            public Builder() {
            }

            public abstract OutputConfig build();

            public abstract Builder setDynamicRange(DynamicRange r1);

            public abstract Builder setMirrorMode(int r1);

            public abstract Builder setPhysicalCameraId(String r1);

            public abstract Builder setSharedSurfaces(List<DeferrableSurface> r1);

            public abstract Builder setSurfaceGroupId(int r1);
        }

        public OutputConfig() {
        }

        public static Builder builder(DeferrableSurface r1) {
            return new AutoValue_SessionConfig_OutputConfig.Builder().setSurface(r1).setSharedSurfaces(Collections.EMPTY_LIST).setPhysicalCameraId(null).setMirrorMode(-1).setSurfaceGroupId(-1).setDynamicRange(DynamicRange.SDR);
        }

        public abstract DynamicRange getDynamicRange();

        public abstract int getMirrorMode();

        public abstract String getPhysicalCameraId();

        public abstract List<DeferrableSurface> getSharedSurfaces();

        public abstract DeferrableSurface getSurface();

        public abstract int getSurfaceGroupId();
    }

    public enum SessionError extends Enum<SessionError> {
        private static final /* synthetic */ SessionError[] $VALUES = null;
        public static final SessionError SESSION_ERROR_SURFACE_NEEDS_RESET = null;
        public static final SessionError SESSION_ERROR_UNKNOWN = null;

        private static /* synthetic */ SessionError[] $values() {
            return new SessionError[]{SESSION_ERROR_SURFACE_NEEDS_RESET, SESSION_ERROR_UNKNOWN};
        }

        static {
            SESSION_ERROR_SURFACE_NEEDS_RESET = new SessionError("SESSION_ERROR_SURFACE_NEEDS_RESET", 0);
            SESSION_ERROR_UNKNOWN = new SessionError("SESSION_ERROR_UNKNOWN", 1);
            $VALUES = $values();
        }

        SessionError(String r1, int r2) {
        }

        public static SessionError valueOf(String r1) {
            return (SessionError) Enum.valueOf(SessionError.class, r1);
        }

        public static SessionError[] values() {
            return (SessionError[]) $VALUES.clone();
        }
    }

    public static final class ValidatingBuilder extends BaseBuilder {
        private List<ErrorListener> mErrorListeners;
        private StringBuilder mInvalidReason;
        private final SurfaceSorter mSurfaceSorter;
        private boolean mTemplateSet;
        private boolean mValid;

        public static /* synthetic */ void $r8$lambda$g6w8i8RYT_XDqkMVE6hhW4VoCdQ(ValidatingBuilder r1, SessionConfig r2, SessionError r3) {
            Iterator<ErrorListener> r12 = r1.mErrorListeners.iterator();
        L4:
            if (r12.hasNext() == false) goto L6;
            r12.next().onError(r2, r3);
            goto L4
        }

        public ValidatingBuilder() {
            this.mSurfaceSorter = new SurfaceSorter();
            this.mValid = true;
            this.mInvalidReason = new StringBuilder();
            this.mTemplateSet = false;
            this.mErrorListeners = new ArrayList();
        }

        private List<DeferrableSurface> getSurfaces() {
            ArrayList r0 = new ArrayList();
            Iterator<OutputConfig> r3 = this.mOutputConfigs.iterator();
        L4:
            if (r3.hasNext() == false) goto L9;
            OutputConfig r1 = r3.next();
            r0.add(r1.getSurface());
            Iterator<DeferrableSurface> r12 = r1.getSharedSurfaces().iterator();
        L7:
            if (r12.hasNext() == false) goto L4;
            r0.add(r12.next());
            goto L7
        L9:
            return r0;
        }

        private void setOrVerifyExpectFrameRateRange(Range<Integer> r3) {
            Range<Integer> r0 = StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED;
            if (r3.equals(r0) == true) goto L14;
            boolean r02 = this.mCaptureConfigBuilder.getExpectedFrameRateRange().equals(r0);
            CaptureConfig.Builder r1 = this.mCaptureConfigBuilder;
            if (r02 == false) goto L10;
            r1.setExpectedFrameRateRange(r3);
            return;
        L10:
            if (r1.getExpectedFrameRateRange().equals(r3) == true) goto L13;
            this.mValid = false;
            String r32 = "Different ExpectedFrameRateRange values; current = " + this.mCaptureConfigBuilder.getExpectedFrameRateRange() + ", new = " + r3;
            Logger.e("ValidatingBuilder", r32);
            this.mInvalidReason.append(r32);
            return;
        L13:
            return;
        }

        private void setPreviewStabilizationMode(int r1) {
            if (r1 == 0) goto L5;
            this.mCaptureConfigBuilder.setPreviewStabilization(r1);
            return;
        }

        private void setVideoStabilizationMode(int r1) {
            if (r1 == 0) goto L5;
            this.mCaptureConfigBuilder.setVideoStabilization(r1);
            return;
        }

        public void add(SessionConfig r6) {
            CaptureConfig r0 = r6.getRepeatingCaptureConfig();
            if (r0.getTemplateType() == (-1)) goto L5;
            this.mTemplateSet = true;
            this.mCaptureConfigBuilder.setTemplateType(SessionConfig.getHigherPriorityTemplateType(r0.getTemplateType(), this.mCaptureConfigBuilder.getTemplateType()));
        L5:
            setOrVerifyExpectFrameRateRange(r0.getExpectedFrameRateRange());
            setPreviewStabilizationMode(r0.getPreviewStabilizationMode());
            setVideoStabilizationMode(r0.getVideoStabilizationMode());
            TagBundle r1 = r6.getRepeatingCaptureConfig().getTagBundle();
            this.mCaptureConfigBuilder.addAllTags(r1);
            this.mDeviceStateCallbacks.addAll(r6.getDeviceStateCallbacks());
            this.mSessionStateCallbacks.addAll(r6.getSessionStateCallbacks());
            this.mCaptureConfigBuilder.addAllCameraCaptureCallbacks(r6.getRepeatingCameraCaptureCallbacks());
            this.mSingleCameraCaptureCallbacks.addAll(r6.getSingleCameraCaptureCallbacks());
            if (r6.getErrorListener() == null) goto L9;
            this.mErrorListeners.add(r6.getErrorListener());
        L9:
            if (r6.getInputConfiguration() == null) goto L11;
            this.mInputConfiguration = r6.getInputConfiguration();
        L11:
            this.mOutputConfigs.addAll(r6.getOutputConfigs());
            this.mCaptureConfigBuilder.getSurfaces().addAll(r0.getSurfaces());
            if (getSurfaces().containsAll(this.mCaptureConfigBuilder.getSurfaces()) == true) goto L15;
            Logger.d("ValidatingBuilder", "Invalid configuration due to capture request surfaces are not a subset of surfaces");
            this.mValid = false;
            this.mInvalidReason.append("Invalid configuration due to capture request surfaces are not a subset of surfaces");
        L15:
            if (r6.getSessionType() == this.mSessionType) goto L22;
            if (r6.getSessionType() == 0) goto L22;
            if (this.mSessionType == 0) goto L22;
            Logger.d("ValidatingBuilder", "Invalid configuration due to that two non-default session types are set");
            this.mValid = false;
            this.mInvalidReason.append("Invalid configuration due to that two non-default session types are set");
        L25:
            if (SessionConfig.access$000(r6) != null) goto L27;
        L32:
            this.mCaptureConfigBuilder.addImplementationOptions(r0.getImplementationOptions());
            return;
        L27:
            if (this.mPostviewOutputConfig != SessionConfig.access$000(r6)) goto L29;
        L31:
            this.mPostviewOutputConfig = SessionConfig.access$000(r6);
            goto L32
        L29:
            if (this.mPostviewOutputConfig == null) goto L31;
            Logger.d("ValidatingBuilder", "Invalid configuration due to that two different postview output configs are set");
            this.mValid = false;
            this.mInvalidReason.append("Invalid configuration due to that two different postview output configs are set");
        L22:
            if (r6.getSessionType() == 0) goto L25;
            this.mSessionType = r6.getSessionType();
            goto L25
        }

        public SessionConfig build() {
            if (this.mValid == false) goto L14;
            ArrayList r2 = new ArrayList(this.mOutputConfigs);
            this.mSurfaceSorter.sort(r2);
            if (this.mSessionType != 1) goto L8;
            new HighSpeedFpsModifier().modifyFpsForPreviewOnlyRepeating(r2, this.mCaptureConfigBuilder);
        L8:
            if (this.mErrorListeners.isEmpty() == true) goto L11;
            SessionConfig$ValidatingBuilder$$ExternalSyntheticLambda0 r0 = new SessionConfig$ValidatingBuilder$$ExternalSyntheticLambda0(this);
        L10:
            SessionConfig$ValidatingBuilder$$ExternalSyntheticLambda0 r7 = r0;
            return new SessionConfig(r2, new ArrayList(this.mDeviceStateCallbacks), new ArrayList(this.mSessionStateCallbacks), new ArrayList(this.mSingleCameraCaptureCallbacks), this.mCaptureConfigBuilder.build(), r7, this.mInputConfiguration, this.mSessionType, this.mPostviewOutputConfig);
        L11:
            r0 = null;
            goto L10
        L14:
            f$$ExternalSyntheticBUOutline1.m("Unsupported session configuration combination");
            return null;
        }

        public String getInvalidReason() {
            if (this.mTemplateSet == true) goto L7;
            return "Template is not set";
        L7:
            return this.mInvalidReason.toString();
        }

        public boolean isValid() {
            if (this.mTemplateSet == true) goto L5;
            return false;
        L5:
            if (this.mValid == false) goto L10;
            return true;
        L10:
            return false;
        }
    }

    static {
        SUPPORTED_TEMPLATE_PRIORITY = Arrays.asList(new Integer[]{1, 5, 3});
    }

    public SessionConfig(List<OutputConfig> r1, List<CameraDevice.StateCallback> r2, List<CameraCaptureSession.StateCallback> r3, List<CameraCaptureCallback> r4, CaptureConfig r5, ErrorListener r6, InputConfiguration r7, int r8, OutputConfig r9) {
        this.mOutputConfigs = r1;
        this.mDeviceStateCallbacks = Collections.unmodifiableList(r2);
        this.mSessionStateCallbacks = Collections.unmodifiableList(r3);
        this.mSingleCameraCaptureCallbacks = Collections.unmodifiableList(r4);
        this.mErrorListener = r6;
        this.mRepeatingCaptureConfig = r5;
        this.mInputConfiguration = r7;
        this.mSessionType = r8;
        this.mPostviewOutputConfig = r9;
    }

    public static /* synthetic */ OutputConfig access$000(SessionConfig r0) {
        return r0.mPostviewOutputConfig;
    }

    public static SessionConfig defaultEmptySessionConfig() {
        return new SessionConfig(new ArrayList(), new ArrayList(0), new ArrayList(0), new ArrayList(0), new CaptureConfig.Builder().build(), null, null, 0, null);
    }

    public static int getHigherPriorityTemplateType(int r3, int r4) {
        List<Integer> r0 = SUPPORTED_TEMPLATE_PRIORITY;
        if (r0.indexOf(Integer.valueOf(r3)) < r0.indexOf(Integer.valueOf(r4))) goto L5;
        return r3;
    L5:
        return r4;
    }

    public List<CameraDevice.StateCallback> getDeviceStateCallbacks() {
        return this.mDeviceStateCallbacks;
    }

    public ErrorListener getErrorListener() {
        return this.mErrorListener;
    }

    public Range<Integer> getExpectedFrameRateRange() {
        return this.mRepeatingCaptureConfig.getExpectedFrameRateRange();
    }

    public Config getImplementationOptions() {
        return this.mRepeatingCaptureConfig.getImplementationOptions();
    }

    public InputConfiguration getInputConfiguration() {
        return this.mInputConfiguration;
    }

    public List<OutputConfig> getOutputConfigs() {
        return this.mOutputConfigs;
    }

    public OutputConfig getPostviewOutputConfig() {
        return this.mPostviewOutputConfig;
    }

    public List<CameraCaptureCallback> getRepeatingCameraCaptureCallbacks() {
        return this.mRepeatingCaptureConfig.getCameraCaptureCallbacks();
    }

    public CaptureConfig getRepeatingCaptureConfig() {
        return this.mRepeatingCaptureConfig;
    }

    public List<CameraCaptureSession.StateCallback> getSessionStateCallbacks() {
        return this.mSessionStateCallbacks;
    }

    public int getSessionType() {
        return this.mSessionType;
    }

    public List<CameraCaptureCallback> getSingleCameraCaptureCallbacks() {
        return this.mSingleCameraCaptureCallbacks;
    }

    public List<DeferrableSurface> getSurfaces() {
        ArrayList r0 = new ArrayList();
        Iterator<OutputConfig> r3 = this.mOutputConfigs.iterator();
    L4:
        if (r3.hasNext() == false) goto L10;
        OutputConfig r1 = r3.next();
        r0.add(r1.getSurface());
        Iterator<DeferrableSurface> r12 = r1.getSharedSurfaces().iterator();
    L7:
        if (r12.hasNext() == false) goto L4;
        r0.add(r12.next());
        goto L7
    L10:
        return Collections.unmodifiableList(r0);
    }

    public int getTemplateType() {
        return this.mRepeatingCaptureConfig.getTemplateType();
    }
}
