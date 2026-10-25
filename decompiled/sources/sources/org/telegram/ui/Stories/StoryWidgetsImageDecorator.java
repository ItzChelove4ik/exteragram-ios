package org.telegram.ui.Stories;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.View;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.Components.Paint.Views.LocationMarker;
import org.telegram.ui.Components.Reactions.ReactionImageHolder;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Stories.recorder.Weather;

/* loaded from: classes7.dex */
public class StoryWidgetsImageDecorator extends ImageReceiver.Decorator {
    ArrayList<DrawingObject> drawingObjects;
    float imageH;
    float imageW;
    float imageX;
    float imageY;

    public static abstract class DrawingObject {
        public DrawingObject() {
        }

        public abstract void draw(Canvas r1, ImageReceiver r2, float r3);

        public abstract void onAttachedToWindow(boolean r1);

        public abstract void setParent(View r1);
    }

    public class ReactionWidget extends DrawingObject {
        private final ReactionImageHolder imageHolder;
        private final TL_stories.TL_mediaAreaSuggestedReaction mediaArea;
        private final StoryReactionWidgetBackground storyReactionWidgetBackground;
        final /* synthetic */ StoryWidgetsImageDecorator this$0;

        public ReactionWidget(StoryWidgetsImageDecorator r3, TL_stories.TL_mediaAreaSuggestedReaction r4) {
            this.this$0 = r3;
            StoryReactionWidgetBackground r32 = new StoryReactionWidgetBackground(null);
            this.storyReactionWidgetBackground = r32;
            ReactionImageHolder r1 = new ReactionImageHolder(null);
            this.imageHolder = r1;
            this.mediaArea = r4;
            if (r4.flipped == false) goto L6;
            r32.setMirror(true, false);
        L6:
            if (r4.dark == false) goto L8;
            r32.nextStyle();
        L8:
            r1.setStatic();
            r1.setVisibleReaction(ReactionsLayoutInBubble.VisibleReaction.fromTL(r4.reaction));
        }

        @Override // org.telegram.ui.Stories.StoryWidgetsImageDecorator.DrawingObject
        public void draw(Canvas r13, ImageReceiver r14, float r15) {
            if (this.imageHolder.isLoaded() == true) goto L5;
            return;
        L5:
            StoryWidgetsImageDecorator r142 = this.this$0;
            double r0 = r142.imageX;
            float r2 = r142.imageW;
            TL_stories.MediaAreaCoordinates r5 = this.mediaArea.coordinates;
            float r02 = (float) (r0 + ((r2 * r5.x) / 100.0d));
            double r3 = r142.imageY;
            float r143 = r142.imageH;
            float r1 = (float) (r3 + ((r143 * r5.y) / 100.0d));
            float r22 = (float) ((r2 * r5.w) / 100.0d);
            float r23 = r22 / 2.0f;
            float r144 = ((float) ((r143 * r5.h) / 100.0d)) / 2.0f;
            this.storyReactionWidgetBackground.setBounds((int) (r02 - r23), (int) (r1 - r144), (int) (r23 + r02), (int) (r144 + r1));
            this.storyReactionWidgetBackground.setAlpha((int) (255.0f * r15));
            r13.save();
            double r24 = this.mediaArea.coordinates.rotation;
            if (r24 == 0.0d) goto L8;
            r13.rotate((float) r24, r02, r1);
        L8:
            Rect r03 = AndroidUtilities.rectTmp2;
            float r145 = (this.storyReactionWidgetBackground.getBounds().height() * 0.61f) / 2.0f;
            r03.set((int) (this.storyReactionWidgetBackground.getBounds().centerX() - r145), (int) (this.storyReactionWidgetBackground.getBounds().centerY() - r145), (int) (this.storyReactionWidgetBackground.getBounds().centerX() + r145), (int) (this.storyReactionWidgetBackground.getBounds().centerY() + r145));
            this.storyReactionWidgetBackground.updateShadowLayer(1.0f);
            this.storyReactionWidgetBackground.draw(r13);
            this.imageHolder.setBounds(r03);
            this.imageHolder.setAlpha(r15);
            ReactionImageHolder r146 = this.imageHolder;
            if (this.storyReactionWidgetBackground.isDarkStyle() == false) goto L11;
            int r152 = -1;
        L12:
            r146.setColor(r152);
            this.imageHolder.draw(r13);
            r13.restore();
            return;
        L11:
            r152 = -16777216;
            goto L12
        }

        @Override // org.telegram.ui.Stories.StoryWidgetsImageDecorator.DrawingObject
        public void onAttachedToWindow(boolean r1) {
            this.imageHolder.onAttachedToWindow(r1);
        }

        @Override // org.telegram.ui.Stories.StoryWidgetsImageDecorator.DrawingObject
        public void setParent(View r1) {
            this.imageHolder.setParent(r1);
        }
    }

    public class WeatherWidget extends DrawingObject {
        private final LocationMarker marker;
        private final TL_stories.TL_mediaAreaWeather mediaArea;
        private View parentView;
        final /* synthetic */ StoryWidgetsImageDecorator this$0;

        /* renamed from: org.telegram.ui.Stories.StoryWidgetsImageDecorator$WeatherWidget$1, reason: invalid class name */
        public class AnonymousClass1 extends LocationMarker {
            final /* synthetic */ WeatherWidget this$1;
            final /* synthetic */ StoryWidgetsImageDecorator val$this$0;

            public AnonymousClass1(WeatherWidget r1, Context r2, int r3, float r4, int r5, StoryWidgetsImageDecorator r6) {
                this.this$1 = r1;
                this.val$this$0 = r6;
                super(r2, r3, r4, r5);
            }

            @Override // android.view.View
            public void invalidate() {
                if (WeatherWidget.m19835$$Nest$fgetparentView(this.this$1) == null) goto L6;
                WeatherWidget.m19835$$Nest$fgetparentView(this.this$1).invalidate();
                return;
            }
        }

        /* renamed from: -$$Nest$fgetparentView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m19835$$Nest$fgetparentView(WeatherWidget r0) {
            return r0.parentView;
        }

        public WeatherWidget(StoryWidgetsImageDecorator r10, TL_stories.TL_mediaAreaWeather r11) {
            this.this$0 = r10;
            this.mediaArea = r11;
            Weather.State r0 = new Weather.State();
            r0.emoji = r11.emoji;
            r0.temperature = (float) r11.temperature_c;
            AnonymousClass1 r2 = new AnonymousClass1(this, ApplicationLoader.applicationContext, 1, AndroidUtilities.density, 0, r10);
            this.marker = r2;
            r2.setMaxWidth(AndroidUtilities.displaySize.x);
            r2.setIsVideo(false);
            r2.setCodeEmoji(UserConfig.selectedAccount, r0.getEmoji());
            r2.setText(r0.getTemperature());
            r2.setType(3, r11.color);
            r2.setupLayout();
        }

        @Override // org.telegram.ui.Stories.StoryWidgetsImageDecorator.DrawingObject
        public void draw(Canvas r12, ImageReceiver r13, float r14) {
            StoryWidgetsImageDecorator r132 = this.this$0;
            double r0 = r132.imageX;
            float r142 = r132.imageW;
            TL_stories.MediaAreaCoordinates r4 = this.mediaArea.coordinates;
            float r02 = (float) (r0 + ((r142 * r4.x) / 100.0d));
            double r1 = r132.imageY;
            float r133 = r132.imageH;
            float r15 = (float) (r1 + ((r133 * r4.y) / 100.0d));
            float r143 = (float) ((r142 * r4.w) / 100.0d);
            float r134 = (float) ((r133 * r4.h) / 100.0d);
            r12.save();
            r12.translate(r02, r15);
            float r135 = Math.min(r143 / ((this.marker.getWidthInternal() - this.marker.getPaddingLeft()) - this.marker.getPaddingRight()), r134 / ((this.marker.getHeightInternal() - this.marker.getPaddingTop()) - this.marker.getPaddingBottom()));
            r12.scale(r135, r135);
            double r136 = this.mediaArea.coordinates.rotation;
            if (r136 == 0.0d) goto L5;
            r12.rotate((float) r136);
        L5:
            r12.translate(((-r0) / 2.0f) - this.marker.getPaddingLeft(), ((-r1) / 2.0f) - this.marker.getPaddingTop());
            this.marker.drawInternal(r12);
            r12.restore();
        }

        @Override // org.telegram.ui.Stories.StoryWidgetsImageDecorator.DrawingObject
        public void onAttachedToWindow(boolean r1) {
            LocationMarker r0 = this.marker;
            if (r1 == false) goto L6;
            r0.attachInternal();
            return;
        L6:
            r0.detachInternal();
        }

        @Override // org.telegram.ui.Stories.StoryWidgetsImageDecorator.DrawingObject
        public void setParent(View r1) {
            this.parentView = r1;
        }
    }

    public StoryWidgetsImageDecorator(TL_stories.StoryItem r5) {
        int r0 = 0;
    L4:
        if (r0 >= r5.media_areas.size()) goto L18;
        if ((r5.media_areas.get(r0) instanceof TL_stories.TL_mediaAreaSuggestedReaction) == false) goto L12;
        if (this.drawingObjects != null) goto L10;
        this.drawingObjects = new ArrayList();
    L10:
        this.drawingObjects.add(new ReactionWidget(this, (TL_stories.TL_mediaAreaSuggestedReaction) r5.media_areas.get(r0)));
    L17:
        r0 = r0 + 1;
        goto L4
    L12:
        if ((r5.media_areas.get(r0) instanceof TL_stories.TL_mediaAreaWeather) == false) goto L17;
        if (this.drawingObjects != null) goto L16;
        this.drawingObjects = new ArrayList();
    L16:
        this.drawingObjects.add(new WeatherWidget(this, (TL_stories.TL_mediaAreaWeather) r5.media_areas.get(r0)));
        goto L17
    }

    @Override // org.telegram.messenger.ImageReceiver.Decorator
    public void onAttachedToWindow(ImageReceiver r4) {
        if (this.drawingObjects == null) goto L9;
        int r0 = 0;
    L7:
        if (r0 >= this.drawingObjects.size()) goto L11;
        this.drawingObjects.get(r0).setParent(r4.getParentView());
        this.drawingObjects.get(r0).onAttachedToWindow(true);
        r0 = r0 + 1;
        goto L7
    L11:
        return;
    }

    @Override // org.telegram.messenger.ImageReceiver.Decorator
    public void onDetachedFromWidnow() {
        if (this.drawingObjects == null) goto L9;
        int r1 = 0;
    L7:
        if (r1 >= this.drawingObjects.size()) goto L11;
        this.drawingObjects.get(r1).onAttachedToWindow(false);
        r1 = r1 + 1;
        goto L7
    L11:
        return;
    }

    @Override // org.telegram.messenger.ImageReceiver.Decorator
    public void onDraw(Canvas r7, ImageReceiver r8) {
        if (this.drawingObjects != null) goto L5;
        return;
    L5:
        float r0 = r8.getAlpha();
        float r1 = r8.getCenterX();
        float r2 = r8.getCenterY();
        float r3 = r8.getImageWidth();
        this.imageW = r3;
        float r4 = (16.0f * r3) / 9.0f;
        this.imageH = r4;
        this.imageX = r1 - (r3 / 2.0f);
        this.imageY = r2 - (r4 / 2.0f);
        r7.save();
        r7.clipRect(r8.getImageX(), r8.getImageY(), r8.getImageX2(), r8.getImageY2());
        int r12 = 0;
    L7:
        if (r12 >= this.drawingObjects.size()) goto L9;
        this.drawingObjects.get(r12).draw(r7, r8, r0);
        r12 = r12 + 1;
        goto L7
    L9:
        r7.restore();
    }
}
