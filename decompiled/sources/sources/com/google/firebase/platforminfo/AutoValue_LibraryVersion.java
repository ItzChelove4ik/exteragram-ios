package com.google.firebase.platforminfo;

import c.f$$ExternalSyntheticBUOutline2;

/* loaded from: classes.dex */
final class AutoValue_LibraryVersion extends LibraryVersion {
    private final String libraryName;
    private final String version;

    public AutoValue_LibraryVersion(String r2, String r3) {
        if (r2 == null) goto L10;
        this.libraryName = r2;
        if (r3 == null) goto L8;
        this.version = r3;
        return;
    L8:
        f$$ExternalSyntheticBUOutline2.m("Null version");
        throw null;
    L10:
        f$$ExternalSyntheticBUOutline2.m("Null libraryName");
        throw null;
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof LibraryVersion) == false) goto L12;
        LibraryVersion r52 = (LibraryVersion) r5;
        if (this.libraryName.equals(r52.getLibraryName()) == false) goto L12;
        if (this.version.equals(r52.getVersion()) == false) goto L12;
        return true;
    L12:
        return false;
    }

    @Override // com.google.firebase.platforminfo.LibraryVersion
    public String getLibraryName() {
        return this.libraryName;
    }

    @Override // com.google.firebase.platforminfo.LibraryVersion
    public String getVersion() {
        return this.version;
    }

    public int hashCode() {
        int r0 = (this.libraryName.hashCode() ^ 1000003) * 1000003;
        return this.version.hashCode() ^ r0;
    }

    public String toString() {
        return "LibraryVersion{libraryName=" + this.libraryName + ", version=" + this.version + "}";
    }
}
