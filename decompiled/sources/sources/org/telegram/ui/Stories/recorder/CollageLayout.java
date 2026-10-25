package org.telegram.ui.Stories.recorder;

import android.graphics.RectF;
import android.text.TextUtils;
import java.util.ArrayList;
import org.telegram.messenger.BuildVars;

/* loaded from: classes7.dex */
public class CollageLayout {
    private static ArrayList<CollageLayout> layouts;
    public final int[] columns;
    public final int h;
    public final ArrayList<Part> parts;
    private final String src;
    public final int w;

    public static class Part {
        public final CollageLayout layout;
        public final int x;
        public final int y;

        private Part(CollageLayout r1, int r2, int r3) {
            this.layout = r1;
            this.x = r2;
            this.y = r3;
        }

        public /* synthetic */ Part(CollageLayout r1, int r2, int r3, CollageLayoutIA r4) {
            this(r1, r2, r3);
        }

        public final float b(float r2) {
            return (r2 / this.layout.h) * (this.y + 1);
        }

        public final void bounds(RectF r3, float r4, float r5) {
            r3.set(l(r4), t(r5), r(r4), b(r5));
        }

        public final float h(float r1) {
            return r1 / this.layout.h;
        }

        public final float l(float r3) {
            return (r3 / this.layout.columns[this.y]) * this.x;
        }

        public final float r(float r3) {
            return (r3 / this.layout.columns[this.y]) * (this.x + 1);
        }

        public final float t(float r2) {
            return (r2 / this.layout.h) * this.y;
        }

        public final float w(float r2) {
            return r2 / this.layout.columns[this.y];
        }
    }

    public CollageLayout(String r7) {
        this.parts = new ArrayList();
        if (r7 != null) goto L5;
        r7 = ".";
    L5:
        this.src = r7;
        String[] r72 = r7.split("/");
        int r0 = r72.length;
        this.h = r0;
        this.columns = new int[r0];
        int r1 = 0;
        int r2 = 0;
    L7:
        if (r1 >= r72.length) goto L9;
        this.columns[r1] = r72[r1].length();
        r2 = Math.max(r2, r72[r1].length());
        r1 = r1 + 1;
        goto L7
    L9:
        this.w = r2;
        int r12 = 0;
    L11:
        if (r12 >= r72.length) goto L17;
        int r22 = 0;
    L14:
        if (r22 >= r72[r12].length()) goto L16;
        this.parts.add(new Part(this, r22, r12, null));
        r22 = r22 + 1;
        goto L14
    L16:
        r12 = r12 + 1;
        goto L11
    }

    public static ArrayList<CollageLayout> getLayouts() {
        if (layouts != null) goto L8;
        ArrayList<CollageLayout> r0 = new ArrayList();
        layouts = r0;
        r0.add(new CollageLayout("./."));
        layouts.add(new CollageLayout(".."));
        layouts.add(new CollageLayout("../."));
        layouts.add(new CollageLayout("./.."));
        layouts.add(new CollageLayout("././."));
        layouts.add(new CollageLayout("..."));
        layouts.add(new CollageLayout("../.."));
        layouts.add(new CollageLayout("./../.."));
        layouts.add(new CollageLayout("../../."));
        layouts.add(new CollageLayout("../../.."));
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L8;
        layouts.add(new CollageLayout("../../../.."));
        layouts.add(new CollageLayout(".../.../..."));
        layouts.add(new CollageLayout("..../..../...."));
        layouts.add(new CollageLayout(".../.../.../..."));
    L8:
        return layouts;
    }

    public static int getMaxCount() {
        ArrayList<CollageLayout> r0 = getLayouts();
        int r1 = r0.size();
        int r2 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L5;
        CollageLayout r4 = r0.get(r3);
        r3 = r3 + 1;
        r2 = Math.max(r2, r4.parts.size());
        goto L3
    L5:
        return r2;
    }

    public static CollageLayout of(int r5) {
        ArrayList<CollageLayout> r0 = getLayouts();
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L7;
        CollageLayout r3 = r0.get(r2);
        r2 = r2 + 1;
        CollageLayout r32 = r3;
        if (r32.parts.size() < r5) goto L3;
        return r32;
    L7:
        return null;
    }

    public CollageLayout delete(int r5) {
        if (r5 >= 0) goto L4;
        return null;
    L4:
        if (r5 >= this.parts.size()) goto L20;
        ArrayList r0 = new ArrayList(this.parts);
        r0.remove(r5);
        StringBuilder r4 = new StringBuilder();
        int r52 = 0;
        int r1 = 0;
    L8:
        if (r52 >= r0.size()) goto L14;
        Part r2 = (Part) r0.get(r52);
        if (r2.y == r1) goto L12;
        r4.append("/");
        r1 = r2.y;
    L12:
        r4.append(".");
        r52 = r52 + 1;
        goto L8
    L14:
        return new CollageLayout(r4.toString());
    L20:
        return null;
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof CollageLayout) == true) goto L5;
        return false;
    L5:
        return TextUtils.equals(this.src, ((CollageLayout) r2).src);
    }

    public String toString() {
        return this.src;
    }
}
