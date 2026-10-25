package androidx.media3.extractor.mp4;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.extractor.TrackOutput;

/* loaded from: classes4.dex */
public final class TrackEncryptionBox {
    public final TrackOutput.CryptoData cryptoData;
    public final byte[] defaultInitializationVector;
    public final boolean isEncrypted;
    public final int perSampleIvSize;
    public final String schemeType;

    public TrackEncryptionBox(boolean r4, String r5, int r6, byte[] r7, int r8, int r9, byte[] r10) {
        boolean r0 = false;
        if (r6 != 0) goto L5;
        boolean r2 = true;
    L6:
        if (r10 != null) goto L8;
        r0 = true;
    L8:
        Assertions.checkArgument(r0 ^ r2);
        this.isEncrypted = r4;
        this.schemeType = r5;
        this.perSampleIvSize = r6;
        this.defaultInitializationVector = r10;
        this.cryptoData = new TrackOutput.CryptoData(schemeToCryptoMode(r5), r7, r8, r9);
        return;
    L5:
        r2 = false;
        goto L6
    }

    private static int schemeToCryptoMode(String r4) {
        if (r4 != null) goto L5;
        return 1;
    L5:
        char r3 = 65535;
        switch(r4.hashCode()) {
            case 3046605: goto L21;
            case 3046671: goto L17;
            case 3049879: goto L13;
            case 3049895: goto L9;
            default: goto L24;
        };
    L24:
        switch(r3) {
            case 0: goto L27;
            case 1: goto L27;
            case 2: goto L26;
            case 3: goto L26;
            default: goto L25;
        };
    L25:
        Log.w("TrackEncryptionBox", "Unsupported protection scheme type '" + r4 + "'. Assuming AES-CTR crypto mode.");
    L26:
        return 1;
    L27:
        return 2;
    L9:
        if (r4.equals("cens") == false) goto L24;
        r3 = 3;
        goto L24
    L13:
        if (r4.equals("cenc") == false) goto L24;
        r3 = 2;
        goto L24
    L17:
        if (r4.equals("cbcs") == false) goto L24;
        r3 = 1;
        goto L24
    L21:
        if (r4.equals("cbc1") == false) goto L24;
        r3 = 0;
        goto L24
    }
}
