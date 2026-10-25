package androidx.media3.extractor.ogg;

import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import androidx.media3.extractor.ExtractorInput;
import androidx.media3.extractor.ExtractorUtil;
import androidx.media3.extractor.SeekMap;
import androidx.media3.extractor.SeekPoint;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.math.BigInteger;
import okio.Buffer$$ExternalSyntheticBUOutline1;
import org.mvel2.asm.MethodWriter$$ExternalSyntheticBUOutline0;
import org.vosk.Model$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
final class DefaultOggSeeker implements OggSeeker {
    private long end;
    private long endGranule;
    private final OggPageHeader pageHeader;
    private final long payloadEndPosition;
    private final long payloadStartPosition;
    private long positionBeforeSeekToEnd;
    private long start;
    private long startGranule;
    private int state;
    private final StreamReader streamReader;
    private long targetGranule;
    private long totalGranules;

    /* renamed from: androidx.media3.extractor.ogg.DefaultOggSeeker$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public final class OggSeekMap implements SeekMap {
        final /* synthetic */ DefaultOggSeeker this$0;

        private OggSeekMap(DefaultOggSeeker r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ OggSeekMap(DefaultOggSeeker r1, AnonymousClass1 r2) {
            this(r1);
        }

        @Override // androidx.media3.extractor.SeekMap
        public long getDurationUs() {
            return DefaultOggSeeker.access$100(this.this$0).convertGranuleToTime(DefaultOggSeeker.access$300(this.this$0));
        }

        @Override // androidx.media3.extractor.SeekMap
        public SeekMap.SeekPoints getSeekPoints(long r11) {
            long r0 = DefaultOggSeeker.access$100(this.this$0).convertTimeToGranule(r11);
            return new SeekMap.SeekPoints(new SeekPoint(r11, Util.constrainValue((DefaultOggSeeker.access$200(this.this$0) + BigInteger.valueOf(r0).multiply(BigInteger.valueOf(DefaultOggSeeker.access$400(this.this$0) - DefaultOggSeeker.access$200(this.this$0))).divide(BigInteger.valueOf(DefaultOggSeeker.access$300(this.this$0))).longValue()) - NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS, DefaultOggSeeker.access$200(this.this$0), DefaultOggSeeker.access$400(this.this$0) - 1)));
        }

        @Override // androidx.media3.extractor.SeekMap
        public boolean isSeekable() {
            return true;
        }
    }

    public DefaultOggSeeker(StreamReader r3, long r4, long r6, long r8, long r10, boolean r12) {
        if (r4 >= 0) goto L5;
    L7:
        boolean r0 = false;
    L8:
        Assertions.checkArgument(r0);
        this.streamReader = r3;
        this.payloadStartPosition = r4;
        this.payloadEndPosition = r6;
        if (r8 == (r6 - r4)) goto L13;
        if (r12 == true) goto L13;
        this.state = 0;
    L14:
        this.pageHeader = new OggPageHeader();
        return;
    L13:
        this.totalGranules = r10;
        this.state = 4;
        goto L14
    L5:
        if (r6 <= r4) goto L7;
        r0 = true;
        goto L8
    }

    public static /* synthetic */ StreamReader access$100(DefaultOggSeeker r0) {
        return r0.streamReader;
    }

    public static /* synthetic */ long access$200(DefaultOggSeeker r2) {
        return r2.payloadStartPosition;
    }

    public static /* synthetic */ long access$300(DefaultOggSeeker r2) {
        return r2.totalGranules;
    }

    public static /* synthetic */ long access$400(DefaultOggSeeker r2) {
        return r2.payloadEndPosition;
    }

    private long getNextSeekPosition(ExtractorInput r18) {
        if (this.start != this.end) goto L5;
        return -1;
    L5:
        long r5 = r18.getPosition();
        if (this.pageHeader.skipToNextPage(r18, this.end) == true) goto L12;
        long r0 = this.start;
        if (r0 == r5) goto L10;
        return r0;
    L10:
        Model$$ExternalSyntheticBUOutline0.m("No ogg page can be found.");
        return 0;
    L12:
        this.pageHeader.populate(r18, false);
        r18.resetPeekPosition();
        long r9 = this.targetGranule;
        OggPageHeader r2 = this.pageHeader;
        long r11 = r2.granulePosition;
        long r92 = r9 - r11;
        int r13 = r2.headerSize + r2.bodySize;
        if (0 > r92) goto L18;
        if (r92 >= 72000) goto L18;
        return -1;
    L18:
        if (r92 >= 0) goto L20;
        this.end = r5;
        this.endGranule = r11;
    L21:
        long r3 = this.end;
        long r52 = this.start;
        if ((r3 - r52) >= 100000) goto L25;
        this.end = r52;
        return r52;
    L25:
        long r32 = r13;
        if (r92 > 0) goto L28;
        long r7 = 2;
    L29:
        long r1 = r18.getPosition() - (r32 * r7);
        long r33 = this.end;
        long r132 = this.start;
        return Util.constrainValue(r1 + ((r92 * (r33 - r132)) / (this.endGranule - this.startGranule)), r132, r33 - 1);
    L28:
        r7 = 1;
        goto L29
    L20:
        this.start = r18.getPosition() + r13;
        this.startGranule = this.pageHeader.granulePosition;
        goto L21
    }

    private void skipToPageOfTargetGranule(ExtractorInput r6) {
    L2:
        this.pageHeader.skipToNextPage(r6);
        this.pageHeader.populate(r6, false);
        OggPageHeader r0 = this.pageHeader;
        if (r0.granulePosition > this.targetGranule) goto L4;
        r6.skipFully(r0.headerSize + r0.bodySize);
        this.start = r6.getPosition();
        this.startGranule = this.pageHeader.granulePosition;
        goto L2
    L4:
        r6.resetPeekPosition();
    }

    @Override // androidx.media3.extractor.ogg.OggSeeker
    public /* bridge */ /* synthetic */ SeekMap createSeekMap() {
        return createSeekMap();
    }

    @Override // androidx.media3.extractor.ogg.OggSeeker
    public OggSeekMap createSeekMap() {
        if (this.totalGranules != 0) goto L5;
        return null;
    L5:
        return new OggSeekMap(this, null);
    }

    @Override // androidx.media3.extractor.ogg.OggSeeker
    public long read(ExtractorInput r8) {
        int r0 = this.state;
        if (r0 == 0) goto L18;
        if (r0 != 1) goto L6;
    L21:
        this.totalGranules = readGranuleOfLastPage(r8);
        this.state = 4;
        return this.positionBeforeSeekToEnd;
    L6:
        if (r0 == 2) goto L12;
        if (r0 == 3) goto L16;
        if (r0 != 4) goto L10;
        return -1;
    L10:
        MethodWriter$$ExternalSyntheticBUOutline0.m();
        return 0;
    L16:
        skipToPageOfTargetGranule(r8);
        this.state = 4;
        return -(this.startGranule + 2);
    L12:
        long r02 = getNextSeekPosition(r8);
        if (r02 == (-1)) goto L15;
        return r02;
    L15:
        this.state = 3;
        goto L16
    L18:
        long r3 = r8.getPosition();
        this.positionBeforeSeekToEnd = r3;
        this.state = 1;
        long r03 = this.payloadEndPosition - 65307;
        if (r03 <= r3) goto L21;
        return r03;
    }

    public long readGranuleOfLastPage(ExtractorInput r7) {
        this.pageHeader.reset();
        if (this.pageHeader.skipToNextPage(r7) == false) goto L18;
        this.pageHeader.populate(r7, false);
        OggPageHeader r0 = this.pageHeader;
        r7.skipFully(r0.headerSize + r0.bodySize);
        long r02 = this.pageHeader.granulePosition;
    L5:
        OggPageHeader r2 = this.pageHeader;
        if ((r2.type & 4) == 4) goto L17;
        if (r2.skipToNextPage(r7) == false) goto L17;
        if (r7.getPosition() >= this.payloadEndPosition) goto L17;
        if (this.pageHeader.populate(r7, true) == false) goto L17;
        OggPageHeader r22 = this.pageHeader;
        if (ExtractorUtil.skipFullyQuietly(r7, r22.headerSize + r22.bodySize) == false) goto L17;
        r02 = this.pageHeader.granulePosition;
    L17:
        return r02;
    L18:
        Buffer$$ExternalSyntheticBUOutline1.m();
        return 0;
    }

    @Override // androidx.media3.extractor.ogg.OggSeeker
    public void startSeek(long r11) {
        this.targetGranule = Util.constrainValue(r11, 0, this.totalGranules - 1);
        this.state = 2;
        this.start = this.payloadStartPosition;
        this.end = this.payloadEndPosition;
        this.startGranule = 0;
        this.endGranule = this.totalGranules;
    }
}
