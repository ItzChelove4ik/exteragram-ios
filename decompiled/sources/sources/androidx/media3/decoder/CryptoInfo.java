package androidx.media3.decoder;

import android.media.MediaCodec;
import androidx.media3.common.util.Assertions;

/* loaded from: classes3.dex */
public final class CryptoInfo {
    public int clearBlocks;
    public int encryptedBlocks;
    private final MediaCodec.CryptoInfo frameworkCryptoInfo;
    public byte[] iv;
    public byte[] key;
    public int mode;
    public int[] numBytesOfClearData;
    public int[] numBytesOfEncryptedData;
    public int numSubSamples;
    private final PatternHolderV24 patternHolder;

    /* renamed from: androidx.media3.decoder.CryptoInfo$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class PatternHolderV24 {
        private final MediaCodec.CryptoInfo frameworkCryptoInfo;
        private final MediaCodec.CryptoInfo.Pattern pattern;

        private PatternHolderV24(MediaCodec.CryptoInfo r2) {
            this.frameworkCryptoInfo = r2;
            this.pattern = new MediaCodec.CryptoInfo.Pattern(0, 0);
        }

        public /* synthetic */ PatternHolderV24(MediaCodec.CryptoInfo r1, AnonymousClass1 r2) {
            this(r1);
        }

        public static /* synthetic */ void access$100(PatternHolderV24 r0, int r1, int r2) {
            r0.set(r1, r2);
        }

        private void set(int r2, int r3) {
            this.pattern.set(r2, r3);
            this.frameworkCryptoInfo.setPattern(this.pattern);
        }
    }

    public CryptoInfo() {
        MediaCodec.CryptoInfo r0 = new MediaCodec.CryptoInfo();
        this.frameworkCryptoInfo = r0;
        this.patternHolder = new PatternHolderV24(r0, null);
    }

    public MediaCodec.CryptoInfo getFrameworkCryptoInfo() {
        return this.frameworkCryptoInfo;
    }

    public void increaseClearDataFirstSubSampleBy(int r3) {
        if (r3 != 0) goto L5;
        return;
    L5:
        if (this.numBytesOfClearData != null) goto L7;
        int[] r0 = new int[1];
        this.numBytesOfClearData = r0;
        this.frameworkCryptoInfo.numBytesOfClearData = r0;
    L7:
        int[] r2 = this.numBytesOfClearData;
        r2[0] = r2[0] + r3;
    }

    public void set(int r2, int[] r3, int[] r4, byte[] r5, byte[] r6, int r7, int r8, int r9) {
        this.numSubSamples = r2;
        this.numBytesOfClearData = r3;
        this.numBytesOfEncryptedData = r4;
        this.key = r5;
        this.iv = r6;
        this.mode = r7;
        this.encryptedBlocks = r8;
        this.clearBlocks = r9;
        MediaCodec.CryptoInfo r0 = this.frameworkCryptoInfo;
        r0.numSubSamples = r2;
        r0.numBytesOfClearData = r3;
        r0.numBytesOfEncryptedData = r4;
        r0.key = r5;
        r0.iv = r6;
        r0.mode = r7;
        PatternHolderV24.access$100((PatternHolderV24) Assertions.checkNotNull(this.patternHolder), r8, r9);
    }
}
