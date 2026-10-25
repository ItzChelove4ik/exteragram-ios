package androidx.credentials.playservices.controllers.identityauth.beginsignin;

import android.content.Context;
import androidx.credentials.CredentialOption;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetPublicKeyCredentialOption;
import androidx.credentials.playservices.controllers.identityauth.createpublickeycredential.PublicKeyCredentialControllerUtility;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0000\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Landroidx/credentials/playservices/controllers/identityauth/beginsignin/BeginSignInControllerUtility;", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class BeginSignInControllerUtility {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0007\u0010\bJ\u0017\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\u0006H\u0002¢\u0006\u0004\b\u000b\u0010\fJ\u001f\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0014\u001a\u00020\u00138\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00068\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0018\u0010\u0017¨\u0006\u0019"}, d2 = {"Landroidx/credentials/playservices/controllers/identityauth/beginsignin/BeginSignInControllerUtility$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroid/content/Context;", "context", _UrlKt.FRAGMENT_ENCODE_SET, "determineDeviceGMSVersionCode", "(Landroid/content/Context;)J", "curAuthVersion", _UrlKt.FRAGMENT_ENCODE_SET, "needsBackwardsCompatibleRequest", "(J)Z", "Landroidx/credentials/GetCredentialRequest;", PluginsConstants.REQUEST, "Lcom/google/android/gms/auth/api/identity/BeginSignInRequest;", "constructBeginSignInRequest$credentials_play_services_auth", "(Landroidx/credentials/GetCredentialRequest;Landroid/content/Context;)Lcom/google/android/gms/auth/api/identity/BeginSignInRequest;", "constructBeginSignInRequest", _UrlKt.FRAGMENT_ENCODE_SET, "TAG", "Ljava/lang/String;", "AUTH_MIN_VERSION_JSON_PARSING", "J", "AUTH_MIN_VERSION_PREFER_IMME_CRED", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private final long determineDeviceGMSVersionCode(Context r2) {
            return r2.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
        }

        private final boolean needsBackwardsCompatibleRequest(long r3) {
            if (r3 < 231815000) goto L6;
            return false;
        L6:
            return true;
        }

        public final BeginSignInRequest constructBeginSignInRequest$credentials_play_services_auth(GetCredentialRequest r8, Context r9) {
            BeginSignInRequest.Builder r0 = new BeginSignInRequest.Builder();
            long r1 = determineDeviceGMSVersionCode(r9);
            Iterator<CredentialOption> r92 = r8.getCredentialOptions().iterator();
            boolean r4 = false;
        L4:
            if (r92.hasNext() == false) goto L14;
            CredentialOption r5 = r92.next();
            if ((r5 instanceof GetPublicKeyCredentialOption) == false) goto L4;
            if (r4 == true) goto L4;
            if (needsBackwardsCompatibleRequest(r1) == false) goto L11;
            r0.setPasskeysSignInRequestOptions(PublicKeyCredentialControllerUtility.Companion.convertToPlayAuthPasskeyRequest((GetPublicKeyCredentialOption) r5));
        L12:
            r4 = true;
            goto L4
        L11:
            r0.setPasskeyJsonSignInRequestOptions(PublicKeyCredentialControllerUtility.Companion.convertToPlayAuthPasskeyJsonRequest((GetPublicKeyCredentialOption) r5));
            goto L12
        L14:
            if (r1 <= 241217000) goto L17;
            r0.setPreferImmediatelyAvailableCredentials(r8.preferImmediatelyAvailableCredentials());
        L17:
            return r0.setAutoSelectEnabled(false).build();
        }
    }

    static {
        Companion = new Companion(null);
    }
}
