package com.android.dx.dex.file;

import com.android.dx.util.AnnotatedOutput;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public final class Statistics {
    private final HashMap<String, Data> dataMap;

    public static class Data {
        private int count;
        private int largestSize;
        private final String name;
        private int smallestSize;
        private int totalSize;

        public Data(Item r1, String r2) {
            int r12 = r1.writeSize();
            this.name = r2;
            this.count = 1;
            this.totalSize = r12;
            this.largestSize = r12;
            this.smallestSize = r12;
        }

        public static /* synthetic */ String access$000(Data r0) {
            return r0.name;
        }

        public void add(Item r2) {
            int r22 = r2.writeSize();
            this.count++;
            this.totalSize += r22;
            if (r22 <= this.largestSize) goto L6;
            this.largestSize = r22;
        L6:
            if (r22 >= this.smallestSize) goto L9;
            this.smallestSize = r22;
            return;
        }

        public String toHuman() {
            StringBuilder r0 = new StringBuilder();
            StringBuilder r1 = new StringBuilder("  ");
            r1.append(this.name);
            r1.append(": ");
            r1.append(this.count);
            r1.append(" item");
            if (this.count != 1) goto L5;
            String r2 = _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            r1.append(r2);
            r1.append("; ");
            r1.append(this.totalSize);
            r1.append(" bytes total\n");
            r0.append(r1.toString());
            if (this.smallestSize != this.largestSize) goto L9;
            r0.append("    " + this.smallestSize + " bytes/item\n");
        L11:
            return r0.toString();
        L9:
            r0.append("    " + this.smallestSize + ".." + this.largestSize + " bytes/item; average " + (this.totalSize / this.count) + "\n");
            goto L11
        L5:
            r2 = "s";
            goto L6
        }

        public void writeAnnotation(AnnotatedOutput r1) {
            r1.annotate(toHuman());
        }
    }

    public Statistics() {
        this.dataMap = new HashMap(50);
    }

    public void add(Item r3) {
        String r0 = r3.typeName();
        Data r1 = this.dataMap.get(r0);
        if (r1 != null) goto L6;
        this.dataMap.put(r0, new Data(r3, r0));
        return;
    L6:
        r1.add(r3);
    }

    public void addAll(Section r2) {
        Iterator<? extends Item> r22 = r2.items().iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        add(r22.next());
        goto L4
    }

    public String toHuman() {
        StringBuilder r0 = new StringBuilder("Statistics:\n");
        TreeMap r1 = new TreeMap();
        Iterator<Data> r4 = this.dataMap.values().iterator();
    L4:
        if (r4.hasNext() == false) goto L6;
        Data r2 = r4.next();
        r1.put(Data.access$000(r2), r2);
        goto L4
    L6:
        Iterator r42 = r1.values().iterator();
    L8:
        if (r42.hasNext() == false) goto L11;
        r0.append(((Data) r42.next()).toHuman());
        goto L8
    L11:
        return r0.toString();
    }

    public final void writeAnnotation(AnnotatedOutput r4) {
        if (this.dataMap.size() == 0) goto L13;
        r4.annotate(0, "\nstatistics:\n");
        TreeMap r0 = new TreeMap();
        Iterator<Data> r3 = this.dataMap.values().iterator();
    L7:
        if (r3.hasNext() == false) goto L9;
        Data r1 = r3.next();
        r0.put(Data.access$000(r1), r1);
        goto L7
    L9:
        Iterator r32 = r0.values().iterator();
    L11:
        if (r32.hasNext() == false) goto L16;
        ((Data) r32.next()).writeAnnotation(r4);
        goto L11
    L16:
        return;
    }
}
