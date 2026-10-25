package androidx.media3.extractor.ts;

import androidx.media3.common.Format;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.container.ReorderingBufferQueue;
import androidx.media3.extractor.CeaUtil;
import androidx.media3.extractor.ExtractorOutput;
import androidx.media3.extractor.TrackOutput;
import androidx.media3.extractor.ts.TsPayloadReader;
import java.util.List;

/* loaded from: classes4.dex */
final class UserDataReader {
    private final List<Format> closedCaptionFormats;
    private final String containerMimeType;
    private final TrackOutput[] outputs;
    private final ReorderingBufferQueue reorderingBufferQueue;

    public static /* synthetic */ void $r8$lambda$zi7aTMO_v4QtNIFv_6gnkb6UCzY(UserDataReader r0, long r1, ParsableByteArray r3) {
        CeaUtil.consumeCcData(r1, r3, r0.outputs);
    }

    public UserDataReader(List<Format> r1, String r2) {
        this.closedCaptionFormats = r1;
        this.containerMimeType = r2;
        this.outputs = new TrackOutput[r1.size()];
        ReorderingBufferQueue r12 = new ReorderingBufferQueue(new UserDataReader$$ExternalSyntheticLambda0(this));
        this.reorderingBufferQueue = r12;
        r12.setMaxSize(3);
    }

    public void consume(long r5, ParsableByteArray r7) {
        if (r7.bytesLeft() < 9) goto L16;
        int r0 = r7.readInt();
        int r1 = r7.readInt();
        int r2 = r7.readUnsignedByte();
        if (r0 == 434) goto L8;
        return;
    L8:
        if (r1 == 1195456820) goto L10;
        return;
    L10:
        if (r2 != 3) goto L15;
        this.reorderingBufferQueue.add(r5, r7);
        return;
    L15:
        return;
    }

    public void createTracks(ExtractorOutput r9, TsPayloadReader.TrackIdGenerator r10) {
        int r1 = 0;
    L4:
        if (r1 >= this.outputs.length) goto L13;
        r10.generateNewId();
        TrackOutput r2 = r9.track(r10.getTrackId(), 3);
        Format r3 = this.closedCaptionFormats.get(r1);
        String r4 = r3.sampleMimeType;
        if ("application/cea-608".equals(r4) == false) goto L8;
    L11:
        boolean r5 = true;
    L12:
        Assertions.checkArgument(r5, "Invalid closed caption MIME type provided: " + r4);
        r2.format(new Format.Builder().setId(r10.getFormatId()).setContainerMimeType(this.containerMimeType).setSampleMimeType(r4).setSelectionFlags(r3.selectionFlags).setLanguage(r3.language).setAccessibilityChannel(r3.accessibilityChannel).setInitializationData(r3.initializationData).build());
        this.outputs[r1] = r2;
        r1 = r1 + 1;
        goto L4
    L8:
        if ("application/cea-708".equals(r4) == true) goto L11;
        r5 = false;
        goto L12
    }
}
