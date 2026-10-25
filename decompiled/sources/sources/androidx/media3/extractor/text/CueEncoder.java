package androidx.media3.extractor.text;

import android.os.Bundle;
import android.os.Parcel;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.BundleCollectionUtil;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes4.dex */
public final class CueEncoder {
    public CueEncoder() {
    }

    public byte[] encode(List<Cue> r2, long r3) {
        ArrayList<Bundle> r1 = BundleCollectionUtil.toBundleArrayList(r2, new CueEncoder$$ExternalSyntheticLambda0());
        Bundle r22 = new Bundle();
        r22.putParcelableArrayList("c", r1);
        r22.putLong("d", r3);
        Parcel r12 = Parcel.obtain();
        r12.writeBundle(r22);
        byte[] r23 = r12.marshall();
        r12.recycle();
        return r23;
    }
}
