package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.core.app.NavUtils$$ExternalSyntheticBUOutline0;
import com.google.android.gms.cast.MediaError;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.fido.zzbk;
import java.util.Arrays;

/* loaded from: classes4.dex */
public class TokenBinding extends AbstractSafeParcelable {
    public static final Parcelable.Creator<TokenBinding> CREATOR = null;
    public static final TokenBinding NOT_SUPPORTED = null;
    public static final TokenBinding SUPPORTED = null;
    private final TokenBindingStatus zza;
    private final String zzb;

    public enum TokenBindingStatus extends Enum<TokenBindingStatus> implements Parcelable {
        public static final Parcelable.Creator<TokenBindingStatus> CREATOR = null;
        public static final TokenBindingStatus NOT_SUPPORTED = null;
        public static final TokenBindingStatus PRESENT = null;
        public static final TokenBindingStatus SUPPORTED = null;
        private static final /* synthetic */ TokenBindingStatus[] zza = null;
        private final String zzb;

        static {
            TokenBindingStatus r0 = new TokenBindingStatus("PRESENT", 0, "present");
            PRESENT = r0;
            TokenBindingStatus r1 = new TokenBindingStatus("SUPPORTED", 1, "supported");
            SUPPORTED = r1;
            TokenBindingStatus r2 = new TokenBindingStatus(MediaError.ERROR_REASON_NOT_SUPPORTED, 2, "not-supported");
            NOT_SUPPORTED = r2;
            zza = new TokenBindingStatus[]{r0, r1, r2};
            CREATOR = new zzay();
        }

        TokenBindingStatus(String r1, int r2, String r3) {
            this.zzb = r3;
        }

        public static TokenBindingStatus fromString(String r5) {
            TokenBindingStatus[] r0 = values();
            int r1 = r0.length;
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L9;
            TokenBindingStatus r3 = r0[r2];
            if (r5.equals(r3.zzb) == true) goto L6;
            r2 = r2 + 1;
            goto L3
        L6:
            return r3;
        L9:
            throw new UnsupportedTokenBindingStatusException(r5);
        }

        public static TokenBindingStatus valueOf(String r1) {
            return (TokenBindingStatus) Enum.valueOf(TokenBindingStatus.class, r1);
        }

        public static TokenBindingStatus[] values() {
            return (TokenBindingStatus[]) zza.clone();
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        @Override // java.lang.Enum
        public String toString() {
            return this.zzb;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r1, int r2) {
            r1.writeString(this.zzb);
        }
    }

    public static class UnsupportedTokenBindingStatusException extends Exception {
        public UnsupportedTokenBindingStatusException(String r2) {
            super(String.format("TokenBindingStatus %s not supported", new Object[]{r2}));
        }
    }

    static {
        CREATOR = new zzaz();
        SUPPORTED = new TokenBinding(TokenBindingStatus.SUPPORTED.toString(), null);
        NOT_SUPPORTED = new TokenBinding(TokenBindingStatus.NOT_SUPPORTED.toString(), null);
    }

    public TokenBinding(String r1, String r2) {
        Preconditions.checkNotNull(r1);
        this.zza = TokenBindingStatus.fromString(r1);     // Catch: UnsupportedTokenBindingStatusException -> L6
        this.zzb = r2;
        return;
    L6:
        e = move-exception;
        NavUtils$$ExternalSyntheticBUOutline0.m(e);
        throw null;
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof TokenBinding) == true) goto L5;
        return false;
    L5:
        TokenBinding r42 = (TokenBinding) r4;
        if (zzbk.zza(this.zza, r42.zza) == true) goto L8;
    L11:
        return false;
    L8:
        if (zzbk.zza(this.zzb, r42.zzb) == false) goto L11;
        return true;
    }

    public String getTokenBindingId() {
        return this.zzb;
    }

    public String getTokenBindingStatusAsString() {
        return this.zza.toString();
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{this.zza, this.zzb});
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeString(r4, 2, getTokenBindingStatusAsString(), false);
        SafeParcelWriter.writeString(r4, 3, getTokenBindingId(), false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }
}
