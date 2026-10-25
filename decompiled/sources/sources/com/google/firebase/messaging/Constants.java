package com.google.firebase.messaging;

import android.os.Bundle;
import androidx.collection.ArrayMap;
import java.util.Iterator;

/* loaded from: classes5.dex */
public abstract class Constants {
    public static final long WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS = 0;

    /* loaded from: classes.dex */
    public static final class MessagePayloadKeys {
        public static ArrayMap<String, String> extractDeveloperDefinedPayload(Bundle r5) {
            ArrayMap<String, String> r0 = new ArrayMap();
            Iterator<String> r1 = r5.keySet().iterator();
        L4:
            if (r1.hasNext() == false) goto L18;
            String r2 = r1.next();
            Object r3 = r5.get(r2);
            if ((r3 instanceof String) == false) goto L4;
            String r32 = (String) r3;
            if (r2.startsWith("google.") == true) goto L4;
            if (r2.startsWith("gcm.") == true) goto L4;
            if (r2.equals("from") == true) goto L4;
            if (r2.equals("message_type") == true) goto L4;
            if (r2.equals("collapse_key") == true) goto L4;
            r0.put(r2, r32);
            goto L4
        L18:
            return r0;
        }
    }

    static {
        WAKE_LOCK_ACQUIRE_TIMEOUT_MILLIS = 180000;
    }
}
