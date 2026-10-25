package kotlin.reflect;

import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.IntrinsicConstEvaluation;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\t\bf\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002J'\u0010\u0016\u001a\u00028\u00002\u0016\u0010\u0017\u001a\f\u0012\b\b\u0001\u0012\u0004\u0018\u00010\u00190\u0018\"\u0004\u0018\u00010\u0019H¦\u0080\u0004¢\u0006\u0002\u0010\u001aJ%\u0010\u001b\u001a\u00028\u00002\u0014\u0010\u0017\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00190\u001cH¦\u0080\u0004¢\u0006\u0002\u0010\u001dR\u001b\u0010\u0003\u001a\u00020\u00048&X§\u0084\b¢\u0006\f\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\bR\u0019\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX¦\u0084\b¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0013\u0010\u000e\u001a\u00020\u000fX¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R!\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\n8&X§\u0084\b¢\u0006\f\u0012\u0004\b\u0014\u0010\u0006\u001a\u0004\b\u0015\u0010\rR\u001d\u0010\u001e\u001a\u0004\u0018\u00010\u001f8&X§\u0084\b¢\u0006\f\u0012\u0004\b \u0010\u0006\u001a\u0004\b!\u0010\"R\u001b\u0010#\u001a\u00020$8&X§\u0084\b¢\u0006\f\u0012\u0004\b%\u0010\u0006\u001a\u0004\b#\u0010&R\u001b\u0010'\u001a\u00020$8&X§\u0084\b¢\u0006\f\u0012\u0004\b(\u0010\u0006\u001a\u0004\b'\u0010&R\u001b\u0010)\u001a\u00020$8&X§\u0084\b¢\u0006\f\u0012\u0004\b*\u0010\u0006\u001a\u0004\b)\u0010&R\u001b\u0010+\u001a\u00020$8&X§\u0084\b¢\u0006\f\u0012\u0004\b,\u0010\u0006\u001a\u0004\b+\u0010&¨\u0006-"}, d2 = {"Lkotlin/reflect/KCallable;", "R", "Lkotlin/reflect/KAnnotatedElement;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "getName$annotations", "()V", "getName", "()Ljava/lang/String;", "parameters", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KParameter;", "getParameters", "()Ljava/util/List;", "returnType", "Lkotlin/reflect/KType;", "getReturnType", "()Lkotlin/reflect/KType;", "typeParameters", "Lkotlin/reflect/KTypeParameter;", "getTypeParameters$annotations", "getTypeParameters", "call", "args", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "([Ljava/lang/Object;)Ljava/lang/Object;", "callBy", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/util/Map;)Ljava/lang/Object;", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility$annotations", "getVisibility", "()Lkotlin/reflect/KVisibility;", "isFinal", _UrlKt.FRAGMENT_ENCODE_SET, "isFinal$annotations", "()Z", "isOpen", "isOpen$annotations", "isAbstract", "isAbstract$annotations", "isSuspend", "isSuspend$annotations", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public interface KCallable<R> extends KAnnotatedElement {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class DefaultImpls {
        @IntrinsicConstEvaluation
        public static /* synthetic */ void getName$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void getTypeParameters$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void getVisibility$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isAbstract$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isFinal$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isOpen$annotations() {
        }

        @SinceKotlin(version = "1.3")
        public static /* synthetic */ void isSuspend$annotations() {
        }
    }

    R call(Object... r1);

    R callBy(Map<KParameter, ? extends Object> r1);

    String getName();

    List<KParameter> getParameters();

    KType getReturnType();

    List<KTypeParameter> getTypeParameters();

    KVisibility getVisibility();

    boolean isAbstract();

    boolean isFinal();

    boolean isOpen();

    boolean isSuspend();
}
