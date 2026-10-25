package androidx.car.app.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* loaded from: classes3.dex */
public abstract class CollectionUtils {
    public static <T> List<T> emptyIfNull(List<T> r0) {
        if (r0 == null) goto L5;
        return r0;
    L5:
        return Collections.EMPTY_LIST;
    }

    public static <T> List<T> unmodifiableCopy(List<T> r1) {
        if (r1 != null) goto L6;
        return Collections.EMPTY_LIST;
    L6:
        return Collections.unmodifiableList(new ArrayList(r1));
    }
}
