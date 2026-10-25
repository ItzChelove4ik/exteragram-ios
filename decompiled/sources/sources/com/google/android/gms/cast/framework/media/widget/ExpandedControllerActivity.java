package com.google.android.gms.cast.framework.media.widget;

import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.R$attr;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.cast.AdBreakClipInfo;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaMetadata;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.internal.zzt;
import com.google.android.gms.cast.framework.media.uicontroller.UIMediaController;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzdr;
import com.google.android.gms.internal.cast.zzdu;
import com.google.android.gms.internal.cast.zzdv;
import com.google.android.gms.internal.cast.zzdw;
import com.google.android.gms.internal.cast.zzdx;
import com.google.android.gms.internal.cast.zzpm;
import com.google.android.gms.internal.cast.zzr;
import java.util.Timer;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public abstract class ExpandedControllerActivity extends AppCompatActivity implements ControlButtonsContainer {
    private ImageView zzA;
    private int[] zzB;
    private final ImageView[] zzC;
    private View zzD;
    private View zzE;
    private ImageView zzF;
    private TextView zzG;
    private TextView zzH;
    private TextView zzI;
    private TextView zzJ;
    private UIMediaController zzK;
    private SessionManager zzL;
    private Cast.Listener zzM;
    private boolean zzN;
    private Timer zzO;
    private String zzP;
    final SessionManagerListener zza;
    final RemoteMediaClient.Listener zzb;
    com.google.android.gms.cast.framework.media.internal.zzb zzc;
    boolean zzd;
    private int zze;
    private int zzf;
    private int zzg;
    private int zzh;
    private int zzi;
    private int zzj;
    private int zzk;
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
    private TextView zzw;
    private SeekBar zzx;
    private CastSeekBar zzy;
    private ImageView zzz;

    public ExpandedControllerActivity() {
        this.zza = new zzn(this, null);
        this.zzb = new zzm(this, null);
        this.zzC = new ImageView[4];
    }

    private final void zzj(View r8, int r9, int r10, UIMediaController r11) {
        ImageView r1 = (ImageView) r8.findViewById(r9);
        if (r10 != R.id.cast_button_type_empty) goto L7;
        r1.setVisibility(4);
        return;
    L7:
        if (r10 != R.id.cast_button_type_play_pause_toggle) goto L11;
        r1.setBackgroundResource(this.zze);
        Drawable r3 = zzo.zzc(this, this.zzs, this.zzg);
        Drawable r2 = zzo.zzc(this, this.zzs, this.zzf);
        Drawable r4 = zzo.zzc(this, this.zzs, this.zzh);
        r1.setImageDrawable(r2);
        r11.bindImageViewToPlayPauseToggle(r1, r2, r3, r4, null, false);
        return;
    L11:
        if (r10 != R.id.cast_button_type_skip_previous) goto L15;
        r1.setBackgroundResource(this.zze);
        r1.setImageDrawable(zzo.zzc(this, this.zzs, this.zzi));
        r1.setContentDescription(getResources().getString(R.string.cast_skip_prev));
        r11.bindViewToSkipPrev(r1, 0);
        return;
    L15:
        if (r10 != R.id.cast_button_type_skip_next) goto L19;
        r1.setBackgroundResource(this.zze);
        r1.setImageDrawable(zzo.zzc(this, this.zzs, this.zzj));
        r1.setContentDescription(getResources().getString(R.string.cast_skip_next));
        r11.bindViewToSkipNext(r1, 0);
        return;
    L19:
        if (r10 != R.id.cast_button_type_rewind_30_seconds) goto L23;
        r1.setBackgroundResource(this.zze);
        r1.setImageDrawable(zzo.zzc(this, this.zzs, this.zzk));
        r1.setContentDescription(getResources().getString(R.string.cast_rewind_30));
        r11.bindViewToRewind(r1, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
        return;
    L23:
        if (r10 != R.id.cast_button_type_forward_30_seconds) goto L27;
        r1.setBackgroundResource(this.zze);
        r1.setImageDrawable(zzo.zzc(this, this.zzs, this.zzl));
        r1.setContentDescription(getResources().getString(R.string.cast_forward_30));
        r11.bindViewToForward(r1, NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS);
        return;
    L27:
        if (r10 != R.id.cast_button_type_mute_toggle) goto L31;
        r1.setBackgroundResource(this.zze);
        r1.setImageDrawable(zzo.zzc(this, this.zzs, this.zzm));
        r11.bindImageViewToMuteToggle(r1);
        return;
    L31:
        if (r10 != R.id.cast_button_type_closed_caption) goto L34;
        r1.setBackgroundResource(this.zze);
        r1.setImageDrawable(zzo.zzc(this, this.zzs, this.zzn));
        r11.bindViewToClosedCaption(r1);
        return;
    }

    private final RemoteMediaClient zzk() {
        CastSession r1 = this.zzL.getCurrentCastSession();
        if (r1 != null) goto L5;
        return null;
    L5:
        if (r1.isConnected() == true) goto L7;
        return null;
    L7:
        return r1.getRemoteMediaClient();
    }

    private final void zzl() {
        RemoteMediaClient r0 = zzk();
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.hasMediaSession() == false) goto L17;
        MediaInfo r02 = r0.getMediaInfo();
        if (r02 == null) goto L18;
        MediaMetadata r03 = r02.getMetadata();
        if (r03 == null) goto L19;
        ActionBar r2 = getSupportActionBar();
        if (r2 == null) goto L20;
        r2.setTitle(r03.getString(MediaMetadata.KEY_TITLE));
        String r04 = zzt.zza(r03);
        if (r04 == null) goto L21;
        r2.setSubtitle(r04);
        return;
    L21:
        return;
    L20:
        return;
    L19:
        return;
    L18:
        return;
    }

    private final void zzm() {
        CastSession r0 = this.zzL.getCurrentCastSession();
        if (r0 == null) goto L10;
        CastDevice r02 = r0.getCastDevice();
        if (r02 == null) goto L10;
        String r03 = r02.getFriendlyName();
        if (TextUtils.isEmpty(r03) == true) goto L10;
        this.zzw.setText(getResources().getString(R.string.cast_casting_to_device, new Object[]{r03}));
        return;
    L10:
        this.zzw.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    }

    private final void zzn() {
        RemoteMediaClient r0 = zzk();
        if (r0 == null) goto L39;
        MediaStatus r1 = r0.getMediaStatus();
        if (r1 == null) goto L38;
        String r3 = null;
        if (r1.isPlayingAd() == true) goto L10;
        this.zzJ.setVisibility(8);
        this.zzI.setVisibility(8);
        this.zzD.setVisibility(8);
        this.zzA.setVisibility(8);
        this.zzA.setImageBitmap(null);
        return;
    L10:
        if (this.zzA.getVisibility() != 8) goto L20;
        Drawable r2 = this.zzz.getDrawable();
        if (r2 == null) goto L20;
        if ((r2 instanceof BitmapDrawable) == false) goto L20;
        Bitmap r22 = ((BitmapDrawable) r2).getBitmap();
        if (r22 == null) goto L20;
        Bitmap r23 = zzo.zza(this, r22, 0.25f, 7.5f);
        if (r23 == null) goto L20;
        this.zzA.setImageBitmap(r23);
        this.zzA.setVisibility(0);
    L20:
        AdBreakClipInfo r12 = r1.getCurrentAdBreakClip();
        if (r12 == null) goto L23;
        String r32 = r12.getTitle();
        r3 = r12.getImageUrl();
        String r13 = r32;
    L25:
        if (TextUtils.isEmpty(r3) == true) goto L28;
        zzp(r3);
    L31:
        TextView r24 = this.zzH;
        if (TextUtils.isEmpty(r13) == false) goto L34;
        r13 = getResources().getString(R.string.cast_ad_label);
    L34:
        r24.setText(r13);
        this.zzH.setTextAppearance(this.zzt);
        this.zzD.setVisibility(0);
        zzo(r0);
        return;
    L28:
        if (TextUtils.isEmpty(this.zzP) == true) goto L30;
        zzp(this.zzP);
        goto L31
    L30:
        this.zzG.setVisibility(0);
        this.zzE.setVisibility(0);
        this.zzF.setVisibility(8);
        goto L31
    L23:
        r13 = null;
        goto L25
    L38:
        return;
    }

    private final void zzo(RemoteMediaClient r10) {
        if (this.zzd == true) goto L25;
        MediaStatus r0 = r10.getMediaStatus();
        if (r0 != null) goto L7;
        return;
    L7:
        if (r10.isBuffering() == true) goto L29;
        this.zzI.setVisibility(8);
        this.zzJ.setVisibility(8);
        AdBreakClipInfo r02 = r0.getCurrentAdBreakClip();
        if (r02 != null) goto L12;
        return;
    L12:
        if (r02.getWhenSkippableInMs() != (-1)) goto L14;
        return;
    L14:
        if (this.zzN == true) goto L17;
        zzj r4 = new zzj(this, r10);
        Timer r3 = new Timer();
        this.zzO = r3;
        r3.scheduleAtFixedRate(r4, 0, 500);
        this.zzN = true;
    L17:
        if ((r02.getWhenSkippableInMs() - r10.getApproximateAdBreakClipPositionMs()) <= 0.0f) goto L19;
        this.zzJ.setVisibility(0);
        this.zzJ.setText(getResources().getString(R.string.cast_expanded_controller_skip_ad_text, new Object[]{Integer.valueOf((int) Math.ceil(r10 / 1000.0f))}));
        this.zzI.setClickable(false);
        return;
    L19:
        if (this.zzN == false) goto L21;
        this.zzO.cancel();
        this.zzN = false;
    L21:
        this.zzI.setVisibility(0);
        this.zzI.setClickable(true);
        return;
    L29:
        return;
    }

    private final void zzp(String r2) {
        Uri r22 = Uri.parse(r2);
        this.zzc.zzb(r22);
        this.zzE.setVisibility(8);
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final ImageView getButtonImageViewAt(int r1) {
        return this.zzC[r1];
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final int getButtonSlotCount() {
        return 4;
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public final int getButtonTypeAt(int r1) {
        return this.zzB[r1];
    }

    @Deprecated
    public SeekBar getSeekBar() {
        return this.zzx;
    }

    public TextView getStatusTextView() {
        return this.zzw;
    }

    @Override // com.google.android.gms.cast.framework.media.widget.ControlButtonsContainer
    public UIMediaController getUIMediaController() {
        return this.zzK;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle r11) {
        super.onCreate(r11);
        SessionManager r112 = CastContext.getSharedInstance(this).getSessionManager();
        this.zzL = r112;
        if (r112.getCurrentCastSession() != null) goto L5;
        finish();
    L5:
        UIMediaController r113 = new UIMediaController(this);
        this.zzK = r113;
        r113.setPostRemoteMediaClientListener(this.zzb);
        setContentView(R.layout.cast_expanded_controller_activity);
        TypedArray r114 = obtainStyledAttributes(new int[]{R$attr.selectableItemBackgroundBorderless});
        this.zze = r114.getResourceId(0, 0);
        r114.recycle();
        TypedArray r115 = obtainStyledAttributes(null, R.styleable.CastExpandedController, R.attr.castExpandedControllerStyle, R.style.CastExpandedController);
        this.zzs = r115.getResourceId(R.styleable.CastExpandedController_castButtonColor, 0);
        this.zzf = r115.getResourceId(R.styleable.CastExpandedController_castPlayButtonDrawable, 0);
        this.zzg = r115.getResourceId(R.styleable.CastExpandedController_castPauseButtonDrawable, 0);
        this.zzh = r115.getResourceId(R.styleable.CastExpandedController_castStopButtonDrawable, 0);
        this.zzi = r115.getResourceId(R.styleable.CastExpandedController_castSkipPreviousButtonDrawable, 0);
        this.zzj = r115.getResourceId(R.styleable.CastExpandedController_castSkipNextButtonDrawable, 0);
        this.zzk = r115.getResourceId(R.styleable.CastExpandedController_castRewind30ButtonDrawable, 0);
        this.zzl = r115.getResourceId(R.styleable.CastExpandedController_castForward30ButtonDrawable, 0);
        this.zzm = r115.getResourceId(R.styleable.CastExpandedController_castMuteToggleButtonDrawable, 0);
        this.zzn = r115.getResourceId(R.styleable.CastExpandedController_castClosedCaptionsButtonDrawable, 0);
        int r1 = r115.getResourceId(R.styleable.CastExpandedController_castControlButtons, 0);
        if (r1 == 0) goto L16;
        TypedArray r12 = getResources().obtainTypedArray(r1);
        if (r12.length() != 4) goto L10;
        boolean r5 = true;
    L11:
        Preconditions.checkArgument(r5);
        this.zzB = new int[r12.length()];
        int r52 = 0;
    L13:
        if (r52 >= r12.length()) goto L15;
        this.zzB[r52] = r12.getResourceId(r52, 0);
        r52 = r52 + 1;
        goto L13
    L15:
        r12.recycle();
    L17:
        this.zzr = r115.getColor(R.styleable.CastExpandedController_castExpandedControllerLoadingIndicatorColor, 0);
        this.zzo = getResources().getColor(r115.getResourceId(R.styleable.CastExpandedController_castAdLabelColor, 0));
        this.zzp = getResources().getColor(r115.getResourceId(R.styleable.CastExpandedController_castAdInProgressTextColor, 0));
        this.zzq = getResources().getColor(r115.getResourceId(R.styleable.CastExpandedController_castAdLabelTextColor, 0));
        this.zzt = r115.getResourceId(R.styleable.CastExpandedController_castAdLabelTextAppearance, 0);
        this.zzu = r115.getResourceId(R.styleable.CastExpandedController_castAdInProgressLabelTextAppearance, 0);
        this.zzv = r115.getResourceId(R.styleable.CastExpandedController_castAdInProgressText, 0);
        int r13 = r115.getResourceId(R.styleable.CastExpandedController_castDefaultAdPosterUrl, 0);
        if (r13 == 0) goto L20;
        this.zzP = getApplicationContext().getResources().getString(r13);
    L20:
        r115.recycle();
        View r116 = findViewById(R.id.expanded_controller_layout);
        UIMediaController r14 = this.zzK;
        this.zzz = (ImageView) r116.findViewById(R.id.background_image_view);
        this.zzA = (ImageView) r116.findViewById(R.id.blurred_background_image_view);
        View r53 = r116.findViewById(R.id.background_place_holder_image_view);
        DisplayMetrics r6 = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(r6);
        r14.zza(this.zzz, new ImageHints(4, r6.widthPixels, r6.heightPixels), r53, new zzl(this, null));
        this.zzw = (TextView) r116.findViewById(R.id.status_text);
        ProgressBar r2 = (ProgressBar) r116.findViewById(R.id.loading_indicator);
        Drawable r3 = r2.getIndeterminateDrawable();
        int r54 = this.zzr;
        if (r54 == 0) goto L23;
        r3.setColorFilter(r54, PorterDuff.Mode.SRC_IN);
    L23:
        r14.bindViewToLoadingIndicator(r2);
        TextView r22 = (TextView) r116.findViewById(R.id.start_text);
        TextView r32 = (TextView) r116.findViewById(R.id.end_text);
        this.zzx = (SeekBar) r116.findViewById(R.id.seek_bar);
        CastSeekBar r55 = (CastSeekBar) r116.findViewById(R.id.cast_seek_bar);
        this.zzy = r55;
        r14.bindSeekBar(r55, 1000);
        r14.bindViewToUIController(r22, new zzdw(r22, r14.zzf()));
        r14.bindViewToUIController(r32, new zzdu(r32, r14.zzf()));
        View r23 = r116.findViewById(R.id.live_indicators);
        r14.bindViewToUIController(r23, new zzdv(r23, r14.zzf()));
        RelativeLayout r24 = (RelativeLayout) r116.findViewById(R.id.tooltip_container);
        zzdr r33 = new zzdx(r24, this.zzy, r14.zzf());
        r14.bindViewToUIController(r24, r33);
        r14.zzb(r33);
        ImageView[] r25 = this.zzC;
        r25[0] = (ImageView) r116.findViewById(R.id.button_0);
        r25[1] = (ImageView) r116.findViewById(R.id.button_1);
        r25[2] = (ImageView) r116.findViewById(R.id.button_2);
        r25[3] = (ImageView) r116.findViewById(R.id.button_3);
        zzj(r116, R.id.button_0, this.zzB[0], r14);
        zzj(r116, R.id.button_1, this.zzB[1], r14);
        zzj(r116, R.id.button_play_pause_toggle, R.id.cast_button_type_play_pause_toggle, r14);
        zzj(r116, R.id.button_2, this.zzB[2], r14);
        zzj(r116, R.id.button_3, this.zzB[3], r14);
        View r117 = findViewById(R.id.ad_container);
        this.zzD = r117;
        this.zzF = (ImageView) r117.findViewById(R.id.ad_image_view);
        this.zzE = this.zzD.findViewById(R.id.ad_background_image_view);
        TextView r118 = (TextView) this.zzD.findViewById(R.id.ad_label);
        this.zzH = r118;
        r118.setTextColor(this.zzq);
        this.zzH.setBackgroundColor(this.zzo);
        this.zzG = (TextView) this.zzD.findViewById(R.id.ad_in_progress_label);
        this.zzJ = (TextView) findViewById(R.id.ad_skip_text);
        TextView r119 = (TextView) findViewById(R.id.ad_skip_button);
        this.zzI = r119;
        r119.setOnClickListener(new zzh(this));
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        ActionBar r1110 = getSupportActionBar();
        if (r1110 == null) goto L26;
        r1110.setDisplayHomeAsUpEnabled(true);
        r1110.setHomeAsUpIndicator(R.drawable.quantum_ic_keyboard_arrow_down_white_36);
    L26:
        zzm();
        zzl();
        TextView r1111 = this.zzG;
        if (r1111 != null) goto L29;
    L31:
        com.google.android.gms.cast.framework.media.internal.zzb r1112 = new com.google.android.gms.cast.framework.media.internal.zzb(getApplicationContext(), new ImageHints(-1, this.zzF.getWidth(), this.zzF.getHeight()));
        this.zzc = r1112;
        r1112.zza(new zzg(this));
        zzr.zzb(zzpm.zzc);
        return;
    L29:
        if (this.zzv == 0) goto L31;
        r1111.setTextAppearance(this.zzu);
        this.zzG.setTextColor(this.zzp);
        this.zzG.setText(this.zzv);
        goto L31
    L10:
        r5 = false;
        goto L11
    L16:
        int r15 = R.id.cast_button_type_empty;
        this.zzB = new int[]{r15, r15, r15, r15};
        goto L17
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        this.zzc.zzc();
        UIMediaController r0 = this.zzK;
        if (r0 == null) goto L5;
        r0.setPostRemoteMediaClientListener(null);
        this.zzK.dispose();
    L5:
        super.onDestroy();
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem r2) {
        if (r2.getItemId() != 16908332) goto L7;
        finish();
        return true;
    L7:
        return true;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        SessionManager r0 = this.zzL;
        if (r0 != null) goto L5;
        return;
    L5:
        CastSession r02 = r0.getCurrentCastSession();
        Cast.Listener r1 = this.zzM;
        if (r1 == null) goto L9;
        if (r02 == null) goto L9;
        r02.removeCastListener(r1);
        this.zzM = null;
    L9:
        this.zzL.removeSessionManagerListener(this.zza, CastSession.class);
        super.onPause();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        SessionManager r0 = this.zzL;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.addSessionManagerListener(this.zza, CastSession.class);
        CastSession r02 = this.zzL.getCurrentCastSession();
        if (r02 != null) goto L8;
    L13:
        finish();
    L14:
        RemoteMediaClient r03 = zzk();
        boolean r1 = true;
        if (r03 != null) goto L17;
    L20:
        this.zzd = r1;
        zzm();
        zzn();
        super.onResume();
        return;
    L17:
        if (r03.hasMediaSession() == false) goto L20;
        r1 = false;
        goto L20
    L8:
        if (r02.isConnected() == false) goto L10;
    L12:
        zzk r12 = new zzk(this);
        this.zzM = r12;
        r02.addCastListener(r12);
        goto L14
    L10:
        if (r02.isConnecting() == true) goto L12;
        goto L12
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean r2) {
        super.onWindowFocusChanged(r2);
        if (r2 == false) goto L6;
        int r22 = getWindow().getDecorView().getSystemUiVisibility() ^ 4102;
        getWindow().getDecorView().setSystemUiVisibility(r22);
        setImmersive(true);
        return;
    }

    public final /* synthetic */ RemoteMediaClient zza() {
        return zzk();
    }

    public final /* synthetic */ void zzb() {
        zzl();
    }

    public final /* synthetic */ void zzc() {
        zzm();
    }

    public final /* synthetic */ void zzd() {
        zzn();
    }

    public final /* synthetic */ void zze(RemoteMediaClient r1) {
        zzo(r1);
    }

    public final /* synthetic */ TextView zzf() {
        return this.zzw;
    }

    public final /* synthetic */ ImageView zzg() {
        return this.zzF;
    }

    public final /* synthetic */ TextView zzh() {
        return this.zzG;
    }

    public final /* synthetic */ TextView zzi() {
        return this.zzI;
    }
}
