package com.google.android.gms.internal.cast;

import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.widget.CastSeekBar;

/* loaded from: classes4.dex */
public final class zzdx extends zzdr {
    private final RelativeLayout zza;
    private final TextView zzb;
    private final CastSeekBar zzc;
    private final com.google.android.gms.cast.framework.media.uicontroller.zza zzd;

    public zzdx(RelativeLayout r3, CastSeekBar r4, com.google.android.gms.cast.framework.media.uicontroller.zza r5) {
        this.zza = r3;
        TextView r32 = (TextView) r3.findViewById(R.id.tooltip);
        this.zzb = r32;
        this.zzc = r4;
        this.zzd = r5;
        TypedArray r2 = r32.getContext().obtainStyledAttributes(null, R.styleable.CastExpandedController, R.attr.castExpandedControllerStyle, R.style.CastExpandedController);
        int r42 = r2.getResourceId(R.styleable.CastExpandedController_castSeekBarTooltipBackgroundColor, 0);
        r2.recycle();
        int r22 = r32.getContext().getResources().getColor(r42);
        r32.getBackground().setColorFilter(r22, PorterDuff.Mode.SRC_IN);
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onMediaStatusUpdated() {
        zzd();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionConnected(CastSession r1) {
        super.onSessionConnected(r1);
        zzd();
    }

    @Override // com.google.android.gms.cast.framework.media.uicontroller.UIController
    public final void onSessionEnded() {
        super.onSessionEnded();
        zzd();
    }

    @Override // com.google.android.gms.internal.cast.zzdr
    public final void zza(long r1) {
        zzd();
    }

    @Override // com.google.android.gms.internal.cast.zzdr
    public final void zzb(boolean r1) {
        super.zzb(r1);
        zzd();
    }

    public final void zzd() {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 != null) goto L5;
    L11:
        this.zza.setVisibility(8);
        return;
    L5:
        if (r0.hasMediaSession() == false) goto L11;
        if (zzc() == true) goto L11;
        this.zza.setVisibility(0);
        TextView r02 = this.zzb;
        com.google.android.gms.cast.framework.media.uicontroller.zza r2 = this.zzd;
        CastSeekBar r8 = this.zzc;
        r02.setText(r2.zzm(r8.getProgress() + r2.zzh()));
        int r22 = (r8.getMeasuredWidth() - r8.getPaddingLeft()) - r8.getPaddingRight();
        r02.measure(View.MeasureSpec.makeMeasureSpec(r22, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
        int r3 = r02.getMeasuredWidth();
        int r82 = Math.min(Math.max(0, ((int) ((r8.getProgress() / r8.getMaxProgress()) * r22)) - (r3 / 2)), r22 - r3);
        RelativeLayout.LayoutParams r1 = (RelativeLayout.LayoutParams) r02.getLayoutParams();
        r1.leftMargin = r82;
        r02.setLayoutParams(r1);
    }
}
