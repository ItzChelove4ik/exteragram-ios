package j$.time.format;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.math.BigInteger;
import okhttp3.internal.connection.RealConnection;
import org.mvel2.asm.signature.SignatureVisitor;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public class h implements e {
    public static final long[] f = null;

    /* renamed from: a, reason: collision with root package name */
    public final j$.time.temporal.p f383a;

    /* renamed from: b, reason: collision with root package name */
    public final int f384b;

    /* renamed from: c, reason: collision with root package name */
    public final int f385c;
    public final a0 d;
    public final int e;

    static {
        f = new long[]{0, 10, 100, 1000, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, 100000, 1000000, 10000000, 100000000, 1000000000, RealConnection.IDLE_CONNECTION_HEALTHY_NS};
    }

    public h(j$.time.temporal.p r1, int r2, int r3, a0 r4) {
        this.f383a = r1;
        this.f384b = r2;
        this.f385c = r3;
        this.d = r4;
        this.e = 0;
    }

    public h(j$.time.temporal.p r1, int r2, int r3, a0 r4, int r5) {
        this.f383a = r1;
        this.f384b = r2;
        this.f385c = r3;
        this.d = r4;
        this.e = r5;
    }

    @Override // j$.time.format.e
    public int D(r r28, CharSequence r29, int r30) {
        int r3 = r29.length();
        if (r30 == r3) goto L5;
        char r4 = r29.charAt(r30);
        DateTimeFormatter r5 = r28.f403a;
        r5.f372c.getClass();
        int r8 = this.f385c;
        a0 r9 = this.d;
        int r10 = this.f384b;
        int r11 = 0;
        boolean r12 = true;
        if (r4 != '+') goto L23;
        boolean r42 = r28.f405c;
        if (r10 != r8) goto L11;
        boolean r6 = true;
    L12:
        int r13 = r9.ordinal();
        if (r13 == 0) goto L19;
        if (r13 == 1) goto L20;
        if (r13 == 4) goto L20;
        if (r42 == true) goto L22;
        if (r6 == false) goto L20;
    L22:
        return ~r30;
    L20:
        int r43 = r30 + 1;
        boolean r2 = false;
        boolean r62 = true;
    L47:
        if (r28.f405c == false) goto L49;
    L52:
        int r7 = r10;
    L53:
        int r132 = r43 + r7;
        if (r132 <= r3) goto L58;
        return ~r43;
    L58:
        if (r28.f405c == false) goto L60;
    L63:
        int r14 = this.e;
        int r16 = Math.max(r14, 0) + r8;
    L64:
        BigInteger r19 = null;
        if (r11 >= 2) goto L91;
        int r82 = Math.min(r43 + r16, r3);
        boolean r162 = r12;
        long r20 = 0;
        int r122 = r43;
    L67:
        if (r122 >= r82) goto L85;
        int r22 = r122 + 1;
        char r23 = r29.charAt(r122);
        boolean r24 = r2;
        r5.f372c.getClass();
        int r25 = r23 - '0';
        int r232 = r3;
        if (r25 < 0) goto L72;
        if (r25 > 9) goto L72;
    L73:
        if (r25 < 0) goto L74;
        if ((r22 - r43) <= 18) goto L83;
        if (r19 != null) goto L82;
        r19 = BigInteger.valueOf(r20);
    L82:
        DateTimeFormatter r252 = r5;
        boolean r26 = r62;
        r19 = r19.multiply(BigInteger.TEN).add(BigInteger.valueOf(r25));
    L84:
        r122 = r22;
        r3 = r232;
        r2 = r24;
        r5 = r252;
        r62 = r26;
        goto L67
    L83:
        r252 = r5;
        r26 = r62;
        r20 = (r20 * 10) + r25;
        goto L84
    L74:
        if (r122 < r132) goto L76;
    L77:
        DateTimeFormatter r253 = r5;
        boolean r262 = r62;
        if (r14 <= 0) goto L89;
        if (r11 != 0) goto L89;
        int r27 = Math.max(r7, (r122 - r43) - r14);
        r11 = r11 + 1;
        r12 = r162;
        r3 = r232;
        r5 = r253;
        r62 = r262;
        r16 = r27;
        r2 = r24;
    L89:
        int r52 = r122;
        long r63 = r20;
    L90:
        BigInteger r210 = r19;
        if (r24 == false) goto L109;
        if (r210 == null) goto L102;
        if (r210.equals(BigInteger.ZERO) == true) goto L97;
    L100:
        r210 = r210.negate();
    L120:
        if (r210 == null) goto L127;
        if (r210.bitLength() <= 63) goto L125;
        r210 = r210.divide(BigInteger.TEN);
        r52 = r52 - 1;
    L125:
        return r28.f(this.f383a, r210.longValue(), r43, r52);
    L127:
        return r28.f(this.f383a, r63, r43, r52);
    L97:
        if (r28.f405c == false) goto L100;
        return ~(r43 - 1);
    L102:
        if (r63 == 0) goto L104;
    L107:
        r63 = -r63;
        goto L120
    L104:
        if (r28.f405c == false) goto L107;
        return ~(r43 - 1);
    L109:
        if (r9 != a0.EXCEEDS_PAD) goto L120;
        if (r28.f405c == false) goto L120;
        int r32 = r52 - r43;
        if (r262 == false) goto L117;
        if (r32 > r10) goto L120;
        return ~(r43 - 1);
    L117:
        if (r32 <= r10) goto L120;
        return ~r43;
    L76:
        return ~r43;
    L72:
        r25 = -1;
        goto L73
    L85:
        r24 = r2;
        r232 = r3;
        goto L77
    L91:
        r24 = r2;
        r262 = r62;
        r52 = r43;
        r63 = 0;
        goto L90
    L60:
        if (a(r28) == true) goto L63;
        r8 = 9;
        goto L63
    L49:
        if (a(r28) == true) goto L52;
        r7 = 1;
        goto L53
    L19:
        if (r42 == true) goto L22;
    L11:
        r6 = false;
        goto L12
    L23:
        r5.f372c.getClass();
        if (r4 != '-') goto L40;
        boolean r44 = r28.f405c;
        if (r10 != r8) goto L28;
        boolean r64 = true;
    L29:
        int r133 = r9.ordinal();
        if (r133 == 0) goto L38;
        if (r133 == 1) goto L38;
        if (r133 == 4) goto L38;
        if (r44 == true) goto L37;
        if (r64 == false) goto L38;
    L37:
        return ~r30;
    L38:
        r43 = r30 + 1;
        r62 = false;
        r2 = true;
        goto L47
    L28:
        r64 = false;
        goto L29
    L40:
        if (r9 == a0.ALWAYS) goto L42;
    L45:
        r43 = r30;
        r2 = false;
        r62 = false;
        goto L47
    L42:
        if (r28.f405c == false) goto L45;
        return ~r30;
    L5:
        return ~r30;
    }

    public boolean a(r r2) {
        int r0 = this.e;
        if (r0 == (-1)) goto L12;
        if (r0 > 0) goto L6;
        return false;
    L6:
        if (this.f384b == this.f385c) goto L8;
        return false;
    L8:
        if (this.d == a0.NOT_NEGATIVE) goto L16;
        return false;
    L16:
        return true;
    L12:
        return true;
    }

    public h b() {
        if (this.e != (-1)) goto L5;
        return this;
    L5:
        a0 r6 = this.d;
        return new h(this.f383a, this.f384b, this.f385c, r6, -1);
    }

    public h c(int r7) {
        int r5 = this.e + r7;
        return new h(this.f383a, this.f384b, this.f385c, this.d, r5);
    }

    @Override // j$.time.format.e
    public boolean s(t r11, StringBuilder r12) {
        j$.time.temporal.p r0 = this.f383a;
        Long r1 = r11.a(r0);
        int r2 = 0;
        if (r1 != null) goto L5;
        return false;
    L5:
        long r3 = r1.longValue();
        x r112 = r11.f410b.f372c;
        if (r3 != Long.MIN_VALUE) goto L8;
        String r13 = "9223372036854775808";
    L9:
        int r5 = r13.length();
        int r8 = this.f385c;
        if (r5 > r8) goto L40;
        r112.getClass();
        int r52 = this.f384b;
        a0 r10 = this.d;
        if (r3 < 0) goto L23;
        int r102 = b.f376a[r10.ordinal()];
        if (r102 == 1) goto L19;
        if (r102 != 2) goto L35;
        r12.append(SignatureVisitor.EXTENDS);
    L35:
        if (r2 >= (r52 - r13.length())) goto L37;
        r12.append('0');
        r2 = r2 + 1;
        goto L35
    L37:
        r12.append(r13);
        return true;
    L19:
        if (r52 >= 19) goto L35;
        if (r3 < f[r52]) goto L35;
        r12.append(SignatureVisitor.EXTENDS);
        goto L35
    L23:
        int r103 = b.f376a[r10.ordinal()];
        if (r103 == 1) goto L33;
        if (r103 == 2) goto L33;
        if (r103 == 3) goto L33;
        if (r103 != 4) goto L35;
        throw new j$.time.b("Field " + r0 + " cannot be printed as the value " + r3 + " cannot be negative according to the SignStyle");
    L33:
        r12.append(SignatureVisitor.SUPER);
        goto L35
    L40:
        throw new j$.time.b("Field " + r0 + " cannot be printed as the value " + r3 + " exceeds the maximum print width of " + r8);
    L8:
        r13 = Long.toString(Math.abs(r3));
        goto L9
    }

    public String toString() {
        int r1 = this.f385c;
        j$.time.temporal.p r4 = this.f383a;
        a0 r5 = this.d;
        int r7 = this.f384b;
        if (r7 != 1) goto L11;
        if (r1 != 19) goto L11;
        if (r5 != a0.NORMAL) goto L11;
        return "Value(" + r4 + ")";
    L11:
        if (r7 != r1) goto L17;
        if (r5 != a0.NOT_NEGATIVE) goto L17;
        return "Value(" + r4 + "," + r7 + ")";
    L17:
        return "Value(" + r4 + "," + r7 + "," + r1 + "," + r5 + ")";
    }
}
