package com.exteragram.messenger.preferences.appearance;

import android.content.Context;
import com.exteragram.messenger.preferences.components.AltSeekbar;
import java.util.Arrays;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.math.MathKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\b\u0010\u0004\u001a\u00020\u0005H\u0014¨\u0006\u0006"}, d2 = {"com/exteragram/messenger/preferences/appearance/AppNavigationPreferencesActivity$createView$seekbar$1", "Lcom/exteragram/messenger/preferences/components/AltSeekbar;", "getTextForHeader", _UrlKt.FRAGMENT_ENCODE_SET, "useExactEndpointHaptic", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class AppNavigationPreferencesActivity$createView$seekbar$1 extends AltSeekbar {
    public AppNavigationPreferencesActivity$createView$seekbar$1(Context r9, AltSeekbar.OnDrag r10, String r11, String r12, String r13) {
        super(r9, r10, 0, 2, r11, r12, r13);
    }

    @Override // com.exteragram.messenger.preferences.components.AltSeekbar
    public CharSequence getTextForHeader() {
        float r0 = MathKt.roundToInt(this.currentValue * 10.0f) / 10.0f;
        if (r0 > 0.0f) goto L7;
        return this.leftTextView.getText().toString().toUpperCase(Locale.US);
    L7:
        if (r0 >= 2.0f) goto L9;
        int r2 = (int) r0;
        if (r0 == r2) goto L13;
        StringCompanionObject r22 = StringCompanionObject.INSTANCE;
        return String.format(Locale.US, "%.1f", Arrays.copyOf(new Object[]{Float.valueOf(r0)}, 1));
    L13:
        return String.valueOf(r2);
    L9:
        return this.rightTextView.getText().toString().toUpperCase(Locale.US);
    }

    @Override // com.exteragram.messenger.preferences.components.AltSeekbar
    public boolean useExactEndpointHaptic() {
        return true;
    }
}
