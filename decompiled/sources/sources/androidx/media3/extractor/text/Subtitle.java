package androidx.media3.extractor.text;

import androidx.media3.common.text.Cue;
import java.util.List;

/* loaded from: classes4.dex */
public interface Subtitle {
    List<Cue> getCues(long r1);

    long getEventTime(int r1);

    int getEventTimeCount();

    int getNextEventTimeIndex(long r1);
}
