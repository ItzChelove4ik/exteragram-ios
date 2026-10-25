package androidx.camera.core.impl;

import android.util.Range;
import android.util.Size;
import androidx.camera.core.DynamicRange;
import androidx.camera.core.impl.UseCaseConfigFactory;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class AttachedSurfaceInfo {
    public AttachedSurfaceInfo() {
    }

    public static AttachedSurfaceInfo create(SurfaceConfig r11, int r12, Size r13, DynamicRange r14, List<UseCaseConfigFactory.CaptureType> r15, Config r16, int r17, Range<Integer> r18, boolean r19, int r20) {
        return new AutoValue_AttachedSurfaceInfo(r11, r12, r13, r14, r15, r16, r17, r18, r19, r20);
    }

    public abstract List<UseCaseConfigFactory.CaptureType> getCaptureTypes();

    public abstract int getCustomMaxFrameRate();

    public abstract DynamicRange getDynamicRange();

    public abstract int getImageFormat();

    public abstract Config getImplementationOptions();

    public abstract int getSessionType();

    public abstract Size getSize();

    public abstract SurfaceConfig getSurfaceConfig();

    public abstract Range<Integer> getTargetFrameRate();

    public abstract boolean isStrictFrameRateRequired();

    public StreamSpec toStreamSpec(Config r3) {
        return StreamSpec.builder(getSize()).setSessionType(getSessionType()).setExpectedFrameRateRange(getTargetFrameRate()).setDynamicRange(getDynamicRange()).setImplementationOptions(r3).build();
    }
}
