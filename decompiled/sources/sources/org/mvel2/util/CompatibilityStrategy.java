package org.mvel2.util;

/* loaded from: classes5.dex */
public class CompatibilityStrategy {
    public static CompatibilityEvaluator compatibilityEvaluator;

    public interface CompatibilityEvaluator {
        boolean areComparisonCompatible(Class<?> r1, Class<?> r2);

        boolean areEqualityCompatible(Class<?> r1, Class<?> r2);
    }

    public static class DefaultCompatibilityEvaluator implements CompatibilityEvaluator {
        public DefaultCompatibilityEvaluator() {
        }

        private boolean arePrimitiveCompatible(Class<?> r4, Class<?> r5, boolean r6) {
            if (r4 != Boolean.TYPE) goto L9;
            if (r5 != Boolean.class) goto L7;
            return true;
        L7:
            return false;
        L9:
            if (r4 != Integer.TYPE) goto L13;
            return isBoxedNumber(r5, r6);
        L13:
            if (r4 != Long.TYPE) goto L17;
            return isBoxedNumber(r5, r6);
        L17:
            if (r4 != Double.TYPE) goto L21;
            return isBoxedNumber(r5, r6);
        L21:
            if (r4 != Float.TYPE) goto L25;
            return isBoxedNumber(r5, r6);
        L25:
            if (r4 != Character.TYPE) goto L31;
            if (r5 != Character.class) goto L29;
            return true;
        L29:
            return false;
        L31:
            if (r4 != Byte.TYPE) goto L37;
            if (r5 != Byte.class) goto L35;
            return true;
        L35:
            return false;
        L37:
            if (r4 == Short.TYPE) goto L39;
        L41:
            return false;
        L39:
            if (r5 != Short.class) goto L41;
            return true;
        }

        private boolean isBoxedNumber(Class<?> r1, boolean r2) {
            if (Number.class.isAssignableFrom(r1) == true) goto L10;
            if (r2 == true) goto L6;
            return false;
        L6:
            if (r1 == String.class) goto L13;
            return false;
        L13:
            return true;
        L10:
            return true;
        }

        @Override // org.mvel2.util.CompatibilityStrategy.CompatibilityEvaluator
        public boolean areComparisonCompatible(Class<?> r1, Class<?> r2) {
            return areEqualityCompatible(r1, r2);
        }

        @Override // org.mvel2.util.CompatibilityStrategy.CompatibilityEvaluator
        public boolean areEqualityCompatible(Class<?> r4, Class<?> r5) {
            if (r4 == NullType.class) goto L30;
            if (r5 == NullType.class) goto L30;
            if (r4.isAssignableFrom(r5) == true) goto L30;
            if (r5.isAssignableFrom(r4) == true) goto L30;
            if (isBoxedNumber(r4, false) == false) goto L17;
            if (isBoxedNumber(r5, true) == false) goto L17;
            return true;
        L17:
            if (r4.isPrimitive() == false) goto L26;
            if (r5.isPrimitive() == false) goto L21;
        L24:
            return true;
        L21:
            if (arePrimitiveCompatible(r4, r5, true) == true) goto L24;
            return false;
        L26:
            if (r5.isPrimitive() == true) goto L28;
            return false;
        L28:
            return arePrimitiveCompatible(r5, r4, false);
        L30:
            return true;
        }
    }

    static {
        compatibilityEvaluator = new DefaultCompatibilityEvaluator();
    }

    private CompatibilityStrategy() {
    }

    public static boolean areComparisonCompatible(Class<?> r1, Class<?> r2) {
        return compatibilityEvaluator.areComparisonCompatible(r1, r2);
    }

    public static boolean areEqualityCompatible(Class<?> r1, Class<?> r2) {
        return compatibilityEvaluator.areEqualityCompatible(r1, r2);
    }

    public static void setCompatibilityEvaluator(CompatibilityEvaluator r0) {
        compatibilityEvaluator = r0;
    }
}
