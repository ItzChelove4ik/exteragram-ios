package androidx.media3.extractor.text.cea;

import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import androidx.media3.common.text.Cue;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Log;
import androidx.media3.common.util.ParsableByteArray;
import androidx.media3.extractor.text.Subtitle;
import androidx.media3.extractor.text.SubtitleInputBuffer;
import androidx.media3.extractor.text.SubtitleOutputBuffer;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import kotlin.jvm.internal.ByteCompanionObject;
import kotlin.jvm.internal.LongCompanionObject;
import org.mvel2.asm.Opcodes;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes4.dex */
public final class Cea608Decoder extends CeaDecoder {
    private static final int[] BASIC_CHARACTER_SET = null;
    private static final int[] COLUMN_INDICES = null;
    private static final boolean[] ODD_PARITY_BYTE_TABLE = null;
    private static final int[] ROW_INDICES = null;
    private static final int[] SPECIAL_CHARACTER_SET = null;
    private static final int[] SPECIAL_ES_FR_CHARACTER_SET = null;
    private static final int[] SPECIAL_PT_DE_CHARACTER_SET = null;
    private static final int[] STYLE_COLORS = null;
    private int captionMode;
    private int captionRowCount;
    private final ParsableByteArray ccData;
    private final ArrayList<CueBuilder> cueBuilders;
    private List<Cue> cues;
    private int currentChannel;
    private CueBuilder currentCueBuilder;
    private boolean isCaptionValid;
    private boolean isInCaptionService;
    private long lastCueUpdateUs;
    private List<Cue> lastCues;
    private final int packetLength;
    private byte repeatableControlCc1;
    private byte repeatableControlCc2;
    private boolean repeatableControlSet;
    private final int selectedChannel;
    private final int selectedField;
    private final long validDataChannelTimeoutUs;

    public static final class CueBuilder {
        private int captionMode;
        private int captionRowCount;
        private final StringBuilder captionStringBuilder;
        private final List<CueStyle> cueStyles;
        private int indent;
        private final List<SpannableString> rolledUpCaptions;
        private int row;
        private int tabOffset;

        public static class CueStyle {
            public int start;
            public final int style;
            public final boolean underline;

            public CueStyle(int r1, boolean r2, int r3) {
                this.style = r1;
                this.underline = r2;
                this.start = r3;
            }
        }

        public CueBuilder(int r2, int r3) {
            this.cueStyles = new ArrayList();
            this.rolledUpCaptions = new ArrayList();
            this.captionStringBuilder = new StringBuilder();
            reset(r2);
            this.captionRowCount = r3;
        }

        public static /* synthetic */ int access$002(CueBuilder r0, int r1) {
            r0.tabOffset = r1;
            return r1;
        }

        public static /* synthetic */ int access$100(CueBuilder r0) {
            return r0.row;
        }

        public static /* synthetic */ int access$102(CueBuilder r0, int r1) {
            r0.row = r1;
            return r1;
        }

        public static /* synthetic */ int access$202(CueBuilder r0, int r1) {
            r0.indent = r1;
            return r1;
        }

        private SpannableString buildCurrentLine() {
            SpannableStringBuilder r0 = new SpannableStringBuilder(this.captionStringBuilder);
            int r1 = r0.length();
            int r5 = -1;
            int r6 = -1;
            int r8 = -1;
            int r9 = -1;
            int r4 = 0;
            int r7 = 0;
            boolean r10 = false;
        L4:
            if (r4 >= this.cueStyles.size()) goto L35;
            CueStyle r11 = this.cueStyles.get(r4);
            boolean r12 = r11.underline;
            int r13 = r11.style;
            if (r13 != 8) goto L8;
        L15:
            int r112 = r11.start;
            r4 = r4 + 1;
            if (r4 >= this.cueStyles.size()) goto L18;
            int r132 = this.cueStyles.get(r4).start;
        L19:
            if (r112 == r132) goto L4;
            if (r5 == (-1)) goto L24;
            if (r12 == true) goto L24;
            setUnderlineSpan(r0, r5, r112);
            r5 = -1;
        L27:
            if (r6 == (-1)) goto L30;
            if (r10 == true) goto L30;
            setItalicSpan(r0, r6, r112);
            r6 = -1;
        L33:
            if (r9 == r8) goto L4;
            setColorSpan(r0, r7, r112, r8);
            r8 = r9;
            r7 = r112;
        L30:
            if (r6 != (-1)) goto L33;
            if (r10 == false) goto L33;
            r6 = r112;
        L24:
            if (r5 != (-1)) goto L27;
            if (r12 == false) goto L27;
            r5 = r112;
            goto L27
        L18:
            r132 = r1;
            goto L19
        L8:
            if (r13 != 7) goto L10;
            boolean r14 = true;
        L11:
            if (r13 == 7) goto L14;
            r9 = Cea608Decoder.access$300()[r13];
        L14:
            r10 = r14;
            goto L15
        L10:
            r14 = false;
            goto L11
        L35:
            if (r5 == (-1)) goto L38;
            if (r5 == r1) goto L38;
            setUnderlineSpan(r0, r5, r1);
        L38:
            if (r6 == (-1)) goto L41;
            if (r6 == r1) goto L41;
            setItalicSpan(r0, r6, r1);
        L41:
            if (r7 == r1) goto L44;
            setColorSpan(r0, r7, r1, r8);
        L44:
            return new SpannableString(r0);
        }

        private static void setColorSpan(SpannableStringBuilder r1, int r2, int r3, int r4) {
            if (r4 != (-1)) goto L5;
            return;
        L5:
            r1.setSpan(new ForegroundColorSpan(r4), r2, r3, 33);
        }

        private static void setItalicSpan(SpannableStringBuilder r2, int r3, int r4) {
            r2.setSpan(new StyleSpan(2), r3, r4, 33);
        }

        private static void setUnderlineSpan(SpannableStringBuilder r2, int r3, int r4) {
            r2.setSpan(new UnderlineSpan(), r3, r4, 33);
        }

        public void append(char r3) {
            if (this.captionStringBuilder.length() >= 32) goto L6;
            this.captionStringBuilder.append(r3);
            return;
        }

        public void backspace() {
            int r0 = this.captionStringBuilder.length();
            if (r0 <= 0) goto L9;
            this.captionStringBuilder.delete(r0 - 1, r0);
            int r1 = this.cueStyles.size() - 1;
        L5:
            if (r1 < 0) goto L12;
            CueStyle r2 = this.cueStyles.get(r1);
            int r3 = r2.start;
            if (r3 != r0) goto L13;
            r2.start = r3 - 1;
            r1 = r1 - 1;
            goto L5
        L13:
            return;
        L12:
            return;
        }

        public Cue build(int r9) {
            SpannableStringBuilder r0 = new SpannableStringBuilder();
            int r2 = 0;
        L4:
            if (r2 >= this.rolledUpCaptions.size()) goto L6;
            r0.append(this.rolledUpCaptions.get(r2));
            r0.append('\n');
            r2 = r2 + 1;
            goto L4
        L6:
            r0.append(buildCurrentLine());
            if (r0.length() != 0) goto L10;
            return null;
        L10:
            int r22 = this.indent + this.tabOffset;
            int r3 = (32 - r22) - r0.length();
            int r4 = r22 - r3;
            if (r9 == Integer.MIN_VALUE) goto L14;
        L24:
            if (r9 != 1) goto L26;
            float r23 = 0.5f;
        L30:
            int r1 = this.row;
            if (r1 <= 7) goto L34;
            r1 = r1 - 17;
        L37:
            return new Cue.Builder().setText(r0).setTextAlignment(Layout.Alignment.ALIGN_NORMAL).setLine(r1, 1).setPosition(r23).setPositionAnchor(r9).build();
        L34:
            if (this.captionMode != 1) goto L37;
            r1 = r1 - (this.captionRowCount - 1);
            goto L37
        L26:
            if (r9 == 2) goto L28;
        L27:
            r23 = ((r22 / 32.0f) * 0.8f) + 0.1f;
            goto L30
        L28:
            r22 = 32 - r3;
            goto L27
        L14:
            if (this.captionMode != 2) goto L20;
            if (Math.abs(r4) < 3) goto L18;
            if (r3 >= 0) goto L20;
        L18:
            r9 = 1;
        L20:
            if (this.captionMode != 2) goto L23;
            if (r4 <= 0) goto L23;
            r9 = 2;
        L23:
            r9 = 0;
            goto L24
        }

        public boolean isEmpty() {
            if (this.cueStyles.isEmpty() == true) goto L5;
            return false;
        L5:
            if (this.rolledUpCaptions.isEmpty() == true) goto L7;
            return false;
        L7:
            if (this.captionStringBuilder.length() != 0) goto L13;
            return true;
        L13:
            return false;
        }

        public void reset(int r2) {
            this.captionMode = r2;
            this.cueStyles.clear();
            this.rolledUpCaptions.clear();
            this.captionStringBuilder.setLength(0);
            this.row = 15;
            this.indent = 0;
            this.tabOffset = 0;
        }

        public void rollUp() {
            this.rolledUpCaptions.add(buildCurrentLine());
            this.captionStringBuilder.setLength(0);
            this.cueStyles.clear();
            int r0 = Math.min(this.captionRowCount, this.row);
        L4:
            if (this.rolledUpCaptions.size() < r0) goto L6;
            this.rolledUpCaptions.remove(0);
            goto L4
        }

        public void setCaptionMode(int r1) {
            this.captionMode = r1;
        }

        public void setCaptionRowCount(int r1) {
            this.captionRowCount = r1;
        }

        public void setStyle(int r3, boolean r4) {
            this.cueStyles.add(new CueStyle(r3, r4, this.captionStringBuilder.length()));
        }
    }

    static {
        ROW_INDICES = new int[]{11, 1, 3, 12, 14, 5, 7, 9};
        COLUMN_INDICES = new int[]{0, 4, 8, 12, 16, 20, 24, 28};
        STYLE_COLORS = new int[]{-1, -16711936, -16776961, -16711681, Opcodes.V_PREVIEW, -256, -65281};
        BASIC_CHARACTER_SET = new int[]{32, 33, 34, 35, 36, 37, 38, 39, 40, 41, com.android.dx.io.Opcodes.SHR_INT_LIT8, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60, 61, 62, 63, 64, 65, 66, 67, 68, 69, 70, 71, 72, 73, 74, 75, 76, 77, 78, 79, 80, 81, 82, 83, 84, 85, 86, 87, 88, 89, 90, 91, 233, 93, 237, 243, 250, 97, 98, 99, 100, 101, 102, 103, 104, 105, 106, 107, 108, 109, 110, 111, 112, 113, 114, 115, 116, 117, 118, 119, 120, 121, 122, 231, 247, com.android.dx.io.Opcodes.RSUB_INT, 241, 9632};
        SPECIAL_CHARACTER_SET = new int[]{174, 176, 189, 191, 8482, 162, 163, 9834, com.android.dx.io.Opcodes.SHL_INT_LIT8, 32, 232, com.android.dx.io.Opcodes.USHR_INT_LIT8, 234, 238, 244, com.android.dx.io.Opcodes.INVOKE_POLYMORPHIC_RANGE};
        SPECIAL_ES_FR_CHARACTER_SET = new int[]{193, 201, com.android.dx.io.Opcodes.DIV_INT_LIT16, com.android.dx.io.Opcodes.MUL_INT_LIT8, com.android.dx.io.Opcodes.REM_INT_LIT8, com.android.dx.io.Opcodes.INVOKE_CUSTOM, 8216, 161, 42, 39, 8212, 169, 8480, 8226, 8220, 8221, 192, 194, 199, 200, 202, 203, 235, com.android.dx.io.Opcodes.DIV_DOUBLE_2ADDR, com.android.dx.io.Opcodes.REM_DOUBLE_2ADDR, 239, com.android.dx.io.Opcodes.REM_INT_LIT16, com.android.dx.io.Opcodes.RSUB_INT_LIT8, 249, com.android.dx.io.Opcodes.DIV_INT_LIT8, 171, 187};
        SPECIAL_PT_DE_CHARACTER_SET = new int[]{195, 227, com.android.dx.io.Opcodes.MUL_DOUBLE_2ADDR, com.android.dx.io.Opcodes.SUB_DOUBLE_2ADDR, 236, com.android.dx.io.Opcodes.MUL_INT_LIT16, 242, com.android.dx.io.Opcodes.AND_INT_LIT16, 245, 123, 125, 92, 94, 95, 124, 126, 196, 228, com.android.dx.io.Opcodes.OR_INT_LIT16, 246, com.android.dx.io.Opcodes.XOR_INT_LIT8, 165, 164, 9474, 197, TLRPC.LAYER, com.android.dx.io.Opcodes.ADD_INT_LIT8, 248, 9484, 9488, 9492, 9496};
        ODD_PARITY_BYTE_TABLE = new boolean[]{false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false, false, true, true, false, true, false, false, true, false, true, true, false, true, false, false, true, true, false, false, true, false, true, true, false};
    }

    public Cea608Decoder(String r9, int r10, long r11) {
        this.ccData = new ParsableByteArray();
        this.cueBuilders = new ArrayList();
        this.currentCueBuilder = new CueBuilder(0, 4);
        this.currentChannel = 0;
        if (r11 != (-9223372036854775807L)) goto L5;
        this.validDataChannelTimeoutUs = -9223372036854775807L;
    L11:
        if ("application/x-mp4-cea-608".equals(r9) == false) goto L13;
        int r92 = 2;
    L14:
        this.packetLength = r92;
        if (r10 == 1) goto L23;
        if (r10 == 2) goto L22;
        if (r10 == 3) goto L21;
        if (r10 == 4) goto L20;
        Log.w("Cea608Decoder", "Invalid channel. Defaulting to CC1.");
        this.selectedChannel = 0;
        this.selectedField = 0;
    L24:
        setCaptionMode(0);
        resetCueBuilders();
        this.isInCaptionService = true;
        this.lastCueUpdateUs = -9223372036854775807L;
        return;
    L20:
        this.selectedChannel = 1;
        this.selectedField = 1;
        goto L24
    L21:
        this.selectedChannel = 0;
        this.selectedField = 1;
        goto L24
    L22:
        this.selectedChannel = 1;
        this.selectedField = 0;
        goto L24
    L23:
        this.selectedChannel = 0;
        this.selectedField = 0;
        goto L24
    L13:
        r92 = 3;
        goto L14
    L5:
        if (r11 < 16000) goto L7;
        boolean r0 = true;
    L8:
        Assertions.checkArgument(r0);
        this.validDataChannelTimeoutUs = r11 * 1000;
        goto L11
    L7:
        r0 = false;
        goto L8
    }

    public static /* synthetic */ int[] access$300() {
        return STYLE_COLORS;
    }

    private static char getBasicChar(byte r1) {
        return (char) BASIC_CHARACTER_SET[(r1 & ByteCompanionObject.MAX_VALUE) - 32];
    }

    private static int getChannel(byte r0) {
        return (r0 >> 3) & 1;
    }

    private List<Cue> getDisplayCues() {
        int r0 = this.cueBuilders.size();
        ArrayList r1 = new ArrayList(r0);
        int r2 = 2;
        int r3 = 0;
        int r4 = 0;
    L3:
        if (r4 >= r0) goto L8;
        Cue r5 = this.cueBuilders.get(r4).build(Integer.MIN_VALUE);
        r1.add(r5);
        if (r5 == null) goto L7;
        r2 = Math.min(r2, r5.positionAnchor);
    L7:
        r4 = r4 + 1;
        goto L3
    L8:
        ArrayList r42 = new ArrayList(r0);
    L9:
        if (r3 >= r0) goto L17;
        Cue r52 = (Cue) r1.get(r3);
        if (r52 == null) goto L16;
        if (r52.positionAnchor == r2) goto L15;
        r52 = (Cue) Assertions.checkNotNull(this.cueBuilders.get(r3).build(r2));
    L15:
        r42.add(r52);
    L16:
        r3 = r3 + 1;
        goto L9
    L17:
        return r42;
    }

    private static char getExtendedEsFrChar(byte r1) {
        int[] r0 = SPECIAL_ES_FR_CHARACTER_SET;
        return (char) r0[r1 & 31];
    }

    private static char getExtendedPtDeChar(byte r1) {
        int[] r0 = SPECIAL_PT_DE_CHARACTER_SET;
        return (char) r0[r1 & 31];
    }

    private static char getExtendedWestEuropeanChar(byte r0, byte r1) {
        if ((r0 & 1) != 0) goto L7;
        return getExtendedEsFrChar(r1);
    L7:
        return getExtendedPtDeChar(r1);
    }

    private static char getSpecialNorthAmericanChar(byte r1) {
        int[] r0 = SPECIAL_CHARACTER_SET;
        return (char) r0[r1 & 15];
    }

    private void handleMidrowCtrl(byte r3) {
        this.currentCueBuilder.append(' ');
        if ((r3 & 1) != 1) goto L5;
        boolean r0 = true;
    L6:
        this.currentCueBuilder.setStyle((r3 >> 1) & 7, r0);
        return;
    L5:
        r0 = false;
        goto L6
    }

    private void handleMiscCode(byte r5) {
        if (r5 != 32) goto L5;
        setCaptionMode(2);
        return;
    L5:
        if (r5 != 41) goto L7;
        setCaptionMode(3);
        return;
    L7:
        switch(r5) {
            case 37: goto L37;
            case 38: goto L35;
            case 39: goto L33;
            default: goto L8;
        };
    L8:
        int r1 = this.captionMode;
        if (r1 != 0) goto L12;
        return;
    L12:
        if (r5 == 33) goto L31;
        switch(r5) {
            case 44: goto L24;
            case 45: goto L19;
            case 46: goto L17;
            case 47: goto L15;
            default: goto L46;
        };
    L15:
        this.cues = getDisplayCues();
        resetCueBuilders();
        return;
    L17:
        resetCueBuilders();
        return;
    L19:
        if (r1 == 1) goto L21;
        return;
    L21:
        if (this.currentCueBuilder.isEmpty() == true) goto L43;
        this.currentCueBuilder.rollUp();
        return;
    L43:
        return;
    L24:
        this.cues = Collections.EMPTY_LIST;
        if (r1 == 1) goto L29;
        if (r1 == 3) goto L29;
        return;
    L29:
        resetCueBuilders();
        return;
    L46:
        return;
    L31:
        this.currentCueBuilder.backspace();
        return;
    L33:
        setCaptionMode(1);
        setCaptionRowCount(4);
        return;
    L35:
        setCaptionMode(1);
        setCaptionRowCount(3);
        return;
    L37:
        setCaptionMode(1);
        setCaptionRowCount(2);
    }

    private void handlePreambleAddressCode(byte r5, byte r6) {
        int r52 = ROW_INDICES[r5 & 7];
        if ((r6 & 32) == 0) goto L6;
        r52 = r52 + 1;
    L6:
        if (r52 != CueBuilder.access$100(this.currentCueBuilder)) goto L8;
    L13:
        boolean r0 = false;
        if ((r6 & 16) != 16) goto L16;
        boolean r53 = true;
    L18:
        if ((r6 & 1) != 1) goto L20;
        r0 = true;
    L20:
        int r62 = (r6 >> 1) & 7;
        CueBuilder r1 = this.currentCueBuilder;
        if (r53 == false) goto L23;
        int r2 = 8;
    L24:
        r1.setStyle(r2, r0);
        if (r53 == false) goto L28;
        CueBuilder.access$202(this.currentCueBuilder, COLUMN_INDICES[r62]);
        return;
    L28:
        return;
    L23:
        r2 = r62;
        goto L24
    L16:
        r53 = false;
        goto L18
    L8:
        if (this.captionMode != 1) goto L10;
    L12:
        CueBuilder.access$102(this.currentCueBuilder, r52);
        goto L13
    L10:
        if (this.currentCueBuilder.isEmpty() == true) goto L12;
        CueBuilder r02 = new CueBuilder(this.captionMode, this.captionRowCount);
        this.currentCueBuilder = r02;
        this.cueBuilders.add(r02);
        goto L12
    }

    private static boolean isCtrlCode(byte r0) {
        if ((r0 & 224) != 0) goto L6;
        return true;
    L6:
        return false;
    }

    private static boolean isExtendedWestEuropeanChar(byte r1, byte r2) {
        if ((r1 & 246) == 18) goto L5;
        return false;
    L5:
        if ((r2 & 224) != 32) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean isMidrowCtrlCode(byte r1, byte r2) {
        if ((r1 & 247) == 17) goto L5;
        return false;
    L5:
        if ((r2 & 240) != 32) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean isMiscCode(byte r1, byte r2) {
        if ((r1 & 246) == 20) goto L5;
        return false;
    L5:
        if ((r2 & 240) != 32) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean isPreambleAddressCode(byte r1, byte r2) {
        if ((r1 & 240) == 16) goto L5;
        return false;
    L5:
        if ((r2 & 192) != 64) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean isRepeatable(byte r1) {
        if ((r1 & 240) != 16) goto L6;
        return true;
    L6:
        return false;
    }

    private boolean isRepeatedCommand(boolean r3, byte r4, byte r5) {
        if (r3 == true) goto L5;
    L15:
        this.repeatableControlSet = false;
    L16:
        return false;
    L5:
        if (isRepeatable(r4) == false) goto L15;
        if (this.repeatableControlSet == true) goto L9;
    L14:
        this.repeatableControlSet = true;
        this.repeatableControlCc1 = r4;
        this.repeatableControlCc2 = r5;
        goto L16
    L9:
        if (this.repeatableControlCc1 != r4) goto L14;
        if (this.repeatableControlCc2 != r5) goto L14;
        this.repeatableControlSet = false;
        return true;
    }

    private static boolean isServiceSwitchCommand(byte r1) {
        if ((r1 & 246) != 20) goto L6;
        return true;
    L6:
        return false;
    }

    private static boolean isSpecialNorthAmericanChar(byte r1, byte r2) {
        if ((r1 & 247) == 17) goto L5;
        return false;
    L5:
        if ((r2 & 240) != 48) goto L10;
        return true;
    L10:
        return false;
    }

    private static boolean isTabCtrlCode(byte r1, byte r2) {
        if ((r1 & 247) == 23) goto L5;
        return false;
    L5:
        if (r2 >= 33) goto L7;
        return false;
    L7:
        if (r2 > 35) goto L13;
        return true;
    L13:
        return false;
    }

    private static boolean isXdsControlCode(byte r2) {
        if (1 <= r2) goto L5;
        return false;
    L5:
        if (r2 > 15) goto L9;
        return true;
    L9:
        return false;
    }

    private void maybeUpdateIsInCaptionService(byte r3, byte r4) {
        if (isXdsControlCode(r3) == false) goto L7;
        this.isInCaptionService = false;
        return;
    L7:
        if (isServiceSwitchCommand(r3) == true) goto L9;
        return;
    L9:
        if (r4 != 32) goto L11;
    L17:
        this.isInCaptionService = true;
        return;
    L11:
        if (r4 == 47) goto L17;
        switch(r4) {
            case 37: goto L17;
            case 38: goto L17;
            case 39: goto L17;
            default: goto L13;
        };
    L13:
        switch(r4) {
            case 41: goto L17;
            case 42: goto L15;
            case 43: goto L15;
            default: goto L20;
        };
    L15:
        this.isInCaptionService = false;
        return;
    }

    private void resetCueBuilders() {
        this.currentCueBuilder.reset(this.captionMode);
        this.cueBuilders.clear();
        this.cueBuilders.add(this.currentCueBuilder);
    }

    private void setCaptionMode(int r3) {
        int r0 = this.captionMode;
        if (r0 == r3) goto L21;
        this.captionMode = r3;
        if (r3 != 3) goto L11;
        int r02 = 0;
    L9:
        if (r02 >= this.cueBuilders.size()) goto L22;
        this.cueBuilders.get(r02).setCaptionMode(r3);
        r02 = r02 + 1;
        goto L9
    L22:
        return;
    L11:
        resetCueBuilders();
        if (r0 != 3) goto L14;
    L18:
        this.cues = Collections.EMPTY_LIST;
        return;
    L14:
        if (r3 == 1) goto L18;
        if (r3 == 0) goto L18;
        return;
    }

    private void setCaptionRowCount(int r1) {
        this.captionRowCount = r1;
        this.currentCueBuilder.setCaptionRowCount(r1);
    }

    private boolean shouldClearStuckCaptions() {
        if (this.validDataChannelTimeoutUs != (-9223372036854775807L)) goto L5;
    L11:
        return false;
    L5:
        if (this.lastCueUpdateUs == (-9223372036854775807L)) goto L11;
        if ((getPositionUs() - this.lastCueUpdateUs) < this.validDataChannelTimeoutUs) goto L11;
        return true;
    }

    private boolean updateAndVerifyCurrentChannel(byte r2) {
        if (isCtrlCode(r2) == false) goto L6;
        this.currentChannel = getChannel(r2);
    L6:
        if (this.currentChannel != this.selectedChannel) goto L9;
        return true;
    L9:
        return false;
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder
    public Subtitle createSubtitle() {
        List<Cue> r0 = this.cues;
        this.lastCues = r0;
        return new CeaSubtitle((List) Assertions.checkNotNull(r0));
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder
    public void decode(SubtitleInputBuffer r10) {
        ByteBuffer r102 = (ByteBuffer) Assertions.checkNotNull(r10.data);
        this.ccData.reset(r102.array(), r102.limit());
        boolean r0 = false;
    L3:
        int r1 = this.ccData.bytesLeft();
        int r2 = this.packetLength;
        if (r1 < r2) goto L64;
        if (r2 != 2) goto L8;
        int r12 = -4;
    L9:
        int r22 = this.ccData.readUnsignedByte();
        int r4 = this.ccData.readUnsignedByte();
        if ((r12 & 2) != 0) goto L3;
        if ((r12 & 1) != this.selectedField) goto L3;
        byte r5 = (byte) (r22 & 127);
        byte r6 = (byte) (r4 & 127);
        if (r5 != 0) goto L19;
        if (r6 == 0) goto L3;
    L19:
        boolean r7 = this.isCaptionValid;
        if ((r12 & 4) != 4) goto L26;
        boolean[] r13 = ODD_PARITY_BYTE_TABLE;
        if (r13[r22] == false) goto L26;
        if (r13[r4] == false) goto L26;
        boolean r14 = true;
    L27:
        this.isCaptionValid = r14;
        if (isRepeatedCommand(r14, r5, r6) == true) goto L3;
        if (this.isCaptionValid == false) goto L32;
        maybeUpdateIsInCaptionService(r5, r6);
        if (this.isInCaptionService == false) goto L3;
        if (updateAndVerifyCurrentChannel(r5) == false) goto L3;
        if (isCtrlCode(r5) == true) goto L44;
        this.currentCueBuilder.append(getBasicChar(r5));
        if ((r6 & 224) != 0) goto L63;
    L34:
        r0 = true;
        goto L3
    L63:
        this.currentCueBuilder.append(getBasicChar(r6));
        goto L34
    L44:
        if (isSpecialNorthAmericanChar(r5, r6) == false) goto L47;
        this.currentCueBuilder.append(getSpecialNorthAmericanChar(r6));
        goto L34
    L47:
        if (isExtendedWestEuropeanChar(r5, r6) == false) goto L50;
        this.currentCueBuilder.backspace();
        this.currentCueBuilder.append(getExtendedWestEuropeanChar(r5, r6));
        goto L34
    L50:
        if (isMidrowCtrlCode(r5, r6) == false) goto L53;
        handleMidrowCtrl(r6);
        goto L34
    L53:
        if (isPreambleAddressCode(r5, r6) == false) goto L56;
        handlePreambleAddressCode(r5, r6);
        goto L34
    L56:
        if (isTabCtrlCode(r5, r6) == false) goto L59;
        CueBuilder.access$002(this.currentCueBuilder, r6 - 32);
        goto L34
    L59:
        if (isMiscCode(r5, r6) == false) goto L34;
        handleMiscCode(r6);
        goto L34
    L32:
        if (r7 == false) goto L3;
        resetCueBuilders();
    L26:
        r14 = false;
        goto L27
    L8:
        r12 = this.ccData.readUnsignedByte();
        goto L9
    L64:
        if (r0 == false) goto L91;
        int r103 = this.captionMode;
        if (r103 != 1) goto L68;
    L69:
        this.cues = getDisplayCues();
        this.lastCueUpdateUs = getPositionUs();
        return;
    L68:
        if (r103 == 3) goto L69;
        return;
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.decoder.Decoder
    public /* bridge */ /* synthetic */ SubtitleInputBuffer dequeueInputBuffer() {
        return super.dequeueInputBuffer();
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.decoder.Decoder
    public SubtitleOutputBuffer dequeueOutputBuffer() {
        SubtitleOutputBuffer r0 = super.dequeueOutputBuffer();
        if (r0 == null) goto L6;
        return r0;
    L6:
        if (shouldClearStuckCaptions() == false) goto L11;
        SubtitleOutputBuffer r1 = getAvailableOutputBuffer();
        if (r1 == null) goto L13;
        this.cues = Collections.EMPTY_LIST;
        this.lastCueUpdateUs = -9223372036854775807L;
        Subtitle r4 = createSubtitle();
        r1.setContent(getPositionUs(), r4, LongCompanionObject.MAX_VALUE);
        return r1;
    L13:
        return null;
    L11:
        return null;
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.decoder.Decoder
    public /* bridge */ /* synthetic */ SubtitleOutputBuffer dequeueOutputBuffer() {
        return dequeueOutputBuffer();
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.decoder.Decoder
    public void flush() {
        super.flush();
        this.cues = null;
        this.lastCues = null;
        setCaptionMode(0);
        setCaptionRowCount(4);
        resetCueBuilders();
        this.isCaptionValid = false;
        this.repeatableControlSet = false;
        this.repeatableControlCc1 = 0;
        this.repeatableControlCc2 = 0;
        this.currentChannel = 0;
        this.isInCaptionService = true;
        this.lastCueUpdateUs = -9223372036854775807L;
    }

    @Override // androidx.media3.decoder.Decoder
    public String getName() {
        return "Cea608Decoder";
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
    public void release() {
    }

    @Override // androidx.media3.extractor.text.cea.CeaDecoder, androidx.media3.extractor.text.SubtitleDecoder
    public /* bridge */ /* synthetic */ void setPositionUs(long r1) {
        super.setPositionUs(r1);
    }
}
