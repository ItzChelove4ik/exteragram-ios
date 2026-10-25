package kotlin.io;

import java.io.Closeable;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u001aI\u0010\u0000\u001a\u0002H\u0001\"\n\b\u0000\u0010\u0002*\u0004\u0018\u00010\u0003\"\u0004\b\u0001\u0010\u0001*\u0002H\u00022\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00010\u0005H\u0087\u0088\bø\u0001\u0000\u0082\u0002\n\n\b\b\u0001\u0012\u0002\u0010\u0001 \u0001¢\u0006\u0002\u0010\u0006\u001a\u001a\u0010\u0007\u001a\u00020\b*\u0004\u0018\u00010\u00032\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0081\u0080\u0004\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u000b"}, d2 = {"use", "R", "T", "Ljava/io/Closeable;", "block", "Lkotlin/Function1;", "(Ljava/io/Closeable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "closeFinally", _UrlKt.FRAGMENT_ENCODE_SET, "cause", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@JvmName(name = "CloseableKt")
/* loaded from: classes.dex */
public final class CloseableKt {
    @SinceKotlin(version = "1.1")
    @PublishedApi
    public static final void closeFinally(Closeable r0, Throwable r1) {
        if (r0 == null) goto L13;
        if (r1 != null) goto L11;
        r0.close();
        return;
    L11:
        r0.close();     // Catch: Throwable -> L8
        return;
    L8:
        th = move-exception;
        kotlin.ExceptionsKt.addSuppressed(r1, th);
        return;
    }

    @IgnorableReturnValue
    @InlineOnly
    private static final <T extends Closeable, R> R use(T r2, Function1<? super T, ? extends R> r3) {
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
