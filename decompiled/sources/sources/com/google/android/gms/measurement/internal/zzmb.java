package com.google.android.gms.measurement.internal;

import android.os.Bundle;
import com.chaquo.python.internal.Common;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzmb extends zzg {
    protected zzlu zza;
    private volatile zzlu zzb;
    private volatile zzlu zzc;
    private final Map zzd;
    private com.google.android.gms.internal.measurement.zzdd zze;
    private volatile boolean zzf;
    private volatile zzlu zzg;
    private zzlu zzh;
    private boolean zzi;
    private final Object zzj;

    public zzmb(zzic r1) {
        super(r1);
        this.zzj = new Object();
        this.zzd = new ConcurrentHashMap();
    }

    private final void zzx(String r15, zzlu r16, boolean r17) {
        zzlu r2 = r16;
        if (this.zzb != null) goto L5;
        zzlu r3 = this.zzc;
    L7:
        if (r2.zzb != null) goto L13;
        if (r15 == null) goto L11;
        String r0 = zzi(r15, "Activity");
    L10:
        String r6 = r0;
        r2 = new zzlu(r2.zza, r6, r2.zzc, r2.zze, r2.zzf, r2.zzg);
        goto L13
    L11:
        r0 = null;
    L13:
        this.zzc = this.zzb;
        this.zzb = r2;
        zzic r02 = this.zzu;
        long r4 = r02.zzba().elapsedRealtime();
        r02.zzaX().zzj(new zzlw(this, r2, r3, r4, r17));
        return;
    L5:
        r3 = this.zzb;
        goto L7
    }

    private final void zzy(zzlu r4, boolean r5, long r6) {
        zzic r3 = this.zzu;
        r3.zzw().zzc(r3.zzba().elapsedRealtime());
        if (r4 != null) goto L5;
    L7:
        boolean r1 = false;
    L9:
        if (r3.zzh().zzb.zzd(r1, r5, r6) == false) goto L13;
        if (r4 == null) goto L14;
        r4.zzd = false;
        return;
    L14:
        return;
    L13:
        return;
    L5:
        if (r4.zzd == false) goto L7;
        r1 = true;
        goto L9
    }

    private final zzlu zzz(com.google.android.gms.internal.measurement.zzdd r7) {
        Preconditions.checkNotNull(r7);
        Integer r0 = Integer.valueOf(r7.zza);
        Map r1 = this.zzd;
        zzlu r2 = (zzlu) r1.get(r0);
        if (r2 != null) goto L6;
        zzlu r3 = new zzlu(null, zzi(r7.zzb, "Activity"), this.zzu.zzk().zzd());
        r1.put(r0, r3);
        r2 = r3;
    L6:
        if (this.zzg != null) goto L8;
        return r2;
    L8:
        return this.zzg;
    }

    @Override // com.google.android.gms.measurement.internal.zzg
    public final boolean zze() {
        return false;
    }

    public final zzlu zzh(boolean r2) {
        zzb();
        zzg();
        zzlu r0 = this.zza;
        if (r2 == true) goto L5;
        return r0;
    L5:
        if (r0 == null) goto L8;
        return r0;
    L8:
        return this.zzh;
    }

    public final String zzi(String r4, String r5) {
        if (r4 != null) goto L5;
        return "Activity";
    L5:
        String[] r42 = r4.split("\\.");
        int r52 = r42.length;
        if (r52 <= 0) goto L8;
        String r43 = r42[r52 - 1];
    L9:
        zzic r3 = this.zzu;
        if (r43.length() > r3.zzc().zze(null, false)) goto L12;
        return r43;
    L12:
        return r43.substring(0, r3.zzc().zze(null, false));
    L8:
        r43 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L9
    }

    public final void zzj(Bundle r13, long r14, long r16) {
        Object r1 = this.zzj;
        monitor-enter(r1);
    L9:
        th = move-exception;
        throw th;
    L5:
        if (this.zzi == true) goto L11;
        this.zzu.zzaW().zzh().zza("Cannot log screen view event when the app is in the background.");     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        return;
    L11:
        String r3 = r13.getString("screen_name");     // Catch: Throwable -> L9
        if (r3 != null) goto L14;
    L20:
        String r4 = r13.getString("screen_class");     // Catch: Throwable -> L9
        if (r4 != null) goto L23;
    L29:
        if (r4 != null) goto L34;
        com.google.android.gms.internal.measurement.zzdd r0 = this.zze;     // Catch: Throwable -> L9
        if (r0 == null) goto L33;
        r4 = zzi(r0.zzb, "Activity");     // Catch: Throwable -> L9
        goto L34
    L33:
        r4 = "Activity";
    L34:
        zzlu r02 = this.zzb;     // Catch: Throwable -> L9
        if (this.zzf == false) goto L43;
        if (r02 == null) goto L43;
        this.zzf = false;     // Catch: Throwable -> L9
        boolean r2 = Objects.equals(r02.zzb, r4);     // Catch: Throwable -> L9
        boolean r03 = Objects.equals(r02.zza, r3);     // Catch: Throwable -> L9
        if (r2 == false) goto L43;
        if (r03 == false) goto L43;
        this.zzu.zzaW().zzh().zza("Ignoring call to log screen view event with duplicate parameters.");     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        return;
    L43:
        monitor-exit(r1);     // Catch: Throwable -> L9
        zzic r04 = this.zzu;
        zzgs r12 = r04.zzaW().zzk();
        if (r3 != null) goto L47;
        String r22 = "null";
    L48:
        if (r4 != null) goto L50;
        String r5 = "null";
    L51:
        r12.zzc("Logging screen view with name, class", r22, r5);
        if (this.zzb != null) goto L54;
        zzlu r15 = this.zzc;
    L55:
        zzlu r23 = new zzlu(r3, r4, r04.zzk().zzd(), true, r14, r16);
        this.zzb = r23;
        this.zzc = r15;
        this.zzg = r23;
        long r10 = r04.zzba().elapsedRealtime();
        r04.zzaX().zzj(new zzlv(this, r13, r23, r15, r10));
        return;
    L54:
        r15 = this.zzb;
        goto L55
    L50:
        r5 = r4;
        goto L51
    L47:
        r22 = r3;
        goto L48
    L23:
        if (r4.length() > 0) goto L25;
    L26:
        this.zzu.zzaW().zzh().zzb("Invalid screen class length for screen view. Length", Integer.valueOf(r4.length()));     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        return;
    L25:
        if (r4.length() <= this.zzu.zzc().zze(null, false)) goto L29;
    L14:
        if (r3.length() > 0) goto L16;
    L17:
        this.zzu.zzaW().zzh().zzb("Invalid screen name length for screen view. Length", Integer.valueOf(r3.length()));     // Catch: Throwable -> L9
        monitor-exit(r1);     // Catch: Throwable -> L9
        return;
    L16:
        if (r3.length() <= this.zzu.zzc().zze(null, false)) goto L20;
        goto L17
    }

    @Deprecated
    public final void zzk(com.google.android.gms.internal.measurement.zzdd r8, String r9, String r10) {
        zzic r0 = this.zzu;
        if (r0.zzc().zzv() == true) goto L6;
        r0.zzaW().zzh().zza("setCurrentScreen cannot be called while screen reporting is disabled.");
        return;
    L6:
        zzlu r1 = this.zzb;
        if (r1 != null) goto L10;
        r0.zzaW().zzh().zza("setCurrentScreen cannot be called while no activity active");
        return;
    L10:
        Map r2 = this.zzd;
        Integer r3 = Integer.valueOf(r8.zza);
        if (r2.get(r3) != null) goto L14;
        r0.zzaW().zzh().zza("setCurrentScreen must be called with an activity in the activity lifecycle");
        return;
    L14:
        if (r10 != null) goto L16;
        r10 = zzi(r8.zzb, "Activity");
    L16:
        String r4 = r1.zzb;
        String r12 = r1.zza;
        boolean r42 = Objects.equals(r4, r10);
        boolean r13 = Objects.equals(r12, r9);
        if (r42 == false) goto L22;
        if (r13 == false) goto L22;
        r0.zzaW().zzh().zza("setCurrentScreen cannot be called with the same class and name");
        return;
    L22:
        if (r9 != null) goto L24;
    L29:
        if (r10 != null) goto L31;
    L36:
        zzgs r14 = r0.zzaW().zzk();
        if (r9 != null) goto L39;
        String r43 = "null";
    L40:
        r14.zzc("Setting current screen to name, class", r43, r10);
        zzlu r15 = new zzlu(r9, r10, r0.zzk().zzd());
        r2.put(r3, r15);
        zzx(r8.zzb, r15, true);
        return;
    L39:
        r43 = r9;
        goto L40
    L31:
        if (r10.length() > 0) goto L33;
    L34:
        r0.zzaW().zzh().zzb("Invalid class name length in setCurrentScreen. Length", Integer.valueOf(r10.length()));
        return;
    L33:
        if (r10.length() <= r0.zzc().zze(null, false)) goto L36;
    L24:
        if (r9.length() > 0) goto L26;
    L27:
        r0.zzaW().zzh().zzb("Invalid screen name length in setCurrentScreen. Length", Integer.valueOf(r9.length()));
        return;
    L26:
        if (r9.length() <= r0.zzc().zze(null, false)) goto L29;
        goto L27
    }

    public final zzlu zzl() {
        return this.zzb;
    }

    public final void zzm(zzlu r18, zzlu r19, long r20, boolean r22, Bundle r23) {
        zzg();
        boolean r6 = false;
        if (r19 != null) goto L5;
    L10:
        boolean r8 = true;
    L12:
        if (r22 == true) goto L14;
    L16:
        if (r8 == false) goto L56;
        if (r23 == null) goto L19;
        Bundle r82 = new Bundle(r23);
    L20:
        zzpp.zzay(r18, r82, true);
        if (r19 == null) goto L30;
        String r5 = r19.zza;
        if (r5 == null) goto L25;
        r82.putString("_pn", r5);
    L25:
        String r52 = r19.zzb;
        if (r52 == null) goto L28;
        r82.putString("_pc", r52);
    L28:
        r82.putLong("_pi", r19.zzc);
    L30:
        if (r6 == false) goto L34;
        zzoa r2 = this.zzu.zzh().zzb;
        long r11 = r20 - r2.zzb;
        r2.zzb = r20;
        if (r11 <= 0) goto L34;
        this.zzu.zzk().zzan(r82, r11);
    L34:
        zzic r24 = this.zzu;
        if (r24.zzc().zzv() == true) goto L37;
        r82.putLong("_mst", 1);
    L37:
        boolean r53 = r18.zze;
        if (true == r53) goto L40;
        String r112 = "auto";
    L41:
        long r12 = r24.zzba().currentTimeMillis();
        if (r53 == false) goto L47;
        long r14 = r18.zzf;
        if (r14 == 0) goto L47;
        r12 = r14;
    L47:
        if (r24.zzc().zzp(null, zzfy.zzbe) == false) goto L49;
        long r9 = r24.zzba().elapsedRealtime();
    L50:
        if (r53 == false) goto L54;
        long r142 = r18.zzg;
        if (r142 == 0) goto L54;
    L55:
        this.zzu.zzj().zzF(r112, "_vs", r12, r142, r82);
    L54:
        r142 = r9;
        goto L55
    L49:
        r9 = 0;
        goto L50
    L40:
        r112 = Common.ASSET_APP;
        goto L41
    L19:
        r82 = new Bundle();
    L56:
        if (r6 == false) goto L58;
        zzy(this.zza, true, r20);
    L58:
        this.zza = r18;
        if (r18.zze == false) goto L61;
        this.zzh = r18;
    L61:
        this.zzu.zzt().zzG(r18);
        return;
    L14:
        if (this.zza == null) goto L16;
        r6 = true;
        goto L16
    L5:
        if (r19.zzc != r18.zzc) goto L10;
        if (Objects.equals(r19.zzb, r18.zzb) == false) goto L10;
        if (Objects.equals(r19.zza, r18.zza) == false) goto L10;
        r8 = false;
        goto L12
    }

    public final void zzn(com.google.android.gms.internal.measurement.zzdd r6, Bundle r7) {
        if (this.zzu.zzc().zzv() == false) goto L12;
        if (r7 == null) goto L10;
        Bundle r72 = r7.getBundle("com.google.app_measurement.screen_service");
        if (r72 == null) goto L11;
        zzlu r0 = new zzlu(r72.getString("name"), r72.getString("referrer_name"), r72.getLong("id"));
        this.zzd.put(Integer.valueOf(r6.zza), r0);
        return;
    L11:
        return;
    L10:
        return;
    }

    public final void zzp(com.google.android.gms.internal.measurement.zzdd r5) {
        Object r0 = this.zzj;
        monitor-enter(r0);
        this.zzi = true;     // Catch: Throwable -> L14
        if (Objects.equals(r5, this.zze) == true) goto L16;
        monitor-enter(r0);     // Catch: Throwable -> L14
        this.zze = r5;     // Catch: Throwable -> L23
        this.zzf = false;     // Catch: Throwable -> L23
        monitor-exit(r0);     // Catch: Throwable -> L23
        zzic r1 = this.zzu;     // Catch: Throwable -> L14
        if (r1.zzc().zzv() == false) goto L16;
        this.zzg = null;     // Catch: Throwable -> L14
        r1.zzaX().zzj(new zzma(this));     // Catch: Throwable -> L14
        goto L16
    L23:
        th = move-exception;
        throw th;     // Catch: Throwable -> L14
    L16:
        monitor-exit(r0);     // Catch: Throwable -> L14
        zzic r02 = this.zzu;
        if (r02.zzc().zzv() == true) goto L21;
        this.zzb = this.zzg;
        r02.zzaX().zzj(new zzlx(this));
        return;
    L21:
        zzlu r03 = zzz(r5);
        zzx(r5.zzb, r03, false);
        zzd r4 = this.zzu.zzw();
        zzic r52 = r4.zzu;
        long r04 = r52.zzba().elapsedRealtime();
        r52.zzaX().zzj(new zzc(r4, r04));
        return;
    L14:
        th = move-exception;
        throw th;
    }

    public final void zzq(com.google.android.gms.internal.measurement.zzdd r6) {
        Object r0 = this.zzj;
        monitor-enter(r0);
        this.zzi = false;     // Catch: Throwable -> L13
        this.zzf = true;     // Catch: Throwable -> L13
        monitor-exit(r0);     // Catch: Throwable -> L13
        zzic r02 = this.zzu;
        long r1 = r02.zzba().elapsedRealtime();
        if (r02.zzc().zzv() == true) goto L11;
        this.zzb = null;
        r02.zzaX().zzj(new zzly(this, r1));
        return;
    L11:
        zzlu r62 = zzz(r6);
        this.zzc = this.zzb;
        this.zzb = null;
        r02.zzaX().zzj(new zzlz(this, r62, r1));
        return;
    L13:
        th = move-exception;
        throw th;
    }

    public final void zzs(com.google.android.gms.internal.measurement.zzdd r4, Bundle r5) {
        if (this.zzu.zzc().zzv() == false) goto L12;
        if (r5 == null) goto L10;
        zzlu r3 = (zzlu) this.zzd.get(Integer.valueOf(r4.zza));
        if (r3 == null) goto L11;
        Bundle r42 = new Bundle();
        r42.putLong("id", r3.zzc);
        r42.putString("name", r3.zza);
        r42.putString("referrer_name", r3.zzb);
        r5.putBundle("com.google.app_measurement.screen_service", r42);
        return;
    L11:
        return;
    L10:
        return;
    }

    public final void zzt(com.google.android.gms.internal.measurement.zzdd r3) {
        Object r0 = this.zzj;
        monitor-enter(r0);
    L7:
        th = move-exception;
        throw th;
    L5:
        if (Objects.equals(this.zze, r3) == false) goto L9;
        this.zze = null;     // Catch: Throwable -> L7
    L9:
        monitor-exit(r0);     // Catch: Throwable -> L7
        if (this.zzu.zzc().zzv() == true) goto L13;
        return;
    L13:
        this.zzd.remove(Integer.valueOf(r3.zza));
    }

    public final /* synthetic */ void zzu(zzlu r1, boolean r2, long r3) {
        zzy(r1, false, r3);
    }

    public final /* synthetic */ zzlu zzv() {
        return this.zzh;
    }

    public final /* synthetic */ void zzw(zzlu r1) {
        this.zzh = null;
    }
}
