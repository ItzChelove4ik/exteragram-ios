package com.google.android.recaptcha.internal;

import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import com.google.android.gms.internal.cast.zzxk$$ExternalSyntheticBUOutline0;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import okio.Segment$$ExternalSyntheticBUOutline1;

/* loaded from: classes5.dex */
public final class zzsj implements zzsk {
    private final List zza;
    private final Map zzb;

    private zzsj(List r1, Map r2) {
        this.zza = r1;
        this.zzb = r2;
    }

    @Deprecated
    public static final zzsj zzf(byte[] r7) {
        zzsc r0 = zzsx.zza();     // Catch: zzagk -> L47
        zzwy r72 = zzwy.zzd(r7, zzafh.zzb());     // Catch: zzagk -> L47
        Iterator r1 = r72.zzb().iterator();     // Catch: zzagk -> L47
    L4:
        if (r1.hasNext() == false) goto L28;
        zzwx r2 = (zzwx) r1.next();     // Catch: zzagk -> L47
        if (r2.zzb().zzi() == 2) goto L11;
        if (r2.zzb().zzi() == 3) goto L11;
        if (r2.zzb().zzi() != 4) goto L4;
    L11:
        int r12 = r2.zzb().zzi();     // Catch: zzagk -> L47
        if (r12 == 2) goto L24;
        if (r12 == 3) goto L23;
        if (r12 != 4) goto L16;
        String r13 = "ASYMMETRIC_PRIVATE";
    L26:
        throw new GeneralSecurityException(String.format("keyset contains key material of type %s for type url %s", new Object[]{r13, r2.zzb().zza()}));     // Catch: zzagk -> L47
    L16:
        if (r12 != 5) goto L18;
        r13 = "ASYMMETRIC_PUBLIC";
        goto L26
    L18:
        if (r12 == 6) goto L20;
        r13 = "UNRECOGNIZED";
        goto L26
    L20:
        r13 = "REMOTE";
        goto L26
    L23:
        r13 = "SYMMETRIC";
        goto L26
    L24:
        r13 = "UNKNOWN_KEYMATERIAL";
        goto L26
    L28:
        if (r72.zzc() <= 0) goto L46;
        List r73 = zzi(r72, r0);     // Catch: zzagk -> L47
        if (zzh(r0) == false) goto L44;
        HashSet r22 = new HashSet();     // Catch: zzagk -> L47
        Iterator r3 = r73.iterator();     // Catch: zzagk -> L47
        boolean r4 = false;
    L33:
        if (r3.hasNext() == false) goto L39;
        zzsi r5 = (zzsi) r3.next();     // Catch: zzagk -> L47
        if (r22.contains(Integer.valueOf(r5.zzc())) == true) goto L37;
        r22.add(Integer.valueOf(r5.zzc()));     // Catch: zzagk -> L47
        r4 = r4 | r5.zzd();     // Catch: zzagk -> L47
        goto L33
    L37:
        int r23 = r5.zzc();     // Catch: zzagk -> L47
        StringBuilder r42 = new StringBuilder(String.valueOf(r23).length() + 121);     // Catch: zzagk -> L47
        r42.append("KeyID ");     // Catch: zzagk -> L47
        r42.append(r23);     // Catch: zzagk -> L47
        r42.append(" is duplicated in the keyset, and Tink is configured to reject such keysets with the flag validateKeysetsOnParsing.");     // Catch: zzagk -> L47
        throw new GeneralSecurityException(r42.toString());     // Catch: zzagk -> L47
    L39:
        if (r4 == true) goto L44;
        throw new GeneralSecurityException("Primary key id not found in keyset, and Tink is configured to reject such keysets with the flag validateKeysetsOnParsing.");     // Catch: zzagk -> L47
    L44:
        return new zzsj(r73, new HashMap());
    L46:
        throw new GeneralSecurityException("empty keyset");     // Catch: zzagk -> L47
    L47:
        zzsj$$ExternalSyntheticBUOutline1.m("invalid keyset");
        return null;
    }

    private static boolean zzh(zzsc r1) {
        if (r1.zzb(zzvz.class) != null) goto L6;
        return true;
    L6:
        return false;
    }

    private static List zzi(zzwy r12, zzsc r13) {
        ArrayList r1 = new ArrayList(r12.zzc());
        Iterator r2 = r12.zzb().iterator();
    L4:
        if (r2.hasNext() == false) goto L30;
        zzwx r3 = (zzwx) r2.next();
        int r7 = r3.zzc();
        zzsp r0 = zzj(r3);     // Catch: GeneralSecurityException -> L9
        zzsq r6 = (zzsq) r13.zzb(zzsq.class);     // Catch: GeneralSecurityException -> L9
        if (r6 == null) goto L12;
        zzse r02 = r6.zza(r0, zzsy.zza());     // Catch: GeneralSecurityException -> L9
        boolean r9 = false;
    L17:
        if (zzh(r13) == false) goto L23;
        if (zzk(r3.zzi()) == true) goto L23;
        zzsj$$ExternalSyntheticBUOutline1.m("Parsing of a single key failed (wrong status) and Tink is configured via validateKeysetsOnParsing to reject such keysets.");
        return null;
    L23:
        int r32 = r3.zzi();
        if (r7 != r12.zza()) goto L26;
        boolean r8 = true;
    L27:
        r1.add(new zzsi(r02, r32, r7, r8, r9, zzsi.zzg(), null));
        goto L4
    L26:
        r8 = false;
        goto L27
    L12:
        throw new GeneralSecurityException("Passed in configuration cannot be used to serialize into proto keyset format.");     // Catch: GeneralSecurityException -> L9
    L9:
        e = move-exception;
        if (zzh(r13) == true) goto L28;
        r02 = new zzui(zzj(r3), zzsy.zza());
        r9 = true;
        goto L17
    L28:
        throw e;
    L30:
        return Collections.unmodifiableList(r1);
    }

    private static zzsp zzj(zzwx r9) {
        int r0 = r9.zzc();
        if (r9.zzj() != 5) goto L5;
        Integer r02 = null;
    L6:
        String r1 = r9.zzb().zza();
        zzaer r3 = r9.zzb().zzb();
        int r4 = r9.zzb().zzi() - 2;
        if (r4 == 0) goto L18;
        if (r4 == 1) goto L17;
        if (r4 == 2) goto L16;
        if (r4 == 3) goto L15;
        if (r4 != 4) goto L14;
        zzsn r42 = zzsn.zze;
    L19:
        int r92 = r9.zzj() - 2;
        if (r92 == 0) goto L33;
        if (r92 == 1) goto L32;
        if (r92 == 2) goto L31;
        if (r92 == 3) goto L30;
        if (r92 == 4) goto L29;
        if (r92 != 5) goto L28;
        zzso r93 = zzso.zzf;
    L35:
        return zzsp.zza(r1, r3, r42, r93, r02);
    L28:
        throw new GeneralSecurityException("Unknown OutputPrefixType: ".concat(Integer.toString(r92)));
    L29:
        r93 = zzso.zze;
        goto L35
    L30:
        r93 = zzso.zzd;
        goto L35
    L31:
        r93 = zzso.zzc;
        goto L35
    L32:
        r93 = zzso.zzb;
        goto L35
    L33:
        r93 = zzso.zza;
        goto L35
    L14:
        throw new GeneralSecurityException("Unknown KeyMaterialType: ".concat(Integer.toString(r4)));
    L15:
        r42 = zzsn.zzd;
        goto L19
    L16:
        r42 = zzsn.zzc;
        goto L19
    L17:
        r42 = zzsn.zzb;
        goto L19
    L18:
        r42 = zzsn.zza;
        goto L19
    L5:
        r02 = Integer.valueOf(r0);
        goto L6
    }

    private static boolean zzk(int r2) {
        int r22 = r2 - 2;
        if (r22 != 1) goto L5;
    L10:
        return true;
    L5:
        if (r22 == 2) goto L10;
        if (r22 == 3) goto L10;
        return false;
    }

    public final String toString() {
        zzwy r4 = zza();
        int r0 = zzta.$r8$clinit;
        zzwz r02 = zzxc.zza();
        r02.zza(r4.zza());
        Iterator r42 = r4.zzb().iterator();
    L4:
        if (r42.hasNext() == false) goto L7;
        zzwx r1 = (zzwx) r42.next();
        zzxa r2 = zzxb.zza();
        r2.zza(r1.zzb().zza());
        r2.zzc(r1.zzi());
        r2.zzd(r1.zzj());
        r2.zzb(r1.zzc());
        r02.zzb((zzxb) r2.zzp());
        goto L4
    L7:
        return ((zzxc) r02.zzp()).toString();
    }

    public final zzwy zza() {
        zzsc r0 = zzsx.zza();     // Catch: GeneralSecurityException -> L57
        zzwv r1 = zzwy.zze();     // Catch: GeneralSecurityException -> L57
        Iterator r2 = this.zza.iterator();     // Catch: GeneralSecurityException -> L57
    L4:
        if (r2.hasNext() == false) goto L56;
        zzsi r3 = (zzsi) r2.next();     // Catch: GeneralSecurityException -> L57
        zzse r4 = r3.zza();     // Catch: GeneralSecurityException -> L57
        int r5 = r3.zzh();     // Catch: GeneralSecurityException -> L57
        int r6 = r3.zzc();     // Catch: GeneralSecurityException -> L57
        zzsq r7 = (zzsq) r0.zzb(zzsq.class);     // Catch: GeneralSecurityException -> L57
        if (r7 == null) goto L54;
        zzsp r72 = r7.zzb(r4, zzsy.zza());     // Catch: GeneralSecurityException -> L57
        Integer r42 = r4.zzb();     // Catch: GeneralSecurityException -> L57
        if (r42 != null) goto L10;
    L14:
        zzww r43 = zzwx.zzd();     // Catch: GeneralSecurityException -> L57
        zzwq r8 = zzwr.zzc();     // Catch: GeneralSecurityException -> L57
        r8.zza(r72.zzb());     // Catch: GeneralSecurityException -> L57
        r8.zzb(r72.zzc());     // Catch: GeneralSecurityException -> L57
        zzsn r9 = r72.zzd();     // Catch: GeneralSecurityException -> L57
        int r12 = 6;
        if (r9.equals(zzsn.zza) == false) goto L18;
        int r92 = 2;
    L31:
        r8.zzc(r92);     // Catch: GeneralSecurityException -> L57
        r43.zza(r8);     // Catch: GeneralSecurityException -> L57
        r43.zzc(r5);     // Catch: GeneralSecurityException -> L57
        r43.zzb(r6);     // Catch: GeneralSecurityException -> L57
        zzso r52 = r72.zze();     // Catch: GeneralSecurityException -> L57
        if (r52.equals(zzso.zza) == false) goto L35;
        r12 = 2;
    L50:
        r43.zzd(r12);     // Catch: GeneralSecurityException -> L57
        r1.zzb((zzwx) r43.zzp());     // Catch: GeneralSecurityException -> L57
        if (r3.zzd() == false) goto L4;
        r1.zza(r3.zzc());     // Catch: GeneralSecurityException -> L57
        goto L4
    L35:
        if (r52 != zzso.zzb) goto L37;
        r12 = 3;
        goto L50
    L37:
        if (r52 != zzso.zzc) goto L39;
        r12 = 4;
        goto L50
    L39:
        if (r52 != zzso.zzd) goto L41;
        r12 = 5;
        goto L50
    L41:
        if (r52 == zzso.zze) goto L50;
        if (r52 != zzso.zzf) goto L46;
        r12 = 7;
        goto L50
    L46:
        throw new GeneralSecurityException("Unknown OutputPrefixType: ".concat(r52.toString()));     // Catch: GeneralSecurityException -> L57
    L18:
        if (r9 != zzsn.zzb) goto L20;
        r92 = 3;
        goto L31
    L20:
        if (r9 != zzsn.zzc) goto L22;
        r92 = 4;
        goto L31
    L22:
        if (r9 != zzsn.zzd) goto L24;
        r92 = 5;
        goto L31
    L24:
        if (r9 != zzsn.zze) goto L27;
        r92 = 6;
        goto L31
    L27:
        throw new GeneralSecurityException("Unknown KeyMaterialType: ".concat(r9.toString()));     // Catch: GeneralSecurityException -> L57
    L10:
        if (r42.intValue() == r6) goto L14;
        throw new GeneralSecurityException("Wrong ID set for key with ID requirement");     // Catch: GeneralSecurityException -> L57
    L54:
        throw new GeneralSecurityException("Passed in configuration cannot be used to parse and serialize proto keysets.");     // Catch: GeneralSecurityException -> L57
    L56:
        return (zzwy) r1.zzp();
    L57:
        e = move-exception;
        throw new zzwb(e);
    }

    public final zzsi zzb() {
        Iterator r3 = this.zza.iterator();
    L4:
        if (r3.hasNext() == false) goto L14;
        zzsi r0 = (zzsi) r3.next();
        if (r0 == null) goto L4;
        if (r0.zzd() == false) goto L4;
        if (r0.zzb() != zzsg.zza) goto L12;
        return r0;
    L12:
        Segment$$ExternalSyntheticBUOutline1.m("Keyset has primary which isn't enabled");
        return null;
    L14:
        Segment$$ExternalSyntheticBUOutline1.m("Keyset has no valid primary");
        return null;
    }

    @Override // com.google.android.recaptcha.internal.zzsk
    public final int zzc() {
        return this.zza.size();
    }

    public final zzsi zzd(int r5) {
        if (r5 >= 0) goto L5;
    L16:
        int r4 = zzc();
        int r1 = String.valueOf(r5).length();
        zzxk$$ExternalSyntheticBUOutline0.m((r1 + 34) + String.valueOf(r4).length(), "Invalid index ", r5, " for keyset of size ", r4);
        return null;
    L5:
        if (r5 >= zzc()) goto L16;
        List r42 = this.zza;
        zzsi r12 = (zzsi) r42.get(r5);
        if (zzk(r12.zzh()) == true) goto L9;
        zzsj$$ExternalSyntheticBUOutline0.m(String.valueOf(r5).length() + 42, "Keyset-Entry at position ", r5, " has wrong status");
        return null;
    L9:
        if (r12.zzf() == false) goto L11;
        zzsj$$ExternalSyntheticBUOutline0.m(String.valueOf(r5).length() + 48, "Keyset-Entry at position ", r5, " didn't parse correctly");
        return null;
    L11:
        return (zzsi) r42.get(r5);
    }

    @Override // com.google.android.recaptcha.internal.zzsk
    public final zzsb zze(Class r1) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.zzb.get(r1));
        return null;
    }

    public final Object zzg(zzsc r10, Class r11) {
        zzwy r0 = zza();
        int r1 = zzta.$r8$clinit;
        int r12 = r0.zza();
        Iterator r02 = r0.zzb().iterator();
        int r2 = 0;
        int r3 = 0;
        boolean r4 = false;
    L4:
        if (r02.hasNext() == false) goto L26;
        zzwx r5 = (zzwx) r02.next();
        if (r5.zzi() != 3) goto L4;
        if (r5.zza() == false) goto L25;
        if (r5.zzj() == 2) goto L23;
        if (r5.zzi() == 2) goto L21;
        if (r5.zzc() != r12) goto L19;
        if (r4 == true) goto L17;
        r4 = true;
        goto L19
    L17:
        zzsj$$ExternalSyntheticBUOutline1.m("keyset contains multiple primary keys");
        return null;
    L19:
        r3 = r3 + 1;
        goto L4
    L21:
        throw new GeneralSecurityException(String.format("key %d has unknown status", new Object[]{Integer.valueOf(r5.zzc())}));
    L23:
        throw new GeneralSecurityException(String.format("key %d has unknown prefix", new Object[]{Integer.valueOf(r5.zzc())}));
    L25:
        throw new GeneralSecurityException(String.format("key %d has no key data", new Object[]{Integer.valueOf(r5.zzc())}));
    L26:
        if (r3 != 0) goto L28;
        zzsj$$ExternalSyntheticBUOutline1.m("keyset must contain at least one ENABLED key");
        return null;
    L28:
        if (r2 >= zzc()) goto L42;
        List r03 = this.zza;
        if (((zzsi) r03.get(r2)).zzf() == true) goto L35;
        if (zzk(((zzsi) r03.get(r2)).zzh()) == false) goto L35;
        r2 = r2 + 1;
    L35:
        zzse r9 = ((zzsi) r03.get(r2)).zze();
        if ((r9 instanceof zzui) == false) goto L38;
        String r92 = ((zzui) r9).zzc(zzsy.zza()).zzb();
    L39:
        StringBuilder r04 = new StringBuilder(((String.valueOf(r2).length() + 44) + String.valueOf(r92).length()) + 32);
        r04.append("Key parsing of key with index ");
        r04.append(r2);
        r04.append(" and type_url ");
        r04.append(r92);
        r04.append(" failed, unable to get primitive");
        throw new GeneralSecurityException(r04.toString());
    L38:
        r92 = "(unknown)";
        goto L39
    L42:
        return r10.zza(this, r11);
    }
}
