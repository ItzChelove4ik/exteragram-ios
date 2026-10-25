package kotlin.jvm.internal;

import java.util.Arrays;
import kotlin.KotlinNullPointerException;
import kotlin.SinceKotlin;
import kotlin.UninitializedPropertyAccessException;

/* loaded from: classes.dex */
public class Intrinsics {

    @SinceKotlin(version = "1.4")
    /* loaded from: classes5.dex */
    public static class Kotlin {
        private Kotlin() {
        }
    }

    private Intrinsics() {
    }

    @SinceKotlin(version = "1.1")
    public static boolean areEqual(double r2, Double r4) {
        if (r4 != null) goto L4;
        return false;
    L4:
        if (r2 != r4.doubleValue()) goto L9;
        return true;
    L9:
        return false;
    }

    @SinceKotlin(version = "1.1")
    public static boolean areEqual(float r0, Float r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r0 != r1.floatValue()) goto L9;
        return true;
    L9:
        return false;
    }

    @SinceKotlin(version = "1.1")
    public static boolean areEqual(Double r2, double r3) {
        if (r2 != null) goto L4;
        return false;
    L4:
        if (r2.doubleValue() != r3) goto L9;
        return true;
    L9:
        return false;
    }

    @SinceKotlin(version = "1.1")
    public static boolean areEqual(Double r4, Double r5) {
        if (r4 != null) goto L7;
        if (r5 != null) goto L6;
        return true;
    L6:
        return false;
    L7:
        if (r5 != null) goto L9;
    L11:
        return false;
    L9:
        if (r4.doubleValue() != r5.doubleValue()) goto L11;
        return true;
    }

    @SinceKotlin(version = "1.1")
    public static boolean areEqual(Float r0, float r1) {
        if (r0 != null) goto L4;
        return false;
    L4:
        if (r0.floatValue() != r1) goto L9;
        return true;
    L9:
        return false;
    }

    @SinceKotlin(version = "1.1")
    public static boolean areEqual(Float r2, Float r3) {
        if (r2 != null) goto L7;
        if (r3 != null) goto L6;
        return true;
    L6:
        return false;
    L7:
        if (r3 != null) goto L9;
    L11:
        return false;
    L9:
        if (r2.floatValue() != r3.floatValue()) goto L11;
        return true;
    }

    public static boolean areEqual(Object r0, Object r1) {
        if (r0 != null) goto L9;
        if (r1 != null) goto L6;
        return true;
    L6:
        return false;
    L9:
        return r0.equals(r1);
    }

    public static void checkExpressionValueIsNotNull(Object r1, String r2) {
        if (r1 == null) goto L5;
        return;
    L5:
        throw ((IllegalStateException) sanitizeStackTrace(new IllegalStateException(r2 + " must not be null")));
    }

    public static void checkFieldIsNotNull(Object r0, String r1) {
        if (r0 == null) goto L5;
        return;
    L5:
        throw ((IllegalStateException) sanitizeStackTrace(new IllegalStateException(r1)));
    }

    public static void checkFieldIsNotNull(Object r2, String r3, String r4) {
        if (r2 == null) goto L5;
        return;
    L5:
        throw ((IllegalStateException) sanitizeStackTrace(new IllegalStateException("Field specified as non-null is null: " + r3 + "." + r4)));
    }

    public static void checkHasClass(String r4) {
        String r42 = r4.replace('/', '.');
        Class.forName(r42);     // Catch: ClassNotFoundException -> L5
        return;
    L5:
        e = move-exception;
        throw ((ClassNotFoundException) sanitizeStackTrace(new ClassNotFoundException("Class " + r42 + " is not found. Please update the Kotlin runtime to the latest version", e)));
    }

    public static void checkHasClass(String r4, String r5) {
        String r42 = r4.replace('/', '.');
        Class.forName(r42);     // Catch: ClassNotFoundException -> L5
        return;
    L5:
        e = move-exception;
        throw ((ClassNotFoundException) sanitizeStackTrace(new ClassNotFoundException("Class " + r42 + " is not found: this code requires the Kotlin runtime of version at least " + r5, e)));
    }

    public static void checkNotNull(Object r0) {
    }

    public static void checkNotNull(Object r0, String r1) {
    }

    public static void checkNotNullExpressionValue(Object r1, String r2) {
        if (r1 == null) goto L5;
        return;
    L5:
        throw ((NullPointerException) sanitizeStackTrace(new NullPointerException(r2 + " must not be null")));
    }

    public static void checkNotNullParameter(Object r0, String r1) {
        if (r0 != null) goto L5;
        throwParameterIsNullNPE(r1);
        return;
    }

    public static void checkParameterIsNotNull(Object r0, String r1) {
        if (r0 != null) goto L5;
        throwParameterIsNullIAE(r1);
        return;
    }

    public static void checkReturnedValueIsNotNull(Object r0, String r1) {
        if (r0 == null) goto L5;
        return;
    L5:
        throw ((IllegalStateException) sanitizeStackTrace(new IllegalStateException(r1)));
    }

    public static void checkReturnedValueIsNotNull(Object r2, String r3, String r4) {
        if (r2 == null) goto L5;
        return;
    L5:
        throw ((IllegalStateException) sanitizeStackTrace(new IllegalStateException("Method specified as non-null returned null: " + r3 + "." + r4)));
    }

    public static int compare(int r0, int r1) {
        if (r0 >= r1) goto L5;
        return -1;
    L5:
        if (r0 != r1) goto L8;
        return 0;
    L8:
        return 1;
    }

    public static int compare(long r0, long r2) {
        if (r0 >= r2) goto L6;
        return -1;
    L6:
        if (r0 != r2) goto L9;
        return 0;
    L9:
        return 1;
    }

    private static String createParameterIsNullExceptionMessage(String r4) {
        StackTraceElement[] r0 = Thread.currentThread().getStackTrace();
        String r1 = Intrinsics.class.getName();
        int r2 = 0;
    L4:
        if (r0[r2].getClassName().equals(r1) == true) goto L7;
        r2 = r2 + 1;
    L7:
        if (r0[r2].getClassName().equals(r1) == false) goto L9;
        r2 = r2 + 1;
        goto L7
    L9:
        StackTraceElement r02 = r0[r2];
        return "Parameter specified as non-null is null: method " + r02.getClassName() + "." + r02.getMethodName() + ", parameter " + r4;
    }

    public static void needClassReification() {
    }

    public static void needClassReification(String r0) {
    }

    public static void reifiedOperationMarker(int r0, String r1) {
    }

    public static void reifiedOperationMarker(int r0, String r1, String r2) {
    }

    private static <T extends Throwable> T sanitizeStackTrace(T r1) {
        return (T) sanitizeStackTrace(r1, Intrinsics.class.getName());
    }

    public static <T extends Throwable> T sanitizeStackTrace(T r5, String r6) {
        StackTraceElement[] r0 = r5.getStackTrace();
        int r1 = r0.length;
        int r2 = -1;
        int r3 = 0;
    L3:
        if (r3 >= r1) goto L8;
        if (r6.equals(r0[r3].getClassName()) == false) goto L7;
        r2 = r3;
    L7:
        r3 = r3 + 1;
        goto L3
    L8:
        r5.setStackTrace((StackTraceElement[]) Arrays.copyOfRange(r0, r2 + 1, r1));
        return r5;
    }

    public static String stringPlus(String r1, Object r2) {
        return r1 + r2;
    }

    public static void throwAssert() {
        throw ((AssertionError) sanitizeStackTrace(new AssertionError()));
    }

    public static void throwAssert(String r1) {
        throw ((AssertionError) sanitizeStackTrace(new AssertionError(r1)));
    }

    public static void throwIllegalArgument() {
        throw ((IllegalArgumentException) sanitizeStackTrace(new IllegalArgumentException()));
    }

    public static void throwIllegalArgument(String r1) {
        throw ((IllegalArgumentException) sanitizeStackTrace(new IllegalArgumentException(r1)));
    }

    public static void throwIllegalState() {
        throw ((IllegalStateException) sanitizeStackTrace(new IllegalStateException()));
    }

    public static void throwIllegalState(String r1) {
        throw ((IllegalStateException) sanitizeStackTrace(new IllegalStateException(r1)));
    }

    @SinceKotlin(version = "1.4")
    public static void throwJavaNpe() {
        throw ((NullPointerException) sanitizeStackTrace(new NullPointerException()));
    }

    @SinceKotlin(version = "1.4")
    public static void throwJavaNpe(String r1) {
        throw ((NullPointerException) sanitizeStackTrace(new NullPointerException(r1)));
    }

    public static void throwNpe() {
        throw ((KotlinNullPointerException) sanitizeStackTrace(new KotlinNullPointerException()));
    }

    public static void throwNpe(String r1) {
        throw ((KotlinNullPointerException) sanitizeStackTrace(new KotlinNullPointerException(r1)));
    }

    private static void throwParameterIsNullIAE(String r1) {
        throw ((IllegalArgumentException) sanitizeStackTrace(new IllegalArgumentException(createParameterIsNullExceptionMessage(r1))));
    }

    private static void throwParameterIsNullNPE(String r1) {
        throw ((NullPointerException) sanitizeStackTrace(new NullPointerException(createParameterIsNullExceptionMessage(r1))));
    }

    public static void throwUndefinedForReified() {
    }

    public static void throwUndefinedForReified(String r1) {
        throw new UnsupportedOperationException(r1);
    }

    public static void throwUninitializedProperty(String r1) {
        throw ((UninitializedPropertyAccessException) sanitizeStackTrace(new UninitializedPropertyAccessException(r1)));
    }

    public static void throwUninitializedPropertyAccessException(String r0) {
    }
}
