package org.telegram.ui.Cells;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.text.TextUtils;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DownloadController;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.ButtonBounce;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LoadingDrawable;
import org.telegram.ui.Components.RadialProgress2;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.spoilers.SpoilerEffect2;
import org.telegram.ui.Stars.StarsIntroActivity;

/* loaded from: classes6.dex */
public class GroupMedia {
    private final AnimatedFloat animatedHidden;
    public boolean attached;
    private Bitmap blurBitmap;
    private int blurBitmapHeight;
    private int blurBitmapMessageId;
    private Paint blurBitmapPaint;
    private int blurBitmapState;
    private int blurBitmapWidth;
    private final ButtonBounce bounce;
    private Text buttonText;
    private long buttonTextPrice;
    public final ChatMessageCell cell;
    private Path clipPath;
    private Path clipPath2;
    private RectF clipRect;
    public int height;
    public boolean hidden;
    public final ArrayList<MediaHolder> holders;
    private GroupedMessages layout;
    private LoadingDrawable loadingDrawable;
    public int maxWidth;
    private int overrideWidth;
    private boolean pressButton;
    private MediaHolder pressHolder;
    private Text priceText;
    private long priceTextPrice;
    SpoilerEffect2 spoilerEffect;
    public int width;
    public int x;
    public int y;

    public static class GroupedMessages {
        private boolean applyAvatarOffset;
        public boolean hasSibling;
        float height;
        public float maxSizeHeight;
        public int maxSizeWidth;
        int maxX;
        int maxY;
        public ArrayList<TLRPC.MessageExtendedMedia> medias;
        public ArrayList<MessageObject.GroupedMessagePosition> posArray;
        public HashMap<TLRPC.MessageExtendedMedia, MessageObject.GroupedMessagePosition> positions;
        public final TransitionParams transitionParams;
        int width;

        public static class MessageGroupedLayoutAttempt {
            public float[] heights;
            public int[] lineCounts;

            public MessageGroupedLayoutAttempt(int r1, int r2, float r3, float r4) {
                this.lineCounts = new int[]{r1, r2};
                this.heights = new float[]{r3, r4};
            }

            public MessageGroupedLayoutAttempt(int r1, int r2, int r3, float r4, float r5, float r6) {
                this.lineCounts = new int[]{r1, r2, r3};
                this.heights = new float[]{r4, r5, r6};
            }

            public MessageGroupedLayoutAttempt(int r1, int r2, int r3, int r4, float r5, float r6, float r7, float r8) {
                this.lineCounts = new int[]{r1, r2, r3, r4};
                this.heights = new float[]{r5, r6, r7, r8};
            }
        }

        public static class TransitionParams {
            public float captionEnterProgress;

            public TransitionParams() {
                this.captionEnterProgress = 1.0f;
            }
        }

        /* renamed from: -$$Nest$mcalculate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m6953$$Nest$mcalculate(GroupedMessages r0, boolean r1) {
            r0.calculate(r1);
        }

        public GroupedMessages() {
            this.medias = new ArrayList();
            this.posArray = new ArrayList();
            this.positions = new HashMap();
            this.maxSizeWidth = 800;
            this.maxSizeHeight = 814.0f;
            this.transitionParams = new TransitionParams();
            this.applyAvatarOffset = true;
        }

        private void calculate(boolean r33) {
            this.applyAvatarOffset = r33;
            this.posArray.clear();
            this.positions.clear();
            this.maxX = 0;
            int r3 = this.medias.size();
            if (r3 != 0) goto L6;
            this.width = 0;
            this.height = 0.0f;
            this.maxY = 0;
            return;
        L6:
            this.maxSizeWidth = 800;
            StringBuilder r5 = new StringBuilder();
            this.hasSibling = false;
            int r7 = 0;
            boolean r9 = false;
            float r8 = 1.0f;
        L8:
            if (r7 >= r3) goto L56;
            TLRPC.MessageExtendedMedia r14 = this.medias.get(r7);
            MessageObject.GroupedMessagePosition r15 = new MessageObject.GroupedMessagePosition();
            if (r7 != (r3 - 1)) goto L12;
            boolean r4 = true;
        L13:
            r15.last = r4;
            if ((r14 instanceof TLRPC.TL_messageExtendedMediaPreview) == false) goto L16;
            TLRPC.TL_messageExtendedMediaPreview r42 = (TLRPC.TL_messageExtendedMediaPreview) r14;
            r15.photoWidth = r42.w;
            r15.photoHeight = r42.h;
            float r17 = 2.0f;
            float r18 = 1.2f;
        L41:
            if (r15.photoWidth > 0) goto L43;
        L44:
            r15.photoWidth = 50;
            r15.photoHeight = 50;
        L45:
            float r43 = r15.photoWidth / r15.photoHeight;
            r15.aspectRatio = r43;
            if (r43 <= r18) goto L49;
            r5.append("w");
        L52:
            float r44 = r15.aspectRatio;
            r8 = r8 + r44;
            if (r44 <= r17) goto L55;
            r9 = true;
        L55:
            this.positions.put(r14, r15);
            this.posArray.add(r15);
            r7 = r7 + 1;
            goto L8
        L49:
            if (r43 >= 0.8f) goto L51;
            r5.append("n");
            goto L52
        L51:
            r5.append("q");
            goto L52
        L43:
            if (r15.photoHeight > 0) goto L45;
        L16:
            r17 = 2.0f;
            int r10 = 100;
            if ((r14 instanceof TLRPC.TL_messageExtendedMedia) == false) goto L39;
            TLRPC.MessageMedia r45 = ((TLRPC.TL_messageExtendedMedia) r14).media;
            r18 = 1.2f;
            if ((r45 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L25;
            TLRPC.Photo r46 = ((TLRPC.TL_messageMediaPhoto) r45).photo;
            if (r46 == null) goto L30;
            TLRPC.PhotoSize r12 = FileLoader.getClosestPhotoSizeWithSize(r46.sizes, AndroidUtilities.getPhotoSize());
        L31:
            if (r12 != null) goto L33;
            int r47 = 100;
        L34:
            r15.photoWidth = r47;
            if (r12 == null) goto L38;
            r10 = r12.h;
        L38:
            r15.photoHeight = r10;
            goto L41
        L33:
            r47 = r12.w;
        L30:
            r12 = null;
            goto L31
        L25:
            if ((r45 instanceof TLRPC.TL_messageMediaDocument) == false) goto L30;
            TLRPC.Document r48 = ((TLRPC.TL_messageMediaDocument) r45).document;
            if (r48 == null) goto L30;
            r12 = FileLoader.getClosestPhotoSizeWithSize(r48.thumbs, AndroidUtilities.getPhotoSize());
            goto L31
        L39:
            r18 = 1.2f;
            r15.photoWidth = 100;
            r15.photoHeight = 100;
            goto L41
        L12:
            r4 = false;
            goto L13
        L56:
            int r72 = AndroidUtilities.dp(120.0f);
            float r49 = AndroidUtilities.dp(120.0f);
            Point r102 = AndroidUtilities.displaySize;
            int r410 = (int) (r49 / (Math.min(r102.x, r102.y) / this.maxSizeWidth));
            float r103 = AndroidUtilities.dp(40.0f);
            Point r11 = AndroidUtilities.displaySize;
            float r112 = Math.min(r11.x, r11.y);
            int r142 = this.maxSizeWidth;
            int r104 = (int) (r103 / (r112 / r142));
            float r113 = r142 / this.maxSizeHeight;
            float r82 = r8 / r3;
            float r143 = AndroidUtilities.dp(100.0f) / this.maxSizeHeight;
            if (r3 != 1) goto L63;
            MessageObject.GroupedMessagePosition r411 = this.posArray.get(0);
            float r52 = r411.aspectRatio;
            if (r52 < 1.0f) goto L61;
            int r6 = this.maxSizeWidth;
            float r73 = r6;
            float r53 = ((r73 / r52) / r6) * this.maxSizeHeight;
        L62:
            r411.set(0, 0, 0, 0, (int) r73, r53 / this.maxSizeHeight, 15);
            int r29 = 0;
            int r114 = r3;
        L223:
            int r83 = r29;
        L224:
            if (r83 >= r114) goto L249;
            MessageObject.GroupedMessagePosition r1 = this.posArray.get(r83);
            if (r1.maxX != this.maxX) goto L228;
        L229:
            r1.spanSize += 200;
        L230:
            int r2 = r1.flags;
            if ((r2 & 1) == 0) goto L233;
            r1.edge = true;
        L233:
            if (r33 == false) goto L248;
            if (r1.edge == false) goto L241;
            int r22 = r1.spanSize;
            if (r22 == 1000) goto L239;
            r1.spanSize = r22 + 108;
        L239:
            r1.pw += 108;
            goto L248
        L241:
            if ((r2 & 2) == 0) goto L248;
            int r23 = r1.spanSize;
            if (r23 == 1000) goto L245;
            r1.spanSize = r23 - 108;
            goto L248
        L245:
            int r24 = r1.leftSpanOffset;
            if (r24 == 0) goto L248;
            r1.leftSpanOffset = r24 + 108;
        L248:
            r83 = r83 + 1;
            goto L224
        L228:
            if ((r1.flags & 2) == 0) goto L230;
        L249:
            int r84 = r29;
        L250:
            if (r84 >= r114) goto L258;
            MessageObject.GroupedMessagePosition r13 = this.posArray.get(r84);
            if (r13.minX != 0) goto L255;
            r13.spanSize += 200;
        L255:
            if ((r13.flags & 2) == 0) goto L257;
            r13.edge = true;
        L257:
            this.maxX = Math.max(this.maxX, r13.maxX);
            this.maxY = Math.max(this.maxY, r13.maxY);
            r13.left = getLeft(r13, r13.minY, r13.maxY, r13.minX);
            r84 = r84 + 1;
            goto L250
        L258:
            int r25 = r29;
        L259:
            if (r25 >= r114) goto L261;
            MessageObject.GroupedMessagePosition r16 = this.posArray.get(r25);
            r16.top = getTop(r16, r16.minY);
            r25 = r25 + 1;
            goto L259
        L261:
            this.width = getWidth();
            this.height = getHeight();
            return;
        L61:
            float r62 = this.maxSizeHeight;
            r73 = this.maxSizeWidth * ((r52 * r62) / r62);
            r53 = r62;
            goto L62
        L63:
            int r122 = 4;
            float r20 = 1.0f;
            int r63 = 3;
            if (r9 == true) goto L69;
            if (r3 == 2) goto L71;
            if (r3 == 3) goto L71;
            if (r3 != 4) goto L69;
        L71:
            if (r3 != 2) goto L92;
            MessageObject.GroupedMessagePosition r64 = this.posArray.get(0);
            MessageObject.GroupedMessagePosition r74 = this.posArray.get(1);
            String r54 = r5.toString();
            if (r54.equals("ww") == false) goto L80;
            if (r82 <= (r113 * 1.4d)) goto L80;
            float r26 = r64.aspectRatio;
            float r32 = r74.aspectRatio;
            if ((r26 - r32) >= 0.2d) goto L80;
            int r412 = this.maxSizeWidth;
            float r27 = Math.round(Math.min(r412 / r26, Math.min(r412 / r32, this.maxSizeHeight / 2.0f))) / this.maxSizeHeight;
            r64.set(0, 0, 0, 0, this.maxSizeWidth, r27, 7);
            r74.set(0, 0, 1, 1, this.maxSizeWidth, r27, 11);
        L90:
            r114 = r3;
        L91:
            r29 = 0;
        L80:
            if (r54.equals("ww") == false) goto L82;
        L89:
            int r28 = this.maxSizeWidth / 2;
            float r34 = r28;
            float r272 = Math.round(Math.min(r34 / r64.aspectRatio, Math.min(r34 / r74.aspectRatio, this.maxSizeHeight))) / this.maxSizeHeight;
            r64.set(0, 0, 0, 0, r28, r272, 13);
            r74.set(1, 1, 0, 0, r28, r272, 14);
            this.maxX = 1;
            goto L90
        L82:
            if (r54.equals("qq") == true) goto L89;
            int r210 = this.maxSizeWidth;
            float r55 = r64.aspectRatio;
            int r211 = (int) Math.max(r210 * 0.4f, Math.round((r210 / r55) / ((1.0f / r55) + (1.0f / r74.aspectRatio))));
            int r35 = this.maxSizeWidth - r211;
            if (r35 >= r410) goto L87;
            r211 = r211 - (r410 - r35);
        L88:
            float r273 = Math.min(this.maxSizeHeight, Math.round(Math.min(r410 / r64.aspectRatio, r211 / r74.aspectRatio))) / this.maxSizeHeight;
            r64.set(0, 0, 0, 0, r410, r273, 13);
            r74.set(1, 1, 0, 0, r211, r273, 14);
            this.maxX = 1;
            goto L90
        L87:
            r410 = r35;
            goto L88
        L92:
            ArrayList<MessageObject.GroupedMessagePosition> r212 = this.posArray;
            r114 = r3;
            if (r114 != 3) goto L102;
            MessageObject.GroupedMessagePosition r213 = r212.get(0);
            MessageObject.GroupedMessagePosition r65 = this.posArray.get(1);
            MessageObject.GroupedMessagePosition r75 = this.posArray.get(2);
            if (r5.charAt(0) != 'n') goto L97;
            float r36 = this.maxSizeHeight * 0.5f;
            float r85 = r65.aspectRatio;
            float r37 = Math.min(r36, Math.round((this.maxSizeWidth * r85) / (r75.aspectRatio + r85)));
            float r86 = this.maxSizeHeight - r37;
            int r413 = (int) Math.max(r410, Math.min(this.maxSizeWidth * 0.5f, Math.round(Math.min(r75.aspectRatio * r37, r65.aspectRatio * r86))));
            int r21 = Math.round(Math.min((this.maxSizeHeight * r213.aspectRatio) + r104, this.maxSizeWidth - r413));
            r213.set(0, 0, 0, 1, r21, 1.0f, 13);
            r65.set(1, 1, 0, 0, r413, r86 / this.maxSizeHeight, 6);
            r75.set(1, 1, 1, 1, r413, r37 / this.maxSizeHeight, 10);
            int r414 = this.maxSizeWidth;
            r75.spanSize = r414;
            float r92 = this.maxSizeHeight;
            r213.siblingHeights = new float[]{r37 / r92, r86 / r92};
            r65.spanSize = r414 - r21;
            r75.leftSpanOffset = r21;
            this.hasSibling = true;
            this.maxX = 1;
            goto L91
        L97:
            float r222 = Math.round(Math.min(this.maxSizeWidth / r213.aspectRatio, this.maxSizeHeight * 0.66f)) / this.maxSizeHeight;
            r213.set(0, 1, 0, 0, this.maxSizeWidth, r222, 7);
            int r214 = this.maxSizeWidth / 2;
            float r415 = r214;
            float r38 = Math.min(this.maxSizeHeight - r222, Math.round(Math.min(r415 / r65.aspectRatio, r415 / r75.aspectRatio))) / this.maxSizeHeight;
            if (r38 >= r143) goto L100;
            float r223 = r143;
        L101:
            r65.set(0, 0, 1, 1, r214, r223, 9);
            r75.set(1, 1, 1, 1, r214, r223, 10);
            this.maxX = 1;
            goto L91
        L100:
            r223 = r38;
            goto L101
        L102:
            MessageObject.GroupedMessagePosition r215 = r212.get(0);
            MessageObject.GroupedMessagePosition r123 = this.posArray.get(1);
            MessageObject.GroupedMessagePosition r39 = this.posArray.get(2);
            MessageObject.GroupedMessagePosition r93 = this.posArray.get(3);
            if (r5.charAt(0) != 'w') goto L112;
            float r274 = Math.round(Math.min(this.maxSizeWidth / r215.aspectRatio, this.maxSizeHeight * 0.66f)) / this.maxSizeHeight;
            r215.set(0, 2, 0, 0, this.maxSizeWidth, r274, 7);
            float r216 = Math.round(this.maxSizeWidth / ((r123.aspectRatio + r39.aspectRatio) + r93.aspectRatio));
            float r416 = r410;
            int r56 = (int) Math.max(r416, Math.min(this.maxSizeWidth * 0.4f, r123.aspectRatio * r216));
            int r417 = (int) Math.max(Math.max(r416, this.maxSizeWidth * 0.33f), r93.aspectRatio * r216);
            int r66 = (this.maxSizeWidth - r56) - r417;
            if (r66 >= AndroidUtilities.dp(58.0f)) goto L107;
            int r87 = AndroidUtilities.dp(58.0f) - r66;
            r66 = AndroidUtilities.dp(58.0f);
            int r76 = r87 / 2;
            r56 = r56 - r76;
            r417 = r417 - (r87 - r76);
        L107:
            int r262 = r56;
            float r217 = Math.min(this.maxSizeHeight - r274, r216) / this.maxSizeHeight;
            if (r217 >= r143) goto L110;
            float r275 = r143;
        L111:
            r123.set(0, 0, 1, 1, r262, r275, 9);
            r39.set(1, 1, 1, 1, r66, r275, 8);
            r93.set(2, 2, 1, 1, r417, r275, 10);
            this.maxX = 2;
            goto L91
        L110:
            r275 = r217;
            goto L111
        L112:
            int r418 = Math.max(r410, Math.round(this.maxSizeHeight / (((1.0f / r123.aspectRatio) + (1.0f / r39.aspectRatio)) + (1.0f / r93.aspectRatio))));
            float r57 = r72;
            float r77 = r418;
            float r88 = Math.min(0.33f, Math.max(r57, r77 / r123.aspectRatio) / this.maxSizeHeight);
            float r58 = Math.min(0.33f, Math.max(r57, r77 / r39.aspectRatio) / this.maxSizeHeight);
            float r78 = (1.0f - r88) - r58;
            int r263 = Math.round(Math.min((this.maxSizeHeight * r215.aspectRatio) + r104, this.maxSizeWidth - r418));
            r215.set(0, 0, 0, 2, r263, (r88 + r58) + r78, 13);
            r123.set(1, 1, 0, 0, r418, r88, 6);
            r39.set(1, 1, 1, 1, r418, r58, 2);
            r39.spanSize = this.maxSizeWidth;
            r93.set(1, 1, 2, 2, r418, r78, 10);
            int r79 = this.maxSizeWidth;
            r93.spanSize = r79;
            r123.spanSize = r79 - r263;
            r39.leftSpanOffset = r263;
            r93.leftSpanOffset = r263;
            r215.siblingHeights = new float[]{r88, r58, r78};
            this.hasSibling = true;
            this.maxX = 1;
        L69:
            r114 = r3;
            int r218 = this.posArray.size();
            float[] r310 = new float[r218];
            int r59 = 0;
        L114:
            if (r59 >= r114) goto L120;
            ArrayList<MessageObject.GroupedMessagePosition> r94 = this.posArray;
            if (r82 <= 1.1f) goto L118;
            float r105 = r20;
            r310[r59] = Math.max(r105, r94.get(r59).aspectRatio);
        L119:
            r310[r59] = Math.max(0.66667f, Math.min(1.7f, r310[r59]));
            r59 = r59 + 1;
            r20 = r105;
            goto L114
        L118:
            r105 = r20;
            r310[r59] = Math.min(r105, r94.get(r59).aspectRatio);
            goto L119
        L120:
            ArrayList r510 = new ArrayList();
            int r710 = 1;
        L121:
            if (r710 >= r218) goto L128;
            int r95 = r218 - r710;
            if (r710 > 3) goto L125;
            if (r95 > 3) goto L125;
            int r172 = r122;
            r510.add(new MessageGroupedLayoutAttempt(r710, r95, multiHeight(r310, 0, r710), multiHeight(r310, r710, r218)));
        L127:
            r710 = r710 + 1;
            r122 = r172;
        L125:
            r172 = r122;
            goto L127
        L128:
            int r173 = r122;
            int r711 = 1;
        L130:
            if (r711 >= (r218 - 1)) goto L146;
            int r96 = 1;
        L132:
            int r106 = r218 - r711;
            if (r96 >= r106) goto L145;
            int r107 = r106 - r96;
            if (r711 <= 3) goto L137;
        L142:
            int r219 = r711;
            int r224 = r96;
        L144:
            r96 = r224 + 1;
            r711 = r219;
            goto L132
        L137:
            if (r82 >= 0.85f) goto L139;
            int r124 = r173;
        L140:
            if (r96 > r124) goto L142;
            if (r107 > 3) goto L142;
            int r125 = r711 + r96;
            r219 = r711;
            r224 = r96;
            r510.add(new MessageGroupedLayoutAttempt(r219, r224, r107, multiHeight(r310, 0, r711), multiHeight(r310, r711, r125), multiHeight(r310, r125, r218)));
            goto L144
        L139:
            r124 = 3;
            goto L140
        L145:
            r711 = r711 + 1;
            goto L130
        L146:
            int r712 = 1;
        L148:
            if (r712 >= (r218 - 2)) goto L165;
            int r89 = 1;
        L150:
            int r97 = r218 - r712;
            if (r89 >= r97) goto L164;
            int r108 = 1;
        L153:
            int r126 = r97 - r89;
            if (r108 >= r126) goto L163;
            int r127 = r126 - r108;
            if (r712 > r63) goto L160;
            if (r89 > r63) goto L160;
            if (r108 > r63) goto L160;
            if (r127 > r63) goto L160;
            int r132 = r712 + r89;
            int r31 = r63;
            int r67 = r132 + r108;
            int r2110 = r712;
            int r225 = r89;
            int r232 = r108;
            r510.add(new MessageGroupedLayoutAttempt(r2110, r225, r232, r127, multiHeight(r310, 0, r712), multiHeight(r310, r712, r132), multiHeight(r310, r132, r67), multiHeight(r310, r67, r218)));
        L162:
            r108 = r232 + 1;
            r712 = r2110;
            r89 = r225;
            r63 = r31;
        L160:
            r31 = r63;
            r2110 = r712;
            r225 = r89;
            r232 = r108;
            goto L162
        L163:
            r89 = r89 + 1;
            goto L150
        L164:
            r712 = r712 + 1;
            goto L148
        L165:
            int r312 = r63;
            float r220 = (this.maxSizeWidth / 3) * 4;
            MessageGroupedLayoutAttempt r68 = null;
            float r713 = 0.0f;
            int r810 = 0;
        L167:
            if (r810 >= r510.size()) goto L202;
            MessageGroupedLayoutAttempt r98 = (MessageGroupedLayoutAttempt) r510.get(r810);
            float r128 = Float.MAX_VALUE;
            int r109 = 0;
            float r133 = 0.0f;
        L169:
            float[] r152 = r98.heights;
            if (r109 >= r152.length) goto L175;
            float r19 = r152[r109];
            r133 = r133 + r19;
            if (r19 >= r128) goto L174;
            r128 = r19;
        L174:
            r109 = r109 + 1;
            goto L169
        L175:
            float r110 = Math.abs(r133 - r220);
            int[] r1010 = r98.lineCounts;
            if (r1010.length <= 1) goto L192;
            int r134 = r1010[0];
            int r153 = r1010[1];
            if (r134 > r153) goto L190;
            float r2111 = r110;
            if (r1010.length > 2) goto L182;
        L185:
            int r154 = r312;
            if (r1010.length > r154) goto L188;
        L193:
            float r111 = r2111;
        L195:
            if (r128 >= r410) goto L197;
            r111 = r111 * 1.5f;
        L197:
            if (r68 != null) goto L199;
        L200:
            r713 = r111;
            r68 = r98;
        L201:
            r810 = r810 + 1;
            r312 = r154;
            goto L167
        L199:
            if (r111 >= r713) goto L201;
        L188:
            if (r1010[2] <= r1010[r154]) goto L193;
        L191:
            r111 = r2111 * 1.2f;
            goto L195
        L182:
            if (r153 <= r1010[2]) goto L185;
        L184:
            r154 = r312;
            goto L191
        L190:
            r2111 = r110;
            goto L184
        L192:
            r2111 = r110;
            r154 = r312;
            goto L193
        L202:
            r29 = 0;
            if (r68 != null) goto L205;
            return;
        L205:
            int r115 = 0;
            int r811 = 0;
        L206:
            int[] r221 = r68.lineCounts;
            if (r811 >= r221.length) goto L223;
            int r226 = r221[r811];
            float r419 = r68.heights[r811];
            int r511 = this.maxSizeWidth;
            int r99 = r226 - 1;
            this.maxX = Math.max(this.maxX, r99);
            int r714 = 0;
            MessageObject.GroupedMessagePosition r1011 = null;
        L209:
            if (r714 >= r226) goto L222;
            int r129 = (int) (r310[r115] * r419);
            r511 = r511 - r129;
            MessageObject.GroupedMessagePosition r2112 = this.posArray.get(r115);
            if (r811 != 0) goto L213;
            int r135 = r173;
        L215:
            if (r811 != (r68.lineCounts.length - 1)) goto L217;
            r135 = r135 | 8;
        L217:
            if (r714 != 0) goto L219;
            r135 = r135 | 1;
        L219:
            if (r714 != r99) goto L221;
            r135 = r135 | 2;
            r1011 = r2112;
        L221:
            int r227 = r714;
            int r242 = r811;
            r2112.set(r227, r714, r242, r811, r129, Math.max(r143, r419 / this.maxSizeHeight), r135);
            r115 = r115 + 1;
            r714 = r227 + 1;
            goto L209
        L213:
            r135 = 0;
            goto L215
        L222:
            r1011.pw += r511;
            r1011.spanSize += r511;
            r811 = r811 + 1;
            goto L206
        }

        private float getLeft(MessageObject.GroupedMessagePosition r12, int r13, int r14, int r15) {
            int r0 = (r14 - r13) + 1;
            float[] r1 = new float[r0];
            float r2 = 0.0f;
            Arrays.fill(r1, 0.0f);
            int r3 = this.posArray.size();
            int r4 = 0;
            int r5 = 0;
        L3:
            if (r5 >= r3) goto L12;
            MessageObject.GroupedMessagePosition r6 = this.posArray.get(r5);
            if (r6 == r12) goto L11;
            if (r6.maxX >= r15) goto L11;
            int r7 = Math.min(r6.maxY, r14) - r13;
            int r8 = Math.max(r6.minY - r13, 0);
        L9:
            if (r8 > r7) goto L11;
            r1[r8] = r1[r8] + r6.pw;
            r8 = r8 + 1;
        L11:
            r5 = r5 + 1;
        L12:
            if (r4 >= r0) goto L17;
            float r11 = r1[r4];
            if (r2 >= r11) goto L16;
            r2 = r11;
        L16:
            r4 = r4 + 1;
            goto L12
        L17:
            return r2;
        }

        private float getTop(MessageObject.GroupedMessagePosition r11, int r12) {
            int r0 = this.maxX + 1;
            float[] r1 = new float[r0];
            float r2 = 0.0f;
            Arrays.fill(r1, 0.0f);
            int r3 = this.posArray.size();
            int r4 = 0;
            int r5 = 0;
        L3:
            if (r5 >= r3) goto L13;
            MessageObject.GroupedMessagePosition r6 = this.posArray.get(r5);
            if (r6 == r11) goto L12;
            if (r6.maxY >= r12) goto L12;
            int r7 = r6.minX;
        L10:
            if (r7 > r6.maxX) goto L12;
            r1[r7] = r1[r7] + r6.ph;
            r7 = r7 + 1;
        L12:
            r5 = r5 + 1;
        L13:
            if (r4 >= r0) goto L18;
            float r10 = r1[r4];
            if (r2 >= r10) goto L17;
            r2 = r10;
        L17:
            r4 = r4 + 1;
            goto L13
        L18:
            return r2;
        }

        private float multiHeight(float[] r3, int r4, int r5) {
            float r0 = 0.0f;
        L3:
            if (r4 >= r5) goto L6;
            r0 = r0 + r3[r4];
            r4 = r4 + 1;
            goto L3
        L6:
            return this.maxSizeWidth / r0;
        }

        public float getHeight() {
            float[] r1 = new float[10];
            Arrays.fill(r1, 0.0f);
            int r2 = this.posArray.size();
            int r4 = 0;
        L3:
            if (r4 >= r2) goto L9;
            MessageObject.GroupedMessagePosition r5 = this.posArray.get(r4);
            float r6 = r5.ph;
            int r7 = r5.minX;
        L6:
            if (r7 > r5.maxX) goto L8;
            r1[r7] = r1[r7] + r6;
            r7 = r7 + 1;
            goto L6
        L8:
            r4 = r4 + 1;
            goto L3
        L9:
            float r9 = r1[0];
            int r22 = 1;
        L10:
            if (r22 >= 10) goto L15;
            float r3 = r1[r22];
            if (r9 >= r3) goto L14;
            r9 = r3;
        L14:
            r22 = r22 + 1;
            goto L10
        L15:
            return r9;
        }

        public MessageObject.GroupedMessagePosition getPosition(TLRPC.MessageExtendedMedia r1) {
            if (r1 != null) goto L6;
            return null;
        L6:
            return this.positions.get(r1);
        }

        public int getWidth() {
            int[] r1 = new int[10];
            Arrays.fill(r1, 0);
            int r3 = this.posArray.size();
            int r4 = 0;
        L3:
            if (r4 >= r3) goto L9;
            MessageObject.GroupedMessagePosition r5 = this.posArray.get(r4);
            int r6 = r5.pw;
            int r7 = r5.minY;
        L6:
            if (r7 > r5.maxY) goto L8;
            r1[r7] = r1[r7] + r6;
            r7 = r7 + 1;
            goto L6
        L8:
            r4 = r4 + 1;
            goto L3
        L9:
            int r9 = r1[0];
            int r2 = 1;
        L10:
            if (r2 >= 10) goto L15;
            int r32 = r1[r2];
            if (r9 >= r32) goto L14;
            r9 = r32;
        L14:
            r2 = r2 + 1;
            goto L10
        L15:
            return r9;
        }
    }

    public static class MediaHolder implements DownloadController.FileDownloadProgressListener {
        private final int TAG;
        public boolean album;
        public String attachPath;
        public boolean attached;
        public boolean autoplay;

        /* renamed from: b, reason: collision with root package name */
        public int f504b;
        public final ChatMessageCell cell;
        public final Path clipPath;
        public final RectF clipRect;
        private int duration;
        private Text durationText;
        private int durationValue;
        public String filename;
        private int h;
        public boolean hidden;
        public int icon;
        public final ImageReceiver imageReceiver;
        public int l;
        public TLRPC.MessageExtendedMedia media;
        public int r;
        public final RadialProgress2 radialProgress;
        public final float[] radii;
        public int t;
        public boolean video;
        private int w;

        /* renamed from: -$$Nest$fgetdurationText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Text m6954$$Nest$fgetdurationText(MediaHolder r0) {
            return r0.durationText;
        }

        /* renamed from: -$$Nest$mgetDefaultIcon, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m6955$$Nest$mgetDefaultIcon(MediaHolder r0) {
            return r0.getDefaultIcon();
        }

        /* renamed from: -$$Nest$mupdateMedia, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m6956$$Nest$mupdateMedia(MediaHolder r0, TLRPC.MessageExtendedMedia r1, MessageObject r2, int r3, int r4) {
            r0.updateMedia(r1, r2, r3, r4);
        }

        public MediaHolder(ChatMessageCell r7, MessageObject r8, TLRPC.MessageExtendedMedia r9, boolean r10, int r11, int r12) {
            this.radii = new float[8];
            this.clipRect = new RectF();
            this.clipPath = new Path();
            this.icon = 4;
            this.duration = 0;
            this.durationValue = 0;
            this.cell = r7;
            this.album = r10;
            this.video = false;
            boolean r2 = true;
            if ((r9 instanceof TLRPC.TL_messageExtendedMedia) == false) goto L12;
            TLRPC.MessageMedia r102 = ((TLRPC.TL_messageExtendedMedia) r9).media;
            if ((r102 instanceof TLRPC.TL_messageMediaDocument) == true) goto L7;
        L9:
            r2 = false;
        L10:
            this.video = r2;
            this.duration = (int) Math.max(1, Math.round(MessageObject.getDocumentDuration(r102.document)));
        L19:
            if (this.video == false) goto L21;
            int r0 = this.duration;
            this.durationValue = r0;
            this.durationText = new Text(AndroidUtilities.formatLongDuration(r0), 12.0f);
        L21:
            ImageReceiver r103 = new ImageReceiver(r7);
            this.imageReceiver = r103;
            r103.setColorFilter(null);
            this.w = r11;
            this.h = r12;
            this.TAG = DownloadController.getInstance(r7.currentAccount).generateObserverTag();
            updateMedia(r9, r8, r11, r12);
            RadialProgress2 r82 = new RadialProgress2(r7, r7.getResourcesProvider());
            this.radialProgress = r82;
            int r72 = getDefaultIcon();
            this.icon = r72;
            r82.setIcon(r72, false, false);
            return;
        L7:
            if (MessageObject.isVideoDocument(r102.document) == false) goto L9;
        L12:
            if ((r9 instanceof TLRPC.TL_messageExtendedMediaPreview) == false) goto L19;
            TLRPC.TL_messageExtendedMediaPreview r104 = (TLRPC.TL_messageExtendedMediaPreview) r9;
            if ((4 & r104.flags) != 0) goto L17;
            r2 = false;
        L17:
            this.video = r2;
            this.duration = r104.video_duration;
            goto L19
        }

        private int getDefaultIcon() {
            if (this.video == true) goto L5;
            return 4;
        L5:
            if (this.autoplay == true) goto L10;
            return 0;
        L10:
            return 4;
        }

        private void updateMedia(TLRPC.MessageExtendedMedia r14, MessageObject r15, int r16, int r17) {
            if (this.w == r16) goto L5;
        L8:
            boolean r2 = true;
        L9:
            this.w = r16;
            this.h = r17;
            if (this.media != r14) goto L13;
            if (r2 == true) goto L13;
            return;
        L13:
            this.media = r14;
            this.autoplay = false;
            String r22 = r16 + "_" + r17;
            if ((r14 instanceof TLRPC.TL_messageExtendedMediaPreview) == false) goto L18;
            this.hidden = true;
            this.filename = null;
            this.imageReceiver.setImage(ImageLocation.getForObject(((TLRPC.TL_messageExtendedMediaPreview) r14).thumb, r15.messageOwner), r22.concat("_b2"), null, null, r15, 0);
            ColorMatrix r142 = new ColorMatrix();
            r142.setSaturation(1.4f);
            AndroidUtilities.adjustBrightnessColorMatrix(r142, -0.1f);
            this.imageReceiver.setColorFilter(new ColorMatrixColorFilter(r142));
            return;
        L18:
            if ((r14 instanceof TLRPC.TL_messageExtendedMedia) == false) goto L57;
            boolean r5 = r15.isRepostPreview;
            this.hidden = r5;
            if (r5 == false) goto L22;
            r22 = r22.concat("_b3");
        L22:
            this.imageReceiver.setColorFilter(null);
            TLRPC.MessageMedia r143 = ((TLRPC.TL_messageExtendedMedia) r14).media;
            this.filename = MessageObject.getFileName(r143);
            if ((r143 instanceof TLRPC.TL_messageMediaPhoto) == false) goto L27;
            TLRPC.TL_messageMediaPhoto r144 = (TLRPC.TL_messageMediaPhoto) r143;
            TLRPC.PhotoSize r3 = FileLoader.getClosestPhotoSizeWithSize(r144.photo.sizes, AndroidUtilities.getPhotoSize(), true, null, true);
            TLRPC.PhotoSize r0 = FileLoader.getClosestPhotoSizeWithSize(r144.photo.sizes, Math.min(r16, r17) / 100, false, r3, false);
            this.imageReceiver.setImage(ImageLocation.getForPhoto(r3, r144.photo), r22, ImageLocation.getForPhoto(r0, r144.photo), r22, 0, null, r15, 0);
            return;
        L27:
            if ((r143 instanceof TLRPC.TL_messageMediaDocument) == false) goto L58;
            TLRPC.TL_messageMediaDocument r145 = (TLRPC.TL_messageMediaDocument) r143;
            if (this.hidden == false) goto L31;
        L37:
            boolean r52 = false;
        L38:
            this.autoplay = r52;
            if (this.album == false) goto L41;
        L53:
            TLRPC.Document r53 = r145.document;
            if (r53 == null) goto L59;
            TLRPC.PhotoSize r32 = FileLoader.getClosestPhotoSizeWithSize(r53.thumbs, AndroidUtilities.getPhotoSize(), true, null, true);
            TLRPC.PhotoSize r02 = FileLoader.getClosestPhotoSizeWithSize(r145.document.thumbs, Math.min(r16, r17), false, r32, false);
            this.imageReceiver.setImage(ImageLocation.getForDocument(r32, r145.document), r22, ImageLocation.getForDocument(r02, r145.document), r22, 0, null, r15, 0);
            return;
        L59:
            return;
        L41:
            if (this.video == false) goto L53;
            TLRPC.Document r54 = r145.document;
            if (r54 == null) goto L53;
            TLRPC.PhotoSize r33 = FileLoader.getClosestPhotoSizeWithSize(r54.thumbs, AndroidUtilities.getPhotoSize(), true, null, true);
            TLRPC.PhotoSize r03 = FileLoader.getClosestPhotoSizeWithSize(r145.document.thumbs, Math.min(r16, r17), false, r33, false);
            ImageLocation r1 = ImageLocation.getForDocument(r145.document);
            ImageLocation r34 = ImageLocation.getForDocument(r33, r145.document);
            ImageLocation r55 = ImageLocation.getForDocument(r03, r145.document);
            ImageReceiver r04 = this.imageReceiver;
            boolean r13 = this.autoplay;
            if (r13 == true) goto L48;
            r1 = null;
        L48:
            if (r13 == false) goto L50;
            String r132 = "_g";
        L51:
            r04.setImage(r1, r22.concat(r132), r34, r22, r55, r22, null, 0, null, r15, 0);
            return;
        L50:
            r132 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L51
        L31:
            if (this.album == true) goto L37;
            if (this.video == false) goto L37;
            if (SharedConfig.isAutoplayVideo() == false) goto L37;
            r52 = true;
            goto L38
        L58:
            return;
        L57:
            return;
        L5:
            if (this.h != r17) goto L8;
            r2 = false;
            goto L9
        }

        public void attach() {
            if (this.attached == false) goto L5;
            return;
        L5:
            this.attached = true;
            this.imageReceiver.onAttachedToWindow();
        }

        public void detach() {
            if (this.attached == true) goto L5;
            return;
        L5:
            this.attached = false;
            this.imageReceiver.onDetachedFromWindow();
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public int getObserverTag() {
            return this.TAG;
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onFailedDownload(String r1, boolean r2) {
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onProgressDownload(String r3, long r4, long r6) {
            if (r6 != 0) goto L5;
            float r32 = 0.0f;
        L6:
            RadialProgress2 r42 = this.radialProgress;
            this.media.downloadProgress = r32;
            r42.setProgress(r32, true);
            if (r32 >= 1.0f) goto L9;
            int r33 = 3;
        L10:
            setIcon(r33);
            this.cell.invalidate();
            return;
        L9:
            r33 = getDefaultIcon();
            goto L10
        L5:
            r32 = Math.min(1.0f, r4 / r6);
            goto L6
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onProgressUpload(String r3, long r4, long r6, boolean r8) {
            if (r6 != 0) goto L5;
            float r32 = 0.0f;
        L6:
            RadialProgress2 r42 = this.radialProgress;
            this.media.uploadProgress = r32;
            r42.setProgress(r32, true);
            if (r32 >= 1.0f) goto L10;
            int r33 = 3;
        L13:
            setIcon(r33);
            this.cell.invalidate();
            return;
        L10:
            if (this.album == false) goto L12;
            r33 = 6;
            goto L13
        L12:
            r33 = getDefaultIcon();
            goto L13
        L5:
            r32 = Math.min(1.0f, r4 / r6);
            goto L6
        }

        @Override // org.telegram.messenger.DownloadController.FileDownloadProgressListener
        public void onSuccessDownload(String r1) {
        }

        public void setIcon(int r2) {
            if (r2 == this.icon) goto L6;
            RadialProgress2 r0 = this.radialProgress;
            this.icon = r2;
            r0.setIcon(r2, true, true);
            return;
        }

        public void setTime(int r3) {
            if (this.video == true) goto L10;
            int r32 = Math.max(0, this.duration - r3);
            if (this.durationValue == r32) goto L9;
            this.durationValue = r32;
            this.durationText = new Text(AndroidUtilities.formatLongDuration(r32), 12.0f);
            return;
        L9:
            return;
        }
    }

    public GroupMedia(ChatMessageCell r9) {
        this.holders = new ArrayList();
        this.clipPath = new Path();
        this.clipPath2 = new Path();
        this.clipRect = new RectF();
        this.cell = r9;
        this.spoilerEffect = SpoilerEffect2.getInstance(r9);
        this.animatedHidden = new AnimatedFloat(r9, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.bounce = new ButtonBounce(r9);
    }

    public boolean allVisible() {
        ArrayList<MediaHolder> r4 = this.holders;
        int r0 = r4.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L7;
        MediaHolder r3 = r4.get(r2);
        r2 = r2 + 1;
        if (r3.imageReceiver.getVisible() == true) goto L3;
        return false;
    L7:
        return true;
    }

    public void checkBlurBitmap() {
        int r1 = 0;
        if (this.cell.getMessageObject() == null) goto L5;
        int r0 = this.cell.getMessageObject().getId();
    L6:
        int r2 = this.width;
        int r3 = this.height;
        float r4 = 100.0f;
        if (r2 <= r3) goto L9;
        float r22 = 100.0f;
    L10:
        int r23 = (int) Math.max(1.0f, r22);
        int r5 = this.height;
        int r6 = this.width;
        if (r5 > r6) goto L14;
        r4 = 100.0f * (r5 / r6);
    L14:
        int r32 = (int) Math.max(1.0f, r4);
        int r42 = 0;
        int r52 = 0;
    L16:
        if (r42 >= this.holders.size()) goto L23;
        MediaHolder r62 = this.holders.get(r42);
        if (r62.imageReceiver.hasImageSet() == false) goto L22;
        if (r62.imageReceiver.getBitmap() == null) goto L22;
        r52 = r52 | (1 << r42);
    L22:
        r42 = r42 + 1;
        goto L16
    L23:
        Bitmap r43 = this.blurBitmap;
        if (r43 != null) goto L26;
    L33:
        this.blurBitmapState = r52;
        this.blurBitmapMessageId = r0;
        this.blurBitmapWidth = r23;
        this.blurBitmapHeight = r32;
        if (r43 == null) goto L36;
        r43.recycle();
    L36:
        this.blurBitmap = Bitmap.createBitmap(r23, r32, Bitmap.Config.ARGB_8888);
        Canvas r02 = new Canvas(this.blurBitmap);
        float r24 = r23;
        int r33 = this.width;
        r02.scale(r24 / r33, r24 / r33);
    L38:
        if (r1 >= this.holders.size()) goto L40;
        MediaHolder r25 = this.holders.get(r1);
        r25.imageReceiver.setImageCoords(r25.l, r25.t, r25.r - r4, r25.f504b - r6);
        r25.imageReceiver.draw(r02);
        r1 = r1 + 1;
        goto L38
    L40:
        Utilities.stackBlurBitmap(this.blurBitmap, 12);
        if (this.blurBitmapPaint != null) goto L50;
        this.blurBitmapPaint = new Paint(3);
        ColorMatrix r03 = new ColorMatrix();
        r03.setSaturation(1.5f);
        this.blurBitmapPaint.setColorFilter(new ColorMatrixColorFilter(r03));
        return;
    L50:
        return;
    L26:
        if (this.blurBitmapMessageId != r0) goto L33;
        if (this.blurBitmapState != r52) goto L33;
        if (this.blurBitmapWidth != r23) goto L33;
        if (this.blurBitmapHeight != r32) goto L33;
        return;
    L9:
        r22 = (r2 / r3) * 100.0f;
        goto L10
    L5:
        r0 = 0;
        goto L6
    }

    public void draw(Canvas r17) {
        Canvas r1 = r17;
        if (this.layout == null) goto L39;
        float r6 = this.animatedHidden.set(this.hidden);
        drawImages(r1, true);
        if (this.buttonText == null) goto L29;
        if (r6 <= 0.0f) goto L29;
        float r2 = this.bounce.getScale(0.05f);
        float r3 = AndroidUtilities.dp(28.0f) + this.buttonText.getCurrentWidth();
        float r4 = AndroidUtilities.dp(32.0f);
        RectF r5 = this.clipRect;
        int r9 = this.x;
        int r11 = this.width;
        int r12 = this.y;
        int r14 = this.height;
        r5.set(r9 + ((r11 - r3) / 2.0f), r12 + ((r14 - r4) / 2.0f), r9 + ((r11 + r3) / 2.0f), r12 + ((r14 + r4) / 2.0f));
        this.clipPath.rewind();
        float r10 = r4 / 2.0f;
        this.clipPath.addRoundRect(this.clipRect, r10, r10, Path.Direction.CW);
        r1.save();
        r1.scale(r2, r2, this.x + (this.width / 2.0f), this.y + (this.height / 2.0f));
        r1.save();
        r1.clipPath(this.clipPath);
        drawBlurred(r1, r6);
        r1.drawColor(Theme.multAlpha(1342177280, r6));
        this.buttonText.draw(r17, AndroidUtilities.dp(14.0f) + ((this.x + (this.width / 2.0f)) - (r3 / 2.0f)), (this.height / 2.0f) + this.y, -1, r6);
        r1 = r17;
        r1.restore();
        boolean r22 = isLoading();
        LoadingDrawable r32 = this.loadingDrawable;
        if (r22 == false) goto L18;
        if (r32 != null) goto L14;
        LoadingDrawable r23 = new LoadingDrawable();
        this.loadingDrawable = r23;
        r23.setCallback(this.cell);
        this.loadingDrawable.setColors(Theme.multAlpha(-1, 0.1f), Theme.multAlpha(-1, 0.3f), Theme.multAlpha(-1, 0.35f), Theme.multAlpha(-1, 0.8f));
        this.loadingDrawable.setAppearByGradient(true);
        this.loadingDrawable.strokePaint.setStrokeWidth(AndroidUtilities.dpf2(1.25f));
    L24:
        LoadingDrawable r24 = this.loadingDrawable;
        if (r24 == null) goto L27;
        r24.setBounds(this.clipRect);
        this.loadingDrawable.setRadiiDp(r10);
        this.loadingDrawable.setAlpha((int) (255.0f * r6));
        this.loadingDrawable.draw(r1);
    L27:
        r1.restore();
        goto L29
    L14:
        if (r32.isDisappeared() == false) goto L16;
    L17:
        this.loadingDrawable.reset();
        this.loadingDrawable.resetDisappear();
        goto L24
    L16:
        if (this.loadingDrawable.isDisappearing() == false) goto L24;
    L18:
        if (r32 == null) goto L24;
        if (r32.isDisappearing() == true) goto L24;
        if (this.loadingDrawable.isDisappeared() == true) goto L24;
        this.loadingDrawable.disappear();
    L29:
        if (this.priceText != null) goto L31;
        return;
    L31:
        if (r6 < 1.0f) goto L33;
        return;
    L33:
        if (allVisible() == false) goto L38;
        float r52 = (1.0f - r6) * this.cell.getTimeAlpha();
        float r25 = AndroidUtilities.dp(11.32f) + this.priceText.getCurrentWidth();
        float r33 = AndroidUtilities.dp(17.0f);
        float r42 = AndroidUtilities.dp(5.0f);
        RectF r62 = this.clipRect;
        int r7 = this.x;
        int r92 = this.width;
        int r112 = this.y;
        r62.set(((r7 + r92) - r25) - r42, r112 + r42, (r7 + r92) - r42, (r112 + r42) + r33);
        this.clipPath.rewind();
        float r34 = r33 / 2.0f;
        this.clipPath.addRoundRect(this.clipRect, r34, r34, Path.Direction.CW);
        r1.save();
        r1.clipPath(this.clipPath);
        r1.drawColor(Theme.multAlpha(TLObject.FLAG_30, r52));
        this.priceText.draw(r1, AndroidUtilities.dp(5.66f) + (((this.x + this.width) - r25) - r42), r34 + (this.y + r42), -1, r52);
        r17.restore();
        return;
    L38:
        return;
    }

    public void drawBlurRect(Canvas r2, RectF r3, float r4, float r5) {
        r2.save();
        this.clipPath.rewind();
        this.clipPath.addRoundRect(r3, r4, r4, Path.Direction.CW);
        r2.clipPath(this.clipPath);
        r2.drawColor(TLObject.FLAG_30);
        r2.restore();
    }

    public void drawBlurred(Canvas r4, float r5) {
        if (this.layout == null) goto L10;
        checkBlurBitmap();
        if (this.blurBitmap == null) goto L9;
        r4.save();
        r4.translate(this.x, this.y);
        r4.scale(this.width / this.blurBitmap.getWidth(), this.width / this.blurBitmap.getWidth());
        this.blurBitmapPaint.setAlpha((int) (r5 * 255.0f));
        r4.drawBitmap(this.blurBitmap, 0.0f, 0.0f, this.blurBitmapPaint);
        r4.restore();
        return;
    L9:
        return;
    }

    public void drawImages(Canvas r20, boolean r21) {
        Canvas r2 = r20;
        float r8 = this.animatedHidden.set(this.hidden);
        MessageObject r1 = this.cell.getMessageObject();
        this.clipPath2.rewind();
        float r3 = Float.MAX_VALUE;
        float r5 = Float.MIN_VALUE;
        float r6 = Float.MIN_VALUE;
        int r7 = 0;
        float r4 = Float.MAX_VALUE;
    L4:
        if (r7 >= this.holders.size()) goto L27;
        MediaHolder r10 = this.holders.get(r7);
        ImageReceiver r14 = r10.imageReceiver;
        int r15 = this.x;
        int r9 = r10.l;
        int r11 = this.y;
        int r12 = r10.t;
        r14.setImageCoords(r15 + r9, r11 + r12, r10.r - r9, r10.f504b - r12);
        r10.imageReceiver.draw(r2);
        if (r10.imageReceiver.getAnimation() == null) goto L9;
        r10.setTime(Math.round(r10.imageReceiver.getAnimation().currentTime / 1000.0f));
    L9:
        if (r8 <= 0.0f) goto L11;
        r3 = Math.min(this.x + r10.l, r3);
        r4 = Math.min(this.y + r10.t, r4);
        r5 = Math.max(this.x + r10.r, r5);
        r6 = Math.max(this.y + r10.f504b, r6);
        RectF r92 = AndroidUtilities.rectTmp;
        float r122 = r10.l + this.x;
        int r13 = this.y;
        r92.set(r122, r10.t + r13, r11 + r10.r, r13 + r10.f504b);
        this.clipPath2.addRoundRect(r92, r10.radii, Path.Direction.CW);
    L11:
        r10.radialProgress.setColorKeys(Theme.key_chat_mediaLoaderPhoto, Theme.key_chat_mediaLoaderPhotoSelected, Theme.key_chat_mediaLoaderPhotoIcon, Theme.key_chat_mediaLoaderPhotoIconSelected);
        float r17 = r3;
        r10.radialProgress.setProgressRect(r10.imageReceiver.getImageX() + ((r10.imageReceiver.getImageWidth() / 2.0f) - r10.radialProgress.getRadius()), r10.imageReceiver.getImageY() + ((r10.imageReceiver.getImageHeight() / 2.0f) - r10.radialProgress.getRadius()), r10.imageReceiver.getImageX() + ((r10.imageReceiver.getImageWidth() / 2.0f) + r10.radialProgress.getRadius()), r10.imageReceiver.getImageY() + ((r10.imageReceiver.getImageHeight() / 2.0f) + r10.radialProgress.getRadius()));
        boolean r32 = r1.isSending();
        int r93 = r1.currentAccount;
        if (r32 == false) goto L22;
        SendMessagesHelper r33 = SendMessagesHelper.getInstance(r93);
        long[] r94 = ImageLoader.getInstance().getFileProgressSizes(r10.attachPath);
        boolean r34 = r33.isSendingPaidMessage(r1.getId(), r7);
        if (r94 != null) goto L25;
        if (r34 == false) goto L25;
        r10.radialProgress.setProgress(1.0f, true);
        if (r10.album == false) goto L19;
        int r35 = 6;
    L20:
        r10.setIcon(r35);
        goto L25
    L19:
        r35 = MediaHolder.m6955$$Nest$mgetDefaultIcon(r10);
    L25:
        r2.saveLayerAlpha(r10.radialProgress.getProgressRect(), (int) ((1.0f - r8) * 255.0f), 31);
        r10.radialProgress.draw(r2);
        r2.restore();
        r7 = r7 + 1;
        r3 = r17;
        goto L4
    L22:
        if (FileLoader.getInstance(r93).isLoadingFile(r10.filename) == false) goto L24;
        r10.setIcon(3);
        goto L25
    L24:
        r10.setIcon(MediaHolder.m6955$$Nest$mgetDefaultIcon(r10));
        goto L25
    L27:
        if (r8 <= 0.0f) goto L30;
        if (r21 == false) goto L30;
        r2.save();
        r2.clipPath(this.clipPath2);
        r2.translate(r3, r4);
        int r95 = (int) (r5 - r3);
        int r102 = (int) (r6 - r4);
        r20.saveLayerAlpha(0.0f, 0.0f, r95, r102, (int) (r8 * 255.0f), 31);
        SpoilerEffect2 r16 = this.spoilerEffect;
        ChatMessageCell r36 = this.cell;
        r2 = r20;
        r16.draw(r2, r36, r95, r102, 1.0f, r36.drawingToBitmap);
        r2.restore();
        r2.restore();
        this.cell.invalidate();
    L30:
        int r96 = 0;
    L32:
        if (r96 >= this.holders.size()) goto L44;
        MediaHolder r18 = this.holders.get(r96);
        if (MediaHolder.m6954$$Nest$fgetdurationText(r18) == null) goto L43;
        float r37 = AndroidUtilities.dp(11.4f) + MediaHolder.m6954$$Nest$fgetdurationText(r18).getCurrentWidth();
        float r42 = AndroidUtilities.dp(17.0f);
        float r52 = AndroidUtilities.dp(5.0f);
        RectF r62 = this.clipRect;
        int r72 = this.x;
        int r103 = r18.l;
        int r123 = this.y;
        int r132 = r18.t;
        r62.set((r72 + r103) + r52, (r123 + r132) + r52, ((r72 + r103) + r52) + r37, ((r123 + r132) + r52) + r42);
        if (this.priceText != null) goto L38;
    L42:
        this.clipPath.rewind();
        float r43 = r42 / 2.0f;
        this.clipPath.addRoundRect(this.clipRect, r43, r43, Path.Direction.CW);
        r2.save();
        r2.clipPath(this.clipPath);
        drawBlurred(r2, r8);
        r2.drawColor(Theme.multAlpha(TLObject.FLAG_30, 1.0f));
        MediaHolder.m6954$$Nest$fgetdurationText(r18).draw(r2, ((this.x + r18.l) + r52) + AndroidUtilities.dp(5.66f), r43 + ((this.y + r18.t) + r52), -1, 1.0f);
        r20.restore();
        goto L43
    L38:
        if (this.clipRect.right <= (((this.x + this.width) - (AndroidUtilities.dp(11.32f) + this.priceText.getCurrentWidth())) - r52)) goto L42;
        if (this.clipRect.top > (this.y + r52)) goto L42;
    L43:
        r96 = r96 + 1;
        r2 = r20;
        goto L32
    }

    public MediaHolder getHolderAt(float r3, float r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.holders.size()) goto L10;
        if (this.holders.get(r0).imageReceiver.isInsideImage(r3, r4) == true) goto L8;
        r0 = r0 + 1;
        goto L4
    L8:
        return this.holders.get(r0);
    L10:
        return null;
    }

    public ImageReceiver getPhotoImage(int r4) {
        GroupedMessages r0 = this.layout;
        if (r0 != null) goto L5;
        return null;
    L5:
        if (r4 >= 0) goto L7;
    L17:
        return null;
    L7:
        if (r4 >= r0.medias.size()) goto L17;
        TLRPC.MessageExtendedMedia r42 = this.layout.medias.get(r4);
        int r02 = 0;
    L11:
        if (r02 >= this.holders.size()) goto L17;
        if (this.holders.get(r02).media == r42) goto L15;
        r02 = r02 + 1;
        goto L11
    L15:
        return this.holders.get(r02).imageReceiver;
    }

    public boolean isLoading() {
        if (this.cell.getDelegate() != null) goto L5;
        return false;
    L5:
        if (this.cell.getDelegate().isProgressLoading(this.cell, 5) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public void onAttachedToWindow() {
        if (this.attached == true) goto L12;
        this.attached = true;
        SpoilerEffect2 r0 = this.spoilerEffect;
        if (r0 == null) goto L8;
        r0.attach(this.cell);
    L8:
        int r02 = 0;
    L10:
        if (r02 >= this.holders.size()) goto L14;
        this.holders.get(r02).attach();
        r02 = r02 + 1;
        goto L10
    L14:
        return;
    }

    public void onDetachedFromWindow() {
        if (this.attached == false) goto L11;
        int r0 = 0;
        this.attached = false;
        SpoilerEffect2 r1 = this.spoilerEffect;
        if (r1 == null) goto L9;
        r1.detach(this.cell);
    L9:
        if (r0 >= this.holders.size()) goto L14;
        this.holders.get(r0).detach();
        r0 = r0 + 1;
        goto L9
    L14:
        return;
    }

    public boolean onTouchEvent(MotionEvent r13) {
        float r0 = r13.getX();
        float r1 = r13.getY();
        if (r13.getAction() != 0) goto L14;
        MediaHolder r132 = getHolderAt(r0, r1);
        this.pressHolder = r132;
        if (r132 != null) goto L7;
    L11:
        boolean r133 = false;
    L12:
        this.pressButton = r133;
    L43:
        ButtonBounce r134 = this.bounce;
        if (this.pressHolder == null) goto L46;
        boolean r02 = true;
    L47:
        r134.setPressed(r02);
        if (this.pressHolder == null) goto L50;
        return true;
    L50:
        return false;
    L46:
        r02 = false;
        goto L47
    L7:
        if (r132.radialProgress.getIcon() == 4) goto L11;
        if (this.pressHolder.radialProgress.getProgressRect().contains(r0, r1) == false) goto L11;
        r133 = true;
        goto L12
    L14:
        if (r13.getAction() != 1) goto L16;
    L17:
        MediaHolder r2 = getHolderAt(r0, r1);
        if (r2 != null) goto L20;
    L24:
        boolean r03 = false;
    L25:
        MediaHolder r12 = this.pressHolder;
        if (r12 == null) goto L42;
        if (r12 != r2) goto L42;
        if (this.cell.getDelegate() == null) goto L42;
        if (r13.getAction() != 1) goto L42;
        MessageObject r14 = this.cell.getMessageObject();
        if (this.pressButton == false) goto L41;
        if (r03 == false) goto L41;
        if (r2.radialProgress.getIcon() != 3) goto L41;
        if (r14 == null) goto L41;
        if (r14.isSending() == false) goto L42;
        SendMessagesHelper.getInstance(r14.currentAccount).cancelSendingMessage(r14);
    L41:
        ChatMessageCell.ChatMessageCellDelegate r6 = this.cell.getDelegate();
        ChatMessageCell r7 = this.cell;
        MediaHolder r04 = this.pressHolder;
        r6.didPressGroupImage(r7, r04.imageReceiver, r04.media, r13.getX(), r13.getY());
    L42:
        this.pressButton = false;
        this.pressHolder = null;
        goto L43
    L20:
        if (r2.radialProgress.getIcon() == 4) goto L24;
        if (r2.radialProgress.getProgressRect().contains(r0, r1) == false) goto L24;
        r03 = true;
        goto L25
    L16:
        if (r13.getAction() != 3) goto L43;
        goto L17
    }

    public void setMessageObject(MessageObject r15, boolean r16, boolean r17) {
        if (r15 == null) goto L96;
        TLRPC.Message r0 = r15.messageOwner;
        if (r0 == null) goto L97;
        TLRPC.MessageMedia r02 = r0.media;
        if ((r02 instanceof TLRPC.TL_messageMediaPaidMedia) == false) goto L98;
        TLRPC.TL_messageMediaPaidMedia r7 = (TLRPC.TL_messageMediaPaidMedia) r02;
        if (this.layout != null) goto L14;
        this.layout = new GroupedMessages();
    L14:
        this.layout.medias.clear();
        this.layout.medias.addAll(r7.extended_media);
        GroupedMessages.m6953$$Nest$mcalculate(this.layout, !this.cell.isProfileAvatarEmbedded());
        int r03 = this.overrideWidth;
        if (r03 <= 0) goto L18;
        this.maxWidth = r03;
    L29:
        if (this.overrideWidth <= 0) goto L34;
        int r04 = this.layout.width;
        if (r04 <= 0) goto L34;
        float r05 = 1000.0f / r04;
    L33:
        float r11 = r05;
        int r12 = 0;
    L36:
        int r06 = r7.extended_media.size();
        ArrayList<TLRPC.MessageExtendedMedia> r1 = r7.extended_media;
        String r13 = null;
        if (r12 >= r06) goto L67;
        TLRPC.MessageExtendedMedia r3 = r1.get(r12);
        int r5 = (int) (((r0.pw / 1000.0f) * r11) * this.maxWidth);
        int r6 = (int) (this.layout.getPosition(r3).ph * this.layout.maxSizeHeight);
        if (r12 < this.holders.size()) goto L41;
        MediaHolder r07 = null;
    L42:
        if (r07 != null) goto L65;
        ChatMessageCell r14 = this.cell;
        if (r7.extended_media.size() == 1) goto L46;
        boolean r4 = true;
    L47:
        MediaHolder r08 = new MediaHolder(r14, r15, r3, r4, r5, r6);
        String r18 = r3.attachPath;
        if (r18 == null) goto L51;
        r08.attachPath = r18;
    L57:
        if (TextUtils.isEmpty(r08.attachPath) == true) goto L62;
        DownloadController.getInstance(this.cell.currentAccount).addLoadingFileObserver(r08.attachPath, r15, r08);
        if (r15.isSending() == false) goto L62;
        r08.radialProgress.setProgress(r3.uploadProgress, false);
    L62:
        if (this.cell.isCellAttachedToWindow() == false) goto L64;
        r08.attach();
    L64:
        this.holders.add(r08);
    L66:
        r12 = r12 + 1;
        goto L36
    L51:
        if (r7.extended_media.size() != 1) goto L57;
        TLRPC.Message r19 = r15.messageOwner;
        if (r19 == null) goto L55;
        r13 = r19.attachPath;
    L55:
        r08.attachPath = r13;
        goto L57
    L46:
        r4 = false;
        goto L47
    L65:
        MediaHolder.m6956$$Nest$mupdateMedia(r07, r3, r15, r5, r6);
        goto L66
    L41:
        r07 = this.holders.get(r12);
        goto L42
    L67:
        int r09 = r1.size();
    L69:
        if (r09 >= this.holders.size()) goto L77;
        if (r09 < this.holders.size()) goto L73;
        MediaHolder r110 = null;
    L74:
        if (r110 == null) goto L76;
        r110.detach();
        this.holders.remove(r09);
        r09 = r09 - 1;
    L76:
        r09 = r09 + 1;
        goto L69
    L73:
        r110 = this.holders.get(r09);
        goto L74
    L77:
        updateHolders(r15);
        if (this.hidden == false) goto L83;
        long r111 = r7.stars_amount;
        this.buttonTextPrice = r111;
        Text r010 = new Text(StarsIntroActivity.replaceStarsWithPlain(LocaleController.formatPluralStringComma("UnlockPaidContent", (int) r111), 0.7f), 14.0f, AndroidUtilities.bold());
        this.buttonText = r010;
        if (r010.getCurrentWidth() <= (this.width - AndroidUtilities.dp(30.0f))) goto L83;
        long r52 = r7.stars_amount;
        this.buttonTextPrice = r52;
        this.buttonText = new Text(StarsIntroActivity.replaceStarsWithPlain(LocaleController.formatPluralStringComma("UnlockPaidContentShort", (int) r52), 0.7f), 14.0f, AndroidUtilities.bold());
    L83:
        if (this.priceText != null) goto L85;
    L88:
        long r112 = r7.stars_amount;
        this.priceTextPrice = r112;
        this.priceText = new Text(StarsIntroActivity.replaceStars(LocaleController.formatPluralStringComma("PaidMediaPrice", (int) r112), 0.9f), 12.0f, AndroidUtilities.bold());
        return;
    L85:
        if (this.priceTextPrice != r7.stars_amount) goto L88;
        return;
    L34:
        r05 = 1.0f;
        goto L33
    L18:
        if (AndroidUtilities.isTablet() == false) goto L20;
        this.maxWidth = AndroidUtilities.getMinTabletSide() - AndroidUtilities.dp(122.0f);
    L26:
        if (this.cell.needDrawAvatarOutside() == false) goto L29;
        this.maxWidth -= AndroidUtilities.dp(52.0f);
        goto L29
    L20:
        int r011 = Math.min(this.cell.getParentWidth(), AndroidUtilities.displaySize.y);
        if (this.cell.checkNeedDrawShareButton(r15) == false) goto L23;
        int r113 = 10;
    L24:
        this.maxWidth = r011 - AndroidUtilities.dp(r113 + 64);
        goto L26
    L23:
        r113 = 0;
        goto L24
    L98:
        return;
    L97:
        return;
    }

    public void setOverrideWidth(int r1) {
        this.overrideWidth = r1;
    }

    public void updateHolders(MessageObject r23) {
        ChatMessageCell r2 = this.cell;
        boolean r5 = false;
        if (r2.namesOffset <= 0) goto L5;
    L10:
        boolean r22 = true;
    L12:
        if (this.cell.captionAbove == false) goto L14;
    L15:
        ChatMessageCell r3 = this.cell;
        if (r3.reactionsLayoutInBubble.isEmpty == true) goto L18;
    L21:
        boolean r32 = true;
    L22:
        int r6 = this.overrideWidth;
        float r8 = 1000.0f;
        if (r6 <= 0) goto L26;
        float r9 = 1000.0f / this.layout.width;
        this.maxWidth = r6;
    L37:
        GroupedMessages r62 = this.layout;
        this.width = (int) (((r62.width / 1000.0f) * r9) * this.maxWidth);
        this.height = (int) (r62.height * r62.maxSizeHeight);
        this.hidden = false;
        int r63 = AndroidUtilities.dp(1.0f);
        int r7 = AndroidUtilities.dp(4.0f);
        char r11 = 2;
        if (SharedConfig.bubbleRadius <= 2) goto L40;
        int r12 = 2;
    L41:
        int r10 = AndroidUtilities.dp(r10 - r12);
        int r122 = Math.min(AndroidUtilities.dp(3.0f), r10);
        int r13 = 0;
    L43:
        if (r13 >= this.holders.size()) goto L114;
        MediaHolder r14 = this.holders.get(r13);
        MessageObject.GroupedMessagePosition r4 = this.layout.getPosition(r14.media);
        if (r4 != null) goto L47;
        boolean r20 = r22;
        boolean r17 = r5;
        float r18 = r8;
        char r19 = r11;
    L112:
        r13 = r13 + 1;
        r5 = r17;
        r8 = r18;
        r11 = r19;
        r22 = r20;
        goto L43
    L47:
        float r15 = (r4.left / r8) * r9;
        r17 = r5;
        int r52 = this.maxWidth;
        r18 = r8;
        int r82 = (int) (r15 * r52);
        float r152 = r4.top;
        r19 = r11;
        float r112 = this.layout.maxSizeHeight;
        int r153 = (int) (r152 * r112);
        r20 = r22;
        int r24 = (int) (((r4.pw / r18) * r9) * r52);
        int r53 = (int) (r4.ph * r112);
        int r113 = r4.flags;
        if ((r113 & 1) != 0) goto L51;
        r82 = r82 + r63;
        r24 = r24 - r63;
    L51:
        if ((r113 & 4) != 0) goto L54;
        r153 = r153 + r63;
        r53 = r53 - r63;
    L54:
        if ((r113 & 2) != 0) goto L57;
        r24 = r24 - r63;
    L57:
        if ((r113 & 8) != 0) goto L59;
        r53 = r53 - r63;
    L59:
        r14.l = r82;
        r14.t = r153;
        r14.r = r82 + r24;
        r14.f504b = r153 + r53;
        r14.imageReceiver.setImageCoords(r82, r153, r24, r53);
        int r25 = r4.flags;
        if ((r25 & 4) != 0) goto L62;
    L65:
        int r42 = r7;
    L67:
        if ((r25 & 4) != 0) goto L69;
    L72:
        int r54 = r7;
    L74:
        if ((r25 & 8) != 0) goto L76;
    L79:
        int r83 = r7;
    L81:
        if ((r25 & 8) != 0) goto L83;
    L86:
        int r26 = r7;
    L87:
        if (r32 == false) goto L89;
    L92:
        if (r20 == false) goto L94;
    L99:
        r14.imageReceiver.setRoundRadius(r42, r54, r26, r83);
        float[] r114 = r14.radii;
        float r43 = r42;
        r114[1] = r43;
        r114[r17 ? 1 : 0] = r43;
        float r44 = r54;
        r114[3] = r44;
        r114[r19] = r44;
        float r27 = r26;
        r114[5] = r27;
        r114[4] = r27;
        float r28 = r83;
        r114[7] = r28;
        r114[6] = r28;
        if (r23 == null) goto L105;
        if (r23.isSending() == false) goto L105;
        r14.setIcon(3);
    L105:
        if (this.hidden == false) goto L107;
    L110:
        boolean r29 = true;
    L111:
        this.hidden = r29;
        goto L112
    L107:
        if (r14.hidden == true) goto L110;
        r29 = r17 ? 1 : 0;
        goto L111
    L94:
        if (this.cell.pinnedTop == false) goto L99;
        if (r23.isOutOwner() == false) goto L98;
        r54 = r122;
        goto L99
    L98:
        r42 = r122;
        goto L99
    L89:
        if (r23.isOutOwner() == false) goto L91;
        r26 = r7;
        goto L92
    L91:
        r83 = r7;
        goto L92
    L83:
        if ((r25 & 2) == 0) goto L86;
        if (r32 == true) goto L86;
        r26 = r10;
        goto L87
    L76:
        if ((r25 & 1) == 0) goto L79;
        if (r32 == true) goto L79;
        r83 = r10;
        goto L81
    L69:
        if ((r25 & 2) == 0) goto L72;
        if (r20 == true) goto L72;
        r54 = r10;
        goto L74
    L62:
        if ((r25 & 1) == 0) goto L65;
        if (r20 == true) goto L65;
        r42 = r10;
        goto L67
    L114:
        if (this.hidden == false) goto L126;
        if (r23 != null) goto L117;
        TLRPC.TL_messageMediaPaidMedia r1 = null;
    L118:
        if (r1 == null) goto L127;
        long r33 = r1.stars_amount;
        this.buttonTextPrice = r33;
        Text r210 = new Text(StarsIntroActivity.replaceStarsWithPlain(LocaleController.formatPluralStringComma("UnlockPaidContent", (int) r33), 0.7f), 14.0f, AndroidUtilities.bold());
        this.buttonText = r210;
        if (r210.getCurrentWidth() <= (this.width - AndroidUtilities.dp(30.0f))) goto L128;
        long r72 = r1.stars_amount;
        this.buttonTextPrice = r72;
        this.buttonText = new Text(StarsIntroActivity.replaceStarsWithPlain(LocaleController.formatPluralStringComma("UnlockPaidContentShort", (int) r72), 0.7f), 14.0f, AndroidUtilities.bold());
        return;
    L128:
        return;
    L127:
        return;
    L117:
        r1 = (TLRPC.TL_messageMediaPaidMedia) r23.messageOwner.media;
        goto L118
    L126:
        return;
    L40:
        r12 = 0;
        goto L41
    L26:
        if (AndroidUtilities.isTablet() == false) goto L28;
        this.maxWidth = AndroidUtilities.getMinTabletSide() - AndroidUtilities.dp(122.0f);
    L34:
        if (this.cell.needDrawAvatarOutside() == false) goto L36;
        this.maxWidth -= AndroidUtilities.dp(52.0f);
    L36:
        r9 = 1.0f;
        goto L37
    L28:
        int r64 = Math.min(this.cell.getParentWidth(), AndroidUtilities.displaySize.y);
        if (this.cell.checkNeedDrawShareButton(r23) == false) goto L31;
        int r92 = 10;
    L32:
        this.maxWidth = r64 - AndroidUtilities.dp(r92 + 64);
        goto L34
    L31:
        r92 = 0;
        goto L32
    L18:
        if (r3.hasCommentLayout() == true) goto L21;
        r32 = false;
        goto L22
    L14:
        if (TextUtils.isEmpty(r23.caption) == false) goto L21;
    L5:
        if (r2.captionAbove == true) goto L7;
    L9:
        r22 = false;
        goto L12
    L7:
        if (TextUtils.isEmpty(r23.caption) == true) goto L9;
        goto L9
    }
}
