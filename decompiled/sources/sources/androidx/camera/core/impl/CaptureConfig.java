package androidx.camera.core.impl;

import android.util.Range;
import androidx.camera.core.impl.Config;
import com.android.dx.dex.code.CstInsn$$ExternalSyntheticBUOutline0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/* loaded from: classes3.dex */
public final class CaptureConfig {
    public static final Config.Option<Integer> OPTION_JPEG_QUALITY = null;
    private static final Config.Option<Range<Integer>> OPTION_RESOLVED_FRAME_RATE = null;
    public static final Config.Option<Integer> OPTION_ROTATION = null;
    final List<CameraCaptureCallback> mCameraCaptureCallbacks;
    private final CameraCaptureResult mCameraCaptureResult;
    final Config mImplementationOptions;
    final boolean mPostviewEnabled;
    final List<DeferrableSurface> mSurfaces;
    private final TagBundle mTagBundle;
    final int mTemplateType;
    private final boolean mUseRepeatingSurface;

    public static final class Builder {
        private List<CameraCaptureCallback> mCameraCaptureCallbacks;
        private CameraCaptureResult mCameraCaptureResult;
        private MutableConfig mImplementationOptions;
        private MutableTagBundle mMutableTagBundle;
        private boolean mPostviewEnabled;
        private final Set<DeferrableSurface> mSurfaces;
        private int mTemplateType;
        private boolean mUseRepeatingSurface;

        public Builder() {
            this.mSurfaces = new HashSet();
            this.mImplementationOptions = MutableOptionsBundle.create();
            this.mTemplateType = -1;
            this.mPostviewEnabled = false;
            this.mCameraCaptureCallbacks = new ArrayList();
            this.mUseRepeatingSurface = false;
            this.mMutableTagBundle = MutableTagBundle.create();
        }

        public static Builder createFrom(UseCaseConfig<?> r2) {
            OptionUnpacker r1 = r2.getCaptureOptionUnpacker(null);
            if (r1 == null) goto L6;
            Builder r0 = new Builder();
            r1.unpack(r2, r0);
            return r0;
        L6:
            CstInsn$$ExternalSyntheticBUOutline0.m("Implementation is missing option unpacker for ", r2.getTargetName(r2.toString()));
            return null;
        }

        public void addAllCameraCaptureCallbacks(Collection<CameraCaptureCallback> r2) {
            Iterator<CameraCaptureCallback> r22 = r2.iterator();
        L4:
            if (r22.hasNext() == false) goto L6;
            addCameraCaptureCallback(r22.next());
            goto L4
        }

        public void addAllTags(TagBundle r1) {
            this.mMutableTagBundle.addTagBundle(r1);
        }

        public void addCameraCaptureCallback(CameraCaptureCallback r2) {
            if (this.mCameraCaptureCallbacks.contains(r2) == false) goto L5;
            return;
        L5:
            this.mCameraCaptureCallbacks.add(r2);
        }

        public <T> void addImplementationOption(Config.Option<T> r1, T r2) {
            this.mImplementationOptions.insertOption(r1, r2);
        }

        public void addImplementationOptions(Config r6) {
            Iterator<Config.Option<?>> r0 = r6.listOptions().iterator();
        L4:
            if (r0.hasNext() == false) goto L6;
            Config.Option<?> r1 = r0.next();
            this.mImplementationOptions.retrieveOption(r1, null);
            Object r2 = r6.retrieveOption(r1);
            this.mImplementationOptions.insertOption(r1, r6.getOptionPriority(r1), r2);
            goto L4
        }

        public void addSurface(DeferrableSurface r1) {
            this.mSurfaces.add(r1);
        }

        public CaptureConfig build() {
            return new CaptureConfig(new ArrayList(this.mSurfaces), OptionsBundle.from(this.mImplementationOptions), this.mTemplateType, this.mPostviewEnabled, new ArrayList(this.mCameraCaptureCallbacks), this.mUseRepeatingSurface, TagBundle.from(this.mMutableTagBundle), this.mCameraCaptureResult);
        }

        public Range<Integer> getExpectedFrameRateRange() {
            return (Range) this.mImplementationOptions.retrieveOption(CaptureConfig.access$000(), StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED);
        }

        public Set<DeferrableSurface> getSurfaces() {
            return this.mSurfaces;
        }

        public int getTemplateType() {
            return this.mTemplateType;
        }

        public void setExpectedFrameRateRange(Range<Integer> r2) {
            addImplementationOption(CaptureConfig.access$000(), r2);
        }

        public void setImplementationOptions(Config r1) {
            this.mImplementationOptions = MutableOptionsBundle.from(r1);
        }

        public void setPreviewStabilization(int r2) {
            if (r2 == 0) goto L5;
            addImplementationOption(UseCaseConfig.OPTION_PREVIEW_STABILIZATION_MODE, Integer.valueOf(r2));
            return;
        }

        public void setTemplateType(int r1) {
            this.mTemplateType = r1;
        }

        public void setUseRepeatingSurface(boolean r1) {
            this.mUseRepeatingSurface = r1;
        }

        public void setVideoStabilization(int r2) {
            if (r2 == 0) goto L5;
            addImplementationOption(UseCaseConfig.OPTION_VIDEO_STABILIZATION_MODE, Integer.valueOf(r2));
            return;
        }
    }

    public interface OptionUnpacker {
        void unpack(UseCaseConfig<?> r1, Builder r2);
    }

    static {
        OPTION_ROTATION = Config.Option.create("camerax.core.captureConfig.rotation", Integer.TYPE);
        OPTION_JPEG_QUALITY = Config.Option.create("camerax.core.captureConfig.jpegQuality", Integer.class);
        OPTION_RESOLVED_FRAME_RATE = Config.Option.create("camerax.core.captureConfig.resolvedFrameRate", Range.class);
    }

    public CaptureConfig(List<DeferrableSurface> r1, Config r2, int r3, boolean r4, List<CameraCaptureCallback> r5, boolean r6, TagBundle r7, CameraCaptureResult r8) {
        this.mSurfaces = r1;
        this.mImplementationOptions = r2;
        this.mTemplateType = r3;
        this.mCameraCaptureCallbacks = Collections.unmodifiableList(r5);
        this.mUseRepeatingSurface = r6;
        this.mTagBundle = r7;
        this.mCameraCaptureResult = r8;
        this.mPostviewEnabled = r4;
    }

    public static /* synthetic */ Config.Option access$000() {
        return OPTION_RESOLVED_FRAME_RATE;
    }

    public static CaptureConfig defaultEmptyCaptureConfig() {
        return new Builder().build();
    }

    public List<CameraCaptureCallback> getCameraCaptureCallbacks() {
        return this.mCameraCaptureCallbacks;
    }

    public Range<Integer> getExpectedFrameRateRange() {
        Range<Integer> r2 = (Range) this.mImplementationOptions.retrieveOption(OPTION_RESOLVED_FRAME_RATE, StreamSpec.FRAME_RATE_RANGE_UNSPECIFIED);
        Objects.requireNonNull(r2);
        return r2;
    }

    public Config getImplementationOptions() {
        return this.mImplementationOptions;
    }

    public int getPreviewStabilizationMode() {
        Integer r2 = (Integer) this.mImplementationOptions.retrieveOption(UseCaseConfig.OPTION_PREVIEW_STABILIZATION_MODE, 0);
        Objects.requireNonNull(r2);
        return r2.intValue();
    }

    public List<DeferrableSurface> getSurfaces() {
        return Collections.unmodifiableList(this.mSurfaces);
    }

    public TagBundle getTagBundle() {
        return this.mTagBundle;
    }

    public int getTemplateType() {
        return this.mTemplateType;
    }

    public int getVideoStabilizationMode() {
        Integer r2 = (Integer) this.mImplementationOptions.retrieveOption(UseCaseConfig.OPTION_VIDEO_STABILIZATION_MODE, 0);
        Objects.requireNonNull(r2);
        return r2.intValue();
    }

    public boolean isUseRepeatingSurface() {
        return this.mUseRepeatingSurface;
    }
}
