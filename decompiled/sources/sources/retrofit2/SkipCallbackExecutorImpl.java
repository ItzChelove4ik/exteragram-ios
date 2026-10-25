package retrofit2;

import java.lang.annotation.Annotation;

/* loaded from: classes3.dex */
final class SkipCallbackExecutorImpl implements SkipCallbackExecutor {
    private static final SkipCallbackExecutor INSTANCE = null;

    static {
        INSTANCE = new SkipCallbackExecutorImpl();
    }

    public SkipCallbackExecutorImpl() {
    }

    public static Annotation[] ensurePresent(Annotation[] r4) {
        if (Utils.isAnnotationPresent(r4, SkipCallbackExecutor.class) == false) goto L5;
        return r4;
    L5:
        Annotation[] r0 = new Annotation[r4.length + 1];
        r0[0] = INSTANCE;
        System.arraycopy(r4, 0, r0, 1, r4.length);
        return r0;
    }

    @Override // java.lang.annotation.Annotation
    public Class<? extends Annotation> annotationType() {
        return SkipCallbackExecutor.class;
    }

    @Override // java.lang.annotation.Annotation
    public boolean equals(Object r1) {
        return r1 instanceof SkipCallbackExecutor;
    }

    @Override // java.lang.annotation.Annotation
    public int hashCode() {
        return 0;
    }

    @Override // java.lang.annotation.Annotation
    public String toString() {
        return "@" + SkipCallbackExecutor.class.getName() + "()";
    }
}
