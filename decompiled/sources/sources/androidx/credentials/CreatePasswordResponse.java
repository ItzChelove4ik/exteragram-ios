package androidx.credentials;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00062\u00020\u0001:\u0001\u0006B\u0011\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005¨\u0006\u0007"}, d2 = {"Landroidx/credentials/CreatePasswordResponse;", "Landroidx/credentials/CreateCredentialResponse;", "Landroid/os/Bundle;", "data", "<init>", "(Landroid/os/Bundle;)V", "Companion", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CreatePasswordResponse extends CreateCredentialResponse {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0001¢\u0006\u0002\b\b¨\u0006\t"}, d2 = {"Landroidx/credentials/CreatePasswordResponse$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "createFrom", "Landroidx/credentials/CreatePasswordResponse;", "data", "Landroid/os/Bundle;", "createFrom$credentials", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final CreatePasswordResponse createFrom$credentials(Bundle r2) {
            return new CreatePasswordResponse(r2, null);
        }
    }

    static {
        Companion = new Companion(null);
    }

    private CreatePasswordResponse(Bundle r2) {
        super("android.credentials.TYPE_PASSWORD_CREDENTIAL", r2);
    }

    public /* synthetic */ CreatePasswordResponse(Bundle r1, DefaultConstructorMarker r2) {
        this(r1);
    }
}
