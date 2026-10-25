package com.android.dx;

import com.android.dx.rop.type.StdTypeList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* loaded from: classes.dex */
final class TypeList {
    final StdTypeList ropTypes;
    final TypeId<?>[] types;

    public TypeList(TypeId<?>[] r4) {
        this.types = (TypeId[]) r4.clone();
        this.ropTypes = new StdTypeList(r4.length);
        int r0 = 0;
    L4:
        if (r0 >= r4.length) goto L6;
        this.ropTypes.set(r0, r4[r0].ropType);
        r0 = r0 + 1;
        goto L4
    }

    public List<TypeId<?>> asList() {
        return Collections.unmodifiableList(Arrays.asList(this.types));
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof TypeList) == true) goto L5;
        return false;
    L5:
        if (Arrays.equals(((TypeList) r2).types, this.types) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public int hashCode() {
        return Arrays.hashCode(this.types);
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        int r1 = 0;
    L4:
        if (r1 >= this.types.length) goto L9;
        if (r1 <= 0) goto L7;
        r0.append(", ");
    L7:
        r0.append(this.types[r1]);
        r1 = r1 + 1;
        goto L4
    L9:
        return r0.toString();
    }
}
