package androidx.car.app;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.car.app.versioning.CarAppApiLevels;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class AppInfo {
    public static final String MIN_API_LEVEL_METADATA_KEY = "androidx.car.app.minCarApiLevel";
    private final int mLatestCarAppApiLevel;
    private final String mLibraryVersion;
    private final int mMinCarAppApiLevel;

    private AppInfo() {
        this.mMinCarAppApiLevel = 0;
        this.mLibraryVersion = null;
        this.mLatestCarAppApiLevel = 0;
    }

    public AppInfo(int r1, int r2, String r3) {
        this.mMinCarAppApiLevel = r1;
        this.mLibraryVersion = r3;
        this.mLatestCarAppApiLevel = r2;
    }

    public static AppInfo create(Context r7) {
        int r1 = retrieveMinCarAppApiLevel(r7);
        if (r1 >= CarAppApiLevels.getOldest()) goto L5;
    L8:
        AppInfo$$ExternalSyntheticBUOutline0.m("Min API level (androidx.car.app.minCarApiLevel=", r1, ") is out of range (", CarAppApiLevels.getOldest(), "-", CarAppApiLevels.getLatest(), ")");
        return null;
    L5:
        if (r1 > CarAppApiLevels.getLatest()) goto L8;
        return new AppInfo(r1, CarAppApiLevels.getLatest(), r7.getResources().getString(R$string.car_app_library_version));
    }

    public static int retrieveMinCarAppApiLevel(Context r3) {
        Bundle r32 = r3.getPackageManager().getApplicationInfo(r3.getPackageName(), 128).metaData;     // Catch: PackageManager.NameNotFoundException -> L11
        if (r32 == null) goto L6;
        int r33 = r32.getInt(MIN_API_LEVEL_METADATA_KEY, 0);     // Catch: PackageManager.NameNotFoundException -> L11
    L7:
        if (r33 == 0) goto L10;
        return r33;
    L10:
        throw new IllegalArgumentException("Min API level not declared in manifest (androidx.car.app.minCarApiLevel)");     // Catch: PackageManager.NameNotFoundException -> L11
    L6:
        r33 = 0;
    L11:
        f$$ExternalSyntheticBUOutline1.m("Unable to read min API level from manifest");
        return 0;
    }

    public int getLatestCarAppApiLevel() {
        return this.mLatestCarAppApiLevel;
    }

    public String getLibraryDisplayVersion() {
        String r0 = this.mLibraryVersion;
        Objects.requireNonNull(r0);
        return r0;
    }

    public int getMinCarAppApiLevel() {
        return this.mMinCarAppApiLevel;
    }

    public String toString() {
        return "Library version: [" + getLibraryDisplayVersion() + "] Min Car Api Level: [" + getMinCarAppApiLevel() + "] Latest Car App Api Level: [" + getLatestCarAppApiLevel() + "]";
    }
}
