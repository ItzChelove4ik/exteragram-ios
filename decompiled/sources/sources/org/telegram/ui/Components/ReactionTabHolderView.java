package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.navigation.NavigationBarView;
import java.util.Iterator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DocumentObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.R;
import org.telegram.messenger.SvgHelper;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;

/* loaded from: classes7.dex */
public class ReactionTabHolderView extends FrameLayout {
    private Paint bgPaint;
    private int count;
    private TextView counterView;
    Drawable drawable;
    private ImageView iconView;
    private Paint outlinePaint;
    private float outlineProgress;
    View overlaySelectorView;
    private Path path;
    private float radius;
    private BackupImageView reactView;
    private ReactionsLayoutInBubble.VisibleReaction reaction;
    private RectF rect;

    public ReactionTabHolderView(Context r9) {
        super(r9);
        this.outlinePaint = new Paint(1);
        this.bgPaint = new Paint(1);
        this.path = new Path();
        this.rect = new RectF();
        this.radius = AndroidUtilities.dp(32.0f);
        View r0 = new View(r9);
        this.overlaySelectorView = r0;
        addView(r0, LayoutHelper.createFrame(-1, -1.0f));
        this.iconView = new ImageView(r9);
        Drawable r02 = ContextCompat.getDrawable(r9, R.drawable.msg_reactions_filled).mutate();
        this.drawable = r02;
        this.iconView.setImageDrawable(r02);
        addView(this.iconView, LayoutHelper.createFrameRelatively(24.0f, 24.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 8.0f, 0.0f, 8.0f, 0.0f));
        BackupImageView r03 = new BackupImageView(r9);
        this.reactView = r03;
        addView(r03, LayoutHelper.createFrameRelatively(24.0f, 24.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 8.0f, 0.0f, 8.0f, 0.0f));
        TextView r04 = new TextView(r9);
        this.counterView = r04;
        r04.setImportantForAccessibility(2);
        this.counterView.setTextColor(Theme.getColor(Theme.key_avatar_nameInMessageBlue));
        this.counterView.setTypeface(AndroidUtilities.bold());
        addView(this.counterView, LayoutHelper.createFrameRelatively(-1.0f, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 40.0f, 0.0f, 8.0f, 0.0f));
        this.outlinePaint.setStyle(Paint.Style.STROKE);
        this.outlinePaint.setStrokeWidth(AndroidUtilities.dp(1.0f));
        setWillNotDraw(false);
        setOutlineProgress(this.outlineProgress);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r5) {
        this.rect.set(0.0f, 0.0f, getWidth(), getHeight());
        RectF r0 = this.rect;
        float r1 = this.radius;
        r5.drawRoundRect(r0, r1, r1, this.bgPaint);
        super.dispatchDraw(r5);
    }

    @Override // android.view.View
    public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r6) {
        super.onInitializeAccessibilityNodeInfo(r6);
        r6.setClassName("android.widget.Button");
        r6.setClickable(true);
        if (this.outlineProgress <= 0.5d) goto L5;
        r6.setSelected(true);
    L5:
        ReactionsLayoutInBubble.VisibleReaction r0 = this.reaction;
        int r5 = this.count;
        if (r0 == null) goto L9;
        r6.setText(LocaleController.formatPluralString("AccDescrNumberOfPeopleReactions", r5, new Object[]{r0}));
        return;
    L9:
        r6.setText(LocaleController.formatPluralString("ReactionsCount", r5, new Object[0]));
    }

    public void setCounter(int r3) {
        this.count = r3;
        this.counterView.setText(String.format("%s", new Object[]{LocaleController.formatShortNumber(r3, null)}));
        this.iconView.setVisibility(0);
        this.reactView.setVisibility(8);
    }

    public void setCounter(int r9, TLRPC.ReactionCount r10) {
        int r0 = r10.count;
        this.count = r0;
        this.counterView.setText(String.format("%s", new Object[]{LocaleController.formatShortNumber(r0, null)}));
        ReactionsLayoutInBubble.VisibleReaction r102 = ReactionsLayoutInBubble.VisibleReaction.fromTL(r10.reaction);
        this.reaction = r102;
        if (r102.emojicon == null) goto L11;
        Iterator<TLRPC.TL_availableReaction> r92 = MediaDataController.getInstance(r9).getReactionsList().iterator();
    L6:
        if (r92.hasNext() == false) goto L16;
        TLRPC.TL_availableReaction r7 = r92.next();
        if (r7.reaction.equals(this.reaction.emojicon) == false) goto L6;
        SvgHelper.SvgDrawable r6 = DocumentObject.getSvgThumb(r7.static_icon, Theme.key_windowBackgroundGray, 1.0f);
        this.reactView.setImage(ImageLocation.getForDocument(r7.center_icon), "40_40_lastreactframe", "webp", r6, r7);
        this.reactView.setVisibility(0);
        this.iconView.setVisibility(8);
        return;
    L16:
        return;
    L11:
        this.reactView.setAnimatedEmojiDrawable(new AnimatedEmojiDrawable(0, r9, this.reaction.documentId));
        this.reactView.setVisibility(0);
        this.iconView.setVisibility(8);
    }

    public void setOutlineProgress(float r7) {
        this.outlineProgress = r7;
        int r0 = Theme.key_chat_inReactionButtonBackground;
        int r1 = Theme.getColor(r0);
        int r02 = ColorUtils.setAlphaComponent(Theme.getColor(r0), 16);
        int r2 = Theme.key_chat_inReactionButtonTextSelected;
        int r3 = Theme.getColor(r2);
        int r32 = ColorUtils.blendARGB(Theme.getColor(Theme.key_chat_inReactionButtonText), r3, r7);
        this.bgPaint.setColor(ColorUtils.blendARGB(r02, r1, r7));
        this.counterView.setTextColor(r32);
        this.drawable.setColorFilter(new PorterDuffColorFilter(r32, PorterDuff.Mode.MULTIPLY));
        if (r7 != 1.0f) goto L6;
        this.overlaySelectorView.setBackground(Theme.createSimpleSelectorRoundRectDrawable((int) this.radius, 0, ColorUtils.setAlphaComponent(Theme.getColor(r2), 76)));
    L8:
        invalidate();
        return;
    L6:
        if (r7 != 0.0f) goto L8;
        this.overlaySelectorView.setBackground(Theme.createSimpleSelectorRoundRectDrawable((int) this.radius, 0, ColorUtils.setAlphaComponent(r1, 76)));
        goto L8
    }
}
