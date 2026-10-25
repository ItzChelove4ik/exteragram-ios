package com.google.android.gms.cast.framework.media.uicontroller;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.gms.cast.MediaSeekOptions;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.cast.framework.Session;
import com.google.android.gms.cast.framework.SessionManager;
import com.google.android.gms.cast.framework.SessionManagerListener;
import com.google.android.gms.cast.framework.media.CastMediaOptions;
import com.google.android.gms.cast.framework.media.ImageHints;
import com.google.android.gms.cast.framework.media.RemoteMediaClient;
import com.google.android.gms.cast.framework.media.TracksChooserDialogFragment;
import com.google.android.gms.cast.framework.media.widget.CastSeekBar;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzct;
import com.google.android.gms.internal.cast.zzcu;
import com.google.android.gms.internal.cast.zzcv;
import com.google.android.gms.internal.cast.zzcx;
import com.google.android.gms.internal.cast.zzcz;
import com.google.android.gms.internal.cast.zzda;
import com.google.android.gms.internal.cast.zzdb;
import com.google.android.gms.internal.cast.zzdc;
import com.google.android.gms.internal.cast.zzdd;
import com.google.android.gms.internal.cast.zzde;
import com.google.android.gms.internal.cast.zzdg;
import com.google.android.gms.internal.cast.zzdh;
import com.google.android.gms.internal.cast.zzdi;
import com.google.android.gms.internal.cast.zzdj;
import com.google.android.gms.internal.cast.zzdl;
import com.google.android.gms.internal.cast.zzdm;
import com.google.android.gms.internal.cast.zzdn;
import com.google.android.gms.internal.cast.zzdo;
import com.google.android.gms.internal.cast.zzdp;
import com.google.android.gms.internal.cast.zzdq;
import com.google.android.gms.internal.cast.zzdr;
import com.google.android.gms.internal.cast.zzds;
import com.google.android.gms.internal.cast.zzdt;
import com.google.android.gms.internal.cast.zzpm;
import com.google.android.gms.internal.cast.zzr;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.checkerframework.checker.nullness.qual.EnsuresNonNullIf;

/* loaded from: classes4.dex */
public class UIMediaController implements RemoteMediaClient.Listener, SessionManagerListener<CastSession> {
    private static final Logger zzb = null;
    final zza zza;
    private final Activity zzc;
    private final SessionManager zzd;
    private final Map zze;
    private final Set zzf;
    private RemoteMediaClient.Listener zzg;
    private RemoteMediaClient zzh;

    static {
        zzb = new Logger("UIMediaController");
    }

    public UIMediaController(Activity r2) {
        this.zze = new HashMap();
        this.zzf = new HashSet();
        this.zza = zza.zza();
        this.zzc = r2;
        CastContext r22 = CastContext.zza(r2);
        zzr.zzb(zzpm.zzp);
        if (r22 == null) goto L5;
        SessionManager r23 = r22.getSessionManager();
    L6:
        this.zzd = r23;
        if (r23 == null) goto L10;
        r23.addSessionManagerListener(this, CastSession.class);
        zzj(r23.getCurrentCastSession());
        return;
    L10:
        return;
    L5:
        r23 = null;
        goto L6
    }

    private final void zzg(int r8) {
        Iterator r0 = this.zzf.iterator();
    L3:
        boolean r2 = true;
        if (r0.hasNext() == false) goto L6;
        ((zzdr) r0.next()).zzb(true);
        goto L3
    L6:
        RemoteMediaClient r02 = getRemoteMediaClient();
        if (r02 != null) goto L9;
        return;
    L9:
        if (r02.hasMediaSession() == false) goto L20;
        zza r7 = this.zza;
        long r3 = r8 + r7.zzh();
        MediaSeekOptions.Builder r82 = new MediaSeekOptions.Builder();
        r82.setPosition(r3);
        if (r02.isLiveStream() == true) goto L13;
    L15:
        r2 = false;
    L16:
        r82.setIsSeekToInfinite(r2);
        r02.seek(r82.build());
        return;
    L13:
        if (r7.zzd(r3) == false) goto L15;
    }

    private final void zzh() {
        Iterator r2 = this.zzf.iterator();
    L4:
        if (r2.hasNext() == false) goto L6;
        ((zzdr) r2.next()).zzb(false);
        goto L4
    }

    private final void zzi(int r7, boolean r8) {
        if (r8 == false) goto L7;
        Iterator r82 = this.zzf.iterator();
    L5:
        if (r82.hasNext() == false) goto L9;
        ((zzdr) r82.next()).zza(r7 + this.zza.zzh());
        goto L5
    L9:
        return;
    }

    private final void zzj(Session r4) {
        if (isActive() == true) goto L21;
        if (r4 != null) goto L6;
        return;
    L6:
        if (r4.isConnected() == false) goto L24;
        CastSession r42 = (CastSession) r4;
        RemoteMediaClient r0 = r42.getRemoteMediaClient();
        this.zzh = r0;
        if (r0 == null) goto L23;
        r0.addListener(this);
        zza r02 = this.zza;
        Preconditions.checkNotNull(r02);
        r02.zza = r42.getRemoteMediaClient();
        Iterator r03 = this.zze.values().iterator();
    L12:
        if (r03.hasNext() == false) goto L17;
        Iterator r1 = ((List) r03.next()).iterator();
    L15:
        if (r1.hasNext() == false) goto L12;
        ((UIController) r1.next()).onSessionConnected(r42);
        goto L15
    L17:
        zzm();
        return;
    L23:
        return;
    L24:
        return;
    }

    private final void zzk() {
        if (isActive() == true) goto L5;
        return;
    L5:
        this.zza.zza = null;
        Iterator r0 = this.zze.values().iterator();
    L7:
        if (r0.hasNext() == false) goto L12;
        Iterator r2 = ((List) r0.next()).iterator();
    L10:
        if (r2.hasNext() == false) goto L7;
        ((UIController) r2.next()).onSessionEnded();
        goto L10
    L12:
        Preconditions.checkNotNull(this.zzh);
        this.zzh.removeListener(this);
        this.zzh = null;
    }

    private final void zzl(View r4, UIController r5) {
        SessionManager r0 = this.zzd;
        if (r0 == null) goto L13;
        Map r1 = this.zze;
        List r2 = (List) r1.get(r4);
        if (r2 != null) goto L8;
        r2 = new ArrayList();
        r1.put(r4, r2);
    L8:
        r2.add(r5);
        if (isActive() == false) goto L12;
        r5.onSessionConnected((CastSession) Preconditions.checkNotNull(r0.getCurrentCastSession()));
        zzm();
        return;
    L12:
        return;
    }

    private final void zzm() {
        Iterator r2 = this.zze.values().iterator();
    L4:
        if (r2.hasNext() == false) goto L9;
        Iterator r0 = ((List) r2.next()).iterator();
    L7:
        if (r0.hasNext() == false) goto L4;
        ((UIController) r0.next()).onMediaStatusUpdated();
        goto L7
    }

    @Deprecated
    public void bindImageViewToImageOfCurrentItem(ImageView r9, int r10, int r11) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        ImageHints r4 = new ImageHints(r10, 0, 0);
        zzl(r9, new zzda(r9, this.zzc, r4, r11, null, null));
    }

    @Deprecated
    public void bindImageViewToImageOfCurrentItem(ImageView r9, int r10, View r11) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        ImageHints r4 = new ImageHints(r10, 0, 0);
        zzl(r9, new zzda(r9, this.zzc, r4, 0, r11, null));
    }

    public void bindImageViewToImageOfCurrentItem(ImageView r9, ImageHints r10, int r11) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(r9, new zzda(r9, this.zzc, r10, r11, null, null));
    }

    public void bindImageViewToImageOfCurrentItem(ImageView r2, ImageHints r3, View r4) {
        zza(r2, r3, r4, null);
    }

    @Deprecated
    public void bindImageViewToImageOfPreloadedItem(ImageView r4, int r5, int r6) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        ImageHints r1 = new ImageHints(r5, 0, 0);
        zzl(r4, new zzcx(r4, this.zzc, r1, r6));
    }

    public void bindImageViewToImageOfPreloadedItem(ImageView r3, ImageHints r4, int r5) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(r3, new zzcx(r3, this.zzc, r4, r5));
    }

    public void bindImageViewToMuteToggle(ImageView r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        r3.setOnClickListener(new zzb(this));
        zzl(r3, new zzdg(r3, this.zzc));
    }

    public void bindImageViewToPlayPauseToggle(ImageView r10, Drawable r11, Drawable r12, Drawable r13, View r14, boolean r15) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzr.zzb(zzpm.zzm);
        r10.setOnClickListener(new zzc(this));
        zzl(r10, new zzdh(r10, this.zzc, r11, r12, r13, r14, r15));
    }

    public void bindProgressBar(ProgressBar r3) {
        bindProgressBar(r3, 1000);
    }

    public void bindProgressBar(ProgressBar r2, long r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(r2, new zzdi(r2, r3));
    }

    public void bindSeekBar(SeekBar r3) {
        bindSeekBar(r3, 1000);
    }

    public void bindSeekBar(SeekBar r3, long r4) {
        zzr.zzb(zzpm.zzn);
        Preconditions.checkMainThread("Must be called from the main thread.");
        r3.setOnSeekBarChangeListener(new zzi(this, r3));
        zzl(r3, new zzdl(r3, r4, this.zza));
    }

    public void bindSeekBar(CastSeekBar r3) {
        bindSeekBar(r3, 1000);
    }

    public void bindSeekBar(CastSeekBar r3, long r4) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzr.zzb(zzpm.zzn);
        r3.zzd = new zzh(this);
        zzl(r3, new zzct(r3, r4, this.zza));
    }

    public void bindTextViewToMetadataOfCurrentItem(TextView r2, String r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        bindTextViewToMetadataOfCurrentItem(r2, Collections.singletonList(r3));
    }

    public void bindTextViewToMetadataOfCurrentItem(TextView r2, List<String> r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(r2, new zzde(r2, r3));
    }

    public void bindTextViewToMetadataOfPreloadedItem(TextView r2, String r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        bindTextViewToMetadataOfPreloadedItem(r2, Collections.singletonList(r3));
    }

    public void bindTextViewToMetadataOfPreloadedItem(TextView r2, List<String> r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(r2, new zzdd(r2, r3));
    }

    public void bindTextViewToSmartSubtitle(TextView r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(r2, new zzdo(r2));
    }

    public void bindTextViewToStreamDuration(TextView r4) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(r4, new zzdp(r4, this.zzc.getString(R.string.cast_invalid_stream_duration_text), null));
    }

    public void bindTextViewToStreamDuration(TextView r4, View r5) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(r4, new zzdp(r4, this.zzc.getString(R.string.cast_invalid_stream_duration_text), r5));
    }

    public void bindTextViewToStreamPosition(TextView r3, boolean r4) {
        bindTextViewToStreamPosition(r3, r4, 1000);
    }

    public void bindTextViewToStreamPosition(TextView r4, boolean r5, long r6) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzdq r0 = new zzdq(r4, r6, this.zzc.getString(R.string.cast_invalid_stream_position_text));
        if (r5 == false) goto L5;
        this.zzf.add(r0);
    L5:
        zzl(r4, r0);
    }

    public void bindViewToClosedCaption(View r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        r3.setOnClickListener(new zzk(this));
        zzl(r3, new zzcu(r3, this.zzc));
    }

    public void bindViewToForward(View r2, long r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        r2.setOnClickListener(new zzf(this, r3));
        zzl(r2, new zzcv(r2, this.zza));
    }

    public void bindViewToLaunchExpandedController(View r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        r2.setOnClickListener(new zzj(this));
        zzl(r2, new zzdb(r2));
    }

    public void bindViewToLoadingIndicator(View r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(r2, new zzdc(r2));
    }

    public void bindViewToRewind(View r2, long r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        r2.setOnClickListener(new zzg(this, r3));
        zzl(r2, new zzdj(r2, this.zza));
    }

    public void bindViewToSkipNext(View r2, int r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        r2.setOnClickListener(new zzd(this));
        zzl(r2, new zzdm(r2, r3));
    }

    public void bindViewToSkipPrev(View r2, int r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        r2.setOnClickListener(new zze(this));
        zzl(r2, new zzdn(r2, r3));
    }

    public void bindViewToUIController(View r2, UIController r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(r2, r3);
    }

    public void bindViewVisibilityToMediaSession(View r2, int r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(r2, new zzdt(r2, r3));
    }

    public void bindViewVisibilityToPreloadingEvent(View r2, int r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(r2, new zzds(r2, r3));
    }

    public void dispose() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzk();
        this.zze.clear();
        SessionManager r0 = this.zzd;
        if (r0 == null) goto L5;
        r0.removeSessionManagerListener(this, CastSession.class);
    L5:
        this.zzg = null;
    }

    public RemoteMediaClient getRemoteMediaClient() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        return this.zzh;
    }

    @EnsuresNonNullIf(expression = {"remoteMediaClient"}, result = true)
    public boolean isActive() {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (this.zzh == null) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onAdBreakStatusUpdated() {
        zzm();
        RemoteMediaClient.Listener r0 = this.zzg;
        if (r0 == null) goto L6;
        r0.onAdBreakStatusUpdated();
        return;
    }

    public void onClosedCaptionClicked(View r3) {
        RemoteMediaClient r32 = getRemoteMediaClient();
        if (r32 != null) goto L5;
        return;
    L5:
        if (r32.hasMediaSession() == false) goto L16;
        Activity r2 = this.zzc;
        if ((r2 instanceof FragmentActivity) == false) goto L15;
        TracksChooserDialogFragment r33 = TracksChooserDialogFragment.newInstance();
        FragmentActivity r22 = (FragmentActivity) r2;
        FragmentTransaction r0 = r22.getSupportFragmentManager().beginTransaction();
        Fragment r23 = r22.getSupportFragmentManager().findFragmentByTag("TRACKS_CHOOSER_DIALOG_TAG");
        if (r23 == null) goto L12;
        r0.remove(r23);
    L12:
        r33.show(r0, "TRACKS_CHOOSER_DIALOG_TAG");
        return;
    L15:
        return;
    }

    public void onForwardClicked(View r5, long r6) {
        RemoteMediaClient r52 = getRemoteMediaClient();
        if (r52 != null) goto L5;
        return;
    L5:
        if (r52.hasMediaSession() == true) goto L7;
        return;
    L7:
        if (r52.zzj() == false) goto L10;
        r52.seek(Math.min(r52.getApproximateStreamPosition() + r6, r4.zzg() + this.zza.zzh()));
        return;
    L10:
        r52.seek(r52.getApproximateStreamPosition() + r6);
    }

    public void onLaunchExpandedControllerClicked(View r3) {
        Activity r2 = this.zzc;
        CastMediaOptions r32 = CastContext.getSharedInstance(r2).getCastOptions().getCastMediaOptions();
        if (r32 != null) goto L5;
        return;
    L5:
        if (TextUtils.isEmpty(r32.getExpandedControllerActivityClassName()) == true) goto L10;
        ComponentName r0 = new ComponentName(r2.getApplicationContext(), r32.getExpandedControllerActivityClassName());
        Intent r33 = new Intent();
        r33.setComponent(r0);
        r2.startActivity(r33);
        return;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onMetadataUpdated() {
        zzm();
        RemoteMediaClient.Listener r0 = this.zzg;
        if (r0 == null) goto L6;
        r0.onMetadataUpdated();
        return;
    }

    public void onMuteToggleClicked(ImageView r2) {
        CastSession r1 = CastContext.getSharedInstance(this.zzc.getApplicationContext()).getSessionManager().getCurrentCastSession();
        if (r1 != null) goto L5;
        return;
    L5:
        if (r1.isConnected() == false) goto L15;
        r1.setMute(!r1.isMute());     // Catch: Throwable -> L9
        return;
    L9:
        e = move-exception;
        zzb.e("Unable to call CastSession.setMute(boolean).", new Object[]{e});
        return;
    }

    public void onPlayPauseToggleClicked(ImageView r1) {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.hasMediaSession() == false) goto L9;
        r0.togglePlayback();
        return;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onPreloadStatusUpdated() {
        zzm();
        RemoteMediaClient.Listener r0 = this.zzg;
        if (r0 == null) goto L6;
        r0.onPreloadStatusUpdated();
        return;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onQueueStatusUpdated() {
        zzm();
        RemoteMediaClient.Listener r0 = this.zzg;
        if (r0 == null) goto L6;
        r0.onQueueStatusUpdated();
        return;
    }

    public void onRewindClicked(View r5, long r6) {
        RemoteMediaClient r52 = getRemoteMediaClient();
        if (r52 != null) goto L5;
        return;
    L5:
        if (r52.hasMediaSession() == true) goto L7;
        return;
    L7:
        if (r52.zzj() == false) goto L10;
        r52.seek(Math.max(r52.getApproximateStreamPosition() - r6, r4.zzf() + this.zza.zzh()));
        return;
    L10:
        r52.seek(r52.getApproximateStreamPosition() - r6);
    }

    public void onSeekBarProgressChanged(SeekBar r1, int r2, boolean r3) {
        zzi(r2, r3);
    }

    public void onSeekBarStartTrackingTouch(SeekBar r3) {
        Map r0 = this.zze;
        if (r0.containsKey(r3) == false) goto L10;
        Iterator r32 = ((List) r0.get(r3)).iterator();
    L6:
        if (r32.hasNext() == false) goto L10;
        UIController r02 = (UIController) r32.next();
        if ((r02 instanceof zzdl) == false) goto L6;
        ((zzdl) r02).zzb(false);
    L10:
        zzh();
    }

    public void onSeekBarStopTrackingTouch(SeekBar r4) {
        Map r0 = this.zze;
        if (r0.containsKey(r4) == false) goto L10;
        Iterator r02 = ((List) r0.get(r4)).iterator();
    L6:
        if (r02.hasNext() == false) goto L10;
        UIController r1 = (UIController) r02.next();
        if ((r1 instanceof zzdl) == false) goto L6;
        ((zzdl) r1).zzb(true);
    L10:
        zzg(r4.getProgress());
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onSendingRemoteMediaRequest() {
        Iterator r0 = this.zze.values().iterator();
    L4:
        if (r0.hasNext() == false) goto L9;
        Iterator r1 = ((List) r0.next()).iterator();
    L7:
        if (r1.hasNext() == false) goto L4;
        ((UIController) r1.next()).onSendingRemoteMediaRequest();
        goto L7
    L9:
        RemoteMediaClient.Listener r3 = this.zzg;
        if (r3 == null) goto L15;
        r3.onSendingRemoteMediaRequest();
        return;
    }

    public void onSessionEnded(CastSession r1, int r2) {
        zzk();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionEnded(Session r1, int r2) {
        onSessionEnded((CastSession) r1, r2);
    }

    public void onSessionEnding(CastSession r1) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionEnding(Session r1) {
        onSessionEnding((CastSession) r1);
    }

    public void onSessionResumeFailed(CastSession r1, int r2) {
        zzk();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResumeFailed(Session r1, int r2) {
        onSessionResumeFailed((CastSession) r1, r2);
    }

    public void onSessionResumed(CastSession r1, boolean r2) {
        zzj(r1);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResumed(Session r1, boolean r2) {
        onSessionResumed((CastSession) r1, r2);
    }

    public void onSessionResuming(CastSession r1, String r2) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionResuming(Session r1, String r2) {
        onSessionResuming((CastSession) r1, r2);
    }

    public void onSessionStartFailed(CastSession r1, int r2) {
        zzk();
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStartFailed(Session r1, int r2) {
        onSessionStartFailed((CastSession) r1, r2);
    }

    public void onSessionStarted(CastSession r1, String r2) {
        zzj(r1);
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStarted(Session r1, String r2) {
        onSessionStarted((CastSession) r1, r2);
    }

    public void onSessionStarting(CastSession r1) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionStarting(Session r1) {
        onSessionStarting((CastSession) r1);
    }

    public void onSessionSuspended(CastSession r1, int r2) {
    }

    @Override // com.google.android.gms.cast.framework.SessionManagerListener
    public final /* bridge */ /* synthetic */ void onSessionSuspended(Session r1, int r2) {
        onSessionSuspended((CastSession) r1, r2);
    }

    public void onSkipNextClicked(View r1) {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.hasMediaSession() == false) goto L9;
        r0.queueNext(null);
        return;
    }

    public void onSkipPrevClicked(View r1) {
        RemoteMediaClient r0 = getRemoteMediaClient();
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.hasMediaSession() == false) goto L9;
        r0.queuePrev(null);
        return;
    }

    @Override // com.google.android.gms.cast.framework.media.RemoteMediaClient.Listener
    public void onStatusUpdated() {
        zzm();
        RemoteMediaClient.Listener r0 = this.zzg;
        if (r0 == null) goto L6;
        r0.onStatusUpdated();
        return;
    }

    public void setPostRemoteMediaClientListener(RemoteMediaClient.Listener r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        this.zzg = r2;
    }

    public final void zza(ImageView r9, ImageHints r10, View r11, zzcz r12) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzl(r9, new zzda(r9, this.zzc, r10, 0, r11, r12));
    }

    public final void zzb(zzdr r1) {
        this.zzf.add(r1);
    }

    public final void zzc(CastSeekBar r1) {
        zzg(r1.getProgress());
    }

    public final void zzd(CastSeekBar r1) {
        zzh();
    }

    public final void zze(CastSeekBar r1, int r2, boolean r3) {
        zzi(r2, r3);
    }

    public final zza zzf() {
        return this.zza;
    }
}
