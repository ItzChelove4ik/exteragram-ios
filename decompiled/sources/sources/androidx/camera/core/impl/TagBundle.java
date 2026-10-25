package androidx.camera.core.impl;

import android.util.ArrayMap;
import android.util.Pair;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* loaded from: classes3.dex */
public class TagBundle {
    private static final TagBundle EMPTY_TAGBUNDLE = null;
    protected final Map<String, Object> mTagMap;

    static {
        EMPTY_TAGBUNDLE = new TagBundle(new ArrayMap());
    }

    public TagBundle(Map<String, Object> r1) {
        this.mTagMap = r1;
    }

    public static TagBundle create(Pair<String, Object> r2) {
        ArrayMap r0 = new ArrayMap();
        r0.put((String) r2.first, r2.second);
        return new TagBundle(r0);
    }

    public static TagBundle emptyBundle() {
        return EMPTY_TAGBUNDLE;
    }

    public static TagBundle from(TagBundle r4) {
        ArrayMap r0 = new ArrayMap();
        Iterator<String> r1 = r4.listKeys().iterator();
    L4:
        if (r1.hasNext() == false) goto L7;
        String r2 = r1.next();
        r0.put(r2, r4.getTag(r2));
        goto L4
    L7:
        return new TagBundle(r0);
    }

    public Object getTag(String r1) {
        return this.mTagMap.get(r1);
    }

    public Set<String> listKeys() {
        return this.mTagMap.keySet();
    }

    public final String toString() {
        return "android.hardware.camera2.CaptureRequest.setTag.CX";
    }
}
