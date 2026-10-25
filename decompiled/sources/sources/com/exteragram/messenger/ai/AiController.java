package com.exteragram.messenger.ai;

import android.content.Context;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.exteragram.messenger.ai.data.Role;
import com.exteragram.messenger.ai.data.Service;
import com.exteragram.messenger.ai.data.Suggestions;
import com.exteragram.messenger.ai.network.backend.OnDeviceAvailability;
import com.exteragram.messenger.utils.chats.ChatUtils;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;

/* loaded from: classes4.dex */
public class AiController {
    private final List<Role> roles;
    private final List<Service> services;

    public static class SingletonHolder {
        private static final AiController INSTANCE = null;

        /* renamed from: -$$Nest$sfgetINSTANCE, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AiController m1015$$Nest$sfgetINSTANCE() {
            return INSTANCE;
        }

        static {
            INSTANCE = new AiController();
        }
    }

    public static /* synthetic */ void $r8$lambda$UrxaUoTrlqMH85byRPLNTm58_Dc(Runnable r0, Context r1, int r2, BulletinFactory r3, int r4, CharSequence r5, int r6, Theme.ResourcesProvider r7) {
        if (r0 == null) goto L9;
        if (r1 == null) goto L9;
        if (isOnDeviceError(r2) == false) goto L9;
        Bulletin.TwoLineLottieLayout r22 = new Bulletin.TwoLineLottieLayout(r1, r7);
        r22.setSticker(r6);
        r22.titleTextView.setText(LocaleController.getString(r4));
        r22.subtitleTextView.setText(r5);
        r22.setButton(new Bulletin.UndoButton(r1, true, r7).setText(LocaleController.getString(R.string.AIChangeModel)).setUndoAction(r0));
        r3.create(r22, 5000).show();
        return;
    L9:
        r3.createSimpleBulletin(LocaleController.getString(r4), r5, r6).show();
    }

    /* renamed from: $r8$lambda$k4Ykokjerf7UwZXW-V7hhEULXCk, reason: not valid java name */
    public static /* synthetic */ void m1014$r8$lambda$k4Ykokjerf7UwZXWV7hhEULXCk(Runnable r0, BaseFragment r1, AlertDialog r2, int r3) {
        AiConfig.clearConversationHistory();
        if (r0 == null) goto L5;
        r0.run();
    L5:
        BulletinFactory.of(r1).createSimpleBulletin(R.raw.ic_delete, LocaleController.getString(R.string.HistoryCleared)).show();
    }

    public static /* synthetic */ boolean $r8$lambda$ml9V0PZBMdpgTeM8PwcrlItS8AE(Role r1) {
        if (r1 != null) goto L4;
        return true;
    L4:
        if (r1.getName() != null) goto L6;
        return true;
    L6:
        if (r1.getPrompt() == null) goto L13;
        return false;
    L13:
        return true;
    }

    public AiController() {
        this.roles = new ArrayList();
        this.services = new ArrayList();
        loadRoles();
        loadServices();
    }

    public static boolean canSendImage(String r3) {
        if (r3 != null) goto L5;
        return false;
    L5:
        File r1 = new File(r3);
        if (r1.exists() == true) goto L8;
    L26:
        return false;
    L8:
        if (r1.isFile() == false) goto L26;
        String r32 = r3.toLowerCase();
        if (r32.endsWith(".png") == false) goto L13;
        return true;
    L13:
        if (r32.endsWith(".jpg") == false) goto L15;
        return true;
    L15:
        if (r32.endsWith(".jpeg") == false) goto L17;
        return true;
    L17:
        if (r32.endsWith(".webp") == false) goto L19;
        return true;
    L19:
        if (r32.endsWith(".heic") == false) goto L21;
        return true;
    L21:
        if (r32.endsWith(".heif") == true) goto L31;
        return false;
    L31:
        return true;
    }

    public static boolean canSendImage(MessageObject r1) {
        String r0 = ChatUtils.getInstance().getPathToMessage(r1);
        if (r1 != null) goto L5;
        return false;
    L5:
        if (canSendImage(r0) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean canUseAI() {
        if (getInstance().getSelected().isOnDevice() == false) goto L7;
        return OnDeviceAvailability.isReady();
    L7:
        return !TextUtils.isEmpty(r0.getKey());
    }

    public static void clearHistory(BaseFragment r1, Theme.ResourcesProvider r2, boolean r3) {
        clearHistory(r1, r2, r3, null);
    }

    public static void clearHistory(BaseFragment r1, Theme.ResourcesProvider r2, boolean r3, Runnable r4) {
        if (r1 == null) goto L15;
        if (r3 == false) goto L9;
        AlertDialog.Builder r32 = new AlertDialog.Builder(r1.getParentActivity(), r2);
        r32.setMessage(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ClearConversationHistoryInfo)));
        r32.setTitle(LocaleController.getString(R.string.ClearHistory));
        r32.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r32.setPositiveButton(LocaleController.getString(R.string.ClearButton), new AiController$$ExternalSyntheticLambda6(r4, r1));
        AlertDialog r22 = r32.create();
        r1.showDialog(r22);
        TextView r12 = (TextView) r22.getButton(-1);
        if (r12 == null) goto L14;
        r12.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L14:
        return;
    L9:
        AiConfig.clearConversationHistory();
        if (r4 == null) goto L12;
        r4.run();
    L12:
        BulletinFactory.of(r1).createSimpleBulletin(R.raw.ic_delete, LocaleController.getString(R.string.HistoryCleared)).show();
        return;
    }

    public static AiController getInstance() {
        return SingletonHolder.m1015$$Nest$sfgetINSTANCE();
    }

    public static boolean isOnDeviceError(int r1) {
        if (r1 != (-1)) goto L5;
        return true;
    L5:
        if (r1 != (-2)) goto L7;
        return true;
    L7:
        if (r1 != (-3)) goto L9;
        return true;
    L9:
        if (r1 != (-4)) goto L11;
        return true;
    L11:
        if (r1 == (-5)) goto L20;
        return false;
    L20:
        return true;
    }

    public static void showErrorBulletin(ViewGroup r6, Theme.ResourcesProvider r7, int r8, String r9, Runnable r10) {
        showErrorBulletin(null, r6, r7, r8, r9, r10);
    }

    public static void showErrorBulletin(BaseFragment r6, int r7, String r8) {
        showErrorBulletin(r6, null, null, r7, r8, null);
    }

    private static void showErrorBulletin(BaseFragment r10, ViewGroup r11, Theme.ResourcesProvider r12, int r13, String r14, Runnable r15) {
        boolean r2 = false;
        if (r13 == (-5)) goto L41;
        int r3 = 2;
        if (r13 != (-4)) goto L7;
        int r0 = R.string.AIErrorOnDeviceTooLong;
        int r1 = R.string.AIErrorOnDeviceTooLongInfo;
    L25:
        int r8 = r3;
    L42:
        Context r32 = null;
        if (r2 == false) goto L45;
    L50:
        String r142 = trimErrorMessage(r14);
    L51:
        if (r2 == false) goto L54;
        if (r142 == null) goto L54;
        r0 = R.string.UnknownError;
    L54:
        int r6 = r0;
        if (r142 == null) goto L57;
    L56:
        String r7 = r142;
        if (r11 == null) goto L61;
        BulletinFactory r143 = BulletinFactory.of((FrameLayout) r11, r12);
    L60:
        BulletinFactory r5 = r143;
        if (r11 == null) goto L66;
        r32 = r11.getContext();
    L68:
        AndroidUtilities.runOnUIThread(new AiController$$ExternalSyntheticLambda0(r15, r32, r13, r5, r6, r7, r8, r12));
        return;
    L66:
        if (r10 == null) goto L68;
        r32 = r10.getContext();
        goto L68
    L61:
        if (r10 == null) goto L63;
        r143 = BulletinFactory.of(r10);
        goto L60
    L63:
        r143 = BulletinFactory.global();
        goto L60
    L57:
        r142 = LocaleController.getString(r1);
        goto L56
    L45:
        if (r13 == 400) goto L50;
        if (r13 == 403) goto L50;
        r142 = null;
        goto L51
    L7:
        if (r13 != (-3)) goto L9;
        r0 = R.string.AIErrorOnDeviceDownloading;
        r1 = R.string.AIErrorOnDeviceDownloadingInfo;
    L35:
        r8 = 5;
        goto L42
    L9:
        if (r13 != (-2)) goto L11;
        r0 = R.string.AIErrorOnDeviceDownload;
        r1 = R.string.AIErrorOnDeviceDownloadInfo;
    L27:
        r8 = 8;
        goto L42
    L11:
        if (r13 != (-1)) goto L13;
        r0 = R.string.AIErrorOnDevice;
        int r33 = R.string.AIErrorOnDeviceInfo;
    L29:
        r8 = 3;
        r1 = r33;
        goto L42
    L13:
        if (r13 != 204) goto L15;
        r0 = R.string.AIErrorEmpty;
        r1 = R.string.AIErrorEmptyInfo;
        goto L25
    L15:
        if (r13 != 408) goto L17;
        r0 = R.string.AIError408;
        r1 = R.string.AIError408Info;
        goto L35
    L17:
        if (r13 != 429) goto L19;
        r0 = R.string.AIError429;
        r1 = R.string.AIError429Info;
        r3 = 6;
        goto L25
    L19:
        if (r13 != 502) goto L21;
        r0 = R.string.AIError502;
        r33 = R.string.AIError502Info;
        goto L29
    L21:
        if (r13 == 503) goto L31;
        switch(r13) {
            case 400: goto L30;
            case 401: goto L28;
            case 402: goto L26;
            case 403: goto L24;
            default: goto L23;
        };
    L23:
        r0 = R.string.AIError;
        r8 = 3;
        r1 = R.string.AIErrorInfo;
        r2 = true;
        goto L42
    L24:
        r0 = R.string.AIError403;
        r1 = R.string.AIError403Info;
        r3 = 9;
        goto L25
    L26:
        r0 = R.string.AIError402;
        r1 = R.string.AIError402Info;
        goto L27
    L28:
        r0 = R.string.AIError401;
        r33 = R.string.AIError401Info;
        goto L29
    L30:
        r0 = R.string.AIError400;
        r1 = R.string.AIError400Info;
        goto L25
    L31:
        r0 = R.string.AIError503;
        r33 = R.string.AIError503Info;
        goto L29
    L41:
        r0 = R.string.AIErrorOnDeviceStopped;
        r33 = R.string.AIErrorOnDeviceStoppedInfo;
        goto L29
    }

    private static String trimErrorMessage(String r3) {
        if (TextUtils.isEmpty(r3) == false) goto L5;
        return null;
    L5:
        String r32 = r3.trim();
        if (r32.isEmpty() == false) goto L9;
        return null;
    L9:
        if (r32.length() > 160) goto L11;
        return r32;
    L11:
        return r32.substring(0, 160).trim() + "…";
    }

    public boolean addRole(Role r3) {
        if (isSuggestedRole(r3) == false) goto L5;
    L9:
        return false;
    L5:
        if (isCustomRole(r3) == true) goto L9;
        this.roles.add(0, r3);
        saveRoles();
        return true;
    }

    public void addService(Service r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        if (r2.isOnDevice() == false) goto L6;
        return;
    L6:
        if (this.services.contains(r2) == true) goto L12;
        this.services.add(r2);
        saveServices();
        return;
    }

    public List<Service> getAll() {
        return Collections.unmodifiableList(this.services);
    }

    public List<Role> getRoles() {
        return Collections.unmodifiableList(this.roles);
    }

    public Service getSelected() {
        Optional<Service> r0 = this.services.stream().filter(new AiController$$ExternalSyntheticLambda2()).findFirst();
        if (r0.isPresent() == true) goto L5;
        Iterator<Service> r2 = this.services.iterator();
    L8:
        if (r2.hasNext() == false) goto L13;
        Service r02 = r2.next();
        if (r02.isOnDevice() == true) goto L8;
        return r02;
    L13:
        return AiConfig.DEFAULT_SERVICE;
    L5:
        return r0.get();
    }

    public Role getSelectedRole() {
        Iterator<Role> r0 = this.roles.iterator();
    L4:
        if (r0.hasNext() == false) goto L8;
        Role r1 = r0.next();
        if (r1.isSelected() == false) goto L4;
        return r1;
    L8:
        Iterator<Role> r3 = getSuggestedRoles().iterator();
    L10:
        if (r3.hasNext() == false) goto L15;
        Role r02 = r3.next();
        if (r02.isSelected() == false) goto L10;
        return r02;
    L15:
        return Suggestions.ASSISTANT.getRole();
    }

    public List<Role> getSuggestedRoles() {
        return (List) Arrays.stream(Suggestions.values()).map(new AiController$$ExternalSyntheticLambda5()).collect(Collectors.toList());
    }

    public boolean isCustomRole(Role r1) {
        if (r1 != null) goto L6;
        return false;
    L6:
        return this.roles.contains(r1);
    }

    public boolean isServicesEmpty() {
        return this.services.isEmpty();
    }

    public boolean isSuggestedRole(Role r1) {
        if (r1 != null) goto L6;
        return false;
    L6:
        return getSuggestedRoles().contains(r1);
    }

    public void loadRoles() {
        ArrayList<Role> r0 = AiConfig.getRoles();
        this.roles.clear();
        this.roles.addAll(r0);
        this.roles.removeIf(new AiController$$ExternalSyntheticLambda3());
    }

    public void loadServices() {
        ArrayList<Service> r0 = AiConfig.getServices();
        this.services.clear();
        this.services.addAll(r0);
        this.services.removeIf(new AiController$$ExternalSyntheticLambda4());
        if (OnDeviceAvailability.isSupported() == false) goto L15;
        String r02 = OnDeviceAvailability.getModelName();
        Service r1 = AiConfig.ON_DEVICE_SERVICE;
        if (TextUtils.isEmpty(r02) == false) goto L7;
        r02 = "Gemini Nano";
    L7:
        r1.setModel(r02);
        if (OnDeviceAvailability.isThinkingSupported() == true) goto L10;
    L12:
        boolean r03 = false;
    L13:
        r1.setReasoningEnabled(r03);
        this.services.add(r1);
        goto L15
    L10:
        if (AiConfig.getOnDeviceReasoning() == false) goto L12;
        r03 = true;
    L15:
        if (OnDeviceAvailability.isReady() == false) goto L17;
        return;
    L17:
        if (AiConfig.ON_DEVICE_SERVICE.getId().equals(AiConfig.getSelectedServiceId()) == false) goto L21;
        AiConfig.setSelectedServiceId(null);
        return;
    }

    public boolean removeRole(Role r2) {
        if (r2 != null) goto L5;
        return false;
    L5:
        boolean r22 = this.roles.remove(r2);
        if (r22 == false) goto L8;
        saveRoles();
    L8:
        return r22;
    }

    public boolean removeService(Service r2) {
        if (r2 != null) goto L4;
        return false;
    L4:
        if (r2.isOnDevice() == true) goto L12;
        boolean r22 = this.services.remove(r2);
        if (r22 == false) goto L9;
        saveServices();
    L9:
        return r22;
    L12:
        return false;
    }

    public void saveRoles() {
        AiConfig.saveRoles(new ArrayList(this.roles));
    }

    public void saveServices() {
        this.services.sort(Comparator.comparing(new AiController$$ExternalSyntheticLambda1(), Comparator.nullsLast(Comparator.naturalOrder())));
        ArrayList r0 = new ArrayList();
        Iterator<Service> r3 = this.services.iterator();
    L4:
        if (r3.hasNext() == false) goto L8;
        Service r1 = r3.next();
        if (r1.isOnDevice() == true) goto L4;
        r0.add(r1);
        goto L4
    L8:
        AiConfig.saveServices(r0);
    }

    public boolean updateRole(Role r4, Role r5) {
        int r0 = this.roles.indexOf(r4);
        if (r0 != (-1)) goto L5;
        return false;
    L5:
        if (isSuggestedRole(r5) == true) goto L7;
    L9:
        this.roles.set(r0, r5);
        saveRoles();
        return true;
    L7:
        if (r4.equals(r5) == true) goto L9;
        return false;
    }

    public void updateService(Service r3, Service r4) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.isOnDevice() == true) goto L14;
        if (r4 != null) goto L7;
        return;
    L7:
        if (r4.isOnDevice() == true) goto L17;
        int r0 = this.services.indexOf(r3);
        if (r0 == (-1)) goto L16;
        r4.setId(r3.getId());
        this.services.set(r0, r4);
        saveServices();
        return;
    L16:
        return;
    L17:
        return;
    }
}
