package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class WebPagePreviewView extends FrameLayout {
    private final Path clipPath;
    private final int currentAccount;
    private final ReplyMessageLine line;
    private final RectF lineRect;
    private final RectF rectF;
    private final Theme.ResourcesProvider resourcesProvider;

    public WebPagePreviewView(Context r2, Theme.ResourcesProvider r3, int r4) {
        super(r2);
        ReplyMessageLine r22 = new ReplyMessageLine(this);
        this.line = r22;
        this.rectF = new RectF();
        this.lineRect = new RectF();
        this.clipPath = new Path();
        setWillNotDraw(false);
        this.resourcesProvider = r3;
        this.currentAccount = r4;
        int r1 = Theme.getColor(Theme.key_telegram_color_text, r3);
        r22.color3 = r1;
        r22.color2 = r1;
        r22.color1 = r1;
        r22.backgroundColor = Theme.multAlpha(r1, 0.1f);
        r22.hasColor3 = false;
        r22.hasColor2 = false;
        r22.resetAnimation();
    }

    public static boolean hasPreview(TLRPC.WebPage r1) {
        if (r1 != null) goto L4;
        return false;
    L4:
        if (r1.site_name == null) goto L6;
        return true;
    L6:
        if (r1.title == null) goto L8;
        return true;
    L8:
        if (r1.description == null) goto L10;
        return true;
    L10:
        if (r1.photo == null) goto L12;
        return true;
    L12:
        if (r1.document == null) goto L21;
        return true;
    L21:
        return false;
    }

    @Override // android.view.View
    public void onDraw(Canvas r6) {
        this.rectF.set(0.0f, 0.0f, getWidth(), getHeight());
        float[] r0 = this.line.radii;
        float r2 = AndroidUtilities.dp(10.0f);
        r0[7] = r2;
        r0[6] = r2;
        r0[1] = r2;
        r0[0] = r2;
        float[] r02 = this.line.radii;
        float r1 = AndroidUtilities.dp(10.0f);
        r02[5] = r1;
        r02[4] = r1;
        r02[3] = r1;
        r02[2] = r1;
        this.clipPath.rewind();
        this.clipPath.addRoundRect(this.rectF, this.line.radii, Path.Direction.CW);
        r6.save();
        r6.clipPath(this.clipPath);
        this.line.drawBackground(r6, this.rectF, 1.0f);
        this.lineRect.set(0.0f, 0.0f, AndroidUtilities.dp(3.0f), getHeight());
        this.line.drawLine(r6, this.lineRect);
        r6.restore();
    }

    public void setWebPage(TLRPC.WebPage r15) {
        removeAllViews();
        if (r15.photo == null) goto L5;
        boolean r0 = true;
    L6:
        LinearLayout r3 = new LinearLayout(getContext());
        r3.setOrientation(1);
        if (r15.site_name == null) goto L10;
        TextView r4 = new TextView(getContext());
        r4.setTypeface(AndroidUtilities.bold());
        r4.setText(r15.site_name);
        r4.setTextSize(1, 14.0f);
        r4.setTextColor(Theme.getColor(Theme.key_telegram_color_text, this.resourcesProvider));
        r4.setSingleLine(true);
        r4.setEllipsize(TextUtils.TruncateAt.END);
        r3.addView(r4, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 0.0f));
    L10:
        if (r15.title == null) goto L13;
        TextView r42 = new TextView(getContext());
        r42.setTypeface(AndroidUtilities.bold());
        r42.setText(r15.title);
        r42.setTextSize(1, 14.0f);
        r42.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourcesProvider));
        r42.setSingleLine(true);
        r42.setEllipsize(TextUtils.TruncateAt.END);
        r3.addView(r42, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 0.0f));
    L13:
        if (r15.description == null) goto L15;
        TextView r43 = new TextView(getContext());
        r43.setText(r15.description);
        r43.setTextSize(1, 13.0f);
        r43.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourcesProvider));
        r43.setMaxLines(4);
        r43.setEllipsize(TextUtils.TruncateAt.END);
        r3.addView(r43, LayoutHelper.createLinear(-1, -2));
    L15:
        if (r0 == false) goto L18;
        float r44 = 56.0f;
    L19:
        addView(r3, LayoutHelper.createFrame(-1, -2.0f, 51, 0.0f, 0.0f, r44, 0.0f));
        if (r0 == false) goto L22;
        BackupImageView r45 = new BackupImageView(getContext());
        r45.setRoundRadius(AndroidUtilities.dp(6.0f));
        r45.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(6.0f), Theme.multAlpha(Theme.getColor(Theme.key_dialogTextBlack, this.resourcesProvider), 0.08f)));
        addView(r45, LayoutHelper.createFrame(48, 48.0f, 53, 0.0f, 5.0f, 0.0f, 1.0f));
        TLRPC.PhotoSize r02 = FileLoader.getClosestPhotoSizeWithSize(r15.photo.sizes, 40);
        r45.setImage(ImageLocation.getForObject(FileLoader.getClosestPhotoSizeWithSize(r15.photo.sizes, AndroidUtilities.dp(36.0f), false, r02, true), r15.photo), "48_48", ImageLocation.getForObject(r02, r15.photo), "48_48_b", null, 0, 1, r15);
    L22:
        setPadding(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(6.0f));
        return;
    L18:
        r44 = 0.0f;
        goto L19
    L5:
        r0 = false;
        goto L6
    }
}
