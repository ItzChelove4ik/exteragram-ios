package com.google.android.recaptcha.internal;

import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.AbstractCollection;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.RandomAccess;
import okio.Buffer$$ExternalSyntheticBUOutline2;

/* loaded from: classes5.dex */
public final class zzar {
    public int zza;
    private Object zzb;
    private long zzc;
    private double zzd;
    private zzv zze;
    private List zzf;
    private zzaj zzg;

    private zzar() {
        this.zza = 1;
    }

    public static zzar zza(Object r10) {
        zzar r0 = new zzar();
        int[] r1 = {1867107722, 219499797, 1647318768, 791628709, 1929020072, -436492773, 156903819, 1390543437, 216220853};
        int r2 = r1[0];
        int r3 = r1[1];
        int r4 = r1[2];
        int r5 = r1[3];
        int r6 = r1[4];
        int r7 = r1[5];
        int r8 = r1[6];
        int r12 = r1[7];
        int r82 = r8 + ((((r3 & (~r2)) | r4) + ((r2 & r5) | r6)) - r7);
        r0.zzr();
        r0.zza = (r12 % 216220853) ^ r82;
        r0.zzb = r10;
        return r0;
    }

    public static zzar zzb(long r10) {
        zzar r0 = new zzar();
        int[] r1 = {1539942439, 1535257185, 1094537855, 446310416, 545003193, -1960816486, 411522957, 1143565421, 981914693};
        int r2 = r1[0];
        int r3 = r1[1];
        int r4 = r1[2];
        int r5 = r1[3];
        int r6 = r1[4];
        int r7 = r1[5];
        int r8 = r1[6];
        int r12 = r1[7];
        int r82 = r8 + ((((r3 & (~r2)) | r4) + ((r2 & r5) | r6)) - r7);
        r0.zzr();
        r0.zza = (r12 % 981914693) ^ r82;
        r0.zzc = r10;
        return r0;
    }

    public static zzar zzc(double r10) {
        zzar r0 = new zzar();
        int[] r1 = {978587665, 1228171534, 1025392329, 1075859846, 983056097, -1399000571, 190113083, 1723578341, 672563970};
        int r2 = r1[0];
        int r3 = r1[1];
        int r4 = r1[2];
        int r5 = r1[3];
        int r6 = r1[4];
        int r7 = r1[5];
        int r8 = r1[6];
        int r12 = r1[7];
        int r82 = r8 + ((((r3 & (~r2)) | r4) + ((r2 & r5) | r6)) - r7);
        r0.zzr();
        r0.zza = (r12 % 672563970) ^ r82;
        r0.zzd = r10;
        return r0;
    }

    public static zzar zzd(zzv r10) {
        zzar r0 = new zzar();
        int[] r1 = {1910210050, 1342515460, 1453014483, 168034308, 1540440520, -994065400, 318322042, 817572761, 14933990};
        int r2 = r1[0];
        int r3 = r1[1];
        int r4 = r1[2];
        int r5 = r1[3];
        int r6 = r1[4];
        int r7 = r1[5];
        int r8 = r1[6];
        int r12 = r1[7];
        int r82 = r8 + ((((r3 & (~r2)) | r4) + ((r2 & r5) | r6)) - r7);
        r0.zzr();
        r0.zza = (r12 % 14933990) ^ r82;
        r0.zze = r10;
        return r0;
    }

    public static zzar zze(List r10) {
        zzar r0 = new zzar();
        int[] r1 = {379366797, 35537464, 1403037965, -2078358863, -1757307767, -386763992, 522971726, 1554725062, 997276125};
        int r2 = r1[0];
        int r3 = r1[1];
        int r4 = r1[2];
        int r5 = r1[3];
        int r6 = r1[4];
        int r7 = r1[5];
        int r8 = r1[6];
        int r12 = r1[7];
        int r82 = r8 + ((((r3 & (~r2)) | r4) + ((r2 & r5) | r6)) - r7);
        r0.zzr();
        r0.zza = (r12 % 997276125) ^ r82;
        r0.zzf = r10;
        return r0;
    }

    public static zzar zzf(zzaj r10) {
        zzar r0 = new zzar();
        int[] r1 = {2021303708, 824774792, 51514445, 1009340548, 257220717, 1682859529, 597010431, 1024027583, 198628789};
        int r2 = r1[0];
        int r3 = r1[1];
        int r4 = r1[2];
        int r5 = r1[3];
        int r6 = r1[4];
        int r7 = r1[5];
        int r8 = r1[6];
        int r12 = r1[7];
        int r82 = r8 + ((((r3 & (~r2)) | r4) + ((r2 & r5) | r6)) - r7);
        r0.zzr();
        r0.zza = (r12 % 198628789) ^ r82;
        r0.zzg = r10;
        return r0;
    }

    public static zzar zzg(Object r4) {
        if ((r4 instanceof Long) == false) goto L7;
        return zzb(((Long) r4).longValue());
    L7:
        if ((r4 instanceof Boolean) == false) goto L15;
        if (true == ((Boolean) r4).booleanValue()) goto L11;
        long r0 = 0;
    L13:
        return zzb(r0);
    L11:
        r0 = 1;
        goto L13
    L15:
        if ((r4 instanceof Integer) == false) goto L19;
        return zzb(((Integer) r4).intValue());
    L19:
        if ((r4 instanceof Double) == false) goto L23;
        return zzc(((Double) r4).doubleValue());
    L23:
        if ((r4 instanceof Float) == false) goto L27;
        return zzc(((Float) r4).floatValue());
    L27:
        if ((r4 instanceof Short) == false) goto L31;
        return zzb(((Short) r4).shortValue());
    L31:
        if ((r4 instanceof Byte) == false) goto L35;
        return zzb(((Byte) r4).byteValue());
    L35:
        if ((r4 instanceof zzv) == false) goto L39;
        return zzd((zzv) r4);
    L39:
        if ((r4 instanceof String) == false) goto L43;
        return zzd(zzv.zzf((String) r4));
    L43:
        if ((r4 instanceof ArrayList) == false) goto L50;
        ArrayList r02 = new ArrayList();
        ArrayList r42 = (ArrayList) r4;
        int r1 = r42.size();
        int r2 = 0;
    L45:
        if (r2 >= r1) goto L48;
        r02.add(zzg(r42.get(r2)));
        r2 = r2 + 1;
        goto L45
    L48:
        return zze(r02);
    L50:
        return zza(r4);
    }

    public static zzar zzj(zzar r9) {
        int[] r0 = {368046387, 1524073695, 1753971274, -1235975019, -1392305918, 807149497, 27679574, 1740033621, 135813159};
        int r1 = r0[0];
        int r2 = r0[1];
        int r3 = r0[2];
        int r4 = r0[3];
        int r5 = r0[4];
        int r6 = r0[5];
        int r7 = r0[6];
        int r02 = r0[7];
        int r72 = r7 + ((((r2 & (~r1)) | r3) + ((r1 & r4) | r5)) - r6);
        int r03 = r02 % 135813159;
        int r12 = r9.zza;     // Catch: zzao -> L28
        int r04 = (r03 ^ r72) + r12;
        if (r12 == 0) goto L27;
        switch(r04) {
            case 0: goto L26;
            case 1: goto L24;
            case 2: goto L22;
            case 3: goto L20;
            case 4: goto L13;
            case 5: goto L12;
            case 6: goto L10;
            default: goto L7;
        };
    L7:
        Buffer$$ExternalSyntheticBUOutline2.m(zzt.zza("HkezqgQcPni/TE/NwjgYPC5H6Q2JRdEp275wOg=="));
        return null;
    L13:
        ArrayList r05 = new ArrayList();     // Catch: zzao -> L28
        Iterator r92 = r9.zzo().iterator();     // Catch: zzao -> L28
    L15:
        if (r92.hasNext() == false) goto L18;
        r05.add(zzj((zzar) r92.next()));     // Catch: zzao -> L28
        goto L15
    L18:
        return zze(r05);
    L10:
        return zzc(r9.zzq());
    L12:
        return zzf(r9.zzp());
    L20:
        return zzd(r9.zzn());
    L22:
        return zzb(r9.zzm());
    L24:
        return zza(r9.zzl());
    L26:
        return new zzar();
    L27:
        throw null;     // Catch: zzao -> L28
    L28:
        e = move-exception;
        throw new AssertionError(zzt.zza("CEiv6BFfPnitUE+D"), e);
    }

    private final void zzr() {
        this.zza = 1;
        this.zzc = 0;
        this.zzb = null;
        this.zze = null;
        this.zzf = null;
        this.zzg = null;
    }

    private final void zzs(int r1) {
        if (r1 != this.zza) goto L6;
        return;
    L6:
        throw new zzao();
    }

    public final Object zzh() {
        int[] r0 = {1946855866, 1570063841, 244151099, -250444080, -1599884485, 118034294, 5125130, 1535589735, 713475737};
        int r1 = r0[0];
        int r2 = r0[1];
        int r3 = r0[2];
        int r4 = r0[3];
        int r5 = r0[4];
        int r6 = r0[5];
        int r7 = r0[6];
        int r02 = r0[7];
        int r72 = r7 + ((((r2 & (~r1)) | r3) + ((r1 & r4) | r5)) - r6);
        int r12 = this.zza;
        int r03 = ((r02 % 713475737) ^ r72) + r12;
        if (r12 == 0) goto L22;
        switch(r03) {
            case 0: goto L21;
            case 1: goto L19;
            case 2: goto L17;
            case 3: goto L15;
            case 4: goto L9;
            case 5: goto L21;
            case 6: goto L8;
            default: goto L5;
        };
    L5:
        Buffer$$ExternalSyntheticBUOutline2.m(zzt.zza("HkezqgQcPni/TE/NwjgYPC5H6Q2JRdEp275wOg=="));
        return null;
    L9:
        ArrayList r04 = new ArrayList();
        Iterator r9 = zzo().iterator();
    L11:
        if (r9.hasNext() == false) goto L13;
        r04.add(((zzar) r9.next()).zzh());
        goto L11
    L13:
        return r04;
    L8:
        return Double.valueOf(zzq());
    L15:
        return zzn().zza();
    L17:
        return Long.valueOf(zzm());
    L19:
        return zzl();
    L21:
        throw new zzao();
    L22:
        throw null;
    }

    public final Object zzi(Class r20) {
        int r2 = ((((~97611886) & 164954443) | 114310280) + ((97611886 & 151322963) | 281583160)) - 463131379;
        int r3 = 1657262432 % 1573177440;
        int r4 = ((((~1700313522) & 591806470) | 140250211) + ((1700313522 & 587350565) | 347441897)) - 1065422613;
        int r5 = 1418468232 % 12692331;
        int r8 = this.zza;
        int r6 = ((((((~1340321698) & 1570808991) | 704716576) + ((1340321698 & (-174021889)) | (-1570942368))) - 718310630) ^ (1426869906 % 213153954)) + r8;
        if (r8 == 0) goto L163;
        int r42 = r4 ^ r5;
        int r22 = r2 ^ r3;
        Class r52 = Double.TYPE;
        Class r9 = Short.TYPE;
        Class r11 = Byte.TYPE;
        Class r13 = Long.TYPE;
        Class r15 = Integer.TYPE;
        Class r7 = Float.TYPE;
        switch(r6) {
            case 0: goto L161;
            case 1: goto L159;
            case 2: goto L98;
            case 3: goto L84;
            case 4: goto L55;
            case 5: goto L53;
            case 6: goto L8;
            default: goto L6;
        };
    L6:
        Buffer$$ExternalSyntheticBUOutline2.m(zzt.zza("HkezqgQcPni/TE/NwjgYPC5H6Q2JRdEp275wOg=="));
        return null;
    L8:
        double r43 = zzq();
        if (r20.equals(Float.class) == true) goto L51;
        if (r20.equals(r7) == true) goto L51;
        if (r20.equals(Integer.class) == true) goto L49;
        if (r20.equals(r15) == true) goto L49;
        if (r20.equals(Long.class) == true) goto L47;
        if (r20.equals(r13) == true) goto L47;
        if (r20.equals(Byte.class) == true) goto L45;
        if (r20.equals(r11) == true) goto L45;
        if (r20.equals(Short.class) == true) goto L43;
        if (r20.equals(r9) == true) goto L43;
        if (r20.equals(Double.class) == true) goto L41;
        if (r20.equals(r52) == true) goto L41;
        if (r20.equals(Object.class) == false) goto L161;
    L41:
        return Double.valueOf(r43);
    L43:
        return Short.valueOf((short) ((((int) r43) << r22) >> r22));
    L45:
        return Byte.valueOf((byte) ((((int) r43) << r42) >> r42));
    L47:
        return Long.valueOf((long) r43);
    L49:
        return Integer.valueOf((int) r43);
    L51:
        return Float.valueOf((float) r43);
    L84:
        zzv r1 = zzn();
        if (r20.equals(zzv.class) == false) goto L88;
        return r1;
    L88:
        if (r20.equals(Object.class) == true) goto L97;
        if (r20.equals(String.class) == true) goto L97;
        if (r20.equals(byte[].class) == false) goto L161;
        return r1.zza();
    L97:
        return r1.zzc();
    L98:
        long r53 = zzm();
        if (r20.equals(Byte.class) == true) goto L157;
        if (r20.equals(r11) == true) goto L157;
        if (r20.equals(Short.class) == true) goto L155;
        if (r20.equals(r9) == true) goto L155;
        if (r20.equals(Integer.class) == true) goto L153;
        if (r20.equals(r15) == true) goto L153;
        if (r20.equals(Long.class) == true) goto L151;
        if (r20.equals(r13) == true) goto L151;
        if (r20.equals(Float.class) == true) goto L149;
        if (r20.equals(r7) == true) goto L149;
        if (r20.equals(Double.class) == true) goto L147;
        if (r20.equals(r52) == true) goto L147;
        if (r20.equals(Boolean.class) == true) goto L141;
        if (r20.equals(Boolean.TYPE) == true) goto L141;
        if (r20.equals(Character.class) == true) goto L157;
        if (r20.equals(Character.TYPE) == true) goto L157;
        if (r20.equals(Object.class) == false) goto L161;
    L141:
        if (r53 == 0) goto L143;
        boolean r32 = true;
    L145:
        return Boolean.valueOf(r32);
    L143:
        r32 = false;
    L147:
        return Double.valueOf(r53);
    L149:
        return Float.valueOf(r53);
    L151:
        return Long.valueOf(r53);
    L153:
        return Integer.valueOf(Math.toIntExact(r53));
    L155:
        return Short.valueOf((short) ((((int) r53) << r22) >> r22));
    L157:
        return Byte.valueOf((byte) ((((int) r53) << r42) >> r42));
    L53:
        return zzp();
    L55:
        if (r20.equals(ArrayList.class) == true) goto L83;
        if (r20.equals(Object.class) == true) goto L83;
        if (r20.equals(AbstractList.class) == true) goto L83;
        if (r20.equals(AbstractCollection.class) == true) goto L83;
        if (r20.equals(Serializable.class) == true) goto L83;
        if (r20.equals(Cloneable.class) == true) goto L83;
        if (r20.equals(Iterable.class) == true) goto L83;
        if (r20.equals(Collection.class) == true) goto L83;
        if (r20.equals(List.class) == true) goto L83;
        if (r20.equals(RandomAccess.class) == true) goto L83;
        if (r20.isArray() == false) goto L161;
        List r12 = zzo();
        Class<?> r0 = r20.getComponentType();
        Object r23 = Array.newInstance(r0, r12.size());
        int r33 = 0;
    L79:
        if (r33 >= r12.size()) goto L81;
        Array.set(r23, r33, ((zzar) r12.get(r33)).zzi(r0));
        r33 = r33 + 1;
        goto L79
    L81:
        return r23;
    L83:
        return zzh();
    L159:
        return zzl();
    L161:
        throw new zzao();
    L163:
        throw null;
    }

    public final void zzk(OutputStream r23) {
        long[] r1 = {1096079422, 21345924, 206269241, 1367643268, 1352213041, 1453396557, 419974390, 1998713308, 1456575805};
        int r2 = 0;
        long r3 = r1[0];
        long r6 = r1[1];
        long r8 = r1[2];
        long r10 = r1[3];
        long r12 = r1[4];
        long r14 = r1[5];
        long r16 = r1[6];
        long r18 = r1[7];
        long r162 = r16 + (((((~r3) & r6) | r8) + ((r3 & r10) | r12)) - r14);
        long r182 = r18 % 1456575805;
        int r62 = ((((~1182595892) & 2124317320) | 1268383979) + ((1182595892 & 872718928) | 1132164311)) - (-1060702887);
        int r7 = 1072628238 % 516816850;
        int r82 = ((((~25701533) & 1812499204) | 591223219) + ((25701533 & 1287816772) | 64642538)) - 1887635333;
        int r9 = 1026374844 % 46811558;
        int r102 = this.zza;
        int r4 = ((((((~2105342203) & 2049862401) | 1698333727) + ((2105342203 & (-1643877632)) | (-2049769426))) - 132143960) ^ (1059789835 % 1012707329)) + r102;
        if (r102 == 0) goto L23;
        switch(r4) {
            case 0: goto L21;
            case 1: goto L21;
            case 2: goto L18;
            case 3: goto L16;
            case 4: goto L11;
            case 5: goto L21;
            case 6: goto L6;
            default: goto L15;
        };
    L6:
        double r32 = zzq();
        zzaq r5 = new zzaq(r23, r62 ^ r7);
        long r33 = Double.doubleToRawLongBits(r32);
        ByteBuffer r0 = ByteBuffer.allocate((((((~1514722040) & 82790042) | 437825894) + ((1514722040 & 1441228440) | 1392519490)) - 1911317206) ^ (1238504350 % 1769972));
        r0.order(ByteOrder.LITTLE_ENDIAN);
        r0.putLong(r33);
        byte[] r13 = r0.array();
        int r34 = r13.length;
    L7:
        if (r2 >= r34) goto L9;
        r5.zza(r13[r2]);
        r2 = r2 + 1;
        goto L7
    L9:
        int r02 = r0.array().length;
        return;
    L11:
        List r35 = zzo();
        zzr.zzb(r35.size(), new zzaq(r23, r82 ^ r9), true);
        Iterator r15 = r35.iterator();
    L13:
        if (r15.hasNext() == false) goto L26;
        ((zzar) r15.next()).zzk(r23);
        goto L13
    L26:
        return;
    L16:
        byte[] r36 = zzn().zza;
        zzr.zzb(r36.length * (r162 ^ r182), new zzaq(r23, 0), true);
        r23.write(r36);
        return;
    L18:
        zzr.zzb(zzm(), new zzaq(r23, 1), true);
        return;
    L15:
        return;
    L21:
        throw new zzao();
    L23:
        throw null;
    }

    public final Object zzl() {
        int[] r0 = {636453333, 363983104, 1075208227, 2146014080, 1783382730, -364134701, 775056794, 1564003050, 99885196};
        int r1 = r0[0];
        int r2 = r0[1];
        int r3 = r0[2];
        int r4 = r0[3];
        int r5 = r0[4];
        int r6 = r0[5];
        int r7 = r0[6];
        int r02 = r0[7];
        int r03 = r02 % 99885196;
        zzs(r03 ^ (r7 + ((((r2 & (~r1)) | r3) + ((r1 & r4) | r5)) - r6)));
        return this.zzb;
    }

    public final long zzm() {
        int[] r0 = {30387226, 1173447944, 304523573, 1171344488, 35694692, 2069301473, 854121980, 1541417540, 1245036421};
        int r1 = r0[0];
        int r2 = r0[1];
        int r3 = r0[2];
        int r4 = r0[3];
        int r5 = r0[4];
        int r6 = r0[5];
        int r7 = r0[6];
        int r02 = r0[7];
        int r03 = r02 % 1245036421;
        zzs(r03 ^ (r7 + ((((r2 & (~r1)) | r3) + ((r1 & r4) | r5)) - r6)));
        return this.zzc;
    }

    public final zzv zzn() {
        int[] r0 = {1281944976, 1463473728, 847256033, 1698424322, 948255187, -1554299396, 419914800, 1727952741, 1091714937};
        int r1 = r0[0];
        int r2 = r0[1];
        int r3 = r0[2];
        int r4 = r0[3];
        int r5 = r0[4];
        int r6 = r0[5];
        int r7 = r0[6];
        int r02 = r0[7];
        int r03 = r02 % 1091714937;
        zzs(r03 ^ (r7 + ((((r2 & (~r1)) | r3) + ((r1 & r4) | r5)) - r6)));
        return this.zze;
    }

    public final List zzo() {
        int[] r0 = {1654844049, 956749401, 3147556, 1023856765, 1183918756, -1963614606, 352085974, 955798986, 791251530};
        int r1 = r0[0];
        int r2 = r0[1];
        int r3 = r0[2];
        int r4 = r0[3];
        int r5 = r0[4];
        int r6 = r0[5];
        int r7 = r0[6];
        int r02 = r0[7];
        int r03 = r02 % 791251530;
        zzs(r03 ^ (r7 + ((((r2 & (~r1)) | r3) + ((r1 & r4) | r5)) - r6)));
        return this.zzf;
    }

    public final zzaj zzp() {
        int[] r0 = {138700754, 1311190794, 1098494212, 235277834, 2126148, 2082044642, 778544518, 631340353, 54449299};
        int r1 = r0[0];
        int r2 = r0[1];
        int r3 = r0[2];
        int r4 = r0[3];
        int r5 = r0[4];
        int r6 = r0[5];
        int r7 = r0[6];
        int r02 = r0[7];
        int r03 = r02 % 54449299;
        zzs(r03 ^ (r7 + ((((r2 & (~r1)) | r3) + ((r1 & r4) | r5)) - r6)));
        return this.zzg;
    }

    public final double zzq() {
        int[] r0 = {1360613073, 10730298, 1998148311, 682535208, 744243906, -1215447656, 441767868, 1566369633, 483689685};
        int r1 = r0[0];
        int r2 = r0[1];
        int r3 = r0[2];
        int r4 = r0[3];
        int r5 = r0[4];
        int r6 = r0[5];
        int r7 = r0[6];
        int r02 = r0[7];
        int r03 = r02 % 483689685;
        zzs(r03 ^ (r7 + ((((r2 & (~r1)) | r3) + ((r1 & r4) | r5)) - r6)));
        return this.zzd;
    }
}
