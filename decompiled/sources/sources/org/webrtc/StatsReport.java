package org.webrtc;

/* loaded from: classes6.dex */
public class StatsReport {
    public final String id;
    public final double timestamp;
    public final String type;
    public final Value[] values;

    public static class Value {
        public final String name;
        public final String value;

        @CalledByNative("Value")
        public Value(String r1, String r2) {
            this.name = r1;
            this.value = r2;
        }

        public String toString() {
            return "[" + this.name + ": " + this.value + "]";
        }
    }

    @CalledByNative
    public StatsReport(String r1, String r2, double r3, Value[] r5) {
        this.id = r1;
        this.type = r2;
        this.timestamp = r3;
        this.values = r5;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("id: ");
        r0.append(this.id);
        r0.append(", type: ");
        r0.append(this.type);
        r0.append(", timestamp: ");
        r0.append(this.timestamp);
        r0.append(", values: ");
        int r1 = 0;
    L3:
        Value[] r2 = this.values;
        if (r1 >= r2.length) goto L7;
        r0.append(r2[r1].toString());
        r0.append(", ");
        r1 = r1 + 1;
        goto L3
    L7:
        return r0.toString();
    }
}
