package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.util.List;

/* loaded from: classes5.dex */
public final class CircleOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<CircleOptions> CREATOR = null;
    private LatLng zza;
    private double zzb;
    private float zzc;
    private int zzd;
    private int zze;
    private float zzf;
    private boolean zzg;
    private boolean zzh;
    private List zzi;

    static {
        CREATOR = new zzc();
    }

    public CircleOptions() {
        this.zza = null;
        this.zzb = 0.0d;
        this.zzc = 10.0f;
        this.zzd = -16777216;
        this.zze = 0;
        this.zzf = 0.0f;
        this.zzg = true;
        this.zzh = false;
        this.zzi = null;
    }

    public CircleOptions(LatLng r1, double r2, float r4, int r5, int r6, float r7, boolean r8, boolean r9, List r10) {
        this.zza = r1;
        this.zzb = r2;
        this.zzc = r4;
        this.zzd = r5;
        this.zze = r6;
        this.zzf = r7;
        this.zzg = r8;
        this.zzh = r9;
        this.zzi = r10;
    }

    public CircleOptions center(LatLng r2) {
        Preconditions.checkNotNull(r2, "center must not be null.");
        this.zza = r2;
        return this;
    }

    public CircleOptions fillColor(int r1) {
        this.zze = r1;
        return this;
    }

    public LatLng getCenter() {
        return this.zza;
    }

    public int getFillColor() {
        return this.zze;
    }

    public double getRadius() {
        return this.zzb;
    }

    public int getStrokeColor() {
        return this.zzd;
    }

    public List<PatternItem> getStrokePattern() {
        return this.zzi;
    }

    public float getStrokeWidth() {
        return this.zzc;
    }

    public float getZIndex() {
        return this.zzf;
    }

    public boolean isClickable() {
        return this.zzh;
    }

    public boolean isVisible() {
        return this.zzg;
    }

    public CircleOptions radius(double r1) {
        this.zzb = r1;
        return this;
    }

    public CircleOptions strokeColor(int r1) {
        this.zzd = r1;
        return this;
    }

    public CircleOptions strokePattern(List<PatternItem> r1) {
        this.zzi = r1;
        return this;
    }

    public CircleOptions strokeWidth(float r1) {
        this.zzc = r1;
        return this;
    }

    @Override // android.os.Parcelable
    public final void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 2, getCenter(), r6, false);
        SafeParcelWriter.writeDouble(r5, 3, getRadius());
        SafeParcelWriter.writeFloat(r5, 4, getStrokeWidth());
        SafeParcelWriter.writeInt(r5, 5, getStrokeColor());
        SafeParcelWriter.writeInt(r5, 6, getFillColor());
        SafeParcelWriter.writeFloat(r5, 7, getZIndex());
        SafeParcelWriter.writeBoolean(r5, 8, isVisible());
        SafeParcelWriter.writeBoolean(r5, 9, isClickable());
        SafeParcelWriter.writeTypedList(r5, 10, getStrokePattern(), false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
    }
}
