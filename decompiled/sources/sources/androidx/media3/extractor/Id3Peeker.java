package androidx.media3.extractor;

import androidx.media3.common.Metadata;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.metadata.id3.Id3Decoder;
import java.io.EOFException;

/* loaded from: classes4.dex */
public final class Id3Peeker {
    private final ParsableByteArray scratch;

    public Id3Peeker() {
        this.scratch = new ParsableByteArray(10);
    }

    public Metadata peekId3Data(ExtractorInput r8, Id3Decoder.FramePredicate r9) {
        Metadata r1 = null;
        int r2 = 0;
    L15:
        r8.peekFully(this.scratch.getData(), 0, 10);     // Catch: EOFException -> L14
        this.scratch.setPosition(0);
        if (this.scratch.readUnsignedInt24() != 4801587) goto L12;
        this.scratch.skipBytes(3);
        int r3 = this.scratch.readSynchSafeInt();
        int r5 = r3 + 10;
        if (r1 != null) goto L10;
        byte[] r12 = new byte[r5];
        System.arraycopy(this.scratch.getData(), 0, r12, 0, 10);
        r8.peekFully(r12, 10, r3);
        r1 = new Id3Decoder(r9).decode(r12, r5);
    L11:
        r2 = r2 + r5;
        goto L15
    L10:
        r8.advancePeekPosition(r3);
    L12:
        r8.resetPeekPosition();
        r8.advancePeekPosition(r2);
        return r1;
    }
}
