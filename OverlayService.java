package com.example.bingodice;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Build;
import android.os.IBinder;
import android.provider.Settings;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class OverlayService extends Service {
    private WindowManager wm;
    private LinearLayout panel;

    @Override public int onStartCommand(Intent intent, int flags, int startId) {
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "Grant overlay permission first", Toast.LENGTH_LONG).show();
            stopSelf();
            return START_NOT_STICKY;
        }
        if (panel != null) return START_NOT_STICKY;
        wm = (WindowManager) getSystemService(WINDOW_SERVICE);
        panel = new LinearLayout(this);
        panel.setOrientation(LinearLayout.VERTICAL);
        panel.setPadding(10, 6, 10, 6);
        panel.setBackgroundColor(0xEE34205A);
        Button status = new Button(this);
        status.setText("BINGO BOT: NOT ACTIVE");
        status.setOnClickListener(v -> Toast.makeText(this,
            "Starter only: live recognition and tapping are not implemented yet.", Toast.LENGTH_LONG).show());
        panel.addView(status);
        Button close = new Button(this);
        close.setText("Close overlay");
        close.setOnClickListener(v -> stopSelf());
        panel.addView(close);
        int type = Build.VERSION.SDK_INT >= 26 ? WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY :
            WindowManager.LayoutParams.TYPE_PHONE;
        WindowManager.LayoutParams p = new WindowManager.LayoutParams(
            WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT,
            type, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
        p.gravity = Gravity.TOP | Gravity.END; p.x = 16; p.y = 160;
        wm.addView(panel, p);
        return START_STICKY;
    }
    @Override public void onDestroy() {
        if (panel != null && wm != null) { try { wm.removeView(panel); } catch (Exception ignored) {} }
        panel = null; super.onDestroy();
    }
    @Override public IBinder onBind(Intent intent) { return null; }
}
