package com.google.android.material.internal;

import android.view.View;
import android.view.ViewGroup;
import com.google.android.material.internal.MaterialCheckable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/* loaded from: classes5.dex */
public class CheckableGroup<T extends MaterialCheckable<T>> {
    private final Map<Integer, T> checkables;
    private final Set<Integer> checkedIds;
    private OnCheckedStateChangeListener onCheckedStateChangeListener;
    private boolean selectionRequired;
    private boolean singleSelection;

    /* renamed from: com.google.android.material.internal.CheckableGroup$1, reason: invalid class name */
    public class AnonymousClass1 implements MaterialCheckable.OnCheckedChangeListener<T> {
        final /* synthetic */ CheckableGroup this$0;

        public AnonymousClass1(CheckableGroup r1) {
            this.this$0 = r1;
        }

        public void onCheckedChanged(T r2, boolean r3) {
            CheckableGroup r0 = this.this$0;
            if (r3 == false) goto L8;
            if (CheckableGroup.access$000(r0, r2) == false) goto L11;
        L9:
            CheckableGroup.access$300(this.this$0);
            return;
        L11:
            return;
        L8:
            if (CheckableGroup.access$200(r0, r2, CheckableGroup.access$100(r0)) == true) goto L9;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // com.google.android.material.internal.MaterialCheckable.OnCheckedChangeListener
        public /* bridge */ /* synthetic */ void onCheckedChanged(Object r1, boolean r2) {
            onCheckedChanged((MaterialCheckable) r1, r2);
        }
    }

    public interface OnCheckedStateChangeListener {
        void onCheckedStateChanged(Set<Integer> r1);
    }

    public CheckableGroup() {
        this.checkables = new HashMap();
        this.checkedIds = new HashSet();
    }

    public static /* synthetic */ boolean access$000(CheckableGroup r0, MaterialCheckable r1) {
        return r0.checkInternal(r1);
    }

    public static /* synthetic */ boolean access$100(CheckableGroup r0) {
        return r0.selectionRequired;
    }

    public static /* synthetic */ boolean access$200(CheckableGroup r0, MaterialCheckable r1, boolean r2) {
        return r0.uncheckInternal(r1, r2);
    }

    public static /* synthetic */ void access$300(CheckableGroup r0) {
        r0.onCheckedStateChanged();
    }

    private boolean checkInternal(MaterialCheckable<T> r5) {
        int r0 = r5.getId();
        if (this.checkedIds.contains(Integer.valueOf(r0)) == false) goto L5;
        return false;
    L5:
        T r1 = this.checkables.get(Integer.valueOf(getSingleCheckedId()));
        if (r1 == null) goto L8;
        uncheckInternal(r1, false);
    L8:
        boolean r4 = this.checkedIds.add(Integer.valueOf(r0));
        if (r5.isChecked() == true) goto L11;
        r5.setChecked(true);
    L11:
        return r4;
    }

    private void onCheckedStateChanged() {
        OnCheckedStateChangeListener r0 = this.onCheckedStateChangeListener;
        if (r0 == null) goto L6;
        r0.onCheckedStateChanged(getCheckedIds());
        return;
    }

    private boolean uncheckInternal(MaterialCheckable<T> r5, boolean r6) {
        int r0 = r5.getId();
        if (this.checkedIds.contains(Integer.valueOf(r0)) == true) goto L5;
        return false;
    L5:
        if (r6 == true) goto L7;
    L12:
        boolean r4 = this.checkedIds.remove(Integer.valueOf(r0));
        if (r5.isChecked() == false) goto L15;
        r5.setChecked(false);
    L15:
        return r4;
    L7:
        if (this.checkedIds.size() != 1) goto L12;
        if (this.checkedIds.contains(Integer.valueOf(r0)) == false) goto L12;
        r5.setChecked(true);
        return false;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void addCheckable(T r3) {
        this.checkables.put(Integer.valueOf(r3.getId()), r3);
        if (r3.isChecked() == false) goto L5;
        checkInternal(r3);
    L5:
        r3.setInternalOnCheckedChangeListener(new AnonymousClass1(this));
    }

    public void check(int r2) {
        T r22 = this.checkables.get(Integer.valueOf(r2));
        if (r22 != null) goto L6;
        return;
    L6:
        if (checkInternal(r22) == false) goto L9;
        onCheckedStateChanged();
        return;
    }

    public void clearCheck() {
        boolean r0 = this.checkedIds.isEmpty();
        Iterator<T> r1 = this.checkables.values().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        uncheckInternal(r1.next(), false);
        goto L4
    L6:
        if (r0 == true) goto L10;
        onCheckedStateChanged();
        return;
    }

    public Set<Integer> getCheckedIds() {
        return new HashSet(this.checkedIds);
    }

    public List<Integer> getCheckedIdsSortedByChildOrder(ViewGroup r5) {
        Set<Integer> r4 = getCheckedIds();
        ArrayList r0 = new ArrayList();
        int r1 = 0;
    L4:
        if (r1 >= r5.getChildCount()) goto L11;
        View r2 = r5.getChildAt(r1);
        if ((r2 instanceof MaterialCheckable) == false) goto L10;
        if (r4.contains(Integer.valueOf(r2.getId())) == false) goto L10;
        r0.add(Integer.valueOf(r2.getId()));
    L10:
        r1 = r1 + 1;
        goto L4
    L11:
        return r0;
    }

    public int getSingleCheckedId() {
        if (this.singleSelection == true) goto L5;
        return -1;
    L5:
        if (this.checkedIds.isEmpty() == false) goto L7;
        return -1;
    L7:
        return this.checkedIds.iterator().next().intValue();
    }

    public boolean isSelectionRequired() {
        return this.selectionRequired;
    }

    public boolean isSingleSelection() {
        return this.singleSelection;
    }

    public void removeCheckable(T r3) {
        r3.setInternalOnCheckedChangeListener(null);
        this.checkables.remove(Integer.valueOf(r3.getId()));
        this.checkedIds.remove(Integer.valueOf(r3.getId()));
    }

    public void setOnCheckedStateChangeListener(OnCheckedStateChangeListener r1) {
        this.onCheckedStateChangeListener = r1;
    }

    public void setSelectionRequired(boolean r1) {
        this.selectionRequired = r1;
    }

    public void setSingleSelection(boolean r2) {
        if (this.singleSelection == r2) goto L6;
        this.singleSelection = r2;
        clearCheck();
        return;
    }

    public void uncheck(int r2) {
        T r22 = this.checkables.get(Integer.valueOf(r2));
        if (r22 != null) goto L6;
        return;
    L6:
        if (uncheckInternal(r22, this.selectionRequired) == false) goto L9;
        onCheckedStateChanged();
        return;
    }
}
