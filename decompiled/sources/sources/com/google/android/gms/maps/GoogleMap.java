package com.google.android.gms.maps;

import android.location.Location;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.maps.zzaj;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.model.AdvancedMarker;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.HashMap;
import java.util.Map;

/* loaded from: classes5.dex */
public class GoogleMap {
    private final IGoogleMapDelegate zza;
    private final Map zzc;
    private final Map zzd;
    private UiSettings zze;

    public interface CancelableCallback {
        void onCancel();

        void onFinish();
    }

    public interface OnCameraIdleListener {
        void onCameraIdle();
    }

    public interface OnCameraMoveListener {
        void onCameraMove();
    }

    public interface OnCameraMoveStartedListener {
        void onCameraMoveStarted(int r1);
    }

    public interface OnMapLoadedCallback {
        void onMapLoaded();
    }

    public interface OnMarkerClickListener {
        boolean onMarkerClick(Marker r1);
    }

    @Deprecated
    public interface OnMyLocationChangeListener {
        void onMyLocationChange(Location r1);
    }

    public GoogleMap(IGoogleMapDelegate r2) {
        this.zzc = new HashMap();
        this.zzd = new HashMap();
        this.zza = (IGoogleMapDelegate) Preconditions.checkNotNull(r2);
    }

    public final Circle addCircle(CircleOptions r2) {
        Preconditions.checkNotNull(r2, "CircleOptions must not be null.");     // Catch: RemoteException -> L4
        return new Circle(this.zza.addCircle(r2));
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public final Marker addMarker(MarkerOptions r3) {
        Preconditions.checkNotNull(r3, "MarkerOptions must not be null.");     // Catch: RemoteException -> L9
        zzaj r2 = this.zza.addMarker(r3);     // Catch: RemoteException -> L9
        if (r2 != null) goto L6;
        return null;
    L6:
        if (r3.zzb() != 1) goto L12;
        return new AdvancedMarker(r2);
    L12:
        return new Marker(r2);
    L9:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public final void animateCamera(CameraUpdate r2) {
        Preconditions.checkNotNull(r2, "CameraUpdate must not be null.");     // Catch: RemoteException -> L4
        this.zza.animateCamera(r2.zza());     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public final void animateCamera(CameraUpdate r2, int r3, CancelableCallback r4) {
        Preconditions.checkNotNull(r2, "CameraUpdate must not be null.");     // Catch: RemoteException -> L8
        IGoogleMapDelegate r1 = this.zza;     // Catch: RemoteException -> L8
        IObjectWrapper r22 = r2.zza();     // Catch: RemoteException -> L8
        if (r4 != null) goto L5;
        zzab r42 = null;
    L6:
        r1.animateCameraWithDurationAndCallback(r22, r3, r42);     // Catch: RemoteException -> L8
        return;
    L5:
        r42 = new zzab(r4);     // Catch: RemoteException -> L8
    L8:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public final void animateCamera(CameraUpdate r2, CancelableCallback r3) {
        Preconditions.checkNotNull(r2, "CameraUpdate must not be null.");     // Catch: RemoteException -> L8
        IGoogleMapDelegate r1 = this.zza;     // Catch: RemoteException -> L8
        IObjectWrapper r22 = r2.zza();     // Catch: RemoteException -> L8
        if (r3 != null) goto L5;
        zzab r32 = null;
    L6:
        r1.animateCameraWithCallback(r22, r32);     // Catch: RemoteException -> L8
        return;
    L5:
        r32 = new zzab(r3);     // Catch: RemoteException -> L8
    L8:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public final CameraPosition getCameraPosition() {
        return this.zza.getCameraPosition();
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public final float getMaxZoomLevel() {
        return this.zza.getMaxZoomLevel();
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return 0.0f;
    }

    public final float getMinZoomLevel() {
        return this.zza.getMinZoomLevel();
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return 0.0f;
    }

    public final Projection getProjection() {
        return new Projection(this.zza.getProjection());
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public final UiSettings getUiSettings() {
    L7:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return null;
    L3:
        if (this.zze != null) goto L5;
        this.zze = new UiSettings(this.zza.getUiSettings());     // Catch: RemoteException -> L7
    L5:
        return this.zze;
    }

    public final void moveCamera(CameraUpdate r2) {
        Preconditions.checkNotNull(r2, "CameraUpdate must not be null.");     // Catch: RemoteException -> L4
        this.zza.moveCamera(r2.zza());     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public boolean setMapStyle(MapStyleOptions r1) {
        return this.zza.setMapStyle(r1);
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
        return false;
    }

    public final void setMapType(int r1) {
        this.zza.setMapType(r1);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public final void setMyLocationEnabled(boolean r1) {
        this.zza.setMyLocationEnabled(r1);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public final void setOnCameraIdleListener(OnCameraIdleListener r3) {
        IGoogleMapDelegate r0 = this.zza;
        if (r3 == null) goto L5;
        r0.setOnCameraIdleListener(new zzy(this, r3));     // Catch: RemoteException -> L9
        return;
    L5:
        r0.setOnCameraIdleListener(null);     // Catch: RemoteException -> L9
        return;
    L9:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public final void setOnCameraMoveListener(OnCameraMoveListener r3) {
        IGoogleMapDelegate r0 = this.zza;
        if (r3 == null) goto L5;
        r0.setOnCameraMoveListener(new zzw(this, r3));     // Catch: RemoteException -> L9
        return;
    L5:
        r0.setOnCameraMoveListener(null);     // Catch: RemoteException -> L9
        return;
    L9:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public final void setOnCameraMoveStartedListener(OnCameraMoveStartedListener r3) {
        IGoogleMapDelegate r0 = this.zza;
        if (r3 == null) goto L5;
        r0.setOnCameraMoveStartedListener(new zzv(this, r3));     // Catch: RemoteException -> L9
        return;
    L5:
        r0.setOnCameraMoveStartedListener(null);     // Catch: RemoteException -> L9
        return;
    L9:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public void setOnMapLoadedCallback(OnMapLoadedCallback r3) {
        IGoogleMapDelegate r0 = this.zza;
        if (r3 == null) goto L5;
        r0.setOnMapLoadedCallback(new zzj(this, r3));     // Catch: RemoteException -> L9
        return;
    L5:
        r0.setOnMapLoadedCallback(null);     // Catch: RemoteException -> L9
        return;
    L9:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public final void setOnMarkerClickListener(OnMarkerClickListener r3) {
        IGoogleMapDelegate r0 = this.zza;
        if (r3 == null) goto L5;
        r0.setOnMarkerClickListener(new zza(this, r3));     // Catch: RemoteException -> L9
        return;
    L5:
        r0.setOnMarkerClickListener(null);     // Catch: RemoteException -> L9
        return;
    L9:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    @Deprecated
    public final void setOnMyLocationChangeListener(OnMyLocationChangeListener r3) {
        IGoogleMapDelegate r0 = this.zza;
        if (r3 == null) goto L5;
        r0.setOnMyLocationChangeListener(new zzg(this, r3));     // Catch: RemoteException -> L9
        return;
    L5:
        r0.setOnMyLocationChangeListener(null);     // Catch: RemoteException -> L9
        return;
    L9:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }

    public final void setPadding(int r1, int r2, int r3, int r4) {
        this.zza.setPadding(r1, r2, r3, r4);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        zzah$$ExternalSyntheticBUOutline0.m(e);
    }
}
