package androidx.media3.extractor.text.cea;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.decoder.DecoderOutputBuffer;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleDecoder;
import androidx.media3.extractor.text.SubtitleInputBuffer;
import androidx.media3.extractor.text.SubtitleOutputBuffer;
import java.util.ArrayDeque;
import kotlin.jvm.internal.LongCompanionObject;

/* loaded from: classes4.dex */
abstract class CeaDecoder implements SubtitleDecoder {
    private final ArrayDeque<CeaInputBuffer> availableInputBuffers;
    private final ArrayDeque<SubtitleOutputBuffer> availableOutputBuffers;
    private CeaInputBuffer dequeuedInputBuffer;
    private long outputStartTimeUs;
    private long playbackPositionUs;
    private long queuedInputBufferCount;
    private final ArrayDeque<CeaInputBuffer> queuedInputBuffers;

    /* renamed from: androidx.media3.extractor.text.cea.CeaDecoder$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class CeaInputBuffer extends SubtitleInputBuffer implements Comparable<CeaInputBuffer> {
        private long queuedInputBufferCount;

        private CeaInputBuffer() {
        }

        public /* synthetic */ CeaInputBuffer(AnonymousClass1 r1) {
            this();
        }

        public static /* synthetic */ long access$102(CeaInputBuffer r0, long r1) {
            r0.queuedInputBufferCount = r1;
            return r1;
        }

        /* renamed from: compareTo, reason: avoid collision after fix types in other method */
        public int compareTo2(CeaInputBuffer r8) {
            if (isEndOfStream() != r8.isEndOfStream()) goto L5;
            long r0 = this.timeUs - r8.timeUs;
            if (r0 != 0) goto L15;
            r0 = this.queuedInputBufferCount - r8.queuedInputBufferCount;
            if (r0 != 0) goto L15;
            return 0;
        L15:
            if (r0 <= 0) goto L17;
            return 1;
        L17:
            return -1;
        L5:
            if (isEndOfStream() == false) goto L7;
            return 1;
        L7:
            return -1;
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(CeaInputBuffer r1) {
            return compareTo2(r1);
        }
    }

    public static final class CeaOutputBuffer extends SubtitleOutputBuffer {
        private DecoderOutputBuffer.Owner<CeaOutputBuffer> owner;

        public CeaOutputBuffer(DecoderOutputBuffer.Owner<CeaOutputBuffer> r1) {
            this.owner = r1;
        }

        @Override // androidx.media3.decoder.DecoderOutputBuffer
        public final void release() {
            this.owner.releaseOutputBuffer(this);
        }
    }

    public CeaDecoder() {
        this.availableInputBuffers = new ArrayDeque();
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= 10) goto L6;
        this.availableInputBuffers.add(new CeaInputBuffer(null));
        r1 = r1 + 1;
        goto L4
    L6:
        this.availableOutputBuffers = new ArrayDeque();
    L8:
        if (r0 >= 2) goto L10;
        this.availableOutputBuffers.add(new CeaOutputBuffer(new CeaDecoder$$ExternalSyntheticLambda0(this)));
        r0 = r0 + 1;
        goto L8
    L10:
        this.queuedInputBuffers = new ArrayDeque();
        this.outputStartTimeUs = -9223372036854775807L;
    }

    private void releaseInputBuffer(CeaInputBuffer r1) {
        r1.clear();
        this.availableInputBuffers.add(r1);
    }

    public abstract Subtitle createSubtitle();

    public abstract void decode(SubtitleInputBuffer r1);

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.media3.decoder.Decoder
    public SubtitleInputBuffer dequeueInputBuffer() {
        if (this.dequeuedInputBuffer != null) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkState(r0);
        if (this.availableInputBuffers.isEmpty() == false) goto L10;
        return null;
    L10:
        CeaInputBuffer r02 = this.availableInputBuffers.pollFirst();
        this.dequeuedInputBuffer = r02;
        return r02;
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.media3.decoder.Decoder
    public /* bridge */ /* synthetic */ SubtitleInputBuffer dequeueInputBuffer() {
        return dequeueInputBuffer();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.media3.decoder.Decoder
    public SubtitleOutputBuffer dequeueOutputBuffer() {
        if (this.availableOutputBuffers.isEmpty() == false) goto L6;
        return null;
    L6:
        if (this.queuedInputBuffers.isEmpty() == true) goto L18;
        if (((CeaInputBuffer) Util.castNonNull(this.queuedInputBuffers.peek())).timeUs > this.playbackPositionUs) goto L18;
        CeaInputBuffer r0 = (CeaInputBuffer) Util.castNonNull(this.queuedInputBuffers.poll());
        if (r0.isEndOfStream() == true) goto L11;
        decode(r0);
        if (isNewSubtitleDataAvailable() == true) goto L15;
        releaseInputBuffer(r0);
        goto L6
    L15:
        Subtitle r6 = createSubtitle();
        SubtitleOutputBuffer r3 = (SubtitleOutputBuffer) Util.castNonNull(this.availableOutputBuffers.pollFirst());
        r3.setContent(r0.timeUs, r6, LongCompanionObject.MAX_VALUE);
        releaseInputBuffer(r0);
        return r3;
    L11:
        SubtitleOutputBuffer r1 = (SubtitleOutputBuffer) Util.castNonNull(this.availableOutputBuffers.pollFirst());
        r1.addFlag(4);
        releaseInputBuffer(r0);
        return r1;
    L18:
        return null;
    }

    @Override // androidx.media3.decoder.Decoder
    public /* bridge */ /* synthetic */ SubtitleOutputBuffer dequeueOutputBuffer() {
        return dequeueOutputBuffer();
    }

    @Override // androidx.media3.decoder.Decoder
    public void flush() {
        this.queuedInputBufferCount = 0;
        this.playbackPositionUs = 0;
    L4:
        if (this.queuedInputBuffers.isEmpty() == true) goto L6;
        releaseInputBuffer((CeaInputBuffer) Util.castNonNull(this.queuedInputBuffers.poll()));
        goto L4
    L6:
        CeaInputBuffer r0 = this.dequeuedInputBuffer;
        if (r0 == null) goto L11;
        releaseInputBuffer(r0);
        this.dequeuedInputBuffer = null;
        return;
    }

    public final SubtitleOutputBuffer getAvailableOutputBuffer() {
        return this.availableOutputBuffers.pollFirst();
    }

    public final long getPositionUs() {
        return this.playbackPositionUs;
    }

    public abstract boolean isNewSubtitleDataAvailable();

    /* JADX WARN: Can't rename method to resolve collision */
    public void queueInputBuffer(SubtitleInputBuffer r7) {
        if (r7 != this.dequeuedInputBuffer) goto L5;
        boolean r0 = true;
    L6:
        Assertions.checkArgument(r0);
        CeaInputBuffer r72 = (CeaInputBuffer) r7;
        if (r72.isEndOfStream() == true) goto L15;
        long r02 = r72.timeUs;
        if (r02 == Long.MIN_VALUE) goto L15;
        long r2 = this.outputStartTimeUs;
        if (r2 == (-9223372036854775807L)) goto L15;
        if (r02 >= r2) goto L15;
        releaseInputBuffer(r72);
    L16:
        this.dequeuedInputBuffer = null;
        return;
    L15:
        long r03 = this.queuedInputBufferCount;
        this.queuedInputBufferCount = 1 + r03;
        CeaInputBuffer.access$102(r72, r03);
        this.queuedInputBuffers.add(r72);
        goto L16
    L5:
        r0 = false;
        goto L6
    }

    @Override // androidx.media3.decoder.Decoder
    public /* bridge */ /* synthetic */ void queueInputBuffer(SubtitleInputBuffer r1) {
        queueInputBuffer(r1);
    }

    @Override // androidx.media3.decoder.Decoder
    public void release() {
    }

    public void releaseOutputBuffer(SubtitleOutputBuffer r1) {
        r1.clear();
        this.availableOutputBuffers.add(r1);
    }

    @Override // androidx.media3.decoder.Decoder
    public final void setOutputStartTimeUs(long r1) {
        this.outputStartTimeUs = r1;
    }

    @Override // androidx.media3.extractor.text.SubtitleDecoder
    public void setPositionUs(long r1) {
        this.playbackPositionUs = r1;
    }
}
