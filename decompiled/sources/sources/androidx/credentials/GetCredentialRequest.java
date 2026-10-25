package androidx.credentials;

import android.content.ComponentName;
import android.os.Bundle;
import c.f$$ExternalSyntheticBUOutline1;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\u0018\u0000 \u00172\u00020\u0001:\u0002\u0016\u0017BC\b\u0007\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\b¢\u0006\u0004\b\f\u0010\rR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0013\u0010\t\u001a\u0004\u0018\u00010\n¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\u000b\u001a\u00020\b8\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0013¨\u0006\u0018"}, d2 = {"Landroidx/credentials/GetCredentialRequest;", _UrlKt.FRAGMENT_ENCODE_SET, "credentialOptions", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/credentials/CredentialOption;", "origin", _UrlKt.FRAGMENT_ENCODE_SET, "preferIdentityDocUi", _UrlKt.FRAGMENT_ENCODE_SET, "preferUiBrandingComponentName", "Landroid/content/ComponentName;", "preferImmediatelyAvailableCredentials", "<init>", "(Ljava/util/List;Ljava/lang/String;ZLandroid/content/ComponentName;Z)V", "getCredentialOptions", "()Ljava/util/List;", "getOrigin", "()Ljava/lang/String;", "getPreferIdentityDocUi", "()Z", "getPreferUiBrandingComponentName", "()Landroid/content/ComponentName;", "Builder", "Companion", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGetCredentialRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetCredentialRequest.kt\nandroidx/credentials/GetCredentialRequest\n+ 2 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,276:1\n1#2:277\n1788#3,4:278\n*S KotlinDebug\n*F\n+ 1 GetCredentialRequest.kt\nandroidx/credentials/GetCredentialRequest\n*L\n81#1:278,4\n*E\n"})
/* loaded from: classes3.dex */
public final class GetCredentialRequest {
    public static final Companion Companion = null;
    private final List<CredentialOption> credentialOptions;
    private final String origin;
    private final boolean preferIdentityDocUi;
    private final boolean preferImmediatelyAvailableCredentials;
    private final ComponentName preferUiBrandingComponentName;

    @Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0015\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0015\u0010\n\u001a\u00020\u00002\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\u0015\u0010\u000e\u001a\u00020\u00002\u0006\u0010\r\u001a\u00020\f¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0011\u001a\u00020\u0010¢\u0006\u0004\b\u0011\u0010\u0012R\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u00138\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0014\u0010\u0015R\u0018\u0010\t\u001a\u0004\u0018\u00010\b8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\t\u0010\u0016R\u0016\u0010\u0017\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u0017\u0010\u0018R\u0016\u0010\r\u001a\u00020\f8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\r\u0010\u0018R\u0018\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001a\u0010\u001b¨\u0006\u001c"}, d2 = {"Landroidx/credentials/GetCredentialRequest$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/credentials/CredentialOption;", "credentialOption", "addCredentialOption", "(Landroidx/credentials/CredentialOption;)Landroidx/credentials/GetCredentialRequest$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "origin", "setOrigin", "(Ljava/lang/String;)Landroidx/credentials/GetCredentialRequest$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "preferImmediatelyAvailableCredentials", "setPreferImmediatelyAvailableCredentials", "(Z)Landroidx/credentials/GetCredentialRequest$Builder;", "Landroidx/credentials/GetCredentialRequest;", "build", "()Landroidx/credentials/GetCredentialRequest;", _UrlKt.FRAGMENT_ENCODE_SET, "credentialOptions", "Ljava/util/List;", "Ljava/lang/String;", "preferIdentityDocUi", "Z", "Landroid/content/ComponentName;", "preferUiBrandingComponentName", "Landroid/content/ComponentName;", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Builder {
        private List<CredentialOption> credentialOptions;
        private String origin;
        private boolean preferIdentityDocUi;
        private boolean preferImmediatelyAvailableCredentials;
        private ComponentName preferUiBrandingComponentName;

        public Builder() {
            this.credentialOptions = new ArrayList();
        }

        public final Builder addCredentialOption(CredentialOption r2) {
            this.credentialOptions.add(r2);
            return this;
        }

        public final GetCredentialRequest build() {
            return new GetCredentialRequest(CollectionsKt.toList(this.credentialOptions), this.origin, this.preferIdentityDocUi, this.preferUiBrandingComponentName, this.preferImmediatelyAvailableCredentials);
        }

        public final Builder setOrigin(String r1) {
            this.origin = r1;
            return this;
        }

        public final Builder setPreferImmediatelyAvailableCredentials(boolean r1) {
            this.preferImmediatelyAvailableCredentials = r1;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\bR\u0014\u0010\n\u001a\u00020\t8\u0000X\u0080T¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\f\u0010\u000bR\u0014\u0010\r\u001a\u00020\t8\u0002X\u0082T¢\u0006\u0006\n\u0004\b\r\u0010\u000b¨\u0006\u000e"}, d2 = {"Landroidx/credentials/GetCredentialRequest$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroidx/credentials/GetCredentialRequest;", PluginsConstants.REQUEST, "Landroid/os/Bundle;", "getRequestMetadataBundle", "(Landroidx/credentials/GetCredentialRequest;)Landroid/os/Bundle;", _UrlKt.FRAGMENT_ENCODE_SET, "BUNDLE_KEY_PREFER_IMMEDIATELY_AVAILABLE_CREDENTIALS", "Ljava/lang/String;", "BUNDLE_KEY_PREFER_IDENTITY_DOC_UI", "BUNDLE_KEY_PREFER_UI_BRANDING_COMPONENT_NAME", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    @SourceDebugExtension({"SMAP\nGetCredentialRequest.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetCredentialRequest.kt\nandroidx/credentials/GetCredentialRequest$Companion\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,276:1\n1563#2:277\n1634#2,3:278\n*S KotlinDebug\n*F\n+ 1 GetCredentialRequest.kt\nandroidx/credentials/GetCredentialRequest$Companion\n*L\n226#1:277\n226#1:278,3\n*E\n"})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final Bundle getRequestMetadataBundle(GetCredentialRequest r3) {
            Bundle r2 = new Bundle();
            r2.putBoolean("androidx.credentials.BUNDLE_KEY_PREFER_IDENTITY_DOC_UI", r3.getPreferIdentityDocUi());
            r2.putBoolean("androidx.credentials.BUNDLE_KEY_PREFER_IMMEDIATELY_AVAILABLE_CREDENTIALS", r3.preferImmediatelyAvailableCredentials());
            r2.putParcelable("androidx.credentials.BUNDLE_KEY_PREFER_UI_BRANDING_COMPONENT_NAME", r3.getPreferUiBrandingComponentName());
            return r2;
        }
    }

    static {
        Companion = new Companion(null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @JvmOverloads
    public GetCredentialRequest(List<? extends CredentialOption> r1, String r2, boolean r3, ComponentName r4, boolean r5) {
        this.credentialOptions = r1;
        this.origin = r2;
        this.preferIdentityDocUi = r3;
        this.preferUiBrandingComponentName = r4;
        this.preferImmediatelyAvailableCredentials = r5;
        if (r1.isEmpty() == false) goto L5;
        f$$ExternalSyntheticBUOutline1.m("credentialOptions should not be empty");
        throw null;
    L5:
        if (r1.size() <= 1) goto L19;
        List<? extends CredentialOption> r12 = r1;
        if ((r12 instanceof Collection) == true) goto L9;
    L11:
        Iterator r13 = r12.iterator();
    L13:
        if (r13.hasNext() == false) goto L15;
        CredentialOption r22 = (CredentialOption) r13.next();
    L15:
        Iterator<CredentialOption> r0 = this.credentialOptions.iterator();
    L17:
        if (r0.hasNext() == false) goto L24;
        CredentialOption r14 = r0.next();
        goto L17
    L24:
        return;
    L9:
        if (r12.isEmpty() == false) goto L11;
    }

    public final List<CredentialOption> getCredentialOptions() {
        return this.credentialOptions;
    }

    public final String getOrigin() {
        return this.origin;
    }

    public final boolean getPreferIdentityDocUi() {
        return this.preferIdentityDocUi;
    }

    public final ComponentName getPreferUiBrandingComponentName() {
        return this.preferUiBrandingComponentName;
    }

    @JvmName(name = "preferImmediatelyAvailableCredentials")
    public final boolean preferImmediatelyAvailableCredentials() {
        return this.preferImmediatelyAvailableCredentials;
    }
}
