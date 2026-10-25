package com.google.android.gms.cast.framework.media;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import com.google.android.gms.cast.MediaTrack;
import com.google.android.gms.cast.framework.R;
import com.google.android.gms.common.internal.Preconditions;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public final class zzbk extends ArrayAdapter implements View.OnClickListener {
    private final Context zza;
    private int zzb;

    public zzbk(Context r2, List r3, int r4) {
        int r0 = R.layout.cast_tracks_chooser_dialog_row_layout;
        if (r3 != null) goto L5;
        r3 = new ArrayList();
    L5:
        super(r2, r0, r3);
        this.zza = r2;
        this.zzb = r4;
    }

    @Override // android.widget.ArrayAdapter, android.widget.Adapter
    public final View getView(int r6, View r7, ViewGroup r8) {
        boolean r0 = false;
        if (r7 != null) goto L5;
        r7 = ((LayoutInflater) Preconditions.checkNotNull((LayoutInflater) this.zza.getSystemService("layout_inflater"))).inflate(R.layout.cast_tracks_chooser_dialog_row_layout, r8, false);
        zzbj r82 = new zzbj(this, (TextView) r7.findViewById(R.id.text), (RadioButton) r7.findViewById(R.id.radio), null);
        r7.setTag(r82);
    L6:
        RadioButton r1 = r82.zzb;
        r1.setTag(Integer.valueOf(r6));
        if (this.zzb != r6) goto L9;
        r0 = true;
    L9:
        r1.setChecked(r0);
        r7.setOnClickListener(this);
        MediaTrack r02 = (MediaTrack) Preconditions.checkNotNull((MediaTrack) getItem(r6));
        String r12 = r02.getName();
        Locale r2 = r02.getLanguageLocale();
        if (TextUtils.isEmpty(r12) == true) goto L12;
    L19:
        r82.zza.setText(r12);
        return r7;
    L12:
        if (r02.getSubtype() != 2) goto L14;
        r12 = this.zza.getString(R.string.cast_tracks_chooser_dialog_closed_captions);
        goto L19
    L14:
        if (r2 == null) goto L18;
        r12 = r2.getDisplayLanguage();
        if (TextUtils.isEmpty(r12) == false) goto L19;
    L18:
        r12 = this.zza.getString(R.string.cast_tracks_chooser_dialog_default_track_name, new Object[]{Integer.valueOf(r6 + 1)});
        goto L19
    L5:
        r82 = (zzbj) Preconditions.checkNotNull((zzbj) r7.getTag());
        goto L6
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View r1) {
        this.zzb = ((Integer) Preconditions.checkNotNull(((zzbj) Preconditions.checkNotNull((zzbj) r1.getTag())).zzb.getTag())).intValue();
        notifyDataSetChanged();
    }

    public final MediaTrack zza() {
        int r0 = this.zzb;
        if (r0 >= 0) goto L5;
        return null;
    L5:
        if (r0 < getCount()) goto L7;
        return null;
    L7:
        return (MediaTrack) getItem(this.zzb);
    }
}
