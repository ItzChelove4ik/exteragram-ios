package org.telegram.ui.Components;

import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.Property;
import android.view.animation.OvershootInterpolator;
import org.telegram.messenger.ImageReceiver;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.PhotoViewer;

/* loaded from: classes7.dex */
public abstract class AnimationProperties {
    public static final Property<ClippingImageView, Float> CLIPPING_IMAGE_VIEW_PROGRESS = null;
    public static final Property<DialogCell, Float> CLIP_DIALOG_CELL_PROGRESS = null;
    public static final Property<Drawable, Integer> COLOR_DRAWABLE_ALPHA = null;
    public static final Property<Drawable, Integer> DRAWABLE_ALPHA = null;
    public static final Property<ImageReceiver, Float> IMAGE_RECEIVER_ALPHA = null;
    public static final Property<Paint, Integer> PAINT_ALPHA = null;
    public static final Property<Paint, Integer> PAINT_COLOR = null;
    public static final Property<PhotoViewer, Float> PHOTO_VIEWER_ANIMATION_VALUE = null;
    public static final Property<ShapeDrawable, Integer> SHAPE_DRAWABLE_ALPHA = null;
    public static OvershootInterpolator overshootInterpolator;

    /* renamed from: org.telegram.ui.Components.AnimationProperties$1, reason: invalid class name */
    public class AnonymousClass1 extends IntProperty<Paint> {
        public AnonymousClass1(String r1) {
            super(r1);
        }

        public Integer get(Paint r1) {
            return Integer.valueOf(r1.getAlpha());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Integer get(Object r1) {
            return get((Paint) r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(Paint r1, int r2) {
            r1.setAlpha(r2);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.IntProperty
        public /* bridge */ /* synthetic */ void setValue(Paint r1, int r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.AnimationProperties$2, reason: invalid class name */
    public class AnonymousClass2 extends IntProperty<Paint> {
        public AnonymousClass2(String r1) {
            super(r1);
        }

        public Integer get(Paint r1) {
            return Integer.valueOf(r1.getColor());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Integer get(Object r1) {
            return get((Paint) r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(Paint r1, int r2) {
            r1.setColor(r2);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.IntProperty
        public /* bridge */ /* synthetic */ void setValue(Paint r1, int r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.AnimationProperties$3, reason: invalid class name */
    public class AnonymousClass3 extends FloatProperty<ImageReceiver> {
        public AnonymousClass3(String r1) {
            super(r1);
        }

        public Float get(ImageReceiver r1) {
            return Float.valueOf(r1.getCurrentAlpha());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((ImageReceiver) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(ImageReceiver r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(ImageReceiver r1, float r2) {
            r1.setCurrentAlpha(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.AnimationProperties$4, reason: invalid class name */
    public class AnonymousClass4 extends IntProperty<Drawable> {
        public AnonymousClass4(String r1) {
            super(r1);
        }

        public Integer get(Drawable r1) {
            return Integer.valueOf(r1.getAlpha());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Integer get(Object r1) {
            return get((Drawable) r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(Drawable r1, int r2) {
            r1.setAlpha(r2);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.IntProperty
        public /* bridge */ /* synthetic */ void setValue(Drawable r1, int r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.AnimationProperties$5, reason: invalid class name */
    public class AnonymousClass5 extends IntProperty<ShapeDrawable> {
        public AnonymousClass5(String r1) {
            super(r1);
        }

        public Integer get(ShapeDrawable r1) {
            return Integer.valueOf(r1.getPaint().getAlpha());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Integer get(Object r1) {
            return get((ShapeDrawable) r1);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(ShapeDrawable r1, int r2) {
            r1.getPaint().setAlpha(r2);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.IntProperty
        public /* bridge */ /* synthetic */ void setValue(ShapeDrawable r1, int r2) {
            setValue2(r1, r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.AnimationProperties$6, reason: invalid class name */
    public class AnonymousClass6 extends FloatProperty<ClippingImageView> {
        public AnonymousClass6(String r1) {
            super(r1);
        }

        public Float get(ClippingImageView r1) {
            return Float.valueOf(r1.getAnimationProgress());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((ClippingImageView) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(ClippingImageView r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(ClippingImageView r1, float r2) {
            r1.setAnimationProgress(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.AnimationProperties$7, reason: invalid class name */
    public class AnonymousClass7 extends FloatProperty<PhotoViewer> {
        public AnonymousClass7(String r1) {
            super(r1);
        }

        public Float get(PhotoViewer r1) {
            return Float.valueOf(r1.getAnimationValue());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((PhotoViewer) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(PhotoViewer r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(PhotoViewer r1, float r2) {
            r1.setAnimationValue(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.AnimationProperties$8, reason: invalid class name */
    public class AnonymousClass8 extends FloatProperty<DialogCell> {
        public AnonymousClass8(String r1) {
            super(r1);
        }

        public Float get(DialogCell r1) {
            return Float.valueOf(r1.getClipProgress());
        }

        @Override // android.util.Property
        public /* bridge */ /* synthetic */ Float get(Object r1) {
            return get((DialogCell) r1);
        }

        @Override // org.telegram.ui.Components.AnimationProperties.FloatProperty
        public /* bridge */ /* synthetic */ void setValue(DialogCell r1, float r2) {
            setValue2(r1, r2);
        }

        /* renamed from: setValue, reason: avoid collision after fix types in other method */
        public void setValue2(DialogCell r1, float r2) {
            r1.setClipProgress(r2);
        }
    }

    /* loaded from: classes3.dex */
    public static abstract class FloatProperty<T> extends Property<T, Float> {
        public FloatProperty(String r2) {
            super(Float.class, r2);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public final void set2(T r1, Float r2) {
            setValue(r1, r2.floatValue());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(Object r1, Float r2) {
            set2(r1, r2);
        }

        public abstract void setValue(T r1, float r2);
    }

    public static abstract class IntProperty<T> extends Property<T, Integer> {
        public IntProperty(String r2) {
            super(Integer.class, r2);
        }

        /* renamed from: set, reason: avoid collision after fix types in other method */
        public final void set2(T r1, Integer r2) {
            setValue(r1, r2.intValue());
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // android.util.Property
        public /* bridge */ /* synthetic */ void set(Object r1, Integer r2) {
            set2(r1, r2);
        }

        public abstract void setValue(T r1, int r2);
    }

    static {
        overshootInterpolator = new OvershootInterpolator(1.9f);
        PAINT_ALPHA = new AnonymousClass1("alpha");
        PAINT_COLOR = new AnonymousClass2("color");
        IMAGE_RECEIVER_ALPHA = new AnonymousClass3("currentAlpha");
        AnonymousClass4 r0 = new AnonymousClass4("alpha");
        DRAWABLE_ALPHA = r0;
        COLOR_DRAWABLE_ALPHA = r0;
        SHAPE_DRAWABLE_ALPHA = new AnonymousClass5("alpha");
        CLIPPING_IMAGE_VIEW_PROGRESS = new AnonymousClass6("animationProgress");
        PHOTO_VIEWER_ANIMATION_VALUE = new AnonymousClass7("animationValue");
        CLIP_DIALOG_CELL_PROGRESS = new AnonymousClass8("clipProgress");
    }
}
