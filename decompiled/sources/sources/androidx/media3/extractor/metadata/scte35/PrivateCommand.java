package androidx.media3.extractor.metadata.scte35;

import androidx.media3.common.util.ParsableByteArray;

/* loaded from: classes4.dex */
public final class PrivateCommand extends SpliceCommand {
    public final byte[] commandBytes;
    public final long identifier;
    public final long ptsAdjustment;

    private PrivateCommand(long r1, byte[] r3, long r4) {
        this.ptsAdjustment = r4;
        this.identifier = r1;
        this.commandBytes = r3;
    }

    public static PrivateCommand parseFromSection(ParsableByteArray r6, int r7, long r8) {
        long r1 = r6.readUnsignedInt();
        int r72 = r7 - 4;
        byte[] r3 = new byte[r72];
        r6.readBytes(r3, 0, r72);
        return new PrivateCommand(r1, r3, r8);
    }

    @Override // androidx.media3.extractor.metadata.scte35.SpliceCommand
    public String toString() {
        return "SCTE-35 PrivateCommand { ptsAdjustment=" + this.ptsAdjustment + ", identifier= " + this.identifier + " }";
    }
}
