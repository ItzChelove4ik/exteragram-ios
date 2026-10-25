package androidx.credentials;

import android.os.Bundle;
import androidx.credentials.internal.FrameworkClassParsingException;
import androidx.credentials.internal.RequestValidationHelper;
import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\u0018\u0000 \f2\u00020\u0001:\u0001\fB\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0006\u0010\bR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Landroidx/credentials/PublicKeyCredential;", "Landroidx/credentials/Credential;", _UrlKt.FRAGMENT_ENCODE_SET, "authenticationResponseJson", "Landroid/os/Bundle;", "data", "<init>", "(Ljava/lang/String;Landroid/os/Bundle;)V", "(Ljava/lang/String;)V", "Ljava/lang/String;", "getAuthenticationResponseJson", "()Ljava/lang/String;", "Companion", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PublicKeyCredential extends Credential {
    public static final Companion Companion = null;
    private final String authenticationResponseJson;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0001¢\u0006\u0002\b\u000bJ\u0015\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\tH\u0001¢\u0006\u0002\b\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/credentials/PublicKeyCredential$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TYPE_PUBLIC_KEY_CREDENTIAL", _UrlKt.FRAGMENT_ENCODE_SET, "BUNDLE_KEY_SUBTYPE", "BUNDLE_KEY_AUTHENTICATION_RESPONSE_JSON", "toBundle", "Landroid/os/Bundle;", "authenticationResponseJson", "toBundle$credentials", "createFrom", "Landroidx/credentials/PublicKeyCredential;", "data", "createFrom$credentials", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final PublicKeyCredential createFrom$credentials(Bundle r3) {
            return new PublicKeyCredential(r3.getString("androidx.credentials.BUNDLE_KEY_AUTHENTICATION_RESPONSE_JSON"), r3, null);
        L5:
            throw new FrameworkClassParsingException();
        }

        @JvmStatic
        public final Bundle toBundle$credentials(String r2) {
            Bundle r1 = new Bundle();
            r1.putString("androidx.credentials.BUNDLE_KEY_AUTHENTICATION_RESPONSE_JSON", r2);
            return r1;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public PublicKeyCredential(String r2) {
        this(r2, Companion.toBundle$credentials(r2));
    }

    private PublicKeyCredential(String r2, Bundle r3) {
        super("androidx.credentials.TYPE_PUBLIC_KEY_CREDENTIAL", r3);
        this.authenticationResponseJson = r2;
        if (RequestValidationHelper.Companion.isValidJSON(r2) == false) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("authenticationResponseJson must not be empty, and must be a valid JSON");
        throw null;
    }

    public /* synthetic */ PublicKeyCredential(String r1, Bundle r2, DefaultConstructorMarker r3) {
        this(r1, r2);
    }
}
