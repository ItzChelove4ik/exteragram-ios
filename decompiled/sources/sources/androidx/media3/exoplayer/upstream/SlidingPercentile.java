package androidx.media3.exoplayer.upstream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/* loaded from: classes4.dex */
public class SlidingPercentile {
    private static final Comparator<Sample> INDEX_COMPARATOR = null;
    private static final Comparator<Sample> VALUE_COMPARATOR = null;
    private int currentSortOrder;
    private final int maxWeight;
    private int nextSampleIndex;
    private int recycledSampleCount;
    private final Sample[] recycledSamples;
    private final ArrayList<Sample> samples;
    private int totalWeight;

    /* renamed from: androidx.media3.exoplayer.upstream.SlidingPercentile$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class Sample {
        public int index;
        public float value;
        public int weight;

        private Sample() {
        }

        public /* synthetic */ Sample(AnonymousClass1 r1) {
            this();
        }
    }

    public static /* synthetic */ int $r8$lambda$3ijAM_deBUuKYuQw9XmdGgt4TZM(Sample r0, Sample r1) {
        return Float.compare(r0.value, r1.value);
    }

    /* renamed from: $r8$lambda$xVrpz1k64v1GGT5OvX-ezvbtrRg, reason: not valid java name */
    public static /* synthetic */ int m848$r8$lambda$xVrpz1k64v1GGT5OvXezvbtrRg(Sample r0, Sample r1) {
        return r0.index - r1.index;
    }

    static {
        INDEX_COMPARATOR = new SlidingPercentile$$ExternalSyntheticLambda0();
        VALUE_COMPARATOR = new SlidingPercentile$$ExternalSyntheticLambda1();
    }

    public SlidingPercentile(int r1) {
        this.maxWeight = r1;
        this.recycledSamples = new Sample[5];
        this.samples = new ArrayList();
        this.currentSortOrder = -1;
    }

    private void ensureSortedByIndex() {
        if (this.currentSortOrder == 1) goto L6;
        Collections.sort(this.samples, INDEX_COMPARATOR);
        this.currentSortOrder = 1;
        return;
    }

    private void ensureSortedByValue() {
        if (this.currentSortOrder == 0) goto L6;
        Collections.sort(this.samples, VALUE_COMPARATOR);
        this.currentSortOrder = 0;
        return;
    }

    public void addSample(int r4, float r5) {
        ensureSortedByIndex();
        int r0 = this.recycledSampleCount;
        if (r0 <= 0) goto L5;
        Sample[] r1 = this.recycledSamples;
        int r02 = r0 - 1;
        this.recycledSampleCount = r02;
        Sample r03 = r1[r02];
    L6:
        int r12 = this.nextSampleIndex;
        this.nextSampleIndex = r12 + 1;
        r03.index = r12;
        r03.weight = r4;
        r03.value = r5;
        this.samples.add(r03);
        this.totalWeight += r4;
    L7:
        int r42 = this.totalWeight;
        int r52 = this.maxWeight;
        if (r42 <= r52) goto L15;
        int r43 = r42 - r52;
        Sample r53 = this.samples.get(0);
        int r13 = r53.weight;
        if (r13 <= r43) goto L11;
        r53.weight = r13 - r43;
        this.totalWeight -= r43;
        goto L7
    L11:
        this.totalWeight -= r13;
        this.samples.remove(0);
        int r44 = this.recycledSampleCount;
        if (r44 >= 5) goto L7;
        Sample[] r04 = this.recycledSamples;
        this.recycledSampleCount = r44 + 1;
        r04[r44] = r53;
        goto L7
    L15:
        return;
    L5:
        r03 = new Sample(null);
        goto L6
    }

    public float getPercentile(float r5) {
        ensureSortedByValue();
        float r52 = r5 * this.totalWeight;
        int r0 = 0;
        int r1 = 0;
    L3:
        int r2 = this.samples.size();
        ArrayList<Sample> r3 = this.samples;
        if (r0 >= r2) goto L11;
        Sample r22 = r3.get(r0);
        r1 = r1 + r22.weight;
        if (r1 >= r52) goto L8;
        r0 = r0 + 1;
        goto L3
    L8:
        return r22.value;
    L11:
        if (r3.isEmpty() == false) goto L15;
        return Float.NaN;
    L15:
        return this.samples.get(r4.size() - 1).value;
    }

    public void reset() {
        this.samples.clear();
        this.currentSortOrder = -1;
        this.nextSampleIndex = 0;
        this.totalWeight = 0;
    }
}
