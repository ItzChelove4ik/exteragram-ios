package kotlin.coroutines;

import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;
import okio.Segment$$ExternalSyntheticBUOutline1;

@SinceKotlin(version = "1.3")
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u00012\u00060\u0002j\u0002`\u0003:\u0001(B\u0019\bF\u0012\u0006\u0010\u0004\u001a\u00020\u0001\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ)\u0010\t\u001a\u0004\u0018\u0001H\n\"\b\b\u0000\u0010\n*\u00020\u00062\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\n0\fH\u0096\u0082\u0004¢\u0006\u0002\u0010\rJ7\u0010\u000e\u001a\u0002H\u000f\"\u0004\b\u0000\u0010\u000f2\u0006\u0010\u0010\u001a\u0002H\u000f2\u0018\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u0002H\u000f\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u000f0\u0012H\u0096\u0080\u0004¢\u0006\u0002\u0010\u0013J\u0016\u0010\u0014\u001a\u00020\u00012\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\fH\u0096\u0080\u0004J\n\u0010\u0015\u001a\u00020\u0016H\u0082\u0080\u0004J\u0012\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0005\u001a\u00020\u0006H\u0082\u0080\u0004J\u0012\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u001a\u001a\u00020\u0000H\u0082\u0080\u0004J\u0014\u0010\u001b\u001a\u00020\u00182\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0096\u0082\u0004J\n\u0010\u001e\u001a\u00020\u0016H\u0096\u0080\u0004J\n\u0010\u001f\u001a\u00020 H\u0096\u0080\u0004J\n\u0010!\u001a\u00020\u001dH\u0082\u0080\u0004J\u001b\u0010\"\u001a\u00020#2\n\u0010$\u001a\u00060%j\u0002`&H\u0082\u0080\u0004¢\u0006\u0002\u0010'R\u000f\u0010\u0004\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0005\u001a\u00020\u0006X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lkotlin/coroutines/CombinedContext;", "Lkotlin/coroutines/CoroutineContext;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "left", "element", "Lkotlin/coroutines/CoroutineContext$Element;", "<init>", "(Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext$Element;)V", "get", "E", PluginsConstants.Settings.KEY, "Lkotlin/coroutines/CoroutineContext$Key;", "(Lkotlin/coroutines/CoroutineContext$Key;)Lkotlin/coroutines/CoroutineContext$Element;", "fold", "R", "initial", "operation", "Lkotlin/Function2;", "(Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/lang/Object;", "minusKey", "size", _UrlKt.FRAGMENT_ENCODE_SET, "contains", _UrlKt.FRAGMENT_ENCODE_SET, "containsAll", "context", "equals", "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "writeReplace", "readObject", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.TYPE_INPUT, "Ljava/io/ObjectInputStream;", "Lkotlin/internal/ReadObjectParameterType;", "(Ljava/io/ObjectInputStream;)V", "Serialized", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCoroutineContextImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoroutineContextImpl.kt\nkotlin/coroutines/CombinedContext\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,200:1\n1#2:201\n*E\n"})
/* loaded from: classes.dex */
public final class CombinedContext implements CoroutineContext, Serializable {
    private final CoroutineContext.Element element;
    private final CoroutineContext left;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0002\u0018\u0000 \r2\u00060\u0001j\u0002`\u0002:\u0001\rB\u0017\bF\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\u000b\u001a\u00020\fH\u0082\u0080\u0004R\u001d\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u0084\b¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\t¨\u0006\u000e"}, d2 = {"Lkotlin/coroutines/CombinedContext$Serialized;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "elements", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/coroutines/CoroutineContext;", "<init>", "([Lkotlin/coroutines/CoroutineContext;)V", "getElements", "()[Lkotlin/coroutines/CoroutineContext;", "[Lkotlin/coroutines/CoroutineContext;", "readResolve", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nCoroutineContextImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CoroutineContextImpl.kt\nkotlin/coroutines/CombinedContext$Serialized\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,200:1\n13459#2,3:201\n*S KotlinDebug\n*F\n+ 1 CoroutineContextImpl.kt\nkotlin/coroutines/CombinedContext$Serialized\n*L\n197#1:201,3\n*E\n"})
    /* loaded from: classes5.dex */
    public static final class Serialized implements Serializable {
        public static final Companion Companion = null;
        private static final long serialVersionUID = 0;
        private final CoroutineContext[] elements;

        @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lkotlin/coroutines/CombinedContext$Serialized$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "serialVersionUID", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
        public static final class Companion {
            private Companion() {
            }

            public /* synthetic */ Companion(DefaultConstructorMarker r1) {
                this();
            }
        }

        static {
            Companion = new Companion(null);
        }

        public Serialized(CoroutineContext[] r1) {
            this.elements = r1;
        }

        private final Object readResolve() {
            CoroutineContext[] r4 = this.elements;
            CoroutineContext r0 = EmptyCoroutineContext.INSTANCE;
            int r1 = r4.length;
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L5;
            r0 = r0.plus(r4[r2]);
            r2 = r2 + 1;
            goto L3
        L5:
            return r0;
        }

        public final CoroutineContext[] getElements() {
            return this.elements;
        }
    }

    /* renamed from: $r8$lambda$VpvYuysrEy-ElHo8MIXshfDY23U, reason: not valid java name */
    public static Unit m3235$r8$lambda$VpvYuysrEyElHo8MIXshfDY23U(CoroutineContext[] r1, Ref.IntRef r2, Unit r3, CoroutineContext.Element r4) {
        int r32 = r2.element;
        r2.element = r32 + 1;
        r1[r32] = r4;
        return Unit.INSTANCE;
    }

    public static String $r8$lambda$heU6HEF2AlutZz9XEZBRDrr2Lvw(String r1, CoroutineContext.Element r2) {
        if (r1.length() != 0) goto L7;
        return r2.toString();
    L7:
        return r1 + ", " + r2;
    }

    public CombinedContext(CoroutineContext r1, CoroutineContext.Element r2) {
        this.left = r1;
        this.element = r2;
    }

    private final boolean contains(CoroutineContext.Element r2) {
        return Intrinsics.areEqual(get(r2.getKey()), r2);
    }

    private final boolean containsAll(CombinedContext r2) {
    L3:
        if (contains(r2.element) == false) goto L4;
        CoroutineContext r22 = r2.left;
        if ((r22 instanceof CombinedContext) == false) goto L10;
        r2 = (CombinedContext) r22;
        goto L3
    L10:
        return contains((CoroutineContext.Element) r22);
    L4:
        return false;
    }

    private final void readObject(ObjectInputStream r1) {
        throw new InvalidObjectException("Deserialization is supported via proxy only");
    }

    private final int size() {
        int r0 = 2;
    L3:
        CoroutineContext r2 = this.left;
        if ((r2 instanceof CombinedContext) == false) goto L6;
        CombinedContext this = (CombinedContext) r2;
    L7:
        if (this == null) goto L8;
        r0 = r0 + 1;
        goto L3
    L8:
        return r0;
    L6:
        this = null;
        goto L7
    }

    private final Object writeReplace() {
        int r0 = size();
        CoroutineContext[] r1 = new CoroutineContext[r0];
        Ref.IntRef r2 = new Ref.IntRef();
        fold(Unit.INSTANCE, new CombinedContext$$ExternalSyntheticLambda0(r1, r2));
        if (r2.element == r0) goto L5;
        Segment$$ExternalSyntheticBUOutline1.m("Check failed.");
        return null;
    L5:
        return new Serialized(r1);
    }

    public boolean equals(Object r3) {
        if (this != r3) goto L4;
        return true;
    L4:
        if ((r3 instanceof CombinedContext) == false) goto L10;
        CombinedContext r32 = (CombinedContext) r3;
        if (r32.size() == size()) goto L8;
        return false;
    L8:
        if (r32.containsAll(this) == true) goto L16;
        return false;
    L16:
        return true;
    L10:
        return false;
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <R> R fold(R r2, Function2<? super R, ? super CoroutineContext.Element, ? extends R> r3) {
        return r3.invoke((Object) this.left.fold(r2, r3), this.element);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public <E extends CoroutineContext.Element> E get(CoroutineContext.Key<E> r2) {
    L2:
        E r0 = (E) this.element.get(r2);
        if (r0 != null) goto L4;
        CoroutineContext r1 = this.left;
        if ((r1 instanceof CombinedContext) == false) goto L9;
        CombinedContext this = (CombinedContext) r1;
        goto L2
    L9:
        return (E) r1.get(r2);
    L4:
        return r0;
    }

    public int hashCode() {
        return this.left.hashCode() + this.element.hashCode();
    }

    @Override // kotlin.coroutines.CoroutineContext
    public CoroutineContext minusKey(CoroutineContext.Key<?> r3) {
        CoroutineContext.Element r0 = this.element.get(r3);
        CoroutineContext r1 = this.left;
        if (r0 == null) goto L5;
        return r1;
    L5:
        CoroutineContext r32 = r1.minusKey(r3);
        if (r32 != this.left) goto L8;
        return this;
    L8:
        EmptyCoroutineContext r02 = EmptyCoroutineContext.INSTANCE;
        CoroutineContext.Element r2 = this.element;
        if (r32 != r02) goto L12;
        return r2;
    L12:
        return new CombinedContext(r32, r2);
    }

    @Override // kotlin.coroutines.CoroutineContext
    public /* bridge */ CoroutineContext plus(CoroutineContext r1) {
        return CoroutineContext.DefaultImpls.plus(this, r1);
    }

    public String toString() {
        return "[" + ((String) fold(_UrlKt.FRAGMENT_ENCODE_SET, new CombinedContext$$ExternalSyntheticLambda1())) + ']';
    }
}
