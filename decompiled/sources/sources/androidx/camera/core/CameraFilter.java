package androidx.camera.core;

import androidx.camera.core.impl.Identifier;
import java.util.List;

/* loaded from: classes3.dex */
public interface CameraFilter {
    public static final Identifier DEFAULT_ID = null;

    static {
        DEFAULT_ID = Identifier.create(new Object());
    }

    List<CameraInfo> filter(List<CameraInfo> r1);

    default Identifier getIdentifier() {
        return DEFAULT_ID;
    }
}
