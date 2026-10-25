package kotlin.jvm.internal;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.GenericDeclaration;
import java.util.List;
import java.util.Map;
import kotlin.SinceKotlin;
import kotlin.jvm.KotlinReflectionNotSupportedError;
import kotlin.reflect.KCallable;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KParameter;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVisibility;

/* loaded from: classes.dex */
public abstract class CallableReference implements KCallable, Serializable, KotlinGenericDeclaration {

    @SinceKotlin(version = "1.1")
    public static final Object NO_RECEIVER = null;

    @SinceKotlin(version = "1.4")
    private final boolean isTopLevel;

    @SinceKotlin(version = "1.4")
    private final String name;

    @SinceKotlin(version = "1.4")
    private final Class owner;

    @SinceKotlin(version = "1.1")
    protected final Object receiver;
    private transient KCallable reflected;

    @SinceKotlin(version = "1.4")
    private final String signature;

    @SinceKotlin(version = "1.2")
    public static class NoReceiver implements Serializable {
        private static final NoReceiver INSTANCE = null;

        static {
            INSTANCE = new NoReceiver();
        }

        private NoReceiver() {
        }

        public static /* synthetic */ NoReceiver access$000() {
            return INSTANCE;
        }

        private Object readResolve() {
            return INSTANCE;
        }
    }

    static {
        NO_RECEIVER = NoReceiver.access$000();
    }

    public CallableReference() {
        this(NO_RECEIVER);
    }

    @SinceKotlin(version = "1.1")
    public CallableReference(Object r7) {
        this(r7, null, null, null, false);
    }

    @SinceKotlin(version = "1.4")
    public CallableReference(Object r1, Class r2, String r3, String r4, boolean r5) {
        this.receiver = r1;
        this.owner = r2;
        this.name = r3;
        this.signature = r4;
        this.isTopLevel = r5;
    }

    @Override // kotlin.reflect.KCallable
    public Object call(Object... r1) {
        return getReflected().call(r1);
    }

    @Override // kotlin.reflect.KCallable
    public Object callBy(Map r1) {
        return getReflected().callBy(r1);
    }

    @SinceKotlin(version = "1.1")
    public KCallable compute() {
        KCallable r0 = this.reflected;
        if (r0 != null) goto L6;
        KCallable r02 = computeReflected();
        this.reflected = r02;
        return r02;
    L6:
        return r0;
    }

    public abstract KCallable computeReflected();

    @Override // kotlin.jvm.internal.KotlinGenericDeclaration
    public GenericDeclaration findJavaDeclaration() {
        return KotlinGenericDeclarationKt.findMethodBySignature(getOwner(), getSignature());
    }

    @Override // kotlin.reflect.KAnnotatedElement
    public List<Annotation> getAnnotations() {
        return getReflected().getAnnotations();
    }

    @SinceKotlin(version = "1.1")
    public Object getBoundReceiver() {
        return this.receiver;
    }

    @Override // kotlin.reflect.KCallable
    public String getName() {
        return this.name;
    }

    public KDeclarationContainer getOwner() {
        Class r0 = this.owner;
        if (r0 != null) goto L7;
        return null;
    L7:
        if (this.isTopLevel == false) goto L11;
        return Reflection.getOrCreateKotlinPackage(r0);
    L11:
        return Reflection.getOrCreateKotlinClass(r0);
    }

    @Override // kotlin.reflect.KCallable
    public List<KParameter> getParameters() {
        return getReflected().getParameters();
    }

    @SinceKotlin(version = "1.1")
    public KCallable getReflected() {
        KCallable r0 = compute();
        if (r0 == this) goto L6;
        return r0;
    L6:
        throw new KotlinReflectionNotSupportedError();
    }

    @Override // kotlin.reflect.KCallable
    public KType getReturnType() {
        return getReflected().getReturnType();
    }

    public String getSignature() {
        return this.signature;
    }

    @Override // kotlin.reflect.KCallable
    @SinceKotlin(version = "1.1")
    public List<KTypeParameter> getTypeParameters() {
        return getReflected().getTypeParameters();
    }

    @Override // kotlin.reflect.KCallable
    @SinceKotlin(version = "1.1")
    public KVisibility getVisibility() {
        return getReflected().getVisibility();
    }

    @Override // kotlin.reflect.KCallable
    @SinceKotlin(version = "1.1")
    public boolean isAbstract() {
        return getReflected().isAbstract();
    }

    @Override // kotlin.reflect.KCallable
    @SinceKotlin(version = "1.1")
    public boolean isFinal() {
        return getReflected().isFinal();
    }

    @Override // kotlin.reflect.KCallable
    @SinceKotlin(version = "1.1")
    public boolean isOpen() {
        return getReflected().isOpen();
    }

    @Override // kotlin.reflect.KCallable
    @SinceKotlin(version = "1.3")
    public boolean isSuspend() {
        return getReflected().isSuspend();
    }
}
