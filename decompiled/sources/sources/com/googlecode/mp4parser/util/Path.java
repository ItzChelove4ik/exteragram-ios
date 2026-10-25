package com.googlecode.mp4parser.util;

import com.coremedia.iso.boxes.Box;
import com.coremedia.iso.boxes.Container;
import java.util.Iterator;
import java.util.regex.Pattern;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public abstract class Path {
    static Pattern component;

    static {
        component = Pattern.compile("(....|\\.\\.)(\\[(.*)\\])?");
    }

    public static String createPath(Box r1) {
        return createPath(r1, _UrlKt.FRAGMENT_ENCODE_SET);
    }

    private static String createPath(Box r6, String r7) {
        Container r0 = r6.getParent();
        Iterator<Box> r1 = r0.getBoxes().iterator();
        int r2 = 0;
    L4:
        if (r1.hasNext() == false) goto L9;
        Box r3 = r1.next();
        if (r3.getType().equals(r6.getType()) == false) goto L4;
        if (r3 == r6) goto L9;
        r2 = r2 + 1;
    L9:
        String r62 = String.format("/%s[%d]", new Object[]{r6.getType(), Integer.valueOf(r2)}) + r7;
        if ((r0 instanceof Box) == true) goto L12;
        return r62;
    L12:
        return createPath((Box) r0, r62);
    }
}
