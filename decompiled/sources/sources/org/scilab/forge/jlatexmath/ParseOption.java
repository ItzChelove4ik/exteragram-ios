package org.scilab.forge.jlatexmath;

import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/* loaded from: classes5.dex */
public final class ParseOption {
    public ParseOption() {
    }

    public static final Map<String, String> parseMap(String r6) {
        HashMap r0 = new HashMap();
        if (r6 != null) goto L5;
    L18:
        return r0;
    L5:
        if (r6.length() == 0) goto L18;
        StringTokenizer r1 = new StringTokenizer(r6, ",");
    L9:
        if (r1.hasMoreTokens() == false) goto L18;
        String[] r62 = r1.nextToken().trim().split("=");
        if (r62 == null) goto L9;
        if (r62.length == 2) goto L14;
        if (r62.length != 1) goto L9;
        r0.put(r62[0].trim(), null);
        goto L9
    L14:
        r0.put(r62[0].trim(), r62[1].trim());
        goto L9
    }
}
