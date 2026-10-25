package com.google.android.material.animation;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.util.Property;
import androidx.collection.SimpleArrayMap;
import com.sun.jna.Native$$ExternalSyntheticBUOutline5;
import java.util.ArrayList;
import java.util.List;
import okio.Segment$$ExternalSyntheticBUOutline0;

/* loaded from: classes5.dex */
public class MotionSpec {
    private static final String TAG = "MotionSpec";
    private final SimpleArrayMap<String, PropertyValuesHolder[]> propertyValues;
    private final SimpleArrayMap<String, MotionTiming> timings;

    public MotionSpec() {
        this.timings = new SimpleArrayMap();
        this.propertyValues = new SimpleArrayMap();
    }

    private static void addInfoFromAnimator(MotionSpec r2, Animator r3) {
        if ((r3 instanceof ObjectAnimator) == false) goto L6;
        ObjectAnimator r32 = (ObjectAnimator) r3;
        r2.setPropertyValues(r32.getPropertyName(), r32.getValues());
        r2.setTiming(r32.getPropertyName(), MotionTiming.createFromAnimator(r32));
        return;
    L6:
        Native$$ExternalSyntheticBUOutline5.m("Animator must be an ObjectAnimator: ", r3);
    }

    private PropertyValuesHolder[] clonePropertyValuesHolder(PropertyValuesHolder[] r3) {
        PropertyValuesHolder[] r2 = new PropertyValuesHolder[r3.length];
        int r0 = 0;
    L4:
        if (r0 >= r3.length) goto L6;
        r2[r0] = r3[r0].clone();
        r0 = r0 + 1;
        goto L4
    L6:
        return r2;
    }

    public static MotionSpec createFromAttribute(Context r1, TypedArray r2, int r3) {
        if (r2.hasValue(r3) == false) goto L8;
        int r22 = r2.getResourceId(r3, 0);
        if (r22 != 0) goto L7;
        return null;
    L7:
        return createFromResource(r1, r22);
    L8:
        return null;
    }

    public static MotionSpec createFromResource(Context r3, int r4) {
        Animator r32 = AnimatorInflater.loadAnimator(r3, r4);     // Catch: Exception -> L7
        if ((r32 instanceof AnimatorSet) == true) goto L6;
        if (r32 == null) goto L12;
        ArrayList r1 = new ArrayList();     // Catch: Exception -> L7
        r1.add(r32);     // Catch: Exception -> L7
        return createSpecFromAnimators(r1);
    L12:
        return null;
    L6:
        return createSpecFromAnimators(((AnimatorSet) r32).getChildAnimations());
    L7:
        e = move-exception;
        Log.w(TAG, "Can't load animation resource ID #0x" + Integer.toHexString(r4), e);
        return null;
    }

    private static MotionSpec createSpecFromAnimators(List<Animator> r4) {
        MotionSpec r0 = new MotionSpec();
        int r1 = r4.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        addInfoFromAnimator(r0, r4.get(r2));
        r2 = r2 + 1;
        goto L3
    L5:
        return r0;
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof MotionSpec) == true) goto L10;
        return false;
    L10:
        return this.timings.equals(((MotionSpec) r2).timings);
    }

    public <T> ObjectAnimator getAnimator(String r2, T r3, Property<T, ?> r4) {
        ObjectAnimator r32 = ObjectAnimator.ofPropertyValuesHolder(r3, getPropertyValues(r2));
        r32.setProperty(r4);
        getTiming(r2).apply(r32);
        return r32;
    }

    public PropertyValuesHolder[] getPropertyValues(String r2) {
        if (hasPropertyValues(r2) == true) goto L5;
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        return clonePropertyValuesHolder(this.propertyValues.get(r2));
    }

    public MotionTiming getTiming(String r2) {
        if (hasTiming(r2) == true) goto L5;
        Segment$$ExternalSyntheticBUOutline0.m();
        return null;
    L5:
        return this.timings.get(r2);
    }

    public long getTotalDuration() {
        int r0 = this.timings.size();
        long r1 = 0;
        int r3 = 0;
    L3:
        if (r3 >= r0) goto L5;
        MotionTiming r4 = this.timings.valueAt(r3);
        r1 = Math.max(r1, r4.getDelay() + r4.getDuration());
        r3 = r3 + 1;
        goto L3
    L5:
        return r1;
    }

    public boolean hasPropertyValues(String r1) {
        if (this.propertyValues.get(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean hasTiming(String r1) {
        if (this.timings.get(r1) == null) goto L6;
        return true;
    L6:
        return false;
    }

    public int hashCode() {
        return this.timings.hashCode();
    }

    public void setPropertyValues(String r1, PropertyValuesHolder[] r2) {
        this.propertyValues.put(r1, r2);
    }

    public void setTiming(String r1, MotionTiming r2) {
        this.timings.put(r1, r2);
    }

    public String toString() {
        return "\n" + getClass().getName() + '{' + Integer.toHexString(System.identityHashCode(this)) + " timings: " + this.timings + "}\n";
    }
}
