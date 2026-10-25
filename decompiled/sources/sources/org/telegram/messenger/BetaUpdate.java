package org.telegram.messenger;

/* loaded from: classes.dex */
public class BetaUpdate {
    public final String changelog;
    public final String version;
    public final int versionCode;

    public BetaUpdate(String r1, int r2, String r3) {
        this.version = r1;
        this.versionCode = r2;
        this.changelog = r3;
    }

    public boolean higherThan(BetaUpdate r3) {
        if (r3 != null) goto L4;
        return true;
    L4:
        if (SharedConfig.versionBiggerOrEqual(this.version, r3.version) == true) goto L6;
        return false;
    L6:
        if (this.versionCode > r3.versionCode) goto L13;
        return false;
    L13:
        return true;
    }
}
