package com.google.android.gms.fido.fido2.api.common;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.Base64Utils;
import com.google.android.gms.internal.fido.zzbi;
import com.google.android.gms.internal.fido.zzbj;
import com.google.android.gms.internal.fido.zzgf;
import com.google.android.gms.internal.fido.zzgx;
import org.json.JSONException;
import org.json.JSONObject;
import org.mvel2.util.Make$Map$$ExternalSyntheticBUOutline0;

/* loaded from: classes4.dex */
public class AuthenticatorAssertionResponse extends AuthenticatorResponse {
    public static final Parcelable.Creator<AuthenticatorAssertionResponse> CREATOR = null;
    private final zzgx zza;
    private final zzgx zzb;
    private final zzgx zzc;
    private final zzgx zzd;
    private final zzgx zze;

    static {
        CREATOR = new zzj();
    }

    public AuthenticatorAssertionResponse(byte[] r3, byte[] r4, byte[] r5, byte[] r6, byte[] r7) {
        byte[] r32 = (byte[]) Preconditions.checkNotNull(r3);
        zzgx r0 = zzgx.zzb;
        zzgx r33 = zzgx.zzl(r32, 0, r32.length);
        byte[] r42 = (byte[]) Preconditions.checkNotNull(r4);
        zzgx r43 = zzgx.zzl(r42, 0, r42.length);
        byte[] r52 = (byte[]) Preconditions.checkNotNull(r5);
        zzgx r53 = zzgx.zzl(r52, 0, r52.length);
        byte[] r62 = (byte[]) Preconditions.checkNotNull(r6);
        zzgx r63 = zzgx.zzl(r62, 0, r62.length);
        if (r7 != null) goto L5;
        zzgx r72 = null;
    L6:
        this.zza = (zzgx) Preconditions.checkNotNull(r33);
        this.zzb = (zzgx) Preconditions.checkNotNull(r43);
        this.zzc = (zzgx) Preconditions.checkNotNull(r53);
        this.zzd = (zzgx) Preconditions.checkNotNull(r63);
        this.zze = r72;
        return;
    L5:
        r72 = zzgx.zzl(r7, 0, r7.length);
        goto L6
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof AuthenticatorAssertionResponse) == true) goto L5;
        return false;
    L5:
        AuthenticatorAssertionResponse r42 = (AuthenticatorAssertionResponse) r4;
        if (Objects.equal(this.zza, r42.zza) == true) goto L8;
    L17:
        return false;
    L8:
        if (Objects.equal(this.zzb, r42.zzb) == false) goto L17;
        if (Objects.equal(this.zzc, r42.zzc) == false) goto L17;
        if (Objects.equal(this.zzd, r42.zzd) == false) goto L17;
        if (Objects.equal(this.zze, r42.zze) == false) goto L17;
        return true;
    }

    public byte[] getAuthenticatorData() {
        return this.zzc.zzm();
    }

    public byte[] getClientDataJSON() {
        return this.zzb.zzm();
    }

    @Deprecated
    public byte[] getKeyHandle() {
        return this.zza.zzm();
    }

    public byte[] getSignature() {
        return this.zzd.zzm();
    }

    public byte[] getUserHandle() {
        zzgx r0 = this.zze;
        if (r0 != null) goto L7;
        return null;
    L7:
        return r0.zzm();
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(Objects.hashCode(new Object[]{this.zza})), Integer.valueOf(Objects.hashCode(new Object[]{this.zzb})), Integer.valueOf(Objects.hashCode(new Object[]{this.zzc})), Integer.valueOf(Objects.hashCode(new Object[]{this.zzd})), Integer.valueOf(Objects.hashCode(new Object[]{this.zze}))});
    }

    public String toString() {
        zzbi r0 = zzbj.zza(this);
        zzgf r1 = zzgf.zzf();
        byte[] r2 = getKeyHandle();
        r0.zzb("keyHandle", r1.zzg(r2, 0, r2.length));
        zzgf r12 = zzgf.zzf();
        byte[] r22 = getClientDataJSON();
        r0.zzb("clientDataJSON", r12.zzg(r22, 0, r22.length));
        zzgf r13 = zzgf.zzf();
        byte[] r23 = getAuthenticatorData();
        r0.zzb("authenticatorData", r13.zzg(r23, 0, r23.length));
        zzgf r14 = zzgf.zzf();
        byte[] r24 = getSignature();
        r0.zzb("signature", r14.zzg(r24, 0, r24.length));
        byte[] r5 = getUserHandle();
        if (r5 == null) goto L6;
        r0.zzb("userHandle", zzgf.zzf().zzg(r5, 0, r5.length));
    L6:
        return r0.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r4, int r5) {
        int r52 = SafeParcelWriter.beginObjectHeader(r4);
        SafeParcelWriter.writeByteArray(r4, 2, getKeyHandle(), false);
        SafeParcelWriter.writeByteArray(r4, 3, getClientDataJSON(), false);
        SafeParcelWriter.writeByteArray(r4, 4, getAuthenticatorData(), false);
        SafeParcelWriter.writeByteArray(r4, 5, getSignature(), false);
        SafeParcelWriter.writeByteArray(r4, 6, getUserHandle(), false);
        SafeParcelWriter.finishObjectHeader(r4, r52);
    }

    public final JSONObject zza() {
        JSONObject r0 = new JSONObject();     // Catch: JSONException -> L6
        r0.put("clientDataJSON", Base64Utils.encodeUrlSafeNoPadding(getClientDataJSON()));     // Catch: JSONException -> L6
        r0.put("authenticatorData", Base64Utils.encodeUrlSafeNoPadding(getAuthenticatorData()));     // Catch: JSONException -> L6
        r0.put("signature", Base64Utils.encodeUrlSafeNoPadding(getSignature()));     // Catch: JSONException -> L6
        if (this.zze == null) goto L5;
        r0.put("userHandle", Base64Utils.encodeUrlSafeNoPadding(getUserHandle()));     // Catch: JSONException -> L6
    L5:
        return r0;
    L6:
        e = move-exception;
        Make$Map$$ExternalSyntheticBUOutline0.m("Error encoding AuthenticatorAssertionResponse to JSON object", e);
        return null;
    }
}
