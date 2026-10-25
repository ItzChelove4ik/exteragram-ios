package j$.nio.channels;

import j$.desugar.sun.nio.fs.g;
import j$.nio.file.OpenOption;
import j$.nio.file.Path;
import j$.nio.file.attribute.FileAttribute;
import j$.nio.file.z;
import java.nio.channels.FileChannel;
import java.util.Collections;
import java.util.HashSet;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public class DesugarChannels {
    public static FileChannel open(Path r2, OpenOption... r3) {
        HashSet r0 = new HashSet();
        Collections.addAll(r0, r3);
        FileAttribute[] r32 = new FileAttribute[0];
        if (j$.adapter.a.f141a == false) goto L7;
        return FileChannel.open(z.s(r2), g.l(r0), g.x(r32));
    L7:
        return g.z(r2, r0);
    }
}
