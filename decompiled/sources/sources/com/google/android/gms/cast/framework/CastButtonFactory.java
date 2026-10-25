package com.google.android.gms.cast.framework;

import android.content.Context;
import android.view.Menu;
import android.view.MenuItem;
import androidx.core.view.MenuItemCompat;
import androidx.mediarouter.app.MediaRouteActionProvider;
import androidx.mediarouter.app.MediaRouteButton;
import androidx.mediarouter.app.MediaRouteDialogFactory;
import androidx.mediarouter.media.MediaRouteSelector;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.internal.Logger;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.cast.zzpm;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Tasks;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Executor;

/* loaded from: classes4.dex */
public final class CastButtonFactory {
    public static final /* synthetic */ int zza = 0;
    private static final Logger zzb = null;
    private static final List zzc = null;
    private static final Object zzd = null;
    private static final List zze = null;
    private static final Object zzf = null;

    static {
        zzb = new Logger("CastButtonFactory");
        zzc = new ArrayList();
        zzd = new Object();
        zze = new ArrayList();
        zzf = new Object();
    }

    private CastButtonFactory() {
    }

    public static MenuItem setUpMediaRouteButton(Context r2, Menu r3, int r4) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        Preconditions.checkNotNull(r3);
        MenuItem r32 = r3.findItem(r4);
        if (r32 != null) goto L20;
        f$$ExternalSyntheticBUOutline1.m(String.format(Locale.ROOT, "menu doesn't contain a menu item whose ID is %d.", new Object[]{Integer.valueOf(r4)}));
        return null;
    L20:
        zzf(r2, r32, null);     // Catch: IllegalArgumentException -> L13
        Object r22 = zzd;     // Catch: IllegalArgumentException -> L13
        monitor-enter(r22);     // Catch: IllegalArgumentException -> L13
        zzc.add(new WeakReference(r32));     // Catch: Throwable -> L10
        monitor-exit(r22);     // Catch: Throwable -> L10
        com.google.android.gms.internal.cast.zzr.zzb(zzpm.zzH);
        return r32;
    L10:
        th = move-exception;
        throw th;     // Catch: IllegalArgumentException -> L13
    L13:
        e = move-exception;
        throw new IllegalArgumentException(String.format(Locale.ROOT, "menu item with ID %d doesn't have a MediaRouteActionProvider.", new Object[]{Integer.valueOf(r4)}), e);
    }

    public static Task<MenuItem> setUpMediaRouteButton(Context r3, Executor r4, Menu r5, int r6) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        Preconditions.checkNotNull(r5);
        MenuItem r52 = r5.findItem(r6);
        if (r52 == null) goto L5;
        Preconditions.checkMainThread("Must be called from the main thread.");     // Catch: IllegalArgumentException -> L9
        MediaRouteActionProvider r0 = zzj(r52);     // Catch: IllegalArgumentException -> L9
        if (r0 != null) goto L11;
        Task r32 = Tasks.forException(new IllegalArgumentException("cannot refreshButtonSelector with null mediaRouteActionProvider"));     // Catch: IllegalArgumentException -> L9
    L12:
        return r32.addOnSuccessListener(new zze(null));
    L11:
        TaskCompletionSource r2 = new TaskCompletionSource();     // Catch: IllegalArgumentException -> L9
        CastContext.getSharedInstance(r3, r4).addOnSuccessListener(new zzb(r0, null, r2, r52)).addOnFailureListener(new zzc(r2));     // Catch: IllegalArgumentException -> L9
        r32 = r2.getTask();     // Catch: IllegalArgumentException -> L9
    L9:
        e = move-exception;
        return Tasks.forException(new IllegalArgumentException(String.format(Locale.ROOT, "menu item with ID %d doesn't have a MediaRouteActionProvider.", new Object[]{Integer.valueOf(r6)}), e));
    L5:
        return Tasks.forException(new IllegalArgumentException(String.format(Locale.ROOT, "menu doesn't contain a menu item whose ID is %d.", new Object[]{Integer.valueOf(r6)})));
    }

    public static Task<Void> setUpMediaRouteButton(Context r2, Executor r3, MediaRouteButton r4) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (r4 == null) goto L6;
        Preconditions.checkMainThread("Must be called from the main thread.");
        TaskCompletionSource r0 = new TaskCompletionSource();
        CastContext.getSharedInstance(r2, r3).addOnSuccessListener(new zzd(r4, null, r0)).addOnFailureListener(new zzf(r0));
        return r0.getTask().addOnSuccessListener(new zza(r4, null));
    L6:
        com.google.android.gms.internal.cast.zzr.zzb(zzpm.zzH);
        return Tasks.forResult(null);
    }

    public static void setUpMediaRouteButton(Context r2, MediaRouteButton r3) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        if (r3 == null) goto L12;
        zzg(r2, r3, null);
        Object r22 = zzf;
        monitor-enter(r22);
        zze.add(new WeakReference(r3));     // Catch: Throwable -> L9
        monitor-exit(r22);     // Catch: Throwable -> L9
    L9:
        th = move-exception;
        throw th;
    L12:
        com.google.android.gms.internal.cast.zzr.zzb(zzpm.zzH);
    }

    public static void zza(Context r5) {
        Object r0 = zzd;
        monitor-enter(r0);
        Iterator r1 = zzc.iterator();     // Catch: Throwable -> L11
    L6:
        if (r1.hasNext() == false) goto L15;
        MenuItem r2 = (MenuItem) ((WeakReference) r1.next()).get();     // Catch: Throwable -> L11
        if (r2 == null) goto L6;
        zzf(r5, r2, null);     // Catch: Throwable -> L11 IllegalArgumentException -> L13
    L13:
        e = move-exception;
        zzb.w("Unexpected exception when refreshing MediaRouteSelectors for Cast buttons", new Object[]{e});     // Catch: Throwable -> L11
        goto L6
    L15:
        monitor-exit(r0);     // Catch: Throwable -> L11
        Object r12 = zzf;
        monitor-enter(r12);
        Iterator r02 = zze.iterator();     // Catch: Throwable -> L24
    L20:
        if (r02.hasNext() == false) goto L26;
        MediaRouteButton r22 = (MediaRouteButton) ((WeakReference) r02.next()).get();     // Catch: Throwable -> L24
        if (r22 == null) goto L20;
        zzg(r5, r22, null);     // Catch: Throwable -> L24
        goto L20
    L26:
        monitor-exit(r12);     // Catch: Throwable -> L24
        return;
    L24:
        th = move-exception;
        throw th;
    L11:
        th = move-exception;
        throw th;
    }

    public static /* synthetic */ void zzb(MediaRouteDialogFactory r2, MenuItem r3) {
        Object r22 = zzd;
        monitor-enter(r22);
        zzc.add(new WeakReference(r3));     // Catch: Throwable -> L8
        monitor-exit(r22);     // Catch: Throwable -> L8
        com.google.android.gms.internal.cast.zzr.zzb(zzpm.zzH);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public static /* synthetic */ void zzc(MediaRouteButton r1, MediaRouteDialogFactory r2, Void r3) {
        Object r22 = zzf;
        monitor-enter(r22);
        zze.add(new WeakReference(r1));     // Catch: Throwable -> L8
        monitor-exit(r22);     // Catch: Throwable -> L8
        com.google.android.gms.internal.cast.zzr.zzb(zzpm.zzH);
        return;
    L8:
        th = move-exception;
        throw th;
    }

    public static /* synthetic */ void zzd(MediaRouteActionProvider r0, MediaRouteDialogFactory r1, TaskCompletionSource r2, MenuItem r3, CastContext r4) {
        zzh(r4, r0, null);
        r2.setResult(r3);
    }

    public static /* synthetic */ void zze(MediaRouteButton r0, MediaRouteDialogFactory r1, TaskCompletionSource r2, CastContext r3) {
        zzi(r3, r0, null);
        r2.setResult(null);
    }

    private static void zzf(Context r0, MenuItem r1, MediaRouteDialogFactory r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        MediaRouteActionProvider r12 = zzj(r1);
        if (r12 == null) goto L6;
        zzh(CastContext.zza(r0), r12, null);
        return;
    L6:
        f$$ExternalSyntheticBUOutline1.m("cannot refreshButtonSelector with null mediaRouteActionProvider");
    }

    private static void zzg(Context r0, MediaRouteButton r1, MediaRouteDialogFactory r2) {
        Preconditions.checkMainThread("Must be called from the main thread.");
        zzi(CastContext.zza(r0), r1, null);
    }

    private static void zzh(CastContext r0, MediaRouteActionProvider r1, MediaRouteDialogFactory r2) {
        if (r0 == null) goto L7;
        MediaRouteSelector r02 = r0.getMergedSelector();
        if (r02 == null) goto L8;
        r1.setRouteSelector(r02);
        return;
    L8:
        return;
    }

    private static void zzi(CastContext r0, MediaRouteButton r1, MediaRouteDialogFactory r2) {
        if (r0 == null) goto L7;
        MediaRouteSelector r02 = r0.getMergedSelector();
        if (r02 == null) goto L8;
        r1.setRouteSelector(r02);
        return;
    L8:
        return;
    }

    private static MediaRouteActionProvider zzj(MenuItem r0) {
        MediaRouteActionProvider r02 = (MediaRouteActionProvider) MenuItemCompat.getActionProvider(r0);
        if (r02 != null) goto L6;
        return null;
    L6:
        return r02;
    }
}
