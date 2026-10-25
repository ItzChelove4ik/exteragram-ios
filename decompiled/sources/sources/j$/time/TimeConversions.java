package j$.time;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public class TimeConversions {
    public static java.time.Duration convert(Duration r4) {
        if (r4 != null) goto L6;
        return null;
    L6:
        return java.time.Duration.ofSeconds(r4.getSeconds(), r4.getNano());
    }
}
