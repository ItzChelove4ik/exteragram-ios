package androidx.credentials;

import android.os.Bundle;
import androidx.credentials.internal.FrameworkClassParsingException;
import androidx.credentials.internal.RequestValidationHelper;
import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.Charsets;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u0000 \u000b2\u00020\u0001:\u0001\u000bB\u0019\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\n¨\u0006\f"}, d2 = {"Landroidx/credentials/DigitalCredential;", "Landroidx/credentials/Credential;", _UrlKt.FRAGMENT_ENCODE_SET, "credentialJson", "Landroid/os/Bundle;", "data", "<init>", "(Ljava/lang/String;Landroid/os/Bundle;)V", "Ljava/lang/String;", "getCredentialJson", "()Ljava/lang/String;", "Companion", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DigitalCredential extends Credential {
    public static final Companion Companion = null;
    private final String credentialJson;

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\t\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0001¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\u000b\u001a\u00020\n8\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u000b\u0010\fR\u0014\u0010\r\u001a\u00020\n8\u0000X\u0080T¢\u0006\u0006\n\u0004\b\r\u0010\fR\u0014\u0010\u000f\u001a\u00020\u000e8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Landroidx/credentials/DigitalCredential$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroid/os/Bundle;", "data", "Landroidx/credentials/DigitalCredential;", "createFrom$credentials", "(Landroid/os/Bundle;)Landroidx/credentials/DigitalCredential;", "createFrom", _UrlKt.FRAGMENT_ENCODE_SET, "TYPE_DIGITAL_CREDENTIAL", "Ljava/lang/String;", "BUNDLE_KEY_REQUEST_JSON", _UrlKt.FRAGMENT_ENCODE_SET, "STRING_LEN_THRESHOLD", "I", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final DigitalCredential createFrom$credentials(Bundle r5) {
            Object r4 = r5.get("androidx.credentials.BUNDLE_KEY_REQUEST_JSON");     // Catch: Exception -> L8
            if ((r4 instanceof byte[]) == false) goto L7;
            return new DigitalCredential(new String((byte[]) r4, Charsets.UTF_8), r5, null);
        L7:
            return new DigitalCredential((String) r4, r5, null);
        L9:
            throw new FrameworkClassParsingException();
        }
    }

    static {
        Companion = new Companion(null);
    }

    private DigitalCredential(String r2, Bundle r3) {
        super("androidx.credentials.TYPE_DIGITAL_CREDENTIAL", r3);
        this.credentialJson = r2;
        if (RequestValidationHelper.Companion.isValidJSON(r2) == false) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("credentialJson must not be empty, and must be a valid JSON");
        throw null;
    }

    public /* synthetic */ DigitalCredential(String r1, Bundle r2, DefaultConstructorMarker r3) {
        this(r1, r2);
    }
}
