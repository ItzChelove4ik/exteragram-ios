package kotlin.reflect;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u00012\u00020\u0002B-\bF\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\b¢\u0006\u0004\b\t\u0010\nJ\b\u0010\r\u001a\u00020\u0006H\u0016J\n\u0010\u000e\u001a\u0004\u0018\u00010\u0006H\u0016J\u0013\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bH\u0016¢\u0006\u0002\u0010\u0010J\n\u0010\u0011\u001a\u00020\u0012H\u0096\u0080\u0004J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0082\u0004J\n\u0010\u0017\u001a\u00020\u0018H\u0096\u0080\u0004J\n\u0010\u0019\u001a\u00020\u0012H\u0096\u0080\u0004R\u0013\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u000bX\u0082\u0084\b¢\u0006\u0004\n\u0002\u0010\f¨\u0006\u001a"}, d2 = {"Lkotlin/reflect/ParameterizedTypeImpl;", "Ljava/lang/reflect/ParameterizedType;", "Lkotlin/reflect/TypeImpl;", "rawType", "Ljava/lang/Class;", "ownerType", "Ljava/lang/reflect/Type;", "typeArguments", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/Class;Ljava/lang/reflect/Type;Ljava/util/List;)V", _UrlKt.FRAGMENT_ENCODE_SET, "[Ljava/lang/reflect/Type;", "getRawType", "getOwnerType", "getActualTypeArguments", "()[Ljava/lang/reflect/Type;", "getTypeName", _UrlKt.FRAGMENT_ENCODE_SET, "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@ExperimentalStdlibApi
@SourceDebugExtension({"SMAP\nTypesJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TypesJVM.kt\nkotlin/reflect/ParameterizedTypeImpl\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,244:1\n37#2,2:245\n*S KotlinDebug\n*F\n+ 1 TypesJVM.kt\nkotlin/reflect/ParameterizedTypeImpl\n*L\n204#1:245,2\n*E\n"})
/* loaded from: classes5.dex */
final class ParameterizedTypeImpl implements ParameterizedType, TypeImpl {
    private final Type ownerType;
    private final Class<?> rawType;
    private final Type[] typeArguments;

    public ParameterizedTypeImpl(Class<?> r1, Type r2, List<? extends Type> r3) {
        this.rawType = r1;
        this.ownerType = r2;
        this.typeArguments = (Type[]) r3.toArray(new Type[0]);
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof ParameterizedType) == false) goto L12;
        ParameterizedType r32 = (ParameterizedType) r3;
        if (Intrinsics.areEqual(this.rawType, r32.getRawType()) == true) goto L7;
        return false;
    L7:
        if (Intrinsics.areEqual(this.ownerType, r32.getOwnerType()) == true) goto L9;
        return false;
    L9:
        if (Arrays.equals(getActualTypeArguments(), r32.getActualTypeArguments()) == false) goto L16;
        return true;
    L16:
        return false;
    L12:
        return false;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type[] getActualTypeArguments() {
        return this.typeArguments;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getOwnerType() {
        return this.ownerType;
    }

    @Override // java.lang.reflect.ParameterizedType
    public Type getRawType() {
        return this.rawType;
    }

    @Override // java.lang.reflect.Type, kotlin.reflect.TypeImpl
    public String getTypeName() {
        StringBuilder r1 = new StringBuilder();
        Type r0 = this.ownerType;
        if (r0 == null) goto L5;
        r1.append(TypesJVMKt.access$typeToString(r0));
        r1.append("$");
        r1.append(this.rawType.getSimpleName());
    L6:
        Type[] r02 = this.typeArguments;
        if (r02.length != 0) goto L9;
        boolean r10 = true;
    L10:
        if (r10 == true) goto L13;
        ArraysKt.joinTo$default(r02, r1, null, "<", ">", 0, null, ParameterizedTypeImpl$getTypeName$1$1.INSTANCE, 50, null);
    L13:
        return r1.toString();
    L9:
        r10 = false;
        goto L10
    L5:
        r1.append(TypesJVMKt.access$typeToString(this.rawType));
        goto L6
    }

    public int hashCode() {
        int r0 = this.rawType.hashCode();
        Type r1 = this.ownerType;
        if (r1 == null) goto L5;
        int r12 = r1.hashCode();
    L6:
        int r2 = Arrays.hashCode(getActualTypeArguments());
        return r2 ^ (r0 ^ r12);
    L5:
        r12 = 0;
        goto L6
    }

    public String toString() {
        return getTypeName();
    }
}
