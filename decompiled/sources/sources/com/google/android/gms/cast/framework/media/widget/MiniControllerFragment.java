package com.google.android.gms.cast.framework.media.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzpm;
import com.google.android.gms.internal.cast.zzr;

/* loaded from: classes4.dex */
public class MiniControllerFragment extends Fragment implements ControlButtonsContainer {
    private Logger zza;
    private boolean zzb;
    private int zzc;
    private int zzd;
    private TextView zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int[] zzj;
    private final ImageView[] zzk;
    private int zzl;
    private int zzm;
    private int zzn;
    private int zzo;
    private int zzp;
    private int zzq;
    private int zzr;
    private int zzs;
    private int zzt;
    private int zzu;
    private int zzv;
    private int zzw;
    private int zzx;
    private UIMediaController zzy;

    public MiniControllerFragment() {
        this.zzk = new ImageView[3];
    }

    private final void zza(UIMediaController r9, RelativeLayout r10, int r11, int r12) {
        ImageView r2 = (ImageView) r10.findViewById(r11);
        int r122 = this.zzj[r12];
        if (r122 != R.id.cast_button_type_empty) goto L7;
        r2.setVisibility(4);
        return;
    L7:
        if (r122 != R.id.cast_button_type_custom) goto L10;
        return;
    L10:
        if (r122 != R.id.cast_button_type_play_pause_toggle) goto L21;
        int r123 = this.zzm;
        int r0 = this.zzn;
        int r1 = this.zzo;
        if (this.zzl != 1) goto L14;
        r123 = this.zzp;
        r0 = this.zzq;
        r1 = this.zzr;
    L14:
        Drawable r3 = zzo.zzb(getContext(), this.zzi, r123);
        Drawable r4 = zzo.zzb(getContext(), this.zzi, r0);
        Drawable r5 = zzo.zzb(getContext(), this.zzi, r1);
        r2.setImageDrawable(r4);
        ProgressBar r6 = new ProgressBar(getContext());
        RelativeLayout.LayoutParams r124 = new RelativeLayout.LayoutParams(-2, -2);
        r124.addRule(8, r11);
        r124.addRule(6, r11);
        r124.addRule(5, r11);
        r124.addRule(7, r11);
        r124.addRule(15);
        r6.setLayoutParams(r124);
        r6.setVisibility(8);
        Drawable r112 = r6.getIndeterminateDrawable();
        int r8 = this.zzh;
        if (r8 == 0) goto L18;
        if (r112 == null) goto L18;
        r112.setColorFilter(r8, PorterDuff.Mode.SRC_IN);
    L18:
        r10.addView(r6);
        r9.bindImageViewToPlayPauseToggle(r2, r3, r4, r5, r6, true);
        return;
    L21:
        if (r122 != R.id.cast_button_type_skip_previous) goto L25;
        r2.setImageDrawable(zzo.zzb(getContext(), this.zzi, this.zzs));
        r2.setContentDescription(getResources().getString(R.string.cast_skip_prev));
        r9.bindViewToSkipPrev(r2, 0);
        return;
    L25:
        if (r122 != R.id.cast_button_type_skip_next) goto L29;
        r2.setImageDrawable(zzo.zzb(getContext(), this.zzi, this.zzt));
        r2.setContentDescription(getResources().getString(R.string.cast_skip_next));
        r9.bindViewToSkipNext(r2, 0);
        return;
    L29:
        if (r122 != R.id.cast_button_type_rewind_30_seconds) goto L33;
        r2.setImageDrawable(zzo.zzb(getContext(), this.zzi, this.zzu));
        r2.setContentDescription(getResources().getString(R.string.cast_rewind_30));
        r9.bindViewToRewind(r2, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
        return;
    L33:
        if (r122 != R.id.cast_button_type_forward_30_seconds) goto L37;
        r2.setImageDrawable(zzo.zzb(getContext(), this.zzi, this.zzv));
        r2.setContentDescription(getResources().getString(R.string.cast_forward_30));
        r9.bindViewToForward(r2, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
        return;
    L37:
        if (r122 != R.id.cast_button_type_mute_toggle) goto L41;
        r2.setImageDrawable(zzo.zzb(getContext(), this.zzi, this.zzw));
        r9.bindImageViewToMuteToggle(r2);
        return;
    L41:
        if (r122 != R.id.cast_button_type_closed_caption) goto L44;
        r2.setImageDrawable(zzo.zzb(getContext(), this.zzi, this.zzx));
        r9.bindViewToClosedCaption(r2);
        return;
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final ImageView getButtonImageViewAt(int r1) {
        return this.zzk[r1];
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final int getButtonSlotCount() {
        return 3;
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final int getButtonTypeAt(int r1) {
        return this.zzj[r1];
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public UIMediaController getUIMediaController() {
        return this.zzy;
    }

    @Override // androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater r9, ViewGroup r10, Bundle r11) {
        this.zza = new Logger("MiniControllerFragment");
        UIMediaController r112 = new UIMediaController(getActivity());
        this.zzy = r112;
        View r92 = r9.inflate(R.layout.cast_mini_controller, r10, false);
        r92.setVisibility(8);
        r112.bindViewVisibilityToMediaSession(r92, 8);
        RelativeLayout r0 = (RelativeLayout) r92.findViewById(R.id.container_current);
        int r2 = this.zzf;
        if (r2 == 0) goto L5;
        r0.setBackgroundResource(r2);
    L5:
        ImageView r22 = (ImageView) r92.findViewById(R.id.icon_view);
        TextView r3 = (TextView) r92.findViewById(R.id.title_view);
        if (this.zzc == 0) goto L8;
        r3.setTextAppearance(getActivity(), this.zzc);
    L8:
        TextView r4 = (TextView) r92.findViewById(R.id.subtitle_view);
        this.zze = r4;
        if (this.zzd == 0) goto L11;
        r4.setTextAppearance(getActivity(), this.zzd);
    L11:
        ProgressBar r42 = (ProgressBar) r92.findViewById(R.id.progressBar);
        if (this.zzg == 0) goto L14;
        ((LayerDrawable) r42.getProgressDrawable()).setColorFilter(this.zzg, PorterDuff.Mode.SRC_IN);
    L14:
        r112.bindTextViewToMetadataOfCurrentItem(r3, MediaMetadata.KEY_TITLE);
        r112.bindTextViewToSmartSubtitle(this.zze);
        r112.bindProgressBar(r42);
        r112.bindViewToLaunchExpandedController(r0);
        if (this.zzb == false) goto L17;
        r112.bindImageViewToImageOfCurrentItem(r22, new ImageHints(2, getResources().getDimensionPixelSize(R.dimen.cast_mini_controller_icon_width), getResources().getDimensionPixelSize(R.dimen.cast_mini_controller_icon_height)), R.drawable.cast_album_art_placeholder);
    L18:
        ImageView[] r102 = this.zzk;
        r102[0] = (ImageView) r0.findViewById(R.id.button_0);
        r102[1] = (ImageView) r0.findViewById(R.id.button_1);
        r102[2] = (ImageView) r0.findViewById(R.id.button_2);
        zza(r112, r0, R.id.button_0, 0);
        zza(r112, r0, R.id.button_1, 1);
        zza(r112, r0, R.id.button_2, 2);
        return r92;
    L17:
        r22.setVisibility(8);
        goto L18
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        UIMediaController r0 = this.zzy;
        if (r0 == null) goto L5;
        r0.dispose();
        this.zzy = null;
    L5:
        super.onDestroy();
    }

    @Override // androidx.fragment.app.Fragment
    public void onInflate(Context r5, AttributeSet r6, Bundle r7) {
        super.onInflate(r5, r6, r7);
        if (this.zzj != null) goto L29;
        TypedArray r62 = r5.obtainStyledAttributes(r6, R.styleable.CastMiniController, R.attr.castMiniControllerStyle, R.style.CastMiniController);
        this.zzb = r62.getBoolean(R.styleable.CastMiniController_castShowImageThumbnail, true);
        int r1 = 0;
        this.zzc = r62.getResourceId(R.styleable.CastMiniController_castTitleTextAppearance, 0);
        this.zzd = r62.getResourceId(R.styleable.CastMiniController_castSubtitleTextAppearance, 0);
        this.zzf = r62.getResourceId(R.styleable.CastMiniController_castBackground, 0);
        int r72 = r62.getColor(R.styleable.CastMiniController_castProgressBarColor, 0);
        this.zzg = r72;
        this.zzh = r62.getColor(R.styleable.CastMiniController_castMiniControllerLoadingIndicatorColor, r72);
        this.zzi = r62.getResourceId(R.styleable.CastMiniController_castButtonColor, 0);
        this.zzm = r62.getResourceId(R.styleable.CastMiniController_castPlayButtonDrawable, 0);
        this.zzn = r62.getResourceId(R.styleable.CastMiniController_castPauseButtonDrawable, 0);
        this.zzo = r62.getResourceId(R.styleable.CastMiniController_castStopButtonDrawable, 0);
        this.zzp = r62.getResourceId(R.styleable.CastMiniController_castPlayButtonDrawable, 0);
        this.zzq = r62.getResourceId(R.styleable.CastMiniController_castPauseButtonDrawable, 0);
        this.zzr = r62.getResourceId(R.styleable.CastMiniController_castStopButtonDrawable, 0);
        this.zzs = r62.getResourceId(R.styleable.CastMiniController_castSkipPreviousButtonDrawable, 0);
        this.zzt = r62.getResourceId(R.styleable.CastMiniController_castSkipNextButtonDrawable, 0);
        this.zzu = r62.getResourceId(R.styleable.CastMiniController_castRewind30ButtonDrawable, 0);
        this.zzv = r62.getResourceId(R.styleable.CastMiniController_castForward30ButtonDrawable, 0);
        this.zzw = r62.getResourceId(R.styleable.CastMiniController_castMuteToggleButtonDrawable, 0);
        this.zzx = r62.getResourceId(R.styleable.CastMiniController_castClosedCaptionsButtonDrawable, 0);
        int r73 = r62.getResourceId(R.styleable.CastMiniController_castControlButtons, 0);
        if (r73 == 0) goto L24;
        TypedArray r52 = r5.getResources().obtainTypedArray(r73);
        if (r52.length() != 3) goto L10;
        boolean r74 = true;
    L11:
        Preconditions.checkArgument(r74);
        this.zzj = new int[r52.length()];
        int r75 = 0;
    L13:
        if (r75 >= r52.length()) goto L15;
        this.zzj[r75] = r52.getResourceId(r75, 0);
        r75 = r75 + 1;
        goto L13
    L15:
        r52.recycle();
        if (this.zzb == false) goto L18;
        this.zzj[0] = R.id.cast_button_type_empty;
    L18:
        this.zzl = 0;
        int[] r53 = this.zzj;
        int r76 = r53.length;
    L19:
        if (r1 >= r76) goto L28;
        if (r53[r1] == R.id.cast_button_type_empty) goto L23;
        this.zzl++;
    L23:
        r1 = r1 + 1;
    L28:
        r62.recycle();
        goto L29
    L10:
        r74 = false;
        goto L11
    L24:
        Logger r54 = this.zza;
        if (r54 == null) goto L27;
        r54.w("Unable to read attribute castControlButtons.", new Object[0]);
    L27:
        int r55 = R.id.cast_button_type_empty;
        this.zzj = new int[]{r55, r55, r55};
    L29:
        zzr.zzb(zzpm.zzd);
    }
}
