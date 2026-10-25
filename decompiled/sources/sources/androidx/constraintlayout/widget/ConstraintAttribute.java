package androidx.constraintlayout.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.util.TypedValue;
import android.util.Xml;
import android.view.View;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes3.dex */
public class ConstraintAttribute {
    boolean mBooleanValue;
    private int mColorValue;
    private float mFloatValue;
    private int mIntegerValue;
    private boolean mMethod;
    String mName;
    private String mStringValue;
    private AttributeType mType;

    public enum AttributeType extends Enum<AttributeType> {
        private static final /* synthetic */ AttributeType[] $VALUES = null;
        public static final AttributeType BOOLEAN_TYPE = null;
        public static final AttributeType COLOR_DRAWABLE_TYPE = null;
        public static final AttributeType COLOR_TYPE = null;
        public static final AttributeType DIMENSION_TYPE = null;
        public static final AttributeType FLOAT_TYPE = null;
        public static final AttributeType INT_TYPE = null;
        public static final AttributeType REFERENCE_TYPE = null;
        public static final AttributeType STRING_TYPE = null;

        private static /* synthetic */ AttributeType[] $values() {
            return new AttributeType[]{INT_TYPE, FLOAT_TYPE, COLOR_TYPE, COLOR_DRAWABLE_TYPE, STRING_TYPE, BOOLEAN_TYPE, DIMENSION_TYPE, REFERENCE_TYPE};
        }

        static {
            INT_TYPE = new AttributeType("INT_TYPE", 0);
            FLOAT_TYPE = new AttributeType("FLOAT_TYPE", 1);
            COLOR_TYPE = new AttributeType("COLOR_TYPE", 2);
            COLOR_DRAWABLE_TYPE = new AttributeType("COLOR_DRAWABLE_TYPE", 3);
            STRING_TYPE = new AttributeType("STRING_TYPE", 4);
            BOOLEAN_TYPE = new AttributeType("BOOLEAN_TYPE", 5);
            DIMENSION_TYPE = new AttributeType("DIMENSION_TYPE", 6);
            REFERENCE_TYPE = new AttributeType("REFERENCE_TYPE", 7);
            $VALUES = $values();
        }

        AttributeType(String r1, int r2) {
        }

        public static AttributeType valueOf(String r1) {
            return (AttributeType) Enum.valueOf(AttributeType.class, r1);
        }

        public static AttributeType[] values() {
            return (AttributeType[]) $VALUES.clone();
        }
    }

    public ConstraintAttribute(ConstraintAttribute r2, Object r3) {
        this.mMethod = false;
        this.mName = r2.mName;
        this.mType = r2.mType;
        setValue(r3);
    }

    public ConstraintAttribute(String r1, AttributeType r2, Object r3, boolean r4) {
        this.mName = r1;
        this.mType = r2;
        this.mMethod = r4;
        setValue(r3);
    }

    public static HashMap<String, ConstraintAttribute> extractAttributes(HashMap<String, ConstraintAttribute> r10, View r11) {
        HashMap<String, ConstraintAttribute> r3 = new HashMap();
        Class<?> r4 = r11.getClass();
        Iterator<String> r5 = r10.keySet().iterator();
    L4:
        if (r5.hasNext() == false) goto L20;
        String r6 = r5.next();
        ConstraintAttribute r7 = r10.get(r6);
    L7:
        if (r6.equals("BackgroundColor") == true) goto L8;
        r3.put(r6, new ConstraintAttribute(r7, r4.getMethod("getMap" + r6, null).invoke(r11, null)));     // Catch: InvocationTargetException -> L9 IllegalAccessException -> L11 NoSuchMethodException -> L13
        goto L4
    L8:
        r3.put(r6, new ConstraintAttribute(r7, Integer.valueOf(((ColorDrawable) r11.getBackground()).getColor())));     // Catch: InvocationTargetException -> L9 IllegalAccessException -> L11 NoSuchMethodException -> L13
    L11:
        e = move-exception;
        Log.e("TransitionLayout", " Custom Attribute \"" + r6 + "\" not found on " + r4.getName(), e);
    L13:
        e = move-exception;
        Log.e("TransitionLayout", r4.getName() + " must have a method " + r6, e);
    L9:
        e = move-exception;
        Log.e("TransitionLayout", " Custom Attribute \"" + r6 + "\" not found on " + r4.getName(), e);
        goto L4
    L20:
        return r3;
    }

    public static void parse(Context r12, XmlPullParser r13, HashMap<String, ConstraintAttribute> r14) {
        TypedArray r132 = r12.obtainStyledAttributes(Xml.asAttributeSet(r13), R$styleable.CustomAttribute);
        int r0 = r132.getIndexCount();
        String r1 = null;
        Object r3 = null;
        AttributeType r4 = null;
        int r5 = 0;
        boolean r6 = false;
    L3:
        if (r5 >= r0) goto L46;
        int r7 = r132.getIndex(r5);
        if (r7 != R$styleable.CustomAttribute_attributeName) goto L12;
        r1 = r132.getString(r7);
        if (r1 == null) goto L45;
        if (r1.length() <= 0) goto L45;
        r1 = Character.toUpperCase(r1.charAt(0)) + r1.substring(1);
    L45:
        r5 = r5 + 1;
        goto L3
    L12:
        if (r7 != R$styleable.CustomAttribute_methodName) goto L15;
        r1 = r132.getString(r7);
        r6 = true;
        goto L45
    L15:
        if (r7 != R$styleable.CustomAttribute_customBoolean) goto L18;
        r3 = Boolean.valueOf(r132.getBoolean(r7, false));
        r4 = AttributeType.BOOLEAN_TYPE;
        goto L45
    L18:
        if (r7 != R$styleable.CustomAttribute_customColorValue) goto L22;
        AttributeType r32 = AttributeType.COLOR_TYPE;
        Object r42 = Integer.valueOf(r132.getColor(r7, 0));
    L20:
        Object r11 = r42;
        r4 = r32;
        r3 = r11;
        goto L45
    L22:
        if (r7 != R$styleable.CustomAttribute_customColorDrawableValue) goto L25;
        r32 = AttributeType.COLOR_DRAWABLE_TYPE;
        r42 = Integer.valueOf(r132.getColor(r7, 0));
        goto L20
    L25:
        if (r7 != R$styleable.CustomAttribute_customPixelDimension) goto L28;
        r32 = AttributeType.DIMENSION_TYPE;
        r42 = Float.valueOf(TypedValue.applyDimension(1, r132.getDimension(r7, 0.0f), r12.getResources().getDisplayMetrics()));
        goto L20
    L28:
        if (r7 != R$styleable.CustomAttribute_customDimension) goto L31;
        r32 = AttributeType.DIMENSION_TYPE;
        r42 = Float.valueOf(r132.getDimension(r7, 0.0f));
        goto L20
    L31:
        if (r7 != R$styleable.CustomAttribute_customFloatValue) goto L34;
        r32 = AttributeType.FLOAT_TYPE;
        r42 = Float.valueOf(r132.getFloat(r7, Float.NaN));
        goto L20
    L34:
        if (r7 != R$styleable.CustomAttribute_customIntegerValue) goto L37;
        r32 = AttributeType.INT_TYPE;
        r42 = Integer.valueOf(r132.getInteger(r7, -1));
        goto L20
    L37:
        if (r7 != R$styleable.CustomAttribute_customStringValue) goto L40;
        r32 = AttributeType.STRING_TYPE;
        r42 = r132.getString(r7);
        goto L20
    L40:
        if (r7 != R$styleable.CustomAttribute_customReference) goto L45;
        r32 = AttributeType.REFERENCE_TYPE;
        int r43 = r132.getResourceId(r7, -1);
        if (r43 != (-1)) goto L44;
        r43 = r132.getInt(r7, -1);
    L44:
        r42 = Integer.valueOf(r43);
        goto L20
    L46:
        if (r1 == null) goto L49;
        if (r3 == null) goto L49;
        r14.put(r1, new ConstraintAttribute(r1, r4, r3, r6));
    L49:
        r132.recycle();
    }

    public static void setAttributes(View r11, HashMap<String, ConstraintAttribute> r12) {
        Class<?> r3 = r11.getClass();
        Iterator<String> r4 = r12.keySet().iterator();
    L4:
        if (r4.hasNext() == false) goto L31;
        String r5 = r4.next();
        ConstraintAttribute r6 = r12.get(r5);
        if (r6.mMethod == true) goto L8;
        String r7 = "set" + r5;
    L32:
        int r8 = r6.mType.ordinal();     // Catch: InvocationTargetException -> L14 IllegalAccessException -> L16 NoSuchMethodException -> L18
    L10:
        Class r9 = Float.TYPE;
        Class r10 = Integer.TYPE;
        switch(r8) {
            case 0: goto L26;
            case 1: goto L25;
            case 2: goto L24;
            case 3: goto L23;
            case 4: goto L22;
            case 5: goto L21;
            case 6: goto L20;
            case 7: goto L13;
            default: goto L4;
        };
    L13:
        r3.getMethod(r7, new Class[]{r10}).invoke(r11, new Object[]{Integer.valueOf(r6.mIntegerValue)});     // Catch: InvocationTargetException -> L14 IllegalAccessException -> L16 NoSuchMethodException -> L18
        goto L4
    L20:
        r3.getMethod(r7, new Class[]{r9}).invoke(r11, new Object[]{Float.valueOf(r6.mFloatValue)});     // Catch: InvocationTargetException -> L14 IllegalAccessException -> L16 NoSuchMethodException -> L18
        goto L4
    L21:
        r3.getMethod(r7, new Class[]{Boolean.TYPE}).invoke(r11, new Object[]{Boolean.valueOf(r6.mBooleanValue)});     // Catch: InvocationTargetException -> L14 IllegalAccessException -> L16 NoSuchMethodException -> L18
        goto L4
    L22:
        r3.getMethod(r7, new Class[]{CharSequence.class}).invoke(r11, new Object[]{r6.mStringValue});     // Catch: InvocationTargetException -> L14 IllegalAccessException -> L16 NoSuchMethodException -> L18
        goto L4
    L23:
        Method r82 = r3.getMethod(r7, new Class[]{Drawable.class});     // Catch: InvocationTargetException -> L14 IllegalAccessException -> L16 NoSuchMethodException -> L18
        ColorDrawable r92 = new ColorDrawable();     // Catch: InvocationTargetException -> L14 IllegalAccessException -> L16 NoSuchMethodException -> L18
        r92.setColor(r6.mColorValue);     // Catch: InvocationTargetException -> L14 IllegalAccessException -> L16 NoSuchMethodException -> L18
        r82.invoke(r11, new Object[]{r92});     // Catch: InvocationTargetException -> L14 IllegalAccessException -> L16 NoSuchMethodException -> L18
        goto L4
    L24:
        r3.getMethod(r7, new Class[]{r10}).invoke(r11, new Object[]{Integer.valueOf(r6.mColorValue)});     // Catch: InvocationTargetException -> L14 IllegalAccessException -> L16 NoSuchMethodException -> L18
        goto L4
    L25:
        r3.getMethod(r7, new Class[]{r9}).invoke(r11, new Object[]{Float.valueOf(r6.mFloatValue)});     // Catch: InvocationTargetException -> L14 IllegalAccessException -> L16 NoSuchMethodException -> L18
        goto L4
    L26:
        r3.getMethod(r7, new Class[]{r10}).invoke(r11, new Object[]{Integer.valueOf(r6.mIntegerValue)});     // Catch: InvocationTargetException -> L14 IllegalAccessException -> L16 NoSuchMethodException -> L18
    L16:
        e = move-exception;
        Log.e("TransitionLayout", " Custom Attribute \"" + r5 + "\" not found on " + r3.getName(), e);
    L18:
        e = move-exception;
        Log.e("TransitionLayout", r3.getName() + " must have a method " + r7, e);
    L14:
        e = move-exception;
        Log.e("TransitionLayout", " Custom Attribute \"" + r5 + "\" not found on " + r3.getName(), e);
        goto L4
    L8:
        r7 = r5;
        goto L32
    }

    public void setValue(Object r2) {
        switch(this.mType.ordinal()) {
            case 0: goto L15;
            case 1: goto L13;
            case 2: goto L11;
            case 3: goto L11;
            case 4: goto L9;
            case 5: goto L7;
            case 6: goto L5;
            case 7: goto L15;
            default: goto L4;
        };
    L4:
        return;
    L5:
        this.mFloatValue = ((Float) r2).floatValue();
        return;
    L7:
        this.mBooleanValue = ((Boolean) r2).booleanValue();
        return;
    L9:
        this.mStringValue = (String) r2;
        return;
    L11:
        this.mColorValue = ((Integer) r2).intValue();
        return;
    L13:
        this.mFloatValue = ((Float) r2).floatValue();
        return;
    L15:
        this.mIntegerValue = ((Integer) r2).intValue();
    }
}
