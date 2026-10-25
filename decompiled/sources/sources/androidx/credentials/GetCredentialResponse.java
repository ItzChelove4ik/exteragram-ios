package androidx.credentials;

import android.os.Bundle;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \b2\u00020\u0001:\u0001\bB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Landroidx/credentials/GetCredentialResponse;", _UrlKt.FRAGMENT_ENCODE_SET, "credential", "Landroidx/credentials/Credential;", "<init>", "(Landroidx/credentials/Credential;)V", "getCredential", "()Landroidx/credentials/Credential;", "Companion", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GetCredentialResponse {
    public static final Companion Companion = null;
    private final Credential credential;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0019\u0010\u0007\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\n\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {"Landroidx/credentials/GetCredentialResponse$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroid/os/Bundle;", "bundle", "Landroidx/credentials/GetCredentialResponse;", "fromBundle", "(Landroid/os/Bundle;)Landroidx/credentials/GetCredentialResponse;", _UrlKt.FRAGMENT_ENCODE_SET, "EXTRA_CREDENTIAL_TYPE", "Ljava/lang/String;", "EXTRA_CREDENTIAL_DATA", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final GetCredentialResponse fromBundle(Bundle r3) {
            String r2 = r3.getString("androidx.credentials.provider.extra.EXTRA_CREDENTIAL_TYPE");
            if (r2 != null) goto L5;
            return null;
        L5:
            Bundle r32 = r3.getBundle("androidx.credentials.provider.extra.EXTRA_CREDENTIAL_DATA");
            if (r32 != null) goto L9;
            return null;
        L9:
            return new GetCredentialResponse(Credential.Companion.createFrom(r2, r32));
        }
    }

    static {
        Companion = new Companion(null);
    }

    public GetCredentialResponse(Credential r1) {
        this.credential = r1;
    }

    public final Credential getCredential() {
        return this.credential;
    }
}
