package org.telegram.messenger.chromecast;

import java.util.ArrayList;

/* loaded from: classes5.dex */
public class ChromecastMediaVariations {
    private final ArrayList<ChromecastMedia> variations;

    public static class Builder {
        private final ArrayList<ChromecastMedia> variations;

        public Builder() {
            this.variations = new ArrayList();
        }

        public Builder add(ChromecastMedia r2) {
            this.variations.add(r2);
            return this;
        }

        public ChromecastMediaVariations build() {
            return new ChromecastMediaVariations(this.variations, null);
        }
    }

    private ChromecastMediaVariations(ArrayList<ChromecastMedia> r1) {
        this.variations = r1;
    }

    public /* synthetic */ ChromecastMediaVariations(ArrayList r1, ChromecastMediaVariationsIA r2) {
        this(r1);
    }

    private ChromecastMediaVariations(ChromecastMedia r3) {
        ArrayList<ChromecastMedia> r0 = new ArrayList(1);
        this.variations = r0;
        r0.add(r3);
    }

    public static ChromecastMediaVariations of(ChromecastMedia r1) {
        return new ChromecastMediaVariations(r1);
    }

    public ChromecastMedia getVariation(int r1) {
        return this.variations.get(r1);
    }

    public int getVariationsCount() {
        return this.variations.size();
    }
}
