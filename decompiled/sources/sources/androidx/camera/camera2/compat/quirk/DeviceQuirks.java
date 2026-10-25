package androidx.camera.camera2.compat.quirk;

import androidx.camera.core.Logger;
import androidx.camera.core.impl.Quirk;
import androidx.camera.core.impl.QuirkSettings;
import androidx.camera.core.impl.QuirkSettingsHolder;
import androidx.camera.core.impl.Quirks;
import androidx.camera.core.impl.utils.executor.CameraXExecutors;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J,\u0010\b\u001a\u0004\u0018\u00018\u0000\"\n\b\u0000\u0010\u0005*\u0004\u0018\u00010\u00042\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006H\u0086\u0002¢\u0006\u0004\b\b\u0010\tR(\u0010\u000b\u001a\u00020\n8\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0004\b\u000b\u0010\f\u0012\u0004\b\u0011\u0010\u0003\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Landroidx/camera/camera2/compat/quirk/DeviceQuirks;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/camera/core/impl/Quirk;", "T", "Ljava/lang/Class;", "quirkClass", "get", "(Ljava/lang/Class;)Landroidx/camera/core/impl/Quirk;", "Landroidx/camera/core/impl/Quirks;", "all", "Landroidx/camera/core/impl/Quirks;", "getAll", "()Landroidx/camera/core/impl/Quirks;", "setAll", "(Landroidx/camera/core/impl/Quirks;)V", "getAll$annotations", "camera-camera2"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeviceQuirks {
    public static final DeviceQuirks INSTANCE = null;
    public static volatile Quirks all;

    /* renamed from: $r8$lambda$HfBMZLZ_-UgnnlH0dufyH-ifpDc, reason: not valid java name */
    public static void m29$r8$lambda$HfBMZLZ_UgnnlH0dufyHifpDc(QuirkSettings r2) {
        setAll(new Quirks(DeviceQuirksLoader.INSTANCE.loadQuirks(r2)));
        Logger.d("DeviceQuirks", "camera2 DeviceQuirks = " + Quirks.toString(getAll()));
    }

    static {
        INSTANCE = new DeviceQuirks();
        QuirkSettingsHolder.instance().observe(CameraXExecutors.directExecutor(), new DeviceQuirks$$ExternalSyntheticLambda0());
    }

    private DeviceQuirks() {
    }

    public static final Quirks getAll() {
        Quirks r0 = all;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return null;
    }

    public static final void setAll(Quirks r0) {
        all = r0;
    }

    public final <T extends Quirk> T get(Class<T> r1) {
        return (T) getAll().get(r1);
    }
}
