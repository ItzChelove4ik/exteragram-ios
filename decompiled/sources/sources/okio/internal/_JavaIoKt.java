package okio.internal;

import java.util.logging.Logger;
import kotlin.Metadata;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\"\u0016\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001c\u0010\u0003\u001a\u00020\u0004*\u00060\u0005j\u0002`\u00068@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0003\u0010\u0007¨\u0006\b"}, d2 = {"logger", "Ljava/util/logging/Logger;", "kotlin.jvm.PlatformType", "isAndroidGetsocknameError", _UrlKt.FRAGMENT_ENCODE_SET, "Ljava/lang/AssertionError;", "Lkotlin/AssertionError;", "(Ljava/lang/AssertionError;)Z", "okio"}, k = 2, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public abstract class _JavaIoKt {
    private static final Logger logger = null;

    static {
        logger = Logger.getLogger("okio.Okio");
    }

    public static final /* synthetic */ Logger access$getLogger$p() {
        return logger;
    }

    public static final boolean isAndroidGetsocknameError(AssertionError r4) {
        if (r4.getCause() == null) goto L11;
        String r42 = r4.getMessage();
        if (r42 == null) goto L7;
        boolean r43 = StringsKt.contains$default(r42, "getsockname failed", false, 2, null);
    L8:
        if (r43 == false) goto L11;
        return true;
    L7:
        r43 = false;
    L11:
        return false;
    }
}
