package org.telegram.ui.Components;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;

/* loaded from: classes7.dex */
public abstract class ChatActivityEnterTopView extends FrameLayout {
    private boolean editMode;
    private EditView editView;
    private View replyView;

    public static class EditView extends LinearLayout {
        private EditViewButton[] buttons;

        /* renamed from: -$$Nest$fgetbuttons, reason: not valid java name */
        public static /* bridge */ /* synthetic */ EditViewButton[] m9112$$Nest$fgetbuttons(EditView r0) {
            return r0.buttons;
        }

        public EditView(Context r1) {
            super(r1);
            this.buttons = new EditViewButton[2];
        }

        public void addButton(EditViewButton r3, LinearLayout.LayoutParams r4) {
            int r0 = getChildCount();
            if (r0 >= 2) goto L6;
            this.buttons[r0] = r3;
            addView(r3, r4);
            return;
        }

        public EditViewButton[] getButtons() {
            return this.buttons;
        }

        public void updateColors() {
            EditViewButton[] r3 = this.buttons;
            int r0 = r3.length;
            int r1 = 0;
        L3:
            if (r1 >= r0) goto L5;
            r3[r1].updateColors();
            r1 = r1 + 1;
            goto L3
        }
    }

    public static abstract class EditViewButton extends LinearLayout {
        private boolean editButton;
        private ImageView imageView;
        private Space space;
        private TextView textView;

        public EditViewButton(Context r1) {
            super(r1);
        }

        public void addImageView(ImageView r2, LinearLayout.LayoutParams r3) {
            if (this.imageView != null) goto L6;
            this.imageView = r2;
            addView(r2, r3);
            return;
        }

        public void addSpaceView(Space r2, LinearLayout.LayoutParams r3) {
            if (this.space != null) goto L6;
            this.space = r2;
            addView(r2, r3);
            return;
        }

        public void addTextView(TextView r2, LinearLayout.LayoutParams r3) {
            if (this.textView != null) goto L6;
            this.textView = r2;
            addView(r2, r3);
            return;
        }

        public ImageView getImageView() {
            return this.imageView;
        }

        public TextView getTextView() {
            return this.textView;
        }

        public boolean isEditButton() {
            return this.editButton;
        }

        public void setEditButton(boolean r1) {
            this.editButton = r1;
        }

        public void setOnlyIconMode(boolean r5) {
            TextView r0 = this.textView;
            int r1 = 0;
            if (r0 == null) goto L8;
            if (r5 == false) goto L6;
            int r3 = 8;
        L7:
            r0.setVisibility(r3);
            goto L8
        L6:
            r3 = 0;
        L8:
            Space r4 = this.space;
            if (r4 == null) goto L14;
            if (r5 == false) goto L12;
            r1 = 8;
        L12:
            r4.setVisibility(r1);
            return;
        }

        public abstract void updateColors();
    }

    public ChatActivityEnterTopView(Context r1) {
        super(r1);
    }

    public void addEditView(EditView r2, FrameLayout.LayoutParams r3) {
        if (this.editView != null) goto L6;
        this.editView = r2;
        r2.setVisibility(8);
        addView(r2, r3);
        return;
    }

    public void addReplyView(View r2, FrameLayout.LayoutParams r3) {
        if (this.replyView != null) goto L6;
        this.replyView = r2;
        addView(r2, r3);
        return;
    }

    public EditView getEditView() {
        return this.editView;
    }

    public View getReplyView() {
        return this.replyView;
    }

    public boolean isEditMode() {
        return this.editMode;
    }

    public void setEditMode(boolean r5) {
        this.editMode = r5;
        View r0 = this.replyView;
        int r1 = 0;
        if (r5 == false) goto L5;
        int r3 = 8;
    L6:
        r0.setVisibility(r3);
        EditView r4 = this.editView;
        if (r5 == true) goto L10;
        r1 = 8;
    L10:
        r4.setVisibility(r1);
        return;
    L5:
        r3 = 0;
        goto L6
    }

    public void setEditSuggestionMode(boolean r3) {
        setEditMode(r3);
        if (r3 == false) goto L5;
        this.replyView.setVisibility(0);
    L5:
        EditView.m9112$$Nest$fgetbuttons(this.editView)[0].setOnlyIconMode(r3);
        EditView.m9112$$Nest$fgetbuttons(this.editView)[1].setOnlyIconMode(r3);
    }
}
