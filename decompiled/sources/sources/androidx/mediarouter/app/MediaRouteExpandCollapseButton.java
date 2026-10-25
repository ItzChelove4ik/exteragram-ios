package androidx.mediarouter.app;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.core.content.ContextCompat;
import androidx.mediarouter.R;

/* loaded from: classes4.dex */
class MediaRouteExpandCollapseButton extends AppCompatImageButton {
    final AnimationDrawable mCollapseAnimationDrawable;
    final String mCollapseGroupDescription;
    final AnimationDrawable mExpandAnimationDrawable;
    final String mExpandGroupDescription;
    boolean mIsGroupExpanded;
    View.OnClickListener mListener;

    /* renamed from: androidx.mediarouter.app.MediaRouteExpandCollapseButton$1, reason: invalid class name */
    public class AnonymousClass1 implements View.OnClickListener {
        final /* synthetic */ MediaRouteExpandCollapseButton this$0;

        public AnonymousClass1(MediaRouteExpandCollapseButton r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r4) {
            MediaRouteExpandCollapseButton r0 = this.this$0;
            boolean r1 = r0.mIsGroupExpanded;
            r0.mIsGroupExpanded = !r1;
            if (r1 == true) goto L5;
            r0.setImageDrawable(r0.mExpandAnimationDrawable);
            this.this$0.mExpandAnimationDrawable.start();
            MediaRouteExpandCollapseButton r02 = this.this$0;
            r02.setContentDescription(r02.mCollapseGroupDescription);
        L6:
            View.OnClickListener r3 = this.this$0.mListener;
            if (r3 == null) goto L10;
            r3.onClick(r4);
            return;
        L10:
            return;
        L5:
            r0.setImageDrawable(r0.mCollapseAnimationDrawable);
            this.this$0.mCollapseAnimationDrawable.start();
            MediaRouteExpandCollapseButton r03 = this.this$0;
            r03.setContentDescription(r03.mExpandGroupDescription);
            goto L6
        }
    }

    public MediaRouteExpandCollapseButton(Context r2) {
        this(r2, null);
    }

    public MediaRouteExpandCollapseButton(Context r2, AttributeSet r3) {
        this(r2, r3, 0);
    }

    public MediaRouteExpandCollapseButton(Context r4, AttributeSet r5, int r6) {
        super(r4, r5, r6);
        AnimationDrawable r52 = (AnimationDrawable) ContextCompat.getDrawable(r4, R.drawable.mr_group_expand);
        this.mExpandAnimationDrawable = r52;
        AnimationDrawable r0 = (AnimationDrawable) ContextCompat.getDrawable(r4, R.drawable.mr_group_collapse);
        this.mCollapseAnimationDrawable = r0;
        PorterDuffColorFilter r1 = new PorterDuffColorFilter(MediaRouterThemeHelper.getControllerColor(r4, r6), PorterDuff.Mode.SRC_IN);
        r52.setColorFilter(r1);
        r0.setColorFilter(r1);
        String r62 = r4.getString(R.string.mr_controller_expand_group);
        this.mExpandGroupDescription = r62;
        this.mCollapseGroupDescription = r4.getString(R.string.mr_controller_collapse_group);
        setImageDrawable(r52.getFrame(0));
        setContentDescription(r62);
        super.setOnClickListener(new AnonymousClass1(this));
    }

    @Override // android.view.View
    public void setOnClickListener(View.OnClickListener r1) {
        this.mListener = r1;
    }
}
