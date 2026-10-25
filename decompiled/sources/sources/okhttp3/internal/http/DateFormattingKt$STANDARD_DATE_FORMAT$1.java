package okhttp3.internal.http;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;
import kotlin.Metadata;
import okhttp3.internal._UtilJvmKt;

@Metadata(d1 = {"\u0000\u0011\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\b\u0010\u0003\u001a\u00020\u0002H\u0014¨\u0006\u0004"}, d2 = {"okhttp3/internal/http/DateFormattingKt$STANDARD_DATE_FORMAT$1", "Ljava/lang/ThreadLocal;", "Ljava/text/DateFormat;", "initialValue", "okhttp"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class DateFormattingKt$STANDARD_DATE_FORMAT$1 extends ThreadLocal<DateFormat> {
    public DateFormattingKt$STANDARD_DATE_FORMAT$1() {
    }

    @Override // java.lang.ThreadLocal
    public /* bridge */ /* synthetic */ DateFormat initialValue() {
        return initialValue2();
    }

    @Override // java.lang.ThreadLocal
    /* renamed from: initialValue, reason: avoid collision after fix types in other method */
    public DateFormat initialValue2() {
        SimpleDateFormat r2 = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.US);
        r2.setLenient(false);
        r2.setTimeZone(_UtilJvmKt.UTC);
        return r2;
    }
}
