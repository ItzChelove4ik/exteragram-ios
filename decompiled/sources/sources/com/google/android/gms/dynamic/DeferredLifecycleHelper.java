package com.google.android.gms.dynamic;

import android.R;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.dynamic.LifecycleDelegate;
import java.util.LinkedList;

/* loaded from: classes4.dex */
public abstract class DeferredLifecycleHelper<T extends LifecycleDelegate> {
    private LifecycleDelegate zaa;
    private Bundle zab;
    private LinkedList zac;
    private final OnDelegateCreatedListener zad;

    public DeferredLifecycleHelper() {
        this.zad = new zaa(this);
    }

    public static void showGooglePlayUnavailableMessage(FrameLayout r8) {
        GoogleApiAvailability r0 = GoogleApiAvailability.getInstance();
        Context r1 = r8.getContext();
        int r2 = r0.isGooglePlayServicesAvailable(r1);
        String r3 = com.google.android.gms.common.internal.zaf.zac(r1, r2);
        String r4 = com.google.android.gms.common.internal.zaf.zae(r1, r2);
        LinearLayout r5 = new LinearLayout(r8.getContext());
        r5.setOrientation(1);
        r5.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        r8.addView(r5);
        TextView r6 = new TextView(r8.getContext());
        r6.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        r6.setText(r3);
        r5.addView(r6);
        Intent r82 = r0.getErrorResolutionIntent(r1, r2, null);
        if (r82 == null) goto L6;
        Button r02 = new Button(r1);
        r02.setId(R.id.button1);
        r02.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
        r02.setText(r4);
        r5.addView(r02);
        r02.setOnClickListener(new zae(r1, r82));
        return;
    }

    private final void zae(int r2) {
    L3:
        if (this.zac.isEmpty() == true) goto L7;
        if (((zah) this.zac.getLast()).zaa() < r2) goto L10;
        this.zac.removeLast();
        goto L3
    L10:
        return;
    }

    private final void zaf(Bundle r2, zah r3) {
        LifecycleDelegate r0 = this.zaa;
        if (r0 == null) goto L7;
        r3.zab(r0);
        return;
    L7:
        if (this.zac != null) goto L9;
        this.zac = new LinkedList();
    L9:
        this.zac.add(r3);
        if (r2 == null) goto L15;
        Bundle r32 = this.zab;
        if (r32 != null) goto L14;
        this.zab = (Bundle) r2.clone();
        goto L15
    L14:
        r32.putAll(r2);
    L15:
        createDelegate(this.zad);
    }

    public abstract void createDelegate(OnDelegateCreatedListener<T> r1);

    public T getDelegate() {
        return (T) this.zaa;
    }

    public void onCreate(Bundle r2) {
        zaf(r2, new zac(this, r2));
    }

    public void onDestroy() {
        LifecycleDelegate r0 = this.zaa;
        if (r0 == null) goto L6;
        r0.onDestroy();
        return;
    L6:
        zae(1);
    }

    public void onLowMemory() {
        LifecycleDelegate r0 = this.zaa;
        if (r0 == null) goto L6;
        r0.onLowMemory();
        return;
    }

    public void onPause() {
        LifecycleDelegate r0 = this.zaa;
        if (r0 == null) goto L6;
        r0.onPause();
        return;
    L6:
        zae(5);
    }

    public void onResume() {
        zaf(null, new zag(this));
    }

    public void onSaveInstanceState(Bundle r2) {
        LifecycleDelegate r0 = this.zaa;
        if (r0 == null) goto L6;
        r0.onSaveInstanceState(r2);
        return;
    L6:
        Bundle r1 = this.zab;
        if (r1 == null) goto L10;
        r2.putAll(r1);
        return;
    }

    public void onStart() {
        zaf(null, new zaf(this));
    }

    public void onStop() {
        LifecycleDelegate r0 = this.zaa;
        if (r0 == null) goto L6;
        r0.onStop();
        return;
    L6:
        zae(4);
    }

    public final /* synthetic */ LifecycleDelegate zaa() {
        return this.zaa;
    }

    public final /* synthetic */ void zab(LifecycleDelegate r1) {
        this.zaa = r1;
    }

    public final /* synthetic */ void zac(Bundle r1) {
        this.zab = null;
    }

    public final /* synthetic */ LinkedList zad() {
        return this.zac;
    }
}
