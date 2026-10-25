package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Text;

/* loaded from: classes6.dex */
public class BotAskCellDrawable extends Drawable {
    private final Drawable botLogo;
    private final int currentAccount;
    private Paint dPaint;
    private final Drawable groupsArrow;
    private int height;
    private final Theme.ResourcesProvider resourcesProvider;
    private final Text text;
    private final Text title;
    private final RectF tmpRect;
    private int width;

    public BotAskCellDrawable(Context r3, int r4, Theme.ResourcesProvider r5) {
        this.tmpRect = new RectF();
        this.dPaint = new Paint(1);
        this.currentAccount = r4;
        this.resourcesProvider = r5;
        Text r42 = new Text(LocaleController.getString(R.string.BotForumAskForStartNewChatTitle), 14.0f, AndroidUtilities.bold());
        this.title = r42;
        Layout.Alignment r52 = Layout.Alignment.ALIGN_CENTER;
        r42.align(r52);
        Text r43 = new Text(_UrlKt.FRAGMENT_ENCODE_SET, 13.0f);
        this.text = r43;
        r43.multiline(4);
        r43.align(r52);
        Drawable r44 = r3.getResources().getDrawable(R.drawable.filled_topic_new_24).mutate();
        this.botLogo = r44;
        PorterDuff.Mode r0 = PorterDuff.Mode.SRC_IN;
        r44.setColorFilter(new PorterDuffColorFilter(-1, r0));
        Drawable r32 = r3.getResources().getDrawable(R.drawable.arrow_more).mutate();
        this.groupsArrow = r32;
        r32.setColorFilter(new PorterDuffColorFilter(-1, r0));
        r32.setAlpha(153);
        this.dPaint.setColor(-16777216);
        this.dPaint.setAlpha(30);
    }

    private boolean hasGradientService() {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L7;
        return r0.hasGradientService();
    L7:
        return Theme.hasGradientService();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r11) {
        r11.drawRoundRect(this.tmpRect, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), Theme.getThemePaint("paintChatActionBackground", this.resourcesProvider));
        if (hasGradientService() == false) goto L5;
        r11.drawRoundRect(this.tmpRect, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f), Theme.getThemePaint("paintChatActionBackgroundDarken", this.resourcesProvider));
    L5:
        r11.save();
        r11.translate(0.0f, this.tmpRect.top + AndroidUtilities.dp(17.0f));
        r11.drawCircle(this.tmpRect.centerX(), AndroidUtilities.dp(35.0f), AndroidUtilities.dp(35.0f), this.dPaint);
        int r1 = (int) (this.tmpRect.centerX() - AndroidUtilities.dp(20.0f));
        int r3 = AndroidUtilities.dp(15.0f);
        this.botLogo.setBounds(r1, r3, AndroidUtilities.dp(40.0f) + r1, AndroidUtilities.dp(40.0f) + r3);
        this.botLogo.draw(r11);
        r11.translate(0.0f, AndroidUtilities.dp(70.0f));
        r11.translate(0.0f, AndroidUtilities.dp(14.0f));
        this.title.draw(r11, this.tmpRect.centerX() - (this.title.getWidth() / 2.0f), this.title.getHeight() / 2.0f, -1, 1.0f);
        r11.translate(0.0f, this.title.getHeight());
        r11.translate(0.0f, AndroidUtilities.dp(4.0f));
        this.text.draw(r11, this.tmpRect.centerX() - (this.text.getWidth() / 2.0f), 0.0f, -1, 1.0f);
        r11.translate(0.0f, this.text.getHeight());
        r11.translate(0.0f, AndroidUtilities.dp(2.0f));
        this.groupsArrow.setBounds((int) (this.tmpRect.centerX() - AndroidUtilities.dp(10.0f)), 0, (int) (this.tmpRect.centerX() + AndroidUtilities.dp(10.0f)), AndroidUtilities.dp(20.0f));
        this.groupsArrow.draw(r11);
        r11.restore();
    }

    public int getBubbleHeight() {
        return this.height;
    }

    public int getBubbleWidth() {
        return this.width;
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return 0;
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r1) {
        super.onBoundsChange(r1);
        this.tmpRect.set(r1);
    }

    public void set(long r5) {
        TLRPC.User r52 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r5));
        this.text.multiline(1);
        this.text.setMaxWidth(9999.0f);
        this.text.setText(LocaleController.formatString(R.string.BotForumAskForStartNewChat, new Object[]{UserObject.getUserName(r52)}));
        float r53 = (this.text.calculateRealWidth() / 2.0f) * 1.2f;
        this.text.multiline(4);
        float r0 = (int) (AndroidUtilities.displaySize.x * 0.95f);
        this.text.setMaxWidth(Math.min(r0, r53));
        if (this.text.getLineCount() <= 2) goto L5;
        this.text.setMaxWidth(Math.min(r0, r53 * 1.2f));
    L5:
        float r54 = Math.min(Math.max(Math.max(0.0f, this.text.calculateRealWidth()), this.title.calculateRealWidth()) + AndroidUtilities.dp(32.0f), r0);
        float r02 = ((((((((AndroidUtilities.dp(17.0f) + 0.0f) + AndroidUtilities.dp(70.0f)) + AndroidUtilities.dp(14.0f)) + this.title.getHeight()) + AndroidUtilities.dp(4.0f)) + this.text.getHeight()) + AndroidUtilities.dp(2.0f)) + AndroidUtilities.dp(20.0f)) + AndroidUtilities.dp(5.0f);
        this.width = (int) r54;
        this.height = (int) r02;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r1) {
    }
}
