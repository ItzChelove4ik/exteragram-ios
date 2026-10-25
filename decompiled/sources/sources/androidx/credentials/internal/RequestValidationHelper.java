package androidx.credentials.internal;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import org.json.JSONObject;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Landroidx/credentials/internal/RequestValidationHelper;", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class RequestValidationHelper {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Landroidx/credentials/internal/RequestValidationHelper$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "isValidJSON", _UrlKt.FRAGMENT_ENCODE_SET, "jsonString", _UrlKt.FRAGMENT_ENCODE_SET, "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final boolean isValidJSON(String r2) {
            if (r2.length() != 0) goto L9;
            return false;
        L9:
            new JSONObject(r2);     // Catch: Exception -> L8
            return true;
        L8:
            return false;
        }
    }

    static {
        Companion = new Companion(null);
    }
}
