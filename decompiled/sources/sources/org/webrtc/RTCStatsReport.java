package org.webrtc;

import java.util.Iterator;
import java.util.Map;

/* loaded from: classes6.dex */
public class RTCStatsReport {
    private final Map<String, RTCStats> stats;
    private final long timestampUs;

    public RTCStatsReport(long r1, Map<String, RTCStats> r3) {
        this.timestampUs = r1;
        this.stats = r3;
    }

    @CalledByNative
    private static RTCStatsReport create(long r1, Map r3) {
        return new RTCStatsReport(r1, r3);
    }

    public Map<String, RTCStats> getStatsMap() {
        return this.stats;
    }

    public double getTimestampUs() {
        return this.timestampUs;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("{ timestampUs: ");
        r0.append(this.timestampUs);
        r0.append(", stats: [\n");
        Iterator<RTCStats> r3 = this.stats.values().iterator();
        boolean r1 = true;
    L4:
        if (r3.hasNext() == false) goto L9;
        RTCStats r2 = r3.next();
        if (r1 == true) goto L8;
        r0.append(",\n");
    L8:
        r0.append(r2);
        r1 = false;
        goto L4
    L9:
        r0.append(" ] }");
        return r0.toString();
    }
}
