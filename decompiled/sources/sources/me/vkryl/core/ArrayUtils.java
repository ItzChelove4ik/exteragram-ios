package me.vkryl.core;

import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Collections;

/* loaded from: classes5.dex */
public abstract class ArrayUtils {
    public static <T extends Comparable<T>> int addSorted(ArrayList<T> r1, T r2) {
        int r0 = Collections.binarySearch(r1, r2);
        if (r0 >= 0) goto L6;
        int r02 = (-r0) - 1;
        r1.add(r02, r2);
        return r02;
    L6:
        f$$ExternalSyntheticBUOutline1.m("Element already exists in list");
        return 0;
    }

    public static <T extends Comparable<T>> boolean removeSorted(ArrayList<T> r0, T r1) {
        int r12 = Collections.binarySearch(r0, r1);
        if (r12 < 0) goto L6;
        r0.remove(r12);
        return true;
    L6:
        return false;
    }
}
