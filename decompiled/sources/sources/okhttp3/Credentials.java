package okhttp3;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.text.Charsets;
import okhttp3.internal.url._UrlKt;
import okio.ByteString;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u00052\b\b\u0002\u0010\b\u001a\u00020\tH\u0007¨\u0006\n"}, d2 = {"Lokhttp3/Credentials;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "basic", _UrlKt.FRAGMENT_ENCODE_SET, "username", "password", "charset", "Ljava/nio/charset/Charset;", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class Credentials {
    public static final Credentials INSTANCE = null;

    static {
        INSTANCE = new Credentials();
    }

    private Credentials() {
    }

    @JvmStatic
    @JvmOverloads
    public static final String basic(String r2, String r3) {
        return basic$default(r2, r3, null, 4, null);
    }

    @JvmStatic
    @JvmOverloads
    public static final String basic(String r1, String r2, Charset r3) {
        return "Basic " + ByteString.Companion.encodeString(r1 + ':' + r2, r3).base64();
    }

    public static /* synthetic */ String basic$default(String r0, String r1, Charset r2, int r3, Object r4) {
        if ((r3 & 4) == 0) goto L6;
        r2 = Charsets.ISO_8859_1;
    L6:
        return basic(r0, r1, r2);
    }
}
