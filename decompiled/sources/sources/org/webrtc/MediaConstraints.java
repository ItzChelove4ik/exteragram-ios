package org.webrtc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes6.dex */
public class MediaConstraints {
    public final List<KeyValuePair> mandatory;
    public final List<KeyValuePair> optional;

    public static class KeyValuePair {
        private final String key;
        private final String value;

        public KeyValuePair(String r1, String r2) {
            this.key = r1;
            this.value = r2;
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if (r5 != null) goto L8;
        L15:
            return false;
        L8:
            if (getClass() != r5.getClass()) goto L15;
            KeyValuePair r52 = (KeyValuePair) r5;
            if (this.key.equals(r52.key) == false) goto L15;
            if (this.value.equals(r52.value) == false) goto L15;
            return true;
        }

        @CalledByNative("KeyValuePair")
        public String getKey() {
            return this.key;
        }

        @CalledByNative("KeyValuePair")
        public String getValue() {
            return this.value;
        }

        public int hashCode() {
            return this.key.hashCode() + this.value.hashCode();
        }

        public String toString() {
            return this.key + ": " + this.value;
        }
    }

    public MediaConstraints() {
        this.mandatory = new ArrayList();
        this.optional = new ArrayList();
    }

    private static String stringifyKeyValuePairList(List<KeyValuePair> r4) {
        StringBuilder r0 = new StringBuilder("[");
        Iterator<KeyValuePair> r42 = r4.iterator();
    L4:
        if (r42.hasNext() == false) goto L9;
        KeyValuePair r1 = r42.next();
        if (r0.length() <= 1) goto L8;
        r0.append(", ");
    L8:
        r0.append(r1.toString());
        goto L4
    L9:
        r0.append("]");
        return r0.toString();
    }

    @CalledByNative
    public List<KeyValuePair> getMandatory() {
        return this.mandatory;
    }

    @CalledByNative
    public List<KeyValuePair> getOptional() {
        return this.optional;
    }

    public String toString() {
        return "mandatory: " + stringifyKeyValuePairList(this.mandatory) + ", optional: " + stringifyKeyValuePairList(this.optional);
    }
}
