package org.telegram.messenger.audioinfo.m4a;

import java.io.InputStream;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.audioinfo.util.PositionInputStream;

/* loaded from: classes5.dex */
public final class MP4Input extends MP4Box<PositionInputStream> {
    public MP4Input(InputStream r3) {
        super(new PositionInputStream(r3), null, _UrlKt.FRAGMENT_ENCODE_SET);
    }

    public MP4Atom nextChildUpTo(String r3) {
    L2:
        MP4Atom r0 = nextChild();
        if (r0.getType().matches(r3) == false) goto L2;
        return r0;
    }

    public String toString() {
        return "mp4[pos=" + getPosition() + "]";
    }
}
