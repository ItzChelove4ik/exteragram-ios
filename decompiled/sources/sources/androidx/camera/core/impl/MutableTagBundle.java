package androidx.camera.core.impl;

import android.util.ArrayMap;
import java.util.Map;

/* loaded from: classes3.dex */
public class MutableTagBundle extends TagBundle {
    private MutableTagBundle(Map<String, Object> r1) {
        super(r1);
    }

    public static MutableTagBundle create() {
        return new MutableTagBundle(new ArrayMap());
    }

    public void addTagBundle(TagBundle r1) {
        Map<String, Object> r0 = this.mTagMap;
        if (r0 == null) goto L8;
        Map<String, Object> r12 = r1.mTagMap;
        if (r12 == null) goto L9;
        r0.putAll(r12);
        return;
    L9:
        return;
    }

    public void putTag(String r1, Object r2) {
        this.mTagMap.put(r1, r2);
    }
}
