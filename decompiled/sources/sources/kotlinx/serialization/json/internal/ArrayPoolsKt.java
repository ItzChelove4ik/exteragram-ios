package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.tgnet.TLObject;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\b\n\u0000\"\u000f\u0010\u0000\u001a\u00020\u0001X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006\u0002"}, d2 = {"MAX_CHARS_IN_POOL", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-serialization-json"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class ArrayPoolsKt {
    private static final int MAX_CHARS_IN_POOL = 0;

    static {
        Object r0 = null;
        Result.Companion r1 = Result.Companion;     // Catch: Throwable -> L6
        String r12 = System.getProperty("kotlinx.serialization.json.pool.size");     // Catch: Throwable -> L6
        if (r12 == null) goto L8;
        Integer r13 = StringsKt.toIntOrNull(r12);     // Catch: Throwable -> L6
    L9:
        Object r14 = Result.m2018constructorimpl(r13);     // Catch: Throwable -> L6
    L13:
        if (Result.m2024isFailureimpl(r14) == true) goto L16;
        r0 = r14;
    L16:
        Integer r02 = (Integer) r0;
        if (r02 == null) goto L19;
        int r03 = r02.intValue();
    L20:
        MAX_CHARS_IN_POOL = r03;
        return;
    L19:
        r03 = TLObject.FLAG_21;
        goto L20
    L8:
        r13 = null;
    L6:
        th = move-exception;
        Result.Companion r2 = Result.Companion;
        r14 = Result.m2018constructorimpl(ResultKt.createFailure(th));
        goto L13
    }

    public static final /* synthetic */ int access$getMAX_CHARS_IN_POOL$p() {
        return MAX_CHARS_IN_POOL;
    }
}
