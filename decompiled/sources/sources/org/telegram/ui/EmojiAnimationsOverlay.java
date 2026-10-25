package org.telegram.ui;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import com.android.dx.rop.code.RegisterSpec;
import com.exteragram.messenger.utils.system.VibratorUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import okhttp3.internal.url._UrlKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.EmojiData;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.Reactions.AnimatedEmojiEffect;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.StickerSetBulletinLayout;
import org.telegram.ui.Components.StickersAlert;
import org.telegram.ui.Stories.StoryReactionWidgetView;

/* loaded from: classes6.dex */
public class EmojiAnimationsOverlay implements NotificationCenter.NotificationCenterDelegate {
    private static final HashSet<String> excludeEmojiFromPack = null;
    private static final HashSet<String> supportedEmoji = null;
    private final int ANIMATION_JSON_VERSION;
    private final String INTERACTIONS_STICKER_PACK;
    ArrayList<Integer> animationIndexes;
    private boolean attached;
    ChatActivity chatActivity;
    FrameLayout contentLayout;
    int currentAccount;
    long dialogId;
    final ArrayList<DrawingObject> drawingObjects;
    HashMap<String, ArrayList<TLRPC.Document>> emojiInteractionsStickersMap;
    Runnable hintRunnable;
    boolean inited;
    HashMap<Long, Integer> lastAnimationIndex;
    String lastTappedEmoji;
    int lastTappedMsgId;
    long lastTappedTime;
    RecyclerListView listView;
    private HashMap<Long, Boolean> preloaded;
    Random random;
    Runnable sentInteractionsRunnable;
    TLRPC.TL_messages_stickerSet set;
    long threadMsgId;
    ArrayList<Long> timeIntervals;

    /* renamed from: org.telegram.ui.EmojiAnimationsOverlay$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ EmojiAnimationsOverlay this$0;
        final /* synthetic */ int val$animation;
        final /* synthetic */ int val$messageId;

        public AnonymousClass1(EmojiAnimationsOverlay r1, int r2, int r3) {
            this.this$0 = r1;
            this.val$messageId = r2;
            this.val$animation = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            EmojiAnimationsOverlay.m14701$$Nest$mfindViewAndShowAnimation(this.this$0, this.val$messageId, this.val$animation);
        }
    }

    /* renamed from: org.telegram.ui.EmojiAnimationsOverlay$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ EmojiAnimationsOverlay this$0;
        final /* synthetic */ Bulletin val$bulletin;

        public AnonymousClass2(EmojiAnimationsOverlay r1, Bulletin r2) {
            this.this$0 = r1;
            this.val$bulletin = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.val$bulletin.show();
            this.this$0.hintRunnable = null;
        }
    }

    /* renamed from: org.telegram.ui.EmojiAnimationsOverlay$3, reason: invalid class name */
    public class AnonymousClass3 implements ImageReceiver.ImageReceiverDelegate {
        final /* synthetic */ EmojiAnimationsOverlay this$0;
        final /* synthetic */ DrawingObject val$drawingObject;
        final /* synthetic */ MessageObject val$messageObject;
        final /* synthetic */ boolean val$sendTap;

        public AnonymousClass3(EmojiAnimationsOverlay r1, DrawingObject r2, boolean r3, MessageObject r4) {
            this.this$0 = r1;
            this.val$drawingObject = r2;
            this.val$sendTap = r3;
            this.val$messageObject = r4;
        }

        @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
        public void didSetImage(ImageReceiver r1, boolean r2, boolean r3, boolean r4) {
            if (this.val$drawingObject.imageReceiver.getLottieAnimation() == null) goto L6;
            this.val$drawingObject.imageReceiver.getLottieAnimation().setCurrentFrame(0, false, true);
            return;
        }

        @Override // org.telegram.messenger.ImageReceiver.ImageReceiverDelegate
        public void onAnimationReady(ImageReceiver r2) {
            if (this.val$sendTap == false) goto L17;
            MessageObject r0 = this.val$messageObject;
            if (r0 != null) goto L7;
            return;
        L7:
            if (r0.isAnimatedAnimatedEmoji() == true) goto L9;
            return;
        L9:
            if (r2.getLottieAnimation() != null) goto L11;
            return;
        L11:
            if (r2.getLottieAnimation().hasVibrationPattern() == true) goto L21;
            this.this$0.contentLayout.performHapticFeedback(VibratorUtils.getType(3), 1);     // Catch: Exception -> L14
            return;
        L22:
            return;
        L21:
            return;
        }
    }

    public static class DrawingObject {
        TLRPC.Document document;
        public long documentId;
        private String fileName;
        public AnimatedEmojiEffect genericEffect;
        ImageReceiver imageReceiver;
        public boolean isMessageEffect;
        boolean isOut;
        public boolean isPremiumSticker;
        public boolean isReaction;
        public float lastH;
        public float lastW;
        public float lastX;
        public float lastY;
        int messageId;
        public float randomOffsetX;
        public float randomOffsetY;
        float removeProgress;
        boolean removing;
        public boolean viewFound;
        boolean wasPlayed;

        public DrawingObject() {
            ImageReceiver r0 = new ImageReceiver();
            this.imageReceiver = r0;
            r0.setAllowLoadingOnAttachedOnly(true);
            this.imageReceiver.setAllowDrawWhileCacheGenerating(true);
        }

        public float getProgress() {
            ImageLocation r0 = this.imageReceiver.getMediaLocation();
            if (r0 != null) goto L5;
            r0 = this.imageReceiver.getImageLocation();
        L5:
            if (r0 != null) goto L8;
            r0 = this.imageReceiver.getThumbLocation();
        L8:
            if (r0 != null) goto L11;
            return -1.0f;
        L11:
            if (this.fileName != null) goto L17;
            TLRPC.Document r2 = r0.document;
            if (r2 == null) goto L15;
            this.fileName = FileLoader.getAttachFileName(r2, "tgs");
            goto L17
        L15:
            this.fileName = FileLoader.getAttachFileName(r0.location, "tgs");
        L17:
            if (this.fileName != null) goto L19;
            return -1.0f;
        L19:
            Float r4 = ImageLoader.getInstance().getFileProgress(this.fileName);
            if (r4 != null) goto L23;
            r4 = Float.valueOf(1.0f);
        L23:
            return ((r4.floatValue() * 0.55f) + 0.15f) + (0.3f * r4.floatValue());
        }
    }

    public static /* synthetic */ void $r8$lambda$KDnjy3l_gSrlOhnh0ZAjPNOHZ1s(EmojiAnimationsOverlay r0, TLObject r1, MessageObject r2) {
        r0.lambda$createDrawingObject$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Lg6LaxPnXzVs9olBEIUMvcZ8Ykk(EmojiAnimationsOverlay r0, MessageObject r1) {
        r0.lambda$showStickerSetBulletin$3(r1);
    }

    /* renamed from: $r8$lambda$Pfe5rK-YxtxhuWZHF66lUqbz0xc, reason: not valid java name */
    public static /* synthetic */ void m14700$r8$lambda$Pfe5rKYxtxhuWZHF66lUqbz0xc(EmojiAnimationsOverlay r0, MessageObject r1, TLObject r2, TLRPC.TL_error r3) {
        r0.lambda$createDrawingObject$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$pYbetYrUkHayxjBk3hcf5D7B4wY(EmojiAnimationsOverlay r0) {
        r0.lambda$createDrawingObject$2();
    }

    /* renamed from: -$$Nest$mfindViewAndShowAnimation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14701$$Nest$mfindViewAndShowAnimation(EmojiAnimationsOverlay r0, int r1, int r2) {
        r0.findViewAndShowAnimation(r1, r2);
    }

    static {
        supportedEmoji = new HashSet();
        HashSet<String> r0 = new HashSet();
        excludeEmojiFromPack = r0;
        r0.add("0⃣");
        r0.add("1⃣");
        r0.add("2⃣");
        r0.add("3⃣");
        r0.add("4⃣");
        r0.add("5⃣");
        r0.add("6⃣");
        r0.add("7⃣");
        r0.add("8⃣");
        r0.add("9⃣");
    }

    public EmojiAnimationsOverlay(FrameLayout r3, int r4) {
        this.ANIMATION_JSON_VERSION = 1;
        this.INTERACTIONS_STICKER_PACK = "EmojiAnimations";
        this.inited = false;
        this.emojiInteractionsStickersMap = new HashMap();
        this.lastAnimationIndex = new HashMap();
        this.random = new Random();
        this.lastTappedMsgId = -1;
        this.lastTappedTime = 0;
        this.timeIntervals = new ArrayList();
        this.animationIndexes = new ArrayList();
        this.drawingObjects = new ArrayList();
        this.contentLayout = r3;
        this.currentAccount = r4;
    }

    public EmojiAnimationsOverlay(ChatActivity r3, FrameLayout r4, RecyclerListView r5, int r6, long r7, long r9) {
        this.ANIMATION_JSON_VERSION = 1;
        this.INTERACTIONS_STICKER_PACK = "EmojiAnimations";
        this.inited = false;
        this.emojiInteractionsStickersMap = new HashMap();
        this.lastAnimationIndex = new HashMap();
        this.random = new Random();
        this.lastTappedMsgId = -1;
        this.lastTappedTime = 0;
        this.timeIntervals = new ArrayList();
        this.animationIndexes = new ArrayList();
        this.drawingObjects = new ArrayList();
        this.chatActivity = r3;
        this.contentLayout = r4;
        this.listView = r5;
        this.currentAccount = r6;
        this.dialogId = r7;
        this.threadMsgId = r9;
    }

    private void clearSendingInfo() {
        this.lastTappedMsgId = 0;
        this.lastTappedEmoji = null;
        this.lastTappedTime = 0;
        this.timeIntervals.clear();
        this.animationIndexes.clear();
    }

    private void findViewAndShowAnimation(int r6, int r7) {
        if (this.attached == false) goto L45;
        int r1 = 0;
    L7:
        if (r1 >= this.listView.getChildCount()) goto L20;
        View r2 = this.listView.getChildAt(r1);
        if ((r2 instanceof ChatMessageCell) == false) goto L19;
        ChatMessageCell r22 = (ChatMessageCell) r2;
        String r3 = r22.getMessageObject().getStickerEmoji();
        if (r3 != null) goto L14;
        r3 = r22.getMessageObject().messageOwner.message;
    L14:
        if (r22.getPhotoImage().hasNotThumb() == false) goto L19;
        if (r3 == null) goto L19;
        if (r22.getMessageObject().getId() != r6) goto L19;
    L21:
        if (r22 == null) goto L43;
        ChatActivity r62 = this.chatActivity;
        if (r62 == null) goto L44;
        r62.restartSticker(r22);
        if (EmojiData.hasEmojiSupportVibration(r22.getMessageObject().getStickerEmoji()) == false) goto L27;
    L32:
        showAnimationForCell(r22, r7, false, true);
        return;
    L27:
        if (r22.getMessageObject().isPremiumSticker() == true) goto L32;
        if (r22.getMessageObject().isAnimatedAnimatedEmoji() == true) goto L32;
        r22.performHapticFeedback(3);     // Catch: Exception -> L34
        goto L32
    L44:
        return;
    L43:
        return;
    L19:
        r1 = r1 + 1;
        goto L7
    L20:
        r22 = null;
        goto L21
    }

    public static int getFilterWidth() {
        if (AndroidUtilities.isTablet() == false) goto L6;
        float r0 = AndroidUtilities.getMinTabletSide();
        float r1 = 0.4f;
    L8:
        return (int) ((((int) (r0 * r1)) * 2.0f) / AndroidUtilities.density);
    L6:
        Point r02 = AndroidUtilities.displaySize;
        r0 = Math.min(r02.x, r02.y);
        r1 = 0.5f;
        goto L8
    }

    private /* synthetic */ void lambda$createDrawingObject$0(TLObject r1, MessageObject r2) {
        showStickerSetBulletin((TLRPC.TL_messages_stickerSet) r1, r2);
    }

    private /* synthetic */ void lambda$createDrawingObject$1(MessageObject r1, TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new EmojiAnimationsOverlay$$ExternalSyntheticLambda3(this, r2, r1));
    }

    private /* synthetic */ void lambda$createDrawingObject$2() {
        sendCurrentTaps();
        this.sentInteractionsRunnable = null;
    }

    private /* synthetic */ void lambda$showStickerSetBulletin$3(MessageObject r9) {
        Activity r1 = this.chatActivity.getParentActivity();
        ChatActivity r2 = this.chatActivity;
        TLRPC.InputStickerSet r3 = r9.getInputStickerSet();
        ChatActivity r92 = this.chatActivity;
        StickersAlert r0 = new StickersAlert(r1, r2, r3, null, r92.chatActivityEnterView, r92.getResourceProvider(), false);
        r0.setCalcMandatoryInsets(this.chatActivity.isKeyboardVisible());
        this.chatActivity.showDialog(r0);
    }

    private void preloadAnimation(TLRPC.Document r4) {
        if (r4 == null) goto L14;
        HashMap<Long, Boolean> r0 = this.preloaded;
        if (r0 == null) goto L10;
        if (r0.containsKey(Long.valueOf(r4.id)) == false) goto L10;
        return;
    L10:
        if (this.preloaded != null) goto L12;
        this.preloaded = new HashMap();
    L12:
        this.preloaded.put(Long.valueOf(r4.id), Boolean.TRUE);
        MediaDataController.getInstance(this.currentAccount).preloadImage(ImageLocation.getForDocument(r4), 2);
        return;
    }

    private void sendCurrentTaps() {
        if (this.lastTappedMsgId != 0) goto L5;
        return;
    L5:
        TLRPC.TL_sendMessageEmojiInteraction r0 = new TLRPC.TL_sendMessageEmojiInteraction();
        r0.msg_id = this.lastTappedMsgId;
        r0.emoticon = this.lastTappedEmoji;
        r0.interaction = new TLRPC.TL_dataJSON();
        JSONObject r1 = new JSONObject();
        r1.put(RegisterSpec.PREFIX, 1);     // Catch: JSONException -> L10
        JSONArray r2 = new JSONArray();     // Catch: JSONException -> L10
        int r4 = 0;
    L8:
        if (r4 >= this.timeIntervals.size()) goto L12;
        JSONObject r5 = new JSONObject();     // Catch: JSONException -> L10
        r5.put("i", this.animationIndexes.get(r4).intValue() + 1);     // Catch: JSONException -> L10
        r5.put("t", this.timeIntervals.get(r4).longValue() / 1000.0f);     // Catch: JSONException -> L10
        r2.put(r4, r5);     // Catch: JSONException -> L10
        r4 = r4 + 1;     // Catch: JSONException -> L10
        goto L8
    L12:
        r1.put("a", r2);     // Catch: JSONException -> L10
        r0.interaction.data = r1.toString();
        TLRPC.TL_messages_setTyping r12 = new TLRPC.TL_messages_setTyping();
        long r42 = this.threadMsgId;
        if (r42 == 0) goto L16;
        r12.top_msg_id = (int) r42;
        r12.flags |= 1;
    L16:
        r12.action = r0;
        r12.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r12, null);
        clearSendingInfo();
        return;
    L10:
        e = move-exception;
        clearSendingInfo();
        FileLog.e(e);
    }

    private void showStickerSetBulletin(TLRPC.TL_messages_stickerSet r8, MessageObject r9) {
        if (this.chatActivity != null) goto L6;
        return;
    L6:
        if (MessagesController.getInstance(this.currentAccount).premiumFeaturesBlocked() == false) goto L8;
        return;
    L8:
        if (this.chatActivity.getParentActivity() == null) goto L14;
        if (r8 == null) goto L16;
        StickerSetBulletinLayout r1 = new StickerSetBulletinLayout(this.contentLayout.getContext(), null, -1, r9.getDocument(), this.chatActivity.getResourceProvider());
        r1.titleTextView.setText(r8.set.title);
        r1.subtitleTextView.setText(LocaleController.getString(R.string.PremiumStickerTooltip));
        Bulletin.UndoButton r82 = new Bulletin.UndoButton(this.chatActivity.getParentActivity(), true, this.chatActivity.getResourceProvider());
        r1.setButton(r82);
        r82.setUndoAction(new EmojiAnimationsOverlay$$ExternalSyntheticLambda2(this, r9));
        r82.setText(LocaleController.getString(R.string.ViewAction));
        Bulletin r7 = Bulletin.make(this.chatActivity, r1, 2750);
        r7.hash = r9.getId();
        r7.show();
        return;
    L16:
        return;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.lang.CharSequence] */
    /* JADX WARN: Type inference failed for: r8v8, types: [java.lang.CharSequence] */
    public static String unwrapEmoji(String r8) {
        if (r8 != null) goto L5;
        return null;
    L5:
        int r0 = r8.length();
        int r2 = 0;
        String r82 = r8;
    L6:
        if (r2 >= r0) goto L28;
        if (r2 >= (r0 - 1)) goto L24;
        if (r82.charAt(r2) != 55356) goto L16;
        int r3 = r2 + 1;
        if (r82.charAt(r3) < 57339) goto L16;
        if (r82.charAt(r3) > 57343) goto L16;
    L21:
        r0 = r0 - 2;
        r82 = TextUtils.concat(new CharSequence[]{r82.subSequence(0, r2), r82.subSequence(r2 + 2, r82.length())});
    L22:
        r2 = r2 - 1;
    L26:
        r2 = r2 + 1;
        r82 = r82;
    L16:
        if (r82.charAt(r2) != 8205) goto L24;
        int r32 = r2 + 1;
        if (r82.charAt(r32) == 9792) goto L21;
        if (r82.charAt(r32) == 9794) goto L21;
    L24:
        if (r82.charAt(r2) != 65039) goto L26;
        r0 = r0 - 1;
        r82 = TextUtils.concat(new CharSequence[]{r82.subSequence(0, r2), r82.subSequence(r2 + 1, r82.length())});
        goto L22
    L28:
        return r82.toString();
    }

    public void cancelAllAnimations() {
        int r0 = 0;
    L4:
        if (r0 >= this.drawingObjects.size()) goto L6;
        this.drawingObjects.get(r0).removing = true;
        r0 = r0 + 1;
        goto L4
    }

    public void cancelHintRunnable() {
        Runnable r0 = this.hintRunnable;
        if (r0 == null) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r0);
    L5:
        this.hintRunnable = null;
    }

    public boolean checkPosition(ChatMessageCell r1, float r2, int r3) {
        float r0 = r1.getY() + r1.getPhotoImage().getCenterY();
        if (r0 > r2) goto L5;
        return false;
    L5:
        if (r0 >= r3) goto L10;
        return true;
    L10:
        return false;
    }

    public void checkStickerPack() {
        if (this.inited == true) goto L45;
        TLRPC.TL_messages_stickerSet r0 = MediaDataController.getInstance(this.currentAccount).getStickerSetByName("EmojiAnimations");
        this.set = r0;
        if (r0 != null) goto L9;
        this.set = MediaDataController.getInstance(this.currentAccount).getStickerSetByEmojiOrName("EmojiAnimations");
    L9:
        if (this.set != null) goto L12;
        MediaDataController.getInstance(this.currentAccount).loadStickersByEmojiOrName("EmojiAnimations", false, true);
    L12:
        if (this.set == null) goto L44;
        HashMap r02 = new HashMap();
        int r1 = 0;
    L15:
        if (r1 >= this.set.documents.size()) goto L17;
        r02.put(Long.valueOf(this.set.documents.get(r1).id), this.set.documents.get(r1));
        r1 = r1 + 1;
        goto L15
    L17:
        int r12 = 0;
    L19:
        if (r12 >= this.set.packs.size()) goto L35;
        TLRPC.TL_stickerPack r4 = this.set.packs.get(r12);
        if (excludeEmojiFromPack.contains(r4.emoticon) == true) goto L34;
        if (r4.documents.size() <= 0) goto L34;
        supportedEmoji.add(r4.emoticon);
        ArrayList<TLRPC.Document> r5 = new ArrayList();
        this.emojiInteractionsStickersMap.put(r4.emoticon, r5);
        int r6 = 0;
    L26:
        if (r6 >= r4.documents.size()) goto L29;
        r5.add((TLRPC.Document) r02.get(r4.documents.get(r6)));
        r6 = r6 + 1;
        goto L26
    L29:
        if (r4.emoticon.equals("❤") == false) goto L34;
        String[] r42 = {"🧡", "💛", "💚", "💙", "💜", "🖤", "🤍", "🤎"};
        int r62 = 0;
    L32:
        if (r62 >= 8) goto L34;
        String r7 = r42[r62];
        supportedEmoji.add(r7);
        this.emojiInteractionsStickersMap.put(r7, r5);
        r62 = r62 + 1;
    L34:
        r12 = r12 + 1;
        goto L19
    L35:
        this.inited = true;
        return;
    L44:
        return;
    }

    public void clear() {
        int r0 = 0;
    L3:
        int r1 = this.drawingObjects.size();
        ArrayList<DrawingObject> r2 = this.drawingObjects;
        if (r0 >= r1) goto L9;
        r2.get(r0).imageReceiver.onDetachedFromWindow();
        if (this.drawingObjects.get(r0).genericEffect == null) goto L8;
        this.drawingObjects.get(r0).genericEffect.removeView(this.contentLayout);
    L8:
        r0 = r0 + 1;
        goto L3
    L9:
        r2.clear();
    }

    public boolean createDrawingObject(String r28, int r29, TLRPC.Document r30, MessageObject r31, int r32, boolean r33, boolean r34, float r35, float r36, boolean r37) {
        int r5 = r32;
        boolean r8 = false;
        if (r31 != null) goto L5;
    L7:
        boolean r9 = false;
    L8:
        if (r31 != null) goto L10;
    L12:
        boolean r10 = false;
    L13:
        if (r10 == true) goto L17;
        if (r9 == true) goto L17;
        if (supportedEmoji.contains(r28) == true) goto L17;
    L24:
        return false;
    L17:
        ArrayList<TLRPC.Document> r11 = this.emojiInteractionsStickersMap.get(r28);
        if (r10 == true) goto L25;
        if (r11 != null) goto L21;
    L22:
        if (r9 == false) goto L24;
    L21:
        if (r11.isEmpty() == true) goto L22;
    L25:
        int r12 = 0;
        int r13 = 0;
        int r14 = 0;
    L27:
        if (r12 >= this.drawingObjects.size()) goto L45;
        if (this.drawingObjects.get(r12).messageId != r29) goto L38;
        r13 = r13 + 1;
        if (r10 == true) goto L38;
        if (this.drawingObjects.get(r12).imageReceiver.getLottieAnimation() == null) goto L36;
        if (this.drawingObjects.get(r12).imageReceiver.getLottieAnimation().isGeneratingCache() == false) goto L38;
    L36:
        return r8;
    L38:
        if (this.drawingObjects.get(r12).document == null) goto L43;
        if (r30 == null) goto L43;
        boolean r16 = r8;
        boolean r17 = r9;
        if (this.drawingObjects.get(r12).document.id != r30.id) goto L44;
        r14 = r14 + 1;
    L44:
        r12 = r12 + 1;
        r8 = r16;
        r9 = r17;
    L43:
        r16 = r8;
        r17 = r9;
        goto L44
    L45:
        boolean r162 = r8;
        boolean r172 = r9;
        if (r33 == false) goto L65;
        if (r172 == false) goto L65;
        if (r13 <= 0) goto L65;
        if (Bulletin.getVisibleBulletin() != null) goto L52;
    L54:
        TLRPC.InputStickerSet r1 = r31.getInputStickerSet();
        if (r1.short_name == null) goto L57;
        TLRPC.TL_messages_stickerSet r3 = MediaDataController.getInstance(this.currentAccount).getStickerSetByName(r1.short_name);
    L58:
        if (r3 != null) goto L60;
        r3 = MediaDataController.getInstance(this.currentAccount).getStickerSetById(r1.id);
    L60:
        if (r3 != null) goto L62;
        TLRPC.TL_messages_getStickerSet r2 = new TLRPC.TL_messages_getStickerSet();
        r2.stickerset = r1;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r2, new EmojiAnimationsOverlay$$ExternalSyntheticLambda0(this, r31));
    L63:
        return r162;
    L62:
        showStickerSetBulletin(r3, r31);
        goto L63
    L57:
        r3 = null;
        goto L58
    L52:
        if (Bulletin.getVisibleBulletin().hash != r31.getId()) goto L54;
        return r162;
    L65:
        if (r13 < 4) goto L67;
        return r162;
    L67:
        if (r10 == false) goto L93;
        TLRPC.TL_availableEffect r112 = r31.getEffect();
        TLRPC.messages_AvailableEffects r122 = MessagesController.getInstance(this.currentAccount).getAvailableEffects();
        if (r122 != null) goto L71;
        return r162;
    L71:
        long r18 = 0;
        long r7 = r112.effect_animation_id;
        if (r7 != 0) goto L75;
        r7 = r112.effect_sticker_id;
    L75:
        int r92 = r162 ? 1 : 0;
    L77:
        if (r92 >= r122.documents.size()) goto L84;
        TLRPC.Document r132 = r122.documents.get(r92);
        if (r132 == null) goto L83;
        if (r132.id != r7) goto L83;
    L85:
        if (r132 != null) goto L88;
        return r162;
    L88:
        if (r112.effect_sticker_id == 0) goto L124;
        TLRPC.VideoSize r38 = MessageObject.getPremiumStickerAnimation(r132);
        if (r38 == null) goto L92;
        boolean r93 = true;
    L130:
        if (r132 != null) goto L133;
        if (r38 != null) goto L133;
        return r162;
    L133:
        DrawingObject r4 = new DrawingObject();
        r4.isPremiumSticker = r93;
        r4.isMessageEffect = r10;
        if (r10 == true) goto L136;
        r4.randomOffsetX = (r35 / 4.0f) * ((this.random.nextInt() % 101) / 100.0f);
        r4.randomOffsetY = (r36 / 4.0f) * ((this.random.nextInt() % 101) / 100.0f);
    L136:
        r4.messageId = r29;
        r4.document = r132;
        r4.isOut = r37;
        r4.imageReceiver.setAllowStartAnimation(true);
        r4.imageReceiver.setAllowLottieVibration(r33);
        if (SharedConfig.getDevicePerformanceClass() > 1) goto L139;
    L142:
        boolean r72 = true;
    L144:
        if (r38 != null) goto L158;
        int r39 = getFilterWidth();
        boolean r322 = r72;
        Integer r73 = this.lastAnimationIndex.get(Long.valueOf(r132.id));
        if (r73 != null) goto L149;
        int r74 = r162 ? 1 : 0;
    L150:
        int r75 = r74 + 1;
        boolean r123 = r93;
        this.lastAnimationIndex.put(Long.valueOf(r132.id), Integer.valueOf(r75));
        ImageLocation r21 = ImageLocation.getForDocument(r132);
        r4.imageReceiver.setUniqKeyPrefix(r75 + "_" + r4.messageId + "_");
        ImageReceiver r76 = r4.imageReceiver;
        StringBuilder r82 = new StringBuilder();
        r82.append(r39);
        r82.append("_");
        r82.append(r39);
        if (r322 == false) goto L153;
        String r310 = "_pcache";
    L154:
        r82.append(r310);
        r76.setImage(r21, r82.toString(), null, "tgs", this.set, 1);
        r4.imageReceiver.setDelegate(new AnonymousClass3(this, r4, r33, r31));
        if (r4.imageReceiver.getLottieAnimation() == null) goto L157;
        r4.imageReceiver.getLottieAnimation().setCurrentFrame(r162 ? 1 : 0, r162, true);
    L157:
        int r102 = r5;
    L171:
        r4.imageReceiver.setLayerNum(Integer.MAX_VALUE);
        r4.imageReceiver.setAutoRepeat(0);
        if (r4.imageReceiver.getLottieAnimation() != null) goto L174;
    L177:
        this.drawingObjects.add(r4);
        r4.imageReceiver.onAttachedToWindow();
        r4.imageReceiver.setParentView(this.contentLayout);
        this.contentLayout.invalidate();
        if (r33 == false) goto L196;
        if (r123 == true) goto L196;
        if (UserConfig.getInstance(this.currentAccount).clientUserId == this.dialogId) goto L196;
        int r311 = this.lastTappedMsgId;
        if (r311 == 0) goto L188;
        if (r311 == r29) goto L188;
        Runnable r312 = this.sentInteractionsRunnable;
        if (r312 == null) goto L188;
        AndroidUtilities.cancelRunOnUIThread(r312);
        this.sentInteractionsRunnable.run();
    L188:
        this.lastTappedMsgId = r29;
        this.lastTappedEmoji = r28;
        if (this.lastTappedTime != r18) goto L191;
        this.lastTappedTime = System.currentTimeMillis();
        this.timeIntervals.clear();
        this.animationIndexes.clear();
        this.timeIntervals.add(Long.valueOf(r18));
        this.animationIndexes.add(Integer.valueOf(r102));
    L192:
        Runnable r22 = this.sentInteractionsRunnable;
        if (r22 == null) goto L195;
        AndroidUtilities.cancelRunOnUIThread(r22);
        this.sentInteractionsRunnable = null;
    L195:
        EmojiAnimationsOverlay$$ExternalSyntheticLambda1 r23 = new EmojiAnimationsOverlay$$ExternalSyntheticLambda1(this);
        this.sentInteractionsRunnable = r23;
        AndroidUtilities.runOnUIThread(r23, 500);
        goto L196
    L191:
        this.timeIntervals.add(Long.valueOf(System.currentTimeMillis() - this.lastTappedTime));
        this.animationIndexes.add(Integer.valueOf(r102));
    L196:
        if (r34 == false) goto L215;
        MessagesController.getInstance(this.currentAccount).sendTyping(this.dialogId, this.threadMsgId, 11, r28, 0);
        return true;
    L215:
        return true;
    L174:
        if (r4.isPremiumSticker == false) goto L176;
        r4.imageReceiver.getLottieAnimation().setCurrentFrame(0, false, true);
    L176:
        r4.imageReceiver.getLottieAnimation().start();
        goto L177
    L153:
        r310 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L154
    L149:
        r74 = r73.intValue();
        goto L150
    L158:
        boolean r323 = r72;
        r123 = r93;
        int r77 = getFilterWidth();
        if (r14 <= 0) goto L165;
        Integer r83 = this.lastAnimationIndex.get(Long.valueOf(r132.id));
        if (r83 != null) goto L163;
        int r84 = 0;
    L164:
        r102 = r5;
        this.lastAnimationIndex.put(Long.valueOf(r132.id), Integer.valueOf((r84 + 1) % 4));
        r4.imageReceiver.setUniqKeyPrefix(r84 + "_" + r4.messageId + "_");
    L166:
        r4.document = r132;
        ImageReceiver r52 = r4.imageReceiver;
        ImageLocation r212 = ImageLocation.getForDocument(r38, r132);
        StringBuilder r313 = new StringBuilder();
        r313.append(r77);
        r313.append("_");
        r313.append(r77);
        if (r323 == false) goto L169;
        String r85 = "_pcache";
    L170:
        r313.append(r85);
        r52.setImage(r212, r313.toString(), null, "tgs", this.set, 1);
        goto L171
    L169:
        r85 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L170
    L163:
        r84 = r83.intValue();
        goto L164
    L165:
        r102 = r5;
        goto L166
    L139:
        if (BuildVars.DEBUG_VERSION == false) goto L142;
        r72 = r162 ? 1 : 0;
        goto L144
    L92:
        r93 = r162 ? 1 : 0;
    L124:
        r93 = r172;
        r38 = null;
    L83:
        r92 = r92 + 1;
        goto L77
    L84:
        r132 = null;
        goto L85
    L93:
        r18 = 0;
        if (r172 == false) goto L96;
        r132 = r31.getDocument();
        r38 = r31.getPremiumStickerAnimation();
        r93 = r172;
        goto L130
    L96:
        if (r31 != null) goto L98;
    L125:
        if (r5 >= 0) goto L127;
    L128:
        r5 = Math.abs(this.random.nextInt()) % r11.size();
    L129:
        r132 = r11.get(r5);
        goto L124
    L127:
        if (r5 <= (r11.size() - 1)) goto L129;
    L98:
        if (r31.isAnimatedAnimatedEmoji() == false) goto L125;
        if (r5 >= 0) goto L101;
    L104:
        ArrayList r314 = new ArrayList();
        int r42 = r162 ? 1 : 0;
    L106:
        if (r42 >= r11.size()) goto L119;
        TLRPC.Document r53 = r11.get(r42);
        if (r53 == null) goto L118;
        HashMap<Long, Boolean> r78 = this.preloaded;
        if (r78 == null) goto L113;
        Boolean r54 = r78.get(Long.valueOf(r53.id));
    L114:
        if (r54 == null) goto L118;
        if (r54.booleanValue() == false) goto L118;
        r314.add(Integer.valueOf(r42));
        goto L118
    L113:
        r54 = null;
    L118:
        r42 = r42 + 1;
        goto L106
    L119:
        boolean r43 = r314.isEmpty();
        Random r55 = this.random;
        if (r43 == false) goto L122;
        int r315 = Math.abs(r55.nextInt()) % r11.size();
    L123:
        r132 = r11.get(r315);
        r5 = r315;
        goto L124
    L122:
        r315 = ((Integer) r314.get(Math.abs(r55.nextInt()) % r314.size())).intValue();
        goto L123
    L101:
        if (r5 > (r11.size() - 1)) goto L104;
        r315 = r5;
        goto L123
    L10:
        if (r31.getEffect() == null) goto L12;
        r10 = true;
        goto L13
    L5:
        if (r31.isPremiumSticker() == false) goto L7;
        r9 = true;
        goto L8
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r8, int r9, Object... r10) {
        int r0 = 0;
        if (r8 != NotificationCenter.diceStickersDidLoad) goto L9;
        if ("EmojiAnimations".equals((String) r10[0]) == false) goto L37;
        checkStickerPack();
        return;
    L37:
        return;
    L9:
        if (r8 != NotificationCenter.onEmojiInteractionsReceived) goto L27;
        if (this.chatActivity == null) goto L45;
        long r82 = ((Long) r10[0]).longValue();
        TLRPC.TL_sendMessageEmojiInteraction r102 = (TLRPC.TL_sendMessageEmojiInteraction) r10[1];
        if (r82 == this.dialogId) goto L16;
        return;
    L16:
        if (supportedEmoji.contains(r102.emoticon) == false) goto L39;
        int r83 = r102.msg_id;
        if (r102.interaction.data == null) goto L40;
        JSONArray r92 = new JSONObject(r102.interaction.data).getJSONArray("a");     // Catch: JSONException -> L24
    L20:
        if (r0 >= r92.length()) goto L46;
        JSONObject r103 = r92.getJSONObject(r0);     // Catch: JSONException -> L24
        int r2 = r103.optInt("i", 1) - 1;     // Catch: JSONException -> L24
        double r3 = r103.optDouble("t", 0.0d);     // Catch: JSONException -> L24
        AndroidUtilities.runOnUIThread(new AnonymousClass1(this, r83, r2), (long) (r3 * 1000.0d));     // Catch: JSONException -> L24
        r0 = r0 + 1;
        goto L20
    L46:
        return;
    L24:
        e = move-exception;
        e.printStackTrace();
        return;
    L40:
        return;
    L39:
        return;
    L45:
        return;
    L27:
        if (r8 != NotificationCenter.updateInterfaces) goto L42;
        Integer r84 = MessagesController.getInstance(this.currentAccount).getPrintingStringType(this.dialogId, this.threadMsgId);
        if (r84 != null) goto L31;
        return;
    L31:
        if (r84.intValue() != 5) goto L44;
        cancelHintRunnable();
        return;
    L44:
        return;
    }

    public void draw(Canvas r18) {
        if (this.drawingObjects.isEmpty() == true) goto L141;
        int r3 = 0;
    L5:
        int r4 = this.drawingObjects.size();
        ArrayList<DrawingObject> r5 = this.drawingObjects;
        if (r3 >= r4) goto L127;
        DrawingObject r42 = r5.get(r3);
        float r6 = 3.0f;
        if (this.chatActivity == null) goto L63;
        r42.viewFound = false;
        int r52 = 0;
    L11:
        if (r52 >= this.listView.getChildCount()) goto L37;
        View r10 = this.listView.getChildAt(r52);
        if ((r10 instanceof ChatMessageCell) == false) goto L16;
        ChatMessageCell r12 = (ChatMessageCell) r10;
        MessageObject r13 = r12.getMessageObject();
        ImageReceiver r122 = r12.getPhotoImage();
    L19:
        if (r13 == null) goto L36;
        if (r13.getId() != r42.messageId) goto L36;
        r42.viewFound = true;
        float r53 = this.listView.getX() + r10.getX();
        float r132 = this.listView.getY() + r10.getY();
        float r14 = r10.getY();
        r42.lastW = r122.getImageWidth();
        r42.lastH = r122.getImageHeight();
        if (r42.isMessageEffect == true) goto L25;
    L27:
        float r16 = r6;
        if (r42.isPremiumSticker == false) goto L30;
        r42.lastX = r53 + r122.getImageX();
        r42.lastY = r132 + r122.getImageY();
    L39:
        if (r42.viewFound == true) goto L41;
    L44:
        r42.removing = true;
    L46:
        if (r42.isPremiumSticker == false) goto L59;
        float r54 = r42.lastH / 2.0f;
        if ((this.listView.getMeasuredHeight() - r14) > r54) goto L50;
        boolean r62 = true;
    L52:
        if (((r14 - this.chatActivity.getChatListViewPadding()) + r54) > 0.0f) goto L54;
        boolean r55 = true;
    L55:
        if (r62 == true) goto L57;
        if (r55 == false) goto L59;
    L57:
        r42.removing = true;
        goto L59
    L54:
        r55 = false;
        goto L55
    L50:
        r62 = false;
    L59:
        if (r42.removing == false) goto L65;
        float r56 = r42.removeProgress;
        if (r56 == 1.0f) goto L65;
        float r57 = Utilities.clamp(r56 + 0.10666667f, 1.0f, 0.0f);
        r42.removeProgress = r57;
        r42.imageReceiver.setAlpha(1.0f - r57);
        this.chatActivity.contentView.invalidate();
    L65:
        if (r42.wasPlayed == false) goto L67;
    L69:
        boolean r58 = false;
    L70:
        if (r58 == false) goto L72;
    L90:
        AnimatedEmojiEffect r63 = r42.genericEffect;
        if (r63 == null) goto L94;
        boolean r64 = r63.isDone();
    L102:
        if (r42.removeProgress == 1.0f) goto L116;
        if (r64 == true) goto L116;
        if (r58 == true) goto L116;
        if (r42.imageReceiver.getLottieAnimation() == null) goto L112;
        if (r42.imageReceiver.getLottieAnimation().isRunning() == false) goto L112;
        r42.wasPlayed = true;
    L125:
        r3 = r3 + 1;
    L112:
        if (r42.imageReceiver.getLottieAnimation() == null) goto L125;
        if (r42.imageReceiver.getLottieAnimation().isRunning() == true) goto L125;
        r42.imageReceiver.getLottieAnimation().setCurrentFrame(0, true);
        r42.imageReceiver.getLottieAnimation().start();
    L116:
        DrawingObject r59 = this.drawingObjects.remove(r3);
        if (r42.isPremiumSticker == true) goto L119;
    L121:
        r59.imageReceiver.onDetachedFromWindow();
        AnimatedEmojiEffect r43 = r59.genericEffect;
        if (r43 == null) goto L124;
        r43.removeView(this.contentLayout);
    L124:
        r3 = r3 - 1;
        goto L125
    L119:
        if (r42.imageReceiver.getLottieAnimation() == null) goto L121;
        r59.imageReceiver.getLottieAnimation().setCurrentFrame(0, true, true);
        goto L121
    L94:
        if (r42.wasPlayed == true) goto L96;
    L100:
        r64 = false;
        goto L102
    L96:
        if (r42.imageReceiver.getLottieAnimation() == null) goto L100;
        if (r42.imageReceiver.getLottieAnimation().getCurrentFrame() < (r42.imageReceiver.getLottieAnimation().getFramesCount() - 2)) goto L100;
        r64 = true;
        goto L102
    L72:
        if (r42.isPremiumSticker == true) goto L74;
    L83:
        AnimatedEmojiEffect r65 = r42.genericEffect;
        if (r65 == null) goto L86;
        float r7 = r42.lastX + r42.randomOffsetX;
        float r102 = r42.lastY + r42.randomOffsetY;
        float r11 = r42.lastW * r16;
        r65.setBounds((int) r7, (int) r102, (int) (r7 + r11), (int) (r102 + r11));
        r42.genericEffect.draw(r18);
        goto L90
    L86:
        ImageReceiver r66 = r42.imageReceiver;
        float r72 = r42.lastX + r42.randomOffsetX;
        float r112 = r42.lastY + r42.randomOffsetY;
        float r123 = r42.lastW;
        r66.setImageCoords(r72, r112, r123 * r16, r123 * r16);
        if (r42.isOut == true) goto L89;
        r18.save();
        r18.scale(-1.0f, 1.0f, r42.imageReceiver.getCenterX(), r42.imageReceiver.getCenterY());
        r42.imageReceiver.draw(r18);
        r18.restore();
        goto L90
    L89:
        r42.imageReceiver.draw(r18);
        goto L90
    L74:
        if (r42.isMessageEffect == true) goto L83;
        float r67 = r42.lastH;
        float r113 = 1.49926f * r67;
        float r124 = 0.0546875f * r113;
        float r133 = ((r42.lastY + (r67 / 2.0f)) - (r113 / 2.0f)) - (0.00279f * r113);
        boolean r68 = r42.isOut;
        ImageReceiver r73 = r42.imageReceiver;
        if (r68 == true) goto L78;
        r73.setImageCoords(r42.lastX - r124, r133, r113, r113);
    L80:
        if (r42.isOut == true) goto L82;
        r18.save();
        r18.scale(-1.0f, 1.0f, r42.imageReceiver.getCenterX(), r42.imageReceiver.getCenterY());
        r42.imageReceiver.draw(r18);
        r18.restore();
        goto L90
    L82:
        r42.imageReceiver.draw(r18);
        goto L90
    L78:
        r73.setImageCoords(((r42.lastX + r42.lastW) - r113) + r124, r133, r113, r113);
        goto L80
    L67:
        if (r42.removing == false) goto L69;
        r58 = true;
        goto L70
    L41:
        if ((r42.lastH + r14) < this.chatActivity.getChatListViewPadding()) goto L44;
        if (r14 <= (this.listView.getMeasuredHeight() - this.chatActivity.blurredViewBottomOffset)) goto L46;
    L30:
        float r510 = r53 + r122.getImageX();
        float r134 = r132 + r122.getImageY();
        if (r42.isOut == false) goto L34;
        float r69 = ((-r122.getImageWidth()) * 2.0f) + AndroidUtilities.dp(24.0f);
    L33:
        float r511 = r510 + r69;
        float r135 = r134 - r122.getImageWidth();
        r42.lastX = r511;
        r42.lastY = r135;
        goto L39
    L34:
        r69 = -AndroidUtilities.dp(24.0f);
        goto L33
    L25:
        if ((r10 instanceof ChatMessageCell) == false) goto L27;
        ChatMessageCell r103 = (ChatMessageCell) r10;
        float r125 = (getFilterWidth() * AndroidUtilities.density) / 1.3f;
        float r15 = r125 / r6;
        r42.lastW = r15;
        r42.lastH = r15;
        float r152 = r125 / 2.0f;
        r16 = r6;
        r42.lastX = Utilities.clamp((r53 + r103.getTimeX()) - r152, AndroidUtilities.displaySize.x - r125, 0.0f);
        r42.lastY = (r132 + r103.getTimeY()) - r152;
    L36:
        r52 = r52 + 1;
        r6 = r6;
        goto L11
    L16:
        if ((r10 instanceof ChatActionCell) == false) goto L18;
        ChatActionCell r126 = (ChatActionCell) r10;
        r13 = r126.getMessageObject();
        r122 = r126.getPhotoImage();
        goto L19
    L18:
        r13 = null;
        r122 = null;
        goto L19
    L37:
        r16 = r6;
        r14 = 0.0f;
        goto L39
    L63:
        r16 = 3.0f;
        layoutObject(r42);
        goto L65
    L127:
        if (r5.isEmpty() == false) goto L129;
        onAllEffectsEnd();
    L129:
        this.contentLayout.invalidate();
        return;
    }

    public float getProgress() {
        if (this.drawingObjects.isEmpty() == false) goto L7;
        return -1.0f;
    L7:
        return this.drawingObjects.get(r1.size() - 1).getProgress();
    }

    public boolean isIdle() {
        return this.drawingObjects.isEmpty();
    }

    public void layoutObject(DrawingObject r1) {
    }

    public void onAllEffectsEnd() {
    }

    public void onAttachedToWindow() {
        this.attached = true;
        checkStickerPack();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.diceStickersDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.onEmojiInteractionsReceived);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.updateInterfaces);
        int r0 = 0;
    L4:
        if (r0 >= this.drawingObjects.size()) goto L9;
        this.drawingObjects.get(r0).imageReceiver.onAttachedToWindow();
        if (this.drawingObjects.get(r0).genericEffect == null) goto L8;
        this.drawingObjects.get(r0).genericEffect.setView(this.contentLayout);
    L8:
        r0 = r0 + 1;
        goto L4
    }

    public void onDetachedFromWindow() {
        int r0 = 0;
        this.attached = false;
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.diceStickersDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.onEmojiInteractionsReceived);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.updateInterfaces);
    L3:
        int r1 = this.drawingObjects.size();
        ArrayList<DrawingObject> r2 = this.drawingObjects;
        if (r0 >= r1) goto L9;
        r2.get(r0).imageReceiver.onDetachedFromWindow();
        if (this.drawingObjects.get(r0).genericEffect == null) goto L8;
        this.drawingObjects.get(r0).genericEffect.removeView(this.contentLayout);
    L8:
        r0 = r0 + 1;
        goto L3
    L9:
        r2.clear();
    }

    public void onScrolled(int r5) {
        int r0 = 0;
    L4:
        if (r0 >= this.drawingObjects.size()) goto L9;
        if (this.drawingObjects.get(r0).viewFound == true) goto L8;
        this.drawingObjects.get(r0).lastY -= r5;
    L8:
        r0 = r0 + 1;
        goto L4
    }

    public boolean onTapItem(ChatMessageCell r10, ChatActivity r11, boolean r12) {
        if (r11.isSecretChat() == false) goto L5;
    L57:
        return false;
    L5:
        if (r10.getMessageObject() == null) goto L57;
        if (r10.getMessageObject().getId() < 0) goto L57;
        if (r10.getMessageObject().isPremiumSticker() == false) goto L12;
    L14:
        boolean r0 = showAnimationForCell(r10, -1, r12, false);
        if (r12 == false) goto L25;
        if (r0 == false) goto L25;
        if (EmojiData.hasEmojiSupportVibration(r10.getMessageObject().getStickerEmoji()) == true) goto L25;
        if (r10.getMessageObject().isPremiumSticker() == true) goto L25;
        if (r10.getMessageObject().isAnimatedAnimatedEmoji() == true) goto L25;
        r10.performHapticFeedback(3);     // Catch: Exception -> L58
    L25:
        if (r10.getMessageObject().isPremiumSticker() == false) goto L27;
    L55:
        r10.getMessageObject().forcePlayEffect = false;
        r10.getMessageObject().messageOwner.premiumEffectWasPlayed = true;
        r11.getMessagesStorage().updateMessageCustomParams(this.dialogId, r10.getMessageObject().messageOwner);
        return r0;
    L27:
        if (r10.getEffect() != null) goto L55;
        if (r12 == false) goto L30;
    L32:
        Integer r122 = MessagesController.getInstance(this.currentAccount).getPrintingStringType(this.dialogId, this.threadMsgId);
        if (r122 == null) goto L38;
        if (r122.intValue() != 5) goto L38;
    L54:
        return r0;
    L38:
        if (this.hintRunnable != null) goto L54;
        if (r0 == false) goto L54;
        if (Bulletin.getVisibleBulletin() == null) goto L45;
        if (Bulletin.getVisibleBulletin().isShowing() == true) goto L54;
    L45:
        if (SharedConfig.emojiInteractionsHintCount <= 0) goto L54;
        if (UserConfig.getInstance(this.currentAccount).getClientUserId() == r11.currentUser.id) goto L54;
        SharedConfig.updateEmojiInteractionsHintCount(SharedConfig.emojiInteractionsHintCount - 1);
        if (r10.getMessageObject().isAnimatedAnimatedEmoji() == false) goto L52;
        TLRPC.Document r102 = r10.getMessageObject().getDocument();
    L51:
        TLRPC.Document r7 = r102;
        StickerSetBulletinLayout r3 = new StickerSetBulletinLayout(r11.getParentActivity(), null, -1, r7, r11.getResourceProvider());
        r3.subtitleTextView.setVisibility(8);
        r3.titleTextView.setText(Emoji.replaceEmoji(AndroidUtilities.replaceTags(LocaleController.formatString("EmojiInteractionTapHint", R.string.EmojiInteractionTapHint, new Object[]{r11.currentUser.first_name})), r3.titleTextView.getPaint().getFontMetricsInt(), false));
        r3.titleTextView.setTypeface(null);
        r3.titleTextView.setMaxLines(3);
        r3.titleTextView.setSingleLine(false);
        AnonymousClass2 r112 = new AnonymousClass2(this, Bulletin.make(r11, r3, 2750));
        this.hintRunnable = r112;
        AndroidUtilities.runOnUIThread(r112, 1500);
        goto L54
    L52:
        r102 = MediaDataController.getInstance(this.currentAccount).getEmojiAnimatedSticker(r10.getMessageObject().getStickerEmoji());
        goto L51
    L30:
        if (r10.getMessageObject().isAnimatedEmojiStickerSingle() == false) goto L32;
    L12:
        if (r11.currentUser != null) goto L14;
        return false;
    }

    public void preload(ReactionsLayoutInBubble.VisibleReaction r4) {
        String r0 = r4.emojicon;
        if (r0 != null) goto L5;
        r0 = MessageObject.findAnimatedEmojiEmoticon(AnimatedEmojiDrawable.findDocument(this.currentAccount, r4.documentId));
    L5:
        if (r0 == null) goto L17;
        ArrayList<TLRPC.Document> r42 = this.emojiInteractionsStickersMap.get(r0);
        if (r42 != null) goto L10;
        return;
    L10:
        if (r42.isEmpty() == true) goto L18;
        int r02 = Math.min(1, r42.size());
        int r1 = 0;
    L13:
        if (r1 >= r02) goto L19;
        preloadAnimation(r42.get(r1));
        r1 = r1 + 1;
        goto L13
    L19:
        return;
    L18:
        return;
    }

    public void preloadAnimation(ChatMessageCell r4) {
        MessageObject r42 = r4.getMessageObject();
        if (r42.isPremiumSticker() == true) goto L21;
        String r0 = r42.getStickerEmoji();
        if (r0 != null) goto L8;
        r0 = r42.messageOwner.message;
    L8:
        String r43 = unwrapEmoji(r0);
        if (supportedEmoji.contains(r43) == false) goto L22;
        ArrayList<TLRPC.Document> r44 = this.emojiInteractionsStickersMap.get(r43);
        if (r44 != null) goto L14;
        return;
    L14:
        if (r44.isEmpty() == true) goto L23;
        int r02 = Math.min(1, r44.size());
        int r1 = 0;
    L17:
        if (r1 >= r02) goto L24;
        preloadAnimation(r44.get(r1));
        r1 = r1 + 1;
        goto L17
    L24:
        return;
    L23:
        return;
    L22:
        return;
    }

    public void setAccount(int r1) {
        this.currentAccount = r1;
    }

    public boolean showAnimationForActionCell(ChatActionCell r17, TLRPC.Document r18, TLRPC.VideoSize r19) {
        if (this.drawingObjects.size() <= 12) goto L6;
        return false;
    L6:
        if (r17.getPhotoImage().hasNotThumb() == true) goto L8;
        return false;
    L8:
        float r2 = r17.getPhotoImage().getImageHeight();
        float r3 = r17.getPhotoImage().getImageWidth();
        if (r2 > 0.0f) goto L11;
    L54:
        return false;
    L11:
        if (r3 <= 0.0f) goto L54;
        int r5 = 0;
        int r6 = 0;
        int r7 = 0;
    L15:
        if (r5 >= this.drawingObjects.size()) goto L31;
        if (this.drawingObjects.get(r5).messageId != r17.getMessageObject().getId()) goto L24;
        r6 = r6 + 1;
        if (this.drawingObjects.get(r5).imageReceiver.getLottieAnimation() == null) goto L22;
        if (this.drawingObjects.get(r5).imageReceiver.getLottieAnimation().isGeneratingCache() == false) goto L24;
    L22:
        return false;
    L24:
        if (this.drawingObjects.get(r5).document == null) goto L29;
        if (r18 == null) goto L29;
        if (this.drawingObjects.get(r5).document.id != r18.id) goto L29;
        r7 = r7 + 1;
    L29:
        r5 = r5 + 1;
        goto L15
    L31:
        if (r6 < 4) goto L33;
        return false;
    L33:
        DrawingObject r62 = new DrawingObject();
        r62.isPremiumSticker = true;
        if (r62.isMessageEffect == true) goto L36;
        r62.randomOffsetX = (r3 / 4.0f) * ((this.random.nextInt() % 101) / 100.0f);
        r62.randomOffsetY = (r2 / 4.0f) * ((this.random.nextInt() % 101) / 100.0f);
    L36:
        r62.messageId = r17.getMessageObject().getId();
        r62.isOut = true;
        r62.imageReceiver.setAllowStartAnimation(true);
        int r22 = getFilterWidth();
        if (r7 <= 0) goto L43;
        Integer r72 = this.lastAnimationIndex.get(Long.valueOf(r18.id));
        if (r72 != null) goto L41;
        int r73 = 0;
    L42:
        this.lastAnimationIndex.put(Long.valueOf(r18.id), Integer.valueOf((r73 + 1) % 4));
        r62.imageReceiver.setUniqKeyPrefix(r73 + "_" + r62.messageId + "_");
        goto L43
    L41:
        r73 = r72.intValue();
    L43:
        r62.document = r18;
        r62.imageReceiver.setImage(ImageLocation.getForDocument(r19, r18), r22 + "_" + r22, null, "tgs", this.set, 1);
        r62.imageReceiver.setLayerNum(Integer.MAX_VALUE);
        r62.imageReceiver.setAutoRepeat(0);
        if (r62.imageReceiver.getLottieAnimation() != null) goto L46;
    L49:
        this.drawingObjects.add(r62);
        if (this.attached == false) goto L52;
        r62.imageReceiver.onAttachedToWindow();
        r62.imageReceiver.setParentView(this.contentLayout);
    L52:
        this.contentLayout.invalidate();
        return true;
    L46:
        if (r62.isPremiumSticker == false) goto L48;
        r62.imageReceiver.getLottieAnimation().setCurrentFrame(0, false, true);
    L48:
        r62.imageReceiver.getLottieAnimation().start();
        goto L49
    }

    public boolean showAnimationForCell(ChatMessageCell r13, int r14, boolean r15, boolean r16) {
        if (r13 != null) goto L5;
        return false;
    L5:
        if (this.drawingObjects.size() > 12) goto L29;
        MessageObject r5 = r13.getMessageObject();
        if (r13.getEffect() == null) goto L10;
    L12:
        String r2 = r5.getStickerEmoji();
        if (r2 != null) goto L16;
        r2 = r5.messageOwner.message;
    L16:
        if (r13.getEffect() != null) goto L19;
        if (r2 != null) goto L19;
        return false;
    L19:
        float r10 = r13.getPhotoImage().getImageHeight();
        float r9 = r13.getPhotoImage().getImageWidth();
        if (r13.getEffect() != null) goto L27;
        if (r10 > 0.0f) goto L24;
    L25:
        return false;
    L24:
        if (r9 <= 0.0f) goto L25;
    L27:
        return createDrawingObject(unwrapEmoji(r2), r13.getMessageObject().getId(), r13.getMessageObject().getDocument(), r5, r14, r15, r16, r9, r10, r13.getMessageObject().isOutOwner());
    L10:
        if (r13.getPhotoImage().hasNotThumb() == true) goto L12;
        return false;
    L29:
        return false;
    }

    public boolean showAnimationForWidget(StoryReactionWidgetView r19) {
        if (this.drawingObjects.size() <= 12) goto L5;
        return false;
    L5:
        ReactionsLayoutInBubble.VisibleReaction r13 = ReactionsLayoutInBubble.VisibleReaction.fromTL(r19.mediaArea.reaction);
        String r1 = r13.emojicon;
        if (r1 != null) goto L8;
        r1 = MessageObject.findAnimatedEmojiEmoticon(AnimatedEmojiDrawable.findDocument(this.currentAccount, r13.documentId));
    L8:
        float r2 = r19.getMeasuredHeight();
        float r3 = r19.getMeasuredWidth();
        View r4 = (View) r19.getParent();
        if (r3 <= (r4.getWidth() * 0.5f)) goto L11;
        float r8 = r4.getWidth() * 0.4f;
        float r9 = r8;
    L12:
        String r12 = unwrapEmoji(r1);
        int r22 = r19.hashCode();
        if (r19.getTranslationX() <= (this.contentLayout.getMeasuredWidth() / 2.0f)) goto L15;
        boolean r10 = true;
    L17:
        if (r13.emojicon == null) goto L28;
        if (createDrawingObject(r12, r22, null, null, -1, false, false, r8, r9, r10) == false) goto L28;
        if (this.drawingObjects.isEmpty() == true) goto L26;
        ArrayList<DrawingObject> r0 = this.drawingObjects;
        DrawingObject r02 = r0.get(r0.size() - 1);
        r02.isReaction = true;
        r02.lastH = r9;
        r02.lastW = r8;
        r02.lastX = r19.getTranslationX() - (r02.lastW / 2.0f);
        float r14 = r19.getTranslationY();
        float r23 = r02.lastW;
        r02.lastY = r14 - (1.5f * r23);
        boolean r15 = r02.isOut;
        float r32 = r02.lastX;
        if (r15 == false) goto L25;
        r02.lastX = r32 + ((-r23) * 1.8f);
        goto L26
    L25:
        r02.lastX = r32 + ((-r23) * 0.2f);
    L26:
        return true;
    L28:
        if (r13.documentId != 0) goto L30;
    L49:
        return false;
    L30:
        if (r19.getAnimatedEmojiDrawable() == null) goto L49;
        int r16 = 0;
        int r33 = 0;
    L33:
        if (r16 >= this.drawingObjects.size()) goto L39;
        if (this.drawingObjects.get(r16).documentId != r13.documentId) goto L37;
        r33 = r33 + 1;
    L37:
        r16 = r16 + 1;
        goto L33
    L39:
        if (r33 < 4) goto L41;
        return false;
    L41:
        DrawingObject r17 = new DrawingObject();
        r17.genericEffect = AnimatedEmojiEffect.createFrom(r19.getAnimatedEmojiDrawable(), true, true);
        if (r17.isMessageEffect == true) goto L44;
        r17.randomOffsetX = (r8 / 4.0f) * ((this.random.nextInt() % 101) / 100.0f);
        r17.randomOffsetY = (r9 / 4.0f) * ((this.random.nextInt() % 101) / 100.0f);
    L44:
        r17.messageId = r22;
        r17.document = null;
        r17.documentId = r13.documentId;
        r17.isOut = r10;
        r17.isReaction = true;
        r17.lastH = r9;
        r17.lastW = r8;
        r17.lastX = r19.getTranslationX() - (r17.lastW / 2.0f);
        float r24 = r19.getTranslationY();
        float r34 = r17.lastW;
        r17.lastY = r24 - (1.5f * r34);
        r17.lastX += (-r34) * 1.8f;
        if (this.attached == false) goto L47;
        r17.genericEffect.setView(this.contentLayout);
    L47:
        this.drawingObjects.add(r17);
        return true;
    L15:
        r10 = false;
        goto L17
    L11:
        r9 = r2;
        r8 = r3;
        goto L12
    }

    public boolean supports(String r1) {
        return this.emojiInteractionsStickersMap.containsKey(unwrapEmoji(r1));
    }
}
