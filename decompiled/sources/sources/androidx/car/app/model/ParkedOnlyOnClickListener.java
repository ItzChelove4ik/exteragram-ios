package androidx.car.app.model;

import android.annotation.SuppressLint;
import java.util.Objects;

@SuppressLint({"ListenerInterface"})
/* loaded from: classes3.dex */
public final class ParkedOnlyOnClickListener implements OnClickListener {
    private final OnClickListener mListener;

    private ParkedOnlyOnClickListener(OnClickListener r1) {
        this.mListener = r1;
    }

    @SuppressLint({"ExecutorRegistration"})
    public static ParkedOnlyOnClickListener create(OnClickListener r1) {
        Objects.requireNonNull(r1);
        return new ParkedOnlyOnClickListener(r1);
    }

    @Override // androidx.car.app.model.OnClickListener
    public void onClick() {
        this.mListener.onClick();
    }
}
