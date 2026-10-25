package androidx.camera.core;

import android.graphics.Matrix;
import androidx.camera.core.impl.TagBundle;
import androidx.camera.core.impl.utils.ExifData;

/* loaded from: classes3.dex */
public abstract class ImmutableImageInfo implements ImageInfo {
    public ImmutableImageInfo() {
    }

    public static ImageInfo create(TagBundle r7, long r8, int r10, Matrix r11, int r12) {
        return new AutoValue_ImmutableImageInfo(r7, r8, r10, r11, r12);
    }

    @Override // androidx.camera.core.ImageInfo
    public abstract int getFlashState();

    public abstract int getRotationDegrees();

    public abstract Matrix getSensorToBufferTransformMatrix();

    @Override // androidx.camera.core.ImageInfo
    public abstract TagBundle getTagBundle();

    @Override // androidx.camera.core.ImageInfo
    public abstract long getTimestamp();

    @Override // androidx.camera.core.ImageInfo
    public void populateExifData(ExifData.Builder r1) {
        r1.setOrientationDegrees(getRotationDegrees());
    }
}
