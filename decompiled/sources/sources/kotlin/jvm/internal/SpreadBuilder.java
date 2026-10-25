package kotlin.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

/* loaded from: classes5.dex */
public class SpreadBuilder {
    private final ArrayList<Object> list;

    public SpreadBuilder(int r2) {
        this.list = new ArrayList(r2);
    }

    public void add(Object r1) {
        this.list.add(r1);
    }

    public void addSpread(Object r4) {
        if (r4 != null) goto L5;
        return;
    L5:
        if ((r4 instanceof Object[]) == false) goto L11;
        Object[] r42 = (Object[]) r4;
        if (r42.length <= 0) goto L26;
        ArrayList<Object> r0 = this.list;
        r0.ensureCapacity(r0.size() + r42.length);
        Collections.addAll(this.list, r42);
        return;
    L26:
        return;
    L11:
        if ((r4 instanceof Collection) == false) goto L15;
        this.list.addAll((Collection) r4);
        return;
    L15:
        if ((r4 instanceof Iterable) == false) goto L21;
        Iterator r43 = ((Iterable) r4).iterator();
    L18:
        if (r43.hasNext() == false) goto L32;
        this.list.add(r43.next());
        goto L18
    L32:
        return;
    L21:
        if ((r4 instanceof Iterator) == false) goto L28;
        Iterator r44 = (Iterator) r4;
    L24:
        if (r44.hasNext() == false) goto L33;
        this.list.add(r44.next());
        goto L24
    L33:
        return;
    L28:
        throw new UnsupportedOperationException("Don't know how to spread " + r4.getClass());
    }

    public int size() {
        return this.list.size();
    }

    public Object[] toArray(Object[] r1) {
        return this.list.toArray(r1);
    }
}
