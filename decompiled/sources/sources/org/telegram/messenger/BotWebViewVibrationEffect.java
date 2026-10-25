package org.telegram.messenger;

import android.os.Build;
import android.os.VibrationEffect;
import com.android.dx.io.Opcodes;
import com.exteragram.messenger.ExteraConfig;
import com.google.android.gms.cast.MediaError;

/* loaded from: classes5.dex */
public enum BotWebViewVibrationEffect extends Enum<BotWebViewVibrationEffect> {
    private static final /* synthetic */ BotWebViewVibrationEffect[] $VALUES = null;
    public static final BotWebViewVibrationEffect APP_ERROR = null;
    public static final BotWebViewVibrationEffect IMPACT_HEAVY = null;
    public static final BotWebViewVibrationEffect IMPACT_LIGHT = null;
    public static final BotWebViewVibrationEffect IMPACT_MEDIUM = null;
    public static final BotWebViewVibrationEffect IMPACT_RIGID = null;
    public static final BotWebViewVibrationEffect IMPACT_SOFT = null;
    public static final BotWebViewVibrationEffect NOTIFICATION_ERROR = null;
    public static final BotWebViewVibrationEffect NOTIFICATION_SUCCESS = null;
    public static final BotWebViewVibrationEffect NOTIFICATION_WARNING = null;
    public static final BotWebViewVibrationEffect SELECTION_CHANGE = null;
    public final int[] amplitudes;
    public final long[] fallbackTimings;
    public final long[] timings;
    private Object vibrationEffect;

    private static /* synthetic */ BotWebViewVibrationEffect[] $values() {
        return new BotWebViewVibrationEffect[]{IMPACT_LIGHT, IMPACT_MEDIUM, IMPACT_HEAVY, IMPACT_RIGID, IMPACT_SOFT, NOTIFICATION_ERROR, NOTIFICATION_SUCCESS, NOTIFICATION_WARNING, SELECTION_CHANGE, APP_ERROR};
    }

    static {
        IMPACT_LIGHT = new BotWebViewVibrationEffect("IMPACT_LIGHT", 0, new long[]{7}, new int[]{65}, new long[]{60});
        IMPACT_MEDIUM = new BotWebViewVibrationEffect("IMPACT_MEDIUM", 1, new long[]{7}, new int[]{145}, new long[]{70});
        IMPACT_HEAVY = new BotWebViewVibrationEffect("IMPACT_HEAVY", 2, new long[]{7}, new int[]{255}, new long[]{80});
        IMPACT_RIGID = new BotWebViewVibrationEffect("IMPACT_RIGID", 3, new long[]{3}, new int[]{Opcodes.SHR_INT_LIT8}, new long[]{50});
        IMPACT_SOFT = new BotWebViewVibrationEffect("IMPACT_SOFT", 4, new long[]{10}, new int[]{175}, new long[]{55});
        NOTIFICATION_ERROR = new BotWebViewVibrationEffect("NOTIFICATION_ERROR", 5, new long[]{14, 48, 14, 48, 14, 48, 20}, new int[]{200, 0, 200, 0, 255, 0, 145}, new long[]{40, 60, 40, 60, 65, 60, 40});
        NOTIFICATION_SUCCESS = new BotWebViewVibrationEffect("NOTIFICATION_SUCCESS", 6, new long[]{14, 65, 14}, new int[]{175, 0, 255}, new long[]{50, 60, 65});
        NOTIFICATION_WARNING = new BotWebViewVibrationEffect("NOTIFICATION_WARNING", 7, new long[]{14, 64, 14}, new int[]{Opcodes.SHR_INT_LIT8, 0, 175}, new long[]{65, 60, 40});
        SELECTION_CHANGE = new BotWebViewVibrationEffect("SELECTION_CHANGE", 8, new long[]{1}, new int[]{65}, new long[]{30});
        APP_ERROR = new BotWebViewVibrationEffect(MediaError.ERROR_REASON_APP_ERROR, 9, new long[]{30, 10, 150, 10}, new int[]{0, 100, 0, 100}, new long[]{40, 60, 40, 60, 65, 60, 40});
        $VALUES = $values();
    }

    BotWebViewVibrationEffect(String r1, int r2, long[] r3, int[] r4, long[] r5) {
        this.timings = r3;
        this.amplitudes = r4;
        this.fallbackTimings = r5;
    }

    public static BotWebViewVibrationEffect valueOf(String r1) {
        return (BotWebViewVibrationEffect) Enum.valueOf(BotWebViewVibrationEffect.class, r1);
    }

    public static BotWebViewVibrationEffect[] values() {
        return (BotWebViewVibrationEffect[]) $VALUES.clone();
    }

    public VibrationEffect getVibrationEffectForOreo() {
        if (this.vibrationEffect != null) goto L9;
        if (AndroidUtilities.getVibrator().hasAmplitudeControl() == true) goto L7;
        this.vibrationEffect = VibrationEffect.createWaveform(this.fallbackTimings, -1);
        goto L9
    L7:
        this.vibrationEffect = VibrationEffect.createWaveform(this.timings, this.amplitudes, -1);
    L9:
        return BotWebViewVibrationEffect$$ExternalSyntheticApiModelOutline0.m(this.vibrationEffect);
    }

    public void vibrate() {
        if (ExteraConfig.getInAppVibration() == true) goto L6;
        return;
    L6:
        if (Build.VERSION.SDK_INT < 26) goto L9;
        AndroidUtilities.getVibrator().vibrate(getVibrationEffectForOreo());
        return;
    L9:
        AndroidUtilities.getVibrator().vibrate(this.fallbackTimings, -1);
    }
}
