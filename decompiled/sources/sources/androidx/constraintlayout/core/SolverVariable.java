package androidx.constraintlayout.core;

import com.google.android.gms.cast.MediaError;
import java.util.Arrays;
import java.util.HashSet;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes3.dex */
public class SolverVariable implements Comparable<SolverVariable> {
    private static int sUniqueErrorId = 1;
    public float computedValue;
    public int id;
    public boolean inGoal;
    public boolean isFinalValue;
    ArrayRow[] mClientEquations;
    int mClientEquationsCount;
    int mDefinitionId;
    float[] mGoalStrengthVector;
    HashSet<ArrayRow> mInRows;
    boolean mIsSynonym;
    private String mName;
    float[] mStrengthVector;
    int mSynonym;
    float mSynonymDelta;
    Type mType;
    public int strength;
    public int usageInRowCount;

    public enum Type extends Enum<Type> {
        private static final /* synthetic */ Type[] $VALUES = null;
        public static final Type CONSTANT = null;
        public static final Type ERROR = null;
        public static final Type SLACK = null;
        public static final Type UNKNOWN = null;
        public static final Type UNRESTRICTED = null;

        private static /* synthetic */ Type[] $values() {
            return new Type[]{UNRESTRICTED, CONSTANT, SLACK, ERROR, UNKNOWN};
        }

        static {
            UNRESTRICTED = new Type("UNRESTRICTED", 0);
            CONSTANT = new Type("CONSTANT", 1);
            SLACK = new Type("SLACK", 2);
            ERROR = new Type(MediaError.ERROR_TYPE_ERROR, 3);
            UNKNOWN = new Type("UNKNOWN", 4);
            $VALUES = $values();
        }

        Type(String r1, int r2) {
        }

        public static Type valueOf(String r1) {
            return (Type) Enum.valueOf(Type.class, r1);
        }

        public static Type[] values() {
            return (Type[]) $VALUES.clone();
        }
    }

    static {
    }

    public SolverVariable(Type r4, String r5) {
        this.id = -1;
        this.mDefinitionId = -1;
        this.strength = 0;
        this.isFinalValue = false;
        this.mStrengthVector = new float[9];
        this.mGoalStrengthVector = new float[9];
        this.mClientEquations = new ArrayRow[16];
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
        this.mIsSynonym = false;
        this.mSynonym = -1;
        this.mSynonymDelta = 0.0f;
        this.mInRows = null;
        this.mType = r4;
    }

    public static void increaseErrorId() {
        sUniqueErrorId++;
    }

    public final void addToRow(ArrayRow r4) {
        int r0 = 0;
    L3:
        int r1 = this.mClientEquationsCount;
        ArrayRow[] r2 = this.mClientEquations;
        if (r0 >= r1) goto L10;
        if (r2[r0] == r4) goto L7;
        r0 = r0 + 1;
        goto L3
    L7:
        return;
    L10:
        if (r1 < r2.length) goto L12;
        this.mClientEquations = (ArrayRow[]) Arrays.copyOf(r2, r2.length * 2);
    L12:
        ArrayRow[] r02 = this.mClientEquations;
        int r12 = this.mClientEquationsCount;
        r02[r12] = r4;
        this.mClientEquationsCount = r12 + 1;
    }

    /* renamed from: compareTo, reason: avoid collision after fix types in other method */
    public int compareTo2(SolverVariable r1) {
        return this.id - r1.id;
    }

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(SolverVariable r1) {
        return compareTo2(r1);
    }

    public final void removeFromRow(ArrayRow r5) {
        int r0 = this.mClientEquationsCount;
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L12;
        if (this.mClientEquations[r1] == r5) goto L7;
        r1 = r1 + 1;
    L7:
        if (r1 >= (r0 - 1)) goto L9;
        ArrayRow[] r52 = this.mClientEquations;
        int r2 = r1 + 1;
        r52[r1] = r52[r2];
        r1 = r2;
        goto L7
    L9:
        this.mClientEquationsCount--;
        return;
    }

    public void reset() {
        this.mName = null;
        this.mType = Type.UNKNOWN;
        this.strength = 0;
        this.id = -1;
        this.mDefinitionId = -1;
        this.computedValue = 0.0f;
        this.isFinalValue = false;
        this.mIsSynonym = false;
        this.mSynonym = -1;
        this.mSynonymDelta = 0.0f;
        int r2 = this.mClientEquationsCount;
        int r4 = 0;
    L3:
        if (r4 >= r2) goto L5;
        this.mClientEquations[r4] = null;
        r4 = r4 + 1;
        goto L3
    L5:
        this.mClientEquationsCount = 0;
        this.usageInRowCount = 0;
        this.inGoal = false;
        Arrays.fill(this.mGoalStrengthVector, 0.0f);
    }

    public void setFinalValue(LinearSystem r4, float r5) {
        this.computedValue = r5;
        this.isFinalValue = true;
        this.mIsSynonym = false;
        this.mSynonym = -1;
        this.mSynonymDelta = 0.0f;
        int r1 = this.mClientEquationsCount;
        this.mDefinitionId = -1;
        int r0 = 0;
    L3:
        if (r0 >= r1) goto L5;
        this.mClientEquations[r0].updateFromFinalVariable(r4, this, false);
        r0 = r0 + 1;
        goto L3
    L5:
        this.mClientEquationsCount = 0;
    }

    public void setType(Type r1, String r2) {
        this.mType = r1;
    }

    public String toString() {
        if (this.mName == null) goto L7;
        return _UrlKt.FRAGMENT_ENCODE_SET + this.mName;
    L7:
        return _UrlKt.FRAGMENT_ENCODE_SET + this.id;
    }

    public final void updateReferencesWithNewDefinition(LinearSystem r5, ArrayRow r6) {
        int r0 = this.mClientEquationsCount;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L5;
        this.mClientEquations[r2].updateFromRow(r5, r6, false);
        r2 = r2 + 1;
        goto L3
    L5:
        this.mClientEquationsCount = 0;
    }
}
