package androidx.credentials;

import android.os.Bundle;
import androidx.credentials.internal.FrameworkClassParsingException;
import androidx.credentials.internal.RequestValidationHelper;
import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\b\b\u0007\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0006\u001a\u0004\b\u0007\u0010\b¨\u0006\n"}, d2 = {"Landroidx/credentials/CreateDigitalCredentialResponse;", "Landroidx/credentials/CreateCredentialResponse;", _UrlKt.FRAGMENT_ENCODE_SET, "responseJson", "<init>", "(Ljava/lang/String;)V", "Ljava/lang/String;", "getResponseJson", "()Ljava/lang/String;", "Companion", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CreateDigitalCredentialResponse extends CreateCredentialResponse {
    public static final Companion Companion = null;
    private final String responseJson;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005H\u0001¢\u0006\u0002\b\tJ\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0007H\u0001¢\u0006\u0002\b\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/credentials/CreateDigitalCredentialResponse$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "BUNDLE_KEY_RESPONSE_JSON", _UrlKt.FRAGMENT_ENCODE_SET, "toBundle", "Landroid/os/Bundle;", "responseJson", "toBundle$credentials", "createFrom", "Landroidx/credentials/CreateDigitalCredentialResponse;", "data", "createFrom$credentials", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final CreateDigitalCredentialResponse createFrom$credentials(Bundle r1) {
            return new CreateDigitalCredentialResponse(r1.getString("androidx.credentials.BUNDLE_KEY_RESPONSE_JSON"));
        L5:
            throw new FrameworkClassParsingException();
        }

        @JvmStatic
        public final Bundle toBundle$credentials(String r2) {
            Bundle r1 = new Bundle();
            r1.putString("androidx.credentials.BUNDLE_KEY_RESPONSE_JSON", r2);
            return r1;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public CreateDigitalCredentialResponse(String r3) {
        super("androidx.credentials.TYPE_DIGITAL_CREDENTIAL", Companion.toBundle$credentials(r3));
        this.responseJson = r3;
        if (RequestValidationHelper.Companion.isValidJSON(r3) == false) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("responseJson must not be empty, and must be a valid JSON");
        throw null;
    }
}
