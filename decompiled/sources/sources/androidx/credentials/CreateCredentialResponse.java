package androidx.credentials;

import android.os.Bundle;
import androidx.credentials.internal.FrameworkClassParsingException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0019\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Landroidx/credentials/CreateCredentialResponse;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "type", "Landroid/os/Bundle;", "data", "<init>", "(Ljava/lang/String;Landroid/os/Bundle;)V", "Ljava/lang/String;", "getType", "()Ljava/lang/String;", "Landroid/os/Bundle;", "getData", "()Landroid/os/Bundle;", "Companion", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class CreateCredentialResponse {
    public static final Companion Companion = null;
    private final Bundle data;
    private final String type;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\t\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\t\u0010\nJ\u0019\u0010\f\u001a\u0004\u0018\u00010\b2\u0006\u0010\u000b\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0010\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0010\u0010\u000f¨\u0006\u0011"}, d2 = {"Landroidx/credentials/CreateCredentialResponse$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "type", "Landroid/os/Bundle;", "data", "Landroidx/credentials/CreateCredentialResponse;", "createFrom", "(Ljava/lang/String;Landroid/os/Bundle;)Landroidx/credentials/CreateCredentialResponse;", "bundle", "fromBundle", "(Landroid/os/Bundle;)Landroidx/credentials/CreateCredentialResponse;", "EXTRA_CREATE_CREDENTIAL_RESPONSE_TYPE", "Ljava/lang/String;", "EXTRA_CREATE_CREDENTIAL_RESPONSE_DATA", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final CreateCredentialResponse createFrom(String r2, Bundle r3) {
            int r1 = r2.hashCode();     // Catch: FrameworkClassParsingException -> L22
            if (r1 == (-1678407252)) goto L17;
            if (r1 == (-543568185)) goto L13;
            if (r1 != (-95037569)) goto L21;
            if (r2.equals("androidx.credentials.TYPE_PUBLIC_KEY_CREDENTIAL") == false) goto L21;
            return CreatePublicKeyCredentialResponse.Companion.createFrom$credentials(r3);
        L21:
            throw new FrameworkClassParsingException();     // Catch: FrameworkClassParsingException -> L22
        L13:
            if (r2.equals("android.credentials.TYPE_PASSWORD_CREDENTIAL") == false) goto L21;
            return CreatePasswordResponse.Companion.createFrom$credentials(r3);
        L17:
            if (r2.equals("androidx.credentials.TYPE_DIGITAL_CREDENTIAL") == false) goto L21;
            return CreateDigitalCredentialResponse.Companion.createFrom$credentials(r3);
        L23:
            return new CreateCustomCredentialResponse(r2, r3);
        }

        @JvmStatic
        public final CreateCredentialResponse fromBundle(Bundle r4) {
            String r0 = r4.getString("androidx.credentials.provider.extra.CREATE_CREDENTIAL_RESPONSE_TYPE");
            if (r0 != null) goto L5;
            return null;
        L5:
            Bundle r42 = r4.getBundle("androidx.credentials.provider.extra.CREATE_CREDENTIAL_REQUEST_DATA");
            if (r42 != null) goto L9;
            return null;
        L9:
            return createFrom(r0, r42);
        }
    }

    static {
        Companion = new Companion(null);
    }

    public CreateCredentialResponse(String r1, Bundle r2) {
        this.type = r1;
        this.data = r2;
    }

    public final Bundle getData() {
        return this.data;
    }
}
