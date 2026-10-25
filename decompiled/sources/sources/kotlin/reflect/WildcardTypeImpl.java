package kotlin.reflect;

import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import kotlin.ExperimentalStdlibApi;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\b\u0003\u0018\u0000 \u00152\u00020\u00012\u00020\u0002:\u0001\u0015B\u001d\bF\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00040\tH\u0016¢\u0006\u0002\u0010\nJ\u0013\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\tH\u0016¢\u0006\u0002\u0010\nJ\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\u0014\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0096\u0082\u0004J\n\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004J\n\u0010\u0014\u001a\u00020\rH\u0096\u0080\u0004R\u0011\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lkotlin/reflect/WildcardTypeImpl;", "Ljava/lang/reflect/WildcardType;", "Lkotlin/reflect/TypeImpl;", "upperBound", "Ljava/lang/reflect/Type;", "lowerBound", "<init>", "(Ljava/lang/reflect/Type;Ljava/lang/reflect/Type;)V", "getUpperBounds", _UrlKt.FRAGMENT_ENCODE_SET, "()[Ljava/lang/reflect/Type;", "getLowerBounds", "getTypeName", _UrlKt.FRAGMENT_ENCODE_SET, "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@ExperimentalStdlibApi
/* loaded from: classes5.dex */
final class WildcardTypeImpl implements WildcardType, TypeImpl {
    public static final Companion Companion = null;
    private static final WildcardTypeImpl STAR = null;
    private final Type lowerBound;
    private final Type upperBound;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0015\u0010\u0004\u001a\u00020\u0005X\u0086\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lkotlin/reflect/WildcardTypeImpl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "STAR", "Lkotlin/reflect/WildcardTypeImpl;", "getSTAR", "()Lkotlin/reflect/WildcardTypeImpl;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final WildcardTypeImpl getSTAR() {
            return WildcardTypeImpl.access$getSTAR$cp();
        }
    }

    static {
        Companion = new Companion(null);
        STAR = new WildcardTypeImpl(null, null);
    }

    public WildcardTypeImpl(Type r1, Type r2) {
        this.upperBound = r1;
        this.lowerBound = r2;
    }

    public static final /* synthetic */ WildcardTypeImpl access$getSTAR$cp() {
        return STAR;
    }

    public boolean equals(Object r3) {
        if ((r3 instanceof WildcardType) == false) goto L10;
        WildcardType r32 = (WildcardType) r3;
        if (Arrays.equals(getUpperBounds(), r32.getUpperBounds()) == true) goto L7;
        return false;
    L7:
        if (Arrays.equals(getLowerBounds(), r32.getLowerBounds()) == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    @Override // java.lang.reflect.WildcardType
    public Type[] getLowerBounds() {
        Type r2 = this.lowerBound;
        if (r2 != null) goto L7;
        return new Type[0];
    L7:
        return new Type[]{r2};
    }

    @Override // java.lang.reflect.Type, kotlin.reflect.TypeImpl
    public String getTypeName() {
        if (this.lowerBound != null) goto L5;
        Type r0 = this.upperBound;
        if (r0 != null) goto L9;
        return "?";
    L9:
        if (Intrinsics.areEqual(r0, Object.class) == false) goto L11;
        return "?";
    L11:
        return "? extends " + TypesJVMKt.access$typeToString(this.upperBound);
    L5:
        return "? super " + TypesJVMKt.access$typeToString(this.lowerBound);
    }

    @Override // java.lang.reflect.WildcardType
    public Type[] getUpperBounds() {
        Type r2 = this.upperBound;
        if (r2 != null) goto L6;
        r2 = Object.class;
    L6:
        return new Type[]{r2};
    }

    public int hashCode() {
        int r0 = Arrays.hashCode(getUpperBounds());
        return Arrays.hashCode(getLowerBounds()) ^ r0;
    }

    public String toString() {
        return getTypeName();
    }
}
