package org.telegram.ui;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.view.View;
import j$.time.LocalDate;
import j$.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BirthdayController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LiteMode;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.Cells.TextDetailCell;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.spoilers.SpoilersTextView;

/* loaded from: classes6.dex */
public class ProfileBirthdayEffect extends View {
    public static String[] interactions = null;
    public static String interactionsPack = "EmojiAnimations";
    public static String numbersEmojipack = "FestiveFontEmoji";
    private boolean attached;
    private boolean autoplayed;
    private final int currentAccount;
    private final long dialogId;
    private BirthdayEffectFetcher fetcher;
    private BirthdayEffectFetcher fetcherToSet;
    private boolean isPlaying;
    private long lastTime;
    private final ProfileActivity profileActivity;
    public PointF sourcePoint;
    private float t;

    public static class BirthdayEffectFetcher {
        public final int age;
        public ArrayList<ImageReceiverAsset> allAssets;
        private ArrayList<Runnable> callbacks;
        public final int currentAccount;
        private boolean detachLater;
        public ArrayList<ImageReceiverAsset> digitAssets;
        public ImageReceiverAsset interactionAsset;
        private boolean loaded;
        public ArrayList<ImageReceiverAsset> loadedAssets;
        private final boolean[] setsLoaded;
        public ArrayList<ProfileBirthdayEffect> views;

        /* renamed from: $r8$lambda$9F-cTDTRhoSDn7qFOLtwiO_X6N4, reason: not valid java name */
        public static /* synthetic */ void m18293$r8$lambda$9FcTDTRhoSDn7qFOLtwiO_X6N4(BirthdayEffectFetcher r0) {
            r0.lambda$new$2();
        }

        public static /* synthetic */ void $r8$lambda$CP78Z6k1ZZWn_h8tHvNmWuFtUj8(BirthdayEffectFetcher r0, ImageReceiverAsset r1) {
            r0.lambda$new$0(r1);
        }

        /* renamed from: $r8$lambda$OkcmfVnX0x3PPmSo9-WADzCLEVM, reason: not valid java name */
        public static /* synthetic */ void m18294$r8$lambda$OkcmfVnX0x3PPmSo9WADzCLEVM(BirthdayEffectFetcher r0, HashSet r1, ArrayList r2, TLRPC.TL_messages_stickerSet r3) {
            r0.lambda$new$1(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$cbp0Ce47FZAicALOKJ6QDim4-TY, reason: not valid java name */
        public static /* synthetic */ void m18295$r8$lambda$cbp0Ce47FZAicALOKJ6QDim4TY(BirthdayEffectFetcher r0, String r1, TLRPC.TL_messages_stickerSet r2) {
            r0.lambda$new$3(r1, r2);
        }

        /* renamed from: -$$Nest$fgetloaded, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m18296$$Nest$fgetloaded(BirthdayEffectFetcher r0) {
            return r0.loaded;
        }

        private BirthdayEffectFetcher(int r8, int r9) {
            this.digitAssets = new ArrayList();
            this.allAssets = new ArrayList();
            this.loadedAssets = new ArrayList();
            boolean[] r2 = new boolean[2];
            this.setsLoaded = r2;
            this.callbacks = new ArrayList();
            this.views = new ArrayList();
            this.currentAccount = r8;
            this.age = r9;
            if (r9 > 0) goto L5;
            r2[0] = true;
        L16:
            String r92 = ProfileBirthdayEffect.interactions[Utilities.random.nextInt(ProfileBirthdayEffect.interactions.length)];
            TLRPC.TL_inputStickerSetShortName r22 = new TLRPC.TL_inputStickerSetShortName();
            r22.short_name = ProfileBirthdayEffect.interactionsPack;
            MediaDataController.getInstance(r8).getStickerSet(r22, 0, false, new ProfileBirthdayEffect$BirthdayEffectFetcher$$ExternalSyntheticLambda1(this, r92));
            return;
        L5:
            ArrayList r23 = new ArrayList();
            HashSet r3 = new HashSet();
            String r93 = _UrlKt.FRAGMENT_ENCODE_SET + r9;
            int r4 = 0;
        L7:
            if (r4 >= r93.length()) goto L15;
            int r5 = r93.charAt(r4) - '0';
            if (r5 < 0) goto L14;
            if (r5 > 9) goto L14;
            r23.add(Integer.valueOf(r5));
            r3.add(Integer.valueOf(r5));
        L14:
            r4 = r4 + 1;
            goto L7
        L15:
            TLRPC.TL_inputStickerSetShortName r94 = new TLRPC.TL_inputStickerSetShortName();
            r94.short_name = ProfileBirthdayEffect.numbersEmojipack;
            MediaDataController.getInstance(r8).getStickerSet(r94, 0, false, new ProfileBirthdayEffect$BirthdayEffectFetcher$$ExternalSyntheticLambda0(this, r3, r23));
            goto L16
        }

        private /* synthetic */ void lambda$new$0(ImageReceiverAsset r2) {
            this.loadedAssets.add(r2);
            checkWhenLoaded();
        }

        private /* synthetic */ void lambda$new$1(HashSet r7, ArrayList r8, TLRPC.TL_messages_stickerSet r9) {
            HashMap r0 = new HashMap();
            Iterator r72 = r7.iterator();
        L4:
            if (r72.hasNext() == false) goto L12;
            Integer r1 = (Integer) r72.next();
            TLRPC.Document r2 = SelectAnimatedEmojiDialog.findSticker(r9, r1 + "️⃣");
            if (r2 != null) goto L8;
            r2 = SelectAnimatedEmojiDialog.findSticker(r9, r1 + "⃣");
        L8:
            if (r2 == null) goto L9;
            r0.put(r1, r2);
            goto L4
        L9:
            FileLog.e("couldn't find " + r1 + "️⃣ emoji in " + ProfileBirthdayEffect.numbersEmojipack);
            return;
        L12:
            HashMap r73 = new HashMap();
            Iterator r02 = r0.entrySet().iterator();
        L14:
            if (r02.hasNext() == false) goto L16;
            Map.Entry r12 = (Map.Entry) r02.next();
            Integer r22 = (Integer) r12.getKey();
            r22.intValue();
            ImageReceiverAsset r3 = new ImageReceiverAsset(null);
            this.allAssets.add(r3);
            r3.setEmoji((TLRPC.Document) r12.getValue(), "80_80", r9, new ProfileBirthdayEffect$BirthdayEffectFetcher$$ExternalSyntheticLambda3(this, r3));
            r3.onAttachedToWindow();
            r73.put(r22, r3);
            goto L14
        L16:
            int r03 = 0;
        L18:
            if (r03 >= r8.size()) goto L20;
            Integer r13 = (Integer) r8.get(r03);
            r13.intValue();
            this.digitAssets.add((ImageReceiverAsset) r73.get(r13));
            r03 = r03 + 1;
            goto L18
        L20:
            this.setsLoaded[0] = true;
            checkWhenLoaded();
        }

        private /* synthetic */ void lambda$new$2() {
            this.loadedAssets.add(this.interactionAsset);
            checkWhenLoaded();
        }

        private /* synthetic */ void lambda$new$3(String r5, TLRPC.TL_messages_stickerSet r6) {
            TLRPC.Document r0 = SelectAnimatedEmojiDialog.findSticker(r6, r5);
            if (r0 != null) goto L6;
            FileLog.e("couldn't find " + r5 + " sticker in " + ProfileBirthdayEffect.interactionsPack);
            return;
        L6:
            ImageReceiverAsset r52 = new ImageReceiverAsset(null);
            this.interactionAsset = r52;
            this.allAssets.add(r52);
            int r53 = EmojiAnimationsOverlay.getFilterWidth();
            this.interactionAsset.setAutoRepeat(0);
            this.interactionAsset.setEmoji(r0, r53 + "_" + r53 + "_precache", r6, new ProfileBirthdayEffect$BirthdayEffectFetcher$$ExternalSyntheticLambda2(this));
            this.interactionAsset.onAttachedToWindow();
            this.setsLoaded[1] = true;
            checkWhenLoaded();
        }

        public static BirthdayEffectFetcher of(int r3, TLRPC.UserFull r4, BirthdayEffectFetcher r5) {
            if (LiteMode.isEnabled(2) == true) goto L5;
        L21:
            if (r5 == null) goto L25;
            r5.detach(false);
            return null;
        L25:
            return null;
        L5:
            if (BirthdayController.isToday(r4) == false) goto L21;
            if (r4 == null) goto L13;
            TL_account.TL_birthday r42 = r4.birthday;
            if (r42 == null) goto L13;
            if ((r42.flags & 1) == 0) goto L13;
            int r43 = Period.between(LocalDate.of(r42.year, r42.month, r42.day), LocalDate.now()).getYears();
        L14:
            if (r5 == null) goto L20;
            if (r5.age != r43) goto L18;
            return r5;
        L18:
            r5.detach(false);
        L20:
            return new BirthdayEffectFetcher(r3, r43);
        L13:
            r43 = 0;
            goto L14
        }

        public void addView(ProfileBirthdayEffect r1) {
            this.views.add(r1);
        }

        public void checkWhenLoaded() {
            if (this.loaded == false) goto L5;
            return;
        L5:
            if (this.loadedAssets.size() < this.allAssets.size()) goto L20;
            boolean[] r0 = this.setsLoaded;
            int r1 = 0;
            if (r0[0] == true) goto L10;
            return;
        L10:
            if (r0[1] == false) goto L21;
            this.loaded = true;
            ArrayList<Runnable> r02 = this.callbacks;
            int r2 = r02.size();
        L13:
            if (r1 >= r2) goto L15;
            Runnable r3 = r02.get(r1);
            r1 = r1 + 1;
            r3.run();
            goto L13
        L15:
            this.callbacks.clear();
            return;
        L21:
            return;
        }

        public void detach(boolean r3) {
            if (r3 == false) goto L4;
        L7:
            this.callbacks.clear();
            int r32 = 0;
        L8:
            int r0 = this.allAssets.size();
            ArrayList<ImageReceiverAsset> r1 = this.allAssets;
            if (r32 >= r0) goto L11;
            r1.get(r32).onDetachedFromWindow();
            r32 = r32 + 1;
            goto L8
        L11:
            r1.clear();
            return;
        L4:
            if (this.views.isEmpty() == true) goto L7;
            this.detachLater = true;
        }

        public void removeView(ProfileBirthdayEffect r2) {
            this.views.remove(r2);
            if (this.views.isEmpty() == true) goto L5;
            return;
        L5:
            if (this.detachLater == false) goto L9;
            detach(true);
            this.detachLater = false;
            return;
        }

        public void subscribe(Runnable r2) {
            if (this.loaded == false) goto L6;
            r2.run();
            return;
        L6:
            this.callbacks.add(r2);
        }
    }

    public static class ImageReceiverAsset extends ImageReceiver {

        /* renamed from: org.telegram.ui.ProfileBirthdayEffect$ImageReceiverAsset$1, reason: invalid class name */
        public class AnonymousClass1 implements ImageReceiver.ImageReceiverDelegate {
            final /* synthetic */ ImageReceiverAsset this$0;
            final /* synthetic */ Runnable[] val$callback;

            /* renamed from: $r8$lambda$KpChqo-gLAmWFbTAkMrzQEJILDc, reason: not valid java name */
            public static /* synthetic */ void m18297$r8$lambda$KpChqogLAmWFbTAkMrzQEJILDc(Runnable[] r2) {
                r2[0].run();
                r2[0] = null;
            }

            public AnonymousClass1(ImageReceiverAsset r1, Runnable[] r2) {
                this.this$0 = r1;
                this.val$callback = r2;
            }

            @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
            public void didSetImage(ImageReceiver r2, boolean r3, boolean r4, boolean r5) {
                if (r2.hasBitmapImage() == true) goto L5;
                return;
            L5:
                if (this.val$callback[0] == null) goto L17;
                RLottieDrawable r22 = r2.getLottieAnimation();
                if (r22 != null) goto L10;
                this.val$callback[0].run();
                this.val$callback[0] = null;
                return;
            L10:
                boolean r52 = r22.isGeneratingCache();
                Runnable[] r0 = this.val$callback;
                if (r52 == false) goto L14;
                r22.whenCacheDone = new ProfileBirthdayEffect$ImageReceiverAsset$1$$ExternalSyntheticLambda0(r0);
                return;
            L14:
                r0[0].run();
                this.val$callback[0] = null;
                return;
            }
        }

        private ImageReceiverAsset() {
        }

        public /* synthetic */ ImageReceiverAsset(ProfileBirthdayEffectIA r1) {
            this();
        }

        public void setEmoji(TLRPC.Document r9, String r10, TLRPC.TL_messages_stickerSet r11, Runnable r12) {
            setDelegate(new AnonymousClass1(this, new Runnable[]{r12}));
            setImage(ImageLocation.getForDocument(r9), r10, null, null, r11, 0);
        }
    }

    /* renamed from: $r8$lambda$2JrsXNVf-X2BA-2TPVEJpVFXQNw, reason: not valid java name */
    public static /* synthetic */ void m18292$r8$lambda$2JrsXNVfX2BA2TPVEJpVFXQNw(ProfileBirthdayEffect r0) {
        r0.lambda$onDraw$0();
    }

    static {
        interactions = new String[]{"🎉", "🎆", "🎈"};
    }

    public ProfileBirthdayEffect(ProfileActivity r3, BirthdayEffectFetcher r4) {
        super(r3.getContext());
        this.sourcePoint = new PointF();
        this.t = 1.0f;
        this.isPlaying = false;
        this.currentAccount = r3.getCurrentAccount();
        this.dialogId = r3.getDialogId();
        this.profileActivity = r3;
        this.fetcher = r4;
    }

    private /* synthetic */ void lambda$onDraw$0() {
        start();
    }

    private void updateSourcePoint() {
        RecyclerListView r0 = this.profileActivity.getListView();
        int r1 = this.profileActivity.birthdayRow;
        if (r1 < 0) goto L15;
        int r2 = 0;
    L7:
        if (r2 >= r0.getChildCount()) goto L20;
        View r3 = r0.getChildAt(r2);
        if (r1 != r0.getChildAdapterPosition(r3)) goto L14;
        if ((r3 instanceof TextDetailCell) == false) goto L14;
        SpoilersTextView r12 = ((TextDetailCell) r3).textView;
        this.sourcePoint.set(((r0.getX() + r3.getX()) + r12.getX()) + AndroidUtilities.dp(12.0f), ((r0.getY() + r3.getY()) + r12.getY()) + (r12.getMeasuredHeight() / 2.0f));
        return;
    L14:
        r2 = r2 + 1;
        goto L7
    L20:
        return;
    }

    public void hide() {
        animate().alpha(0.0f).setDuration(200).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
    }

    @Override // android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.fetcher.addView(this);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (this.attached == false) goto L9;
        int r1 = 0;
    L6:
        if (r1 >= this.fetcher.allAssets.size()) goto L8;
        this.fetcher.allAssets.get(r1).setParentView(null);
        r1 = r1 + 1;
        goto L6
    L8:
        this.attached = false;
    L9:
        this.fetcher.removeView(this);
    }

    @Override // android.view.View
    public void onDraw(Canvas r19) {
        if (BirthdayEffectFetcher.m18296$$Nest$fgetloaded(this.fetcher) == false) goto L28;
        int r4 = 1;
        if (this.attached == true) goto L15;
        int r2 = 0;
    L9:
        if (r2 >= this.fetcher.allAssets.size()) goto L11;
        this.fetcher.allAssets.get(r2).setParentView(this);
        r2 = r2 + 1;
        goto L9
    L11:
        this.attached = true;
        if (this.autoplayed == true) goto L15;
        this.autoplayed = true;
        post(new ProfileBirthdayEffect$$ExternalSyntheticLambda0(this));
    L15:
        if (this.isPlaying == true) goto L17;
        return;
    L17:
        long r5 = System.currentTimeMillis();
        this.t = Utilities.clamp(this.t + (Utilities.clamp(r5 - this.lastTime, 20, 0) / 4200.0f), 1.0f, 0.0f);
        this.lastTime = r5;
        updateSourcePoint();
        int r52 = EmojiAnimationsOverlay.getFilterWidth();
        float r9 = 2.0f;
        this.fetcher.interactionAsset.setImageCoords((getWidth() - AndroidUtilities.dp(r52)) / 2.0f, Math.max(0.0f, this.sourcePoint.y - (AndroidUtilities.dp(r5) * 0.5f)), AndroidUtilities.dp(r5), AndroidUtilities.dp(r5));
        r19.save();
        r19.scale(-1.0f, 1.0f, getWidth() / 2.0f, 0.0f);
        this.fetcher.interactionAsset.draw(r19);
        this.fetcher.interactionAsset.setAlpha(1.0f - ((this.t - 0.9f) / 0.1f));
        r19.restore();
        int r53 = AndroidUtilities.dp(110.0f);
        int r6 = this.fetcher.digitAssets.size() - 1;
    L18:
        if (r6 < 0) goto L21;
        ImageReceiverAsset r7 = this.fetcher.digitAssets.get(r6);
        float r11 = r6;
        float r10 = AndroidUtilities.cascade(this.t, r11, this.fetcher.digitAssets.size(), 1.8f);
        float r13 = r53;
        float r14 = 0.88f * r13;
        float r12 = (getWidth() - ((this.fetcher.digitAssets.size() - r4) * r14)) / r9;
        PointF r15 = this.sourcePoint;
        float r42 = r15.x;
        float r152 = r15.y;
        float r43 = (r42 + (r14 * r11)) + ((r12 - r42) * r10);
        float r142 = r9;
        float r153 = r152 - ((r152 + r13) * ((float) Math.pow(this.t, 2.0d)));
        float r92 = CubicBezierInterpolator.EASE_OUT_QUINT.getInterpolation(Utilities.clamp(r10 / 0.4f, 1.0f, 0.0f));
        float r102 = (r13 / r142) * r92;
        float r132 = r13 * r92;
        r7.setImageCoords(r43 - r102, r153 - r102, r132, r132);
        r7.draw(r19);
        r6 = r6 - 1;
        r9 = r142;
        r4 = 1;
        goto L18
    L21:
        if (this.t < 1.0f) goto L24;
        this.isPlaying = false;
        updateFetcher(this.fetcherToSet);
        this.fetcherToSet = null;
        return;
    L24:
        invalidate();
        return;
    }

    public boolean start() {
        if (BirthdayEffectFetcher.m18296$$Nest$fgetloaded(this.fetcher) == true) goto L6;
        return false;
    L6:
        if (this.t >= 1.0f) goto L9;
        return false;
    L9:
        if (this.fetcher.interactionAsset.getLottieAnimation() == null) goto L11;
        this.fetcher.interactionAsset.getLottieAnimation().setCurrentFrame(0, false);
        this.fetcher.interactionAsset.getLottieAnimation().restart(true);
    L11:
        this.isPlaying = true;
        this.t = 0.0f;
        invalidate();
        return true;
    }

    public void updateFetcher(BirthdayEffectFetcher r5) {
        if (this.fetcher == r5) goto L26;
        if (r5 != null) goto L7;
        return;
    L7:
        if (this.isPlaying == false) goto L10;
        this.fetcherToSet = r5;
        return;
    L10:
        int r1 = 0;
        if (this.attached == false) goto L17;
        int r0 = 0;
    L14:
        if (r0 >= this.fetcher.allAssets.size()) goto L16;
        this.fetcher.allAssets.get(r0).setParentView(null);
        r0 = r0 + 1;
        goto L14
    L16:
        this.attached = false;
    L17:
        this.fetcher.removeView(this);
        this.fetcher = r5;
        if (this.attached == false) goto L20;
        return;
    L20:
        if (r1 >= r5.allAssets.size()) goto L22;
        r5.allAssets.get(r1).setParentView(this);
        r1 = r1 + 1;
        goto L20
    L22:
        this.attached = true;
        return;
    }
}
