package kotlin.jvm.internal;

import java.util.List;
import kotlin.LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlin.reflect.KVariance;
import kotlin.time.DurationKt$$ExternalSyntheticBUOutline1;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.4")
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB-\bF\u0012\n\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0004\b\u000b\u0010\fJ\u0018\u0010\u001a\u001a\u00020\u001b2\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013H\u0086\u0080\u0004R\u0015\u0010\u0005\u001a\u00020\u0006X\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0015\u0010\u0007\u001a\u00020\bX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0015\u0010\t\u001a\u00020\nX\u0096\u0084\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0011R\u0017\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u0013X\u0082\u008e\b¢\u0006\u0002\n\u0000R!\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u00138VX\u0096\u0084\b¢\u0006\f\u0012\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001d"}, d2 = {"Lkotlin/jvm/internal/TypeParameterReference;", "Lkotlin/jvm/internal/KTypeParameterBase;", "container", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/jvm/internal/TypeParameterContainer;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "variance", "Lkotlin/reflect/KVariance;", "isReified", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/Object;Ljava/lang/String;Lkotlin/reflect/KVariance;Z)V", "getName", "()Ljava/lang/String;", "getVariance", "()Lkotlin/reflect/KVariance;", "()Z", "bounds", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KType;", "upperBounds", "getUpperBounds$annotations", "()V", "getUpperBounds", "()Ljava/util/List;", "setUpperBounds", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nTypeParameterReference.kt\nKotlin\n*S Kotlin\n*F\n+ 1 TypeParameterReference.kt\nkotlin/jvm/internal/TypeParameterReference\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,50:1\n1#2:51\n*E\n"})
/* loaded from: classes5.dex */
public final class TypeParameterReference extends KTypeParameterBase {
    public static final Companion Companion = null;
    private volatile List<? extends KType> bounds;
    private final boolean isReified;
    private final String name;
    private final KVariance variance;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0086\u0080\u0004¨\u0006\b"}, d2 = {"Lkotlin/jvm/internal/TypeParameterReference$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "typeParameter", "Lkotlin/reflect/KTypeParameter;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {

        @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
        public static final /* synthetic */ class WhenMappings {
            public static final /* synthetic */ int[] $EnumSwitchMapping$0 = null;

            static {
                int[] r0 = new int[KVariance.values().length];
                r0[KVariance.INVARIANT.ordinal()] = 1;     // Catch: NoSuchFieldError -> L8
            L11:
                r0[KVariance.IN.ordinal()] = 2;     // Catch: NoSuchFieldError -> L9
            L15:
                r0[KVariance.OUT.ordinal()] = 3;     // Catch: NoSuchFieldError -> L10
            L6:
                $EnumSwitchMapping$0 = r0;
            }
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final String toString(KTypeParameter r3) {
            StringBuilder r2 = new StringBuilder();
            KVariance r0 = r3.getVariance();
            int r02 = WhenMappings.$EnumSwitchMapping$0[r0.ordinal()];
            if (r02 != 1) goto L5;
            Unit r03 = Unit.INSTANCE;
        L13:
            r2.append(r3.getName());
            return r2.toString();
        L5:
            if (r02 != 2) goto L7;
            r2.append("in ");
            goto L13
        L7:
            if (r02 != 3) goto L9;
            r2.append("out ");
            goto L13
        L9:
            LazyKt__LazyJVMKt$$ExternalSyntheticBUOutline0.m();
            return null;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public TypeParameterReference(Object r1, String r2, KVariance r3, boolean r4) {
        super(r1);
        this.name = r2;
        this.variance = r3;
        this.isReified = r4;
    }

    public static /* synthetic */ void getUpperBounds$annotations() {
    }

    @Override // kotlin.reflect.KTypeParameter
    public String getName() {
        return this.name;
    }

    @Override // kotlin.reflect.KTypeParameter
    public List<KType> getUpperBounds() {
        List r0 = this.bounds;
        if (r0 != null) goto L6;
        List<KType> r02 = CollectionsKt.listOf(Reflection.nullableTypeOf(Object.class));
        this.bounds = r02;
        return r02;
    L6:
        return r0;
    }

    @Override // kotlin.reflect.KTypeParameter
    public KVariance getVariance() {
        return this.variance;
    }

    @Override // kotlin.reflect.KTypeParameter
    public boolean isReified() {
        return this.isReified;
    }

    public final void setUpperBounds(List<? extends KType> r2) {
        if (this.bounds != null) goto L6;
        this.bounds = r2;
        return;
    L6:
        DurationKt$$ExternalSyntheticBUOutline1.m("Upper bounds of type parameter '", this, "' have already been initialized.");
    }
}
