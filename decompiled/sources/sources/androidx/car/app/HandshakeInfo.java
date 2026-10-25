package androidx.car.app;

import java.util.Objects;

/* loaded from: classes3.dex */
public final class HandshakeInfo {
    private final int mHostCarAppApiLevel;
    private final String mHostPackageName;

    private HandshakeInfo() {
        this.mHostPackageName = null;
        this.mHostCarAppApiLevel = 0;
    }

    public HandshakeInfo(String r1, int r2) {
        this.mHostPackageName = r1;
        this.mHostCarAppApiLevel = r2;
    }

    public int getHostCarAppApiLevel() {
        return this.mHostCarAppApiLevel;
    }

    public String getHostPackageName() {
        String r0 = this.mHostPackageName;
        Objects.requireNonNull(r0);
        return r0;
    }
}
