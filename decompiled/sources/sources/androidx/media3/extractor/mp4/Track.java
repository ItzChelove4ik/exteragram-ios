package androidx.media3.extractor.mp4;

import androidx.media3.common.Format;

/* loaded from: classes4.dex */
public final class Track {
    public final long durationUs;
    public final long[] editListDurations;
    public final long[] editListMediaTimes;
    public final Format format;
    public final int id;
    public final long mediaDurationUs;
    public final long movieTimescale;
    public final int nalUnitLengthFieldLength;
    private final TrackEncryptionBox[] sampleDescriptionEncryptionBoxes;
    public final int sampleTransformation;
    public final long timescale;
    public final int type;

    public Track(int r1, int r2, long r3, long r5, long r7, long r9, Format r11, int r12, TrackEncryptionBox[] r13, int r14, long[] r15, long[] r16) {
        this.id = r1;
        this.type = r2;
        this.timescale = r3;
        this.movieTimescale = r5;
        this.durationUs = r7;
        this.mediaDurationUs = r9;
        this.format = r11;
        this.sampleTransformation = r12;
        this.sampleDescriptionEncryptionBoxes = r13;
        this.nalUnitLengthFieldLength = r14;
        this.editListDurations = r15;
        this.editListMediaTimes = r16;
    }

    public Track copyWithFormat(Format r18) {
        return new Track(this.id, this.type, this.timescale, this.movieTimescale, this.durationUs, this.mediaDurationUs, r18, this.sampleTransformation, this.sampleDescriptionEncryptionBoxes, this.nalUnitLengthFieldLength, this.editListDurations, this.editListMediaTimes);
    }

    public TrackEncryptionBox getSampleDescriptionEncryptionBox(int r1) {
        TrackEncryptionBox[] r0 = this.sampleDescriptionEncryptionBoxes;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0[r1];
    }
}
