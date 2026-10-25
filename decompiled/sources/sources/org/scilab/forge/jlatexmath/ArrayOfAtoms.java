package org.scilab.forge.jlatexmath;

import java.util.Iterator;
import java.util.LinkedList;

/* loaded from: classes5.dex */
public class ArrayOfAtoms extends TeXFormula {
    public LinkedList<LinkedList<Atom>> array;
    public int col;
    public int row;

    public ArrayOfAtoms() {
        LinkedList<LinkedList<Atom>> r0 = new LinkedList();
        this.array = r0;
        r0.add(new LinkedList());
        this.row = 0;
    }

    public void addCol() {
        this.array.get(this.row).add(this.root);
        this.root = null;
    }

    public void addCol(int r6) {
        if (r6 >= 1) goto L6;
        r6 = 1;
    L8:
        this.array.get(this.row).add(this.root);
        int r1 = 1;
    L10:
        if (r1 >= (r6 - 1)) goto L12;
        this.array.get(this.row).add(null);
        r1 = r1 + 1;
        goto L10
    L12:
        this.root = null;
        return;
    L6:
        if (r6 <= 4096) goto L8;
        r6 = 4096;
        goto L8
    }

    public void addRow() {
        addCol();
        this.array.add(new LinkedList());
        this.row++;
    }

    public void checkDimensions() {
        if (this.array.getLast().size() == 0) goto L6;
        addRow();
    L8:
        int r1 = 1;
        this.row = this.array.size() - 1;
        this.col = this.array.get(0).size();
    L10:
        if (r1 >= this.row) goto L15;
        if (this.array.get(r1).size() <= this.col) goto L14;
        this.col = this.array.get(r1).size();
    L14:
        r1 = r1 + 1;
        goto L10
    L15:
        int r0 = 0;
    L17:
        if (r0 >= this.row) goto L29;
        int r12 = this.array.get(r0).size();
        if (r12 == this.col) goto L28;
        if (this.array.get(r0).get(0) == null) goto L28;
        if (this.array.get(r0).get(0).type == 11) goto L28;
        LinkedList<Atom> r3 = this.array.get(r0);
    L26:
        if (r12 >= this.col) goto L28;
        r3.add(null);
        r12 = r12 + 1;
    L28:
        r0 = r0 + 1;
        goto L17
    L29:
        return;
    L6:
        if (this.root == null) goto L8;
        addRow();
        goto L8
    }

    public VRowAtom getAsVRow() {
        VRowAtom r0 = new VRowAtom();
        r0.setAddInterline(true);
        Iterator<LinkedList<Atom>> r3 = this.array.iterator();
    L4:
        if (r3.hasNext() == false) goto L9;
        Iterator<Atom> r1 = r3.next().iterator();
    L7:
        if (r1.hasNext() == false) goto L4;
        r0.append(r1.next());
        goto L7
    L9:
        return r0;
    }

    public int getCols() {
        return this.col;
    }

    public int getRows() {
        return this.row;
    }
}
