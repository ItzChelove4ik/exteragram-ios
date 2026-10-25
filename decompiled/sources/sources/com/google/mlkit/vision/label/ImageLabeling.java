package com.google.mlkit.vision.label;

import com.google.android.gms.common.internal.Preconditions;
import com.google.mlkit.vision.common.internal.MultiFlavorDetectorCreator;

/* loaded from: classes5.dex */
public abstract class ImageLabeling {
    public static ImageLabeler getClient(ImageLabelerOptionsBase r1) {
        Preconditions.checkNotNull(r1, "options cannot be null");
        return (ImageLabeler) MultiFlavorDetectorCreator.getInstance().create(r1);
    }
}
