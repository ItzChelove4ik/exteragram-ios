package androidx.sharetarget;

/* loaded from: classes4.dex */
class ShareTargetCompat {
    final String[] mCategories;
    final String mTargetClass;
    final TargetData[] mTargetData;

    public static class TargetData {
        final String mHost;
        final String mMimeType;
        final String mPath;
        final String mPathPattern;
        final String mPathPrefix;
        final String mPort;
        final String mScheme;

        public TargetData(String r1, String r2, String r3, String r4, String r5, String r6, String r7) {
            this.mScheme = r1;
            this.mHost = r2;
            this.mPort = r3;
            this.mPath = r4;
            this.mPathPattern = r5;
            this.mPathPrefix = r6;
            this.mMimeType = r7;
        }
    }

    public ShareTargetCompat(TargetData[] r1, String r2, String[] r3) {
        this.mTargetData = r1;
        this.mTargetClass = r2;
        this.mCategories = r3;
    }
}
