package kotlin.jvm.internal;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.reflect.KDeclarationContainer;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u0004\u0018\u00010\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\u0080\u0004\u001a\u000e\u0010\u0005\u001a\u00020\u0004*\u00020\u0006H\u0082\u0080\u0004\u001a\u001e\u0010\u0007\u001a\u00020\b*\u00060\tj\u0002`\n2\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0082\u0080\u0004¨\u0006\r"}, d2 = {"findMethodBySignature", "Ljava/lang/reflect/GenericDeclaration;", "Lkotlin/reflect/KDeclarationContainer;", "signature", _UrlKt.FRAGMENT_ENCODE_SET, "computeMethodSignature", "Ljava/lang/reflect/Method;", "appendClass", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "start", "Ljava/lang/Class;", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class KotlinGenericDeclarationKt {
    private static final void appendClass(Appendable r7, Class<?> r8) {
    L3:
        if (r8.isArray() == false) goto L6;
        r7.append("[");
        r8 = r8.getComponentType();
        goto L3
    L6:
        if (Intrinsics.areEqual(r8, Void.TYPE) == false) goto L10;
        r7.append("V");
        return;
    L10:
        if (Intrinsics.areEqual(r8, Integer.TYPE) == false) goto L14;
        r7.append("I");
        return;
    L14:
        if (Intrinsics.areEqual(r8, Long.TYPE) == false) goto L18;
        r7.append("J");
        return;
    L18:
        if (Intrinsics.areEqual(r8, Short.TYPE) == false) goto L22;
        r7.append("S");
        return;
    L22:
        if (Intrinsics.areEqual(r8, Byte.TYPE) == false) goto L26;
        r7.append("B");
        return;
    L26:
        if (Intrinsics.areEqual(r8, Boolean.TYPE) == false) goto L30;
        r7.append("Z");
        return;
    L30:
        if (Intrinsics.areEqual(r8, Character.TYPE) == false) goto L34;
        r7.append("C");
        return;
    L34:
        if (Intrinsics.areEqual(r8, Float.TYPE) == false) goto L38;
        r7.append("F");
        return;
    L38:
        if (Intrinsics.areEqual(r8, Double.TYPE) == false) goto L41;
        r7.append("D");
        return;
    L41:
        r7.append("L");
        r7.append(StringsKt.replace$default(r8.getName(), '.', '/', false, 4, null));
        r7.append(";");
    }

    private static final String computeMethodSignature(Method r5) {
        StringBuilder r0 = new StringBuilder();
        r0.append(r5.getName());
        r0.append("(");
        Class<?>[] r1 = r5.getParameterTypes();
        int r2 = r1.length;
        int r3 = 0;
    L3:
        if (r3 >= r2) goto L5;
        appendClass(r0, r1[r3]);
        r3 = r3 + 1;
        goto L3
    L5:
        r0.append(")");
        appendClass(r0, r5.getReturnType());
        return r0.toString();
    }

    public static final GenericDeclaration findMethodBySignature(KDeclarationContainer r6, String r7) {
        if ((r6 instanceof ClassBasedDeclarationContainer) == true) goto L5;
        return null;
    L5:
        String r0 = StringsKt.substringBefore$default(r7, '(', null, 2, null);
        if (Intrinsics.areEqual(r0, "<init>") == true) goto L17;
        Method[] r62 = ((ClassBasedDeclarationContainer) r6).getJClass().getDeclaredMethods();
        int r2 = r62.length;
        int r3 = 0;
    L8:
        if (r3 >= r2) goto L15;
        Method r4 = r62[r3];
        if (Intrinsics.areEqual(r4.getName(), r0) == false) goto L14;
        if (Intrinsics.areEqual(computeMethodSignature(r4), r7) == false) goto L14;
        return r4;
    L14:
        r3 = r3 + 1;
        goto L8
    L15:
        return null;
    L17:
        throw new UnsupportedOperationException("Generic Java constructors are not supported: " + r6 + '/' + r7);
    }
}
