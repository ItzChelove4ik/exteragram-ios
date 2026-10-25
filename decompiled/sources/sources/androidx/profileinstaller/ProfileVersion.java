package androidx.profileinstaller;

import java.util.Arrays;

/* loaded from: classes4.dex */
public abstract class ProfileVersion {
    static final byte[] METADATA_V001_N = null;
    static final byte[] METADATA_V002 = null;
    static final byte[] V001_N = null;
    static final byte[] V005_O = null;
    static final byte[] V009_O_MR1 = null;
    static final byte[] V010_P = null;
    static final byte[] V015_S = null;

    static {
        V015_S = new byte[]{48, 49, 53, 0};
        V010_P = new byte[]{48, 49, 48, 0};
        V009_O_MR1 = new byte[]{48, 48, 57, 0};
        V005_O = new byte[]{48, 48, 53, 0};
        V001_N = new byte[]{48, 48, 49, 0};
        METADATA_V001_N = new byte[]{48, 48, 49, 0};
        METADATA_V002 = new byte[]{48, 48, 50, 0};
    }

    public static String dexKeySeparator(byte[] r2) {
        if (Arrays.equals(r2, V001_N) == false) goto L6;
        return ":";
    L6:
        if (Arrays.equals(r2, V005_O) == false) goto L8;
        return ":";
    L8:
        return "!";
    }
}
