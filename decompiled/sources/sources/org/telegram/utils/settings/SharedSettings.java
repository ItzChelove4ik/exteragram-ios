package org.telegram.utils.settings;

import kotlin.time.DurationKt;
import org.telegram.utils.camera.roundvideo.RoundVideoSession;

/* loaded from: classes6.dex */
public abstract class SharedSettings {
    public static final BooleanSetting experimentalSettingsAllowed = null;
    public static final BooleanSetting roundVideoCamera2Enabled = null;
    public static final EnumSetting<RoundVideoSession.CameraResolution> roundVideoCameraResolution = null;
    public static final BooleanSetting roundVideoComposition = null;
    public static final EnumSetting<RoundVideoSession.FrameRate> roundVideoFrameRate = null;
    public static final EnumSetting<RoundVideoSession.CameraFacing> roundVideoLastCamera = null;
    public static final EnumSetting<RoundVideoSession.OutputResolution> roundVideoOutputResolution = null;
    public static final IntSetting roundVideoVideoBitrate = null;

    static {
        experimentalSettingsAllowed = BooleanSetting.of("experimental_settings_allowed", false);
        roundVideoCamera2Enabled = BooleanSetting.of("round_video_camera2_enabled", false);
        roundVideoOutputResolution = EnumSetting.of("round_video_output_resolution", RoundVideoSession.OutputResolution.P480);
        roundVideoCameraResolution = EnumSetting.of("round_video_camera_resolution", RoundVideoSession.CameraResolution.HIGH);
        roundVideoFrameRate = EnumSetting.of("round_video_frame_rate", RoundVideoSession.FrameRate.FPS_30);
        roundVideoVideoBitrate = IntSetting.of("round_video_video_bitrate", DurationKt.NANOS_IN_MILLIS);
        roundVideoComposition = BooleanSetting.of("round_video_composition", true);
        roundVideoLastCamera = EnumSetting.of("round_video_last_camera", RoundVideoSession.CameraFacing.FRONT);
    }
}
