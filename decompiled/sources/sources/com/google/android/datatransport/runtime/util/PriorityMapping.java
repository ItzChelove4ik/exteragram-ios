package com.google.android.datatransport.runtime.util;

import android.util.SparseArray;
import com.android.dx.DexMaker$$ExternalSyntheticBUOutline0;
import com.google.android.datatransport.Priority;
import java.util.HashMap;
import java.util.Iterator;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public abstract class PriorityMapping {
    private static HashMap<Priority, Integer> PRIORITY_INT_MAP;
    private static SparseArray<Priority> PRIORITY_MAP;

    static {
        PRIORITY_MAP = new SparseArray();
        HashMap<Priority, Integer> r0 = new HashMap();
        PRIORITY_INT_MAP = r0;
        r0.put(Priority.DEFAULT, 0);
        PRIORITY_INT_MAP.put(Priority.VERY_LOW, 1);
        PRIORITY_INT_MAP.put(Priority.HIGHEST, 2);
        Iterator<Priority> r02 = PRIORITY_INT_MAP.keySet().iterator();
    L4:
        if (r02.hasNext() == false) goto L6;
        Priority r1 = r02.next();
        PRIORITY_MAP.append(PRIORITY_INT_MAP.get(r1).intValue(), r1);
        goto L4
    }

    public static int toInt(Priority r1) {
        Integer r0 = PRIORITY_INT_MAP.get(r1);
        if (r0 != null) goto L5;
        DexMaker$$ExternalSyntheticBUOutline0.m("PriorityMapping is missing known Priority value ", r1);
        return 0;
    L5:
        return r0.intValue();
    }

    public static Priority valueOf(int r1) {
        Priority r0 = PRIORITY_MAP.get(r1);
        if (r0 == null) goto L5;
        return r0;
    L5:
        CharCodeKt$$ExternalSyntheticBUOutline0.m("Unknown Priority for value ", r1);
        return null;
    }
}
