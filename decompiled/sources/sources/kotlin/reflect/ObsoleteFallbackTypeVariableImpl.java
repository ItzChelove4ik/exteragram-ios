package kotlin.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003B\u0011\bF\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\b\u001a\u00020\tH\u0016J\b\u0010\n\u001a\u00020\u0002H\u0016J\u0013\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0016¢\u0006\u0002\u0010\u000eJ\n\u0010\u000f\u001a\u00020\tH\u0096\u0080\u0004J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0096\u0082\u0004J\n\u0010\u0014\u001a\u00020\u0015H\u0096\u0080\u0004J\n\u0010\u0016\u001a\u00020\tH\u0096\u0080\u0004J)\u0010\u0017\u001a\u0004\u0018\u0001H\u0018\"\b\b\u0000\u0010\u0018*\u00020\u00192\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001bH\u0086\u0080\u0004¢\u0006\u0002\u0010\u001cJ\u0015\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00190\fH\u0086\u0080\u0004¢\u0006\u0002\u0010\u001eJ\u0015\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00190\fH\u0086\u0080\u0004¢\u0006\u0002\u0010\u001eR\u000f\u0010\u0004\u001a\u00020\u0005X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lkotlin/reflect/ObsoleteFallbackTypeVariableImpl;", "Ljava/lang/reflect/TypeVariable;", "Ljava/lang/reflect/GenericDeclaration;", "Lkotlin/reflect/TypeImpl;", "typeParameter", "Lkotlin/reflect/KTypeParameter;", "<init>", "(Lkotlin/reflect/KTypeParameter;)V", "getName", _UrlKt.FRAGMENT_ENCODE_SET, "getGenericDeclaration", "getBounds", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/reflect/Type;", "()[Ljava/lang/reflect/Type;", "getTypeName", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "getAnnotation", "T", _UrlKt.FRAGMENT_ENCODE_SET, "annotationClass", "Ljava/lang/Class;", "(Ljava/lang/Class;)Ljava/lang/annotation/Annotation;", "getAnnotations", "()[Ljava/lang/annotation/Annotation;", "getDeclaredAnnotations", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@ExperimentalStdlibApi
@SourceDebugExtension({"SMAP\nTypesJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TypesJVM.kt\nkotlin/reflect/ObsoleteFallbackTypeVariableImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,244:1\n1586#2:245\n1661#2,3:246\n37#3,2:249\n*S KotlinDebug\n*F\n+ 1 TypesJVM.kt\nkotlin/reflect/ObsoleteFallbackTypeVariableImpl\n*L\n130#1:245\n130#1:246,3\n130#1:249,2\n*E\n"})
/* loaded from: classes5.dex */
final class ObsoleteFallbackTypeVariableImpl implements TypeVariable<GenericDeclaration>, TypeImpl {
    private final KTypeParameter typeParameter;

    public ObsoleteFallbackTypeVariableImpl(KTypeParameter r1) {
        this.typeParameter = r1;
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof TypeVariable) == false) goto L10;
        TypeVariable r32 = (TypeVariable) r3;
        if (Intrinsics.areEqual(getName(), r32.getName()) == true) goto L7;
        return false;
    L7:
        if (Intrinsics.areEqual(getGenericDeclaration(), r32.getGenericDeclaration()) == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    public final <T extends Annotation> T getAnnotation(Class<T> r1) {
        return null;
    }

    public final Annotation[] getAnnotations() {
        return new Annotation[0];
    }

    @Override // java.lang.reflect.TypeVariable
    public Type[] getBounds() {
        List<KType> r3 = this.typeParameter.getUpperBounds();
        ArrayList r0 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r3, 10));
        Iterator<T> r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L7;
        r0.add(TypesJVMKt.access$computeJavaType((KType) r32.next(), true));
        goto L4
    L7:
        return (Type[]) r0.toArray(new Type[0]);
    }

    public final Annotation[] getDeclaredAnnotations() {
        return new Annotation[0];
    }

    @Override // java.lang.reflect.TypeVariable
    public GenericDeclaration getGenericDeclaration() {
        throw new UnsupportedOperationException("getGenericDeclaration() is not supported for type variables created from KType: " + this.typeParameter + ".\nUpdate kotlin-reflect dependency to 2.3.20+.");
    }

    @Override // java.lang.reflect.TypeVariable
    public String getName() {
        return this.typeParameter.getName();
    }

    @Override // java.lang.reflect.Type, kotlin.reflect.TypeImpl
    public String getTypeName() {
        return getName();
    }

    public int hashCode() {
        int r0 = getName().hashCode();
        return getGenericDeclaration().hashCode() ^ r0;
    }

    public String toString() {
        return getTypeName();
    }
}
