package org.telegram.ui.Stories.recorder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.RenderNode;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotFullscreenButtons$$ExternalSyntheticApiModelOutline0;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Text;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes7.dex */
public class ScannedLinkPreview extends View {
    private final AnimatedFloat animatedAlpha;
    private final Paint backgroundPaint;
    private final int[] blurLocation;
    private Object blurRenderNode;
    private View blurView;
    private final ButtonBounce bounce;
    private final RectF bounds;
    private Utilities.Callback<Utilities.Callback<BaseFragment>> clickListener;
    private final RectF clipBounds;
    private final Path clipPath;
    private final int currentAccount;
    private Runnable currentCancel;
    private String currentLink;
    private boolean hasImage;
    private boolean hasResolved;
    private final ImageReceiver imageReceiver;
    private ResolvedLink resolved;
    private final Runnable resolvedListener;
    private Text subtitle;
    private final int[] thisLocation;
    private Text title;
    private boolean touch;

    public static class ResolvedLink {
        public final String sourceLink;

        /* renamed from: org.telegram.ui.Stories.recorder.ScannedLinkPreview$ResolvedLink$1, reason: invalid class name */
        public class AnonymousClass1 extends ResolvedLink {
            final /* synthetic */ TLRPC.User val$user;

            public AnonymousClass1(String r1, TLRPC.User r2) {
                this.val$user = r2;
                super(r1);
            }

            @Override // org.telegram.ui.Stories.recorder.ScannedLinkPreview.ResolvedLink
            public String getSubtitle() {
                return LocaleController.getString(R.string.ViewProfile);
            }

            @Override // org.telegram.ui.Stories.recorder.ScannedLinkPreview.ResolvedLink
            public String getTitle() {
                return UserObject.getUserName(this.val$user);
            }

            @Override // org.telegram.ui.Stories.recorder.ScannedLinkPreview.ResolvedLink
            public void open(BaseFragment r5) {
                if (this.val$user.id != UserConfig.getInstance(UserConfig.selectedAccount).getClientUserId()) goto L6;
                Bundle r0 = new Bundle();
                r0.putLong("user_id", this.val$user.id);
                r0.putBoolean("my_profile", true);
                r5.presentFragment(new ProfileActivity(r0, null));
                return;
            L6:
                r5.presentFragment(ProfileActivity.of(this.val$user.id));
            }

            @Override // org.telegram.ui.Stories.recorder.ScannedLinkPreview.ResolvedLink
            public boolean setImage(ImageReceiver r3) {
                AvatarDrawable r0 = new AvatarDrawable();
                r0.setInfo(this.val$user);
                r3.setForUserOrChat(this.val$user, r0);
                return true;
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.ScannedLinkPreview$ResolvedLink$2, reason: invalid class name */
        public class AnonymousClass2 extends ResolvedLink {
            final /* synthetic */ TLRPC.Chat val$chat;

            public AnonymousClass2(String r1, TLRPC.Chat r2) {
                this.val$chat = r2;
                super(r1);
            }

            @Override // org.telegram.ui.Stories.recorder.ScannedLinkPreview.ResolvedLink
            public String getSubtitle() {
                return LocaleController.getString(R.string.AccDescrOpenChat);
            }

            @Override // org.telegram.ui.Stories.recorder.ScannedLinkPreview.ResolvedLink
            public String getTitle() {
                return this.val$chat.title;
            }

            @Override // org.telegram.ui.Stories.recorder.ScannedLinkPreview.ResolvedLink
            public void open(BaseFragment r3) {
                r3.presentFragment(ChatActivity.of(-this.val$chat.id));
            }

            @Override // org.telegram.ui.Stories.recorder.ScannedLinkPreview.ResolvedLink
            public boolean setImage(ImageReceiver r3) {
                AvatarDrawable r0 = new AvatarDrawable();
                r0.setInfo(this.val$chat);
                r3.setForUserOrChat(this.val$chat, r0);
                return true;
            }
        }

        public static /* synthetic */ void $r8$lambda$reoAkm1DnCABK49Ro2mLuJjhF6g(Utilities.Callback r2, MessagesController r3, String r4, Long r5) {
            if (r5 != null) goto L5;
            r2.run(null);
            return;
        L5:
            TLObject r32 = r3.getUserOrChat(r5.longValue());
            if ((r32 instanceof TLRPC.User) == false) goto L10;
            r2.run(fromUser(r4, (TLRPC.User) r32));
            return;
        L10:
            if ((r32 instanceof TLRPC.Chat) == false) goto L13;
            r2.run(fromChat(r4, (TLRPC.Chat) r32));
            return;
        }

        public ResolvedLink(String r1) {
            this.sourceLink = r1;
        }

        public static ResolvedLink fromChat(String r1, TLRPC.Chat r2) {
            if (r2 != null) goto L6;
            return null;
        L6:
            return new AnonymousClass2(r1, r2);
        }

        public static ResolvedLink fromUser(String r1, TLRPC.User r2) {
            if (r2 != null) goto L6;
            return null;
        L6:
            return new AnonymousClass1(r1, r2);
        }

        public static Runnable resolve(int r5, String r6, Utilities.Callback<ResolvedLink> r7) {
            if (r7 != null) goto L27;
            return null;
        L27:
            MessagesController r52 = MessagesController.getInstance(r5);     // Catch: Exception -> L17
            String r1 = r52.linkPrefix;     // Catch: Exception -> L17
            Uri r2 = Uri.parse(r6);     // Catch: Exception -> L17
            if (TextUtils.equals(r2.getHost(), r1) == true) goto L8;
            return null;
        L8:
            List<String> r12 = r2.getPathSegments();     // Catch: Exception -> L17
            if (r12.isEmpty() == false) goto L11;
            return null;
        L11:
            String r13 = r12.get(0);     // Catch: Exception -> L17
            String r22 = r2.getQueryParameter("ref");     // Catch: Exception -> L17
            if (TextUtils.isEmpty(r22) == false) goto L24;
            TLObject r3 = r52.getUserOrChat(r13);     // Catch: Exception -> L17
            if ((r3 instanceof TLRPC.User) == false) goto L20;
            r7.run(fromUser(r6, (TLRPC.User) r3));     // Catch: Exception -> L17
            return null;
        L20:
            if ((r3 instanceof TLRPC.Chat) == false) goto L24;
            r7.run(fromChat(r6, (TLRPC.Chat) r3));     // Catch: Exception -> L17
            return null;
        L24:
            return r52.getUserNameResolver().resolve(r13, r22, new ScannedLinkPreview$ResolvedLink$$ExternalSyntheticLambda0(r7, r52, r6));
        L17:
            e = move-exception;
            FileLog.e(e);
            r7.run(null);
            return null;
        }

        public abstract String getSubtitle();

        public abstract String getTitle();

        public abstract void open(BaseFragment r1);

        public abstract boolean setImage(ImageReceiver r1);
    }

    public static /* synthetic */ void $r8$lambda$07arbdd1QF1Jc7kwj_P0g_oh_cU(ScannedLinkPreview r0, BaseFragment r1) {
        r0.lambda$dispatchTouchEvent$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$2mf_sGXmI_SlIu_FSf4uuAzjOGs(ScannedLinkPreview r0, ResolvedLink r1) {
        r0.lambda$setLink$0(r1);
    }

    public ScannedLinkPreview(Context r8, int r9, Runnable r10) {
        super(r8);
        this.animatedAlpha = new AnimatedFloat(this, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.bounds = new RectF();
        this.clipBounds = new RectF();
        this.imageReceiver = new ImageReceiver(this);
        this.clipPath = new Path();
        this.backgroundPaint = new Paint(1);
        this.bounce = new ButtonBounce(this);
        this.thisLocation = new int[2];
        this.blurLocation = new int[2];
        this.currentAccount = r9;
        this.resolvedListener = r10;
    }

    private /* synthetic */ void lambda$dispatchTouchEvent$1(BaseFragment r1) {
        ResolvedLink r0 = this.resolved;
        if (r0 != null) goto L7;
        if (r1 == null) goto L7;
        return;
    L7:
        r0.open(r1);
    }

    private /* synthetic */ void lambda$setLink$0(ResolvedLink r2) {
        this.currentCancel = null;
        this.resolved = r2;
        if (r2 == null) goto L5;
        boolean r22 = true;
    L6:
        this.hasResolved = r22;
        setup();
        invalidate();
        Runnable r1 = this.resolvedListener;
        if (r1 == null) goto L10;
        r1.run();
        return;
    L10:
        return;
    L5:
        r22 = false;
        goto L6
    }

    private void setup() {
        ResolvedLink r0 = this.resolved;
        if (r0 != null) goto L5;
        return;
    L5:
        this.title = new Text(r0.getTitle(), 16.0f, AndroidUtilities.bold());
        SpannableStringBuilder r02 = new SpannableStringBuilder(this.resolved.getSubtitle());
        if (r02.toString().contains(">") == false) goto L8;
        r02.clear();
        r02.append(AndroidUtilities.replaceArrows(this.resolved.getSubtitle(), false));
    L9:
        this.subtitle = new Text(r02, 14.0f);
        this.hasImage = this.resolved.setImage(this.imageReceiver);
        return;
    L8:
        r02.append(" ");
        r02.append(">");
        ColoredImageSpan r1 = new ColoredImageSpan(R.drawable.settings_arrow);
        r1.setScale(1.25f, 1.25f);
        r02.setSpan(r1, r02.length() - 1, r02.length(), 33);
        goto L9
    }

    @Override // android.view.View
    public void dispatchDraw(Canvas r19) {
        float r5 = this.animatedAlpha.set(this.hasResolved);
        Text r2 = this.title;
        if (r2 != null) goto L5;
        return;
    L5:
        if (this.subtitle == null) goto L39;
        float r7 = 0.0f;
        if (r5 <= 0.0f) goto L40;
        r2.ellipsize(getWidth() * 0.7f);
        this.subtitle.ellipsize(getWidth() * 0.7f);
        float r8 = AndroidUtilities.dp(5.0f);
        float r22 = AndroidUtilities.dp(10.0f);
        float r9 = AndroidUtilities.dp(32.0f);
        float r11 = AndroidUtilities.dp(2.0f);
        float r12 = AndroidUtilities.dp(11.0f);
        float r3 = Math.min(AndroidUtilities.dp(200.0f), getWidth() * 0.8f);
        if (this.hasImage == false) goto L13;
        float r4 = (r12 + r9) + r12;
    L14:
        float r32 = Math.max(r3, (((r4 + r8) + Math.max(this.title.getCurrentWidth(), this.subtitle.getCurrentWidth())) + AndroidUtilities.dp(15.0f)) + r8);
        if (this.hasImage == false) goto L17;
        float r42 = r9;
    L18:
        float r43 = (Math.max(r42, (this.title.getHeight() + r11) + this.subtitle.getHeight()) + r22) + r22;
        float r23 = this.bounce.getScale(0.05f) * AndroidUtilities.lerp(0.6f, 1.0f, r5);
        float r6 = AndroidUtilities.dp(15.0f) * (1.0f - r5);
        this.bounds.set((getWidth() - r32) / 2.0f, (getHeight() - r43) / 2.0f, (getWidth() + r32) / 2.0f, (getHeight() + r43) / 2.0f);
        this.clipBounds.set(this.bounds);
        AndroidUtilities.scaleRect(this.clipBounds, r23);
        this.clipBounds.offset(0.0f, r6);
        if (Build.VERSION.SDK_INT < 29) goto L25;
        Object r33 = this.blurRenderNode;
        if (r33 == null) goto L25;
        if (this.blurView == null) goto L25;
        RenderNode r34 = BotFullscreenButtons$$ExternalSyntheticApiModelOutline0.m(r33);
        this.clipPath.rewind();
        this.clipPath.addRoundRect(this.clipBounds, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), Path.Direction.CW);
        getLocationOnScreen(this.thisLocation);
        this.blurView.getLocationOnScreen(this.blurLocation);
        r19.saveLayerAlpha(this.clipBounds, (int) (255.0f * r5), 31);
        r19.clipPath(this.clipPath);
        int r13 = this.blurLocation[0];
        int[] r14 = this.thisLocation;
        r19.translate(r13 - r14[0], r4[1] - r14[1]);
        float r44 = Math.max(this.blurView.getWidth() / r34.getWidth(), this.blurView.getHeight() / r34.getHeight());
        r19.scale(r44, r44);
        r19.drawRenderNode(r34);
        r19.restore();
        this.backgroundPaint.setColor(Theme.multAlpha(1879048192, r5));
        r19.drawRoundRect(this.clipBounds, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), this.backgroundPaint);
    L26:
        r19.save();
        r19.translate(0.0f, r6);
        r19.scale(r23, r23, this.bounds.centerX(), this.bounds.centerY());
        if (this.hasImage == false) goto L29;
        float r35 = r9 / 2.0f;
        this.imageReceiver.setRoundRadius((int) r35);
        ImageReceiver r24 = this.imageReceiver;
        RectF r45 = this.bounds;
        r24.setImageCoords((r45.left + r8) + r12, r45.centerY() - r35, r9, r9);
        this.imageReceiver.setAlpha(r5);
        this.imageReceiver.draw(r19);
    L29:
        float r10 = this.bounds.centerY() - (((this.title.getHeight() + r11) + this.subtitle.getHeight()) / 2.0f);
        Text r1 = this.title;
        float r25 = this.bounds.left;
        if (this.hasImage == false) goto L32;
        float r36 = (r12 + r9) + r12;
    L33:
        r1.draw(r19, (r25 + r36) + r8, r10 + (r1.getHeight() / 2.0f), -1, r5);
        Text r15 = this.subtitle;
        float r26 = this.bounds.left;
        if (this.hasImage == false) goto L36;
        r7 = (r9 + r12) + r12;
    L36:
        r15.draw(r19, (r26 + r7) + r8, ((r10 + this.title.getHeight()) + r11) + (this.subtitle.getHeight() / 2.0f), Theme.blendOver(-16777216, -1610612737), r5);
        r19.restore();
        return;
    L32:
        r36 = 0.0f;
    L25:
        this.backgroundPaint.setColor(Theme.multAlpha(-587202560, r5));
        r19.drawRoundRect(this.clipBounds, AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f), this.backgroundPaint);
        goto L26
    L17:
        r42 = 0.0f;
        goto L18
    L13:
        r4 = 0.0f;
        goto L14
    L40:
        return;
    }

    @Override // android.view.View
    public boolean dispatchTouchEvent(MotionEvent r5) {
        if (this.hasResolved == true) goto L5;
    L39:
        ButtonBounce r52 = this.bounce;
        this.touch = false;
        r52.setPressed(false);
        return false;
    L5:
        if (this.resolved == null) goto L39;
        if (r5.getAction() != 0) goto L13;
        if (this.bounds.contains(r5.getX(), r5.getY()) == false) goto L33;
        ButtonBounce r53 = this.bounce;
        this.touch = true;
        r53.setPressed(true);
    L33:
        if (this.touch == false) goto L35;
    L38:
        return true;
    L35:
        if (this.bounce.isPressed() == true) goto L38;
        return false;
    L13:
        if (r5.getAction() != 2) goto L20;
        if (this.bounce.isPressed() == false) goto L33;
        if (this.bounds.contains(r5.getX(), r5.getY()) == true) goto L33;
        this.bounce.setPressed(false);
        goto L33
    L20:
        if (r5.getAction() != 1) goto L30;
        if (this.bounce.isPressed() == false) goto L28;
        Utilities.Callback<Utilities.Callback<BaseFragment>> r54 = this.clickListener;
        if (r54 == null) goto L28;
        if (this.resolved == null) goto L28;
        r54.run(new ScannedLinkPreview$$ExternalSyntheticLambda1(this));
    L28:
        this.bounce.setPressed(false);
        this.touch = false;
        goto L33
    L30:
        if (r5.getAction() != 3) goto L33;
        this.bounce.setPressed(false);
        this.touch = false;
        goto L33
    }

    public boolean inTouch() {
        if (this.bounce.isPressed() == false) goto L5;
        return true;
    L5:
        if (this.touch == true) goto L11;
        return false;
    L11:
        return true;
    }

    public boolean isResolved() {
        return this.hasResolved;
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

    public void setBlurRenderNode(View r1, Object r2) {
        this.blurView = r1;
        this.blurRenderNode = r2;
        invalidate();
    }

    public void setLink(String r4) {
        if (TextUtils.isEmpty(r4) == false) goto L14;
        Runnable r42 = this.currentCancel;
        if (r42 == null) goto L8;
        r42.run();
        this.currentCancel = null;
    L8:
        if (this.hasResolved == false) goto L10;
        invalidate();
    L10:
        this.hasResolved = false;
        this.currentLink = null;
        Runnable r3 = this.resolvedListener;
        if (r3 == null) goto L38;
        r3.run();
        return;
    L38:
        return;
    L14:
        ResolvedLink r0 = this.resolved;
        if (r0 == null) goto L17;
    L18:
        if (r0 != null) goto L20;
    L28:
        ResolvedLink r02 = this.resolved;
        if (r02 != null) goto L31;
        return;
    L31:
        if (this.hasResolved == false) goto L33;
        return;
    L33:
        if (TextUtils.equals(r02.sourceLink, r4) == false) goto L41;
        this.hasResolved = true;
        setup();
        invalidate();
        Runnable r32 = this.resolvedListener;
        if (r32 == null) goto L42;
        r32.run();
        return;
    L42:
        return;
    L41:
        return;
    L20:
        if (TextUtils.equals(r0.sourceLink, r4) == true) goto L28;
        if (TextUtils.equals(this.currentLink, r4) == true) goto L28;
    L23:
        Runnable r03 = this.currentCancel;
        if (r03 == null) goto L26;
        r03.run();
        this.currentCancel = null;
    L26:
        this.resolved = null;
        this.currentLink = r4;
        this.currentCancel = ResolvedLink.resolve(this.currentAccount, r4, new ScannedLinkPreview$$ExternalSyntheticLambda0(this));
        return;
    L17:
        if (this.currentCancel == null) goto L23;
        goto L18
    }

    public void whenClicked(Utilities.Callback<Utilities.Callback<BaseFragment>> r1) {
        this.clickListener = r1;
    }
}
