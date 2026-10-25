package com.google.android.libraries.identity.googleid;

import android.net.Uri;
import android.os.Bundle;
import androidx.credentials.CustomCredential;
import c.f$$ExternalSyntheticBUOutline1;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \r2\u00020\u0001:\u0002\u000e\rBI\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0004\u001a\u00020\u0002\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010\t\u001a\u0004\u0018\u00010\b\u0012\b\u0010\n\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u000b\u0010\f¨\u0006\u000f"}, d2 = {"Lcom/google/android/libraries/identity/googleid/GoogleIdTokenCredential;", "Landroidx/credentials/CustomCredential;", _UrlKt.FRAGMENT_ENCODE_SET, "id", "idToken", "displayName", "familyName", "givenName", "Landroid/net/Uri;", "profilePictureUri", "phoneNumber", "<init>", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;Ljava/lang/String;)V", "Companion", "Builder", "java.com.google.android.libraries.identity.googleid.granule_granule"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class GoogleIdTokenCredential extends CustomCredential {
    public static final Companion Companion = null;
    private final String zza;
    private final String zzb;
    private final String zzc;
    private final String zzd;
    private final String zze;
    private final Uri zzf;
    private final String zzg;

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJ\u0010\u0010\u000e\u001a\u00020\u00002\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u000f\u001a\u00020\u00002\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0010\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004J\u000e\u0010\u0011\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\b\u001a\u00020\u0004J\u0010\u0010\u0013\u001a\u00020\u00002\b\u0010\t\u001a\u0004\u0018\u00010\u0004J\u0010\u0010\u0014\u001a\u00020\u00002\b\u0010\n\u001a\u0004\u0018\u00010\u000bR\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Lcom/google/android/libraries/identity/googleid/GoogleIdTokenCredential$Builder;", _UrlKt.FRAGMENT_ENCODE_SET, "()V", "displayName", _UrlKt.FRAGMENT_ENCODE_SET, "familyName", "givenName", "id", "idToken", "phoneNumber", "profilePictureUri", "Landroid/net/Uri;", "build", "Lcom/google/android/libraries/identity/googleid/GoogleIdTokenCredential;", "setDisplayName", "setFamilyName", "setGivenName", "setId", "setIdToken", "setPhoneNumber", "setProfilePictureUri", "java.com.google.android.libraries.identity.googleid.granule_granule"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Builder {
        private String zza;
        private String zzb;
        private String zzc;
        private String zzd;
        private String zze;
        private Uri zzf;
        private String zzg;

        public Builder() {
            this.zza = _UrlKt.FRAGMENT_ENCODE_SET;
            this.zzb = _UrlKt.FRAGMENT_ENCODE_SET;
        }

        public final GoogleIdTokenCredential build() {
            return new GoogleIdTokenCredential(this.zza, this.zzb, this.zzc, this.zzd, this.zze, this.zzf, this.zzg);
        }

        public final Builder setDisplayName(String r1) {
            this.zzc = r1;
            return this;
        }

        public final Builder setFamilyName(String r1) {
            this.zzd = r1;
            return this;
        }

        public final Builder setGivenName(String r1) {
            this.zze = r1;
            return this;
        }

        public final Builder setId(String r1) {
            this.zza = r1;
            return this;
        }

        public final Builder setIdToken(String r1) {
            this.zzb = r1;
            return this;
        }

        public final Builder setPhoneNumber(String r1) {
            this.zzg = r1;
            return this;
        }

        public final Builder setProfilePictureUri(Uri r1) {
            this.zzf = r1;
            return this;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u0086\u0003\u0018\u00002\u00020\u0001JQ\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u00022\b\u0010\u0005\u001a\u0004\u0018\u00010\u00022\b\u0010\u0006\u001a\u0004\u0018\u00010\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\u00022\b\u0010\t\u001a\u0004\u0018\u00010\b2\b\u0010\n\u001a\u0004\u0018\u00010\u0002H\u0001¢\u0006\u0004\b\f\u0010\rR\u001a\u0010\u000f\u001a\u00020\u00028\u0000X\u0081T¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u0012\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u00028\u0000X\u0081T¢\u0006\f\n\u0004\b\u0013\u0010\u0010\u0012\u0004\b\u0014\u0010\u0012R\u001a\u0010\u0015\u001a\u00020\u00028\u0000X\u0081T¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u0012\u0004\b\u0016\u0010\u0012R\u0014\u0010\u0017\u001a\u00020\u00028\u0006X\u0086T¢\u0006\u0006\n\u0004\b\u0017\u0010\u0010R\u001a\u0010\u0018\u001a\u00020\u00028\u0000X\u0081T¢\u0006\f\n\u0004\b\u0018\u0010\u0010\u0012\u0004\b\u0019\u0010\u0012R\u001a\u0010\u001a\u001a\u00020\u00028\u0000X\u0081T¢\u0006\f\n\u0004\b\u001a\u0010\u0010\u0012\u0004\b\u001b\u0010\u0012R\u001a\u0010\u001c\u001a\u00020\u00028\u0000X\u0081T¢\u0006\f\n\u0004\b\u001c\u0010\u0010\u0012\u0004\b\u001d\u0010\u0012R\u001a\u0010\u001e\u001a\u00020\u00028\u0000X\u0081T¢\u0006\f\n\u0004\b\u001e\u0010\u0010\u0012\u0004\b\u001f\u0010\u0012R\u0014\u0010 \u001a\u00020\u00028\u0006X\u0086T¢\u0006\u0006\n\u0004\b \u0010\u0010R\u0014\u0010!\u001a\u00020\u00028\u0006X\u0086T¢\u0006\u0006\n\u0004\b!\u0010\u0010¨\u0006\""}, d2 = {"Lcom/google/android/libraries/identity/googleid/GoogleIdTokenCredential$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, "id", "idToken", "displayName", "familyName", "givenName", "Landroid/net/Uri;", "profilePictureUri", "phoneNumber", "Landroid/os/Bundle;", "toBundle$java_com_google_android_libraries_identity_googleid_granule_granule", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;Ljava/lang/String;)Landroid/os/Bundle;", "toBundle", "BUNDLE_KEY_DISPLAY_NAME", "Ljava/lang/String;", "getBUNDLE_KEY_DISPLAY_NAME$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "()V", "BUNDLE_KEY_FAMILY_NAME", "getBUNDLE_KEY_FAMILY_NAME$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_GIVEN_NAME", "getBUNDLE_KEY_GIVEN_NAME$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_GOOGLE_ID_TOKEN_SUBTYPE", "BUNDLE_KEY_ID", "getBUNDLE_KEY_ID$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_ID_TOKEN", "getBUNDLE_KEY_ID_TOKEN$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_PHONE_NUMBER", "getBUNDLE_KEY_PHONE_NUMBER$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "BUNDLE_KEY_PROFILE_PICTURE_URI", "getBUNDLE_KEY_PROFILE_PICTURE_URI$java_com_google_android_libraries_identity_googleid_granule_granule$annotations", "TYPE_GOOGLE_ID_TOKEN_CREDENTIAL", "TYPE_GOOGLE_ID_TOKEN_SIWG_CREDENTIAL", "java.com.google.android.libraries.identity.googleid.granule_granule"}, k = 1, mv = {1, 8, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
        }
    }

    static {
        Companion = new Companion(null);
    }

    public GoogleIdTokenCredential(String r3, String r4, String r5, String r6, String r7, Uri r8, String r9) {
        Bundle r0 = new Bundle();
        r0.putString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_ID", r3);
        r0.putString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_ID_TOKEN", r4);
        r0.putString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_DISPLAY_NAME", r5);
        r0.putString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_FAMILY_NAME", r6);
        r0.putString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_GIVEN_NAME", r7);
        r0.putString("com.google.android.libraries.identity.googleid.BUNDLE_KEY_PHONE_NUMBER", r9);
        r0.putParcelable("com.google.android.libraries.identity.googleid.BUNDLE_KEY_PROFILE_PICTURE_URI", r8);
        super("com.google.android.libraries.identity.googleid.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL", r0);
        this.zza = r3;
        this.zzb = r4;
        this.zzc = r5;
        this.zzd = r6;
        this.zze = r7;
        this.zzf = r8;
        this.zzg = r9;
        if (r3.length() > 0) goto L5;
        f$$ExternalSyntheticBUOutline1.m("id should not be empty");
        throw null;
    L5:
        if (r4.length() <= 0) goto L7;
        return;
    L7:
        f$$ExternalSyntheticBUOutline1.m("idToken should not be empty");
        throw null;
    }
}
