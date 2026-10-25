package androidx.work;

import androidx.tracing.Trace;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000'\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0005H\u0016J\u0018\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\r\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\u000e"}, d2 = {"androidx/work/ConfigurationKt$createDefaultTracer$tracer$1", "Landroidx/work/Tracer;", "isEnabled", _UrlKt.FRAGMENT_ENCODE_SET, "beginSection", _UrlKt.FRAGMENT_ENCODE_SET, "label", _UrlKt.FRAGMENT_ENCODE_SET, "endSection", "beginAsyncSection", "methodName", "cookie", _UrlKt.FRAGMENT_ENCODE_SET, "endAsyncSection", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class ConfigurationKt$createDefaultTracer$tracer$1 implements Tracer {
    public ConfigurationKt$createDefaultTracer$tracer$1() {
    }

    @Override // androidx.work.Tracer
    public void beginAsyncSection(String r1, int r2) {
        Trace.beginAsyncSection(r1, r2);
    }

    @Override // androidx.work.Tracer
    public void beginSection(String r1) {
        Trace.beginSection(r1);
    }

    @Override // androidx.work.Tracer
    public void endAsyncSection(String r1, int r2) {
        Trace.endAsyncSection(r1, r2);
    }

    @Override // androidx.work.Tracer
    public void endSection() {
        Trace.endSection();
    }

    @Override // androidx.work.Tracer
    public boolean isEnabled() {
        return Trace.isEnabled();
    }
}
