package androidx.media3.exoplayer.dash.manifest;

import androidx.media3.extractor.metadata.emsg.EventMessage;

/* loaded from: classes4.dex */
public final class EventStream {
    public final EventMessage[] events;
    public final long[] presentationTimesUs;
    public final String schemeIdUri;
    public final long timescale;
    public final String value;

    public EventStream(String r1, String r2, long r3, long[] r5, EventMessage[] r6) {
        this.schemeIdUri = r1;
        this.value = r2;
        this.timescale = r3;
        this.presentationTimesUs = r5;
        this.events = r6;
    }

    public String id() {
        return this.schemeIdUri + "/" + this.value;
    }
}
