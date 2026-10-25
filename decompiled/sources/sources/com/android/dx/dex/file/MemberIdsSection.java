package com.android.dx.dex.file;

import com.android.dex.DexIndexOverflowException;
import java.util.Formatter;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/* loaded from: classes.dex */
public abstract class MemberIdsSection extends UniformItemSection {
    public MemberIdsSection(String r2, DexFile r3) {
        super(r2, r3, 4);
    }

    private String getTooManyMembersMessage() {
        TreeMap r0 = new TreeMap();
        Iterator<? extends Item> r1 = items().iterator();
    L4:
        if (r1.hasNext() == false) goto L9;
        String r2 = ((MemberIdItem) r1.next()).getDefiningClass().getPackageName();
        AtomicInteger r3 = (AtomicInteger) r0.get(r2);
        if (r3 != null) goto L8;
        r3 = new AtomicInteger();
        r0.put(r2, r3);
    L8:
        r3.incrementAndGet();
        goto L4
    L9:
        Formatter r12 = new Formatter();
    L13:
        th = move-exception;
        r12.close();
        throw th;
    L11:
        if ((this instanceof MethodIdsSection) == false) goto L15;
        String r22 = "method";
    L16:
        r12.format("Too many %1$s references to fit in one dex file: %2$d; max is %3$d.%nYou may try using multi-dex. If multi-dex is enabled then the list of classes for the main dex list is too large.%nReferences by package:", new Object[]{r22, Integer.valueOf(items().size()), 65536});     // Catch: Throwable -> L13
        Iterator r5 = r0.entrySet().iterator();     // Catch: Throwable -> L13
    L18:
        if (r5.hasNext() == false) goto L20;
        Map.Entry r02 = (Map.Entry) r5.next();     // Catch: Throwable -> L13
        r12.format("%n%6d %s", new Object[]{Integer.valueOf(((AtomicInteger) r02.getValue()).get()), r02.getKey()});     // Catch: Throwable -> L13
        goto L18
    L20:
        String r52 = r12.toString();     // Catch: Throwable -> L13
        r12.close();
        return r52;
    L15:
        r22 = "field";
        goto L16
    }

    @Override // com.android.dx.dex.file.UniformItemSection
    public void orderItems() {
        if (items().size() > 65536) goto L10;
        Iterator<? extends Item> r2 = items().iterator();
        int r0 = 0;
    L6:
        if (r2.hasNext() == false) goto L8;
        ((MemberIdItem) r2.next()).setIndex(r0);
        r0 = r0 + 1;
        goto L6
    L8:
        return;
    L10:
        throw new DexIndexOverflowException(getTooManyMembersMessage());
    }
}
