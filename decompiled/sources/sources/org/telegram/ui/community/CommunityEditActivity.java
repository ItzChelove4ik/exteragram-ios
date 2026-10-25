package org.telegram.ui.community;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.exteragram.messenger.AvatarCornerType;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.utils.DrawableUtils;
import org.telegram.messenger.utils.GradientProtectionDrawable;
import org.telegram.messenger.utils.TextWatcherImpl;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_communities;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.RadioButtonCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.ChatUsersActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.ImageUpdater;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadialProgressView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceColor;
import org.telegram.ui.PhotoViewer;

/* loaded from: classes7.dex */
public class CommunityEditActivity extends BaseFragment implements ImageUpdater.ImageUpdaterDelegate, NotificationCenter.NotificationCenterDelegate, FactorAnimator.Target {
    private final BoolAnimator animatorDoneVisible;
    private TLRPC.FileLocation avatar;
    private AnimatorSet avatarAnimation;
    private AvatarDrawable avatarDrawable;
    private BackupImageView avatarImage;
    private View avatarOverlay;
    private RadialProgressView avatarProgressView;
    private boolean canAllManageLinkedPeers;
    private boolean canAllManageLinkedPeersOriginal;
    private CommunityHeaderView communityHeaderView;
    private long communityId;
    private String communityNameOriginal;
    private FrameLayout containerView;
    private TLRPC.Chat currentChat;
    private TextView doneItem;
    private EditTextCell editTextCell;
    private ImageUpdater imageUpdater;
    private TLRPC.ChatFull info;
    private UniversalRecyclerView listView;
    private final AlertDialog[] progressDialog;
    private PhotoViewer.PhotoViewerProvider provider;

    /* renamed from: org.telegram.ui.community.CommunityEditActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ CommunityEditActivity this$0;

        public AnonymousClass1(CommunityEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.community.CommunityEditActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final GradientProtectionDrawable gradientProtectionDrawable;
        final GradientProtectionDrawable gradientProtectionDrawableBottom;
        final /* synthetic */ CommunityEditActivity this$0;

        public AnonymousClass2(CommunityEditActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.gradientProtectionDrawable = new GradientProtectionDrawable(2);
            this.gradientProtectionDrawableBottom = new GradientProtectionDrawable(8);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r4, View r5, long r6) {
            boolean r62 = super.drawChild(r4, r5, r6);
            if (r5 != CommunityEditActivity.m21506$$Nest$fgetlistView(this.this$0)) goto L7;
            GradientProtectionDrawable r52 = this.gradientProtectionDrawable;
            CommunityEditActivity r7 = this.this$0;
            int r0 = Theme.key_windowBackgroundGray;
            r52.setColor(r7.getThemedColor(r0));
            this.gradientProtectionDrawable.setBounds(0, 0, getWidth(), AndroidUtilities.statusBarHeight);
            this.gradientProtectionDrawable.draw(r4);
            float r53 = AndroidUtilities.getNavigationBarThirdButtonsFactor(AndroidUtilities.navigationBarHeight);
            if (r53 <= 0.0f) goto L7;
            this.gradientProtectionDrawableBottom.setAlpha((int) (r53 * 255.0f));
            this.gradientProtectionDrawableBottom.setColor(this.this$0.getThemedColor(r0));
            this.gradientProtectionDrawableBottom.setBounds(0, getHeight() - AndroidUtilities.navigationBarHeight, getWidth(), getHeight());
            this.gradientProtectionDrawableBottom.draw(r4);
        L7:
            return r62;
        }
    }

    /* renamed from: org.telegram.ui.community.CommunityEditActivity$3, reason: invalid class name */
    public class AnonymousClass3 implements TextWatcherImpl {
        final /* synthetic */ CommunityEditActivity this$0;

        public AnonymousClass3(CommunityEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
            CommunityEditActivity.m21508$$Nest$mcheckSaveButtonVisible(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.community.CommunityEditActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends TextView {
        Paint p;
        final /* synthetic */ CommunityEditActivity this$0;

        public AnonymousClass4(CommunityEditActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.p = new Paint(1);
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r13) {
            this.p.setColor(this.this$0.getThemedColor(Theme.key_featuredStickers_addButton));
            r13.drawRoundRect(0.0f, (getHeight() / 2.0f) - AndroidUtilities.dp(14.0f), getWidth(), (getHeight() / 2.0f) + AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), this.p);
            super.onDraw(r13);
        }
    }

    /* renamed from: org.telegram.ui.community.CommunityEditActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends View {
        final Paint paint;
        final /* synthetic */ CommunityEditActivity this$0;

        public AnonymousClass5(CommunityEditActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.paint = new Paint(1);
        }

        @Override // android.view.View
        public void onDraw(Canvas r10) {
            if (CommunityEditActivity.m21501$$Nest$fgetavatarImage(this.this$0) != null) goto L5;
            return;
        L5:
            if (CommunityEditActivity.m21501$$Nest$fgetavatarImage(this.this$0).getImageReceiver().hasNotThumb() == false) goto L9;
            this.paint.setColor(1426063360);
            this.paint.setAlpha((int) (CommunityEditActivity.m21501$$Nest$fgetavatarImage(this.this$0).getImageReceiver().getCurrentAlpha() * 85.0f));
            r10.drawRoundRect(0.0f, 0.0f, getWidth(), getHeight(), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f), this.paint);
            return;
        }
    }

    /* renamed from: org.telegram.ui.community.CommunityEditActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends PhotoViewer.EmptyPhotoViewerProvider {
        final /* synthetic */ CommunityEditActivity this$0;

        public AnonymousClass6(CommunityEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean canLoadMoreAvatars() {
            return false;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public PhotoViewer.PlaceProviderObject getPlaceForPhoto(MessageObject r3, TLRPC.FileLocation r4, int r5, boolean r6, boolean r7) {
            if (r4 != null) goto L5;
            return null;
        L5:
            TLRPC.Chat r52 = this.this$0.getMessagesController().getChat(Long.valueOf(CommunityEditActivity.m21504$$Nest$fgetcommunityId(this.this$0)));
            if (r52 == null) goto L12;
            TLRPC.ChatPhoto r53 = r52.photo;
            if (r53 == null) goto L12;
            TLRPC.FileLocation r54 = r53.photo_big;
            if (r54 == null) goto L12;
        L13:
            if (r54 != null) goto L15;
        L22:
            return null;
        L15:
            if (r54.local_id != r4.local_id) goto L22;
            if (r54.volume_id != r4.volume_id) goto L22;
            if (r54.dc_id != r4.dc_id) goto L22;
            int[] r32 = new int[2];
            CommunityEditActivity.m21501$$Nest$fgetavatarImage(this.this$0).getLocationInWindow(r32);
            PhotoViewer.PlaceProviderObject r42 = new PhotoViewer.PlaceProviderObject();
            r42.viewX = r32[0];
            r42.viewY = r32[1];
            r42.parentView = CommunityEditActivity.m21501$$Nest$fgetavatarImage(this.this$0);
            r42.imageReceiver = CommunityEditActivity.m21501$$Nest$fgetavatarImage(this.this$0).getImageReceiver();
            r42.dialogId = -CommunityEditActivity.m21504$$Nest$fgetcommunityId(this.this$0);
            r42.thumb = r42.imageReceiver.getBitmapSafe();
            r42.size = -1;
            r42.radius = CommunityEditActivity.m21501$$Nest$fgetavatarImage(this.this$0).getImageReceiver().getRoundRadius(true);
            r42.scale = 1.0f;
            r42.canEdit = true;
            return r42;
        L12:
            r54 = null;
            goto L13
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public int getTotalImageCount() {
            return 1;
        }

        @Override // org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean onDeletePhoto(int r1) {
            return true;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void openPhotoForEdit(String r2, String r3, boolean r4) {
            CommunityEditActivity.m21505$$Nest$fgetimageUpdater(this.this$0).openPhotoForEdit(r2, r3, 0, r4);
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public void willHidePhotoViewer() {
            CommunityEditActivity.m21501$$Nest$fgetavatarImage(this.this$0).getImageReceiver().setVisible(true, true);
        }
    }

    /* renamed from: org.telegram.ui.community.CommunityEditActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ CommunityEditActivity this$0;
        final /* synthetic */ boolean val$show;

        public AnonymousClass7(CommunityEditActivity r1, boolean r2) {
            this.this$0 = r1;
            this.val$show = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            CommunityEditActivity.m21507$$Nest$fputavatarAnimation(this.this$0, null);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (CommunityEditActivity.m21500$$Nest$fgetavatarAnimation(this.this$0) != null) goto L5;
            return;
        L5:
            if (CommunityEditActivity.m21503$$Nest$fgetavatarProgressView(this.this$0) != null) goto L8;
            return;
        L8:
            if (this.val$show == true) goto L10;
            CommunityEditActivity.m21503$$Nest$fgetavatarProgressView(this.this$0).setVisibility(4);
            CommunityEditActivity.m21502$$Nest$fgetavatarOverlay(this.this$0).setVisibility(4);
        L10:
            CommunityEditActivity.m21507$$Nest$fputavatarAnimation(this.this$0, null);
        }
    }

    public static class CommunityHeaderView extends FrameLayout implements Theme.Colorable {
        public final BackupImageView avatarView;
        private final Theme.ResourcesProvider resourcesProvider;

        public CommunityHeaderView(Context r8, Theme.ResourcesProvider r9) {
            super(r8);
            this.resourcesProvider = r9;
            BackupImageView r92 = new BackupImageView(r8);
            this.avatarView = r92;
            r92.setRoundRadius(ExteraConfig.getAvatarCorners(72.0f, false, AvatarCornerType.COMMUNITY));
            addView(r92, LayoutHelper.createFrame(72, 72.0f, 81, 0.0f, 0.0f, 0.0f, 28.0f));
            updateColors();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            super.dispatchDraw(r6);
            DrawableUtils.drawCommunityCardDrawable(r6, Theme.dialogs_communityCardsDrawable, this.avatarView.getLeft() + (this.avatarView.getWidth() / 2.0f), this.avatarView.getTop() + (this.avatarView.getHeight() / 2.0f), this.avatarView.getHeight());
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(136.0f), TLObject.FLAG_30));
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public void updateColors() {
        }
    }

    public static class EditTextCell extends FrameLayout {
        private final Theme.ResourcesProvider resourcesProvider;
        public EditTextBoldCursor textView;

        /* renamed from: org.telegram.ui.community.CommunityEditActivity$EditTextCell$1, reason: invalid class name */
        public class AnonymousClass1 extends EditTextBoldCursor {
            final /* synthetic */ EditTextCell this$0;

            public AnonymousClass1(EditTextCell r1, Context r2) {
                this.this$0 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
            public boolean onTouchEvent(MotionEvent r2) {
                if (isEnabled() == true) goto L7;
                return false;
            L7:
                return super.onTouchEvent(r2);
            }
        }

        public EditTextCell(Context r8, Theme.ResourcesProvider r9) {
            super(r8);
            this.resourcesProvider = r9;
            AnonymousClass1 r0 = new AnonymousClass1(this, r8);
            this.textView = r0;
            r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r9));
            this.textView.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, r9));
            this.textView.setHintTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText, r9));
            this.textView.setTextSize(1, 16.0f);
            this.textView.setMaxLines(Integer.MAX_VALUE);
            this.textView.setBackground(null);
            EditTextBoldCursor r82 = this.textView;
            r82.setImeOptions(r82.getImeOptions() | 268435456);
            EditTextBoldCursor r83 = this.textView;
            r83.setInputType(r83.getInputType() | 16384);
            this.textView.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(11.0f));
            this.textView.setMinHeight(AndroidUtilities.dp(50.0f));
            EditTextBoldCursor r84 = this.textView;
            if (LocaleController.isRTL == false) goto L5;
            int r92 = 5;
        L6:
            addView(r84, LayoutHelper.createFrame(-1, -2.0f, r92 | 16, 13.0f, 0.0f, 13.0f, 0.0f));
            return;
        L5:
            r92 = 3;
            goto L6
        }

        public String getText() {
            return this.textView.getText().toString();
        }
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$0dUTvpFKjQ2IHOmRlU7HvD8LFlQ(CommunityEditActivity r0, View r1, WindowInsetsCompat r2) {
        return r0.onApplyWindowInsets(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$1qTm7KGWWvIhzv_htPg2xlDsh4I(CommunityEditActivity r0, boolean r1) {
        r0.lambda$onClick$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$3pPlh3hlVlhzXHv0zkuN04zDxvY(CommunityEditActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClick(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$AJtUuJESknQtJZvBPJutf6PM4rM(CommunityEditActivity r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItems(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$M1vQzNx5PRVV5xeuIOIu9pzCE2M(CommunityEditActivity r0, UItem r1, View r2, int r3, float r4, float r5) {
        return r0.onLongClick(r1, r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$SYe-LWGXfsCkP6IaLtIuBS31nXk, reason: not valid java name */
    public static /* synthetic */ void m21495$r8$lambda$SYeLWGXfsCkP6IaLtIuBS31nXk(DialogInterface r0) {
    }

    /* renamed from: $r8$lambda$X19--lNH8XrsgOLtZAdHQTlFBLE, reason: not valid java name */
    public static /* synthetic */ void m21496$r8$lambda$X19lNH8XrsgOLtZAdHQTlFBLE(CommunityEditActivity r0) {
        r0.lambda$processDone$6();
    }

    public static /* synthetic */ void $r8$lambda$XOKIAaMFrHG8GGqu3U_2HkbU29w(CommunityEditActivity r0, long r1) {
        r0.lambda$onLongClick$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$_fHfRrssBJzP1x7DUq6dgMSNQlI(CommunityEditActivity r0) {
        r0.lambda$openSetPhotoAlert$7();
    }

    /* renamed from: $r8$lambda$adXpmFH6jIKYE-zh8jqTgxRfIXU, reason: not valid java name */
    public static /* synthetic */ void m21497$r8$lambda$adXpmFH6jIKYEzh8jqTgxRfIXU(CommunityEditActivity r0, TLRPC.Bool r1, TLRPC.TL_error r2) {
        r0.lambda$onLongClick$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$bxqaJ0t8SQ1NLHmBu8pgrd9APqE(CommunityEditActivity r0, long r1) {
        r0.lambda$onLongClick$4(r1);
    }

    /* renamed from: $r8$lambda$lJeD-1YqDhcDpmtylHlSWEvSHvg, reason: not valid java name */
    public static /* synthetic */ void m21498$r8$lambda$lJeD1YqDhcDpmtylHlSWEvSHvg(CommunityEditActivity r0, View r1) {
        r0.lambda$createView$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$uWbJkh_CvPrkSZJ_V5oh8Sr3rck(CommunityEditActivity r0, TLRPC.PhotoSize r1, TLRPC.InputFile r2, TLRPC.InputFile r3, TLRPC.VideoSize r4, double r5, String r7, TLRPC.PhotoSize r8) {
        r0.lambda$didUploadPhoto$9(r1, r2, r3, r4, r5, r7, r8);
    }

    /* renamed from: $r8$lambda$v-zHxBO5PNtEfA8qXY_wbyDHswA, reason: not valid java name */
    public static /* synthetic */ void m21499$r8$lambda$vzHxBO5PNtEfA8qXY_wbyDHswA(CommunityEditActivity r0, boolean r1, boolean r2, long r3) {
        r0.lambda$onLongClick$5(r1, r2, r3);
    }

    /* renamed from: -$$Nest$fgetavatarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatorSet m21500$$Nest$fgetavatarAnimation(CommunityEditActivity r0) {
        return r0.avatarAnimation;
    }

    /* renamed from: -$$Nest$fgetavatarImage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackupImageView m21501$$Nest$fgetavatarImage(CommunityEditActivity r0) {
        return r0.avatarImage;
    }

    /* renamed from: -$$Nest$fgetavatarOverlay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m21502$$Nest$fgetavatarOverlay(CommunityEditActivity r0) {
        return r0.avatarOverlay;
    }

    /* renamed from: -$$Nest$fgetavatarProgressView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RadialProgressView m21503$$Nest$fgetavatarProgressView(CommunityEditActivity r0) {
        return r0.avatarProgressView;
    }

    /* renamed from: -$$Nest$fgetcommunityId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m21504$$Nest$fgetcommunityId(CommunityEditActivity r2) {
        return r2.communityId;
    }

    /* renamed from: -$$Nest$fgetimageUpdater, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageUpdater m21505$$Nest$fgetimageUpdater(CommunityEditActivity r0) {
        return r0.imageUpdater;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalRecyclerView m21506$$Nest$fgetlistView(CommunityEditActivity r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fputavatarAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21507$$Nest$fputavatarAnimation(CommunityEditActivity r0, AnimatorSet r1) {
        r0.avatarAnimation = r1;
    }

    /* renamed from: -$$Nest$mcheckSaveButtonVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21508$$Nest$mcheckSaveButtonVisible(CommunityEditActivity r0) {
        r0.checkSaveButtonVisible();
    }

    public CommunityEditActivity(Bundle r7) {
        super(r7);
        this.animatorDoneVisible = new BoolAnimator(0, this, CubicBezierInterpolator.EASE_OUT_QUINT, 320);
        this.progressDialog = new AlertDialog[1];
        this.provider = new AnonymousClass6(this);
    }

    private void checkSaveButtonVisible() {
        if (this.canAllManageLinkedPeersOriginal == this.canAllManageLinkedPeers) goto L5;
    L8:
        boolean r0 = true;
    L9:
        this.animatorDoneVisible.setValue(r0, true);
        return;
    L5:
        if (TextUtils.equals(this.editTextCell.getText(), this.communityNameOriginal) == false) goto L8;
        r0 = false;
        goto L9
    }

    private void fillItems(ArrayList<UItem> r7, UniversalAdapter r8) {
        r7.add(UItem.asCustomShadow(140, this.communityHeaderView));
        int r1 = 0;
        if (ChatObject.canUserDoAdminAction(this.currentChat, 1) == false) goto L10;
        int r82 = R.drawable.outline_profile_photo;
        if (ChatObject.hasPhoto(this.currentChat) == false) goto L7;
        int r3 = R.string.CommunitySettingsChangePhoto;
    L8:
        r7.add(UItem.asButton(141, r82, LocaleController.getString(r3)).accent());
        r7.add(UItem.asSpace(2, AndroidUtilities.dp(14.0f)));
        r7.add(UItem.asHeader(0, LocaleController.getString(R.string.CommunitySectionCommunityName)));
        r7.add(UItem.asCustom(7, this.editTextCell));
        r7.add(UItem.asSpace(1, AndroidUtilities.dp(14.0f)));
        goto L10
    L7:
        r3 = R.string.CommunitySettingsSetPhoto;
    L10:
        if (ChatObject.canBlockUsers(this.currentChat) == false) goto L13;
        r7.add(UItem.asHeader(3, LocaleController.getString(R.string.CommunitySectionWhoCanAddChats)));
        r7.add(UItem.asRadio2(150, LocaleController.getString(R.string.CommunityWhoCanAddChatsAllMembers), LocaleController.getString(R.string.CommunityWhoCanAddChatsAllMembersInfo)).setChecked(this.canAllManageLinkedPeers));
        r7.add(UItem.asRadio2(151, LocaleController.getString(R.string.CommunityWhoCanAddChatsOnlyAdmins), LocaleController.getString(R.string.CommunityWhoCanAddChatsOnlyAdminsInfo)).setChecked(true ^ this.canAllManageLinkedPeers));
        r7.add(UItem.asSpace(4, AndroidUtilities.dp(14.0f)));
    L13:
        if (ChatObject.hasAdminRights(this.currentChat) == false) goto L26;
        int r83 = R.drawable.msg_admins;
        String r0 = LocaleController.getString(R.string.CommunityAdministrators);
        TLRPC.ChatFull r32 = this.info;
        String r4 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r32 == null) goto L17;
        String r33 = Integer.toString(r32.admins_count);
    L18:
        r7.add(UItem.asButton(142, r83, r0, r33));
        int r84 = R.drawable.community_requests_outline_24;
        String r02 = LocaleController.getString(R.string.CommunityPendingRequests);
        TLRPC.ChatFull r34 = this.info;
        if (r34 == null) goto L21;
        String r35 = Integer.toString(r34.requests_pending);
    L22:
        r7.add(UItem.asButton(143, r84, r02, r35));
        int r85 = R.drawable.msg_user_remove;
        String r03 = LocaleController.getString(R.string.CommunityRemovedUsers);
        TLRPC.ChatFull r36 = this.info;
        if (r36 == null) goto L25;
        r4 = Integer.toString(r36.kicked_count);
    L25:
        r7.add(UItem.asButton(144, r85, r03, r4));
        goto L26
    L21:
        r35 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L22
    L17:
        r33 = _UrlKt.FRAGMENT_ENCODE_SET;
    L26:
        r7.add(UItem.asSpace(5, AndroidUtilities.dp(14.0f)));
        r7.add(UItem.asButton(146, R.drawable.msg_groups_create, LocaleController.getString(R.string.CommunityMenuAddChat)).accent());
        TLRPC.ChatFull r86 = this.info;
        if (r86 == null) goto L33;
        ArrayList<TL_communities.CommunityPeer> r87 = r86.linked_peers;
        if (r87 == null) goto L35;
        int r04 = r87.size();
    L31:
        if (r1 >= r04) goto L36;
        TL_communities.CommunityPeer r2 = r87.get(r1);
        r1 = r1 + 1;
        long r22 = DialogObject.getPeerDialogId(r2.peer);
        r7.add(UItem.asProfileCell(getMessagesController().getUserOrChat(r22)));
        goto L31
    L36:
        return;
    L35:
        return;
    }

    private /* synthetic */ void lambda$createView$0(View r1) {
        processDone();
        finishFragment();
    }

    private /* synthetic */ void lambda$didUploadPhoto$9(TLRPC.PhotoSize r22, TLRPC.InputFile r23, TLRPC.InputFile r24, TLRPC.VideoSize r25, double r26, String r28, TLRPC.PhotoSize r29) {
        TLRPC.FileLocation r2 = r22.location;
        this.avatar = r2;
        if (r23 != null) goto L8;
        if (r24 != null) goto L8;
        if (r25 != null) goto L8;
        this.avatarImage.setImage(ImageLocation.getForLocal(r2), "50_50", this.avatarDrawable, this.currentChat);
        showAvatarProgress(true, false);
    L9:
        this.listView.adapter.update(true);
        return;
    L8:
        getMessagesController().changeChatAvatar(this.communityId, null, r23, r24, r25, r26, r28, r22.location, r29.location, null);
        showAvatarProgress(false, true);
        goto L9
    }

    private /* synthetic */ void lambda$onClick$1(boolean r5) {
        finishFragment();
        getNotificationCenter().postNotificationName(NotificationCenter.needDeleteDialog, new Object[]{Long.valueOf(-this.communityId), null, this.currentChat, Boolean.valueOf(r5)});
    }

    private /* synthetic */ void lambda$onLongClick$2(long r1) {
        presentFragment(ChatActivity.of(r1));
    }

    private /* synthetic */ void lambda$onLongClick$3(TLRPC.Bool r1, TLRPC.TL_error r2) {
        if (r2 == null) goto L5;
        BulletinFactory.of(this).showForError(r2);
        return;
    }

    private /* synthetic */ void lambda$onLongClick$4(long r8) {
        MessagesController.getInstance(this.currentAccount).unlinkCommunity(r8, this.communityId, new CommunityEditActivity$$ExternalSyntheticLambda13(this));
    }

    private /* synthetic */ void lambda$onLongClick$5(boolean r8, boolean r9, long r10) {
        String r2 = LocaleController.getString(R.string.CommunityMenuRemoveFromCommunity);
        if (r8 == false) goto L5;
        int r82 = R.string.CommunityMenuRemoveBotFromCommunityConfirm;
    L8:
        AlertsCreator.showSimpleConfirmAlert(this, r2, LocaleController.getString(r82), LocaleController.getString(R.string.Remove), true, new CommunityEditActivity$$ExternalSyntheticLambda10(this, r10));
        return;
    L5:
        if (r9 == false) goto L7;
        r82 = R.string.CommunityMenuRemoveChannelFromCommunityConfirm;
        goto L8
    L7:
        r82 = R.string.CommunityMenuRemoveGroupFromCommunityConfirm;
        goto L8
    }

    private /* synthetic */ void lambda$openSetPhotoAlert$7() {
        this.avatar = null;
        MessagesController.getInstance(this.currentAccount).changeChatAvatar(this.communityId, null, null, null, null, 0.0d, null, null, null, null);
        showAvatarProgress(false, true);
        this.avatarImage.setImage(null, null, this.avatarDrawable, this.currentChat);
    }

    private /* synthetic */ void lambda$processDone$6() {
        getNotificationCenter().postNotificationName(NotificationCenter.updateInterfaces, new Object[]{Integer.valueOf(MessagesController.UPDATE_MASK_CHAT)});
    }

    private WindowInsetsCompat onApplyWindowInsets(View r2, WindowInsetsCompat r3) {
        Insets r22 = r3.getInsets(WindowInsetsCompat.Type.systemBars());
        this.listView.setPadding(0, r22.top, 0, r22.bottom);
        return WindowInsetsCompat.CONSUMED;
    }

    private void onClick(UItem r10, View r11, int r12, float r13, float r14) {
        int r112 = r10.id;
        if (r112 != 140) goto L25;
        if (this.imageUpdater.isUploadingImage() == true) goto L67;
        TLRPC.Chat r102 = getMessagesController().getChat(Long.valueOf(this.communityId));
        TLRPC.ChatPhoto r113 = r102.photo;
        if (r113 != null) goto L10;
        return;
    L10:
        if (r113.photo_big == null) goto L65;
        PhotoViewer.getInstance().setParentActivity(this);
        TLRPC.ChatPhoto r114 = r102.photo;
        int r122 = r114.dc_id;
        if (r122 == 0) goto L14;
        r114.photo_big.dc_id = r122;
    L14:
        TLRPC.ChatFull r115 = this.info;
        if (r115 == null) goto L21;
        TLRPC.Photo r116 = r115.chat_photo;
        if ((r116 instanceof TLRPC.TL_photo) == false) goto L21;
        if (r116.video_sizes.isEmpty() == true) goto L21;
        ImageLocation r117 = ImageLocation.getForPhoto(this.info.chat_photo.video_sizes.get(0), this.info.chat_photo);
    L22:
        PhotoViewer.getInstance().openPhotoWithVideo(r102.photo.photo_big, r117, this.provider);
        return;
    L21:
        r117 = null;
        goto L22
    L65:
        return;
    L67:
        return;
    L25:
        if (r112 != 141) goto L29;
        openSetPhotoAlert();
        return;
    L29:
        if (r112 != 142) goto L33;
        Bundle r103 = new Bundle();
        r103.putLong("chat_id", this.communityId);
        r103.putInt("type", 1);
        ChatUsersActivity r118 = new ChatUsersActivity(r103);
        r118.setInfo(this.info);
        presentFragment(r118);
        return;
    L33:
        if (r112 != 144) goto L37;
        Bundle r104 = new Bundle();
        r104.putLong("chat_id", this.communityId);
        r104.putInt("type", 0);
        ChatUsersActivity r119 = new ChatUsersActivity(r104);
        r119.setInfo(this.info);
        presentFragment(r119);
        return;
    L37:
        if (r112 != 143) goto L41;
        Bundle r105 = new Bundle();
        r105.putLong("community_id", this.communityId);
        presentFragment(new CommunityPendingRequestsActivity(r105));
        return;
    L41:
        if (r112 != 150) goto L45;
        setAllowedManageLinkedPeers(true);
        return;
    L45:
        if (r112 != 151) goto L49;
        setAllowedManageLinkedPeers(false);
        return;
    L49:
        if (r112 != 145) goto L53;
        AlertsCreator.createClearOrDeleteDialogAlert(this, false, this.currentChat, null, false, true, true, false, new CommunityEditActivity$$ExternalSyntheticLambda6(this));
        return;
    L53:
        if (r112 != 146) goto L56;
        CommunityUtils.showChatsToAddToCommunity(this.progressDialog, this, this.currentAccount, this.currentChat);
        return;
    L56:
        Object r9 = r10.object;
        if ((r9 instanceof TLRPC.Chat) == false) goto L61;
        presentFragment(ChatActivity.of(-((TLRPC.Chat) r9).id));
        return;
    L61:
        if ((r9 instanceof TLRPC.User) == false) goto L66;
        presentFragment(ChatActivity.of(((TLRPC.User) r9).id));
        return;
    }

    private boolean onLongClick(UItem r11, View r12, int r13, float r14, float r15) {
        Object r112 = r11.object;
        if ((r112 instanceof TLRPC.Chat) == false) goto L7;
        TLRPC.Chat r113 = (TLRPC.Chat) r112;
        long r0 = -r113.id;
        boolean r132 = ChatObject.isChannelAndNotMegaGroup(r113);
        boolean r114 = ChatObject.canRemoveChatFromCommunity(r113, this.currentChat);
        boolean r7 = r132;
        boolean r6 = false;
    L5:
        boolean r3 = r114;
        long r8 = r0;
        CommunityChatType r115 = CommunityUtils.getCommunityChatType(this.currentAccount, r8);
        if (r115 != CommunityChatType.YouAreIn) goto L12;
    L15:
        boolean r116 = true;
    L16:
        if (r3 == true) goto L19;
        if (r116 == true) goto L19;
        return false;
    L19:
        ItemOptions r2 = ItemOptions.makeOptions(this.containerView, r12);
        int r133 = R.drawable.msg_viewintopic;
        if (r6 == false) goto L22;
        int r142 = R.string.CommunityMenuViewBot;
    L25:
        r2.addIf(r116, r133, LocaleController.getString(r142), new CommunityEditActivity$$ExternalSyntheticLambda8(this, r8));
        r2.addIf(r3, R.drawable.msg_cancel, LocaleController.getString(R.string.CommunityMenuRemoveFromCommunity), true, new CommunityEditActivity$$ExternalSyntheticLambda9(this, r6, r7, r8));
        r2.setScrimViewBackground(this.listView.getClipBackground(r12, true));
        r2.show();
        return true;
    L22:
        if (r7 == false) goto L24;
        r142 = R.string.CommunityMenuViewChannel;
        goto L25
    L24:
        r142 = R.string.CommunityMenuViewGroup;
        goto L25
    L12:
        if (r115 == CommunityChatType.YouCanView) goto L15;
        r116 = false;
        goto L16
    L7:
        if ((r112 instanceof TLRPC.User) == false) goto L27;
        TLRPC.User r117 = (TLRPC.User) r112;
        r0 = r117.id;
        boolean r134 = UserObject.isBot(r117);
        r114 = ChatObject.canRemoveBotFromCommunity(r117, this.currentChat);
        r6 = r134;
        r7 = false;
        goto L5
    L27:
        return false;
    }

    private void processDone() {
        TLRPC.Chat r0 = this.currentChat;
        if (r0 != null) goto L5;
    L7:
        TLRPC.Chat r02 = this.currentChat;
        if (r02 != null) goto L10;
        return;
    L10:
        if (this.canAllManageLinkedPeers != this.canAllManageLinkedPeersOriginal) goto L12;
        return;
    L12:
        if (r02.default_banned_rights != null) goto L14;
        r02.default_banned_rights = new TLRPC.TL_chatBannedRights();
    L14:
        this.currentChat.default_banned_rights.manage_linked_peers = !this.canAllManageLinkedPeers;
        getMessagesController().setDefaultBannedRole(this.communityId, this.currentChat.default_banned_rights, false, this);
        return;
    L5:
        if (r0.title.equals(this.editTextCell.getText()) == true) goto L7;
        getMessagesController().changeChatTitle(this.currentChat.id, this.editTextCell.getText(), new CommunityEditActivity$$ExternalSyntheticLambda7(this));
        goto L7
    }

    private void setAllowedManageLinkedPeers(boolean r4) {
        if (this.canAllManageLinkedPeers != r4) goto L5;
        return;
    L5:
        RadioButtonCell r0 = (RadioButtonCell) this.listView.findViewByItemId(151);
        if (r0 == null) goto L8;
        r0.setChecked(!r4, true);
    L8:
        RadioButtonCell r02 = (RadioButtonCell) this.listView.findViewByItemId(150);
        if (r02 == null) goto L11;
        r02.setChecked(r4, true);
    L11:
        this.canAllManageLinkedPeers = r4;
        checkSaveButtonVisible();
    }

    private void showAvatarProgress(boolean r9, boolean r10) {
        if (this.avatarProgressView != null) goto L5;
        return;
    L5:
        AnimatorSet r0 = this.avatarAnimation;
        if (r0 == null) goto L9;
        r0.cancel();
        this.avatarAnimation = null;
    L9:
        if (r10 == false) goto L16;
        AnimatorSet r102 = new AnimatorSet();
        this.avatarAnimation = r102;
        RadialProgressView r3 = this.avatarProgressView;
        if (r9 == false) goto L13;
        r3.setVisibility(0);
        this.avatarOverlay.setVisibility(0);
        AnimatorSet r103 = this.avatarAnimation;
        RadialProgressView r02 = this.avatarProgressView;
        Property r32 = View.ALPHA;
        r103.playTogether(new Animator[]{ObjectAnimator.ofFloat(r02, r32, new float[]{1.0f}), ObjectAnimator.ofFloat(this.avatarOverlay, r32, new float[]{1.0f})});
    L14:
        this.avatarAnimation.setDuration(180);
        this.avatarAnimation.addListener(new AnonymousClass7(this, r9));
        this.avatarAnimation.start();
        return;
    L13:
        Property r1 = View.ALPHA;
        r102.playTogether(new Animator[]{ObjectAnimator.ofFloat(r3, r1, new float[]{0.0f}), ObjectAnimator.ofFloat(this.avatarOverlay, r1, new float[]{0.0f})});
        goto L14
    L16:
        RadialProgressView r104 = this.avatarProgressView;
        if (r9 == false) goto L20;
        r104.setAlpha(1.0f);
        this.avatarProgressView.setVisibility(0);
        this.avatarOverlay.setAlpha(1.0f);
        this.avatarOverlay.setVisibility(0);
        return;
    L20:
        r104.setAlpha(0.0f);
        this.avatarProgressView.setVisibility(4);
        this.avatarOverlay.setAlpha(0.0f);
        this.avatarOverlay.setVisibility(4);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r12) {
        setHasOwnBackground(true);
        this.actionBar.setBackButtonDrawable(new BackDrawable(false));
        this.actionBar.setAllowOverlayTitle(false);
        this.actionBar.setAddToContainer(false);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        BlurredBackgroundSourceColor r1 = new BlurredBackgroundSourceColor();
        r1.setColor(getThemedColor(Theme.key_windowBackgroundWhite));
        BlurredBackgroundDrawableViewFactory r2 = new BlurredBackgroundDrawableViewFactory(r1);
        this.actionBar.setupGlass(r2, BlurredBackgroundProviderImpl.topPanelChatActivity(this.resourceProvider));
        this.actionBar.setGlassOnlyBack();
        AnonymousClass2 r13 = new AnonymousClass2(this, r12);
        this.containerView = r13;
        r13.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.avatarDrawable = new AvatarDrawable(this.currentChat);
        CommunityHeaderView r14 = new CommunityHeaderView(r12, this.resourceProvider);
        this.communityHeaderView = r14;
        r14.avatarView.setForUserOrChat(this.currentChat, this.avatarDrawable);
        this.avatarImage = this.communityHeaderView.avatarView;
        String r15 = DialogObject.getName(this.currentChat);
        this.communityNameOriginal = r15;
        EditTextCell r22 = new EditTextCell(r12, this.resourceProvider);
        this.editTextCell = r22;
        r22.textView.setText(r15);
        this.editTextCell.textView.setSelection(r15.length());
        this.editTextCell.textView.addTextChangedListener(new AnonymousClass3(this));
        AnonymousClass4 r16 = new AnonymousClass4(this, r12);
        this.doneItem = r16;
        r16.setTextColor(getThemedColor(Theme.key_featuredStickers_buttonText));
        this.doneItem.setText(LocaleController.getString(R.string.Save));
        this.doneItem.setTypeface(AndroidUtilities.bold());
        this.doneItem.setTextSize(1, 14.0f);
        this.doneItem.setGravity(17);
        this.doneItem.setVisibility(8);
        this.doneItem.setPadding(AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), 0);
        this.doneItem.setOnClickListener(new CommunityEditActivity$$ExternalSyntheticLambda0(this));
        ScaleStateListAnimator.apply(this.doneItem);
        this.actionBar.addView(this.doneItem, LayoutHelper.createFrame(-2, 56.0f, 85, 0.0f, 0.0f, 12.0f, 0.0f));
        AnonymousClass5 r0 = new AnonymousClass5(this, r12);
        this.avatarOverlay = r0;
        this.communityHeaderView.addView(r0, LayoutHelper.createFrame(72, 72.0f, 81, 0.0f, 0.0f, 0.0f, 28.0f));
        RadialProgressView r02 = new RadialProgressView(r12);
        this.avatarProgressView = r02;
        r02.setSize(AndroidUtilities.dp(30.0f));
        this.avatarProgressView.setProgressColor(-1);
        this.avatarProgressView.setNoProgress(false);
        this.communityHeaderView.addView(this.avatarProgressView, LayoutHelper.createFrame(64, 64.0f, 81, 0.0f, 0.0f, 0.0f, 32.0f));
        showAvatarProgress(false, false);
        UniversalRecyclerView r122 = new UniversalRecyclerView(this, new CommunityEditActivity$$ExternalSyntheticLambda1(this), new CommunityEditActivity$$ExternalSyntheticLambda2(this), new CommunityEditActivity$$ExternalSyntheticLambda3(this));
        this.listView = r122;
        r122.setClipToPadding(false);
        this.listView.adapter.setApplyBackground(false);
        this.listView.setSections();
        this.actionBar.setBackground(null);
        this.containerView.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        this.containerView.addView(this.actionBar, LayoutHelper.createFrame(-1, -2, 48));
        ViewCompat.setOnApplyWindowInsetsListener(this.containerView, new CommunityEditActivity$$ExternalSyntheticLambda4(this));
        FrameLayout r123 = this.containerView;
        this.fragmentView = r123;
        return r123;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.chatInfoDidLoad) goto L8;
        TLRPC.ChatFull r32 = (TLRPC.ChatFull) r5[0];
        if (r32.id != this.communityId) goto L9;
        this.info = r32;
        this.listView.adapter.update(true);
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void didStartUpload(boolean r1, boolean r2) {
        RadialProgressView r0 = this.avatarProgressView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setProgress(0.0f);
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void didUploadPhoto(TLRPC.InputFile r4, TLRPC.InputFile r5, double r6, String r8, TLRPC.PhotoSize r9, TLRPC.PhotoSize r10, boolean r11, TLRPC.VideoSize r12) {
        AndroidUtilities.runOnUIThread(new CommunityEditActivity$$ExternalSyntheticLambda5(this, r10, r4, r5, r12, r6, r8, r9));
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void dismissCurrentDialog() {
        if (this.imageUpdater.dismissCurrentDialog(this.visibleDialog) == false) goto L5;
        return;
    L5:
        super.dismissCurrentDialog();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean dismissDialogOnPause(Dialog r2) {
        if (this.imageUpdater.dismissDialogOnPause(r2) == true) goto L5;
        return false;
    L5:
        if (super.dismissDialogOnPause(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean drawEdgeNavigationBar() {
        return false;
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public String getInitialSearchString() {
        return this.editTextCell.getText();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onActivityResultFragment(int r1, int r2, Intent r3) {
        this.imageUpdater.onActivityResult(r1, r2, r3);
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r2, float r3, float r4, FactorAnimator r5) {
        this.doneItem.setAlpha(r3);
        this.doneItem.setScaleX(AndroidUtilities.lerp(0.75f, 1.0f, r3));
        this.doneItem.setScaleY(AndroidUtilities.lerp(0.75f, 1.0f, r3));
        TextView r1 = this.doneItem;
        if (r3 <= 0.0f) goto L5;
        int r22 = 0;
    L6:
        r1.setVisibility(r22);
        return;
    L5:
        r22 = 8;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        this.communityId = this.arguments.getLong("community_id", 0);
        TLRPC.Chat r0 = getMessagesController().getChat(Long.valueOf(this.communityId));
        this.currentChat = r0;
        if (r0 == null) goto L9;
        TLRPC.TL_chatBannedRights r02 = r0.default_banned_rights;
        if (r02 != null) goto L7;
    L8:
        boolean r03 = true;
    L10:
        this.canAllManageLinkedPeersOriginal = r03;
        this.canAllManageLinkedPeers = r03;
        this.info = getMessagesController().getChatFull(this.communityId);
        ImageUpdater r04 = new ImageUpdater(true, 3, true);
        this.imageUpdater = r04;
        r04.parentFragment = this;
        r04.setDelegate(this);
        getNotificationCenter().addObserver(this, NotificationCenter.chatInfoDidLoad);
        return super.onFragmentCreate();
    L7:
        if (r02.manage_linked_peers == false) goto L8;
    L9:
        r03 = false;
        goto L10
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        getNotificationCenter().removeObserver(this, NotificationCenter.chatInfoDidLoad);
        ImageUpdater r2 = this.imageUpdater;
        if (r2 == null) goto L6;
        r2.clear();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        super.onPause();
        this.imageUpdater.onPause();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onRequestPermissionsResultFragment(int r1, String[] r2, int[] r3) {
        this.imageUpdater.onRequestPermissionsResultFragment(r1, r2, r3);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        this.imageUpdater.onResume();
    }

    @Override // org.telegram.ui.Components.ImageUpdater.ImageUpdaterDelegate
    public void onUploadProgressChanged(float r1) {
        RadialProgressView r0 = this.avatarProgressView;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setProgress(r1);
    }

    public void openSetPhotoAlert() {
        ImageUpdater r0 = this.imageUpdater;
        if (this.avatar == null) goto L5;
        boolean r1 = true;
    L6:
        r0.openMenu(r1, new CommunityEditActivity$$ExternalSyntheticLambda11(this), new CommunityEditActivity$$ExternalSyntheticLambda12(), 0);
        return;
    L5:
        r1 = false;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void restoreSelfArgs(Bundle r2) {
        ImageUpdater r1 = this.imageUpdater;
        if (r1 == null) goto L6;
        r1.currentPicturePath = r2.getString("path");
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void saveSelfArgs(Bundle r3) {
        ImageUpdater r0 = this.imageUpdater;
        if (r0 == null) goto L7;
        String r02 = r0.currentPicturePath;
        if (r02 == null) goto L7;
        r3.putString("path", r02);
    L7:
        EditTextCell r2 = this.editTextCell;
        if (r2 == null) goto L13;
        String r22 = r2.getText();
        if (r22.isEmpty() == true) goto L14;
        r3.putString("nameTextView", r22);
        return;
    L14:
        return;
    }
}
