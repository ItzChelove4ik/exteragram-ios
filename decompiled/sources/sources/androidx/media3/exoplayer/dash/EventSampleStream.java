package androidx.media3.exoplayer.dash;

import androidx.media3.common.Format;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderInputBuffer;
import androidx.media3.exoplayer.FormatHolder;
import androidx.media3.exoplayer.dash.manifest.EventStream;
import androidx.media3.exoplayer.source.SampleStream;
import androidx.media3.extractor.metadata.emsg.EventMessageEncoder;

/* loaded from: classes4.dex */
final class EventSampleStream implements SampleStream {
    private int currentIndex;
    private final EventMessageEncoder eventMessageEncoder;
    private EventStream eventStream;
    private boolean eventStreamAppendable;
    private long[] eventTimesUs;
    private boolean isFormatSentDownstream;
    private long pendingSeekPositionUs;
    private final Format upstreamFormat;

    public EventSampleStream(EventStream r3, Format r4, boolean r5) {
        this.upstreamFormat = r4;
        this.eventStream = r3;
        this.eventMessageEncoder = new EventMessageEncoder();
        this.pendingSeekPositionUs = -9223372036854775807L;
        this.eventTimesUs = r3.presentationTimesUs;
        updateEventStream(r3, r5);
    }

    public String eventStreamId() {
        return this.eventStream.id();
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public boolean isReady() {
        return true;
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public void maybeThrowError() {
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public int readData(FormatHolder r7, DecoderInputBuffer r8, int r9) {
        int r0 = this.currentIndex;
        if (r0 != this.eventTimesUs.length) goto L5;
        boolean r1 = true;
    L7:
        if (r1 == false) goto L13;
        if (this.eventStreamAppendable == true) goto L13;
        r8.setFlags(4);
        return -4;
    L13:
        if ((r9 & 2) == 0) goto L15;
    L28:
        r7.format = this.upstreamFormat;
        this.isFormatSentDownstream = true;
        return -5;
    L15:
        if (this.isFormatSentDownstream == false) goto L28;
        if (r1 == false) goto L21;
        return -3;
    L21:
        if ((r9 & 1) != 0) goto L24;
        this.currentIndex = r0 + 1;
    L24:
        if ((r9 & 4) != 0) goto L26;
        byte[] r72 = this.eventMessageEncoder.encode(this.eventStream.events[r0]);
        r8.ensureSpaceForWrite(r72.length);
        r8.data.put(r72);
    L26:
        r8.timeUs = this.eventTimesUs[r0];
        r8.setFlags(1);
        return -4;
    L5:
        r1 = false;
        goto L7
    }

    public void seekToUs(long r4) {
        int r0 = Util.binarySearchCeil(this.eventTimesUs, r4, true, false);
        this.currentIndex = r0;
        if (this.eventStreamAppendable == true) goto L5;
    L7:
        r4 = -9223372036854775807L;
    L8:
        this.pendingSeekPositionUs = r4;
        return;
    L5:
        if (r0 != this.eventTimesUs.length) goto L7;
        goto L7
    }

    @Override // androidx.media3.exoplayer.source.SampleStream
    public int skipData(long r5) {
        int r52 = Math.max(this.currentIndex, Util.binarySearchCeil(this.eventTimesUs, r5, true, false));
        int r6 = r52 - this.currentIndex;
        this.currentIndex = r52;
        return r6;
    }

    public void updateEventStream(EventStream r9, boolean r10) {
        int r0 = this.currentIndex;
        if (r0 != 0) goto L5;
        long r4 = -9223372036854775807L;
    L6:
        this.eventStreamAppendable = r10;
        this.eventStream = r9;
        long[] r92 = r9.presentationTimesUs;
        this.eventTimesUs = r92;
        long r6 = this.pendingSeekPositionUs;
        if (r6 == (-9223372036854775807L)) goto L11;
        seekToUs(r6);
        return;
    L11:
        if (r4 == (-9223372036854775807L)) goto L14;
        this.currentIndex = Util.binarySearchCeil(r92, r4, false, false);
        return;
    L14:
        return;
    L5:
        r4 = this.eventTimesUs[r0 - 1];
        goto L6
    }
}
