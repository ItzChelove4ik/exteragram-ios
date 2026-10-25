package kotlin.reflect;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0019\n\u0002\u0010\b\n\u0000\bf\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u0005J\u0014\u0010\u0019\u001a\u00020\u001a2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0002H§\u0080\u0004J\u0014\u0010B\u001a\u00020\u001a2\b\u0010C\u001a\u0004\u0018\u00010\u0002H¦\u0082\u0004J\n\u0010D\u001a\u00020EH¦\u0080\u0004R\u0015\u0010\u0006\u001a\u0004\u0018\u00010\u0007X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0015\u0010\n\u001a\u0004\u0018\u00010\u0007X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u000b\u0010\tR\u001d\u0010\f\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000e0\rX¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u001f\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00120\rX¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0010R\u001d\u0010\u0014\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00000\rX¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0010R\u0015\u0010\u0016\u001a\u0004\u0018\u00018\u0000X¦\u0084\b¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018R!\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d8&X§\u0084\b¢\u0006\f\u0012\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R!\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u001d8&X§\u0084\b¢\u0006\f\u0012\u0004\b%\u0010 \u001a\u0004\b&\u0010\"R)\u0010'\u001a\u0010\u0012\f\u0012\n\u0012\u0006\b\u0001\u0012\u00028\u00000\u00000\u001d8&X§\u0084\b¢\u0006\f\u0012\u0004\b(\u0010 \u001a\u0004\b)\u0010\"R\u001d\u0010*\u001a\u0004\u0018\u00010+8&X§\u0084\b¢\u0006\f\u0012\u0004\b,\u0010 \u001a\u0004\b-\u0010.R\u001b\u0010/\u001a\u00020\u001a8&X§\u0084\b¢\u0006\f\u0012\u0004\b0\u0010 \u001a\u0004\b/\u00101R\u001b\u00102\u001a\u00020\u001a8&X§\u0084\b¢\u0006\f\u0012\u0004\b3\u0010 \u001a\u0004\b2\u00101R\u001b\u00104\u001a\u00020\u001a8&X§\u0084\b¢\u0006\f\u0012\u0004\b5\u0010 \u001a\u0004\b4\u00101R\u001b\u00106\u001a\u00020\u001a8&X§\u0084\b¢\u0006\f\u0012\u0004\b7\u0010 \u001a\u0004\b6\u00101R\u001b\u00108\u001a\u00020\u001a8&X§\u0084\b¢\u0006\f\u0012\u0004\b9\u0010 \u001a\u0004\b8\u00101R\u001b\u0010:\u001a\u00020\u001a8&X§\u0084\b¢\u0006\f\u0012\u0004\b;\u0010 \u001a\u0004\b:\u00101R\u001b\u0010<\u001a\u00020\u001a8&X§\u0084\b¢\u0006\f\u0012\u0004\b=\u0010 \u001a\u0004\b<\u00101R\u001b\u0010>\u001a\u00020\u001a8&X§\u0084\b¢\u0006\f\u0012\u0004\b?\u0010 \u001a\u0004\b>\u00101R\u001b\u0010@\u001a\u00020\u001a8&X§\u0084\b¢\u0006\f\u0012\u0004\bA\u0010 \u001a\u0004\b@\u00101¨\u0006F"}, d2 = {"Lkotlin/reflect/KClass;", "T", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KDeclarationContainer;", "Lkotlin/reflect/KAnnotatedElement;", "Lkotlin/reflect/KClassifier;", "simpleName", _UrlKt.FRAGMENT_ENCODE_SET, "getSimpleName", "()Ljava/lang/String;", "qualifiedName", "getQualifiedName", "members", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KCallable;", "getMembers", "()Ljava/util/Collection;", "constructors", "Lkotlin/reflect/KFunction;", "getConstructors", "nestedClasses", "getNestedClasses", "objectInstance", "getObjectInstance", "()Ljava/lang/Object;", "isInstance", _UrlKt.FRAGMENT_ENCODE_SET, "value", "typeParameters", _UrlKt.FRAGMENT_ENCODE_SET, "Lkotlin/reflect/KTypeParameter;", "getTypeParameters$annotations", "()V", "getTypeParameters", "()Ljava/util/List;", "supertypes", "Lkotlin/reflect/KType;", "getSupertypes$annotations", "getSupertypes", "sealedSubclasses", "getSealedSubclasses$annotations", "getSealedSubclasses", "visibility", "Lkotlin/reflect/KVisibility;", "getVisibility$annotations", "getVisibility", "()Lkotlin/reflect/KVisibility;", "isFinal", "isFinal$annotations", "()Z", "isOpen", "isOpen$annotations", "isAbstract", "isAbstract$annotations", "isSealed", "isSealed$annotations", "isData", "isData$annotations", "isInner", "isInner$annotations", "isCompanion", "isCompanion$annotations", "isFun", "isFun$annotations", "isValue", "isValue$annotations", "equals", "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public interface KClass<T> extends KDeclarationContainer, KAnnotatedElement, KClassifier {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
    /* loaded from: classes5.dex */
    public static final class DefaultImpls {
        @SinceKotlin(version = "1.3")
        public static /* synthetic */ void getSealedSubclasses$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void getSupertypes$annotations() {
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
        public static /* synthetic */ void isCompanion$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isData$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isFinal$annotations() {
        }

        @SinceKotlin(version = "1.4")
        public static /* synthetic */ void isFun$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isInner$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isOpen$annotations() {
        }

        @SinceKotlin(version = "1.1")
        public static /* synthetic */ void isSealed$annotations() {
        }

        @SinceKotlin(version = "1.5")
        public static /* synthetic */ void isValue$annotations() {
        }
    }

    boolean equals(Object r1);

    Collection<KFunction<T>> getConstructors();

    @Override // kotlin.reflect.KDeclarationContainer
    Collection<KCallable<?>> getMembers();

    Collection<KClass<?>> getNestedClasses();

    T getObjectInstance();

    String getQualifiedName();

    List<KClass<? extends T>> getSealedSubclasses();

    String getSimpleName();

    List<KType> getSupertypes();

    List<KTypeParameter> getTypeParameters();

    KVisibility getVisibility();

    int hashCode();

    boolean isAbstract();

    boolean isCompanion();

    boolean isData();

    boolean isFinal();

    boolean isFun();

    boolean isInner();

    @SinceKotlin(version = "1.1")
    boolean isInstance(Object r1);

    boolean isOpen();

    boolean isSealed();

    boolean isValue();
}
