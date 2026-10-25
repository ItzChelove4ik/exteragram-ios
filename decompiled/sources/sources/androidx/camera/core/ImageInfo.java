package androidx.camera.core;

import androidx.camera.core.impl.TagBundle;
import androidx.camera.core.impl.utils.ExifData;

/* loaded from: classes3.dex */
public interface ImageInfo {
    default int getFlashState() {
        return 0;
    }

    TagBundle getTagBundle();

    long getTimestamp();

    void populateExifData(ExifData.Builder r1);
}
