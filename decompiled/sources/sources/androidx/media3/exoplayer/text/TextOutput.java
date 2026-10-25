package androidx.media3.exoplayer.text;

import androidx.media3.common.text.Cue;
import androidx.media3.common.text.CueGroup;
import java.util.List;

/* loaded from: classes4.dex */
public interface TextOutput {
    void onCues(CueGroup r1);

    @Deprecated
    void onCues(List<Cue> r1);
}
