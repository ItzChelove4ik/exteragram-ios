package com.google.android.gms.identitycredentials;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 BE\b\u0007\u0012\b\b\u0003\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0006\u001a\u00020\u0005\u0012\b\b\u0001\u0010\u0007\u001a\u00020\u0003\u0012\b\b\u0003\u0010\b\u001a\u00020\u0003\u0012\b\b\u0003\u0010\t\u001a\u00020\u0003¢\u0006\u0004\b\n\u0010\u000bB\u0011\b\u0017\u0012\u0006\u0010\f\u001a\u00020\u0005¢\u0006\u0004\b\n\u0010\rJ\b\u0010\u0019\u001a\u00020\u0005H\u0007J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016R\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0016\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0016\u0010\u0006\u001a\u00020\u00058\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0011R\u0016\u0010\u0007\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000fR\u001c\u0010\b\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u000fR\u001c\u0010\t\u001a\u00020\u00038\u0006X\u0087\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0017\u0010\u0015\u001a\u0004\b\u0018\u0010\u000f¨\u0006!"}, d2 = {"Lcom/google/android/gms/identitycredentials/CredentialOption;", "Lcom/google/android/gms/common/internal/safeparcel/AbstractSafeParcelable;", "type", _UrlKt.FRAGMENT_ENCODE_SET, "credentialRetrievalData", "Landroid/os/Bundle;", "candidateQueryData", "requestMatcher", "requestType", "protocolType", "<init>", "(Ljava/lang/String;Landroid/os/Bundle;Landroid/os/Bundle;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "bundle", "(Landroid/os/Bundle;)V", "getType", "()Ljava/lang/String;", "getCredentialRetrievalData", "()Landroid/os/Bundle;", "getCandidateQueryData", "getRequestMatcher", "getRequestType$annotations", "()V", "getRequestType", "getProtocolType$annotations", "getProtocolType", "toBundle", "writeToParcel", _UrlKt.FRAGMENT_ENCODE_SET, "dest", "Landroid/os/Parcel;", "flags", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "java.com.google.android.gmscore.integ.client.identity_credentials_identity_credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class CredentialOption extends AbstractSafeParcelable {

    @JvmField
    public static final Parcelable.Creator<CredentialOption> CREATOR = null;
    public static final Companion Companion = null;
    private final Bundle candidateQueryData;
    private final Bundle credentialRetrievalData;
    private final String protocolType;
    private final String requestMatcher;
    private final String requestType;
    private final String type;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00058\u0006X\u0087\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\bX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/google/android/gms/identitycredentials/CredentialOption$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/google/android/gms/identitycredentials/CredentialOption;", "EXTRA_TYPE", _UrlKt.FRAGMENT_ENCODE_SET, "EXTRA_CREDENTIAL_RETRIEVAL_DATA", "EXTRA_CANDIDATE_QUERY_DATA", "EXTRA_REQUEST_MATCHER", "EXTRA_REQUEST_TYPE", "EXTRA_PROTOCOL_TYPE", "java.com.google.android.gmscore.integ.client.identity_credentials_identity_credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
        CREATOR = new CredentialOptionCreator();
    }

    public CredentialOption(String r1, Bundle r2, Bundle r3, String r4, String r5, String r6) {
        this.type = r1;
        this.credentialRetrievalData = r2;
        this.candidateQueryData = r3;
        this.requestMatcher = r4;
        this.requestType = r5;
        this.protocolType = r6;
        boolean r22 = true;
        if (StringsKt.isBlank(r5) == false) goto L5;
    L7:
        boolean r0 = false;
    L9:
        if (StringsKt.isBlank(r1) == false) goto L11;
    L15:
        r22 = false;
    L16:
        if (r0 == true) goto L21;
        if (r22 == true) goto L22;
        int r02 = String.valueOf(r1).length();
        StringBuilder r23 = new StringBuilder(((((r02 + 31) + String.valueOf(r5).length()) + 19) + String.valueOf(r6).length()) + 69);
        r23.append("Either type: ");
        r23.append(r1);
        r23.append(", or requestType: ");
        r23.append(r5);
        r23.append(" and protocolType: ");
        r23.append(r6);
        r23.append(" must be specified, but at least one contains an invalid blank value.");
        throw new IllegalArgumentException(r23.toString());
    L22:
        return;
    L21:
        return;
    L11:
        if (r5.length() != 0) goto L15;
        if (r6.length() != 0) goto L15;
    L5:
        if (StringsKt.isBlank(r6) == true) goto L7;
        r0 = true;
        goto L9
    }

    public final Bundle getCandidateQueryData() {
        return this.candidateQueryData;
    }

    public final Bundle getCredentialRetrievalData() {
        return this.credentialRetrievalData;
    }

    public final String getProtocolType() {
        return this.protocolType;
    }

    public final String getRequestMatcher() {
        return this.requestMatcher;
    }

    public final String getRequestType() {
        return this.requestType;
    }

    public final String getType() {
        return this.type;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r1, int r2) {
        CredentialOptionCreator.writeToParcel(this, r1, r2);
    }
}
