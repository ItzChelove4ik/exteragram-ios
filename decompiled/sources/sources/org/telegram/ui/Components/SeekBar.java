package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.SystemClock;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.Pair;
import android.view.View;
import java.util.ArrayList;
import java.util.Collections;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.Utilities;

/* loaded from: classes7.dex */
public class SeekBar {
    private static Paint paint;
    private static int thumbWidth;
    private static Path tmpPath;
    private static float[] tmpRadii;
    private final float TIMESTAMP_GAP;
    private float alpha;
    private int backgroundColor;
    private int backgroundSelectedColor;
    private float bufferedProgress;
    private int cacheColor;
    private int circleColor;
    private float currentRadius;
    private int currentTimestamp;
    private SeekBarDelegate delegate;
    private int draggingThumbX;
    private int height;
    private CharSequence lastCaption;
    private int lastTimestamp;
    private long lastTimestampUpdate;
    private long lastTimestampsAppearingUpdate;
    private long lastUpdateTime;
    private long lastVideoDuration;
    private float lastWidth;
    private int lineHeight;
    private View parentView;
    private boolean pressed;
    private int progressColor;
    private RectF rect;
    private boolean selected;
    private int thumbDX;
    private float thumbProgress;
    private int thumbX;
    private float timestampChangeT;
    private StaticLayout[] timestampLabel;
    private TextPaint timestampLabelPaint;
    private ArrayList<Pair<Float, URLSpanNoUnderline>> timestamps;
    private float timestampsAppearing;
    private int width;

    /* loaded from: classes3.dex */
    public interface SeekBarDelegate {
        default boolean isSeekBarDragAllowed() {
            return true;
        }

        default void onSeekBarContinuousDrag(float r1) {
        }

        void onSeekBarDrag(float r1);

        default void onSeekBarPressed() {
        }

        default void onSeekBarReleased() {
        }

        default boolean reverseWaveform() {
            return false;
        }
    }

    public static /* synthetic */ int $r8$lambda$Z3AHhuRQfXw11EzXbdCyVNofXTQ(Pair r2, Pair r3) {
        if (((Float) r2.first).floatValue() <= ((Float) r3.first).floatValue()) goto L7;
        return 1;
    L7:
        if (((Float) r3.first).floatValue() <= ((Float) r2.first).floatValue()) goto L10;
        return -1;
    L10:
        return 0;
    }

    public SeekBar(View r3) {
        this.thumbX = 0;
        this.draggingThumbX = 0;
        this.thumbDX = 0;
        this.pressed = false;
        this.rect = new RectF();
        this.lineHeight = AndroidUtilities.dp(2.0f);
        this.alpha = 1.0f;
        this.timestampsAppearing = 0.0f;
        this.TIMESTAMP_GAP = 1.0f;
        this.currentTimestamp = -1;
        this.lastTimestamp = -1;
        this.timestampChangeT = 1.0f;
        this.lastWidth = -1.0f;
        if (paint != null) goto L5;
        paint = new Paint(1);
    L5:
        this.parentView = r3;
        thumbWidth = AndroidUtilities.dp(24.0f);
        this.currentRadius = AndroidUtilities.dp(6.0f);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r17v0 */
    /* JADX WARN: Type inference failed for: r17v1 */
    /* JADX WARN: Type inference failed for: r17v2 */
    private void drawProgressBar(Canvas r26, RectF r27, Paint r28) {
        SeekBar r0 = this;
        float r4 = thumbWidth / 2.0f;
        ArrayList<Pair<Float, URLSpanNoUnderline>> r6 = r0.timestamps;
        if (r6 != null) goto L5;
    L86:
        r26.drawRoundRect(r27, r4, r4, r28);
        return;
    L5:
        if (r6.isEmpty() == true) goto L86;
        float r62 = r27.bottom;
        int r63 = thumbWidth;
        float r7 = r63 / 2.0f;
        float r8 = r0.width - (r63 / 2.0f);
        AndroidUtilities.rectTmp.set(r27);
        float r64 = AndroidUtilities.dp(r0.timestampsAppearing * 1.0f) / 2.0f;
        if (tmpPath != null) goto L10;
        tmpPath = new Path();
    L10:
        tmpPath.reset();
        float r5 = AndroidUtilities.dp(4.0f) / (r8 - r7);
        int r11 = 0;
    L11:
        int r13 = -1;
        if (r11 >= r0.timestamps.size()) goto L17;
        if (((Float) r0.timestamps.get(r11).first).floatValue() >= r5) goto L18;
        r11 = r11 + 1;
    L18:
        if (r11 >= 0) goto L20;
        r11 = 0;
    L20:
        boolean r14 = true;
        int r12 = r0.timestamps.size() - 1;
    L21:
        if (r12 < 0) goto L26;
        if ((1.0f - ((Float) r0.timestamps.get(r12).first).floatValue()) >= r5) goto L24;
        r12 = r12 - 1;
        goto L21
    L24:
        r13 = r12 + 1;
    L26:
        if (r13 >= 0) goto L28;
        r13 = r0.timestamps.size();
    L28:
        int r122 = r11;
    L29:
        if (r122 > r13) goto L84;
        float r15 = 0.0f;
        if (r122 != r11) goto L33;
        float r9 = 0.0f;
        char r16 = 0;
    L34:
        if (r122 != r13) goto L36;
        float r10 = 1.0f;
    L37:
        if (r122 == r13) goto L44;
        if (r122 == 0) goto L44;
        ?? r17 = r14;
        if (r122 >= (r0.timestamps.size() - 1)) goto L45;
        if ((((Float) r0.timestamps.get(r122).first).floatValue() - r9) > r5) goto L45;
        r122 = r122 + 1;
        r10 = ((Float) r0.timestamps.get(r122).first).floatValue();
        r14 = r17 == true ? 1 : 0;
    L45:
        RectF r142 = AndroidUtilities.rectTmp;
        float r92 = AndroidUtilities.lerp(r7, r8, r9);
        if (r122 <= 0) goto L48;
        float r18 = r64;
    L49:
        r142.left = r92 + r18;
        float r93 = AndroidUtilities.lerp(r7, r8, r10);
        if (r122 >= r13) goto L52;
        r15 = r64;
    L52:
        float r94 = r93 - r15;
        r142.right = r94;
        float r102 = r27.right;
        if (r94 <= r102) goto L55;
        char r95 = r17;
    L56:
        if (r95 == 0) goto L58;
        r142.right = r102;
    L58:
        float r103 = r142.right;
        float r152 = r27.left;
        if (r103 < r152) goto L83;
        if (r142.left >= r152) goto L65;
        r142.left = r152;
    L65:
        if (tmpRadii != null) goto L68;
        tmpRadii = new float[8];
    L68:
        if (r122 == r11) goto L78;
        if (r95 == 0) goto L73;
        char r24 = 4;
        if (r142.left >= r27.left) goto L79;
    L74:
        float r104 = r0.timestampsAppearing;
        if (r122 < r13) goto L77;
        float[] r153 = tmpRadii;
        float r20 = (0.7f * r4) * r104;
        r153[7] = r20;
        r153[6] = r20;
        r153[r17] = r20;
        r153[r16] = r20;
        r153[5] = r4;
        r153[r24] = r4;
        r153[3] = r4;
        r153[2] = r4;
    L80:
        tmpPath.addRoundRect(r142, tmpRadii, Path.Direction.CW);
        if (r95 == 0) goto L83;
    L77:
        float[] r154 = tmpRadii;
        float r202 = (0.7f * r4) * r104;
        r154[5] = r202;
        r154[r24] = r202;
        r154[3] = r202;
        r154[2] = r202;
        r154[7] = r202;
        r154[6] = r202;
        r154[r17] = r202;
        r154[r16] = r202;
    L79:
        float[] r105 = tmpRadii;
        r105[7] = r4;
        r105[6] = r4;
        r105[r17] = r4;
        r105[r16] = r4;
        float r203 = (0.7f * r4) * r0.timestampsAppearing;
        r105[5] = r203;
        r105[r24] = r203;
        r105[3] = r203;
        r105[2] = r203;
        goto L80
    L73:
        r24 = 4;
        goto L74
    L78:
        r24 = 4;
    L83:
        r122 = r122 + 1;
        r0 = this;
        r14 = r17;
        goto L29
    L55:
        r95 = r16;
        goto L56
    L48:
        r18 = 0.0f;
    L44:
        r17 = r14;
        goto L45
    L36:
        r10 = ((Float) r0.timestamps.get(r122).first).floatValue();
        goto L37
    L33:
        r16 = 0;
        r9 = ((Float) r0.timestamps.get(r122 - 1).first).floatValue();
    L84:
        r26.drawPath(tmpPath, r28);
        return;
    L17:
        r11 = -1;
        goto L18
    }

    private void updateTimestampAnimation() {
        ArrayList<Pair<Float, URLSpanNoUnderline>> r0 = this.timestamps;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.isEmpty() == false) goto L8;
        return;
    L8:
        if (this.pressed == false) goto L10;
        int r02 = this.draggingThumbX;
    L11:
        float r03 = r02 / (this.width - thumbWidth);
        int r1 = this.timestamps.size() - 1;
    L12:
        if (r1 < 0) goto L17;
        if ((((Float) this.timestamps.get(r1).first).floatValue() - 0.001f) <= r03) goto L19;
        r1 = r1 - 1;
    L19:
        if (this.timestampLabel != null) goto L21;
        this.timestampLabel = new StaticLayout[2];
    L21:
        int r04 = thumbWidth;
        this.lastWidth = Math.abs((r04 / 2.0f) - (this.width - (r04 / 2.0f))) - AndroidUtilities.dp(66.0f);
        if (r1 == this.currentTimestamp) goto L32;
        if (this.pressed == false) goto L26;
        AndroidUtilities.vibrateCursor(this.parentView);
    L26:
        this.currentTimestamp = r1;
        if (r1 < 0) goto L32;
        if (r1 >= this.timestamps.size()) goto L32;
        onTimestampUpdate((URLSpanNoUnderline) this.timestamps.get(this.currentTimestamp).second);
    L32:
        if (this.timestampChangeT >= 1.0f) goto L42;
        long r4 = Math.min(17, Math.abs(SystemClock.elapsedRealtime() - this.lastTimestampUpdate));
        if (this.timestamps.size() <= 8) goto L36;
        float r05 = 160.0f;
    L37:
        this.timestampChangeT = Math.min(this.timestampChangeT + (r4 / r05), 1.0f);
        View r06 = this.parentView;
        if (r06 == null) goto L40;
        r06.invalidate();
    L40:
        this.lastTimestampUpdate = SystemClock.elapsedRealtime();
        goto L42
    L36:
        r05 = 220.0f;
    L42:
        if (this.timestampsAppearing >= 1.0f) goto L51;
        this.timestampsAppearing = Math.min(this.timestampsAppearing + (Math.min(17, Math.abs(SystemClock.elapsedRealtime() - this.lastTimestampUpdate)) / 200.0f), 1.0f);
        View r07 = this.parentView;
        if (r07 == null) goto L46;
        r07.invalidate();
    L46:
        this.lastTimestampsAppearingUpdate = SystemClock.elapsedRealtime();
        return;
    L51:
        return;
    L17:
        r1 = -1;
        goto L19
    L10:
        r02 = this.thumbX;
        goto L11
    }

    public void clearTimestamps() {
        this.timestamps = null;
        this.currentTimestamp = -1;
        this.timestampsAppearing = 0.0f;
        StaticLayout[] r1 = this.timestampLabel;
        if (r1 == null) goto L5;
        r1[1] = null;
        r1[0] = null;
    L5:
        this.lastCaption = null;
        this.lastVideoDuration = -1;
    }

    public void draw(Canvas r12) {
        float r0 = this.alpha;
        if (r0 > 0.0f) goto L6;
        return;
    L6:
        if (r0 >= 1.0f) goto L8;
        Canvas r4 = r12;
        r4.saveLayerAlpha(0.0f, 0.0f, this.width, this.height, (int) (r0 * 255.0f), 31);
    L9:
        RectF r122 = this.rect;
        int r02 = thumbWidth;
        int r5 = this.height;
        int r7 = this.lineHeight;
        r122.set(r02 / 2, (r5 / 2) - (r7 / 2), this.width - (r02 / 2), (r5 / 2) + (r7 / 2));
        Paint r123 = paint;
        if (this.selected == false) goto L12;
        int r03 = this.backgroundSelectedColor;
    L13:
        r123.setColor(r03);
        drawProgressBar(r4, this.rect, paint);
        if (this.bufferedProgress <= 0.0f) goto L20;
        Paint r124 = paint;
        if (this.selected == false) goto L18;
        int r04 = this.backgroundSelectedColor;
    L19:
        r124.setColor(r04);
        RectF r125 = this.rect;
        int r05 = thumbWidth;
        int r3 = this.height;
        int r6 = this.lineHeight;
        r125.set(r05 / 2, (r3 / 2) - (r6 / 2), (r05 / 2) + (this.bufferedProgress * (this.width - r05)), (r3 / 2) + (r6 / 2));
        drawProgressBar(r4, this.rect, paint);
        goto L20
    L18:
        r04 = this.cacheColor;
    L20:
        RectF r126 = this.rect;
        int r06 = thumbWidth;
        float r1 = r06 / 2;
        float r52 = (this.height / 2) - (this.lineHeight / 2);
        int r07 = r06 / 2;
        if (this.pressed == false) goto L23;
        int r72 = this.draggingThumbX;
    L24:
        r126.set(r1, r52, r07 + r72, (r3 / 2) + (r6 / 2));
        paint.setColor(this.progressColor);
        drawProgressBar(r4, this.rect, paint);
        paint.setColor(this.circleColor);
        if (this.pressed == false) goto L27;
        float r127 = 8.0f;
    L28:
        float r128 = AndroidUtilities.dp(r127);
        if (this.currentRadius == r128) goto L45;
        long r08 = SystemClock.elapsedRealtime() - this.lastUpdateTime;
        if (r08 <= 18) goto L33;
        r08 = 16;
    L33:
        float r32 = this.currentRadius;
        if (r32 >= r128) goto L38;
        float r33 = r32 + (AndroidUtilities.dp(1.0f) * (r08 / 60.0f));
        this.currentRadius = r33;
        if (r33 <= r128) goto L41;
        this.currentRadius = r128;
    L41:
        View r129 = this.parentView;
        if (r129 == null) goto L45;
        r129.invalidate();
        goto L45
    L38:
        float r34 = r32 - (AndroidUtilities.dp(1.0f) * (r08 / 60.0f));
        this.currentRadius = r34;
        if (r34 >= r128) goto L41;
        this.currentRadius = r128;
    L45:
        if (this.pressed == false) goto L47;
        int r1210 = this.draggingThumbX;
    L48:
        r4.drawCircle(r1210 + (thumbWidth / 2), this.height / 2, this.currentRadius, paint);
        if (this.alpha >= 1.0f) goto L51;
        r4.restore();
    L51:
        updateTimestampAnimation();
        return;
    L47:
        r1210 = this.thumbX;
        goto L48
    L27:
        r127 = 6.0f;
        goto L28
    L23:
        r72 = this.thumbX;
        goto L24
    L12:
        r03 = this.backgroundColor;
        goto L13
    L8:
        r4 = r12;
        goto L9
    }

    public float getProgress() {
        return this.thumbX / (this.width - thumbWidth);
    }

    public int getWidth() {
        return this.width - thumbWidth;
    }

    public boolean isDragging() {
        return this.pressed;
    }

    public void onTimestampUpdate(URLSpanNoUnderline r1) {
    }

    public boolean onTouch(int r7, float r8, float r9) {
        if (r7 != 0) goto L24;
        int r72 = this.height;
        int r3 = thumbWidth;
        int r4 = (r72 - r3) / 2;
        if (r8 < (-r4)) goto L50;
        int r0 = this.width;
        if (r8 > (r0 + r4)) goto L50;
        if (r9 < 0.0f) goto L50;
        if (r9 > r72) goto L50;
        int r73 = this.thumbX;
        if ((r73 - r4) <= r8) goto L15;
    L16:
        int r74 = ((int) r8) - (r3 / 2);
        this.thumbX = r74;
        if (r74 >= 0) goto L20;
        this.thumbX = 0;
    L22:
        this.pressed = true;
        int r75 = this.thumbX;
        this.draggingThumbX = r75;
        this.thumbDX = (int) (r8 - r75);
        return true;
    L20:
        if (r74 <= (r0 - r3)) goto L22;
        this.thumbX = r0 - r3;
        goto L22
    L15:
        if (r8 <= ((r73 + r3) + r4)) goto L22;
    L50:
        return false;
    L24:
        if (r7 == 1) goto L42;
        if (r7 == 3) goto L42;
        if (r7 != 2) goto L50;
        if (this.pressed == false) goto L50;
        int r76 = (int) (r8 - this.thumbDX);
        this.draggingThumbX = r76;
        if (r76 >= 0) goto L34;
        this.draggingThumbX = 0;
    L37:
        SeekBarDelegate r77 = this.delegate;
        if (r77 == null) goto L40;
        r77.onSeekBarContinuousDrag(this.draggingThumbX / (this.width - thumbWidth));
    L40:
        return true;
    L34:
        int r82 = this.width;
        int r92 = thumbWidth;
        if (r76 <= (r82 - r92)) goto L37;
        this.draggingThumbX = r82 - r92;
    L42:
        if (this.pressed == false) goto L50;
        int r83 = this.draggingThumbX;
        this.thumbX = r83;
        if (r7 != 1) goto L48;
        SeekBarDelegate r78 = this.delegate;
        if (r78 == null) goto L48;
        r78.onSeekBarDrag(r83 / (this.width - thumbWidth));
    L48:
        this.pressed = false;
        return true;
    }

    public void setAlpha(float r1) {
        this.alpha = r1;
    }

    public void setBufferedProgress(float r1) {
        this.bufferedProgress = r1;
    }

    public void setColors(int r1, int r2, int r3, int r4, int r5) {
        this.backgroundColor = r1;
        this.cacheColor = r2;
        this.circleColor = r4;
        this.progressColor = r3;
        this.backgroundSelectedColor = r5;
    }

    public void setDelegate(SeekBarDelegate r1) {
        this.delegate = r1;
    }

    public void setParent(View r1) {
        this.parentView = r1;
    }

    public void setProgress(float r4) {
        this.thumbProgress = r4;
        int r42 = (int) Math.ceil((this.width - thumbWidth) * r4);
        this.thumbX = r42;
        if (r42 >= 0) goto L6;
        this.thumbX = 0;
        return;
    L6:
        int r0 = this.width;
        int r1 = thumbWidth;
        if (r42 <= (r0 - r1)) goto L10;
        this.thumbX = r0 - r1;
        return;
    }

    public void setSelected(boolean r1) {
        this.selected = r1;
    }

    public void setSize(int r2, int r3) {
        if (this.width == r2) goto L5;
    L7:
        this.width = r2;
        this.height = r3;
        setProgress(this.thumbProgress);
        return;
    L5:
        if (this.height != r3) goto L7;
    }

    public void updateTimestamps(MessageObject r10, Long r11) {
        if (r10 != null) goto L6;
        clearTimestamps();
        return;
    L6:
        if (r11 != null) goto L9;
        r11 = Long.valueOf(((long) r10.getDuration()) * 1000);
    L9:
        if (r11.longValue() >= 0) goto L12;
        clearTimestamps();
        return;
    L12:
        CharSequence r0 = r10.caption;
        if (r10.isYouTubeVideo() == false) goto L21;
        if (r10.youtubeDescription != null) goto L19;
        String r02 = r10.messageOwner.media.webpage.description;
        if (r02 == null) goto L19;
        r10.youtubeDescription = SpannableString.valueOf(r02);
        MessageObject.addUrlsByPattern(r10.isOut(), r10.youtubeDescription, false, 3, (int) r11.longValue(), false);
    L19:
        r0 = r10.youtubeDescription;
    L21:
        if (r0 == this.lastCaption) goto L23;
    L25:
        this.lastCaption = r0;
        this.lastVideoDuration = r11.longValue();
        if ((r0 instanceof Spanned) == true) goto L31;
        this.timestamps = null;
        this.currentTimestamp = -1;
        this.timestampsAppearing = 0.0f;
        StaticLayout[] r9 = this.timestampLabel;
        if (r9 == null) goto L73;
        r9[1] = null;
        r9[0] = null;
        return;
    L73:
        return;
    L31:
        Spanned r03 = (Spanned) r0;
        URLSpanNoUnderline[] r102 = (URLSpanNoUnderline[]) r03.getSpans(0, r03.length(), URLSpanNoUnderline.class);     // Catch: Exception -> L56
        this.timestamps = new ArrayList();
        this.timestampsAppearing = 0.0f;
        if (this.timestampLabelPaint != null) goto L36;
        TextPaint r04 = new TextPaint(1);
        this.timestampLabelPaint = r04;
        r04.setTextSize(AndroidUtilities.dp(12.0f));
        this.timestampLabelPaint.setColor(-1);
    L36:
        int r3 = 0;
    L38:
        if (r3 >= r102.length) goto L54;
        URLSpanNoUnderline r05 = r102[r3];     // Catch: Exception -> L51
        if (r05 == null) goto L53;
        if (r05.getURL() == null) goto L53;
        if (r05.label == null) goto L53;
        if (r05.getURL().startsWith("audio?") == false) goto L53;
        if (Utilities.parseInt(r05.getURL().substring(6)) < 0) goto L53;
        Emoji.replaceEmoji(new SpannableStringBuilder(r05.label), this.timestampLabelPaint.getFontMetricsInt(), false);     // Catch: Exception -> L51
        this.timestamps.add(new Pair(Float.valueOf((r4 * 1000) / r11.longValue()), r05));     // Catch: Exception -> L51
    L53:
        r3 = r3 + 1;
    L51:
        e = move-exception;
        FileLog.e(e);
        goto L53
    L54:
        Collections.sort(this.timestamps, new SeekBar$$ExternalSyntheticLambda0());
        return;
    L56:
        e = move-exception;
        FileLog.e(e);
        this.timestamps = null;
        this.currentTimestamp = -1;
        this.timestampsAppearing = 0.0f;
        StaticLayout[] r92 = this.timestampLabel;
        if (r92 == null) goto L74;
        r92[1] = null;
        r92[0] = null;
        return;
    L74:
        return;
    L23:
        if (this.lastVideoDuration != r11.longValue()) goto L25;
    }
}
