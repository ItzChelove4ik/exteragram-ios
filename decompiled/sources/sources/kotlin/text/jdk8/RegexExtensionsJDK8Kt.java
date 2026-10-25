package kotlin.text.jdk8;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import kotlin.text.MatchGroup;
import kotlin.text.MatchGroupCollection;
import kotlin.text.MatchNamedGroupCollection;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u0018\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0087\u0082\u0004¨\u0006\u0005"}, d2 = {"get", "Lkotlin/text/MatchGroup;", "Lkotlin/text/MatchGroupCollection;", "name", _UrlKt.FRAGMENT_ENCODE_SET, "kotlin-stdlib"}, k = 2, mv = {2, 3, 0}, pn = "kotlin.text", xi = 48)
@JvmName(name = "RegexExtensionsJDK8Kt")
/* loaded from: classes5.dex */
public final class RegexExtensionsJDK8Kt {
    @SinceKotlin(version = "1.2")
    public static final MatchGroup get(MatchGroupCollection r2, String r3) {
        if ((r2 instanceof MatchNamedGroupCollection) == false) goto L5;
        MatchNamedGroupCollection r22 = (MatchNamedGroupCollection) r2;
    L6:
        if (r22 != null) goto L8;
        ByteString$$ExternalSyntheticBUOutline0.m("Retrieving groups by name is not supported on this platform.");
        return null;
    L8:
        return r22.get(r3);
    L5:
        r22 = null;
        goto L6
    }
}
