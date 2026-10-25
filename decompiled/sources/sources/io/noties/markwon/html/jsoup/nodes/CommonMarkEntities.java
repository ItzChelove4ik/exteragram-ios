package io.noties.markwon.html.jsoup.nodes;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;
import org.commonmark.internal.util.Html5Entities;

/* loaded from: classes5.dex */
public abstract class CommonMarkEntities {
    private static final Map<String, String> COMMONMARK_NAMED_ENTITIES = null;

    static {
        Field r0 = Html5Entities.class.getDeclaredField("NAMED_CHARACTER_REFERENCES");     // Catch: Throwable -> L4
        r0.setAccessible(true);     // Catch: Throwable -> L4
        Map<String, String> r02 = (Map) r0.get(null);     // Catch: Throwable -> L4
    L6:
        COMMONMARK_NAMED_ENTITIES = r02;
        return;
    L4:
        th = move-exception;
        Map<String, String> r1 = Collections.EMPTY_MAP;
        th.printStackTrace();
        r02 = r1;
        goto L6
    }

    public static int codepointsForName(String r4, int[] r5) {
        String r42 = COMMONMARK_NAMED_ENTITIES.get(r4);
        if (r42 == null) goto L10;
        int r1 = r42.length();
        if (r1 != 1) goto L8;
        r5[0] = r42.charAt(0);
        return r1;
    L8:
        r5[0] = r42.charAt(0);
        r5[1] = r42.charAt(1);
        return r1;
    L10:
        return 0;
    }

    public static boolean isNamedEntity(String r1) {
        return COMMONMARK_NAMED_ENTITIES.containsKey(r1);
    }
}
