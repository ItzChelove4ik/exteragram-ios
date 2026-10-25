package org.telegram.ui;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.wearable.Wearable;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.crypto.Cipher;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.telegram.messenger.AiTonesController$$ExternalSyntheticLambda0;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.TextHelper;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes6.dex */
public abstract class WearAuthSheet {
    private static final BigInteger DH_G = null;
    private static final BigInteger DH_P = null;
    private static AuthSession currentSession;
    private static BottomSheet currentSheet;

    public static class AuthSession {
        List<String> emojis;
        byte[] noncePhone;
        final String originNodeId;
        final byte[] peerPub;
        BigInteger privateExponent;
        final byte[] sessionId;
        byte[] sharedKey;

        public AuthSession(byte[] r1, byte[] r2, String r3) {
            this.sessionId = r1;
            this.peerPub = r2;
            this.originNodeId = r3;
        }

        public byte[] acceptAndBuildAnswer() {
            SecureRandom r0 = new SecureRandom();
            BigInteger r1 = new BigInteger(2048, r0);
            BigInteger r2 = WearAuthSheet.m21281$$Nest$sfgetDH_G().modPow(r1, WearAuthSheet.m21282$$Nest$sfgetDH_P());
            if (WearAuthSheet.m21286$$Nest$smisValidPub(r2) == false) goto L10;
            byte[] r22 = WearAuthSheet.m21284$$Nest$smencode256(r2);
            BigInteger r3 = new BigInteger(1, this.peerPub);
            if (WearAuthSheet.m21286$$Nest$smisValidPub(r3) == false) goto L8;
            byte[] r32 = WearAuthSheet.m21284$$Nest$smencode256(r3.modPow(r1, WearAuthSheet.m21282$$Nest$sfgetDH_P()));
            byte[] r5 = new byte[16];
            r0.nextBytes(r5);
            byte[] r02 = WearAuthSheet.m21287$$Nest$smsha256(new byte[][]{r32, this.sessionId, r5});
            byte[] r33 = WearAuthSheet.m21287$$Nest$smsha256(new byte[][]{r32, this.peerPub});
            this.privateExponent = r1;
            this.sharedKey = r02;
            this.noncePhone = r5;
            this.emojis = WearAuthSheet.m21283$$Nest$smemojify(r33, 4);
            FileLog.d("wear-auth: built answer; session " + WearAuthSheet.m21285$$Nest$smhex(this.sessionId) + " emojis=" + this.emojis);
            byte[] r03 = new byte[288];
            System.arraycopy(this.sessionId, 0, r03, 0, 16);
            System.arraycopy(r5, 0, r03, 16, 16);
            System.arraycopy(r22, 0, r03, 32, 256);
            return r03;
        L8:
            f$$ExternalSyntheticBUOutline1.m("peer pubkey out of range");
            return null;
        L10:
            Segment$$ExternalSyntheticBUOutline1.m("our pubkey invalid (extremely unlikely)");
            return null;
        }
    }

    public static /* synthetic */ void $r8$lambda$B5hgXdvDp8J0vgwWMb33wSz5fCQ(ButtonWithCounterView r4, int[] r5, View r6) {
        if (r4.isLoading() == true) goto L17;
        AuthSession r0 = currentSession;
        if (r0 != null) goto L8;
        return;
    L8:
        r4.setLoading(true);
        Context r62 = r6.getContext().getApplicationContext();
        byte[] r1 = r0.acceptAndBuildAnswer();     // Catch: Exception -> L12
        Wearable.getMessageClient(r62).sendMessage(r0.originNodeId, "/tg-wear-auth/answer", r1).addOnSuccessListener(new WearAuthSheet$$ExternalSyntheticLambda5(r0, r4, r5)).addOnFailureListener(new WearAuthSheet$$ExternalSyntheticLambda6(r4));
        return;
    L12:
        e = move-exception;
        FileLog.e(e);
        r4.setLoading(false);
        return;
    }

    public static /* synthetic */ void $r8$lambda$OcJG_vbyI8TW993hMKLFWF8qEFU(AuthSession r1, ButtonWithCounterView r2, Integer r3) {
        FileLog.d("wear-auth: /token delivered to " + r1.originNodeId);
        r2.setLoading(false);
        currentSession = null;
        cancel();
    }

    public static /* synthetic */ void $r8$lambda$OlMawrInXqaCFdhiTyWaQHdJHeI(int[] r1, int r2, AvatarDrawable r3, TLRPC.User r4, BackupImageView r5) {
        r1[0] = r2;
        r3.setInfo(r4);
        r5.setForUserOrChat(r4, r3);
    }

    /* renamed from: $r8$lambda$Xwb-qUKYwf0Syqvg0qWMLNf6-Tw, reason: not valid java name */
    public static /* synthetic */ void m21279$r8$lambda$XwbqUKYwf0Syqvg0qWMLNf6Tw(ButtonWithCounterView r2, Exception r3) {
        FileLog.e("wear-auth: /answer send failed: " + r3.getMessage());
        r2.setLoading(false);
    }

    public static /* synthetic */ int $r8$lambda$buTLqhwFrVAmtPQch46uwC7EpKk(Integer r2, Integer r3) {
        long r0 = UserConfig.getInstance(r2.intValue()).loginTime;
        long r22 = UserConfig.getInstance(r3.intValue()).loginTime;
        if (r0 <= r22) goto L6;
        return 1;
    L6:
        if (r0 >= r22) goto L9;
        return -1;
    L9:
        return 0;
    }

    public static /* synthetic */ void $r8$lambda$c_qbh0Jp_OD1nqWPUmrx5EQJ8yg(BottomSheet r8, FrameLayout r9, ArrayList r10, int[] r11, AvatarDrawable r12, BackupImageView r13, View r14) {
        ItemOptions r82 = ItemOptions.makeOptions(r8.container, r8.getResourcesProvider(), r9);
        int r92 = r10.size();
        int r0 = 0;
    L3:
        if (r0 >= r92) goto L12;
        Object r1 = r10.get(r0);
        r0 = r0 + 1;
        int r4 = ((Integer) r1).intValue();
        TLRPC.User r6 = UserConfig.getInstance(r4).getCurrentUser();
        if (r6 == null) goto L3;
        if (r11[0] != r4) goto L10;
        boolean r15 = true;
    L11:
        r82.addAccount(r4, r15, new WearAuthSheet$$ExternalSyntheticLambda4(r11, r4, r12, r6, r13));
        goto L3
    L10:
        r15 = false;
        goto L11
    L12:
        r82.setDrawScrim(false).setOnTopOfScrim().setDimAlpha(0).setGravity(3).translate(-AndroidUtilities.dp(8.0f), -AndroidUtilities.dp(8.0f)).show();
    }

    public static /* synthetic */ void $r8$lambda$ezHylZBEz8URlQfWkZle4PuaTos(ButtonWithCounterView r2, Exception r3) {
        FileLog.e("wear-auth: /token send failed: " + r3.getMessage());
        r2.setLoading(false);
    }

    public static /* synthetic */ void $r8$lambda$qyXj0JZQmSIB8o9BZQgtRMdhUnk(ButtonWithCounterView r3, BottomSheet r4, int r5, View r6, AuthSession r7, TLRPC.UrlAuthResult r8, TLRPC.TL_error r9) {
        r3.setLoading(false);
        if ((r8 instanceof TLRPC.TL_urlAuthResultAccepted) == false) goto L15;
        String r82 = Uri.parse("?" + Uri.parse(((TLRPC.TL_urlAuthResultAccepted) r8).url).getFragment()).getQueryParameter("tgWebAuthToken");
        if (r82 != null) goto L8;
        BulletinFactory.of(r4.topBulletinContainer, r4.getResourcesProvider()).showForError("NO_TOKEN");
        return;
    L8:
        int r92 = ConnectionsManager.getInstance(r5).getCurrentDatacenterId();
        boolean r0 = ConnectionsManager.getInstance(r5).isTestBackend();
        FileLog.d("wear-auth: sending /token account=" + r5 + " dcId=" + r92 + " isTest=" + r0);
        Context r52 = r6.getContext().getApplicationContext();
        Wearable.getMessageClient(r52).sendMessage(r7.originNodeId, "/tg-wear-auth/token", buildEncryptedTokenWire(r7, r82, r92, r0)).addOnSuccessListener(new WearAuthSheet$$ExternalSyntheticLambda9(r7, r3)).addOnFailureListener(new WearAuthSheet$$ExternalSyntheticLambda10(r3));
        r4.dismiss();
        return;
    L12:
        e = move-exception;
        FileLog.e(e);
        BulletinFactory.of(r4.topBulletinContainer, r4.getResourcesProvider()).showForError(e.getMessage());
        return;
    L15:
        if (r9 == null) goto L18;
        BulletinFactory.of(r4.topBulletinContainer, r4.getResourcesProvider()).showForError(r9);
        return;
    L18:
        BulletinFactory.of(r4.topBulletinContainer, r4.getResourcesProvider()).showForError("NO_TOKEN");
    }

    public static /* synthetic */ void $r8$lambda$sT1lps5_P_spk0l9a6EloZJgcVg(AuthSession r1, ButtonWithCounterView r2, int[] r3, Integer r4) {
        FileLog.d("wear-auth: /answer delivered to " + r1.originNodeId);
        r2.setLoading(false);
        showEmojis(r3[0], r1.emojis);
    }

    /* renamed from: $r8$lambda$xdTM_-WaemPMfWHPpaSnoOgZsnM, reason: not valid java name */
    public static /* synthetic */ void m21280$r8$lambda$xdTM_WaemPMfWHPpaSnoOgZsnM(ButtonWithCounterView r9, int r10, BottomSheet r11, View r12) {
        if (r9.isLoading() == false) goto L5;
        return;
    L5:
        AuthSession r6 = currentSession;
        if (r6 != null) goto L8;
    L12:
        FileLog.d("wear-auth: login pressed with no session/key");
        return;
    L8:
        if (r6.sharedKey == null) goto L12;
        r9.setLoading(true);
        TLRPC.TL_messages_requestUrlAuth r0 = new TLRPC.TL_messages_requestUrlAuth();
        r0.flags |= 4;
        r0.url = "https://web.telegram.org/";
        ConnectionsManager.getInstance(r10).sendRequestTyped(r0, new AiTonesController$$ExternalSyntheticLambda0(), new WearAuthSheet$$ExternalSyntheticLambda8(r9, r11, r10, r12, r6));
    }

    /* renamed from: -$$Nest$sfgetDH_G, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BigInteger m21281$$Nest$sfgetDH_G() {
        return DH_G;
    }

    /* renamed from: -$$Nest$sfgetDH_P, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BigInteger m21282$$Nest$sfgetDH_P() {
        return DH_P;
    }

    /* renamed from: -$$Nest$smemojify, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m21283$$Nest$smemojify(byte[] r0, int r1) {
        return emojify(r0, r1);
    }

    /* renamed from: -$$Nest$smencode256, reason: not valid java name */
    public static /* bridge */ /* synthetic */ byte[] m21284$$Nest$smencode256(BigInteger r0) {
        return encode256(r0);
    }

    /* renamed from: -$$Nest$smhex, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m21285$$Nest$smhex(byte[] r0) {
        return hex(r0);
    }

    /* renamed from: -$$Nest$smisValidPub, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21286$$Nest$smisValidPub(BigInteger r0) {
        return isValidPub(r0);
    }

    /* renamed from: -$$Nest$smsha256, reason: not valid java name */
    public static /* bridge */ /* synthetic */ byte[] m21287$$Nest$smsha256(byte[][] r0) {
        return sha256(r0);
    }

    static {
        DH_P = new BigInteger("FFFFFFFFFFFFFFFFC90FDAA22168C234C4C6628B80DC1CD129024E088A67CC74020BBEA63B139B22514A08798E3404DDEF9519B3CD3A431B302B0A6DF25F14374FE1356D6D51C245E485B576625E7EC6F44C42E9A637ED6B0BFF5CB6F406B7EDEE386BFB5A899FA5AE9F24117C4B1FE649286651ECE45B3DC2007CB8A163BF0598DA48361C55D39A69163FA8FD24CF5F83655D23DCA3AD961C62F356208552BB9ED529077096966D670C354E4ABC9804F1746C08CA18217C32905E462E36CE3BE39E772C180E86039B2783A2EC07A28FB5C55DF06F4C52C9DE2BCBF6955817183995497CEA956AE515D2261898FA051015728E5A8AACAA68FFFFFFFFFFFFFFFF", 16);
        DH_G = BigInteger.valueOf(2);
    }

    private static byte[] aeadEncrypt(byte[] r3, byte[] r4, byte[] r5, byte[] r6) {
        Cipher r0 = Cipher.getInstance("AES/GCM/NoPadding");
        r0.init(1, new SecretKeySpec(r3, "AES"), new GCMParameterSpec(128, r4));
        if (r5 == null) goto L8;
        if (r5.length <= 0) goto L8;
        r0.updateAAD(r5);
    L8:
        return r0.doFinal(r6);
    }

    private static byte[] buildEncryptedTokenWire(AuthSession r4, String r5, int r6, boolean r7) {
        byte[] r52 = r5.getBytes(StandardCharsets.UTF_8);
        ByteBuffer r0 = ByteBuffer.allocate(r52.length + 9);
        r0.order(ByteOrder.BIG_ENDIAN);
        r0.putInt(r52.length);
        r0.put(r52);
        r0.putInt(r6);
        r0.put(r7 ? 1 : 0);
        byte[] r53 = r0.array();
        byte[] r72 = new byte[12];
        new SecureRandom().nextBytes(r72);
        byte[] r54 = aeadEncrypt(r4.sharedKey, r72, null, r53);
        byte[] r02 = new byte[r54.length + 28];
        System.arraycopy(r4.sessionId, 0, r02, 0, 16);
        System.arraycopy(r72, 0, r02, 16, 12);
        System.arraycopy(r54, 0, r02, 28, r54.length);
        return r02;
    }

    private static long bytesToLong(byte[] r7, int r8) {
        return (r7[r8 + 7] & 255) | ((((((((r7[r8] & 127) << 56) | ((r7[r8 + 1] & 255) << 48)) | ((r7[r8 + 2] & 255) << 40)) | ((r7[r8 + 3] & 255) << 32)) | ((r7[r8 + 4] & 255) << 24)) | ((r7[r8 + 5] & 255) << 16)) | ((r7[r8 + 6] & 255) << 8));
    }

    public static void cancel() {
        BottomSheet r0 = currentSheet;
        if (r0 == null) goto L6;
        r0.dismiss();
        currentSheet = null;
        return;
    }

    private static List<String> emojify(byte[] r7, int r8) {
        String[] r0 = getEmojis();
        ArrayList r1 = new ArrayList(r8);
        int r2 = 0;
    L3:
        if (r2 >= r8) goto L5;
        r1.add(r0[(int) (bytesToLong(r7, r2 * 8) % r0.length)]);
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }

    private static byte[] encode256(BigInteger r4) {
        byte[] r42 = r4.toByteArray();
        if (r42.length != 256) goto L6;
        return r42;
    L6:
        if (r42.length != 257) goto L12;
        if (r42[0] != 0) goto L12;
        return Arrays.copyOfRange(r42, 1, r42.length);
    L12:
        if (r42.length >= 256) goto L15;
        byte[] r0 = new byte[256];
        System.arraycopy(r42, 0, r0, 256 - r42.length, r42.length);
        return r0;
    L15:
        WearAuthSheet$$ExternalSyntheticBUOutline0.m("unexpected DH value size ", r42.length);
        return null;
    }

    private static String[] getEmojis() {
        return new String[]{"👋", "👍", "👎", "👌", "👊", "🤟", "🫵", "👏", "🤝", "✍", "💪", "👀", "👅", "🥶", "🤡", "💀", "👽", "😈", "😎", "🤠", "🤩", "😍", "🤯", "🦄", "🐶", "🐷", "🐔", "🐥", "🦊", "🐙", "🐸", "🐳", "🦉", "🦆", "🐢", "🦖", "🐵", "🐝", "🦁", "🐧", "🦋", "🐬", "🦀", "🐌", "🦠", "🐠", "🌵", "💐", "💐", "🎄", "🍄", "🍔", "🍕", "☕", "🍩", "🍪", "🎂", "🍫", "🍭", "🍎", "🥥", "🍒", "🌶", "🥒", "🥦", "🍇", "🍋", "🍓", "🍌", "🍍", "🍆", "🌽", "🍺", "🍷", "🍾", "🍦", "🍰", "🍞", "🍖", "🌭", "🧊", "🍳", "⭐", "☁", "🚀", "🎈", "💎", "💡", "🔑", "❄", "🔎", "👠", "👕", "👗", "👖", "👙", "👜", "👓", "🎀", "💄", "💍", "♠", "❤", "♦", "♣", "🌈", "🌊", "🎃", "👻", "🎁", "🔮", "🎥", "💿", "💻", "📡", "🔉", "⏳", "🔒", "🚗", "🔱", "🔗", "🎲", "🎮", "⚽", "🎳", "🏁", "🏆", "🎸", "💣", "🚽", "🎹", "🎤", "🎨", "🔫", "💊", "💰", "📦", "📅", "📚", "❗", "❓", "💯", "💦", "💤", "🌍", "🏝", "🚂", "🛢", "🛹", "🚢", "✈", "🛎", "🧳", "🌖", "🌞", "🔥", "🏓", "🎰", "🧸", "🪩", "🎭", "👑", "🎩", "🧢", "🔈", "🔋", "🕯", "✏", "💼", "📌", "✂", "🗑", "🛡", "⚙", "🧲", "\u1fa8f", "⚖", "🧪", "🚪", "🫧", "🛒", "🪑", "🗿", "🏁", "🏴\u200d☠", "📊", "🥁", "🎧", "🎵", "🧩", "⛳", "🥇", "🥈", "🥈", "🌪", "⛺", "🧭", "\u1fac6", "🧠", "💋"};
    }

    private static String hex(byte[] r5) {
        StringBuilder r0 = new StringBuilder(r5.length * 2);
        int r1 = r5.length;
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L6;
        r0.append(String.format("%02x", new Object[]{Byte.valueOf(r5[r2])}));
        r2 = r2 + 1;
        goto L3
    L6:
        return r0.toString();
    }

    private static boolean isValidPub(BigInteger r2) {
        BigInteger r0 = BigInteger.ONE;
        if (r2.compareTo(r0) > 0) goto L5;
        return false;
    L5:
        if (r2.compareTo(DH_P.subtract(r0)) >= 0) goto L10;
        return true;
    L10:
        return false;
    }

    public static void onCancelReceived() {
        FileLog.d("wear-auth: cancel received; dropping session and dismissing sheet");
        currentSession = null;
        cancel();
    }

    public static void onOfferReceived(byte[] r3, String r4) {
        if (r3 != null) goto L4;
    L14:
        StringBuilder r42 = new StringBuilder("wear-auth: malformed offer (");
        if (r3 != null) goto L17;
        int r32 = -1;
    L18:
        r42.append(r32);
        r42.append(")");
        FileLog.d(r42.toString());
        return;
    L17:
        r32 = r3.length;
        goto L18
    L4:
        if (r3.length != 272) goto L14;
        byte[] r0 = Arrays.copyOfRange(r3, 0, 16);
        byte[] r33 = Arrays.copyOfRange(r3, 16, r3.length);
        AuthSession r1 = currentSession;
        if (r1 != null) goto L9;
    L12:
        FileLog.d("wear-auth: new session " + hex(r0) + " from " + r4);
        currentSession = new AuthSession(r0, r33, r4);
        show();
        return;
    L9:
        if (Arrays.equals(r1.sessionId, r0) == false) goto L12;
        FileLog.d("wear-auth: duplicate offer (same sessionId) — ignoring");
    }

    private static byte[] sha256(byte[]... r4) {
        MessageDigest r0 = MessageDigest.getInstance("SHA-256");     // Catch: Exception -> L7
        int r1 = r4.length;     // Catch: Exception -> L7
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L5;
        r0.update(r4[r2]);     // Catch: Exception -> L7
        r2 = r2 + 1;     // Catch: Exception -> L7
        goto L3
    L5:
        return r0.digest();
    L7:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
        return null;
    }

    public static void show() {
        Context r0 = LaunchActivity.instance;
        if (r0 != null) goto L5;
        r0 = ApplicationLoader.applicationContext;
    L5:
        if (r0 == null) goto L32;
        BaseFragment r1 = LaunchActivity.getSafeLastFragment();
        if (r1 == null) goto L10;
        Theme.ResourcesProvider r12 = r1.getResourceProvider();
    L11:
        cancel();
        BottomSheet.Builder r2 = new BottomSheet.Builder(r0, false, r12);
        FrameLayout r4 = new FrameLayout(r0);
        r2.setCustomView(r4);
        int r5 = UserConfig.selectedAccount;
        ArrayList r9 = new ArrayList();
        r9.clear();
        int r6 = 0;
    L13:
        if (r6 >= 16) goto L21;
        if (UserConfig.getInstance(r6).isClientActivated() == false) goto L20;
        if (ConnectionsManager.getInstance(r6).isTestBackend() == true) goto L19;
        r5 = r6;
    L19:
        r9.add(Integer.valueOf(r6));
    L20:
        r6 = r6 + 1;
        goto L13
    L21:
        Collections.sort(r9, new WearAuthSheet$$ExternalSyntheticLambda1());
        if (r9.isEmpty() == false) goto L24;
        return;
    L24:
        FrameLayout r13 = new FrameLayout(r0);
        FrameLayout r8 = new FrameLayout(r0);
        r8.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(14.0f), Theme.getColor(Theme.key_dialogBackgroundGray, r12)));
        BackupImageView r122 = new BackupImageView(r0);
        r122.setRoundRadius(AndroidUtilities.dp(14.0f));
        r122.getImageReceiver().setCrossfadeWithOldImage(true);
        AvatarDrawable r11 = new AvatarDrawable();
        int[] r7 = {UserConfig.selectedAccount};
        TLRPC.User r14 = UserConfig.getInstance(r7[0]).getCurrentUser();
        r11.setInfo(r14);
        r122.setForUserOrChat(r14, r11);
        r8.addView(r122, LayoutHelper.createFrame(28, 28, 115));
        ImageView r142 = new ImageView(r0);
        r142.setScaleType(ImageView.ScaleType.CENTER);
        r142.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_dialogTextGray3, r12), PorterDuff.Mode.SRC_IN));
        r142.setImageResource(R.drawable.arrows_select);
        r8.addView(r142, LayoutHelper.createFrame(18, 18.0f, 21, 0.0f, 0.0f, 4.0f, 0.0f));
        r13.addView(r8, LayoutHelper.createFrame(52, 28, 17));
        r13.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(8.0f), 0);
        r4.addView(r13, LayoutHelper.createLinear(-2, -2, 0.0f, 51, 6, 4, 6, 0));
        ScaleStateListAnimator.apply(r13);
        if (r9.size() > 1) goto L27;
        r13.setVisibility(8);
    L27:
        LinearLayout r3 = new LinearLayout(r0);
        r3.setOrientation(1);
        r4.addView(r3, LayoutHelper.createFrame(-1, -1, 119));
        BackupImageView r42 = new BackupImageView(r0);
        r3.addView(r42, LayoutHelper.createLinear(130, 130, 49, 32.0f, 32.0f, 32.0f, 9.66f));
        MediaDataController.getInstance(r5).setPlaceholderImage(r42, "Utya3D", "😎", "130_130");
        int r43 = Theme.key_dialogTextBlack;
        TextView r52 = TextHelper.makeTextView(r0, 20.0f, r43, true, r12);
        r52.setGravity(17);
        r52.setText(LocaleController.getString(R.string.WearAuthTitle));
        r3.addView(r52, LayoutHelper.createLinear(-1, -2, 49, 32.0f, 24.0f, 32.0f, 9.66f));
        TextView r44 = TextHelper.makeTextView(r0, 14.0f, r43, false);
        r44.setGravity(17);
        r44.setText(LocaleController.getString(R.string.WearAuthText));
        r3.addView(r44, LayoutHelper.createLinear(-1, -2, 49, 32, 0, 32, 24));
        ButtonWithCounterView r02 = new ButtonWithCounterView(r0, r12).setRound();
        r02.setText(LocaleController.getString(R.string.Next));
        r3.addView(r02, LayoutHelper.createLinear(-1, 48, 7, 12, 12, 12, 8));
        BottomSheet r22 = r2.create();
        int r32 = Theme.key_windowBackgroundGray;
        r22.setBackgroundColor(Theme.getColor(r32, r12));
        r22.fixNavigationBar(Theme.getColor(r32, r12));
        r13.setOnClickListener(new WearAuthSheet$$ExternalSyntheticLambda2(r22, r8, r9, r7, r11, r122));
        r02.setOnClickListener(new WearAuthSheet$$ExternalSyntheticLambda3(r02, r7));
        currentSheet = r22;
        r22.show();
        return;
    L10:
        r12 = null;
        goto L11
    }

    public static void showEmojis(int r14, List<String> r15) {
        if (r15 != null) goto L4;
        return;
    L4:
        if (r15.isEmpty() == true) goto L28;
        Context r0 = LaunchActivity.instance;
        if (r0 != null) goto L9;
        r0 = ApplicationLoader.applicationContext;
    L9:
        if (r0 == null) goto L29;
        BaseFragment r1 = LaunchActivity.getSafeLastFragment();
        if (r1 == null) goto L14;
        Theme.ResourcesProvider r12 = r1.getResourceProvider();
    L15:
        cancel();
        int r3 = 0;
        BottomSheet.Builder r2 = new BottomSheet.Builder(r0, false, r12);
        FrameLayout r4 = new FrameLayout(r0);
        r2.setCustomView(r4);
        LinearLayout r5 = new LinearLayout(r0);
        r5.setOrientation(1);
        r4.addView(r5, LayoutHelper.createFrame(-1, -1, 119));
        TextView r42 = TextHelper.makeTextView(r0, 20.0f, Theme.key_dialogTextBlack, true, r12);
        r42.setGravity(17);
        r42.setText(LocaleController.getString(R.string.WearAuthEmojis));
        r5.addView(r42, LayoutHelper.createLinear(-1, -2, 49, 32.0f, 24.0f, 32.0f, 9.66f));
        LinearLayout r43 = new LinearLayout(r0);
        r43.setOrientation(0);
    L17:
        if (r3 >= r15.size()) goto L24;
        Drawable r6 = Emoji.getEmojiBigDrawable(r15.get(r3));
        ImageView r7 = new ImageView(r0);
        r7.setImageDrawable(r6);
        NotificationCenter.listenEmojiLoading(r7);
        r7.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), Theme.multAlpha(Theme.getColor(Theme.key_featuredStickers_addButton, r12), 0.15f)));
        if (r3 != 0) goto L22;
        float r62 = 0.0f;
    L23:
        r43.addView(r7, LayoutHelper.createLinear(80, 80, r62, 0.0f, 0.0f, 0.0f));
        r3 = r3 + 1;
        goto L17
    L22:
        r62 = 5.0f;
        goto L23
    L24:
        r5.addView(r43, LayoutHelper.createLinear(-2, -2, 49, 32, 12, 32, 12));
        ButtonWithCounterView r152 = new ButtonWithCounterView(r0, r12).setRound();
        r152.setText(LocaleController.getString(R.string.WearAuthEmojisLogIn));
        r5.addView(r152, LayoutHelper.createLinear(-1, 48, 7, 12, 12, 12, 8));
        BottomSheet r02 = r2.create();
        int r22 = Theme.key_windowBackgroundGray;
        r02.setBackgroundColor(Theme.getColor(r22, r12));
        r02.fixNavigationBar(Theme.getColor(r22, r12));
        currentSheet = r02;
        r02.show();
        r152.setOnClickListener(new WearAuthSheet$$ExternalSyntheticLambda7(r152, r14, r02));
        return;
    L14:
        r12 = null;
        goto L15
    L29:
        return;
    }
}
