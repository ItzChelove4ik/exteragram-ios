package kotlin;

import java.io.Serializable;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmField;
import kotlin.jvm.JvmInline;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@SinceKotlin(version = "1.3")
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\b\u0087@\u0018\u0000 \u001e*\u0006\b\u0000\u0010\u0001 \u00012\u00060\u0002j\u0002`\u0003:\u0002\u001e\u001fB\u0013\bA\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\u0010\u001a\u0004\u0018\u00018\u0000H\u0087\u0088\u0004¢\u0006\u0004\b\u0011\u0010\u0007J\u0013\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0086\u0080\u0004¢\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0016\u001a\u00020\u0017H\u0096\u0080\u0004¢\u0006\u0004\b\u0018\u0010\u0019J\u0014\u0010\u001a\u001a\u00020\u000b2\b\u0010\u001b\u001a\u0004\u0018\u00010\u0005HÖ\u0083\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004R\u0019\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0000X\u0081\u0084\b¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\tR\u0015\u0010\n\u001a\u00020\u000b8FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0015\u0010\u000e\u001a\u00020\u000b8FX\u0086\u0084\b¢\u0006\u0006\u001a\u0004\b\u000f\u0010\r\u0088\u0001\u0004\u0092\u0001\u0004\u0018\u00010\u0005¨\u0006 "}, d2 = {"Lkotlin/Result;", "T", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "value", _UrlKt.FRAGMENT_ENCODE_SET, "constructor-impl", "(Ljava/lang/Object;)Ljava/lang/Object;", "getValue$annotations", "()V", "isSuccess", _UrlKt.FRAGMENT_ENCODE_SET, "isSuccess-impl", "(Ljava/lang/Object;)Z", "isFailure", "isFailure-impl", "getOrNull", "getOrNull-impl", "exceptionOrNull", _UrlKt.FRAGMENT_ENCODE_SET, "exceptionOrNull-impl", "(Ljava/lang/Object;)Ljava/lang/Throwable;", "toString", _UrlKt.FRAGMENT_ENCODE_SET, "toString-impl", "(Ljava/lang/Object;)Ljava/lang/String;", "equals", "other", "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "Failure", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
@JvmInline
/* loaded from: classes.dex */
public final class Result<T> implements Serializable {
    public static final Companion Companion = null;
    private final Object value;

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0001\u0010\u00062\u0006\u0010\u0007\u001a\u0002H\u0006H\u0087\u0088\u0004¢\u0006\u0002\u0010\bJ#\u0010\t\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0001\u0010\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0087\u0088\u0004¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lkotlin/Result$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "success", "Lkotlin/Result;", "T", "value", "(Ljava/lang/Object;)Ljava/lang/Object;", "failure", "exception", _UrlKt.FRAGMENT_ENCODE_SET, "(Ljava/lang/Throwable;)Ljava/lang/Object;", "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @InlineOnly
        @JvmName(name = "failure")
        private final <T> Object failure(Throwable r1) {
            return Result.m2018constructorimpl(ResultKt.createFailure(r1));
        }

        @InlineOnly
        @JvmName(name = "success")
        private final <T> Object success(T r1) {
            return Result.m2018constructorimpl(r1);
        }
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0000\u0018\u00002\u00060\u0001j\u0002`\u0002B\u0011\bF\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\u0014\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0096\u0082\u0004J\n\u0010\u000b\u001a\u00020\fH\u0096\u0080\u0004J\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004R\u0011\u0010\u0003\u001a\u00020\u00048\u0006X\u0087\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lkotlin/Result$Failure;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "exception", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/Throwable;)V", "equals", _UrlKt.FRAGMENT_ENCODE_SET, "other", _UrlKt.FRAGMENT_ENCODE_SET, "hashCode", _UrlKt.FRAGMENT_ENCODE_SET, "toString", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 1, mv = {2, 3, 0}, xi = 48)
    public static final class Failure implements Serializable {

        @JvmField
        public final Throwable exception;

        public Failure(Throwable r1) {
            this.exception = r1;
        }

        public boolean equals(Object r2) {
            if ((r2 instanceof Failure) == true) goto L5;
            return false;
        L5:
            if (Intrinsics.areEqual(this.exception, ((Failure) r2).exception) == false) goto L10;
            return true;
        L10:
            return false;
        }

        public int hashCode() {
            return this.exception.hashCode();
        }

        public String toString() {
            return "Failure(" + this.exception + ')';
        }
    }

    static {
        Companion = new Companion(null);
    }

    @PublishedApi
    private /* synthetic */ Result(Object r1) {
        this.value = r1;
    }

    /* renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ Result m2017boximpl(Object r1) {
        return new Result(r1);
    }

    @PublishedApi
    /* renamed from: constructor-impl, reason: not valid java name */
    public static <T> Object m2018constructorimpl(Object r0) {
        return r0;
    }

    /* renamed from: equals-impl, reason: not valid java name */
    public static boolean m2019equalsimpl(Object r2, Object r3) {
        if ((r3 instanceof Result) == true) goto L6;
        return false;
    L6:
        if (Intrinsics.areEqual(r2, ((Result) r3).m2027unboximpl()) == true) goto L8;
        return false;
    L8:
        return true;
    }

    /* renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2020equalsimpl0(Object r0, Object r1) {
        return Intrinsics.areEqual(r0, r1);
    }

    /* renamed from: exceptionOrNull-impl, reason: not valid java name */
    public static final Throwable m2021exceptionOrNullimpl(Object r1) {
        if ((r1 instanceof Failure) == true) goto L5;
        return null;
    L5:
        return ((Failure) r1).exception;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @InlineOnly
    /* renamed from: getOrNull-impl, reason: not valid java name */
    private static final T m2022getOrNullimpl(Object r1) {
        if (m2024isFailureimpl(r1) == false) goto L6;
        return null;
    L6:
        return r1;
    }

    @PublishedApi
    public static /* synthetic */ void getValue$annotations() {
    }

    /* renamed from: hashCode-impl, reason: not valid java name */
    public static int m2023hashCodeimpl(Object r0) {
        if (r0 != null) goto L6;
        return 0;
    L6:
        return r0.hashCode();
    }

    /* renamed from: isFailure-impl, reason: not valid java name */
    public static final boolean m2024isFailureimpl(Object r0) {
        return r0 instanceof Failure;
    }

    /* renamed from: isSuccess-impl, reason: not valid java name */
    public static final boolean m2025isSuccessimpl(Object r0) {
        return !(r0 instanceof Failure);
    }

    /* renamed from: toString-impl, reason: not valid java name */
    public static String m2026toStringimpl(Object r2) {
        if ((r2 instanceof Failure) == false) goto L7;
        return ((Failure) r2).toString();
    L7:
        return "Success(" + r2 + ')';
    }

    public boolean equals(Object r1) {
        return m2019equalsimpl(this.value, r1);
    }

    public int hashCode() {
        return m2023hashCodeimpl(this.value);
    }

    public String toString() {
        return m2026toStringimpl(this.value);
    }

    /* renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ Object m2027unboximpl() {
        return this.value;
    }
}
