package androidx.appcompat.view;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.util.Log;
import android.util.Xml;
import android.view.InflateException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import androidx.appcompat.R$styleable;
import androidx.appcompat.view.menu.MenuBuilder;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.view.menu.MenuItemWrapperICS;
import androidx.appcompat.widget.DrawableUtils;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.internal.view.SupportMenu;
import androidx.core.view.ActionProvider;
import androidx.core.view.MenuItemCompat;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.mvel2.asm.Opcodes;
import org.webrtc.GlShader$$ExternalSyntheticBUOutline1;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes3.dex */
public class SupportMenuInflater extends MenuInflater {
    static final Class<?>[] ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = null;
    static final Class<?>[] ACTION_VIEW_CONSTRUCTOR_SIGNATURE = null;
    final Object[] mActionProviderConstructorArguments;
    final Object[] mActionViewConstructorArguments;
    Context mContext;
    private Object mRealOwner;

    public static class InflatedOnMenuItemClickListener implements MenuItem.OnMenuItemClickListener {
        private static final Class<?>[] PARAM_TYPES = null;
        private Method mMethod;
        private Object mRealOwner;

        static {
            PARAM_TYPES = new Class[]{MenuItem.class};
        }

        public InflatedOnMenuItemClickListener(Object r4, String r5) {
            this.mRealOwner = r4;
            Class<?> r42 = r4.getClass();
            this.mMethod = r42.getMethod(r5, PARAM_TYPES);     // Catch: Exception -> L5
            return;
        L5:
            e = move-exception;
            InflateException r0 = new InflateException("Couldn't resolve menu item onClick handler " + r5 + " in class " + r42.getName());
            r0.initCause(e);
            throw r0;
        }

        @Override // android.view.MenuItem.OnMenuItemClickListener
        public boolean onMenuItemClick(MenuItem r4) {
            Class<?> r0 = this.mMethod.getReturnType();     // Catch: Exception -> L10
            Class<?> r1 = Boolean.TYPE;     // Catch: Exception -> L10
            Method r2 = this.mMethod;
            if (r0 == r1) goto L6;
            r2.invoke(this.mRealOwner, new Object[]{r4});     // Catch: Exception -> L10
            return true;
        L6:
            return ((Boolean) r2.invoke(this.mRealOwner, new Object[]{r4})).booleanValue();
        L10:
            e = move-exception;
            HttpUrl$$ExternalSyntheticBUOutline0.m(e);
            return false;
        }
    }

    public class MenuState {
        private int groupCategory;
        private int groupCheckable;
        private boolean groupEnabled;
        private int groupId;
        private int groupOrder;
        private boolean groupVisible;
        ActionProvider itemActionProvider;
        private String itemActionProviderClassName;
        private String itemActionViewClassName;
        private int itemActionViewLayout;
        private boolean itemAdded;
        private int itemAlphabeticModifiers;
        private char itemAlphabeticShortcut;
        private int itemCategoryOrder;
        private int itemCheckable;
        private boolean itemChecked;
        private CharSequence itemContentDescription;
        private boolean itemEnabled;
        private int itemIconResId;
        private ColorStateList itemIconTintList;
        private PorterDuff.Mode itemIconTintMode;
        private int itemId;
        private String itemListenerMethodName;
        private int itemNumericModifiers;
        private char itemNumericShortcut;
        private int itemShowAsAction;
        private CharSequence itemTitle;
        private CharSequence itemTitleCondensed;
        private CharSequence itemTooltipText;
        private boolean itemVisible;
        private Menu menu;
        final /* synthetic */ SupportMenuInflater this$0;

        public MenuState(SupportMenuInflater r1, Menu r2) {
            this.this$0 = r1;
            this.itemIconTintList = null;
            this.itemIconTintMode = null;
            this.menu = r2;
            resetGroup();
        }

        private char getShortcut(String r1) {
            if (r1 != null) goto L6;
            return 0;
        L6:
            return r1.charAt(0);
        }

        private <T> T newInstance(String r2, Class<?>[] r3, Object[] r4) {
            Constructor<?> r1 = Class.forName(r2, false, this.this$0.mContext.getClassLoader()).getConstructor(r3);     // Catch: Exception -> L4
            r1.setAccessible(true);     // Catch: Exception -> L4
            return (T) r1.newInstance(r4);
        L4:
            e = move-exception;
            Log.w("SupportMenuInflater", "Cannot instantiate class: " + r2, e);
            return null;
        }

        private void setItem(MenuItem r6) {
            MenuItem r0 = r6.setChecked(this.itemChecked).setVisible(this.itemVisible).setEnabled(this.itemEnabled);
            boolean r2 = false;
            if (this.itemCheckable < 1) goto L5;
            boolean r1 = true;
        L6:
            r0.setCheckable(r1).setTitleCondensed(this.itemTitleCondensed).setIcon(this.itemIconResId);
            int r02 = this.itemShowAsAction;
            if (r02 < 0) goto L10;
            r6.setShowAsAction(r02);
        L10:
            if (this.itemListenerMethodName == null) goto L17;
            if (this.this$0.mContext.isRestricted() == true) goto L14;
            r6.setOnMenuItemClickListener(new InflatedOnMenuItemClickListener(this.this$0.getRealOwner(), this.itemListenerMethodName));
            goto L17
        L14:
            Segment$$ExternalSyntheticBUOutline1.m("The android:onClick attribute cannot be used within a restricted context");
            return;
        L17:
            if (this.itemCheckable >= 2) goto L19;
        L24:
            String r03 = this.itemActionViewClassName;
            if (r03 == null) goto L27;
            r6.setActionView((View) newInstance(r03, SupportMenuInflater.ACTION_VIEW_CONSTRUCTOR_SIGNATURE, this.this$0.mActionViewConstructorArguments));
            r2 = true;
        L27:
            int r04 = this.itemActionViewLayout;
            if (r04 <= 0) goto L32;
            if (r2 == true) goto L31;
            r6.setActionView(r04);
            goto L32
        L31:
            Log.w("SupportMenuInflater", "Ignoring attribute 'itemActionViewLayout'. Action view already specified.");
        L32:
            ActionProvider r05 = this.itemActionProvider;
            if (r05 == null) goto L35;
            MenuItemCompat.setActionProvider(r6, r05);
        L35:
            MenuItemCompat.setContentDescription(r6, this.itemContentDescription);
            MenuItemCompat.setTooltipText(r6, this.itemTooltipText);
            MenuItemCompat.setAlphabeticShortcut(r6, this.itemAlphabeticShortcut, this.itemAlphabeticModifiers);
            MenuItemCompat.setNumericShortcut(r6, this.itemNumericShortcut, this.itemNumericModifiers);
            PorterDuff.Mode r06 = this.itemIconTintMode;
            if (r06 == null) goto L38;
            MenuItemCompat.setIconTintMode(r6, r06);
        L38:
            ColorStateList r5 = this.itemIconTintList;
            if (r5 == null) goto L42;
            MenuItemCompat.setIconTintList(r6, r5);
            return;
        L42:
            return;
        L19:
            if ((r6 instanceof MenuItemImpl) == false) goto L22;
            ((MenuItemImpl) r6).setExclusiveCheckable(true);
            goto L24
        L22:
            if ((r6 instanceof MenuItemWrapperICS) == false) goto L24;
            ((MenuItemWrapperICS) r6).setExclusiveCheckable(true);
            goto L24
        L5:
            r1 = false;
            goto L6
        }

        public void addItem() {
            this.itemAdded = true;
            setItem(this.menu.add(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle));
        }

        public SubMenu addSubMenuItem() {
            this.itemAdded = true;
            SubMenu r0 = this.menu.addSubMenu(this.groupId, this.itemId, this.itemCategoryOrder, this.itemTitle);
            setItem(r0.getItem());
            return r0;
        }

        public boolean hasAddedItem() {
            return this.itemAdded;
        }

        public void readGroup(AttributeSet r3) {
            TypedArray r32 = this.this$0.mContext.obtainStyledAttributes(r3, R$styleable.MenuGroup);
            this.groupId = r32.getResourceId(R$styleable.MenuGroup_android_id, 0);
            this.groupCategory = r32.getInt(R$styleable.MenuGroup_android_menuCategory, 0);
            this.groupOrder = r32.getInt(R$styleable.MenuGroup_android_orderInCategory, 0);
            this.groupCheckable = r32.getInt(R$styleable.MenuGroup_android_checkableBehavior, 0);
            this.groupVisible = r32.getBoolean(R$styleable.MenuGroup_android_visible, true);
            this.groupEnabled = r32.getBoolean(R$styleable.MenuGroup_android_enabled, true);
            r32.recycle();
        }

        public void readItem(AttributeSet r7) {
            TintTypedArray r72 = TintTypedArray.obtainStyledAttributes(this.this$0.mContext, r7, R$styleable.MenuItem);
            this.itemId = r72.getResourceId(R$styleable.MenuItem_android_id, 0);
            this.itemCategoryOrder = (r72.getInt(R$styleable.MenuItem_android_menuCategory, this.groupCategory) & Opcodes.V_PREVIEW) | (r72.getInt(R$styleable.MenuItem_android_orderInCategory, this.groupOrder) & 65535);
            this.itemTitle = r72.getText(R$styleable.MenuItem_android_title);
            this.itemTitleCondensed = r72.getText(R$styleable.MenuItem_android_titleCondensed);
            this.itemIconResId = r72.getResourceId(R$styleable.MenuItem_android_icon, 0);
            this.itemAlphabeticShortcut = getShortcut(r72.getString(R$styleable.MenuItem_android_alphabeticShortcut));
            this.itemAlphabeticModifiers = r72.getInt(R$styleable.MenuItem_alphabeticModifiers, 4096);
            this.itemNumericShortcut = getShortcut(r72.getString(R$styleable.MenuItem_android_numericShortcut));
            this.itemNumericModifiers = r72.getInt(R$styleable.MenuItem_numericModifiers, 4096);
            if (r72.hasValue(R$styleable.MenuItem_android_checkable) == false) goto L5;
            this.itemCheckable = r72.getBoolean(R$styleable.MenuItem_android_checkable, false) ? 1 : 0;
        L6:
            this.itemChecked = r72.getBoolean(R$styleable.MenuItem_android_checked, false);
            this.itemVisible = r72.getBoolean(R$styleable.MenuItem_android_visible, this.groupVisible);
            this.itemEnabled = r72.getBoolean(R$styleable.MenuItem_android_enabled, this.groupEnabled);
            this.itemShowAsAction = r72.getInt(R$styleable.MenuItem_showAsAction, -1);
            this.itemListenerMethodName = r72.getString(R$styleable.MenuItem_android_onClick);
            this.itemActionViewLayout = r72.getResourceId(R$styleable.MenuItem_actionLayout, 0);
            this.itemActionViewClassName = r72.getString(R$styleable.MenuItem_actionViewClass);
            String r0 = r72.getString(R$styleable.MenuItem_actionProviderClass);
            this.itemActionProviderClassName = r0;
            if (r0 == null) goto L9;
            boolean r3 = true;
        L11:
            if (r3 == true) goto L13;
        L17:
            if (r3 == false) goto L19;
            Log.w("SupportMenuInflater", "Ignoring attribute 'actionProviderClass'. Action view already specified.");
        L19:
            this.itemActionProvider = null;
        L20:
            this.itemContentDescription = r72.getText(R$styleable.MenuItem_contentDescription);
            this.itemTooltipText = r72.getText(R$styleable.MenuItem_tooltipText);
            if (r72.hasValue(R$styleable.MenuItem_iconTintMode) == false) goto L23;
            this.itemIconTintMode = DrawableUtils.parseTintMode(r72.getInt(R$styleable.MenuItem_iconTintMode, -1), this.itemIconTintMode);
        L25:
            if (r72.hasValue(R$styleable.MenuItem_iconTint) == false) goto L27;
            this.itemIconTintList = r72.getColorStateList(R$styleable.MenuItem_iconTint);
        L28:
            r72.recycle();
            this.itemAdded = false;
            return;
        L27:
            this.itemIconTintList = null;
            goto L28
        L23:
            this.itemIconTintMode = null;
            goto L25
        L13:
            if (this.itemActionViewLayout != 0) goto L17;
            if (this.itemActionViewClassName != null) goto L17;
            this.itemActionProvider = (ActionProvider) newInstance(r0, SupportMenuInflater.ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE, this.this$0.mActionProviderConstructorArguments);
            goto L20
        L9:
            r3 = false;
            goto L11
        L5:
            this.itemCheckable = this.groupCheckable;
            goto L6
        }

        public void resetGroup() {
            this.groupId = 0;
            this.groupCategory = 0;
            this.groupOrder = 0;
            this.groupCheckable = 0;
            this.groupVisible = true;
            this.groupEnabled = true;
        }
    }

    static {
        Class<?>[] r0 = {Context.class};
        ACTION_VIEW_CONSTRUCTOR_SIGNATURE = r0;
        ACTION_PROVIDER_CONSTRUCTOR_SIGNATURE = r0;
    }

    public SupportMenuInflater(Context r1) {
        super(r1);
        this.mContext = r1;
        Object[] r12 = {r1};
        this.mActionViewConstructorArguments = r12;
        this.mActionProviderConstructorArguments = r12;
    }

    private Object findRealOwner(Object r2) {
        if ((r2 instanceof Activity) == false) goto L6;
        return r2;
    L6:
        if ((r2 instanceof ContextWrapper) == true) goto L8;
        return r2;
    L8:
        return findRealOwner(((ContextWrapper) r2).getBaseContext());
    }

    private void parseMenu(XmlPullParser r13, AttributeSet r14, Menu r15) {
        MenuState r0 = new MenuState(this, r15);
        int r152 = r13.getEventType();
    L4:
        if (r152 == 2) goto L5;
        r152 = r13.next();
        if (r152 != 1) goto L4;
    L12:
        boolean r6 = false;
        boolean r7 = false;
        String r8 = null;
    L13:
        if (r6 == true) goto L74;
        if (r152 == 1) goto L54;
        if (r152 != 2) goto L18;
        if (r7 == true) goto L53;
        String r153 = r13.getName();
        if (r153.equals("group") == false) goto L47;
        r0.readGroup(r14);
        goto L53
    L47:
        if (r153.equals(PluginsConstants.Settings.ITEM) == false) goto L50;
        r0.readItem(r14);
        goto L53
    L50:
        if (r153.equals("menu") == false) goto L52;
        parseMenu(r13, r14, r0.addSubMenuItem());
        goto L53
    L52:
        r8 = r153;
        r7 = true;
    L53:
        r152 = r13.next();
        goto L13
    L18:
        if (r152 != 3) goto L53;
        String r154 = r13.getName();
        if (r7 == false) goto L26;
        if (r154.equals(r8) == false) goto L26;
        r7 = false;
        r8 = null;
    L26:
        if (r154.equals("group") == false) goto L29;
        r0.resetGroup();
        goto L53
    L29:
        if (r154.equals(PluginsConstants.Settings.ITEM) == false) goto L39;
        if (r0.hasAddedItem() == true) goto L53;
        ActionProvider r155 = r0.itemActionProvider;
        if (r155 != null) goto L35;
    L37:
        r0.addItem();
        goto L53
    L35:
        if (r155.hasSubMenu() == false) goto L37;
        r0.addSubMenuItem();
        goto L53
    L39:
        if (r154.equals("menu") == false) goto L53;
        r6 = true;
        goto L53
    L54:
        GlShader$$ExternalSyntheticBUOutline1.m("Unexpected end of document");
        return;
    L74:
        return;
    L5:
        String r156 = r13.getName();
        if (r156.equals("menu") == false) goto L8;
        r152 = r13.next();
        goto L12
    L8:
        GlShader$$ExternalSyntheticBUOutline1.m("Expecting menu, got ".concat(r156));
    }

    public Object getRealOwner() {
        if (this.mRealOwner != null) goto L6;
        this.mRealOwner = findRealOwner(this.mContext);
    L6:
        return this.mRealOwner;
    }

    @Override // android.view.MenuInflater
    public void inflate(int r6, Menu r7) {
        if ((r7 instanceof SupportMenu) == true) goto L6;
        super.inflate(r6, r7);
        return;
    L6:
        XmlResourceParser r1 = null;
        boolean r2 = false;
        r1 = this.mContext.getResources().getLayout(r6);     // Catch: Throwable -> L12 IOException -> L14 XmlPullParserException -> L16
        AttributeSet r62 = Xml.asAttributeSet(r1);     // Catch: Throwable -> L12 IOException -> L14 XmlPullParserException -> L16
        if ((r7 instanceof MenuBuilder) == false) goto L18;
        MenuBuilder r3 = (MenuBuilder) r7;     // Catch: Throwable -> L12 IOException -> L14 XmlPullParserException -> L16
        if (r3.isDispatchingItemsChanged() == false) goto L18;
        r3.stopDispatchingItemsChanged();     // Catch: Throwable -> L12 IOException -> L14 XmlPullParserException -> L16
        r2 = true;
    L18:
        parseMenu(r1, r62, r7);     // Catch: Throwable -> L12 IOException -> L14 XmlPullParserException -> L16
        if (r2 == false) goto L21;
        ((MenuBuilder) r7).startDispatchingItemsChanged();
    L21:
        if (r1 == null) goto L35;
        r1.close();
        return;
    L35:
        return;
    L12:
        th = move-exception;
        if (r2 == false) goto L30;
        ((MenuBuilder) r7).startDispatchingItemsChanged();
    L30:
        if (r1 == null) goto L32;
        r1.close();
    L32:
        throw th;
    L14:
        e = move-exception;
        throw new InflateException("Error inflating menu XML", e);     // Catch: Throwable -> L12
    L16:
        e = move-exception;
        throw new InflateException("Error inflating menu XML", e);     // Catch: Throwable -> L12
    }
}
