package androidx.media3.extractor.metadata.scte35;

import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.common.util.TimestampAdjuster;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class SpliceInsertCommand extends SpliceCommand {
    public final boolean autoReturn;
    public final int availNum;
    public final int availsExpected;
    public final long breakDurationUs;
    public final List<ComponentSplice> componentSpliceList;
    public final boolean outOfNetworkIndicator;
    public final boolean programSpliceFlag;
    public final long programSplicePlaybackPositionUs;
    public final long programSplicePts;
    public final boolean spliceEventCancelIndicator;
    public final long spliceEventId;
    public final boolean spliceImmediateFlag;
    public final int uniqueProgramId;

    /* renamed from: androidx.media3.extractor.metadata.scte35.SpliceInsertCommand$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class ComponentSplice {
        public final long componentSplicePlaybackPositionUs;
        public final long componentSplicePts;
        public final int componentTag;

        private ComponentSplice(int r1, long r2, long r4) {
            this.componentTag = r1;
            this.componentSplicePts = r2;
            this.componentSplicePlaybackPositionUs = r4;
        }

        public /* synthetic */ ComponentSplice(int r1, long r2, long r4, AnonymousClass1 r6) {
            this(r1, r2, r4);
        }
    }

    private SpliceInsertCommand(long r1, boolean r3, boolean r4, boolean r5, boolean r6, long r7, long r9, List<ComponentSplice> r11, boolean r12, long r13, int r15, int r16, int r17) {
        this.spliceEventId = r1;
        this.spliceEventCancelIndicator = r3;
        this.outOfNetworkIndicator = r4;
        this.programSpliceFlag = r5;
        this.spliceImmediateFlag = r6;
        this.programSplicePts = r7;
        this.programSplicePlaybackPositionUs = r9;
        this.componentSpliceList = Collections.unmodifiableList(r11);
        this.autoReturn = r12;
        this.breakDurationUs = r13;
        this.uniqueProgramId = r15;
        this.availNum = r16;
        this.availsExpected = r17;
    }

    public static SpliceInsertCommand parseFromSection(ParsableByteArray r25, long r26, TimestampAdjuster r28) {
        long r1 = r25.readUnsignedInt();
        if ((r25.readUnsignedByte() & 128) == 0) goto L5;
        boolean r3 = true;
    L6:
        List r6 = Collections.EMPTY_LIST;
        long r7 = -9223372036854775807L;
        if (r3 == true) goto L45;
        int r9 = r25.readUnsignedByte();
        if ((r9 & 128) == 0) goto L11;
        boolean r10 = true;
    L13:
        if ((r9 & 64) == 0) goto L15;
        boolean r11 = true;
    L17:
        if ((r9 & 32) == 0) goto L19;
        boolean r12 = true;
    L21:
        if ((r9 & 16) == 0) goto L23;
        boolean r92 = true;
    L24:
        if (r11 == false) goto L27;
        if (r92 == true) goto L27;
        long r13 = TimeSignalCommand.parseSpliceTime(r25, r26);
    L28:
        if (r11 == true) goto L37;
        int r62 = r25.readUnsignedByte();
        ArrayList r15 = new ArrayList(r62);
        int r4 = 0;
    L30:
        if (r4 >= r62) goto L36;
        int r18 = r25.readUnsignedByte();
        if (r92 == true) goto L34;
        int r24 = r62;
        long r5 = TimeSignalCommand.parseSpliceTime(r25, r26);
    L35:
        r15.add(new ComponentSplice(r18, r5, r28.adjustTsTimestamp(r5), null));
        r4 = r4 + 1;
        r62 = r24;
        goto L30
    L34:
        r24 = r62;
        r5 = -9223372036854775807L;
        goto L35
    L36:
        r6 = r15;
    L37:
        if (r12 == false) goto L43;
        long r42 = r25.readUnsignedByte();
        if ((128 & r42) == 0) goto L41;
        boolean r16 = true;
    L42:
        r7 = ((((r42 & 1) << 32) | r25.readUnsignedInt()) * 1000) / 90;
        boolean r52 = r16;
    L44:
        int r43 = r25.readUnsignedShort();
        long r162 = r13;
        long r132 = r7;
        r7 = r162;
        int r163 = r25.readUnsignedByte();
        int r17 = r25.readUnsignedByte();
        int r152 = r43;
        boolean r122 = r52;
        boolean r44 = r10;
        boolean r53 = r11;
        List r112 = r6;
        boolean r63 = r92;
    L47:
        return new SpliceInsertCommand(r1, r3, r44, r53, r63, r7, r28.adjustTsTimestamp(r7), r112, r122, r132, r152, r163, r17);
    L41:
        r16 = false;
        goto L42
    L43:
        r52 = false;
    L27:
        r13 = -9223372036854775807L;
        goto L28
    L23:
        r92 = false;
        goto L24
    L19:
        r12 = false;
        goto L21
    L15:
        r11 = false;
        goto L17
    L11:
        r10 = false;
        goto L13
    L45:
        r112 = r6;
        r132 = -9223372036854775807L;
        r44 = false;
        r53 = false;
        r63 = false;
        r122 = false;
        r152 = 0;
        r163 = 0;
        r17 = 0;
        goto L47
    L5:
        r3 = false;
        goto L6
    }

    @Override // androidx.media3.extractor.metadata.scte35.SpliceCommand
    public String toString() {
        return "SCTE-35 SpliceInsertCommand { programSplicePts=" + this.programSplicePts + ", programSplicePlaybackPositionUs= " + this.programSplicePlaybackPositionUs + " }";
    }
}
