package com.google.android.gms.cast.framework.media.widget;

import android.os.Bundle;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.SeekBar;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zzf extends View.AccessibilityDelegate {
    final /* synthetic */ CastSeekBar zza;

    public /* synthetic */ zzf(CastSeekBar r1, byte[] r2) {
        Objects.requireNonNull(r1);
        this.zza = r1;
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void onInitializeAccessibilityEvent(View r1, AccessibilityEvent r2) {
        super.onInitializeAccessibilityEvent(r1, r2);
        r2.setClassName(SeekBar.class.getName());
        CastSeekBar r0 = this.zza;
        r2.setItemCount(r0.zza.zzb);
        r2.setCurrentItemIndex(r0.getProgress());
    }

    @Override // android.view.View.AccessibilityDelegate
    public final void onInitializeAccessibilityNodeInfo(View r1, AccessibilityNodeInfo r2) {
        super.onInitializeAccessibilityNodeInfo(r1, r2);
        r2.setClassName(SeekBar.class.getName());
        if (r1.isEnabled() == false) goto L6;
        r2.addAction(4096);
        r2.addAction(8192);
        return;
    }

    @Override // android.view.View.AccessibilityDelegate
    public final boolean performAccessibilityAction(View r3, int r4, Bundle r5) {
        if (r3.isEnabled() == true) goto L6;
        return false;
    L6:
        if (super.performAccessibilityAction(r3, r4, r5) == false) goto L10;
        return true;
    L10:
        if (r4 == 4096) goto L13;
        if (r4 == 8192) goto L13;
    L17:
        return false;
    L13:
        CastSeekBar r2 = this.zza;
        r2.zzd();
        int r32 = r2.zza.zzb / 20;
        if (r4 != 8192) goto L16;
        r32 = -r32;
    L16:
        r2.zzc(r2.getProgress() + r32);
        r2.zze();
        goto L17
    }
}
