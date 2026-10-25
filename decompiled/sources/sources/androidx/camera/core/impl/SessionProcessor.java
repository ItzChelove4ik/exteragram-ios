package androidx.camera.core.impl;

import android.util.Pair;
import java.util.Set;

/* loaded from: classes3.dex */
public interface SessionProcessor {
    Pair<Integer, Integer> getImplementationType();

    Set<Integer> getSupportedCameraOperations();
}
