package kotlin.jvm;

import java.lang.annotation.Annotation;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.ClassBasedDeclarationContainer;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u001b\n\u0002\b\u0004\n\u0002\u0010\u0010\n\u0002\b\u0005\u001a#\u0010\u0014\u001a\u00020\u0015\"\n\b\u0000\u0010\u0002\u0018\u0001*\u00020\t*\u0006\u0012\u0002\b\u00030\u0016H\u0086\u0080\u0004¢\u0006\u0002\u0010\u0017\"1\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00038GX\u0086\u0084\b¢\u0006\f\u0012\u0004\b\u0004\u0010\u0005\u001a\u0004\b\u0006\u0010\u0007\"1\u0010\b\u001a\n\u0012\u0004\u0012\u0002H\u0002\u0018\u00010\u0001\"\b\b\u0000\u0010\u0002*\u00020\t*\b\u0012\u0004\u0012\u0002H\u00020\u00038FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\n\u0010\u0007\"/\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\t*\b\u0012\u0004\u0012\u0002H\u00020\u00038FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\f\u0010\u0007\"/\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0003\"\b\b\u0000\u0010\u0002*\u00020\t*\b\u0012\u0004\u0012\u0002H\u00020\u00018GX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f\"*\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\t*\u0002H\u00028Æ\u0002X\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0011\"<\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\t*\b\u0012\u0004\u0012\u0002H\u00020\u00038Ç\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b\u0012\u0010\u0005\u001a\u0004\b\u0013\u0010\u0007\"+\u0010\u0018\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00020\u0003\"\b\b\u0000\u0010\u0002*\u00020\u0019*\u0002H\u00028FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001b\"<\u0010\u001c\u001a\b\u0012\u0004\u0012\u0002H\u001d0\u0001\"\u000e\b\u0000\u0010\u001d*\b\u0012\u0004\u0012\u0002H\u001d0\u001e*\b\u0012\u0004\u0012\u0002H\u001d0\u001e8Æ\u0002X\u0087\u0084\b¢\u0006\f\u0012\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"¨\u0006#"}, d2 = {"java", "Ljava/lang/Class;", "T", "Lkotlin/reflect/KClass;", "getJavaClass$annotations", "(Lkotlin/reflect/KClass;)V", "getJavaClass", "(Lkotlin/reflect/KClass;)Ljava/lang/Class;", "javaPrimitiveType", _UrlKt.FRAGMENT_ENCODE_SET, "getJavaPrimitiveType", "javaObjectType", "getJavaObjectType", "kotlin", "getKotlinClass", "(Ljava/lang/Class;)Lkotlin/reflect/KClass;", "javaClass", "(Ljava/lang/Object;)Ljava/lang/Class;", "getRuntimeClassOfKClassInstance$annotations", "getRuntimeClassOfKClassInstance", "isArrayOf", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "([Ljava/lang/Object;)Z", "annotationClass", _UrlKt.FRAGMENT_ENCODE_SET, "getAnnotationClass", "(Ljava/lang/annotation/Annotation;)Lkotlin/reflect/KClass;", "declaringJavaClass", "E", _UrlKt.FRAGMENT_ENCODE_SET, "getDeclaringJavaClass$annotations", "(Ljava/lang/Enum;)V", "getDeclaringJavaClass", "(Ljava/lang/Enum;)Ljava/lang/Class;", "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@JvmName(name = "JvmClassMappingKt")
/* loaded from: classes.dex */
public final class JvmClassMappingKt {
    public static final <T extends Annotation> KClass<? extends T> getAnnotationClass(T r0) {
        return getKotlinClass(r0.annotationType());
    }

    private static final <E extends Enum<E>> Class<E> getDeclaringJavaClass(Enum<E> r0) {
        return r0.getDeclaringClass();
    }

    @SinceKotlin(version = "1.7")
    @InlineOnly
    public static /* synthetic */ void getDeclaringJavaClass$annotations(Enum r0) {
    }

    public static final <T> Class<T> getJavaClass(T r0) {
        return (Class<T>) r0.getClass();
    }

    @JvmName(name = "getJavaClass")
    public static final <T> Class<T> getJavaClass(KClass<T> r0) {
        return (Class<T>) ((ClassBasedDeclarationContainer) r0).getJClass();
    }

    public static /* synthetic */ void getJavaClass$annotations(KClass r0) {
    }

    public static final <T> Class<T> getJavaObjectType(KClass<T> r2) {
        Class<T> r22 = (Class<T>) ((ClassBasedDeclarationContainer) r2).getJClass();
        if (r22.isPrimitive() == true) goto L5;
        return r22;
    L5:
        String r0 = r22.getName();
        switch(r0.hashCode()) {
            case -1325958191: goto L41;
            case 104431: goto L37;
            case 3039496: goto L33;
            case 3052374: goto L29;
            case 3327612: goto L25;
            case 3625364: goto L21;
            case 64711720: goto L17;
            case 97526364: goto L13;
            case 109413500: goto L9;
            default: goto L53;
        };
    L53:
        return r22;
    L9:
        if (r0.equals("short") == false) goto L54;
        return Short.class;
    L54:
        return r22;
    L13:
        if (r0.equals("float") == false) goto L55;
        return Float.class;
    L55:
        return r22;
    L17:
        if (r0.equals("boolean") == false) goto L56;
        return Boolean.class;
    L56:
        return r22;
    L21:
        if (r0.equals("void") == false) goto L57;
        return Void.class;
    L57:
        return r22;
    L25:
        if (r0.equals("long") == false) goto L58;
        return Long.class;
    L58:
        return r22;
    L29:
        if (r0.equals("char") == false) goto L59;
        return Character.class;
    L59:
        return r22;
    L33:
        if (r0.equals("byte") == false) goto L60;
        return Byte.class;
    L60:
        return r22;
    L37:
        if (r0.equals("int") == false) goto L61;
        return Integer.class;
    L61:
        return r22;
    L41:
        if (r0.equals("double") == false) goto L62;
        return Double.class;
    L62:
        return r22;
    }

    public static final <T> Class<T> getJavaPrimitiveType(KClass<T> r1) {
        Class<T> r12 = (Class<T>) ((ClassBasedDeclarationContainer) r1).getJClass();
        if (r12.isPrimitive() == false) goto L5;
        return r12;
    L5:
        String r13 = r12.getName();
        switch(r13.hashCode()) {
            case -2056817302: goto L49;
            case -527879800: goto L44;
            case -515992664: goto L39;
            case 155276373: goto L34;
            case 344809556: goto L29;
            case 398507100: goto L24;
            case 398795216: goto L19;
            case 399092968: goto L14;
            case 761287205: goto L9;
            default: goto L54;
        };
    L54:
        return null;
    L9:
        if (r13.equals("java.lang.Double") == true) goto L12;
        return null;
    L12:
        return Double.TYPE;
    L14:
        if (r13.equals("java.lang.Void") == true) goto L17;
        return null;
    L17:
        return Void.TYPE;
    L19:
        if (r13.equals("java.lang.Long") == true) goto L22;
        return null;
    L22:
        return Long.TYPE;
    L24:
        if (r13.equals("java.lang.Byte") == true) goto L27;
        return null;
    L27:
        return Byte.TYPE;
    L29:
        if (r13.equals("java.lang.Boolean") == true) goto L32;
        return null;
    L32:
        return Boolean.TYPE;
    L34:
        if (r13.equals("java.lang.Character") == true) goto L37;
        return null;
    L37:
        return Character.TYPE;
    L39:
        if (r13.equals("java.lang.Short") == true) goto L42;
        return null;
    L42:
        return Short.TYPE;
    L44:
        if (r13.equals("java.lang.Float") == true) goto L47;
        return null;
    L47:
        return Float.TYPE;
    L49:
        if (r13.equals("java.lang.Integer") == true) goto L53;
        return null;
    L53:
        return Integer.TYPE;
    }

    @JvmName(name = "getKotlinClass")
    public static final <T> KClass<T> getKotlinClass(Class<T> r0) {
        return Reflection.getOrCreateKotlinClass(r0);
    }

    @JvmName(name = "getRuntimeClassOfKClassInstance")
    public static final <T> Class<KClass<T>> getRuntimeClassOfKClassInstance(KClass<T> r0) {
        return (Class<KClass<T>>) r0.getClass();
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Use 'java' property to get Java class corresponding to this Kotlin class or cast this instance to Any if you really want to get the runtime Java class of this implementation of KClass.", replaceWith = @ReplaceWith(expression = "(this as Any).javaClass", imports = {}))
    public static /* synthetic */ void getRuntimeClassOfKClassInstance$annotations(KClass r0) {
    }

    public static final /* synthetic */ boolean isArrayOf(Object[] r2) {
        Intrinsics.reifiedOperationMarker(4, "T");
        return Object.class.isAssignableFrom(r2.getClass().getComponentType());
    }
}
