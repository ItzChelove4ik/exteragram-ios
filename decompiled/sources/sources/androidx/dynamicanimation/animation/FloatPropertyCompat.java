package androidx.dynamicanimation.animation;

import android.util.FloatProperty;

/* loaded from: classes.dex */
public abstract class FloatPropertyCompat<T> {
    final String mPropertyName;

    /* renamed from: androidx.dynamicanimation.animation.FloatPropertyCompat$1, reason: invalid class name */
    /* loaded from: classes3.dex */
    public class AnonymousClass1 extends FloatPropertyCompat<T> {
        final /* synthetic */ FloatProperty val$property;

        public AnonymousClass1(String r1, FloatProperty r2) {
            this.val$property = r2;
            super(r1);
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public float getValue(T r1) {
            return ((Float) this.val$property.get(r1)).floatValue();
        }

        @Override // androidx.dynamicanimation.animation.FloatPropertyCompat
        public void setValue(T r1, float r2) {
            this.val$property.setValue(r1, r2);
        }
    }

    public FloatPropertyCompat(String r1) {
        this.mPropertyName = r1;
    }

    public static <T> FloatPropertyCompat<T> createFloatPropertyCompat(FloatProperty<T> r2) {
        return new AnonymousClass1(r2.getName(), r2);
    }

    public abstract float getValue(T r1);

    public abstract void setValue(T r1, float r2);
}
