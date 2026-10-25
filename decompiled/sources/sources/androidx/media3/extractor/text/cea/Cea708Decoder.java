package androidx.media3.extractor.text.cea;

import android.graphics.Color;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.BackgroundColorSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.CodecSpecificDataUtil;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableBitArray;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleInputBuffer;
import androidx.media3.extractor.text.SubtitleOutputBuffer;
import com.sun.jna.IntegerType$$ExternalSyntheticBUOutline0;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import kotlin.text.Typography;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* loaded from: classes4.dex */
public final class Cea708Decoder extends CeaDecoder {
    private final ParsableBitArray captionChannelPacketData;
    private final ParsableByteArray ccData;
    private final CueInfoBuilder[] cueInfoBuilders;
    private List<Cue> cues;
    private CueInfoBuilder currentCueInfoBuilder;
    private DtvCcPacket currentDtvCcPacket;
    private int currentWindow;
    private final boolean isWideAspectRatio;
    private List<Cue> lastCues;
    private int previousSequenceNumber;
    private final int selectedServiceNumber;

    public static final class Cea708CueInfo {
        private static final Comparator<Cea708CueInfo> LEAST_IMPORTANT_FIRST = null;
        public final Cue cue;
        public final int priority;

        public static /* synthetic */ int $r8$lambda$nOy38SZgKNOaUqbGOPEt_NYgM1Y(Cea708CueInfo r0, Cea708CueInfo r1) {
            return Integer.compare(r1.priority, r0.priority);
        }

        static {
            LEAST_IMPORTANT_FIRST = new Cea708Decoder$Cea708CueInfo$$ExternalSyntheticLambda0();
        }

        public Cea708CueInfo(CharSequence r2, Layout.Alignment r3, float r4, int r5, int r6, float r7, int r8, float r9, boolean r10, int r11, int r12) {
            Cue.Builder r22 = new Cue.Builder().setText(r2).setTextAlignment(r3).setLine(r4, r5).setLineAnchor(r6).setPosition(r7).setPositionAnchor(r8).setSize(r9);
            if (r10 == false) goto L5;
            r22.setWindowColor(r11);
        L5:
            this.cue = r22.build();
            this.priority = r12;
        }

        public static /* synthetic */ Comparator access$000() {
            return LEAST_IMPORTANT_FIRST;
        }
    }

    public static final class CueInfoBuilder {
        public static final int COLOR_SOLID_BLACK = 0;
        public static final int COLOR_SOLID_WHITE = 0;
        public static final int COLOR_TRANSPARENT = 0;
        private static final int[] PEN_STYLE_BACKGROUND = null;
        private static final int[] PEN_STYLE_EDGE_TYPE = null;
        private static final int[] PEN_STYLE_FONT_STYLE = null;
        private static final int[] WINDOW_STYLE_FILL = null;
        private static final int[] WINDOW_STYLE_JUSTIFICATION = null;
        private static final int[] WINDOW_STYLE_PRINT_DIRECTION = null;
        private static final int[] WINDOW_STYLE_SCROLL_DIRECTION = null;
        private static final boolean[] WINDOW_STYLE_WORD_WRAP = null;
        private int anchorId;
        private int backgroundColor;
        private int backgroundColorStartPosition;
        private final SpannableStringBuilder captionStringBuilder;
        private boolean defined;
        private int foregroundColor;
        private int foregroundColorStartPosition;
        private int horizontalAnchor;
        private int italicsStartPosition;
        private int justification;
        private int penStyleId;
        private int priority;
        private boolean relativePositioning;
        private final List<SpannableString> rolledUpCaptions;
        private int row;
        private int rowCount;
        private int underlineStartPosition;
        private int verticalAnchor;
        private boolean visible;
        private int windowFillColor;
        private int windowStyleId;

        static {
            COLOR_SOLID_WHITE = getArgbColorFromCeaColor(2, 2, 2, 0);
            int r2 = getArgbColorFromCeaColor(0, 0, 0, 0);
            COLOR_SOLID_BLACK = r2;
            int r3 = getArgbColorFromCeaColor(0, 0, 0, 3);
            COLOR_TRANSPARENT = r3;
            WINDOW_STYLE_JUSTIFICATION = new int[]{0, 0, 0, 0, 0, 2, 0};
            WINDOW_STYLE_PRINT_DIRECTION = new int[]{0, 0, 0, 0, 0, 0, 2};
            WINDOW_STYLE_SCROLL_DIRECTION = new int[]{3, 3, 3, 3, 3, 3, 1};
            WINDOW_STYLE_WORD_WRAP = new boolean[]{false, false, false, true, true, true, false};
            WINDOW_STYLE_FILL = new int[]{r2, r3, r2, r2, r3, r2, r2};
            PEN_STYLE_FONT_STYLE = new int[]{0, 1, 2, 3, 4, 3, 4};
            PEN_STYLE_EDGE_TYPE = new int[]{0, 0, 0, 0, 0, 3, 3};
            PEN_STYLE_BACKGROUND = new int[]{r2, r2, r2, r2, r2, r3, r3};
        }

        public CueInfoBuilder() {
            this.rolledUpCaptions = new ArrayList();
            this.captionStringBuilder = new SpannableStringBuilder();
            reset();
        }

        public static int getArgbColorFromCeaColor(int r1, int r2, int r3) {
            return getArgbColorFromCeaColor(r1, r2, r3, 0);
        }

        public static int getArgbColorFromCeaColor(int r4, int r5, int r6, int r7) {
            int r0 = 0;
            Assertions.checkIndex(r4, 0, 4);
            Assertions.checkIndex(r5, 0, 4);
            Assertions.checkIndex(r6, 0, 4);
            Assertions.checkIndex(r7, 0, 4);
            if (r7 == 0) goto L9;
            if (r7 == 1) goto L9;
            if (r7 != 2) goto L8;
            int r72 = 127;
        L12:
            if (r4 <= 1) goto L14;
            int r42 = 255;
        L15:
            if (r5 <= 1) goto L17;
            int r52 = 255;
        L18:
            if (r6 <= 1) goto L21;
            r0 = 255;
        L21:
            return Color.argb(r72, r42, r52, r0);
        L17:
            r52 = 0;
            goto L18
        L14:
            r42 = 0;
            goto L15
        L8:
            if (r7 != 3) goto L9;
            r72 = 0;
        L9:
            r72 = 255;
            goto L12
        }

        public void append(char r3) {
            if (r3 != '\n') goto L24;
            this.rolledUpCaptions.add(buildSpannableString());
            this.captionStringBuilder.clear();
            if (this.italicsStartPosition == (-1)) goto L8;
            this.italicsStartPosition = 0;
        L8:
            if (this.underlineStartPosition == (-1)) goto L11;
            this.underlineStartPosition = 0;
        L11:
            if (this.foregroundColorStartPosition == (-1)) goto L14;
            this.foregroundColorStartPosition = 0;
        L14:
            if (this.backgroundColorStartPosition == (-1)) goto L17;
            this.backgroundColorStartPosition = 0;
        L17:
            if (this.rolledUpCaptions.size() >= this.rowCount) goto L23;
            if (this.rolledUpCaptions.size() >= 15) goto L23;
            this.row = this.rolledUpCaptions.size();
            return;
        L23:
            this.rolledUpCaptions.remove(0);
            goto L17
        L24:
            this.captionStringBuilder.append(r3);
        }

        public void backspace() {
            int r0 = this.captionStringBuilder.length();
            if (r0 <= 0) goto L6;
            this.captionStringBuilder.delete(r0 - 1, r0);
            return;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r7v10 */
        /* JADX WARN: Type inference failed for: r7v7 */
        /* JADX WARN: Type inference failed for: r7v8 */
        /* JADX WARN: Type inference failed for: r7v9, types: [int] */
        public Cea708CueInfo build() {
            if (isEmpty() == false) goto L6;
            return null;
        L6:
            SpannableStringBuilder r1 = new SpannableStringBuilder();
            int r2 = 0;
        L8:
            if (r2 >= this.rolledUpCaptions.size()) goto L10;
            r1.append(this.rolledUpCaptions.get(r2));
            r1.append('\n');
            r2 = r2 + 1;
            goto L8
        L10:
            r1.append(buildSpannableString());
            int r22 = this.justification;
            int r5 = 1;
            if (r22 == 0) goto L20;
            if (r22 == 1) goto L19;
            if (r22 == 2) goto L18;
            if (r22 == 3) goto L20;
            IntegerType$$ExternalSyntheticBUOutline0.m("Unexpected justification value: ", this.justification);
            return null;
        L18:
            Layout.Alignment r23 = Layout.Alignment.ALIGN_CENTER;
        L21:
            boolean r6 = this.relativePositioning;
            int r7 = this.horizontalAnchor;
            if (r6 == false) goto L24;
            float r62 = r7 / 99.0f;
            float r8 = this.verticalAnchor / 99.0f;
        L25:
            float r63 = (r62 * 0.9f) + 0.05f;
            float r82 = (r8 * 0.9f) + 0.05f;
            int r72 = this.anchorId;
            if ((r72 / 3) != 0) goto L29;
            boolean r9 = true;
            r5 = 0;
        L33:
            if ((r72 % 3) != 0) goto L36;
            ?? r73 = 0;
        L40:
            if (this.windowFillColor != COLOR_SOLID_BLACK) goto L44;
            r9 = false;
        L44:
            return new Cea708CueInfo(r1, r23, r82, 0, r5, r63, r73, -3.4028235E38f, r9, this.windowFillColor, this.priority);
        L36:
            if ((r72 % 3) != r9) goto L38;
            r73 = r9;
            goto L40
        L38:
            r73 = 2;
            goto L40
        L29:
            if ((r72 / 3) != 1) goto L31;
            r9 = true;
            goto L33
        L31:
            r9 = true;
            r5 = 2;
            goto L33
        L24:
            r62 = r7 / 209.0f;
            r8 = this.verticalAnchor / 74.0f;
            goto L25
        L19:
            r23 = Layout.Alignment.ALIGN_OPPOSITE;
        L20:
            r23 = Layout.Alignment.ALIGN_NORMAL;
            goto L21
        }

        public SpannableString buildSpannableString() {
            SpannableStringBuilder r0 = new SpannableStringBuilder(this.captionStringBuilder);
            int r1 = r0.length();
            if (r1 <= 0) goto L17;
            if (this.italicsStartPosition == (-1)) goto L8;
            r0.setSpan(new StyleSpan(2), this.italicsStartPosition, r1, 33);
        L8:
            if (this.underlineStartPosition == (-1)) goto L11;
            r0.setSpan(new UnderlineSpan(), this.underlineStartPosition, r1, 33);
        L11:
            if (this.foregroundColorStartPosition == (-1)) goto L14;
            r0.setSpan(new ForegroundColorSpan(this.foregroundColor), this.foregroundColorStartPosition, r1, 33);
        L14:
            if (this.backgroundColorStartPosition == (-1)) goto L17;
            r0.setSpan(new BackgroundColorSpan(this.backgroundColor), this.backgroundColorStartPosition, r1, 33);
        L17:
            return new SpannableString(r0);
        }

        public void clear() {
            this.rolledUpCaptions.clear();
            this.captionStringBuilder.clear();
            this.italicsStartPosition = -1;
            this.underlineStartPosition = -1;
            this.foregroundColorStartPosition = -1;
            this.backgroundColorStartPosition = -1;
            this.row = 0;
        }

        public void defineWindow(boolean r10, int r11, boolean r12, int r13, int r14, int r15, int r16, int r17, int r18) {
            this.defined = true;
            this.visible = r10;
            this.priority = r11;
            this.relativePositioning = r12;
            this.verticalAnchor = r13;
            this.horizontalAnchor = r14;
            this.anchorId = r16;
            int r4 = r15 + 1;
            if (this.rowCount == r4) goto L10;
            this.rowCount = r4;
        L6:
            if (this.rolledUpCaptions.size() >= this.rowCount) goto L9;
            if (this.rolledUpCaptions.size() < 15) goto L10;
        L9:
            this.rolledUpCaptions.remove(0);
        L10:
            if (r17 != 0) goto L12;
        L14:
            if (r18 != 0) goto L16;
            return;
        L16:
            if (this.penStyleId == r18) goto L23;
            this.penStyleId = r18;
            int r0 = r18 - 1;
            setPenAttributes(0, 1, 1, false, false, PEN_STYLE_EDGE_TYPE[r0], PEN_STYLE_FONT_STYLE[r0]);
            setPenColor(COLOR_SOLID_WHITE, PEN_STYLE_BACKGROUND[r0], COLOR_SOLID_BLACK);
            return;
        L23:
            return;
        L12:
            if (this.windowStyleId == r17) goto L14;
            this.windowStyleId = r17;
            int r02 = r17 - 1;
            setWindowAttributes(WINDOW_STYLE_FILL[r02], COLOR_TRANSPARENT, WINDOW_STYLE_WORD_WRAP[r02], 0, WINDOW_STYLE_PRINT_DIRECTION[r02], WINDOW_STYLE_SCROLL_DIRECTION[r02], WINDOW_STYLE_JUSTIFICATION[r02]);
            goto L14
        }

        public boolean isDefined() {
            return this.defined;
        }

        public boolean isEmpty() {
            if (isDefined() == true) goto L5;
            return true;
        L5:
            if (this.rolledUpCaptions.isEmpty() == true) goto L7;
            return false;
        L7:
            if (this.captionStringBuilder.length() == 0) goto L14;
            return false;
        L14:
            return true;
        }

        public boolean isVisible() {
            return this.visible;
        }

        public void reset() {
            clear();
            this.defined = false;
            this.visible = false;
            this.priority = 4;
            this.relativePositioning = false;
            this.verticalAnchor = 0;
            this.horizontalAnchor = 0;
            this.anchorId = 0;
            this.rowCount = 15;
            this.justification = 0;
            this.windowStyleId = 0;
            this.penStyleId = 0;
            int r0 = COLOR_SOLID_BLACK;
            this.windowFillColor = r0;
            this.foregroundColor = COLOR_SOLID_WHITE;
            this.backgroundColor = r0;
        }

        public void setPenAttributes(int r1, int r2, int r3, boolean r4, boolean r5, int r6, int r7) {
            if (this.italicsStartPosition == (-1)) goto L6;
            if (r4 == true) goto L9;
            this.captionStringBuilder.setSpan(new StyleSpan(2), this.italicsStartPosition, this.captionStringBuilder.length(), 33);
            this.italicsStartPosition = -1;
        L9:
            if (this.underlineStartPosition == (-1)) goto L13;
            if (r5 == true) goto L16;
            this.captionStringBuilder.setSpan(new UnderlineSpan(), this.underlineStartPosition, this.captionStringBuilder.length(), 33);
            this.underlineStartPosition = -1;
            return;
        L16:
            return;
        L13:
            if (r5 == false) goto L17;
            this.underlineStartPosition = this.captionStringBuilder.length();
            return;
        L17:
            return;
        L6:
            if (r4 == false) goto L9;
            this.italicsStartPosition = this.captionStringBuilder.length();
            goto L9
        }

        public void setPenColor(int r6, int r7, int r8) {
            if (this.foregroundColorStartPosition == (-1)) goto L8;
            if (this.foregroundColor == r6) goto L8;
            this.captionStringBuilder.setSpan(new ForegroundColorSpan(this.foregroundColor), this.foregroundColorStartPosition, this.captionStringBuilder.length(), 33);
        L8:
            if (r6 == COLOR_SOLID_WHITE) goto L11;
            this.foregroundColorStartPosition = this.captionStringBuilder.length();
            this.foregroundColor = r6;
        L11:
            if (this.backgroundColorStartPosition == (-1)) goto L16;
            if (this.backgroundColor == r7) goto L16;
            this.captionStringBuilder.setSpan(new BackgroundColorSpan(this.backgroundColor), this.backgroundColorStartPosition, this.captionStringBuilder.length(), 33);
        L16:
            if (r7 == COLOR_SOLID_BLACK) goto L19;
            this.backgroundColorStartPosition = this.captionStringBuilder.length();
            this.backgroundColor = r7;
            return;
        }

        public void setPenLocation(int r1, int r2) {
            if (this.row == r1) goto L5;
            append('\n');
        L5:
            this.row = r1;
        }

        public void setVisibility(boolean r1) {
            this.visible = r1;
        }

        public void setWindowAttributes(int r1, int r2, boolean r3, int r4, int r5, int r6, int r7) {
            this.windowFillColor = r1;
            this.justification = r7;
        }
    }

    public static final class DtvCcPacket {
        int currentIndex;
        public final byte[] packetData;
        public final int packetSize;
        public final int sequenceNumber;

        public DtvCcPacket(int r1, int r2) {
            this.sequenceNumber = r1;
            this.packetSize = r2;
            this.packetData = new byte[(r2 * 2) - 1];
            this.currentIndex = 0;
        }
    }

    public Cea708Decoder(int r4, List<byte[]> r5) {
        this.ccData = new ParsableByteArray();
        this.captionChannelPacketData = new ParsableBitArray();
        this.previousSequenceNumber = -1;
        boolean r1 = true;
        if (r4 != (-1)) goto L5;
        r4 = 1;
    L5:
        this.selectedServiceNumber = r4;
        if (r5 != null) goto L8;
    L10:
        r1 = false;
    L11:
        this.isWideAspectRatio = r1;
        this.cueInfoBuilders = new CueInfoBuilder[8];
        int r0 = 0;
    L12:
        CueInfoBuilder[] r12 = this.cueInfoBuilders;
        if (r0 >= 8) goto L15;
        r12[r0] = new CueInfoBuilder();
        r0 = r0 + 1;
        goto L12
    L15:
        this.currentCueInfoBuilder = r12[0];
        return;
    L8:
        if (CodecSpecificDataUtil.parseCea708InitializationData(r5) == false) goto L10;
        goto L10
    }

    private void finalizeCurrentPacket() {
        if (this.currentDtvCcPacket != null) goto L5;
        return;
    L5:
        processCurrentPacket();
        this.currentDtvCcPacket = null;
    }

    private List<Cue> getDisplayCues() {
        ArrayList r0 = new ArrayList();
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r2 >= 8) goto L13;
        if (this.cueInfoBuilders[r2].isEmpty() == true) goto L12;
        if (this.cueInfoBuilders[r2].isVisible() == false) goto L12;
        Cea708CueInfo r3 = this.cueInfoBuilders[r2].build();
        if (r3 == null) goto L12;
        r0.add(r3);
    L12:
        r2 = r2 + 1;
        goto L4
    L13:
        Collections.sort(r0, Cea708CueInfo.access$000());
        ArrayList r4 = new ArrayList(r0.size());
    L15:
        if (r1 >= r0.size()) goto L18;
        r4.add(((Cea708CueInfo) r0.get(r1)).cue);
        r1 = r1 + 1;
        goto L15
    L18:
        return Collections.unmodifiableList(r4);
    }

    private void handleC0Command(int r5) {
        if (r5 != 0) goto L4;
        return;
    L4:
        if (r5 != 3) goto L6;
        this.cues = getDisplayCues();
        return;
    L6:
        if (r5 == 8) goto L26;
        switch(r5) {
            case 12: goto L24;
            case 13: goto L22;
            case 14: goto L31;
            default: goto L9;
        };
    L22:
        this.currentCueInfoBuilder.append('\n');
        return;
    L24:
        resetCueBuilders();
        return;
    L31:
        return;
    L9:
        if (r5 < 17) goto L15;
        if (r5 > 23) goto L15;
        Log.w("Cea708Decoder", "Currently unsupported COMMAND_EXT1 Command: " + r5);
        this.captionChannelPacketData.skipBits(8);
        return;
    L15:
        if (r5 >= 24) goto L17;
    L20:
        Log.w("Cea708Decoder", "Invalid C0 command: " + r5);
        return;
    L17:
        if (r5 > 31) goto L20;
        Log.w("Cea708Decoder", "Currently unsupported COMMAND_P16 Command: " + r5);
        this.captionChannelPacketData.skipBits(16);
        return;
    L26:
        this.currentCueInfoBuilder.backspace();
    }

    private void handleC1Command(int r5) {
        int r2 = 1;
        switch(r5) {
            case 128: goto L65;
            case 129: goto L65;
            case 130: goto L65;
            case 131: goto L65;
            case 132: goto L65;
            case 133: goto L65;
            case 134: goto L65;
            case 135: goto L65;
            case 136: goto L60;
            case 137: goto L54;
            case 138: goto L49;
            case 139: goto L43;
            case 140: goto L38;
            case 141: goto L36;
            case 142: goto L84;
            case 143: goto L34;
            case 144: goto L29;
            case 145: goto L23;
            case 146: goto L17;
            case 147: goto L4;
            case 148: goto L4;
            case 149: goto L4;
            case 150: goto L4;
            case 151: goto L11;
            case 152: goto L6;
            case 153: goto L6;
            case 154: goto L6;
            case 155: goto L6;
            case 156: goto L6;
            case 157: goto L6;
            case 158: goto L6;
            case 159: goto L6;
            default: goto L4;
        };
    L4:
        Log.w("Cea708Decoder", "Invalid C1 command: " + r5);
        return;
    L6:
        int r52 = r5 - 152;
        handleDefineWindow(r52);
        if (this.currentWindow == r52) goto L85;
        this.currentWindow = r52;
        this.currentCueInfoBuilder = this.cueInfoBuilders[r52];
        return;
    L85:
        return;
    L34:
        resetCueBuilders();
        return;
    L36:
        this.captionChannelPacketData.skipBits(8);
        return;
    L38:
        if (r2 > 8) goto L87;
        if (this.captionChannelPacketData.readBit() == false) goto L42;
        this.cueInfoBuilders[8 - r2].reset();
    L42:
        r2 = r2 + 1;
        goto L38
    L87:
        return;
    L43:
        int r53 = 1;
    L44:
        if (r53 > 8) goto L88;
        if (this.captionChannelPacketData.readBit() == false) goto L48;
        this.cueInfoBuilders[8 - r53].setVisibility(!r0.isVisible());
    L48:
        r53 = r53 + 1;
        goto L44
    L88:
        return;
    L49:
        if (r2 > 8) goto L89;
        if (this.captionChannelPacketData.readBit() == false) goto L53;
        this.cueInfoBuilders[8 - r2].setVisibility(false);
    L53:
        r2 = r2 + 1;
        goto L49
    L89:
        return;
    L54:
        int r54 = 1;
    L55:
        if (r54 > 8) goto L90;
        if (this.captionChannelPacketData.readBit() == false) goto L59;
        this.cueInfoBuilders[8 - r54].setVisibility(true);
    L59:
        r54 = r54 + 1;
        goto L55
    L90:
        return;
    L60:
        if (r2 > 8) goto L91;
        if (this.captionChannelPacketData.readBit() == false) goto L64;
        this.cueInfoBuilders[8 - r2].clear();
    L64:
        r2 = r2 + 1;
        goto L60
    L91:
        return;
    L65:
        int r55 = r5 - 128;
        if (this.currentWindow == r55) goto L86;
        this.currentWindow = r55;
        this.currentCueInfoBuilder = this.cueInfoBuilders[r55];
        return;
    L86:
        return;
    L84:
        return;
    L11:
        if (this.currentCueInfoBuilder.isDefined() == true) goto L14;
        this.captionChannelPacketData.skipBits(32);
        return;
    L14:
        handleSetWindowAttributes();
        return;
    L17:
        if (this.currentCueInfoBuilder.isDefined() == true) goto L20;
        this.captionChannelPacketData.skipBits(16);
        return;
    L20:
        handleSetPenLocation();
        return;
    L23:
        if (this.currentCueInfoBuilder.isDefined() == true) goto L26;
        this.captionChannelPacketData.skipBits(24);
        return;
    L26:
        handleSetPenColor();
        return;
    L29:
        if (this.currentCueInfoBuilder.isDefined() == true) goto L32;
        this.captionChannelPacketData.skipBits(16);
        return;
    L32:
        handleSetPenAttributes();
    }

    private void handleC2Command(int r2) {
        if (r2 > 7) goto L6;
        return;
    L6:
        if (r2 > 15) goto L10;
        this.captionChannelPacketData.skipBits(8);
        return;
    L10:
        if (r2 > 23) goto L14;
        this.captionChannelPacketData.skipBits(16);
        return;
    L14:
        if (r2 > 31) goto L17;
        this.captionChannelPacketData.skipBits(24);
        return;
    }

    private void handleC3Command(int r2) {
        if (r2 > 135) goto L7;
        this.captionChannelPacketData.skipBits(32);
        return;
    L7:
        if (r2 > 143) goto L11;
        this.captionChannelPacketData.skipBits(40);
        return;
    L11:
        if (r2 > 159) goto L14;
        this.captionChannelPacketData.skipBits(2);
        int r22 = this.captionChannelPacketData.readBits(6);
        this.captionChannelPacketData.skipBits(r22 * 8);
        return;
    }

    private void handleDefineWindow(int r12) {
        CueInfoBuilder r1 = this.cueInfoBuilders[r12];
        this.captionChannelPacketData.skipBits(2);
        boolean r2 = this.captionChannelPacketData.readBit();
        this.captionChannelPacketData.skipBits(2);
        int r122 = this.captionChannelPacketData.readBits(3);
        boolean r4 = this.captionChannelPacketData.readBit();
        int r5 = this.captionChannelPacketData.readBits(7);
        int r6 = this.captionChannelPacketData.readBits(8);
        int r7 = this.captionChannelPacketData.readBits(4);
        int r8 = this.captionChannelPacketData.readBits(4);
        this.captionChannelPacketData.skipBits(2);
        this.captionChannelPacketData.skipBits(6);
        this.captionChannelPacketData.skipBits(2);
        r1.defineWindow(r2, r122, r4, r5, r6, r8, r7, this.captionChannelPacketData.readBits(3), this.captionChannelPacketData.readBits(3));
    }

    private void handleG0Character(int r2) {
        CueInfoBuilder r1 = this.currentCueInfoBuilder;
        if (r2 != 127) goto L6;
        r1.append(9835);
        return;
    L6:
        r1.append((char) (r2 & 255));
    }

    private void handleG1Character(int r1) {
        this.currentCueInfoBuilder.append((char) (r1 & 255));
    }

    private void handleG2Character(int r2) {
        if (r2 != 32) goto L5;
        this.currentCueInfoBuilder.append(' ');
        return;
    L5:
        if (r2 != 33) goto L7;
        this.currentCueInfoBuilder.append(Typography.nbsp);
        return;
    L7:
        if (r2 != 37) goto L9;
        this.currentCueInfoBuilder.append(Typography.ellipsis);
        return;
    L9:
        if (r2 != 42) goto L11;
        this.currentCueInfoBuilder.append(352);
        return;
    L11:
        if (r2 != 44) goto L13;
        this.currentCueInfoBuilder.append(338);
        return;
    L13:
        if (r2 != 63) goto L15;
        this.currentCueInfoBuilder.append(376);
        return;
    L15:
        if (r2 != 57) goto L17;
        this.currentCueInfoBuilder.append(Typography.tm);
        return;
    L17:
        if (r2 != 58) goto L19;
        this.currentCueInfoBuilder.append(353);
        return;
    L19:
        if (r2 != 60) goto L21;
        this.currentCueInfoBuilder.append(339);
        return;
    L21:
        if (r2 == 61) goto L58;
        switch(r2) {
            case 48: goto L56;
            case 49: goto L54;
            case 50: goto L52;
            case 51: goto L50;
            case 52: goto L48;
            case 53: goto L46;
            default: goto L23;
        };
    L23:
        switch(r2) {
            case 118: goto L44;
            case 119: goto L42;
            case 120: goto L40;
            case 121: goto L38;
            case 122: goto L36;
            case 123: goto L34;
            case 124: goto L32;
            case 125: goto L30;
            case 126: goto L28;
            case 127: goto L26;
            default: goto L24;
        };
    L24:
        Log.w("Cea708Decoder", "Invalid G2 character: " + r2);
        return;
    L26:
        this.currentCueInfoBuilder.append(9484);
        return;
    L28:
        this.currentCueInfoBuilder.append(9496);
        return;
    L30:
        this.currentCueInfoBuilder.append(9472);
        return;
    L32:
        this.currentCueInfoBuilder.append(9492);
        return;
    L34:
        this.currentCueInfoBuilder.append(9488);
        return;
    L36:
        this.currentCueInfoBuilder.append(9474);
        return;
    L38:
        this.currentCueInfoBuilder.append(8542);
        return;
    L40:
        this.currentCueInfoBuilder.append(8541);
        return;
    L42:
        this.currentCueInfoBuilder.append(8540);
        return;
    L44:
        this.currentCueInfoBuilder.append(8539);
        return;
    L46:
        this.currentCueInfoBuilder.append(Typography.bullet);
        return;
    L48:
        this.currentCueInfoBuilder.append(Typography.rightDoubleQuote);
        return;
    L50:
        this.currentCueInfoBuilder.append(Typography.leftDoubleQuote);
        return;
    L52:
        this.currentCueInfoBuilder.append(Typography.rightSingleQuote);
        return;
    L54:
        this.currentCueInfoBuilder.append(Typography.leftSingleQuote);
        return;
    L56:
        this.currentCueInfoBuilder.append(9608);
        return;
    L58:
        this.currentCueInfoBuilder.append(8480);
    }

    private void handleG3Character(int r3) {
        if (r3 != 160) goto L6;
        this.currentCueInfoBuilder.append(13252);
        return;
    L6:
        Log.w("Cea708Decoder", "Invalid G3 character: " + r3);
        this.currentCueInfoBuilder.append('_');
    }

    private void handleSetPenAttributes() {
        int r3 = this.captionChannelPacketData.readBits(4);
        int r4 = this.captionChannelPacketData.readBits(2);
        int r5 = this.captionChannelPacketData.readBits(2);
        boolean r6 = this.captionChannelPacketData.readBit();
        boolean r7 = this.captionChannelPacketData.readBit();
        int r8 = this.captionChannelPacketData.readBits(3);
        int r9 = this.captionChannelPacketData.readBits(3);
        this.currentCueInfoBuilder.setPenAttributes(r3, r4, r5, r6, r7, r8, r9);
    }

    private void handleSetPenColor() {
        int r0 = this.captionChannelPacketData.readBits(2);
        int r02 = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), r0);
        int r2 = this.captionChannelPacketData.readBits(2);
        int r22 = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), r2);
        this.captionChannelPacketData.skipBits(2);
        int r1 = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2));
        this.currentCueInfoBuilder.setPenColor(r02, r22, r1);
    }

    private void handleSetPenLocation() {
        this.captionChannelPacketData.skipBits(4);
        int r0 = this.captionChannelPacketData.readBits(4);
        this.captionChannelPacketData.skipBits(2);
        int r1 = this.captionChannelPacketData.readBits(6);
        this.currentCueInfoBuilder.setPenLocation(r0, r1);
    }

    private void handleSetWindowAttributes() {
        int r0 = this.captionChannelPacketData.readBits(2);
        int r6 = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), r0);
        int r02 = this.captionChannelPacketData.readBits(2);
        int r7 = CueInfoBuilder.getArgbColorFromCeaColor(this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2), this.captionChannelPacketData.readBits(2));
        if (this.captionChannelPacketData.readBit() == false) goto L5;
        r02 = r02 | 4;
    L5:
        int r9 = r02;
        boolean r8 = this.captionChannelPacketData.readBit();
        int r10 = this.captionChannelPacketData.readBits(2);
        int r11 = this.captionChannelPacketData.readBits(2);
        int r12 = this.captionChannelPacketData.readBits(2);
        this.captionChannelPacketData.skipBits(8);
        this.currentCueInfoBuilder.setWindowAttributes(r6, r7, r8, r9, r10, r11, r12);
    }

    @RequiresNonNull({"currentDtvCcPacket"})
    private void processCurrentPacket() {
        DtvCcPacket r0 = this.currentDtvCcPacket;
        if (r0.currentIndex == ((r0.packetSize * 2) - 1)) goto L5;
        Log.d("Cea708Decoder", "DtvCcPacket ended prematurely; size is " + ((this.currentDtvCcPacket.packetSize * 2) - 1) + ", but current index is " + this.currentDtvCcPacket.currentIndex + " (sequence number " + this.currentDtvCcPacket.sequenceNumber + ");");
    L5:
        ParsableBitArray r02 = this.captionChannelPacketData;
        DtvCcPacket r1 = this.currentDtvCcPacket;
        r02.reset(r1.packetData, r1.currentIndex);
        boolean r03 = false;
    L7:
        if (this.captionChannelPacketData.bitsLeft() <= 0) goto L44;
        int r12 = this.captionChannelPacketData.readBits(3);
        int r5 = this.captionChannelPacketData.readBits(5);
        if (r12 != 7) goto L13;
        this.captionChannelPacketData.skipBits(2);
        r12 = this.captionChannelPacketData.readBits(6);
        if (r12 >= 7) goto L13;
        Log.w("Cea708Decoder", "Invalid extended service number: " + r12);
    L13:
        if (r5 == 0) goto L14;
        int r6 = this.selectedServiceNumber;
        ParsableBitArray r7 = this.captionChannelPacketData;
        if (r12 != r6) goto L18;
        int r13 = r7.getPosition() + (r5 * 8);
    L21:
        if (this.captionChannelPacketData.getPosition() >= r13) goto L7;
        int r52 = this.captionChannelPacketData.readBits(8);
        if (r52 != 16) goto L24;
        int r53 = this.captionChannelPacketData.readBits(8);
        if (r53 > 31) goto L37;
        handleC2Command(r53);
        goto L21
    L37:
        if (r53 <= 127) goto L38;
        if (r53 > 159) goto L41;
        handleC3Command(r53);
        goto L21
    L41:
        if (r53 <= 255) goto L42;
        Log.w("Cea708Decoder", "Invalid extended command: " + r53);
        goto L21
    L42:
        handleG3Character(r53);
    L28:
        r03 = true;
        goto L21
    L38:
        handleG2Character(r53);
        goto L28
    L24:
        if (r52 <= 31) goto L25;
        if (r52 <= 127) goto L27;
        if (r52 <= 159) goto L30;
        if (r52 <= 255) goto L32;
        Log.w("Cea708Decoder", "Invalid base command: " + r52);
        goto L21
    L32:
        handleG1Character(r52);
        goto L28
    L30:
        handleC1Command(r52);
        goto L28
    L27:
        handleG0Character(r52);
        goto L28
    L25:
        handleC0Command(r52);
        goto L21
    L18:
        r7.skipBytes(r5);
        goto L7
    L14:
        if (r12 == 0) goto L44;
        Log.w("Cea708Decoder", "serviceNumber is non-zero (" + r12 + ") when blockSize is 0");
    L44:
        if (r03 == false) goto L76;
        this.cues = getDisplayCues();
        return;
    }

    private void resetCueBuilders() {
        int r0 = 0;
    L4:
        if (r0 >= 8) goto L6;
        this.cueInfoBuilders[r0].reset();
        r0 = r0 + 1;
        goto L4
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder
    public Subtitle createSubtitle() {
        List<Cue> r0 = this.cues;
        this.lastCues = r0;
        return new CeaSubtitle((List) Assertions.checkNotNull(r0));
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder
    public void decode(SubtitleInputBuffer r9) {
        ByteBuffer r92 = (ByteBuffer) Assertions.checkNotNull(r9.data);
        this.ccData.reset(r92.array(), r92.limit());
    L4:
        if (this.ccData.bytesLeft() < 3) goto L35;
        int r93 = this.ccData.readUnsignedByte();
        int r1 = r93 & 3;
        boolean r3 = false;
        if ((r93 & 4) != 4) goto L8;
        boolean r94 = true;
    L9:
        byte r5 = (byte) this.ccData.readUnsignedByte();
        byte r6 = (byte) this.ccData.readUnsignedByte();
        if (r1 == 2) goto L13;
        if (r1 != 3) goto L4;
    L13:
        if (r94 == false) goto L4;
        if (r1 == 3) goto L17;
        if (r1 != 2) goto L28;
        r3 = true;
    L28:
        Assertions.checkArgument(r3);
        DtvCcPacket r0 = this.currentDtvCcPacket;
        if (r0 != null) goto L31;
        Log.e("Cea708Decoder", "Encountered DTVCC_PACKET_DATA before DTVCC_PACKET_START");
        goto L4
    L31:
        byte[] r95 = r0.packetData;
        int r12 = r0.currentIndex;
        int r2 = r12 + 1;
        r0.currentIndex = r2;
        r95[r12] = r5;
        r0.currentIndex = r12 + 2;
        r95[r2] = r6;
    L32:
        DtvCcPacket r96 = this.currentDtvCcPacket;
        if (r96.currentIndex != ((r96.packetSize * 2) - 1)) goto L4;
        finalizeCurrentPacket();
        goto L4
    L17:
        finalizeCurrentPacket();
        int r02 = (r5 & 192) >> 6;
        int r13 = this.previousSequenceNumber;
        if (r13 != (-1)) goto L20;
    L22:
        this.previousSequenceNumber = r02;
        int r97 = r5 & 63;
        if (r97 != 0) goto L25;
        r97 = 64;
    L25:
        DtvCcPacket r14 = new DtvCcPacket(r02, r97);
        this.currentDtvCcPacket = r14;
        byte[] r98 = r14.packetData;
        int r03 = r14.currentIndex;
        r14.currentIndex = r03 + 1;
        r98[r03] = r6;
        goto L32
    L20:
        if (r02 == ((r13 + 1) % 4)) goto L22;
        resetCueBuilders();
        Log.w("Cea708Decoder", "Sequence number discontinuity. previous=" + this.previousSequenceNumber + " current=" + r02);
        goto L22
    L8:
        r94 = false;
        goto L9
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.decoder.Decoder
    public /* bridge */ /* synthetic */ SubtitleInputBuffer dequeueInputBuffer() {
        return super.dequeueInputBuffer();
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.decoder.Decoder
    public /* bridge */ /* synthetic */ SubtitleOutputBuffer dequeueOutputBuffer() {
        return super.dequeueOutputBuffer();
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.decoder.Decoder
    public void flush() {
        super.flush();
        this.cues = null;
        this.lastCues = null;
        this.currentWindow = 0;
        this.currentCueInfoBuilder = this.cueInfoBuilders[0];
        resetCueBuilders();
        this.currentDtvCcPacket = null;
    }

    @Override // androidx.media3.decoder.Decoder
    public String getName() {
        return "Cea708Decoder";
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder
    public boolean isNewSubtitleDataAvailable() {
        if (this.cues == this.lastCues) goto L6;
        return true;
    L6:
        return false;
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder
    public /* bridge */ /* synthetic */ void queueInputBuffer(SubtitleInputBuffer r1) {
        super.queueInputBuffer(r1);
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.decoder.Decoder
    public /* bridge */ /* synthetic */ void release() {
        super.release();
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.extractor.text.SubtitleDecoder
    public /* bridge */ /* synthetic */ void setPositionUs(long r1) {
        super.setPositionUs(r1);
    }
}
