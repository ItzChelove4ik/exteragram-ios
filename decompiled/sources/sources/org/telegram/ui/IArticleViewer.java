package org.telegram.ui;

import android.view.MotionEvent;
import android.view.View;
import androidx.collection.LongSparseArray;
import java.util.ArrayList;
import org.telegram.messenger.video.VideoPlayerHolderBase;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ArticleViewer;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.LoadingDrawable;
import org.telegram.ui.Components.TextPaintUrlSpan;

/* loaded from: classes3.dex */
public abstract class IArticleViewer {
    public ArticleViewer.BlockVideoCell currentPlayer;
    public int currentSearchIndex;
    public boolean drawBlockSelection;
    public BottomSheet linkSheet;
    public LinkSpanDrawable.LinkCollector links;
    public TLRPC.Chat loadedChannel;
    public boolean loadingChannel;
    public TextPaintUrlSpan loadingLink;
    public LoadingDrawable loadingLinkDrawable;
    public View loadingLinkView;
    public ArticleViewer.DrawingText loadingText;
    public ActionBarPopupWindow popupWindow;
    public int pressedLayoutY;
    public LinkSpanDrawable<TextPaintUrlSpan> pressedLink;
    public ArticleViewer.DrawingText pressedLinkOwnerLayout;
    public View pressedLinkOwnerView;
    public ArrayList<ArticleViewer.SearchResult> searchResults;
    public String searchText;
    public int selectedFont;
    public VideoPlayerHolderBase videoPlayer;
    public LongSparseArray<ArticleViewer.BlockVideoCellState> videoStates;

    public IArticleViewer() {
        this.selectedFont = 0;
        this.links = new LinkSpanDrawable.LinkCollector();
        this.videoStates = new LongSparseArray();
        this.searchResults = new ArrayList();
    }

    public abstract boolean allowTouches();

    public boolean canStartSelection(View r1) {
        return false;
    }

    public abstract void checkLayoutForLinks(MotionEvent r1, View r2);

    public abstract ArticleViewer.WebpageAdapter getAdapter();

    public abstract int getCurrentAccount();

    public abstract int getGrayTextColor();

    public abstract int getLinkTextColor();

    public abstract ArticleViewer.Resources getResources();

    public abstract Theme.ResourcesProvider getResourcesProvider();

    public abstract int getTextColor();

    public abstract TextSelectionHelper.ArticleTextSelectionHelper getTextSelectionHelper(View r1);

    public abstract int getThemedColor(int r1);

    public abstract void handleLinkClick(ArticleViewer.WebpageAdapter r1, TextPaintUrlSpan r2);

    public abstract boolean openPhoto(TL_iv.PageBlock r1, ArticleViewer.WebpageAdapter r2);

    public int padx() {
        return 18;
    }

    public int pady() {
        return 8;
    }
}
