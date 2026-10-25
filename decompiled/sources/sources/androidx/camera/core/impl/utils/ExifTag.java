package androidx.camera.core.impl.utils;

/* loaded from: classes3.dex */
class ExifTag {
    public final String name;
    public final int number;
    public final int primaryFormat;
    public final int secondaryFormat;

    public ExifTag(String r1, int r2, int r3) {
        this.name = r1;
        this.number = r2;
        this.primaryFormat = r3;
        this.secondaryFormat = -1;
    }

    public ExifTag(String r1, int r2, int r3, int r4) {
        this.name = r1;
        this.number = r2;
        this.primaryFormat = r3;
        this.secondaryFormat = r4;
    }
}
