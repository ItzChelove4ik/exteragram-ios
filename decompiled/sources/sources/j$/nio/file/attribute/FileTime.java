package j$.nio.file.attribute;

import j$.time.Instant;
import j$.time.LocalDateTime;
import j$.time.ZoneOffset;
import java.util.concurrent.TimeUnit;
import kotlin.jvm.internal.LongCompanionObject;
import kotlin.time.DurationKt;
import okhttp3.internal.url._UrlKt;
import org.mvel2.asm.signature.SignatureVisitor;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class FileTime implements Comparable<FileTime> {

    /* renamed from: a, reason: collision with root package name */
    public final long f188a;

    /* renamed from: b, reason: collision with root package name */
    public Instant f189b;

    /* renamed from: c, reason: collision with root package name */
    public String f190c;

    public FileTime(long r1) {
        this.f188a = r1;
        this.f189b = null;
    }

    public static long I(long r1, long r3, long r5) {
        if (r1 <= r5) goto L7;
        return LongCompanionObject.MAX_VALUE;
    L7:
        if (r1 >= (-r5)) goto L11;
        return Long.MIN_VALUE;
    L11:
        return r1 * r3;
    }

    public static FileTime fromMillis(long r1) {
        return new FileTime(r1);
    }

    public static void s(StringBuilder r1, int r2, int r3) {
    L2:
        if (r2 <= 0) goto L4;
        r1.append((char) ((r3 / r2) + 48));
        r3 = r3 % r2;
        r2 = r2 / 10;
        goto L2
    }

    public final int D(FileTime r8) {
        TimeUnit r0 = TimeUnit.MILLISECONDS;
        if (r0 == null) goto L6;
        r8.getClass();
        return Long.compare(this.f188a, r8.f188a);
    L6:
        long r1 = toInstant().getEpochSecond();
        int r3 = Long.compare(r1, r8.toInstant().getEpochSecond());
        if (r3 == 0) goto L9;
        return r3;
    L9:
        int r32 = Long.compare(toInstant().getNano(), r8.toInstant().getNano());
        if (r32 == 0) goto L13;
        return r32;
    L13:
        if (r1 == 31556889864403199L) goto L19;
        if (r1 == (-31557014167219200L)) goto L19;
        return 0;
    L19:
        if (r0 == null) goto L21;
        long r5 = this.f188a / DurationKt.MILLIS_IN_DAY;
    L22:
        if (r0 == null) goto L24;
        long r02 = r8.f188a / DurationKt.MILLIS_IN_DAY;
    L26:
        if (r5 != r02) goto L30;
        return Long.compare(K(r5), r8.K(r02));
    L30:
        return Long.compare(r5, r02);
    L24:
        r02 = r8.toInstant().getEpochSecond() / 86400;
        goto L26
    L21:
        r5 = toInstant().getEpochSecond() / 86400;
        goto L22
    }

    public final long K(long r5) {
        TimeUnit r0 = TimeUnit.DAYS;
        TimeUnit r1 = TimeUnit.MILLISECONDS;
        if (r1 != null) goto L5;
        long r12 = toInstant().getEpochSecond() - r0.toSeconds(r5);
        return TimeUnit.SECONDS.toNanos(r12);
    L5:
        return r1.toNanos(this.f188a - r1.convert(r5, r0));
    }

    @Override // java.lang.Comparable
    public final /* bridge */ /* synthetic */ int compareTo(FileTime r1) {
        return D(r1);
    }

    public final boolean equals(Object r3) {
        if ((r3 instanceof FileTime) == true) goto L5;
    L8:
        return false;
    L5:
        if (D((FileTime) r3) != 0) goto L8;
        return true;
    }

    public final int hashCode() {
        return toInstant().hashCode();
    }

    public final Instant toInstant() {
        if (this.f189b != null) goto L24;
        switch(r.f207a[TimeUnit.MILLISECONDS.ordinal()]) {
            case 1: goto L15;
            case 2: goto L14;
            case 3: goto L13;
            case 4: goto L11;
            case 5: goto L10;
            case 6: goto L9;
            case 7: goto L8;
            default: goto L7;
        };
    L8:
        long r0 = Math.floorDiv(this.f188a, 1000000000);
        int r2 = (int) Math.floorMod(this.f188a, 1000000000);
    L17:
        if (r0 > (-31557014167219200L)) goto L20;
        this.f189b = Instant.d;
        goto L24
    L20:
        if (r0 < 31556889864403199L) goto L22;
        this.f189b = Instant.e;
        goto L24
    L22:
        this.f189b = Instant.ofEpochSecond(r0, r2);
        goto L24
    L9:
        r0 = Math.floorDiv(this.f188a, 1000000);
        r2 = ((int) Math.floorMod(this.f188a, 1000000)) * 1000;
        goto L17
    L10:
        r0 = Math.floorDiv(this.f188a, 1000);
        r2 = ((int) Math.floorMod(this.f188a, 1000)) * DurationKt.NANOS_IN_MILLIS;
        goto L17
    L11:
        long r22 = this.f188a;
    L12:
        long r10 = r22;
        r2 = 0;
        r0 = r10;
        goto L17
    L13:
        r22 = I(this.f188a, 60, 153722867280912930L);
        goto L12
    L14:
        r22 = I(this.f188a, 3600, 2562047788015215L);
        goto L12
    L15:
        r22 = I(this.f188a, 86400, 106751991167300L);
        goto L12
    L7:
        throw new AssertionError("Unit not handled");
    L24:
        return this.f189b;
    }

    public long toMillis() {
        if (TimeUnit.MILLISECONDS != null) goto L5;
        long r0 = this.f189b.getEpochSecond();
        int r10 = this.f189b.getNano();
        long r4 = r0 * 1000;
        if (((Math.abs(r0) | 1000) >>> 31) == 0) goto L17;
        if ((r4 / 1000) == r0) goto L17;
        if (r0 >= 0) goto L14;
        return Long.MIN_VALUE;
    L14:
        return LongCompanionObject.MAX_VALUE;
    L17:
        return r4 + (r10 / DurationKt.NANOS_IN_MILLIS);
    L5:
        return this.f188a;
    }

    public final String toString() {
        if (this.f190c != null) goto L34;
        if (this.f189b == null) goto L7;
    L9:
        long r0 = toInstant().getEpochSecond();
        int r2 = toInstant().getNano();
    L11:
        if (r0 < (-62167219200L)) goto L14;
        long r02 = r0 - 253402300800L;
        long r9 = Math.floorDiv(r02, 315569520000L) + 1;
        LocalDateTime r03 = LocalDateTime.K(Math.floorMod(r02, 315569520000L) - 62167219200L, r2, ZoneOffset.UTC);
        int r1 = r03.f292a.getYear();
    L13:
        int r22 = (((int) r9) * 10000) + r1;
        if (r22 > 0) goto L17;
        r22 = r22 - 1;
    L17:
        int r12 = r03.f293b.getNano();
        StringBuilder r3 = new StringBuilder(64);
        if (r22 >= 0) goto L20;
        String r5 = "-";
    L21:
        r3.append(r5);
        int r23 = Math.abs(r22);
        if (r23 >= 10000) goto L24;
        s(r3, 1000, Math.abs(r23));
    L25:
        r3.append(SignatureVisitor.SUPER);
        s(r3, 10, r03.f292a.getMonthValue());
        r3.append(SignatureVisitor.SUPER);
        s(r3, 10, r03.f292a.getDayOfMonth());
        r3.append('T');
        s(r3, 10, r03.f293b.getHour());
        r3.append(':');
        s(r3, 10, r03.f293b.getMinute());
        r3.append(':');
        s(r3, 10, r03.f293b.getSecond());
        if (r12 == 0) goto L32;
        r3.append('.');
        int r04 = 100000000;
    L29:
        if ((r12 % 10) != 0) goto L31;
        r12 = r12 / 10;
        r04 = r04 / 10;
        goto L29
    L31:
        s(r3, r04, r12);
    L32:
        r3.append('Z');
        this.f190c = r3.toString();
        goto L34
    L24:
        r3.append(String.valueOf(r23));
        goto L25
    L20:
        r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L21
    L14:
        long r05 = r0 + 62167219200L;
        r9 = r05 / 315569520000L;
        r03 = LocalDateTime.K((r05 % 315569520000L) - 62167219200L, r2, ZoneOffset.UTC);
        r1 = r03.f292a.getYear();
        goto L13
    L7:
        if (TimeUnit.MILLISECONDS.compareTo(TimeUnit.SECONDS) < 0) goto L9;
        r0 = this.f188a / 1000;
        r2 = 0;
    L34:
        return this.f190c;
    }
}
