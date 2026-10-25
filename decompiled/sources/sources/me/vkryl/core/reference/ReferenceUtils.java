package me.vkryl.core.reference;

import java.lang.ref.Reference;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public abstract class ReferenceUtils {
    public static <T> boolean addReference(ReferenceCreator<T> r5, List<Reference<T>> r6, T r7) {
        int r0 = r6.size() - 1;
        boolean r3 = false;
    L3:
        if (r0 < 0) goto L13;
        Reference<T> r4 = r6.get(r0);
        if (r4 == null) goto L7;
        T r42 = r4.get();
    L8:
        if (r42 != null) goto L10;
        r6.remove(r0);
    L12:
        r0 = r0 - 1;
        goto L3
    L10:
        if (r42 != r7) goto L12;
        r3 = true;
        goto L12
    L7:
        r42 = null;
        goto L8
    L13:
        if (r3 == true) goto L19;
        Reference<T> r52 = r5.newReference(r7);
        if (r52 == null) goto L18;
        r6.add(r52);
        return true;
    L18:
        Segment$$ExternalSyntheticBUOutline0.m();
    L19:
        return false;
    }

    public static <T> void gcReferenceList(List<Reference<T>> r2) {
        if (r2 == null) goto L9;
        int r0 = r2.size() - 2;
    L4:
        if (r0 < 0) goto L13;
        if (r2.get(r0).get() != null) goto L8;
        r2.remove(r0);
    L8:
        r0 = r0 - 1;
        goto L4
    L13:
        return;
    }

    public static <T> boolean removeReference(List<Reference<T>> r3, T r4) {
        int r0 = r3.size() - 1;
    L3:
        if (r0 < 0) goto L11;
        T r2 = r3.get(r0).get();
        if (r2 == null) goto L9;
        if (r2 == r4) goto L9;
        r0 = r0 - 1;
    L9:
        r3.remove(r0);
        return true;
    L11:
        return false;
    }
}
