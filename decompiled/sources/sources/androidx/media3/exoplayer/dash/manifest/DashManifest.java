package androidx.media3.exoplayer.dash.manifest;

import android.net.Uri;
import androidx.media3.common.StreamKey;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.offline.FilterableManifest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/* loaded from: classes4.dex */
public class DashManifest implements FilterableManifest<DashManifest> {
    public final long availabilityStartTimeMs;
    public final long durationMs;
    public final boolean dynamic;
    public final Uri location;
    public final long minBufferTimeMs;
    public final long minUpdatePeriodMs;
    private final List<Period> periods;
    public final ProgramInformation programInformation;
    public final long publishTimeMs;
    public final ServiceDescriptionElement serviceDescription;
    public final long suggestedPresentationDelayMs;
    public final long timeShiftBufferDepthMs;
    public final UtcTimingElement utcTiming;

    public DashManifest(long r1, long r3, long r5, boolean r7, long r8, long r10, long r12, long r14, ProgramInformation r16, UtcTimingElement r17, ServiceDescriptionElement r18, Uri r19, List<Period> r20) {
        this.availabilityStartTimeMs = r1;
        this.durationMs = r3;
        this.minBufferTimeMs = r5;
        this.dynamic = r7;
        this.minUpdatePeriodMs = r8;
        this.timeShiftBufferDepthMs = r10;
        this.suggestedPresentationDelayMs = r12;
        this.publishTimeMs = r14;
        this.programInformation = r16;
        this.utcTiming = r17;
        this.location = r19;
        this.serviceDescription = r18;
        if (r20 != null) goto L5;
        List<Period> r13 = Collections.EMPTY_LIST;
    L6:
        this.periods = r13;
        return;
    L5:
        r13 = r20;
        goto L6
    }

    private static ArrayList<AdaptationSet> copyAdaptationSets(List<AdaptationSet> r14, LinkedList<StreamKey> r15) {
        StreamKey r0 = r15.poll();
        int r1 = r0.periodIndex;
        ArrayList<AdaptationSet> r2 = new ArrayList();
    L3:
        int r3 = r0.groupIndex;
        AdaptationSet r4 = r14.get(r3);
        List<Representation> r5 = r4.representations;
        ArrayList r10 = new ArrayList();
    L4:
        r10.add(r5.get(r0.streamIndex));
        r0 = r15.poll();
        if (r0.periodIndex != r1) goto L8;
        if (r0.groupIndex == r3) goto L4;
    L8:
        r2.add(new AdaptationSet(r4.id, r4.type, r10, r4.accessibilityDescriptors, r4.essentialProperties, r4.supplementalProperties));
        if (r0.periodIndex == r1) goto L3;
        r15.addFirst(r0);
        return r2;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.media3.exoplayer.offline.FilterableManifest
    public final DashManifest copy(List<StreamKey> r26) {
        LinkedList r1 = new LinkedList(r26);
        Collections.sort(r1);
        r1.add(new StreamKey(-1, -1, -1));
        ArrayList r2 = new ArrayList();
        long r3 = 0;
        int r5 = 0;
    L3:
        long r7 = -9223372036854775807L;
        if (r5 >= getPeriodCount()) goto L12;
        if (((StreamKey) r1.peek()).periodIndex == r5) goto L10;
        long r9 = getPeriodDurationMs(r5);
        if (r9 == (-9223372036854775807L)) goto L11;
        r3 = r3 + r9;
    L11:
        r5 = r5 + 1;
        goto L3
    L10:
        Period r6 = getPeriod(r5);
        ArrayList<AdaptationSet> r12 = copyAdaptationSets(r6.adaptationSets, r1);
        r2.add(new Period(r6.id, r6.startMs - r3, r12, r6.eventStreams));
        goto L11
    L12:
        long r52 = this.durationMs;
        if (r52 == (-9223372036854775807L)) goto L16;
        r7 = r52 - r3;
    L16:
        return new DashManifest(this.availabilityStartTimeMs, r7, this.minBufferTimeMs, this.dynamic, this.minUpdatePeriodMs, this.timeShiftBufferDepthMs, this.suggestedPresentationDelayMs, this.publishTimeMs, this.programInformation, this.utcTiming, this.serviceDescription, this.location, r2);
    }

    @Override // androidx.media3.exoplayer.offline.FilterableManifest
    public /* bridge */ /* synthetic */ DashManifest copy(List r1) {
        return copy(r1);
    }

    public final Period getPeriod(int r1) {
        return this.periods.get(r1);
    }

    public final int getPeriodCount() {
        return this.periods.size();
    }

    public final long getPeriodDurationMs(int r6) {
        if (r6 != (this.periods.size() - 1)) goto L10;
        long r0 = this.durationMs;
        if (r0 != (-9223372036854775807L)) goto L7;
        return -9223372036854775807L;
    L7:
        long r5 = this.periods.get(r6).startMs;
    L9:
        return r0 - r5;
    L10:
        r0 = this.periods.get(r6 + 1).startMs;
        r5 = this.periods.get(r6).startMs;
        goto L9
    }

    public final long getPeriodDurationUs(int r1) {
        return Util.msToUs(getPeriodDurationMs(r1));
    }
}
