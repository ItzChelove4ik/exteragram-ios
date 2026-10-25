package org.telegram.ui.Stories;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.view.View;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes7.dex */
public class StoryPrivacyButton extends View {
    private final Paint arrowPaint;
    private final Path arrowPath;
    private final Paint[] backgroundPaint;
    private int bottomColor;
    private final ButtonBounce bounce;
    private final AnimatedFloat crossfadeT;
    public boolean draw;
    private boolean drawArrow;
    private final Matrix gradientMatrix;
    private final Drawable[] icon;
    private int iconResId;
    private final float[] iconSize;
    private int topColor;

    public StoryPrivacyButton(Context r9) {
        super(r9);
        this.gradientMatrix = new Matrix();
        this.backgroundPaint = new Paint[]{new Paint(1), new Paint(1)};
        this.crossfadeT = new AnimatedFloat(this, 0, 260, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.icon = new Drawable[2];
        this.iconSize = new float[2];
        Paint r8 = new Paint(1);
        this.arrowPaint = r8;
        this.arrowPath = new Path();
        this.bounce = new ButtonBounce(this, 0.6f, 5.0f);
        r8.setStyle(Paint.Style.STROKE);
        r8.setStrokeCap(Paint.Cap.ROUND);
        r8.setStrokeJoin(Paint.Join.ROUND);
        r8.setColor(-1);
    }

    private void setIcon(int r7, float r8) {
        if (r7 != R.drawable.msg_stories_closefriends) goto L6;
        setContentDescription(LocaleController.getString(R.string.StoryPrivacyOptionCloseFriends));
    L14:
        Drawable[] r0 = this.icon;
        Drawable r2 = r0[0];
        r0[1] = r2;
        float[] r4 = this.iconSize;
        r4[1] = r4[0];
        if (r2 != null) goto L17;
    L20:
        Resources r22 = getContext().getResources();
        this.iconResId = r7;
        r0[0] = r22.getDrawable(r7).mutate();
        this.icon[0].setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        this.iconSize[0] = AndroidUtilities.dpf2(r8);
        invalidate();
        return;
    L17:
        if (r7 != this.iconResId) goto L20;
        return;
    L6:
        if (r7 != R.drawable.msg_folders_private) goto L9;
        setContentDescription(LocaleController.getString(R.string.StoryPrivacyOptionContacts));
        goto L14
    L9:
        if (r7 != R.drawable.msg_folders_groups) goto L12;
        setContentDescription(LocaleController.getString(R.string.StoryPrivacyOptionSelectedContacts));
        goto L14
    L12:
        if (r7 != R.drawable.msg_folders_channels) goto L14;
        setContentDescription(LocaleController.getString(R.string.StoryPrivacyOptionEveryone));
        goto L14
    }

    private void setupGradient(int r12, int r13) {
        Paint[] r0 = this.backgroundPaint;
        r0[1].setShader(r0[0].getShader());
        if (this.topColor == r12) goto L5;
    L8:
        float r7 = AndroidUtilities.dp(23.0f);
        this.topColor = r12;
        this.bottomColor = r13;
        LinearGradient r3 = new LinearGradient(0.0f, 0.0f, 0.0f, r7, new int[]{r12, r13}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
        this.gradientMatrix.reset();
        this.gradientMatrix.postTranslate(0.0f, AndroidUtilities.dp(8.0f));
        r3.setLocalMatrix(this.gradientMatrix);
        this.backgroundPaint[0].setShader(r3);
        invalidate();
        return;
    L5:
        if (this.bottomColor != r13) goto L8;
    }

    public float getCenterX() {
        float r0 = getX() + (getWidth() / 2.0f);
        if (this.drawArrow == false) goto L5;
        int r3 = 0;
    L7:
        return r0 + r3;
    L5:
        r3 = AndroidUtilities.dp(14.0f);
        goto L7
    }

    @Override // android.view.View
    public void onDraw(Canvas r18) {
        if (this.draw == true) goto L6;
        return;
    L6:
        if (this.drawArrow == false) goto L8;
        float r2 = 0.0f;
    L10:
        if (this.drawArrow == false) goto L12;
        float r4 = AndroidUtilities.dpf2(43.0f);
    L13:
        float r5 = AndroidUtilities.dpf2(23.66f);
        RectF r6 = AndroidUtilities.rectTmp;
        r6.set(((getWidth() - r4) / 2.0f) + r2, (getHeight() - r5) / 2.0f, r2 + ((getWidth() + r4) / 2.0f), (getHeight() + r5) / 2.0f);
        float r22 = this.bounce.getScale(0.075f);
        r18.save();
        r18.scale(r22, r22, r6.centerX(), r6.centerY());
        float r23 = this.crossfadeT.set(0.0f);
        if (r23 <= 0.0f) goto L17;
        this.backgroundPaint[1].setAlpha(255);
        r18.drawRoundRect(r6, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), this.backgroundPaint[1]);
    L17:
        if (r23 >= 1.0f) goto L19;
        this.backgroundPaint[0].setAlpha((int) ((1.0f - r23) * 255.0f));
        r18.drawRoundRect(r6, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), this.backgroundPaint[0]);
    L19:
        float r7 = Math.abs(r23 - 0.5f) + 0.5f;
        if (this.icon[1] != null) goto L22;
    L28:
        float r16 = 0.5f;
    L30:
        if (this.icon[0] == null) goto L39;
        if (r23 > r16) goto L39;
        if (this.drawArrow == false) goto L36;
        float r24 = r6.left + AndroidUtilities.dpf2(14.66f);
    L37:
        Drawable r3 = this.icon[0];
        int r52 = (int) (r24 - ((this.iconSize[0] / 2.0f) * r7));
        float r10 = r6.centerY();
        float r11 = this.iconSize[0];
        r3.setBounds(r52, (int) (r10 - ((r11 / 2.0f) * r7)), (int) (r24 + ((r11 / 2.0f) * r7)), (int) (r6.centerY() + ((this.iconSize[0] / 2.0f) * r7)));
        this.icon[0].draw(r18);
        goto L39
    L36:
        r24 = r6.centerX();
    L39:
        if (this.drawArrow == false) goto L41;
        this.arrowPath.rewind();
        this.arrowPath.moveTo(r6.right - AndroidUtilities.dpf2(15.66f), r6.centerY() - AndroidUtilities.dpf2(1.33f));
        this.arrowPath.lineTo(r6.right - AndroidUtilities.dpf2(12.0f), r6.centerY() + AndroidUtilities.dpf2(2.33f));
        this.arrowPath.lineTo(r6.right - AndroidUtilities.dpf2(8.16f), r6.centerY() - AndroidUtilities.dpf2(1.33f));
        this.arrowPaint.setStrokeWidth(AndroidUtilities.dpf2(1.33f));
        r18.drawPath(this.arrowPath, this.arrowPaint);
    L41:
        r18.restore();
        return;
    L22:
        if (r23 <= 0.5f) goto L28;
        if (this.drawArrow == false) goto L26;
        float r102 = r6.left + AndroidUtilities.dpf2(14.66f);
    L27:
        Drawable r12 = this.icon[1];
        int r13 = (int) (r102 - ((this.iconSize[1] / 2.0f) * r7));
        float r14 = r6.centerY();
        float r15 = this.iconSize[1];
        r16 = 0.5f;
        r12.setBounds(r13, (int) (r14 - ((r15 / 2.0f) * r7)), (int) (r102 + ((r15 / 2.0f) * r7)), (int) (r6.centerY() + ((this.iconSize[1] / 2.0f) * r7)));
        this.icon[1].draw(r18);
        goto L30
    L26:
        r102 = r6.centerX();
        goto L27
    L12:
        r4 = AndroidUtilities.dpf2(23.66f);
        goto L13
    L8:
        r2 = AndroidUtilities.dpf2(7.0f);
        goto L10
    }

    @Override // android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(60.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(40.0f), TLObject.FLAG_30));
    }

    public boolean set(boolean r5, TL_stories.StoryItem r6, boolean r7) {
        this.drawArrow = r5;
        this.draw = true;
        int r1 = 0;
        if (r6 != null) goto L6;
        this.draw = false;
    L24:
        if (this.draw == true) goto L27;
        r1 = 8;
    L27:
        setVisibility(r1);
        invalidate();
        return this.draw;
    L6:
        if (r6.close_friends == false) goto L9;
        setIcon(R.drawable.msg_stories_closefriends, 15.0f);
        setupGradient(-7808710, -13781445);
        this.crossfadeT.set(r7, true);
        goto L24
    L9:
        if (r6.contacts == false) goto L12;
        setIcon(R.drawable.msg_folders_private, 17.33f);
        setupGradient(-3905294, -6923014);
        this.crossfadeT.set(r7, true);
        goto L24
    L12:
        if (r6.selected_contacts == true) goto L22;
        if (r5 == false) goto L19;
        ArrayList<TLRPC.PrivacyRule> r62 = r6.privacy;
        if (r62 == null) goto L22;
        if (r62.isEmpty() == true) goto L22;
    L19:
        if (r5 == false) goto L21;
        setIcon(R.drawable.msg_folders_channels, 17.33f);
        setupGradient(-15292942, -15630089);
        this.crossfadeT.set(r7, true);
        goto L24
    L21:
        this.draw = false;
    L22:
        setIcon(R.drawable.msg_folders_groups, 17.33f);
        setupGradient(-18621, -618956);
        this.crossfadeT.set(r7, true);
        goto L24
    }

    public boolean set(boolean r4, StoriesController.UploadingStory r5, boolean r6) {
        this.drawArrow = r4;
        this.draw = true;
        int r1 = 0;
        if (r5 == null) goto L19;
        StoryPrivacyBottomSheet.StoryPrivacy r52 = r5.entry.privacy;
        if (r52 == null) goto L19;
        int r53 = r52.type;
        if (r53 != 1) goto L11;
        setIcon(R.drawable.msg_stories_closefriends, 15.0f);
        setupGradient(-7808710, -13781445);
        this.crossfadeT.set(r6, !r6);
    L21:
        if (this.draw == true) goto L24;
        r1 = 8;
    L24:
        setVisibility(r1);
        invalidate();
        return this.draw;
    L11:
        if (r53 != 2) goto L14;
        setIcon(R.drawable.msg_folders_private, 17.33f);
        setupGradient(-3905294, -6923014);
        this.crossfadeT.set(r6, !r6);
        goto L21
    L14:
        if (r53 != 3) goto L16;
        setIcon(R.drawable.msg_folders_groups, 17.33f);
        setupGradient(-18621, -618956);
        this.crossfadeT.set(r6, !r6);
        goto L21
    L16:
        if (r4 == false) goto L18;
        setIcon(R.drawable.msg_folders_channels, 17.33f);
        setupGradient(-15292942, -15630089);
        this.crossfadeT.set(r6, !r6);
        goto L21
    L18:
        this.draw = false;
    L19:
        this.draw = false;
        goto L21
    }

    @Override // android.view.View
    public void setPressed(boolean r1) {
        super.setPressed(r1);
        this.bounce.setPressed(r1);
    }
}
