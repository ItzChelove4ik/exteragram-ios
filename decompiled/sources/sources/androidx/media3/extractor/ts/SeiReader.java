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
public final class SeiReader {
    private final List<Format> closedCaptionFormats;
    private final String containerMimeType;
    private final TrackOutput[] outputs;
    private final ReorderingBufferQueue reorderingBufferQueue;

    public static /* synthetic */ void $r8$lambda$NXFkAmBgfgkngJQGcXxdP1VlNes(SeiReader r0, long r1, ParsableByteArray r3) {
        CeaUtil.consume(r1, r3, r0.outputs);
    }

    public SeiReader(List<Format> r1, String r2) {
        this.closedCaptionFormats = r1;
        this.containerMimeType = r2;
        this.outputs = new TrackOutput[r1.size()];
        this.reorderingBufferQueue = new ReorderingBufferQueue(new SeiReader$$ExternalSyntheticLambda0(this));
    }

    public void clear() {
        this.reorderingBufferQueue.flush();
    }

    public void consume(long r1, ParsableByteArray r3) {
        this.reorderingBufferQueue.add(r1, r3);
    }

    public void createTracks(ExtractorOutput r9, TsPayloadReader.TrackIdGenerator r10) {
        int r1 = 0;
    L4:
        if (r1 >= this.outputs.length) goto L17;
        r10.generateNewId();
        TrackOutput r2 = r9.track(r10.getTrackId(), 3);
        Format r3 = this.closedCaptionFormats.get(r1);
        String r4 = r3.sampleMimeType;
        if ("application/cea-608".equals(r4) == false) goto L8;
    L11:
        boolean r5 = true;
    L12:
        Assertions.checkArgument(r5, "Invalid closed caption MIME type provided: " + r4);
        String r52 = r3.id;
        if (r52 != null) goto L16;
        r52 = r10.getFormatId();
    L16:
        r2.format(new Format.Builder().setId(r52).setContainerMimeType(this.containerMimeType).setSampleMimeType(r4).setSelectionFlags(r3.selectionFlags).setLanguage(r3.language).setAccessibilityChannel(r3.accessibilityChannel).setInitializationData(r3.initializationData).build());
        this.outputs[r1] = r2;
        r1 = r1 + 1;
        goto L4
    L8:
        if ("application/cea-708".equals(r4) == true) goto L11;
        r5 = false;
        goto L12
    }

    public void flush() {
        this.reorderingBufferQueue.flush();
    }

    public void setReorderingQueueSize(int r1) {
        this.reorderingBufferQueue.setMaxSize(r1);
    }
}
