package com.google.firebase.platforminfo;

/* loaded from: classes.dex */
abstract class LibraryVersion {
    public LibraryVersion() {
    }

    public static LibraryVersion create(String r1, String r2) {
        return new AutoValue_LibraryVersion(r1, r2);
    }

    public abstract String getLibraryName();

    public abstract String getVersion();
}
