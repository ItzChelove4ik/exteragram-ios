package com.exteragram.messenger.preferences;

import android.os.CountDownTimer;
import android.widget.TextView;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.StringCompanionObject;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016J\b\u0010\u0006\u001a\u00020\u0003H\u0016¨\u0006\u0007"}, d2 = {"com/exteragram/messenger/preferences/OtherPreferencesActivity$handleDeleteAccountClick$1$1$1", "Landroid/os/CountDownTimer;", "onTick", _UrlKt.FRAGMENT_ENCODE_SET, "millisUntilFinished", _UrlKt.FRAGMENT_ENCODE_SET, "onFinish", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class OtherPreferencesActivity$handleDeleteAccountClick$1$1$1 extends CountDownTimer {
    final /* synthetic */ TextView $button;
    final /* synthetic */ CharSequence $buttonText;

    public OtherPreferencesActivity$handleDeleteAccountClick$1$1$1(TextView r3, CharSequence r4) {
        this.$button = r3;
        this.$buttonText = r4;
        super(NotificationOptions.SKIP_STEP_THIRTY_SECONDS_IN_MS, 100);
    }

    @Override // android.os.CountDownTimer
    public void onFinish() {
        this.$button.setText(this.$buttonText);
        this.$button.setEnabled(true);
    }

    @Override // android.os.CountDownTimer
    public void onTick(long r5) {
        TextView r0 = this.$button;
        StringCompanionObject r1 = StringCompanionObject.INSTANCE;
        r0.setText(String.format(Locale.getDefault(), "%s • %d", Arrays.copyOf(new Object[]{this.$buttonText, Long.valueOf((r5 / 1000) + 1)}, 2)));
    }
}
