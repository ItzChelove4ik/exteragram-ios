package com.google.android.gms.cast;

import android.net.Network;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.cast.internal.CastUtils;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes4.dex */
public class CastDevice extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final int CAPABILITY_AUDIO_IN = 8;
    public static final int CAPABILITY_AUDIO_OUT = 4;
    public static final int CAPABILITY_MULTIZONE_GROUP = 32;
    public static final int CAPABILITY_VIDEO_IN = 2;
    public static final int CAPABILITY_VIDEO_OUT = 1;
    public static final Parcelable.Creator<CastDevice> CREATOR = null;
    final String zza;
    final Boolean zzb;
    final Network zzc;
    private final String zzd;
    private InetAddress zze;
    private final String zzf;
    private final String zzg;
    private final String zzh;
    private final int zzi;
    private final List zzj;
    private final com.google.android.gms.cast.internal.zzp zzk;
    private final int zzl;
    private final String zzm;
    private final String zzn;
    private final int zzo;
    private final String zzp;
    private final byte[] zzq;
    private final String zzr;
    private final boolean zzs;
    private final com.google.android.gms.cast.internal.zzaa zzt;
    private final Integer zzu;

    static {
        CREATOR = new zzr();
    }

    public CastDevice(String r5, String r6, String r7, String r8, String r9, int r10, List r11, int r12, int r13, String r14, String r15, int r16, String r17, byte[] r18, String r19, boolean r20, com.google.android.gms.cast.internal.zzaa r21, Integer r22, Boolean r23, Network r24) {
        this.zzd = zzf(r5);
        String r52 = zzf(r6);
        this.zza = r52;
        if (TextUtils.isEmpty(r52) == false) goto L13;
    L8:
        this.zzf = zzf(r7);
        this.zzg = zzf(r8);
        this.zzh = zzf(r9);
        this.zzi = r10;
        if (r11 != null) goto L11;
        r11 = new ArrayList();
    L11:
        this.zzj = r11;
        this.zzl = r13;
        this.zzm = zzf(r14);
        this.zzn = r15;
        this.zzo = r16;
        this.zzp = r17;
        this.zzq = r18;
        this.zzr = r19;
        this.zzs = r20;
        this.zzt = r21;
        this.zzu = r22;
        this.zzb = r23;
        this.zzc = r24;
        this.zzk = new com.google.android.gms.cast.internal.zzp(r12, r21);
        return;
    L13:
        this.zze = InetAddress.getByName(r52);     // Catch: UnknownHostException -> L6
    L6:
        e = move-exception;
        String r62 = this.zza;
        String r53 = e.getMessage();
        int r0 = String.valueOf(r62).length();
        StringBuilder r3 = new StringBuilder((r0 + 48) + String.valueOf(r53).length());
        r3.append("Unable to convert host address (");
        r3.append(r62);
        r3.append(") to ipaddress: ");
        r3.append(r53);
        Log.i("CastDevice", r3.toString());
        goto L8
    }

    public static CastDevice getFromBundle(Bundle r1) {
        if (r1 == null) goto L10;
        ClassLoader r0 = CastDevice.class.getClassLoader();
        if (r0 == null) goto L8;
        r1.setClassLoader(r0);
        return (CastDevice) r1.getParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE");
    L8:
        return null;
    L10:
        return null;
    }

    private static String zzf(String r0) {
        if (r0 != null) goto L5;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        return r0;
    }

    public boolean equals(Object r7) {
        if (r7 != this) goto L6;
        return true;
    L6:
        if ((r7 instanceof CastDevice) == true) goto L8;
        return false;
    L8:
        CastDevice r72 = (CastDevice) r7;
        String r1 = this.zzd;
        String r3 = r72.zzd;
        if (r1 != null) goto L14;
        if (r3 != null) goto L12;
        return true;
    L12:
        return false;
    L14:
        if (CastUtils.zza(r1, r3) == true) goto L16;
    L64:
        return false;
    L16:
        if (CastUtils.zza(this.zze, r72.zze) == false) goto L64;
        if (CastUtils.zza(this.zzg, r72.zzg) == false) goto L64;
        if (CastUtils.zza(this.zzf, r72.zzf) == false) goto L64;
        String r12 = this.zzh;
        if (CastUtils.zza(r12, r72.zzh) == false) goto L64;
        int r32 = this.zzi;
        if (r32 != r72.zzi) goto L64;
        if (CastUtils.zza(this.zzj, r72.zzj) == false) goto L64;
        if (this.zzk.zza() != r72.zzk.zza()) goto L64;
        if (this.zzl != r72.zzl) goto L64;
        if (CastUtils.zza(this.zzm, r72.zzm) == false) goto L64;
        if (CastUtils.zza(Integer.valueOf(this.zzo), Integer.valueOf(r72.zzo)) == false) goto L64;
        if (CastUtils.zza(this.zzp, r72.zzp) == false) goto L64;
        if (CastUtils.zza(this.zzn, r72.zzn) == false) goto L64;
        if (CastUtils.zza(r12, r72.getDeviceVersion()) == false) goto L64;
        if (r32 != r72.getServicePort()) goto L64;
        byte[] r13 = this.zzq;
        if (r13 != null) goto L48;
        if (r72.zzq != null) goto L48;
    L50:
        if (CastUtils.zza(this.zzr, r72.zzr) == false) goto L64;
        if (this.zzs != r72.zzs) goto L64;
        if (CastUtils.zza(zzb(), r72.zzb()) == false) goto L64;
        Boolean r14 = Boolean.valueOf(zze());
        if (r72.zze() == true) goto L58;
    L60:
        boolean r6 = false;
    L62:
        if (CastUtils.zza(r14, Boolean.valueOf(r6)) == false) goto L64;
        return true;
    L58:
        if (CastUtils.zza(this.zzc, r72.zzc) == false) goto L60;
        r6 = true;
    L48:
        if (Arrays.equals(r13, r72.zzq) == false) goto L64;
        goto L64
    }

    public String getDeviceId() {
        String r1 = this.zzd;
        if (r1.startsWith("__cast_nearby__") == true) goto L5;
        return r1;
    L5:
        return r1.substring(16);
    }

    public String getDeviceVersion() {
        return this.zzh;
    }

    public String getFriendlyName() {
        return this.zzf;
    }

    public WebImage getIcon(int r9, int r10) {
        List r8 = this.zzj;
        WebImage r1 = null;
        if (r8.isEmpty() == false) goto L6;
        return null;
    L6:
        if (r9 <= 0) goto L36;
        if (r10 <= 0) goto L36;
        Iterator r2 = r8.iterator();
        WebImage r3 = null;
    L11:
        if (r2.hasNext() == false) goto L29;
        WebImage r4 = (WebImage) r2.next();
        int r5 = r4.getWidth();
        int r6 = r4.getHeight();
        if (r5 < r9) goto L21;
        if (r6 < r10) goto L21;
        if (r1 == null) goto L20;
        if (r1.getWidth() <= r5) goto L11;
        if (r1.getHeight() <= r6) goto L11;
    L20:
        r1 = r4;
    L21:
        if (r5 >= r9) goto L11;
        if (r6 >= r10) goto L11;
        if (r3 == null) goto L28;
        if (r3.getWidth() >= r5) goto L11;
        if (r3.getHeight() >= r6) goto L11;
    L28:
        r3 = r4;
        goto L11
    L29:
        if (r1 == null) goto L31;
        return r1;
    L31:
        if (r3 == null) goto L34;
        return r3;
    L34:
        return (WebImage) r8.get(0);
    L36:
        return (WebImage) r8.get(0);
    }

    public List<WebImage> getIcons() {
        return Collections.unmodifiableList(this.zzj);
    }

    public InetAddress getInetAddress() {
        return this.zze;
    }

    @Deprecated
    public Inet4Address getIpAddress() {
        if (hasIPv4Address() == true) goto L5;
        return null;
    L5:
        return (Inet4Address) this.zze;
    }

    public String getModelName() {
        return this.zzg;
    }

    public int getServicePort() {
        return this.zzi;
    }

    public boolean hasCapabilities(int[] r5) {
        if (r5 != null) goto L5;
        return false;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= r5.length) goto L12;
        if (this.zzk.zzb(r5[r1]) == false) goto L10;
        r1 = r1 + 1;
        goto L7
    L10:
        return false;
    L12:
        return true;
    }

    public boolean hasCapability(int r1) {
        return this.zzk.zzb(r1);
    }

    public boolean hasIPv4Address() {
        return getInetAddress() instanceof Inet4Address;
    }

    public boolean hasIPv6Address() {
        return getInetAddress() instanceof Inet6Address;
    }

    public boolean hasIcons() {
        if (this.zzj.isEmpty() == true) goto L6;
        return true;
    L6:
        return false;
    }

    public int hashCode() {
        String r0 = this.zzd;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.hashCode();
    }

    public boolean isOnLocalNetwork() {
        String r0 = this.zzd;
        if (r0.startsWith("__cast_nearby__") == false) goto L5;
        return false;
    L5:
        if (this.zzs == false) goto L7;
        return false;
    L7:
        if (r0.startsWith("__cast_bettertogether__") == true) goto L13;
        return true;
    L13:
        return false;
    }

    public boolean isSameDevice(CastDevice r3) {
        if (r3 != null) goto L5;
        return false;
    L5:
        if (TextUtils.isEmpty(getDeviceId()) == false) goto L7;
    L15:
        String r2 = this.zzp;
        if (TextUtils.isEmpty(r2) == true) goto L21;
        String r32 = r3.zzp;
        if (TextUtils.isEmpty(r32) == false) goto L20;
        return false;
    L20:
        return CastUtils.zza(r2, r32);
    L21:
        return false;
    L7:
        if (getDeviceId().startsWith("__cast_ble__") == true) goto L15;
        if (TextUtils.isEmpty(r3.getDeviceId()) == true) goto L15;
        if (r3.getDeviceId().startsWith("__cast_ble__") == true) goto L15;
        return CastUtils.zza(getDeviceId(), r3.getDeviceId());
    }

    public void putInBundle(Bundle r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        r2.putParcelable("com.google.android.gms.cast.EXTRA_CAST_DEVICE", this);
    }

    public String toString() {
        com.google.android.gms.cast.internal.zzp r0 = this.zzk;
        if (r0.zzb(64) == false) goto L6;
        String r1 = "[dynamic group]";
    L13:
        if (r0.zzb(262144) == false) goto L15;
        r1 = r1.concat("[cast connect]");
    L15:
        String r02 = this.zzf;
        Locale r2 = Locale.ROOT;
        int r3 = CastUtils.zza;
        if (TextUtils.isEmpty(r02) == true) goto L24;
        int r32 = r02.length();
        if (r32 > 2) goto L22;
        if (r32 != 2) goto L21;
        r02 = "xx";
        goto L24
    L21:
        r02 = "x";
        goto L24
    L22:
        r02 = String.format(r2, "%c%d%c", new Object[]{Character.valueOf(r02.charAt(0)), Integer.valueOf(r32 - 2), Character.valueOf(r02.charAt(r32 - 1))});
    L24:
        return String.format(r2, "\"%s\" (%s) %s", new Object[]{r02, this.zzd, r1});
    L6:
        if (r0.zzc() == false) goto L9;
        r1 = "[static group]";
        goto L13
    L9:
        if (r0.zzd() == false) goto L11;
        r1 = "[speaker pair]";
        goto L13
    L11:
        r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L13
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r5, int r6) {
        String r0 = this.zzd;
        int r1 = SafeParcelWriter.beginObjectHeader(r5);
        SafeParcelWriter.writeString(r5, 2, r0, false);
        SafeParcelWriter.writeString(r5, 3, this.zza, false);
        SafeParcelWriter.writeString(r5, 4, getFriendlyName(), false);
        SafeParcelWriter.writeString(r5, 5, getModelName(), false);
        SafeParcelWriter.writeString(r5, 6, getDeviceVersion(), false);
        SafeParcelWriter.writeInt(r5, 7, getServicePort());
        SafeParcelWriter.writeTypedList(r5, 8, getIcons(), false);
        SafeParcelWriter.writeInt(r5, 9, this.zzk.zza());
        SafeParcelWriter.writeInt(r5, 10, this.zzl);
        SafeParcelWriter.writeString(r5, 11, this.zzm, false);
        SafeParcelWriter.writeString(r5, 12, this.zzn, false);
        SafeParcelWriter.writeInt(r5, 13, this.zzo);
        SafeParcelWriter.writeString(r5, 14, this.zzp, false);
        SafeParcelWriter.writeByteArray(r5, 15, this.zzq, false);
        SafeParcelWriter.writeString(r5, 16, this.zzr, false);
        SafeParcelWriter.writeBoolean(r5, 17, this.zzs);
        SafeParcelWriter.writeParcelable(r5, 18, zzb(), r6, false);
        SafeParcelWriter.writeIntegerObject(r5, 19, this.zzu, false);
        SafeParcelWriter.writeBooleanObject(r5, 20, Boolean.valueOf(zze()), false);
        SafeParcelWriter.writeParcelable(r5, 21, this.zzc, r6, false);
        SafeParcelWriter.finishObjectHeader(r5, r1);
    }

    public final String zza() {
        return this.zzn;
    }

    public final com.google.android.gms.cast.internal.zzaa zzb() {
        com.google.android.gms.cast.internal.zzaa r0 = this.zzt;
        if (r0 == null) goto L5;
    L8:
        return r0;
    L5:
        if (this.zzk.zzd() == false) goto L8;
        return com.google.android.gms.cast.internal.zzz.zza(1);
    }

    public final int zzc() {
        return this.zzk.zza();
    }

    public final int zzd() {
        com.google.android.gms.cast.internal.zzp r0 = this.zzk;
        if (r0.zzb(64) == false) goto L5;
        return 4;
    L5:
        if (r0.zzc() == false) goto L7;
        return 3;
    L7:
        if (r0.zzd() == false) goto L11;
        return 5;
    L11:
        if (hasCapability(1) == false) goto L14;
        return 2;
    L14:
        return 1;
    }

    public final boolean zze() {
        Boolean r0 = this.zzb;
        if (r0 != null) goto L5;
        int r1 = this.zzl;
        if (r1 != (-1)) goto L9;
        return false;
    L9:
        if ((r1 & 2) <= 0) goto L14;
        return true;
    L14:
        return false;
    L5:
        return r0.booleanValue();
    }
}
