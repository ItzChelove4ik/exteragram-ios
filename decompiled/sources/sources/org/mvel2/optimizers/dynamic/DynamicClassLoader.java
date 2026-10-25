package org.mvel2.optimizers.dynamic;

import java.util.Iterator;
import java.util.LinkedList;
import org.mvel2.util.MVELClassLoader;

/* loaded from: classes5.dex */
public class DynamicClassLoader extends ClassLoader implements MVELClassLoader {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final LinkedList<DynamicAccessor> allAccessors;
    private int tenureLimit;
    private int totalClasses;

    static {
    }

    public DynamicClassLoader(ClassLoader r1, int r2) {
        super(r1);
        this.allAccessors = new LinkedList();
        this.tenureLimit = r2;
    }

    @Override // org.mvel2.util.MVELClassLoader
    public Class defineClassX(String r2, byte[] r3, int r4, int r5) {
        this.totalClasses++;
        return super.defineClass(r2, r3, r4, r5);
    }

    public void deoptimizeAll() {
        LinkedList<DynamicAccessor> r0 = this.allAccessors;
        monitor-enter(r0);
        Iterator<DynamicAccessor> r1 = this.allAccessors.iterator();     // Catch: Throwable -> L10
    L6:
        if (r1.hasNext() == false) goto L12;
        DynamicAccessor r2 = r1.next();     // Catch: Throwable -> L10
        if (r2 == null) goto L6;
        r2.deoptimize();     // Catch: Throwable -> L10
        goto L6
    L12:
        this.allAccessors.clear();     // Catch: Throwable -> L10
        monitor-exit(r0);     // Catch: Throwable -> L10
        return;
    L10:
        th = move-exception;
        throw th;
    }

    public int getTotalClasses() {
        return this.totalClasses;
    }

    public boolean isOverloaded() {
        if (this.tenureLimit >= this.totalClasses) goto L6;
        return true;
    L6:
        return false;
    }

    public DynamicAccessor registerDynamicAccessor(DynamicAccessor r4) {
        LinkedList<DynamicAccessor> r0 = this.allAccessors;
        monitor-enter(r0);
        this.allAccessors.add(r4);     // Catch: Throwable -> L10
    L6:
        if (this.allAccessors.size() <= this.tenureLimit) goto L12;
        DynamicAccessor r1 = this.allAccessors.removeFirst();     // Catch: Throwable -> L10
        if (r1 == null) goto L6;
        r1.deoptimize();     // Catch: Throwable -> L10
        goto L6
    L12:
        monitor-exit(r0);     // Catch: Throwable -> L10
        return r4;
    L10:
        th = move-exception;
        throw th;
    }
}
