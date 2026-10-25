package androidx.camera.camera2.pipe.config;

import androidx.camera.camera2.pipe.core.SystemClockOffsets;
import dagger.internal.Preconditions;
import dagger.internal.Provider;

/* loaded from: classes3.dex */
public abstract class SharedCameraGraphModules_Companion_ProvideSystemClockOffsetsFactory implements Provider {
    public static SystemClockOffsets provideSystemClockOffsets() {
        return (SystemClockOffsets) Preconditions.checkNotNullFromProvides(SharedCameraGraphModules.Companion.provideSystemClockOffsets());
    }
}
