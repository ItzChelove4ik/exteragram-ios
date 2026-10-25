package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.WorkSource;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.util.WorkSourceUtil;
import com.google.android.gms.internal.location.zzd;
import com.google.android.gms.internal.location.zzen;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.time.DurationKt;
import org.checkerframework.dataflow.qual.Pure;

/* loaded from: classes5.dex */
public final class LocationRequest extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<LocationRequest> CREATOR = null;
    private int zza;
    private long zzb;
    private long zzc;
    private long zzd;
    private long zze;
    private int zzf;
    private float zzg;
    private boolean zzh;
    private long zzi;
    private final int zzj;
    private final int zzk;
    private final boolean zzl;
    private final WorkSource zzm;
    private final zzd zzn;

    public static final class Builder {
        private int zza;
        private long zzb;
        private long zzc;
        private long zzd;
        private long zze;
        private int zzf;
        private float zzg;
        private boolean zzh;
        private long zzi;
        private int zzj;
        private int zzk;
        private boolean zzl;
        private WorkSource zzm;
        private zzd zzn;

        public Builder(int r1, long r2) {
            this(r2);
            setPriority(r1);
        }

        public Builder(long r5) {
            this.zza = 102;
            this.zzc = -1;
            this.zzd = 0;
            this.zze = LongCompanionObject.MAX_VALUE;
            this.zzf = Integer.MAX_VALUE;
            this.zzg = 0.0f;
            this.zzh = true;
            this.zzi = -1;
            this.zzj = 0;
            this.zzk = 0;
            this.zzl = false;
            this.zzm = null;
            this.zzn = null;
            setIntervalMillis(r5);
        }

        public Builder(LocationRequest r4) {
            this(r4.getPriority(), r4.getIntervalMillis());
            setMinUpdateIntervalMillis(r4.getMinUpdateIntervalMillis());
            setMaxUpdateDelayMillis(r4.getMaxUpdateDelayMillis());
            setDurationMillis(r4.getDurationMillis());
            setMaxUpdates(r4.getMaxUpdates());
            setMinUpdateDistanceMeters(r4.getMinUpdateDistanceMeters());
            setWaitForAccurateLocation(r4.isWaitForAccurateLocation());
            setMaxUpdateAgeMillis(r4.getMaxUpdateAgeMillis());
            setGranularity(r4.getGranularity());
            int r0 = r4.zza();
            zzao.zza(r0);
            this.zzk = r0;
            this.zzl = r4.zzb();
            this.zzm = r4.zzc();
            zzd r42 = r4.zzd();
            boolean r02 = true;
            if (r42 != null) goto L5;
        L8:
            Preconditions.checkArgument(r02);
            this.zzn = r42;
            return;
        L5:
            if (r42.zza() == false) goto L8;
            r02 = false;
            goto L8
        }

        public LocationRequest build() {
            int r1 = this.zza;
            long r2 = this.zzb;
            long r5 = this.zzc;
            if (r1 == 105) goto L6;
            r5 = Math.min(r5, r2);
        L6:
            long r4 = r5;
            return new LocationRequest(r1, r2, r4, Math.max(this.zzd, this.zzb), LongCompanionObject.MAX_VALUE, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, new WorkSource(this.zzm), this.zzn);
        }

        public Builder setDurationMillis(long r3) {
            if (r3 <= 0) goto L5;
            boolean r0 = true;
        L6:
            Preconditions.checkArgument(r0, "durationMillis must be greater than 0");
            this.zze = r3;
            return this;
        L5:
            r0 = false;
            goto L6
        }

        public Builder setGranularity(int r1) {
            zzp.zza(r1);
            this.zzj = r1;
            return this;
        }

        public Builder setIntervalMillis(long r3) {
            if (r3 < 0) goto L5;
            boolean r0 = true;
        L6:
            Preconditions.checkArgument(r0, "intervalMillis must be greater than or equal to 0");
            this.zzb = r3;
            return this;
        L5:
            r0 = false;
            goto L6
        }

        public Builder setMaxUpdateAgeMillis(long r5) {
            boolean r1 = true;
            if (r5 != (-1)) goto L5;
        L8:
            Preconditions.checkArgument(r1, "maxUpdateAgeMillis must be greater than or equal to 0, or IMPLICIT_MAX_UPDATE_AGE");
            this.zzi = r5;
            return this;
        L5:
            if (r5 >= 0) goto L8;
            r1 = false;
            goto L8
        }

        public Builder setMaxUpdateDelayMillis(long r3) {
            if (r3 < 0) goto L5;
            boolean r0 = true;
        L6:
            Preconditions.checkArgument(r0, "maxUpdateDelayMillis must be greater than or equal to 0");
            this.zzd = r3;
            return this;
        L5:
            r0 = false;
            goto L6
        }

        public Builder setMaxUpdates(int r3) {
            if (r3 <= 0) goto L4;
            boolean r0 = true;
        L5:
            Preconditions.checkArgument(r0, "maxUpdates must be greater than 0");
            this.zzf = r3;
            return this;
        L4:
            r0 = false;
            goto L5
        }

        public Builder setMinUpdateDistanceMeters(float r3) {
            if (r3 < 0.0f) goto L5;
            boolean r0 = true;
        L6:
            Preconditions.checkArgument(r0, "minUpdateDistanceMeters must be greater than or equal to 0");
            this.zzg = r3;
            return this;
        L5:
            r0 = false;
            goto L6
        }

        public Builder setMinUpdateIntervalMillis(long r5) {
            boolean r1 = true;
            if (r5 != (-1)) goto L5;
        L8:
            Preconditions.checkArgument(r1, "minUpdateIntervalMillis must be greater than or equal to 0, or IMPLICIT_MIN_UPDATE_INTERVAL");
            this.zzc = r5;
            return this;
        L5:
            if (r5 >= 0) goto L8;
            r1 = false;
            goto L8
        }

        public Builder setPriority(int r1) {
            zzak.zza(r1);
            this.zza = r1;
            return this;
        }

        public Builder setWaitForAccurateLocation(boolean r1) {
            this.zzh = r1;
            return this;
        }

        public final Builder zza(int r1) {
            zzao.zza(r1);
            this.zzk = r1;
            return this;
        }

        public final Builder zzb(boolean r1) {
            this.zzl = r1;
            return this;
        }

        public final Builder zzc(WorkSource r1) {
            this.zzm = r1;
            return this;
        }
    }

    static {
        CREATOR = new zzae();
    }

    public LocationRequest(int r1, long r2, long r4, long r6, long r8, long r10, int r12, float r13, boolean r14, long r15, int r17, int r18, boolean r19, WorkSource r20, zzd r21) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r4;
        this.zzd = r6;
        if (r8 == LongCompanionObject.MAX_VALUE) goto L6;
        r10 = Math.min(Math.max(1, r8 - SystemClock.elapsedRealtime()), r10);
    L6:
        this.zze = r10;
        this.zzf = r12;
        this.zzg = r13;
        this.zzh = r14;
        this.zzi = r15;
        this.zzj = r17;
        this.zzk = r18;
        this.zzl = r19;
        this.zzm = r20;
        this.zzn = r21;
    }

    @Deprecated
    public static LocationRequest create() {
        return new LocationRequest(102, DurationKt.MILLIS_IN_HOUR, 600000, 0, LongCompanionObject.MAX_VALUE, LongCompanionObject.MAX_VALUE, Integer.MAX_VALUE, 0.0f, true, DurationKt.MILLIS_IN_HOUR, 0, 0, false, new WorkSource(), null);
    }

    private static String zze(long r2) {
        if (r2 != LongCompanionObject.MAX_VALUE) goto L7;
        return "∞";
    L7:
        return zzen.zzb(r2);
    }

    public boolean equals(Object r7) {
        if ((r7 instanceof LocationRequest) == false) goto L37;
        LocationRequest r72 = (LocationRequest) r7;
        boolean r0 = isBatched();
        if (this.zza != r72.zza) goto L37;
        if (isPassive() == true) goto L11;
        if (this.zzb != r72.zzb) goto L37;
    L11:
        if (getMinUpdateIntervalMillis() != r72.getMinUpdateIntervalMillis()) goto L37;
        if (r0 != r72.isBatched()) goto L37;
        if (r0 == false) goto L18;
        if (this.zzd != r72.zzd) goto L37;
    L18:
        if (this.zze != r72.zze) goto L37;
        if (this.zzf != r72.zzf) goto L37;
        if (this.zzg != r72.zzg) goto L37;
        if (this.zzh != r72.zzh) goto L37;
        if (this.zzj != r72.zzj) goto L37;
        if (this.zzk != r72.zzk) goto L37;
        if (this.zzl != r72.zzl) goto L37;
        if (this.zzm.equals(r72.zzm) == false) goto L37;
        if (Objects.equal(this.zzn, r72.zzn) == false) goto L37;
        return true;
    L37:
        return false;
    }

    @Pure
    public long getDurationMillis() {
        return this.zze;
    }

    @Pure
    public int getGranularity() {
        return this.zzj;
    }

    @Pure
    public long getIntervalMillis() {
        if (getPriority() != 105) goto L7;
        return LongCompanionObject.MAX_VALUE;
    L7:
        return this.zzb;
    }

    @Pure
    public long getMaxUpdateAgeMillis() {
        long r0 = this.zzi;
        if (r0 == (-1)) goto L5;
        return r0;
    L5:
        return this.zzb;
    }

    @Pure
    public long getMaxUpdateDelayMillis() {
        return this.zzd;
    }

    @Pure
    public int getMaxUpdates() {
        return this.zzf;
    }

    @Pure
    public float getMinUpdateDistanceMeters() {
        return this.zzg;
    }

    @Pure
    public long getMinUpdateIntervalMillis() {
        long r0 = this.zzc;
        if (r0 == (-1)) goto L5;
        return r0;
    L5:
        return this.zzb / 2;
    }

    @Pure
    public int getPriority() {
        return this.zza;
    }

    public int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(this.zza), Long.valueOf(getIntervalMillis()), Long.valueOf(getMinUpdateIntervalMillis()), this.zzm});
    }

    @Pure
    public boolean isBatched() {
        long r0 = this.zzd;
        if (r0 > 0) goto L5;
        return false;
    L5:
        if ((r0 >> 1) < getIntervalMillis()) goto L9;
        return true;
    L9:
        return false;
    }

    @Pure
    public boolean isPassive() {
        if (this.zza != 105) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isWaitForAccurateLocation() {
        return this.zzh;
    }

    @Deprecated
    public LocationRequest setFastestInterval(long r4) {
        Object[] r0 = {Long.valueOf(r4)};
        if (r4 < 0) goto L5;
        boolean r1 = true;
    L6:
        Preconditions.checkArgument(r1, "illegal fastest interval: %d", r0);
        this.zzc = r4;
        return this;
    L5:
        r1 = false;
        goto L6
    }

    @Deprecated
    public LocationRequest setInterval(long r9) {
        if (r9 < 0) goto L5;
        boolean r0 = true;
    L6:
        Preconditions.checkArgument(r0, "intervalMillis must be greater than or equal to 0");
        long r02 = this.zzc;
        long r2 = this.zzb;
        if (r02 != (r2 / 6)) goto L10;
        this.zzc = r9 / 6;
    L10:
        if (this.zzi != r2) goto L12;
        this.zzi = r9;
    L12:
        this.zzb = r9;
        return this;
    L5:
        r0 = false;
        goto L6
    }

    @Deprecated
    public LocationRequest setPriority(int r1) {
        zzak.zza(r1);
        this.zza = r1;
        return this;
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        r0.append("Request[");
        if (isPassive() == false) goto L7;
        r0.append(zzak.zzb(this.zza));
        if (this.zzd <= 0) goto L13;
        r0.append("/");
        zzen.zzc(this.zzd, r0);
    L13:
        if (isPassive() == false) goto L15;
    L16:
        r0.append(", minUpdateInterval=");
        r0.append(zze(this.zzc));
    L18:
        if (this.zzg <= 0.0d) goto L21;
        r0.append(", minUpdateDistance=");
        r0.append(this.zzg);
    L21:
        if (this.zzi == (-1)) goto L24;
        r0.append(", maxUpdateAge=");
        r0.append(zze(this.zzi));
    L24:
        if (this.zze == LongCompanionObject.MAX_VALUE) goto L27;
        r0.append(", duration=");
        zzen.zzc(this.zze, r0);
    L27:
        if (this.zzf == Integer.MAX_VALUE) goto L29;
        r0.append(", maxUpdates=");
        r0.append(this.zzf);
    L29:
        int r1 = this.zzk;
        if (r1 == 0) goto L32;
        r0.append(", ");
        r0.append(zzao.zzb(r1));
    L32:
        int r12 = this.zzj;
        if (r12 == 0) goto L36;
        r0.append(", ");
        r0.append(zzp.zzb(r12));
    L36:
        if (this.zzh == false) goto L39;
        r0.append(", waitForAccurateLocation");
    L39:
        if (this.zzl == false) goto L41;
        r0.append(", bypass");
    L41:
        WorkSource r13 = this.zzm;
        if (WorkSourceUtil.isEmpty(r13) == true) goto L44;
        r0.append(", ");
        r0.append(r13);
    L44:
        zzd r8 = this.zzn;
        if (r8 == null) goto L47;
        r0.append(", impersonation=");
        r0.append(r8);
    L47:
        r0.append(']');
        return r0.toString();
    L15:
        if (this.zzc == (-1)) goto L18;
    L7:
        r0.append("@");
        if (isBatched() == false) goto L10;
        zzen.zzc(getIntervalMillis(), r0);
        r0.append("/");
        zzen.zzc(this.zzd, r0);
    L11:
        r0.append(" ");
        r0.append(zzak.zzb(this.zza));
        goto L13
    L10:
        zzen.zzc(getIntervalMillis(), r0);
        goto L11
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeInt(r5, 1, getPriority());
        SafeParcelWriter.writeLong(r5, 2, getIntervalMillis());
        SafeParcelWriter.writeLong(r5, 3, getMinUpdateIntervalMillis());
        SafeParcelWriter.writeInt(r5, 6, getMaxUpdates());
        SafeParcelWriter.writeFloat(r5, 7, getMinUpdateDistanceMeters());
        SafeParcelWriter.writeLong(r5, 8, getMaxUpdateDelayMillis());
        SafeParcelWriter.writeBoolean(r5, 9, isWaitForAccurateLocation());
        SafeParcelWriter.writeLong(r5, 10, getDurationMillis());
        SafeParcelWriter.writeLong(r5, 11, getMaxUpdateAgeMillis());
        SafeParcelWriter.writeInt(r5, 12, getGranularity());
        SafeParcelWriter.writeInt(r5, 13, this.zzk);
        SafeParcelWriter.writeBoolean(r5, 15, this.zzl);
        SafeParcelWriter.writeParcelable(r5, 16, this.zzm, r6, false);
        SafeParcelWriter.writeParcelable(r5, 17, this.zzn, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }

    @Pure
    public final int zza() {
        return this.zzk;
    }

    @Pure
    public final boolean zzb() {
        return this.zzl;
    }

    @Pure
    public final WorkSource zzc() {
        return this.zzm;
    }

    @Pure
    public final zzd zzd() {
        return this.zzn;
    }
}
