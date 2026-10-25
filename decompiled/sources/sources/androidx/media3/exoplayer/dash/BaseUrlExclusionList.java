package androidx.media3.exoplayer.dash;

import android.os.SystemClock;
import android.util.Pair;
import androidx.media3.common.util.Util;
import androidx.media3.exoplayer.dash.manifest.BaseUrl;
import com.google.common.collect.Iterables;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

/* loaded from: classes4.dex */
public final class BaseUrlExclusionList {
    private final Map<Integer, Long> excludedPriorities;
    private final Map<String, Long> excludedServiceLocations;
    private final Random random;
    private final Map<List<Pair<String, Integer>>, BaseUrl> selectionsTaken;

    public static /* synthetic */ int $r8$lambda$B0M_VULuMho33wL69NUZWXpBNM8(BaseUrl r0, BaseUrl r1) {
        return compareBaseUrl(r0, r1);
    }

    public BaseUrlExclusionList() {
        this(new Random());
    }

    public BaseUrlExclusionList(Random r2) {
        this.selectionsTaken = new HashMap();
        this.random = r2;
        this.excludedServiceLocations = new HashMap();
        this.excludedPriorities = new HashMap();
    }

    private static <T> void addExclusion(T r2, long r3, Map<T, Long> r5) {
        if (r5.containsKey(r2) == false) goto L5;
        r3 = Math.max(r3, ((Long) Util.castNonNull(r5.get(r2))).longValue());
    L5:
        r5.put(r2, Long.valueOf(r3));
    }

    private List<BaseUrl> applyExclusions(List<BaseUrl> r6) {
        long r0 = SystemClock.elapsedRealtime();
        removeExpiredExclusions(r0, this.excludedServiceLocations);
        removeExpiredExclusions(r0, this.excludedPriorities);
        ArrayList r02 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r6.size()) goto L11;
        BaseUrl r2 = r6.get(r1);
        if (this.excludedServiceLocations.containsKey(r2.serviceLocation) == true) goto L10;
        if (this.excludedPriorities.containsKey(Integer.valueOf(r2.priority)) == true) goto L10;
        r02.add(r2);
    L10:
        r1 = r1 + 1;
        goto L4
    L11:
        return r02;
    }

    private static int compareBaseUrl(BaseUrl r2, BaseUrl r3) {
        int r0 = Integer.compare(r2.priority, r3.priority);
        if (r0 == 0) goto L6;
        return r0;
    L6:
        return r2.serviceLocation.compareTo(r3.serviceLocation);
    }

    public static int getPriorityCount(List<BaseUrl> r3) {
        HashSet r0 = new HashSet();
        int r1 = 0;
    L4:
        if (r1 >= r3.size()) goto L7;
        r0.add(Integer.valueOf(r3.get(r1).priority));
        r1 = r1 + 1;
        goto L4
    L7:
        return r0.size();
    }

    private static <T> void removeExpiredExclusions(long r5, Map<T, Long> r7) {
        ArrayList r0 = new ArrayList();
        Iterator<Map.Entry<T, Long>> r1 = r7.entrySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L8;
        Map.Entry<T, Long> r2 = r1.next();
        if (r2.getValue().longValue() > r5) goto L4;
        r0.add(r2.getKey());
        goto L4
    L8:
        int r52 = 0;
    L10:
        if (r52 >= r0.size()) goto L12;
        r7.remove(r0.get(r52));
        r52 = r52 + 1;
        goto L10
    }

    private BaseUrl selectWeighted(List<BaseUrl> r5) {
        int r0 = 0;
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= r5.size()) goto L6;
        r2 = r2 + r5.get(r1).weight;
        r1 = r1 + 1;
        goto L4
    L6:
        int r4 = this.random.nextInt(r2);
        int r12 = 0;
    L8:
        if (r0 >= r5.size()) goto L14;
        BaseUrl r22 = r5.get(r0);
        r12 = r12 + r22.weight;
        if (r4 < r12) goto L11;
        r0 = r0 + 1;
        goto L8
    L11:
        return r22;
    L14:
        return (BaseUrl) Iterables.getLast(r5);
    }

    public void exclude(BaseUrl r3, long r4) {
        long r0 = SystemClock.elapsedRealtime() + r4;
        addExclusion(r3.serviceLocation, r0, this.excludedServiceLocations);
        int r32 = r3.priority;
        if (r32 == Integer.MIN_VALUE) goto L6;
        addExclusion(Integer.valueOf(r32), r0, this.excludedPriorities);
        return;
    }

    public int getPriorityCountAfterExclusion(List<BaseUrl> r3) {
        HashSet r0 = new HashSet();
        List<BaseUrl> r2 = applyExclusions(r3);
        int r32 = 0;
    L4:
        if (r32 >= r2.size()) goto L7;
        r0.add(Integer.valueOf(r2.get(r32).priority));
        r32 = r32 + 1;
        goto L4
    L7:
        return r0.size();
    }

    public void reset() {
        this.excludedServiceLocations.clear();
        this.excludedPriorities.clear();
        this.selectionsTaken.clear();
    }

    public BaseUrl selectBaseUrl(List<BaseUrl> r8) {
        List<BaseUrl> r82 = applyExclusions(r8);
        if (r82.size() < 2) goto L5;
        Collections.sort(r82, new BaseUrlExclusionList$$ExternalSyntheticLambda0());
        ArrayList r0 = new ArrayList();
        int r2 = r82.get(0).priority;
        int r3 = 0;
    L8:
        if (r3 >= r82.size()) goto L16;
        BaseUrl r4 = r82.get(r3);
        if (r2 != r4.priority) goto L12;
        r0.add(new Pair(r4.serviceLocation, Integer.valueOf(r4.weight)));
        r3 = r3 + 1;
        goto L8
    L12:
        if (r0.size() != 1) goto L16;
        return r82.get(0);
    L16:
        BaseUrl r22 = this.selectionsTaken.get(r0);
        if (r22 != null) goto L20;
        BaseUrl r83 = selectWeighted(r82.subList(0, r0.size()));
        this.selectionsTaken.put(r0, r83);
        return r83;
    L20:
        return r22;
    L5:
        return (BaseUrl) Iterables.getFirst(r82, null);
    }
}
