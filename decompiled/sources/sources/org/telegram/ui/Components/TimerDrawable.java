package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import androidx.core.content.ContextCompat;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class TimerDrawable extends Drawable {
    Context context;
    ColorFilter currentColorFilter;
    private Drawable currentTtlIcon;
    private int iconColor;
    private boolean isDialog;
    private boolean isStaticIcon;
    private Paint linePaint;
    private boolean overrideColor;
    private Paint paint;
    Theme.ResourcesProvider resourcesProvider;
    private int time;
    private int timeHeight;
    private StaticLayout timeLayout;
    private TextPaint timePaint;
    private float timeWidth;

    public TimerDrawable(Context r3, Theme.ResourcesProvider r4) {
        this.timePaint = new TextPaint(1);
        this.paint = new Paint(1);
        this.linePaint = new Paint(1);
        this.timeWidth = 0.0f;
        this.timeHeight = 0;
        this.time = -1;
        this.context = r3;
        this.resourcesProvider = r4;
        this.timePaint.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_CONDENSED_BOLD));
        this.linePaint.setStrokeWidth(AndroidUtilities.dp(1.0f));
        this.linePaint.setStyle(Paint.Style.STROKE);
    }

    public static TimerDrawable getTtlIcon(int r3) {
        TimerDrawable r0 = new TimerDrawable(ApplicationLoader.applicationContext, null);
        r0.setTime(r3);
        r0.isStaticIcon = true;
        return r0;
    }

    public static TimerDrawable getTtlIconForDialogs(int r3) {
        TimerDrawable r0 = new TimerDrawable(ApplicationLoader.applicationContext, null);
        r0.isDialog = true;
        r0.setTime(r3);
        return r0;
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r11) {
        int r0 = getIntrinsicWidth();
        int r1 = getIntrinsicHeight();
        int r3 = -1;
        if (this.isDialog == false) goto L6;
        this.timePaint.setColor(-1);
    L13:
        if (this.currentTtlIcon == null) goto L26;
        if (this.isStaticIcon == true) goto L22;
        if (this.isDialog == true) goto L22;
        r11.drawCircle(getBounds().centerX(), getBounds().centerY(), getBounds().width() / 2.0f, this.paint);
        int r2 = Theme.getColor(Theme.key_actionBarDefaultTitle, this.resourcesProvider);
        if (this.iconColor == r2) goto L22;
        this.iconColor = r2;
        this.currentTtlIcon.setColorFilter(new PorterDuffColorFilter(r2, PorterDuff.Mode.MULTIPLY));
    L22:
        if (this.isDialog == false) goto L24;
        this.currentTtlIcon.setBounds(getBounds().left, getBounds().top, getBounds().left + this.currentTtlIcon.getIntrinsicWidth(), getBounds().top + this.currentTtlIcon.getIntrinsicHeight());
        this.currentTtlIcon.draw(r11);
        goto L26
    L24:
        Rect r22 = AndroidUtilities.rectTmp2;
        r22.set(getBounds().centerX() - AndroidUtilities.dp(10.5f), getBounds().centerY() - AndroidUtilities.dp(10.5f), (getBounds().centerX() - AndroidUtilities.dp(10.5f)) + this.currentTtlIcon.getIntrinsicWidth(), (getBounds().centerY() - AndroidUtilities.dp(10.5f)) + this.currentTtlIcon.getIntrinsicHeight());
        this.currentTtlIcon.setBounds(r22);
        this.currentTtlIcon.draw(r11);
    L26:
        if (this.time != 0) goto L28;
        return;
    L28:
        if (this.timeLayout != null) goto L30;
        return;
    L30:
        if (AndroidUtilities.density == 3.0f) goto L33;
        r3 = 0;
    L33:
        r11.save();
        if (this.isDialog == false) goto L36;
        r11.translate((float) (((getBounds().width() / 2) - Math.ceil(this.timeWidth / 2.0f)) + r3), (getBounds().height() - this.timeHeight) / 2.0f);
        this.timeLayout.draw(r11);
    L37:
        r11.restore();
        return;
    L36:
        r11.translate(((int) ((r0 / 2) - Math.ceil(this.timeWidth / 2.0f))) + r3, (r1 - this.timeHeight) / 2.0f);
        this.timeLayout.draw(r11);
        goto L37
    L6:
        if (this.isStaticIcon == false) goto L8;
        this.timePaint.setColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItemIcon, this.resourcesProvider));
        goto L13
    L8:
        if (this.overrideColor == true) goto L10;
        this.paint.setColor(Theme.getColor(Theme.key_actionBarDefault, this.resourcesProvider));
    L10:
        this.timePaint.setColor(Theme.getColor(Theme.key_actionBarDefaultTitle, this.resourcesProvider));
        goto L13
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        return AndroidUtilities.dp(23.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        return AndroidUtilities.dp(23.0f);
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        return -2;
    }

    public int getTime() {
        return this.time;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r1) {
    }

    public void setBackgroundColor(int r2) {
        this.overrideColor = true;
        this.paint.setColor(r2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        this.currentColorFilter = r2;
        if (this.isStaticIcon == false) goto L6;
        this.currentTtlIcon.setColorFilter(r2);
        return;
    }

    public void setTime(int r9) {
        if (this.time == r9) goto L66;
        this.time = r9;
        boolean r0 = this.isDialog;
        Context r1 = this.context;
        if (r0 == false) goto L7;
        this.currentTtlIcon = ContextCompat.getDrawable(r1, R.drawable.msg_autodelete_badge2).mutate();
    L11:
        invalidateSelf();
        int r02 = this.time;
        if (r02 < 1) goto L19;
        if (r02 >= 60) goto L19;
        String r92 = _UrlKt.FRAGMENT_ENCODE_SET + r9;
        if (r92.length() >= 2) goto L17;
        r92 = r92 + LocaleController.getString(R.string.SecretChatTimerSeconds);
    L17:
        String r12 = r92;
        this.timePaint.setTextSize(AndroidUtilities.dp(11.0f));
        float r93 = this.timePaint.measureText(r12);
        this.timeWidth = r93;
        if (r93 <= AndroidUtilities.dp(13.0f)) goto L56;
        this.timePaint.setTextSize(AndroidUtilities.dp(9.0f));
        this.timeWidth = this.timePaint.measureText(r12);
    L56:
        if (this.timeWidth <= AndroidUtilities.dp(13.0f)) goto L64;
        this.timePaint.setTextSize(AndroidUtilities.dp(6.0f));
        this.timeWidth = this.timePaint.measureText(r12);
    L64:
        StaticLayout r03 = new StaticLayout(r12, this.timePaint, (int) Math.ceil(this.timeWidth), Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);     // Catch: Exception -> L60
        this.timeLayout = r03;     // Catch: Exception -> L60
        this.timeHeight = r03.getHeight();     // Catch: Exception -> L60
    L62:
        invalidateSelf();
        return;
    L60:
        e = move-exception;
        this.timeLayout = null;
        FileLog.e(e);
    L19:
        if (r02 < 60) goto L25;
        if (r02 >= 3600) goto L25;
        r92 = _UrlKt.FRAGMENT_ENCODE_SET + (r9 / 60);
        if (r92.length() >= 2) goto L17;
        r92 = r92 + LocaleController.getString(R.string.SecretChatTimerMinutes);
    L25:
        if (r02 < 3600) goto L30;
        if (r02 >= 86400) goto L30;
        r92 = _UrlKt.FRAGMENT_ENCODE_SET + ((r9 / 60) / 60);
        if (r92.length() >= 2) goto L17;
        r92 = r92 + LocaleController.getString(R.string.SecretChatTimerHours);
    L30:
        if (r02 < 86400) goto L37;
        if (r02 >= 604800) goto L37;
        r92 = _UrlKt.FRAGMENT_ENCODE_SET + (((r9 / 60) / 60) / 24);
        if (r92.length() >= 2) goto L17;
        r92 = r92 + LocaleController.getString(R.string.SecretChatTimerDays);
    L37:
        if (r02 >= 2678400) goto L45;
        r92 = _UrlKt.FRAGMENT_ENCODE_SET + ((((r9 / 60) / 60) / 24) / 7);
        if (r92.length() >= 2) goto L42;
        r92 = r92 + LocaleController.getString(R.string.SecretChatTimerWeeks);
        goto L17
    L42:
        if (r92.length() <= 2) goto L17;
        r92 = "c";
        goto L17
    L45:
        if (r02 >= 31449600) goto L49;
        r92 = _UrlKt.FRAGMENT_ENCODE_SET + ((((r9 / 60) / 60) / 24) / 30);
        if (r92.length() >= 2) goto L17;
        r92 = r92 + LocaleController.getString(R.string.SecretChatTimerMonths);
        goto L17
    L49:
        r92 = _UrlKt.FRAGMENT_ENCODE_SET + ((((r9 / 60) / 60) / 24) / 364);
        if (r92.length() >= 2) goto L17;
        r92 = r92 + LocaleController.getString(R.string.SecretChatTimerYears);
        goto L17
    L7:
        if (r9 != 0) goto L9;
        int r04 = R.drawable.msg_mini_autodelete;
    L10:
        Drawable r05 = ContextCompat.getDrawable(r1, r04).mutate();
        this.currentTtlIcon = r05;
        r05.setColorFilter(this.currentColorFilter);
        goto L11
    L9:
        r04 = R.drawable.msg_mini_autodelete_empty;
        goto L10
    }
}
