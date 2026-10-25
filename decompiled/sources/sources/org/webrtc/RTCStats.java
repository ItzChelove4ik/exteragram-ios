package org.webrtc;

import java.util.Iterator;
import java.util.Map;
import kotlin.text.Typography;

/* loaded from: classes6.dex */
public class RTCStats {
    private final String id;
    private final Map<String, Object> members;
    private final long timestampUs;
    private final String type;

    public RTCStats(long r1, String r3, String r4, Map<String, Object> r5) {
        this.timestampUs = r1;
        this.type = r3;
        this.id = r4;
        this.members = r5;
    }

    private static void appendValue(StringBuilder r2, Object r3) {
        if ((r3 instanceof Object[]) == false) goto L13;
        Object[] r32 = (Object[]) r3;
        r2.append('[');
        int r0 = 0;
    L6:
        if (r0 >= r32.length) goto L10;
        if (r0 == 0) goto L9;
        r2.append(", ");
    L9:
        appendValue(r2, r32[r0]);
        r0 = r0 + 1;
        goto L6
    L10:
        r2.append(']');
        return;
    L13:
        if ((r3 instanceof String) == false) goto L16;
        r2.append(Typography.quote);
        r2.append(r3);
        r2.append(Typography.quote);
        return;
    L16:
        r2.append(r3);
    }

    @CalledByNative
    public static RTCStats create(long r6, String r8, String r9, Map r10) {
        return new RTCStats(r6, r8, r9, r10);
    }

    public String getId() {
        return this.id;
    }

    public Map<String, Object> getMembers() {
        return this.members;
    }

    public double getTimestampUs() {
        return this.timestampUs;
    }

    public String getType() {
        return this.type;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        r0.append("{ timestampUs: ");
        r0.append(this.timestampUs);
        r0.append(", type: ");
        r0.append(this.type);
        r0.append(", id: ");
        r0.append(this.id);
        Iterator<Map.Entry<String, Object>> r3 = this.members.entrySet().iterator();
    L4:
        if (r3.hasNext() == false) goto L6;
        Map.Entry<String, Object> r1 = r3.next();
        r0.append(", ");
        r0.append(r1.getKey());
        r0.append(": ");
        appendValue(r0, r1.getValue());
        goto L4
    L6:
        r0.append(" }");
        return r0.toString();
    }
}
