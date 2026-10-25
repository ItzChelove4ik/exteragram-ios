package androidx.camera.core.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* loaded from: classes3.dex */
public final class SurfaceCombination {
    private final List<SurfaceConfig> mSurfaceConfigList;

    public SurfaceCombination() {
        this.mSurfaceConfigList = new ArrayList();
    }

    private static void generateArrangements(List<int[]> r4, int r5, int[] r6, int r7) {
        if (r7 < r6.length) goto L6;
        r4.add((int[]) r6.clone());
        return;
    L6:
        int r1 = 0;
    L7:
        if (r1 >= r5) goto L16;
        int r2 = 0;
    L9:
        if (r2 >= r7) goto L14;
        if (r1 == r6[r2]) goto L15;
        r2 = r2 + 1;
    L15:
        r1 = r1 + 1;
        goto L7
    L14:
        r6[r7] = r1;
        generateArrangements(r4, r5, r6, r7 + 1);
        goto L15
    }

    private List<int[]> getElementsArrangements(int r3) {
        ArrayList r2 = new ArrayList();
        generateArrangements(r2, r3, new int[r3], 0);
        return r2;
    }

    public boolean addSurfaceConfig(SurfaceConfig r1) {
        return this.mSurfaceConfigList.add(r1);
    }

    public List<SurfaceConfig> getOrderedSupportedSurfaceConfigList(List<SurfaceConfig> r10) {
        if (r10.isEmpty() == false) goto L7;
        return new ArrayList();
    L7:
        if (r10.size() == this.mSurfaceConfigList.size()) goto L9;
        return null;
    L9:
        List<int[]> r0 = getElementsArrangements(this.mSurfaceConfigList.size());
        SurfaceConfig[] r1 = new SurfaceConfig[r10.size()];
        Iterator<int[]> r02 = r0.iterator();
    L10:
        int r4 = 0;
        if (r02.hasNext() == false) goto L24;
        int[] r3 = r02.next();
        boolean r6 = true;
    L14:
        if (r4 >= this.mSurfaceConfigList.size()) goto L22;
        if (r3[r4] >= r10.size()) goto L21;
        r6 = r6 & this.mSurfaceConfigList.get(r4).isSupported(r10.get(r3[r4]));
        if (r6 == false) goto L22;
        r1[r3[r4]] = this.mSurfaceConfigList.get(r4);
    L21:
        r4 = r4 + 1;
    L22:
        if (r6 == false) goto L10;
        r4 = 1;
    L24:
        if (r4 != 0) goto L26;
        return null;
    L26:
        return Arrays.asList(r1);
    }
}
