package kotlin.text;

import java.nio.charset.Charset;
import kotlin.Metadata;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0087\u0088\u0004¨\u0006\u0004"}, d2 = {"charset", "Ljava/nio/charset/Charset;", "charsetName", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, xi = 48)
@JvmName(name = "CharsetsKt")
/* loaded from: classes5.dex */
public final class CharsetsKt {
    @InlineOnly
    private static final Charset charset(String r0) {
        return Charset.forName(r0);
    }
}
