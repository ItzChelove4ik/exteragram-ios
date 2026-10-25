package androidx.transition;

import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.Property;
import android.view.View;

/* loaded from: classes4.dex */
abstract class ViewUtils {
    static final Property<View, Rect> CLIP_BOUNDS = null;
    private static final ViewUtilsApi19 IMPL = null;
    static final Property<View, Float> TRANSITION_ALPHA = null;

    /* renamed from: androidx.transition.ViewUtils$1, reason: invalid class name */
    public class AnonymousClass1 extends Property<View, Float> {
        public AnonymousClass1(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public Float get2(View r1) {
            return Float.valueOf(ViewUtils.getTransitionAlpha(r1));
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(View r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(View r1, Float r2) {
            ViewUtils.setTransitionAlpha(r1, r2.floatValue());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(View r1, Float r2) {
            set2(r1, r2);
        }
    }

    /* renamed from: androidx.transition.ViewUtils$2, reason: invalid class name */
    public class AnonymousClass2 extends Property<View, Rect> {
        public AnonymousClass2(Class r1, String r2) {
            super(r1, r2);
        }

        /* renamed from: get, reason: avoid collision after fix types in other method */
        public Rect get2(View r1) {
            return r1.getClipBounds();
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Rect get(View r1) {
            return get2(r1);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public void set2(View r1, Rect r2) {
            r1.setClipBounds(r2);
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(View r1, Rect r2) {
            set2(r1, r2);
        }
    }

    static {
        if (Build.VERSION.SDK_INT < 29) goto L5;
        IMPL = new ViewUtilsApi29();
    L6:
        TRANSITION_ALPHA = new AnonymousClass1(Float.class, "translationAlpha");
        CLIP_BOUNDS = new AnonymousClass2(Rect.class, "clipBounds");
        return;
    L5:
        IMPL = new ViewUtilsApi23();
        goto L6
    }

    public static void clearNonTransitionAlpha(View r1) {
        IMPL.clearNonTransitionAlpha(r1);
    }

    public static float getTransitionAlpha(View r1) {
        return IMPL.getTransitionAlpha(r1);
    }

    public static void saveNonTransitionAlpha(View r1) {
        IMPL.saveNonTransitionAlpha(r1);
    }

    public static void setLeftTopRightBottom(View r6, int r7, int r8, int r9, int r10) {
        IMPL.setLeftTopRightBottom(r6, r7, r8, r9, r10);
    }

    public static void setTransitionAlpha(View r1, float r2) {
        IMPL.setTransitionAlpha(r1, r2);
    }

    public static void setTransitionVisibility(View r1, int r2) {
        IMPL.setTransitionVisibility(r1, r2);
    }

    public static void transformMatrixToGlobal(View r1, Matrix r2) {
        IMPL.transformMatrixToGlobal(r1, r2);
    }

    public static void transformMatrixToLocal(View r1, Matrix r2) {
        IMPL.transformMatrixToLocal(r1, r2);
    }
}
