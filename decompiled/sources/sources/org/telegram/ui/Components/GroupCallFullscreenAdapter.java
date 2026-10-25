package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.text.TextPaint;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.GroupCallUserCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.voip.GroupCallMiniTextureView;
import org.telegram.ui.Components.voip.GroupCallRenderersContainer;
import org.telegram.ui.Components.voip.GroupCallStatusIcon;
import org.telegram.ui.GroupCallActivity;

/* loaded from: classes7.dex */
public class GroupCallFullscreenAdapter extends RecyclerListView.SelectionAdapter {
    private final GroupCallActivity activity;
    private ArrayList<GroupCallMiniTextureView> attachedRenderers;
    private final int currentAccount;
    private ChatObject.Call groupCall;
    private final ArrayList<TLRPC.GroupCallParticipant> participants;
    private GroupCallRenderersContainer renderersContainer;
    private final ArrayList<ChatObject.VideoParticipant> videoParticipants;
    private boolean visible;

    /* renamed from: org.telegram.ui.Components.GroupCallFullscreenAdapter$1, reason: invalid class name */
    public class AnonymousClass1 extends DiffUtil.Callback {
        final /* synthetic */ GroupCallFullscreenAdapter this$0;
        final /* synthetic */ ArrayList val$oldParticipants;
        final /* synthetic */ ArrayList val$oldVideoParticipants;

        public AnonymousClass1(GroupCallFullscreenAdapter r1, ArrayList r2, ArrayList r3) {
            this.this$0 = r1;
            this.val$oldVideoParticipants = r2;
            this.val$oldParticipants = r3;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areContentsTheSame(int r1, int r2) {
            return true;
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public boolean areItemsTheSame(int r6, int r7) {
            if (r6 < this.val$oldVideoParticipants.size()) goto L5;
        L8:
            int r0 = r6 - this.val$oldVideoParticipants.size();
            int r1 = r7 - GroupCallFullscreenAdapter.m10837$$Nest$fgetvideoParticipants(this.this$0).size();
            if (r1 < 0) goto L20;
            if (r1 >= GroupCallFullscreenAdapter.m10835$$Nest$fgetparticipants(this.this$0).size()) goto L20;
            if (r0 < 0) goto L20;
            if (r0 >= this.val$oldParticipants.size()) goto L20;
            if (MessageObject.getPeerId(((TLRPC.GroupCallParticipant) this.val$oldParticipants.get(r0)).peer) != MessageObject.getPeerId(((TLRPC.GroupCallParticipant) GroupCallFullscreenAdapter.m10835$$Nest$fgetparticipants(this.this$0).get(r1)).peer)) goto L18;
            return true;
        L18:
            return false;
        L20:
            if (r6 >= this.val$oldVideoParticipants.size()) goto L22;
            TLRPC.GroupCallParticipant r62 = ((ChatObject.VideoParticipant) this.val$oldVideoParticipants.get(r6)).participant;
        L23:
            int r02 = GroupCallFullscreenAdapter.m10837$$Nest$fgetvideoParticipants(this.this$0).size();
            GroupCallFullscreenAdapter r5 = this.this$0;
            if (r7 >= r02) goto L26;
            TLRPC.GroupCallParticipant r52 = ((ChatObject.VideoParticipant) GroupCallFullscreenAdapter.m10837$$Nest$fgetvideoParticipants(r5).get(r7)).participant;
        L28:
            if (MessageObject.getPeerId(r62.peer) != MessageObject.getPeerId(r52.peer)) goto L30;
            return true;
        L30:
            return false;
        L26:
            r52 = (TLRPC.GroupCallParticipant) GroupCallFullscreenAdapter.m10835$$Nest$fgetparticipants(r5).get(r1);
            goto L28
        L22:
            r62 = (TLRPC.GroupCallParticipant) this.val$oldParticipants.get(r0);
            goto L23
        L5:
            if (r7 >= GroupCallFullscreenAdapter.m10837$$Nest$fgetvideoParticipants(this.this$0).size()) goto L8;
            return ((ChatObject.VideoParticipant) this.val$oldVideoParticipants.get(r6)).equals(GroupCallFullscreenAdapter.m10837$$Nest$fgetvideoParticipants(this.this$0).get(r7));
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getNewListSize() {
            return GroupCallFullscreenAdapter.m10837$$Nest$fgetvideoParticipants(this.this$0).size() + GroupCallFullscreenAdapter.m10835$$Nest$fgetparticipants(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.DiffUtil.Callback
        public int getOldListSize() {
            return this.val$oldVideoParticipants.size() + this.val$oldParticipants.size();
        }
    }

    public class GroupCallUserCell extends FrameLayout implements GroupCallStatusIcon.Callback {
        boolean attached;
        AvatarDrawable avatarDrawable;
        private BackupImageView avatarImageView;
        GroupCallUserCell.AvatarWavesDrawable avatarWavesDrawable;
        Paint backgroundPaint;
        ValueAnimator colorAnimator;
        private TLRPC.Chat currentChat;
        private TLRPC.User currentUser;
        String drawingName;
        boolean hasAvatar;
        int lastColor;
        int lastWavesColor;
        RLottieImageView muteButton;
        String name;
        int nameWidth;
        TLRPC.GroupCallParticipant participant;
        long peerId;
        float progress;
        GroupCallMiniTextureView renderer;
        boolean selected;
        Paint selectionPaint;
        float selectionProgress;
        boolean skipInvalidate;
        GroupCallStatusIcon statusIcon;
        TextPaint textPaint;
        final /* synthetic */ GroupCallFullscreenAdapter this$0;
        ChatObject.VideoParticipant videoParticipant;

        /* renamed from: org.telegram.ui.Components.GroupCallFullscreenAdapter$GroupCallUserCell$1, reason: invalid class name */
        public class AnonymousClass1 extends RLottieImageView {
            final /* synthetic */ GroupCallUserCell this$1;
            final /* synthetic */ GroupCallFullscreenAdapter val$this$0;

            public AnonymousClass1(GroupCallUserCell r1, Context r2, GroupCallFullscreenAdapter r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.view.View
            public void invalidate() {
                super.invalidate();
                this.this$1.invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Components.GroupCallFullscreenAdapter$GroupCallUserCell$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ GroupCallUserCell this$1;
            final /* synthetic */ int val$newColor;
            final /* synthetic */ int val$newWavesColor;

            public AnonymousClass2(GroupCallUserCell r1, int r2, int r3) {
                this.this$1 = r1;
                this.val$newColor = r2;
                this.val$newWavesColor = r3;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r4) {
                GroupCallUserCell r42 = this.this$1;
                r42.lastColor = this.val$newColor;
                r42.lastWavesColor = this.val$newWavesColor;
                r42.muteButton.setColorFilter(new PorterDuffColorFilter(this.this$1.lastColor, PorterDuff.Mode.MULTIPLY));
                GroupCallUserCell r43 = this.this$1;
                r43.textPaint.setColor(r43.lastColor);
                GroupCallUserCell r44 = this.this$1;
                r44.selectionPaint.setColor(r44.lastWavesColor);
                GroupCallUserCell r3 = this.this$1;
                r3.avatarWavesDrawable.setColor(ColorUtils.setAlphaComponent(r3.lastWavesColor, 38));
            }
        }

        public static /* synthetic */ void $r8$lambda$w_n4bi7syf2HhwqU6xW7q4gjgTo(GroupCallUserCell r0, int r1, int r2, int r3, int r4, ValueAnimator r5) {
            r0.lambda$updateState$0(r1, r2, r3, r4, r5);
        }

        public GroupCallUserCell(GroupCallFullscreenAdapter r9, Context r10) {
            this.this$0 = r9;
            super(r10);
            this.avatarDrawable = new AvatarDrawable();
            this.backgroundPaint = new Paint(1);
            this.selectionPaint = new Paint(1);
            this.progress = 1.0f;
            this.textPaint = new TextPaint(1);
            this.avatarWavesDrawable = new GroupCallUserCell.AvatarWavesDrawable(AndroidUtilities.dp(26.0f), AndroidUtilities.dp(29.0f));
            this.avatarDrawable.setTextSize((int) (AndroidUtilities.dp(18.0f) / 1.15f));
            BackupImageView r0 = new BackupImageView(r10);
            this.avatarImageView = r0;
            r0.setRoundRadius(ExteraConfig.getAvatarCorners(40.0f));
            addView(this.avatarImageView, LayoutHelper.createFrame(40, 40.0f, 1, 0.0f, 9.0f, 0.0f, 9.0f));
            setWillNotDraw(false);
            this.backgroundPaint.setColor(Theme.getColor(Theme.key_voipgroup_listViewBackground));
            this.selectionPaint.setColor(Theme.getColor(Theme.key_voipgroup_speakingText));
            this.selectionPaint.setStyle(Paint.Style.STROKE);
            this.selectionPaint.setStrokeWidth(AndroidUtilities.dp(2.0f));
            this.textPaint.setColor(-1);
            AnonymousClass1 r02 = new AnonymousClass1(this, r10, r9);
            this.muteButton = r02;
            r02.setScaleType(ImageView.ScaleType.CENTER);
            addView(this.muteButton, LayoutHelper.createFrame(24, 24.0f));
        }

        private void drawSelection(Canvas r7) {
            boolean r0 = this.selected;
            if (r0 == false) goto L11;
            float r4 = this.selectionProgress;
            if (r4 == 1.0f) goto L11;
            float r42 = r4 + 0.10666667f;
            if (r42 <= 1.0f) goto L9;
            r42 = 1.0f;
        L10:
            setSelectedProgress(r42);
        L20:
            if (this.selectionProgress <= 0.0f) goto L23;
            float r02 = (getMeasuredWidth() / 2.0f) * (1.0f - this.progress);
            RectF r2 = AndroidUtilities.rectTmp;
            r2.set(r02, r02, getMeasuredWidth() - r02, getMeasuredHeight() - r02);
            r2.inset(this.selectionPaint.getStrokeWidth() / 2.0f, this.selectionPaint.getStrokeWidth() / 2.0f);
            r7.drawRoundRect(r2, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), this.selectionPaint);
            return;
        L23:
            return;
        L9:
            invalidate();
        L11:
            if (r0 == true) goto L20;
            float r03 = this.selectionProgress;
            if (r03 == 0.0f) goto L20;
            float r04 = r03 - 0.10666667f;
            if (r04 >= 0.0f) goto L17;
            r04 = 0.0f;
        L18:
            setSelectedProgress(r04);
            goto L20
        L17:
            invalidate();
            goto L18
        }

        private /* synthetic */ void lambda$updateState$0(int r2, int r3, int r4, int r5, ValueAnimator r6) {
            this.lastColor = ColorUtils.blendARGB(r2, r3, ((Float) r6.getAnimatedValue()).floatValue());
            this.lastWavesColor = ColorUtils.blendARGB(r4, r5, ((Float) r6.getAnimatedValue()).floatValue());
            this.muteButton.setColorFilter(new PorterDuffColorFilter(this.lastColor, PorterDuff.Mode.MULTIPLY));
            this.textPaint.setColor(this.lastColor);
            this.selectionPaint.setColor(this.lastWavesColor);
            this.avatarWavesDrawable.setColor(ColorUtils.setAlphaComponent(this.lastWavesColor, 38));
            invalidate();
        }

        private void setSelectedProgress(float r2) {
            if (this.selectionProgress == r2) goto L6;
            this.selectionProgress = r2;
            this.selectionPaint.setAlpha((int) (r2 * 255.0f));
            return;
        }

        public void attachRenderer(boolean r9) {
            if (GroupCallFullscreenAdapter.m10831$$Nest$fgetactivity(this.this$0).isDismissed() == true) goto L18;
            if (r9 == false) goto L11;
            if (this.renderer != null) goto L11;
            this.renderer = GroupCallMiniTextureView.getOrCreate(GroupCallFullscreenAdapter.m10832$$Nest$fgetattachedRenderers(this.this$0), GroupCallFullscreenAdapter.m10836$$Nest$fgetrenderersContainer(this.this$0), null, this, null, this.videoParticipant, GroupCallFullscreenAdapter.m10834$$Nest$fgetgroupCall(this.this$0), GroupCallFullscreenAdapter.m10831$$Nest$fgetactivity(this.this$0));
            return;
        L11:
            if (r9 == true) goto L17;
            GroupCallMiniTextureView r8 = this.renderer;
            if (r8 == null) goto L15;
            r8.setSecondaryView(null);
        L15:
            this.renderer = null;
            return;
        L17:
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            GroupCallMiniTextureView r0 = this.renderer;
            if (r0 == null) goto L11;
            if (r0.isFullyVisible() == false) goto L11;
            if (GroupCallFullscreenAdapter.m10831$$Nest$fgetactivity(this.this$0).drawingForBlur == true) goto L11;
            drawSelection(r6);
            return;
        L11:
            if (this.progress <= 0.0f) goto L13;
            float r02 = (getMeasuredWidth() / 2.0f) * (1.0f - this.progress);
            RectF r2 = AndroidUtilities.rectTmp;
            r2.set(r02, r02, getMeasuredWidth() - r02, getMeasuredHeight() - r02);
            r6.drawRoundRect(r2, AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.0f), this.backgroundPaint);
            drawSelection(r6);
        L13:
            float r03 = this.avatarImageView.getX() + (this.avatarImageView.getMeasuredWidth() / 2);
            float r22 = this.avatarImageView.getY() + (this.avatarImageView.getMeasuredHeight() / 2);
            this.avatarWavesDrawable.update();
            this.avatarWavesDrawable.draw(r6, r03, r22, this);
            float r23 = this.progress;
            float r04 = ((AndroidUtilities.dp(46.0f) / AndroidUtilities.dp(40.0f)) * (1.0f - r23)) + (r23 * 1.0f);
            this.avatarImageView.setScaleX(this.avatarWavesDrawable.getAvatarScale() * r04);
            this.avatarImageView.setScaleY(this.avatarWavesDrawable.getAvatarScale() * r04);
            super.dispatchDraw(r6);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if (r3 != this.muteButton) goto L7;
            return true;
        L7:
            return super.drawChild(r2, r3, r4);
        }

        public void drawOverlays(Canvas r7) {
            if (this.drawingName == null) goto L9;
            r7.save();
            int r0 = ((getMeasuredWidth() - this.nameWidth) - AndroidUtilities.dp(24.0f)) / 2;
            this.textPaint.setAlpha((int) ((this.progress * 255.0f) * getAlpha()));
            r7.drawText(this.drawingName, AndroidUtilities.dp(22.0f) + r0, AndroidUtilities.dp(69.0f), this.textPaint);
            r7.restore();
            r7.save();
            r7.translate(r0, AndroidUtilities.dp(53.0f));
            if (this.muteButton.getDrawable() == null) goto L7;
            this.muteButton.getDrawable().setAlpha((int) ((this.progress * 255.0f) * getAlpha()));
            this.muteButton.draw(r7);
            this.muteButton.getDrawable().setAlpha(255);
        L7:
            r7.restore();
            return;
        }

        public BackupImageView getAvatarImageView() {
            return this.avatarImageView;
        }

        public TLRPC.GroupCallParticipant getParticipant() {
            return this.participant;
        }

        public long getPeerId() {
            return this.peerId;
        }

        public float getProgressToFullscreen() {
            return this.progress;
        }

        public GroupCallMiniTextureView getRenderer() {
            return this.renderer;
        }

        public ChatObject.VideoParticipant getVideoParticipant() {
            return this.videoParticipant;
        }

        @Override // android.view.View
        public void invalidate() {
            if (this.skipInvalidate == false) goto L5;
            return;
        L5:
            this.skipInvalidate = true;
            super.invalidate();
            GroupCallMiniTextureView r0 = this.renderer;
            if (r0 == null) goto L8;
            r0.invalidate();
        L9:
            this.skipInvalidate = false;
            return;
        L8:
            GroupCallFullscreenAdapter.m10836$$Nest$fgetrenderersContainer(this.this$0).invalidate();
            goto L9
        }

        public boolean isRemoving(RecyclerListView r1) {
            if (r1.getChildAdapterPosition(this) != (-1)) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (GroupCallFullscreenAdapter.m10838$$Nest$fgetvisible(this.this$0) == true) goto L5;
        L7:
            this.attached = true;
            if (GroupCallFullscreenAdapter.m10831$$Nest$fgetactivity(this.this$0).statusIconPool.size() <= 0) goto L10;
            this.statusIcon = GroupCallFullscreenAdapter.m10831$$Nest$fgetactivity(this.this$0).statusIconPool.remove(GroupCallFullscreenAdapter.m10831$$Nest$fgetactivity(this.this$0).statusIconPool.size() - 1);
        L11:
            this.statusIcon.setCallback(this);
            this.statusIcon.setImageView(this.muteButton);
            this.statusIcon.setParticipant(this.participant, false);
            updateState(false);
            this.avatarWavesDrawable.setShowWaves(this.statusIcon.isSpeaking(), this);
            if (this.statusIcon.isSpeaking() == true) goto L15;
            this.avatarWavesDrawable.setAmplitude(0.0d);
            return;
        L15:
            return;
        L10:
            this.statusIcon = new GroupCallStatusIcon();
            goto L11
        L5:
            if (this.videoParticipant == null) goto L7;
            attachRenderer(true);
            goto L7
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            attachRenderer(false);
            this.attached = false;
            if (this.statusIcon == null) goto L5;
            GroupCallFullscreenAdapter.m10831$$Nest$fgetactivity(this.this$0).statusIconPool.add(this.statusIcon);
            this.statusIcon.setImageView(null);
            this.statusIcon.setCallback(null);
        L5:
            this.statusIcon = null;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            this.textPaint.setTextSize(AndroidUtilities.dp(12.0f));
            if (this.name == null) goto L5;
            int r32 = (int) Math.min(AndroidUtilities.dp(46.0f), this.textPaint.measureText(this.name));
            this.nameWidth = r32;
            this.drawingName = TextUtils.ellipsize(this.name, this.textPaint, r32, TextUtils.TruncateAt.END).toString();
        L5:
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(80.0f), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(80.0f), TLObject.FLAG_30));
        }

        @Override // org.telegram.ui.Components.voip.GroupCallStatusIcon.Callback
        public void onStatusChanged() {
            this.avatarWavesDrawable.setShowWaves(this.statusIcon.isSpeaking(), this);
            updateState(true);
        }

        @Override // android.view.View
        public void setAlpha(float r1) {
            super.setAlpha(r1);
        }

        public void setAmplitude(double r2) {
            GroupCallStatusIcon r0 = this.statusIcon;
            if (r0 == null) goto L5;
            r0.setAmplitude(r2);
        L5:
            this.avatarWavesDrawable.setAmplitude(r2);
        }

        public void setParticipant(ChatObject.VideoParticipant r11, TLRPC.GroupCallParticipant r12) {
            this.videoParticipant = r11;
            this.participant = r12;
            long r0 = this.peerId;
            long r2 = MessageObject.getPeerId(r12.peer);
            this.peerId = r2;
            GroupCallFullscreenAdapter r3 = this.this$0;
            boolean r6 = false;
            if (r2 <= 0) goto L9;
            this.currentUser = AccountInstance.getInstance(GroupCallFullscreenAdapter.m10833$$Nest$fgetcurrentAccount(r3)).getMessagesController().getUser(Long.valueOf(this.peerId));
            this.currentChat = null;
            this.avatarDrawable.setInfo(GroupCallFullscreenAdapter.m10833$$Nest$fgetcurrentAccount(this.this$0), this.currentUser);
            this.name = UserObject.getFirstName(this.currentUser);
            this.avatarImageView.getImageReceiver().setCurrentAccount(GroupCallFullscreenAdapter.m10833$$Nest$fgetcurrentAccount(this.this$0));
            ImageLocation r22 = ImageLocation.getForUser(this.currentUser, 1);
            if (r22 == null) goto L7;
            boolean r32 = true;
        L8:
            this.hasAvatar = r32;
            this.avatarImageView.setImage(r22, "50_50", this.avatarDrawable, this.currentUser);
        L17:
            if (r0 != this.peerId) goto L19;
            boolean r02 = true;
        L20:
            GroupCallFullscreenAdapter r1 = this.this$0;
            if (r11 != null) goto L27;
            if (GroupCallFullscreenAdapter.m10836$$Nest$fgetrenderersContainer(r1).fullscreenPeerId != MessageObject.getPeerId(r12.peer)) goto L25;
            r6 = true;
        L25:
            this.selected = r6;
        L30:
            if (r02 == false) goto L32;
        L36:
            GroupCallStatusIcon r112 = this.statusIcon;
            if (r112 == null) goto L40;
            r112.setParticipant(r12, r02);
            updateState(r02);
            return;
        L40:
            return;
        L32:
            if (this.selected == false) goto L34;
            float r113 = 1.0f;
        L35:
            setSelectedProgress(r113);
            goto L36
        L34:
            r113 = 0.0f;
            goto L35
        L27:
            if (GroupCallFullscreenAdapter.m10836$$Nest$fgetrenderersContainer(r1).fullscreenParticipant == null) goto L29;
            this.selected = GroupCallFullscreenAdapter.m10836$$Nest$fgetrenderersContainer(this.this$0).fullscreenParticipant.equals(r11);
            goto L30
        L29:
            this.selected = false;
            goto L30
        L19:
            r02 = false;
            goto L20
        L7:
            r32 = false;
            goto L8
        L9:
            this.currentChat = AccountInstance.getInstance(GroupCallFullscreenAdapter.m10833$$Nest$fgetcurrentAccount(r3)).getMessagesController().getChat(Long.valueOf(-this.peerId));
            this.currentUser = null;
            this.avatarDrawable.setInfo(GroupCallFullscreenAdapter.m10833$$Nest$fgetcurrentAccount(this.this$0), this.currentChat);
            TLRPC.Chat r23 = this.currentChat;
            if (r23 == null) goto L17;
            this.name = r23.title;
            this.avatarImageView.getImageReceiver().setCurrentAccount(GroupCallFullscreenAdapter.m10833$$Nest$fgetcurrentAccount(this.this$0));
            ImageLocation r24 = ImageLocation.getForChat(this.currentChat, 1);
            if (r24 == null) goto L14;
            boolean r33 = true;
        L15:
            this.hasAvatar = r33;
            this.avatarImageView.setImage(r24, "50_50", this.avatarDrawable, this.currentChat);
            goto L17
        L14:
            r33 = false;
            goto L15
        }

        public void setProgressToFullscreen(float r5) {
            if (this.progress == r5) goto L17;
            this.progress = r5;
            BackupImageView r2 = this.avatarImageView;
            if (r5 != 1.0f) goto L11;
            r2.setTranslationY(0.0f);
            this.avatarImageView.setScaleX(1.0f);
            this.avatarImageView.setScaleY(1.0f);
            this.backgroundPaint.setAlpha(255);
            invalidate();
            GroupCallMiniTextureView r4 = this.renderer;
            if (r4 == null) goto L15;
            r4.invalidate();
            return;
        L15:
            return;
        L11:
            float r1 = (r2.getTop() + (this.avatarImageView.getMeasuredHeight() / 2.0f)) - (getMeasuredHeight() / 2.0f);
            float r3 = 1.0f - r5;
            float r22 = ((AndroidUtilities.dp(46.0f) / AndroidUtilities.dp(40.0f)) * r3) + (1.0f * r5);
            this.avatarImageView.setTranslationY((-r1) * r3);
            this.avatarImageView.setScaleX(r22);
            this.avatarImageView.setScaleY(r22);
            this.backgroundPaint.setAlpha((int) (r5 * 255.0f));
            invalidate();
            GroupCallMiniTextureView r42 = this.renderer;
            if (r42 == null) goto L16;
            r42.invalidate();
            return;
        L16:
            return;
        }

        public void setRenderer(GroupCallMiniTextureView r1) {
            this.renderer = r1;
        }

        public void updateState(boolean r9) {
            GroupCallStatusIcon r0 = this.statusIcon;
            if (r0 != null) goto L5;
            return;
        L5:
            r0.updateIcon(r9);
            if (this.statusIcon.isMutedByMe() == false) goto L10;
            int r02 = Theme.getColor(Theme.key_voipgroup_mutedByAdminIcon);
        L8:
            int r5 = r02;
            int r7 = r5;
        L13:
            if (r9 == true) goto L19;
            ValueAnimator r92 = this.colorAnimator;
            if (r92 == null) goto L17;
            r92.removeAllListeners();
            this.colorAnimator.cancel();
        L17:
            this.lastColor = r5;
            this.lastWavesColor = r7;
            this.muteButton.setColorFilter(new PorterDuffColorFilter(r5, PorterDuff.Mode.MULTIPLY));
            this.textPaint.setColor(this.lastColor);
            this.selectionPaint.setColor(r7);
            this.avatarWavesDrawable.setColor(ColorUtils.setAlphaComponent(r7, 38));
            invalidate();
            return;
        L19:
            int r4 = this.lastColor;
            int r6 = this.lastWavesColor;
            ValueAnimator r93 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.colorAnimator = r93;
            r93.addUpdateListener(new GroupCallFullscreenAdapter$GroupCallUserCell$$ExternalSyntheticLambda0(this, r4, r5, r6, r7));
            this.colorAnimator.addListener(new AnonymousClass2(this, r5, r7));
            this.colorAnimator.start();
            return;
        L10:
            if (this.statusIcon.isSpeaking() == false) goto L12;
            r02 = Theme.getColor(Theme.key_voipgroup_speakingText);
            goto L8
        L12:
            int r03 = Theme.getColor(Theme.key_voipgroup_nameText);
            r5 = r03;
            r7 = Theme.getColor(Theme.key_voipgroup_listeningText);
            goto L13
        }
    }

    /* renamed from: -$$Nest$fgetactivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCallActivity m10831$$Nest$fgetactivity(GroupCallFullscreenAdapter r0) {
        return r0.activity;
    }

    /* renamed from: -$$Nest$fgetattachedRenderers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10832$$Nest$fgetattachedRenderers(GroupCallFullscreenAdapter r0) {
        return r0.attachedRenderers;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10833$$Nest$fgetcurrentAccount(GroupCallFullscreenAdapter r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetgroupCall, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatObject.Call m10834$$Nest$fgetgroupCall(GroupCallFullscreenAdapter r0) {
        return r0.groupCall;
    }

    /* renamed from: -$$Nest$fgetparticipants, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10835$$Nest$fgetparticipants(GroupCallFullscreenAdapter r0) {
        return r0.participants;
    }

    /* renamed from: -$$Nest$fgetrenderersContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GroupCallRenderersContainer m10836$$Nest$fgetrenderersContainer(GroupCallFullscreenAdapter r0) {
        return r0.renderersContainer;
    }

    /* renamed from: -$$Nest$fgetvideoParticipants, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m10837$$Nest$fgetvideoParticipants(GroupCallFullscreenAdapter r0) {
        return r0.videoParticipants;
    }

    /* renamed from: -$$Nest$fgetvisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10838$$Nest$fgetvisible(GroupCallFullscreenAdapter r0) {
        return r0.visible;
    }

    public GroupCallFullscreenAdapter(ChatObject.Call r2, int r3, GroupCallActivity r4) {
        this.videoParticipants = new ArrayList();
        this.participants = new ArrayList();
        this.visible = false;
        this.groupCall = r2;
        this.currentAccount = r3;
        this.activity = r4;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.videoParticipants.size() + this.participants.size();
    }

    @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
    public boolean isEnabled(RecyclerView.ViewHolder r1) {
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder r4, int r5) {
        GroupCallUserCell r42 = (GroupCallUserCell) r4.itemView;
        ChatObject.VideoParticipant r0 = r42.videoParticipant;
        int r1 = this.videoParticipants.size();
        ArrayList<ChatObject.VideoParticipant> r2 = this.videoParticipants;
        if (r5 >= r1) goto L6;
        ChatObject.VideoParticipant r12 = r2.get(r5);
        TLRPC.GroupCallParticipant r52 = this.videoParticipants.get(r5).participant;
    L8:
        r42.setParticipant(r12, r52);
        if (r0 == null) goto L21;
        if (r0.equals(r12) == true) goto L21;
        if (r42.attached == false) goto L21;
        if (r42.getRenderer() == null) goto L21;
        r42.attachRenderer(false);
        if (r12 == null) goto L35;
        r42.attachRenderer(true);
        return;
    L35:
        return;
    L21:
        if (r42.attached == true) goto L23;
        return;
    L23:
        if (r42.getRenderer() != null) goto L30;
        if (r12 == null) goto L30;
        if (this.visible == false) goto L30;
        r42.attachRenderer(true);
        return;
    L30:
        if (r42.getRenderer() == null) goto L37;
        if (r12 != null) goto L38;
        r42.attachRenderer(false);
        return;
    L38:
        return;
    L37:
        return;
    L6:
        if ((r5 - r2.size()) >= this.participants.size()) goto L34;
        r52 = this.participants.get(r5 - this.videoParticipants.size());
        r12 = null;
        goto L8
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
        return new RecyclerListView.Holder(new GroupCallUserCell(this, r2.getContext()));
    }

    public void scrollTo(ChatObject.VideoParticipant r3, RecyclerListView r4) {
        LinearLayoutManager r42 = (LinearLayoutManager) r4.getLayoutManager();
        if (r42 == null) goto L13;
        int r0 = 0;
    L7:
        if (r0 >= this.videoParticipants.size()) goto L16;
        if (this.videoParticipants.get(r0).equals(r3) == true) goto L10;
        r0 = r0 + 1;
        goto L7
    L10:
        r42.scrollToPositionWithOffset(r0, AndroidUtilities.dp(13.0f));
        return;
    L16:
        return;
    }

    public void setGroupCall(ChatObject.Call r1) {
        this.groupCall = r1;
    }

    public void setRenderersPool(ArrayList<GroupCallMiniTextureView> r1, GroupCallRenderersContainer r2) {
        this.attachedRenderers = r1;
        this.renderersContainer = r2;
    }

    public void setVisibility(RecyclerListView r3, boolean r4) {
        this.visible = r4;
        int r2 = 0;
    L4:
        if (r2 >= r3.getChildCount()) goto L11;
        View r0 = r3.getChildAt(r2);
        if ((r0 instanceof GroupCallUserCell) == false) goto L10;
        GroupCallUserCell r02 = (GroupCallUserCell) r0;
        if (r02.getVideoParticipant() == null) goto L10;
        r02.attachRenderer(r4);
    L10:
        r2 = r2 + 1;
        goto L4
    }

    public void update(boolean r4, RecyclerListView r5) {
        if (this.groupCall != null) goto L5;
        return;
    L5:
        if (r4 == false) goto L14;
        ArrayList r42 = new ArrayList(this.participants);
        ArrayList r0 = new ArrayList(this.videoParticipants);
        this.participants.clear();
        ChatObject.Call r1 = this.groupCall;
        if (r1.call.rtmp_stream == true) goto L9;
        this.participants.addAll(r1.visibleParticipants);
    L9:
        this.videoParticipants.clear();
        ChatObject.Call r12 = this.groupCall;
        if (r12.call.rtmp_stream == true) goto L12;
        this.videoParticipants.addAll(r12.visibleVideoParticipants);
    L12:
        DiffUtil.calculateDiff(new AnonymousClass1(this, r0, r42)).dispatchUpdatesTo(this);
        AndroidUtilities.updateVisibleRows(r5);
        return;
    L14:
        this.participants.clear();
        ChatObject.Call r43 = this.groupCall;
        if (r43.call.rtmp_stream == true) goto L17;
        this.participants.addAll(r43.visibleParticipants);
    L17:
        this.videoParticipants.clear();
        ChatObject.Call r44 = this.groupCall;
        if (r44.call.rtmp_stream == true) goto L20;
        this.videoParticipants.addAll(r44.visibleVideoParticipants);
    L20:
        notifyDataSetChanged();
    }
}
