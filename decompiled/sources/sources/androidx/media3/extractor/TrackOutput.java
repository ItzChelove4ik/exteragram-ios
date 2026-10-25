package androidx.media3.extractor;

import androidx.media3.common.DataReader;
import androidx.media3.common.Format;
import androidx.media3.common.util.ParsableByteArray;
import java.util.Arrays;

/* loaded from: classes4.dex */
public interface TrackOutput {

    public static final class CryptoData {
        public final int clearBlocks;
        public final int cryptoMode;
        public final int encryptedBlocks;
        public final byte[] encryptionKey;

        public CryptoData(int r1, byte[] r2, int r3, int r4) {
            this.cryptoMode = r1;
            this.encryptionKey = r2;
            this.encryptedBlocks = r3;
            this.clearBlocks = r4;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L19:
            return false;
        L8:
            if (CryptoData.class != r5.getClass()) goto L19;
            CryptoData r52 = (CryptoData) r5;
            if (this.cryptoMode != r52.cryptoMode) goto L19;
            if (this.encryptedBlocks != r52.encryptedBlocks) goto L19;
            if (this.clearBlocks != r52.clearBlocks) goto L19;
            if (Arrays.equals(this.encryptionKey, r52.encryptionKey) == false) goto L19;
            return true;
        }

        public int hashCode() {
            return (((((this.cryptoMode * 31) + Arrays.hashCode(this.encryptionKey)) * 31) + this.encryptedBlocks) * 31) + this.clearBlocks;
        }
    }

    default void durationUs(long r1) {
    }

    void format(Format r1);

    default int sampleData(DataReader r2, int r3, boolean r4) {
        return sampleData(r2, r3, r4, 0);
    }

    int sampleData(DataReader r1, int r2, boolean r3, int r4);

    default void sampleData(ParsableByteArray r2, int r3) {
        sampleData(r2, r3, 0);
    }

    void sampleData(ParsableByteArray r1, int r2, int r3);

    void sampleMetadata(long r1, int r3, int r4, int r5, CryptoData r6);
}
