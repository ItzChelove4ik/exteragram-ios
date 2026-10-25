package androidx.camera.core.streamsharing;

import androidx.camera.core.DynamicRange;
import androidx.camera.core.impl.UseCaseConfig;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/* loaded from: classes3.dex */
public abstract class DynamicRangeUtils {
    private static DynamicRange intersectDynamicRange(List<DynamicRange> r6) {
        if (r6.isEmpty() == false) goto L5;
        return null;
    L5:
        DynamicRange r0 = r6.get(0);
        Integer r2 = Integer.valueOf(r0.getEncoding());
        Integer r02 = Integer.valueOf(r0.getBitDepth());
        int r3 = 1;
    L7:
        if (r3 >= r6.size()) goto L15;
        DynamicRange r4 = r6.get(r3);
        r2 = intersectDynamicRangeEncoding(r2, Integer.valueOf(r4.getEncoding()));
        r02 = intersectDynamicRangeBitDepth(r02, Integer.valueOf(r4.getBitDepth()));
        if (r2 == null) goto L13;
        if (r02 == null) goto L13;
        r3 = r3 + 1;
    L13:
        return null;
    L15:
        return new DynamicRange(r2.intValue(), r02.intValue());
    }

    private static Integer intersectDynamicRangeBitDepth(Integer r2, Integer r3) {
        if (r2.equals(0) == false) goto L6;
        return r3;
    L6:
        if (r3.equals(0) == false) goto L9;
    L10:
        return r2;
    L9:
        if (r2.equals(r3) == true) goto L10;
        return null;
    }

    private static Integer intersectDynamicRangeEncoding(Integer r4, Integer r5) {
        if (r4.equals(0) == false) goto L6;
        return r5;
    L6:
        if (r5.equals(0) == false) goto L9;
    L20:
        return r4;
    L9:
        if (r4.equals(2) == false) goto L14;
        if (r5.equals(1) == true) goto L14;
        return r5;
    L14:
        if (r5.equals(2) == false) goto L19;
        if (r4.equals(1) == false) goto L20;
    L19:
        if (r4.equals(r5) == true) goto L20;
        return null;
    }

    public static DynamicRange resolveDynamicRange(Set<UseCaseConfig<?>> r2) {
        ArrayList r0 = new ArrayList();
        Iterator<UseCaseConfig<?>> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L7;
        r0.add(r22.next().getDynamicRange());
        goto L4
    L7:
        return intersectDynamicRange(r0);
    }
}
