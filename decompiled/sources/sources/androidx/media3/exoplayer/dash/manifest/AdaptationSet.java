package androidx.media3.exoplayer.dash.manifest;

import java.util.Collections;
import java.util.List;

/* loaded from: classes4.dex */
public class AdaptationSet {
    public final List<Descriptor> accessibilityDescriptors;
    public final List<Descriptor> essentialProperties;
    public final long id;
    public final List<Representation> representations;
    public final List<Descriptor> supplementalProperties;
    public final int type;

    public AdaptationSet(long r1, int r3, List<Representation> r4, List<Descriptor> r5, List<Descriptor> r6, List<Descriptor> r7) {
        this.id = r1;
        this.type = r3;
        this.representations = Collections.unmodifiableList(r4);
        this.accessibilityDescriptors = Collections.unmodifiableList(r5);
        this.essentialProperties = Collections.unmodifiableList(r6);
        this.supplementalProperties = Collections.unmodifiableList(r7);
    }
}
