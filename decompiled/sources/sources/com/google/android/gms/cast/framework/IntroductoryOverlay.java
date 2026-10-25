package com.google.android.gms.cast.framework;

import android.app.Activity;
import android.view.MenuItem;
import android.view.View;
import androidx.mediarouter.app.MediaRouteButton;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzbk;
import com.google.android.gms.internal.cast.zzpm;

/* loaded from: classes4.dex */
public interface IntroductoryOverlay {

    public static class Builder {
        private final Activity zza;
        private final View zzb;
        private int zzc;
        private String zzd;
        private OnOverlayDismissedListener zze;
        private boolean zzf;

        public Builder(Activity r1, MenuItem r2) {
            this.zza = (Activity) Preconditions.checkNotNull(r1);
            this.zzb = ((MenuItem) Preconditions.checkNotNull(r2)).getActionView();
        }

        public Builder(Activity r1, MediaRouteButton r2) {
            this.zza = (Activity) Preconditions.checkNotNull(r1);
            this.zzb = (View) Preconditions.checkNotNull(r2);
        }

        public IntroductoryOverlay build() {
            com.google.android.gms.internal.cast.zzr.zzb(zzpm.zzs);
            return new zzbk(this);
        }

        public Builder setButtonText(int r2) {
            this.zza.getResources().getString(r2);
            return this;
        }

        public Builder setButtonText(String r1) {
            return this;
        }

        public Builder setFocusRadius(float r1) {
            return this;
        }

        public Builder setFocusRadiusId(int r2) {
            this.zza.getResources().getDimension(r2);
            return this;
        }

        public Builder setOnOverlayDismissedListener(OnOverlayDismissedListener r1) {
            this.zze = r1;
            return this;
        }

        public Builder setOverlayColor(int r2) {
            this.zzc = this.zza.getResources().getColor(r2);
            return this;
        }

        public Builder setSingleTime() {
            this.zzf = true;
            return this;
        }

        public Builder setTitleText(int r2) {
            this.zzd = this.zza.getResources().getString(r2);
            return this;
        }

        public Builder setTitleText(String r1) {
            this.zzd = r1;
            return this;
        }

        public final Activity zza() {
            return this.zza;
        }

        public final View zzb() {
            return this.zzb;
        }

        public final OnOverlayDismissedListener zzc() {
            return this.zze;
        }

        public final int zzd() {
            return this.zzc;
        }

        public final boolean zze() {
            return this.zzf;
        }

        public final String zzf() {
            return this.zzd;
        }
    }

    public interface OnOverlayDismissedListener {
        void onOverlayDismissed();
    }

    void remove();

    void show();
}
