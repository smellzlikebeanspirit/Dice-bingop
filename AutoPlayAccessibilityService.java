package com.example.bingodice;

import android.accessibilityservice.AccessibilityService;
import android.view.accessibility.AccessibilityEvent;

public class AutoPlayAccessibilityService extends AccessibilityService {
    @Override public void onAccessibilityEvent(AccessibilityEvent event) {
        // Intentionally inactive until screen-state recognition is implemented and tested.
    }
    @Override public void onInterrupt() { }
}
