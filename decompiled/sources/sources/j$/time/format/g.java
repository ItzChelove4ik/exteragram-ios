package j$.time.format;

import com.google.android.gms.cast.framework.media.NotificationOptions;
import j$.time.LocalDate;
import j$.time.LocalDateTime;
import j$.time.LocalTime;
import j$.time.ZoneOffset;
import java.util.Locale;
import org.mvel2.asm.signature.SignatureVisitor;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class g implements e {
    @Override // j$.time.format.e
    public final int D(r r17, CharSequence r18, int r19) {
        q r1 = new q();
        r1.a(DateTimeFormatter.ISO_LOCAL_DATE);
        r1.c('T');
        j$.time.temporal.a r2 = j$.time.temporal.a.HOUR_OF_DAY;
        r1.g(r2, 2);
        r1.c(':');
        j$.time.temporal.a r6 = j$.time.temporal.a.MINUTE_OF_HOUR;
        r1.g(r6, 2);
        r1.c(':');
        j$.time.temporal.a r5 = j$.time.temporal.a.SECOND_OF_MINUTE;
        r1.g(r5, 2);
        j$.time.temporal.a r7 = j$.time.temporal.a.NANO_OF_SECOND;
        r1.b(new f(r7));
        r1.c('Z');
        d r12 = r1.l(Locale.getDefault(), z.SMART, null).f370a;
        if (r12.f382b == false) goto L6;
        r12 = new d(r12.f381a, false);
    L6:
        r r3 = new r(r17.f403a);
        r3.f404b = r17.f404b;
        r3.f405c = r17.f405c;
        int r13 = r12.D(r3, r18, r19);
        if (r13 >= 0) goto L9;
        return r13;
    L9:
        long r9 = r3.d(j$.time.temporal.a.YEAR).longValue();
        int r11 = r3.d(j$.time.temporal.a.MONTH_OF_YEAR).intValue();
        int r122 = r3.d(j$.time.temporal.a.DAY_OF_MONTH).intValue();
        int r22 = r3.d(r2).intValue();
        int r62 = r3.d(r6).intValue();
        Long r52 = r3.d(r5);
        Long r32 = r3.d(r7);
        if (r52 == null) goto L12;
        int r53 = r52.intValue();
    L13:
        if (r32 == null) goto L15;
        int r132 = r32.intValue();
    L16:
        int r14 = 1;
        if (r22 != 24) goto L23;
        if (r62 != 0) goto L23;
        if (r53 != 0) goto L23;
        if (r132 != 0) goto L23;
        r22 = 0;
    L30:
        int r33 = ((int) r9) % 10000;
        LocalDateTime r15 = LocalDateTime.f291c;     // Catch: RuntimeException -> L34
        LocalDate r34 = LocalDate.of(r33, r11, r122);     // Catch: RuntimeException -> L34
        LocalTime r23 = LocalTime.of(r22, r62, r53, 0);     // Catch: RuntimeException -> L34
        return r17.f(r7, r132, r19, r17.f(j$.time.temporal.a.INSTANT_SECONDS, new LocalDateTime(r34, r23).c0(r34.plusDays(r14), r23).toEpochSecond(ZoneOffset.UTC) + Math.multiplyExact(r9 / NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS, 315569520000L), r19, r13));
    L35:
        return ~r19;
    L23:
        if (r22 == 23) goto L25;
    L29:
        r14 = 0;
        goto L30
    L25:
        if (r62 != 59) goto L29;
        if (r53 != 60) goto L29;
        r17.c().d = true;
        r53 = 59;
        goto L29
    L15:
        r132 = 0;
        goto L16
    L12:
        r53 = 0;
        goto L13
    }

    @Override // j$.time.format.e
    public final boolean s(t r19, StringBuilder r20) {
        Long r2 = r19.a(j$.time.temporal.a.INSTANT_SECONDS);
        j$.time.temporal.l r0 = r19.f409a;
        j$.time.temporal.a r3 = j$.time.temporal.a.NANO_OF_SECOND;
        if (r0.i(r3) == false) goto L5;
        Long r02 = Long.valueOf(r0.k(r3));
    L6:
        int r4 = 0;
        if (r2 != null) goto L9;
        return false;
    L9:
        long r5 = r2.longValue();
        if (r02 == null) goto L12;
        long r9 = r02.longValue();
    L13:
        int r03 = r3.f447b.a(r9, r3);
        if (r5 < (-62167219200L)) goto L21;
        long r52 = r5 - 253402300800L;
        long r92 = Math.floorDiv(r52, 315569520000L) + 1;
        LocalDateTime r22 = LocalDateTime.K(Math.floorMod(r52, 315569520000L) - 62167219200L, 0, ZoneOffset.UTC);
        if (r92 <= 0) goto L18;
        r20.append(SignatureVisitor.EXTENDS);
        r20.append(r92);
    L18:
        r20.append(r22);
        if (r22.f293b.getSecond() != 0) goto L33;
        r20.append(":00");
    L33:
        if (r03 <= 0) goto L42;
        r20.append('.');
        int r23 = 100000000;
    L36:
        if (r03 > 0) goto L44;
        if ((r4 % 3) != 0) goto L44;
        if (r4 >= (-2)) goto L42;
    L44:
        int r32 = r03 / r23;
        r20.append((char) (r32 + 48));
        r03 = r03 - (r32 * r23);
        r23 = r23 / 10;
        r4 = r4 + 1;
    L42:
        r20.append('Z');
        return true;
    L21:
        long r53 = r5 + 62167219200L;
        long r7 = r53 / 315569520000L;
        long r54 = r53 % 315569520000L;
        LocalDateTime r24 = LocalDateTime.K(r54 - 62167219200L, 0, ZoneOffset.UTC);
        int r12 = r20.length();
        r20.append(r24);
        if (r24.f293b.getSecond() != 0) goto L25;
        r20.append(":00");
    L25:
        if (r7 >= 0) goto L33;
        if (r24.f292a.getYear() != (-10000)) goto L30;
        r20.replace(r12, r12 + 2, Long.toString(r7 - 1));
        goto L33
    L30:
        if (r54 != 0) goto L32;
        r20.insert(r12, r7);
        goto L33
    L32:
        r20.insert(r12 + 1, Math.abs(r7));
        goto L33
    L12:
        r9 = 0;
        goto L13
    L5:
        r02 = null;
        goto L6
    }

    public final String toString() {
        return "Instant()";
    }
}
