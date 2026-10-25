package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.PendingIntent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.PlatformVersion;

/* loaded from: classes4.dex */
public final class Status extends AbstractSafeParcelable implements Result, ReflectedParcelable {
    public static final Parcelable.Creator<Status> CREATOR = null;
    public static final Status RESULT_API_NOT_CONNECTED = null;
    public static final Status RESULT_CANCELED = null;
    public static final Status RESULT_DEAD_CLIENT = null;
    public static final Status RESULT_INTERNAL_ERROR = null;
    public static final Status RESULT_INTERRUPTED = null;
    public static final Status RESULT_SUCCESS = null;
    public static final Status RESULT_SUCCESS_CACHE = null;
    public static final Status RESULT_TIMEOUT = null;
    private final int zza;
    private final String zzb;
    private final PendingIntent zzc;
    private final ConnectionResult zzd;

    static {
        RESULT_SUCCESS_CACHE = new Status(-1);
        RESULT_SUCCESS = new Status(0);
        RESULT_INTERRUPTED = new Status(14);
        RESULT_INTERNAL_ERROR = new Status(8);
        RESULT_TIMEOUT = new Status(15);
        RESULT_CANCELED = new Status(16);
        RESULT_API_NOT_CONNECTED = new Status(17);
        RESULT_DEAD_CLIENT = new Status(18);
        CREATOR = new zze();
    }

    public Status(int r2) {
        this(r2, null);
    }

    public Status(int r2, String r3) {
        this(r2, r3, null);
    }

    public Status(int r2, String r3, PendingIntent r4) {
        this(r2, r3, r4, null);
    }

    public Status(int r1, String r2, PendingIntent r3, ConnectionResult r4) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r3;
        this.zzd = r4;
    }

    public Status(ConnectionResult r2, String r3) {
        this(r2, r3, 17);
    }

    @Deprecated
    public Status(ConnectionResult r2, String r3, int r4) {
        this(r4, r3, r2.getResolution(), r2);
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof Status) == true) goto L5;
        return false;
    L5:
        Status r42 = (Status) r4;
        if (this.zza == r42.zza) goto L8;
    L15:
        return false;
    L8:
        if (Objects.equal(this.zzb, r42.zzb) == false) goto L15;
        if (Objects.equal(this.zzc, r42.zzc) == false) goto L15;
        if (Objects.equal(this.zzd, r42.zzd) == false) goto L15;
        return true;
    }

    public ConnectionResult getConnectionResult() {
        return this.zzd;
    }

    @Override // com.google.android.gms.common.api.Result
    public Status getStatus() {
        return this;
    }

    public int getStatusCode() {
        return this.zza;
    }

    public String getStatusMessage() {
        return this.zzb;
    }

    public boolean hasResolution() {
        if (this.zzc == null) goto L6;
        return true;
    L6:
        return false;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zza), this.zzb, this.zzc, this.zzd});
    }

    public boolean isSuccess() {
        if (this.zza > 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void startResolutionForResult(Activity r10, int r11) {
        if (hasResolution() == true) goto L6;
        return;
    L6:
        if (PlatformVersion.isAtLeastU() == false) goto L9;
        Bundle r0 = ActivityOptions.makeBasic().setPendingIntentBackgroundActivityStartMode(1).toBundle();
    L8:
        Bundle r8 = r0;
        PendingIntent r9 = this.zzc;
        Preconditions.checkNotNull(r9);
        r10.startIntentSenderForResult(r9.getIntentSender(), r11, null, 0, 0, 0, r8);
        return;
    L9:
        r0 = null;
        goto L8
    }

    public String toString() {
        Objects.ToStringHelper r0 = Objects.toStringHelper(this);
        r0.add("statusCode", zza());
        r0.add("resolution", this.zzc);
        return r0.toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 1, getStatusCode());
        SafeParcelWriter.writeString(r5, 2, getStatusMessage(), false);
        SafeParcelWriter.writeParcelable(r5, 3, this.zzc, r6, false);
        SafeParcelWriter.writeParcelable(r5, 4, getConnectionResult(), r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    public final String zza() {
        String r0 = this.zzb;
        if (r0 == null) goto L6;
        return r0;
    L6:
        return CommonStatusCodes.getStatusCodeString(this.zza);
    }
}
