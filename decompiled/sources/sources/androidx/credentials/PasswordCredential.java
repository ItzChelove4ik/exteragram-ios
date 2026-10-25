package androidx.credentials;

import android.os.Bundle;
import androidx.credentials.internal.FrameworkClassParsingException;
import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u0018\u0000 \u000e2\u00020\u0001:\u0001\u000eB!\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bB\u0019\b\u0016\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002¢\u0006\u0004\b\u0007\u0010\tR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0004\u0010\n\u001a\u0004\b\r\u0010\f¨\u0006\u000f"}, d2 = {"Landroidx/credentials/PasswordCredential;", "Landroidx/credentials/Credential;", _UrlKt.FRAGMENT_ENCODE_SET, "id", "password", "Landroid/os/Bundle;", "data", "<init>", "(Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V", "(Ljava/lang/String;Ljava/lang/String;)V", "Ljava/lang/String;", "getId", "()Ljava/lang/String;", "getPassword", "Companion", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nPasswordCredential.kt\nKotlin\n*S Kotlin\n*F\n+ 1 PasswordCredential.kt\nandroidx/credentials/PasswordCredential\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n*L\n1#1,74:1\n1#2:75\n*E\n"})
/* loaded from: classes3.dex */
public final class PasswordCredential extends Credential {
    public static final Companion Companion = null;
    private final String id;
    private final String password;

    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\u0005H\u0001¢\u0006\u0002\b\fJ\u0015\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\tH\u0001¢\u0006\u0002\b\u0010R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Landroidx/credentials/PasswordCredential$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TYPE_PASSWORD_CREDENTIAL", _UrlKt.FRAGMENT_ENCODE_SET, "BUNDLE_KEY_ID", "BUNDLE_KEY_PASSWORD", "toBundle", "Landroid/os/Bundle;", "id", "password", "toBundle$credentials", "createFrom", "Landroidx/credentials/PasswordCredential;", "data", "createFrom$credentials", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final PasswordCredential createFrom$credentials(Bundle r4) {
            return new PasswordCredential(r4.getString("androidx.credentials.BUNDLE_KEY_ID"), r4.getString("androidx.credentials.BUNDLE_KEY_PASSWORD"), r4, null);
        L5:
            throw new FrameworkClassParsingException();
        }

        @JvmStatic
        public final Bundle toBundle$credentials(String r2, String r3) {
            Bundle r1 = new Bundle();
            r1.putString("androidx.credentials.BUNDLE_KEY_ID", r2);
            r1.putString("androidx.credentials.BUNDLE_KEY_PASSWORD", r3);
            return r1;
        }
    }

    static {
        Companion = new Companion(null);
    }

    public PasswordCredential(String r2, String r3) {
        this(r2, r3, Companion.toBundle$credentials(r2, r3));
    }

    private PasswordCredential(String r2, String r3, Bundle r4) {
        super("android.credentials.TYPE_PASSWORD_CREDENTIAL", r4);
        this.id = r2;
        this.password = r3;
        if (r3.length() <= 0) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("password should not be empty");
        throw null;
    }

    public /* synthetic */ PasswordCredential(String r1, String r2, Bundle r3, DefaultConstructorMarker r4) {
        this(r1, r2, r3);
    }
}
