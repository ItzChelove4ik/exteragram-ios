package androidx.vectordrawable.graphics.drawable;

import android.R;

/* loaded from: classes4.dex */
abstract class AndroidResources {
    static final int[] STYLEABLE_ANIMATED_VECTOR_DRAWABLE = null;
    static final int[] STYLEABLE_ANIMATED_VECTOR_DRAWABLE_TARGET = null;
    public static final int[] STYLEABLE_ANIMATOR = null;
    public static final int[] STYLEABLE_ANIMATOR_SET = null;
    public static final int[] STYLEABLE_KEYFRAME = null;
    public static final int[] STYLEABLE_PATH_INTERPOLATOR = null;
    public static final int[] STYLEABLE_PROPERTY_ANIMATOR = null;
    public static final int[] STYLEABLE_PROPERTY_VALUES_HOLDER = null;
    static final int[] STYLEABLE_VECTOR_DRAWABLE_CLIP_PATH = null;
    static final int[] STYLEABLE_VECTOR_DRAWABLE_GROUP = null;
    static final int[] STYLEABLE_VECTOR_DRAWABLE_PATH = null;
    static final int[] STYLEABLE_VECTOR_DRAWABLE_TYPE_ARRAY = null;

    static {
        STYLEABLE_VECTOR_DRAWABLE_TYPE_ARRAY = new int[]{R.attr.name, R.attr.tint, R.attr.height, R.attr.width, R.attr.alpha, R.attr.autoMirrored, R.attr.tintMode, R.attr.viewportWidth, R.attr.viewportHeight};
        STYLEABLE_VECTOR_DRAWABLE_GROUP = new int[]{R.attr.name, R.attr.pivotX, R.attr.pivotY, R.attr.scaleX, R.attr.scaleY, R.attr.rotation, R.attr.translateX, R.attr.translateY};
        STYLEABLE_VECTOR_DRAWABLE_PATH = new int[]{R.attr.name, R.attr.fillColor, R.attr.pathData, R.attr.strokeColor, R.attr.strokeWidth, R.attr.trimPathStart, R.attr.trimPathEnd, R.attr.trimPathOffset, R.attr.strokeLineCap, R.attr.strokeLineJoin, R.attr.strokeMiterLimit, R.attr.strokeAlpha, R.attr.fillAlpha, R.attr.fillType};
        STYLEABLE_VECTOR_DRAWABLE_CLIP_PATH = new int[]{R.attr.name, R.attr.pathData, R.attr.fillType};
        STYLEABLE_ANIMATED_VECTOR_DRAWABLE = new int[]{R.attr.drawable};
        STYLEABLE_ANIMATED_VECTOR_DRAWABLE_TARGET = new int[]{R.attr.name, R.attr.animation};
        STYLEABLE_ANIMATOR = new int[]{R.attr.interpolator, R.attr.duration, R.attr.startOffset, R.attr.repeatCount, R.attr.repeatMode, R.attr.valueFrom, R.attr.valueTo, R.attr.valueType};
        STYLEABLE_ANIMATOR_SET = new int[]{R.attr.ordering};
        STYLEABLE_PROPERTY_VALUES_HOLDER = new int[]{R.attr.valueFrom, R.attr.valueTo, R.attr.valueType, R.attr.propertyName};
        STYLEABLE_KEYFRAME = new int[]{R.attr.value, R.attr.interpolator, R.attr.valueType, R.attr.fraction};
        STYLEABLE_PROPERTY_ANIMATOR = new int[]{R.attr.propertyName, R.attr.pathData, R.attr.propertyXName, R.attr.propertyYName};
        STYLEABLE_PATH_INTERPOLATOR = new int[]{R.attr.controlX1, R.attr.controlY1, R.attr.controlX2, R.attr.controlY2, R.attr.pathData};
    }
}
