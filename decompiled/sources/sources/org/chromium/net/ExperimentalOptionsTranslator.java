package org.chromium.net;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

/* loaded from: classes5.dex */
abstract class ExperimentalOptionsTranslator {
    public static JSONObject applyJsonPatches(JSONObject r2, List<Object> r3) {
        if (r2 == null) goto L5;
    L7:
        if (r2 != null) goto L9;
        r2 = new JSONObject();
    L9:
        Iterator<Object> r32 = r3.iterator();
        if (r32.hasNext() == true) goto L12;
        return r2;
    L12:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r32.next());
        throw null;
    L5:
        if (r3.isEmpty() == false) goto L7;
        return null;
    }
}
