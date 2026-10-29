package com.example.bingodice;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout root = new LinearLayout(this);
        root.setOrientation(LinearLayout.VERTICAL);
        root.setPadding(32, 40, 32, 32);
        root.setGravity(Gravity.CENTER_HORIZONTAL);

        TextView title = new TextView(this);
        title.setText("Bingo Dice AutoPlay — starter");
        title.setTextSize(24);
        root.addView(title);

        TextView info = new TextView(this);
        info.setText("\nThis is a starter app, not a completed bot yet. The move-selection engine is included, but live screen recognition, automated taps, turn detection, and ad recovery still need implementation and testing.\n\nThe intended rules are: never reroll; play a legal number if available; pass only if no legal number exists.");
        info.setTextSize(16);
        root.addView(info);

        Button overlay = new Button(this);
        overlay.setText("Grant overlay permission");
        overlay.setOnClickListener(v -> {
            if (!Settings.canDrawOverlays(this)) {
                startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName())));
            } else {
                startService(new Intent(this, OverlayService.class));
            }
        });
        root.addView(overlay);

        Button access = new Button(this);
        access.setText("Open Accessibility Settings");
        access.setOnClickListener(v -> startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)));
        root.addView(access);

        Button show = new Button(this);
        show.setText("Show floating control");
        show.setOnClickListener(v -> {
            if (Settings.canDrawOverlays(this)) startService(new Intent(this, OverlayService.class));
        });
        root.addView(show);
        setContentView(root);
    }
}
