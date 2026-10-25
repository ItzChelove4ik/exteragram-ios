package com.google.android.material.tabs;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import java.lang.ref.WeakReference;

/* loaded from: classes5.dex */
public final class TabLayoutMediator {
    private RecyclerView.Adapter<?> adapter;
    private boolean attached;
    private final boolean autoRefresh;
    private TabLayoutOnPageChangeCallback onPageChangeCallback;
    private TabLayout.OnTabSelectedListener onTabSelectedListener;
    private RecyclerView.AdapterDataObserver pagerAdapterObserver;
    private final boolean smoothScroll;
    private final TabConfigurationStrategy tabConfigurationStrategy;
    private final TabLayout tabLayout;
    private final ViewPager2 viewPager;

    public class PagerAdapterObserver extends RecyclerView.AdapterDataObserver {
        final /* synthetic */ TabLayoutMediator this$0;

        public PagerAdapterObserver(TabLayoutMediator r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onChanged() {
            this.this$0.populateTabsFromPagerAdapter();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int r1, int r2) {
            this.this$0.populateTabsFromPagerAdapter();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeChanged(int r1, int r2, Object r3) {
            this.this$0.populateTabsFromPagerAdapter();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeInserted(int r1, int r2) {
            this.this$0.populateTabsFromPagerAdapter();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeMoved(int r1, int r2, int r3) {
            this.this$0.populateTabsFromPagerAdapter();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.AdapterDataObserver
        public void onItemRangeRemoved(int r1, int r2) {
            this.this$0.populateTabsFromPagerAdapter();
        }
    }

    public interface TabConfigurationStrategy {
        void onConfigureTab(TabLayout.Tab r1, int r2);
    }

    public static class TabLayoutOnPageChangeCallback extends ViewPager2.OnPageChangeCallback {
        private int previousScrollState;
        private int scrollState;
        private final WeakReference<TabLayout> tabLayoutRef;

        public TabLayoutOnPageChangeCallback(TabLayout r2) {
            this.tabLayoutRef = new WeakReference(r2);
            reset();
        }

        public void onPageScrollStateChanged(int r2) {
            this.previousScrollState = this.scrollState;
            this.scrollState = r2;
            TabLayout r22 = this.tabLayoutRef.get();
            if (r22 == null) goto L6;
            r22.updateViewPagerScrollState(this.scrollState);
            return;
        }

        public void onPageScrolled(int r7, float r8, int r9) {
            TabLayout r0 = this.tabLayoutRef.get();
            if (r0 == null) goto L18;
            int r92 = this.scrollState;
            boolean r3 = true;
            if (r92 == 2) goto L7;
        L10:
            boolean r4 = true;
        L11:
            if (r92 == 2) goto L13;
        L16:
            r0.setScrollPosition(r7, r8, r3, r4, false);
            return;
        L13:
            if (this.previousScrollState != 0) goto L16;
            r4 = false;
            goto L16
        L7:
            if (this.previousScrollState == 1) goto L10;
            r4 = true;
            r3 = false;
            goto L11
        }

        public void onPageSelected(int r4) {
            TabLayout r0 = this.tabLayoutRef.get();
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.getSelectedTabPosition() != r4) goto L7;
            return;
        L7:
            if (r4 >= r0.getTabCount()) goto L21;
            int r1 = this.scrollState;
            if (r1 != 0) goto L11;
        L16:
            boolean r3 = true;
        L17:
            r0.selectTab(r0.getTabAt(r4), r3);
            return;
        L11:
            if (r1 == 2) goto L13;
        L15:
            r3 = false;
            goto L17
        L13:
            if (this.previousScrollState != 0) goto L15;
        }

        public void reset() {
            this.scrollState = 0;
            this.previousScrollState = 0;
        }
    }

    public static class ViewPagerOnTabSelectedListener implements TabLayout.OnTabSelectedListener {
        private final boolean smoothScroll;
        private final ViewPager2 viewPager;

        public ViewPagerOnTabSelectedListener(ViewPager2 r1, boolean r2) {
            this.smoothScroll = r2;
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab r1) {
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab r1) {
            r1.getPosition();
            throw null;
        }

        @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab r1) {
        }
    }

    public TabLayoutMediator(TabLayout r2, ViewPager2 r3, TabConfigurationStrategy r4) {
        this(r2, r3, true, r4);
    }

    public TabLayoutMediator(TabLayout r7, ViewPager2 r8, boolean r9, TabConfigurationStrategy r10) {
        this(r7, r8, r9, true, r10);
    }

    public TabLayoutMediator(TabLayout r1, ViewPager2 r2, boolean r3, boolean r4, TabConfigurationStrategy r5) {
        this.tabLayout = r1;
        this.autoRefresh = r3;
        this.smoothScroll = r4;
        this.tabConfigurationStrategy = r5;
    }

    public void attach() {
        if (this.attached == false) goto L7;
        throw new IllegalStateException("TabLayoutMediator is already attached");
    L7:
        throw null;
    }

    public void detach() {
        if (this.attached == true) goto L6;
        return;
    L6:
        if (this.autoRefresh == false) goto L10;
        RecyclerView.Adapter<?> r0 = this.adapter;
        if (r0 == null) goto L10;
        r0.unregisterAdapterDataObserver(this.pagerAdapterObserver);
        this.pagerAdapterObserver = null;
    L10:
        this.tabLayout.removeOnTabSelectedListener(this.onTabSelectedListener);
        throw null;
    }

    public boolean isAttached() {
        return this.attached;
    }

    public void populateTabsFromPagerAdapter() {
        this.tabLayout.removeAllTabs();
        RecyclerView.Adapter<?> r0 = this.adapter;
        if (r0 == null) goto L11;
        int r02 = r0.getItemCount();
        int r2 = 0;
    L5:
        if (r2 >= r02) goto L7;
        TabLayout.Tab r3 = this.tabLayout.newTab();
        this.tabConfigurationStrategy.onConfigureTab(r3, r2);
        this.tabLayout.addTab(r3, false);
        r2 = r2 + 1;
        goto L5
    L7:
        if (r02 <= 0) goto L13;
        this.tabLayout.getTabCount();
        throw null;
    L13:
        return;
    }
}
