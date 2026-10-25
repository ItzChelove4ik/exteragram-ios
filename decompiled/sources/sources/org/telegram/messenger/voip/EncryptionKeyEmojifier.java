package org.telegram.messenger.voip;

import c.f$$ExternalSyntheticBUOutline1;
import kotlin.UByte;
import kotlin.jvm.internal.ByteCompanionObject;

/* loaded from: classes6.dex */
public class EncryptionKeyEmojifier {
    private static final String[] emojis = null;
    private static final int[] offsets = null;

    static {
        emojis = new String[]{"😉", "😍", "😛", "😭", "😱", "😡", "😎", "😴", "😵", "😈", "😬", "😇", "😏", "👮", "👷", "💂", "👶", "👨", "👩", "👴", "👵", "😻", "😽", "🙀", "👺", "🙈", "🙉", "🙊", "💀", "👽", "💩", "🔥", "💥", "💤", "👂", "👀", "👃", "👅", "👄", "👍", "👎", "👌", "👊", "✌", "✋", "👐", "👆", "👇", "👉", "👈", "🙏", "👏", "💪", "🚶", "🏃", "💃", "👫", "👨\u200d👩\u200d👦", "👬", "👭", "💅", "🎩", "👑", "👒", "👟", "👞", "👠", "👕", "👗", "👖", "👙", "👜", "👓", "🎀", "💄", "💛", "💙", "💜", "💚", "💍", "💎", "🐶", "🐺", "🐱", "🐭", "🐹", "🐰", "🐸", "🐯", "🐨", "🐻", "🐷", "🐮", "🐗", "🐴", "🐑", "🐘", "🐼", "🐧", "🐥", "🐔", "🐍", "🐢", "🐛", "🐝", "🐜", "🐞", "🐌", "🐙", "🐚", "🐟", "🐬", "🐋", "🐐", "🐊", "🐫", "🍀", "🌹", "🌻", "🍁", "🌾", "🍄", "🌵", "🌴", "🌳", "🌞", "🌚", "🌙", "🌎", "🌋", "⚡", "☔", "❄", "⛄", "🌀", "🌈", "🌊", "🎓", "🎆", "🎃", "👻", "🎅", "🎄", "🎁", "🎈", "🔮", "🎥", "📷", "💿", "💻", "☎", "📡", "📺", "📻", "🔉", "🔔", "⏳", "⏰", "⌚", "🔒", "🔑", "🔎", "💡", "🔦", "🔌", "🔋", "🚿", "🚽", "🔧", "🔨", "🚪", "🚬", "💣", "🔫", "🔪", "💊", "💉", "💰", "💵", "💳", "✉", "📫", "📦", "📅", "📁", "✂", "📌", "📎", "✒", "✏", "📐", "📚", "🔬", "🔭", "🎨", "🎬", "🎤", "🎧", "🎵", "🎹", "🎻", "🎺", "🎸", "👾", "🎮", "🃏", "🎲", "🎯", "🏈", "🏀", "⚽", "⚾", "🎾", "🎱", "🏉", "🎳", "🏁", "🏇", "🏆", "🏊", "🏄", "☕", "🍼", "🍺", "🍷", "🍴", "🍕", "🍔", "🍟", "🍗", "🍱", "🍚", "🍜", "🍡", "🍳", "🍞", "🍩", "🍦", "🎂", "🍰", "🍪", "🍫", "🍭", "🍯", "🍎", "🍏", "🍊", "🍋", "🍒", "🍇", "🍉", "🍓", "🍑", "🍌", "🍐", "🍍", "🍆", "🍅", "🌽", "🏡", "🏥", "🏦", "⛪", "🏰", "⛺", "🏭", "🗻", "🗽", "🎠", "🎡", "⛲", "🎢", "🚢", "🚤", "⚓", "🚀", "✈", "🚁", "🚂", "🚋", "🚎", "🚌", "🚙", "🚗", "🚕", "🚛", "🚨", "🚔", "🚒", "🚑", "🚲", "🚠", "🚜", "🚦", "⚠", "🚧", "⛽", "🎰", "🗿", "🎪", "🎭", "🇯🇵", "🇰🇷", "🇩🇪", "🇨🇳", "🇺🇸", "🇫🇷", "🇪🇸", "🇮🇹", "🇷🇺", "🇬🇧", "1⃣", "2⃣", "3⃣", "4⃣", "5⃣", "6⃣", "7⃣", "8⃣", "9⃣", "0⃣", "🔟", "❗", "❓", "♥", "♦", "💯", "🔗", "🔱", "🔴", "🔵", "🔶", "🔷"};
        offsets = new int[]{0, 4, 8, 12, 16};
    }

    public EncryptionKeyEmojifier() {
    }

    private static int bytesToInt(byte[] r2, int r3) {
        int r0 = (((r2[r3] & ByteCompanionObject.MAX_VALUE) << 24) | ((r2[r3 + 1] & UByte.MAX_VALUE) << 16)) | ((r2[r3 + 2] & UByte.MAX_VALUE) << 8);
        return (r2[r3 + 3] & UByte.MAX_VALUE) | r0;
    }

    private static long bytesToLong(byte[] r7, int r8) {
        return (r7[r8 + 7] & 255) | ((((((((r7[r8] & 127) << 56) | ((r7[r8 + 1] & 255) << 48)) | ((r7[r8 + 2] & 255) << 40)) | ((r7[r8 + 3] & 255) << 32)) | ((r7[r8 + 4] & 255) << 24)) | ((r7[r8 + 5] & 255) << 16)) | ((r7[r8 + 6] & 255) << 8));
    }

    public static String[] emojify(byte[] r6) {
        if (r6.length != 32) goto L8;
        String[] r1 = new String[5];
        int r2 = 0;
    L5:
        if (r2 >= 5) goto L7;
        String[] r3 = emojis;
        r1[r2] = r3[bytesToInt(r6, offsets[r2]) % r3.length];
        r2 = r2 + 1;
        goto L5
    L7:
        return r1;
    L8:
        f$$ExternalSyntheticBUOutline1.m("sha256 needs to be exactly 32 bytes");
        return null;
    }

    public static String[] emojifyForCall(byte[] r8) {
        String[] r1 = new String[4];
        int r2 = 0;
    L3:
        if (r2 >= 4) goto L5;
        r1[r2] = emojis[(int) (bytesToLong(r8, r2 * 8) % r3.length)];
        r2 = r2 + 1;
        goto L3
    L5:
        return r1;
    }
}
