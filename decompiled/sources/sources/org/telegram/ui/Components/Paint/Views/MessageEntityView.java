package org.telegram.ui.Components.Paint.Views;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.GridLayoutManagerFixed;
import androidx.recyclerview.widget.RecyclerView;
import java.io.File;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatMessageSharedResources;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.MessageDrawable;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.BlurringShader;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MessageBackgroundDrawable;
import org.telegram.ui.Components.Paint.Views.EntityView;
import org.telegram.ui.Components.RectOld;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Stories.recorder.PreviewView;
import org.telegram.ui.Stories.recorder.StoryEntry;

/* loaded from: classes7.dex */
public abstract class MessageEntityView extends EntityView {
    private final BlurringShader.BlurManager blurManager;
    private boolean clipVideoMessageForBitmap;
    public final FrameLayout container;
    private final SparseIntArray currentColors;
    public boolean firstMeasure;
    private MessageObject.GroupedMessages groupedMessages;
    private boolean isDark;
    public final RecyclerListView listView;
    public final ArrayList<MessageObject> messageObjects;
    private MessageDrawable msgInDrawable;
    private MessageDrawable msgInDrawableSelected;
    private MessageDrawable msgMediaInDrawable;
    private MessageDrawable msgMediaInDrawableSelected;
    private MessageDrawable msgMediaOutDrawable;
    private MessageDrawable msgMediaOutDrawableSelected;
    private MessageDrawable msgOutDrawable;
    private MessageDrawable msgOutDrawableSelected;
    public final Theme.ResourcesProvider resourcesProvider;
    private TextureView textureView;
    private boolean textureViewActive;
    private boolean usesBackgroundPaint;
    private int videoHeight;
    private int videoWidth;

    /* renamed from: org.telegram.ui.Components.Paint.Views.MessageEntityView$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        private final Path clipPath;
        private final float[] radii;
        final /* synthetic */ MessageEntityView this$0;
        private final Matrix videoMatrix;

        public AnonymousClass1(MessageEntityView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.videoMatrix = new Matrix();
            this.radii = new float[8];
            this.clipPath = new Path();
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r10, View r11, long r12) {
            if (r11 != MessageEntityView.m11534$$Nest$fgettextureView(this.this$0)) goto L17;
            ChatMessageCell r0 = MessageEntityView.m11547$$Nest$mgetCell(this.this$0);
            int r1 = 0;
            if (r0 != null) goto L7;
            return false;
        L7:
            ImageReceiver r2 = r0.getPhotoImage();
            if (r2 != null) goto L10;
            return false;
        L10:
            this.videoMatrix.reset();
            float r3 = Math.max(r2.getImageWidth() / MessageEntityView.m11537$$Nest$fgetvideoWidth(this.this$0), r2.getImageHeight() / MessageEntityView.m11536$$Nest$fgetvideoHeight(this.this$0));
            this.videoMatrix.postScale((MessageEntityView.m11537$$Nest$fgetvideoWidth(this.this$0) / MessageEntityView.m11534$$Nest$fgettextureView(this.this$0).getWidth()) * r3, (MessageEntityView.m11536$$Nest$fgetvideoHeight(this.this$0) / MessageEntityView.m11534$$Nest$fgettextureView(this.this$0).getHeight()) * r3);
            this.videoMatrix.postTranslate(((this.this$0.listView.getX() + r0.getX()) + r2.getCenterX()) - ((MessageEntityView.m11537$$Nest$fgetvideoWidth(this.this$0) * r3) / 2.0f), ((this.this$0.listView.getY() + r0.getY()) + r2.getCenterY()) - ((MessageEntityView.m11536$$Nest$fgetvideoHeight(this.this$0) * r3) / 2.0f));
            MessageEntityView.m11534$$Nest$fgettextureView(this.this$0).setTransform(this.videoMatrix);
            r10.save();
            this.clipPath.rewind();
            AndroidUtilities.rectTmp.set((this.this$0.listView.getX() + r0.getX()) + r2.getImageX(), (this.this$0.listView.getY() + r0.getY()) + r2.getImageY(), (this.this$0.listView.getX() + r0.getX()) + r2.getImageX2(), (this.this$0.listView.getY() + r0.getY()) + r2.getImageY2());
        L12:
            if (r1 >= r2.getRoundRadius().length) goto L14;
            int r32 = r1 * 2;
            this.radii[r32] = r2.getRoundRadius()[r1];
            this.radii[r32 + 1] = r2.getRoundRadius()[r1];
            r1 = r1 + 1;
            goto L12
        L14:
            this.clipPath.addRoundRect(AndroidUtilities.rectTmp, this.radii, Path.Direction.CW);
            r10.clipPath(this.clipPath);
            boolean r9 = super.drawChild(r10, r11, r12);
            r10.restore();
            return r9;
        L17:
            return super.drawChild(r10, r11, r12);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r4, int r5, int r6, int r7, int r8) {
            int r42 = this.this$0.listView.getMeasuredWidth();
            int r62 = 0;
            int r72 = 0;
        L3:
            int r82 = this.this$0.listView.getChildCount();
            MessageEntityView r0 = this.this$0;
            if (r62 >= r82) goto L13;
            View r83 = r0.listView.getChildAt(r62);
            int r02 = r83.getLeft();
            int r1 = r83.getRight();
            if ((r83 instanceof ChatMessageCell) == false) goto L10;
            ChatMessageCell r12 = (ChatMessageCell) r83;
            r02 = r83.getLeft() + r12.getBoundsLeft();
            int r84 = r83.getLeft();
            int r13 = r12.getBoundsRight();
        L8:
            r1 = r13 + r84;
        L12:
            r42 = Math.min(r02, r42);
            r72 = Math.max(r1, r72);
            r62 = r62 + 1;
            goto L3
        L10:
            if ((r83 instanceof ChatActionCell) == false) goto L12;
            ChatActionCell r14 = (ChatActionCell) r83;
            r02 = r83.getLeft() + r14.getBoundsLeft();
            r84 = r83.getLeft();
            r13 = r14.getBoundsRight();
            goto L8
        L13:
            RecyclerListView r63 = r0.listView;
            r63.layout(-r42, 0, r63.getMeasuredWidth() - r42, this.this$0.listView.getMeasuredHeight());
            if (MessageEntityView.m11534$$Nest$fgettextureView(this.this$0) == null) goto L20;
            MessageEntityView.m11534$$Nest$fgettextureView(this.this$0).layout(0, 0, getMeasuredWidth(), this.this$0.listView.getMeasuredHeight());
            return;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r6, int r7) {
            int r0 = 0;
            this.this$0.listView.measure(r6, View.MeasureSpec.makeMeasureSpec(0, 0));
            if (MessageEntityView.m11534$$Nest$fgettextureView(this.this$0) == null) goto L5;
            MessageEntityView.m11534$$Nest$fgettextureView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(this.this$0.listView.getMeasuredWidth(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(this.this$0.listView.getMeasuredHeight(), TLObject.FLAG_30));
        L5:
            int r62 = this.this$0.listView.getMeasuredWidth();
            int r72 = 0;
        L6:
            int r1 = this.this$0.listView.getChildCount();
            MessageEntityView r2 = this.this$0;
            if (r0 >= r1) goto L16;
            View r12 = r2.listView.getChildAt(r0);
            int r22 = r12.getLeft();
            int r3 = r12.getRight();
            if ((r12 instanceof ChatMessageCell) == false) goto L13;
            ChatMessageCell r32 = (ChatMessageCell) r12;
            r22 = r12.getLeft() + r32.getBoundsLeft();
            int r13 = r12.getLeft();
            int r33 = r32.getBoundsRight();
        L11:
            r3 = r33 + r13;
        L15:
            r62 = Math.min(r22, r62);
            r72 = Math.max(r3, r72);
            r0 = r0 + 1;
            goto L6
        L13:
            if ((r12 instanceof ChatActionCell) == false) goto L15;
            ChatActionCell r34 = (ChatActionCell) r12;
            r22 = r12.getLeft() + r34.getBoundsLeft();
            r13 = r12.getLeft();
            r33 = r34.getBoundsRight();
            goto L11
        L16:
            setMeasuredDimension(r72 - r62, r2.listView.getMeasuredHeight());
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.MessageEntityView$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView {
        private final ArrayList<ChatMessageCell> drawCaptionAfter;
        private final ArrayList<ChatMessageCell> drawNamesAfter;
        private final ArrayList<ChatMessageCell> drawReactionsAfter;
        private final ArrayList<ChatMessageCell> drawTimeAfter;
        private final ArrayList<MessageObject.GroupedMessages> drawingGroups;
        final /* synthetic */ MessageEntityView this$0;

        public AnonymousClass2(MessageEntityView r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
            this.drawTimeAfter = new ArrayList();
            this.drawNamesAfter = new ArrayList();
            this.drawCaptionAfter = new ArrayList();
            this.drawReactionsAfter = new ArrayList();
            this.drawingGroups = new ArrayList(10);
        }

        private void drawChatBackgroundElements(Canvas r26) {
            Canvas r2 = r26;
            int r11 = getChildCount();
            int r1 = 0;
            MessageObject.GroupedMessages r3 = null;
        L3:
            float r14 = 0.0f;
            int r4 = 2;
            boolean r5 = true;
            if (r1 >= r11) goto L42;
            View r6 = getChildAt(r1);
            if (r6.getVisibility() == 4) goto L41;
            if ((r6 instanceof ChatMessageCell) == false) goto L37;
            ChatMessageCell r62 = (ChatMessageCell) r6;
            MessageObject.GroupedMessages r52 = r62.getCurrentMessagesGroup();
            if (r52 == null) goto L13;
            if (r52 == r3) goto L41;
        L13:
            MessageObject.GroupedMessagePosition r32 = r62.getCurrentPosition();
            MessageBackgroundDrawable r7 = r62.getBackgroundDrawable();
            if (r7.isAnimationInProgress() == false) goto L16;
        L17:
            if (r32 != null) goto L19;
        L20:
            int r42 = (int) r62.getY();
            r2.save();
            if (r32 != null) goto L23;
            int r33 = r62.getMeasuredHeight();
        L34:
            int r34 = r33 + r42;
            r2.clipRect(0, r42, getMeasuredWidth(), r34);
            r7.setCustomPaint(null);
            r7.setColor(getThemedColor(Theme.key_chat_selectedBackground));
            r7.setBounds(0, r42, getMeasuredWidth(), r34);
            r7.draw(r2);
            r2.restore();
        L35:
            r3 = r52;
            goto L41
        L23:
            int r35 = r62.getMeasuredHeight() + r42;
            long r8 = 0;
            float r63 = 0.0f;
            int r10 = 0;
        L24:
            if (r10 >= r11) goto L33;
            View r15 = getChildAt(r10);
            if ((r15 instanceof ChatMessageCell) == false) goto L32;
            ChatMessageCell r152 = (ChatMessageCell) r15;
            if (r152.getCurrentMessagesGroup() != r52) goto L32;
            MessageBackgroundDrawable r12 = r152.getBackgroundDrawable();
            r42 = Math.min(r42, (int) r152.getY());
            r35 = Math.max(r35, ((int) r152.getY()) + r152.getMeasuredHeight());
            long r18 = r12.getLastTouchTime();
            if (r18 <= r8) goto L32;
            r14 = r12.getTouchX() + r152.getX();
            r63 = r12.getTouchY() + r152.getY();
            r8 = r18;
        L32:
            r10 = r10 + 1;
            goto L24
        L33:
            r7.setTouchCoordsOverride(r14, r63 - r42);
            r33 = r35 - r42;
            goto L34
        L19:
            if ((2 & r32.flags) == 0) goto L35;
        L16:
            if (r62.isDrawingSelectionBackground() == false) goto L35;
        L37:
            if ((r6 instanceof ChatActionCell) == false) goto L41;
            ChatActionCell r64 = (ChatActionCell) r6;
            if (r64.hasGradientService() == false) goto L41;
            r2.save();
            r2.translate(r64.getX(), r64.getY() + r64.getPaddingTop());
            r2.scale(r64.getScaleX(), r64.getScaleY(), r64.getMeasuredWidth() / 2.0f, r64.getMeasuredHeight() / 2.0f);
            r64.drawBackground(r2, true);
            r64.drawReactions(r2, true, null);
            r2.restore();
        L41:
            r1 = r1 + 1;
            goto L3
        L42:
            int r122 = 0;
        L44:
            if (r122 >= 3) goto L145;
            this.drawingGroups.clear();
            if (r122 == r4) goto L48;
        L51:
            int r82 = 0;
        L52:
            if (r82 >= r11) goto L117;
            View r13 = getChildAt(r82);
            if ((r13 instanceof ChatMessageCell) == false) goto L116;
            ChatMessageCell r36 = (ChatMessageCell) r13;
            if (r13.getY() > getHeight()) goto L116;
            if ((r13.getY() + r13.getHeight()) < r14) goto L116;
            if (r36.getVisibility() == 4) goto L116;
            if (r36.getVisibility() == 8) goto L116;
            MessageObject.GroupedMessages r16 = r36.getCurrentMessagesGroup();
            if (r16 == null) goto L116;
            if (r122 == 0) goto L68;
        L69:
            if (r122 == r5) goto L71;
        L73:
            if (r122 == 0) goto L75;
        L76:
            if (r122 == r5) goto L78;
        L80:
            if (r122 == r4) goto L82;
        L83:
            if (r122 == r4) goto L88;
            if (r36.willRemovedAfterAnimation() == true) goto L116;
        L88:
            if (this.drawingGroups.contains(r16) == true) goto L91;
            MessageObject.GroupedMessages.TransitionParams r72 = r16.transitionParams;
            r72.left = 0;
            r72.top = 0;
            r72.right = 0;
            r72.bottom = 0;
            r72.pinnedBotton = false;
            r72.pinnedTop = false;
            r72.cell = r36;
            this.drawingGroups.add(r16);
        L91:
            r16.transitionParams.pinnedTop = r36.isPinnedTop();
            r16.transitionParams.pinnedBotton = r36.isPinnedBottom();
            int r73 = r36.getLeft() + r36.getBackgroundDrawableLeft();
            int r9 = r36.getLeft() + r36.getBackgroundDrawableRight();
            int r102 = (r36.getTop() + r36.getPaddingTop()) + r36.getBackgroundDrawableTop();
            int r17 = (r36.getTop() + r36.getPaddingTop()) + r36.getBackgroundDrawableBottom();
            if ((r36.getCurrentPosition().flags & 4) != 0) goto L95;
            r102 = r102 - AndroidUtilities.dp(10.0f);
        L95:
            if ((r36.getCurrentPosition().flags & 8) != 0) goto L97;
            r17 = r17 + AndroidUtilities.dp(10.0f);
        L97:
            int r43 = r17;
            if (r36.willRemovedAfterAnimation() == false) goto L100;
            r16.transitionParams.cell = r36;
        L100:
            MessageObject.GroupedMessages.TransitionParams r19 = r16.transitionParams;
            int r37 = r19.top;
            if (r37 == 0) goto L103;
            if (r102 < r37) goto L103;
        L104:
            int r38 = r19.bottom;
            if (r38 == 0) goto L107;
            if (r43 > r38) goto L107;
        L108:
            int r39 = r19.left;
            if (r39 == 0) goto L111;
            if (r73 < r39) goto L111;
        L112:
            int r310 = r19.right;
            if (r310 == 0) goto L115;
            if (r9 <= r310) goto L116;
        L115:
            r19.right = r9;
        L111:
            r19.left = r73;
        L107:
            r19.bottom = r43;
        L103:
            r19.top = r102;
            goto L104
        L82:
            if (r36.willRemovedAfterAnimation() == false) goto L116;
        L78:
            if (r36.getMessageObject().deleted == true) goto L80;
        L75:
            if (r36.getMessageObject().deleted == true) goto L116;
        L71:
            if (r16.transitionParams.drawBackgroundForDeletedItems == true) goto L73;
        L68:
            if (r16.messages.size() != r5) goto L69;
        L116:
            r82 = r82 + 1;
            r4 = 2;
            goto L52
        L117:
            boolean r132 = false;
            int r110 = 0;
        L119:
            if (r110 >= this.drawingGroups.size()) goto L143;
            MessageObject.GroupedMessages r311 = this.drawingGroups.get(r110);
            float r44 = r311.transitionParams.cell.getNonAnimationTranslationX(r5);
            MessageObject.GroupedMessages.TransitionParams r65 = r311.transitionParams;
            float r74 = (r65.left + r44) + r65.offsetLeft;
            float r83 = r65.top + r65.offsetTop;
            float r45 = r65.offsetRight + (r65.right + r44);
            float r92 = r65.bottom + r65.offsetBottom;
            if (r65.backgroundChangeBounds == true) goto L123;
            r83 = r83 + r65.cell.getTranslationY();
            r92 = r92 + r311.transitionParams.cell.getTranslationY();
        L123:
            float r66 = r83;
            float r84 = r92;
            if (r311.transitionParams.cell.getScaleX() == 1.0f) goto L126;
        L129:
            boolean r172 = r5;
        L130:
            if (r172 == false) goto L132;
            r2.save();
            r2.scale(r311.transitionParams.cell.getScaleX(), r311.transitionParams.cell.getScaleY(), r74 + ((r45 - r74) / 2.0f), r66 + ((r84 - r66) / 2.0f));
        L132:
            MessageObject.GroupedMessages.TransitionParams r53 = r311.transitionParams;
            int r133 = r110;
            r53.cell.drawBackground(r2, (int) r74, (int) r66, (int) r45, (int) r84, r53.pinnedTop, r53.pinnedBotton, false, 0);
            MessageObject.GroupedMessages.TransitionParams r111 = r311.transitionParams;
            r111.cell = null;
            r111.drawCaptionLayout = r311.hasCaption;
            if (r172 == false) goto L142;
            r26.restore();
            int r85 = 0;
        L135:
            if (r85 >= r11) goto L142;
            View r112 = getChildAt(r85);
            if ((r112 instanceof ChatMessageCell) == false) goto L141;
            ChatMessageCell r22 = (ChatMessageCell) r112;
            if (r22.getCurrentMessagesGroup() != r311) goto L141;
            int r312 = r22.getLeft();
            int r23 = r22.getTop();
            r112.setPivotX((r74 - r312) + ((r45 - r74) / 2.0f));
            r112.setPivotY((r66 - r23) + ((r84 - r66) / 2.0f));
        L141:
            r85 = r85 + 1;
        L142:
            r110 = r133 + 1;
            r2 = r26;
            r5 = true;
            r132 = false;
            goto L119
        L126:
            if (r311.transitionParams.cell.getScaleY() != 1.0f) goto L129;
            r172 = r132;
            goto L130
        L143:
            int r20 = 2;
        L144:
            r122 = r122 + 1;
            r2 = r26;
            r4 = r20;
            r5 = r5;
            r14 = 0.0f;
            goto L44
        L48:
            if (isFastScrollAnimationRunning() == true) goto L51;
            r20 = r4;
            goto L144
        }

        private void drawChatForegroundElements(Canvas r18) {
            int r2 = this.drawTimeAfter.size();
            boolean r5 = false;
            if (r2 <= 0) goto L13;
            int r6 = 0;
        L5:
            ArrayList<ChatMessageCell> r7 = this.drawTimeAfter;
            if (r6 >= r2) goto L12;
            ChatMessageCell r72 = r7.get(r6);
            r18.save();
            r18.translate(r72.getLeft() + r72.getNonAnimationTranslationX(false), r72.getY());
            if (r72.shouldDrawAlphaLayer() == false) goto L10;
            float r8 = r72.getAlpha();
        L11:
            r72.drawTime(r18, r8, true);
            r18.restore();
            r6 = r6 + 1;
            goto L5
        L10:
            r8 = 1.0f;
            goto L11
        L12:
            r7.clear();
        L13:
            int r22 = this.drawNamesAfter.size();
            if (r22 <= 0) goto L24;
            int r62 = 0;
        L16:
            ArrayList<ChatMessageCell> r73 = this.drawNamesAfter;
            if (r62 >= r22) goto L23;
            ChatMessageCell r74 = r73.get(r62);
            float r82 = r74.getLeft() + r74.getNonAnimationTranslationX(false);
            float r9 = r74.getY();
            if (r74.shouldDrawAlphaLayer() == false) goto L21;
            float r10 = r74.getAlpha();
        L22:
            r18.save();
            r18.translate(r82, r9);
            r74.setInvalidatesParent(true);
            r74.drawNamesLayout(r18, r10);
            r74.setInvalidatesParent(false);
            r18.restore();
            r62 = r62 + 1;
            goto L16
        L21:
            r10 = 1.0f;
            goto L22
        L23:
            r73.clear();
        L24:
            int r23 = this.drawCaptionAfter.size();
            if (r23 <= 0) goto L53;
            int r75 = 0;
        L27:
            ArrayList<ChatMessageCell> r83 = this.drawCaptionAfter;
            if (r75 >= r23) goto L52;
            ChatMessageCell r84 = r83.get(r75);
            if (r84.getCurrentPosition() != null) goto L32;
        L34:
            boolean r92 = r5;
        L36:
            if (r84.shouldDrawAlphaLayer() == false) goto L38;
            float r102 = r84.getAlpha();
        L39:
            float r11 = r84.getLeft() + r84.getNonAnimationTranslationX(r5);
            float r12 = r84.getY();
            r18.save();
            MessageObject.GroupedMessages r13 = r84.getCurrentMessagesGroup();
            if (r13 == null) goto L49;
            if (r13.transitionParams.backgroundChangeBounds == false) goto L49;
            float r14 = r84.getNonAnimationTranslationX(true);
            MessageObject.GroupedMessages.TransitionParams r132 = r13.transitionParams;
            float r15 = (r132.left + r14) + r132.offsetLeft;
            float r3 = r132.top + r132.offsetTop;
            float r63 = (r132.right + r14) + r132.offsetRight;
            float r142 = r132.bottom + r132.offsetBottom;
            if (r132.backgroundChangeBounds == true) goto L46;
            r3 = r3 + r84.getTranslationY();
            r142 = r142 + r84.getTranslationY();
        L46:
            r18.clipRect(r15 + AndroidUtilities.dp(8.0f), r3 + AndroidUtilities.dp(8.0f), r63 - AndroidUtilities.dp(8.0f), r142 - AndroidUtilities.dp(8.0f));
        L49:
            if (r84.getTransitionParams().wasDraw == false) goto L51;
            r18.translate(r11, r12);
            r84.setInvalidatesParent(true);
            r84.drawCaptionLayout(r18, r92, r102);
            r84.setInvalidatesParent(false);
            r18.restore();
        L51:
            r75 = r75 + 1;
            r5 = false;
            goto L27
        L38:
            r102 = 1.0f;
            goto L39
        L32:
            if ((r84.getCurrentPosition().flags & 1) != 0) goto L34;
            r92 = true;
            goto L36
        L52:
            float r16 = 8.0f;
            r83.clear();
        L54:
            int r24 = this.drawReactionsAfter.size();
            if (r24 <= 0) goto L94;
            int r32 = 0;
        L57:
            ArrayList<ChatMessageCell> r52 = this.drawReactionsAfter;
            if (r32 >= r24) goto L80;
            ChatMessageCell r53 = r52.get(r32);
            if (r53.getCurrentPosition() == null) goto L65;
            if ((r53.getCurrentPosition().flags & 1) != 0) goto L65;
        L79:
            r32 = r32 + 1;
        L65:
            if (r53.shouldDrawAlphaLayer() == false) goto L67;
            float r64 = r53.getAlpha();
        L68:
            float r76 = r53.getLeft() + r53.getNonAnimationTranslationX(false);
            float r85 = r53.getY();
            r18.save();
            MessageObject.GroupedMessages r93 = r53.getCurrentMessagesGroup();
            if (r93 == null) goto L77;
            if (r93.transitionParams.backgroundChangeBounds == false) goto L77;
            float r103 = r53.getNonAnimationTranslationX(true);
            MessageObject.GroupedMessages.TransitionParams r94 = r93.transitionParams;
            float r112 = (r94.left + r103) + r94.offsetLeft;
            float r122 = r94.top + r94.offsetTop;
            float r133 = (r94.right + r103) + r94.offsetRight;
            float r104 = r94.bottom + r94.offsetBottom;
            if (r94.backgroundChangeBounds == true) goto L75;
            r122 = r122 + r53.getTranslationY();
            r104 = r104 + r53.getTranslationY();
        L75:
            r18.clipRect(r112 + AndroidUtilities.dp(r16), r122 + AndroidUtilities.dp(r16), r133 - AndroidUtilities.dp(r16), r104 - AndroidUtilities.dp(r16));
        L77:
            if (r53.getTransitionParams().wasDraw == false) goto L79;
            r18.translate(r76, r85);
            r53.setInvalidatesParent(true);
            r53.drawReactionsLayout(r18, r64, null);
            r53.drawCommentLayout(r18, r64);
            r53.setInvalidatesParent(false);
            r18.restore();
            goto L79
        L67:
            r64 = 1.0f;
            goto L68
        L80:
            r52.clear();
            return;
        L94:
            return;
        L53:
            r16 = 8.0f;
            goto L54
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r2) {
            r2.save();
            this.selectorRect.setEmpty();
            drawChatBackgroundElements(r2);
            super.dispatchDraw(r2);
            drawChatForegroundElements(r2);
            r2.restore();
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean drawChild(Canvas r20, View r21, long r22) {
            ChatMessageCell r4 = null;
            if ((r21 instanceof ChatMessageCell) == false) goto L6;
            r4 = (ChatMessageCell) r21;
            ChatActionCell r3 = null;
        L9:
            boolean r5 = super.drawChild(r20, r21, r22);
            if (r4 != null) goto L12;
        L14:
            if (r3 == null) goto L16;
            r20.save();
            r20.translate(r3.getX(), r3.getY());
            r3.drawOutboundsContent(r20);
            r20.restore();
        L16:
            float r6 = 0.0f;
            if (r21.getTranslationY() == 0.0f) goto L19;
            r20.save();
            r20.translate(0.0f, r21.getTranslationY());
        L19:
            if (r4 == null) goto L22;
            r4.drawCheckBox(r20);
        L22:
            if (r21.getTranslationY() == 0.0f) goto L25;
            r20.restore();
        L25:
            if (r21.getTranslationY() == 0.0f) goto L27;
            r20.save();
            r20.translate(0.0f, r21.getTranslationY());
        L27:
            if (r4 == null) goto L199;
            r4.getMessageObject();
            MessageObject.GroupedMessagePosition r32 = r4.getCurrentPosition();
            boolean r7 = true;
            if (r32 == null) goto L31;
        L32:
            if (r32 != null) goto L34;
        L39:
            if (r32 != null) goto L41;
        L42:
            this.drawTimeAfter.add(r4);
        L43:
            if (r32 == null) goto L49;
            if (r32.minX == 0) goto L47;
        L51:
            if (r32 == null) goto L53;
        L56:
            if (r32 != null) goto L58;
        L59:
            this.drawCaptionAfter.add(r4);
        L60:
            if (r32 == null) goto L65;
            int r8 = r32.flags;
            if ((r8 & 8) != 0) goto L64;
        L66:
            ImageReceiver r82 = r4.getAvatarImage();
            if (r82 == null) goto L199;
            if (isFastScrollAnimationRunning() == false) goto L71;
        L76:
            boolean r9 = true;
        L77:
            if (r9 == false) goto L79;
            int r11 = r21.getTop();
        L81:
            if (r4.drawPinnedBottom() == false) goto L105;
            int r12 = this.this$0.listView.getChildViewHolder(r21).getAdapterPosition();
            if (r12 < 0) goto L105;
            if (MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0) == null) goto L97;
            if (r32 == null) goto L97;
            int r13 = MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0).posArray.indexOf(r32);
            int r14 = MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0).posArray.size();
            if ((r32.flags & 8) == 0) goto L90;
            int r122 = (r12 - r14) + r13;
        L96:
            boolean r222 = r7;
        L99:
            if (findViewHolderForAdapterPosition(r122) != null) goto L101;
        L106:
            float r72 = r4.getSlidingOffsetX() + r4.getCheckBoxTranslation();
            if (r9 == false) goto L109;
            float r123 = r21.getTop();
        L110:
            int r124 = (int) ((r123 + r4.getLayoutHeight()) + r4.getTransitionParams().deltaBottom);
            int r132 = getMeasuredHeight() - getPaddingBottom();
            if (r4.isCheckBoxVisible() == true) goto L113;
        L115:
            boolean r142 = false;
        L117:
            if (r4.isPlayingRound() == true) goto L124;
            if (r4.getTransitionParams().animatePlayingRound == true) goto L124;
            if (r124 <= r132) goto L129;
            r124 = r132;
        L129:
            if (r9 == true) goto L134;
            if (r21.getTranslationY() == 0.0f) goto L134;
            r20.restore();
        L134:
            if (r4.drawPinnedTop() == false) goto L140;
            int r133 = getChildViewHolder(r21).getAdapterPosition();
            if (r133 < 0) goto L140;
            int r15 = 0;
        L139:
            if (r15 >= 20) goto L140;
            r15 = r15 + 1;
            if (MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0) == null) goto L156;
            if (r32 == null) goto L156;
            int r10 = MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0).posArray.indexOf(r32);
            if (r10 < 0) goto L140;
            float r17 = r6;
            MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0).posArray.size();
            if ((r32.flags & 4) == 0) goto L150;
            r133 = (r133 + r10) + 1;
        L157:
            RecyclerView.ViewHolder r2 = findViewHolderForAdapterPosition(r133);
            if (r2 == null) goto L171;
            r11 = r2.itemView.getTop();
            View r23 = r2.itemView;
            if ((r23 instanceof ChatMessageCell) == false) goto L171;
            r4 = (ChatMessageCell) r23;
            float r24 = r4.getSlidingOffsetX() + r4.getCheckBoxTranslation();
            if (r142 == false) goto L167;
            if (r24 <= r17) goto L167;
            r72 = r24;
        L167:
            if (r4.drawPinnedTop() == false) goto L171;
            r6 = r17;
        L171:
            if ((r124 - AndroidUtilities.dp(42.0f)) >= r11) goto L174;
            r124 = r11 + AndroidUtilities.dp(42.0f);
        L174:
            if (r4.drawPinnedBottom() == true) goto L180;
            if (r9 == false) goto L177;
            int r0 = r4.getBottom();
        L178:
            if (r124 <= r0) goto L180;
            r124 = r0;
            goto L180
        L177:
            r0 = (int) ((r4.getY() + r4.getMeasuredHeight()) + r4.getTransitionParams().deltaBottom);
        L180:
            r20.save();
            if (r72 == r17) goto L184;
            r20.translate(r72, r17);
        L184:
            if (r4.getCurrentMessagesGroup() != null) goto L186;
        L188:
            r82.setImageY(r124 - AndroidUtilities.dp(40.0f));
            if (r4.shouldDrawAlphaLayer() == false) goto L192;
            r82.setAlpha(r4.getAlpha());
            r20.scale(r4.getScaleX(), r4.getScaleY(), r4.getX() + r4.getPivotX(), r4.getY() + (r4.getHeight() >> 1));
        L193:
            r82.setVisible(r222, false);
            r82.draw(r20);
            r20.restore();
            if (r9 == true) goto L198;
            float r172 = 0.0f;
            if (r21.getTranslationY() == 0.0f) goto L201;
            r20.save();
        L201:
            if (r21.getTranslationY() == r172) goto L203;
            r20.restore();
        L203:
            return r5;
        L198:
            r172 = 0.0f;
            goto L201
        L192:
            r82.setAlpha(1.0f);
            goto L193
        L186:
            if (r4.getCurrentMessagesGroup().transitionParams.backgroundChangeBounds == false) goto L188;
            r124 = (int) (r124 - r4.getTranslationY());
            goto L188
        L150:
            r133 = r133 + 1;
            int r102 = r10 - 1;
        L151:
            if (r102 < 0) goto L157;
            if (MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0).posArray.get(r102).maxY < r32.minY) goto L157;
            r133 = r133 + 1;
            r102 = r102 - 1;
        L156:
            r17 = r6;
            r133 = r133 + 1;
        L140:
            r17 = r6;
        L124:
            if (r4.getTransitionParams().animatePlayingRound == false) goto L129;
            float r152 = r4.getTransitionParams().animateChangeProgress;
            if (r4.isPlayingRound() == true) goto L128;
            r152 = 1.0f - r152;
        L128:
            r124 = (int) ((r124 * r152) + (Math.min(r124, r132) * (1.0f - r152)));
            goto L129
        L113:
            if (r72 != 0.0f) goto L115;
            r142 = r222;
            goto L117
        L109:
            r123 = r21.getY();
            goto L110
        L101:
            if (r21.getTranslationY() == 0.0f) goto L103;
            r20.restore();
        L103:
            r82.setVisible(false, false);
            return r5;
        L90:
            r122 = r12 - 1;
            int r134 = r13 + 1;
        L91:
            if (r134 >= r14) goto L96;
            r222 = r7;
            if (MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0).posArray.get(r134).minY > r32.maxY) goto L99;
            r122 = r122 - 1;
            r134 = r134 + 1;
            r7 = r222;
        L97:
            r222 = true;
            r122 = r12 - 1;
        L105:
            r222 = true;
            goto L106
        L79:
            r11 = (int) r21.getY();
            goto L81
        L71:
            if (MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0) != null) goto L73;
        L75:
            r9 = false;
            goto L77
        L73:
            if (MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0).transitionParams.backgroundChangeBounds == false) goto L75;
        L64:
            if ((r8 & 1) == 0) goto L66;
        L65:
            this.drawReactionsAfter.add(r4);
            goto L66
        L58:
            if ((r32.flags & r4.captionFlag()) == 0) goto L60;
        L53:
            if (r4.getTransitionParams().transformGroupToSingleMessage == true) goto L56;
            if (r4.getTransitionParams().animateBackgroundBoundsInner == false) goto L66;
        L47:
            if (r32.minY != 0) goto L51;
        L49:
            if (r4.hasNameLayout() == false) goto L51;
            this.drawNamesAfter.add(r4);
            goto L51
        L41:
            if (r32.last == false) goto L43;
        L34:
            if (r32.last == true) goto L39;
            if (r32.minX != 0) goto L51;
            if (r32.minY != 0) goto L51;
        L31:
            if (r4.getTransitionParams().animateBackgroundBoundsInner == false) goto L66;
        L199:
            r172 = 0.0f;
            goto L201
        L12:
            if (r4.hasOutboundsContent() == false) goto L14;
            r20.save();
            r20.translate(r4.getX(), r4.getY() + r4.getPaddingTop());
            r4.drawOutboundsContent(r20);
            r20.restore();
            goto L16
        L6:
            if ((r21 instanceof ChatActionCell) == false) goto L8;
            r3 = (ChatActionCell) r21;
            goto L9
        L8:
            r3 = null;
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.MessageEntityView$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ MessageEntityView this$0;
        final /* synthetic */ BlurringShader.BlurManager val$blurManager;
        final /* synthetic */ Context val$context;
        final /* synthetic */ boolean val$isRepostVideoPreview;
        final /* synthetic */ PreviewView.TextureViewHolder val$videoTextureHolder;

        /* renamed from: org.telegram.ui.Components.Paint.Views.MessageEntityView$3$1, reason: invalid class name */
        public class AnonymousClass1 extends ChatActionCell {
            public final BlurringShader.StoryBlurDrawer blurDrawer;
            private final TextPaint textPaint;
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass1(AnonymousClass3 r1, Context r2, boolean r3, Theme.ResourcesProvider r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
                this.blurDrawer = new BlurringShader.StoryBlurDrawer(r1.val$blurManager, this, 10);
                TextPaint r12 = new TextPaint(1);
                this.textPaint = r12;
                r12.setTypeface(AndroidUtilities.bold());
                r12.setTextSize(AndroidUtilities.dp(Math.max(16, SharedConfig.fontSize) - 2));
                r12.setColor(-1);
            }

            @Override // org.telegram.ui.Cells.ChatActionCell
            public Paint getThemedPaint(String r3) {
                if ("paintChatActionText".equals(r3) == true) goto L15;
                if ("paintChatActionText2".equals(r3) == true) goto L15;
                if ("paintChatActionBackground".equals(r3) == false) goto L13;
                MessageEntityView.m11546$$Nest$fputusesBackgroundPaint(this.this$1.this$0, true);
                Paint r0 = this.blurDrawer.adapt(MessageEntityView.m11525$$Nest$fgetisDark(this.this$1.this$0)).getPaint(1.0f);
                if (r0 == null) goto L13;
                return r0;
            L13:
                return super.getThemedPaint(r3);
            L15:
                return this.textPaint;
            }
        }

        /* renamed from: org.telegram.ui.Components.Paint.Views.MessageEntityView$3$2, reason: invalid class name */
        public class AnonymousClass2 extends ChatMessageCell {
            public BlurringShader.StoryBlurDrawer blurDrawer;
            private final Paint clearPaint;
            private final Path clipPath;
            private final RectF dst;
            private final float[] radii;
            private final Rect src;
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass2(AnonymousClass3 r7, Context r8, int r9, boolean r10, ChatMessageSharedResources r11, Theme.ResourcesProvider r12) {
                this.this$1 = r7;
                super(r8, r9, r10, r11, r12);
                this.blurDrawer = new BlurringShader.StoryBlurDrawer(r7.val$blurManager, this, 10);
                this.radii = new float[8];
                this.clipPath = new Path();
                Paint r6 = new Paint();
                this.clearPaint = r6;
                r6.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
                this.src = new Rect();
                this.dst = new RectF();
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell
            public boolean drawPhotoImage(Canvas r9) {
                ImageReceiver r0 = getPhotoImage();
                AnonymousClass3 r1 = this.this$1;
                if (r1.val$isRepostVideoPreview == false) goto L35;
                if (r0 == null) goto L35;
                PreviewView.TextureViewHolder r2 = r1.val$videoTextureHolder;
                if (r2 == null) goto L14;
                if (r2.active == false) goto L14;
                if (r2.textureViewActive == false) goto L14;
                if (MessageEntityView.m11535$$Nest$fgettextureViewActive(r1.this$0) == false) goto L14;
            L19:
                int r22 = 0;
            L21:
                if (r22 >= r0.getRoundRadius().length) goto L23;
                int r5 = r22 * 2;
                this.radii[r5] = r0.getRoundRadius()[r22];
                this.radii[r5 + 1] = r0.getRoundRadius()[r22];
                r22 = r22 + 1;
                goto L21
            L23:
                RectF r23 = AndroidUtilities.rectTmp;
                r23.set(r0.getImageX(), r0.getImageY(), r0.getImageX2(), r0.getImageY2());
                this.clipPath.rewind();
                this.clipPath.addRoundRect(r23, this.radii, Path.Direction.CW);
                if (MessageEntityView.m11534$$Nest$fgettextureView(this.this$1.this$0) != null) goto L26;
            L32:
                r9.drawPath(this.clipPath, this.clearPaint);
            L33:
                return true;
            L26:
                if (this.this$1.this$0.drawForBitmap() == false) goto L32;
                Bitmap r24 = MessageEntityView.m11534$$Nest$fgettextureView(this.this$1.this$0).getBitmap();
                if (r24 == null) goto L31;
                r9.save();
                r9.clipPath(this.clipPath);
                r9.translate(-getX(), -getY());
                float r3 = Math.max(r0.getImageWidth() / MessageEntityView.m11537$$Nest$fgetvideoWidth(this.this$1.this$0), r0.getImageHeight() / MessageEntityView.m11536$$Nest$fgetvideoHeight(this.this$1.this$0));
                r9.translate(r0.getCenterX() - ((MessageEntityView.m11537$$Nest$fgetvideoWidth(this.this$1.this$0) * r3) / 2.0f), r0.getCenterY() - ((MessageEntityView.m11536$$Nest$fgetvideoHeight(this.this$1.this$0) * r3) / 2.0f));
                r9.scale((MessageEntityView.m11537$$Nest$fgetvideoWidth(this.this$1.this$0) / MessageEntityView.m11534$$Nest$fgettextureView(this.this$1.this$0).getWidth()) * r3, (MessageEntityView.m11536$$Nest$fgetvideoHeight(this.this$1.this$0) / MessageEntityView.m11534$$Nest$fgettextureView(this.this$1.this$0).getHeight()) * r3);
                this.src.set(0, 0, r24.getWidth(), r24.getHeight());
                this.dst.set(0.0f, 0.0f, MessageEntityView.m11534$$Nest$fgettextureView(this.this$1.this$0).getWidth(), MessageEntityView.m11534$$Nest$fgettextureView(this.this$1.this$0).getHeight());
                r9.drawBitmap(r24, this.src, this.dst, null);
                r9.restore();
                goto L33
            L31:
                return super.drawPhotoImage(r9);
            L14:
                if (MessageEntityView.m11522$$Nest$fgetclipVideoMessageForBitmap(this.this$1.this$0) == true) goto L19;
                if (MessageEntityView.m11534$$Nest$fgettextureView(this.this$1.this$0) == null) goto L35;
                if (this.this$1.this$0.drawForBitmap() == true) goto L19;
            L35:
                return super.drawPhotoImage(r9);
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell
            public Paint getThemedPaint(String r3) {
                if ("paintChatActionBackground".equals(r3) == false) goto L8;
                MessageEntityView.m11546$$Nest$fputusesBackgroundPaint(this.this$1.this$0, true);
                Paint r0 = this.blurDrawer.getPaint(1.0f);
                if (r0 == null) goto L8;
                return r0;
            L8:
                return super.getThemedPaint(r3);
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell, android.view.View
            public void onDraw(Canvas r9) {
                AnonymousClass3 r0 = this.this$1;
                PreviewView.TextureViewHolder r1 = r0.val$videoTextureHolder;
                if (r1 == null) goto L9;
                if (r1.active == false) goto L9;
                if (r1.textureViewActive == false) goto L9;
            L10:
                Canvas r12 = r9;
                r12.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
            L12:
                super.onDraw(r12);
                r12.restore();
                return;
            L9:
                if (MessageEntityView.m11522$$Nest$fgetclipVideoMessageForBitmap(r0.this$0) == true) goto L10;
                r12 = r9;
                r12.save();
                goto L12
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell, android.view.View
            public boolean onTouchEvent(MotionEvent r1) {
                return false;
            }
        }

        public AnonymousClass3(MessageEntityView r1, Context r2, BlurringShader.BlurManager r3, PreviewView.TextureViewHolder r4, boolean r5) {
            this.this$0 = r1;
            this.val$context = r2;
            this.val$blurManager = r3;
            this.val$videoTextureHolder = r4;
            this.val$isRepostVideoPreview = r5;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.this$0.messageObjects.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            return this.this$0.messageObjects.get((this.this$0.messageObjects.size() - 1) - r2).contentType;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r9, int r10) {
            MessageObject r3 = this.this$0.messageObjects.get((this.this$0.messageObjects.size() - 1) - r10);
            View r92 = r9.itemView;
            if ((r92 instanceof ChatMessageCell) == false) goto L21;
            ChatMessageCell r2 = (ChatMessageCell) r92;
            if (MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0) == null) goto L13;
            MessageObject.GroupedMessagePosition r93 = MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0).getPosition(r3);
            if (r93 == null) goto L13;
            if (r93.minY == 0) goto L11;
            boolean r94 = true;
        L12:
            boolean r6 = r94;
        L14:
            MessageObject.GroupedMessages r4 = MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0);
            if (MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0) == null) goto L17;
            boolean r5 = true;
        L18:
            r2.setMessageObject(r3, r4, r5, r6, false);
            return;
        L17:
            r5 = false;
            goto L18
        L11:
            r94 = false;
        L13:
            r6 = false;
            goto L14
        L21:
            if ((r92 instanceof ChatActionCell) == false) goto L24;
            ((ChatActionCell) r92).setMessageObject(r3);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r8, int r9) {
            if (r9 == 1) goto L5;
            AnonymousClass2 r0 = new AnonymousClass2(this, this.val$context, UserConfig.selectedAccount, false, null, this.this$0.resourcesProvider);
            r0.isChat = true;
            return new RecyclerListView.Holder(r0);
        L5:
            return new RecyclerListView.Holder(new AnonymousClass1(this, this.val$context, false, this.this$0.resourcesProvider));
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.MessageEntityView$4, reason: invalid class name */
    public class AnonymousClass4 extends GridLayoutManagerFixed {
        final /* synthetic */ MessageEntityView this$0;

        public AnonymousClass4(MessageEntityView r1, Context r2, int r3, int r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // androidx.recyclerview.widget.GridLayoutManagerFixed
        public boolean hasSiblingChild(int r8) {
            int r0 = (this.this$0.messageObjects.size() - 1) - r8;
            if (MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0) == null) goto L26;
            if (r0 < 0) goto L26;
            if (r0 >= this.this$0.messageObjects.size()) goto L26;
            MessageObject.GroupedMessagePosition r82 = MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0).getPosition(this.this$0.messageObjects.get(r0));
            if (r82 == null) goto L26;
            if (r82.minX == r82.maxX) goto L26;
            byte r02 = r82.minY;
            if (r02 != r82.maxY) goto L26;
            if (r02 == 0) goto L26;
            int r03 = MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0).posArray.size();
            int r3 = 0;
        L16:
            if (r3 >= r03) goto L26;
            MessageObject.GroupedMessagePosition r4 = MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0).posArray.get(r3);
            if (r4 == r82) goto L25;
            byte r5 = r4.minY;
            byte r6 = r82.minY;
            if (r5 > r6) goto L25;
            if (r4.maxY < r6) goto L25;
            return true;
        L25:
            r3 = r3 + 1;
        L26:
            return false;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManagerFixed
        public boolean shouldLayoutChildFromOpositeSide(View r1) {
            if ((r1 instanceof ChatMessageCell) == true) goto L5;
            return false;
        L5:
            return !((ChatMessageCell) r1).getMessageObject().isOutOwner();
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.MessageEntityView$5, reason: invalid class name */
    public class AnonymousClass5 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ MessageEntityView this$0;

        public AnonymousClass5(MessageEntityView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r2) {
            int r0 = (this.this$0.messageObjects.size() - 1) - r2;
            if (MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0) == null) goto L11;
            if (r0 >= 0) goto L6;
            return 1000;
        L6:
            if (r0 >= MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0).messages.size()) goto L14;
            MessageObject.GroupedMessagePosition r1 = MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0).getPosition(MessageEntityView.m11524$$Nest$fgetgroupedMessages(this.this$0).messages.get(r0));
            if (r1 != null) goto L10;
            return 1000;
        L10:
            return r1.spanSize;
        L14:
            return 1000;
        L11:
            return 1000;
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.MessageEntityView$6, reason: invalid class name */
    public class AnonymousClass6 extends RecyclerView.ItemDecoration {
        final /* synthetic */ MessageEntityView this$0;

        public AnonymousClass6(MessageEntityView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r8, View r9, RecyclerView r10, RecyclerView.State r11) {
            int r7 = 0;
            r8.bottom = 0;
            if ((r9 instanceof ChatMessageCell) == false) goto L37;
            ChatMessageCell r92 = (ChatMessageCell) r9;
            MessageObject.GroupedMessages r102 = r92.getCurrentMessagesGroup();
            if (r102 == null) goto L38;
            MessageObject.GroupedMessagePosition r112 = r92.getCurrentPosition();
            if (r112 != null) goto L9;
            return;
        L9:
            if (r112.siblingHeights == null) goto L40;
            Point r0 = AndroidUtilities.displaySize;
            float r02 = Math.max(r0.x, r0.y) * 0.5f;
            int r93 = r92.getExtraInsetHeight();
            int r1 = 0;
        L12:
            if (r1 >= r112.siblingHeights.length) goto L14;
            r93 = r93 + ((int) Math.ceil(r2[r1] * r02));
            r1 = r1 + 1;
            goto L12
        L14:
            int r94 = r93 + ((r112.maxY - r112.minY) * Math.round(AndroidUtilities.density * 7.0f));
            int r12 = r102.posArray.size();
        L15:
            if (r7 >= r12) goto L29;
            MessageObject.GroupedMessagePosition r2 = r102.posArray.get(r7);
            byte r3 = r2.minY;
            byte r4 = r112.minY;
            if (r3 != r4) goto L28;
            if (r2.minX == r112.minX) goto L21;
        L26:
            if (r3 != r4) goto L28;
            r94 = r94 - (((int) Math.ceil(r02 * r2.ph)) - AndroidUtilities.dp(4.0f));
            goto L29
        L21:
            if (r2.maxX != r112.maxX) goto L26;
            if (r3 != r4) goto L26;
            if (r2.maxY != r112.maxY) goto L26;
        L28:
            r7 = r7 + 1;
        L29:
            r8.bottom = -r94;
            return;
        L40:
            return;
        L38:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Paint.Views.MessageEntityView$7, reason: invalid class name */
    public class AnonymousClass7 implements Theme.ResourcesProvider {
        public final Paint chat_actionBackgroundGradientDarkenPaint;
        public final Paint chat_actionBackgroundPaint;
        public final Paint chat_actionBackgroundSelectedPaint;
        public final TextPaint chat_actionTextPaint;
        public final TextPaint chat_actionTextPaint2;
        public final TextPaint chat_botButtonPaint;
        final /* synthetic */ MessageEntityView this$0;

        public AnonymousClass7(MessageEntityView r5) {
            this.this$0 = r5;
            TextPaint r52 = new TextPaint();
            this.chat_actionTextPaint = r52;
            TextPaint r0 = new TextPaint();
            this.chat_actionTextPaint2 = r0;
            TextPaint r1 = new TextPaint();
            this.chat_botButtonPaint = r1;
            this.chat_actionBackgroundPaint = new Paint(3);
            this.chat_actionBackgroundSelectedPaint = new Paint(3);
            Paint r2 = new Paint(3);
            this.chat_actionBackgroundGradientDarkenPaint = r2;
            r52.setTextSize(AndroidUtilities.dp(Math.max(16, SharedConfig.fontSize) - 2));
            r0.setTextSize(AndroidUtilities.dp(Math.max(16, SharedConfig.fontSize) - 2));
            r1.setTextSize(AndroidUtilities.dp(15.0f));
            r1.setTypeface(AndroidUtilities.bold());
            r2.setColor(352321536);
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public int getColor(int r2) {
            return MessageEntityView.m11523$$Nest$fgetcurrentColors(this.this$0).get(r2, Theme.getColor(r2));
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public Drawable getDrawable(String r5) {
            if (r5.equals("drawableMsgIn") == false) goto L10;
            if (MessageEntityView.m11526$$Nest$fgetmsgInDrawable(this.this$0) != null) goto L8;
            MessageEntityView.m11538$$Nest$fputmsgInDrawable(this.this$0, new MessageDrawable(0, false, false, this.this$0.resourcesProvider));
        L8:
            return MessageEntityView.m11526$$Nest$fgetmsgInDrawable(this.this$0);
        L10:
            if (r5.equals("drawableMsgInSelected") == false) goto L17;
            if (MessageEntityView.m11527$$Nest$fgetmsgInDrawableSelected(this.this$0) != null) goto L15;
            MessageEntityView.m11539$$Nest$fputmsgInDrawableSelected(this.this$0, new MessageDrawable(0, false, true, this.this$0.resourcesProvider));
        L15:
            return MessageEntityView.m11527$$Nest$fgetmsgInDrawableSelected(this.this$0);
        L17:
            if (r5.equals("drawableMsgOut") == false) goto L24;
            if (MessageEntityView.m11532$$Nest$fgetmsgOutDrawable(this.this$0) != null) goto L22;
            MessageEntityView.m11544$$Nest$fputmsgOutDrawable(this.this$0, new MessageDrawable(0, true, false, this.this$0.resourcesProvider));
        L22:
            return MessageEntityView.m11532$$Nest$fgetmsgOutDrawable(this.this$0);
        L24:
            if (r5.equals("drawableMsgOutSelected") == false) goto L31;
            if (MessageEntityView.m11533$$Nest$fgetmsgOutDrawableSelected(this.this$0) != null) goto L29;
            MessageEntityView.m11545$$Nest$fputmsgOutDrawableSelected(this.this$0, new MessageDrawable(0, true, true, this.this$0.resourcesProvider));
        L29:
            return MessageEntityView.m11533$$Nest$fgetmsgOutDrawableSelected(this.this$0);
        L31:
            if (r5.equals("drawableMsgInMedia") == false) goto L38;
            if (MessageEntityView.m11528$$Nest$fgetmsgMediaInDrawable(this.this$0) != null) goto L35;
            MessageEntityView.m11540$$Nest$fputmsgMediaInDrawable(this.this$0, new MessageDrawable(1, false, false, this.this$0.resourcesProvider));
        L35:
            MessageEntityView.m11528$$Nest$fgetmsgMediaInDrawable(this.this$0).invalidateSelf();
            return MessageEntityView.m11528$$Nest$fgetmsgMediaInDrawable(this.this$0);
        L38:
            if (r5.equals("drawableMsgInMediaSelected") == false) goto L45;
            if (MessageEntityView.m11529$$Nest$fgetmsgMediaInDrawableSelected(this.this$0) != null) goto L43;
            MessageEntityView.m11541$$Nest$fputmsgMediaInDrawableSelected(this.this$0, new MessageDrawable(1, false, true, this.this$0.resourcesProvider));
        L43:
            return MessageEntityView.m11529$$Nest$fgetmsgMediaInDrawableSelected(this.this$0);
        L45:
            if (r5.equals("drawableMsgOutMedia") == false) goto L52;
            if (MessageEntityView.m11530$$Nest$fgetmsgMediaOutDrawable(this.this$0) != null) goto L50;
            MessageEntityView.m11542$$Nest$fputmsgMediaOutDrawable(this.this$0, new MessageDrawable(1, true, false, this.this$0.resourcesProvider));
        L50:
            return MessageEntityView.m11530$$Nest$fgetmsgMediaOutDrawable(this.this$0);
        L52:
            if (r5.equals("drawableMsgOutMediaSelected") == false) goto L59;
            if (MessageEntityView.m11531$$Nest$fgetmsgMediaOutDrawableSelected(this.this$0) != null) goto L57;
            MessageEntityView.m11543$$Nest$fputmsgMediaOutDrawableSelected(this.this$0, new MessageDrawable(1, true, true, this.this$0.resourcesProvider));
        L57:
            return MessageEntityView.m11531$$Nest$fgetmsgMediaOutDrawableSelected(this.this$0);
        L59:
            return Theme.getThemeDrawable(r5);
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public Paint getPaint(String r3) {
            r3.getClass();
            char r1 = 65535;
            switch(r3.hashCode()) {
                case -1490966183: goto L22;
                case 1712385955: goto L18;
                case 1790254137: goto L14;
                case 1897339317: goto L10;
                case 2030114297: goto L6;
                default: goto L25;
            };
        L25:
            switch(r1) {
                case 0: goto L37;
                case 1: goto L35;
                case 2: goto L33;
                case 3: goto L31;
                case 4: goto L29;
                default: goto L27;
            };
        L27:
            return super.getPaint(r3);
        L29:
            return this.chat_actionTextPaint;
        L31:
            return this.chat_actionBackgroundSelectedPaint;
        L33:
            return this.chat_actionBackgroundGradientDarkenPaint;
        L35:
            return this.chat_botButtonPaint;
        L37:
            return this.chat_actionTextPaint2;
        L6:
            if (r3.equals("paintChatActionText") == false) goto L25;
            r1 = 4;
            goto L25
        L10:
            if (r3.equals("paintChatActionBackgroundSelected") == false) goto L25;
            r1 = 3;
            goto L25
        L14:
            if (r3.equals("paintChatActionBackgroundDarken") == false) goto L25;
            r1 = 2;
            goto L25
        L18:
            if (r3.equals("paintChatBotButton") == false) goto L25;
            r1 = 1;
            goto L25
        L22:
            if (r3.equals("paintChatActionText2") == false) goto L25;
            r1 = 0;
            goto L25
        }

        @Override // org.telegram.ui.ActionBar.Theme.ResourcesProvider
        public boolean isDark() {
            return MessageEntityView.m11525$$Nest$fgetisDark(this.this$0);
        }
    }

    public class MessageEntityViewSelectionView extends EntityView.SelectionView {
        private final Paint clearPaint;
        private Path path;
        final /* synthetic */ MessageEntityView this$0;

        public MessageEntityViewSelectionView(MessageEntityView r1, Context r2) {
            this.this$0 = r1;
            super(r1, r2);
            Paint r12 = new Paint(1);
            this.clearPaint = r12;
            this.path = new Path();
            r12.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }

        @Override // android.view.View
        public void onDraw(Canvas r19) {
            super.onDraw(r19);
            int r8 = r19.getSaveCount();
            float r1 = getShowAlpha();
            if (r1 > 0.0f) goto L6;
            return;
        L6:
            if (r1 >= 1.0f) goto L8;
            int r6 = (int) (r1 * 255.0f);
            Canvas r12 = r19;
            r12.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), r6, 31);
        L9:
            float r3 = AndroidUtilities.dp(2.0f);
            float r10 = AndroidUtilities.dpf2(5.66f);
            float r11 = (r3 + r10) + AndroidUtilities.dp(15.0f);
            float r4 = r11 * 2.0f;
            float r32 = getMeasuredWidth() - r4;
            float r5 = getMeasuredHeight() - r4;
            RectF r42 = AndroidUtilities.rectTmp;
            float r122 = r11 + r32;
            float r13 = r11 + r5;
            r42.set(r11, r11, r122, r13);
            float r62 = AndroidUtilities.dp(12.0f);
            float r33 = Math.min(r62, r32 / 2.0f);
            float r52 = r5 / 2.0f;
            float r14 = Math.min(r62, r52);
            this.path.rewind();
            float r34 = r33 * 2.0f;
            float r63 = r11 + r34;
            float r2 = 2.0f * r14;
            float r7 = r11 + r2;
            r42.set(r11, r11, r63, r7);
            this.path.arcTo(r42, 180.0f, 90.0f);
            float r35 = r122 - r34;
            r42.set(r35, r11, r122, r7);
            this.path.arcTo(r42, 270.0f, 90.0f);
            r12.drawPath(this.path, this.paint);
            this.path.rewind();
            float r72 = r13 - r2;
            r42.set(r11, r72, r63, r13);
            this.path.arcTo(r42, 180.0f, -90.0f);
            r42.set(r35, r72, r122, r13);
            this.path.arcTo(r42, 90.0f, -90.0f);
            r12.drawPath(this.path, this.paint);
            float r9 = r11 + r52;
            r12.drawCircle(r11, r9, r10, this.dotStrokePaint);
            r12.drawCircle(r11, r9, (r10 - AndroidUtilities.dp(1.0f)) + 1.0f, this.dotPaint);
            r12.drawCircle(r122, r9, r10, this.dotStrokePaint);
            r12.drawCircle(r122, r9, (r10 - AndroidUtilities.dp(1.0f)) + 1.0f, this.dotPaint);
            r12.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), 255, 31);
            float r36 = r11 + r14;
            float r53 = r13 - r14;
            r19.drawLine(r11, r36, r11, r53, this.paint);
            r19.drawLine(r122, r36, r122, r53, this.paint);
            r19.drawCircle(r122, r9, (AndroidUtilities.dp(1.0f) + r10) - 1.0f, this.clearPaint);
            r19.drawCircle(r11, r9, (r10 + AndroidUtilities.dp(1.0f)) - 1.0f, this.clearPaint);
            r19.restoreToCount(r8);
            return;
        L8:
            r12 = r19;
            goto L9
        }

        @Override // org.telegram.ui.Components.Paint.Views.EntityView.SelectionView
        public int pointInsideHandle(float r7, float r8) {
            float r0 = AndroidUtilities.dp(1.0f);
            float r1 = AndroidUtilities.dp(19.5f);
            float r02 = r0 + r1;
            float r4 = r02 * 2.0f;
            float r2 = getMeasuredWidth() - r4;
            float r6 = getMeasuredHeight() - r4;
            float r3 = (r6 / 2.0f) + r02;
            if (r7 > (r02 - r1)) goto L5;
        L12:
            float r42 = r02 + r2;
            if (r7 <= (r42 - r1)) goto L23;
            if (r8 <= (r3 - r1)) goto L23;
            if (r7 >= (r42 + r1)) goto L23;
            if (r8 >= (r3 + r1)) goto L23;
            return 2;
        L23:
            if (r7 > r02) goto L25;
            return 0;
        L25:
            if (r7 < r2) goto L27;
            return 0;
        L27:
            if (r8 > r02) goto L29;
            return 0;
        L29:
            if (r8 >= r6) goto L36;
            return 3;
        L36:
            return 0;
        L5:
            if (r8 <= (r3 - r1)) goto L12;
            if (r7 >= (r02 + r1)) goto L12;
            if (r8 >= (r3 + r1)) goto L12;
            return 1;
        }
    }

    public static /* synthetic */ void $r8$lambda$4Gymlgib3eubZGRcOho8RcXoCII(MessageEntityView r0) {
        r0.lambda$new$1();
    }

    /* renamed from: $r8$lambda$nyV-tb1sWjkFgpCpeFXv_M0fuRU, reason: not valid java name */
    public static /* synthetic */ void m11521$r8$lambda$nyVtb1sWjkFgpCpeFXv_M0fuRU(MessageEntityView r0, TextureView r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$w919DwjW9iUE5kguGV32cn7aNoc(MessageEntityView r0, Integer r1, Integer r2) {
        r0.lambda$new$2(r1, r2);
    }

    /* renamed from: -$$Nest$fgetclipVideoMessageForBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11522$$Nest$fgetclipVideoMessageForBitmap(MessageEntityView r0) {
        return r0.clipVideoMessageForBitmap;
    }

    /* renamed from: -$$Nest$fgetcurrentColors, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SparseIntArray m11523$$Nest$fgetcurrentColors(MessageEntityView r0) {
        return r0.currentColors;
    }

    /* renamed from: -$$Nest$fgetgroupedMessages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageObject.GroupedMessages m11524$$Nest$fgetgroupedMessages(MessageEntityView r0) {
        return r0.groupedMessages;
    }

    /* renamed from: -$$Nest$fgetisDark, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11525$$Nest$fgetisDark(MessageEntityView r0) {
        return r0.isDark;
    }

    /* renamed from: -$$Nest$fgetmsgInDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageDrawable m11526$$Nest$fgetmsgInDrawable(MessageEntityView r0) {
        return r0.msgInDrawable;
    }

    /* renamed from: -$$Nest$fgetmsgInDrawableSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageDrawable m11527$$Nest$fgetmsgInDrawableSelected(MessageEntityView r0) {
        return r0.msgInDrawableSelected;
    }

    /* renamed from: -$$Nest$fgetmsgMediaInDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageDrawable m11528$$Nest$fgetmsgMediaInDrawable(MessageEntityView r0) {
        return r0.msgMediaInDrawable;
    }

    /* renamed from: -$$Nest$fgetmsgMediaInDrawableSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageDrawable m11529$$Nest$fgetmsgMediaInDrawableSelected(MessageEntityView r0) {
        return r0.msgMediaInDrawableSelected;
    }

    /* renamed from: -$$Nest$fgetmsgMediaOutDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageDrawable m11530$$Nest$fgetmsgMediaOutDrawable(MessageEntityView r0) {
        return r0.msgMediaOutDrawable;
    }

    /* renamed from: -$$Nest$fgetmsgMediaOutDrawableSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageDrawable m11531$$Nest$fgetmsgMediaOutDrawableSelected(MessageEntityView r0) {
        return r0.msgMediaOutDrawableSelected;
    }

    /* renamed from: -$$Nest$fgetmsgOutDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageDrawable m11532$$Nest$fgetmsgOutDrawable(MessageEntityView r0) {
        return r0.msgOutDrawable;
    }

    /* renamed from: -$$Nest$fgetmsgOutDrawableSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageDrawable m11533$$Nest$fgetmsgOutDrawableSelected(MessageEntityView r0) {
        return r0.msgOutDrawableSelected;
    }

    /* renamed from: -$$Nest$fgettextureView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextureView m11534$$Nest$fgettextureView(MessageEntityView r0) {
        return r0.textureView;
    }

    /* renamed from: -$$Nest$fgettextureViewActive, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m11535$$Nest$fgettextureViewActive(MessageEntityView r0) {
        return r0.textureViewActive;
    }

    /* renamed from: -$$Nest$fgetvideoHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11536$$Nest$fgetvideoHeight(MessageEntityView r0) {
        return r0.videoHeight;
    }

    /* renamed from: -$$Nest$fgetvideoWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11537$$Nest$fgetvideoWidth(MessageEntityView r0) {
        return r0.videoWidth;
    }

    /* renamed from: -$$Nest$fputmsgInDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11538$$Nest$fputmsgInDrawable(MessageEntityView r0, MessageDrawable r1) {
        r0.msgInDrawable = r1;
    }

    /* renamed from: -$$Nest$fputmsgInDrawableSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11539$$Nest$fputmsgInDrawableSelected(MessageEntityView r0, MessageDrawable r1) {
        r0.msgInDrawableSelected = r1;
    }

    /* renamed from: -$$Nest$fputmsgMediaInDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11540$$Nest$fputmsgMediaInDrawable(MessageEntityView r0, MessageDrawable r1) {
        r0.msgMediaInDrawable = r1;
    }

    /* renamed from: -$$Nest$fputmsgMediaInDrawableSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11541$$Nest$fputmsgMediaInDrawableSelected(MessageEntityView r0, MessageDrawable r1) {
        r0.msgMediaInDrawableSelected = r1;
    }

    /* renamed from: -$$Nest$fputmsgMediaOutDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11542$$Nest$fputmsgMediaOutDrawable(MessageEntityView r0, MessageDrawable r1) {
        r0.msgMediaOutDrawable = r1;
    }

    /* renamed from: -$$Nest$fputmsgMediaOutDrawableSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11543$$Nest$fputmsgMediaOutDrawableSelected(MessageEntityView r0, MessageDrawable r1) {
        r0.msgMediaOutDrawableSelected = r1;
    }

    /* renamed from: -$$Nest$fputmsgOutDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11544$$Nest$fputmsgOutDrawable(MessageEntityView r0, MessageDrawable r1) {
        r0.msgOutDrawable = r1;
    }

    /* renamed from: -$$Nest$fputmsgOutDrawableSelected, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11545$$Nest$fputmsgOutDrawableSelected(MessageEntityView r0, MessageDrawable r1) {
        r0.msgOutDrawableSelected = r1;
    }

    /* renamed from: -$$Nest$fputusesBackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11546$$Nest$fputusesBackgroundPaint(MessageEntityView r0, boolean r1) {
        r0.usesBackgroundPaint = r1;
    }

    /* renamed from: -$$Nest$mgetCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatMessageCell m11547$$Nest$mgetCell(MessageEntityView r0) {
        return r0.getCell();
    }

    public MessageEntityView(Context r27, PointF r28, float r29, float r30, ArrayList<MessageObject> r31, BlurringShader.BlurManager r32, boolean r33, PreviewView.TextureViewHolder r34) {
        super(r27, r28);
        this.messageObjects = new ArrayList();
        this.videoWidth = 1;
        this.videoHeight = 1;
        this.firstMeasure = true;
        this.isDark = Theme.isCurrentThemeDark();
        this.currentColors = new SparseIntArray();
        this.resourcesProvider = new AnonymousClass7(this);
        this.blurManager = r32;
        setRotation(r29);
        setScale(r30);
        int r5 = 0;
    L4:
        if (r5 >= r31.size()) goto L15;
        MessageObject r8 = r31.get(r5);
        TLRPC.Message r9 = r8.messageOwner;
        int r10 = r9.date;
        TLRPC.Message r13 = copyMessage(r9);
        Boolean r92 = StoryEntry.useForwardForRepost(r8);
        if (r92 == null) goto L14;
        if (r92.booleanValue() == false) goto L14;
        TLRPC.MessageFwdHeader r93 = r13.fwd_from;
        if (r93 == null) goto L14;
        TLRPC.Peer r94 = r93.from_id;
        if (r94 == null) goto L14;
        r13.from_id = r94;
        r13.peer_id = r94;
        r13.flags &= -5;
        r13.fwd_from = null;
    L14:
        r13.voiceTranscriptionOpen = false;
        int r12 = r8.currentAccount;
        MessageObject r11 = new MessageObject(r12, r13, r8.replyMessageObject, MessagesController.getInstance(r12).getUsers(), MessagesController.getInstance(r8.currentAccount).getChats(), null, null, true, true, 0, true, r33, false);
        r11.setType();
        this.messageObjects.add(r11);
        r5 = r5 + 1;
        goto L4
    L15:
        this.groupedMessages = null;
        if (this.messageObjects.size() <= 1) goto L18;
        MessageObject.GroupedMessages r0 = new MessageObject.GroupedMessages();
        this.groupedMessages = r0;
        r0.messages.addAll(this.messageObjects);
        this.groupedMessages.groupId = this.messageObjects.get(0).getGroupId();
        this.groupedMessages.calculate();
    L18:
        AnonymousClass1 r6 = new AnonymousClass1(this, r27);
        this.container = r6;
        addView(r6, LayoutHelper.createFrame(-1, -1.0f));
        AnonymousClass2 r95 = new AnonymousClass2(this, r27, this.resourcesProvider);
        this.listView = r95;
        r95.setAdapter(new AnonymousClass3(this, r27, r32, r34, r33));
        AnonymousClass4 r02 = new AnonymousClass4(this, r27, 1000, 1, true);
        r02.setSpanSizeLookup(new AnonymousClass5(this));
        r95.setLayoutManager(r02);
        r95.addItemDecoration(new AnonymousClass6(this));
        r6.addView(r95, LayoutHelper.createFrame(-1, -1.0f));
        if (r34 != null) goto L21;
    L23:
        updatePosition();
        return;
    L21:
        if (r34.active == false) goto L23;
        r34.takeTextureView(new MessageEntityView$$ExternalSyntheticLambda0(this), new MessageEntityView$$ExternalSyntheticLambda1(this));
        goto L23
    }

    private ChatMessageCell getCell() {
        if (this.listView != null) goto L5;
        return null;
    L5:
        int r0 = 0;
    L7:
        if (r0 >= this.listView.getChildCount()) goto L13;
        if ((this.listView.getChildAt(r0) instanceof ChatMessageCell) == true) goto L11;
        r0 = r0 + 1;
        goto L7
    L11:
        return (ChatMessageCell) this.listView.getChildAt(r0);
    L13:
        return null;
    }

    private /* synthetic */ void lambda$new$0(TextureView r2) {
        this.textureView = r2;
        if (r2 == null) goto L6;
        this.container.addView(r2, 0);
        return;
    }

    private /* synthetic */ void lambda$new$1() {
        this.textureViewActive = true;
        invalidateAll();
    }

    private /* synthetic */ void lambda$new$2(Integer r3, Integer r4) {
        this.videoWidth = r3.intValue();
        this.videoHeight = r4.intValue();
        AndroidUtilities.runOnUIThread(new MessageEntityView$$ExternalSyntheticLambda2(this), 60);
    }

    public TLRPC.Message copyMessage(TLRPC.Message r3) {
        if ((r3 instanceof TLRPC.TL_message) == false) goto L6;
        TLRPC.Message r2 = new TLRPC.TL_message();
    L8:
        r2.id = r3.id;
        r2.from_id = r3.from_id;
        r2.peer_id = r3.peer_id;
        r2.date = r3.date;
        r2.expire_date = r3.expire_date;
        r2.action = r3.action;
        r2.message = r3.message;
        r2.media = r3.media;
        r2.flags = r3.flags;
        r2.mentioned = r3.mentioned;
        r2.media_unread = r3.media_unread;
        r2.out = r3.out;
        r2.unread = r3.unread;
        r2.entities = r3.entities;
        r2.via_bot_name = r3.via_bot_name;
        r2.reply_markup = r3.reply_markup;
        r2.views = r3.views;
        r2.forwards = r3.forwards;
        r2.replies = r3.replies;
        r2.edit_date = r3.edit_date;
        r2.silent = r3.silent;
        r2.post = r3.post;
        r2.from_scheduled = r3.from_scheduled;
        r2.legacy = r3.legacy;
        r2.edit_hide = r3.edit_hide;
        r2.pinned = r3.pinned;
        r2.fwd_from = r3.fwd_from;
        r2.via_bot_id = r3.via_bot_id;
        r2.reply_to = r3.reply_to;
        r2.post_author = r3.post_author;
        r2.grouped_id = r3.grouped_id;
        r2.reactions = r3.reactions;
        r2.restriction_reason = r3.restriction_reason;
        r2.ttl_period = r3.ttl_period;
        r2.noforwards = r3.noforwards;
        r2.invert_media = r3.invert_media;
        r2.send_state = r3.send_state;
        r2.fwd_msg_id = r3.fwd_msg_id;
        r2.attachPath = r3.attachPath;
        r2.params = r3.params;
        r2.random_id = r3.random_id;
        r2.local_id = r3.local_id;
        r2.dialog_id = r3.dialog_id;
        r2.ttl = r3.ttl;
        r2.destroyTime = r3.destroyTime;
        r2.destroyTimeMillis = r3.destroyTimeMillis;
        r2.layer = r3.layer;
        r2.seq_in = r3.seq_in;
        r2.seq_out = r3.seq_out;
        r2.with_my_score = r3.with_my_score;
        r2.replyMessage = r3.replyMessage;
        r2.reqId = r3.reqId;
        r2.realId = r3.realId;
        r2.stickerVerified = r3.stickerVerified;
        r2.isThreadMessage = r3.isThreadMessage;
        r2.voiceTranscription = r3.voiceTranscription;
        r2.voiceTranscriptionOpen = r3.voiceTranscriptionOpen;
        r2.voiceTranscriptionRated = r3.voiceTranscriptionRated;
        r2.voiceTranscriptionFinal = r3.voiceTranscriptionFinal;
        r2.voiceTranscriptionForce = r3.voiceTranscriptionForce;
        r2.voiceTranscriptionId = r3.voiceTranscriptionId;
        r2.premiumEffectWasPlayed = r3.premiumEffectWasPlayed;
        r2.originalLanguage = r3.originalLanguage;
        r2.translatedToLanguage = r3.translatedToLanguage;
        r2.translatedText = r3.translatedText;
        r2.replyStory = r3.replyStory;
        return r2;
    L6:
        if ((r3 instanceof TLRPC.TL_messageService) == false) goto L10;
        r2 = new TLRPC.TL_messageService();
        goto L8
    L10:
        return r3;
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public EntityView.SelectionView createSelectionView() {
        return new MessageEntityViewSelectionView(this, getContext());
    }

    public abstract boolean drawForBitmap();

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public float getBounceScale() {
        return 0.02f;
    }

    public float getBubbleBounds(RectF r13) {
        float r0 = 2.1474836E9f;
        float r3 = -2.1474836E9f;
        int r4 = 0;
        float r1 = 2.1474836E9f;
        float r2 = -2.1474836E9f;
    L4:
        if (r4 >= this.listView.getChildCount()) goto L25;
        View r5 = this.listView.getChildAt(r4);
        if ((r5 instanceof ChatMessageCell) == false) goto L20;
        ChatMessageCell r6 = (ChatMessageCell) r5;
        if (r6.getMessageObject() != null) goto L10;
    L14:
        float r7 = ((this.container.getX() + r5.getX()) + r6.getBackgroundDrawableLeft()) + AndroidUtilities.dp(1.0f);
        if (this.groupedMessages != null) goto L17;
        r7 = r7 + AndroidUtilities.dp(8.0f);
    L17:
        float r9 = ((this.container.getX() + r5.getX()) + r6.getBackgroundDrawableRight()) - AndroidUtilities.dp(1.66f);
        float r10 = ((this.container.getY() + r5.getY()) + r6.getBackgroundDrawableTop()) + AndroidUtilities.dp(2.0f);
        float r92 = ((this.container.getY() + r5.getY()) + r6.getBackgroundDrawableBottom()) - AndroidUtilities.dp(1.0f);
        float r52 = r7;
        float r72 = r9;
        float r8 = r10;
    L18:
        r0 = Math.min(Math.min(r0, r52), r72);
        r2 = Math.max(Math.max(r2, r52), r72);
        r1 = Math.min(Math.min(r1, r8), r92);
        r3 = Math.max(Math.max(r3, r8), r92);
    L24:
        r4 = r4 + 1;
        goto L4
    L10:
        if (r6.getMessageObject().isRoundVideo() == false) goto L14;
        if (r6.getPhotoImage() == null) goto L14;
        r52 = (this.container.getX() + r6.getX()) + r6.getPhotoImage().getImageX();
        r72 = (this.container.getX() + r6.getX()) + r6.getPhotoImage().getImageX2();
        r8 = (this.container.getY() + r6.getY()) + r6.getPhotoImage().getImageY();
        r92 = (this.container.getY() + r6.getY()) + r6.getPhotoImage().getImageY2();
        goto L18
    L20:
        if ((r5 instanceof ChatActionCell) == false) goto L24;
        ChatActionCell r53 = (ChatActionCell) r5;
        if (r53.starGiftLayout.has() == false) goto L24;
        float r62 = (this.container.getX() + r53.getX()) + r53.getBoundsLeft();
        float r73 = (this.container.getX() + r53.getX()) + r53.getBoundsRight();
        float r82 = this.container.getY() + r53.getY();
        float r93 = (this.container.getY() + r53.getY()) + r53.getMeasuredHeight();
        r0 = Math.min(Math.min(r0, r62), r73);
        r2 = Math.max(Math.max(r2, r62), r73);
        r1 = Math.min(Math.min(r1, r82), r93);
        r3 = Math.max(Math.max(r3, r82), r93);
        goto L24
    L25:
        r13.set(r0, r1, r2, r3);
        return AndroidUtilities.dp(SharedConfig.bubbleRadius);
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public RectOld getSelectionBounds() {
        ViewGroup r0 = (ViewGroup) getParent();
        if (r0 == null) goto L5;
        float r02 = r0.getScaleX();
        return new RectOld(((getPositionX() * r02) - (((getMeasuredWidth() * getScale()) / 2.0f) * r02)) - AndroidUtilities.dp(35.5f), ((getPositionY() * r02) - (((getMeasuredHeight() * getScale()) / 2.0f) * r02)) - AndroidUtilities.dp(35.5f), ((getMeasuredWidth() * getScale()) * r02) + AndroidUtilities.dp(71.0f), ((getMeasuredHeight() * getScale()) * r02) + AndroidUtilities.dp(71.0f));
    L5:
        return new RectOld();
    }

    public void invalidateAll() {
        this.listView.invalidate();
        int r0 = 0;
    L4:
        if (r0 >= this.listView.getChildCount()) goto L6;
        this.listView.getChildAt(r0).invalidate();
        r0 = r0 + 1;
        goto L4
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        this.container.measure(r5, r6);
        setMeasuredDimension(this.container.getMeasuredWidth(), this.container.getMeasuredHeight());
        updatePosition();
        if (this.firstMeasure == false) goto L28;
        ArrayList<MessageObject> r0 = this.messageObjects;
        if (r0 == null) goto L11;
        boolean r2 = true;
        if (r0.size() != 1) goto L11;
        if (this.messageObjects.get(0).contentType != 1) goto L11;
    L12:
        int r52 = View.MeasureSpec.getSize(r5);
        float r02 = 0.0f;
        if (r2 == false) goto L15;
        float r3 = 0.0f;
    L16:
        int r53 = r52 - AndroidUtilities.dp(r3);
        int r62 = View.MeasureSpec.getSize(r6);
        if (r2 == true) goto L20;
        r02 = 192.0f;
    L20:
        int r63 = r62 - AndroidUtilities.dp(r02);
        float r54 = Math.min(r53 / getMeasuredWidth(), r63 / getMeasuredHeight());
        if (r54 >= 1.0f) goto L23;
        setScale(r54);
    L23:
        PointF r03 = getPosition();
        if (r2 == true) goto L26;
        r03.x -= AndroidUtilities.dp(19.0f) * Math.min(1.0f, r54);
    L26:
        setPosition(r03);
        this.firstMeasure = false;
        return;
    L15:
        r3 = 44.0f;
    L11:
        r2 = false;
        goto L12
    }

    public void prepareToDraw(boolean r4) {
        this.clipVideoMessageForBitmap = r4;
        int r0 = 0;
    L4:
        if (r0 >= this.listView.getChildCount()) goto L9;
        View r1 = this.listView.getChildAt(r0);
        if ((r1 instanceof ChatMessageCell) == false) goto L8;
        ((ChatMessageCell) r1).drawingToBitmap = r4;
    L8:
        r0 = r0 + 1;
        goto L4
    }

    public void setupTheme(StoryEntry r8) {
        if (r8 != null) goto L5;
        this.currentColors.clear();
        return;
    L5:
        SharedPreferences r0 = ApplicationLoader.applicationContext.getSharedPreferences("themeconfig", 0);
        String r3 = "Blue";
        String r1 = r0.getString("lastDayTheme", "Blue");
        if (Theme.getTheme(r1) != null) goto L8;
    L9:
        r1 = "Blue";
    L10:
        String r5 = "Dark Blue";
        String r02 = r0.getString("lastDarkTheme", "Dark Blue");
        if (Theme.getTheme(r02) != null) goto L13;
    L14:
        r02 = "Dark Blue";
    L15:
        Theme.ThemeInfo r4 = Theme.getActiveTheme();
        if (r1.equals(r02) == true) goto L18;
        r5 = r02;
    L24:
        r3 = r1;
    L27:
        boolean r82 = r8.isDark;
        this.isDark = r82;
        if (r82 == false) goto L30;
        Theme.ThemeInfo r83 = Theme.getTheme(r5);
    L31:
        String[] r03 = new String[1];
        String r12 = r83.assetName;
        if (r12 == null) goto L34;
        SparseIntArray r04 = Theme.getThemeFileValues(null, r12, r03);
    L35:
        this.currentColors.clear();
        int[] r13 = Theme.getDefaultColors();
        if (r13 == null) goto L41;
        int r32 = 0;
    L39:
        if (r32 >= r13.length) goto L41;
        this.currentColors.put(r32, r13[r32]);
        r32 = r32 + 1;
    L41:
        if (r04 == null) goto L49;
        int r14 = 0;
    L44:
        if (r14 >= r04.size()) goto L46;
        this.currentColors.put(r04.keyAt(r14), r04.valueAt(r14));
        r14 = r14 + 1;
        goto L44
    L46:
        Theme.ThemeAccent r84 = r83.getAccent(false);
        if (r84 == null) goto L49;
        r84.fillAccentColors(r04, this.currentColors);
    L49:
        invalidateAll();
        return;
    L34:
        r04 = Theme.getThemeFileValues(new File(r83.pathToFile), null, r03);
        goto L35
    L30:
        r83 = Theme.getTheme(r3);
        goto L31
    L18:
        if (r4.isDark() == false) goto L20;
    L25:
        r5 = r02;
        goto L27
    L20:
        if (r1.equals("Dark Blue") == true) goto L25;
        if (r1.equals("Night") == false) goto L24;
    L13:
        if (Theme.getTheme(r02).isDark() == true) goto L15;
    L8:
        if (Theme.getTheme(r1).isDark() == false) goto L10;
        goto L9
    }

    @Override // org.telegram.ui.Components.Paint.Views.EntityView
    public void updatePosition() {
        setX(getPositionX() - (getMeasuredWidth() / 2.0f));
        setY(getPositionY() - (getMeasuredHeight() / 2.0f));
        updateSelectionView();
        if (this.usesBackgroundPaint == false) goto L6;
        invalidateAll();
        return;
    }
}
