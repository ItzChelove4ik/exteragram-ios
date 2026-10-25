package androidx.car.app.navigation.model;

import androidx.car.app.model.CarIcon;
import androidx.car.app.model.constraints.CarIconConstraints;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class Maneuver {
    public static final int TYPE_DEPART = 1;
    public static final int TYPE_DESTINATION = 39;
    public static final int TYPE_DESTINATION_LEFT = 41;
    public static final int TYPE_DESTINATION_RIGHT = 42;
    public static final int TYPE_DESTINATION_STRAIGHT = 40;
    public static final int TYPE_FERRY_BOAT = 37;
    public static final int TYPE_FERRY_BOAT_LEFT = 47;
    public static final int TYPE_FERRY_BOAT_RIGHT = 48;
    public static final int TYPE_FERRY_TRAIN = 38;
    public static final int TYPE_FERRY_TRAIN_LEFT = 49;
    public static final int TYPE_FERRY_TRAIN_RIGHT = 50;
    public static final int TYPE_FORK_LEFT = 25;
    public static final int TYPE_FORK_RIGHT = 26;
    public static final int TYPE_KEEP_LEFT = 3;
    public static final int TYPE_KEEP_RIGHT = 4;
    public static final int TYPE_MERGE_LEFT = 27;
    public static final int TYPE_MERGE_RIGHT = 28;
    public static final int TYPE_MERGE_SIDE_UNSPECIFIED = 29;
    public static final int TYPE_NAME_CHANGE = 2;
    public static final int TYPE_OFF_RAMP_NORMAL_LEFT = 23;
    public static final int TYPE_OFF_RAMP_NORMAL_RIGHT = 24;
    public static final int TYPE_OFF_RAMP_SLIGHT_LEFT = 21;
    public static final int TYPE_OFF_RAMP_SLIGHT_RIGHT = 22;
    public static final int TYPE_ON_RAMP_NORMAL_LEFT = 15;
    public static final int TYPE_ON_RAMP_NORMAL_RIGHT = 16;
    public static final int TYPE_ON_RAMP_SHARP_LEFT = 17;
    public static final int TYPE_ON_RAMP_SHARP_RIGHT = 18;
    public static final int TYPE_ON_RAMP_SLIGHT_LEFT = 13;
    public static final int TYPE_ON_RAMP_SLIGHT_RIGHT = 14;
    public static final int TYPE_ON_RAMP_U_TURN_LEFT = 19;
    public static final int TYPE_ON_RAMP_U_TURN_RIGHT = 20;
    public static final int TYPE_ROUNDABOUT_ENTER_AND_EXIT_CCW = 34;
    public static final int TYPE_ROUNDABOUT_ENTER_AND_EXIT_CCW_WITH_ANGLE = 35;
    public static final int TYPE_ROUNDABOUT_ENTER_AND_EXIT_CW = 32;
    public static final int TYPE_ROUNDABOUT_ENTER_AND_EXIT_CW_WITH_ANGLE = 33;
    public static final int TYPE_ROUNDABOUT_ENTER_CCW = 45;
    public static final int TYPE_ROUNDABOUT_ENTER_CW = 43;
    public static final int TYPE_ROUNDABOUT_EXIT_CCW = 46;
    public static final int TYPE_ROUNDABOUT_EXIT_CW = 44;
    public static final int TYPE_STRAIGHT = 36;
    public static final int TYPE_TURN_NORMAL_LEFT = 7;
    public static final int TYPE_TURN_NORMAL_RIGHT = 8;
    public static final int TYPE_TURN_SHARP_LEFT = 9;
    public static final int TYPE_TURN_SHARP_RIGHT = 10;
    public static final int TYPE_TURN_SLIGHT_LEFT = 5;
    public static final int TYPE_TURN_SLIGHT_RIGHT = 6;
    public static final int TYPE_UNKNOWN = 0;
    public static final int TYPE_U_TURN_LEFT = 11;
    public static final int TYPE_U_TURN_RIGHT = 12;
    private final CarIcon mIcon;
    private final int mRoundaboutExitAngle;
    private final int mRoundaboutExitNumber;
    private final int mType;

    private Maneuver() {
        this.mType = 0;
        this.mRoundaboutExitNumber = 0;
        this.mRoundaboutExitAngle = 0;
        this.mIcon = null;
    }

    public Maneuver(int r1, int r2, int r3, CarIcon r4) {
        this.mType = r1;
        this.mRoundaboutExitNumber = r2;
        this.mRoundaboutExitAngle = r3;
        CarIconConstraints.DEFAULT.validateOrThrow(r4);
        this.mIcon = r4;
    }

    public static boolean isExitNumberRequired(int r1) {
        if (r1 != 32) goto L5;
        return true;
    L5:
        if (r1 == 34) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isValidType(int r1) {
        if (r1 >= 0) goto L4;
        return false;
    L4:
        if (r1 > 50) goto L9;
        return true;
    L9:
        return false;
    }

    public static boolean isValidTypeWithExitAngle(int r1) {
        if (r1 != 33) goto L5;
        return true;
    L5:
        if (r1 == 35) goto L11;
        return false;
    L11:
        return true;
    }

    public static boolean isValidTypeWithExitNumber(int r1) {
        if (r1 != 32) goto L5;
        return true;
    L5:
        if (r1 != 34) goto L7;
        return true;
    L7:
        if (r1 != 33) goto L9;
        return true;
    L9:
        if (r1 == 35) goto L17;
        return false;
    L17:
        return true;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof Maneuver) == true) goto L8;
        return false;
    L8:
        Maneuver r52 = (Maneuver) r5;
        if (this.mType == r52.mType) goto L11;
    L17:
        return false;
    L11:
        if (this.mRoundaboutExitNumber != r52.mRoundaboutExitNumber) goto L17;
        if (this.mRoundaboutExitAngle != r52.mRoundaboutExitAngle) goto L17;
        if (Objects.equals(this.mIcon, r52.mIcon) == false) goto L17;
        return true;
    }

    public CarIcon getIcon() {
        return this.mIcon;
    }

    public int getRoundaboutExitAngle() {
        return this.mRoundaboutExitAngle;
    }

    public int getRoundaboutExitNumber() {
        return this.mRoundaboutExitNumber;
    }

    public int getType() {
        return this.mType;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Integer.valueOf(this.mType), Integer.valueOf(this.mRoundaboutExitNumber), Integer.valueOf(this.mRoundaboutExitAngle), this.mIcon});
    }

    public String toString() {
        return "[type: " + this.mType + ", exit #: " + this.mRoundaboutExitNumber + ", exit angle: " + this.mRoundaboutExitAngle + ", icon: " + this.mIcon + "]";
    }
}
