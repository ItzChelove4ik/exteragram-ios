package com.exteragram.messenger.drawer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.exteragram.messenger.MainMenuItem;
import com.exteragram.messenger.feed.FeedController;
import com.exteragram.messenger.utils.ui.UIUtil;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessagesStorage;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes4.dex */
public class DrawerMenuItemView extends FrameLayout {
    private static final int COLOR_KEY_ICON = 0;
    private static final int COLOR_KEY_SELECTOR = 0;
    private static final int COLOR_KEY_TEXT = 0;
    private final ImageView iconView;
    private int layoutButtonId;
    private final TextView textView;
    private final DrawerUnreadBadge unreadBadge;

    static {
        COLOR_KEY_SELECTOR = Theme.key_listSelector;
        COLOR_KEY_ICON = Theme.key_windowBackgroundWhiteGrayIcon;
        COLOR_KEY_TEXT = Theme.key_windowBackgroundWhiteBlackText;
    }

    public DrawerMenuItemView(Context r10) {
        super(r10);
        this.layoutButtonId = Integer.MIN_VALUE;
        setWillNotDraw(false);
        setLayoutParams(new FrameLayout.LayoutParams(-1, AndroidUtilities.dp(48.0f)));
        setBackground(createSelectorDrawable());
        UIUtil.applyScaleStateListAnimator(this, 16.0f, false, false, 2, 0.04f, 1.5f);
        ImageView r9 = new ImageView(r10);
        this.iconView = r9;
        r9.setScaleType(ImageView.ScaleType.CENTER);
        r9.setColorFilter(createIconColorFilter());
        addView(r9, LayoutHelper.createFrame(24, 24.0f, 19, 20.0f, 0.0f, 0.0f, 0.0f));
        TextView r92 = new TextView(r10);
        this.textView = r92;
        r92.setTextSize(1, 15.0f);
        r92.setTypeface(AndroidUtilities.bold());
        r92.setTextColor(Theme.getColor(COLOR_KEY_TEXT));
        r92.setGravity(19);
        r92.setSingleLine(true);
        r92.setEllipsize(TextUtils.TruncateAt.END);
        addView(r92, LayoutHelper.createFrame(-1, -1.0f, 19, 68.0f, 0.0f, 16.0f, 0.0f));
        this.unreadBadge = new DrawerUnreadBadge();
    }

    private static PorterDuffColorFilter createIconColorFilter() {
        return new PorterDuffColorFilter(Theme.getColor(COLOR_KEY_ICON), PorterDuff.Mode.SRC_IN);
    }

    private static Drawable createSelectorDrawable() {
        return Theme.createRadSelectorDrawable(Theme.getColor(COLOR_KEY_SELECTOR), 12, 12);
    }

    private int resolveUnreadCounter(int r3) {
        if (this.layoutButtonId != MainMenuItem.ARCHIVE.getId()) goto L7;
        return MessagesStorage.getInstance(r3).getArchiveUnreadCount();
    L7:
        if (this.layoutButtonId == MainMenuItem.FEED.getId()) goto L9;
        return 0;
    L9:
        return FeedController.getInstance(r3).getUnreadCount();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r2) {
        super.dispatchDraw(r2);
        this.unreadBadge.draw(this, r2);
    }

    public void setMenuItem(int r1, int r2, int r3, CharSequence r4) {
        this.layoutButtonId = r1;
        this.iconView.setImageResource(r3);
        this.textView.setText(r4);
        updateUnreadCounter(r2);
    }

    public void updateColors() {
        setBackground(createSelectorDrawable());
        this.iconView.setColorFilter(createIconColorFilter());
        this.textView.setTextColor(Theme.getColor(COLOR_KEY_TEXT));
        invalidate();
    }

    public void updateUnreadCounter(int r3) {
        this.unreadBadge.bind(resolveUnreadCounter(r3), this.textView);
        invalidate();
    }
}
