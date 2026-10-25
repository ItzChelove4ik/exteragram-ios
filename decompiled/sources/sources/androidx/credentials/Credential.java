package androidx.credentials;

import android.os.Bundle;
import androidx.credentials.internal.FrameworkClassParsingException;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\b&\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB\u0019\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\b\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\u00020\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u000f"}, d2 = {"Landroidx/credentials/Credential;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "type", "Landroid/os/Bundle;", "data", "<init>", "(Ljava/lang/String;Landroid/os/Bundle;)V", "Ljava/lang/String;", "getType", "()Ljava/lang/String;", "Landroid/os/Bundle;", "getData", "()Landroid/os/Bundle;", "Companion", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class Credential {
    public static final Companion Companion = null;
    private final Bundle data;
    private final String type;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0007¨\u0006\f"}, d2 = {"Landroidx/credentials/Credential$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "createFrom", "Landroidx/credentials/Credential;", "type", _UrlKt.FRAGMENT_ENCODE_SET, "data", "Landroid/os/Bundle;", "credential", "Landroid/credentials/Credential;", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final Credential createFrom(android.credentials.Credential r2) {
            return createFrom(r2.getType(), r2.getData());
        }

        @JvmStatic
        public final Credential createFrom(String r1, Bundle r2) {
            switch(r1.hashCode()) {
                case -1678407252: goto L18;
                case -1072734346: goto L14;
                case -543568185: goto L10;
                case -95037569: goto L6;
                default: goto L22;
            };     // Catch: FrameworkClassParsingException -> L23
        L22:
            throw new FrameworkClassParsingException();     // Catch: FrameworkClassParsingException -> L23
        L6:
            if (r1.equals("androidx.credentials.TYPE_PUBLIC_KEY_CREDENTIAL") == false) goto L22;
            return PublicKeyCredential.Companion.createFrom$credentials(r2);
        L10:
            if (r1.equals("android.credentials.TYPE_PASSWORD_CREDENTIAL") == false) goto L22;
            return PasswordCredential.Companion.createFrom$credentials(r2);
        L14:
            if (r1.equals("androidx.credentials.TYPE_RESTORE_CREDENTIAL") == false) goto L22;
            return RestoreCredential.Companion.createFrom$credentials(r2);
        L18:
            if (r1.equals("androidx.credentials.TYPE_DIGITAL_CREDENTIAL") == false) goto L22;
            return DigitalCredential.Companion.createFrom$credentials(r2);
        L24:
            return new CustomCredential(r1, r2);
        }
    }

    static {
        Companion = new Companion(null);
    }

    public Credential(String r1, Bundle r2) {
        this.type = r1;
        this.data = r2;
    }

    public final Bundle getData() {
        return this.data;
    }
}
