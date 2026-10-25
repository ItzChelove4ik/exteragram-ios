package androidx.car.app;

import android.content.Intent;
import android.os.Bundle;
import c.f$$ExternalSyntheticBUOutline1;

/* loaded from: classes3.dex */
public abstract class SessionInfoIntentEncoder {
    public static boolean containsSessionInfo(Intent r1) {
        Bundle r12 = r1.getExtras();
        if (r12 != null) goto L7;
        return false;
    L7:
        return r12.containsKey("androidx.car.app.extra.SESSION_INFO_BUNDLE");
    }

    public static SessionInfo decode(Intent r2) {
        Bundle r22 = r2.getExtras();
        if (r22 == null) goto L6;
        Bundle r23 = r22.getBundle("androidx.car.app.extra.SESSION_INFO_BUNDLE");
        return new SessionInfo(r23.getInt("display-type"), r23.getString("session-id"));
    L6:
        f$$ExternalSyntheticBUOutline1.m("Expected the SessionInfo to be encoded in the bind intent extras, but the extras were null.");
        return null;
    }
}
