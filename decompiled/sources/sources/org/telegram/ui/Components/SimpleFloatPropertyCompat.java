package org.telegram.ui.Components;

import androidx.dynamicanimation.animation.FloatPropertyCompat;

/* loaded from: classes3.dex */
public class SimpleFloatPropertyCompat<T> extends FloatPropertyCompat<T> {
    private Getter<T> getter;
    private float multiplier;
    private Setter<T> setter;

    public interface Getter<T> {
        float get(T r1);
    }

    public interface Setter<T> {
        void set(T r1, float r2);
    }

    public SimpleFloatPropertyCompat(String r1, Getter<T> r2, Setter<T> r3) {
        super(r1);
        this.multiplier = 1.0f;
        this.getter = r2;
        this.setter = r3;
    }

    @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
    public float getValue(T r2) {
        return this.getter.get(r2) * this.multiplier;
    }

    public SimpleFloatPropertyCompat<T> setMultiplier(float r1) {
        this.multiplier = r1;
        return this;
    }

    @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
    public void setValue(T r2, float r3) {
        this.setter.set(r2, r3 / this.multiplier);
    }
}
