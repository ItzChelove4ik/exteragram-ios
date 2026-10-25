package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;

/* loaded from: classes.dex */
public class FunctionReferenceImpl extends FunctionReference {
    @SinceKotlin(version = "1.4")
    public FunctionReferenceImpl(int r8, Class r9, String r10, String r11, int r12) {
        super(r8, CallableReference.NO_RECEIVER, r9, r10, r11, r12);
    }

    @SinceKotlin(version = "1.4")
    public FunctionReferenceImpl(int r1, Object r2, Class r3, String r4, String r5, int r6) {
        super(r1, r2, r3, r4, r5, r6);
    }

    public FunctionReferenceImpl(int r8, KDeclarationContainer r9, String r10, String r11) {
        super(r8, CallableReference.NO_RECEIVER, ((ClassBasedDeclarationContainer) r9).getJClass(), r10, r11, !(r9 instanceof KClass) ? 1 : 0);
    }
}
