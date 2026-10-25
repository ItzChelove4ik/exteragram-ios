package androidx.media3.exoplayer.video;

import androidx.media3.container.ObuParser;
import java.nio.ByteBuffer;
import java.util.List;

/* loaded from: classes4.dex */
public final class Av1SampleDependencyParser {
    private ObuParser.SequenceHeader sequenceHeader;

    public Av1SampleDependencyParser() {
    }

    private boolean canSkipObu(ObuParser.Obu r5, boolean r6) {
        int r0 = r5.type;
        if (r0 != 2) goto L5;
    L22:
        return true;
    L5:
        if (r0 == 15) goto L22;
        if (r0 != 3) goto L12;
        if (r6 == true) goto L12;
        return false;
    L12:
        if (r0 == 6) goto L14;
        if (r0 == 3) goto L14;
    L21:
        return false;
    L14:
        ObuParser.SequenceHeader r4 = this.sequenceHeader;
        if (r4 == null) goto L21;
        ObuParser.FrameHeader r42 = ObuParser.FrameHeader.parse(r4, r5);
        if (r42 == null) goto L21;
        if (r42.isDependedOn() == true) goto L21;
        return true;
    }

    private void updateSequenceHeaders(List<ObuParser.Obu> r4) {
        int r0 = 0;
    L4:
        if (r0 >= r4.size()) goto L9;
        if (r4.get(r0).type != 1) goto L8;
        this.sequenceHeader = ObuParser.SequenceHeader.parse(r4.get(r0));
    L8:
        r0 = r0 + 1;
        goto L4
    }

    public void queueInputBuffer(ByteBuffer r1) {
        updateSequenceHeaders(ObuParser.split(r1));
    }

    public void reset() {
        this.sequenceHeader = null;
    }

    public int sampleLimitAfterSkippingNonReferenceFrame(ByteBuffer r7, boolean r8) {
        List<ObuParser.Obu> r0 = ObuParser.split(r7);
        updateSequenceHeaders(r0);
        int r1 = r0.size() - 1;
        int r3 = 0;
    L3:
        if (r1 < 0) goto L12;
        if (canSkipObu(r0.get(r1), r8) == false) goto L12;
        if (r0.get(r1).type != 6) goto L9;
    L10:
        r3 = r3 + 1;
    L11:
        r1 = r1 - 1;
        goto L3
    L9:
        if (r0.get(r1).type != 3) goto L11;
    L12:
        if (r3 > 1) goto L22;
        if ((r1 + 1) >= 8) goto L22;
        if (r1 < 0) goto L20;
        return r0.get(r1).payload.limit();
    L20:
        return r7.position();
    L22:
        return r7.limit();
    }
}
