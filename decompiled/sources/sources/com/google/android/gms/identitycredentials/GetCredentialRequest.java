package com.google.android.gms.identitycredentials;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.ResultReceiver;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0010\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u0000 !2\u00020\u00012\u00020\u0002:\u0001!B9\b\u0007\u0012\u000e\b\u0001\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0007\u0012\n\b\u0003\u0010\b\u001a\u0004\u0018\u00010\t\u0012\b\b\u0001\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rB\u0011\b\u0017\u0012\u0006\u0010\u000e\u001a\u00020\u0007¢\u0006\u0004\b\f\u0010\u000fJ\b\u0010\u001a\u001a\u00020\u0007H\u0007J\u0018\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 H\u0016R\u001c\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u00048\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0006\u001a\u00020\u00078\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\b\u001a\u0004\u0018\u00010\t8\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u001c\u0010\n\u001a\u00020\u000b8\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0016\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\""}, d2 = {"Lcom/google/android/gms/identitycredentials/GetCredentialRequest;", "Lcom/google/android/gms/common/internal/safeparcel/AbstractSafeParcelable;", "Lcom/google/android/gms/common/internal/ReflectedParcelable;", "credentialOptions", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/google/android/gms/identitycredentials/CredentialOption;", "data", "Landroid/os/Bundle;", "origin", _UrlKt.FRAGMENT_ENCODE_SET, "resultReceiver", "Landroid/os/ResultReceiver;", "<init>", "(Ljava/util/List;Landroid/os/Bundle;Ljava/lang/String;Landroid/os/ResultReceiver;)V", "bundle", "(Landroid/os/Bundle;)V", "getCredentialOptions", "()Ljava/util/List;", "getData", "()Landroid/os/Bundle;", "getOrigin", "()Ljava/lang/String;", "getResultReceiver$annotations", "()V", "getResultReceiver", "()Landroid/os/ResultReceiver;", "toBundle", "writeToParcel", _UrlKt.FRAGMENT_ENCODE_SET, "dest", "Landroid/os/Parcel;", "flags", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "java.com.google.android.gmscore.integ.client.identity_credentials_identity_credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class GetCredentialRequest extends AbstractSafeParcelable implements ReflectedParcelable {

    @JvmField
    public static final Parcelable.Creator<GetCredentialRequest> CREATOR = null;
    public static final Companion Companion = null;
    private final List<CredentialOption> credentialOptions;
    private final Bundle data;
    private final String origin;
    private final ResultReceiver resultReceiver;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/google/android/gms/identitycredentials/GetCredentialRequest$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/google/android/gms/identitycredentials/GetCredentialRequest;", "EXTRA_ORIGIN", _UrlKt.FRAGMENT_ENCODE_SET, "EXTRA_DATA", "EXTRA_CREDENTIAL_OPTIONS_SIZE", "EXTRA_CREDENTIAL_OPTIONS_PREFIX", "EXTRA_RESULT_RECEIVER", "java.com.google.android.gmscore.integ.client.identity_credentials_identity_credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
        CREATOR = new GetCredentialRequestCreator();
    }

    public GetCredentialRequest(List<CredentialOption> r1, Bundle r2, String r3, ResultReceiver r4) {
        this.credentialOptions = r1;
        this.data = r2;
        this.origin = r3;
        this.resultReceiver = r4;
    }

    public final List<CredentialOption> getCredentialOptions() {
        return this.credentialOptions;
    }

    public final Bundle getData() {
        return this.data;
    }

    public final String getOrigin() {
        return this.origin;
    }

    public final ResultReceiver getResultReceiver() {
        return this.resultReceiver;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        GetCredentialRequestCreator.writeToParcel(this, r1, r2);
    }
}
