package androidx.car.app;

import java.util.Objects;

/* loaded from: classes3.dex */
public final class HostInfo {
    private final String mPackageName;
    private final int mUid;

    public HostInfo(String r1, int r2) {
        Objects.requireNonNull(r1);
        this.mPackageName = r1;
        this.mUid = r2;
    }

    public String getPackageName() {
        return this.mPackageName;
    }

    public int getUid() {
        return this.mUid;
    }

    public String toString() {
        return this.mPackageName + ", uid: " + this.mUid;
    }
}
