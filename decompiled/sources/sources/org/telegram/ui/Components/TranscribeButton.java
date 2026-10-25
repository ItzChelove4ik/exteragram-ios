package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.SystemClock;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ImageSpan;
import android.util.StateSet;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.speech.VoiceRecognitionController;
import com.exteragram.messenger.speech.utils.MediaLoader;
import com.exteragram.messenger.utils.chats.ChatUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.RequestDelegate;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;

/* loaded from: classes7.dex */
public abstract class TranscribeButton {
    private static final int[] pressedState = null;
    private static HashMap<Integer, MessageObject> transcribeOperationsByDialogPosition;
    private static HashMap<Long, MessageObject> transcribeOperationsById;
    private static ArrayList<Integer> videoTranscriptionsOpen;

    /* renamed from: a, reason: collision with root package name */
    private float f512a;
    private final AnimatedFloat animatedDrawLock;

    /* renamed from: b, reason: collision with root package name */
    private float f513b;
    private float backgroundBack;
    private int backgroundColor;
    private Paint backgroundPaint;
    private Rect bounds;
    private Path boundsPath;
    private boolean clickedToOpen;
    private Paint clipLockPaint;
    private int color;
    private int diameter;
    private boolean drawLock;
    private int iconColor;
    private RLottieDrawable inIconDrawable;
    private int inIconDrawableAlpha;
    private final FastOutSlowInInterpolator interpolator;
    private boolean isOpen;
    private boolean loading;
    private final AnimatedFloat loadingFloat;
    private Path lockHandlePath;
    private float lockHandlePathDensity;
    private Paint lockPaint;
    private Paint lockStrokePaint;
    private RLottieDrawable outIconDrawable;
    private int outIconDrawableAlpha;
    private ChatMessageCell parent;
    private boolean premium;
    private Rect pressBounds;
    private long pressId;
    private boolean pressed;
    private Path progressClipPath;
    private int radius;
    private int rippleColor;
    private SeekBarWaveform seekBar;
    private float[] segments;
    private Drawable selectorDrawable;
    private boolean shouldBeOpen;
    private long start;
    private Paint strokePaint;

    /* renamed from: org.telegram.ui.Components.TranscribeButton$1, reason: invalid class name */
    public class AnonymousClass1 extends ArrayList<MessageObject> {
        final /* synthetic */ MessageObject val$messageObject;

        public AnonymousClass1(MessageObject r1) {
            this.val$messageObject = r1;
            add(r1);
        }
    }

    /* renamed from: org.telegram.ui.Components.TranscribeButton$2, reason: invalid class name */
    public class AnonymousClass2 implements VoiceRecognitionController.RecognitionCallback {
        final /* synthetic */ Long val$hashCode;
        final /* synthetic */ RequestDelegate val$onComplete;

        public AnonymousClass2(Long r1, RequestDelegate r2) {
            this.val$hashCode = r1;
            this.val$onComplete = r2;
        }

        @Override // com.exteragram.messenger.speech.VoiceRecognitionController.RecognitionCallback
        public void onChunk(String r4) {
            TLRPC.TL_messages_transcribedAudio r0 = new TLRPC.TL_messages_transcribedAudio();
            r0.text = r4;
            r0.pending = true;
            r0.transcription_id = this.val$hashCode.longValue();
            this.val$onComplete.run(r0, null);
        }

        @Override // com.exteragram.messenger.speech.VoiceRecognitionController.RecognitionCallback
        public void onCompleted(String r4) {
            TLRPC.TL_messages_transcribedAudio r0 = new TLRPC.TL_messages_transcribedAudio();
            r0.text = r4;
            r0.pending = false;
            r0.transcription_id = this.val$hashCode.longValue();
            this.val$onComplete.run(r0, null);
        }

        @Override // com.exteragram.messenger.speech.VoiceRecognitionController.RecognitionCallback
        public void onError(Exception r2) {
            TLRPC.TL_error r22 = new TLRPC.TL_error();
            r22.text = "RECOGNIZE_FAILED";
            this.val$onComplete.run(null, r22);
        }

        @Override // com.exteragram.messenger.speech.VoiceRecognitionController.RecognitionCallback
        public void onLanguageNotDownloaded(String r1) {
        }

        @Override // com.exteragram.messenger.speech.VoiceRecognitionController.RecognitionCallback
        public void onLanguageNotSupported(String r1) {
        }
    }

    public static class LoadingPointsDrawable extends Drawable {
        private final Drawable.Callback callback;
        private int lastColor;
        private RLottieDrawable lottie;
        private Paint paint;

        /* renamed from: org.telegram.ui.Components.TranscribeButton$LoadingPointsDrawable$1, reason: invalid class name */
        public class AnonymousClass1 implements Drawable.Callback {
            final /* synthetic */ LoadingPointsDrawable this$0;

            public AnonymousClass1(LoadingPointsDrawable r1) {
                this.this$0 = r1;
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void invalidateDrawable(Drawable r1) {
                this.this$0.invalidateSelf();
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
            }

            @Override // android.graphics.drawable.Drawable.Callback
            public void unscheduleDrawable(Drawable r1, Runnable r2) {
            }
        }

        public LoadingPointsDrawable(TextPaint r6) {
            AnonymousClass1 r0 = new AnonymousClass1(this);
            this.callback = r0;
            this.paint = r6;
            float r62 = r6.getTextSize() * 0.89f;
            RLottieDrawable r1 = new RLottieDrawable(R.raw.dots_loading, (int) r62, (int) (r62 * 1.25f));
            this.lottie = r1;
            r1.setCallback(r0);
            this.lottie.setAutoRepeat(1);
            this.lottie.setCurrentFrame((int) ((SystemClock.elapsedRealtime() / 16.0f) % 60.0f));
            this.lottie.setAllowDecodeSingleFrame(true);
            this.lottie.start();
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r3) {
            int r0 = this.paint.getColor();
            if (r0 == this.lastColor) goto L5;
            setColor(r0);
            this.lastColor = r0;
        L5:
            this.lottie.draw(r3);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
        }

        public void setColor(int r3) {
            this.lottie.beginApplyLayerColors();
            this.lottie.setLayerColor("Comp 1", r3);
            this.lottie.commitApplyLayerColors();
            this.lottie.setAllowDecodeSingleFrame(true);
            this.lottie.updateCurrentFrame(0, false);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
        }
    }

    public static class LoadingPointsSpan extends ImageSpan {
        private static LoadingPointsDrawable drawable;

        public LoadingPointsSpan() {
            LoadingPointsDrawable r0 = drawable;
            if (r0 != null) goto L5;
            r0 = new LoadingPointsDrawable(Theme.chat_msgTextPaint);
            drawable = r0;
        L5:
            super(r0, 0);
            float r02 = Theme.chat_msgTextPaint.getTextSize() * 0.89f;
            int r2 = (int) (0.02f * r02);
            getDrawable().setBounds(0, r2, (int) r02, ((int) (r02 * 1.25f)) + r2);
        }

        @Override // android.text.style.ReplacementSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r6) {
            float r0 = r6.getTextSize() * 0.89f;
            int r1 = (int) (0.02f * r0);
            getDrawable().setBounds(0, r1, (int) r0, ((int) (r0 * 1.25f)) + r1);
            super.updateDrawState(r6);
        }
    }

    /* renamed from: $r8$lambda$1VsD2GsCgYYhqv4wc1i0Eh-Iq-0, reason: not valid java name */
    public static /* synthetic */ void m13376$r8$lambda$1VsD2GsCgYYhqv4wc1i0EhIq0(MessageObject r2, long r3, String r5) {
        NotificationCenter r0 = NotificationCenter.getInstance(r2.currentAccount);
        int r1 = NotificationCenter.voiceTranscriptionUpdate;
        Long r32 = Long.valueOf(r3);
        Boolean r4 = Boolean.TRUE;
        r0.postNotificationName(r1, new Object[]{r2, r32, r5, r4, r4});
    }

    public static /* synthetic */ void $r8$lambda$E2DTTm67GyFFCCRusbCoYcmM2M4(int r3, MessageObject r4) {
        NotificationCenter r32 = NotificationCenter.getInstance(r3);
        int r0 = NotificationCenter.voiceTranscriptionUpdate;
        Boolean r2 = Boolean.TRUE;
        r32.postNotificationName(r0, new Object[]{r4, null, null, r2, r2});
    }

    public static /* synthetic */ void $r8$lambda$H6uErkToW2FXW3uPB4aDaZPjAwU(TranscribeButton r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$JO7tJ6zuPfrfC6q9DpmXF7FK7ts(int r3, MessageObject r4) {
        NotificationCenter.getInstance(r3).postNotificationName(NotificationCenter.voiceTranscriptionUpdate, new Object[]{r4, null, null, Boolean.FALSE, null});
    }

    public static /* synthetic */ void $r8$lambda$MeX4zTChvjtBQPRlHQ_ds02X024(MessageObject r2, int r3) {
        HashMap<Integer, MessageObject> r0 = transcribeOperationsByDialogPosition;
        if (r0 == null) goto L5;
        r0.remove(Integer.valueOf(reqInfoHash(r2)));
    L5:
        NotificationCenter.getInstance(r3).postNotificationName(NotificationCenter.voiceTranscriptionUpdate, new Object[]{r2});
    }

    public static /* synthetic */ void $r8$lambda$WVaNQpZB4bOQY85fS5DzEIvQlF0(int r17, ChatMessageCell.ChatMessageCellDelegate r18, MessageObject r19, long r20, long r22, int r24, TLObject r25, TLRPC.TL_error r26) {
        TLRPC.TL_messages_transcribedAudio r4 = (TLRPC.TL_messages_transcribedAudio) r25;
        String r5 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r4 == null) goto L17;
        String r3 = r4.text;
        long r9 = r4.transcription_id;
        boolean r11 = r4.pending;
        boolean r12 = !r11;
        if (TextUtils.isEmpty(r3) == false) goto L8;
        if (r11 == false) goto L10;
        r5 = null;
    L10:
        if ((r4.flags & 2) == 0) goto L13;
        MessagesController.getInstance(r17).updateTranscribeAudioTrialCurrentNumber(r4.trial_remains_num);
        MessagesController.getInstance(r17).updateTranscribeAudioTrialCooldownUntil(r4.trial_remains_until_date);
        AndroidUtilities.runOnUIThread(new TranscribeButton$$ExternalSyntheticLambda8(r18, r4));
    L13:
        if (transcribeOperationsById != null) goto L15;
        transcribeOperationsById = new HashMap();
    L15:
        transcribeOperationsById.put(Long.valueOf(r9), r19);
        r19.messageOwner.voiceTranscriptionId = r9;
        long r32 = r9;
        boolean r1 = r12;
    L16:
        String r13 = r5;
        long r15 = SystemClock.elapsedRealtime() - r20;
        openVideoTranscription(r19);
        TLRPC.Message r52 = r19.messageOwner;
        r52.voiceTranscriptionOpen = true;
        r52.voiceTranscriptionFinal = r1;
        if (BuildVars.LOGS_ENABLED == false) goto L30;
        FileLog.d("Transcription request sent, received final=" + r1 + " id=" + r32 + " text=" + r13);
    L30:
        MessagesStorage.getInstance(r17).updateMessageVoiceTranscription(r22, r24, r13, r19.messageOwner);
        if (r1 == false) goto L34;
        AndroidUtilities.runOnUIThread(new TranscribeButton$$ExternalSyntheticLambda11(r19, r32, r13), Math.max(0, 350 - r15));
        return;
    L34:
        return;
    L8:
        r5 = r3;
        goto L10
    L17:
        if (r26 == null) goto L26;
        String r42 = r26.text;
        if (r42 == null) goto L26;
        if (r42.startsWith("FLOOD_WAIT_") == false) goto L24;
        MessagesController.getInstance(r17).updateTranscribeAudioTrialCurrentNumber(0);
        MessagesController.getInstance(r17).updateTranscribeAudioTrialCooldownUntil(ConnectionsManager.getInstance(r17).getCurrentTime() + Utilities.parseInt(r26.text));
        AndroidUtilities.runOnUIThread(new TranscribeButton$$ExternalSyntheticLambda9(r19, r18, r17));
        return;
    L24:
        AndroidUtilities.runOnUIThread(new TranscribeButton$$ExternalSyntheticLambda10(r19, r17));
        return;
    L26:
        r1 = true;
        r32 = 0;
        goto L16
    }

    public static /* synthetic */ void $r8$lambda$XJwJoJ_oHFiFTFFmY9ITIfZKcbY(MessageObject r2) {
        NotificationCenter.getInstance(r2.currentAccount).postNotificationName(NotificationCenter.voiceTranscriptionUpdate, new Object[]{r2});
    }

    /* renamed from: $r8$lambda$a2o22si0EBawg-KyJsqzCtlJsVw, reason: not valid java name */
    public static /* synthetic */ void m13377$r8$lambda$a2o22si0EBawgKyJsqzCtlJsVw(ChatMessageCell.ChatMessageCellDelegate r0, TLRPC.TL_messages_transcribedAudio r1) {
        if (r0 != null) goto L4;
        return;
    L4:
        if (r1.trial_remains_num <= 0) goto L6;
        int r12 = 1;
    L7:
        r0.needShowPremiumBulletin(r12);
        return;
    L6:
        r12 = 2;
        goto L7
    }

    /* renamed from: $r8$lambda$kNGsRkppBULGalCwHXf-cm26L9g, reason: not valid java name */
    public static /* synthetic */ void m13378$r8$lambda$kNGsRkppBULGalCwHXfcm26L9g(MessageObject r0, RequestDelegate r1, int r2) {
        if (r2 <= 0) goto L5;
        transcribeLocally(r0, r1);
        return;
    }

    /* renamed from: $r8$lambda$oaPhGEe95pO5I2f8R2G6iDB-Ayg, reason: not valid java name */
    public static /* synthetic */ void m13379$r8$lambda$oaPhGEe95pO5I2f8R2G6iDBAyg(MessageObject r2, ChatMessageCell.ChatMessageCellDelegate r3, int r4) {
        HashMap<Integer, MessageObject> r0 = transcribeOperationsByDialogPosition;
        if (r0 == null) goto L5;
        r0.remove(Integer.valueOf(reqInfoHash(r2)));
    L5:
        if (r3 == null) goto L7;
        r3.needShowPremiumBulletin(3);
    L7:
        NotificationCenter.getInstance(r4).postNotificationName(NotificationCenter.voiceTranscriptionUpdate, new Object[]{r2});
        NotificationCenter.getInstance(r4).postNotificationName(NotificationCenter.updateTranscriptionLock, new Object[0]);
    }

    public static /* synthetic */ void $r8$lambda$qTeWpodzojzFl0nOR5qjuuuAo3E(TranscribeButton r0) {
        r0.lambda$new$0();
    }

    /* renamed from: $r8$lambda$vX9cc4AD2o2CIR-Un2kBKg_aRo0, reason: not valid java name */
    public static /* synthetic */ void m13380$r8$lambda$vX9cc4AD2o2CIRUn2kBKg_aRo0(MessageObject r0, long r1, String r3) {
        finishTranscription(r0, r1, r3);
    }

    static {
        pressedState = new int[]{android.R.attr.state_enabled, android.R.attr.state_pressed};
    }

    public TranscribeButton(ChatMessageCell r7, SeekBarWaveform r8) {
        boolean r0 = false;
        this.clickedToOpen = false;
        this.pressed = false;
        this.pressId = 0;
        this.interpolator = new FastOutSlowInInterpolator();
        this.start = SystemClock.elapsedRealtime();
        this.parent = r7;
        this.seekBar = r8;
        this.bounds = new Rect(0, 0, AndroidUtilities.dp(30.0f), AndroidUtilities.dp(30.0f));
        Rect r82 = new Rect(this.bounds);
        this.pressBounds = r82;
        r82.inset(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        RLottieDrawable r83 = new RLottieDrawable(R.raw.transcribe_out, AndroidUtilities.dp(26.0f), AndroidUtilities.dp(26.0f));
        this.outIconDrawable = r83;
        r83.setCurrentFrame(0);
        this.outIconDrawable.setCallback(r7);
        this.outIconDrawable.setOnFinishCallback(new TranscribeButton$$ExternalSyntheticLambda6(this), 19);
        this.outIconDrawable.setAllowDecodeSingleFrame(true);
        RLottieDrawable r84 = new RLottieDrawable(R.raw.transcribe_in, AndroidUtilities.dp(26.0f), AndroidUtilities.dp(26.0f));
        this.inIconDrawable = r84;
        r84.setCurrentFrame(0);
        this.inIconDrawable.setCallback(r7);
        this.inIconDrawable.setMasterParent(r7);
        this.inIconDrawable.setOnFinishCallback(new TranscribeButton$$ExternalSyntheticLambda7(this), 19);
        this.inIconDrawable.setAllowDecodeSingleFrame(true);
        this.isOpen = false;
        this.shouldBeOpen = false;
        if (r7.getMessageObject() != null) goto L5;
    L7:
        this.premium = r0;
        CubicBezierInterpolator r02 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.loadingFloat = new AnimatedFloat(r7, 250, r02);
        this.animatedDrawLock = new AnimatedFloat(r7, 250, r02);
        return;
    L5:
        if (UserConfig.getInstance(r7.getMessageObject().currentAccount).isPremium() == false) goto L7;
        r0 = true;
        goto L7
    }

    private void addCorner(Path r3, int r4, int r5, int r6, int r7, float r8, float r9) {
        float r82 = MathUtils.clamp(r8, 0.0f, 1.0f);
        float r92 = MathUtils.clamp(r9, 0.0f, 1.0f) - r82;
        if (r92 > 0.0f) goto L6;
        return;
    L6:
        if (r7 != 1) goto L9;
        AndroidUtilities.rectTmp.set(r4 - r6, r5, r4, r5 + r6);
    L17:
        r3.addArc(AndroidUtilities.rectTmp, ((r7 * 90) - 180) + (r82 * 90.0f), r92 * 90.0f);
        return;
    L9:
        if (r7 != 2) goto L12;
        AndroidUtilities.rectTmp.set(r4 - r6, r5 - r6, r4, r5);
        goto L17
    L12:
        if (r7 != 3) goto L15;
        AndroidUtilities.rectTmp.set(r4, r5 - r6, r4 + r6, r5);
        goto L17
    L15:
        if (r7 != 4) goto L17;
        AndroidUtilities.rectTmp.set(r4, r5, r4 + r6, r5 + r6);
        goto L17
    }

    private void addCorner(Path r20, int r21, int r22, int r23, int r24, float r25, float r26, float r27, float r28) {
        if (r25 <= r26) goto L6;
        float r1 = r28 - r27;
        addCorner(r20, r21, r22, r23, r24, (r25 - r27) / r1, 1.0f);
        addCorner(r20, r21, r22, r23, r24, 0.0f, (r26 - r27) / r1);
        return;
    L6:
        float r2 = Math.max(0.0f, r25 - r27);
        float r3 = r28 - r27;
        addCorner(r20, r21, r22, r23, r24, r2 / r3, (Math.min(r26, r28) - r27) / r3);
    }

    private void addLine(Path r2, int r3, int r4, int r5, int r6, float r7, float r8) {
        if (r3 != r5) goto L5;
        if (r4 != r6) goto L5;
        return;
    L5:
        float r72 = MathUtils.clamp(r7, 0.0f, 1.0f);
        if ((MathUtils.clamp(r8, 0.0f, 1.0f) - r72) > 0.0f) goto L8;
        return;
    L8:
        r2.moveTo(AndroidUtilities.lerp(r3, r5, r72), AndroidUtilities.lerp(r4, r6, r72));
        r2.lineTo(AndroidUtilities.lerp(r3, r5, r8), AndroidUtilities.lerp(r4, r6, r8));
    }

    private void addLine(Path r11, int r12, int r13, int r14, int r15, float r16, float r17, float r18, float r19) {
        if (r12 != r14) goto L7;
        if (r13 != r15) goto L7;
        return;
    L7:
        if (r16 <= r17) goto L10;
        float r9 = r19 - r18;
        addLine(r11, r12, r13, r14, r15, (r16 - r18) / r9, 1.0f);
        addLine(r11, r12, r13, r14, r15, 0.0f, (r17 - r18) / r9);
        return;
    L10:
        float r2 = Math.max(0.0f, r16 - r18);
        float r3 = r19 - r18;
        addLine(r11, r12, r13, r14, r15, r2 / r3, (Math.min(r17, r19) - r18) / r3);
    }

    public static boolean canTranscribeTrial(MessageObject r9) {
        if (r9 != null) goto L5;
    L24:
        return false;
    L5:
        if (r9.messageOwner == null) goto L24;
        ConnectionsManager r1 = ConnectionsManager.getInstance(r9.currentAccount);
        MessagesController r2 = MessagesController.getInstance(r9.currentAccount);
        if (isFreeTranscribeInChat(r9) == false) goto L11;
        return true;
    L11:
        if (r2.transcribeAudioTrialWeeklyNumber <= 0) goto L24;
        if (r9.getDuration() > r2.transcribeAudioTrialDurationMax) goto L24;
        if (r2.transcribeAudioTrialCooldownUntil != 0) goto L18;
    L23:
        return true;
    L18:
        if (r1.getCurrentTime() > r2.transcribeAudioTrialCooldownUntil) goto L23;
        if (r2.transcribeAudioTrialCurrentNumber > 0) goto L23;
        return false;
    }

    private void drawLock(Canvas r11) {
        AnimatedFloat r0 = this.animatedDrawLock;
        if (this.drawLock == true) goto L5;
    L9:
        boolean r1 = false;
    L10:
        float r02 = r0.set(r1);
        if (r02 > 0.0f) goto L13;
        return;
    L13:
        r11.save();
        r11.translate(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(12.0f));
        if (this.clipLockPaint != null) goto L16;
        Paint r4 = new Paint(1);
        this.clipLockPaint = r4;
        r4.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
    L16:
        RectF r42 = AndroidUtilities.rectTmp;
        r42.set(0.0f, -AndroidUtilities.dp(0.4f), AndroidUtilities.dp(6.666f), AndroidUtilities.dp(8.733f));
        r11.scale(r02, r02, r42.centerX(), r42.centerY());
        r11.drawRoundRect(r42, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), this.clipLockPaint);
        if (this.lockPaint != null) goto L19;
        this.lockPaint = new Paint(1);
    L19:
        this.lockPaint.setColor(this.iconColor);
        int r03 = (int) (r02 * 255.0f);
        this.lockPaint.setAlpha(r03);
        r42.set(0.0f, AndroidUtilities.dp(3.33f), AndroidUtilities.dp(6.666f), AndroidUtilities.dp(8.33f));
        r11.drawRoundRect(r42, AndroidUtilities.dp(1.33f), AndroidUtilities.dp(1.33f), this.lockPaint);
        if (this.lockHandlePath != null) goto L22;
    L23:
        this.lockHandlePathDensity = AndroidUtilities.density;
        Path r12 = new Path();
        this.lockHandlePath = r12;
        r12.moveTo(AndroidUtilities.dp(1.66f), AndroidUtilities.dp(3.33f));
        this.lockHandlePath.lineTo(AndroidUtilities.dp(1.66f), AndroidUtilities.dp(2.0f));
        r42.set(AndroidUtilities.dp(1.66f), AndroidUtilities.dp(0.33f), AndroidUtilities.dp(4.99f), AndroidUtilities.dp(3.6599998f));
        this.lockHandlePath.arcTo(r42, -180.0f, 180.0f, false);
        this.lockHandlePath.lineTo(AndroidUtilities.dp(5.0f), AndroidUtilities.dp(3.33f));
    L25:
        if (this.lockStrokePaint != null) goto L27;
        Paint r13 = new Paint(1);
        this.lockStrokePaint = r13;
        r13.setStyle(Paint.Style.STROKE);
    L27:
        this.lockStrokePaint.setStrokeWidth(AndroidUtilities.dp(1.0f));
        this.lockStrokePaint.setColor(this.iconColor);
        this.lockStrokePaint.setAlpha(r03);
        r11.drawPath(this.lockHandlePath, this.lockStrokePaint);
        r11.restore();
        return;
    L22:
        if (Math.abs(this.lockHandlePathDensity - AndroidUtilities.density) <= 0.1f) goto L25;
    L5:
        if (this.isOpen == true) goto L9;
        if (this.loading == true) goto L9;
        r1 = true;
        goto L10
    }

    public static boolean finishTranscription(MessageObject r9, long r10, String r12) {
        HashMap<Long, MessageObject> r1 = transcribeOperationsById;     // Catch: Exception -> L21
        if (r1 != null) goto L6;
    L8:
        MessageObject r13 = null;
    L9:
        if (r9 != null) goto L11;
        r9 = r13;
    L11:
        if (r9 == null) goto L20;
        if (r9.messageOwner == null) goto L20;
        HashMap<Integer, MessageObject> r14 = transcribeOperationsByDialogPosition;     // Catch: Exception -> L21
        if (r14 == null) goto L18;
        r14.remove(Integer.valueOf(reqInfoHash(r9)));     // Catch: Exception -> L21
    L18:
        r9.messageOwner.voiceTranscriptionFinal = true;     // Catch: Exception -> L21
        MessagesStorage.getInstance(r9.currentAccount).updateMessageVoiceTranscription(r9.getDialogId(), r9.getId(), r12, r9.messageOwner);     // Catch: Exception -> L21
        AndroidUtilities.runOnUIThread(new TranscribeButton$$ExternalSyntheticLambda1(r9, r10, r12));     // Catch: Exception -> L21
        return true;
    L6:
        if (r1.containsKey(Long.valueOf(r10)) == false) goto L8;
        r13 = transcribeOperationsById.remove(Long.valueOf(r10));     // Catch: Exception -> L21
    L20:
        return false;
    }

    private float[] getSegments(long r12) {
        if (this.segments != null) goto L5;
        this.segments = new float[2];
    L5:
        long r122 = r12 % 5400;
        float[] r0 = this.segments;
        float r1 = (1520 * r122) / 5400.0f;
        r0[0] = r1 - 20.0f;
        r0[1] = r1;
        int r02 = 0;
    L6:
        float[] r13 = this.segments;
        if (r02 >= 4) goto L9;
        int r6 = r02 * 1350;
        r13[1] = r13[1] + (this.interpolator.getInterpolation((r122 - r6) / 667.0f) * 250.0f);
        float[] r14 = this.segments;
        r14[0] = r14[0] + (this.interpolator.getInterpolation((r122 - (r6 + 667)) / 667.0f) * 250.0f);
        r02 = r02 + 1;
        goto L6
    L9:
        return r13;
    }

    public static int getTranscribeTrialCount(int r2) {
        ConnectionsManager r0 = ConnectionsManager.getInstance(r2);
        MessagesController r22 = MessagesController.getInstance(r2);
        if (r22.transcribeAudioTrialWeeklyNumber > 0) goto L7;
        return 0;
    L7:
        if (r22.transcribeAudioTrialCooldownUntil == 0) goto L14;
        if (r0.getCurrentTime() > r22.transcribeAudioTrialCooldownUntil) goto L14;
        return r22.transcribeAudioTrialCurrentNumber;
    L14:
        return r22.transcribeAudioTrialWeeklyNumber;
    }

    public static boolean isFreeTranscribeInChat(MessageObject r4) {
        if (r4 != null) goto L5;
    L13:
        return false;
    L5:
        if (r4.messageOwner == null) goto L13;
        MessagesController r1 = MessagesController.getInstance(r4.currentAccount);
        TLRPC.Chat r42 = r1.getChat(Long.valueOf(r4.getChatId()));
        if (ChatObject.isMegagroup(r42) == false) goto L13;
        if (r42.level < r1.groupTranscribeLevelMin) goto L13;
        return true;
    }

    public static boolean isTranscribing(MessageObject r3) {
        HashMap<Integer, MessageObject> r0 = transcribeOperationsByDialogPosition;
        if (r0 != null) goto L5;
    L8:
        HashMap<Long, MessageObject> r02 = transcribeOperationsById;
        if (r02 == null) goto L15;
        if (r3 == null) goto L15;
        TLRPC.Message r1 = r3.messageOwner;
        if (r1 == null) goto L15;
        if (r02.containsKey(Long.valueOf(r1.voiceTranscriptionId)) == false) goto L15;
        return true;
    L15:
        if (r3 != null) goto L17;
        return false;
    L17:
        if (VoiceRecognitionController.getInstance().isRecognizing(Long.valueOf(r3.getDialogId()), r3.getId()) == false) goto L25;
        return true;
    L25:
        return false;
    L5:
        if (r0.containsValue(r3) == false) goto L7;
        return true;
    L7:
        if (transcribeOperationsByDialogPosition.containsKey(Integer.valueOf(reqInfoHash(r3))) == false) goto L8;
        return true;
    }

    public static boolean isVideoTranscriptionOpen(MessageObject r1) {
        if (videoTranscriptionsOpen != null) goto L5;
        return false;
    L5:
        if (r1.isRoundVideo() == true) goto L7;
        return true;
    L7:
        if (videoTranscriptionsOpen.contains(Integer.valueOf(reqInfoHash(r1))) == false) goto L13;
        return true;
    L13:
        return false;
    }

    private /* synthetic */ void lambda$new$0() {
        this.outIconDrawable.stop();
        this.inIconDrawable.stop();
        this.shouldBeOpen = true;
        this.isOpen = true;
        this.inIconDrawable.setCurrentFrame(0);
    }

    private /* synthetic */ void lambda$new$1() {
        this.inIconDrawable.stop();
        this.outIconDrawable.stop();
        this.shouldBeOpen = false;
        this.isOpen = false;
        this.outIconDrawable.setCurrentFrame(0);
    }

    public static void openVideoTranscription(MessageObject r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (isVideoTranscriptionOpen(r2) == false) goto L7;
        return;
    L7:
        if (videoTranscriptionsOpen != null) goto L9;
        videoTranscriptionsOpen = new ArrayList(1);
    L9:
        videoTranscriptionsOpen.add(Integer.valueOf(reqInfoHash(r2)));
    }

    private static int reqInfoHash(MessageObject r3) {
        if (r3 != null) goto L6;
        return 0;
    L6:
        return Objects.hash(new Object[]{Integer.valueOf(r3.currentAccount), Long.valueOf(r3.getDialogId()), Integer.valueOf(r3.getId())});
    }

    public static void resetVideoTranscriptionsOpen() {
        ArrayList<Integer> r0 = videoTranscriptionsOpen;
        if (r0 == null) goto L6;
        r0.clear();
        return;
    }

    public static void showOffTranscribe(MessageObject r1) {
        showOffTranscribe(r1, true);
    }

    public static void showOffTranscribe(MessageObject r5, boolean r6) {
        if (r5 == null) goto L10;
        TLRPC.Message r0 = r5.messageOwner;
        if (r0 == null) goto L12;
        r0.voiceTranscriptionForce = true;
        MessagesStorage.getInstance(r5.currentAccount).updateMessageVoiceTranscriptionOpen(r5.getDialogId(), r5.getId(), r5.messageOwner);
        if (r6 == false) goto L11;
        AndroidUtilities.runOnUIThread(new TranscribeButton$$ExternalSyntheticLambda0(r5));
        return;
    L11:
        return;
    L12:
        return;
    }

    public static boolean showTranscribeLock(MessageObject r3) {
        if (r3 != null) goto L5;
    L27:
        return false;
    L5:
        if (r3.messageOwner == null) goto L27;
        if (VoiceRecognitionController.isCustomRecognitionEnabled() == false) goto L11;
        return false;
    L11:
        if (isFreeTranscribeInChat(r3) == false) goto L14;
        return false;
    L14:
        if (TextUtils.isEmpty(r3.messageOwner.voiceTranscription) == true) goto L16;
        return false;
    L16:
        ConnectionsManager r1 = ConnectionsManager.getInstance(r3.currentAccount);
        MessagesController r2 = MessagesController.getInstance(r3.currentAccount);
        if (UserConfig.getInstance(r3.currentAccount).isPremium() == false) goto L20;
        return false;
    L20:
        if (r2.transcribeAudioTrialCooldownUntil == 0) goto L27;
        if (r1.getCurrentTime() > r2.transcribeAudioTrialCooldownUntil) goto L27;
        if (r2.transcribeAudioTrialCurrentNumber > 0) goto L27;
        return true;
    }

    private static void transcribeLocally(MessageObject r7, RequestDelegate r8) {
        String r4 = ChatUtils.getInstance().getPathToMessage(r7);
        if (TextUtils.isEmpty(r4) == false) goto L5;
        return;
    L5:
        long r0 = DialogObject.getPeerDialogId(MessagesController.getInstance(r7.currentAccount).getInputPeer(r7.messageOwner.peer_id));
        int r72 = r7.messageOwner.id;
        VoiceRecognitionController r1 = VoiceRecognitionController.getInstance();
        r1.startRecognition(r1.key(Long.valueOf(r0), r72), ExteraConfig.getRecognitionLanguage(), r4, "vosk", new AnonymousClass2(Long.valueOf(r2.hashCode()), r8));
    }

    private static void transcribePressed(MessageObject r10, boolean r11, ChatMessageCell.ChatMessageCellDelegate r12) {
        if (r10 != null) goto L4;
        return;
    L4:
        if (r10.messageOwner != null) goto L6;
        return;
    L6:
        if (r10.isSent() == false) goto L40;
        int r2 = r10.currentAccount;
        long r5 = SystemClock.elapsedRealtime();
        TLRPC.InputPeer r0 = MessagesController.getInstance(r2).getInputPeer(r10.messageOwner.peer_id);
        long r7 = DialogObject.getPeerDialogId(r0);
        TLRPC.Message r1 = r10.messageOwner;
        int r9 = r1.id;
        int r3 = 0;
        if (r11 == true) goto L11;
        HashMap<Integer, MessageObject> r102 = transcribeOperationsByDialogPosition;
        if (r102 == null) goto L36;
        r102.remove(Integer.valueOf(reqInfoHash(r10)));
    L36:
        r10.messageOwner.voiceTranscriptionOpen = false;
        MessagesStorage.getInstance(r2).updateMessageVoiceTranscriptionOpen(r7, r9, r10.messageOwner);
        AndroidUtilities.runOnUIThread(new TranscribeButton$$ExternalSyntheticLambda5(r2, r10));
        return;
    L11:
        if (r1.voiceTranscription == null) goto L17;
        if (r1.voiceTranscriptionFinal == false) goto L17;
        openVideoTranscription(r10);
        r10.messageOwner.voiceTranscriptionOpen = true;
        MessagesStorage.getInstance(r2).updateMessageVoiceTranscriptionOpen(r7, r9, r10.messageOwner);
        AndroidUtilities.runOnUIThread(new TranscribeButton$$ExternalSyntheticLambda2(r2, r10));
        return;
    L17:
        if (BuildVars.LOGS_ENABLED == false) goto L19;
        FileLog.d("sending Transcription request, msg_id=" + r9 + " dialog_id=" + r7);
    L19:
        TLRPC.TL_messages_transcribeAudio r112 = new TLRPC.TL_messages_transcribeAudio();
        r112.peer = r0;
        r112.msg_id = r9;
        if (transcribeOperationsByDialogPosition != null) goto L22;
        transcribeOperationsByDialogPosition = new HashMap();
    L22:
        transcribeOperationsByDialogPosition.put(Integer.valueOf(reqInfoHash(r10)), r10);
        if (UserConfig.getInstance(r2).isPremium() == true) goto L25;
        r3 = 1024;
    L25:
        int r02 = r3;
        TranscribeButton$$ExternalSyntheticLambda3 r13 = new TranscribeButton$$ExternalSyntheticLambda3(r2, r12, r10, r5, r7, r9);
        if (VoiceRecognitionController.isCustomRecognitionEnabled() == true) goto L28;
    L31:
        ConnectionsManager.getInstance(r2).sendRequest(r112, r13, r02);
        return;
    L28:
        if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == true) goto L31;
        MediaLoader.loadFiles(AccountInstance.getInstance(r2), new AnonymousClass1(r10), new TranscribeButton$$ExternalSyntheticLambda4(r10, r13));
        return;
    }

    public void draw(Canvas r13, float r14) {
        this.pressBounds.set(this.bounds.left - AndroidUtilities.dp(8.0f), this.bounds.top - AndroidUtilities.dp(8.0f), this.bounds.right + AndroidUtilities.dp(8.0f), this.bounds.bottom + AndroidUtilities.dp(8.0f));
        Path r1 = this.boundsPath;
        if (r1 != null) goto L5;
        this.boundsPath = new Path();
    L6:
        RectF r12 = AndroidUtilities.rectTmp;
        r12.set(this.bounds);
        Path r2 = this.boundsPath;
        int r3 = this.radius;
        r2.addRoundRect(r12, r3, r3, Path.Direction.CW);
        r13.save();
        r13.clipPath(this.boundsPath);
        float r15 = this.backgroundBack;
        if ((r15 * r14) <= 0.0f) goto L9;
        drawGradientBackground(r13, this.bounds, r15 * r14);
    L9:
        Paint r16 = this.backgroundPaint;
        if (r16 == null) goto L12;
        int r17 = r16.getAlpha();
        this.backgroundPaint.setAlpha((int) (r17 * r14));
        r13.drawRect(this.bounds, this.backgroundPaint);
        this.backgroundPaint.setAlpha(r17);
    L12:
        Drawable r18 = this.selectorDrawable;
        if (r18 == null) goto L15;
        r18.setBounds(this.bounds);
        this.selectorDrawable.draw(r13);
    L15:
        r13.restore();
        AnimatedFloat r19 = this.loadingFloat;
        float r4 = 1.0f;
        if (this.loading == false) goto L18;
        float r22 = 1.0f;
    L19:
        float r110 = r19.set(r22);
        if (r110 <= 0.0f) goto L35;
        float[] r23 = getSegments((long) ((SystemClock.elapsedRealtime() - this.start) * 0.75f));
        Path r5 = this.progressClipPath;
        if (r5 != null) goto L24;
        this.progressClipPath = new Path();
    L25:
        float r52 = Math.max(40.0f * r110, r23[1] - r23[0]);
        float r24 = r23[0];
        float r6 = (1.0f - r110) * r52;
        if (this.loading == false) goto L28;
        r4 = 0.0f;
    L28:
        float r25 = r24 + (r6 * r4);
        float r53 = (r52 * r110) + r25;
        float r26 = r25 % 360.0f;
        float r54 = r53 % 360.0f;
        if (r26 >= 0.0f) goto L31;
        r26 = r26 + 360.0f;
    L31:
        float r62 = r26;
        if (r54 >= 0.0f) goto L34;
        r54 = r54 + 360.0f;
    L34:
        float r7 = r54;
        Path r111 = this.progressClipPath;
        int r27 = this.bounds.centerX();
        Rect r32 = this.bounds;
        int r42 = r32.top;
        addLine(r111, r27, r42, r32.right - this.radius, r42, r62, r7, 0.0f, this.f512a);
        Path r112 = this.progressClipPath;
        Rect r28 = this.bounds;
        addCorner(r112, r28.right, r28.top, this.diameter, 1, r62, r7, this.f512a, this.f513b);
        Path r113 = this.progressClipPath;
        Rect r29 = this.bounds;
        int r33 = r29.right;
        int r43 = r29.top;
        int r55 = this.radius;
        int r44 = r43 + r55;
        int r56 = r29.bottom - r55;
        float r8 = this.f513b;
        addLine(r113, r33, r44, r33, r56, r62, r7, r8, 180.0f - r8);
        Path r114 = this.progressClipPath;
        Rect r210 = this.bounds;
        addCorner(r114, r210.right, r210.bottom, this.diameter, 2, r62, r7, 180.0f - this.f513b, 180.0f - this.f512a);
        Path r115 = this.progressClipPath;
        Rect r211 = this.bounds;
        int r34 = r211.right;
        int r45 = this.radius;
        int r35 = r34 - r45;
        int r36 = r211.bottom;
        int r46 = r45 + r211.left;
        float r212 = this.f512a;
        addLine(r115, r35, r36, r46, r36, r62, r7, 180.0f - r212, r212 + 180.0f);
        Path r116 = this.progressClipPath;
        Rect r213 = this.bounds;
        addCorner(r116, r213.left, r213.bottom, this.diameter, 3, r62, r7, this.f512a + 180.0f, this.f513b + 180.0f);
        Path r117 = this.progressClipPath;
        Rect r214 = this.bounds;
        int r37 = r214.left;
        int r47 = r214.bottom;
        int r57 = this.radius;
        int r48 = r47 - r57;
        int r58 = r57 + r214.top;
        float r215 = this.f513b;
        addLine(r117, r37, r48, r37, r58, r62, r7, r215 + 180.0f, 360.0f - r215);
        Path r118 = this.progressClipPath;
        Rect r216 = this.bounds;
        addCorner(r118, r216.left, r216.top, this.diameter, 4, r62, r7, 360.0f - this.f513b, 360.0f - this.f512a);
        Path r119 = this.progressClipPath;
        Rect r217 = this.bounds;
        addLine(r119, r217.left + this.radius, r217.top, r217.centerX(), this.bounds.top, r62, r7, 360.0f - this.f512a, 360.0f);
        this.strokePaint.setStrokeWidth(AndroidUtilities.dp(1.5f));
        int r0 = this.strokePaint.getAlpha();
        this.strokePaint.setAlpha((int) (r0 * r14));
        r13.drawPath(this.progressClipPath, this.strokePaint);
        this.strokePaint.setAlpha(r0);
        this.parent.invalidate();
        goto L35
    L24:
        r5.rewind();
    L35:
        r13.save();
        r13.translate(this.bounds.centerX() + AndroidUtilities.dp(-13.0f), this.bounds.centerY() + AndroidUtilities.dp(-13.0f));
        r13.saveLayerAlpha(0.0f, 0.0f, AndroidUtilities.dp(26.0f), AndroidUtilities.dp(26.0f), 255, 31);
        if (this.isOpen == false) goto L38;
        this.inIconDrawable.setAlpha((int) (this.inIconDrawableAlpha * r14));
        this.inIconDrawable.draw(r13);
    L39:
        drawLock(r13);
        r13.restore();
        r13.restore();
        return;
    L38:
        this.outIconDrawable.setAlpha((int) (this.outIconDrawableAlpha * r14));
        this.outIconDrawable.draw(r13);
        goto L39
    L18:
        r22 = 0.0f;
        goto L19
    L5:
        r1.rewind();
        goto L6
    }

    public abstract void drawGradientBackground(Canvas r1, Rect r2, float r3);

    public int height() {
        return this.bounds.height();
    }

    public abstract void onOpen();

    public void onTap() {
        ChatMessageCell r0 = this.parent;
        if (r0 == null) goto L27;
        this.clickedToOpen = false;
        boolean r2 = this.shouldBeOpen;
        boolean r3 = !r2;
        if (r2 == true) goto L16;
        boolean r5 = !this.loading;
        if (this.premium == true) goto L14;
        if (canTranscribeTrial(r0.getMessageObject()) == true) goto L14;
        if (VoiceRecognitionController.isCustomRecognitionEnabled() == true) goto L14;
    L17:
        Drawable r02 = this.selectorDrawable;
        if ((r02 instanceof RippleDrawable) == false) goto L20;
        r02.setState(StateSet.NOTHING);
        this.parent.invalidate();
    L20:
        this.pressed = false;
        if (r5 == false) goto L26;
        if (r2 == true) goto L24;
        this.clickedToOpen = true;
    L24:
        transcribePressed(this.parent.getMessageObject(), r3, this.parent.getDelegate());
        return;
    L26:
        return;
    L14:
        if (this.parent.getMessageObject().isSent() == false) goto L17;
        setLoading(true, true);
        goto L17
    L16:
        setOpen(false, true);
        setLoading(false, true);
        r5 = true;
        goto L17
    }

    public boolean onTouch(int r6, float r7, float r8) {
        if (r6 == 1) goto L19;
        if (r6 == 3) goto L19;
        if (this.pressBounds.contains((int) r7, (int) r8) == true) goto L10;
        return false;
    L10:
        if (r6 != 0) goto L13;
        this.pressed = true;
    L13:
        if (this.pressed == false) goto L17;
        Drawable r62 = this.selectorDrawable;
        if ((r62 instanceof RippleDrawable) == false) goto L17;
        r62.setHotspot(r7, r8);
        this.selectorDrawable.setState(pressedState);
        this.parent.invalidate();
    L17:
        return true;
    L19:
        if (this.pressed == false) goto L23;
        if (r6 != 1) goto L23;
        onTap();
        return true;
    L23:
        this.pressed = false;
        return false;
    }

    public void setBounds(int r18, int r19, int r20, int r21, int r22) {
        if (r20 == this.bounds.width()) goto L5;
    L6:
        float r6 = r20 / 2.0f;
        float r8 = r22;
        float r10 = r21 / 2.0f;
        this.f512a = (float) ((Math.atan((r6 - r8) / r10) * 180.0d) / 3.141592653589793d);
        this.f513b = (float) ((Math.atan(r6 / (r10 - r8)) * 180.0d) / 3.141592653589793d);
    L7:
        this.bounds.set(r18, r19, r18 + r20, r19 + r21);
        int r1 = Math.min(Math.min(r20, r21) / 2, r22);
        this.radius = r1;
        this.diameter = r1 * 2;
        return;
    L5:
        if (r21 == this.bounds.height()) goto L7;
        goto L6
    }

    public void setColor(int r5, int r6, boolean r7, float r8) {
        if (this.color == r5) goto L5;
        boolean r62 = true;
    L6:
        this.color = r5;
        this.iconColor = r5;
        int r1 = ColorUtils.setAlphaComponent(r5, (int) (Color.alpha(r5) * 0.156f));
        this.backgroundColor = r1;
        this.backgroundBack = r8;
        float r2 = Color.alpha(r5);
        if (Theme.isCurrentThemeDark() == false) goto L9;
        float r3 = 0.3f;
    L10:
        this.rippleColor = Theme.blendOver(r1, ColorUtils.setAlphaComponent(r5, (int) (r2 * r3)));
        if (this.backgroundPaint != null) goto L13;
        this.backgroundPaint = new Paint();
    L13:
        this.backgroundPaint.setColor(this.backgroundColor);
        this.backgroundPaint.setAlpha((int) (r1.getAlpha() * (1.0f - r8)));
        if (r62 == false) goto L16;
    L17:
        Drawable r82 = Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(8.0f), 0, this.rippleColor);
        this.selectorDrawable = r82;
        r82.setCallback(this.parent);
    L18:
        if (r62 == false) goto L21;
        this.inIconDrawable.beginApplyLayerColors();
        this.inIconDrawable.setLayerColor("Artboard Outlines", this.iconColor);
        this.inIconDrawable.commitApplyLayerColors();
        this.inIconDrawable.setAllowDecodeSingleFrame(true);
        this.inIconDrawable.updateCurrentFrame(0, false);
        RLottieDrawable r63 = this.inIconDrawable;
        int r83 = Color.alpha(r5);
        this.inIconDrawableAlpha = r83;
        r63.setAlpha(r83);
        this.outIconDrawable.beginApplyLayerColors();
        this.outIconDrawable.setLayerColor("Artboard Outlines", this.iconColor);
        this.outIconDrawable.commitApplyLayerColors();
        this.outIconDrawable.setAllowDecodeSingleFrame(true);
        this.outIconDrawable.updateCurrentFrame(0, false);
        RLottieDrawable r64 = this.outIconDrawable;
        int r72 = Color.alpha(r5);
        this.outIconDrawableAlpha = r72;
        r64.setAlpha(r72);
    L21:
        if (this.strokePaint != null) goto L23;
        Paint r65 = new Paint(1);
        this.strokePaint = r65;
        r65.setStyle(Paint.Style.STROKE);
        this.strokePaint.setStrokeCap(Paint.Cap.ROUND);
    L23:
        this.strokePaint.setColor(r5);
        return;
    L16:
        if (this.selectorDrawable != null) goto L18;
    L9:
        r3 = 0.2f;
        goto L10
    L5:
        r62 = false;
        goto L6
    }

    public void setLoading(boolean r2, boolean r3) {
        this.loading = r2;
        this.seekBar.setLoading(r2);
        AnimatedFloat r22 = this.loadingFloat;
        if (r3 == true) goto L6;
        r22.set(this.loading, true);
    L8:
        ChatMessageCell r1 = this.parent;
        if (r1 == null) goto L12;
        r1.invalidate();
        return;
    L12:
        return;
    L6:
        if (r22.get() > 0.0f) goto L8;
        this.start = SystemClock.elapsedRealtime();
        goto L8
    }

    public void setLock(boolean r2, boolean r3) {
        if (this.drawLock == r2) goto L7;
        ChatMessageCell r0 = this.parent;
        if (r0 == null) goto L7;
        r0.invalidate();
    L7:
        this.drawLock = r2;
        if (r3 == true) goto L11;
        this.animatedDrawLock.set(r2, true);
        return;
    }

    public void setOpen(boolean r3, boolean r4) {
        if (this.shouldBeOpen == true) goto L8;
        if (r3 == false) goto L8;
        if (this.clickedToOpen == false) goto L8;
        this.clickedToOpen = false;
        onOpen();
    L8:
        boolean r0 = this.shouldBeOpen;
        this.shouldBeOpen = r3;
        if (r4 == false) goto L16;
        if (r3 == false) goto L13;
        if (r0 == true) goto L13;
        this.isOpen = false;
        this.inIconDrawable.setCurrentFrame(0);
        this.outIconDrawable.setCurrentFrame(0);
        this.outIconDrawable.start();
    L17:
        ChatMessageCell r2 = this.parent;
        if (r2 == null) goto L21;
        r2.invalidate();
        return;
    L21:
        return;
    L13:
        if (r3 == true) goto L17;
        if (r0 == false) goto L17;
        this.isOpen = true;
        this.outIconDrawable.setCurrentFrame(0);
        this.inIconDrawable.setCurrentFrame(0);
        this.inIconDrawable.start();
        goto L17
    L16:
        this.isOpen = r3;
        this.inIconDrawable.stop();
        this.outIconDrawable.stop();
        this.inIconDrawable.setCurrentFrame(0);
        this.outIconDrawable.setCurrentFrame(0);
        goto L17
    }

    public int width() {
        return this.bounds.width();
    }
}
