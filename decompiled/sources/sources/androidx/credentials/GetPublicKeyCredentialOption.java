package androidx.credentials;

import android.content.ComponentName;
import android.os.Bundle;
import androidx.credentials.internal.RequestValidationHelper;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017BC\b\u0002\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000b\u001a\u00020\t\u0012\b\b\u0002\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fB-\b\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0004\u0012\u000e\b\u0002\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\u000e\u0010\u0010R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0019\u0010\u0005\u001a\u0004\u0018\u00010\u00048\u0006¢\u0006\f\n\u0004\b\u0005\u0010\u0014\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Landroidx/credentials/GetPublicKeyCredentialOption;", "Landroidx/credentials/CredentialOption;", _UrlKt.FRAGMENT_ENCODE_SET, "requestJson", _UrlKt.FRAGMENT_ENCODE_SET, "clientDataHash", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/content/ComponentName;", "allowedProviders", "Landroid/os/Bundle;", "requestData", "candidateQueryData", _UrlKt.FRAGMENT_ENCODE_SET, "typePriorityCategory", "<init>", "(Ljava/lang/String;[BLjava/util/Set;Landroid/os/Bundle;Landroid/os/Bundle;I)V", "(Ljava/lang/String;[BLjava/util/Set;)V", "Ljava/lang/String;", "getRequestJson", "()Ljava/lang/String;", "[B", "getClientDataHash", "()[B", "Companion", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GetPublicKeyCredentialOption extends CredentialOption {
    public static final Companion Companion = null;
    private final byte[] clientDataHash;
    private final String requestJson;

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0080\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u000b\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0001¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\f\u001a\u00020\u00048\u0000X\u0080T¢\u0006\u0006\n\u0004\b\f\u0010\rR\u0014\u0010\u000e\u001a\u00020\u00048\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u000e\u0010\rR\u0014\u0010\u000f\u001a\u00020\u00048\u0000X\u0080T¢\u0006\u0006\n\u0004\b\u000f\u0010\r¨\u0006\u0010"}, d2 = {"Landroidx/credentials/GetPublicKeyCredentialOption$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "requestJson", _UrlKt.FRAGMENT_ENCODE_SET, "clientDataHash", "Landroid/os/Bundle;", "toRequestDataBundle$credentials", "(Ljava/lang/String;[B)Landroid/os/Bundle;", "toRequestDataBundle", "BUNDLE_KEY_CLIENT_DATA_HASH", "Ljava/lang/String;", "BUNDLE_KEY_REQUEST_JSON", "BUNDLE_VALUE_SUBTYPE_GET_PUBLIC_KEY_CREDENTIAL_OPTION", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final Bundle toRequestDataBundle$credentials(String r3, byte[] r4) {
            Bundle r2 = new Bundle();
            r2.putString("androidx.credentials.BUNDLE_KEY_SUBTYPE", "androidx.credentials.BUNDLE_VALUE_SUBTYPE_GET_PUBLIC_KEY_CREDENTIAL_OPTION");
            r2.putString("androidx.credentials.BUNDLE_KEY_REQUEST_JSON", r3);
            r2.putByteArray("androidx.credentials.BUNDLE_KEY_CLIENT_DATA_HASH", r4);
            return r2;
        }
    }

    static {
        Companion = new Companion(null);
    }

    @JvmOverloads
    public GetPublicKeyCredentialOption(String r7, byte[] r8) {
        this(r7, r8, null, 4, null);
    }

    @JvmOverloads
    public GetPublicKeyCredentialOption(String r11, byte[] r12, Set<ComponentName> r13) {
        Companion r0 = Companion;
        this(r11, r12, r13, r0.toRequestDataBundle$credentials(r11, r12), r0.toRequestDataBundle$credentials(r11, r12), 0, 32, null);
    }

    public /* synthetic */ GetPublicKeyCredentialOption(String r1, byte[] r2, Set r3, int r4, DefaultConstructorMarker r5) {
        if ((r4 & 2) == 0) goto L6;
        r2 = null;
    L6:
        if ((r4 & 4) == 0) goto L8;
        r3 = SetsKt.emptySet();
    L8:
        this(r1, r2, r3);
    }

    private GetPublicKeyCredentialOption(String r9, byte[] r10, Set<ComponentName> r11, Bundle r12, Bundle r13, int r14) {
        super("androidx.credentials.TYPE_PUBLIC_KEY_CREDENTIAL", r12, r13, false, true, r11, r14);
        this.requestJson = r9;
        this.clientDataHash = r10;
        if (RequestValidationHelper.Companion.isValidJSON(r9) == false) goto L5;
        return;
    L5:
        f$$ExternalSyntheticBUOutline1.m("requestJson must not be empty, and must be a valid JSON");
        throw null;
    }

    public /* synthetic */ GetPublicKeyCredentialOption(String r8, byte[] r9, Set r10, Bundle r11, Bundle r12, int r13, int r14, DefaultConstructorMarker r15) {
        if ((r14 & 32) == 0) goto L5;
        r13 = 100;
    L5:
        this(r8, r9, r10, r11, r12, r13);
    }

    public final String getRequestJson() {
        return this.requestJson;
    }
}
