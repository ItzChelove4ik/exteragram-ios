package com.google.android.gms.cast.framework.media;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TabHost;
import androidx.fragment.app.DialogFragment;
import com.google.android.gms.cast.MediaInfo;
import com.google.android.gms.cast.MediaStatus;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.CastSession;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public class TracksChooserDialogFragment extends DialogFragment {
    boolean zza;
    List zzb;
    List zzc;
    private long[] zzd;
    private Dialog zze;
    private RemoteMediaClient zzf;
    private MediaInfo zzg;
    private long[] zzh;

    @Deprecated
    public TracksChooserDialogFragment() {
    }

    private TracksChooserDialogFragment(MediaInfo r1, long[] r2) {
        this.zzg = r1;
        this.zzh = r2;
    }

    public static TracksChooserDialogFragment newInstance() {
        return new TracksChooserDialogFragment();
    }

    @Deprecated
    public static TracksChooserDialogFragment newInstance(MediaInfo r1, long[] r2) {
        return new TracksChooserDialogFragment(r1, r2);
    }

    private final void zzd() {
        Dialog r0 = this.zze;
        if (r0 == null) goto L6;
        r0.cancel();
        this.zze = null;
        return;
    }

    private static ArrayList zze(List r3, int r4) {
        ArrayList r0 = new ArrayList();
        Iterator r32 = r3.iterator();
    L4:
        if (r32.hasNext() == false) goto L8;
        MediaTrack r1 = (MediaTrack) r32.next();
        if (r1.getType() != r4) goto L4;
        r0.add(r1);
        goto L4
    L8:
        return r0;
    }

    private static int zzf(List r7, long[] r8, int r9) {
        if (r8 == null) goto L15;
        if (r7 == null) goto L15;
        int r1 = 0;
    L6:
        if (r1 >= r7.size()) goto L15;
        int r2 = 0;
    L9:
        if (r2 >= r8.length) goto L14;
        if (r8[r2] == ((MediaTrack) r7.get(r1)).getId()) goto L12;
        r2 = r2 + 1;
        goto L9
    L12:
        return r1;
    L14:
        r1 = r1 + 1;
    L15:
        return r9;
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onCreate(Bundle r7) {
        super.onCreate(r7);
        this.zza = true;
        this.zzc = new ArrayList();
        this.zzb = new ArrayList();
        this.zzd = new long[0];
        CastSession r1 = CastContext.getSharedInstance(getContext()).getSessionManager().getCurrentCastSession();
        if (r1 != null) goto L5;
    L34:
        this.zza = false;
        return;
    L5:
        if (r1.isConnected() == false) goto L34;
        RemoteMediaClient r12 = r1.getRemoteMediaClient();
        this.zzf = r12;
        if (r12 == null) goto L34;
        if (r12.hasMediaSession() == false) goto L34;
        if (this.zzf.getMediaInfo() == null) goto L34;
        RemoteMediaClient r13 = this.zzf;
        long[] r2 = this.zzh;
        if (r2 == null) goto L16;
        this.zzd = r2;
    L19:
        MediaInfo r22 = this.zzg;
        if (r22 != null) goto L23;
        r22 = r13.getMediaInfo();
    L23:
        if (r22 != null) goto L26;
        this.zza = false;
        return;
    L26:
        List<MediaTrack> r14 = r22.getMediaTracks();
        if (r14 != null) goto L30;
        this.zza = false;
        return;
    L30:
        this.zzc = zze(r14, 2);
        ArrayList r15 = zze(r14, 1);
        this.zzb = r15;
        if (r15.isEmpty() == true) goto L36;
        List r16 = this.zzb;
        MediaTrack.Builder r3 = new MediaTrack.Builder(-1, 1);
        r3.setName(String.format(Locale.ROOT, getActivity().getString(R.string.cast_tracks_chooser_dialog_none), new Object[0]));
        r3.setSubtype(2);
        r3.setContentId(_UrlKt.FRAGMENT_ENCODE_SET);
        r16.add(0, r3.build());
        return;
    L36:
        return;
    L16:
        MediaStatus r23 = r13.getMediaStatus();
        if (r23 == null) goto L19;
        this.zzd = r23.getActiveTrackIds();
        goto L19
    }

    @Override // androidx.fragment.app.DialogFragment
    public Dialog onCreateDialog(Bundle r13) {
        int r132 = zzf(this.zzb, this.zzd, 0);
        int r0 = zzf(this.zzc, this.zzd, -1);
        zzbk r2 = new zzbk(getActivity(), this.zzb, r132);
        zzbk r133 = new zzbk(getActivity(), this.zzc, r0);
        AlertDialog.Builder r02 = new AlertDialog.Builder(getActivity());
        View r3 = getActivity().getLayoutInflater().inflate(R.layout.cast_tracks_chooser_dialog_layout, null);
        ListView r4 = (ListView) r3.findViewById(R.id.text_list_view);
        ListView r6 = (ListView) r3.findViewById(R.id.audio_list_view);
        TabHost r7 = (TabHost) r3.findViewById(R.id.tab_host);
        r7.setup();
        if (r2.getCount() != 0) goto L5;
        r4.setVisibility(4);
    L7:
        if (r133.getCount() > 1) goto L9;
        r6.setVisibility(4);
    L10:
        AlertDialog.Builder r32 = r02.setView(r3);
        Locale r42 = Locale.ROOT;
        r32.setPositiveButton(String.format(r42, getActivity().getString(R.string.cast_tracks_chooser_dialog_ok), new Object[0]), new zzbi(this, r2, r133)).setNegativeButton(String.format(r42, getActivity().getString(R.string.cast_tracks_chooser_dialog_cancel), new Object[0]), new zzbh(this));
        Dialog r134 = this.zze;
        if (r134 == null) goto L13;
        r134.cancel();
        this.zze = null;
    L13:
        AlertDialog r135 = r02.create();
        this.zze = r135;
        return r135;
    L9:
        r6.setAdapter(r133);
        TabHost.TabSpec r43 = r7.newTabSpec("audioTab");
        r43.setContent(R.id.audio_list_view);
        r43.setIndicator(String.format(Locale.ROOT, getActivity().getString(R.string.cast_tracks_chooser_dialog_audio), new Object[0]));
        r7.addTab(r43);
        goto L10
    L5:
        r4.setAdapter(r2);
        TabHost.TabSpec r44 = r7.newTabSpec("textTab");
        r44.setContent(R.id.text_list_view);
        r44.setIndicator(String.format(Locale.ROOT, getActivity().getString(R.string.cast_tracks_chooser_dialog_subtitles), new Object[0]));
        r7.addTab(r44);
        goto L7
    }

    @Override // androidx.fragment.app.DialogFragment, androidx.fragment.app.Fragment
    public void onDestroyView() {
        Dialog r0 = getDialog();
        if (r0 != null) goto L5;
    L7:
        super.onDestroyView();
        return;
    L5:
        if (getRetainInstance() == false) goto L7;
        r0.setDismissMessage(null);
        goto L7
    }

    public final /* synthetic */ void zza(zzbk r8, zzbk r9) {
        if (this.zza == true) goto L6;
        zzd();
        return;
    L6:
        RemoteMediaClient r0 = (RemoteMediaClient) Preconditions.checkNotNull(this.zzf);
        if (r0.hasMediaSession() == true) goto L10;
        zzd();
        return;
    L10:
        ArrayList r1 = new ArrayList();
        MediaTrack r82 = r8.zza();
        if (r82 != null) goto L13;
    L15:
        MediaTrack r83 = r9.zza();
        if (r83 == null) goto L18;
        r1.add(Long.valueOf(r83.getId()));
    L18:
        long[] r84 = this.zzd;
        int r92 = 0;
        if (r84 != null) goto L21;
    L36:
        long[] r85 = new long[r1.size()];
    L38:
        if (r92 >= r1.size()) goto L40;
        r85[r92] = ((Long) r1.get(r92)).longValue();
        r92 = r92 + 1;
        goto L38
    L40:
        Arrays.sort(r85);
        r0.setActiveMediaTracks(r85);
        zzd();
        return;
    L21:
        if (r84.length <= 0) goto L36;
        HashSet r2 = new HashSet();
        Iterator r3 = this.zzc.iterator();
    L24:
        if (r3.hasNext() == false) goto L26;
        r2.add(Long.valueOf(((MediaTrack) r3.next()).getId()));
        goto L24
    L26:
        Iterator r32 = this.zzb.iterator();
    L28:
        if (r32.hasNext() == false) goto L30;
        r2.add(Long.valueOf(((MediaTrack) r32.next()).getId()));
        goto L28
    L30:
        int r33 = r84.length;
        int r4 = 0;
    L31:
        if (r4 >= r33) goto L36;
        Long r5 = Long.valueOf(r84[r4]);
        if (r2.contains(r5) == true) goto L35;
        r1.add(r5);
    L35:
        r4 = r4 + 1;
        goto L31
    L13:
        if (r82.getId() == (-1)) goto L15;
        r1.add(Long.valueOf(r82.getId()));
        goto L15
    }

    public final /* synthetic */ Dialog zzb() {
        return this.zze;
    }

    public final /* synthetic */ void zzc(Dialog r1) {
        this.zze = null;
    }
}
