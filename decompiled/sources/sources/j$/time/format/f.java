package j$.time.format;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class f extends h {
    public final boolean g;

    public f(j$.time.temporal.p r7) {
        this(r7, 0, 9, true, 0);
        Objects.requireNonNull(r7, "field");
        j$.time.temporal.s r6 = r7.K();
        if (r6.f466a == r6.f467b) goto L5;
    L7:
        j$.nio.file.b.a(j$.time.c.a("Field must have a fixed set of values: ", r7));
        throw null;
    L5:
        if (r6.f468c != r6.d) goto L7;
    }

    public f(j$.time.temporal.p r7, int r8, int r9, boolean r10, int r11) {
        super(r7, r8, r9, a0.NOT_NEGATIVE, r11);
        this.g = r10;
    }

    @Override // j$.time.format.h, j$.time.format.e
    public final int D(r r13, CharSequence r14, int r15) {
        boolean r0 = r13.f405c;
        DateTimeFormatter r1 = r13.f403a;
        if (r0 == false) goto L5;
    L8:
        int r02 = this.f384b;
    L10:
        if (r13.f405c == false) goto L12;
    L15:
        int r3 = this.f385c;
    L16:
        int r5 = r14.length();
        if (r15 != r5) goto L22;
        if (r02 > 0) goto L20;
    L28:
        return r15;
    L20:
        return ~r15;
    L22:
        if (this.g == false) goto L30;
        char r6 = r14.charAt(r15);
        r1.f372c.getClass();
        if (r6 == '.') goto L29;
        if (r02 <= 0) goto L28;
        return ~r15;
    L29:
        r15 = r15 + 1;
    L30:
        int r10 = r15;
        int r03 = r02 + r10;
        if (r03 > r5) goto L33;
        int r152 = Math.min(r3 + r10, r5);
        int r32 = 0;
        int r11 = r10;
    L35:
        if (r11 >= r152) goto L46;
        int r52 = r11 + 1;
        char r62 = r14.charAt(r11);
        r1.f372c.getClass();
        int r63 = r62 - '0';
        if (r63 < 0) goto L40;
        if (r63 > 9) goto L40;
    L41:
        if (r63 < 0) goto L42;
        r32 = (r32 * 10) + r63;
        r11 = r52;
        goto L35
    L42:
        if (r52 >= r03) goto L46;
        return ~r10;
    L40:
        r63 = -1;
    L46:
        BigDecimal r142 = new BigDecimal(r32).movePointLeft(r11 - r10);
        j$.time.temporal.s r153 = this.f383a.K();
        BigDecimal r04 = BigDecimal.valueOf(r153.f466a);
        return r13.f(this.f383a, r142.multiply(BigDecimal.valueOf(r153.d).subtract(r04).add(BigDecimal.ONE)).setScale(0, RoundingMode.FLOOR).add(r04).longValueExact(), r10, r11);
    L33:
        return ~r10;
    L12:
        if (a(r13) == true) goto L15;
        r3 = 9;
        goto L16
    L5:
        if (a(r13) == true) goto L8;
        r02 = 0;
        goto L10
    }

    @Override // j$.time.format.h
    public final boolean a(r r2) {
        if (r2.f405c == true) goto L5;
        return false;
    L5:
        if (this.f384b == this.f385c) goto L7;
        return false;
    L7:
        if (this.g == true) goto L13;
        return true;
    L13:
        return false;
    }

    @Override // j$.time.format.h
    public final h b() {
        if (this.e != (-1)) goto L5;
        return this;
    L5:
        boolean r6 = this.g;
        return new f(this.f383a, this.f384b, this.f385c, r6, -1);
    }

    @Override // j$.time.format.h
    public final h c(int r7) {
        int r5 = this.e + r7;
        return new f(this.f383a, this.f384b, this.f385c, this.g, r5);
    }

    @Override // j$.time.format.h, j$.time.format.e
    public final boolean s(t r9, StringBuilder r10) {
        j$.time.temporal.p r0 = this.f383a;
        Long r1 = r9.a(r0);
        int r2 = 0;
        if (r1 != null) goto L5;
        return false;
    L5:
        x r92 = r9.f410b.f372c;
        long r3 = r1.longValue();
        j$.time.temporal.s r12 = r0.K();
        r12.b(r3, r0);
        BigDecimal r02 = BigDecimal.valueOf(r12.f466a);
        BigDecimal r13 = BigDecimal.valueOf(r12.d).subtract(r02).add(BigDecimal.ONE);
        BigDecimal r03 = BigDecimal.valueOf(r3).subtract(r02);
        RoundingMode r32 = RoundingMode.FLOOR;
        BigDecimal r04 = r03.divide(r13, 9, r32);
        BigDecimal r14 = BigDecimal.ZERO;
        if (r04.compareTo(r14) != 0) goto L9;
    L12:
        int r05 = r14.scale();
        boolean r6 = this.g;
        int r7 = this.f384b;
        if (r05 != 0) goto L20;
        if (r7 <= 0) goto L19;
        if (r6 == false) goto L17;
        r92.getClass();
        r10.append('.');
    L17:
        if (r2 >= r7) goto L19;
        r92.getClass();
        r10.append('0');
        r2 = r2 + 1;
    L19:
        return true;
    L20:
        String r8 = r14.setScale(Math.min(Math.max(r14.scale(), r7), this.f385c), r32).toPlainString().substring(2);
        r92.getClass();
        if (r6 == false) goto L23;
        r10.append('.');
    L23:
        r10.append(r8);
        return true;
    L9:
        if (r04.signum() != 0) goto L11;
        r14 = new BigDecimal(BigInteger.ZERO, 0);
        goto L12
    L11:
        r14 = r04.stripTrailingZeros();
        goto L12
    }

    @Override // j$.time.format.h
    public final String toString() {
        if (this.g == false) goto L5;
        String r0 = ",DecimalPoint";
    L7:
        return "Fraction(" + this.f383a + "," + this.f384b + "," + this.f385c + r0 + ")";
    L5:
        r0 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L7
    }
}
