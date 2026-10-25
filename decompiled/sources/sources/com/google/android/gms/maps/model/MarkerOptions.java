package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.ObjectWrapper;

/* loaded from: classes5.dex */
public class MarkerOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<MarkerOptions> CREATOR = null;
    private LatLng zza;
    private String zzb;
    private String zzc;
    private BitmapDescriptor zzd;
    private float zze;
    private float zzf;
    private boolean zzg;
    private boolean zzh;
    private boolean zzi;
    private float zzj;
    private float zzk;
    private float zzl;
    private float zzm;
    private float zzn;
    private int zzo;
    private View zzp;
    private int zzq;
    private String zzr;

    static {
        CREATOR = new zzp();
    }

    public MarkerOptions() {
        this.zze = 0.5f;
        this.zzf = 1.0f;
        this.zzh = true;
        this.zzi = false;
        this.zzj = 0.0f;
        this.zzk = 0.5f;
        this.zzl = 0.0f;
        this.zzm = 1.0f;
        this.zzo = 0;
    }

    public MarkerOptions(LatLng r5, String r6, String r7, IBinder r8, float r9, float r10, boolean r11, boolean r12, boolean r13, float r14, float r15, float r16, float r17, float r18, int r19, IBinder r20, int r21, String r22) {
        this.zze = 0.5f;
        this.zzf = 1.0f;
        this.zzh = true;
        this.zzi = false;
        this.zzj = 0.0f;
        this.zzk = 0.5f;
        this.zzl = 0.0f;
        this.zzm = 1.0f;
        this.zzo = 0;
        this.zza = r5;
        this.zzb = r6;
        this.zzc = r7;
        View r52 = null;
        if (r8 != null) goto L5;
        this.zzd = null;
    L6:
        this.zze = r9;
        this.zzf = r10;
        this.zzg = r11;
        this.zzh = r12;
        this.zzi = r13;
        this.zzj = r14;
        this.zzk = r15;
        this.zzl = r16;
        this.zzm = r17;
        this.zzn = r18;
        this.zzq = r21;
        this.zzo = r19;
        IObjectWrapper r62 = IObjectWrapper.Stub.asInterface(r20);
        if (r62 == null) goto L10;
        r52 = (View) ObjectWrapper.unwrap(r62);
    L10:
        this.zzp = r52;
        this.zzr = r22;
        return;
    L5:
        this.zzd = new BitmapDescriptor(IObjectWrapper.Stub.asInterface(r8));
        goto L6
    }

    public MarkerOptions anchor(float r1, float r2) {
        this.zze = r1;
        this.zzf = r2;
        return this;
    }

    public MarkerOptions flat(boolean r1) {
        this.zzi = r1;
        return this;
    }

    public float getAlpha() {
        return this.zzm;
    }

    public float getAnchorU() {
        return this.zze;
    }

    public float getAnchorV() {
        return this.zzf;
    }

    public float getInfoWindowAnchorU() {
        return this.zzk;
    }

    public float getInfoWindowAnchorV() {
        return this.zzl;
    }

    public LatLng getPosition() {
        return this.zza;
    }

    public float getRotation() {
        return this.zzj;
    }

    public String getSnippet() {
        return this.zzc;
    }

    public String getTitle() {
        return this.zzb;
    }

    public float getZIndex() {
        return this.zzn;
    }

    public MarkerOptions icon(BitmapDescriptor r1) {
        this.zzd = r1;
        return this;
    }

    public boolean isDraggable() {
        return this.zzg;
    }

    public boolean isFlat() {
        return this.zzi;
    }

    public boolean isVisible() {
        return this.zzh;
    }

    public MarkerOptions position(LatLng r1) {
        if (r1 == null) goto L5;
        this.zza = r1;
        return this;
    L5:
        f$$ExternalSyntheticBUOutline1.m("latlng cannot be null - a position is required.");
        return null;
    }

    public MarkerOptions snippet(String r1) {
        this.zzc = r1;
        return this;
    }

    public MarkerOptions title(String r1) {
        this.zzb = r1;
        return this;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        int r0 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeParcelable(r5, 2, getPosition(), r6, false);
        SafeParcelWriter.writeString(r5, 3, getTitle(), false);
        SafeParcelWriter.writeString(r5, 4, getSnippet(), false);
        BitmapDescriptor r62 = this.zzd;
        if (r62 != null) goto L5;
        IBinder r63 = null;
    L6:
        SafeParcelWriter.writeIBinder(r5, 5, r63, false);
        SafeParcelWriter.writeFloat(r5, 6, getAnchorU());
        SafeParcelWriter.writeFloat(r5, 7, getAnchorV());
        SafeParcelWriter.writeBoolean(r5, 8, isDraggable());
        SafeParcelWriter.writeBoolean(r5, 9, isVisible());
        SafeParcelWriter.writeBoolean(r5, 10, isFlat());
        SafeParcelWriter.writeFloat(r5, 11, getRotation());
        SafeParcelWriter.writeFloat(r5, 12, getInfoWindowAnchorU());
        SafeParcelWriter.writeFloat(r5, 13, getInfoWindowAnchorV());
        SafeParcelWriter.writeFloat(r5, 14, getAlpha());
        SafeParcelWriter.writeFloat(r5, 15, getZIndex());
        SafeParcelWriter.writeInt(r5, 17, this.zzo);
        SafeParcelWriter.writeIBinder(r5, 18, ObjectWrapper.wrap(this.zzp).asBinder(), false);
        SafeParcelWriter.writeInt(r5, 19, this.zzq);
        SafeParcelWriter.writeString(r5, 20, this.zzr, false);
        SafeParcelWriter.finishObjectHeader(r5, r0);
        return;
    L5:
        r63 = r62.zza().asBinder();
        goto L6
    }

    public final int zzb() {
        return this.zzq;
    }
}
