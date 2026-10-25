package com.exteragram.messenger.components;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import androidx.exifinterface.media.ExifInterface;
import com.exteragram.messenger.utils.JpegFingerprint;
import com.exteragram.messenger.utils.MediaUtils;
import com.exteragram.messenger.utils.chats.ChatUtils;
import com.google.android.gms.cast.MediaError;
import com.google.zxing.Dimension;
import java.io.File;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.PopupSwipeBackLayout;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes4.dex */
public abstract class MessageDetailsPopupWrapper {
    private final int BITRATE;
    private final int FILE_PATH;
    private final int LOCATION;
    private final int PLATFORM;
    private final int RESOLUTION;
    private final int SET_OWNER;
    private String filePath;
    private final BaseFragment fragment;
    private String[] geo;
    private long ownerId;
    private JpegFingerprint photoFingerprint;
    private final Theme.ResourcesProvider resourcesProvider;
    public LinearLayout swipeBack;

    /* renamed from: com.exteragram.messenger.components.MessageDetailsPopupWrapper$1, reason: invalid class name */
    public class AnonymousClass1 extends ScrollView {
        final AnimatedFloat alphaFloat;
        final /* synthetic */ MessageDetailsPopupWrapper this$0;
        Drawable topShadowDrawable;
        private boolean wasCanScrollVertically;

        public AnonymousClass1(MessageDetailsPopupWrapper r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.alphaFloat = new AnimatedFloat(this, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r7) {
            super.dispatchDraw(r7);
            AnimatedFloat r0 = this.alphaFloat;
            if (canScrollVertically(-1) == false) goto L5;
            float r1 = 1.0f;
        L6:
            float r02 = r0.set(r1) * 0.5f;
            if (r02 > 0.0f) goto L9;
            return;
        L9:
            if (this.topShadowDrawable != null) goto L11;
            this.topShadowDrawable = ContextCompat.getDrawable(getContext(), R.drawable.header_shadow);
        L11:
            Drawable r12 = this.topShadowDrawable;
            if (r12 == null) goto L16;
            r12.setBounds(0, getScrollY(), getWidth(), getScrollY() + this.topShadowDrawable.getIntrinsicHeight());
            this.topShadowDrawable.setAlpha((int) (r02 * 255.0f));
            this.topShadowDrawable.draw(r7);
            return;
        L16:
            return;
        L5:
            r1 = 0.0f;
            goto L6
        }

        @Override // android.widget.ScrollView, android.view.ViewGroup, android.view.ViewParent
        public void onNestedScroll(View r1, int r2, int r3, int r4, int r5) {
            super.onNestedScroll(r1, r2, r3, r4, r5);
            boolean r12 = canScrollVertically(-1);
            if (this.wasCanScrollVertically == r12) goto L6;
            invalidate();
            this.wasCanScrollVertically = r12;
            return;
        }
    }

    public static class Item {
        int id;
        int resId;
        String subtitle;
        String title;

        public Item(int r1, int r2, String r3, String r4) {
            this.id = r1;
            this.resId = r2;
            this.title = r3;
            this.subtitle = r4;
        }

        public Item(int r2, String r3, int r4) {
            this(-1, r2, r3, String.valueOf(r4));
        }

        public Item(int r2, String r3, String r4) {
            this(-1, r2, r3, r4);
        }
    }

    public static /* synthetic */ void $r8$lambda$3EevXX1nGfpoYykqZ8BopnpxVGI(MessageDetailsPopupWrapper r0, ActionBarMenuSubItem r1, Item r2) {
        r0.lambda$new$10(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$6ymp8PbdNmG2c7PvYGXVnKmMx4U(MessageDetailsPopupWrapper r0, Item r1, View r2) {
        return r0.lambda$new$12(r1, r2);
    }

    /* renamed from: $r8$lambda$8KY68BRgfBrUh0xLJ-dGa54yNS4, reason: not valid java name */
    public static /* synthetic */ void m1199$r8$lambda$8KY68BRgfBrUh0xLJdGa54yNS4(String r1, ActionBarMenuSubItem r2, Item r3) {
        if (TextUtils.isEmpty(r1) == true) goto L6;
        r2.setSubtext(r1);
        r3.subtitle = r1;
        return;
    L6:
        r2.setVisibility(8);
    }

    /* renamed from: $r8$lambda$NLlHPoRU-EZMja3wPpG-KHPiJMA, reason: not valid java name */
    public static /* synthetic */ void m1200$r8$lambda$NLlHPoRUEZMja3wPpGKHPiJMA(MessageDetailsPopupWrapper r0, ActionBarMenuSubItem r1, Item r2) {
        r0.lambda$new$3(r1, r2);
    }

    /* renamed from: $r8$lambda$UPzDw9WCXg-ShefkGx0ZnEoORY4, reason: not valid java name */
    public static /* synthetic */ void m1201$r8$lambda$UPzDw9WCXgShefkGx0ZnEoORY4(MessageDetailsPopupWrapper r0, ActionBarMenuSubItem r1, Item r2) {
        r0.lambda$new$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$dE2BmWFMp3ZO7Tcpe0bsPv1CgxY(Item r2, ActionBarMenuSubItem r3, TLRPC.User r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (TextUtils.isEmpty(UserObject.getPublicUsername(r4)) == true) goto L6;
        r2.subtitle = "@" + UserObject.getPublicUsername(r4);
    L7:
        r3.setSubtext(r2.subtitle);
        return;
    L6:
        r2.subtitle = ContactsController.formatName(r4);
        goto L7
    }

    public static /* synthetic */ void $r8$lambda$dTsoAI0JSEnOtQF1MNz4hgjP0nA(MessageDetailsPopupWrapper r0, Item r1, Activity r2, boolean r3, boolean r4, MessageObject r5, BaseFragment r6, View r7) {
        r0.lambda$new$11(r1, r2, r3, r4, r5, r6, r7);
    }

    public static /* synthetic */ void $r8$lambda$f_aN3ZZNn5AFFp878etwVxDefTs(int r2, ActionBarMenuSubItem r3, Item r4) {
        if (r2 <= 0) goto L5;
        r3.setSubtext(r2 + " Kbps");
        r4.subtitle = r2 + " Kbps";
        return;
    L5:
        r3.setVisibility(8);
    }

    public static /* synthetic */ void $r8$lambda$nKLUxPKKYKjJIhJVpxjuwM7e7_4(Dimension r1, ActionBarMenuSubItem r2, Item r3) {
        if (r1 == null) goto L5;
        r2.setSubtext(r1.toString());
        r3.subtitle = r1.toString();
        return;
    L5:
        r2.setVisibility(8);
    }

    public static /* synthetic */ void $r8$lambda$pXC2yz9RQMzdhywRyiSds2ZFD1U(PopupSwipeBackLayout r0, View r1) {
        r0.closeForeground();
    }

    public static /* synthetic */ void $r8$lambda$u7l1QJpJ6d9onAYkKJZ5F9iiJC8(MessageDetailsPopupWrapper r0, boolean r1, MessageObject r2, ActionBarMenuSubItem r3, Item r4) {
        r0.lambda$new$8(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$xa633sylsHB6N4484Nof0auR-Z8, reason: not valid java name */
    public static /* synthetic */ void m1202$r8$lambda$xa633sylsHB6N4484Nof0auRZ8(MessageDetailsPopupWrapper r0, MessageObject r1, ActionBarMenuSubItem r2, Item r3) {
        r0.lambda$new$6(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$xhcW3NufjIOSClSkm5mOx7QiAJo(PopupSwipeBackLayout r0, View r1) {
        r0.closeForeground();
    }

    public MessageDetailsPopupWrapper(BaseFragment r27, PopupSwipeBackLayout r28, MessageObject r29, Theme.ResourcesProvider r30) {
        MessageObject r3 = r29;
        this.SET_OWNER = 0;
        this.FILE_PATH = 1;
        this.LOCATION = 2;
        this.BITRATE = 3;
        this.RESOLUTION = 4;
        this.PLATFORM = 5;
        this.ownerId = 0;
        this.fragment = r27;
        this.resourcesProvider = r30;
        Activity r2 = r27.getParentActivity();
        LinearLayout r4 = new LinearLayout(r2);
        this.swipeBack = r4;
        r4.setOrientation(1);
        AnonymousClass1 r42 = new AnonymousClass1(this, r2);
        LinearLayout r5 = new LinearLayout(r2);
        r42.addView(r5);
        r5.setOrientation(1);
        ActionBarMenuSubItem r6 = new ActionBarMenuSubItem(r27.getParentActivity(), true, false, r30);
        r6.setItemHeight(44);
        r6.setTextAndIcon(LocaleController.getString(R.string.Back), R.drawable.msg_arrow_back);
        AnimatedEmojiSpan.TextViewEmojis r7 = r6.getTextView();
        if (LocaleController.isRTL == false) goto L5;
        int r13 = 0;
    L7:
        if (LocaleController.isRTL == false) goto L9;
        int r12 = AndroidUtilities.dp(40.0f);
    L10:
        r7.setPadding(r13, 0, r12, 0);
        r6.setOnClickListener(new MessageDetailsPopupWrapper$$ExternalSyntheticLambda0(r28));
        this.swipeBack.addView(r6, LayoutHelper.createLinear(-1, -2));
        r5.addView(createGap(), LayoutHelper.createLinear(-1, 8));
        ArrayList r62 = new ArrayList();
        int r72 = r3.messageOwner.views;
        if (r72 <= 0) goto L13;
        r62.add(new Item(R.drawable.msg_view_file, String.format(LocaleController.getPluralString("Views", r72), new Object[]{AndroidUtilities.formatCount(r3.messageOwner.views)}), null));
    L13:
        int r73 = r3.messageOwner.forwards;
        if (r73 <= 0) goto L17;
        r62.add(new Item(R.drawable.msg_forward, String.format(LocaleController.getPluralString("Shares", r73), new Object[]{AndroidUtilities.formatCount(r3.messageOwner.forwards)}), null));
    L17:
        if (r62.isEmpty() == true) goto L19;
        r62.add(null);
    L19:
        r62.add(new Item(R.drawable.msg_info, "ID", r3.messageOwner.id));
        if (r3.messageOwner.date <= 0) goto L22;
        r62.add(new Item(R.drawable.msg_calendar2, LocaleController.getString(R.string.Date), formatTime(r3.messageOwner.date, true)));
    L22:
        TLRPC.Message r74 = r3.messageOwner;
        TLRPC.MessageFwdHeader r122 = r74.fwd_from;
        if (r122 == null) goto L29;
        int r123 = r122.date;
        if (r123 <= 0) goto L29;
        if (r123 == r74.date) goto L29;
        r62.add(new Item(R.drawable.msg_recent, LocaleController.getString(R.string.ForwardedDate), formatTime(r3.messageOwner.fwd_from.date, true)));
    L29:
        TLRPC.Message r75 = r3.messageOwner;
        int r124 = r75.edit_date;
        if (r124 > 0) goto L32;
    L36:
        r62.add(null);
        if (r3.getSize() <= 0) goto L40;
        r62.add(new Item(R.drawable.msg_sendfile, LocaleController.getString(R.string.FileSize), AndroidUtilities.formatFileSize(r3.getSize())));
    L40:
        if (r3.getMimeType() == null) goto L45;
        if (r3.getMimeType().isEmpty() == true) goto L45;
        r62.add(new Item(R.drawable.msg_media, LocaleController.getString(R.string.MimeType), r3.getMimeType()));
    L45:
        if (MessageObject.getMedia(r3.messageOwner) != null) goto L47;
    L61:
        String r0 = ChatUtils.getInstance().getPathToMessage(r3);
        this.filePath = r0;
        if (TextUtils.isEmpty(r0) == true) goto L65;
        r62.add(new Item(1, R.drawable.msg_map, LocaleController.getString(R.string.FilePath), LocaleController.getString(R.string.Open)));
    L65:
        if (r3.isVoice() == false) goto L67;
    L70:
        boolean r02 = true;
    L72:
        if (r3.isVideo() == false) goto L74;
    L81:
        boolean r15 = true;
    L82:
        boolean r76 = isPhotoAsDocument(r3);
        if (r76 == false) goto L85;
    L90:
        boolean r10 = true;
    L91:
        if (r10 == true) goto L93;
    L95:
        boolean r24 = r02;
    L96:
        if (r15 == true) goto L98;
        if (r10 == true) goto L98;
    L99:
        if (r76 == true) goto L101;
    L103:
        if (r15 == true) goto L105;
        if (r24 == true) goto L105;
    L109:
        if (MessageObject.getMedia(r3.messageOwner) != null) goto L111;
    L134:
        int r03 = 0;
    L135:
        if (r03 == 0) goto L138;
        r62.add(new Item(R.drawable.msg_satellite, LocaleController.getString(R.string.Datacenter), String.format(Locale.ROOT, "DC%d, %s", new Object[]{Integer.valueOf(r03), ChatUtils.getDCName(r03)})));
    L138:
        if (r62.get(r62.size() - 1) != null) goto L140;
        r62.remove(r62.size() - 1);
    L140:
        int r11 = r62.size();
        int r04 = 0;
        int r77 = 0;
    L141:
        if (r77 >= r11) goto L169;
        int r132 = r77 + 1;
        Item r125 = (Item) r62.get(r77);
        if (r125 == null) goto L144;
        Activity r21 = r2;
        AnonymousClass1 r22 = r42;
        ActionBarMenuSubItem r43 = new ActionBarMenuSubItem(r27.getParentActivity(), false, false, r30);
        r43.setTextAndIcon(r125.title, r125.resId);
        r43.setMinimumWidth(AndroidUtilities.dp(196.0f));
        r43.setOnClickListener(new MessageDetailsPopupWrapper$$ExternalSyntheticLambda1(r28));
        r5.addView(r43, LayoutHelper.createLinear(-1, 48));
        int r78 = r04 + 48;
        String r14 = r125.subtitle;
        if (r14 == null) goto L148;
        r43.setSubtext(r14);
        r43.subtextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        r43.subtextView.setMarqueeRepeatLimit(-1);
        r43.subtextView.setSelected(true);
        r43.setItemHeight(56);
        int r23 = r04 + 56;
    L149:
        int r05 = r125.id;
        boolean r79 = r15;
        if (r05 != 0) goto L157;
        if (this.ownerId <= 0) goto L157;
        ChatUtils.getInstance().searchUserById(Long.valueOf(this.ownerId), new MessageDetailsPopupWrapper$$ExternalSyntheticLambda2(r125, r43));
    L154:
        ActionBarMenuSubItem r32 = r43;
        LinearLayout r18 = r5;
        Item r52 = r125;
        char r44 = 5;
    L155:
        AnonymousClass1 r126 = r22;
        boolean r25 = r79;
    L168:
        r32.setTag(r52);
        Item r710 = r52;
        boolean r53 = r25;
        ActionBarMenuSubItem r142 = r32;
        boolean r45 = r10;
        r142.setOnClickListener(new MessageDetailsPopupWrapper$$ExternalSyntheticLambda7(this, r710, r21, r45, r53, r29, r27));
        r142.setOnLongClickListener(new MessageDetailsPopupWrapper$$ExternalSyntheticLambda8(this, r710));
        r3 = r29;
        r15 = r53;
        r62 = r62;
        r77 = r132;
        r5 = r18;
        r2 = r21;
        r04 = r23;
        r10 = r45;
        r42 = r126;
    L157:
        if (r05 != 2) goto L160;
        ChatUtils.utilsQueue.postRunnable(new MessageDetailsPopupWrapper$$ExternalSyntheticLambda3(this, r43, r125));
        goto L154
    L160:
        if (r05 != 3) goto L163;
        ChatUtils.utilsQueue.postRunnable(new MessageDetailsPopupWrapper$$ExternalSyntheticLambda4(this, r3, r43, r125));
        r32 = r43;
        r18 = r5;
        r52 = r125;
        r44 = 5;
        goto L155
    L163:
        if (r05 != 4) goto L165;
        DispatchQueue r06 = ChatUtils.utilsQueue;
        r126 = r22;
        r25 = r79;
        r18 = r5;
        r52 = r125;
        MessageDetailsPopupWrapper$$ExternalSyntheticLambda5 r07 = new MessageDetailsPopupWrapper$$ExternalSyntheticLambda5(this, r25, r3, r43, r52);
        r32 = r43;
        r06.postRunnable(r07);
        r44 = 5;
        goto L168
    L165:
        r32 = r43;
        r18 = r5;
        r52 = r125;
        r44 = 5;
        r126 = r22;
        r25 = r79;
        if (r05 != 5) goto L168;
        ChatUtils.utilsQueue.postRunnable(new MessageDetailsPopupWrapper$$ExternalSyntheticLambda6(this, r32, r52));
        goto L168
    L148:
        r23 = r78;
        goto L149
    L144:
        r5.addView(createGap(), LayoutHelper.createLinear(-1, 8));
        r04 = r04 + 8;
        r77 = r132;
        r2 = r2;
        goto L141
    L169:
        AnonymousClass1 r127 = r42;
        if (r04 > 380) goto L172;
    L175:
        this.swipeBack.addView(r127, LayoutHelper.createLinear(-1, -2));
        return;
    L172:
        if (Math.abs(r04 - 380) <= 112) goto L175;
        this.swipeBack.addView(r127, LayoutHelper.createLinear(-1, 380));
        return;
    L111:
        if (MessageObject.getMedia(r3.messageOwner).photo == null) goto L116;
        if (MessageObject.getMedia(r3.messageOwner).photo.dc_id <= 0) goto L116;
        r03 = MessageObject.getMedia(r3.messageOwner).photo.dc_id;
    L116:
        if (MessageObject.getMedia(r3.messageOwner).document == null) goto L121;
        if (MessageObject.getMedia(r3.messageOwner).document.dc_id <= 0) goto L121;
        r03 = MessageObject.getMedia(r3.messageOwner).document.dc_id;
    L121:
        if (MessageObject.getMedia(r3.messageOwner).webpage == null) goto L128;
        if (MessageObject.getMedia(r3.messageOwner).webpage.photo == null) goto L128;
        if (MessageObject.getMedia(r3.messageOwner).webpage.photo.dc_id <= 0) goto L128;
        r03 = MessageObject.getMedia(r3.messageOwner).webpage.photo.dc_id;
    L128:
        if (MessageObject.getMedia(r3.messageOwner).webpage == null) goto L134;
        if (MessageObject.getMedia(r3.messageOwner).webpage.document == null) goto L134;
        if (MessageObject.getMedia(r3.messageOwner).webpage.document.dc_id <= 0) goto L134;
        r03 = MessageObject.getMedia(r3.messageOwner).webpage.document.dc_id;
    L105:
        r62.add(new Item(3, R.drawable.msg_noise_on, LocaleController.getString(R.string.Bitrate), "0 Kbps"));
        int r08 = (int) r3.getDuration();
        if (r08 <= 0) goto L109;
        r62.add(new Item(R.drawable.msg2_animations, LocaleController.getString(R.string.Duration), AndroidUtilities.formatShortDuration(r08)));
        goto L109
    L101:
        if (TextUtils.isEmpty(this.filePath) == true) goto L103;
        r62.add(new Item(2, R.drawable.msg_location, LocaleController.getString(R.string.ShareLocation), "0.0, 0.0"));
    L98:
        r62.add(new Item(4, R.drawable.msg_photo_crop, LocaleController.getString(R.string.Resolution), "0x0"));
        goto L99
    L93:
        if (TextUtils.isEmpty(this.filePath) == true) goto L95;
        r24 = r02;
        r62.add(new Item(5, R.drawable.menu_devices, LocaleController.getString(R.string.Platform), LocaleController.getString(R.string.NumberUnknown)));
        goto L96
    L85:
        if (r3.isPhoto() == true) goto L90;
        if (r3.isSticker() == true) goto L90;
        r10 = false;
        goto L91
    L74:
        if (r3.isRoundVideo() == true) goto L81;
        if (r3.isVideoSticker() == true) goto L81;
        if (r3.isGif() == true) goto L81;
        r15 = false;
        goto L82
    L67:
        if (r3.isMusic() == true) goto L70;
        r02 = false;
        goto L72
    L47:
        if (MessageObject.getMedia(r3.messageOwner).document == null) goto L61;
        ArrayList<TLRPC.DocumentAttribute> r711 = MessageObject.getMedia(r3.messageOwner).document.attributes;
        int r128 = r711.size();
        int r133 = 0;
    L49:
        if (r133 >= r128) goto L61;
        TLRPC.DocumentAttribute r143 = r711.get(r133);
        r133 = r133 + 1;
        TLRPC.DocumentAttribute r144 = r143;
        if ((r144 instanceof TLRPC.TL_documentAttributeFilename) == false) goto L54;
        r62.add(new Item(R.drawable.msg_log, LocaleController.getString(R.string.FileName), r144.file_name));
    L54:
        if ((r144 instanceof TLRPC.TL_documentAttributeSticker) == false) goto L49;
        TLRPC.InputStickerSet r09 = r144.stickerset;
        if (r09 == null) goto L49;
        long r102 = ChatUtils.extractOwnerId(r09.id);
        this.ownerId = r102;
        if (r102 <= 0) goto L49;
        r62.add(new Item(0, R.drawable.msg_sticker, LocaleController.getString(R.string.ChannelCreator), String.valueOf(this.ownerId)));
        goto L49
    L32:
        if (r124 == r75.date) goto L36;
        if (r75.edit_hide == true) goto L36;
        r62.add(new Item(R.drawable.msg_edit, LocaleController.getString(R.string.EditedDate), formatTime(r3.messageOwner.edit_date, true)));
        goto L36
    L9:
        r12 = 0;
        goto L10
    L5:
        r13 = AndroidUtilities.dp(40.0f);
        goto L7
    }

    private static double convertToDegrees(String r8) {
        String[] r82 = r8.split(",");
        double r0 = convertToDouble(r82[0]);
        double r2 = convertToDouble(r82[1]);
        return (r0 + (r2 / 60.0d)) + (convertToDouble(r82[2]) / 3600.0d);
    }

    private static double convertToDouble(String r7) {
        String[] r0 = r7.split("/");
        if (r0.length != 1) goto L7;
        return Double.parseDouble(r0[0]);
    L7:
        if (r0.length != 2) goto L14;
        double r1 = Double.parseDouble(r0[0]);
        double r3 = Double.parseDouble(r0[1]);
        if (r3 != 0.0d) goto L13;
        FileLog.e("Division by zero in GPS data");
        return 0.0d;
    L13:
        return r1 / r3;
    L14:
        FileLog.e("Invalid rational number format: ".concat(r7));
        return 0.0d;
    }

    private View createGap() {
        ActionBarPopupWindow.GapView r0 = new ActionBarPopupWindow.GapView(this.fragment.getContext(), this.resourcesProvider);
        r0.setDividerVisible(false);
        return r0;
    }

    private String formatTime(int r6, boolean r7) {
        if (r6 == 2147483646) goto L5;
        if (r7 == false) goto L10;
        long r1 = r6 * 1000;
        return LocaleController.formatString("formatDateAtTime", R.string.formatDateAtTime, new Object[]{LocaleController.getInstance().getFormatterYear().format(new Date(r1)), LocaleController.getInstance().getFormatterDayWithSeconds().format(new Date(r1))});
    L10:
        return LocaleController.formatDateAudio(r6, true);
    L5:
        return LocaleController.getString(R.string.SendWhenOnline);
    }

    public static int getBitrate(MessageObject r2, String r3) {
        if (TextUtils.isEmpty(r3) == false) goto L15;
    L8:
        int r32 = -1;
    L9:
        if (r32 != (-1)) goto L19;
        return getBitrateFromAttributes(r2);
    L12:
        e = move-exception;
        FileLog.e(e);
        return r32;
    L19:
        return r32;
    L15:
        r32 = getBitrateFromPath(r3);     // Catch: Exception -> L6
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    public static int getBitrateFromAttributes(MessageObject r9) {
        long r0 = MessageObject.getMessageSize(r9.messageOwner);
        if (r0 > 0) goto L6;
        return -1;
    L6:
        if (MessageObject.getMedia(r9.messageOwner) != null) goto L8;
    L19:
        return -1;
    L8:
        if (MessageObject.getMedia(r9.messageOwner).document == null) goto L19;
        ArrayList<TLRPC.DocumentAttribute> r92 = MessageObject.getMedia(r9.messageOwner).document.attributes;
        int r2 = r92.size();
        int r4 = 0;
    L10:
        if (r4 >= r2) goto L19;
        TLRPC.DocumentAttribute r5 = r92.get(r4);
        r4 = r4 + 1;
        TLRPC.DocumentAttribute r52 = r5;
        if ((r52 instanceof TLRPC.TL_documentAttributeAudio) == true) goto L15;
        if ((r52 instanceof TLRPC.TL_documentAttributeVideo) == false) goto L10;
    L15:
        double r53 = r52.duration;
        if (r53 <= 0.0d) goto L10;
        return (int) (((r0 / r53) * 8.0d) / 1000.0d);
    }

    public static int getBitrateFromPath(String r1) {
        MediaMetadataRetriever r0 = new MediaMetadataRetriever();
        r0.setDataSource(r1);     // Catch: Exception -> L5
        String r12 = r0.extractMetadata(20);     // Catch: Exception -> L5
        Objects.requireNonNull(r12);     // Catch: Exception -> L5
        int r13 = Integer.parseInt(r12) / 1000;     // Catch: Exception -> L5
    L12:
        r0.release();     // Catch: Throwable -> L9
    L11:
        return r13;
    L9:
        th = move-exception;
        FileLog.e(th);
    L5:
        e = move-exception;
        FileLog.e(e);
        r13 = -1;
        goto L12
    }

    public static String[] getLatLongFromPhoto(File r5) {
        ExifInterface r0 = new ExifInterface(r5.getAbsolutePath());     // Catch: Exception -> L15
        String r52 = r0.getAttribute("GPSLatitude");     // Catch: Exception -> L15
        String r1 = r0.getAttribute("GPSLongitude");     // Catch: Exception -> L15
        String r2 = r0.getAttribute("GPSLatitudeRef");     // Catch: Exception -> L15
        String r02 = r0.getAttribute("GPSLongitudeRef");     // Catch: Exception -> L15
        if (r52 == null) goto L21;
        if (r1 == null) goto L22;
        if (r2 == null) goto L23;
        if (r02 == null) goto L24;
        double r3 = convertToDegrees(r52);     // Catch: Exception -> L15
        if ("S".equalsIgnoreCase(r2) == false) goto L10;
        r3 = -r3;
    L10:
        double r12 = convertToDegrees(r1);     // Catch: Exception -> L15
        if ("W".equalsIgnoreCase(r02) == false) goto L13;
        r12 = -r12;
    L13:
        DecimalFormat r53 = new DecimalFormat("#.######");     // Catch: Exception -> L15
        r53.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ENGLISH));     // Catch: Exception -> L15
        return new String[]{r53.format(r3), r53.format(r12)};
    L24:
        return null;
    L23:
        return null;
    L22:
        return null;
    L21:
        return null;
    L15:
        e = move-exception;
        FileLog.e(e);
        return null;
    }

    public static Dimension getPhotoResolution(MessageObject r1, String r2) {
        if (TextUtils.isEmpty(r2) == false) goto L15;
    L8:
        Dimension r22 = null;
    L9:
        if (r22 != null) goto L19;
        return getPhotoResolutionFromAttributes(r1);
    L12:
        e = move-exception;
        FileLog.e(e);
        return r22;
    L19:
        return r22;
    L15:
        r22 = getPhotoResolutionFromPath(r2);     // Catch: Exception -> L6
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    public static Dimension getPhotoResolutionFromAttributes(MessageObject r5) {
        int r1 = 0;
        Dimension r2 = null;
        if (MessageObject.getMedia(r5.messageOwner) == null) goto L24;
        if (MessageObject.getMedia(r5.messageOwner).photo == null) goto L24;
        TLRPC.PhotoSize r0 = FileLoader.getClosestPhotoSizeWithSize(MessageObject.getMedia(r5.messageOwner).photo.sizes, AndroidUtilities.getPhotoSize(), false, null, true);
        if (r0 == null) goto L13;
        int r3 = r0.w;
        if (r3 <= 0) goto L13;
        int r02 = r0.h;
        if (r02 <= 0) goto L13;
        r2 = new Dimension(r3, r02);
    L13:
        if (r2 != null) goto L22;
        TLRPC.VideoSize r52 = FileLoader.getClosestVideoSizeWithSize(MessageObject.getMedia(r5.messageOwner).photo.video_sizes, AndroidUtilities.getPhotoSize(), false, true);
        if (r52 == null) goto L22;
        int r03 = r52.w;
        if (r03 <= 0) goto L22;
        int r53 = r52.h;
        if (r53 <= 0) goto L22;
        return new Dimension(r03, r53);
    L22:
        return r2;
    L24:
        if (MessageObject.getMedia(r5.messageOwner) != null) goto L26;
    L37:
        return null;
    L26:
        if (MessageObject.getMedia(r5.messageOwner).document == null) goto L37;
        ArrayList<TLRPC.DocumentAttribute> r54 = MessageObject.getMedia(r5.messageOwner).document.attributes;
        int r04 = r54.size();
    L28:
        if (r1 >= r04) goto L37;
        TLRPC.DocumentAttribute r32 = r54.get(r1);
        r1 = r1 + 1;
        TLRPC.DocumentAttribute r33 = r32;
        if ((r33 instanceof TLRPC.TL_documentAttributeImageSize) == false) goto L28;
        int r4 = r33.w;
        if (r4 <= 0) goto L28;
        int r34 = r33.h;
        if (r34 <= 0) goto L28;
        return new Dimension(r4, r34);
    }

    public static Dimension getPhotoResolutionFromPath(String r2) {
        BitmapFactory.Options r0 = new BitmapFactory.Options();
        r0.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(r2, r0);
        return new Dimension(r0.outWidth, r0.outHeight);
    }

    public static Dimension getVideoResolution(MessageObject r1, String r2) {
        if (TextUtils.isEmpty(r2) == false) goto L15;
    L8:
        Dimension r22 = null;
    L9:
        if (r22 != null) goto L19;
        return getVideoResolutionFromAttributes(r1);
    L12:
        e = move-exception;
        FileLog.e(e);
        return r22;
    L19:
        return r22;
    L15:
        r22 = getVideoResolutionFromPath(r2);     // Catch: Exception -> L6
    L6:
        e = move-exception;
        FileLog.e(e);
        goto L8
    }

    public static Dimension getVideoResolutionFromAttributes(MessageObject r4) {
        if (MessageObject.getMedia(r4.messageOwner) != null) goto L5;
        return null;
    L5:
        if (MessageObject.getMedia(r4.messageOwner).document == null) goto L26;
        ArrayList<TLRPC.DocumentAttribute> r42 = MessageObject.getMedia(r4.messageOwner).document.attributes;
        int r0 = r42.size();
        int r1 = 0;
    L7:
        if (r1 >= r0) goto L27;
        TLRPC.DocumentAttribute r2 = r42.get(r1);
        r1 = r1 + 1;
        TLRPC.DocumentAttribute r22 = r2;
        if ((r22 instanceof TLRPC.TL_documentAttributeVideo) == false) goto L7;
        int r3 = r22.w;
        if (r3 <= 0) goto L7;
        int r23 = r22.h;
        if (r23 <= 0) goto L7;
        return new Dimension(r3, r23);
    L27:
        return null;
    L26:
        return null;
    }

    public static Dimension getVideoResolutionFromPath(String r3) {
        MediaMetadataRetriever r0 = new MediaMetadataRetriever();
        int r1 = 0;
        r0.setDataSource(r3);     // Catch: Exception -> L9
        String r32 = r0.extractMetadata(18);     // Catch: Exception -> L9
        Objects.requireNonNull(r32);     // Catch: Exception -> L9
        int r33 = Integer.parseInt(r32);     // Catch: Exception -> L9
        String r2 = r0.extractMetadata(19);     // Catch: Exception -> L7
        Objects.requireNonNull(r2);     // Catch: Exception -> L7
        r1 = Integer.parseInt(r2);     // Catch: Exception -> L7
    L22:
        r0.release();     // Catch: Throwable -> L14
    L17:
        return new Dimension(r33, r1);
    L14:
        th = move-exception;
        FileLog.e(th);
    L7:
        e = e;
    L11:
        FileLog.e(e);
    L9:
        e = e;
        r33 = 0;
        goto L11
    }

    private boolean isPhotoAsDocument(MessageObject r5) {
    L17:
        e = move-exception;
        FileLog.e(e);
    L19:
        return false;
    L4:
        if (MessageObject.getMedia(r5.messageOwner) == null) goto L19;
        if (MessageObject.getMedia(r5.messageOwner).document == null) goto L19;
        ArrayList<TLRPC.DocumentAttribute> r52 = MessageObject.getMedia(r5.messageOwner).document.attributes;     // Catch: Exception -> L17
        int r0 = r52.size();     // Catch: Exception -> L17
        int r1 = 0;
    L8:
        if (r1 >= r0) goto L19;
        TLRPC.DocumentAttribute r2 = r52.get(r1);     // Catch: Exception -> L17
        r1 = r1 + 1;     // Catch: Exception -> L17
        TLRPC.DocumentAttribute r22 = r2;     // Catch: Exception -> L17
        if ((r22 instanceof TLRPC.TL_documentAttributeImageSize) == false) goto L8;
        if (r22.w <= 0) goto L8;
        if (r22.h <= 0) goto L8;
        return true;
    }

    private /* synthetic */ void lambda$new$10(ActionBarMenuSubItem r2, Item r3) {
        JpegFingerprint r0 = JpegFingerprint.parse(this.filePath);
        this.photoFingerprint = r0;
        AndroidUtilities.runOnUIThread(new MessageDetailsPopupWrapper$$ExternalSyntheticLambda9(MediaUtils.getPhotoPlatform(r0), r2, r3));
    }

    private /* synthetic */ void lambda$new$11(Item r3, Activity r4, boolean r5, boolean r6, MessageObject r7, BaseFragment r8, View r9) {
        closeMenu();
        if (r3.id == 1) goto L5;
    L18:
        int r42 = r3.id;
        if (r42 != 0) goto L27;
        if (r3.subtitle.startsWith("@") == false) goto L24;
        Bundle r32 = new Bundle();
        r32.putLong("user_id", this.ownerId);
        r8.presentFragment(new ProfileActivity(r32));
        return;
    L24:
        copy(String.valueOf(this.ownerId));
        return;
    L27:
        if (r42 != 2) goto L30;
        Activity r33 = r8.getParentActivity();
        String[] r2 = this.geo;
        Browser.openUrl(r33, String.format("https://maps.google.com/?q=%s,%s", new Object[]{r2[0], r2[1]}));
        return;
    L30:
        String r43 = r3.subtitle;
        if (r43 != null) goto L34;
        r43 = r3.title;
    L34:
        copy(r43);
        return;
    L5:
        if (TextUtils.isEmpty(this.filePath) == true) goto L18;
        Uri r22 = FileProvider.getUriForFile(r4, ApplicationLoader.getApplicationId() + ".provider", new File(this.filePath));     // Catch: IllegalArgumentException -> L15
        if (r5 == true) goto L9;
        if (r6 == true) goto L9;
    L13:
        Intent r34 = new Intent("android.intent.action.SEND");
        r34.setFlags(1);
        r34.putExtra("android.intent.extra.STREAM", r22);
        r34.setDataAndType(r22, r7.getMimeType());
        r4.startActivityForResult(Intent.createChooser(r34, LocaleController.getString(R.string.ShareFile)), MediaError.DetailedErrorCode.SEGMENT_UNKNOWN);
        return;
    L9:
        Intent r35 = new Intent("android.intent.action.VIEW");
        r35.setFlags(1);
        r35.setDataAndType(r22, r7.getMimeType());
        if (r4.getPackageManager().queryIntentActivities(r35, 0).isEmpty() == true) goto L13;
        r4.startActivity(r35);
        return;
    L15:
        e = move-exception;
        FileLog.e(e);
    }

    private /* synthetic */ boolean lambda$new$12(Item r3, View r4) {
        if (r3.id == 1) goto L5;
    L7:
        int r42 = r3.id;
        if (r42 != 0) goto L11;
        String r32 = String.valueOf(this.ownerId);
    L19:
        copy(r32);
        return true;
    L11:
        if (r42 != 5) goto L15;
        JpegFingerprint r43 = this.photoFingerprint;
        if (r43 == null) goto L15;
        r32 = r43.describe();
    L15:
        String r44 = r3.subtitle;
        if (r44 == null) goto L18;
        r32 = r44;
        goto L19
    L18:
        r32 = r3.title;
        goto L19
    L5:
        if (TextUtils.isEmpty(this.filePath) == true) goto L7;
        r32 = this.filePath;
        goto L19
    }

    private /* synthetic */ void lambda$new$3(ActionBarMenuSubItem r6, Item r7) {
        if (this.geo == null) goto L6;
        r6.setSubtext(this.geo[0] + ", " + this.geo[1]);
        r7.subtitle = this.geo[0] + ", " + this.geo[1];
        return;
    L6:
        r6.setVisibility(8);
    }

    private /* synthetic */ void lambda$new$4(ActionBarMenuSubItem r3, Item r4) {
        this.geo = getLatLongFromPhoto(new File(this.filePath));
        AndroidUtilities.runOnUIThread(new MessageDetailsPopupWrapper$$ExternalSyntheticLambda10(this, r3, r4));
    }

    private /* synthetic */ void lambda$new$6(MessageObject r1, ActionBarMenuSubItem r2, Item r3) {
        AndroidUtilities.runOnUIThread(new MessageDetailsPopupWrapper$$ExternalSyntheticLambda11(getBitrate(r1, this.filePath), r2, r3));
    }

    private /* synthetic */ void lambda$new$8(boolean r1, MessageObject r2, ActionBarMenuSubItem r3, Item r4) {
        String r0 = this.filePath;
        if (r1 == false) goto L5;
        Dimension r02 = getVideoResolution(r2, r0);
    L6:
        AndroidUtilities.runOnUIThread(new MessageDetailsPopupWrapper$$ExternalSyntheticLambda12(r02, r3, r4));
        return;
    L5:
        r02 = getPhotoResolution(r2, r0);
        goto L6
    }

    public void closeMenu() {
    }

    public abstract void copy(String r1);
}
