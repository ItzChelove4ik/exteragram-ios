package com.exteragram.messenger.utils.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.debug.DebugConfig;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.MessageObject;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.ChatActivityTopPanelLayout;
import org.telegram.ui.Components.ChatAvatarContainer;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.Components.chat.layouts.ChatActivityFadeView;

/* loaded from: classes4.dex */
public abstract class ChatHeaderUiHelper {

    public static final class ProfileTransitionState {
        private float avatarSizeDp;
        private float avatarStartY;
        private float avatarTranslation;
        private float nameTranslationX;
        private float nameTranslationY;
        private float onlineTranslationX;
        private float onlineTranslationY;

        public ProfileTransitionState() {
            this.avatarStartY = Float.NaN;
            this.avatarSizeDp = 42.0f;
            this.nameTranslationX = Float.NaN;
            this.nameTranslationY = Float.NaN;
            this.onlineTranslationX = Float.NaN;
            this.onlineTranslationY = Float.NaN;
        }

        private static int getTransitionLayoutLeft(View r2) {
            ViewGroup.LayoutParams r0 = r2.getLayoutParams();
            if ((r0 instanceof ViewGroup.MarginLayoutParams) == false) goto L7;
            return ((ViewGroup.MarginLayoutParams) r0).leftMargin;
        L7:
            return r2.getLeft();
        }

        private static int getTransitionLayoutTop(View r2) {
            ViewGroup.LayoutParams r0 = r2.getLayoutParams();
            if ((r0 instanceof ViewGroup.MarginLayoutParams) == false) goto L7;
            return ((ViewGroup.MarginLayoutParams) r0).topMargin;
        L7:
            return r2.getTop();
        }

        private void updateBadgePositions(View r4, float r5, ImageView r6, ImageView r7, ImageView r8, ImageView r9) {
            if (r6 == null) goto L5;
            r6.setTranslationX((r4.getX() + AndroidUtilities.dp(this.avatarSizeDp - 26.0f)) + r5);
            r6.setTranslationY((r4.getY() + AndroidUtilities.dp(-10.0f)) + r5);
        L5:
            if (r7 == null) goto L8;
            r7.setTranslationX((r4.getX() + AndroidUtilities.dp(this.avatarSizeDp - 14.0f)) + r5);
            r7.setTranslationY((r4.getY() + AndroidUtilities.dp(this.avatarSizeDp - 15.5f)) + r5);
        L8:
            if (r8 == null) goto L10;
            r8.setTranslationX((r4.getX() + AndroidUtilities.dp(this.avatarSizeDp - 14.0f)) + r5);
            r8.setTranslationY((r4.getY() + AndroidUtilities.dp(this.avatarSizeDp - 18.0f)) + r5);
        L10:
            if (r9 == null) goto L13;
            r9.setTranslationX((r4.getX() + AndroidUtilities.dp(this.avatarSizeDp - 14.0f)) + r5);
            r9.setTranslationY((r4.getY() + AndroidUtilities.dp(this.avatarSizeDp - 18.0f)) + r5);
            return;
        }

        public void capture(ChatAvatarContainer r3, ViewGroup r4, View r5, View r6) {
            if (r3 == null) goto L23;
            if (r4 == null) goto L26;
            BackupImageView r0 = r3.getAvatarImageView();
            if (r0 == null) goto L27;
            this.avatarTranslation = ViewPositionWatcher.computeXCoordinateInParent(r0, r4);
            this.avatarStartY = ViewPositionWatcher.computeYCoordinateInParent(r0, r4);
            if (r0.getMeasuredWidth() == 0) goto L11;
            int r02 = r0.getMeasuredWidth();
        L12:
            if (r02 <= 0) goto L15;
            this.avatarSizeDp = r02 / AndroidUtilities.density;
        L15:
            if (r3.getTitleTextView() == null) goto L18;
            if (r5 == null) goto L18;
            this.nameTranslationX = ViewPositionWatcher.computeXCoordinateInParent(r3.getTitleTextView(), r4) - getTransitionLayoutLeft(r5);
            this.nameTranslationY = ViewPositionWatcher.computeYCoordinateInParent(r3.getTitleTextView(), r4) - getTransitionLayoutTop(r5);
        L18:
            View r32 = r3.getSubtitleTextView();
            if (r32 == null) goto L24;
            if (r6 == null) goto L25;
            this.onlineTranslationX = ViewPositionWatcher.computeXCoordinateInParent(r32, r4) - getTransitionLayoutLeft(r6);
            this.onlineTranslationY = ViewPositionWatcher.computeYCoordinateInParent(r32, r4) - getTransitionLayoutTop(r6);
            return;
        L25:
            return;
        L24:
            return;
        L11:
            r02 = r0.getWidth();
            goto L12
        L27:
            return;
        L26:
            return;
        }

        public float getAvatarSizeDp() {
            return this.avatarSizeDp;
        }

        public float getAvatarStartScale() {
            return this.avatarSizeDp / 100.0f;
        }

        public float getAvatarStartY(ActionBar r4) {
            if (Float.isNaN(this.avatarStartY) == true) goto L7;
            return this.avatarStartY;
        L7:
            if (r4.getOccupyStatusBar() == false) goto L9;
            int r0 = AndroidUtilities.statusBarHeight;
        L11:
            return ((r0 + (ActionBar.getCurrentActionBarHeight() / 2.0f)) - AndroidUtilities.dp(this.avatarSizeDp / 2.0f)) + r4.getTranslationY();
        L9:
            r0 = 0;
            goto L11
        }

        public float getAvatarTranslation() {
            return this.avatarTranslation;
        }

        public float getNameTranslationX() {
            if (Float.isNaN(this.nameTranslationX) == true) goto L7;
            return this.nameTranslationX;
        L7:
            return (this.avatarTranslation - AndroidUtilities.dp(109.0f)) + AndroidUtilities.dp(this.avatarSizeDp + 6.0f);
        }

        public float getNameTranslationY(ActionBar r2) {
            if (Float.isNaN(this.nameTranslationY) == true) goto L7;
            return this.nameTranslationY;
        L7:
            if (ChatHeaderUiHelper.isMaterial3ChatHeaderStyle() == false) goto L9;
            float r0 = 1.66f;
        L11:
            return ((float) Math.floor(getAvatarStartY(r2))) + AndroidUtilities.dp(r0);
        L9:
            r0 = 1.3f;
            goto L11
        }

        public float getOnlineTranslationX() {
            if (Float.isNaN(this.onlineTranslationX) == true) goto L7;
            return this.onlineTranslationX;
        L7:
            return getNameTranslationX();
        }

        public float getOnlineTranslationY(ActionBar r2) {
            if (Float.isNaN(this.onlineTranslationY) == true) goto L7;
            return this.onlineTranslationY;
        L7:
            if (ChatHeaderUiHelper.isMaterial3ChatHeaderStyle() == false) goto L9;
            float r0 = 26.66f;
        L11:
            return ((float) Math.floor(getAvatarStartY(r2))) + AndroidUtilities.dp(r0);
        L9:
            r0 = 24.0f;
            goto L11
        }

        public void reset() {
            this.avatarTranslation = 0.0f;
            this.avatarStartY = Float.NaN;
            this.avatarSizeDp = 42.0f;
            this.nameTranslationX = Float.NaN;
            this.nameTranslationY = Float.NaN;
            this.onlineTranslationX = Float.NaN;
            this.onlineTranslationY = Float.NaN;
        }

        public void updateBadgePositionsFromCollapsedAvatar(View r9, float r10, ImageView r11, ImageView r12, ImageView r13, ImageView r14) {
            updateBadgePositions(r9, (AndroidUtilities.dp(this.avatarSizeDp) * ((r10 * 100.0f) / this.avatarSizeDp)) - AndroidUtilities.dp(r1), r11, r12, r13, r14);
        }

        public void updateBadgePositionsFromExpandedAvatar(View r9, float r10, ImageView r11, ImageView r12, ImageView r13, ImageView r14) {
            updateBadgePositions(r9, (r9.getMeasuredWidth() - AndroidUtilities.dp(this.avatarSizeDp)) * ((r10 * 100.0f) / this.avatarSizeDp), r11, r12, r13, r14);
        }
    }

    public static void applyChatHeaderGlassStyle(ActionBar r1) {
        applyChatHeaderGlassStyle(r1, isMaterial3ChatHeaderStyle());
    }

    public static void applyChatHeaderGlassStyle(ActionBar r0, boolean r1) {
        if (r1 == false) goto L5;
        r0.setDrawGlassMiddlePill(false);
        r0.setGlassShadowAlpha(0.0f);
        return;
    }

    public static int getAvatarContainerLeftMargin(boolean r0) {
        if (r0 == false) goto L6;
        return 4;
    L6:
        if (isMaterial3ChatHeaderStyle() == false) goto L9;
        return 57;
    L9:
        return 52;
    }

    public static int getAvatarInsetPx() {
        return !isMaterial3ChatHeaderStyle() ? 1 : 0;
    }

    public static int getAvatarRadius(int r1, boolean r2) {
        if (isMaterial3ChatHeaderStyle() == false) goto L7;
        return ExteraConfig.getAvatarCorners(r1, false, r2);
    L7:
        return ExteraConfig.getAvatarCorners(getAvatarSizePx(r1), true, r2);
    }

    public static int getAvatarSizePx(int r1) {
        return AndroidUtilities.dp(r1) - (getAvatarInsetPx() * 2);
    }

    public static int getChatAvatarSizeDp() {
        if (isMaterial3ChatHeaderStyle() == false) goto L6;
        return 46;
    L6:
        return 42;
    }

    public static int getChatFadeColorKey() {
        if (DebugConfig.getChatFadeUseWhiteBackground() == false) goto L7;
        return Theme.key_windowBackgroundWhite;
    L7:
        return Theme.key_windowBackgroundGray;
    }

    public static int getChatTopFadeHeight(boolean r0) {
        if (r0 == false) goto L4;
        float r02 = 78.0f;
    L6:
        return AndroidUtilities.dp(r02);
    L4:
        r02 = 48.0f;
        goto L6
    }

    public static int getChatTopFadeZone(int r0, boolean r1) {
        if (r1 == true) goto L4;
        return r0;
    L4:
        return r0 + AndroidUtilities.dp(42.0f);
    }

    public static float getFinalTopPanelHeight(float r0, ChatActivityTopPanelLayout r1) {
        return r0 + getTopPanelActionBarGapOffset(r1);
    }

    public static float getGlassPillExtraRightPaddingDp() {
        if (isMaterial3ChatHeaderStyle() == false) goto L6;
        return 0.0f;
    L6:
        return 6.0f;
    }

    private static int getScaledChatTopFadeZone(ActionBar r0, int r1, boolean r2) {
        int r02 = r0.getMeasuredHeight();
        int r22 = getChatTopFadeZone(r02, r2);
        if (r02 == 0) goto L8;
        if (r22 >= r1) goto L8;
        return r22 + Math.round((r1 - r22) * 0.5f);
    L8:
        return r1;
    }

    public static float getTopPanelActionBarGapOffset(ChatActivityTopPanelLayout r1) {
        if (isMaterial3ChatHeaderStyle() == false) goto L8;
        if (r1 != null) goto L7;
        return 0.0f;
    L7:
        return AndroidUtilities.dp(4.0f) * r1.getMetadata().getTotalVisibility();
    L8:
        return 0.0f;
    }

    public static float getTopPanelTranslationY(float r1, float r2, float r3) {
        if (isMaterial3ChatHeaderStyle() == false) goto L5;
        int r0 = -1;
    L7:
        return (r1 + AndroidUtilities.dp(r0)) - (r2 * r3);
    L5:
        r0 = -5;
        goto L7
    }

    public static boolean isLightChatStatusBar(ActionBar r1, int r2) {
        return isLightChatStatusBar(r1, r2, isMaterial3ChatHeaderStyle());
    }

    public static boolean isLightChatStatusBar(ActionBar r0, int r1, boolean r2) {
        if (r2 == true) goto L4;
    L6:
        r1 = r0.getBackgroundColor();
    L8:
        if (AndroidUtilities.computePerceivedBrightness(r1) <= 0.721f) goto L11;
        return true;
    L11:
        return false;
    L4:
        if (r0.isActionModeShowed() == true) goto L6;
        goto L6
    }

    public static boolean isMaterial3ChatHeaderStyle() {
        return ExteraConfig.getNewChatHeaderStyle();
    }

    public static boolean scrimReachesTopFade(ChatActivityFadeView r7, ViewGroup r8, View r9, MessageObject.GroupedMessages r10, float r11) {
        float r72 = r7.getFadeZoneTop();
        if (r11 < r72) goto L5;
        return false;
    L5:
        int r112 = r8.getChildCount();
        int r1 = 0;
    L7:
        if (r1 >= r112) goto L27;
        View r3 = r8.getChildAt(r1);
        if ((r3 instanceof ChatMessageCell) == false) goto L11;
        ChatMessageCell r4 = (ChatMessageCell) r3;
    L12:
        if (r3 == r9) goto L18;
        if (r10 == null) goto L26;
        if (r4 == null) goto L26;
        if (r4.getCurrentMessagesGroup() == r10) goto L18;
    L26:
        r1 = r1 + 1;
    L18:
        if (r3.getAlpha() == 0.0f) goto L26;
        if (r4 == null) goto L24;
        if (r4.getTransitionParams().animateBackgroundBoundsInner == false) goto L24;
    L25:
        return true;
    L24:
        if ((r8.getY() + r3.getY()) >= r72) goto L26;
    L11:
        r4 = null;
        goto L12
    L27:
        if (r10 == null) goto L36;
        MessageObject.GroupedMessages.TransitionParams r92 = r10.transitionParams;
        ChatMessageCell r102 = r92.cell;
        if (r102 == null) goto L36;
        float r113 = r92.top + r92.offsetTop;
        if (r92.backgroundChangeBounds == true) goto L34;
        r113 = r113 + r102.getTranslationY();
    L34:
        if ((r8.getY() + r113) >= r72) goto L36;
        return true;
    L36:
        return false;
    }

    public static void setupChatTopFade(ChatActivityFadeView r1, ActionBar r2, int r3, int r4) {
        setupChatTopFade(r1, r2, r3, r4, isMaterial3ChatHeaderStyle());
    }

    public static void setupChatTopFade(ChatActivityFadeView r1, ActionBar r2, int r3, int r4, boolean r5) {
        if (r2.getVisibility() != 0) goto L5;
        int r0 = 255;
    L6:
        r1.setFadeTopAlpha(r0);
        int r42 = getChatTopFadeZone(r4, r5);
        if (r5 == false) goto L9;
        r1.setFadeZoneTop(getScaledChatTopFadeZone(r2, r42, r5));
        r1.setTopFadeColor(r3);
    L10:
        r1.setFadeHeightTop(getChatTopFadeHeight(r5));
        return;
    L9:
        r1.setFadeZoneTop(r42);
        goto L10
    L5:
        r0 = 0;
        goto L6
    }

    public static void setupGlassAvatarContainer(ChatAvatarContainer r1) {
        r1.setGlassMode();
        if (isMaterial3ChatHeaderStyle() == false) goto L6;
        r1.setAvatarSizeInDp(46);
        return;
    }
}
