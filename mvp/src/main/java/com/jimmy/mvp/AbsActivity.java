package com.jimmy.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.jimmy.mvp.external.helper.PermissionHelper;


/**
 * @author yangyoujun
 */
abstract class AbsActivity extends AppCompatActivity {

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermission(savedInstanceState);
    }

    private void checkPermission(Bundle savedInstanceState) {
        if (hasPermission()) {
            onAgree(savedInstanceState);
        } else {
            requestPermission(savedInstanceState);
        }
    }

    protected final boolean hasPermission() {
        if (!PermissionHelper.ins().has()) {
            PermissionHelper.ins().init(this);
        }
        return PermissionHelper.ins().has();
    }

    protected final void setPermission(boolean isRun) {
        PermissionHelper.ins().update(this, isRun);
    }

    // 建议复写
    protected void requestPermission(Bundle savedInstanceState) {
        setPermission(true);
        onAgree(savedInstanceState);
    }

    protected <T extends View> T findView(int resId) {
        return (T) findViewById(resId);
    }

    protected abstract void onAgree(Bundle savedInstanceState);

}
