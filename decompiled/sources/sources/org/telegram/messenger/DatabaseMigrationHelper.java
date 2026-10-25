package org.telegram.messenger;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes5.dex */
public class DatabaseMigrationHelper {
    public DatabaseMigrationHelper() {
    }

    private static void executeNoException(SQLiteDatabase r0, String r1) {
        r0.executeFast(r1).stepThis().dispose();     // Catch: Exception -> L4
        return;
    L4:
        e = move-exception;
        FileLog.e(e);
    }

    public static int migrate(MessagesStorage r1, int r2) {
        return migrate(r1, r1.getDatabase(), r2);
    }

    public static int migrate(MessagesStorage r26, SQLiteDatabase r27, int r28) {
        int r2 = 4;
        int r0 = r28;
        if (r0 >= 4) goto L5;
        r27.executeFast("CREATE TABLE IF NOT EXISTS user_photos(uid INTEGER, id INTEGER, data BLOB, PRIMARY KEY (uid, id))").stepThis().dispose();
        r27.executeFast("DROP INDEX IF EXISTS read_state_out_idx_messages;").stepThis().dispose();
        r27.executeFast("DROP INDEX IF EXISTS ttl_idx_messages;").stepThis().dispose();
        r27.executeFast("DROP INDEX IF EXISTS date_idx_messages;").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS mid_out_idx_messages ON messages(mid, out);").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS task_idx_messages ON messages(uid, out, read_state, ttl, date, send_state);").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS uid_date_mid_idx_messages ON messages(uid, date, mid);").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS user_contacts_v6(uid INTEGER PRIMARY KEY, fname TEXT, sname TEXT)").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS user_phones_v6(uid INTEGER, phone TEXT, sphone TEXT, deleted INTEGER, PRIMARY KEY (uid, phone))").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS sphone_deleted_idx_user_phones ON user_phones_v6(sphone, deleted);").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS mid_idx_randoms ON randoms(mid);").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS sent_files_v2(uid TEXT, type INTEGER, data BLOB, PRIMARY KEY (uid, type))").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS download_queue(uid INTEGER, type INTEGER, date INTEGER, data BLOB, PRIMARY KEY (uid, type));").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS type_date_idx_download_queue ON download_queue(type, date);").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS dialog_settings(did INTEGER PRIMARY KEY, flags INTEGER);").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS unread_count_idx_dialogs ON dialogs(unread_count);").stepThis().dispose();
        r27.executeFast("UPDATE messages SET send_state = 2 WHERE mid < 0 AND send_state = 1").stepThis().dispose();
        r26.fixNotificationSettings();
        r27.executeFast("PRAGMA user_version = 4").stepThis().dispose();
        r0 = 4;
    L5:
        int r3 = 6;
        int r4 = 2;
        int r5 = 1;
        int r6 = 0;
        if (r0 != 4) goto L18;
        r27.executeFast("CREATE TABLE IF NOT EXISTS enc_tasks_v2(mid INTEGER PRIMARY KEY, date INTEGER)").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS date_idx_enc_tasks_v2 ON enc_tasks_v2(date);").stepThis().dispose();
        r27.beginTransaction();
        SQLiteCursor r02 = r27.queryFinalized("SELECT date, data FROM enc_tasks WHERE 1", new Object[0]);
        SQLitePreparedStatement r7 = r27.executeFast("REPLACE INTO enc_tasks_v2 VALUES(?, ?)");
        if (r02.next() == false) goto L16;
        int r8 = r02.intValue(0);
        NativeByteBuffer r9 = r02.byteBufferValue(1);
        if (r9 == null) goto L16;
        int r10 = r9.limit();
        int r11 = 0;
    L13:
        if (r11 >= (r10 / 4)) goto L15;
        r7.requery();
        r7.bindInteger(1, r9.readInt32(false));
        r7.bindInteger(2, r8);
        r7.step();
        r11 = r11 + 1;
        goto L13
    L15:
        r9.reuse();
    L16:
        r7.dispose();
        r02.dispose();
        r27.commitTransaction();
        r27.executeFast("DROP INDEX IF EXISTS date_idx_enc_tasks;").stepThis().dispose();
        r27.executeFast("DROP TABLE IF EXISTS enc_tasks;").stepThis().dispose();
        r27.executeFast("ALTER TABLE messages ADD COLUMN media INTEGER default 0").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 6").stepThis().dispose();
        r0 = 6;
    L18:
        if (r0 != 6) goto L21;
        r27.executeFast("CREATE TABLE IF NOT EXISTS messages_seq(mid INTEGER PRIMARY KEY, seq_in INTEGER, seq_out INTEGER);").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS seq_idx_messages_seq ON messages_seq(seq_in, seq_out);").stepThis().dispose();
        r27.executeFast("ALTER TABLE enc_chats ADD COLUMN layer INTEGER default 0").stepThis().dispose();
        r27.executeFast("ALTER TABLE enc_chats ADD COLUMN seq_in INTEGER default 0").stepThis().dispose();
        r27.executeFast("ALTER TABLE enc_chats ADD COLUMN seq_out INTEGER default 0").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 7").stepThis().dispose();
        r0 = 7;
    L21:
        if (r0 == 7) goto L24;
        if (r0 == 8) goto L24;
        if (r0 == 9) goto L24;
    L26:
        if (r0 != 10) goto L29;
        r27.executeFast("CREATE TABLE IF NOT EXISTS web_recent_v3(id TEXT, type INTEGER, image_url TEXT, thumb_url TEXT, local_url TEXT, width INTEGER, height INTEGER, size INTEGER, date INTEGER, PRIMARY KEY (id, type));").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 11").stepThis().dispose();
        r0 = 11;
    L29:
        if (r0 == 11) goto L31;
        if (r0 == 12) goto L31;
    L33:
        if (r0 != 13) goto L36;
        r27.executeFast("ALTER TABLE messages ADD COLUMN replydata BLOB default NULL").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 14").stepThis().dispose();
        r0 = 14;
    L36:
        if (r0 != 14) goto L39;
        r27.executeFast("CREATE TABLE IF NOT EXISTS hashtag_recent_v2(id TEXT PRIMARY KEY, date INTEGER);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 15").stepThis().dispose();
        r0 = 15;
    L39:
        if (r0 != 15) goto L42;
        r27.executeFast("CREATE TABLE IF NOT EXISTS webpage_pending(id INTEGER, mid INTEGER, PRIMARY KEY (id, mid));").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 16").stepThis().dispose();
        r0 = 16;
    L42:
        if (r0 != 16) goto L45;
        r27.executeFast("ALTER TABLE dialogs ADD COLUMN inbox_max INTEGER default 0").stepThis().dispose();
        r27.executeFast("ALTER TABLE dialogs ADD COLUMN outbox_max INTEGER default 0").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 17").stepThis().dispose();
        r0 = 17;
    L45:
        if (r0 != 17) goto L48;
        r27.executeFast("PRAGMA user_version = 18").stepThis().dispose();
        r0 = 18;
    L48:
        if (r0 != 18) goto L51;
        r27.executeFast("DROP TABLE IF EXISTS stickers;").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS stickers_v2(id INTEGER PRIMARY KEY, data BLOB, date INTEGER, hash INTEGER);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 19").stepThis().dispose();
        r0 = 19;
    L51:
        if (r0 != 19) goto L54;
        r27.executeFast("CREATE TABLE IF NOT EXISTS bot_keyboard(uid INTEGER PRIMARY KEY, mid INTEGER, info BLOB)").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS bot_keyboard_idx_mid ON bot_keyboard(mid);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 20").stepThis().dispose();
        r0 = 20;
    L54:
        if (r0 != 20) goto L57;
        r27.executeFast("CREATE TABLE search_recent(did INTEGER PRIMARY KEY, date INTEGER);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 21").stepThis().dispose();
        r0 = 21;
    L57:
        if (r0 != 21) goto L69;
        r27.executeFast("CREATE TABLE IF NOT EXISTS chat_settings_v2(uid INTEGER PRIMARY KEY, info BLOB)").stepThis().dispose();
        SQLiteCursor r03 = r27.queryFinalized("SELECT uid, participants FROM chat_settings WHERE uid < 0", new Object[0]);
        SQLitePreparedStatement r15 = r27.executeFast("REPLACE INTO chat_settings_v2 VALUES(?, ?)");
    L60:
        if (r03.next() == false) goto L67;
        long r112 = r03.intValue(0);
        NativeByteBuffer r102 = r03.byteBufferValue(1);
        if (r102 == null) goto L60;
        TLRPC.ChatParticipants r82 = TLRPC.ChatParticipants.TLdeserialize(r102, r102.readInt32(false), false);
        r102.reuse();
        if (r82 == null) goto L60;
        TLRPC.TL_chatFull r103 = new TLRPC.TL_chatFull();
        r103.id = r112;
        r103.chat_photo = new TLRPC.TL_photoEmpty();
        r103.notify_settings = new TLRPC.TL_peerNotifySettingsEmpty_layer77();
        r103.exported_invite = null;
        r103.participants = r82;
        NativeByteBuffer r83 = new NativeByteBuffer(r103.getObjectSize());
        r103.serializeToStream(r83);
        r15.requery();
        r15.bindLong(1, r112);
        r15.bindByteBuffer(2, r83);
        r15.step();
        r83.reuse();
        goto L60
    L67:
        r15.dispose();
        r03.dispose();
        r27.executeFast("DROP TABLE IF EXISTS chat_settings;").stepThis().dispose();
        r27.executeFast("ALTER TABLE dialogs ADD COLUMN last_mid_i INTEGER default 0").stepThis().dispose();
        r27.executeFast("ALTER TABLE dialogs ADD COLUMN unread_count_i INTEGER default 0").stepThis().dispose();
        r27.executeFast("ALTER TABLE dialogs ADD COLUMN pts INTEGER default 0").stepThis().dispose();
        r27.executeFast("ALTER TABLE dialogs ADD COLUMN date_i INTEGER default 0").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS last_mid_i_idx_dialogs ON dialogs(last_mid_i);").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS unread_count_i_idx_dialogs ON dialogs(unread_count_i);").stepThis().dispose();
        r27.executeFast("ALTER TABLE messages ADD COLUMN imp INTEGER default 0").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS messages_holes(uid INTEGER, start INTEGER, end INTEGER, PRIMARY KEY(uid, start));").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS uid_end_messages_holes ON messages_holes(uid, end);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 22").stepThis().dispose();
        r0 = 22;
    L69:
        if (r0 != 22) goto L72;
        r27.executeFast("CREATE TABLE IF NOT EXISTS media_holes_v2(uid INTEGER, type INTEGER, start INTEGER, end INTEGER, PRIMARY KEY(uid, type, start));").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS uid_end_media_holes_v2 ON media_holes_v2(uid, type, end);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 23").stepThis().dispose();
        r0 = 23;
    L72:
        if (r0 != 23) goto L74;
    L75:
        r27.executeFast("DELETE FROM media_holes_v2 WHERE uid != 0 AND type >= 0 AND start IN (0, 1)").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 25").stepThis().dispose();
        r0 = 25;
    L77:
        if (r0 != 25) goto L79;
    L80:
        r27.executeFast("CREATE TABLE IF NOT EXISTS channel_users_v2(did INTEGER, uid INTEGER, date INTEGER, data BLOB, PRIMARY KEY(did, uid))").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 27").stepThis().dispose();
        r0 = 27;
    L82:
        if (r0 != 27) goto L85;
        r27.executeFast("ALTER TABLE web_recent_v3 ADD COLUMN document BLOB default NULL").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 28").stepThis().dispose();
        r0 = 28;
    L85:
        if (r0 != 28) goto L87;
    L88:
        r27.executeFast("DELETE FROM sent_files_v2 WHERE 1").stepThis().dispose();
        r27.executeFast("DELETE FROM download_queue WHERE 1").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 30").stepThis().dispose();
        r0 = 30;
    L90:
        if (r0 != 30) goto L92;
        r27.executeFast("ALTER TABLE chat_settings_v2 ADD COLUMN pinned INTEGER default 0").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS chat_settings_pinned_idx ON chat_settings_v2(uid, pinned) WHERE pinned != 0;").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS users_data(uid INTEGER PRIMARY KEY, about TEXT)").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 31").stepThis().dispose();
        r0 = 31;
    L92:
        char r84 = ' ';
        if (r0 != 31) goto L96;
        r27.executeFast("DROP TABLE IF EXISTS bot_recent;").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS chat_hints(did INTEGER, type INTEGER, rating REAL, date INTEGER, PRIMARY KEY(did, type))").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS chat_hints_rating_idx ON chat_hints(rating);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 32").stepThis().dispose();
        r0 = 32;
    L96:
        if (r0 != 32) goto L99;
        r27.executeFast("DROP INDEX IF EXISTS uid_mid_idx_imp_messages;").stepThis().dispose();
        r27.executeFast("DROP INDEX IF EXISTS uid_date_mid_imp_idx_messages;").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 33").stepThis().dispose();
        r0 = 33;
    L99:
        if (r0 != 33) goto L102;
        r27.executeFast("CREATE TABLE IF NOT EXISTS pending_tasks(id INTEGER PRIMARY KEY, data BLOB);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 34").stepThis().dispose();
        r0 = 34;
    L102:
        if (r0 != 34) goto L105;
        r27.executeFast("CREATE TABLE IF NOT EXISTS stickers_featured(id INTEGER PRIMARY KEY, data BLOB, unread BLOB, date INTEGER, hash INTEGER);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 35").stepThis().dispose();
        r0 = 35;
    L105:
        if (r0 != 35) goto L108;
        r27.executeFast("CREATE TABLE IF NOT EXISTS requested_holes(uid INTEGER, seq_out_start INTEGER, seq_out_end INTEGER, PRIMARY KEY (uid, seq_out_start, seq_out_end));").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 36").stepThis().dispose();
        r0 = 36;
    L108:
        if (r0 != 36) goto L111;
        r27.executeFast("ALTER TABLE enc_chats ADD COLUMN in_seq_no INTEGER default 0").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 37").stepThis().dispose();
        r0 = 37;
    L111:
        if (r0 != 37) goto L114;
        r27.executeFast("CREATE TABLE IF NOT EXISTS botcache(id TEXT PRIMARY KEY, date INTEGER, data BLOB)").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS botcache_date_idx ON botcache(date);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 38").stepThis().dispose();
        r0 = 38;
    L114:
        if (r0 != 38) goto L116;
        r27.executeFast("ALTER TABLE dialogs ADD COLUMN pinned INTEGER default 0").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 39").stepThis().dispose();
        r0 = 39;
    L116:
        if (r0 != 39) goto L119;
        r27.executeFast("ALTER TABLE enc_chats ADD COLUMN admin_id INTEGER default 0").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 40").stepThis().dispose();
        r0 = 40;
    L119:
        if (r0 != 40) goto L122;
        r26.fixNotificationSettings();
        r27.executeFast("PRAGMA user_version = 41").stepThis().dispose();
        r0 = 41;
    L122:
        if (r0 != 41) goto L125;
        r27.executeFast("ALTER TABLE messages ADD COLUMN mention INTEGER default 0").stepThis().dispose();
        r27.executeFast("ALTER TABLE user_contacts_v6 ADD COLUMN imported INTEGER default 0").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS uid_mention_idx_messages ON messages(uid, mention, read_state);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 42").stepThis().dispose();
        r0 = 42;
    L125:
        if (r0 != 42) goto L128;
        r27.executeFast("CREATE TABLE IF NOT EXISTS sharing_locations(uid INTEGER PRIMARY KEY, mid INTEGER, date INTEGER, period INTEGER, message BLOB);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 43").stepThis().dispose();
        r0 = 43;
    L128:
        if (r0 != 43) goto L131;
        r27.executeFast("PRAGMA user_version = 44").stepThis().dispose();
        r0 = 44;
    L131:
        if (r0 != 44) goto L134;
        r27.executeFast("CREATE TABLE IF NOT EXISTS user_contacts_v7(key TEXT PRIMARY KEY, uid INTEGER, fname TEXT, sname TEXT, imported INTEGER)").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS user_phones_v7(key TEXT, phone TEXT, sphone TEXT, deleted INTEGER, PRIMARY KEY (key, phone))").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS sphone_deleted_idx_user_phones ON user_phones_v7(sphone, deleted);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 45").stepThis().dispose();
        r0 = 45;
    L134:
        if (r0 != 45) goto L137;
        r27.executeFast("ALTER TABLE enc_chats ADD COLUMN mtproto_seq INTEGER default 0").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 46").stepThis().dispose();
        r0 = 46;
    L137:
        if (r0 != 46) goto L140;
        r27.executeFast("DELETE FROM botcache WHERE 1").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 47").stepThis().dispose();
        r0 = 47;
    L140:
        if (r0 != 47) goto L143;
        r27.executeFast("ALTER TABLE dialogs ADD COLUMN flags INTEGER default 0").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 48").stepThis().dispose();
        r0 = 48;
    L143:
        if (r0 != 48) goto L146;
        r27.executeFast("CREATE TABLE IF NOT EXISTS unread_push_messages(uid INTEGER, mid INTEGER, random INTEGER, date INTEGER, data BLOB, fm TEXT, name TEXT, uname TEXT, flags INTEGER, PRIMARY KEY(uid, mid))").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS unread_push_messages_idx_date ON unread_push_messages(date);").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS unread_push_messages_idx_random ON unread_push_messages(random);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 49").stepThis().dispose();
        r0 = 49;
    L146:
        if (r0 != 49) goto L149;
        r27.executeFast("CREATE TABLE IF NOT EXISTS user_settings(uid INTEGER PRIMARY KEY, info BLOB, pinned INTEGER)").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS user_settings_pinned_idx ON user_settings(uid, pinned) WHERE pinned != 0;").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 50").stepThis().dispose();
        r0 = 50;
    L149:
        if (r0 != 50) goto L152;
        r27.executeFast("DELETE FROM sent_files_v2 WHERE 1").stepThis().dispose();
        r27.executeFast("ALTER TABLE sent_files_v2 ADD COLUMN parent TEXT").stepThis().dispose();
        r27.executeFast("DELETE FROM download_queue WHERE 1").stepThis().dispose();
        r27.executeFast("ALTER TABLE download_queue ADD COLUMN parent TEXT").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 51").stepThis().dispose();
        r0 = 51;
    L152:
        if (r0 != 51) goto L155;
        r27.executeFast("ALTER TABLE media_counts_v2 ADD COLUMN old INTEGER").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 52").stepThis().dispose();
        r0 = 52;
    L155:
        if (r0 != 52) goto L158;
        r27.executeFast("CREATE TABLE IF NOT EXISTS polls_v2(mid INTEGER, uid INTEGER, id INTEGER, PRIMARY KEY (mid, uid));").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS polls_id ON polls_v2(id);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 53").stepThis().dispose();
        r0 = 53;
    L158:
        if (r0 != 53) goto L161;
        r27.executeFast("ALTER TABLE chat_settings_v2 ADD COLUMN online INTEGER default 0").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 54").stepThis().dispose();
        r0 = 54;
    L161:
        if (r0 != 54) goto L164;
        r27.executeFast("DROP TABLE IF EXISTS wallpapers;").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 55").stepThis().dispose();
        r0 = 55;
    L164:
        if (r0 != 55) goto L167;
        r27.executeFast("CREATE TABLE IF NOT EXISTS wallpapers2(uid INTEGER PRIMARY KEY, data BLOB, num INTEGER)").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS wallpapers_num ON wallpapers2(num);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 56").stepThis().dispose();
        r0 = 56;
    L167:
        if (r0 != 56) goto L169;
    L170:
        r27.executeFast("CREATE TABLE IF NOT EXISTS emoji_keywords_v2(lang TEXT, keyword TEXT, emoji TEXT, PRIMARY KEY(lang, keyword, emoji));").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS emoji_keywords_info_v2(lang TEXT PRIMARY KEY, alias TEXT, version INTEGER);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 58").stepThis().dispose();
        r0 = 58;
    L172:
        if (r0 != 58) goto L175;
        r27.executeFast("CREATE INDEX IF NOT EXISTS emoji_keywords_v2_keyword ON emoji_keywords_v2(keyword);").stepThis().dispose();
        r27.executeFast("ALTER TABLE emoji_keywords_info_v2 ADD COLUMN date INTEGER default 0").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 59").stepThis().dispose();
        r0 = 59;
    L175:
        if (r0 != 59) goto L178;
        r27.executeFast("ALTER TABLE dialogs ADD COLUMN folder_id INTEGER default 0").stepThis().dispose();
        r27.executeFast("ALTER TABLE dialogs ADD COLUMN data BLOB default NULL").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS folder_id_idx_dialogs ON dialogs(folder_id);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 60").stepThis().dispose();
        r0 = 60;
    L178:
        if (r0 != 60) goto L181;
        r27.executeFast("DROP TABLE IF EXISTS channel_admins;").stepThis().dispose();
        r27.executeFast("DROP TABLE IF EXISTS blocked_users;").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 61").stepThis().dispose();
        r0 = 61;
    L181:
        if (r0 != 61) goto L184;
        r27.executeFast("DROP INDEX IF EXISTS send_state_idx_messages;").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS send_state_idx_messages2 ON messages(mid, send_state, date);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 62").stepThis().dispose();
        r0 = 62;
    L184:
        if (r0 != 62) goto L187;
        r27.executeFast("CREATE TABLE IF NOT EXISTS scheduled_messages(mid INTEGER PRIMARY KEY, uid INTEGER, send_state INTEGER, date INTEGER, data BLOB, ttl INTEGER, replydata BLOB)").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS send_state_idx_scheduled_messages ON scheduled_messages(mid, send_state, date);").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS uid_date_idx_scheduled_messages ON scheduled_messages(uid, date);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 63").stepThis().dispose();
        r0 = 63;
    L187:
        if (r0 != 63) goto L190;
        r27.executeFast("DELETE FROM download_queue WHERE 1").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 64").stepThis().dispose();
        r0 = 64;
    L190:
        if (r0 != 64) goto L193;
        r27.executeFast("CREATE TABLE IF NOT EXISTS dialog_filter(id INTEGER PRIMARY KEY, ord INTEGER, unread_count INTEGER, flags INTEGER, title TEXT)").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS dialog_filter_ep(id INTEGER, peer INTEGER, PRIMARY KEY (id, peer))").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 65").stepThis().dispose();
        r0 = 65;
    L193:
        if (r0 != 65) goto L196;
        r27.executeFast("CREATE INDEX IF NOT EXISTS flags_idx_dialogs ON dialogs(flags);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 66").stepThis().dispose();
        r0 = 66;
    L196:
        if (r0 != 66) goto L199;
        r27.executeFast("CREATE TABLE dialog_filter_pin_v2(id INTEGER, peer INTEGER, pin INTEGER, PRIMARY KEY (id, peer))").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 67").stepThis().dispose();
        r0 = 67;
    L199:
        if (r0 != 67) goto L202;
        r27.executeFast("CREATE TABLE IF NOT EXISTS stickers_dice(emoji TEXT PRIMARY KEY, data BLOB, date INTEGER);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 68").stepThis().dispose();
        r0 = 68;
    L202:
        if (r0 != 68) goto L205;
        executeNoException(r27, "ALTER TABLE messages ADD COLUMN forwards INTEGER default 0");
        r27.executeFast("PRAGMA user_version = 69").stepThis().dispose();
        r0 = 69;
    L205:
        if (r0 != 69) goto L208;
        executeNoException(r27, "ALTER TABLE messages ADD COLUMN replies_data BLOB default NULL");
        executeNoException(r27, "ALTER TABLE messages ADD COLUMN thread_reply_id INTEGER default 0");
        r27.executeFast("PRAGMA user_version = 70").stepThis().dispose();
        r0 = 70;
    L208:
        if (r0 != 70) goto L211;
        r27.executeFast("CREATE TABLE IF NOT EXISTS chat_pinned_v2(uid INTEGER, mid INTEGER, data BLOB, PRIMARY KEY (uid, mid));").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 71").stepThis().dispose();
        r0 = 71;
    L211:
        if (r0 != 71) goto L214;
        executeNoException(r27, "ALTER TABLE sharing_locations ADD COLUMN proximity INTEGER default 0");
        r27.executeFast("PRAGMA user_version = 72").stepThis().dispose();
        r0 = 72;
    L214:
        if (r0 != 72) goto L217;
        r27.executeFast("CREATE TABLE IF NOT EXISTS chat_pinned_count(uid INTEGER PRIMARY KEY, count INTEGER, end INTEGER);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 73").stepThis().dispose();
        r0 = 73;
    L217:
        if (r0 != 73) goto L220;
        executeNoException(r27, "ALTER TABLE chat_settings_v2 ADD COLUMN inviter INTEGER default 0");
        r27.executeFast("PRAGMA user_version = 74").stepThis().dispose();
        r0 = 74;
    L220:
        if (r0 != 74) goto L223;
        r27.executeFast("CREATE TABLE IF NOT EXISTS shortcut_widget(id INTEGER, did INTEGER, ord INTEGER, PRIMARY KEY (id, did));").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS shortcut_widget_did ON shortcut_widget(did);").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 75").stepThis().dispose();
        r0 = 75;
    L223:
        if (r0 != 75) goto L226;
        executeNoException(r27, "ALTER TABLE chat_settings_v2 ADD COLUMN links INTEGER default 0");
        r27.executeFast("PRAGMA user_version = 76").stepThis().dispose();
        r0 = 76;
    L226:
        if (r0 != 76) goto L229;
        executeNoException(r27, "ALTER TABLE enc_tasks_v2 ADD COLUMN media INTEGER default -1");
        r27.executeFast("PRAGMA user_version = 77").stepThis().dispose();
        r0 = 77;
    L229:
        if (r0 != 77) goto L232;
        r27.executeFast("DROP TABLE IF EXISTS channel_admins_v2;").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS channel_admins_v3(did INTEGER, uid INTEGER, data BLOB, PRIMARY KEY(did, uid))").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 78").stepThis().dispose();
        r0 = 78;
    L232:
        if (r0 != 78) goto L234;
        r27.executeFast("DROP TABLE IF EXISTS bot_info;").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS bot_info_v2(uid INTEGER, dialogId INTEGER, info BLOB, PRIMARY KEY(uid, dialogId))").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 79").stepThis().dispose();
        r0 = 79;
    L234:
        int r104 = 3;
        if (r0 != 79) goto L240;
        r27.executeFast("CREATE TABLE IF NOT EXISTS enc_tasks_v3(mid INTEGER, date INTEGER, media INTEGER, PRIMARY KEY(mid, media))").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS date_idx_enc_tasks_v3 ON enc_tasks_v3(date);").stepThis().dispose();
        r27.beginTransaction();
        SQLiteCursor r04 = r27.queryFinalized("SELECT mid, date, media FROM enc_tasks_v2 WHERE 1", new Object[0]);
        SQLitePreparedStatement r92 = r27.executeFast("REPLACE INTO enc_tasks_v3 VALUES(?, ?, ?)");
        if (r04.next() == false) goto L239;
        long r113 = r04.longValue(0);
        int r14 = r04.intValue(1);
        int r152 = r04.intValue(2);
        r92.requery();
        r92.bindLong(1, r113);
        r92.bindInteger(2, r14);
        r92.bindInteger(3, r152);
        r92.step();
    L239:
        r92.dispose();
        r04.dispose();
        r27.commitTransaction();
        r27.executeFast("DROP INDEX IF EXISTS date_idx_enc_tasks_v2;").stepThis().dispose();
        r27.executeFast("DROP TABLE IF EXISTS enc_tasks_v2;").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 80").stepThis().dispose();
        r0 = 80;
    L240:
        int r114 = 5;
        if (r0 != 80) goto L265;
        r27.executeFast("CREATE TABLE IF NOT EXISTS scheduled_messages_v2(mid INTEGER, uid INTEGER, send_state INTEGER, date INTEGER, data BLOB, ttl INTEGER, replydata BLOB, PRIMARY KEY(mid, uid))").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS send_state_idx_scheduled_messages_v2 ON scheduled_messages_v2(mid, send_state, date);").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS uid_date_idx_scheduled_messages_v2 ON scheduled_messages_v2(uid, date);").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS bot_keyboard_idx_mid_v2 ON bot_keyboard(mid, uid);").stepThis().dispose();
        r27.executeFast("DROP INDEX IF EXISTS bot_keyboard_idx_mid;").stepThis().dispose();
        r27.beginTransaction();
        SQLiteCursor r05 = r27.queryFinalized("SELECT mid, uid, send_state, date, data, ttl, replydata FROM scheduled_messages_v2 WHERE 1", new Object[0]);     // Catch: Exception -> L245
    L247:
        if (r05 == null) goto L263;
        SQLitePreparedStatement r93 = r27.executeFast("REPLACE INTO scheduled_messages_v2 VALUES(?, ?, ?, ?, ?, ?, ?)");
    L250:
        if (r05.next() == false) goto L262;
        NativeByteBuffer r12 = r05.byteBufferValue(4);
        if (r12 == null) goto L250;
        int r142 = r05.intValue(r6);
        long r62 = r05.longValue(1);
        char r17 = r84;
        int r85 = r05.intValue(2);
        int r13 = r05.intValue(3);
        int r153 = r05.intValue(r114);
        NativeByteBuffer r115 = r05.byteBufferValue(6);
        r93.requery();
        r93.bindInteger(1, r142);
        r93.bindLong(2, r62);
        r93.bindInteger(3, r85);
        r93.bindByteBuffer(4, r12);
        r93.bindInteger(5, r13);
        r93.bindInteger(6, r153);
        if (r115 == null) goto L257;
        r93.bindByteBuffer(7, r115);
    L258:
        r93.step();
        if (r115 == null) goto L261;
        r115.reuse();
    L261:
        r12.reuse();
        r84 = r17;
        r6 = 0;
        r114 = 5;
        goto L250
    L257:
        r93.bindNull(7);
        goto L258
    L262:
        char r172 = r84;
        r05.dispose();
        r93.dispose();
    L264:
        r27.executeFast("DROP INDEX IF EXISTS send_state_idx_scheduled_messages;").stepThis().dispose();
        r27.executeFast("DROP INDEX IF EXISTS uid_date_idx_scheduled_messages;").stepThis().dispose();
        r27.executeFast("DROP TABLE IF EXISTS scheduled_messages;").stepThis().dispose();
        r27.commitTransaction();
        r27.executeFast("PRAGMA user_version = 81").stepThis().dispose();
        r0 = 81;
    L267:
        if (r0 != 81) goto L287;
        r27.executeFast("CREATE TABLE IF NOT EXISTS media_v3(mid INTEGER, uid INTEGER, date INTEGER, type INTEGER, data BLOB, PRIMARY KEY(mid, uid))").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS uid_mid_type_date_idx_media_v3 ON media_v3(uid, mid, type, date);").stepThis().dispose();
        r27.beginTransaction();
        SQLiteCursor r06 = r27.queryFinalized("SELECT mid, uid, date, type, data FROM media_v2 WHERE 1", new Object[0]);     // Catch: Exception -> L271
    L273:
        if (r06 == null) goto L285;
        SQLitePreparedStatement r63 = r27.executeFast("REPLACE INTO media_v3 VALUES(?, ?, ?, ?, ?)");
    L276:
        if (r06.next() == false) goto L284;
        NativeByteBuffer r72 = r06.byteBufferValue(4);
        if (r72 == null) goto L276;
        int r86 = r06.intValue(0);
        long r116 = r06.longValue(1);
        if (((int) r116) != 0) goto L283;
        r116 = DialogObject.makeEncryptedDialogId((int) (r116 >> r172));
    L283:
        int r94 = r06.intValue(2);
        int r132 = r06.intValue(3);
        r63.requery();
        r63.bindInteger(1, r86);
        r63.bindLong(2, r116);
        r63.bindInteger(3, r94);
        r63.bindInteger(4, r132);
        r63.bindByteBuffer(5, r72);
        r63.step();
        r72.reuse();
        goto L276
    L284:
        r06.dispose();
        r63.dispose();
    L285:
        r27.executeFast("DROP INDEX IF EXISTS uid_mid_type_date_idx_media;").stepThis().dispose();
        r27.executeFast("DROP TABLE IF EXISTS media_v2;").stepThis().dispose();
        r27.commitTransaction();
        r27.executeFast("PRAGMA user_version = 82").stepThis().dispose();
        r0 = 82;
    L271:
        e = move-exception;
        FileLog.e(e);
        r06 = null;
    L287:
        if (r0 != 82) goto L343;
        r27.executeFast("CREATE TABLE IF NOT EXISTS randoms_v2(random_id INTEGER, mid INTEGER, uid INTEGER, PRIMARY KEY (random_id, mid, uid))").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS mid_idx_randoms_v2 ON randoms_v2(mid, uid);").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS enc_tasks_v4(mid INTEGER, uid INTEGER, date INTEGER, media INTEGER, PRIMARY KEY(mid, uid, media))").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS date_idx_enc_tasks_v4 ON enc_tasks_v4(date);").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS polls_v2(mid INTEGER, uid INTEGER, id INTEGER, PRIMARY KEY (mid, uid));").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS polls_id_v2 ON polls_v2(id);").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS webpage_pending_v2(id INTEGER, mid INTEGER, uid INTEGER, PRIMARY KEY (id, mid, uid));").stepThis().dispose();
        r27.beginTransaction();
        SQLiteCursor r07 = r27.queryFinalized("SELECT r.random_id, r.mid, m.uid FROM randoms as r INNER JOIN messages as m ON r.mid = m.mid WHERE 1", new Object[0]);     // Catch: Exception -> L291
    L293:
        if (r07 == null) goto L721;
        SQLitePreparedStatement r64 = r27.executeFast("REPLACE INTO randoms_v2 VALUES(?, ?, ?)");
    L296:
        if (r07.next() == false) goto L301;
        long r73 = r07.longValue(0);
        int r95 = r07.intValue(1);
        long r117 = r07.longValue(2);
        if (((int) r117) != 0) goto L300;
        r117 = DialogObject.makeEncryptedDialogId((int) (r117 >> r172));
    L300:
        r64.requery();
        r64.bindLong(1, r73);
        r64.bindInteger(2, r95);
        r64.bindLong(3, r117);
        r64.step();
        goto L296
    L301:
        r07.dispose();
        r64.dispose();
    L721:
        SQLiteCursor r08 = r27.queryFinalized("SELECT p.mid, m.uid, p.id FROM polls as p INNER JOIN messages as m ON p.mid = m.mid WHERE 1", new Object[0]);     // Catch: Exception -> L304
    L306:
        if (r08 == null) goto L725;
        SQLitePreparedStatement r65 = r27.executeFast("REPLACE INTO polls_v2 VALUES(?, ?, ?)");
    L309:
        if (r08.next() == false) goto L314;
        int r74 = r08.intValue(0);
        long r87 = r08.longValue(1);
        long r118 = r08.longValue(2);
        if (((int) r87) != 0) goto L313;
        r87 = DialogObject.makeEncryptedDialogId((int) (r87 >> r172));
    L313:
        r65.requery();
        r65.bindInteger(1, r74);
        r65.bindLong(2, r87);
        r65.bindLong(3, r118);
        r65.step();
        goto L309
    L314:
        r08.dispose();
        r65.dispose();
    L725:
        SQLiteCursor r09 = r27.queryFinalized("SELECT wp.id, wp.mid, m.uid FROM webpage_pending as wp INNER JOIN messages as m ON wp.mid = m.mid WHERE 1", new Object[0]);     // Catch: Exception -> L317
    L319:
        if (r09 == null) goto L731;
        SQLitePreparedStatement r66 = r27.executeFast("REPLACE INTO webpage_pending_v2 VALUES(?, ?, ?)");
    L322:
        if (r09.next() == false) goto L327;
        long r75 = r09.longValue(0);
        int r96 = r09.intValue(1);
        long r119 = r09.longValue(2);
        if (((int) r119) != 0) goto L326;
        r119 = DialogObject.makeEncryptedDialogId((int) (r119 >> r172));
    L326:
        r66.requery();
        r66.bindLong(1, r75);
        r66.bindInteger(2, r96);
        r66.bindLong(3, r119);
        r66.step();
        goto L322
    L327:
        r09.dispose();
        r66.dispose();
    L731:
        SQLiteCursor r010 = r27.queryFinalized("SELECT et.mid, m.uid, et.date, et.media FROM enc_tasks_v3 as et INNER JOIN messages as m ON et.mid = m.mid WHERE 1", new Object[0]);     // Catch: Exception -> L330
    L332:
        if (r010 == null) goto L341;
        SQLitePreparedStatement r67 = r27.executeFast("REPLACE INTO enc_tasks_v4 VALUES(?, ?, ?, ?)");
    L335:
        if (r010.next() == false) goto L340;
        int r76 = r010.intValue(0);
        long r88 = r010.longValue(1);
        int r1110 = r010.intValue(2);
        int r122 = r010.intValue(3);
        if (((int) r88) != 0) goto L339;
        r88 = DialogObject.makeEncryptedDialogId((int) (r88 >> r172));
    L339:
        r67.requery();
        r67.bindInteger(1, r76);
        r67.bindLong(2, r88);
        r67.bindInteger(3, r1110);
        r67.bindInteger(4, r122);
        r67.step();
        goto L335
    L340:
        r010.dispose();
        r67.dispose();
    L341:
        r27.executeFast("DROP INDEX IF EXISTS mid_idx_randoms;").stepThis().dispose();
        r27.executeFast("DROP TABLE IF EXISTS randoms;").stepThis().dispose();
        r27.executeFast("DROP INDEX IF EXISTS date_idx_enc_tasks_v3;").stepThis().dispose();
        r27.executeFast("DROP TABLE IF EXISTS enc_tasks_v3;").stepThis().dispose();
        r27.executeFast("DROP INDEX IF EXISTS polls_id;").stepThis().dispose();
        r27.executeFast("DROP TABLE IF EXISTS polls;").stepThis().dispose();
        r27.executeFast("DROP TABLE IF EXISTS webpage_pending;").stepThis().dispose();
        r27.commitTransaction();
        r27.executeFast("PRAGMA user_version = 83").stepThis().dispose();
        r0 = 83;
    L330:
        e = move-exception;
        FileLog.e(e);
        r010 = null;
    L317:
        e = move-exception;
        FileLog.e(e);
        r09 = null;
    L304:
        e = move-exception;
        FileLog.e(e);
        r08 = null;
    L291:
        e = move-exception;
        FileLog.e(e);
        r07 = null;
    L343:
        if (r0 != 83) goto L422;
        r27.executeFast("CREATE TABLE IF NOT EXISTS messages_v2(mid INTEGER, uid INTEGER, read_state INTEGER, send_state INTEGER, date INTEGER, data BLOB, out INTEGER, ttl INTEGER, media INTEGER, replydata BLOB, imp INTEGER, mention INTEGER, forwards INTEGER, replies_data BLOB, thread_reply_id INTEGER, is_channel INTEGER, PRIMARY KEY(mid, uid))").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS uid_mid_read_out_idx_messages_v2 ON messages_v2(uid, mid, read_state, out);").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS uid_date_mid_idx_messages_v2 ON messages_v2(uid, date, mid);").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS mid_out_idx_messages_v2 ON messages_v2(mid, out);").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS task_idx_messages_v2 ON messages_v2(uid, out, read_state, ttl, date, send_state);").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS send_state_idx_messages_v2 ON messages_v2(mid, send_state, date);").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS uid_mention_idx_messages_v2 ON messages_v2(uid, mention, read_state);").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS is_channel_idx_messages_v2 ON messages_v2(mid, is_channel);").stepThis().dispose();
        r27.beginTransaction();
        SQLiteCursor r011 = r27.queryFinalized("SELECT mid, uid, read_state, send_state, date, data, out, ttl, media, replydata, imp, mention, forwards, replies_data, thread_reply_id FROM messages WHERE 1", new Object[0]);     // Catch: Exception -> L347
    L349:
        if (r011 == null) goto L392;
        SQLitePreparedStatement r68 = r27.executeFast("REPLACE INTO messages_v2 VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
    L352:
        if (r011.next() == false) goto L391;
        NativeByteBuffer r77 = r011.byteBufferValue(5);
        if (r77 == null) goto L352;
        long r89 = r011.intValue(0);
        long r1111 = r011.longValue(r5);
        if (((int) r1111) != 0) goto L359;
        r1111 = DialogObject.makeEncryptedDialogId((int) (r1111 >> r172));
    L359:
        int r133 = r011.intValue(r4);
        int r143 = r011.intValue(r104);
        int r154 = r011.intValue(r2);
        int r22 = r011.intValue(r3);
        int r105 = r011.intValue(7);
        int r42 = r011.intValue(8);
        NativeByteBuffer r52 = r011.byteBufferValue(9);
        int r1 = r011.intValue(10);
        int r16 = r011.intValue(11);
        int r18 = r011.intValue(12);
        NativeByteBuffer r19 = r011.byteBufferValue(13);
        int r110 = r011.intValue(14);
        SQLiteCursor r32 = r011;
        int r012 = (int) (r1111 >> r172);
        if (r105 >= 0) goto L369;
        TLRPC.Message r013 = TLRPC.Message.TLdeserialize(r77, r77.readInt32(false), false);
        if (r013 == null) goto L367;
        SQLiteCursor r25 = r32;
        int r24 = r42;
        r013.readAttachPath(r77, r26.getUserConfig().clientUserId);
        if (r013.params != null) goto L366;
        HashMap<String, String> r111 = new HashMap();
        r013.params = r111;
        r111.put("fwd_peer", _UrlKt.FRAGMENT_ENCODE_SET + r105);
    L366:
        r77.reuse();
        r77 = new NativeByteBuffer(r013.getObjectSize());
        r013.serializeToStream(r77);
    L368:
        r105 = 0;
    L370:
        r68.requery();
        r68.bindInteger(1, (int) r89);
        r68.bindLong(2, r1111);
        r68.bindInteger(3, r133);
        r68.bindInteger(4, r143);
        r68.bindInteger(5, r154);
        r68.bindByteBuffer(6, r77);
        r68.bindInteger(7, r22);
        r68.bindInteger(8, r105);
        r68.bindInteger(9, r24);
        if (r52 == null) goto L374;
        r68.bindByteBuffer(10, r52);
    L375:
        r68.bindInteger(11, r1);
        r68.bindInteger(12, r16);
        r68.bindInteger(13, r18);
        if (r19 == null) goto L379;
        NativeByteBuffer r014 = r19;
        r68.bindByteBuffer(14, r014);
    L380:
        r68.bindInteger(15, r110);
        if (r012 <= 0) goto L384;
        int r155 = 1;
    L385:
        r68.bindInteger(16, r155);
        r68.step();
        if (r52 == null) goto L388;
        r52.reuse();
    L388:
        if (r014 == null) goto L390;
        r014.reuse();
    L390:
        r77.reuse();
        r3 = 6;
        r011 = r25;
        r2 = 4;
        r4 = 2;
        r5 = 1;
        r104 = 3;
        goto L352
    L384:
        r155 = 0;
        goto L385
    L379:
        r014 = r19;
        r68.bindNull(14);
        goto L380
    L374:
        r68.bindNull(10);
        goto L375
    L367:
        r25 = r32;
        r24 = r42;
        goto L368
    L369:
        r25 = r32;
        r24 = r42;
        goto L370
    L391:
        r011.dispose();
        r68.dispose();
    L392:
        int r156 = 0;
        SQLiteDatabase r23 = r27;
        SQLiteCursor r015 = r23.queryFinalized("SELECT did, last_mid, last_mid_i FROM dialogs WHERE 1", new Object[0]);
        SQLitePreparedStatement r120 = r23.executeFast("UPDATE dialogs SET last_mid = ?, last_mid_i = ? WHERE did = ?");
        ArrayList r33 = null;
        ArrayList r43 = null;
    L394:
        if (r015.next() == false) goto L406;
        long r53 = r015.longValue(r156);
        int r78 = (int) r53;
        int r810 = (int) (r53 >> r172);
        if (r78 != 0) goto L401;
        if (r33 != null) goto L399;
        r33 = new ArrayList();
    L399:
        r33.add(Integer.valueOf(r810));
    L405:
        r120.requery();
        r120.bindInteger(1, r015.intValue(1));
        r120.bindInteger(2, r015.intValue(2));
        r120.bindLong(3, r53);
        r120.step();
        r156 = 0;
        goto L394
    L401:
        if (r810 != 2) goto L405;
        if (r43 != null) goto L404;
        r43 = new ArrayList();
    L404:
        r43.add(Integer.valueOf(r78));
        goto L405
    L406:
        r120.dispose();
        r015.dispose();
        int r157 = 0;
        SQLiteCursor r016 = r23.queryFinalized("SELECT uid, mid FROM unread_push_messages WHERE 1", new Object[0]);
        SQLitePreparedStatement r121 = r23.executeFast("UPDATE unread_push_messages SET mid = ? WHERE uid = ? AND mid = ?");
    L408:
        if (r016.next() == false) goto L410;
        long r54 = r016.longValue(r157);
        int r811 = r016.intValue(1);
        r121.requery();
        r121.bindInteger(1, r811);
        r121.bindLong(2, r54);
        r121.bindInteger(3, r811);
        r121.step();
        r157 = 0;
        goto L408
    L410:
        r121.dispose();
        r016.dispose();
        if (r33 == null) goto L416;
        SQLitePreparedStatement r017 = r23.executeFast("UPDATE dialogs SET did = ? WHERE did = ?");
        SQLitePreparedStatement r123 = r23.executeFast("UPDATE dialog_filter_pin_v2 SET peer = ? WHERE peer = ?");
        SQLitePreparedStatement r55 = r23.executeFast("UPDATE dialog_filter_ep SET peer = ? WHERE peer = ?");
        int r69 = r33.size();
        int r79 = 0;
    L413:
        if (r79 >= r69) goto L415;
        long r812 = ((Integer) r33.get(r79)).intValue();
        long r106 = DialogObject.makeEncryptedDialogId(r812);
        long r813 = r812 << r172;
        r017.requery();
        r017.bindLong(1, r106);
        r017.bindLong(2, r813);
        r017.step();
        r123.requery();
        r123.bindLong(1, r106);
        r123.bindLong(2, r813);
        r123.step();
        r55.requery();
        r55.bindLong(1, r106);
        r55.bindLong(2, r813);
        r55.step();
        r79 = r79 + 1;
        goto L413
    L415:
        r017.dispose();
        r123.dispose();
        r55.dispose();
    L416:
        if (r43 == null) goto L421;
        SQLitePreparedStatement r018 = r23.executeFast("UPDATE dialogs SET did = ? WHERE did = ?");
        int r124 = r43.size();
        int r34 = 0;
    L418:
        if (r34 >= r124) goto L420;
        int r56 = ((Integer) r43.get(r34)).intValue();
        long r610 = DialogObject.makeFolderDialogId(r56);
        r018.requery();
        r018.bindLong(1, r610);
        r018.bindLong(2, 8589934592L | r56);
        r018.step();
        r34 = r34 + 1;
        goto L418
    L420:
        r018.dispose();
    L421:
        r23.executeFast("DROP INDEX IF EXISTS uid_mid_read_out_idx_messages;").stepThis().dispose();
        r23.executeFast("DROP INDEX IF EXISTS uid_date_mid_idx_messages;").stepThis().dispose();
        r23.executeFast("DROP INDEX IF EXISTS mid_out_idx_messages;").stepThis().dispose();
        r23.executeFast("DROP INDEX IF EXISTS task_idx_messages;").stepThis().dispose();
        r23.executeFast("DROP INDEX IF EXISTS send_state_idx_messages2;").stepThis().dispose();
        r23.executeFast("DROP INDEX IF EXISTS uid_mention_idx_messages;").stepThis().dispose();
        r23.executeFast("DROP TABLE IF EXISTS messages;").stepThis().dispose();
        r23.commitTransaction();
        r23.executeFast("PRAGMA user_version = 84").stepThis().dispose();
        r0 = 84;
    L424:
        if (r0 != 84) goto L444;
        r23.executeFast("CREATE TABLE IF NOT EXISTS media_v4(mid INTEGER, uid INTEGER, date INTEGER, type INTEGER, data BLOB, PRIMARY KEY(mid, uid, type))").stepThis().dispose();
        r23.beginTransaction();
        SQLiteCursor r134 = r23.queryFinalized("SELECT mid, uid, date, type, data FROM media_v3 WHERE 1", new Object[0]);     // Catch: Exception -> L428
    L430:
        if (r134 == null) goto L442;
        SQLitePreparedStatement r019 = r23.executeFast("REPLACE INTO media_v4 VALUES(?, ?, ?, ?, ?)");
    L433:
        if (r134.next() == false) goto L441;
        NativeByteBuffer r35 = r134.byteBufferValue(4);
        if (r35 == null) goto L433;
        int r125 = r134.intValue(0);
        long r44 = r134.longValue(1);
        if (((int) r44) != 0) goto L440;
        r44 = DialogObject.makeEncryptedDialogId((int) (r44 >> r172));
    L440:
        int r611 = r134.intValue(2);
        int r814 = r134.intValue(3);
        r019.requery();
        r019.bindInteger(1, r125);
        r019.bindLong(2, r44);
        r019.bindInteger(3, r611);
        r019.bindInteger(4, r814);
        r019.bindByteBuffer(5, r35);
        r019.step();
        r35.reuse();
        goto L433
    L441:
        r134.dispose();
        r019.dispose();
    L442:
        r23.commitTransaction();
        r23.executeFast("DROP TABLE IF EXISTS media_v3;").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 85").stepThis().dispose();
        r0 = 85;
    L428:
        e = move-exception;
        FileLog.e(e);
        r134 = null;
    L444:
        if (r0 != 85) goto L447;
        executeNoException(r23, "ALTER TABLE messages_v2 ADD COLUMN reply_to_message_id INTEGER default 0");
        executeNoException(r23, "ALTER TABLE scheduled_messages_v2 ADD COLUMN reply_to_message_id INTEGER default 0");
        r23.executeFast("CREATE INDEX IF NOT EXISTS reply_to_idx_messages_v2 ON messages_v2(mid, reply_to_message_id);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS reply_to_idx_scheduled_messages_v2 ON scheduled_messages_v2(mid, reply_to_message_id);").stepThis().dispose();
        executeNoException(r23, "UPDATE messages_v2 SET replydata = NULL");
        executeNoException(r23, "UPDATE scheduled_messages_v2 SET replydata = NULL");
        r23.executeFast("PRAGMA user_version = 86").stepThis().dispose();
        r0 = 86;
    L447:
        if (r0 != 86) goto L450;
        r23.executeFast("CREATE TABLE IF NOT EXISTS reactions(data BLOB, hash INTEGER, date INTEGER);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 87").stepThis().dispose();
        r0 = 87;
    L450:
        if (r0 != 87) goto L453;
        r23.executeFast("ALTER TABLE dialogs ADD COLUMN unread_reactions INTEGER default 0").stepThis().dispose();
        r23.executeFast("CREATE TABLE reaction_mentions(message_id INTEGER PRIMARY KEY, state INTEGER);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 88").stepThis().dispose();
        r0 = 88;
    L453:
        if (r0 != 88) goto L455;
    L456:
        r23.executeFast("DROP TABLE IF EXISTS reaction_mentions;").stepThis().dispose();
        r23.executeFast("CREATE TABLE IF NOT EXISTS reaction_mentions(message_id INTEGER, state INTEGER, dialog_id INTEGER, PRIMARY KEY(dialog_id, message_id));").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS reaction_mentions_did ON reaction_mentions(dialog_id);").stepThis().dispose();
        r23.executeFast("DROP INDEX IF EXISTS uid_mid_type_date_idx_media_v3").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS uid_mid_type_date_idx_media_v4 ON media_v4(uid, mid, type, date);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 90").stepThis().dispose();
        r0 = 90;
    L458:
        if (r0 != 90) goto L460;
    L461:
        r23.executeFast("DROP TABLE IF EXISTS downloading_documents;").stepThis().dispose();
        r23.executeFast("CREATE TABLE downloading_documents(data BLOB, hash INTEGER, id INTEGER, state INTEGER, date INTEGER, PRIMARY KEY(hash, id));").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 92").stepThis().dispose();
        r0 = 92;
    L463:
        if (r0 != 92) goto L466;
        r23.executeFast("CREATE TABLE IF NOT EXISTS attach_menu_bots(data BLOB, hash INTEGER, date INTEGER);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 93").stepThis().dispose();
        r0 = 95;
    L466:
        if (r0 != 95) goto L468;
    L469:
        executeNoException(r23, "ALTER TABLE messages_v2 ADD COLUMN custom_params BLOB default NULL");
        r23.executeFast("PRAGMA user_version = 96").stepThis().dispose();
        r0 = 96;
    L471:
        if (r0 != 96) goto L474;
        r23.executeFast("CREATE TABLE IF NOT EXISTS premium_promo(data BLOB, date INTEGER);").stepThis().dispose();
        r23.executeFast("UPDATE stickers_v2 SET date = 0");
        r23.executeFast("PRAGMA user_version = 97").stepThis().dispose();
        r0 = 97;
    L474:
        if (r0 != 97) goto L477;
        r23.executeFast("DROP TABLE IF EXISTS stickers_featured;").stepThis().dispose();
        r23.executeFast("CREATE TABLE stickers_featured(id INTEGER PRIMARY KEY, data BLOB, unread BLOB, date INTEGER, hash INTEGER, premium INTEGER);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 98").stepThis().dispose();
        r0 = 98;
    L477:
        if (r0 != 98) goto L480;
        r23.executeFast("CREATE TABLE animated_emoji(document_id INTEGER PRIMARY KEY, data BLOB);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 99").stepThis().dispose();
        r0 = 99;
    L480:
        if (r0 != 99) goto L483;
        r23.executeFast("ALTER TABLE stickers_featured ADD COLUMN emoji INTEGER default 0").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 100").stepThis().dispose();
        r0 = 100;
    L483:
        if (r0 != 100) goto L486;
        r23.executeFast("CREATE TABLE emoji_statuses(data BLOB, type INTEGER);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 101").stepThis().dispose();
        r0 = 101;
    L486:
        if (r0 != 101) goto L489;
        r23.executeFast("ALTER TABLE messages_v2 ADD COLUMN group_id INTEGER default NULL").stepThis().dispose();
        r23.executeFast("ALTER TABLE dialogs ADD COLUMN last_mid_group INTEGER default NULL").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS uid_mid_groupid_messages_v2 ON messages_v2(uid, mid, group_id);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 102").stepThis().dispose();
        r0 = 102;
    L489:
        if (r0 != 102) goto L492;
        r23.executeFast("CREATE TABLE messages_holes_topics(uid INTEGER, topic_id INTEGER, start INTEGER, end INTEGER, PRIMARY KEY(uid, topic_id, start));").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS uid_end_messages_holes ON messages_holes_topics(uid, topic_id, end);").stepThis().dispose();
        r23.executeFast("CREATE TABLE messages_topics(mid INTEGER, uid INTEGER, topic_id INTEGER, read_state INTEGER, send_state INTEGER, date INTEGER, data BLOB, out INTEGER, ttl INTEGER, media INTEGER, replydata BLOB, imp INTEGER, mention INTEGER, forwards INTEGER, replies_data BLOB, thread_reply_id INTEGER, is_channel INTEGER, reply_to_message_id INTEGER, custom_params BLOB, PRIMARY KEY(mid, topic_id, uid))").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS uid_mid_read_out_idx_messages_topics ON messages_topics(uid, mid, read_state, out);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS uid_date_mid_idx_messages_topics ON messages_topics(uid, date, mid);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS mid_out_idx_messages_topics ON messages_topics(mid, out);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS task_idx_messages_topics ON messages_topics(uid, out, read_state, ttl, date, send_state);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS send_state_idx_messages_topics ON messages_topics(mid, send_state, date);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS uid_mention_idx_messages_topics ON messages_topics(uid, mention, read_state);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS is_channel_idx_messages_topics ON messages_topics(mid, is_channel);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS reply_to_idx_messages_topics ON messages_topics(mid, reply_to_message_id);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS mid_uid_messages_topics ON messages_topics(mid, uid);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS mid_uid_topic_id_messages_topics ON messages_topics(mid, topic_id, uid);").stepThis().dispose();
        r23.executeFast("CREATE TABLE media_topics(mid INTEGER, uid INTEGER, topic_id INTEGER, date INTEGER, type INTEGER, data BLOB, PRIMARY KEY(mid, uid, topic_id, type))").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS uid_mid_type_date_idx_media_topics ON media_topics(uid, topic_id, mid, type, date);").stepThis().dispose();
        r23.executeFast("CREATE TABLE media_holes_topics(uid INTEGER, topic_id INTEGER, type INTEGER, start INTEGER, end INTEGER, PRIMARY KEY(uid, topic_id, type, start));").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS uid_end_media_holes_topics ON media_holes_topics(uid, topic_id, type, end);").stepThis().dispose();
        r23.executeFast("CREATE TABLE topics(did INTEGER, topic_id INTEGER, data BLOB, top_message INTEGER, topic_message BLOB, unread_count INTEGER, max_read_id INTEGER, unread_mentions INTEGER, unread_reactions INTEGER, PRIMARY KEY(did, topic_id));").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS did_top_message_topics ON topics(did, top_message);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 103").stepThis().dispose();
        r0 = 103;
    L492:
        if (r0 != 103) goto L495;
        r23.executeFast("CREATE TABLE IF NOT EXISTS media_counts_topics(uid INTEGER, topic_id INTEGER, type INTEGER, count INTEGER, old INTEGER, PRIMARY KEY(uid, topic_id, type))").stepThis().dispose();
        r23.executeFast("CREATE TABLE IF NOT EXISTS reaction_mentions_topics(message_id INTEGER, state INTEGER, dialog_id INTEGER, topic_id INTEGER, PRIMARY KEY(message_id, dialog_id, topic_id))").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS reaction_mentions_topics_did ON reaction_mentions_topics(dialog_id, topic_id);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 104").stepThis().dispose();
        r0 = 104;
    L495:
        if (r0 != 104) goto L498;
        r23.executeFast("ALTER TABLE topics ADD COLUMN read_outbox INTEGER default 0").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 105").stepThis().dispose();
        r0 = 105;
    L498:
        if (r0 != 105) goto L501;
        r23.executeFast("ALTER TABLE topics ADD COLUMN pinned INTEGER default 0").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 106").stepThis().dispose();
        r0 = 106;
    L501:
        if (r0 != 106) goto L504;
        r23.executeFast("DROP INDEX IF EXISTS uid_mid_read_out_idx_messages_topics").stepThis().dispose();
        r23.executeFast("DROP INDEX IF EXISTS uid_mention_idx_messages_topics").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS uid_mid_read_out_idx_messages_topics ON messages_topics(uid, topic_id, mid, read_state, out);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS uid_mention_idx_messages_topics ON messages_topics(uid, topic_id, mention, read_state);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS uid_topic_id_messages_topics ON messages_topics(uid, topic_id);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS uid_topic_id_date_mid_messages_topics ON messages_topics(uid, topic_id, date, mid);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS uid_topic_id_mid_messages_topics ON messages_topics(uid, topic_id, mid);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS did_topics ON topics(did);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 107").stepThis().dispose();
        r0 = 107;
    L504:
        if (r0 != 107) goto L507;
        r23.executeFast("ALTER TABLE topics ADD COLUMN total_messages_count INTEGER default 0").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 108").stepThis().dispose();
        r0 = 108;
    L507:
        if (r0 != 108) goto L510;
        r23.executeFast("ALTER TABLE topics ADD COLUMN hidden INTEGER default 0").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 109").stepThis().dispose();
        r0 = 109;
    L510:
        if (r0 != 109) goto L513;
        r23.executeFast("ALTER TABLE dialogs ADD COLUMN ttl_period INTEGER default 0").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 110").stepThis().dispose();
        r0 = 110;
    L513:
        if (r0 != 110) goto L516;
        r23.executeFast("CREATE TABLE stickersets(id INTEGER PRIMATE KEY, data BLOB, hash INTEGER);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 111").stepThis().dispose();
        r0 = 111;
    L516:
        if (r0 != 111) goto L519;
        r23.executeFast("CREATE TABLE emoji_groups(type INTEGER PRIMARY KEY, data BLOB)").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 112").stepThis().dispose();
        r0 = 112;
    L519:
        if (r0 != 112) goto L522;
        r23.executeFast("CREATE TABLE app_config(data BLOB)").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 113").stepThis().dispose();
        r0 = 113;
    L522:
        if (r0 != 113) goto L525;
        r26.reset();
        r23.executeFast("PRAGMA user_version = 114").stepThis().dispose();
        r0 = 114;
    L525:
        if (r0 != 114) goto L528;
        r23.executeFast("CREATE TABLE bot_keyboard_topics(uid INTEGER, tid INTEGER, mid INTEGER, info BLOB, PRIMARY KEY(uid, tid))").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS bot_keyboard_topics_idx_mid_v2 ON bot_keyboard_topics(mid, uid, tid);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 115").stepThis().dispose();
        r0 = 115;
    L528:
        if (r0 != 115) goto L531;
        r23.executeFast("CREATE INDEX IF NOT EXISTS idx_to_reply_messages_v2 ON messages_v2(reply_to_message_id, mid);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS idx_to_reply_scheduled_messages_v2 ON scheduled_messages_v2(reply_to_message_id, mid);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS idx_to_reply_messages_topics ON messages_topics(reply_to_message_id, mid);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 117").stepThis().dispose();
        r0 = 117;
    L531:
        if (r0 != 116) goto L533;
    L536:
        r23.executeFast("DROP TABLE IF EXISTS stories").stepThis().dispose();
        r23.executeFast("DROP TABLE IF EXISTS stories_counter").stepThis().dispose();
        r23.executeFast("CREATE TABLE stories (dialog_id INTEGER, story_id INTEGER, data BLOB, local_path TEXT, local_thumb_path TEXT, PRIMARY KEY (dialog_id, story_id));").stepThis().dispose();
        r23.executeFast("CREATE TABLE stories_counter (dialog_id INTEGER PRIMARY KEY, count INTEGER, max_read INTEGER);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 119").stepThis().dispose();
        r26.getMessagesController().getStoriesController().cleanup();
        r0 = 119;
    L538:
        if (r0 != 119) goto L541;
        r23.executeFast("ALTER TABLE messages_v2 ADD COLUMN reply_to_story_id INTEGER default 0").stepThis().dispose();
        r23.executeFast("ALTER TABLE messages_topics ADD COLUMN reply_to_story_id INTEGER default 0").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 120").stepThis().dispose();
        r0 = 120;
    L541:
        if (r0 != 120) goto L544;
        r23.executeFast("CREATE TABLE profile_stories (dialog_id INTEGER, story_id INTEGER, data BLOB, PRIMARY KEY(dialog_id, story_id));").stepThis().dispose();
        r23.executeFast("CREATE TABLE archived_stories (story_id INTEGER PRIMARY KEY, data BLOB);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 121").stepThis().dispose();
        r0 = 121;
    L544:
        if (r0 != 121) goto L547;
        r23.executeFast("CREATE TABLE story_drafts (id INTEGER PRIMARY KEY, date INTEGER, data BLOB);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 122").stepThis().dispose();
        r0 = 122;
    L547:
        if (r0 != 122) goto L550;
        r23.executeFast("ALTER TABLE chat_settings_v2 ADD COLUMN participants_count INTEGER default 0").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 123").stepThis().dispose();
        r0 = 123;
    L550:
        if (r0 != 123) goto L553;
        r23.executeFast("CREATE TABLE story_pushes (uid INTEGER PRIMARY KEY, minId INTEGER, maxId INTEGER, date INTEGER, localName TEXT);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 124").stepThis().dispose();
        r0 = 124;
    L553:
        if (r0 != 124) goto L556;
        r23.executeFast("DROP TABLE IF EXISTS story_pushes;").stepThis().dispose();
        r23.executeFast("CREATE TABLE story_pushes (uid INTEGER, sid INTEGER, date INTEGER, localName TEXT, PRIMARY KEY(uid, sid));").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 125").stepThis().dispose();
        r0 = 125;
    L556:
        if (r0 != 125) goto L559;
        r23.executeFast("ALTER TABLE story_pushes ADD COLUMN flags INTEGER default 0").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 126").stepThis().dispose();
        r0 = 126;
    L559:
        if (r0 != 126) goto L562;
        r23.executeFast("ALTER TABLE story_pushes ADD COLUMN expire_date INTEGER default 0").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 127").stepThis().dispose();
        r0 = 127;
    L562:
        if (r0 != 127) goto L565;
        r23.executeFast("ALTER TABLE stories ADD COLUMN custom_params BLOB default NULL").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 128").stepThis().dispose();
        r0 = 128;
    L565:
        if (r0 != 128) goto L568;
        r23.executeFast("ALTER TABLE story_drafts ADD COLUMN type INTEGER default 0").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 129").stepThis().dispose();
        r0 = 129;
    L568:
        if (r0 != 129) goto L571;
        r23.executeFast("CREATE INDEX IF NOT EXISTS stickers_featured_emoji_index ON stickers_featured(emoji);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 130").stepThis().dispose();
        r0 = 130;
    L571:
        if (r0 != 130) goto L574;
        r23.executeFast("DROP TABLE archived_stories").stepThis().dispose();
        r23.executeFast("ALTER TABLE profile_stories ADD COLUMN type INTEGER default 0").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 131").stepThis().dispose();
        r0 = 131;
    L574:
        if (r0 != 131) goto L577;
        r23.executeFast("ALTER TABLE stories DROP COLUMN local_path").stepThis().dispose();
        r23.executeFast("ALTER TABLE stories DROP COLUMN local_thumb_path").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 132").stepThis().dispose();
        r0 = 132;
    L577:
        if (r0 != 132) goto L580;
        r23.executeFast("CREATE TABLE unconfirmed_auth (data BLOB);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 133").stepThis().dispose();
        r0 = 133;
    L580:
        if (r0 != 133) goto L583;
        r23.executeFast("ALTER TABLE unread_push_messages ADD COLUMN topicId INTEGER default 0").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 134").stepThis().dispose();
        r0 = 134;
    L583:
        if (r0 != 134) goto L586;
        r23.executeFast("DROP TABLE user_photos").stepThis().dispose();
        r23.executeFast("CREATE TABLE dialog_photos(uid INTEGER, id INTEGER, num INTEGER, data BLOB, PRIMARY KEY (uid, id))").stepThis().dispose();
        r23.executeFast("CREATE TABLE dialog_photos_count(uid INTEGER PRIMARY KEY, count INTEGER)").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 135").stepThis().dispose();
        r0 = 135;
    L586:
        if (r0 != 135) goto L592;
        if (ApplicationLoader.isAndroidTestEnvironment() == false) goto L590;
        r23.executeFast("DROP TABLE stickersets").stepThis().dispose();
    L590:
        r23.executeFast("CREATE TABLE stickersets2(id INTEGER PRIMATE KEY, data BLOB, hash INTEGER, date INTEGER);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS stickersets2_id_index ON stickersets2(id);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 136").stepThis().dispose();
        r0 = 136;
    L592:
        if (r0 != 136) goto L595;
        r23.executeFast("CREATE TABLE saved_dialogs(did INTEGER PRIMARY KEY, date INTEGER, last_mid INTEGER, pinned INTEGER, flags INTEGER, folder_id INTEGER, last_mid_group INTEGER, count INTEGER)").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS date_idx_dialogs ON saved_dialogs(date);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS last_mid_idx_dialogs ON saved_dialogs(last_mid);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS folder_id_idx_dialogs ON saved_dialogs(folder_id);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS flags_idx_dialogs ON saved_dialogs(flags);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 137").stepThis().dispose();
        r0 = 137;
    L595:
        if (r0 != 137) goto L598;
        r23.executeFast("ALTER TABLE unread_push_messages ADD COLUMN is_reaction INTEGER default 0").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 138").stepThis().dispose();
        r0 = 138;
    L598:
        if (r0 != 138) goto L600;
    L605:
        r23.executeFast("DROP TABLE IF EXISTS tag_message_id;").stepThis().dispose();
        r23.executeFast("CREATE TABLE tag_message_id(mid INTEGER, topic_id INTEGER, tag INTEGER, text TEXT);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS tag_idx_tag_message_id ON tag_message_id(tag);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS tag_text_idx_tag_message_id ON tag_message_id(tag, text COLLATE NOCASE);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS tag_topic_idx_tag_message_id ON tag_message_id(topic_id, tag);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS tag_topic_text_idx_tag_message_id ON tag_message_id(topic_id, tag, text COLLATE NOCASE);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 142").stepThis().dispose();
        r0 = 142;
    L607:
        if (r0 != 142) goto L610;
        r23.executeFast("DROP TABLE IF EXISTS saved_reaction_tags;").stepThis().dispose();
        r23.executeFast("CREATE TABLE saved_reaction_tags (topic_id INTEGER PRIMARY KEY, data BLOB);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 143").stepThis().dispose();
        r0 = 143;
    L610:
        if (r0 != 143) goto L613;
        r23.executeFast("DROP TABLE IF EXISTS dialog_filter_extera;").stepThis().dispose();
        r23.executeFast("DROP TABLE IF EXISTS dialog_filter;").stepThis().dispose();
        r23.executeFast("CREATE TABLE dialog_filter(id INTEGER PRIMARY KEY, ord INTEGER, unread_count INTEGER, flags INTEGER, title TEXT, color INTEGER DEFAULT -1, emoticon TEXT)").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 144").stepThis().dispose();
        r26.getUserConfig().clearFilters();
        r0 = 144;
    L613:
        if (r0 != 144) goto L616;
        r23.executeFast("PRAGMA user_version = 145").stepThis().dispose();
        r0 = 145;
    L616:
        if (r0 != 145) goto L619;
        r23.executeFast("CREATE TABLE business_replies(topic_id INTEGER PRIMARY KEY, name TEXT, order_value INTEGER);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 146").stepThis().dispose();
        r0 = 146;
    L619:
        if (r0 != 146) goto L622;
        r23.executeFast("CREATE TABLE quick_replies_messages(mid INTEGER, topic_id INTEGER, send_state INTEGER, date INTEGER, data BLOB, ttl INTEGER, replydata BLOB, reply_to_message_id INTEGER, PRIMARY KEY(mid, topic_id))").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS send_state_idx_quick_replies_messages ON quick_replies_messages(mid, send_state, date);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS topic_date_idx_quick_replies_messages ON quick_replies_messages(topic_id, date);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS reply_to_idx_quick_replies_messages ON quick_replies_messages(mid, reply_to_message_id);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS idx_to_reply_quick_replies_messages ON quick_replies_messages(reply_to_message_id, mid);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 147").stepThis().dispose();
        r0 = 147;
    L622:
        if (r0 != 147) goto L625;
        r23.executeFast("ALTER TABLE business_replies ADD COLUMN count INTEGER default 0").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 148").stepThis().dispose();
        r0 = 148;
    L625:
        if (r0 != 148) goto L628;
        r23.executeFast("ALTER TABLE topics ADD COLUMN edit_date INTEGER default 0").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 149").stepThis().dispose();
        r0 = 149;
    L628:
        if (r0 != 149) goto L631;
        r23.executeFast("ALTER TABLE stickersets2 ADD COLUMN short_name TEXT;").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS stickersets2_id_short_name ON stickersets2(id, short_name);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 150").stepThis().dispose();
        r0 = 150;
    L631:
        if (r0 != 150) goto L634;
        r23.executeFast("CREATE TABLE business_links(data BLOB, order_value INTEGER);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 151").stepThis().dispose();
        r0 = 151;
    L634:
        if (r0 != 151) goto L637;
        r23.executeFast("ALTER TABLE profile_stories ADD COLUMN seen INTEGER default 0;").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 152").stepThis().dispose();
        r0 = 152;
    L637:
        if (r0 != 152) goto L640;
        r23.executeFast("ALTER TABLE profile_stories ADD COLUMN pin INTEGER default 0;").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 153").stepThis().dispose();
        r0 = 153;
    L640:
        if (r0 != 153) goto L643;
        r23.executeFast("CREATE TABLE effects(data BLOB)").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 154").stepThis().dispose();
        r0 = 154;
    L643:
        if (r0 != 154) goto L646;
        r23.executeFast("CREATE TABLE fact_checks(hash INTEGER PRIMARY KEY, data BLOB, expires INTEGER);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 155").stepThis().dispose();
        r0 = 155;
    L646:
        if (r0 != 155) goto L649;
        r23.executeFast("CREATE TABLE popular_bots(uid INTEGER PRIMARY KEY, time INTEGER, offset TEXT);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 156").stepThis().dispose();
        r0 = 156;
    L649:
        if (r0 != 156) goto L651;
    L652:
        r23.executeFast("CREATE TABLE star_gifts2(id INTEGER PRIMARY KEY, data BLOB, hash INTEGER, time INTEGER);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 158").stepThis().dispose();
        r0 = 158;
    L654:
        if (r0 != 158) goto L657;
        r23.executeFast("DELETE FROM star_gifts2").stepThis().dispose();
        r23.executeFast("ALTER TABLE star_gifts2 ADD COLUMN pos INTEGER default 0;").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 159").stepThis().dispose();
        r0 = 159;
    L657:
        if (r0 != 159) goto L660;
        r23.executeFast("ALTER TABLE dialog_filter ADD COLUMN entities BLOB").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 160").stepThis().dispose();
        r0 = 160;
    L660:
        if (r0 != 160) goto L663;
        r23.executeFast("ALTER TABLE dialog_filter ADD COLUMN noanimate INTEGER").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 161").stepThis().dispose();
        r0 = 161;
    L663:
        if (r0 != 161) goto L666;
        r23.executeFast("DELETE FROM popular_bots").stepThis().dispose();
        r23.executeFast("ALTER TABLE popular_bots ADD COLUMN pos INTEGER").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 162").stepThis().dispose();
        r0 = 162;
    L666:
        if (r0 != 162) goto L669;
        r23.executeFast("DROP TABLE saved_dialogs").stepThis().dispose();
        r23.executeFast("CREATE TABLE saved_dialogs(did INTEGER, date INTEGER, last_mid INTEGER, pinned INTEGER, flags INTEGER, folder_id INTEGER, last_mid_group INTEGER, count INTEGER, forumChatId INTEGER, PRIMARY KEY (did, forumChatId))").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS date_idx_dialogs ON saved_dialogs(date);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS last_mid_idx_dialogs ON saved_dialogs(last_mid);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS folder_id_idx_dialogs ON saved_dialogs(folder_id);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS flags_idx_dialogs ON saved_dialogs(flags);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS forum_idx_dialogs ON saved_dialogs(forumChatId);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 163").stepThis().dispose();
        r0 = 163;
    L669:
        if (r0 != 163) goto L672;
        r23.executeFast("DROP TABLE saved_dialogs").stepThis().dispose();
        r23.executeFast("CREATE TABLE saved_dialogs(did INTEGER, date INTEGER, last_mid INTEGER, pinned INTEGER, flags INTEGER, folder_id INTEGER, last_mid_group INTEGER, count INTEGER, forumChatId INTEGER, unread_count INTEGER, max_read_id INTEGER, read_outbox INTEGER, PRIMARY KEY (did, forumChatId))").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS date_idx_dialogs ON saved_dialogs(date);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS last_mid_idx_dialogs ON saved_dialogs(last_mid);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS folder_id_idx_dialogs ON saved_dialogs(folder_id);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS flags_idx_dialogs ON saved_dialogs(flags);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS forum_idx_dialogs ON saved_dialogs(forumChatId);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 164").stepThis().dispose();
        r0 = 164;
    L672:
        if (r0 != 164) goto L675;
        r23.executeFast("ALTER TABLE topics ADD COLUMN nopaid_messages_exception INTEGER default 0;").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 165").stepThis().dispose();
        r0 = 165;
    L675:
        if (r0 != 165) goto L678;
        r23.executeFast("CREATE TABLE profile_stories_albums (dialog_id INTEGER, album_id INTEGER, order_index INTEGER, data BLOB, PRIMARY KEY(dialog_id, album_id));").stepThis().dispose();
        r23.executeFast("CREATE TABLE profile_stories_albums_links (dialog_id INTEGER, album_id INTEGER, story_id INTEGER, order_index INTEGER, PRIMARY KEY (dialog_id, album_id, story_id));").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 166").stepThis().dispose();
        r0 = 166;
    L678:
        if (r0 != 166) goto L681;
        r23.executeFast("DROP TABLE profile_stories").stepThis().dispose();
        r23.executeFast("CREATE TABLE profile_stories (dialog_id INTEGER, story_id INTEGER, data BLOB, type INTEGER, seen INTEGER, pin INTEGER, PRIMARY KEY(dialog_id, story_id, type));").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 167").stepThis().dispose();
        r0 = 167;
    L681:
        if (r0 != 167) goto L684;
        r23.executeFast("CREATE TABLE gift_themes (slug TEXT PRIMARY KEY, data BLOB);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 168").stepThis().dispose();
        r0 = 168;
    L684:
        if (r0 != 168) goto L687;
        r23.executeFast("ALTER TABLE dialogs ADD COLUMN unread_poll_votes INTEGER default 0").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 169").stepThis().dispose();
        r0 = 169;
    L687:
        if (r0 != 169) goto L690;
        r23.executeFast("ALTER TABLE topics ADD COLUMN unread_poll_votes INTEGER default 0").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 170").stepThis().dispose();
        r0 = 170;
    L690:
        if (r0 != 170) goto L693;
        r23.executeFast("CREATE TABLE IF NOT EXISTS poll_votes_mentions(message_id INTEGER, state INTEGER, dialog_id INTEGER, PRIMARY KEY(message_id, dialog_id))").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS poll_votes_mentions_did ON poll_votes_mentions(dialog_id);").stepThis().dispose();
        r23.executeFast("CREATE TABLE IF NOT EXISTS poll_votes_mentions_topics(message_id INTEGER, state INTEGER, dialog_id INTEGER, topic_id INTEGER, PRIMARY KEY(message_id, dialog_id, topic_id))").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS poll_votes_mentions_topics_did ON poll_votes_mentions_topics(dialog_id, topic_id);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 171").stepThis().dispose();
        r0 = 171;
    L693:
        if (r0 != 171) goto L696;
        r23.executeFast("DROP TABLE story_pushes").stepThis().dispose();
        r23.executeFast("CREATE TABLE story_pushes (uid INTEGER, sid INTEGER, date INTEGER, localName TEXT, flags INTEGER, expire_date INTEGER, live INTEGER, PRIMARY KEY(uid, sid));").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 172").stepThis().dispose();
        r0 = 172;
    L696:
        if (r0 != 172) goto L699;
        r23.executeFast("CREATE INDEX IF NOT EXISTS uid_end_messages_holes_4_dialogs ON messages_holes(uid, end);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS uid_end_messages_holes_4_topics ON messages_holes_topics(uid, topic_id, end);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS date_idx_4_saved_dialogs ON saved_dialogs(date);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS date_idx_4_dialogs ON dialogs(date);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS last_mid_idx_4_saved_dialogs ON saved_dialogs(last_mid);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS last_mid_idx_4_dialogs ON dialogs(last_mid);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS folder_id_idx_4_saved_dialogs ON saved_dialogs(folder_id);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS folder_id_idx_4_dialogs ON dialogs(folder_id);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS flags_idx_4_saved_dialogs ON saved_dialogs(flags);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS flags_idx_4_dialogs ON dialogs(flags);").stepThis().dispose();
        r23.executeFast("DROP INDEX IF EXISTS uid_end_messages_holes;").stepThis().dispose();
        r23.executeFast("DROP INDEX IF EXISTS date_idx_dialogs;").stepThis().dispose();
        r23.executeFast("DROP INDEX IF EXISTS last_mid_idx_dialogs;").stepThis().dispose();
        r23.executeFast("DROP INDEX IF EXISTS folder_id_idx_dialogs;").stepThis().dispose();
        r23.executeFast("DROP INDEX IF EXISTS flags_idx_dialogs;").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 173").stepThis().dispose();
        r0 = 173;
    L699:
        if (r0 != 173) goto L702;
        r23.executeFast("CREATE TABLE web_browser_settings(data BLOB)").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 174").stepThis().dispose();
        r0 = 174;
    L702:
        if (r0 != 174) goto L705;
        r23.executeFast("CREATE INDEX IF NOT EXISTS uid_type_date_mid_idx_media_v4 ON media_v4(uid, type, date DESC, mid DESC);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 175").stepThis().dispose();
        r0 = 175;
    L705:
        if (r0 != 175) goto L708;
        r23.executeFast("CREATE TABLE ephemeral_messages (id INTEGER, dialog_id INTEGER, topic_id INTEGER, date INTEGER, data BLOB, PRIMARY KEY(dialog_id, id));").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS ephemeral_messages_date_idx ON ephemeral_messages(date);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 176").stepThis().dispose();
        r0 = 176;
    L708:
        if (r0 != 176) goto L711;
        r23.executeFast("CREATE TABLE welcome_messages(mid INTEGER, dialog_id INTEGER, send_state INTEGER, date INTEGER, data BLOB, ttl INTEGER, replydata BLOB, reply_to_message_id INTEGER, PRIMARY KEY(mid, dialog_id))").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS send_state_idx_welcome_messages ON welcome_messages(mid, send_state, date);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS dialog_date_idx_welcome_messages ON welcome_messages(dialog_id, date);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS reply_to_idx_welcome_messages ON welcome_messages(mid, reply_to_message_id);").stepThis().dispose();
        r23.executeFast("CREATE INDEX IF NOT EXISTS idx_to_reply_welcome_messages ON welcome_messages(reply_to_message_id, mid);").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 177").stepThis().dispose();
        r0 = 177;
    L711:
        if (r0 != 177) goto L714;
        r23.executeFast("CREATE INDEX IF NOT EXISTS media_v4_music_browse_idx ON media_v4(uid, date DESC, mid DESC) WHERE type = 4 AND mid > 0 AND uid != 0;").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 178").stepThis().dispose();
        r0 = 178;
    L714:
        if (r0 != 178) goto L777;
        r23.executeFast("DELETE FROM downloading_documents").stepThis().dispose();
        r23.executeFast("PRAGMA user_version = 179").stepThis().dispose();
        return 179;
    L777:
        return r0;
    L651:
        if (r0 != 157) goto L654;
    L600:
        if (r0 == 139) goto L605;
        if (r0 == 140) goto L605;
        if (r0 != 141) goto L607;
    L533:
        if (r0 == 117) goto L536;
        if (r0 != 118) goto L538;
    L468:
        if (r0 != 93) goto L471;
    L460:
        if (r0 != 91) goto L463;
    L455:
        if (r0 != 89) goto L458;
    L347:
        e = move-exception;
        FileLog.e(e);
        r011 = null;
        goto L349
    L422:
        r23 = r27;
        goto L424
    L263:
        r172 = ' ';
    L245:
        e = move-exception;
        FileLog.e(e);
        r05 = null;
        goto L247
    L265:
        r172 = ' ';
        goto L267
    L169:
        if (r0 != 57) goto L172;
    L87:
        if (r0 != 29) goto L90;
    L79:
        if (r0 != 26) goto L82;
    L74:
        if (r0 != 24) goto L77;
    L31:
        r27.executeFast("DROP INDEX IF EXISTS uid_mid_idx_media;").stepThis().dispose();
        r27.executeFast("DROP INDEX IF EXISTS mid_idx_media;").stepThis().dispose();
        r27.executeFast("DROP INDEX IF EXISTS uid_date_mid_idx_media;").stepThis().dispose();
        r27.executeFast("DROP TABLE IF EXISTS media;").stepThis().dispose();
        r27.executeFast("DROP TABLE IF EXISTS media_counts;").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS media_v2(mid INTEGER PRIMARY KEY, uid INTEGER, date INTEGER, type INTEGER, data BLOB)").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS media_counts_v2(uid INTEGER, type INTEGER, count INTEGER, PRIMARY KEY(uid, type))").stepThis().dispose();
        r27.executeFast("CREATE INDEX IF NOT EXISTS uid_mid_type_date_idx_media ON media_v2(uid, mid, type, date);").stepThis().dispose();
        r27.executeFast("CREATE TABLE IF NOT EXISTS keyvalue(id TEXT PRIMARY KEY, value TEXT)").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 13").stepThis().dispose();
        r0 = 13;
    L24:
        r27.executeFast("ALTER TABLE enc_chats ADD COLUMN use_count INTEGER default 0").stepThis().dispose();
        r27.executeFast("ALTER TABLE enc_chats ADD COLUMN exchange_id INTEGER default 0").stepThis().dispose();
        r27.executeFast("ALTER TABLE enc_chats ADD COLUMN key_date INTEGER default 0").stepThis().dispose();
        r27.executeFast("ALTER TABLE enc_chats ADD COLUMN fprint INTEGER default 0").stepThis().dispose();
        r27.executeFast("ALTER TABLE enc_chats ADD COLUMN fauthkey BLOB default NULL").stepThis().dispose();
        r27.executeFast("ALTER TABLE enc_chats ADD COLUMN khash BLOB default NULL").stepThis().dispose();
        r27.executeFast("PRAGMA user_version = 10").stepThis().dispose();
        r0 = 10;
        goto L26
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean recoverDatabase(File r22, File r23, File r24, int r25) {
        File r3 = new File(ApplicationLoader.getFilesDirFixed(), "recover_database_" + r25 + "/");
        r3.mkdirs();
        File r4 = new File(r3, "cache4.db");
        File r5 = new File(r3, "cache4.db-wal");
        File r6 = new File(r3, "cache4.db-shm");
        r4.delete();     // Catch: Exception -> L5
        r5.delete();     // Catch: Exception -> L5
        r6.delete();     // Catch: Exception -> L5
    L7:
        ArrayList r0 = new ArrayList();
        ArrayList r32 = new ArrayList();
        FileLog.d("start recover database");
        int r7 = 1;
        int r8 = 0;
        long r9 = System.currentTimeMillis();     // Catch: Exception -> L57
        SQLiteDatabase r11 = new SQLiteDatabase(r4.getPath());     // Catch: Exception -> L51
        r11.executeFast("PRAGMA secure_delete = ON").stepThis().dispose();     // Catch: Exception -> L51
        r11.executeFast("PRAGMA temp_store = MEMORY").stepThis().dispose();     // Catch: Exception -> L51
        r11.executeFast("PRAGMA journal_mode = WAL").stepThis().dispose();     // Catch: Exception -> L51
        r11.executeFast("PRAGMA journal_size_limit = 10485760").stepThis().dispose();     // Catch: Exception -> L51
        MessagesStorage.createTables(r11);     // Catch: Exception -> L51
        r11.executeFast("ATTACH DATABASE \"" + r22.getAbsolutePath() + "\" AS old;").stepThis().dispose();     // Catch: Exception -> L51
        int r2 = r11.executeInt("PRAGMA old.user_version", new Object[0]).intValue();     // Catch: Exception -> L51
        if (r2 == 179) goto L16;
        FileLog.e("can't restore database from version " + r2);     // Catch: Exception -> L14
        return false;
    L16:
        HashSet r1 = new HashSet();     // Catch: Exception -> L51
        r1.add("messages_v2");     // Catch: Exception -> L51
        r1.add("messages_holes");     // Catch: Exception -> L51
        r1.add("scheduled_messages_v2");     // Catch: Exception -> L51
        r1.add("media_holes_v2");     // Catch: Exception -> L51
        r1.add("media_v4");     // Catch: Exception -> L51
        r1.add("messages_holes_topics");     // Catch: Exception -> L51
        r1.add("messages_topics");     // Catch: Exception -> L51
        r1.add("media_topics");     // Catch: Exception -> L51
        r1.add("media_holes_topics");     // Catch: Exception -> L51
        r1.add("topics");     // Catch: Exception -> L51
        r1.add("media_counts_v2");     // Catch: Exception -> L51
        r1.add("media_counts_topics");     // Catch: Exception -> L51
        r1.add("dialogs");     // Catch: Exception -> L51
        r1.add("dialog_filter");     // Catch: Exception -> L51
        r1.add("dialog_filter_ep");     // Catch: Exception -> L51
        r1.add("dialog_filter_pin_v2");     // Catch: Exception -> L51
        int r26 = 0;
    L17:
        String[] r12 = MessagesStorage.DATABASE_TABLES;     // Catch: Exception -> L51
        if (r26 >= r12.length) goto L24;
        String r122 = r12[r26];     // Catch: Exception -> L14
        if (r1.contains(r122) == true) goto L23;
        r11.executeFast(String.format(Locale.US, "INSERT OR IGNORE INTO %s SELECT * FROM old.%s;", new Object[]{r122, r122})).stepThis().dispose();     // Catch: Exception -> L14
    L23:
        r26 = r26 + 1;
        goto L17
    L24:
        SQLiteCursor r13 = r11.queryFinalized("SELECT did FROM old.dialogs", new Object[0]);     // Catch: Exception -> L51
    L26:
        if (r13.next() == false) goto L32;
        long r123 = r13.longValue(0);     // Catch: Exception -> L14
        if (DialogObject.isEncryptedDialog(r123) == true) goto L29;
        r32.add(Long.valueOf(r123));     // Catch: Exception -> L14
        goto L26
    L29:
        r0.add(Long.valueOf(r123));     // Catch: Exception -> L14
        goto L26
    L32:
        r13.dispose();     // Catch: Exception -> L51
        int r14 = 0;
    L34:
        if (r14 >= r0.size()) goto L37;
        Long r27 = (Long) r0.get(r14);     // Catch: Exception -> L14
        r27.longValue();     // Catch: Exception -> L14
        Locale r124 = Locale.US;     // Catch: Exception -> L14
        r11.executeFast(String.format(r124, "INSERT OR IGNORE INTO messages_v2 SELECT * FROM old.messages_v2 WHERE uid = %d;", new Object[]{r27})).stepThis().dispose();     // Catch: Exception -> L14
        r11.executeFast(String.format(r124, "INSERT OR IGNORE INTO messages_holes SELECT * FROM old.messages_holes WHERE uid = %d;", new Object[]{r27})).stepThis().dispose();     // Catch: Exception -> L14
        r11.executeFast(String.format(r124, "INSERT OR IGNORE INTO media_holes_v2 SELECT * FROM old.media_holes_v2 WHERE uid = %d;", new Object[]{r27})).stepThis().dispose();     // Catch: Exception -> L14
        r11.executeFast(String.format(r124, "INSERT OR IGNORE INTO media_v4 SELECT * FROM old.media_v4 WHERE uid = %d;", new Object[]{r27})).stepThis().dispose();     // Catch: Exception -> L14
        r14 = r14 + 1;
        goto L34
    L37:
        SQLitePreparedStatement r142 = r11.executeFast("REPLACE INTO messages_holes VALUES(?, ?, ?)");     // Catch: Exception -> L51
        SQLitePreparedStatement r15 = r11.executeFast("REPLACE INTO media_holes_v2 VALUES(?, ?, ?, ?)");     // Catch: Exception -> L51
        int r02 = 0;
    L38:
        if (r02 >= r32.size()) goto L55;
        Long r16 = (Long) r32.get(r02);     // Catch: Exception -> L51
        SQLiteCursor r28 = r11.queryFinalized("SELECT last_mid_i, last_mid FROM old.dialogs WHERE did = " + r16, new Object[r8]);     // Catch: Exception -> L51
        if (r28.next() == false) goto L53;
        long r125 = r28.longValue(r8);     // Catch: Exception -> L51
        int r252 = r8;
        long r19 = r9;
        long r82 = r28.longValue(r7);     // Catch: Exception -> L49
        StringBuilder r10 = new StringBuilder();     // Catch: Exception -> L49
        int r21 = r7;
        r10.append("INSERT OR IGNORE INTO messages_v2 SELECT * FROM old.messages_v2 WHERE uid = ");     // Catch: Exception -> L47
        r10.append(r16);     // Catch: Exception -> L47
        r10.append(" AND mid IN (");     // Catch: Exception -> L47
        r10.append(r125);     // Catch: Exception -> L47
        r10.append(",");     // Catch: Exception -> L47
        r10.append(r82);     // Catch: Exception -> L47
        r10.append(")");     // Catch: Exception -> L47
        r11.executeFast(r10.toString()).stepThis().dispose();     // Catch: Exception -> L47
        MessagesStorage.createFirstHoles(r16.longValue(), r142, r15, (int) r82, 0);     // Catch: Exception -> L47
    L54:
        r28.dispose();     // Catch: Exception -> L47
        r02 = r02 + 1;     // Catch: Exception -> L47
        r8 = r252;
        r9 = r19;
        r7 = r21;
    L47:
        e = e;
    L48:
        r9 = r19;
        int r212 = r21;
        int r253 = r252;
    L59:
        FileLog.e(e);
        boolean r03 = r253 == true ? 1 : 0;
        boolean r213 = r212;
        boolean r254 = r253;
    L60:
        if (r03 == true) goto L73;
        return r254;
    L73:
        r22.delete();     // Catch: IOException -> L65
        r23.delete();     // Catch: IOException -> L65
        r24.delete();     // Catch: IOException -> L65
        AndroidUtilities.copyFile(r4, r22);     // Catch: IOException -> L65
        AndroidUtilities.copyFile(r5, r23);     // Catch: IOException -> L65
        AndroidUtilities.copyFile(r6, r24);     // Catch: IOException -> L65
        r4.delete();     // Catch: IOException -> L65
        r5.delete();     // Catch: IOException -> L65
        r6.delete();     // Catch: IOException -> L65
        FileLog.d("database recovered time " + (System.currentTimeMillis() - r9));
        return r213;
    L65:
        e = move-exception;
        e.printStackTrace();
        return r254;
    L49:
        e = e;
        r21 = r7;
        goto L48
    L53:
        r21 = r7;
        r252 = r8;
        r19 = r9;
        goto L54
    L55:
        r21 = r7;
        r252 = r8;
        r19 = r9;
        r142.dispose();     // Catch: Exception -> L47
        r15.dispose();     // Catch: Exception -> L47
        r11.executeFast("DETACH DATABASE old;").stepThis().dispose();     // Catch: Exception -> L47
        r11.close();     // Catch: Exception -> L47
        r9 = r19;
        r03 = r21 == true ? 1 : 0;
        r213 = r21;
        r254 = r252;
    L14:
        e = e;
        r212 = 1;
        r253 = 0;
    L51:
        e = e;
        r212 = r7;
        r253 = r8;
    L57:
        e = e;
        r212 = 1;
        r253 = 0;
        r9 = 0;
    L5:
        e = move-exception;
        e.printStackTrace();
        goto L7
    }
}
