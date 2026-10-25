package androidx.media3.extractor.metadata.scte35;

import androidx.media3.common.util.ParsableByteArray;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public final class SpliceScheduleCommand extends SpliceCommand {
    public final List<Event> events;

    /* renamed from: androidx.media3.extractor.metadata.scte35.SpliceScheduleCommand$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class ComponentSplice {
        public final int componentTag;
        public final long utcSpliceTime;

        private ComponentSplice(int r1, long r2) {
            this.componentTag = r1;
            this.utcSpliceTime = r2;
        }

        public /* synthetic */ ComponentSplice(int r1, long r2, AnonymousClass1 r4) {
            this(r1, r2);
        }
    }

    public static final class Event {
        public final boolean autoReturn;
        public final int availNum;
        public final int availsExpected;
        public final long breakDurationUs;
        public final List<ComponentSplice> componentSpliceList;
        public final boolean outOfNetworkIndicator;
        public final boolean programSpliceFlag;
        public final boolean spliceEventCancelIndicator;
        public final long spliceEventId;
        public final int uniqueProgramId;
        public final long utcSpliceTime;

        private Event(long r1, boolean r3, boolean r4, boolean r5, List<ComponentSplice> r6, long r7, boolean r9, long r10, int r12, int r13, int r14) {
            this.spliceEventId = r1;
            this.spliceEventCancelIndicator = r3;
            this.outOfNetworkIndicator = r4;
            this.programSpliceFlag = r5;
            this.componentSpliceList = Collections.unmodifiableList(r6);
            this.utcSpliceTime = r7;
            this.autoReturn = r9;
            this.breakDurationUs = r10;
            this.uniqueProgramId = r12;
            this.availNum = r13;
            this.availsExpected = r14;
        }

        public static /* synthetic */ Event access$100(ParsableByteArray r0) {
            return parseFromSection(r0);
        }

        private static Event parseFromSection(ParsableByteArray r23) {
            long r1 = r23.readUnsignedInt();
            boolean r3 = true;
            if ((r23.readUnsignedByte() & 128) == 0) goto L5;
            boolean r0 = true;
        L6:
            ArrayList r5 = new ArrayList();
            if (r3 == true) goto L36;
            int r8 = r23.readUnsignedByte();
            if ((r8 & 128) == 0) goto L11;
            boolean r9 = r0;
        L13:
            if ((r8 & 64) == 0) goto L15;
            boolean r10 = r0;
        L17:
            if ((r8 & 32) == 0) goto L19;
            boolean r82 = r0;
        L20:
            if (r10 == false) goto L22;
            long r12 = r23.readUnsignedInt();
        L23:
            if (r10 == true) goto L28;
            int r52 = r23.readUnsignedByte();
            ArrayList r14 = new ArrayList(r52);
            int r15 = 0;
        L25:
            if (r15 >= r52) goto L27;
            r14.add(new ComponentSplice(r23.readUnsignedByte(), r23.readUnsignedInt(), null));
            r15 = r15 + 1;
            r52 = r52;
            goto L25
        L27:
            r5 = r14;
        L28:
            if (r82 == false) goto L34;
            long r6 = r23.readUnsignedByte();
            if ((128 & r6) == 0) goto L32;
            boolean r16 = true;
        L33:
            long r62 = ((((r6 & 1) << 32) | r23.readUnsignedInt()) * 1000) / 90;
            boolean r4 = r16;
        L35:
            int r02 = r23.readUnsignedShort();
            int r83 = r23.readUnsignedByte();
            boolean r142 = r9;
            boolean r92 = r4;
            boolean r42 = r142;
            int r143 = r23.readUnsignedByte();
            long r19 = r12;
            int r122 = r02;
            int r13 = r83;
            long r21 = r62;
            ArrayList r63 = r5;
            boolean r53 = r10;
            long r7 = r19;
            long r102 = r21;
        L38:
            return new Event(r1, r3, r42, r53, r63, r7, r92, r102, r122, r13, r143);
        L32:
            r16 = false;
            goto L33
        L34:
            r4 = false;
            r62 = -9223372036854775807L;
            goto L35
        L22:
            r12 = -9223372036854775807L;
            goto L23
        L19:
            r82 = false;
            goto L20
        L15:
            r10 = false;
            goto L17
        L11:
            r9 = false;
            goto L13
        L36:
            r63 = r5;
            r42 = false;
            r53 = false;
            r7 = -9223372036854775807L;
            r92 = false;
            r102 = -9223372036854775807L;
            r122 = 0;
            r13 = 0;
            r143 = 0;
            goto L38
        L5:
            r0 = true;
            r3 = false;
            goto L6
        }
    }

    private SpliceScheduleCommand(List<Event> r1) {
        this.events = Collections.unmodifiableList(r1);
    }

    public static SpliceScheduleCommand parseFromSection(ParsableByteArray r4) {
        int r0 = r4.readUnsignedByte();
        ArrayList r1 = new ArrayList(r0);
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L6;
        r1.add(Event.access$100(r4));
        r2 = r2 + 1;
        goto L3
    L6:
        return new SpliceScheduleCommand(r1);
    }
}
