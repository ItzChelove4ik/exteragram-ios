package androidx.camera.core.internal.compat.quirk;

import androidx.camera.core.Logger;
import androidx.camera.core.impl.Quirk;
import androidx.camera.core.impl.QuirkSettings;
import androidx.camera.core.impl.QuirkSettingsHolder;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;

/* loaded from: classes3.dex */
public abstract class DeviceQuirks {
    private static volatile Quirks sQuirks;

    public static /* synthetic */ void $r8$lambda$0bNPLFIPV_CFfkO7wfM7sLoLGZE(QuirkSettings r1) {
        sQuirks = new Quirks(DeviceQuirksLoader.loadQuirks(r1));
        Logger.d("DeviceQuirks", "core DeviceQuirks = " + Quirks.toString(sQuirks));
    }

    static {
        QuirkSettingsHolder.instance().observe(CameraXExecutors.directExecutor(), new DeviceQuirks$$ExternalSyntheticLambda0());
    }

    public static <T extends Quirk> T get(Class<T> r1) {
        return (T) sQuirks.get(r1);
    }

    public static Quirks getAll() {
        return sQuirks;
    }
}
