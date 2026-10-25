package androidx.profileinstaller;

import java.util.TreeMap;

/* loaded from: classes4.dex */
class DexProfileData {
    final String apkName;
    int classSetSize;
    int[] classes;
    final long dexChecksum;
    final String dexName;
    final int hotMethodRegionSize;
    long mTypeIdCount;
    final TreeMap<Integer, Integer> methods;
    final int numMethodIds;

    public DexProfileData(String r1, String r2, long r3, long r5, int r7, int r8, int r9, int[] r10, TreeMap<Integer, Integer> r11) {
        this.apkName = r1;
        this.dexName = r2;
        this.dexChecksum = r3;
        this.mTypeIdCount = r5;
        this.classSetSize = r7;
        this.hotMethodRegionSize = r8;
        this.numMethodIds = r9;
        this.classes = r10;
        this.methods = r11;
    }
}
