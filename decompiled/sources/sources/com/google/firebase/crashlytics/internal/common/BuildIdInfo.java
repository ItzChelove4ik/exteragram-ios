package com.google.firebase.crashlytics.internal.common;

/* loaded from: classes5.dex */
public class BuildIdInfo {
    private final String arch;
    private final String buildId;
    private final String libraryName;

    public BuildIdInfo(String r1, String r2, String r3) {
        this.libraryName = r1;
        this.arch = r2;
        this.buildId = r3;
    }

    public String getArch() {
        return this.arch;
    }

    public String getBuildId() {
        return this.buildId;
    }

    public String getLibraryName() {
        return this.libraryName;
    }
}
