package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.CombinedDrawable;

/* loaded from: classes7.dex */
public class AlbumButton extends View {
    private StaticLayout countLayout;
    private float countLayoutLeft;
    private float countLayoutWidth;
    private final TextPaint countPaintLayout;
    private final ImageReceiver imageReceiver;
    final float imageSize;
    private StaticLayout nameLayout;
    private float nameLayoutLeft;
    private float nameLayoutWidth;
    private final TextPaint namePaintLayout;
    private final CharSequence subtitle;
    private final CharSequence title;

    public AlbumButton(Context r10, MediaController.PhotoEntry r11, CharSequence r12, int r13, Theme.ResourcesProvider r14) {
        super(r10);
        ImageReceiver r0 = new ImageReceiver(this);
        this.imageReceiver = r0;
        TextPaint r1 = new TextPaint(1);
        this.namePaintLayout = r1;
        TextPaint r3 = new TextPaint(1);
        this.countPaintLayout = r3;
        this.imageSize = 30.0f;
        setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        setBackground(Theme.getSelectorDrawable(false));
        setMinimumWidth(AndroidUtilities.dp(196.0f));
        setLayoutParams(new LinearLayout.LayoutParams(-1, 48));
        int r4 = Theme.key_actionBarDefaultSubmenuItem;
        r1.setColor(Theme.getColor(r4, r14));
        r1.setTextSize(AndroidUtilities.dp(16.0f));
        r3.setColor(Theme.getColor(r4, r14));
        r3.setAlpha(102);
        r3.setTextSize(AndroidUtilities.dp(13.0f));
        String r8 = _UrlKt.FRAGMENT_ENCODE_SET;
        String r122 = _UrlKt.FRAGMENT_ENCODE_SET + r12;
        this.title = r122;
        this.subtitle = _UrlKt.FRAGMENT_ENCODE_SET + r13;
        r0.setRoundRadius(AndroidUtilities.dp(4.0f));
        Drawable r102 = r10.getResources().getDrawable(R.drawable.msg_media_gallery).mutate();
        r102.setColorFilter(new PorterDuffColorFilter(1308622847, PorterDuff.Mode.MULTIPLY));
        CombinedDrawable r5 = new CombinedDrawable(Theme.createRoundRectDrawable(AndroidUtilities.dp(6.0f), -13750737), r102);
        r5.setFullsize(false);
        r5.setIconSize(AndroidUtilities.dp(18.0f), AndroidUtilities.dp(18.0f));
        if (r11 == null) goto L7;
        String r103 = r11.thumbPath;
        if (r103 == null) goto L7;
        r0.setImage(ImageLocation.getForPath(r103), "30.0_30.0", null, null, r5, null, 0);
    L15:
        StringBuilder r104 = new StringBuilder();
        r104.append(r122);
        if (r13 <= 0) goto L18;
        r8 = " " + LocaleController.formatPluralStringComma("Media", r13);
    L18:
        r104.append(r8);
        setContentDescription(r104.toString());
        return;
    L7:
        if (r11 != null) goto L9;
    L14:
        r0.setImageBitmap(r5);
        goto L15
    L9:
        if (r11.path == null) goto L14;
        boolean r105 = r11.isVideo;
        int r142 = r11.imageId;
        if (r105 == false) goto L13;
        r0.setImage(ImageLocation.getForPath("vthumb://" + r142 + ":" + r11.path), "30.0_30.0", null, null, r5, null, 0);
        goto L15
    L13:
        r0.setImage(ImageLocation.getForPath("thumb://" + r142 + ":" + r11.path), "30.0_30.0", null, null, r5, null, 0);
        goto L15
    }

    private void updateLayouts(int r19) {
        StaticLayout r2 = this.nameLayout;
        if (r2 != null) goto L5;
    L8:
        TextUtils.TruncateAt r5 = TextUtils.TruncateAt.END;
        CharSequence r7 = TextUtils.ellipsize(this.title, this.namePaintLayout, r19, r5);
        TextPaint r8 = this.namePaintLayout;
        int r9 = Math.max(0, r19);
        Layout.Alignment r14 = Layout.Alignment.ALIGN_NORMAL;
        StaticLayout r6 = new StaticLayout(r7, r8, r9, r14, 1.0f, 0.0f, false);
        this.nameLayout = r6;
        float r4 = 0.0f;
        if (r6.getLineCount() <= 0) goto L11;
        float r3 = this.nameLayout.getLineLeft(0);
    L12:
        this.nameLayoutLeft = r3;
        if (this.nameLayout.getLineCount() <= 0) goto L15;
        float r32 = this.nameLayout.getLineWidth(0);
    L16:
        this.nameLayoutWidth = r32;
        int r1 = r19 - ((int) (r32 + AndroidUtilities.dp(8.0f)));
        StaticLayout r10 = new StaticLayout(TextUtils.ellipsize(this.subtitle, this.countPaintLayout, r1, r5), this.countPaintLayout, Math.max(0, r1), r14, 1.0f, 0.0f, false);
        this.countLayout = r10;
        if (r10.getLineCount() <= 0) goto L19;
        float r12 = this.countLayout.getLineLeft(0);
    L20:
        this.countLayoutLeft = r12;
        if (this.countLayout.getLineCount() <= 0) goto L23;
        r4 = this.countLayout.getLineWidth(0);
    L23:
        this.countLayoutWidth = r4;
        return;
    L19:
        r12 = 0.0f;
        goto L20
    L15:
        r32 = 0.0f;
        goto L16
    L11:
        r3 = 0.0f;
        goto L12
    L5:
        if (r2.getWidth() != r19) goto L8;
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r8) {
        float r0 = getPaddingLeft();
        this.imageReceiver.setImageCoords(r0, (getMeasuredHeight() - AndroidUtilities.dp(30.0f)) / 2.0f, AndroidUtilities.dp(30.0f), AndroidUtilities.dp(30.0f));
        this.imageReceiver.draw(r8);
        float r02 = (r0 + AndroidUtilities.dp(30.0f)) + AndroidUtilities.dp(12.0f);
        if (this.nameLayout == null) goto L6;
        r8.save();
        r8.translate(r02 - this.nameLayoutLeft, (getMeasuredHeight() - this.nameLayout.getHeight()) / 2.0f);
        this.nameLayout.draw(r8);
        r02 = (r02 + this.nameLayoutWidth) + AndroidUtilities.dp(6.0f);
        r8.restore();
    L6:
        if (this.countLayout == null) goto L9;
        r8.save();
        r8.translate(r02 - this.countLayoutLeft, ((getMeasuredHeight() - this.countLayout.getHeight()) / 2.0f) + AndroidUtilities.dpf2(1.6f));
        this.countLayout.draw(r8);
        r8.restore();
        return;
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.imageReceiver.onAttachedToWindow();
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.imageReceiver.onDetachedFromWindow();
    }

    @Override // android.view.View
    public void onMeasure(int r5, int r6) {
        updateLayouts((((View.MeasureSpec.getSize(r5) - AndroidUtilities.dp(30.0f)) - AndroidUtilities.dp(12.0f)) - getPaddingLeft()) - getPaddingRight());
        if (View.MeasureSpec.getMode(r5) != Integer.MIN_VALUE) goto L7;
        setMeasuredDimension((int) Math.min((((((getPaddingLeft() + AndroidUtilities.dp(30.0f)) + AndroidUtilities.dp(12.0f)) + this.nameLayoutWidth) + AndroidUtilities.dp(8.0f)) + this.countLayoutWidth) + getPaddingRight(), View.MeasureSpec.getSize(r5)), AndroidUtilities.dp(48.0f));
        return;
    L7:
        if (View.MeasureSpec.getMode(r5) != 1073741824) goto L10;
        setMeasuredDimension(View.MeasureSpec.getSize(r5), AndroidUtilities.dp(48.0f));
        return;
    }
}
