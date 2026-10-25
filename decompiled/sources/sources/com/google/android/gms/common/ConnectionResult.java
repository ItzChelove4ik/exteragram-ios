package com.google.android.gms.common;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;

/* loaded from: classes4.dex */
public final class ConnectionResult extends AbstractSafeParcelable {
    public static final Parcelable.Creator<ConnectionResult> CREATOR = null;
    public static final ConnectionResult RESULT_SUCCESS = null;
    final int zza;
    private final int zzb;
    private final PendingIntent zzc;
    private final String zzd;
    private final Integer zze;

    static {
        RESULT_SUCCESS = new ConnectionResult(0);
        CREATOR = new zza();
    }

    public ConnectionResult(int r2) {
        this(r2, null, null);
    }

    public ConnectionResult(int r1, int r2, PendingIntent r3, String r4, Integer r5) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
        this.zze = r5;
    }

    public ConnectionResult(int r2, PendingIntent r3) {
        this(r2, r3, null);
    }

    public ConnectionResult(int r7, PendingIntent r8, String r9) {
        this(1, r7, r8, r9, null);
    }

    public ConnectionResult(int r7, PendingIntent r8, String r9, Integer r10) {
        this(1, r7, r8, r9, r10);
    }

    public static String zza(int r2) {
        if (r2 != 99) goto L5;
        return "UNFINISHED";
    L5:
        if (r2 == 1500) goto L62;
        switch(r2) {
            case -1: goto L60;
            case 0: goto L58;
            case 1: goto L56;
            case 2: goto L54;
            case 3: goto L52;
            case 4: goto L50;
            case 5: goto L48;
            case 6: goto L46;
            case 7: goto L44;
            case 8: goto L42;
            case 9: goto L40;
            case 10: goto L38;
            case 11: goto L36;
            default: goto L7;
        };
    L7:
        switch(r2) {
            case 13: goto L34;
            case 14: goto L32;
            case 15: goto L30;
            case 16: goto L28;
            case 17: goto L26;
            case 18: goto L24;
            case 19: goto L22;
            case 20: goto L20;
            case 21: goto L18;
            case 22: goto L16;
            case 23: goto L14;
            case 24: goto L12;
            case 25: goto L10;
            default: goto L8;
        };
    L8:
        StringBuilder r1 = new StringBuilder(String.valueOf(r2).length() + 20);
        r1.append("UNKNOWN_ERROR_CODE(");
        r1.append(r2);
        r1.append(")");
        return r1.toString();
    L10:
        return "API_INSTALL_REQUIRED";
    L12:
        return "API_DISABLED_FOR_CONNECTION";
    L14:
        return "API_DISABLED";
    L16:
        return "RESOLUTION_ACTIVITY_NOT_FOUND";
    L18:
        return "API_VERSION_UPDATE_REQUIRED";
    L20:
        return "RESTRICTED_PROFILE";
    L22:
        return "SERVICE_MISSING_PERMISSION";
    L24:
        return "SERVICE_UPDATING";
    L26:
        return "SIGN_IN_FAILED";
    L28:
        return "API_UNAVAILABLE";
    L30:
        return "INTERRUPTED";
    L32:
        return "TIMEOUT";
    L34:
        return "CANCELED";
    L36:
        return "LICENSE_CHECK_FAILED";
    L38:
        return "DEVELOPER_ERROR";
    L40:
        return "SERVICE_INVALID";
    L42:
        return "INTERNAL_ERROR";
    L44:
        return "NETWORK_ERROR";
    L46:
        return "RESOLUTION_REQUIRED";
    L48:
        return "INVALID_ACCOUNT";
    L50:
        return "SIGN_IN_REQUIRED";
    L52:
        return "SERVICE_DISABLED";
    L54:
        return "SERVICE_VERSION_UPDATE_REQUIRED";
    L56:
        return "SERVICE_MISSING";
    L58:
        return "SUCCESS";
    L60:
        return "UNKNOWN";
    L62:
        return "DRIVE_EXTERNAL_STORAGE_REQUIRED";
    }

    public boolean equals(Object r5) {
        if (r5 != this) goto L6;
        return true;
    L6:
        if ((r5 instanceof ConnectionResult) == true) goto L8;
        return false;
    L8:
        ConnectionResult r52 = (ConnectionResult) r5;
        if (this.zzb == r52.zzb) goto L11;
    L17:
        return false;
    L11:
        if (Objects.equal(this.zzc, r52.zzc) == false) goto L17;
        if (Objects.equal(this.zzd, r52.zzd) == false) goto L17;
        if (Objects.equal(this.zze, r52.zze) == false) goto L17;
        return true;
    }

    public Integer getClientMethodKey() {
        return this.zze;
    }

    public int getErrorCode() {
        return this.zzb;
    }

    public String getErrorMessage() {
        return this.zzd;
    }

    public PendingIntent getResolution() {
        return this.zzc;
    }

    public boolean hasResolution() {
        if (this.zzb != 0) goto L5;
        return false;
    L5:
        if (this.zzc == null) goto L10;
        return true;
    L10:
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zzb), this.zzc, this.zzd, this.zze});
    }

    public boolean isSuccess() {
        if (this.zzb != 0) goto L6;
        return true;
    L6:
        return false;
    }

    public String toString() {
        Objects.ToStringHelper r0 = Objects.toStringHelper(this);
        r0.add("statusCode", zza(this.zzb));
        r0.add("resolution", this.zzc);
        r0.add("message", this.zzd);
        r0.add("clientMethodKey", this.zze);
        return r0.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = this.zza;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 1, r0);
        SafeParcelWriter.writeInt(r5, 2, getErrorCode());
        SafeParcelWriter.writeParcelable(r5, 3, getResolution(), r6, false);
        SafeParcelWriter.writeString(r5, 4, getErrorMessage(), false);
        SafeParcelWriter.writeIntegerObject(r5, 5, getClientMethodKey(), false);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }
}
