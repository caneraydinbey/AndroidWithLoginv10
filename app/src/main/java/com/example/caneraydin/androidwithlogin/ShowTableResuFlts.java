/*
 * Decompiled with CFR 0_110.
 * 
 * Could not load the following classes:
 *  android.app.ActionBar
 *  android.app.Activity
 *  android.app.ActivityManager
 *  android.app.ActivityManager$TaskDescription
 *  android.app.Application
 *  android.app.Dialog
 *  android.app.Fragment
 *  android.app.FragmentManager
 *  android.app.LoaderManager
 *  android.app.PendingIntent
 *  android.app.SharedElementCallback
 *  android.app.TaskStackBuilder
 *  android.app.VoiceInteractor
 *  android.app.assist.AssistContent
 *  android.content.BroadcastReceiver
 *  android.content.ComponentCallbacks
 *  android.content.ComponentName
 *  android.content.ContentResolver
 *  android.content.Context
 *  android.content.Intent
 *  android.content.IntentFilter
 *  android.content.IntentSender
 *  android.content.ServiceConnection
 *  android.content.SharedPreferences
 *  android.content.pm.ApplicationInfo
 *  android.content.pm.PackageManager
 *  android.content.res.AssetManager
 *  android.content.res.ColorStateList
 *  android.content.res.Configuration
 *  android.content.res.Resources
 *  android.content.res.Resources$Theme
 *  android.content.res.TypedArray
 *  android.database.Cursor
 *  android.database.DatabaseErrorHandler
 *  android.database.sqlite.SQLiteDatabase
 *  android.database.sqlite.SQLiteDatabase$CursorFactory
 *  android.graphics.Bitmap
 *  android.graphics.Canvas
 *  android.graphics.drawable.Drawable
 *  android.media.session.MediaController
 *  android.net.Uri
 *  android.os.Bundle
 *  android.os.Handler
 *  android.os.Looper
 *  android.os.PersistableBundle
 *  android.os.UserHandle
 *  android.transition.Scene
 *  android.transition.TransitionManager
 *  android.util.AttributeSet
 *  android.util.Log
 *  android.view.ActionMode
 *  android.view.ActionMode$Callback
 *  android.view.ContextMenu
 *  android.view.ContextMenu$ContextMenuInfo
 *  android.view.Display
 *  android.view.KeyEvent
 *  android.view.LayoutInflater
 *  android.view.Menu
 *  android.view.MenuInflater
 *  android.view.MenuItem
 *  android.view.MotionEvent
 *  android.view.SearchEvent
 *  android.view.View
 *  android.view.ViewGroup
 *  android.view.ViewGroup$LayoutParams
 *  android.view.Window
 *  android.view.WindowManager
 *  android.view.WindowManager$LayoutParams
 *  android.view.accessibility.AccessibilityEvent
 *  android.widget.TableLayout
 *  android.widget.TextView
 *  android.widget.Toolbar
 *  com.android.tools.fd.runtime.IncrementalChange
 *  com.android.tools.fd.runtime.InstantReloadException
 *  java.io.File
 *  java.io.FileDescriptor
 *  java.io.FileInputStream
 *  java.io.FileOutputStream
 *  java.io.InputStream
 *  java.io.PrintWriter
 *  java.lang.Boolean
 *  java.lang.CharSequence
 *  java.lang.Class
 *  java.lang.ClassLoader
 *  java.lang.Integer
 *  java.lang.Number
 *  java.lang.Object
 *  java.lang.Runnable
 *  java.lang.String
 *  java.util.ArrayList
 *  java.util.List
 *//*

package com.example.caneraydin.androidwithlogin;

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.app.SharedElementCallback;
import android.app.TaskStackBuilder;
import android.app.VoiceInteractor;
import android.app.assist.AssistContent;
import android.content.BroadcastReceiver;
import android.content.ComponentCallbacks;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PersistableBundle;
import android.os.UserHandle;
import android.transition.Scene;
import android.transition.TransitionManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toolbar;
import com.android.tools.fd.runtime.IncrementalChange;
import com.android.tools.fd.runtime.InstantReloadException;
import com.example.caneraydin.androidwithlogin.DatabaseHandler;
import com.example.caneraydin.androidwithlogin.domains.TrainingResponse;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ShowTableRefsults
extends Activity {f
    public static volatile transient */
/* synthetic *//*
 IncrementalChange $change;
    public String TAG;
    public DatabaseHandler dbHandler;
    public TableLayout resultsTable;
    public String username;

    */
/*
     * Exception decompiling
     *//*

    public ShowTableResults() {
        // This method has failed to decompile.  When submitting a bug report, please provide this stack trace, and (if you hold appropriate legal rights) the relevant class file.
        // java.lang.IllegalStateException
        // org.benf.cfr.reader.bytecode.analysis.variables.VariableFactory.localVariable(VariableFactory.java:59)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs.mkRetrieve(Op02WithProcessedDataAndRefs.java:850)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs.createStatement(Op02WithProcessedDataAndRefs.java:897)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs$11.call(Op02WithProcessedDataAndRefs.java:1922)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs$11.call(Op02WithProcessedDataAndRefs.java:1919)
        // org.benf.cfr.reader.util.graph.AbstractGraphVisitorFI.process(AbstractGraphVisitorFI.java:63)
        // org.benf.cfr.reader.bytecode.analysis.opgraph.Op02WithProcessedDataAndRefs.convertToOp03List(Op02WithProcessedDataAndRefs.java:1931)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisInner(CodeAnalyser.java:389)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysisOrWrapFail(CodeAnalyser.java:220)
        // org.benf.cfr.reader.bytecode.CodeAnalyser.getAnalysis(CodeAnalyser.java:165)
        // org.benf.cfr.reader.entities.attributes.AttributeCode.analyse(AttributeCode.java:91)
        // org.benf.cfr.reader.entities.Method.analyse(Method.java:354)
        // org.benf.cfr.reader.entities.ClassFile.analyseMid(ClassFile.java:751)
        // org.benf.cfr.reader.entities.ClassFile.analyseTop(ClassFile.java:683)
        // org.benf.cfr.reader.Main.doJar(Main.java:128)
        // com.njlabs.showjava.processor.JavaExtractor$1.run(JavaExtractor.java:100)
        // java.lang.Thread.run(Thread.java:841)
        throw new IllegalStateException("Decompilation failed");
    }

    ShowTableResults(Object[] arrobject, InstantReloadException instantReloadException) {
        String string2 = (String)arrobject[0];
        switch (string2.hashCode()) {
            default: {
                Object[] arrobject2 = new Object[]{string2, string2.hashCode(), "com/example/caneraydin/androidwithlogin/ShowTableResults"};
                throw new InstantReloadException(String.format((String)"String switch could not find '%s' with hashcode %s in %s", (Object[])arrobject2));
            }
            case -1301693345: {
                this();
                return;
            }
            case -1230767868: 
        }
    }

    public static */
/* varargs *//*
 */
/* synthetic *//*
 Object access$super(ShowTableResults showTableResults, String string2, Object ... arrobject) {
        switch (string2.hashCode()) {
            default: {
                Object[] arrobject2 = new Object[]{string2, string2.hashCode(), "com/example/caneraydin/androidwithlogin/ShowTableResults"};
                throw new InstantReloadException(String.format((String)"String switch could not find '%s' with hashcode %s in %s", (Object[])arrobject2));
            }
            case -2147180915: {
                showTableResults.onSaveInstanceState((Bundle)arrobject[0]);
                return null;
            }
            case -2146661417: {
                showTableResults.showDialog(((Number)arrobject[0]).intValue());
                return null;
            }
            case -2128160755: {
                return showTableResults.toString();
            }
            case -2119242196: {
                return showTableResults.getSystemService((Class)arrobject[0]);
            }
            case -2116008609: {
                showTableResults.startActivity((Intent)arrobject[0], (Bundle)arrobject[1]);
                return null;
            }
            case -2067683862: {
                return new Boolean(showTableResults.onPreparePanel(((Number)arrobject[0]).intValue(), (View)arrobject[1], (Menu)arrobject[2]));
            }
            case -2042375299: {
                showTableResults.setVisible(((Boolean)arrobject[0]).booleanValue());
                return null;
            }
            case -2012646654: {
                showTableResults.onWindowFocusChanged(((Boolean)arrobject[0]).booleanValue());
                return null;
            }
            case -2012012406: {
                return new Boolean(showTableResults.dispatchKeyShortcutEvent((KeyEvent)arrobject[0]));
            }
            case -2006928146: {
                return showTableResults.createPendingResult(((Number)arrobject[0]).intValue(), (Intent)arrobject[1], ((Number)arrobject[2]).intValue());
            }
            case -2001885447: {
                showTableResults.stopLockTask();
                return null;
            }
            case -1991028125: {
                return showTableResults.getCallingActivity();
            }
            case -1989445603: {
                showTableResults.closeContextMenu();
                return null;
            }
            case -1973108070: {
                showTableResults.finishActivityFromChild((Activity)arrobject[0], ((Number)arrobject[1]).intValue());
                return null;
            }
            case -1970257541: {
                showTableResults.setVolumeControlStream(((Number)arrobject[0]).intValue());
                return null;
            }
            case -1949484118: {
                showTableResults.onRestoreInstanceState((Bundle)arrobject[0], (PersistableBundle)arrobject[1]);
                return null;
            }
            case -1935838630: {
                return showTableResults.getColorStateList(((Number)arrobject[0]).intValue());
            }
            case -1932934201: {
                showTableResults.setWallpaper((Bitmap)arrobject[0]);
                return null;
            }
            case -1929117203: {
                showTableResults.onVisibleBehindCanceled();
                return null;
            }
            case -1920723310: {
                showTableResults.sendStickyBroadcastAsUser((Intent)arrobject[0], (UserHandle)arrobject[1]);
                return null;
            }
            case -1920144170: {
                return showTableResults.getClassLoader();
            }
            case -1910120743: {
                return showTableResults.onWindowStartingActionMode((ActionMode.Callback)arrobject[0], ((Number)arrobject[1]).intValue());
            }
            case -1833452664: {
                showTableResults.startActivityFromChild((Activity)arrobject[0], (Intent)arrobject[1], ((Number)arrobject[2]).intValue(), (Bundle)arrobject[3]);
                return null;
            }
            case -1824869760: {
                return new Boolean(showTableResults.onPrepareOptionsMenu((Menu)arrobject[0]));
            }
            case -1811304260: {
                return new Boolean(showTableResults.isImmersive());
            }
            case -1796734047: {
                return new Boolean(showTableResults.showDialog(((Number)arrobject[0]).intValue(), (Bundle)arrobject[1]));
            }
            case -1786273732: {
                showTableResults.clearWallpaper();
                return null;
            }
            case -1781634548: {
                showTableResults.closeOptionsMenu();
                return null;
            }
            case -1772888905: {
                showTableResults.onSaveInstanceState((Bundle)arrobject[0], (PersistableBundle)arrobject[1]);
                return null;
            }
            case -1754231978: {
                showTableResults.setProgressBarIndeterminate(((Boolean)arrobject[0]).booleanValue());
                return null;
            }
            case -1744479168: {
                showTableResults.setMediaController((MediaController)arrobject[0]);
                return null;
            }
            case -1715303871: {
                showTableResults.setActionBar((Toolbar)arrobject[0]);
                return null;
            }
            case -1708008179: {
                showTableResults.onStateNotSaved();
                return null;
            }
            case -1689367812: {
                return showTableResults.getMainLooper();
            }
            case -1663345066: {
                showTableResults.onChildTitleChanged((Activity)arrobject[0], (CharSequence)arrobject[1]);
                return null;
            }
            case -1651465300: {
                showTableResults.onCreateNavigateUpTaskStack((TaskStackBuilder)arrobject[0]);
                return null;
            }
            case -1648999705: {
                return showTableResults.getBaseContext();
            }
            case -1635453101: {
                return new Boolean(showTableResults.onCreateOptionsMenu((Menu)arrobject[0]));
            }
            case -1630383126: {
                showTableResults.startActivityForResult((Intent)arrobject[0], ((Number)arrobject[1]).intValue(), (Bundle)arrobject[2]);
                return null;
            }
            case -1628464379: {
                showTableResults.startIntentSenderForResult((IntentSender)arrobject[0], ((Number)arrobject[1]).intValue(), (Intent)arrobject[2], ((Number)arrobject[3]).intValue(), ((Number)arrobject[4]).intValue(), ((Number)arrobject[5]).intValue());
                return null;
            }
            case -1601511123: {
                showTableResults.onPanelClosed(((Number)arrobject[0]).intValue(), (Menu)arrobject[1]);
                return null;
            }
            case -1600833221: {
                showTableResults.wait(((Number)arrobject[0]).longValue(), ((Number)arrobject[1]).intValue());
                return null;
            }
            case -1597737654: {
                showTableResults.unregisterForContextMenu((View)arrobject[0]);
                return null;
            }
            case -1596168386: {
                return showTableResults.managedQuery((Uri)arrobject[0], (String[])arrobject[1], (String)arrobject[2], (String[])arrobject[3], (String)arrobject[4]);
            }
            case -1580823932: {
                return new Boolean(showTableResults.onNavigateUpFromChild((Activity)arrobject[0]));
            }
            case -1567038203: {
                showTableResults.setFeatureDrawableUri(((Number)arrobject[0]).intValue(), (Uri)arrobject[1]);
                return null;
            }
            case -1563053631: {
                return showTableResults.getLastNonConfigurationInstance();
            }
            case -1554832987: {
                showTableResults.finalize();
                return null;
            }
            case -1545899961: {
                return new Boolean(showTableResults.releaseInstance());
            }
            case -1526884382: {
                showTableResults.onActivityReenter(((Number)arrobject[0]).intValue(), (Intent)arrobject[1]);
                return null;
            }
            case -1512679303: {
                showTableResults.startSearch((String)arrobject[0], ((Boolean)arrobject[1]).booleanValue(), (Bundle)arrobject[2], ((Boolean)arrobject[3]).booleanValue());
                return null;
            }
            case -1512649357: {
                showTableResults.onResume();
                return null;
            }
            case -1510369713: {
                return new Boolean(showTableResults.onTrackballEvent((MotionEvent)arrobject[0]));
            }
            case -1504501726: {
                showTableResults.onDestroy();
                return null;
            }
            case -1502047401: {
                showTableResults.enforceCallingOrSelfUriPermission((Uri)arrobject[0], ((Number)arrobject[1]).intValue(), (String)arrobject[2]);
                return null;
            }
            case -1485318024: {
                return showTableResults.getCodeCacheDir();
            }
            case -1483807804: {
                return showTableResults.getString(((Number)arrobject[0]).intValue(), (Object[])arrobject[1]);
            }
            case -1467836826: {
                showTableResults.enforceCallingUriPermission((Uri)arrobject[0], ((Number)arrobject[1]).intValue(), (String)arrobject[2]);
                return null;
            }
            case -1447998406: {
                return new Boolean(showTableResults.onTouchEvent((MotionEvent)arrobject[0]));
            }
            case -1446660257: {
                showTableResults.onPrepareNavigateUpTaskStack((TaskStackBuilder)arrobject[0]);
                return null;
            }
            case -1398848845: {
                showTableResults.onPostResume();
                return null;
            }
            case -1388205895: {
                return new Boolean(showTableResults.onMenuItemSelected(((Number)arrobject[0]).intValue(), (MenuItem)arrobject[1]));
            }
            case -1381466698: {
                showTableResults.stopManagingCursor((Cursor)arrobject[0]);
                return null;
            }
            case -1377658544: {
                return showTableResults.fileList();
            }
            case -1364880825: {
                showTableResults.setFeatureDrawableResource(((Number)arrobject[0]).intValue(), ((Number)arrobject[1]).intValue());
                return null;
            }
            case -1362550164: {
                return showTableResults.createConfigurationContext((Configuration)arrobject[0]);
            }
            case -1360644566: {
                return new Boolean(showTableResults.shouldUpRecreateTask((Intent)arrobject[0]));
            }
            case -1349865163: {
                return new Integer(showTableResults.checkCallingUriPermission((Uri)arrobject[0], ((Number)arrobject[1]).intValue()));
            }
            case -1344529914: {
                showTableResults.setTitle(((Number)arrobject[0]).intValue());
                return null;
            }
            case -1330876624: {
                return new Boolean(showTableResults.isRestricted());
            }
            case -1327523470: {
                return showTableResults.onCreateDescription();
            }
            case -1274460991: {
                return new Integer(showTableResults.getWallpaperDesiredMinimumHeight());
            }
            case -1264569478: {
                showTableResults.openOptionsMenu();
                return null;
            }
            case -1261436153: {
                return showTableResults.getWallpaper();
            }
            case -1258591458: {
                return showTableResults.getPackageName();
            }
            case -1237633061: {
                return new Integer(showTableResults.getTitleColor());
            }
            case -1218355944: {
                showTableResults.revokeUriPermission((Uri)arrobject[0], ((Number)arrobject[1]).intValue());
                return null;
            }
            case -1211749994: {
                return showTableResults.onCreateDialog(((Number)arrobject[0]).intValue());
            }
            case -1188103088: {
                return showTableResults.getVoiceInteractor();
            }
            case -1166127280: {
                showTableResults.notify();
                return null;
            }
            case -1145345097: {
                return new Boolean(showTableResults.dispatchPopulateAccessibilityEvent((AccessibilityEvent)arrobject[0]));
            }
            case -1141736541: {
                showTableResults.startIntentSenderFromChild((Activity)arrobject[0], (IntentSender)arrobject[1], ((Number)arrobject[2]).intValue(), (Intent)arrobject[3], ((Number)arrobject[4]).intValue(), ((Number)arrobject[5]).intValue(), ((Number)arrobject[6]).intValue());
                return null;
            }
            case -1120807196: {
                return showTableResults.getLayoutInflater();
            }
            case -1116895675: {
                return showTableResults.createPackageContext((String)arrobject[0], ((Number)arrobject[1]).intValue());
            }
            case -1115540218: {
                return showTableResults.onWindowStartingActionMode((ActionMode.Callback)arrobject[0]);
            }
            case -1115381115: {
                showTableResults.onPrepareDialog(((Number)arrobject[0]).intValue(), (Dialog)arrobject[1]);
                return null;
            }
            case -1110176650: {
                return showTableResults.obtainStyledAttributes(((Number)arrobject[0]).intValue(), (int[])arrobject[1]);
            }
            case -1107583308: {
                return showTableResults.openFileInput((String)arrobject[0]);
            }
            case -1106401236: {
                showTableResults.onAttachFragment((Fragment)arrobject[0]);
                return null;
            }
            case -1076054347: {
                showTableResults.unregisterReceiver((BroadcastReceiver)arrobject[0]);
                return null;
            }
            case -1053855858: {
                showTableResults.onActionModeStarted((ActionMode)arrobject[0]);
                return null;
            }
            case -1025252350: {
                showTableResults.setTaskDescription((ActivityManager.TaskDescription)arrobject[0]);
                return null;
            }
            case -1024841183: {
                return showTableResults.getCallingPackage();
            }
            case -1021472056: {
                showTableResults.wait(((Number)arrobject[0]).longValue());
                return null;
            }
            case -1021247703: {
                return new Boolean(showTableResults.requestWindowFeature(((Number)arrobject[0]).intValue()));
            }
            case -978110490: {
                return showTableResults.startActionMode((ActionMode.Callback)arrobject[0], ((Number)arrobject[1]).intValue());
            }
            case -976790220: {
                return showTableResults.getApplicationContext();
            }
            case -962742886: {
                showTableResults.onTrimMemory(((Number)arrobject[0]).intValue());
                return null;
            }
            case -946928039: {
                return showTableResults.startActionMode((ActionMode.Callback)arrobject[0]);
            }
            case -908335631: {
                showTableResults.startActivityFromFragment((Fragment)arrobject[0], (Intent)arrobject[1], ((Number)arrobject[2]).intValue(), (Bundle)arrobject[3]);
                return null;
            }
            case -894847381: {
                return new Boolean(showTableResults.showAssist((Bundle)arrobject[0]));
            }
            case -889668642: {
                showTableResults.postponeEnterTransition();
                return null;
            }
            case -884160602: {
                return new Boolean(showTableResults.onKeyDown(((Number)arrobject[0]).intValue(), (KeyEvent)arrobject[1]));
            }
            case -842839078: {
                return showTableResults.getIntent();
            }
            case -834926630: {
                showTableResults.finishActivity(((Number)arrobject[0]).intValue());
                return null;
            }
            case -813556434: {
                return showTableResults.getPreferences(((Number)arrobject[0]).intValue());
            }
            case -801135301: {
                showTableResults.onUserLeaveHint();
                return null;
            }
            case -783742144: {
                showTableResults.onCreateContextMenu((ContextMenu)arrobject[0], (View)arrobject[1], (ContextMenu.ContextMenuInfo)arrobject[2]);
                return null;
            }
            case -783227656: {
                showTableResults.removeStickyBroadcast((Intent)arrobject[0]);
                return null;
            }
            case -775132968: {
                return showTableResults.getComponentName();
            }
            case -771598921: {
                return showTableResults.getNoBackupFilesDir();
            }
            case -754148941: {
                showTableResults.setFeatureDrawableAlpha(((Number)arrobject[0]).intValue(), ((Number)arrobject[1]).intValue());
                return null;
            }
            case -731685428: {
                showTableResults.startActivityFromChild((Activity)arrobject[0], (Intent)arrobject[1], ((Number)arrobject[2]).intValue());
                return null;
            }
            case -730211576: {
                return new Boolean(showTableResults.onCreatePanelMenu(((Number)arrobject[0]).intValue(), (Menu)arrobject[1]));
            }
            case -712101345: {
                showTableResults.notifyAll();
                return null;
            }
            case -657193226: {
                showTableResults.onContextMenuClosed((Menu)arrobject[0]);
                return null;
            }
            case -645158372: {
                showTableResults.unregisterComponentCallbacks((ComponentCallbacks)arrobject[0]);
                return null;
            }
            case -641568046: {
                showTableResults.onCreate((Bundle)arrobject[0]);
                return null;
            }
            case -632893333: {
                return new Boolean(showTableResults.onKeyLongPress(((Number)arrobject[0]).intValue(), (KeyEvent)arrobject[1]));
            }
            case -626801900: {
                return showTableResults.getApplicationInfo();
            }
            case -623553922: {
                showTableResults.unbindService((ServiceConnection)arrobject[0]);
                return null;
            }
            case -600646834: {
                showTableResults.sendOrderedBroadcastAsUser((Intent)arrobject[0], (UserHandle)arrobject[1], (String)arrobject[2], (BroadcastReceiver)arrobject[3], (Handler)arrobject[4], ((Number)arrobject[5]).intValue(), (String)arrobject[6], (Bundle)arrobject[7]);
                return null;
            }
            case -593599514: {
                return new Integer(showTableResults.checkCallingOrSelfUriPermission((Uri)arrobject[0], ((Number)arrobject[1]).intValue()));
            }
            case -583985582: {
                return showTableResults.getObbDir();
            }
            case -561536166: {
                return showTableResults.getExternalCacheDir();
            }
            case -543045568: {
                showTableResults.requestPermissions((String[])arrobject[0], ((Number)arrobject[1]).intValue());
                return null;
            }
            case -533115309: {
                showTableResults.sendBroadcastAsUser((Intent)arrobject[0], (UserHandle)arrobject[1], (String)arrobject[2]);
                return null;
            }
            case -523441978: {
                showTableResults.setEnterSharedElementCallback((SharedElementCallback)arrobject[0]);
                return null;
            }
            case -521108148: {
                return new Boolean(showTableResults.onGenericMotionEvent((MotionEvent)arrobject[0]));
            }
            case -485599684: {
                showTableResults.takeKeyEvents(((Boolean)arrobject[0]).booleanValue());
                return null;
            }
            case -451962688: {
                showTableResults.onRestoreInstanceState((Bundle)arrobject[0]);
                return null;
            }
            case -449658531: {
                showTableResults.onTitleChanged((CharSequence)arrobject[0], ((Number)arrobject[1]).intValue());
                return null;
            }
            case -445489924: {
                showTableResults.onPostCreate((Bundle)arrobject[0], (PersistableBundle)arrobject[1]);
                return null;
            }
            case -440799600: {
                return showTableResults.getFragmentManager();
            }
            case -432656633: {
                showTableResults.overridePendingTransition(((Number)arrobject[0]).intValue(), ((Number)arrobject[1]).intValue());
                return null;
            }
            case -427921124: {
                showTableResults.startActivities((Intent[])arrobject[0]);
                return null;
            }
            case -412857127: {
                return showTableResults.onCreateView((String)arrobject[0], (Context)arrobject[1], (AttributeSet)arrobject[2]);
            }
            case -406270088: {
                return showTableResults.openOrCreateDatabase((String)arrobject[0], ((Number)arrobject[1]).intValue(), (SQLiteDatabase.CursorFactory)arrobject[2]);
            }
            case -406251089: {
                showTableResults.startIntentSenderForResult((IntentSender)arrobject[0], ((Number)arrobject[1]).intValue(), (Intent)arrobject[2], ((Number)arrobject[3]).intValue(), ((Number)arrobject[4]).intValue(), ((Number)arrobject[5]).intValue(), (Bundle)arrobject[6]);
                return null;
            }
            case -389284119: {
                return showTableResults.getExternalFilesDirs((String)arrobject[0]);
            }
            case -386103215: {
                showTableResults.startIntentSenderFromChild((Activity)arrobject[0], (IntentSender)arrobject[1], ((Number)arrobject[2]).intValue(), (Intent)arrobject[3], ((Number)arrobject[4]).intValue(), ((Number)arrobject[5]).intValue(), ((Number)arrobject[6]).intValue(), (Bundle)arrobject[7]);
                return null;
            }
            case -370434026: {
                return showTableResults.startService((Intent)arrobject[0]);
            }
            case -367936228: {
                return new Boolean(showTableResults.deleteFile((String)arrobject[0]));
            }
            case -349229044: {
                showTableResults.onConfigurationChanged((Configuration)arrobject[0]);
                return null;
            }
            case -328499411: {
                showTableResults.runOnUiThread((Runnable)arrobject[0]);
                return null;
            }
            case -322248589: {
                return new Boolean(showTableResults.onCreateThumbnail((Bitmap)arrobject[0], (Canvas)arrobject[1]));
            }
            case -315360737: {
                return new Boolean(showTableResults.onKeyUp(((Number)arrobject[0]).intValue(), (KeyEvent)arrobject[1]));
            }
            case -299620925: {
                showTableResults.registerComponentCallbacks((ComponentCallbacks)arrobject[0]);
                return null;
            }
            case -294950962: {
                showTableResults.setFeatureDrawable(((Number)arrobject[0]).intValue(), (Drawable)arrobject[1]);
                return null;
            }
            case -293022599: {
                showTableResults.setContentTransitionManager((TransitionManager)arrobject[0]);
                return null;
            }
            case -280716353: {
                return new Boolean(showTableResults.bindService((Intent)arrobject[0], (ServiceConnection)arrobject[1], ((Number)arrobject[2]).intValue()));
            }
            case -261153143: {
                return new Boolean(showTableResults.startNextMatchingActivity((Intent)arrobject[0]));
            }
            case -257219777: {
                return showTableResults.onProvideReferrer();
            }
            case -256744805: {
                return new Integer(showTableResults.checkPermission((String)arrobject[0], ((Number)arrobject[1]).intValue(), ((Number)arrobject[2]).intValue()));
            }
            case -242751949: {
                return new Boolean(showTableResults.startNextMatchingActivity((Intent)arrobject[0], (Bundle)arrobject[1]));
            }
            case -237096951: {
                return showTableResults.onRetainNonConfigurationInstance();
            }
            case -237085799: {
                return new Boolean(showTableResults.moveTaskToBack(((Boolean)arrobject[0]).booleanValue()));
            }
            case -232347918: {
                return showTableResults.getDrawable(((Number)arrobject[0]).intValue());
            }
            case -212501619: {
                showTableResults.onActionModeFinished((ActionMode)arrobject[0]);
                return null;
            }
            case -201279008: {
                showTableResults.showLockTaskEscapeMessage();
                return null;
            }
            case -199913269: {
                return showTableResults.getContentTransitionManager();
            }
            case -191939775: {
                showTableResults.onContentChanged();
                return null;
            }
            case -182141982: {
                showTableResults.setIntent((Intent)arrobject[0]);
                return null;
            }
            case -159635284: {
                showTableResults.setWallpaper((InputStream)arrobject[0]);
                return null;
            }
            case -157574108: {
                showTableResults.setProgressBarIndeterminateVisibility(((Boolean)arrobject[0]).booleanValue());
                return null;
            }
            case -129020188: {
                return new Boolean(showTableResults.onContextItemSelected((MenuItem)arrobject[0]));
            }
            case -124775396: {
                return new Boolean(showTableResults.startActivityIfNeeded((Intent)arrobject[0], ((Number)arrobject[1]).intValue(), (Bundle)arrobject[2]));
            }
            case -92595176: {
                showTableResults.finishFromChild((Activity)arrobject[0]);
                return null;
            }
            case -61650832: {
                return showTableResults.getLoaderManager();
            }
            case -51098230: {
                showTableResults.sendOrderedBroadcast((Intent)arrobject[0], (String)arrobject[1]);
                return null;
            }
            case -49297836: {
                showTableResults.setImmersive(((Boolean)arrobject[0]).booleanValue());
                return null;
            }
            case -40033047: {
                return showTableResults.getSharedPreferences((String)arrobject[0], ((Number)arrobject[1]).intValue());
            }
            case 28656581: {
                return showTableResults.getExternalFilesDir((String)arrobject[0]);
            }
            case 33078750: {
                showTableResults.onApplyThemeResource((Resources.Theme)arrobject[0], ((Number)arrobject[1]).intValue(), ((Boolean)arrobject[2]).booleanValue());
                return null;
            }
            case 66113232: {
                showTableResults.setExitSharedElementCallback((SharedElementCallback)arrobject[0]);
                return null;
            }
            case 82670668: {
                showTableResults.enforcePermission((String)arrobject[0], ((Number)arrobject[1]).intValue(), ((Number)arrobject[2]).intValue(), (String)arrobject[3]);
                return null;
            }
            case 105425515: {
                return showTableResults.obtainStyledAttributes((AttributeSet)arrobject[0], (int[])arrobject[1], ((Number)arrobject[2]).intValue(), ((Number)arrobject[3]).intValue());
            }
            case 114588953: {
                showTableResults.setDefaultKeyMode(((Number)arrobject[0]).intValue());
                return null;
            }
            case 116272469: {
                showTableResults.startActivity((Intent)arrobject[0]);
                return null;
            }
            case 127696083: {
                return showTableResults.getReferrer();
            }
            case 133027723: {
                showTableResults.onWindowAttributesChanged((WindowManager.LayoutParams)arrobject[0]);
                return null;
            }
            case 134332931: {
                showTableResults.startActivityFromFragment((Fragment)arrobject[0], (Intent)arrobject[1], ((Number)arrobject[2]).intValue());
                return null;
            }
            case 143326307: {
                showTableResults.onBackPressed();
                return null;
            }
            case 154887605: {
                showTableResults.sendStickyOrderedBroadcast((Intent)arrobject[0], (BroadcastReceiver)arrobject[1], (Handler)arrobject[2], ((Number)arrobject[3]).intValue(), (String)arrobject[4], (Bundle)arrobject[5]);
                return null;
            }
            case 179534607: {
                showTableResults.dump((String)arrobject[0], (FileDescriptor)arrobject[1], (PrintWriter)arrobject[2], (String[])arrobject[3]);
                return null;
            }
            case 188604040: {
                showTableResults.onStop();
                return null;
            }
            case 189950177: {
                return showTableResults.getParent();
            }
            case 192936453: {
                return showTableResults.getSystemServiceName((Class)arrobject[0]);
            }
            case 201261558: {
                return showTableResults.getClass();
            }
            case 215736879: {
                showTableResults.onPrepareDialog(((Number)arrobject[0]).intValue(), (Dialog)arrobject[1], (Bundle)arrobject[2]);
                return null;
            }
            case 223198551: {
                return new Boolean(showTableResults.isVoiceInteractionRoot());
            }
            case 224771354: {
                return new Boolean(showTableResults.isDestroyed());
            }
            case 244142972: {
                showTableResults.wait();
                return null;
            }
            case 256941319: {
                return new Integer(showTableResults.getVolumeControlStream());
            }
            case 264300484: {
                return showTableResults.getMenuInflater();
            }
            case 277776542: {
                return showTableResults.getCurrentFocus();
            }
            case 323739864: {
                showTableResults.setRequestedOrientation(((Number)arrobject[0]).intValue());
                return null;
            }
            case 323816587: {
                return showTableResults.getString(((Number)arrobject[0]).intValue());
            }
            case 342401934: {
                showTableResults.removeStickyBroadcastAsUser((Intent)arrobject[0], (UserHandle)arrobject[1]);
                return null;
            }
            case 356149802: {
                showTableResults.dismissDialog(((Number)arrobject[0]).intValue());
                return null;
            }
            case 361598322: {
                return new Boolean(showTableResults.startInstrumentation((ComponentName)arrobject[0], (String)arrobject[1], (Bundle)arrobject[2]));
            }
            case 361690892: {
                return showTableResults.getExternalMediaDirs();
            }
            case 366229479: {
                return new Boolean(showTableResults.onMenuOpened(((Number)arrobject[0]).intValue(), (Menu)arrobject[1]));
            }
            case 369126896: {
                return new Integer(showTableResults.getTaskId());
            }
            case 389417251: {
                showTableResults.triggerSearch((String)arrobject[0], (Bundle)arrobject[1]);
                return null;
            }
            case 402547913: {
                showTableResults.sendBroadcastAsUser((Intent)arrobject[0], (UserHandle)arrobject[1]);
                return null;
            }
            case 430677542: {
                return showTableResults.getMediaController();
            }
            case 432240098: {
                showTableResults.finishAffinity();
                return null;
            }
            case 468603210: {
                return new Boolean(showTableResults.onKeyShortcut(((Number)arrobject[0]).intValue(), (KeyEvent)arrobject[1]));
            }
            case 488359506: {
                return showTableResults.getPackageManager();
            }
            case 498687345: {
                return showTableResults.getTitle();
            }
            case 506020951: {
                return showTableResults.getPackageCodePath();
            }
            case 508196455: {
                showTableResults.sendBroadcast((Intent)arrobject[0], (String)arrobject[1]);
                return null;
            }
            case 514894248: {
                showTableResults.attachBaseContext((Context)arrobject[0]);
                return null;
            }
            case 533817968: {
                return new Boolean(showTableResults.isFinishing());
            }
            case 554472204: {
                showTableResults.enforceCallingPermission((String)arrobject[0], (String)arrobject[1]);
                return null;
            }
            case 557411162: {
                return showTableResults.getSearchEvent();
            }
            case 592925556: {
                showTableResults.sendStickyBroadcast((Intent)arrobject[0]);
                return null;
            }
            case 595642473: {
                showTableResults.invalidateOptionsMenu();
                return null;
            }
            case 602429250: {
                showTableResults.onRequestPermissionsResult(((Number)arrobject[0]).intValue(), (String[])arrobject[1], (int[])arrobject[2]);
                return null;
            }
            case 603335571: {
                showTableResults.setFinishOnTouchOutside(((Boolean)arrobject[0]).booleanValue());
                return null;
            }
            case 630071634: {
                showTableResults.enforceUriPermission((Uri)arrobject[0], (String)arrobject[1], (String)arrobject[2], ((Number)arrobject[3]).intValue(), ((Number)arrobject[4]).intValue(), ((Number)arrobject[5]).intValue(), (String)arrobject[6]);
                return null;
            }
            case 647580201: {
                return new Integer(showTableResults.checkUriPermission((Uri)arrobject[0], ((Number)arrobject[1]).intValue(), ((Number)arrobject[2]).intValue(), ((Number)arrobject[3]).intValue()));
            }
            case 663239282: {
                return new Integer(showTableResults.getRequestedOrientation());
            }
            case 664773775: {
                return showTableResults.databaseList();
            }
            case 677056085: {
                return new Boolean(showTableResults.isVoiceInteraction());
            }
            case 689402016: {
                return new Integer(showTableResults.getWallpaperDesiredMinimumWidth());
            }
            case 698237531: {
                return new Boolean(showTableResults.deleteDatabase((String)arrobject[0]));
            }
            case 712753272: {
                return showTableResults.getContentResolver();
            }
            case 743397380: {
                showTableResults.startIntentSender((IntentSender)arrobject[0], (Intent)arrobject[1], ((Number)arrobject[2]).intValue(), ((Number)arrobject[3]).intValue(), ((Number)arrobject[4]).intValue());
                return null;
            }
            case 757106516: {
                return new Boolean(showTableResults.requestVisibleBehind(((Boolean)arrobject[0]).booleanValue()));
            }
            case 761478486: {
                return showTableResults.getPackageResourcePath();
            }
            case 782036510: {
                return new Boolean(showTableResults.shouldShowRequestPermissionRationale((String)arrobject[0]));
            }
            case 797441118: {
                showTableResults.onPause();
                return null;
            }
            case 812243512: {
                showTableResults.onProvideAssistData((Bundle)arrobject[0]);
                return null;
            }
            case 819840342: {
                showTableResults.startManagingCursor((Cursor)arrobject[0]);
                return null;
            }
            case 850345319: {
                showTableResults.openContextMenu((View)arrobject[0]);
                return null;
            }
            case 885118356: {
                showTableResults.finishAndRemoveTask();
                return null;
            }
            case 902425770: {
                showTableResults.startActivityForResult((Intent)arrobject[0], ((Number)arrobject[1]).intValue());
                return null;
            }
            case 921927566: {
                showTableResults.setContentView((View)arrobject[0], (ViewGroup.LayoutParams)arrobject[1]);
                return null;
            }
            case 922616583: {
                return showTableResults.getResources();
            }
            case 944385474: {
                return new Integer(showTableResults.getColor(((Number)arrobject[0]).intValue()));
            }
            case 949399698: {
                showTableResults.onDetachedFromWindow();
                return null;
            }
            case 957566518: {
                return new Boolean(showTableResults.onNavigateUp());
            }
            case 966976865: {
                return showTableResults.openFileOutput((String)arrobject[0], ((Number)arrobject[1]).intValue());
            }
            case 968107227: {
                showTableResults.reportFullyDrawn();
                return null;
            }
            case 975965057: {
                return showTableResults.getParentActivityIntent();
            }
            case 978076981: {
                showTableResults.setTheme(((Number)arrobject[0]).intValue());
                return null;
            }
            case 981214139: {
                showTableResults.enforceCallingOrSelfPermission((String)arrobject[0], (String)arrobject[1]);
                return null;
            }
            case 1025294348: {
                showTableResults.onProvideAssistContent((AssistContent)arrobject[0]);
                return null;
            }
            case 1047335636: {
                return showTableResults.onCreateDialog(((Number)arrobject[0]).intValue(), (Bundle)arrobject[1]);
            }
            case 1049763651: {
                showTableResults.registerForContextMenu((View)arrobject[0]);
                return null;
            }
            case 1055225029: {
                showTableResults.finishAfterTransition();
                return null;
            }
            case 1062055011: {
                return showTableResults.getContentScene();
            }
            case 1065143297: {
                return showTableResults.getText(((Number)arrobject[0]).intValue());
            }
            case 1067388306: {
                return showTableResults.getDir((String)arrobject[0], ((Number)arrobject[1]).intValue());
            }
            case 1070024805: {
                return new Boolean(showTableResults.navigateUpToFromChild((Activity)arrobject[0], (Intent)arrobject[1]));
            }
            case 1070661222: {
                return showTableResults.registerReceiver((BroadcastReceiver)arrobject[0], (IntentFilter)arrobject[1], (String)arrobject[2], (Handler)arrobject[3]);
            }
            case 1082051997: {
                return new Boolean(showTableResults.isChild());
            }
            case 1084679948: {
                return showTableResults.peekWallpaper();
            }
            case 1106102624: {
                return showTableResults.registerReceiver((BroadcastReceiver)arrobject[0], (IntentFilter)arrobject[1]);
            }
            case 1111017016: {
                showTableResults.startActivities((Intent[])arrobject[0], (Bundle)arrobject[1]);
                return null;
            }
            case 1123733599: {
                return new Boolean(showTableResults.isChangingConfigurations());
            }
            case 1150324634: {
                showTableResults.finish();
                return null;
            }
            case 1182266256: {
                return showTableResults.getActionBar();
            }
            case 1199311782: {
                return showTableResults.getDatabasePath((String)arrobject[0]);
            }
            case 1208144271: {
                showTableResults.setTitleColor(((Number)arrobject[0]).intValue());
                return null;
            }
            case 1220607435: {
                return showTableResults.getLocalClassName();
            }
            case 1222830949: {
                return showTableResults.getFileStreamPath((String)arrobject[0]);
            }
            case 1246973220: {
                return new Boolean(showTableResults.dispatchKeyEvent((KeyEvent)arrobject[0]));
            }
            case 1257714799: {
                showTableResults.onActivityResult(((Number)arrobject[0]).intValue(), ((Number)arrobject[1]).intValue(), (Intent)arrobject[2]);
                return null;
            }
            case 1260281423: {
                return new Boolean(showTableResults.navigateUpTo((Intent)arrobject[0]));
            }
            case 1264052993: {
                showTableResults.onNewIntent((Intent)arrobject[0]);
                return null;
            }
            case 1270686685: {
                showTableResults.onLowMemory();
                return null;
            }
            case 1278144004: {
                return showTableResults.getWindowManager();
            }
            case 1281559479: {
                showTableResults.onRestart();
                return null;
            }
            case 1284851429: {
                return showTableResults.getCacheDir();
            }
            case 1288659661: {
                showTableResults.applyOverrideConfiguration((Configuration)arrobject[0]);
                return null;
            }
            case 1298332573: {
                showTableResults.setResult(((Number)arrobject[0]).intValue());
                return null;
            }
            case 1308233817: {
                return new Boolean(showTableResults.onSearchRequested((SearchEvent)arrobject[0]));
            }
            case 1320984464: {
                return showTableResults.onCreatePanelView(((Number)arrobject[0]).intValue());
            }
            case 1340357437: {
                return showTableResults.onCreateView((View)arrobject[0], (String)arrobject[1], (Context)arrobject[2], (AttributeSet)arrobject[3]);
            }
            case 1391904137: {
                return new Boolean(showTableResults.onKeyMultiple(((Number)arrobject[0]).intValue(), ((Number)arrobject[1]).intValue(), (KeyEvent)arrobject[2]));
            }
            case 1403628309: {
                return new Integer(showTableResults.hashCode());
            }
            case 1428545341: {
                showTableResults.setSecondaryProgress(((Number)arrobject[0]).intValue());
                return null;
            }
            case 1437166753: {
                showTableResults.sendStickyOrderedBroadcastAsUser((Intent)arrobject[0], (UserHandle)arrobject[1], (BroadcastReceiver)arrobject[2], (Handler)arrobject[3], ((Number)arrobject[4]).intValue(), (String)arrobject[5], (Bundle)arrobject[6]);
                return null;
            }
            case 1464460400: {
                return showTableResults.getApplication();
            }
            case 1474035477: {
                return new Integer(showTableResults.checkUriPermission((Uri)arrobject[0], (String)arrobject[1], (String)arrobject[2], ((Number)arrobject[3]).intValue(), ((Number)arrobject[4]).intValue(), ((Number)arrobject[5]).intValue()));
            }
            case 1479324538: {
                return new Integer(showTableResults.getChangingConfigurations());
            }
            case 1515366061: {
                return showTableResults.obtainStyledAttributes((int[])arrobject[0]);
            }
            case 1525338969: {
                showTableResults.startLockTask();
                return null;
            }
            case 1538025040: {
                showTableResults.startIntentSender((IntentSender)arrobject[0], (Intent)arrobject[1], ((Number)arrobject[2]).intValue(), ((Number)arrobject[3]).intValue(), ((Number)arrobject[4]).intValue(), (Bundle)arrobject[5]);
                return null;
            }
            case 1542479423: {
                return showTableResults.createDisplayContext((Display)arrobject[0]);
            }
            case 1553572081: {
                return showTableResults.getSystemService((String)arrobject[0]);
            }
            case 1609189330: {
                return new Boolean(showTableResults.onSearchRequested());
            }
            case 1609275863: {
                showTableResults.setProgressBarVisibility(((Boolean)arrobject[0]).booleanValue());
                return null;
            }
            case 1609329947: {
                return new Boolean(showTableResults.stopService((Intent)arrobject[0]));
            }
            case 1614070695: {
                return new Integer(showTableResults.checkSelfPermission((String)arrobject[0]));
            }
            case 1615975956: {
                showTableResults.sendOrderedBroadcast((Intent)arrobject[0], (String)arrobject[1], (BroadcastReceiver)arrobject[2], (Handler)arrobject[3], ((Number)arrobject[4]).intValue(), (String)arrobject[5], (Bundle)arrobject[6]);
                return null;
            }
            case 1617604079: {
                showTableResults.onUserInteraction();
                return null;
            }
            case 1626033557: {
                showTableResults.onAttachedToWindow();
                return null;
            }
            case 1627836879: {
                return new Integer(showTableResults.checkCallingPermission((String)arrobject[0]));
            }
            case 1629601113: {
                return new Boolean(showTableResults.hasWindowFocus());
            }
            case 1683598447: {
                showTableResults.setContentView((View)arrobject[0]);
                return null;
            }
            case 1685568843: {
                return showTableResults.obtainStyledAttributes((AttributeSet)arrobject[0], (int[])arrobject[1]);
            }
            case 1718256830: {
                return new Integer(showTableResults.checkCallingOrSelfPermission((String)arrobject[0]));
            }
            case 1765014364: {
                return new Boolean(showTableResults.isTaskRoot());
            }
            case 1770587104: {
                showTableResults.setContentView(((Number)arrobject[0]).intValue());
                return null;
            }
            case 1814730534: {
                return new Boolean(showTableResults.equals(arrobject[0]));
            }
            case 1824337728: {
                return new Boolean(showTableResults.startActivityIfNeeded((Intent)arrobject[0], ((Number)arrobject[1]).intValue()));
            }
            case 1835627922: {
                showTableResults.onPostCreate((Bundle)arrobject[0]);
                return null;
            }
            case 1842319466: {
                return new Boolean(showTableResults.dispatchTrackballEvent((MotionEvent)arrobject[0]));
            }
            case 1867171439: {
                showTableResults.addContentView((View)arrobject[0], (ViewGroup.LayoutParams)arrobject[1]);
                return null;
            }
            case 1874373038: {
                return showTableResults.findViewById(((Number)arrobject[0]).intValue());
            }
            case 1876348903: {
                showTableResults.onOptionsMenuClosed((Menu)arrobject[0]);
                return null;
            }
            case 1877256764: {
                showTableResults.onCreate((Bundle)arrobject[0], (PersistableBundle)arrobject[1]);
                return null;
            }
            case 1879378497: {
                showTableResults.setTitle((CharSequence)arrobject[0]);
                return null;
            }
            case 1893326613: {
                return new Boolean(showTableResults.onOptionsItemSelected((MenuItem)arrobject[0]));
            }
            case 1908229466: {
                return showTableResults.getFilesDir();
            }
            case 1940660514: {
                return showTableResults.getObbDirs();
            }
            case 1944176744: {
                showTableResults.startPostponedEnterTransition();
                return null;
            }
            case 1984083782: {
                return showTableResults.getTheme();
            }
            case 1992548598: {
                return showTableResults.openOrCreateDatabase((String)arrobject[0], ((Number)arrobject[1]).intValue(), (SQLiteDatabase.CursorFactory)arrobject[2], (DatabaseErrorHandler)arrobject[3]);
            }
            case 1996274778: {
                showTableResults.setResult(((Number)arrobject[0]).intValue(), (Intent)arrobject[1]);
                return null;
            }
            case 2000782320: {
                showTableResults.removeDialog(((Number)arrobject[0]).intValue());
                return null;
            }
            case 2008219788: {
                showTableResults.grantUriPermission((String)arrobject[0], (Uri)arrobject[1], ((Number)arrobject[2]).intValue());
                return null;
            }
            case 2025021518: {
                return showTableResults.clone();
            }
            case 2038901213: {
                showTableResults.sendBroadcast((Intent)arrobject[0]);
                return null;
            }
            case 2039712810: {
                return showTableResults.getExternalCacheDirs();
            }
            case 2051394150: {
                showTableResults.enforceUriPermission((Uri)arrobject[0], ((Number)arrobject[1]).intValue(), ((Number)arrobject[2]).intValue(), ((Number)arrobject[3]).intValue(), (String)arrobject[4]);
                return null;
            }
            case 2058746343: {
                return new Boolean(showTableResults.dispatchGenericMotionEvent((MotionEvent)arrobject[0]));
            }
            case 2064626307: {
                return showTableResults.getAssets();
            }
            case 2066955307: {
                showTableResults.onEnterAnimationComplete();
                return null;
            }
            case 2075560917: {
                return new Boolean(showTableResults.dispatchTouchEvent((MotionEvent)arrobject[0]));
            }
            case 2079339533: {
                showTableResults.setProgress(((Number)arrobject[0]).intValue());
                return null;
            }
            case 2095084022: {
                showTableResults.recreate();
                return null;
            }
            case 2133689546: {
                showTableResults.onStart();
                return null;
            }
            case 2136601924: 
        }
        return showTableResults.getWindow();
    }

    public void onCreate(Bundle bundle) {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("onCreate.(Landroid/os/Bundle;)V", new Object[]{this, bundle});
            return;
        }
        Log.d((String)this.TAG, (String)"SHOWTABLERESULTS oncreate");
        super.onCreate(bundle);
        this.setContentView(2130968606);
        this.username = this.getIntent().getExtras().getString("username");
        this.resultsTable = (TableLayout)this.findViewById(2131558510);
        this.dbHandler = new DatabaseHandler((Context)this);
    }

    public void onResume() {
        IncrementalChange incrementalChange = $change;
        if (incrementalChange != null) {
            incrementalChange.access$dispatch("onResume.()V", new Object[]{this});
            return;
        }
        Log.d((String)this.TAG, (String)"SHOWTABLERESULTS onresume");
        super.onResume();
        new ArrayList();
        List<String> list = this.dbHandler.getAllObjectObjectName(this.username);
        Log.v((String)this.TAG, (String)("size :" + list.size()));
        for (int i = 0; i < list.size(); ++i) {
            View view = this.getLayoutInflater().inflate(2130968620, null);
            String string2 = (String)list.get(i);
            ((TextView)view.findViewById(2131558526)).setText((CharSequence)string2);
            TextView[] arrtextView = new TextView[9];
            new TextView((Context)this);
            new TextView((Context)this);
            new TextView((Context)this);
            new TextView((Context)this);
            new TextView((Context)this);
            new TextView((Context)this);
            new TextView((Context)this);
            new TextView((Context)this);
            new TextView((Context)this);
            arrtextView[0] = (TextView)view.findViewById(2131558531);
            arrtextView[1] = (TextView)view.findViewById(2131558532);
            arrtextView[2] = (TextView)view.findViewById(2131558533);
            arrtextView[3] = (TextView)view.findViewById(2131558534);
            arrtextView[4] = (TextView)view.findViewById(2131558535);
            arrtextView[5] = (TextView)view.findViewById(2131558536);
            arrtextView[6] = (TextView)view.findViewById(2131558537);
            arrtextView[7] = (TextView)view.findViewById(2131558538);
            arrtextView[8] = (TextView)view.findViewById(2131558539);
            for (int j = 1; j < 4; ++j) {
                for (int k = 1; k < 4; ++k) {
                    Log.d((String)this.TAG, (String)" -");
                    Log.d((String)this.TAG, (String)("k, , i, j total " + k + " " + j + " " + i + " " + (-1 + (k + 3 * (j - 1)))));
                    arrtextView[-1 + (k + 3 * (j - 1))].setText((CharSequence)String.valueOf((int)this.dbHandler.getCountForTable(j, (k + k) % 3, string2, this.username)));
                }
            }
            this.resultsTable.addView(view);
        }
        View view = this.getLayoutInflater().inflate(2130968620, null);
        int n = this.dbHandler.getTotalCountForTable(1, 2, this.username);
        int n2 = this.dbHandler.getTotalCountForTable(1, 1, this.username);
        int n3 = this.dbHandler.getTotalCountForTable(1, 0, this.username);
        n3 + (n + n3);
        int n4 = this.dbHandler.getTotalCountForTable(2, 2, this.username);
        int n5 = this.dbHandler.getTotalCountForTable(2, 1, this.username);
        int n6 = this.dbHandler.getTotalCountForTable(2, 0, this.username);
        n6 + (n4 + n5);
        int n7 = this.dbHandler.getTotalCountForTable(3, 2, this.username);
        int n8 = this.dbHandler.getTotalCountForTable(3, 1, this.username);
        int n9 = this.dbHandler.getTotalCountForTable(3, 0, this.username);
        int n10 = n9 + (n7 + n8);
        ((TextView)view.findViewById(2131558531)).setText((CharSequence)String.valueOf((int)n));
        ((TextView)view.findViewById(2131558532)).setText((CharSequence)String.valueOf((int)n2));
        ((TextView)view.findViewById(2131558533)).setText((CharSequence)String.valueOf((int)n3));
        ((TextView)view.findViewById(2131558534)).setText((CharSequence)String.valueOf((int)n4));
        ((TextView)view.findViewById(2131558535)).setText((CharSequence)String.valueOf((int)n5));
        ((TextView)view.findViewById(2131558536)).setText((CharSequence)String.valueOf((int)n6));
        ((TextView)view.findViewById(2131558537)).setText((CharSequence)String.valueOf((int)n7));
        ((TextView)view.findViewById(2131558538)).setText((CharSequence)String.valueOf((int)n8));
        ((TextView)view.findViewById(2131558539)).setText((CharSequence)String.valueOf((int)n9));
        this.resultsTable.addView(view);
        View view2 = this.getLayoutInflater().inflate(2130968619, null);
        int n11 = 0;
        if (n10 != 0) {
            n11 = 100 * (n7 + n8 / 2) / n10;
        }
        ((TextView)view2.findViewById(2131558527)).setText((CharSequence)String.valueOf((int)0));
        ((TextView)view2.findViewById(2131558528)).setText((CharSequence)String.valueOf((int)0));
        ((TextView)view2.findViewById(2131558529)).setText((CharSequence)String.valueOf((int)n11));
        this.resultsTable.addView(view2);
        this.dbHandler.getCounttForTable();
        this.dbHandler.getAllTrainingResponse();
    }
}

*/
