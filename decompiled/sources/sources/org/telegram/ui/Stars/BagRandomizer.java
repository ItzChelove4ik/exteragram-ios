package org.telegram.ui.Stars;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/* loaded from: classes7.dex */
public class BagRandomizer<T> {
    private final List<T> bag;
    private int currentIndex;
    private T next;
    private final Random random;
    private boolean reshuffleIfEnd;
    private final List<T> shuffledBag;

    public BagRandomizer(List<T> r2) {
        this.reshuffleIfEnd = true;
        if (r2 != null) goto L5;
        r2 = new ArrayList();
    L5:
        ArrayList r0 = new ArrayList(r2);
        this.bag = r0;
        this.shuffledBag = new ArrayList(r0);
        this.currentIndex = 0;
        this.random = new Random();
        reshuffle();
        next();
    }

    private void reshuffle() {
        Collections.shuffle(this.shuffledBag, this.random);
        this.currentIndex = 0;
    }

    public T getNext() {
        return this.next;
    }

    public T next() {
        if (this.bag.isEmpty() == false) goto L6;
        return null;
    L6:
        T r0 = this.next;
        if (this.currentIndex >= this.shuffledBag.size()) goto L9;
    L12:
        List<T> r1 = this.shuffledBag;
        int r2 = this.currentIndex;
        this.currentIndex = r2 + 1;
        this.next = r1.get(r2);
        return r0;
    L9:
        if (this.reshuffleIfEnd == false) goto L11;
        reshuffle();
        goto L12
    L11:
        this.currentIndex = 0;
        goto L12
    }

    public void reset() {
        this.currentIndex = 0;
        next();
    }

    public void setReshuffleIfEnd(boolean r1) {
        this.reshuffleIfEnd = r1;
    }
}
