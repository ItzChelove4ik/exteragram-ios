package org.telegram.messenger.video;

import com.googlecode.mp4parser.h264.read.CAVLCReader;
import java.io.InputStream;
import java.lang.reflect.Array;

/* loaded from: classes6.dex */
public class SequenceParameterSetRbsp {
    public int bit_depth_chroma_minus8;
    public int bit_depth_luma_minus8;
    public int chroma_format_idc;
    public long general_constraint_indicator_flags;
    public byte general_level_idc;
    public long general_profile_compatibility_flags;
    public int general_profile_idc;
    public int general_profile_space;
    public boolean general_tier_flag;
    public int pic_height_in_luma_samples;
    public int pic_width_in_luma_samples;
    public int sps_max_sub_layers_minus1;
    public boolean sps_temporal_id_nesting_flag;

    public SequenceParameterSetRbsp(InputStream r11) {
        CAVLCReader r0 = new CAVLCReader(r11);
        r0.readNBit(4, "sps_video_parameter_set_id");
        this.sps_max_sub_layers_minus1 = (int) r0.readNBit(3, "sps_max_sub_layers_minus1");
        r0.readBool("sps_temporal_id_nesting_flag");
        profile_tier_level(this.sps_max_sub_layers_minus1, r0);
        r0.readUE("sps_seq_parameter_set_id");
        int r112 = r0.readUE("chroma_format_idc");
        this.chroma_format_idc = r112;
        if (r112 != 3) goto L5;
        r0.read1Bit();
    L5:
        this.pic_width_in_luma_samples = r0.readUE("pic_width_in_luma_samples");
        this.pic_height_in_luma_samples = r0.readUE("pic_width_in_luma_samples");
        if (r0.readBool("conformance_window_flag") == false) goto L8;
        r0.readUE("conf_win_left_offset");
        r0.readUE("conf_win_right_offset");
        r0.readUE("conf_win_top_offset");
        r0.readUE("conf_win_bottom_offset");
    L8:
        this.bit_depth_luma_minus8 = r0.readUE("bit_depth_luma_minus8");
        this.bit_depth_chroma_minus8 = r0.readUE("bit_depth_chroma_minus8");
        int r113 = r0.readUE("log2_max_pic_order_cnt_lsb_minus4");
        boolean r2 = r0.readBool("sps_sub_layer_ordering_info_present_flag");
        int r3 = this.sps_max_sub_layers_minus1;
        int r4 = 0;
        if (r2 == false) goto L11;
        int r5 = 0;
    L12:
        int r52 = (r3 - r5) + 1;
        int[] r6 = new int[r52];
        int[] r7 = new int[r52];
        int[] r53 = new int[r52];
        if (r2 == false) goto L16;
        r3 = 0;
    L16:
        if (r3 > this.sps_max_sub_layers_minus1) goto L18;
        r6[r3] = r0.readUE("sps_max_dec_pic_buffering_minus1[" + r3 + "]");
        r7[r3] = r0.readUE("sps_max_num_reorder_pics[" + r3 + "]");
        r53[r3] = r0.readUE("sps_max_latency_increase_plus1[" + r3 + "]");
        r3 = r3 + 1;
        goto L16
    L18:
        r0.readUE("log2_min_luma_coding_block_size_minus3");
        r0.readUE("log2_diff_max_min_luma_coding_block_size");
        r0.readUE("log2_min_transform_block_size_minus2");
        r0.readUE("log2_diff_max_min_transform_block_size");
        r0.readUE("max_transform_hierarchy_depth_inter");
        r0.readUE("max_transform_hierarchy_depth_intra");
        if (r0.readBool("scaling_list_enabled_flag") == true) goto L21;
    L23:
        r0.readBool("amp_enabled_flag");
        r0.readBool("sample_adaptive_offset_enabled_flag");
        if (r0.readBool("pcm_enabled_flag") == false) goto L26;
        r0.readNBit(4, "pcm_sample_bit_depth_luma_minus1");
        r0.readNBit(4, "pcm_sample_bit_depth_chroma_minus1");
        r0.readUE("log2_min_pcm_luma_coding_block_size_minus3");
        r0.readUE("log2_diff_max_min_pcm_luma_coding_block_size");
        r0.readBool("pcm_loop_filter_disabled_flag");
    L26:
        parse_short_term_ref_pic_sets(r0.readUE("num_short_term_ref_pic_sets"), r0);
        if (r0.readBool("long_term_ref_pics_present_flag") == false) goto L31;
        int r10 = r0.readUE("num_long_term_ref_pics_sps");
        int[] r22 = new int[r10];
        boolean[] r32 = new boolean[r10];
    L29:
        if (r4 >= r10) goto L31;
        r22[r4] = r0.readU(r113 + 4, "lt_ref_pic_poc_lsb_sps[" + r4 + "]");
        r32[r4] = r0.readBool("used_by_curr_pic_lt_sps_flag[" + r4 + "]");
        r4 = r4 + 1;
    L31:
        r0.readBool("sps_temporal_mvp_enabled_flag");
        r0.readBool("strong_intra_smoothing_enabled_flag");
        return;
    L21:
        if (r0.readBool("sps_scaling_list_data_present_flag") == false) goto L23;
        skip_scaling_list_data(r0);
        goto L23
    L11:
        r5 = r3;
        goto L12
    }

    private void parse_short_term_ref_pic_sets(int r10, CAVLCReader r11) {
        long[] r9 = new long[r10];
        int r1 = 0;
    L3:
        if (r1 >= r10) goto L24;
        long r4 = 0;
        if (r1 != 0) goto L7;
    L19:
        long r6 = r11.readUE("num_negative_pics") + r11.readUE("num_positive_pics");
        r9[r1] = r6;
    L21:
        if (r4 >= r6) goto L23;
        r11.readUE("delta_poc_s0/1_minus1");
        r11.readBool("used_by_curr_pic_s0/1_flag");
        r4 = r4 + 1;
    L23:
        r1 = r1 + 1;
        goto L3
    L7:
        if (r11.readBool() == false) goto L19;
        r11.readBool("delta_rps_sign");
        r11.readUE("abs_delta_rps_minus1");
        r9[r1] = 0;
        int r42 = 0;
    L10:
        if (r42 > r9[r1 - 1]) goto L23;
        boolean r5 = r11.readBool();
        if (r5 == true) goto L14;
        boolean r62 = r11.readBool();
    L15:
        if (r5 == true) goto L17;
        if (r62 == true) goto L17;
    L18:
        r42 = r42 + 1;
    L17:
        r9[r1] = r9[r1] + 1;
        goto L18
    L14:
        r62 = false;
        goto L15
    }

    private void profile_tier_level(int r20, CAVLCReader r21) {
        int r1 = r20;
        this.general_profile_space = r21.readU(2, "general_profile_space");
        this.general_tier_flag = r21.readBool("general_tier_flag");
        this.general_profile_idc = r21.readU(5, "general_profile_idc");
        this.general_profile_compatibility_flags = r21.readNBit(32);
        this.general_constraint_indicator_flags = r21.readNBit(48);
        this.general_level_idc = (byte) r21.readByte();
        boolean[] r0 = new boolean[r1];
        boolean[] r6 = new boolean[r1];
        int r8 = 0;
    L4:
        if (r8 >= r1) goto L7;
        r0[r8] = r21.readBool("sub_layer_profile_present_flag[" + r8 + "]");
        r6[r8] = r21.readBool("sub_layer_level_present_flag[" + r8 + "]");
        r8 = r8 + 1;
        goto L4
    L7:
        if (r1 <= 0) goto L11;
        int[] r10 = new int[8];
        int r11 = r1;
    L9:
        if (r11 >= 8) goto L11;
        r10[r11] = r21.readU(2, "reserved_zero_2bits[" + r11 + "]");
        r11 = r11 + 1;
    L11:
        int[] r102 = new int[r1];
        boolean[] r112 = new boolean[r1];
        int[] r12 = new int[r1];
        boolean[][] r13 = (boolean[][]) Array.newInstance(Boolean.TYPE, new int[]{r1, 32});
        boolean[] r14 = new boolean[r1];
        boolean[] r15 = new boolean[r1];
        boolean[] r7 = new boolean[r1];
        boolean[] r82 = new boolean[r1];
        long[] r3 = new long[r1];
        int[] r5 = new int[r1];
        int r4 = 0;
    L12:
        if (r4 >= r1) goto L26;
        if (r0[r4] == false) goto L20;
        boolean[] r17 = r0;
        r102[r4] = r21.readU(2, "sub_layer_profile_space[" + r4 + "]");
        r112[r4] = r21.readBool("sub_layer_tier_flag[" + r4 + "]");
        r12[r4] = r21.readU(5, "sub_layer_profile_idc[" + r4 + "]");
        int r02 = 0;
    L17:
        if (r02 >= 32) goto L19;
        r13[r4][r02] = r21.readBool("sub_layer_profile_compatibility_flag[" + r4 + "][" + r02 + "]");
        r02 = r02 + 1;
        r3 = r3;
        goto L17
    L19:
        long[] r18 = r3;
        r14[r4] = r21.readBool("sub_layer_progressive_source_flag[" + r4 + "]");
        r15[r4] = r21.readBool("sub_layer_interlaced_source_flag[" + r4 + "]");
        r7[r4] = r21.readBool("sub_layer_non_packed_constraint_flag[" + r4 + "]");
        r82[r4] = r21.readBool("sub_layer_frame_only_constraint_flag[" + r4 + "]");
        r18[r4] = r21.readNBit(44);
    L22:
        if (r6[r4] == false) goto L25;
        r5[r4] = r21.readU(8, "sub_layer_level_idc[" + r4 + "]");
    L25:
        r4 = r4 + 1;
        r1 = r20;
        r0 = r17;
        r3 = r18;
        goto L12
    L20:
        r17 = r0;
        r18 = r3;
        goto L22
    }

    private static void skip_scaling_list_data(CAVLCReader r7) {
        int r1 = 0;
    L4:
        if (r1 >= 4) goto L22;
        int r3 = 0;
    L7:
        if (r1 != 3) goto L9;
        int r4 = 2;
    L10:
        if (r3 >= r4) goto L21;
        if (r7.readBool() == false) goto L14;
        r7.readUE("scaling_list_pred_matrix_id_delta");
    L20:
        r3 = r3 + 1;
        goto L7
    L14:
        int r42 = Math.min(64, 1 << ((r1 << 1) + 4));
        if (r1 <= 1) goto L17;
        r7.readUE("scaling_list_dc_coef_minus8");
    L17:
        int r5 = 0;
    L18:
        if (r5 >= r42) goto L20;
        r7.readUE("scaling_list_delta_coef");
        r5 = r5 + 1;
        goto L18
    L21:
        r1 = r1 + 1;
        goto L4
    L9:
        r4 = 6;
        goto L10
    }
}
