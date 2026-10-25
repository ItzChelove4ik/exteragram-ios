package me.vkryl.android.animator;

import android.graphics.RectF;
import android.view.animation.Interpolator;
import androidx.core.math.MathUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import me.vkryl.android.animator.FactorAnimator;
import me.vkryl.core.ArrayUtils;
import me.vkryl.core.lambda.Destroyable;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public final class ListAnimator<T> implements Iterable<Entry<T>> {
    private final ArrayList<Entry<T>> actualList;
    private final FactorAnimator animator;
    private final Callback callback;
    private final ArrayList<Entry<T>> entries;
    private boolean foundListChanges;
    private final Metadata metadata;

    /* renamed from: me.vkryl.android.animator.ListAnimator$1, reason: invalid class name */
    public class AnonymousClass1 implements FactorAnimator.Target {
        final /* synthetic */ ListAnimator this$0;

        public AnonymousClass1(ListAnimator r1) {
            this.this$0 = r1;
        }

        @Override // me.vkryl.android.animator.FactorAnimator.Target
        public void onFactorChangeFinished(int r1, float r2, FactorAnimator r3) {
            this.this$0.applyAnimation(r2);
        }

        @Override // me.vkryl.android.animator.FactorAnimator.Target
        public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
            this.this$0.applyAnimation(r2);
        }
    }

    public interface Callback extends MetadataCallback {
        void onItemsChanged(ListAnimator<?> r1);
    }

    public static class Entry<T> implements Comparable<Entry<T>> {
        private int index;
        private boolean isBeingRemoved;
        public final T item;
        private final VariableRect measuredPositionRect;
        private final VariableFloat measuredSpacingStart;
        private final VariableFloat position;
        private final VariableFloat visibility;

        /* renamed from: -$$Nest$fgetindex, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m3574$$Nest$fgetindex(Entry r0) {
            return r0.index;
        }

        /* renamed from: -$$Nest$fgetmeasuredPositionRect, reason: not valid java name */
        public static /* bridge */ /* synthetic */ VariableRect m3575$$Nest$fgetmeasuredPositionRect(Entry r0) {
            return r0.measuredPositionRect;
        }

        /* renamed from: -$$Nest$fgetmeasuredSpacingStart, reason: not valid java name */
        public static /* bridge */ /* synthetic */ VariableFloat m3576$$Nest$fgetmeasuredSpacingStart(Entry r0) {
            return r0.measuredSpacingStart;
        }

        /* renamed from: -$$Nest$fgetposition, reason: not valid java name */
        public static /* bridge */ /* synthetic */ VariableFloat m3577$$Nest$fgetposition(Entry r0) {
            return r0.position;
        }

        /* renamed from: -$$Nest$fgetvisibility, reason: not valid java name */
        public static /* bridge */ /* synthetic */ VariableFloat m3578$$Nest$fgetvisibility(Entry r0) {
            return r0.visibility;
        }

        /* renamed from: -$$Nest$fputindex, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3579$$Nest$fputindex(Entry r0, int r1) {
            r0.index = r1;
        }

        /* renamed from: -$$Nest$mapplyAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m3580$$Nest$mapplyAnimation(Entry r0, float r1) {
            return r0.applyAnimation(r1);
        }

        /* renamed from: -$$Nest$mfinishAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3581$$Nest$mfinishAnimation(Entry r0, boolean r1) {
            r0.finishAnimation(r1);
        }

        /* renamed from: -$$Nest$monPrepareAppear, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3582$$Nest$monPrepareAppear(Entry r0) {
            r0.onPrepareAppear();
        }

        /* renamed from: -$$Nest$monPrepareRemove, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3583$$Nest$monPrepareRemove(Entry r0) {
            r0.onPrepareRemove();
        }

        /* renamed from: -$$Nest$monRecycled, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3584$$Nest$monRecycled(Entry r0) {
            r0.onRecycled();
        }

        public Entry(T r3, int r4, boolean r5) {
            this.isBeingRemoved = false;
            this.item = r3;
            this.index = r4;
            if (r5 == false) goto L5;
            float r52 = 1.0f;
        L6:
            this.visibility = new VariableFloat(r52);
            this.position = new VariableFloat(r4);
            this.measuredPositionRect = new VariableRect();
            this.measuredSpacingStart = new VariableFloat(0.0f);
            finishAnimation(false);
            return;
        L5:
            r52 = 0.0f;
            goto L6
        }

        private boolean applyAnimation(float r5) {
            boolean r0 = this.position.applyAnimation(r5);
            if (this.visibility.applyAnimation(r5) == true) goto L7;
            if (r0 == true) goto L7;
            boolean r02 = false;
        L9:
            if (this.measuredPositionRect.applyAnimation(r5) == true) goto L13;
            if (r02 == true) goto L13;
            boolean r03 = false;
        L15:
            if (this.measuredSpacingStart.applyAnimation(r5) == true) goto L19;
            if (r03 == true) goto L19;
            boolean r04 = false;
        L20:
            T r4 = this.item;
            if ((r4 instanceof Animatable) == true) goto L23;
            return r04;
        L23:
            if (((Animatable) r4).applyAnimation(r5) == true) goto L27;
            if (r04 == true) goto L27;
            return false;
        L27:
            return true;
        L19:
            r04 = true;
        L13:
            r03 = true;
        L7:
            r02 = true;
            goto L9
        }

        private void finishAnimation(boolean r2) {
            this.position.finishAnimation(r2);
            this.visibility.finishAnimation(r2);
            this.measuredPositionRect.finishAnimation(r2);
            this.measuredSpacingStart.finishAnimation(r2);
            T r1 = this.item;
            if ((r1 instanceof Animatable) == false) goto L6;
            ((Animatable) r1).finishAnimation(r2);
            return;
        }

        private void onPrepareAppear() {
            this.visibility.setTo(1.0f);
            this.isBeingRemoved = false;
        }

        private void onPrepareRemove() {
            this.visibility.setTo(0.0f);
            this.isBeingRemoved = true;
        }

        private void onRecycled() {
            T r1 = this.item;
            if ((r1 instanceof Destroyable) == false) goto L6;
            ((Destroyable) r1).performDestroy();
            return;
        }

        @Override // java.lang.Comparable
        public /* bridge */ /* synthetic */ int compareTo(Object r1) {
            return compareTo((Entry) r1);
        }

        public int compareTo(Entry<T> r1) {
            return Integer.compare(this.index, r1.index);
        }

        public float getPosition() {
            return this.position.get();
        }

        public RectF getRectF() {
            return this.measuredPositionRect.toRectF();
        }

        public float getSpacingStart() {
            return this.measuredSpacingStart.get();
        }

        public float getVisibility() {
            return MathUtils.clamp(this.visibility.get(), 0.0f, 1.0f);
        }

        public boolean isAffectingList() {
            return !this.isBeingRemoved;
        }

        public boolean isJunk() {
            if (getVisibility() == 0.0f) goto L5;
            return false;
        L5:
            if (isAffectingList() == true) goto L10;
            return true;
        L10:
            return false;
        }
    }

    public interface Measurable {
        int getHeight();

        default int getSpacingEnd(boolean r1) {
            return 0;
        }

        default int getSpacingStart(boolean r1) {
            return 0;
        }

        int getWidth();
    }

    public static class Metadata {
        private final ListAnimator<?> context;
        private final VariableFloat maxItemHeight;
        private final VariableFloat maxItemWidth;
        private final MetadataCallback metadataCallback;
        private final VariableFloat size;
        private final VariableFloat totalHeight;
        private final VariableFloat totalVisibility;
        private final VariableFloat totalWidth;

        /* renamed from: -$$Nest$fgetmaxItemHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ VariableFloat m3585$$Nest$fgetmaxItemHeight(Metadata r0) {
            return r0.maxItemHeight;
        }

        /* renamed from: -$$Nest$fgetmaxItemWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ VariableFloat m3586$$Nest$fgetmaxItemWidth(Metadata r0) {
            return r0.maxItemWidth;
        }

        /* renamed from: -$$Nest$fgetmetadataCallback, reason: not valid java name */
        public static /* bridge */ /* synthetic */ MetadataCallback m3587$$Nest$fgetmetadataCallback(Metadata r0) {
            return r0.metadataCallback;
        }

        /* renamed from: -$$Nest$fgettotalHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ VariableFloat m3588$$Nest$fgettotalHeight(Metadata r0) {
            return r0.totalHeight;
        }

        /* renamed from: -$$Nest$fgettotalWidth, reason: not valid java name */
        public static /* bridge */ /* synthetic */ VariableFloat m3589$$Nest$fgettotalWidth(Metadata r0) {
            return r0.totalWidth;
        }

        /* renamed from: -$$Nest$msetSize, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m3590$$Nest$msetSize(Metadata r0, int r1, boolean r2) {
            r0.setSize(r1, r2);
        }

        private Metadata(ListAnimator<?> r3, MetadataCallback r4) {
            this.size = new VariableFloat(0.0f);
            this.totalVisibility = new VariableFloat(0.0f);
            this.maxItemWidth = new VariableFloat(0.0f);
            this.maxItemHeight = new VariableFloat(0.0f);
            this.totalWidth = new VariableFloat(0.0f);
            this.totalHeight = new VariableFloat(0.0f);
            this.context = r3;
            this.metadataCallback = r4;
        }

        public /* synthetic */ Metadata(ListAnimator r1, MetadataCallback r2, ListAnimatorIA r3) {
            this(r1, r2);
        }

        private void setSize(int r4, boolean r5) {
            VariableFloat r0 = this.size;
            float r1 = 0.0f;
            if (r5 == false) goto L9;
            r0.setTo(r4);
            VariableFloat r3 = this.totalVisibility;
            if (r4 <= 0) goto L7;
            r1 = 1.0f;
        L7:
            r3.setTo(r1);
            return;
        L9:
            r0.set(r4);
            VariableFloat r32 = this.totalVisibility;
            if (r4 <= 0) goto L12;
            r1 = 1.0f;
        L12:
            r32.set(r1);
        }

        public boolean applyAnimation(float r5) {
            boolean r0 = this.size.applyAnimation(r5);
            if (this.maxItemWidth.applyAnimation(r5) == true) goto L7;
            if (r0 == true) goto L7;
            boolean r02 = false;
        L9:
            if (this.maxItemHeight.applyAnimation(r5) == true) goto L13;
            if (r02 == true) goto L13;
            boolean r03 = false;
        L15:
            if (this.totalWidth.applyAnimation(r5) == true) goto L19;
            if (r03 == true) goto L19;
            boolean r04 = false;
        L21:
            if (this.totalHeight.applyAnimation(r5) == true) goto L25;
            if (r04 == true) goto L25;
            boolean r05 = false;
        L27:
            if (this.totalVisibility.applyAnimation(r5) == true) goto L31;
            if (r05 == true) goto L31;
            boolean r06 = false;
        L33:
            if (this.metadataCallback.onApplyMetadataAnimation(this.context, r5) == true) goto L37;
            if (r06 == true) goto L37;
            return false;
        L37:
            return true;
        L31:
            r06 = true;
        L25:
            r05 = true;
        L19:
            r04 = true;
        L13:
            r03 = true;
        L7:
            r02 = true;
            goto L9
        }

        public void finishAnimation(boolean r2) {
            this.size.finishAnimation(r2);
            this.maxItemWidth.finishAnimation(r2);
            this.maxItemHeight.finishAnimation(r2);
            this.totalWidth.finishAnimation(r2);
            this.totalHeight.finishAnimation(r2);
            this.totalVisibility.finishAnimation(r2);
            this.metadataCallback.onFinishMetadataAnimation(this.context, r2);
        }

        public float getTotalHeight() {
            return this.totalHeight.get();
        }

        public float getTotalVisibility() {
            return this.totalVisibility.get();
        }

        public float getTotalWidth() {
            return this.totalWidth.get();
        }
    }

    public interface MetadataCallback {
        default boolean hasChanges(ListAnimator<?> r1) {
            return false;
        }

        default boolean onApplyMetadataAnimation(ListAnimator<?> r1, float r2) {
            return false;
        }

        default void onFinishMetadataAnimation(ListAnimator<?> r1, boolean r2) {
        }

        default void onForceApplyChanges(ListAnimator<?> r1) {
        }

        default void onPrepareMetadataAnimation(ListAnimator<?> r1) {
        }
    }

    /* loaded from: classes5.dex */
    public interface ResetCallback<T> {
        void onItemAdded(T r1, boolean r2);

        void onItemRemoved(T r1);
    }

    public ListAnimator(Callback r9, Interpolator r10, long r11) {
        this.callback = r9;
        this.metadata = new Metadata(this, r9, null);
        this.entries = new ArrayList();
        this.actualList = new ArrayList();
        if (r10 != null) goto L5;
    L8:
        this.animator = null;
        return;
    L5:
        if (r11 <= 0) goto L8;
        this.animator = new FactorAnimator(0, new AnonymousClass1(this), r10, r11);
    }

    private int indexOfItem(T r5) {
        ArrayList<Entry<T>> r4 = this.entries;
        int r0 = 0;
        if (r5 != null) goto L10;
        int r52 = r4.size();
        int r1 = 0;
    L5:
        if (r1 >= r52) goto L16;
        Entry<T> r2 = r4.get(r1);
        r1 = r1 + 1;
        if (r2.item == null) goto L8;
        r0 = r0 + 1;
        goto L5
    L8:
        return r0;
    L16:
        return -1;
    L10:
        int r12 = r4.size();
        int r22 = 0;
    L11:
        if (r22 >= r12) goto L22;
        Entry<T> r3 = r4.get(r22);
        r22 = r22 + 1;
        if (r5.equals(r3.item) == true) goto L14;
        r0 = r0 + 1;
        goto L11
    L14:
        return r0;
    L22:
        return -1;
    }

    private void onApplyListChanges() {
        int r1 = 0;
        if (this.foundListChanges == false) goto L9;
        this.foundListChanges = false;
        FactorAnimator r5 = this.animator;
        if (r5 == null) goto L13;
        r5.animateTo(1.0f);
        return;
    L13:
        return;
    L9:
        if (this.animator != null) goto L15;
        ArrayList<Entry<T>> r52 = this.entries;
        int r0 = r52.size();
    L11:
        if (r1 >= r0) goto L16;
        Entry<T> r2 = r52.get(r1);
        r1 = r1 + 1;
        Entry<T> r22 = r2;
        Entry.m3578$$Nest$fgetvisibility(r22).setFrom(Entry.m3578$$Nest$fgetvisibility(r22).get());
        Entry.m3577$$Nest$fgetposition(r22).setFrom(Entry.m3577$$Nest$fgetposition(r22).get());
        goto L11
    L16:
        return;
    }

    private void onBeforeListChanged() {
        if (this.foundListChanges == true) goto L6;
        this.foundListChanges = true;
        stopAnimation(false);
        return;
    }

    private void removeJunk(boolean r6) {
        int r0 = this.entries.size() - 1;
        boolean r2 = false;
    L3:
        if (r0 < 0) goto L8;
        Entry<T> r3 = this.entries.get(r0);
        Entry.m3581$$Nest$mfinishAnimation(r3, r6);
        if (r3.isJunk() == false) goto L7;
        this.entries.remove(r0);
        Entry.m3584$$Nest$monRecycled(r3);
        r2 = true;
    L7:
        r0 = r0 - 1;
        goto L3
    L8:
        if (r2 == false) goto L10;
        this.entries.trimToSize();
    L10:
        this.metadata.finishAnimation(r6);
    }

    public void applyAnimation(float r8) {
        boolean r0 = this.metadata.applyAnimation(r8);
        ArrayList<Entry<T>> r1 = this.entries;
        int r2 = r1.size();
        int r4 = 0;
    L4:
        if (r4 >= r2) goto L11;
        Entry<T> r6 = r1.get(r4);
        r4 = r4 + 1;
        if (Entry.m3580$$Nest$mapplyAnimation(r6, r8) == true) goto L10;
        if (r0 == true) goto L10;
        r0 = false;
    L10:
        r0 = true;
        goto L4
    L11:
        if (r0 == false) goto L23;
        this.callback.onItemsChanged(this);
        if (r8 != 1.0f) goto L24;
        removeJunk(true);
        return;
    L24:
        return;
    }

    public void clear(boolean r2) {
        reset(null, r2);
    }

    public boolean compareContents(List<T> r5) {
        if (r5 == null) goto L19;
        if (r5.isEmpty() == true) goto L19;
        if (this.actualList.size() == r5.size()) goto L9;
        return false;
    L9:
        int r0 = 0;
    L11:
        if (r0 >= r5.size()) goto L16;
        if (this.actualList.get(r0).equals(r5.get(r0)) == false) goto L14;
        r0 = r0 + 1;
        goto L11
    L14:
        return false;
    L16:
        return true;
    L19:
        return this.actualList.isEmpty();
    }

    public Entry<T> getEntry(int r1) {
        return this.entries.get(r1);
    }

    public Metadata getMetadata() {
        return this.metadata;
    }

    @Override // java.lang.Iterable
    public Iterator<Entry<T>> iterator() {
        return this.entries.iterator();
    }

    public void measureImpl(boolean r19) {
        ArrayList<Entry<T>> r1 = this.actualList;
        int r2 = r1.size();
        int r4 = 0;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
        int r8 = 0;
    L3:
        if (r8 >= r2) goto L28;
        Entry<T> r9 = r1.get(r8);
        r8 = r8 + 1;
        Entry<T> r92 = r9;
        T r10 = r92.item;
        if ((r10 instanceof Measurable) == false) goto L26;
        Measurable r102 = (Measurable) r10;
        boolean r12 = true;
        if (Entry.m3574$$Nest$fgetindex(r92) != 0) goto L9;
        boolean r11 = true;
    L11:
        if ((Entry.m3574$$Nest$fgetindex(r92) + 1) == this.actualList.size()) goto L14;
        r12 = false;
    L14:
        int r112 = r102.getSpacingStart(r11);
        int r122 = r102.getSpacingEnd(r12);
        int r13 = r102.getWidth();
        int r103 = r102.getHeight();
        int r14 = ((r112 + r13) + r122) + r4;
        int r15 = ((r112 + r103) + r122) + r5;
        if (r19 == true) goto L17;
    L24:
        ArrayList<Entry<T>> r17 = r1;
        Entry.m3575$$Nest$fgetmeasuredPositionRect(r92).set(r4, r5, r14, r15);
        Entry.m3576$$Nest$fgetmeasuredSpacingStart(r92).set(r112);
    L25:
        r6 = Math.max(r6, r13);
        r7 = Math.max(r7, r103);
        r4 = r14;
        r5 = r15;
    L27:
        r1 = r17;
        goto L3
    L17:
        if (r92.getVisibility() <= 0.0f) goto L24;
        float r42 = r4;
        float r52 = r5;
        float r3 = r14;
        r17 = r1;
        float r16 = r15;
        if (Entry.m3575$$Nest$fgetmeasuredPositionRect(r92).differs(r42, r52, r3, r16) == false) goto L21;
        onBeforeListChanged();
        Entry.m3575$$Nest$fgetmeasuredPositionRect(r92).setTo(r42, r52, r3, r16);
    L21:
        float r32 = r112;
        if (Entry.m3576$$Nest$fgetmeasuredSpacingStart(r92).differs(r32) == false) goto L25;
        onBeforeListChanged();
        Entry.m3576$$Nest$fgetmeasuredSpacingStart(r92).setTo(r32);
        goto L25
    L9:
        r11 = false;
        goto L11
    L26:
        r17 = r1;
        goto L27
    L28:
        if (r19 == false) goto L36;
        ArrayList<Entry<T>> r18 = this.entries;
        int r22 = r18.size();
        int r33 = 0;
    L30:
        if (r33 >= r22) goto L36;
        Entry<T> r82 = r18.get(r33);
        r33 = r33 + 1;
        T r83 = r82.item;
        if ((r83 instanceof Animatable) == false) goto L30;
        if (((Animatable) r83).hasChanges() == false) goto L30;
        onBeforeListChanged();
    L36:
        ArrayList<Entry<T>> r110 = this.entries;
        int r23 = r110.size();
        int r34 = 0;
    L37:
        if (r34 >= r23) goto L46;
        Entry<T> r84 = r110.get(r34);
        r34 = r34 + 1;
        T r85 = r84.item;
        if ((r85 instanceof Animatable) == false) goto L37;
        Animatable r86 = (Animatable) r85;
        if (r19 == true) goto L43;
        r86.applyChanges();
        goto L37
    L43:
        if (r86.hasChanges() == false) goto L37;
        r86.prepareChanges();
        goto L37
    L46:
        Metadata r111 = this.metadata;
        if (r19 == false) goto L64;
        float r24 = r4;
        if (Metadata.m3589$$Nest$fgettotalWidth(r111).differs(r24) == false) goto L51;
        onBeforeListChanged();
        Metadata.m3589$$Nest$fgettotalWidth(this.metadata).setTo(r24);
    L51:
        float r25 = r5;
        if (Metadata.m3588$$Nest$fgettotalHeight(this.metadata).differs(r25) == false) goto L54;
        onBeforeListChanged();
        Metadata.m3588$$Nest$fgettotalHeight(this.metadata).setTo(r25);
    L54:
        float r26 = r6;
        if (Metadata.m3586$$Nest$fgetmaxItemWidth(this.metadata).differs(r26) == false) goto L57;
        onBeforeListChanged();
        Metadata.m3586$$Nest$fgetmaxItemWidth(this.metadata).setTo(r26);
    L57:
        float r27 = r7;
        if (Metadata.m3585$$Nest$fgetmaxItemHeight(this.metadata).differs(r27) == false) goto L61;
        onBeforeListChanged();
        Metadata.m3585$$Nest$fgetmaxItemHeight(this.metadata).setTo(r27);
    L61:
        if (Metadata.m3587$$Nest$fgetmetadataCallback(this.metadata).hasChanges(this) == false) goto L85;
        onBeforeListChanged();
        Metadata.m3587$$Nest$fgetmetadataCallback(this.metadata).onPrepareMetadataAnimation(this);
        return;
    L85:
        return;
    L64:
        Metadata.m3589$$Nest$fgettotalWidth(r111).set(r4);
        Metadata.m3588$$Nest$fgettotalHeight(this.metadata).set(r5);
        Metadata.m3586$$Nest$fgetmaxItemWidth(this.metadata).set(r6);
        Metadata.m3585$$Nest$fgetmaxItemHeight(this.metadata).set(r7);
        Metadata.m3587$$Nest$fgetmetadataCallback(this.metadata).onForceApplyChanges(this);
    }

    public void reset(List<T> r2, boolean r3) {
        reset(r2, r3, null);
    }

    public void reset(List<T> r11, boolean r12, ResetCallback<T> r13) {
        boolean r0 = false;
        if (r12 == true) goto L21;
        stopAnimation(false);
        int r122 = this.entries.size() - 1;
    L5:
        ArrayList<Entry<T>> r132 = this.entries;
        if (r122 < 0) goto L8;
        Entry.m3584$$Nest$monRecycled(r132.get(r122));
        r122 = r122 - 1;
        goto L5
    L8:
        r132.clear();
        this.actualList.clear();
        if (r11 == null) goto L11;
        int r123 = r11.size();
    L12:
        if (r123 <= 0) goto L18;
        this.entries.ensureCapacity(r123);
        this.actualList.ensureCapacity(r123);
        Iterator<T> r112 = r11.iterator();
    L15:
        if (r112.hasNext() == false) goto L17;
        Entry<T> r2 = new Entry(r112.next(), this.actualList.size(), true);
        this.entries.add(r2);
        this.actualList.add(r2);
        goto L15
    L17:
        this.entries.trimToSize();
        this.actualList.trimToSize();
    L18:
        Metadata.m3590$$Nest$msetSize(this.metadata, r123, false);
        measureImpl(false);
        this.callback.onItemsChanged(this);
        return;
    L11:
        r123 = 0;
        goto L12
    L21:
        if (compareContents(r11) == false) goto L23;
        return;
    L23:
        onBeforeListChanged();
        if (r11 == null) goto L82;
        if (r11.isEmpty() == true) goto L82;
        int r22 = 0;
        boolean r3 = false;
        int r4 = 0;
        boolean r5 = false;
    L29:
        if (r22 >= this.entries.size()) goto L64;
        Entry<T> r6 = this.entries.get(r22);
        int r8 = r11.indexOf(r6.item);
        if (r8 == (-1)) goto L52;
        r4 = r4 + 1;
        float r9 = r8;
        if (Entry.m3577$$Nest$fgetposition(r6).differs(r9) == false) goto L36;
        onBeforeListChanged();
        Entry.m3577$$Nest$fgetposition(r6).setTo(r9);
    L36:
        if (Entry.m3574$$Nest$fgetindex(r6) == r8) goto L46;
        Entry.m3579$$Nest$fputindex(r6, r8);
        if (r3 == false) goto L40;
    L43:
        r3 = true;
    L44:
        r5 = true;
        goto L46
    L40:
        if (r6.isAffectingList() == true) goto L43;
        r3 = false;
    L46:
        if (Entry.m3578$$Nest$fgetvisibility(r6).differs(1.0f) == false) goto L63;
        onBeforeListChanged();
        Entry.m3582$$Nest$monPrepareAppear(r6);
        this.actualList.add(r6);
        Metadata.m3590$$Nest$msetSize(this.metadata, this.actualList.size(), true);
        if (r13 == null) goto L50;
        r13.onItemAdded(r6.item, true);
    L50:
        r3 = true;
    L63:
        r22 = r22 + 1;
        goto L29
    L52:
        if (Entry.m3578$$Nest$fgetvisibility(r6).differs(0.0f) == false) goto L63;
        onBeforeListChanged();
        Entry.m3583$$Nest$monPrepareRemove(r6);
        ArrayList<Entry<T>> r7 = this.actualList;
        if (r3 == false) goto L56;
        boolean r72 = r7.remove(r6);
    L57:
        if (r72 == false) goto L61;
        Metadata.m3590$$Nest$msetSize(this.metadata, this.actualList.size(), true);
        if (r13 == null) goto L63;
        r13.onItemRemoved(r6.item);
        goto L63
    L61:
        Segment$$ExternalSyntheticBUOutline0.m();
        return;
    L56:
        r72 = ArrayUtils.removeSorted(r7, r6);
        goto L57
    L64:
        if (r3 == false) goto L67;
        Collections.sort(this.actualList);
    L67:
        if (r4 >= r11.size()) goto L80;
        ArrayList<Entry<T>> r124 = this.entries;
        r124.ensureCapacity(r124.size() + (r11.size() - r4));
        Iterator<T> r113 = r11.iterator();
        int r125 = 0;
    L70:
        if (r113.hasNext() == false) goto L80;
        T r23 = r113.next();
        if (indexOfItem(r23) != (-1)) goto L79;
        if (r125 == this.entries.size()) goto L76;
        r5 = true;
    L76:
        onBeforeListChanged();
        Entry<T> r32 = new Entry(r23, r125, false);
        Entry.m3582$$Nest$monPrepareAppear(r32);
        this.entries.add(r32);
        ArrayUtils.addSorted(this.actualList, r32);
        Metadata.m3590$$Nest$msetSize(this.metadata, this.actualList.size(), true);
        if (r13 == null) goto L79;
        r13.onItemAdded(r32.item, false);
    L79:
        r125 = r125 + 1;
    L80:
        r0 = r5;
    L97:
        if (r0 == false) goto L99;
        Collections.sort(this.entries);
    L99:
        measureImpl(true);
        onApplyListChanges();
        return;
    L82:
        if (this.foundListChanges == true) goto L89;
        ArrayList<Entry<T>> r114 = this.entries;
        int r24 = r114.size();
        int r33 = 0;
    L84:
        if (r33 >= r24) goto L89;
        Entry<T> r42 = r114.get(r33);
        r33 = r33 + 1;
        if (Entry.m3578$$Nest$fgetvisibility(r42).differs(0.0f) == false) goto L84;
        onBeforeListChanged();
    L89:
        if (this.foundListChanges == false) goto L97;
        ArrayList<Entry<T>> r115 = this.entries;
        int r25 = r115.size();
        int r34 = 0;
    L91:
        if (r34 >= r25) goto L97;
        Entry<T> r43 = r115.get(r34);
        r34 = r34 + 1;
        Entry<T> r44 = r43;
        if (Entry.m3578$$Nest$fgetvisibility(r44).differs(0.0f) == false) goto L91;
        onBeforeListChanged();
        Entry.m3583$$Nest$monPrepareRemove(r44);
        ArrayUtils.removeSorted(this.actualList, r44);
        Metadata.m3590$$Nest$msetSize(this.metadata, this.actualList.size(), true);
        if (r13 == null) goto L91;
        r13.onItemRemoved(r44.item);
        goto L91
    }

    public int size() {
        return this.entries.size();
    }

    public void stopAnimation(boolean r2) {
        FactorAnimator r0 = this.animator;
        if (r0 == null) goto L6;
        r0.cancel();
        removeJunk(r2);
        this.animator.forceFactor(0.0f);
        return;
    L6:
        removeJunk(r2);
    }
}
