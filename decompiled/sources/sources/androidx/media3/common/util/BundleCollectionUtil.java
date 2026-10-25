package androidx.media3.common.util;

import android.os.Bundle;
import android.util.SparseArray;
import com.google.common.base.Function;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* loaded from: classes3.dex */
public abstract class BundleCollectionUtil {
    public static HashMap<String, String> bundleToStringHashMap(Bundle r4) {
        HashMap<String, String> r0 = new HashMap();
        if (r4 == Bundle.EMPTY) goto L11;
        Iterator<String> r1 = r4.keySet().iterator();
    L7:
        if (r1.hasNext() == false) goto L11;
        String r2 = r1.next();
        String r3 = r4.getString(r2);
        if (r3 == null) goto L7;
        r0.put(r2, r3);
    L11:
        return r0;
    }

    public static ImmutableMap<String, String> bundleToStringImmutableMap(Bundle r1) {
        if (r1 != Bundle.EMPTY) goto L7;
        return ImmutableMap.of();
    L7:
        return ImmutableMap.copyOf(bundleToStringHashMap(r1));
    }

    public static void ensureClassLoader(Bundle r1) {
        if (r1 == null) goto L5;
        r1.setClassLoader((ClassLoader) Util.castNonNull(BundleCollectionUtil.class.getClassLoader()));
        return;
    }

    public static <T> ImmutableList<T> fromBundleList(Function<Bundle, T> r3, List<Bundle> r4) {
        ImmutableList.Builder r0 = ImmutableList.builder();
        int r1 = 0;
    L4:
        if (r1 >= r4.size()) goto L7;
        r0.add(r3.apply((Bundle) Assertions.checkNotNull(r4.get(r1))));
        r1 = r1 + 1;
        goto L4
    L7:
        return r0.build();
    }

    public static Bundle getBundleWithDefault(Bundle r0, String r1, Bundle r2) {
        Bundle r02 = r0.getBundle(r1);
        if (r02 == null) goto L5;
        return r02;
    L5:
        return r2;
    }

    public static ArrayList<Integer> getIntegerArrayListWithDefault(Bundle r0, String r1, ArrayList<Integer> r2) {
        ArrayList<Integer> r02 = r0.getIntegerArrayList(r1);
        if (r02 == null) goto L5;
        return r02;
    L5:
        return r2;
    }

    public static Bundle stringMapToBundle(Map<String, String> r3) {
        Bundle r0 = new Bundle();
        Iterator<Map.Entry<String, String>> r32 = r3.entrySet().iterator();
    L4:
        if (r32.hasNext() == false) goto L6;
        Map.Entry<String, String> r1 = r32.next();
        r0.putString(r1.getKey(), r1.getValue());
        goto L4
    L6:
        return r0;
    }

    public static <T> ArrayList<Bundle> toBundleArrayList(Collection<T> r2, Function<T, Bundle> r3) {
        ArrayList<Bundle> r0 = new ArrayList(r2.size());
        Iterator<T> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        r0.add(r3.apply(r22.next()));
        goto L4
    L6:
        return r0;
    }

    public static <T> ImmutableList<Bundle> toBundleList(List<T> r3, Function<T, Bundle> r4) {
        ImmutableList.Builder r0 = ImmutableList.builder();
        int r1 = 0;
    L4:
        if (r1 >= r3.size()) goto L7;
        r0.add(r4.apply(r3.get(r1)));
        r1 = r1 + 1;
        goto L4
    L7:
        return r0.build();
    }

    public static <T> SparseArray<Bundle> toBundleSparseArray(SparseArray<T> r4, Function<T, Bundle> r5) {
        SparseArray<Bundle> r0 = new SparseArray(r4.size());
        int r1 = 0;
    L4:
        if (r1 >= r4.size()) goto L6;
        r0.put(r4.keyAt(r1), r5.apply(r4.valueAt(r1)));
        r1 = r1 + 1;
        goto L4
    L6:
        return r0;
    }
}
