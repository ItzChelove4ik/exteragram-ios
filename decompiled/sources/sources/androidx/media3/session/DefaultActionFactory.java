package androidx.media3.session;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

/* loaded from: classes4.dex */
abstract class DefaultActionFactory {
    public static KeyEvent getKeyEvent(Intent r2) {
        Bundle r22 = r2.getExtras();
        if (r22 != null) goto L5;
        return null;
    L5:
        if (r22.containsKey("android.intent.extra.KEY_EVENT") == true) goto L7;
        return null;
    L7:
        return (KeyEvent) r22.getParcelable("android.intent.extra.KEY_EVENT");
    }
}
