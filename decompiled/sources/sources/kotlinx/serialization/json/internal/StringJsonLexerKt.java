package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlinx.serialization.json.Json;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0080\u0080\u0004¨\u0006\u0006"}, d2 = {"StringJsonLexer", "Lkotlinx/serialization/json/internal/StringJsonLexer;", "json", "Lkotlinx/serialization/json/Json;", "source", _UrlKt.FRAGMENT_ENCODE_SET, "kotlinx-serialization-json"}, k = 2, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes.dex */
public abstract class StringJsonLexerKt {
    public static final StringJsonLexer StringJsonLexer(Json r1, String r2) {
        if (r1.getConfiguration().getAllowComments() == true) goto L7;
        return new StringJsonLexer(r2, r1.getConfiguration());
    L7:
        return new StringJsonLexerWithComments(r2, r1.getConfiguration());
    }
}
