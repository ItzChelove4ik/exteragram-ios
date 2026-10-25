package androidx.activity.result;

import android.annotation.SuppressLint;
import androidx.core.app.ActivityOptionsCompat;

/* loaded from: classes.dex */
public abstract class ActivityResultLauncher<I> {
    public ActivityResultLauncher() {
    }

    public void launch(@SuppressLint({"UnknownNullness"}) I r2) {
        launch(r2, null);
    }

    public abstract void launch(@SuppressLint({"UnknownNullness"}) I r1, ActivityOptionsCompat r2);

    public abstract void unregister();
}
