package com.google.android.gms.maps;

import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;
import com.android.dx.io.Opcodes;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;

/* loaded from: classes5.dex */
public final class GoogleMapOptions extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Parcelable.Creator<GoogleMapOptions> CREATOR = null;
    private static final Integer zza = null;
    private Boolean zzb;
    private Boolean zzc;
    private int zzd;
    private CameraPosition zze;
    private Boolean zzf;
    private Boolean zzg;
    private Boolean zzh;
    private Boolean zzi;
    private Boolean zzj;
    private Boolean zzk;
    private Boolean zzl;
    private Boolean zzm;
    private Boolean zzn;
    private Float zzo;
    private Float zzp;
    private LatLngBounds zzq;
    private Boolean zzr;
    private Integer zzs;
    private String zzt;
    private int zzu;

    static {
        CREATOR = new zzac();
        zza = Integer.valueOf(Color.argb(255, 236, 233, Opcodes.SHR_INT_LIT8));
    }

    public GoogleMapOptions(byte r2, byte r3, int r4, CameraPosition r5, byte r6, byte r7, byte r8, byte r9, byte r10, byte r11, byte r12, byte r13, byte r14, Float r15, Float r16, LatLngBounds r17, byte r18, Integer r19, String r20, int r21) {
        this.zzd = -1;
        this.zzo = null;
        this.zzp = null;
        this.zzq = null;
        this.zzs = null;
        this.zzt = null;
        this.zzb = com.google.android.gms.maps.internal.zza.zzb(r2);
        this.zzc = com.google.android.gms.maps.internal.zza.zzb(r3);
        this.zzd = r4;
        this.zze = r5;
        this.zzf = com.google.android.gms.maps.internal.zza.zzb(r6);
        this.zzg = com.google.android.gms.maps.internal.zza.zzb(r7);
        this.zzh = com.google.android.gms.maps.internal.zza.zzb(r8);
        this.zzi = com.google.android.gms.maps.internal.zza.zzb(r9);
        this.zzj = com.google.android.gms.maps.internal.zza.zzb(r10);
        this.zzk = com.google.android.gms.maps.internal.zza.zzb(r11);
        this.zzl = com.google.android.gms.maps.internal.zza.zzb(r12);
        this.zzm = com.google.android.gms.maps.internal.zza.zzb(r13);
        this.zzn = com.google.android.gms.maps.internal.zza.zzb(r14);
        this.zzo = r15;
        this.zzp = r16;
        this.zzq = r17;
        this.zzr = com.google.android.gms.maps.internal.zza.zzb(r18);
        this.zzs = r19;
        this.zzt = r20;
        this.zzu = r21;
    }

    public Integer getBackgroundColor() {
        return this.zzs;
    }

    public CameraPosition getCamera() {
        return this.zze;
    }

    public LatLngBounds getLatLngBoundsForCameraTarget() {
        return this.zzq;
    }

    public int getMapColorScheme() {
        return this.zzu;
    }

    public String getMapId() {
        return this.zzt;
    }

    public int getMapType() {
        return this.zzd;
    }

    public Float getMaxZoomPreference() {
        return this.zzp;
    }

    public Float getMinZoomPreference() {
        return this.zzo;
    }

    public String toString() {
        return Objects.toStringHelper(this).add("MapType", Integer.valueOf(this.zzd)).add("LiteMode", this.zzl).add("Camera", this.zze).add("CompassEnabled", this.zzg).add("ZoomControlsEnabled", this.zzf).add("ScrollGesturesEnabled", this.zzh).add("ZoomGesturesEnabled", this.zzi).add("TiltGesturesEnabled", this.zzj).add("RotateGesturesEnabled", this.zzk).add("ScrollGesturesEnabledDuringRotateOrZoom", this.zzr).add("MapToolbarEnabled", this.zzm).add("AmbientEnabled", this.zzn).add("MinZoomPreference", this.zzo).add("MaxZoomPreference", this.zzp).add("BackgroundColor", this.zzs).add("LatLngBoundsForCameraTarget", this.zzq).add("ZOrderOnTop", this.zzb).add("UseViewLifecycleInFragment", this.zzc).add("mapColorScheme", Integer.valueOf(this.zzu)).toString();
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeByte(r5, 2, com.google.android.gms.maps.internal.zza.zza(this.zzb));
        SafeParcelWriter.writeByte(r5, 3, com.google.android.gms.maps.internal.zza.zza(this.zzc));
        SafeParcelWriter.writeInt(r5, 4, getMapType());
        SafeParcelWriter.writeParcelable(r5, 5, getCamera(), r6, false);
        SafeParcelWriter.writeByte(r5, 6, com.google.android.gms.maps.internal.zza.zza(this.zzf));
        SafeParcelWriter.writeByte(r5, 7, com.google.android.gms.maps.internal.zza.zza(this.zzg));
        SafeParcelWriter.writeByte(r5, 8, com.google.android.gms.maps.internal.zza.zza(this.zzh));
        SafeParcelWriter.writeByte(r5, 9, com.google.android.gms.maps.internal.zza.zza(this.zzi));
        SafeParcelWriter.writeByte(r5, 10, com.google.android.gms.maps.internal.zza.zza(this.zzj));
        SafeParcelWriter.writeByte(r5, 11, com.google.android.gms.maps.internal.zza.zza(this.zzk));
        SafeParcelWriter.writeByte(r5, 12, com.google.android.gms.maps.internal.zza.zza(this.zzl));
        SafeParcelWriter.writeByte(r5, 14, com.google.android.gms.maps.internal.zza.zza(this.zzm));
        SafeParcelWriter.writeByte(r5, 15, com.google.android.gms.maps.internal.zza.zza(this.zzn));
        SafeParcelWriter.writeFloatObject(r5, 16, getMinZoomPreference(), false);
        SafeParcelWriter.writeFloatObject(r5, 17, getMaxZoomPreference(), false);
        SafeParcelWriter.writeParcelable(r5, 18, getLatLngBoundsForCameraTarget(), r6, false);
        SafeParcelWriter.writeByte(r5, 19, com.google.android.gms.maps.internal.zza.zza(this.zzr));
        SafeParcelWriter.writeIntegerObject(r5, 20, getBackgroundColor(), false);
        SafeParcelWriter.writeString(r5, 21, getMapId(), false);
        SafeParcelWriter.writeInt(r5, 23, getMapColorScheme());
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
