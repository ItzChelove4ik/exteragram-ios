package com.google.android.gms.internal.cast;

import android.app.Activity;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.RelativeLayout;
import com.google.android.gms.cast.framework.IntroductoryOverlay;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.internal.featurehighlight.HelpTextView;

/* loaded from: classes4.dex */
public final class zzbk extends RelativeLayout implements IntroductoryOverlay {
    private final boolean zza;
    private Activity zzb;
    private IntroductoryOverlay.OnOverlayDismissedListener zzc;
    private View zzd;
    private String zze;
    private boolean zzf;
    private int zzg;

    public zzbk(IntroductoryOverlay.Builder r2) {
        super(r2.zza());
        this.zzb = r2.zza();
        this.zza = r2.zze();
        this.zzc = r2.zzc();
        this.zzd = r2.zzb();
        this.zze = r2.zzf();
        this.zzg = r2.zzd();
    }

    private final void zzd() {
        removeAllViews();
        this.zzb = null;
        this.zzc = null;
        this.zzd = null;
        this.zze = null;
        this.zzg = 0;
        this.zzf = false;
    }

    @Override // com.google.android.gms.cast.framework.IntroductoryOverlay
    public final void remove() {
        if (this.zzf == false) goto L8;
        Activity r0 = this.zzb;
        if (r0 == null) goto L9;
        ((ViewGroup) r0.getWindow().getDecorView()).removeView(this);
        zzd();
        return;
    L9:
        return;
    }

    @Override // com.google.android.gms.cast.framework.IntroductoryOverlay
    public final void show() {
        Activity r0 = this.zzb;
        if (r0 == null) goto L26;
        View r1 = this.zzd;
        if (r1 != null) goto L7;
        return;
    L7:
        if (this.zzf == true) goto L29;
        AccessibilityManager r2 = (AccessibilityManager) r0.getSystemService("accessibility");
        if (r2 == null) goto L16;
        if (r2.isEnabled() == false) goto L16;
        if (r2.isTouchExplorationEnabled() == false) goto L16;
        return;
    L16:
        if (this.zza == true) goto L18;
    L21:
        com.google.android.gms.cast.framework.internal.featurehighlight.zzh r22 = new com.google.android.gms.cast.framework.internal.featurehighlight.zzh(r0);
        int r4 = this.zzg;
        if (r4 == 0) goto L24;
        r22.zzf(r4);
    L24:
        addView(r22);
        HelpTextView r3 = (HelpTextView) r0.getLayoutInflater().inflate(R.layout.cast_help_text, r22, false);
        r3.setText(this.zze, null);
        r22.zzn(r3);
        r22.zza(r1, null, true, new zzbj(this, r0, r22));
        this.zzf = true;
        ((ViewGroup) r0.getWindow().getDecorView()).addView(this);
        r22.zzb(null);
        return;
    L18:
        if (PreferenceManager.getDefaultSharedPreferences(r0).getBoolean("googlecast-introOverlayShown", false) == false) goto L21;
        zzd();
        return;
    L29:
        return;
    }

    public final /* synthetic */ void zza() {
        zzd();
    }

    public final /* synthetic */ IntroductoryOverlay.OnOverlayDismissedListener zzb() {
        return this.zzc;
    }

    public final /* synthetic */ boolean zzc() {
        return this.zzf;
    }
}
