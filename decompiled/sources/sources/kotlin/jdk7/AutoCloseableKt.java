package kotlin.jdk7;

import androidx.camera.camera2.config.UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0;
import kotlin.ExceptionsKt;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0000\u001a!\u0010\u0000\u001a\u00060\u0001j\u0002`\u00052\u000e\b\u0004\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007H\u0087\u0088\u0004ø\u0001\u0000\u001aO\u0010\t\u001a\u0002H\n\"\u0010\b\u0000\u0010\u000b*\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u0005\"\u0004\b\u0001\u0010\n*\u0002H\u000b2\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u0002H\n0\rH\u0087\u0088\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u000e\u001a \u0010\u000f\u001a\u00020\b*\n\u0018\u00010\u0001j\u0004\u0018\u0001`\u00052\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0081\u0080\u0004*\u001a\b\u0007\u0010\u0000\"\u00020\u00012\u00020\u0001B\f\b\u0002\u0012\b\b\u0003\u0012\u0004\b\b(\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u0012"}, d2 = {"AutoCloseable", "Ljava/lang/AutoCloseable;", "Lkotlin/SinceKotlin;", "version", "2.0", "Lkotlin/AutoCloseable;", "closeAction", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "use", "R", "T", "block", "Lkotlin/Function1;", "(Ljava/lang/AutoCloseable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "closeFinally", "cause", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, pn = "kotlin", xi = 48)
@JvmName(name = "AutoCloseableKt")
/* loaded from: classes.dex */
public final class AutoCloseableKt {

    @Metadata(k = 3, mv = {2, 3, 0}, xi = 176)
    @SourceDebugExtension({"SMAP\nAutoCloseableJVM.kt\nKotlin\n*S Kotlin\n*F\n+ 1 AutoCloseableJVM.kt\nkotlin/jdk7/AutoCloseableKt$AutoCloseable$1\n*L\n1#1,51:1\n*E\n"})
    /* renamed from: kotlin.jdk7.AutoCloseableKt$AutoCloseable$1, reason: invalid class name */
    /* loaded from: classes5.dex */
    public static final class AnonymousClass1 implements AutoCloseable {
        final /* synthetic */ Function0<Unit> $closeAction;

        public AnonymousClass1(Function0<Unit> r1) {
            this.$closeAction = r1;
        }

        @Override // java.lang.AutoCloseable
        public final void close() {
            this.$closeAction.invoke();
        }
    }

    @SinceKotlin(version = "2.0")
    @InlineOnly
    private static final AutoCloseable AutoCloseable(Function0<Unit> r1) {
        return new AnonymousClass1(r1);
    }

    @SinceKotlin(version = "2.0")
    public static /* synthetic */ void AutoCloseable$annotations() {
    }

    @SinceKotlin(version = "1.2")
    @PublishedApi
    public static final void closeFinally(AutoCloseable r0, Throwable r1) {
        if (r0 == null) goto L13;
        if (r1 != null) goto L11;
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(r0);
        return;
    L11:
        UseCaseGraphContext$$ExternalSyntheticAutoCloseableDispatcher0.m(r0);     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        ExceptionsKt.addSuppressed(r1, th);
        return;
    }

    @SinceKotlin(version = "1.2")
    @IgnorableReturnValue
    @InlineOnly
    private static final <T extends AutoCloseable, R> R use(T r2, Function1<? super T, ? extends R> r3) {
        R r32 = r3.invoke(r2);     // Catch: Throwable -> L6
        InlineMarker.finallyStart(1);
        closeFinally(r2, null);
        InlineMarker.finallyEnd(1);
        return r32;
    L6:
        th = move-exception;
        throw th;     // Catch: Throwable -> L8
    L8:
        th = move-exception;
        InlineMarker.finallyStart(1);
        closeFinally(r2, th);
        InlineMarker.finallyEnd(1);
        throw th;
    }
}
